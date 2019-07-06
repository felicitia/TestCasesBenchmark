package com.contextlogic.wish.activity.cart.shipping;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.cart.shipping.ShippingAddressFormView.EntryCompletedCallback;
import com.contextlogic.wish.analytics.CommerceLogger;
import com.contextlogic.wish.analytics.CommerceLogger.Action;
import com.contextlogic.wish.analytics.CommerceLogger.Result;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ProfileDataCenter;
import com.contextlogic.wish.api.model.WishShippingInfo;
import com.contextlogic.wish.util.KeyboardUtil;
import com.contextlogic.wish.util.StringUtil;
import java.util.ArrayList;
import java.util.HashMap;

public class StandaloneShippingInfoFragment extends UiFragment<StandaloneShippingInfoActivity> {
    /* access modifiers changed from: private */
    public ShippingAddressFormView mForm;

    /* access modifiers changed from: protected */
    public int getLayoutResourceId() {
        return R.layout.standalone_shipping_info_fragment;
    }

    public void releaseImages() {
    }

    public void restoreImages() {
    }

    /* access modifiers changed from: protected */
    public void initialize() {
        this.mForm = (ShippingAddressFormView) findViewById(R.id.standalone_shipping_info_fragment_form);
        this.mForm.prefillNameFromProfile();
        if (ProfileDataCenter.getInstance().getCountryCode() != null) {
            this.mForm.populateCountry(ProfileDataCenter.getInstance().getCountryCode());
        }
        this.mForm.setEntryCompletedCallback(new EntryCompletedCallback() {
            public void onEntryCompletion() {
                StandaloneShippingInfoFragment.this.handleDone();
            }
        });
        findViewById(R.id.standalone_shipping_info_fragment_done_button).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                StandaloneShippingInfoFragment.this.handleDone();
            }
        });
        withActivity(new ActivityTask<StandaloneShippingInfoActivity>() {
            public void performTask(StandaloneShippingInfoActivity standaloneShippingInfoActivity) {
                WishShippingInfo editAddressShippingInfo = standaloneShippingInfoActivity.getEditAddressShippingInfo();
                if (editAddressShippingInfo != null) {
                    StandaloneShippingInfoFragment.this.mForm.prefillAddress(editAddressShippingInfo);
                }
            }
        });
    }

    public boolean onBackPressed() {
        withServiceFragment(new ServiceTask<BaseActivity, StandaloneShippingInfoServiceFragment>() {
            public void performTask(BaseActivity baseActivity, StandaloneShippingInfoServiceFragment standaloneShippingInfoServiceFragment) {
                standaloneShippingInfoServiceFragment.showConfirmCloseDialog();
            }
        });
        return true;
    }

    /* access modifiers changed from: private */
    public void handleDone() {
        ArrayList missingFields = this.mForm.getMissingFields();
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_SHIPPING_NEXT);
        if (!missingFields.isEmpty()) {
            HashMap hashMap = new HashMap();
            hashMap.put("affected_fields", StringUtil.join(missingFields, ","));
            CommerceLogger.logError(Action.NATIVE_SAVE_SHIPPING_INFO, Result.MISSING_FIELDS, hashMap);
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_SHIPPING_NEXT_FAILURE);
            withServiceFragment(new ServiceTask<BaseActivity, ServiceFragment>() {
                public void performTask(BaseActivity baseActivity, ServiceFragment serviceFragment) {
                    serviceFragment.showErrorMessage(baseActivity.getString(R.string.please_provide_information_in_all_required_fields));
                }
            });
            return;
        }
        withServiceFragment(new ServiceTask<StandaloneShippingInfoActivity, StandaloneShippingInfoServiceFragment>() {
            public void performTask(StandaloneShippingInfoActivity standaloneShippingInfoActivity, StandaloneShippingInfoServiceFragment standaloneShippingInfoServiceFragment) {
                KeyboardUtil.hideKeyboard((Activity) standaloneShippingInfoActivity);
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_SAVE_EDITED_ADDRESS);
                WishShippingInfo editAddressShippingInfo = standaloneShippingInfoActivity.getEditAddressShippingInfo();
                WishShippingInfo enteredShippingAddress = StandaloneShippingInfoFragment.this.mForm.getEnteredShippingAddress();
                if (editAddressShippingInfo != null) {
                    enteredShippingAddress.setId(editAddressShippingInfo.getId());
                }
                standaloneShippingInfoServiceFragment.setShippingAddress(enteredShippingAddress, editAddressShippingInfo == null);
            }
        });
    }

    public void onShippingInfoUpdateSuccess() {
        withActivity(new ActivityTask<StandaloneShippingInfoActivity>() {
            public void performTask(StandaloneShippingInfoActivity standaloneShippingInfoActivity) {
                Intent intent = new Intent();
                intent.putExtra("ExtraRequiresReload", true);
                standaloneShippingInfoActivity.setResult(-1, intent);
                standaloneShippingInfoActivity.finishActivity();
            }
        });
    }
}
