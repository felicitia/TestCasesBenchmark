package com.contextlogic.wish.payments.processing;

import com.contextlogic.wish.R;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.service.standalone.InitiateCommerceLoanPaymentService;
import com.contextlogic.wish.api.service.standalone.InitiateCommerceLoanPaymentService.FailureCallback;
import com.contextlogic.wish.api.service.standalone.InitiateCommerceLoanPaymentService.SuccessCallback;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.payments.CartContext;
import com.contextlogic.wish.payments.processing.CartPaymentProcessor.FailureListener;
import com.contextlogic.wish.payments.processing.CartPaymentProcessor.PaymentContext;
import com.contextlogic.wish.payments.processing.CartPaymentProcessor.SuccessListener;
import java.util.HashMap;

public class CommerceLoanPaymentProcessor extends CartPaymentProcessor {
    private InitiateCommerceLoanPaymentService mInitiateCommerceLoanPaymentService = new InitiateCommerceLoanPaymentService();

    public enum LoanType {
        PAY_LATER(1),
        TWO_PAYMENTS(2);
        
        private final int value;

        private LoanType(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public CommerceLoanPaymentProcessor(CartPaymentProcessorServiceFragment cartPaymentProcessorServiceFragment) {
        super(cartPaymentProcessorServiceFragment);
    }

    public void checkout(final SuccessListener successListener, final FailureListener failureListener) {
        final HashMap hashMap = new HashMap();
        hashMap.put("cart_type", this.mServiceFragment.getCartContext().getCartType().toString());
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_COMMERCE_LOAN_PLACE_ORDER, hashMap);
        LoanType loanType = ExperimentDataCenter.getInstance().canSeePayHalfBillingOption() ? LoanType.TWO_PAYMENTS : LoanType.PAY_LATER;
        this.mServiceFragment.showLoadingSpinner();
        CartContext cartContext = this.mServiceFragment.getCartContext();
        this.mInitiateCommerceLoanPaymentService.requestService(cartContext.getCurrencyCode(), cartContext.getCheckoutOfferId(), cartContext.getCartType().getValue(), loanType, new SuccessCallback() {
            public void onSuccess(String str) {
                CommerceLoanPaymentProcessor.this.mServiceFragment.hideLoadingSpinner();
                CommerceLoanPaymentProcessor.this.handleSuccessfulPayment();
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_COMMERCE_LOAN_PLACE_ORDER_SUCCESS, hashMap);
                PaymentContext paymentContext = new PaymentContext();
                paymentContext.transactionId = str;
                successListener.onSuccess(this, paymentContext);
            }
        }, new FailureCallback() {
            public void onFailure(String str, int i) {
                CommerceLoanPaymentProcessor.this.mServiceFragment.hideLoadingSpinner();
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_COMMERCE_LOAN_PLACE_ORDER_FAILUE, hashMap);
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
