package com.contextlogic.wish.payments.processing;

import com.contextlogic.wish.api.model.WishDeclineRedirectInfo;
import com.contextlogic.wish.payments.braintree.BraintreeManager;
import com.contextlogic.wish.payments.riskified.RiskifiedManager;

public abstract class CartPaymentProcessor {
    protected CartPaymentProcessorServiceFragment mServiceFragment;

    public interface FailureListener {
        void onCancel(CartPaymentProcessor cartPaymentProcessor);

        void onFailure(CartPaymentProcessor cartPaymentProcessor, PaymentContext paymentContext);
    }

    public static class PaymentContext {
        public String buyUrl;
        public WishDeclineRedirectInfo declineRedirectInfo;
        public int errorCode;
        public String errorMessage;
        public boolean showInExternalBrowser;
        public String transactionId;
    }

    public interface SuccessListener {
        void onSuccess(CartPaymentProcessor cartPaymentProcessor, PaymentContext paymentContext);
    }

    public abstract void checkout(SuccessListener successListener, FailureListener failureListener);

    public CartPaymentProcessor(CartPaymentProcessorServiceFragment cartPaymentProcessorServiceFragment) {
        this.mServiceFragment = cartPaymentProcessorServiceFragment;
    }

    public void handleSuccessfulPayment() {
        performSuccessfulPaymentActions();
    }

    public static void performSuccessfulPaymentActions() {
        BraintreeManager.getInstance().setCachedBillingInfo(null);
        RiskifiedManager.getInstance().logPurchaseComplete();
    }

    public void checkoutWithCVV(SuccessListener successListener, FailureListener failureListener, String str) {
        checkout(successListener, failureListener);
    }
}
