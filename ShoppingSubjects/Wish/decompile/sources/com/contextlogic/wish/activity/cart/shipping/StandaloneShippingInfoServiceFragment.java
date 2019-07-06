package com.contextlogic.wish.activity.cart.shipping;

import android.os.Bundle;
import android.text.TextUtils;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.UiTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishCart;
import com.contextlogic.wish.api.model.WishShippingInfo;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.standalone.UpdateShippingInfoService;
import com.contextlogic.wish.api.service.standalone.UpdateShippingInfoService.SuccessCallback;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.dialog.BaseDialogFragment.BaseDialogCallback;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;

public class StandaloneShippingInfoServiceFragment extends ServiceFragment<StandaloneShippingInfoActivity> {
    private UpdateShippingInfoService mUpdateShippingInfoService;

    /* access modifiers changed from: protected */
    public void initializeServices() {
        super.initializeServices();
        this.mUpdateShippingInfoService = new UpdateShippingInfoService();
    }

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
        super.cancelAllRequests();
        this.mUpdateShippingInfoService.cancelAllRequests();
    }

    public void setShippingAddress(WishShippingInfo wishShippingInfo, boolean z) {
        showLoadingSpinner();
        this.mUpdateShippingInfoService.requestService(wishShippingInfo.getName(), wishShippingInfo.getStreetAddressLineOne(), wishShippingInfo.getStreetAddressLineTwo(), wishShippingInfo.getCity(), wishShippingInfo.getState(), wishShippingInfo.getZipCode(), wishShippingInfo.getCountryCode(), wishShippingInfo.getPhoneNumber(), z, wishShippingInfo.getId(), new SuccessCallback() {
            public void onSuccess(WishShippingInfo wishShippingInfo, WishCart wishCart) {
                StandaloneShippingInfoServiceFragment.this.hideLoadingSpinner();
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_SHIPPING_NEXT_SUCCESS);
                StandaloneShippingInfoServiceFragment.this.withUiFragment(new UiTask<BaseActivity, StandaloneShippingInfoFragment>() {
                    public void performTask(BaseActivity baseActivity, StandaloneShippingInfoFragment standaloneShippingInfoFragment) {
                        standaloneShippingInfoFragment.onShippingInfoUpdateSuccess();
                    }
                });
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                StandaloneShippingInfoServiceFragment.this.hideLoadingSpinner();
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_SHIPPING_NEXT_FAILURE);
                StandaloneShippingInfoServiceFragment.this.showErrorMessage(str);
            }
        });
    }

    public void showConfirmCloseDialog() {
        withActivity(new ActivityTask<StandaloneShippingInfoActivity>() {
            public void performTask(final StandaloneShippingInfoActivity standaloneShippingInfoActivity) {
                String string = standaloneShippingInfoActivity.getString(R.string.leave_form_question);
                String cancelWarning = standaloneShippingInfoActivity.getCancelWarning();
                if (TextUtils.isEmpty(cancelWarning)) {
                    cancelWarning = standaloneShippingInfoActivity.getString(R.string.leave_form_description);
                }
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_MOBILE_LEAVE_FORM_DIALOG);
                standaloneShippingInfoActivity.startDialog(MultiButtonDialogFragment.createCustomMultiButtonYesNoDialog(string, cancelWarning, standaloneShippingInfoActivity.getString(R.string.continue_editing), standaloneShippingInfoActivity.getString(R.string.leave_form)), new BaseDialogCallback() {
                    public void onCancel(BaseDialogFragment baseDialogFragment) {
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_DISMISS_LEAVE_FORM_DIALOG);
                    }

                    public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                        if (i == 1) {
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_CONTINUE_EDITING);
                            return;
                        }
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_LEAVE_FORM);
                        standaloneShippingInfoActivity.setResult(0);
                        standaloneShippingInfoActivity.finish();
                    }
                });
            }
        });
    }
}
