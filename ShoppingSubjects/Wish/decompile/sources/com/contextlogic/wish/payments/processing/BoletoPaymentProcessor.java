package com.contextlogic.wish.payments.processing;

import com.contextlogic.wish.R;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.service.standalone.InitiateBoletoPaymentService;
import com.contextlogic.wish.api.service.standalone.InitiateBoletoPaymentService.FailureCallback;
import com.contextlogic.wish.api.service.standalone.InitiateBoletoPaymentService.SuccessCallback;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.payments.processing.CartPaymentProcessor.FailureListener;
import com.contextlogic.wish.payments.processing.CartPaymentProcessor.PaymentContext;
import com.contextlogic.wish.payments.processing.CartPaymentProcessor.SuccessListener;
import java.util.HashMap;

public class BoletoPaymentProcessor extends CartPaymentProcessor {
    private InitiateBoletoPaymentService mInitiateBoletoPaymentService = new InitiateBoletoPaymentService();

    public BoletoPaymentProcessor(CartPaymentProcessorServiceFragment cartPaymentProcessorServiceFragment) {
        super(cartPaymentProcessorServiceFragment);
    }

    public void checkout(final SuccessListener successListener, final FailureListener failureListener) {
        final HashMap hashMap = new HashMap();
        hashMap.put("cart_type", this.mServiceFragment.getCartContext().getCartType().toString());
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_BOLETO_PLACE_ORDER, hashMap);
        this.mServiceFragment.showLoadingSpinner();
        this.mInitiateBoletoPaymentService.requestService(this.mServiceFragment.getCartContext().getCurrencyCode(), this.mServiceFragment.getCartContext().getCheckoutOfferId(), this.mServiceFragment.getCartContext().getCartType().getValue(), new SuccessCallback() {
            public void onSuccess(String str) {
                BoletoPaymentProcessor.this.mServiceFragment.hideLoadingSpinner();
                BoletoPaymentProcessor.this.handleSuccessfulPayment();
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_BOLETO_PLACE_ORDER_SUCCESS, hashMap);
                PaymentContext paymentContext = new PaymentContext();
                paymentContext.transactionId = str;
                successListener.onSuccess(this, paymentContext);
            }
        }, new FailureCallback() {
            public void onFailure(String str, int i) {
                BoletoPaymentProcessor.this.mServiceFragment.hideLoadingSpinner();
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_BOLETO_PLACE_ORDER_FAILURE, hashMap);
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
