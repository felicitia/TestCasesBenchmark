package com.contextlogic.wish.activity.referralprogram;

import com.contextlogic.wish.activity.BaseFragment.UiTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.api.model.WishReferralProgramInfoSpec;
import com.contextlogic.wish.api.service.ApiService.DefaultCodeFailureCallback;
import com.contextlogic.wish.api.service.standalone.ReferralProgramService;
import com.contextlogic.wish.api.service.standalone.ReferralProgramService.SuccessCallback;

public class ReferralProgramServiceFragment extends ServiceFragment<ReferralProgramActivity> {
    ReferralProgramService mReferralProgramService;

    /* access modifiers changed from: protected */
    public void initializeServices() {
        super.initializeServices();
        this.mReferralProgramService = new ReferralProgramService();
    }

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
        super.cancelAllRequests();
        this.mReferralProgramService.cancelAllRequests();
    }

    public void requestReferralProgramInfo() {
        withUiFragment(new UiTask<ReferralProgramActivity, ReferralProgramFragment>() {
            public void performTask(final ReferralProgramActivity referralProgramActivity, final ReferralProgramFragment referralProgramFragment) {
                referralProgramActivity.showLoadingDialog();
                ReferralProgramServiceFragment.this.mReferralProgramService.requestService(new SuccessCallback() {
                    public void onSuccess(WishReferralProgramInfoSpec wishReferralProgramInfoSpec) {
                        referralProgramActivity.hideLoadingDialog();
                        referralProgramFragment.handleSuccess(wishReferralProgramInfoSpec);
                    }
                }, new DefaultCodeFailureCallback() {
                    public void onFailure(String str, int i) {
                        referralProgramActivity.hideLoadingDialog();
                        ReferralProgramFragment referralProgramFragment = referralProgramFragment;
                        if (i < 10) {
                            str = null;
                        }
                        referralProgramFragment.handleFailure(str);
                    }
                });
            }
        });
    }
}
