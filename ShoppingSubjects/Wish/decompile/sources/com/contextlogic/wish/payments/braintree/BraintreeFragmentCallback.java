package com.contextlogic.wish.payments.braintree;

import com.braintreepayments.api.BraintreeFragment;

public interface BraintreeFragmentCallback {
    void onBraintreeFragmentLoadFailed(String str);

    void onBraintreeFragmentLoaded(BraintreeFragment braintreeFragment);
}
