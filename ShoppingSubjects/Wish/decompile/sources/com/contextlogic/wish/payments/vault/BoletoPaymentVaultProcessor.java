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
import com.contextlogic.wish.api.service.standalone.UpdateBoletoBillingInfoService;
import com.contextlogic.wish.api.service.standalone.UpdateBoletoBillingInfoService.SuccessCallback;
import com.contextlogic.wish.payments.vault.CartPaymentVaultProcessor.PrepareListener;
import com.contextlogic.wish.payments.vault.CartPaymentVaultProcessor.SaveListener;
import java.util.HashMap;

public class BoletoPaymentVaultProcessor extends CartPaymentVaultProcessor {
    private UpdateBoletoBillingInfoService mUpdateBoletoBillingInfoService = new UpdateBoletoBillingInfoService();

    public BoletoPaymentVaultProcessor(CartPaymentVaultProcessorServiceFragment cartPaymentVaultProcessorServiceFragment) {
        super(cartPaymentVaultProcessorServiceFragment);
    }

    public void prepareTab(PrepareListener prepareListener) {
        prepareListener.onTabPrepared(this);
    }

    public void save(final SaveListener saveListener, Bundle bundle) {
        this.mServiceFragment.showLoadingSpinner();
        final HashMap hashMap = new HashMap();
        hashMap.put("cart_type", this.mServiceFragment.getCartContext().getCartType().toString());
        WishShippingInfo parseBillingAddress = parseBillingAddress(bundle);
        if (parseBillingAddress.getStreetAddressLineOne() == null) {
            parseBillingAddress = null;
        }
        WishShippingInfo wishShippingInfo = parseBillingAddress;
        UpdateBoletoBillingInfoService updateBoletoBillingInfoService = this.mUpdateBoletoBillingInfoService;
        String string = bundle.getString("ParamName");
        String string2 = bundle.getString("ParamIdentityNumber");
        String string3 = bundle.getString("ParamEmail");
        final HashMap hashMap2 = hashMap;
        final WishShippingInfo wishShippingInfo2 = wishShippingInfo;
        final SaveListener saveListener2 = saveListener;
        AnonymousClass1 r1 = new SuccessCallback() {
            public void onSuccess(WishUserBillingInfo wishUserBillingInfo) {
                BoletoPaymentVaultProcessor.this.mServiceFragment.hideLoadingSpinner();
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_BILLING_NEXT_BOLETO_SUCCESS, hashMap2);
                BoletoPaymentVaultProcessor.this.mServiceFragment.getCartContext().updatePreferredPaymentMode("PaymentModeBoleto");
                BoletoPaymentVaultProcessor.this.mServiceFragment.getCartContext().updateData(BoletoPaymentVaultProcessor.this.mServiceFragment.getCartContext().getCart(), wishShippingInfo2 == null ? BoletoPaymentVaultProcessor.this.mServiceFragment.getCartContext().getShippingInfo() : wishShippingInfo2, wishUserBillingInfo);
                saveListener2.onSaveComplete(this);
            }
        };
        updateBoletoBillingInfoService.requestService(string, string2, string3, wishShippingInfo, r1, new DefaultFailureCallback() {
            public void onFailure(String str) {
                BoletoPaymentVaultProcessor.this.mServiceFragment.hideLoadingSpinner();
                HashMap hashMap = new HashMap();
                if (str != null) {
                    hashMap.put("error_message", str);
                }
                CommerceLogger.logError(Action.UPDATE_BOLETO_BILLING_INFO, Result.API_ERROR, hashMap);
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_BILLING_NEXT_BOLETO_FAILURE, hashMap);
                BoletoPaymentVaultProcessor.this.mServiceFragment.hideLoadingSpinner();
                saveListener.onSaveFailed(this, str);
            }
        });
    }
}
