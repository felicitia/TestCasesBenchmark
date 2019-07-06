package com.contextlogic.wish.payments.google;

import com.braintreepayments.api.BraintreeFragment;
import com.braintreepayments.api.DataCollector;
import com.braintreepayments.api.GooglePayment;
import com.braintreepayments.api.interfaces.BraintreeErrorListener;
import com.braintreepayments.api.interfaces.BraintreeResponseListener;
import com.braintreepayments.api.interfaces.PaymentMethodNonceCreatedListener;
import com.braintreepayments.api.models.PaymentMethodNonce;
import com.contextlogic.wish.R;
import com.contextlogic.wish.api.service.standalone.InitiateGooglePayBraintreePaymentService;
import com.contextlogic.wish.api.service.standalone.InitiateGooglePayBraintreePaymentService.FailureCallback;
import com.contextlogic.wish.api.service.standalone.InitiateGooglePayBraintreePaymentService.SuccessCallback;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.payments.CartContext;
import com.contextlogic.wish.payments.braintree.BraintreeManager;
import com.contextlogic.wish.payments.google.GooglePayPaymentCollector.FailureListener;
import com.contextlogic.wish.payments.google.GooglePayPaymentCollector.SuccessListener;
import com.contextlogic.wish.payments.processing.CartPaymentProcessor.PaymentContext;
import com.google.android.gms.wallet.PaymentData;

public class BraintreeGooglePayPaymentCollector extends GooglePayPaymentCollector {
    /* access modifiers changed from: private */
    public InitiateGooglePayBraintreePaymentService mInitiateGooglePayBraintreePaymentService = new InitiateGooglePayBraintreePaymentService();

    public void collectPayment(CartContext cartContext, PaymentData paymentData, BraintreeFragment braintreeFragment, SuccessListener successListener, final FailureListener failureListener) {
        final BraintreeFragment braintreeFragment2 = braintreeFragment;
        final CartContext cartContext2 = cartContext;
        final PaymentData paymentData2 = paymentData;
        final SuccessListener successListener2 = successListener;
        final FailureListener failureListener2 = failureListener;
        AnonymousClass1 r0 = new PaymentMethodNonceCreatedListener() {
            public void onPaymentMethodNonceCreated(final PaymentMethodNonce paymentMethodNonce) {
                BraintreeManager.getInstance().clearBraintreeListeners(braintreeFragment2);
                DataCollector.collectDeviceData(braintreeFragment2, new BraintreeResponseListener<String>() {
                    public void onResponse(String str) {
                        BraintreeGooglePayPaymentCollector.this.mInitiateGooglePayBraintreePaymentService.requestService(cartContext2.getCurrencyCode(), paymentMethodNonce.getNonce(), paymentData2, cartContext2.getCheckoutOfferId(), str, cartContext2.getCartType().getValue(), new SuccessCallback() {
                            public void onSuccess(String str) {
                                PaymentContext paymentContext = new PaymentContext();
                                paymentContext.transactionId = str;
                                successListener2.onSuccess(this, paymentContext);
                            }
                        }, new FailureCallback() {
                            public void onFailure(String str, int i) {
                                if (str == null) {
                                    str = WishApplication.getInstance().getString(R.string.general_payment_error);
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
        AnonymousClass2 r10 = new BraintreeErrorListener() {
            public void onError(Exception exc) {
                String string = WishApplication.getInstance().getString(R.string.general_payment_error);
                PaymentContext paymentContext = new PaymentContext();
                paymentContext.errorMessage = string;
                failureListener.onFailure(this, paymentContext);
            }
        };
        BraintreeManager.getInstance().clearBraintreeListeners(braintreeFragment);
        BraintreeManager.getInstance().addBraintreeListener(braintreeFragment, r10);
        BraintreeManager.getInstance().addBraintreeListener(braintreeFragment, r0);
        GooglePayment.tokenize(braintreeFragment, paymentData);
    }
}
