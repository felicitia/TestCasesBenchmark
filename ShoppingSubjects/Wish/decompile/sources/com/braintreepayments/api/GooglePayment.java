package com.braintreepayments.api;

import android.content.Intent;
import com.braintreepayments.api.exceptions.ErrorWithResponse;
import com.braintreepayments.api.exceptions.GoogleApiClientException;
import com.braintreepayments.api.exceptions.GoogleApiClientException.ErrorType;
import com.braintreepayments.api.exceptions.GooglePaymentException;
import com.braintreepayments.api.interfaces.BraintreeResponseListener;
import com.braintreepayments.api.interfaces.ConfigurationListener;
import com.braintreepayments.api.interfaces.TokenizationParametersListener;
import com.braintreepayments.api.models.AndroidPayConfiguration;
import com.braintreepayments.api.models.Configuration;
import com.braintreepayments.api.models.GooglePaymentCardNonce;
import com.braintreepayments.api.models.MetadataBuilder;
import com.braintreepayments.api.models.PaymentMethodNonce;
import com.braintreepayments.api.models.TokenizationKey;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wallet.AutoResolveHelper;
import com.google.android.gms.wallet.IsReadyToPayRequest;
import com.google.android.gms.wallet.PaymentData;
import com.google.android.gms.wallet.PaymentMethodTokenizationParameters;
import com.google.android.gms.wallet.PaymentsClient;
import com.google.android.gms.wallet.Wallet;
import com.google.android.gms.wallet.Wallet.WalletOptions.Builder;
import org.json.JSONException;

public class GooglePayment {
    public static void isReadyToPay(final BraintreeFragment braintreeFragment, final BraintreeResponseListener<Boolean> braintreeResponseListener) {
        try {
            Class.forName(PaymentsClient.class.getName());
            braintreeFragment.waitForConfiguration(new ConfigurationListener() {
                public void onConfigurationFetched(Configuration configuration) {
                    if (!configuration.getAndroidPay().isEnabled(braintreeFragment.getApplicationContext())) {
                        braintreeResponseListener.onResponse(Boolean.valueOf(false));
                        return;
                    }
                    if (braintreeFragment.getActivity() == null) {
                        braintreeFragment.postCallback((Exception) new GoogleApiClientException(ErrorType.NotAttachedToActivity, 1));
                    }
                    Wallet.getPaymentsClient(braintreeFragment.getActivity(), new Builder().setEnvironment(GooglePayment.getEnvironment(configuration.getAndroidPay())).build()).isReadyToPay(IsReadyToPayRequest.newBuilder().addAllowedPaymentMethod(1).addAllowedPaymentMethod(2).build()).addOnCompleteListener(new OnCompleteListener<Boolean>() {
                        public void onComplete(Task<Boolean> task) {
                            try {
                                braintreeResponseListener.onResponse(task.getResult(ApiException.class));
                            } catch (ApiException unused) {
                                braintreeResponseListener.onResponse(Boolean.valueOf(false));
                            }
                        }
                    });
                }
            });
        } catch (ClassNotFoundException | NoClassDefFoundError unused) {
            braintreeResponseListener.onResponse(Boolean.valueOf(false));
        }
    }

    public static void getTokenizationParameters(final BraintreeFragment braintreeFragment, final TokenizationParametersListener tokenizationParametersListener) {
        braintreeFragment.waitForConfiguration(new ConfigurationListener() {
            public void onConfigurationFetched(Configuration configuration) {
                tokenizationParametersListener.onResult(GooglePayment.getTokenizationParameters(braintreeFragment), GooglePayment.getAllowedCardNetworks(braintreeFragment));
            }
        });
    }

    public static void tokenize(BraintreeFragment braintreeFragment, PaymentData paymentData) {
        try {
            braintreeFragment.postCallback((PaymentMethodNonce) GooglePaymentCardNonce.fromPaymentData(paymentData));
            braintreeFragment.sendAnalyticsEvent("google-payment.nonce-received");
        } catch (NullPointerException | JSONException unused) {
            braintreeFragment.sendAnalyticsEvent("google-payment.failed");
            try {
                braintreeFragment.postCallback((Exception) ErrorWithResponse.fromJson(paymentData.getPaymentMethodToken().getToken()));
            } catch (NullPointerException | JSONException e) {
                braintreeFragment.postCallback(e);
            }
        }
    }

    static void onActivityResult(BraintreeFragment braintreeFragment, int i, Intent intent) {
        if (i == -1) {
            braintreeFragment.sendAnalyticsEvent("google-payment.authorized");
            tokenize(braintreeFragment, PaymentData.getFromIntent(intent));
        } else if (i == 1) {
            braintreeFragment.sendAnalyticsEvent("google-payment.failed");
            braintreeFragment.postCallback((Exception) new GooglePaymentException("An error was encountered during the Google Payments flow. See the status object in this exception for more details.", AutoResolveHelper.getStatusFromIntent(intent)));
        } else if (i == 0) {
            braintreeFragment.sendAnalyticsEvent("google-payment.canceled");
        }
    }

    static int getEnvironment(AndroidPayConfiguration androidPayConfiguration) {
        return "production".equals(androidPayConfiguration.getEnvironment()) ? 1 : 3;
    }

    static PaymentMethodTokenizationParameters getTokenizationParameters(BraintreeFragment braintreeFragment) {
        PaymentMethodTokenizationParameters.Builder addParameter = PaymentMethodTokenizationParameters.newBuilder().setPaymentMethodTokenizationType(1).addParameter("gateway", "braintree").addParameter("braintree:merchantId", braintreeFragment.getConfiguration().getMerchantId()).addParameter("braintree:authorizationFingerprint", braintreeFragment.getConfiguration().getAndroidPay().getGoogleAuthorizationFingerprint()).addParameter("braintree:apiVersion", "v1").addParameter("braintree:sdkVersion", "2.7.0").addParameter("braintree:metadata", new MetadataBuilder().integration(braintreeFragment.getIntegrationType()).sessionId(braintreeFragment.getSessionId()).version().toString());
        if (braintreeFragment.getAuthorization() instanceof TokenizationKey) {
            addParameter.addParameter("braintree:clientKey", braintreeFragment.getAuthorization().toString());
        }
        return addParameter.build();
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x007a  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0082 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.util.ArrayList<java.lang.Integer> getAllowedCardNetworks(com.braintreepayments.api.BraintreeFragment r10) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            com.braintreepayments.api.models.Configuration r10 = r10.getConfiguration()
            com.braintreepayments.api.models.AndroidPayConfiguration r10 = r10.getAndroidPay()
            java.lang.String[] r10 = r10.getSupportedNetworks()
            int r1 = r10.length
            r2 = 0
            r3 = 0
        L_0x0014:
            if (r3 >= r1) goto L_0x0085
            r4 = r10[r3]
            r5 = -1
            int r6 = r4.hashCode()
            r7 = -2038717326(0xffffffff867ba472, float:-4.7328668E-35)
            r8 = 2
            r9 = 1
            if (r6 == r7) goto L_0x0052
            r7 = 2997727(0x2dbddf, float:4.20071E-39)
            if (r6 == r7) goto L_0x0048
            r7 = 3619905(0x373c41, float:5.072567E-39)
            if (r6 == r7) goto L_0x003e
            r7 = 273184745(0x104877e9, float:3.953542E-29)
            if (r6 == r7) goto L_0x0034
            goto L_0x005c
        L_0x0034:
            java.lang.String r6 = "discover"
            boolean r4 = r4.equals(r6)
            if (r4 == 0) goto L_0x005c
            r4 = 3
            goto L_0x005d
        L_0x003e:
            java.lang.String r6 = "visa"
            boolean r4 = r4.equals(r6)
            if (r4 == 0) goto L_0x005c
            r4 = 0
            goto L_0x005d
        L_0x0048:
            java.lang.String r6 = "amex"
            boolean r4 = r4.equals(r6)
            if (r4 == 0) goto L_0x005c
            r4 = 2
            goto L_0x005d
        L_0x0052:
            java.lang.String r6 = "mastercard"
            boolean r4 = r4.equals(r6)
            if (r4 == 0) goto L_0x005c
            r4 = 1
            goto L_0x005d
        L_0x005c:
            r4 = -1
        L_0x005d:
            switch(r4) {
                case 0: goto L_0x007a;
                case 1: goto L_0x0071;
                case 2: goto L_0x0069;
                case 3: goto L_0x0061;
                default: goto L_0x0060;
            }
        L_0x0060:
            goto L_0x0082
        L_0x0061:
            java.lang.Integer r4 = java.lang.Integer.valueOf(r8)
            r0.add(r4)
            goto L_0x0082
        L_0x0069:
            java.lang.Integer r4 = java.lang.Integer.valueOf(r9)
            r0.add(r4)
            goto L_0x0082
        L_0x0071:
            r4 = 4
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r0.add(r4)
            goto L_0x0082
        L_0x007a:
            r4 = 5
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r0.add(r4)
        L_0x0082:
            int r3 = r3 + 1
            goto L_0x0014
        L_0x0085:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.braintreepayments.api.GooglePayment.getAllowedCardNetworks(com.braintreepayments.api.BraintreeFragment):java.util.ArrayList");
    }
}
