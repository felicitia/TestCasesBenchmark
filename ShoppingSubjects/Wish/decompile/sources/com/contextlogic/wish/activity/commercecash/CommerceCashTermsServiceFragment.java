package com.contextlogic.wish.activity.commercecash;

import android.os.Bundle;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.UiTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.commercecash.GetCommerceCashTermsService.SuccessCallback;
import com.contextlogic.wish.api.model.WishCommerceCashTerms;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;

public class CommerceCashTermsServiceFragment extends ServiceFragment<CommerceCashTermsActivity> {
    private GetCommerceCashTermsService mGetCommerceCashTermsService;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    /* access modifiers changed from: protected */
    public void initializeServices() {
        super.initializeServices();
        this.mGetCommerceCashTermsService = new GetCommerceCashTermsService();
    }

    public void loadCommerceCashTerms() {
        showLoadingSpinner();
        this.mGetCommerceCashTermsService.requestService(new SuccessCallback() {
            public void onSuccess(final WishCommerceCashTerms wishCommerceCashTerms) {
                CommerceCashTermsServiceFragment.this.hideLoadingSpinner();
                CommerceCashTermsServiceFragment.this.withUiFragment(new UiTask<BaseActivity, CommerceCashTermsFragment>() {
                    public void performTask(BaseActivity baseActivity, CommerceCashTermsFragment commerceCashTermsFragment) {
                        commerceCashTermsFragment.handleLoadingInfoSuccess(wishCommerceCashTerms);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(final String str) {
                CommerceCashTermsServiceFragment.this.hideLoadingSpinner();
                if (str == null) {
                    str = CommerceCashTermsServiceFragment.this.getString(R.string.error_loading_terms);
                }
                CommerceCashTermsServiceFragment.this.withUiFragment(new UiTask<BaseActivity, CommerceCashTermsFragment>() {
                    public void performTask(BaseActivity baseActivity, CommerceCashTermsFragment commerceCashTermsFragment) {
                        baseActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(str));
                    }
                }, "FragmentTagMainContent");
            }
        });
    }

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
        super.cancelAllRequests();
        this.mGetCommerceCashTermsService.cancelAllRequests();
    }
}
