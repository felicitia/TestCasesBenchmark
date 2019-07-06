package com.contextlogic.wish.activity.commercecash;

import android.os.Bundle;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.UiTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.api.model.WishCommerceCashHelpInfo;
import com.contextlogic.wish.api.model.WishCommerceCashHistory;
import com.contextlogic.wish.api.model.WishCommerceCashSpecs;
import com.contextlogic.wish.api.model.WishCommerceCashUserInfo;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.standalone.GetCommerceCashEventsService;
import com.contextlogic.wish.api.service.standalone.GetCommerceCashInfoService;
import com.contextlogic.wish.api.service.standalone.GetCommerceCashInfoService.SuccessCallback;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;

public class CommerceCashServiceFragment extends ServiceFragment<CommerceCashActivity> {
    private GetCommerceCashEventsService mGetCommerceCashEventsService;
    private GetCommerceCashInfoService mGetCommerceCashInfoService;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    /* access modifiers changed from: protected */
    public void initializeServices() {
        super.initializeServices();
        this.mGetCommerceCashInfoService = new GetCommerceCashInfoService();
        this.mGetCommerceCashEventsService = new GetCommerceCashEventsService();
    }

    public void getCommerceCashInfo() {
        this.mGetCommerceCashInfoService.requestService(new SuccessCallback() {
            public void onSuccess(final WishCommerceCashSpecs wishCommerceCashSpecs, final WishCommerceCashUserInfo wishCommerceCashUserInfo, final WishCommerceCashHelpInfo wishCommerceCashHelpInfo) {
                CommerceCashServiceFragment.this.withUiFragment(new UiTask<BaseActivity, CommerceCashFragment>() {
                    public void performTask(BaseActivity baseActivity, CommerceCashFragment commerceCashFragment) {
                        commerceCashFragment.handleLoadingInfoSuccess(wishCommerceCashSpecs, wishCommerceCashUserInfo, wishCommerceCashHelpInfo);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(final String str) {
                if (str == null) {
                    str = CommerceCashServiceFragment.this.getString(R.string.error_loading_wish_cash);
                }
                CommerceCashServiceFragment.this.withUiFragment(new UiTask<BaseActivity, CommerceCashFragment>() {
                    public void performTask(BaseActivity baseActivity, CommerceCashFragment commerceCashFragment) {
                        baseActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(str));
                        commerceCashFragment.handleLoadingInfoFailure();
                    }
                }, "FragmentTagMainContent");
            }
        });
    }

    public void getCommerceCashEvents(int i, int i2) {
        this.mGetCommerceCashEventsService.requestService(i, i2, new GetCommerceCashEventsService.SuccessCallback() {
            public void onSuccess(final WishCommerceCashHistory wishCommerceCashHistory) {
                CommerceCashServiceFragment.this.withUiFragment(new UiTask<BaseActivity, CommerceCashFragment>() {
                    public void performTask(BaseActivity baseActivity, CommerceCashFragment commerceCashFragment) {
                        commerceCashFragment.handleLoadingHistorySuccess(wishCommerceCashHistory);
                    }
                });
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                CommerceCashServiceFragment.this.withUiFragment(new UiTask<BaseActivity, CommerceCashFragment>() {
                    public void performTask(BaseActivity baseActivity, CommerceCashFragment commerceCashFragment) {
                        commerceCashFragment.handleLoadingHistoryFailure();
                    }
                });
            }
        });
    }

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
        super.cancelAllRequests();
        this.mGetCommerceCashInfoService.cancelAllRequests();
    }
}
