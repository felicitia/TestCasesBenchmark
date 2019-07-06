package com.braintreepayments.api;

import android.content.Intent;
import com.braintreepayments.api.exceptions.BraintreeException;
import com.braintreepayments.api.exceptions.ErrorWithResponse;
import com.braintreepayments.api.models.PaymentMethodNonce;
import com.braintreepayments.api.models.ThreeDSecureAuthenticationResponse;

public class ThreeDSecure {
    protected static void onActivityResult(BraintreeFragment braintreeFragment, int i, Intent intent) {
        if (i == -1) {
            ThreeDSecureAuthenticationResponse threeDSecureAuthenticationResponse = (ThreeDSecureAuthenticationResponse) intent.getParcelableExtra("com.braintreepayments.api.EXTRA_THREE_D_SECURE_RESULT");
            if (threeDSecureAuthenticationResponse.isSuccess()) {
                braintreeFragment.postCallback((PaymentMethodNonce) threeDSecureAuthenticationResponse.getCardNonce());
            } else if (threeDSecureAuthenticationResponse.getException() != null) {
                braintreeFragment.postCallback((Exception) new BraintreeException(threeDSecureAuthenticationResponse.getException()));
            } else {
                braintreeFragment.postCallback((Exception) new ErrorWithResponse(422, threeDSecureAuthenticationResponse.getErrors()));
            }
        }
    }
}
