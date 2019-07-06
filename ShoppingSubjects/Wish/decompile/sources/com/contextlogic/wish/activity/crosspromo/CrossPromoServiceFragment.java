package com.contextlogic.wish.activity.crosspromo;

import android.os.Bundle;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.UiTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.api.model.WishCrossPromoApp;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.ApiService.DefaultSuccessCallback;
import com.contextlogic.wish.api.service.standalone.GetCrossPromoAppListService;
import com.contextlogic.wish.api.service.standalone.GetCrossPromoAppListService.SuccessCallback;
import com.contextlogic.wish.api.service.standalone.LogCrossPromoAppClickService;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import com.contextlogic.wish.link.DeepLink;
import com.contextlogic.wish.link.DeepLinkManager;
import java.util.ArrayList;

public class CrossPromoServiceFragment extends ServiceFragment<CrossPromoActivity> {
    private GetCrossPromoAppListService mGetCrossPromoAppListService;
    private LogCrossPromoAppClickService mLogCrossPromoAppClickService;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    /* access modifiers changed from: protected */
    public void initializeServices() {
        super.initializeServices();
        this.mGetCrossPromoAppListService = new GetCrossPromoAppListService();
        this.mLogCrossPromoAppClickService = new LogCrossPromoAppClickService();
    }

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
        super.cancelAllRequests();
        this.mGetCrossPromoAppListService.cancelAllRequests();
        this.mLogCrossPromoAppClickService.cancelAllRequests();
    }

    public void handleCrossPromoAppClicked(final WishCrossPromoApp wishCrossPromoApp) {
        withActivity(new ActivityTask<CrossPromoActivity>() {
            public void performTask(CrossPromoActivity crossPromoActivity) {
                crossPromoActivity.showLoadingDialog();
            }
        });
        this.mLogCrossPromoAppClickService.requestService(wishCrossPromoApp.getPromoId(), "app_list", new DefaultSuccessCallback() {
            public void onSuccess() {
                CrossPromoServiceFragment.this.withActivity(new ActivityTask<CrossPromoActivity>() {
                    public void performTask(CrossPromoActivity crossPromoActivity) {
                        crossPromoActivity.hideLoadingDialog();
                        CrossPromoServiceFragment.this.performCrossPromoAppAction(wishCrossPromoApp);
                    }
                });
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                CrossPromoServiceFragment.this.withActivity(new ActivityTask<CrossPromoActivity>() {
                    public void performTask(CrossPromoActivity crossPromoActivity) {
                        crossPromoActivity.hideLoadingDialog();
                        CrossPromoServiceFragment.this.performCrossPromoAppAction(wishCrossPromoApp);
                    }
                });
            }
        });
    }

    public void performCrossPromoAppAction(final WishCrossPromoApp wishCrossPromoApp) {
        withActivity(new ActivityTask<CrossPromoActivity>() {
            public void performTask(CrossPromoActivity crossPromoActivity) {
                DeepLinkManager.processDeepLink(crossPromoActivity, new DeepLink(wishCrossPromoApp.getAction()));
            }
        });
    }

    public void loadCrossPromoApps() {
        this.mGetCrossPromoAppListService.requestService(new SuccessCallback() {
            public void onSuccess(final ArrayList<WishCrossPromoApp> arrayList) {
                CrossPromoServiceFragment.this.withUiFragment(new UiTask<BaseActivity, CrossPromoFragment>() {
                    public void performTask(BaseActivity baseActivity, CrossPromoFragment crossPromoFragment) {
                        crossPromoFragment.handleLoadingSuccess(arrayList);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(final String str) {
                CrossPromoServiceFragment.this.withUiFragment(new UiTask<BaseActivity, CrossPromoFragment>() {
                    public void performTask(BaseActivity baseActivity, CrossPromoFragment crossPromoFragment) {
                        baseActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(str));
                        crossPromoFragment.handleLoadingErrored();
                    }
                }, "FragmentTagMainContent");
            }
        });
    }
}
