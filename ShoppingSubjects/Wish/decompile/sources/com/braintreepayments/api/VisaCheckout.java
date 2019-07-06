package com.braintreepayments.api;

import android.content.Intent;
import com.braintreepayments.api.exceptions.BraintreeException;
import com.braintreepayments.api.interfaces.PaymentMethodNonceCallback;
import com.braintreepayments.api.models.PaymentMethodNonce;
import com.braintreepayments.api.models.VisaCheckoutBuilder;
import com.braintreepayments.api.models.VisaCheckoutNonce;
import com.visa.checkout.VisaPaymentSummary;

public class VisaCheckout {
    static void onActivityResult(BraintreeFragment braintreeFragment, int i, Intent intent) {
        if (i == 0) {
            braintreeFragment.sendAnalyticsEvent("visacheckout.result.cancelled");
        } else if (i != -1 || intent == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Visa Checkout responded with an invalid resultCode: ");
            sb.append(i);
            braintreeFragment.postCallback((Exception) new BraintreeException(sb.toString()));
            braintreeFragment.sendAnalyticsEvent("visacheckout.result.failed");
        } else {
            tokenize(braintreeFragment, intent.getParcelableExtra("payment_summary"));
            braintreeFragment.sendAnalyticsEvent("visacheckout.result.succeeded");
        }
    }

    static void tokenize(final BraintreeFragment braintreeFragment, VisaPaymentSummary visaPaymentSummary) {
        TokenizationClient.tokenize(braintreeFragment, new VisaCheckoutBuilder(visaPaymentSummary), new PaymentMethodNonceCallback() {
            public void success(PaymentMethodNonce paymentMethodNonce) {
                VisaCheckoutNonce visaCheckoutNonce = (VisaCheckoutNonce) paymentMethodNonce;
                braintreeFragment.postCallback(paymentMethodNonce);
                braintreeFragment.sendAnalyticsEvent("visacheckout.tokenize.succeeded");
            }

            public void failure(Exception exc) {
                braintreeFragment.postCallback(exc);
                braintreeFragment.sendAnalyticsEvent("visacheckout.tokenize.failed");
            }
        });
    }
}
