package com.contextlogic.wish.payments.vault;

import android.os.Bundle;
import com.contextlogic.wish.analytics.CommerceLogger;
import com.contextlogic.wish.analytics.CommerceLogger.Action;
import com.contextlogic.wish.analytics.CommerceLogger.Result;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishUserBillingInfo;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.standalone.UpdateOxxoBillingInfoService;
import com.contextlogic.wish.api.service.standalone.UpdateOxxoBillingInfoService.SuccessCallback;
import com.contextlogic.wish.payments.vault.CartPaymentVaultProcessor.PrepareListener;
import com.contextlogic.wish.payments.vault.CartPaymentVaultProcessor.SaveListener;
import java.util.HashMap;

public class OxxoPaymentVaultProcessor extends CartPaymentVaultProcessor {
    private UpdateOxxoBillingInfoService mUpdateOxxoBillingInfoService = new UpdateOxxoBillingInfoService();

    public OxxoPaymentVaultProcessor(CartPaymentVaultProcessorServiceFragment cartPaymentVaultProcessorServiceFragment) {
        super(cartPaymentVaultProcessorServiceFragment);
    }

    public void prepareTab(PrepareListener prepareListener) {
        prepareListener.onTabPrepared(this);
    }

    public void save(final SaveListener saveListener, Bundle bundle) {
        this.mServiceFragment.showLoadingSpinner();
        final HashMap hashMap = new HashMap();
        hashMap.put("cart_type", this.mServiceFragment.getCartContext().getCartType().toString());
        this.mUpdateOxxoBillingInfoService.cancelAllRequests();
        this.mUpdateOxxoBillingInfoService.requestService(bundle.getString("ParamName"), bundle.getString("ParamEmail"), new SuccessCallback() {
            public void onSuccess(WishUserBillingInfo wishUserBillingInfo) {
                OxxoPaymentVaultProcessor.this.mServiceFragment.hideLoadingSpinner();
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_BILLING_NEXT_OXXO_SUCCESS, hashMap);
                OxxoPaymentVaultProcessor.this.mServiceFragment.getCartContext().updatePreferredPaymentMode("PaymentModeOxxo");
                OxxoPaymentVaultProcessor.this.mServiceFragment.getCartContext().updateData(OxxoPaymentVaultProcessor.this.mServiceFragment.getCartContext().getCart(), OxxoPaymentVaultProcessor.this.mServiceFragment.getCartContext().getShippingInfo(), wishUserBillingInfo);
                saveListener.onSaveComplete(this);
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                OxxoPaymentVaultProcessor.this.mServiceFragment.hideLoadingSpinner();
                HashMap hashMap = new HashMap();
                if (str != null) {
                    hashMap.put("error_message", str);
                }
                CommerceLogger.logError(Action.UPDATE_OXXO_BILLING_INFO, Result.API_ERROR, hashMap);
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_BILLING_NEXT_OXXO_FAILURE, hashMap);
                OxxoPaymentVaultProcessor.this.mServiceFragment.hideLoadingSpinner();
                saveListener.onSaveFailed(this, str);
            }
        });
    }
}
