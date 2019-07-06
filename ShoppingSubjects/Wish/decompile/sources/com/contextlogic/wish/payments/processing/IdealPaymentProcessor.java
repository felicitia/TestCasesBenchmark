package com.contextlogic.wish.payments.processing;

import com.contextlogic.wish.R;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.service.standalone.InitiateIdealStripePaymentService;
import com.contextlogic.wish.api.service.standalone.InitiateIdealStripePaymentService.FailureCallback;
import com.contextlogic.wish.api.service.standalone.InitiateIdealStripePaymentService.SuccessCallback;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.payments.processing.CartPaymentProcessor.FailureListener;
import com.contextlogic.wish.payments.processing.CartPaymentProcessor.PaymentContext;
import com.contextlogic.wish.payments.processing.CartPaymentProcessor.SuccessListener;
import java.util.HashMap;

public class IdealPaymentProcessor extends CartPaymentProcessor {
    private InitiateIdealStripePaymentService mInitiateIdealStripePaymentService = new InitiateIdealStripePaymentService();

    public IdealPaymentProcessor(CartPaymentProcessorServiceFragment cartPaymentProcessorServiceFragment) {
        super(cartPaymentProcessorServiceFragment);
    }

    public void checkout(final SuccessListener successListener, final FailureListener failureListener) {
        final HashMap hashMap = new HashMap();
        hashMap.put("cart_type", this.mServiceFragment.getCartContext().getCartType().toString());
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_IDEAL_PLACE_ORDER, hashMap);
        this.mServiceFragment.showLoadingSpinner();
        this.mInitiateIdealStripePaymentService.requestService(this.mServiceFragment.getCartContext().getCheckoutOfferId(), this.mServiceFragment.getCartContext().getCartType().getValue(), new SuccessCallback() {
            public void onSuccess(String str) {
                IdealPaymentProcessor.this.mServiceFragment.hideLoadingSpinner();
                IdealPaymentProcessor.this.handleSuccessfulPayment();
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_COMMERCE_IDEAL_INITIATE_PAYMENT_SUCCESS, hashMap);
                PaymentContext paymentContext = new PaymentContext();
                paymentContext.buyUrl = str;
                paymentContext.showInExternalBrowser = true;
                successListener.onSuccess(this, paymentContext);
            }
        }, new FailureCallback() {
            public void onFailure(String str, int i) {
                IdealPaymentProcessor.this.mServiceFragment.hideLoadingSpinner();
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_COMMERCE_IDEAL_INITIATE_PAYMENT_FAILURE, hashMap);
                if (str == null) {
                    str = WishApplication.getInstance().getString(R.string.general_payment_error);
                }
                PaymentContext paymentContext = new PaymentContext();
                paymentContext.errorMessage = str;
                paymentContext.errorCode = i;
                failureListener.onFailure(this, paymentContext);
            }
        });
    }
}
