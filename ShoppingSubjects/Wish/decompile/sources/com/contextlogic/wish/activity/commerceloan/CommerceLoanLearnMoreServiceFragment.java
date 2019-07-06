package com.contextlogic.wish.activity.commerceloan;

import android.os.Bundle;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.UiTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.commerceloan.GetCommerceLoanLearnMoreInfoService.SuccessCallback;
import com.contextlogic.wish.api.model.WishCommerceLoanLearnMoreInfo;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;

public class CommerceLoanLearnMoreServiceFragment extends ServiceFragment<CommerceLoanLearnMoreActivity> {
    private GetCommerceLoanLearnMoreInfoService mGetCommerceLoanLearnMoreInfoService;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    /* access modifiers changed from: protected */
    public void initializeServices() {
        super.initializeServices();
        this.mGetCommerceLoanLearnMoreInfoService = new GetCommerceLoanLearnMoreInfoService();
    }

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
        super.cancelAllRequests();
        this.mGetCommerceLoanLearnMoreInfoService.cancelAllRequests();
    }

    public void loadLearnMoreInfo() {
        showLoadingSpinner();
        this.mGetCommerceLoanLearnMoreInfoService.requestService(new SuccessCallback() {
            public void onSuccess(final WishCommerceLoanLearnMoreInfo wishCommerceLoanLearnMoreInfo) {
                CommerceLoanLearnMoreServiceFragment.this.hideLoadingSpinner();
                CommerceLoanLearnMoreServiceFragment.this.withUiFragment(new UiTask<BaseActivity, CommerceLoanLearnMoreFragment>() {
                    public void performTask(BaseActivity baseActivity, CommerceLoanLearnMoreFragment commerceLoanLearnMoreFragment) {
                        commerceLoanLearnMoreFragment.handleLearnMoreInfoLoadingSuccess(wishCommerceLoanLearnMoreInfo);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(final String str) {
                CommerceLoanLearnMoreServiceFragment.this.hideLoadingSpinner();
                if (str == null) {
                    str = CommerceLoanLearnMoreServiceFragment.this.getString(R.string.commerce_loan_learn_more_error_message);
                }
                CommerceLoanLearnMoreServiceFragment.this.withUiFragment(new UiTask<BaseActivity, CommerceLoanLearnMoreFragment>() {
                    public void performTask(BaseActivity baseActivity, CommerceLoanLearnMoreFragment commerceLoanLearnMoreFragment) {
                        baseActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(str));
                    }
                }, "FragmentTagMainContent");
            }
        });
    }
}
