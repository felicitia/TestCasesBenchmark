package com.contextlogic.wish.payments.processing;

import com.braintreepayments.api.BraintreeFragment;
import com.braintreepayments.api.DataCollector;
import com.braintreepayments.api.PayPal;
import com.braintreepayments.api.interfaces.BraintreeErrorListener;
import com.braintreepayments.api.interfaces.BraintreeResponseListener;
import com.braintreepayments.api.interfaces.PaymentMethodNonceCreatedListener;
import com.braintreepayments.api.models.PayPalRequest;
import com.braintreepayments.api.models.PaymentMethodNonce;
import com.contextlogic.wish.R;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishDeclineRedirectInfo;
import com.contextlogic.wish.api.model.WishUserBillingInfo;
import com.contextlogic.wish.api.service.standalone.CompleteFuturePayPalPaymentService;
import com.contextlogic.wish.api.service.standalone.CompleteFuturePayPalPaymentService.FailureCallback;
import com.contextlogic.wish.api.service.standalone.CompleteFuturePayPalPaymentService.SuccessCallback;
import com.contextlogic.wish.api.service.standalone.PreAuthorizePayPalPaymentService;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.payments.braintree.BraintreeFragmentCallback;
import com.contextlogic.wish.payments.braintree.BraintreeManager;
import com.contextlogic.wish.payments.processing.CartPaymentProcessor.FailureListener;
import com.contextlogic.wish.payments.processing.CartPaymentProcessor.PaymentContext;
import com.contextlogic.wish.payments.processing.CartPaymentProcessor.SuccessListener;
import java.util.HashMap;

public class FuturePayPalPaymentProcessor extends CartPaymentProcessor {
    /* access modifiers changed from: private */
    public BraintreeErrorListener mBraintreeErrorListener;
    /* access modifiers changed from: private */
    public CompleteFuturePayPalPaymentService mCompleteFuturePayPalPaymentService = new CompleteFuturePayPalPaymentService();
    /* access modifiers changed from: private */
    public PaymentMethodNonceCreatedListener mNonceCreatedListener;
    /* access modifiers changed from: private */
    public PreAuthorizePayPalPaymentService mPreAuthorizePayPalPaymentService = new PreAuthorizePayPalPaymentService();

    public FuturePayPalPaymentProcessor(CartPaymentProcessorServiceFragment cartPaymentProcessorServiceFragment) {
        super(cartPaymentProcessorServiceFragment);
    }

    public void checkout(SuccessListener successListener, FailureListener failureListener) {
        if (this.mServiceFragment.getCartContext().getUserBillingInfo() == null || this.mServiceFragment.getCartContext().getUserBillingInfo().getBraintreePayPalInfo() == null || this.mServiceFragment.getCartContext().getUserBillingInfo().getBraintreePayPalInfo().getPaymentMethodToken() == null) {
            handlePayPalAuthorization(successListener, failureListener);
            return;
        }
        final HashMap hashMap = new HashMap();
        hashMap.put("cart_type", this.mServiceFragment.getCartContext().getCartType().toString());
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_PAYPAL_ORDER, hashMap);
        this.mServiceFragment.showLoadingSpinner();
        CartPaymentProcessorServiceFragment cartPaymentProcessorServiceFragment = this.mServiceFragment;
        final SuccessListener successListener2 = successListener;
        final FailureListener failureListener2 = failureListener;
        AnonymousClass1 r1 = new BraintreeFragmentCallback() {
            public void onBraintreeFragmentLoaded(BraintreeFragment braintreeFragment) {
                DataCollector.collectDeviceData(braintreeFragment, new BraintreeResponseListener<String>() {
                    public void onResponse(String str) {
                        FuturePayPalPaymentProcessor.this.mCompleteFuturePayPalPaymentService.requestService(str, FuturePayPalPaymentProcessor.this.mServiceFragment.getCartContext().getCurrencyCode(), FuturePayPalPaymentProcessor.this.mServiceFragment.getCartContext().getCheckoutOfferId(), FuturePayPalPaymentProcessor.this.mServiceFragment.getCartContext().getCartType().getValue(), new SuccessCallback() {
                            public void onSuccess(String str) {
                                FuturePayPalPaymentProcessor.this.handleSuccessfulPayment();
                                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_PAYPAL_FUTURE_PAY_SUCCESS, hashMap);
                                PaymentContext paymentContext = new PaymentContext();
                                paymentContext.transactionId = str;
                                successListener2.onSuccess(this, paymentContext);
                            }
                        }, new FailureCallback() {
                            public void onFailure(String str, boolean z, int i, WishDeclineRedirectInfo wishDeclineRedirectInfo) {
                                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_PAYPAL_FUTURE_PAY_ERROR, hashMap);
                                if (z) {
                                    FuturePayPalPaymentProcessor.this.mServiceFragment.getCartContext().getUserBillingInfo().getBraintreePayPalInfo().revokePaymentMethodToken();
                                    FuturePayPalPaymentProcessor.this.checkout(successListener2, failureListener2);
                                    return;
                                }
                                if (str == null) {
                                    str = WishApplication.getInstance().getString(R.string.paypal_payment_error);
                                }
                                PaymentContext paymentContext = new PaymentContext();
                                paymentContext.errorMessage = str;
                                paymentContext.errorCode = i;
                                paymentContext.declineRedirectInfo = wishDeclineRedirectInfo;
                                failureListener2.onFailure(this, paymentContext);
                            }
                        });
                    }
                });
            }

            public void onBraintreeFragmentLoadFailed(String str) {
                FuturePayPalPaymentProcessor.this.mServiceFragment.hideLoadingSpinner();
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_PAYPAL_FUTURE_PAY_ERROR, hashMap);
                if (str == null) {
                    str = WishApplication.getInstance().getString(R.string.paypal_payment_error);
                }
                PaymentContext paymentContext = new PaymentContext();
                paymentContext.errorMessage = str;
                failureListener2.onFailure(this, paymentContext);
            }
        };
        cartPaymentProcessorServiceFragment.withBraintreeFragment(r1);
    }

    private void handlePayPalAuthorization(SuccessListener successListener, FailureListener failureListener) {
        this.mServiceFragment.showLoadingSpinner();
        final HashMap hashMap = new HashMap();
        hashMap.put("cart_type", this.mServiceFragment.getCartContext().getCartType().toString());
        CartPaymentProcessorServiceFragment cartPaymentProcessorServiceFragment = this.mServiceFragment;
        final SuccessListener successListener2 = successListener;
        final FailureListener failureListener2 = failureListener;
        AnonymousClass2 r1 = new BraintreeFragmentCallback() {
            public void onBraintreeFragmentLoaded(final BraintreeFragment braintreeFragment) {
                FuturePayPalPaymentProcessor.this.mServiceFragment.hideLoadingSpinner();
                FuturePayPalPaymentProcessor.this.mNonceCreatedListener = new PaymentMethodNonceCreatedListener() {
                    public void onPaymentMethodNonceCreated(final PaymentMethodNonce paymentMethodNonce) {
                        BraintreeManager.getInstance().clearBraintreeListeners(braintreeFragment);
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_PAYPAL_AUTH_SUCCESS, hashMap);
                        FuturePayPalPaymentProcessor.this.mServiceFragment.showLoadingSpinner();
                        DataCollector.collectDeviceData(braintreeFragment, new BraintreeResponseListener<String>() {
                            public void onResponse(String str) {
                                FuturePayPalPaymentProcessor.this.mPreAuthorizePayPalPaymentService.requestService(paymentMethodNonce, str, FuturePayPalPaymentProcessor.this.mServiceFragment.getCartContext().getCartType().getValue(), new PreAuthorizePayPalPaymentService.SuccessCallback() {
                                    public void onSuccess(WishUserBillingInfo wishUserBillingInfo) {
                                        FuturePayPalPaymentProcessor.this.mServiceFragment.hideLoadingSpinner();
                                        FuturePayPalPaymentProcessor.this.mServiceFragment.getCartContext().updatePreferredPaymentMode("PaymentModePayPal");
                                        FuturePayPalPaymentProcessor.this.mServiceFragment.getCartContext().updateData(FuturePayPalPaymentProcessor.this.mServiceFragment.getCartContext().getCart(), FuturePayPalPaymentProcessor.this.mServiceFragment.getCartContext().getShippingInfo(), wishUserBillingInfo);
                                        FuturePayPalPaymentProcessor.this.checkout(successListener2, failureListener2);
                                    }
                                }, new PreAuthorizePayPalPaymentService.FailureCallback() {
                                    public void onFailure(String str, int i) {
                                        FuturePayPalPaymentProcessor.this.mServiceFragment.hideLoadingSpinner();
                                        if (str == null) {
                                            str = WishApplication.getInstance().getString(R.string.paypal_payment_error);
                                        }
                                        PaymentContext paymentContext = new PaymentContext();
                                        paymentContext.errorMessage = str;
                                        paymentContext.errorCode = i;
                                        failureListener2.onFailure(this, paymentContext);
                                    }
                                });
                            }
                        });
                    }
                };
                FuturePayPalPaymentProcessor.this.mBraintreeErrorListener = new BraintreeErrorListener() {
                    public void onError(Exception exc) {
                        BraintreeManager.getInstance().clearBraintreeListeners(braintreeFragment);
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_PAYPAL_AUTH_ERROR, hashMap);
                        String string = WishApplication.getInstance().getString(R.string.paypal_payment_error);
                        PaymentContext paymentContext = new PaymentContext();
                        paymentContext.errorMessage = string;
                        failureListener2.onFailure(this, paymentContext);
                    }
                };
                BraintreeManager.getInstance().clearBraintreeListeners(braintreeFragment);
                BraintreeManager.getInstance().addBraintreeListener(braintreeFragment, FuturePayPalPaymentProcessor.this.mBraintreeErrorListener);
                BraintreeManager.getInstance().addBraintreeListener(braintreeFragment, FuturePayPalPaymentProcessor.this.mNonceCreatedListener);
                PayPalRequest payPalRequest = new PayPalRequest();
                payPalRequest.userAction("commit");
                PayPal.requestBillingAgreement(braintreeFragment, payPalRequest);
            }

            public void onBraintreeFragmentLoadFailed(String str) {
                FuturePayPalPaymentProcessor.this.mServiceFragment.hideLoadingSpinner();
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_PAYPAL_AUTH_ERROR, hashMap);
                if (str == null) {
                    str = WishApplication.getInstance().getString(R.string.paypal_payment_error);
                }
                PaymentContext paymentContext = new PaymentContext();
                paymentContext.errorMessage = str;
                failureListener2.onFailure(this, paymentContext);
            }
        };
        cartPaymentProcessorServiceFragment.withBraintreeFragment(r1);
    }
}
