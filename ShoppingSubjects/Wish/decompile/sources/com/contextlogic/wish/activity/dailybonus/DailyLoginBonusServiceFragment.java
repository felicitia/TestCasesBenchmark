package com.contextlogic.wish.activity.dailybonus;

import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.UiTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.api.model.WishDailyLoginStampSpec;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.standalone.GetDailyLoginBonusInfoService;
import com.contextlogic.wish.api.service.standalone.GetDailyLoginBonusInfoService.SuccessCallback;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;

public class DailyLoginBonusServiceFragment extends ServiceFragment<DailyLoginBonusActivity> {
    private GetDailyLoginBonusInfoService mGetDailyLoginBonusService;

    /* access modifiers changed from: protected */
    public void initializeServices() {
        super.initializeServices();
        this.mGetDailyLoginBonusService = new GetDailyLoginBonusInfoService();
    }

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
        this.mGetDailyLoginBonusService.cancelAllRequests();
    }

    public void loadStamps() {
        showLoadingSpinner();
        this.mGetDailyLoginBonusService.requestService(new SuccessCallback() {
            public void onSuccess(final WishDailyLoginStampSpec wishDailyLoginStampSpec) {
                DailyLoginBonusServiceFragment.this.hideLoadingSpinner();
                DailyLoginBonusServiceFragment.this.withUiFragment(new UiTask<BaseActivity, DailyLoginBonusFragment>() {
                    public void performTask(BaseActivity baseActivity, DailyLoginBonusFragment dailyLoginBonusFragment) {
                        dailyLoginBonusFragment.handleStampInfoLoaded(wishDailyLoginStampSpec);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(final String str) {
                DailyLoginBonusServiceFragment.this.hideLoadingSpinner();
                DailyLoginBonusServiceFragment.this.withUiFragment(new UiTask<BaseActivity, DailyLoginBonusFragment>() {
                    public void performTask(BaseActivity baseActivity, DailyLoginBonusFragment dailyLoginBonusFragment) {
                        baseActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(str));
                    }
                }, "FragmentTagMainContent");
            }
        });
    }
}
