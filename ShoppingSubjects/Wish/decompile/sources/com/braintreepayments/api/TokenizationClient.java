package com.braintreepayments.api;

import com.braintreepayments.api.interfaces.ConfigurationListener;
import com.braintreepayments.api.interfaces.HttpResponseCallback;
import com.braintreepayments.api.interfaces.PaymentMethodNonceCallback;
import com.braintreepayments.api.internal.BraintreeHttpClient;
import com.braintreepayments.api.models.Configuration;
import com.braintreepayments.api.models.PaymentMethodBuilder;
import com.braintreepayments.api.models.PaymentMethodNonce;
import org.json.JSONException;

class TokenizationClient {
    static void tokenize(final BraintreeFragment braintreeFragment, final PaymentMethodBuilder paymentMethodBuilder, final PaymentMethodNonceCallback paymentMethodNonceCallback) {
        paymentMethodBuilder.setSessionId(braintreeFragment.getSessionId());
        braintreeFragment.waitForConfiguration(new ConfigurationListener() {
            public void onConfigurationFetched(Configuration configuration) {
                BraintreeHttpClient httpClient = braintreeFragment.getHttpClient();
                StringBuilder sb = new StringBuilder();
                sb.append("payment_methods/");
                sb.append(paymentMethodBuilder.getApiPath());
                httpClient.post(TokenizationClient.versionedPath(sb.toString()), paymentMethodBuilder.build(), new HttpResponseCallback() {
                    public void success(String str) {
                        try {
                            paymentMethodNonceCallback.success(PaymentMethodNonce.parsePaymentMethodNonces(str, paymentMethodBuilder.getResponsePaymentMethodType()));
                        } catch (JSONException e) {
                            paymentMethodNonceCallback.failure(e);
                        }
                    }

                    public void failure(Exception exc) {
                        paymentMethodNonceCallback.failure(exc);
                    }
                });
            }
        });
    }

    static String versionedPath(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("/v1/");
        sb.append(str);
        return sb.toString();
    }
}
