package com.contextlogic.wish.payments.vault;

import android.os.Bundle;
import com.contextlogic.wish.analytics.CommerceLogger;
import com.contextlogic.wish.analytics.CommerceLogger.Action;
import com.contextlogic.wish.analytics.CommerceLogger.Result;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishCart;
import com.contextlogic.wish.api.model.WishShippingInfo;
import com.contextlogic.wish.api.model.WishUserBillingInfo;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.ExternalJsonApiService;
import com.contextlogic.wish.api.service.standalone.EbanxTokenService;
import com.contextlogic.wish.api.service.standalone.EbanxTokenService.SuccessCallback;
import com.contextlogic.wish.api.service.standalone.UpdateEbanxBillingInfoService;
import com.contextlogic.wish.payments.vault.CartPaymentVaultProcessor.PrepareListener;
import com.contextlogic.wish.payments.vault.CartPaymentVaultProcessor.SaveListener;
import com.contextlogic.wish.util.CreditCardUtil;
import com.contextlogic.wish.util.CreditCardUtil.CardType;
import com.contextlogic.wish.util.CreditCardUtil.CreditCardHolder;
import java.util.HashMap;

public class EbanxCreditCardPaymentVaultProcessor extends CartPaymentVaultProcessor {
    private EbanxTokenService mEbanxTokenService = new EbanxTokenService();
    /* access modifiers changed from: private */
    public UpdateEbanxBillingInfoService mUpdateEbanxBillingInfoService = new UpdateEbanxBillingInfoService();

    public EbanxCreditCardPaymentVaultProcessor(CartPaymentVaultProcessorServiceFragment cartPaymentVaultProcessorServiceFragment) {
        super(cartPaymentVaultProcessorServiceFragment);
    }

    public void prepareTab(PrepareListener prepareListener) {
        prepareListener.onTabPrepared(this);
    }

    public void save(SaveListener saveListener, Bundle bundle) {
        final Bundle bundle2 = bundle;
        this.mServiceFragment.showLoadingSpinner();
        final HashMap hashMap = new HashMap();
        hashMap.put("cart_type", this.mServiceFragment.getCartContext().getCartType().toString());
        CreditCardHolder creditCardHolder = new CreditCardHolder(bundle2.getString("ParamCreditCardId"), bundle2.getString("ParamCreditCardNumber"), bundle2.getString("ParamCreditCardExpiry"), bundle2.getString("ParamCreditCardCvv"));
        WishShippingInfo parseBillingAddress = parseBillingAddress(bundle2);
        EbanxTokenService ebanxTokenService = this.mEbanxTokenService;
        String cardId = creditCardHolder.getCardId();
        String cardNumber = creditCardHolder.getCardNumber();
        int expiryMonth = creditCardHolder.getExpiryMonth();
        int expiryYear = creditCardHolder.getExpiryYear();
        String securityCode = creditCardHolder.getSecurityCode();
        CardType cardTypeFromNumber = CreditCardUtil.getCardTypeFromNumber(creditCardHolder.getCardNumber());
        final WishShippingInfo wishShippingInfo = parseBillingAddress;
        final HashMap hashMap2 = hashMap;
        final SaveListener saveListener2 = saveListener;
        AnonymousClass1 r0 = new SuccessCallback() {
            public void onSuccess(String str, String str2, String str3) {
                EbanxCreditCardPaymentVaultProcessor.this.mUpdateEbanxBillingInfoService.requestService(str, str2, str3, bundle2.getString("ParamIdentityNumber"), bundle2.getString("ParamEmail"), wishShippingInfo, new UpdateEbanxBillingInfoService.SuccessCallback() {
                    public void onSuccess(WishUserBillingInfo wishUserBillingInfo, WishCart wishCart) {
                        EbanxCreditCardPaymentVaultProcessor.this.mServiceFragment.hideLoadingSpinner();
                        CommerceLogger.logSuccess(Action.NATIVE_SAVE_TABBED_BILLING_INFO, Result.SUCCESS, null);
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_BILLING_NEXT_CC_SUCCESS, hashMap2);
                        EbanxCreditCardPaymentVaultProcessor.this.mServiceFragment.getCartContext().updatePreferredPaymentMode("PaymentModeCC");
                        if (wishCart == null) {
                            wishCart = EbanxCreditCardPaymentVaultProcessor.this.mServiceFragment.getCartContext().getCart();
                        }
                        EbanxCreditCardPaymentVaultProcessor.this.mServiceFragment.getCartContext().updateData(wishCart, EbanxCreditCardPaymentVaultProcessor.this.mServiceFragment.getCartContext().getShippingInfo(), wishUserBillingInfo);
                        saveListener2.onSaveComplete(this);
                    }
                }, new DefaultFailureCallback() {
                    public void onFailure(String str) {
                        HashMap hashMap = new HashMap();
                        if (str != null) {
                            hashMap.put("error_message", str);
                        }
                        CommerceLogger.logError(Action.NATIVE_SAVE_TABBED_BILLING_INFO, Result.API_ERROR, hashMap);
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_BILLING_NEXT_CC_FAILURE, hashMap2);
                        EbanxCreditCardPaymentVaultProcessor.this.mServiceFragment.hideLoadingSpinner();
                        saveListener2.onSaveFailed(this, str);
                    }
                });
            }
        };
        final SaveListener saveListener3 = saveListener;
        ebanxTokenService.requestService(cardId, cardNumber, expiryMonth, expiryYear, securityCode, cardTypeFromNumber, parseBillingAddress, r0, new ExternalJsonApiService.DefaultFailureCallback() {
            public void onFailure(String str) {
                HashMap hashMap = new HashMap();
                hashMap.put("error_message", str);
                CommerceLogger.logError(Action.NATIVE_SAVE_TABBED_BILLING_INFO, Result.EBANX_SDK_ERROR, hashMap);
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_BILLING_NEXT_CC_FAILURE, hashMap);
                EbanxCreditCardPaymentVaultProcessor.this.mServiceFragment.hideLoadingSpinner();
                saveListener3.onSaveFailed(this, str);
            }
        });
    }
}
