package com.contextlogic.wish.payments.processing;

import com.contextlogic.wish.R;
import com.contextlogic.wish.analytics.CommerceLogger;
import com.contextlogic.wish.analytics.CommerceLogger.Action;
import com.contextlogic.wish.analytics.CommerceLogger.Result;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishDeclineRedirectInfo;
import com.contextlogic.wish.api.service.ExternalJsonApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.standalone.EbanxSetCVVService;
import com.contextlogic.wish.api.service.standalone.InitiateEbanxPaymentService;
import com.contextlogic.wish.api.service.standalone.InitiateEbanxPaymentService.FailureCallback;
import com.contextlogic.wish.api.service.standalone.InitiateEbanxPaymentService.SuccessCallback;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.payments.CartContext;
import com.contextlogic.wish.payments.processing.CartPaymentProcessor.FailureListener;
import com.contextlogic.wish.payments.processing.CartPaymentProcessor.PaymentContext;
import com.contextlogic.wish.payments.processing.CartPaymentProcessor.SuccessListener;
import java.util.HashMap;

public class EbanxCreditCardPaymentProcessor extends CartPaymentProcessor {
    private EbanxSetCVVService mEbanxSetCVVService = new EbanxSetCVVService();
    private InitiateEbanxPaymentService mInitiateEbanxPaymentService = new InitiateEbanxPaymentService();

    public EbanxCreditCardPaymentProcessor(CartPaymentProcessorServiceFragment cartPaymentProcessorServiceFragment) {
        super(cartPaymentProcessorServiceFragment);
    }

    public void checkout(SuccessListener successListener, FailureListener failureListener) {
        HashMap hashMap = new HashMap();
        hashMap.put("cart_type", this.mServiceFragment.getCartContext().getCartType().toString());
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_PLACE_ORDER, hashMap);
        checkoutWithCVV(successListener, failureListener, null);
    }

    public void checkoutWithCVV(final SuccessListener successListener, final FailureListener failureListener, String str) {
        final HashMap hashMap = new HashMap();
        hashMap.put("cart_type", this.mServiceFragment.getCartContext().getCartType().toString());
        if (str == null || str == "SetCVVMarker") {
            this.mServiceFragment.showLoadingSpinner();
            this.mInitiateEbanxPaymentService.requestService(this.mServiceFragment.getCartContext().getCurrencyCode(), str, this.mServiceFragment.getCartContext().getCheckoutOfferId(), this.mServiceFragment.getCartContext().getCartType().getValue(), new SuccessCallback() {
                public void onSuccess(String str) {
                    EbanxCreditCardPaymentProcessor.this.handleSuccessfulPayment();
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_PLACE_ORDER_SUCCESS, hashMap);
                    PaymentContext paymentContext = new PaymentContext();
                    paymentContext.transactionId = str;
                    successListener.onSuccess(this, paymentContext);
                }
            }, new FailureCallback() {
                public void onFailure(String str, int i, WishDeclineRedirectInfo wishDeclineRedirectInfo) {
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
            return;
        }
        CartContext cartContext = this.mServiceFragment.getCartContext();
        this.mEbanxSetCVVService.requestService(cartContext.getUserBillingInfo().getDefaultCreditCardInfo(cartContext.getPaymentProcessor()).getToken(), str, new EbanxSetCVVService.SuccessCallback() {
            public void onSuccess(String str, String str2) {
                EbanxCreditCardPaymentProcessor.this.checkoutWithCVV(successListener, failureListener, "SetCVVMarker");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                EbanxCreditCardPaymentProcessor.this.mServiceFragment.hideLoadingSpinner();
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_PLACE_ORDER_FAILURE, hashMap);
                HashMap hashMap = new HashMap();
                hashMap.put("error_message", str);
                CommerceLogger.logError(Action.NATIVE_EBANX_SET_CVV, Result.EBANX_SDK_ERROR, hashMap);
            }
        });
    }
}
