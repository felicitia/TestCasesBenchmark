package com.contextlogic.wish.activity.returnpolicy;

import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.UiTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.api.model.WishReturnPolicyInfo;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.GetReturnPolicyInfoService;
import com.contextlogic.wish.api.service.GetReturnPolicyInfoService.SuccessCallback;

public class ReturnPolicyServiceFragment extends ServiceFragment<ReturnPolicyActivity> {
    /* access modifiers changed from: private */
    public GetReturnPolicyInfoService mGetReturnPolicyInfoService;

    /* access modifiers changed from: protected */
    public void initializeServices() {
        super.initializeServices();
        this.mGetReturnPolicyInfoService = new GetReturnPolicyInfoService();
    }

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
        super.cancelAllRequests();
        this.mGetReturnPolicyInfoService.cancelAllRequests();
    }

    public void getReturnPolicyInfoService() {
        withUiFragment(new UiTask<BaseActivity, ReturnPolicyFragment>() {
            public void performTask(BaseActivity baseActivity, final ReturnPolicyFragment returnPolicyFragment) {
                ReturnPolicyServiceFragment.this.mGetReturnPolicyInfoService.requestService(new SuccessCallback() {
                    public void onSuccess(WishReturnPolicyInfo wishReturnPolicyInfo) {
                        returnPolicyFragment.handleLoadingReturnPolicyInfoSuccess(wishReturnPolicyInfo);
                    }
                }, new DefaultFailureCallback() {
                    public void onFailure(String str) {
                        returnPolicyFragment.handleLoadingReturnPolicyInfoFailure();
                    }
                });
            }
        }, "FragmentTagMainContent");
    }
}
