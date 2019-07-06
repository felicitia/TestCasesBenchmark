package com.contextlogic.wish.payments.vault;

import android.os.Bundle;
import com.braintreepayments.api.BraintreeFragment;
import com.braintreepayments.api.DataCollector;
import com.braintreepayments.api.PayPal;
import com.braintreepayments.api.interfaces.BraintreeErrorListener;
import com.braintreepayments.api.interfaces.BraintreeResponseListener;
import com.braintreepayments.api.interfaces.PaymentMethodNonceCreatedListener;
import com.braintreepayments.api.models.PayPalRequest;
import com.braintreepayments.api.models.PaymentMethodNonce;
import com.contextlogic.wish.R;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishUserBillingInfo;
import com.contextlogic.wish.api.service.standalone.PreAuthorizePayPalPaymentService;
import com.contextlogic.wish.api.service.standalone.PreAuthorizePayPalPaymentService.FailureCallback;
import com.contextlogic.wish.api.service.standalone.PreAuthorizePayPalPaymentService.SuccessCallback;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.payments.braintree.BraintreeFragmentCallback;
import com.contextlogic.wish.payments.braintree.BraintreeManager;
import com.contextlogic.wish.payments.vault.CartPaymentVaultProcessor.PrepareListener;
import com.contextlogic.wish.payments.vault.CartPaymentVaultProcessor.SaveListener;
import java.util.HashMap;

public class FuturePayPalPaymentVaultProcessor extends CartPaymentVaultProcessor {
    /* access modifiers changed from: private */
    public BraintreeErrorListener mBraintreeErrorListener;
    /* access modifiers changed from: private */
    public PaymentMethodNonceCreatedListener mNonceCreatedListener;
    /* access modifiers changed from: private */
    public PreAuthorizePayPalPaymentService mPreAuthorizePayPalPaymentService = new PreAuthorizePayPalPaymentService();

    public FuturePayPalPaymentVaultProcessor(CartPaymentVaultProcessorServiceFragment cartPaymentVaultProcessorServiceFragment) {
        super(cartPaymentVaultProcessorServiceFragment);
    }

    public void prepareTab(PrepareListener prepareListener) {
        prepareListener.onTabPrepared(this);
    }

    public void save(final SaveListener saveListener, Bundle bundle) {
        this.mServiceFragment.showLoadingSpinner();
        final HashMap hashMap = new HashMap();
        hashMap.put("cart_type", this.mServiceFragment.getCartContext().getCartType().toString());
        this.mServiceFragment.withBraintreeFragment(new BraintreeFragmentCallback() {
            public void onBraintreeFragmentLoaded(final BraintreeFragment braintreeFragment) {
                FuturePayPalPaymentVaultProcessor.this.mServiceFragment.hideLoadingSpinner();
                FuturePayPalPaymentVaultProcessor.this.mNonceCreatedListener = new PaymentMethodNonceCreatedListener() {
                    public void onPaymentMethodNonceCreated(final PaymentMethodNonce paymentMethodNonce) {
                        BraintreeManager.getInstance().clearBraintreeListeners(braintreeFragment);
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_PAYPAL_AUTH_SUCCESS, hashMap);
                        FuturePayPalPaymentVaultProcessor.this.mServiceFragment.showLoadingSpinner();
                        DataCollector.collectDeviceData(braintreeFragment, new BraintreeResponseListener<String>() {
                            public void onResponse(String str) {
                                FuturePayPalPaymentVaultProcessor.this.mPreAuthorizePayPalPaymentService.requestService(paymentMethodNonce, str, FuturePayPalPaymentVaultProcessor.this.mServiceFragment.getCartContext().getCartType().getValue(), new SuccessCallback() {
                                    public void onSuccess(WishUserBillingInfo wishUserBillingInfo) {
                                        FuturePayPalPaymentVaultProcessor.this.mServiceFragment.hideLoadingSpinner();
                                        FuturePayPalPaymentVaultProcessor.this.mServiceFragment.getCartContext().updatePreferredPaymentMode("PaymentModePayPal");
                                        FuturePayPalPaymentVaultProcessor.this.mServiceFragment.getCartContext().updateData(FuturePayPalPaymentVaultProcessor.this.mServiceFragment.getCartContext().getCart(), FuturePayPalPaymentVaultProcessor.this.mServiceFragment.getCartContext().getShippingInfo(), wishUserBillingInfo);
                                        saveListener.onSaveComplete(this);
                                    }
                                }, new FailureCallback() {
                                    public void onFailure(String str, int i) {
                                        FuturePayPalPaymentVaultProcessor.this.mServiceFragment.hideLoadingSpinner();
                                        if (str == null) {
                                            str = WishApplication.getInstance().getString(R.string.paypal_payment_error);
                                        }
                                        saveListener.onSaveFailed(this, str);
                                    }
                                });
                            }
                        });
                    }
                };
                FuturePayPalPaymentVaultProcessor.this.mBraintreeErrorListener = new BraintreeErrorListener() {
                    public void onError(Exception exc) {
                        BraintreeManager.getInstance().clearBraintreeListeners(braintreeFragment);
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_PAYPAL_AUTH_ERROR, hashMap);
                        FuturePayPalPaymentVaultProcessor.this.mServiceFragment.hideLoadingSpinner();
                        saveListener.onSaveFailed(this, WishApplication.getInstance().getString(R.string.paypal_payment_error));
                    }
                };
                BraintreeManager.getInstance().clearBraintreeListeners(braintreeFragment);
                BraintreeManager.getInstance().addBraintreeListener(braintreeFragment, FuturePayPalPaymentVaultProcessor.this.mBraintreeErrorListener);
                BraintreeManager.getInstance().addBraintreeListener(braintreeFragment, FuturePayPalPaymentVaultProcessor.this.mNonceCreatedListener);
                PayPalRequest payPalRequest = new PayPalRequest();
                if (FuturePayPalPaymentVaultProcessor.this.mServiceFragment.getCartContext().isInCheckoutFlow()) {
                    payPalRequest.userAction("commit");
                } else {
                    payPalRequest.userAction("");
                }
                PayPal.requestBillingAgreement(braintreeFragment, payPalRequest);
            }

            public void onBraintreeFragmentLoadFailed(String str) {
                FuturePayPalPaymentVaultProcessor.this.mServiceFragment.hideLoadingSpinner();
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_PAYPAL_AUTH_ERROR, hashMap);
                if (str == null) {
                    str = WishApplication.getInstance().getString(R.string.paypal_payment_error);
                }
                saveListener.onSaveFailed(this, str);
            }
        });
    }
}
