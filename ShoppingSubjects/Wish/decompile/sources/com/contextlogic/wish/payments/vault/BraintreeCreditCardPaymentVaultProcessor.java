package com.contextlogic.wish.payments.vault;

import android.os.Bundle;
import com.braintreepayments.api.BraintreeFragment;
import com.braintreepayments.api.Card;
import com.braintreepayments.api.DataCollector;
import com.braintreepayments.api.interfaces.BraintreeErrorListener;
import com.braintreepayments.api.interfaces.BraintreeResponseListener;
import com.braintreepayments.api.interfaces.PaymentMethodNonceCreatedListener;
import com.braintreepayments.api.models.CardBuilder;
import com.braintreepayments.api.models.PaymentMethodNonce;
import com.contextlogic.wish.analytics.CommerceLogger;
import com.contextlogic.wish.analytics.CommerceLogger.Action;
import com.contextlogic.wish.analytics.CommerceLogger.Result;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishCachedBillingInfo;
import com.contextlogic.wish.api.model.WishCachedBillingInfo.CachedBillingInfoSaveCallback;
import com.contextlogic.wish.api.model.WishShippingInfo;
import com.contextlogic.wish.api.model.WishUserBillingInfo;
import com.contextlogic.wish.api.service.standalone.UpdateBraintreeBillingInfoService;
import com.contextlogic.wish.api.service.standalone.UpdateBraintreeBillingInfoService.FailureCallback;
import com.contextlogic.wish.api.service.standalone.UpdateBraintreeBillingInfoService.SuccessCallback;
import com.contextlogic.wish.payments.braintree.BraintreeFragmentCallback;
import com.contextlogic.wish.payments.braintree.BraintreeManager;
import com.contextlogic.wish.payments.vault.CartPaymentVaultProcessor.PrepareListener;
import com.contextlogic.wish.payments.vault.CartPaymentVaultProcessor.SaveListener;
import com.contextlogic.wish.util.CreditCardUtil;
import com.contextlogic.wish.util.CreditCardUtil.CreditCardHolder;
import java.util.HashMap;

public class BraintreeCreditCardPaymentVaultProcessor extends CartPaymentVaultProcessor {
    /* access modifiers changed from: private */
    public BraintreeErrorListener mBraintreeErrorListener;
    /* access modifiers changed from: private */
    public PaymentMethodNonceCreatedListener mNonceCreatedListener;
    /* access modifiers changed from: private */
    public UpdateBraintreeBillingInfoService mUpdateBraintreeBillingInfoService = new UpdateBraintreeBillingInfoService();

    public BraintreeCreditCardPaymentVaultProcessor(CartPaymentVaultProcessorServiceFragment cartPaymentVaultProcessorServiceFragment) {
        super(cartPaymentVaultProcessorServiceFragment);
    }

    public void prepareTab(PrepareListener prepareListener) {
        prepareListener.onTabPrepared(this);
    }

    public void save(SaveListener saveListener, Bundle bundle) {
        this.mServiceFragment.showLoadingSpinner();
        final HashMap hashMap = new HashMap();
        hashMap.put("cart_type", this.mServiceFragment.getCartContext().getCartType().toString());
        final CreditCardHolder creditCardHolder = new CreditCardHolder(bundle.getString("ParamCreditCardId"), bundle.getString("ParamCreditCardNumber"), bundle.getString("ParamCreditCardExpiry"), bundle.getString("ParamCreditCardCvv"));
        final WishShippingInfo parseBillingAddress = parseBillingAddress(bundle);
        final boolean z = bundle.getBoolean("paramIsForCommerceLoan", false);
        CartPaymentVaultProcessorServiceFragment cartPaymentVaultProcessorServiceFragment = this.mServiceFragment;
        final SaveListener saveListener2 = saveListener;
        AnonymousClass1 r1 = new BraintreeFragmentCallback() {
            public void onBraintreeFragmentLoaded(final BraintreeFragment braintreeFragment) {
                BraintreeCreditCardPaymentVaultProcessor.this.mNonceCreatedListener = new PaymentMethodNonceCreatedListener() {
                    public void onPaymentMethodNonceCreated(final PaymentMethodNonce paymentMethodNonce) {
                        BraintreeManager.getInstance().clearBraintreeListeners(braintreeFragment);
                        DataCollector.collectDeviceData(braintreeFragment, new BraintreeResponseListener<String>() {
                            public void onResponse(String str) {
                                BraintreeCreditCardPaymentVaultProcessor.this.mUpdateBraintreeBillingInfoService.requestService(creditCardHolder.getCardId(), paymentMethodNonce.getNonce(), str, parseBillingAddress, z, new SuccessCallback() {
                                    public void onSuccess(final WishUserBillingInfo wishUserBillingInfo) {
                                        CommerceLogger.logSuccess(Action.NATIVE_SAVE_TABBED_BILLING_INFO, Result.SUCCESS, null);
                                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_BILLING_NEXT_CC_SUCCESS, hashMap);
                                        new WishCachedBillingInfo(braintreeFragment, creditCardHolder, parseBillingAddress, new CachedBillingInfoSaveCallback() {
                                            public void onSaveComplete(WishCachedBillingInfo wishCachedBillingInfo) {
                                                BraintreeManager.getInstance().setCachedBillingInfo(wishCachedBillingInfo);
                                                BraintreeCreditCardPaymentVaultProcessor.this.mServiceFragment.hideLoadingSpinner();
                                                BraintreeCreditCardPaymentVaultProcessor.this.mServiceFragment.getCartContext().updatePreferredPaymentMode("PaymentModeCC");
                                                BraintreeCreditCardPaymentVaultProcessor.this.mServiceFragment.getCartContext().updateData(BraintreeCreditCardPaymentVaultProcessor.this.mServiceFragment.getCartContext().getCart(), BraintreeCreditCardPaymentVaultProcessor.this.mServiceFragment.getCartContext().getShippingInfo(), wishUserBillingInfo);
                                                saveListener2.onSaveComplete(this);
                                            }

                                            public void onSaveFailure(WishCachedBillingInfo wishCachedBillingInfo) {
                                                BraintreeCreditCardPaymentVaultProcessor.this.mServiceFragment.hideLoadingSpinner();
                                                BraintreeCreditCardPaymentVaultProcessor.this.mServiceFragment.getCartContext().updatePreferredPaymentMode("PaymentModeCC");
                                                BraintreeCreditCardPaymentVaultProcessor.this.mServiceFragment.getCartContext().updateData(BraintreeCreditCardPaymentVaultProcessor.this.mServiceFragment.getCartContext().getCart(), BraintreeCreditCardPaymentVaultProcessor.this.mServiceFragment.getCartContext().getShippingInfo(), wishUserBillingInfo);
                                                saveListener2.onSaveComplete(this);
                                            }
                                        });
                                    }
                                }, new FailureCallback() {
                                    public void onFailure(String str) {
                                        HashMap hashMap = new HashMap();
                                        if (str != null) {
                                            hashMap.put("error_message", str);
                                        }
                                        CommerceLogger.logError(Action.NATIVE_SAVE_TABBED_BILLING_INFO, Result.API_ERROR, hashMap);
                                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_BILLING_NEXT_CC_FAILURE, hashMap);
                                        BraintreeCreditCardPaymentVaultProcessor.this.mServiceFragment.hideLoadingSpinner();
                                        saveListener2.onSaveFailed(this, str);
                                    }
                                });
                            }
                        });
                    }
                };
                BraintreeCreditCardPaymentVaultProcessor.this.mBraintreeErrorListener = new BraintreeErrorListener() {
                    public void onError(Exception exc) {
                        BraintreeManager.getInstance().clearBraintreeListeners(braintreeFragment);
                        HashMap hashMap = new HashMap();
                        if (exc != null) {
                            hashMap.put("error_message", exc.toString());
                        }
                        CommerceLogger.logError(Action.NATIVE_SAVE_TABBED_BILLING_INFO, Result.BRAINTREE_SDK_ERROR, hashMap);
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_BILLING_NEXT_CC_FAILURE, hashMap);
                        BraintreeCreditCardPaymentVaultProcessor.this.mServiceFragment.hideLoadingSpinner();
                        saveListener2.onSaveFailed(this, null);
                    }
                };
                BraintreeManager.getInstance().clearBraintreeListeners(braintreeFragment);
                BraintreeManager.getInstance().addBraintreeListener(braintreeFragment, BraintreeCreditCardPaymentVaultProcessor.this.mBraintreeErrorListener);
                BraintreeManager.getInstance().addBraintreeListener(braintreeFragment, BraintreeCreditCardPaymentVaultProcessor.this.mNonceCreatedListener);
                Card.tokenize(braintreeFragment, (CardBuilder) ((CardBuilder) ((CardBuilder) ((CardBuilder) new CardBuilder().cardNumber(creditCardHolder.getCardNumber())).expirationDate(CreditCardUtil.getFormattedExpiryDate(creditCardHolder.getExpiryMonth(), creditCardHolder.getExpiryYear()))).cvv(creditCardHolder.getSecurityCode())).postalCode(parseBillingAddress.getZipCode()));
            }

            public void onBraintreeFragmentLoadFailed(String str) {
                HashMap hashMap = new HashMap();
                if (str != null) {
                    hashMap.put("error_message", str);
                }
                CommerceLogger.logError(Action.NATIVE_SAVE_TABBED_BILLING_INFO, Result.BRAINTREE_SDK_ERROR, hashMap);
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_BILLING_NEXT_CC_FAILURE, hashMap);
                BraintreeCreditCardPaymentVaultProcessor.this.mServiceFragment.hideLoadingSpinner();
                saveListener2.onSaveFailed(this, null);
            }
        };
        cartPaymentVaultProcessorServiceFragment.withBraintreeFragment(r1);
    }
}
