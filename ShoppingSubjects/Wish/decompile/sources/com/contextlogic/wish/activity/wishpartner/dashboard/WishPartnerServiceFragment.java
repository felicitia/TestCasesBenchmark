package com.contextlogic.wish.activity.wishpartner.dashboard;

import android.os.Bundle;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.UiTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.api.model.WishPartnerSummary;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.standalone.GetWishPartnerSummaryService;
import com.contextlogic.wish.api.service.standalone.GetWishPartnerSummaryService.SuccessCallback;

public class WishPartnerServiceFragment extends ServiceFragment<WishPartnerDashboardActivity> {
    private GetWishPartnerSummaryService mGetWishPartnerSummaryService;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    /* access modifiers changed from: protected */
    public void initializeServices() {
        super.initializeServices();
        this.mGetWishPartnerSummaryService = new GetWishPartnerSummaryService();
    }

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
        super.cancelAllRequests();
        this.mGetWishPartnerSummaryService.cancelAllRequests();
    }

    public void initialSetup() {
        this.mGetWishPartnerSummaryService.requestService(null, 20, false, new SuccessCallback() {
            public void onSuccess(final WishPartnerSummary wishPartnerSummary) {
                WishPartnerServiceFragment.this.withUiFragment(new UiTask<BaseActivity, WishPartnerDashboardFragment>() {
                    public void performTask(BaseActivity baseActivity, WishPartnerDashboardFragment wishPartnerDashboardFragment) {
                        wishPartnerDashboardFragment.handleInitialLoadingSuccess(wishPartnerSummary);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(final String str) {
                WishPartnerServiceFragment.this.withUiFragment(new UiTask<BaseActivity, WishPartnerDashboardFragment>() {
                    public void performTask(BaseActivity baseActivity, WishPartnerDashboardFragment wishPartnerDashboardFragment) {
                        wishPartnerDashboardFragment.handleInitialLoadingFailed(str == null ? WishPartnerServiceFragment.this.getString(R.string.general_error) : str);
                    }
                }, "FragmentTagMainContent");
            }
        });
    }

    public void getWishCommunityEvents(String str) {
        this.mGetWishPartnerSummaryService.requestService(str, 20, true, new SuccessCallback() {
            public void onSuccess(final WishPartnerSummary wishPartnerSummary) {
                WishPartnerServiceFragment.this.withUiFragment(new UiTask<BaseActivity, WishPartnerDashboardFragment>() {
                    public void performTask(BaseActivity baseActivity, WishPartnerDashboardFragment wishPartnerDashboardFragment) {
                        wishPartnerDashboardFragment.handleCommunityLoadingSuccess(wishPartnerSummary);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(final String str) {
                WishPartnerServiceFragment.this.withUiFragment(new UiTask<BaseActivity, WishPartnerDashboardFragment>() {
                    public void performTask(BaseActivity baseActivity, WishPartnerDashboardFragment wishPartnerDashboardFragment) {
                        wishPartnerDashboardFragment.handleLoadingFailed(str == null ? WishPartnerServiceFragment.this.getString(R.string.general_error) : str);
                    }
                }, "FragmentTagMainContent");
            }
        });
    }

    public void getWishRecentEvents(String str) {
        this.mGetWishPartnerSummaryService.requestService(str, 20, false, new SuccessCallback() {
            public void onSuccess(final WishPartnerSummary wishPartnerSummary) {
                WishPartnerServiceFragment.this.withUiFragment(new UiTask<BaseActivity, WishPartnerDashboardFragment>() {
                    public void performTask(BaseActivity baseActivity, WishPartnerDashboardFragment wishPartnerDashboardFragment) {
                        wishPartnerDashboardFragment.handleRecentEarningsLoadingSuccess(wishPartnerSummary);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(final String str) {
                WishPartnerServiceFragment.this.withUiFragment(new UiTask<BaseActivity, WishPartnerDashboardFragment>() {
                    public void performTask(BaseActivity baseActivity, WishPartnerDashboardFragment wishPartnerDashboardFragment) {
                        wishPartnerDashboardFragment.handleLoadingFailed(str == null ? WishPartnerServiceFragment.this.getString(R.string.general_error) : str);
                    }
                }, "FragmentTagMainContent");
            }
        });
    }
}
