package com.contextlogic.wish.payments.vault;

import android.os.Bundle;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishUserBillingInfo;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.standalone.UpdateCommerceLoanBillingInfoService;
import com.contextlogic.wish.api.service.standalone.UpdateCommerceLoanBillingInfoService.SuccessCallback;
import com.contextlogic.wish.payments.vault.CartPaymentVaultProcessor.PrepareListener;
import com.contextlogic.wish.payments.vault.CartPaymentVaultProcessor.SaveListener;
import java.util.HashMap;

public class CommerceLoanPaymentVaultProcessor extends CartPaymentVaultProcessor {
    private UpdateCommerceLoanBillingInfoService mUpdateCommerceLoanBillingInfoService = new UpdateCommerceLoanBillingInfoService();

    public CommerceLoanPaymentVaultProcessor(CartPaymentVaultProcessorServiceFragment cartPaymentVaultProcessorServiceFragment) {
        super(cartPaymentVaultProcessorServiceFragment);
    }

    public void prepareTab(PrepareListener prepareListener) {
        prepareListener.onTabPrepared(this);
    }

    public void save(final SaveListener saveListener, Bundle bundle) {
        this.mServiceFragment.showLoadingSpinner();
        final HashMap hashMap = new HashMap();
        hashMap.put("cart_type", this.mServiceFragment.getCartContext().getCartType().toString());
        this.mUpdateCommerceLoanBillingInfoService.cancelAllRequests();
        this.mUpdateCommerceLoanBillingInfoService.requestService(bundle.getString("paramDueDate"), bundle.getInt("paramPaymentProcessor"), new SuccessCallback() {
            public void onSuccess(WishUserBillingInfo wishUserBillingInfo) {
                CommerceLoanPaymentVaultProcessor.this.mServiceFragment.hideLoadingSpinner();
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_BILLING_NEXT_COMMERCE_LOAN_SUCCESS, hashMap);
                if (ExperimentDataCenter.getInstance().canSeeCommerceLoanBillingOption()) {
                    CommerceLoanPaymentVaultProcessor.this.mServiceFragment.getCartContext().updatePreferredPaymentMode("PaymentModeCommerceLoan");
                }
                CommerceLoanPaymentVaultProcessor.this.mServiceFragment.getCartContext().updateData(CommerceLoanPaymentVaultProcessor.this.mServiceFragment.getCartContext().getCart(), CommerceLoanPaymentVaultProcessor.this.mServiceFragment.getCartContext().getShippingInfo(), wishUserBillingInfo);
                saveListener.onSaveComplete(this);
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                CommerceLoanPaymentVaultProcessor.this.mServiceFragment.hideLoadingSpinner();
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_BILLING_NEXT_COMMERCE_LOAN_FAILURE, hashMap);
                CommerceLoanPaymentVaultProcessor.this.mServiceFragment.hideLoadingSpinner();
                saveListener.onSaveFailed(this, str);
            }
        });
    }
}
