package com.contextlogic.wish.payments.vault;

import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.payments.CartContext;
import com.contextlogic.wish.payments.braintree.BraintreeFragmentCallback;

public interface CartPaymentVaultProcessorServiceFragment<A extends BaseActivity> {
    CartContext getCartContext();

    void hideLoadingSpinner();

    void showLoadingSpinner();

    void withActivity(ActivityTask<A> activityTask);

    void withBraintreeFragment(BraintreeFragmentCallback braintreeFragmentCallback);
}
