package com.contextlogic.wish.activity.wishpartner.info;

import android.os.Bundle;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.UiTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.api.model.WishPartnerInfoSpec;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.standalone.GetWishPartnerInfoService;
import com.contextlogic.wish.api.service.standalone.GetWishPartnerInfoService.SuccessCallback;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;

public class WishPartnerInfoServiceFragment extends ServiceFragment<WishPartnerInfoActivity> {
    private GetWishPartnerInfoService mGetWishPartnerInfoService;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    /* access modifiers changed from: protected */
    public void initializeServices() {
        super.initializeServices();
        this.mGetWishPartnerInfoService = new GetWishPartnerInfoService();
    }

    public void loadWishProductInfo(String str) {
        this.mGetWishPartnerInfoService.requestService(str, new SuccessCallback() {
            public void onSuccess(final WishPartnerInfoSpec wishPartnerInfoSpec) {
                WishPartnerInfoServiceFragment.this.withUiFragment(new UiTask<BaseActivity, WishPartnerInfoFragment>() {
                    public void performTask(BaseActivity baseActivity, WishPartnerInfoFragment wishPartnerInfoFragment) {
                        wishPartnerInfoFragment.handleLoadingInfoSuccess(wishPartnerInfoSpec);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(final String str) {
                if (str == null) {
                    str = WishPartnerInfoServiceFragment.this.getString(R.string.error_generating_code);
                }
                WishPartnerInfoServiceFragment.this.withUiFragment(new UiTask<BaseActivity, WishPartnerInfoFragment>() {
                    public void performTask(BaseActivity baseActivity, WishPartnerInfoFragment wishPartnerInfoFragment) {
                        baseActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(str));
                        wishPartnerInfoFragment.handleLoadingInfoFailure();
                    }
                }, "FragmentTagMainContent");
            }
        });
    }

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
        super.cancelAllRequests();
        this.mGetWishPartnerInfoService.cancelAllRequests();
    }
}
