package com.contextlogic.wish.activity.feed.dailyraffle;

import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.UiTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.api.model.WishUser;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.standalone.GetRaffleWinnersService;
import com.contextlogic.wish.api.service.standalone.GetRaffleWinnersService.SuccessCallback;
import com.contextlogic.wish.dialog.prompt.PromptDialogFragment;
import java.util.ArrayList;

public class DailyRaffleWinnersServiceFragment extends ServiceFragment<DailyRaffleWinnersListActivity> {
    /* access modifiers changed from: private */
    public GetRaffleWinnersService mGetRaffleWinnersService;

    /* access modifiers changed from: protected */
    public void initializeServices() {
        super.initializeServices();
        this.mGetRaffleWinnersService = new GetRaffleWinnersService();
    }

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
        super.cancelAllRequests();
        this.mGetRaffleWinnersService.cancelAllRequests();
    }

    public void getRaffleWinners(final String str) {
        showLoadingSpinner();
        withUiFragment(new UiTask<BaseActivity, DailyRaffleWinnersListFragment>() {
            public void performTask(final BaseActivity baseActivity, final DailyRaffleWinnersListFragment dailyRaffleWinnersListFragment) {
                DailyRaffleWinnersServiceFragment.this.mGetRaffleWinnersService.requestService(str, new SuccessCallback() {
                    public void onSuccess(ArrayList<WishUser> arrayList, ArrayList<WishUser> arrayList2) {
                        DailyRaffleWinnersServiceFragment.this.hideLoadingSpinner();
                        dailyRaffleWinnersListFragment.handleLoadingSuccess(arrayList, arrayList2);
                    }
                }, new DefaultFailureCallback() {
                    public void onFailure(String str) {
                        DailyRaffleWinnersServiceFragment.this.hideLoadingSpinner();
                        baseActivity.startDialog(PromptDialogFragment.createErrorDialog(str));
                    }
                });
            }
        }, "FragmentTagMainContent");
    }
}
