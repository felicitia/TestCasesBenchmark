package com.contextlogic.wish.payments.processing;

import com.braintreepayments.api.BraintreeFragment;
import com.braintreepayments.api.DataCollector;
import com.braintreepayments.api.PayPal;
import com.braintreepayments.api.interfaces.BraintreeCancelListener;
import com.braintreepayments.api.interfaces.BraintreeErrorListener;
import com.braintreepayments.api.interfaces.BraintreeResponseListener;
import com.braintreepayments.api.interfaces.PaymentMethodNonceCreatedListener;
import com.braintreepayments.api.models.PayPalRequest;
import com.braintreepayments.api.models.PaymentMethodNonce;
import com.contextlogic.wish.R;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishCart;
import com.contextlogic.wish.api.model.WishCommerceCashCart;
import com.contextlogic.wish.api.model.WishCommerceLoanCart;
import com.contextlogic.wish.api.model.WishDeclineRedirectInfo;
import com.contextlogic.wish.api.model.WishShippingInfo;
import com.contextlogic.wish.api.model.WishUserBillingInfo;
import com.contextlogic.wish.api.service.standalone.PreVerifyPayPalPaymentService;
import com.contextlogic.wish.api.service.standalone.PreVerifyPayPalPaymentService.SuccessCallback;
import com.contextlogic.wish.api.service.standalone.VerifyPayPalPaymentService;
import com.contextlogic.wish.api.service.standalone.VerifyPayPalPaymentService.FailureCallback;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.payments.CommerceCashCartContext;
import com.contextlogic.wish.payments.CommerceLoanCartContext;
import com.contextlogic.wish.payments.braintree.BraintreeFragmentCallback;
import com.contextlogic.wish.payments.braintree.BraintreeManager;
import com.contextlogic.wish.payments.processing.CartPaymentProcessor.FailureListener;
import com.contextlogic.wish.payments.processing.CartPaymentProcessor.PaymentContext;
import com.contextlogic.wish.payments.processing.CartPaymentProcessor.SuccessListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;

public class PayPalPaymentProcessor extends CartPaymentProcessor {
    /* access modifiers changed from: private */
    public BraintreeErrorListener mBraintreeErrorListener;
    /* access modifiers changed from: private */
    public PaymentMethodNonceCreatedListener mNonceCreatedListener;
    private PreVerifyPayPalPaymentService mPreVerifyPayPalPaymentService = new PreVerifyPayPalPaymentService();
    /* access modifiers changed from: private */
    public VerifyPayPalPaymentService mVerifyPayPalPaymentService = new VerifyPayPalPaymentService();

    public PayPalPaymentProcessor(CartPaymentProcessorServiceFragment cartPaymentProcessorServiceFragment) {
        super(cartPaymentProcessorServiceFragment);
    }

    public void checkout(SuccessListener successListener, FailureListener failureListener) {
        final HashMap hashMap = new HashMap();
        hashMap.put("cart_type", this.mServiceFragment.getCartContext().getCartType().toString());
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_PAYPAL_ORDER, hashMap);
        WishShippingInfo shippingInfo = this.mServiceFragment.getCartContext().getShippingInfo();
        String str = null;
        String name = shippingInfo != null ? shippingInfo.getName() : null;
        String streetAddressLineOne = shippingInfo != null ? shippingInfo.getStreetAddressLineOne() : null;
        String streetAddressLineTwo = shippingInfo != null ? shippingInfo.getStreetAddressLineTwo() : null;
        String city = shippingInfo != null ? shippingInfo.getCity() : null;
        String state = shippingInfo != null ? shippingInfo.getState() : null;
        String zipCode = shippingInfo != null ? shippingInfo.getZipCode() : null;
        String countryCode = shippingInfo != null ? shippingInfo.getCountryCode() : null;
        if (shippingInfo != null) {
            str = shippingInfo.getPhoneNumber();
        }
        String str2 = str;
        this.mServiceFragment.showLoadingSpinner();
        PreVerifyPayPalPaymentService preVerifyPayPalPaymentService = this.mPreVerifyPayPalPaymentService;
        String currencyCode = this.mServiceFragment.getCartContext().getCurrencyCode();
        int value = this.mServiceFragment.getCartContext().getCartType().getValue();
        final HashMap hashMap2 = hashMap;
        final SuccessListener successListener2 = successListener;
        final FailureListener failureListener2 = failureListener;
        AnonymousClass1 r0 = new SuccessCallback() {
            public void onSuccess(WishCart wishCart, WishCommerceCashCart wishCommerceCashCart, WishCommerceLoanCart wishCommerceLoanCart, WishShippingInfo wishShippingInfo, WishUserBillingInfo wishUserBillingInfo) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_PAYPAL_PREPAY_SUCCESS, hashMap2);
                if (wishCart != null) {
                    PayPalPaymentProcessor.this.mServiceFragment.getCartContext().updateData(wishCart, wishShippingInfo, wishUserBillingInfo);
                } else if (wishCommerceCashCart != null) {
                    ((CommerceCashCartContext) PayPalPaymentProcessor.this.mServiceFragment.getCartContext()).updateData(wishCommerceCashCart, wishUserBillingInfo);
                } else if (wishCommerceLoanCart != null) {
                    ((CommerceLoanCartContext) PayPalPaymentProcessor.this.mServiceFragment.getCartContext()).updateData(wishCommerceLoanCart, wishUserBillingInfo);
                }
                PayPalPaymentProcessor.this.mServiceFragment.withBraintreeFragment(new BraintreeFragmentCallback() {
                    public void onBraintreeFragmentLoaded(final BraintreeFragment braintreeFragment) {
                        String str;
                        String str2;
                        PayPalPaymentProcessor.this.mNonceCreatedListener = new PaymentMethodNonceCreatedListener() {
                            public void onPaymentMethodNonceCreated(final PaymentMethodNonce paymentMethodNonce) {
                                BraintreeManager.getInstance().clearBraintreeListeners(braintreeFragment);
                                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_PAYPAL_PAY_SUCCESS, hashMap2);
                                PayPalPaymentProcessor.this.mServiceFragment.showLoadingSpinner();
                                DataCollector.collectDeviceData(braintreeFragment, new BraintreeResponseListener<String>() {
                                    public void onResponse(String str) {
                                        PayPalPaymentProcessor.this.mVerifyPayPalPaymentService.requestService(paymentMethodNonce, str, PayPalPaymentProcessor.this.mServiceFragment.getCartContext().getCurrencyCode(), PayPalPaymentProcessor.this.mServiceFragment.getCartContext().getCheckoutOfferId(), PayPalPaymentProcessor.this.mServiceFragment.getCartContext().getCartType().getValue(), new VerifyPayPalPaymentService.SuccessCallback() {
                                            public void onSuccess(String str) {
                                                PayPalPaymentProcessor.this.mServiceFragment.hideLoadingSpinner();
                                                PayPalPaymentProcessor.this.handleSuccessfulPayment();
                                                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_PAYPAL_VERIFY_SUCCESS, hashMap2);
                                                PaymentContext paymentContext = new PaymentContext();
                                                paymentContext.transactionId = str;
                                                successListener2.onSuccess(this, paymentContext);
                                            }
                                        }, new FailureCallback() {
                                            public void onFailure(String str, int i, WishDeclineRedirectInfo wishDeclineRedirectInfo) {
                                                PayPalPaymentProcessor.this.mServiceFragment.hideLoadingSpinner();
                                                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_PAYPAL_VERIFY_ERROR, hashMap2);
                                                PaymentContext paymentContext = new PaymentContext();
                                                if (str == null) {
                                                    str = WishApplication.getInstance().getString(R.string.paypal_payment_error);
                                                }
                                                paymentContext.errorMessage = str;
                                                paymentContext.errorCode = i;
                                                paymentContext.declineRedirectInfo = wishDeclineRedirectInfo;
                                                failureListener2.onFailure(this, paymentContext);
                                            }
                                        });
                                    }
                                });
                            }
                        };
                        PayPalPaymentProcessor.this.mBraintreeErrorListener = new BraintreeErrorListener() {
                            public void onError(Exception exc) {
                                PayPalPaymentProcessor.this.mServiceFragment.hideLoadingSpinner();
                                BraintreeManager.getInstance().clearBraintreeListeners(braintreeFragment);
                                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_PAYPAL_PAY_ERROR, hashMap2);
                                PaymentContext paymentContext = new PaymentContext();
                                paymentContext.errorMessage = WishApplication.getInstance().getString(R.string.paypal_payment_error);
                                failureListener2.onFailure(this, paymentContext);
                            }
                        };
                        BraintreeManager.getInstance().clearBraintreeListeners(braintreeFragment);
                        BraintreeManager.getInstance().addBraintreeListener(braintreeFragment, PayPalPaymentProcessor.this.mBraintreeErrorListener);
                        BraintreeManager.getInstance().addBraintreeListener(braintreeFragment, PayPalPaymentProcessor.this.mNonceCreatedListener);
                        BraintreeManager.getInstance().addBraintreeListener(braintreeFragment, new BraintreeCancelListener() {
                            public void onCancel(int i) {
                                if (PayPalPaymentProcessor.this.mServiceFragment != null) {
                                    PayPalPaymentProcessor.this.mServiceFragment.hideLoadingSpinner();
                                }
                            }
                        });
                        HashSet hashSet = new HashSet();
                        hashSet.add("BIF");
                        hashSet.add("CLP");
                        hashSet.add("DJF");
                        hashSet.add("GNF");
                        hashSet.add("JPY");
                        hashSet.add("KMF");
                        hashSet.add("KRW");
                        hashSet.add("MGA");
                        hashSet.add("PYG");
                        hashSet.add("RWF");
                        hashSet.add("VUV");
                        hashSet.add("XAF");
                        hashSet.add("XOF");
                        hashSet.add("XPF");
                        if (ExperimentDataCenter.getInstance().shouldUsePsuedoLocalizedCurrency()) {
                            str = "USD";
                        } else {
                            str = PayPalPaymentProcessor.this.mServiceFragment.getCartContext().getCurrencyCode();
                        }
                        String str3 = hashSet.contains(str) ? "%.0f" : "%.2f";
                        if (str.equals("USD")) {
                            str2 = String.format(Locale.US, str3, new Object[]{Double.valueOf(PayPalPaymentProcessor.this.mServiceFragment.getCartContext().getTotal().getUsdValue())});
                        } else {
                            str2 = String.format(Locale.US, str3, new Object[]{Double.valueOf(PayPalPaymentProcessor.this.mServiceFragment.getCartContext().getTotal().getValue())});
                        }
                        PayPal.requestOneTimePayment(braintreeFragment, new PayPalRequest(str2).currencyCode(str).shippingAddressRequired(false).userAction("commit"));
                    }

                    public void onBraintreeFragmentLoadFailed(String str) {
                        PayPalPaymentProcessor.this.mServiceFragment.hideLoadingSpinner();
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_PAYPAL_PAY_ERROR, hashMap2);
                        PaymentContext paymentContext = new PaymentContext();
                        if (str == null) {
                            str = WishApplication.getInstance().getString(R.string.paypal_payment_error);
                        }
                        paymentContext.errorMessage = str;
                        failureListener2.onFailure(this, paymentContext);
                    }
                });
            }
        };
        final FailureListener failureListener3 = failureListener;
        preVerifyPayPalPaymentService.requestService(name, streetAddressLineOne, streetAddressLineTwo, city, state, zipCode, countryCode, str2, currencyCode, value, r0, new PreVerifyPayPalPaymentService.FailureCallback() {
            public void onFailure(String str, int i) {
                PayPalPaymentProcessor.this.mServiceFragment.hideLoadingSpinner();
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_PAYPAL_PREPAY_ERROR, hashMap);
                PaymentContext paymentContext = new PaymentContext();
                if (str == null) {
                    str = WishApplication.getInstance().getString(R.string.paypal_payment_error);
                }
                paymentContext.errorMessage = str;
                paymentContext.errorCode = i;
                failureListener3.onFailure(this, paymentContext);
            }
        });
    }
}
