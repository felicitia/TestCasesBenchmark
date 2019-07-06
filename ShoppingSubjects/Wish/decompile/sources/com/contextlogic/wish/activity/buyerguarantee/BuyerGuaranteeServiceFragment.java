package com.contextlogic.wish.activity.buyerguarantee;

import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.UiTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.api.model.BuyerGuaranteeInfo;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.standalone.GetBuyerGuaranteeService;
import com.contextlogic.wish.api.service.standalone.GetBuyerGuaranteeService.SuccessCallback;

public class BuyerGuaranteeServiceFragment extends ServiceFragment<BuyerGuaranteeActivity> {
    /* access modifiers changed from: private */
    public GetBuyerGuaranteeService mGetBuyerGuaranteeService;

    /* access modifiers changed from: protected */
    public void initializeServices() {
        super.initializeServices();
        this.mGetBuyerGuaranteeService = new GetBuyerGuaranteeService();
    }

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
        super.cancelAllRequests();
        this.mGetBuyerGuaranteeService.cancelAllRequests();
    }

    public void getBuyerGuaranteeInfoService() {
        withUiFragment(new UiTask<BaseActivity, BuyerGuaranteeFragment>() {
            public void performTask(BaseActivity baseActivity, final BuyerGuaranteeFragment buyerGuaranteeFragment) {
                BuyerGuaranteeServiceFragment.this.mGetBuyerGuaranteeService.requestService(new SuccessCallback() {
                    public void onSuccess(BuyerGuaranteeInfo buyerGuaranteeInfo) {
                        buyerGuaranteeFragment.handleLoadingBuyerGuaranteeInfoSuccess(buyerGuaranteeInfo);
                    }
                }, new DefaultFailureCallback() {
                    public void onFailure(String str) {
                        buyerGuaranteeFragment.handleLoadingReturnPolicyInfoFailure();
                    }
                });
            }
        }, "FragmentTagMainContent");
    }
}
