package com.braintreepayments.api;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Parcel;
import android.text.TextUtils;
import android.util.Base64;
import com.braintreepayments.api.exceptions.BraintreeException;
import com.braintreepayments.api.exceptions.BrowserSwitchException;
import com.braintreepayments.api.exceptions.ErrorWithResponse;
import com.braintreepayments.api.interfaces.ConfigurationListener;
import com.braintreepayments.api.interfaces.HttpResponseCallback;
import com.braintreepayments.api.interfaces.PayPalApprovalCallback;
import com.braintreepayments.api.interfaces.PayPalApprovalHandler;
import com.braintreepayments.api.interfaces.PaymentMethodNonceCallback;
import com.braintreepayments.api.internal.AppHelper;
import com.braintreepayments.api.internal.BraintreeSharedPreferences;
import com.braintreepayments.api.internal.ManifestValidator;
import com.braintreepayments.api.models.ClientToken;
import com.braintreepayments.api.models.Configuration;
import com.braintreepayments.api.models.PayPalAccountBuilder;
import com.braintreepayments.api.models.PayPalAccountNonce;
import com.braintreepayments.api.models.PayPalPaymentResource;
import com.braintreepayments.api.models.PayPalRequest;
import com.braintreepayments.api.models.PaymentMethodNonce;
import com.braintreepayments.api.models.PostalAddress;
import com.paypal.android.sdk.onetouch.core.AuthorizationRequest;
import com.paypal.android.sdk.onetouch.core.BillingAgreementRequest;
import com.paypal.android.sdk.onetouch.core.CheckoutRequest;
import com.paypal.android.sdk.onetouch.core.PayPalOneTouchCore;
import com.paypal.android.sdk.onetouch.core.Request;
import com.paypal.android.sdk.onetouch.core.Result;
import com.paypal.android.sdk.onetouch.core.enums.RequestTarget;
import com.paypal.android.sdk.onetouch.core.sdk.PayPalScope;
import com.paypal.android.sdk.onetouch.core.sdk.PendingRequest;
import org.json.JSONException;
import org.json.JSONObject;

public class PayPal {
    public static final String SCOPE_ADDRESS = PayPalScope.ADDRESS.getScopeUri();
    public static final String SCOPE_EMAIL = PayPalScope.EMAIL.getScopeUri();
    public static final String SCOPE_FUTURE_PAYMENTS = PayPalScope.FUTURE_PAYMENTS.getScopeUri();
    protected static boolean sFuturePaymentsOverride = false;

    public static void requestBillingAgreement(BraintreeFragment braintreeFragment, PayPalRequest payPalRequest) {
        requestBillingAgreement(braintreeFragment, payPalRequest, null);
    }

    public static void requestBillingAgreement(BraintreeFragment braintreeFragment, PayPalRequest payPalRequest, PayPalApprovalHandler payPalApprovalHandler) {
        if (payPalRequest.getAmount() == null) {
            braintreeFragment.sendAnalyticsEvent("paypal.billing-agreement.selected");
            if (payPalRequest.shouldOfferCredit()) {
                braintreeFragment.sendAnalyticsEvent("paypal.billing-agreement.credit.offered");
            }
            requestOneTimePayment(braintreeFragment, payPalRequest, true, payPalApprovalHandler);
            return;
        }
        braintreeFragment.postCallback((Exception) new BraintreeException("There must be no amount specified for the Billing Agreement flow"));
    }

    public static void requestOneTimePayment(BraintreeFragment braintreeFragment, PayPalRequest payPalRequest) {
        requestOneTimePayment(braintreeFragment, payPalRequest, null);
    }

    public static void requestOneTimePayment(BraintreeFragment braintreeFragment, PayPalRequest payPalRequest, PayPalApprovalHandler payPalApprovalHandler) {
        if (payPalRequest.getAmount() != null) {
            braintreeFragment.sendAnalyticsEvent("paypal.one-time-payment.selected");
            if (payPalRequest.shouldOfferCredit()) {
                braintreeFragment.sendAnalyticsEvent("paypal.single-payment.credit.offered");
            }
            requestOneTimePayment(braintreeFragment, payPalRequest, false, payPalApprovalHandler);
            return;
        }
        braintreeFragment.postCallback((Exception) new BraintreeException("An amount must be specified for the Single Payment flow."));
    }

    private static void requestOneTimePayment(final BraintreeFragment braintreeFragment, final PayPalRequest payPalRequest, final boolean z, final PayPalApprovalHandler payPalApprovalHandler) {
        final AnonymousClass2 r0 = new HttpResponseCallback() {
            public void success(String str) {
                Request request;
                try {
                    String builder = Uri.parse(PayPalPaymentResource.fromJson(str).getRedirectUrl()).buildUpon().appendQueryParameter("useraction", payPalRequest.getUserAction()).toString();
                    if (z) {
                        request = PayPal.getBillingAgreementRequest(braintreeFragment, builder);
                    } else {
                        request = PayPal.getCheckoutRequest(braintreeFragment, builder);
                    }
                    PayPal.startPayPal(braintreeFragment, request, payPalApprovalHandler);
                } catch (JSONException e) {
                    braintreeFragment.postCallback((Exception) e);
                }
            }

            public void failure(Exception exc) {
                braintreeFragment.postCallback(exc);
            }
        };
        braintreeFragment.waitForConfiguration(new ConfigurationListener() {
            public void onConfigurationFetched(Configuration configuration) {
                if (!configuration.isPayPalEnabled()) {
                    braintreeFragment.postCallback((Exception) new BraintreeException("PayPal is not enabled"));
                } else if (!PayPal.isManifestValid(braintreeFragment)) {
                    braintreeFragment.sendAnalyticsEvent("paypal.invalid-manifest");
                    braintreeFragment.postCallback((Exception) new BraintreeException("BraintreeBrowserSwitchActivity missing, incorrectly configured in AndroidManifest.xml or another app defines the same browser switch url as this app. See https://developers.braintreepayments.com/guides/client-sdk/android/v2#browser-switch for the correct configuration"));
                } else {
                    try {
                        PayPal.persistPayPalRequest(braintreeFragment.getApplicationContext(), payPalRequest);
                        PayPal.createPaymentResource(braintreeFragment, payPalRequest, z, r0);
                    } catch (BraintreeException | ErrorWithResponse | JSONException e) {
                        braintreeFragment.postCallback(e);
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public static void createPaymentResource(BraintreeFragment braintreeFragment, PayPalRequest payPalRequest, boolean z, HttpResponseCallback httpResponseCallback) throws JSONException, ErrorWithResponse, BraintreeException {
        String currencyCode = payPalRequest.getCurrencyCode();
        if (currencyCode == null) {
            currencyCode = braintreeFragment.getConfiguration().getPayPal().getCurrencyIsoCode();
        }
        CheckoutRequest checkoutRequest = getCheckoutRequest(braintreeFragment, null);
        JSONObject put = new JSONObject().put("return_url", checkoutRequest.getSuccessUrl()).put("cancel_url", checkoutRequest.getCancelUrl()).put("offer_paypal_credit", payPalRequest.shouldOfferCredit());
        if (braintreeFragment.getAuthorization() instanceof ClientToken) {
            put.put("authorization_fingerprint", ((ClientToken) braintreeFragment.getAuthorization()).getAuthorizationFingerprint());
        } else {
            put.put("client_key", braintreeFragment.getAuthorization().toString());
        }
        if (!z) {
            put.put("amount", payPalRequest.getAmount()).put("currency_iso_code", currencyCode).put("intent", payPalRequest.getIntent());
        } else if (!TextUtils.isEmpty(payPalRequest.getBillingAgreementDescription())) {
            put.put("description", payPalRequest.getBillingAgreementDescription());
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("no_shipping", !payPalRequest.isShippingAddressRequired());
        jSONObject.put("landing_page_type", payPalRequest.getLandingPageType());
        String displayName = payPalRequest.getDisplayName();
        if (TextUtils.isEmpty(displayName)) {
            displayName = braintreeFragment.getConfiguration().getPayPal().getDisplayName();
        }
        jSONObject.put("brand_name", displayName);
        if (payPalRequest.getLocaleCode() != null) {
            jSONObject.put("locale_code", payPalRequest.getLocaleCode());
        }
        if (payPalRequest.getShippingAddressOverride() != null) {
            jSONObject.put("address_override", true);
            PostalAddress shippingAddressOverride = payPalRequest.getShippingAddressOverride();
            put.put("line1", shippingAddressOverride.getStreetAddress());
            put.put("line2", shippingAddressOverride.getExtendedAddress());
            put.put("city", shippingAddressOverride.getLocality());
            put.put("state", shippingAddressOverride.getRegion());
            put.put("postal_code", shippingAddressOverride.getPostalCode());
            put.put("country_code", shippingAddressOverride.getCountryCodeAlpha2());
            put.put("recipient_name", shippingAddressOverride.getRecipientName());
        } else {
            jSONObject.put("address_override", false);
        }
        put.put("experience_profile", jSONObject);
        String str = z ? "paypal_hermes/setup_billing_agreement" : "paypal_hermes/create_payment_resource";
        StringBuilder sb = new StringBuilder();
        sb.append("/v1/");
        sb.append(str);
        braintreeFragment.getHttpClient().post(sb.toString(), put.toString(), httpResponseCallback);
    }

    /* access modifiers changed from: private */
    public static void startPayPal(final BraintreeFragment braintreeFragment, Request request, PayPalApprovalHandler payPalApprovalHandler) {
        PayPalApprovalCallback payPalApprovalCallback;
        persistRequest(braintreeFragment.getApplicationContext(), request);
        if (payPalApprovalHandler == null) {
            payPalApprovalHandler = getDefaultApprovalHandler(braintreeFragment);
            payPalApprovalCallback = null;
        } else {
            payPalApprovalCallback = new PayPalApprovalCallback() {
            };
        }
        payPalApprovalHandler.handleApproval(request, payPalApprovalCallback);
    }

    private static PayPalApprovalHandler getDefaultApprovalHandler(final BraintreeFragment braintreeFragment) {
        return new PayPalApprovalHandler() {
            public void handleApproval(Request request, PayPalApprovalCallback payPalApprovalCallback) {
                PendingRequest startIntent = PayPalOneTouchCore.getStartIntent(braintreeFragment.getApplicationContext(), request);
                if (startIntent.isSuccess() && startIntent.getRequestTarget() == RequestTarget.wallet) {
                    PayPal.sendAnalyticsForPayPal(braintreeFragment, request, true, RequestTarget.wallet);
                    braintreeFragment.startActivityForResult(startIntent.getIntent(), 13591);
                } else if (!startIntent.isSuccess() || startIntent.getRequestTarget() != RequestTarget.browser) {
                    PayPal.sendAnalyticsForPayPal(braintreeFragment, request, false, null);
                } else {
                    PayPal.sendAnalyticsForPayPal(braintreeFragment, request, true, RequestTarget.browser);
                    braintreeFragment.browserSwitch(13591, startIntent.getIntent());
                }
            }
        };
    }

    /* access modifiers changed from: private */
    public static void sendAnalyticsForPayPal(BraintreeFragment braintreeFragment, Request request, boolean z, RequestTarget requestTarget) {
        String str = "";
        if (request instanceof CheckoutRequest) {
            if (!z) {
                str = "paypal-single-payment.initiate.failed";
            } else if (requestTarget == RequestTarget.browser) {
                str = "paypal-single-payment.webswitch.initiate.started";
            } else if (requestTarget == RequestTarget.wallet) {
                str = "paypal-single-payment.appswitch.initiate.started";
            }
        } else if (!z) {
            str = "paypal-future-payments.initiate.failed";
        } else if (requestTarget == RequestTarget.browser) {
            str = "paypal-future-payments.webswitch.initiate.started";
        } else if (requestTarget == RequestTarget.wallet) {
            str = "paypal-future-payments.appswitch.initiate.started";
        }
        braintreeFragment.sendAnalyticsEvent(str);
    }

    protected static void onActivityResult(BraintreeFragment braintreeFragment, int i, Intent intent) {
        Request persistedRequest = getPersistedRequest(braintreeFragment.getApplicationContext());
        if (i != -1 || intent == null || persistedRequest == null) {
            String lowerCase = persistedRequest != null ? persistedRequest.getClass().getSimpleName().toLowerCase() : "unknown";
            StringBuilder sb = new StringBuilder();
            sb.append("paypal.");
            sb.append(lowerCase);
            sb.append(".canceled");
            braintreeFragment.sendAnalyticsEvent(sb.toString());
            if (i != 0) {
                braintreeFragment.postCancelCallback(13591);
                return;
            }
            return;
        }
        boolean isAppSwitch = isAppSwitch(intent);
        Result parseResponse = PayPalOneTouchCore.parseResponse(braintreeFragment.getApplicationContext(), persistedRequest, intent);
        switch (parseResponse.getResultType()) {
            case Error:
                braintreeFragment.postCallback((Exception) new BrowserSwitchException(parseResponse.getError().getMessage()));
                sendAnalyticsEventForSwitchResult(braintreeFragment, persistedRequest, isAppSwitch, "failed");
                return;
            case Cancel:
                sendAnalyticsEventForSwitchResult(braintreeFragment, persistedRequest, isAppSwitch, "canceled");
                braintreeFragment.postCancelCallback(13591);
                return;
            case Success:
                onSuccess(braintreeFragment, intent, persistedRequest, parseResponse);
                sendAnalyticsEventForSwitchResult(braintreeFragment, persistedRequest, isAppSwitch, "succeeded");
                return;
            default:
                return;
        }
    }

    private static void onSuccess(final BraintreeFragment braintreeFragment, Intent intent, Request request, Result result) {
        TokenizationClient.tokenize(braintreeFragment, parseResponse(getPersistedPayPalRequest(braintreeFragment.getApplicationContext()), request, result, intent), new PaymentMethodNonceCallback() {
            public void success(PaymentMethodNonce paymentMethodNonce) {
                if ((paymentMethodNonce instanceof PayPalAccountNonce) && ((PayPalAccountNonce) paymentMethodNonce).getCreditFinancing() != null) {
                    braintreeFragment.sendAnalyticsEvent("paypal.credit.accepted");
                }
                braintreeFragment.postCallback(paymentMethodNonce);
            }

            public void failure(Exception exc) {
                braintreeFragment.postCallback(exc);
            }
        });
    }

    private static PayPalAccountBuilder parseResponse(PayPalRequest payPalRequest, Request request, Result result, Intent intent) {
        PayPalAccountBuilder clientMetadataId = new PayPalAccountBuilder().clientMetadataId(request.getClientMetadataId());
        if ((request instanceof CheckoutRequest) && payPalRequest != null) {
            clientMetadataId.intent(payPalRequest.getIntent());
        }
        if (isAppSwitch(intent)) {
            clientMetadataId.source("paypal-app");
        } else {
            clientMetadataId.source("paypal-browser");
        }
        JSONObject response = result.getResponse();
        try {
            JSONObject jSONObject = response.getJSONObject("client");
            JSONObject jSONObject2 = response.getJSONObject("response");
            if ("mock".equalsIgnoreCase(jSONObject.getString("client")) && jSONObject2.getString("code") != null && !(request instanceof CheckoutRequest)) {
                StringBuilder sb = new StringBuilder();
                sb.append("fake-code:");
                sb.append(((AuthorizationRequest) request).getScopeString());
                response.put("response", new JSONObject().put("code", sb.toString()));
            }
        } catch (JSONException unused) {
        }
        clientMetadataId.oneTouchCoreData(response);
        return clientMetadataId;
    }

    private static void sendAnalyticsEventForSwitchResult(BraintreeFragment braintreeFragment, Request request, boolean z, String str) {
        braintreeFragment.sendAnalyticsEvent(String.format("%s.%s.%s", new Object[]{request instanceof CheckoutRequest ? "paypal-single-payment" : "paypal-future-payments", z ? "appswitch" : "webswitch", str}));
    }

    static CheckoutRequest getCheckoutRequest(BraintreeFragment braintreeFragment, String str) {
        CheckoutRequest approvalURL = ((CheckoutRequest) populateRequestData(braintreeFragment, new CheckoutRequest())).approvalURL(str);
        if (str != null) {
            approvalURL.pairingId(braintreeFragment.getApplicationContext(), Uri.parse(str).getQueryParameter("token"));
        }
        return approvalURL;
    }

    static BillingAgreementRequest getBillingAgreementRequest(BraintreeFragment braintreeFragment, String str) {
        BillingAgreementRequest approvalURL = ((BillingAgreementRequest) populateRequestData(braintreeFragment, new BillingAgreementRequest())).approvalURL(str);
        if (str != null) {
            approvalURL.pairingId(braintreeFragment.getApplicationContext(), Uri.parse(str).getQueryParameter("ba_token"));
        }
        return approvalURL;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0033  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x003b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static <T extends com.paypal.android.sdk.onetouch.core.Request> T populateRequestData(com.braintreepayments.api.BraintreeFragment r4, T r5) {
        /*
            com.braintreepayments.api.models.Configuration r0 = r4.getConfiguration()
            com.braintreepayments.api.models.PayPalConfiguration r0 = r0.getPayPal()
            java.lang.String r1 = r0.getEnvironment()
            int r2 = r1.hashCode()
            r3 = -1548612125(0xffffffffa3b20de3, float:-1.930468E-17)
            if (r2 == r3) goto L_0x0025
            r3 = 3322092(0x32b0ec, float:4.655242E-39)
            if (r2 == r3) goto L_0x001b
            goto L_0x002f
        L_0x001b:
            java.lang.String r2 = "live"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x002f
            r1 = 0
            goto L_0x0030
        L_0x0025:
            java.lang.String r2 = "offline"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x002f
            r1 = 1
            goto L_0x0030
        L_0x002f:
            r1 = -1
        L_0x0030:
            switch(r1) {
                case 0: goto L_0x003b;
                case 1: goto L_0x0038;
                default: goto L_0x0033;
            }
        L_0x0033:
            java.lang.String r1 = r0.getEnvironment()
            goto L_0x003d
        L_0x0038:
            java.lang.String r1 = "mock"
            goto L_0x003d
        L_0x003b:
            java.lang.String r1 = "live"
        L_0x003d:
            java.lang.String r0 = r0.getClientId()
            if (r0 != 0) goto L_0x004d
            java.lang.String r2 = "mock"
            boolean r2 = r2.equals(r1)
            if (r2 == 0) goto L_0x004d
            java.lang.String r0 = "FAKE-PAYPAL-CLIENT-ID"
        L_0x004d:
            com.paypal.android.sdk.onetouch.core.Request r1 = r5.environment(r1)
            com.paypal.android.sdk.onetouch.core.Request r0 = r1.clientId(r0)
            java.lang.String r1 = r4.getReturnUrlScheme()
            java.lang.String r2 = "cancel"
            com.paypal.android.sdk.onetouch.core.Request r0 = r0.cancelUrl(r1, r2)
            java.lang.String r4 = r4.getReturnUrlScheme()
            java.lang.String r1 = "success"
            r0.successUrl(r4, r1)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.braintreepayments.api.PayPal.populateRequestData(com.braintreepayments.api.BraintreeFragment, com.paypal.android.sdk.onetouch.core.Request):com.paypal.android.sdk.onetouch.core.Request");
    }

    private static boolean isAppSwitch(Intent intent) {
        return intent.getData() == null;
    }

    /* access modifiers changed from: private */
    public static void persistPayPalRequest(Context context, PayPalRequest payPalRequest) {
        Parcel obtain = Parcel.obtain();
        payPalRequest.writeToParcel(obtain, 0);
        BraintreeSharedPreferences.getSharedPreferences(context).edit().putString("com.braintreepayments.api.PayPal.PAYPAL_REQUEST_KEY", Base64.encodeToString(obtain.marshall(), 0)).apply();
    }

    private static void persistRequest(Context context, Request request) {
        Parcel obtain = Parcel.obtain();
        request.writeToParcel(obtain, 0);
        BraintreeSharedPreferences.getSharedPreferences(context).edit().putString("com.braintreepayments.api.PayPal.REQUEST_KEY", Base64.encodeToString(obtain.marshall(), 0)).putString("com.braintreepayments.api.PayPal.REQUEST_TYPE_KEY", request.getClass().getSimpleName()).apply();
    }

    /* JADX INFO: finally extract failed */
    private static PayPalRequest getPersistedPayPalRequest(Context context) {
        SharedPreferences sharedPreferences = BraintreeSharedPreferences.getSharedPreferences(context);
        try {
            byte[] decode = Base64.decode(sharedPreferences.getString("com.braintreepayments.api.PayPal.PAYPAL_REQUEST_KEY", ""), 0);
            Parcel obtain = Parcel.obtain();
            obtain.unmarshall(decode, 0, decode.length);
            obtain.setDataPosition(0);
            PayPalRequest payPalRequest = (PayPalRequest) PayPalRequest.CREATOR.createFromParcel(obtain);
            sharedPreferences.edit().remove("com.braintreepayments.api.PayPal.PAYPAL_REQUEST_KEY").apply();
            return payPalRequest;
        } catch (Exception unused) {
            sharedPreferences.edit().remove("com.braintreepayments.api.PayPal.PAYPAL_REQUEST_KEY").apply();
            return null;
        } catch (Throwable th) {
            sharedPreferences.edit().remove("com.braintreepayments.api.PayPal.PAYPAL_REQUEST_KEY").apply();
            throw th;
        }
    }

    private static Request getPersistedRequest(Context context) {
        Request request;
        SharedPreferences sharedPreferences = BraintreeSharedPreferences.getSharedPreferences(context);
        try {
            byte[] decode = Base64.decode(sharedPreferences.getString("com.braintreepayments.api.PayPal.REQUEST_KEY", ""), 0);
            Parcel obtain = Parcel.obtain();
            obtain.unmarshall(decode, 0, decode.length);
            obtain.setDataPosition(0);
            String string = sharedPreferences.getString("com.braintreepayments.api.PayPal.REQUEST_TYPE_KEY", "");
            if (AuthorizationRequest.class.getSimpleName().equals(string)) {
                request = (Request) AuthorizationRequest.CREATOR.createFromParcel(obtain);
            } else if (BillingAgreementRequest.class.getSimpleName().equals(string)) {
                request = (Request) BillingAgreementRequest.CREATOR.createFromParcel(obtain);
            } else {
                if (CheckoutRequest.class.getSimpleName().equals(string)) {
                    request = (Request) CheckoutRequest.CREATOR.createFromParcel(obtain);
                }
                sharedPreferences.edit().remove("com.braintreepayments.api.PayPal.REQUEST_KEY").remove("com.braintreepayments.api.PayPal.REQUEST_TYPE_KEY").apply();
                return null;
            }
            sharedPreferences.edit().remove("com.braintreepayments.api.PayPal.REQUEST_KEY").remove("com.braintreepayments.api.PayPal.REQUEST_TYPE_KEY").apply();
            return request;
        } catch (Exception unused) {
        } catch (Throwable th) {
            sharedPreferences.edit().remove("com.braintreepayments.api.PayPal.REQUEST_KEY").remove("com.braintreepayments.api.PayPal.REQUEST_TYPE_KEY").apply();
            throw th;
        }
    }

    /* access modifiers changed from: private */
    public static boolean isManifestValid(BraintreeFragment braintreeFragment) {
        Intent intent = new Intent("android.intent.action.VIEW");
        StringBuilder sb = new StringBuilder();
        sb.append(braintreeFragment.getReturnUrlScheme());
        sb.append("://");
        Intent addCategory = intent.setData(Uri.parse(sb.toString())).addCategory("android.intent.category.DEFAULT").addCategory("android.intent.category.BROWSABLE");
        ActivityInfo activityInfo = ManifestValidator.getActivityInfo(braintreeFragment.getApplicationContext(), BraintreeBrowserSwitchActivity.class);
        return activityInfo != null && activityInfo.launchMode == 2 && AppHelper.isIntentAvailable(braintreeFragment.getApplicationContext(), addCategory);
    }
}
