package com.contextlogic.wish.payments.processing;

import android.content.Intent;
import com.braintreepayments.api.BraintreeFragment;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseActivity.ActivityResultCallback;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.cart.CartActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishCart.PaymentProcessor;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.payments.braintree.BraintreeFragmentCallback;
import com.contextlogic.wish.payments.google.BraintreeGooglePayPaymentCollector;
import com.contextlogic.wish.payments.google.GooglePayManager;
import com.contextlogic.wish.payments.google.GooglePayPaymentCollector;
import com.contextlogic.wish.payments.google.StripeGooglePayPaymentCollector;
import com.contextlogic.wish.payments.processing.CartPaymentProcessor.FailureListener;
import com.contextlogic.wish.payments.processing.CartPaymentProcessor.PaymentContext;
import com.contextlogic.wish.payments.processing.CartPaymentProcessor.SuccessListener;
import com.crashlytics.android.Crashlytics;
import com.google.android.gms.wallet.PaymentData;
import com.google.android.gms.wallet.PaymentDataRequest;
import java.util.HashMap;

public class GooglePayPaymentProcessor extends CartPaymentProcessor {
    public GooglePayPaymentProcessor(CartPaymentProcessorServiceFragment cartPaymentProcessorServiceFragment) {
        super(cartPaymentProcessorServiceFragment);
    }

    public void checkout(SuccessListener successListener, FailureListener failureListener) {
        final HashMap hashMap = new HashMap();
        hashMap.put("cart_type", this.mServiceFragment.getCartContext().getCartType().toString());
        if (this.mServiceFragment.getCartContext().getGooglePayPaymentData() == null) {
            this.mServiceFragment.showLoadingSpinner();
            CartPaymentProcessorServiceFragment cartPaymentProcessorServiceFragment = this.mServiceFragment;
            final FailureListener failureListener2 = failureListener;
            final SuccessListener successListener2 = successListener;
            AnonymousClass1 r0 = new BraintreeFragmentCallback() {
                public void onBraintreeFragmentLoaded(BraintreeFragment braintreeFragment) {
                    final PaymentDataRequest createPaymentDataRequest = GooglePayManager.getInstance().createPaymentDataRequest(GooglePayPaymentProcessor.this.mServiceFragment.getCartContext(), braintreeFragment);
                    GooglePayPaymentProcessor.this.mServiceFragment.withActivity(new ActivityTask<CartActivity>() {
                        public void performTask(CartActivity cartActivity) {
                            cartActivity.getServiceFragment().loadPaymentData(createPaymentDataRequest, cartActivity.addResultCodeCallback(new ActivityResultCallback() {
                                public void onActivityResult(BaseActivity baseActivity, int i, int i2, Intent intent) {
                                    if (i2 == -1) {
                                        PaymentData fromIntent = PaymentData.getFromIntent(intent);
                                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_GWALLET_FETCH_SUCCESS, hashMap);
                                        if (fromIntent == null) {
                                            Crashlytics.logException(new Exception("Google Pay API returned null payment data with status code RESULT_OK"));
                                            PaymentContext paymentContext = new PaymentContext();
                                            paymentContext.errorMessage = GooglePayManager.getInstance().getGooglePayErrorMessage(413);
                                            paymentContext.errorCode = 413;
                                            failureListener2.onFailure(this, paymentContext);
                                            return;
                                        }
                                        GooglePayPaymentProcessor.this.mServiceFragment.getCartContext().updateGooglePayPaymentData(fromIntent);
                                        GooglePayPaymentProcessor.this.mServiceFragment.getCartContext().updatePreferredPaymentMode("PaymentModeGoogle");
                                        GooglePayPaymentProcessor.this.charge(fromIntent, successListener2, failureListener2);
                                        GooglePayPaymentProcessor.this.mServiceFragment.hideLoadingSpinner();
                                    } else if (i2 == 0) {
                                        failureListener2.onCancel(this);
                                        GooglePayPaymentProcessor.this.mServiceFragment.hideLoadingSpinner();
                                    } else {
                                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_GWALLET_FETCH_FAILURE, hashMap);
                                        int intExtra = intent.getIntExtra("com.google.android.gms.wallet.EXTRA_ERROR_CODE", 413);
                                        String googlePayErrorMessage = GooglePayManager.getInstance().getGooglePayErrorMessage(intExtra);
                                        PaymentContext paymentContext2 = new PaymentContext();
                                        paymentContext2.errorMessage = googlePayErrorMessage;
                                        paymentContext2.errorCode = intExtra;
                                        failureListener2.onFailure(this, paymentContext2);
                                        GooglePayPaymentProcessor.this.mServiceFragment.hideLoadingSpinner();
                                    }
                                }
                            }));
                        }
                    });
                }

                public void onBraintreeFragmentLoadFailed(String str) {
                    GooglePayPaymentProcessor.this.mServiceFragment.hideLoadingSpinner();
                    if (str == null) {
                        str = WishApplication.getInstance().getString(R.string.general_payment_error);
                    }
                    new PaymentContext().errorMessage = str;
                }
            };
            cartPaymentProcessorServiceFragment.withBraintreeFragment(r0);
            return;
        }
        charge(this.mServiceFragment.getCartContext().getGooglePayPaymentData(), successListener, failureListener);
    }

    /* access modifiers changed from: private */
    public void charge(PaymentData paymentData, SuccessListener successListener, FailureListener failureListener) {
        final HashMap hashMap = new HashMap();
        hashMap.put("cart_type", this.mServiceFragment.getCartContext().getCartType().toString());
        this.mServiceFragment.showLoadingSpinner();
        CartPaymentProcessorServiceFragment cartPaymentProcessorServiceFragment = this.mServiceFragment;
        final PaymentData paymentData2 = paymentData;
        final SuccessListener successListener2 = successListener;
        final FailureListener failureListener2 = failureListener;
        AnonymousClass2 r0 = new BraintreeFragmentCallback() {
            public void onBraintreeFragmentLoaded(BraintreeFragment braintreeFragment) {
                GooglePayPaymentCollector stripeGooglePayPaymentCollector;
                if (GooglePayPaymentProcessor.this.mServiceFragment.getCartContext().getPaymentProcessor() == PaymentProcessor.Braintree) {
                    stripeGooglePayPaymentCollector = new BraintreeGooglePayPaymentCollector();
                } else {
                    stripeGooglePayPaymentCollector = new StripeGooglePayPaymentCollector();
                }
                GooglePayPaymentCollector googlePayPaymentCollector = stripeGooglePayPaymentCollector;
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_GWALLET_ORDER, hashMap);
                googlePayPaymentCollector.collectPayment(GooglePayPaymentProcessor.this.mServiceFragment.getCartContext(), paymentData2, braintreeFragment, new GooglePayPaymentCollector.SuccessListener() {
                    public void onSuccess(GooglePayPaymentCollector googlePayPaymentCollector, PaymentContext paymentContext) {
                        GooglePayPaymentProcessor.this.mServiceFragment.hideLoadingSpinner();
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_GWALLET_ORDER_SUCCESS, hashMap);
                        successListener2.onSuccess(this, paymentContext);
                    }
                }, new GooglePayPaymentCollector.FailureListener() {
                    public void onFailure(GooglePayPaymentCollector googlePayPaymentCollector, PaymentContext paymentContext) {
                        GooglePayPaymentProcessor.this.mServiceFragment.hideLoadingSpinner();
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_GWALLET_ORDER_FAILURE, hashMap);
                        failureListener2.onFailure(this, paymentContext);
                    }
                });
            }

            public void onBraintreeFragmentLoadFailed(String str) {
                GooglePayPaymentProcessor.this.mServiceFragment.hideLoadingSpinner();
                if (str == null) {
                    str = WishApplication.getInstance().getString(R.string.general_payment_error);
                }
                PaymentContext paymentContext = new PaymentContext();
                paymentContext.errorMessage = str;
                failureListener2.onFailure(this, paymentContext);
            }
        };
        cartPaymentProcessorServiceFragment.withBraintreeFragment(r0);
    }
}
