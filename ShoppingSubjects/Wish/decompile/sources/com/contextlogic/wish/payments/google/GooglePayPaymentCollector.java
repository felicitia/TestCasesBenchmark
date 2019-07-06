package com.contextlogic.wish.payments.google;

import com.braintreepayments.api.BraintreeFragment;
import com.contextlogic.wish.payments.CartContext;
import com.contextlogic.wish.payments.processing.CartPaymentProcessor.PaymentContext;
import com.google.android.gms.wallet.PaymentData;

public abstract class GooglePayPaymentCollector {

    public interface FailureListener {
        void onFailure(GooglePayPaymentCollector googlePayPaymentCollector, PaymentContext paymentContext);
    }

    public interface SuccessListener {
        void onSuccess(GooglePayPaymentCollector googlePayPaymentCollector, PaymentContext paymentContext);
    }

    public abstract void collectPayment(CartContext cartContext, PaymentData paymentData, BraintreeFragment braintreeFragment, SuccessListener successListener, FailureListener failureListener);
}
