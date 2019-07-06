package com.contextlogic.wish.activity.login.landing;

import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.UiTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.api.model.LoggedOutCountdownCoupon;
import com.contextlogic.wish.api.model.WishReloginInfo;
import com.contextlogic.wish.api.model.WishSignupVideoPopupSpec;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.standalone.GetLoggedOutExperimentsService;
import com.contextlogic.wish.api.service.standalone.GetLoggedOutExperimentsService.SuccessCallback;
import java.util.Map;

public class LandingServiceFragment extends ServiceFragment<LandingActivity> {
    protected GetLoggedOutExperimentsService mLoggedOutExperimentsService;

    /* access modifiers changed from: protected */
    public void initializeServices() {
        super.initializeServices();
        this.mLoggedOutExperimentsService = new GetLoggedOutExperimentsService();
    }

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
        super.cancelAllRequests();
        this.mLoggedOutExperimentsService.cancelAllRequests();
    }

    public void getLoggedOutExperiments() {
        withUiFragment(new UiTask<BaseActivity, LandingFragment>() {
            public void performTask(BaseActivity baseActivity, final LandingFragment landingFragment) {
                LandingServiceFragment.this.mLoggedOutExperimentsService.requestService(new SuccessCallback() {
                    public void onSuccess(Map<String, String> map, boolean z, WishReloginInfo wishReloginInfo, LoggedOutCountdownCoupon loggedOutCountdownCoupon, WishSignupVideoPopupSpec wishSignupVideoPopupSpec) {
                        landingFragment.handleLoggedOutExperimentLoadSuccess(map, z, wishReloginInfo, loggedOutCountdownCoupon, wishSignupVideoPopupSpec);
                    }
                }, new DefaultFailureCallback() {
                    public void onFailure(String str) {
                        landingFragment.handleLoggedOutExperimentLoadFailure();
                    }
                });
            }
        }, "FragmentTagMainContent");
    }
}
