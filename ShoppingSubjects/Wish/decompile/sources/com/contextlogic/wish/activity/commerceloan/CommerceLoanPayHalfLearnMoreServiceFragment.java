package com.contextlogic.wish.activity.commerceloan;

import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.UiTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.commerceloan.GetPayHalfLearnMoreInfoService.SuccessCallback;
import com.contextlogic.wish.api.model.WishCommerceLoanLearnMoreInfo;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;

public class CommerceLoanPayHalfLearnMoreServiceFragment extends ServiceFragment<CommerceLoanPayHalfLearnMoreActivity> {
    private GetPayHalfLearnMoreInfoService mGetPayHalfLearnMoreInfoService;

    /* access modifiers changed from: protected */
    public void initializeServices() {
        super.initializeServices();
        this.mGetPayHalfLearnMoreInfoService = new GetPayHalfLearnMoreInfoService();
    }

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
        super.cancelAllRequests();
        this.mGetPayHalfLearnMoreInfoService.cancelAllRequests();
    }

    public void loadLearnMoreInfo() {
        showLoadingSpinner();
        this.mGetPayHalfLearnMoreInfoService.requestService(new SuccessCallback() {
            public void onSuccess(final WishCommerceLoanLearnMoreInfo wishCommerceLoanLearnMoreInfo) {
                CommerceLoanPayHalfLearnMoreServiceFragment.this.hideLoadingSpinner();
                CommerceLoanPayHalfLearnMoreServiceFragment.this.withUiFragment(new UiTask<BaseActivity, CommerceLoanPayHalfLearnMoreFragment>() {
                    public void performTask(BaseActivity baseActivity, CommerceLoanPayHalfLearnMoreFragment commerceLoanPayHalfLearnMoreFragment) {
                        commerceLoanPayHalfLearnMoreFragment.handleLearnMoreInfoLoadingSuccess(wishCommerceLoanLearnMoreInfo);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(final String str) {
                CommerceLoanPayHalfLearnMoreServiceFragment.this.hideLoadingSpinner();
                if (str == null) {
                    str = CommerceLoanPayHalfLearnMoreServiceFragment.this.getString(R.string.commerce_loan_learn_more_error_message);
                }
                CommerceLoanPayHalfLearnMoreServiceFragment.this.withUiFragment(new UiTask<BaseActivity, CommerceLoanPayHalfLearnMoreFragment>() {
                    public void performTask(BaseActivity baseActivity, CommerceLoanPayHalfLearnMoreFragment commerceLoanPayHalfLearnMoreFragment) {
                        baseActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(str));
                    }
                }, "FragmentTagMainContent");
            }
        });
    }
}
