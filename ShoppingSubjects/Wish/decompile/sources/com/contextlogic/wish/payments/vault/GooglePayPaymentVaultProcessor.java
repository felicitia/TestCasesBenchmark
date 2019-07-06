package com.contextlogic.wish.payments.vault;

import android.content.Intent;
import android.os.Bundle;
import com.braintreepayments.api.BraintreeFragment;
import com.braintreepayments.api.GooglePayment;
import com.braintreepayments.api.interfaces.BraintreeResponseListener;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseActivity.ActivityResultCallback;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.payments.braintree.BraintreeFragmentCallback;
import com.contextlogic.wish.payments.google.GooglePayManager;
import com.contextlogic.wish.payments.vault.CartPaymentVaultProcessor.PrepareListener;
import com.contextlogic.wish.payments.vault.CartPaymentVaultProcessor.SaveListener;
import com.google.android.gms.wallet.PaymentData;
import com.google.android.gms.wallet.PaymentDataRequest;
import java.util.HashMap;

public class GooglePayPaymentVaultProcessor extends CartPaymentVaultProcessor {
    public GooglePayPaymentVaultProcessor(CartPaymentVaultProcessorServiceFragment cartPaymentVaultProcessorServiceFragment) {
        super(cartPaymentVaultProcessorServiceFragment);
    }

    public void prepareTab(final PrepareListener prepareListener) {
        if (this.mServiceFragment.getCartContext().getGooglePayPaymentData() == null) {
            this.mServiceFragment.showLoadingSpinner();
            final HashMap hashMap = new HashMap();
            hashMap.put("cart_type", this.mServiceFragment.getCartContext().getCartType().toString());
            this.mServiceFragment.withBraintreeFragment(new BraintreeFragmentCallback() {
                public void onBraintreeFragmentLoaded(final BraintreeFragment braintreeFragment) {
                    GooglePayment.isReadyToPay(braintreeFragment, new BraintreeResponseListener<Boolean>() {
                        public void onResponse(Boolean bool) {
                            GooglePayManager.getInstance().setGooglePayReady(bool.booleanValue());
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_GWALLET_FETCH, hashMap);
                            final PaymentDataRequest createPaymentDataRequest = GooglePayManager.getInstance().createPaymentDataRequest(GooglePayPaymentVaultProcessor.this.mServiceFragment.getCartContext(), braintreeFragment);
                            GooglePayPaymentVaultProcessor.this.mServiceFragment.withActivity(new ActivityTask<BaseActivity>() {
                                public void performTask(BaseActivity baseActivity) {
                                    baseActivity.getServiceFragment().loadPaymentData(createPaymentDataRequest, baseActivity.addResultCodeCallback(new ActivityResultCallback() {
                                        public void onActivityResult(BaseActivity baseActivity, int i, int i2, Intent intent) {
                                            if (i2 == -1) {
                                                GooglePayPaymentVaultProcessor.this.mServiceFragment.hideLoadingSpinner();
                                                GooglePayPaymentVaultProcessor.this.mServiceFragment.getCartContext().updateGooglePayPaymentData(PaymentData.getFromIntent(intent));
                                                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_GWALLET_FETCH_SUCCESS, hashMap);
                                                prepareListener.onTabPrepared(this);
                                            } else if (i2 == 0) {
                                                GooglePayPaymentVaultProcessor.this.mServiceFragment.hideLoadingSpinner();
                                                prepareListener.onTabPrepareCancelled(this);
                                            } else {
                                                GooglePayPaymentVaultProcessor.this.mServiceFragment.hideLoadingSpinner();
                                                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_GWALLET_FETCH_FAILURE, hashMap);
                                                prepareListener.onTabPrepareFailed(this, GooglePayManager.getInstance().getGooglePayErrorMessage(intent.getIntExtra("com.google.android.gms.wallet.EXTRA_ERROR_CODE", 413)));
                                            }
                                        }
                                    }));
                                }
                            });
                        }
                    });
                }

                public void onBraintreeFragmentLoadFailed(String str) {
                    GooglePayPaymentVaultProcessor.this.mServiceFragment.hideLoadingSpinner();
                    prepareListener.onTabPrepareFailed(this, str);
                }
            });
            return;
        }
        prepareListener.onTabPrepared(this);
    }

    public void save(SaveListener saveListener, Bundle bundle) {
        HashMap hashMap = new HashMap();
        hashMap.put("cart_type", this.mServiceFragment.getCartContext().getCartType().toString());
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_BILLING_NEXT_GWALLET, hashMap);
        if (this.mServiceFragment.getCartContext().getGooglePayPaymentData() == null) {
            this.mServiceFragment.hideLoadingSpinner();
            saveListener.onSaveFailed(this, WishApplication.getInstance().getString(R.string.google_pay_error));
            return;
        }
        this.mServiceFragment.hideLoadingSpinner();
        this.mServiceFragment.getCartContext().updatePreferredPaymentMode("PaymentModeGoogle");
        saveListener.onSaveComplete(this);
    }
}
