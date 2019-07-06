package com.contextlogic.wish.payments.vault;

import android.os.Bundle;
import com.contextlogic.wish.analytics.CommerceLogger;
import com.contextlogic.wish.analytics.CommerceLogger.Action;
import com.contextlogic.wish.analytics.CommerceLogger.Result;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishShippingInfo;
import com.contextlogic.wish.api.model.WishUserBillingInfo;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.standalone.UpdateAdyenBillingInfoService;
import com.contextlogic.wish.api.service.standalone.UpdateAdyenBillingInfoService.SuccessCallback;
import com.contextlogic.wish.payments.adyen.CardPaymentData;
import com.contextlogic.wish.payments.vault.CartPaymentVaultProcessor.PrepareListener;
import com.contextlogic.wish.payments.vault.CartPaymentVaultProcessor.SaveListener;
import com.contextlogic.wish.util.CreditCardUtil;
import com.contextlogic.wish.util.CreditCardUtil.CreditCardHolder;
import java.util.Date;
import java.util.HashMap;

public class AdyenCreditCardPaymentVaultProcessor extends CartPaymentVaultProcessor {
    private UpdateAdyenBillingInfoService mUpdateAdyenBillingInfoService = new UpdateAdyenBillingInfoService();

    public AdyenCreditCardPaymentVaultProcessor(CartPaymentVaultProcessorServiceFragment cartPaymentVaultProcessorServiceFragment) {
        super(cartPaymentVaultProcessorServiceFragment);
    }

    public void prepareTab(PrepareListener prepareListener) {
        prepareListener.onTabPrepared(this);
    }

    public void save(SaveListener saveListener, Bundle bundle) {
        final SaveListener saveListener2 = saveListener;
        Bundle bundle2 = bundle;
        this.mServiceFragment.showLoadingSpinner();
        final HashMap hashMap = new HashMap();
        hashMap.put("cart_type", this.mServiceFragment.getCartContext().getCartType().toString());
        CreditCardHolder creditCardHolder = new CreditCardHolder(bundle2.getString("ParamCreditCardId"), bundle2.getString("ParamCreditCardNumber"), bundle2.getString("ParamCreditCardExpiry"), bundle2.getString("ParamCreditCardCvv"));
        String cardNumber = creditCardHolder.getCardNumber();
        CardPaymentData cardPaymentData = new CardPaymentData();
        cardPaymentData.setNumber(cardNumber);
        cardPaymentData.setCvc(creditCardHolder.getSecurityCode());
        cardPaymentData.setExpiryMonth(Integer.toString(creditCardHolder.getExpiryMonth()));
        cardPaymentData.setExpiryYear(Integer.toString(creditCardHolder.getExpiryYear()));
        cardPaymentData.setGenerationTime(new Date());
        cardPaymentData.setCardHolderName(bundle2.getString("ParamName"));
        String sanitizeCreditCardNumber = CreditCardUtil.sanitizeCreditCardNumber(cardNumber);
        String substring = sanitizeCreditCardNumber.substring(0, 6);
        String substring2 = sanitizeCreditCardNumber.substring(sanitizeCreditCardNumber.length() - 4);
        String creditCardTypeString = CreditCardUtil.getCreditCardTypeString(CreditCardUtil.getCardTypeFromNumber(cardNumber));
        WishShippingInfo parseBillingAddress = parseBillingAddress(bundle2);
        try {
            String serialize = cardPaymentData.serialize();
            this.mUpdateAdyenBillingInfoService.requestService(creditCardHolder.getCardId(), serialize, substring, substring2, creditCardTypeString, parseBillingAddress, new SuccessCallback() {
                public void onSuccess(WishUserBillingInfo wishUserBillingInfo) {
                    CommerceLogger.logSuccess(Action.NATIVE_SAVE_TABBED_BILLING_INFO, Result.SUCCESS, null);
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_BILLING_NEXT_CC_SUCCESS, hashMap);
                    AdyenCreditCardPaymentVaultProcessor.this.mServiceFragment.hideLoadingSpinner();
                    AdyenCreditCardPaymentVaultProcessor.this.mServiceFragment.getCartContext().updatePreferredPaymentMode("PaymentModeCC");
                    AdyenCreditCardPaymentVaultProcessor.this.mServiceFragment.getCartContext().updateData(AdyenCreditCardPaymentVaultProcessor.this.mServiceFragment.getCartContext().getCart(), AdyenCreditCardPaymentVaultProcessor.this.mServiceFragment.getCartContext().getShippingInfo(), wishUserBillingInfo);
                    saveListener2.onSaveComplete(this);
                }
            }, new DefaultFailureCallback() {
                public void onFailure(String str) {
                    HashMap hashMap = new HashMap();
                    if (str != null) {
                        hashMap.put("error_message", str);
                    }
                    CommerceLogger.logError(Action.NATIVE_SAVE_TABBED_BILLING_INFO, Result.API_ERROR, hashMap);
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_BILLING_NEXT_CC_FAILURE, hashMap);
                    AdyenCreditCardPaymentVaultProcessor.this.mServiceFragment.hideLoadingSpinner();
                    saveListener2.onSaveFailed(this, str);
                }
            });
        } catch (Throwable th) {
            Throwable th2 = th;
            HashMap hashMap2 = new HashMap();
            if (th2.toString() != null) {
                hashMap2.put("error_message", th2.toString());
            }
            CommerceLogger.logError(Action.NATIVE_SAVE_TABBED_BILLING_INFO, Result.ADYEN_SDK_ERROR, hashMap2);
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_BILLING_NEXT_CC_FAILURE, hashMap);
            this.mServiceFragment.hideLoadingSpinner();
            saveListener2.onSaveFailed(this, null);
        }
    }
}
