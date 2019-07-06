package com.contextlogic.wish.activity.cart.shipping;

import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.UiTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.model.WishCart;
import com.contextlogic.wish.api.model.WishShippingInfo;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.standalone.DeleteShippingAddressService;
import com.contextlogic.wish.api.service.standalone.GetUserShippingDetailsService;
import com.contextlogic.wish.api.service.standalone.GetUserShippingDetailsService.SuccessCallback;
import com.contextlogic.wish.api.service.standalone.UpdateShippingInfoService;
import java.util.ArrayList;
import java.util.List;

public class StandaloneManageAddressesServiceFragment extends ServiceFragment<StandaloneManageAddressesActivity> {
    private DeleteShippingAddressService mDeleteShippingAddressService;
    private GetUserShippingDetailsService mGetUserShippingDetailsService;
    private UpdateShippingInfoService mUpdateShippingInfoService;

    /* access modifiers changed from: protected */
    public void initializeServices() {
        super.initializeServices();
        this.mDeleteShippingAddressService = new DeleteShippingAddressService();
        this.mGetUserShippingDetailsService = new GetUserShippingDetailsService();
        this.mUpdateShippingInfoService = new UpdateShippingInfoService();
    }

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
        super.cancelAllRequests();
        this.mDeleteShippingAddressService.cancelAllRequests();
        this.mGetUserShippingDetailsService.cancelAllRequests();
        this.mUpdateShippingInfoService.cancelAllRequests();
    }

    public void loadUserShippingInfo() {
        showLoadingSpinner();
        this.mGetUserShippingDetailsService.requestService(new SuccessCallback() {
            public void onSuccess(final List<WishShippingInfo> list, final String str) {
                StandaloneManageAddressesServiceFragment.this.hideLoadingSpinner();
                StandaloneManageAddressesServiceFragment.this.withUiFragment(new UiTask<BaseActivity, StandaloneManageAddressesFragment>() {
                    public void performTask(BaseActivity baseActivity, StandaloneManageAddressesFragment standaloneManageAddressesFragment) {
                        standaloneManageAddressesFragment.onUserShippingInfoLoadSuccess(list, str);
                    }
                });
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                StandaloneManageAddressesServiceFragment.this.hideLoadingSpinner();
                if (str == null) {
                    str = StandaloneManageAddressesServiceFragment.this.getString(R.string.something_went_wrong);
                }
                StandaloneManageAddressesServiceFragment.this.showErrorMessage(str);
            }
        });
    }

    public void deleteShippingAddress(WishShippingInfo wishShippingInfo) {
        showLoadingSpinner();
        this.mDeleteShippingAddressService.requestService(wishShippingInfo, new DeleteShippingAddressService.SuccessCallback() {
            public void onSuccess(final ArrayList<WishShippingInfo> arrayList, final String str) {
                StandaloneManageAddressesServiceFragment.this.hideLoadingSpinner();
                StandaloneManageAddressesServiceFragment.this.withUiFragment(new UiTask<BaseActivity, StandaloneManageAddressesFragment>() {
                    public void performTask(BaseActivity baseActivity, StandaloneManageAddressesFragment standaloneManageAddressesFragment) {
                        standaloneManageAddressesFragment.onDeleteAddressSuccess(arrayList, str);
                    }
                });
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                StandaloneManageAddressesServiceFragment.this.hideLoadingSpinner();
                if (str == null) {
                    str = StandaloneManageAddressesServiceFragment.this.getString(R.string.something_went_wrong);
                }
                StandaloneManageAddressesServiceFragment.this.showErrorMessage(str);
            }
        });
    }

    public void setShippingAddress(WishShippingInfo wishShippingInfo) {
        showLoadingSpinner();
        this.mUpdateShippingInfoService.requestService(wishShippingInfo.getName(), wishShippingInfo.getStreetAddressLineOne(), wishShippingInfo.getStreetAddressLineTwo(), wishShippingInfo.getCity(), wishShippingInfo.getState(), wishShippingInfo.getZipCode(), wishShippingInfo.getCountryCode(), wishShippingInfo.getPhoneNumber(), false, wishShippingInfo.getId(), new UpdateShippingInfoService.SuccessCallback() {
            public void onSuccess(final WishShippingInfo wishShippingInfo, WishCart wishCart) {
                StandaloneManageAddressesServiceFragment.this.hideLoadingSpinner();
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_SHIPPING_NEXT_SUCCESS);
                StandaloneManageAddressesServiceFragment.this.withUiFragment(new UiTask<BaseActivity, StandaloneManageAddressesFragment>() {
                    public void performTask(BaseActivity baseActivity, StandaloneManageAddressesFragment standaloneManageAddressesFragment) {
                        standaloneManageAddressesFragment.onUserSetAddressSuccess(wishShippingInfo);
                    }
                });
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                StandaloneManageAddressesServiceFragment.this.hideLoadingSpinner();
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_NATIVE_SHIPPING_NEXT_FAILURE);
                StandaloneManageAddressesServiceFragment.this.showErrorMessage(str);
            }
        });
    }
}
