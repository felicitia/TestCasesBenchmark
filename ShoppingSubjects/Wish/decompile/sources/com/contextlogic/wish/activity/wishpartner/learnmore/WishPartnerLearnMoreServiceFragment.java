package com.contextlogic.wish.activity.wishpartner.learnmore;

import android.os.Bundle;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.UiTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.api.model.WishPartnerLearnMoreSpec;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.standalone.GetWishPartnerLearnMoreService;
import com.contextlogic.wish.api.service.standalone.GetWishPartnerLearnMoreService.SuccessCallback;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;

public class WishPartnerLearnMoreServiceFragment extends ServiceFragment<WishPartnerLearnMoreActivity> {
    private GetWishPartnerLearnMoreService mGetWishPartnerLearnMoreService;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    /* access modifiers changed from: protected */
    public void initializeServices() {
        super.initializeServices();
        this.mGetWishPartnerLearnMoreService = new GetWishPartnerLearnMoreService();
    }

    public void loadLearnMoreInfo() {
        this.mGetWishPartnerLearnMoreService.requestService(new SuccessCallback() {
            public void onSuccess(final WishPartnerLearnMoreSpec wishPartnerLearnMoreSpec) {
                WishPartnerLearnMoreServiceFragment.this.withUiFragment(new UiTask<BaseActivity, WishPartnerLearnMoreFragment>() {
                    public void performTask(BaseActivity baseActivity, WishPartnerLearnMoreFragment wishPartnerLearnMoreFragment) {
                        wishPartnerLearnMoreFragment.handleLoadingInfoSuccess(wishPartnerLearnMoreSpec);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(final String str) {
                if (str == null) {
                    str = WishPartnerLearnMoreServiceFragment.this.getString(R.string.general_error);
                }
                WishPartnerLearnMoreServiceFragment.this.withUiFragment(new UiTask<BaseActivity, WishPartnerLearnMoreFragment>() {
                    public void performTask(BaseActivity baseActivity, WishPartnerLearnMoreFragment wishPartnerLearnMoreFragment) {
                        baseActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(str));
                        wishPartnerLearnMoreFragment.handleLoadingInfoFailure();
                    }
                }, "FragmentTagMainContent");
            }
        });
    }

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
        super.cancelAllRequests();
        this.mGetWishPartnerLearnMoreService.cancelAllRequests();
    }
}
