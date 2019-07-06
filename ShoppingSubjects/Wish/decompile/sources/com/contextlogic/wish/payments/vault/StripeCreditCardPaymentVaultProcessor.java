package com.contextlogic.wish.payments.vault;

import android.os.Bundle;
import com.contextlogic.wish.analytics.CommerceLogger;
import com.contextlogic.wish.analytics.CommerceLogger.Action;
import com.contextlogic.wish.analytics.CommerceLogger.Result;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ConfigDataCenter;
import com.contextlogic.wish.api.model.WishCachedBillingInfo;
import com.contextlogic.wish.api.model.WishShippingInfo;
import com.contextlogic.wish.api.model.WishUserBillingInfo;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.standalone.UpdateStripeBillingInfoService;
import com.contextlogic.wish.api.service.standalone.UpdateStripeBillingInfoService.SuccessCallback;
import com.contextlogic.wish.payments.stripe.StripeManager;
import com.contextlogic.wish.payments.vault.CartPaymentVaultProcessor.PrepareListener;
import com.contextlogic.wish.payments.vault.CartPaymentVaultProcessor.SaveListener;
import com.contextlogic.wish.util.CreditCardUtil.CreditCardHolder;
import com.stripe.android.Stripe;
import com.stripe.android.TokenCallback;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;
import java.util.HashMap;

public class StripeCreditCardPaymentVaultProcessor extends CartPaymentVaultProcessor {
    /* access modifiers changed from: private */
    public UpdateStripeBillingInfoService mUpdateStripeBillingInfoService = new UpdateStripeBillingInfoService();

    public StripeCreditCardPaymentVaultProcessor(CartPaymentVaultProcessorServiceFragment cartPaymentVaultProcessorServiceFragment) {
        super(cartPaymentVaultProcessorServiceFragment);
    }

    public void prepareTab(PrepareListener prepareListener) {
        prepareListener.onTabPrepared(this);
    }

    public void save(SaveListener saveListener, Bundle bundle) {
        this.mServiceFragment.showLoadingSpinner();
        final CreditCardHolder creditCardHolder = new CreditCardHolder(bundle.getString("ParamCreditCardId"), bundle.getString("ParamCreditCardNumber"), bundle.getString("ParamCreditCardExpiry"), bundle.getString("ParamCreditCardCvv"));
        final WishShippingInfo parseBillingAddress = parseBillingAddress(bundle);
        final boolean z = bundle.getBoolean("paramIsForCommerceLoan", false);
        HashMap hashMap = new HashMap();
        hashMap.put("cart_type", this.mServiceFragment.getCartContext().getCartType().toString());
        try {
            Card card = new Card(creditCardHolder.getCardNumber(), Integer.valueOf(creditCardHolder.getExpiryMonth()), Integer.valueOf(creditCardHolder.getExpiryYear()), creditCardHolder.getSecurityCode());
            if (parseBillingAddress.getName() != null) {
                card.setName(parseBillingAddress.getName());
            }
            if (parseBillingAddress.getStreetAddressLineOne() != null) {
                card.setAddressLine1(parseBillingAddress.getStreetAddressLineOne());
            }
            if (parseBillingAddress.getStreetAddressLineTwo() != null) {
                card.setAddressLine2(parseBillingAddress.getStreetAddressLineTwo());
            }
            if (parseBillingAddress.getCity() != null) {
                card.setAddressCity(parseBillingAddress.getCity());
            }
            if (parseBillingAddress.getState() != null) {
                card.setAddressState(parseBillingAddress.getState());
            }
            if (parseBillingAddress.getZipCode() != null) {
                card.setAddressZip(parseBillingAddress.getZipCode());
            }
            if (parseBillingAddress.getCountryCode() != null) {
                card.setAddressCountry(parseBillingAddress.getCountryCode());
            }
            Stripe stripe = new Stripe(ConfigDataCenter.getInstance().getPaymentProcessorData().getStripeKey());
            final HashMap hashMap2 = hashMap;
            final SaveListener saveListener2 = saveListener;
            final Stripe stripe2 = stripe;
            final Card card2 = card;
            AnonymousClass1 r1 = new TokenCallback() {
                public void onError(Exception exc) {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_BILLING_NEXT_CC_FAILURE, hashMap2);
                    StripeCreditCardPaymentVaultProcessor.this.mServiceFragment.hideLoadingSpinner();
                    saveListener2.onSaveFailed(this, null);
                }

                public void onSuccess(Token token) {
                    StripeCreditCardPaymentVaultProcessor.this.mUpdateStripeBillingInfoService.requestService(creditCardHolder.getCardId(), token.getId(), parseBillingAddress, z, new SuccessCallback() {
                        public void onSuccess(final WishUserBillingInfo wishUserBillingInfo) {
                            CommerceLogger.logSuccess(Action.NATIVE_SAVE_TABBED_BILLING_INFO, Result.SUCCESS, null);
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_BILLING_NEXT_CC_SUCCESS, hashMap2);
                            stripe2.createToken(card2, new TokenCallback() {
                                public void onError(Exception exc) {
                                    StripeCreditCardPaymentVaultProcessor.this.mServiceFragment.hideLoadingSpinner();
                                    StripeCreditCardPaymentVaultProcessor.this.mServiceFragment.getCartContext().updatePreferredPaymentMode("PaymentModeCC");
                                    StripeCreditCardPaymentVaultProcessor.this.mServiceFragment.getCartContext().updateData(StripeCreditCardPaymentVaultProcessor.this.mServiceFragment.getCartContext().getCart(), StripeCreditCardPaymentVaultProcessor.this.mServiceFragment.getCartContext().getShippingInfo(), wishUserBillingInfo);
                                    saveListener2.onSaveComplete(this);
                                }

                                public void onSuccess(Token token) {
                                    StripeManager.getInstance().setCachedBillingInfo(new WishCachedBillingInfo(token.getId(), creditCardHolder, parseBillingAddress));
                                    StripeCreditCardPaymentVaultProcessor.this.mServiceFragment.hideLoadingSpinner();
                                    StripeCreditCardPaymentVaultProcessor.this.mServiceFragment.getCartContext().updatePreferredPaymentMode("PaymentModeCC");
                                    StripeCreditCardPaymentVaultProcessor.this.mServiceFragment.getCartContext().updateData(StripeCreditCardPaymentVaultProcessor.this.mServiceFragment.getCartContext().getCart(), StripeCreditCardPaymentVaultProcessor.this.mServiceFragment.getCartContext().getShippingInfo(), wishUserBillingInfo);
                                    saveListener2.onSaveComplete(this);
                                }
                            });
                        }
                    }, new DefaultFailureCallback() {
                        public void onFailure(String str) {
                            HashMap hashMap = new HashMap();
                            if (str != null) {
                                hashMap.put("error_message", str);
                            }
                            CommerceLogger.logError(Action.NATIVE_SAVE_TABBED_BILLING_INFO, Result.API_ERROR, hashMap);
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_BILLING_NEXT_CC_FAILURE, hashMap2);
                            StripeCreditCardPaymentVaultProcessor.this.mServiceFragment.hideLoadingSpinner();
                            saveListener2.onSaveFailed(this, str);
                        }
                    });
                }
            };
            stripe.createToken(card, r1);
        } catch (Throwable th) {
            HashMap hashMap3 = new HashMap();
            if (th.getMessage() != null) {
                hashMap3.put("error_message", th.getMessage());
            }
            CommerceLogger.logError(Action.NATIVE_SAVE_TABBED_BILLING_INFO, Result.STRIPE_SDK_ERROR, hashMap3);
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_BILLING_NEXT_CC_FAILURE, hashMap);
            this.mServiceFragment.hideLoadingSpinner();
            saveListener.onSaveFailed(this, null);
        }
    }
}
