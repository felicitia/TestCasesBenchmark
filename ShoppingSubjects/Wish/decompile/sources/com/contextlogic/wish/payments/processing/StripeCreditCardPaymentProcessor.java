package com.contextlogic.wish.payments.processing;

import com.contextlogic.wish.R;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishCachedBillingInfo;
import com.contextlogic.wish.api.model.WishDeclineRedirectInfo;
import com.contextlogic.wish.api.service.standalone.InitiateStripePaymentService;
import com.contextlogic.wish.api.service.standalone.InitiateStripePaymentService.FailureCallback;
import com.contextlogic.wish.api.service.standalone.InitiateStripePaymentService.SuccessCallback;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.payments.processing.CartPaymentProcessor.FailureListener;
import com.contextlogic.wish.payments.processing.CartPaymentProcessor.PaymentContext;
import com.contextlogic.wish.payments.processing.CartPaymentProcessor.SuccessListener;
import com.contextlogic.wish.payments.stripe.StripeManager;
import java.util.HashMap;

public class StripeCreditCardPaymentProcessor extends CartPaymentProcessor {
    private InitiateStripePaymentService mInitiateStripePaymentService = new InitiateStripePaymentService();

    public StripeCreditCardPaymentProcessor(CartPaymentProcessorServiceFragment cartPaymentProcessorServiceFragment) {
        super(cartPaymentProcessorServiceFragment);
    }

    public void checkout(final SuccessListener successListener, final FailureListener failureListener) {
        String str;
        final HashMap hashMap = new HashMap();
        hashMap.put("cart_type", this.mServiceFragment.getCartContext().getCartType().toString());
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_PLACE_ORDER, hashMap);
        WishCachedBillingInfo cachedBillingInfo = StripeManager.getInstance().getCachedBillingInfo();
        if (cachedBillingInfo != null) {
            str = cachedBillingInfo.getStripeToken();
            cachedBillingInfo.clearStripeToken();
        } else {
            str = null;
        }
        String str2 = str;
        this.mServiceFragment.showLoadingSpinner();
        this.mInitiateStripePaymentService.requestService(str2, this.mServiceFragment.getCartContext().getCurrencyCode(), this.mServiceFragment.getCartContext().getCheckoutOfferId(), this.mServiceFragment.getCartContext().getCartType().getValue(), new SuccessCallback() {
            public void onSuccess(String str) {
                StripeCreditCardPaymentProcessor.this.mServiceFragment.hideLoadingSpinner();
                StripeCreditCardPaymentProcessor.this.handleSuccessfulPayment();
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_PLACE_ORDER_SUCCESS, hashMap);
                PaymentContext paymentContext = new PaymentContext();
                paymentContext.transactionId = str;
                successListener.onSuccess(this, paymentContext);
            }
        }, new FailureCallback() {
            public void onFailure(String str, int i, WishDeclineRedirectInfo wishDeclineRedirectInfo) {
                StripeCreditCardPaymentProcessor.this.mServiceFragment.hideLoadingSpinner();
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_PLACE_ORDER_FAILURE, hashMap);
                if (str == null) {
                    str = WishApplication.getInstance().getString(R.string.general_payment_error);
                }
                PaymentContext paymentContext = new PaymentContext();
                paymentContext.errorMessage = str;
                paymentContext.errorCode = i;
                paymentContext.declineRedirectInfo = wishDeclineRedirectInfo;
                failureListener.onFailure(this, paymentContext);
            }
        });
    }
}
