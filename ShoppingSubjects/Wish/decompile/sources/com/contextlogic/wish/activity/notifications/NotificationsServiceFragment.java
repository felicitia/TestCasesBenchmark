package com.contextlogic.wish.activity.notifications;

import android.os.Bundle;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.UiTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.api.datacenter.StatusDataCenter;
import com.contextlogic.wish.api.model.WishNotification;
import com.contextlogic.wish.api.model.WishTag;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.standalone.ClickNotificationService;
import com.contextlogic.wish.api.service.standalone.GetNotificationCategoriesService;
import com.contextlogic.wish.api.service.standalone.GetNotificationCategoriesService.SuccessCallback;
import com.contextlogic.wish.api.service.standalone.GetNotificationsService;
import com.contextlogic.wish.api.service.standalone.ViewNotificationService;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import java.util.ArrayList;

public class NotificationsServiceFragment extends ServiceFragment<NotificationsActivity> {
    private ClickNotificationService mClickNotificationService;
    private GetNotificationCategoriesService mGetNotificationCategoriesService;
    private GetNotificationsService mGetNotificationsService;
    private ViewNotificationService mViewNotificationService;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    /* access modifiers changed from: protected */
    public void initializeServices() {
        super.initializeServices();
        this.mGetNotificationsService = new GetNotificationsService();
        this.mGetNotificationCategoriesService = new GetNotificationCategoriesService();
        this.mClickNotificationService = new ClickNotificationService();
        this.mViewNotificationService = new ViewNotificationService();
    }

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
        super.cancelAllRequests();
        this.mGetNotificationsService.cancelAllRequests();
        this.mGetNotificationCategoriesService.cancelAllRequests();
        this.mClickNotificationService.cancelAllRequests();
        this.mViewNotificationService.cancelAllRequests();
    }

    public void loadCategories() {
        this.mGetNotificationCategoriesService.requestService(new SuccessCallback() {
            public void onSuccess(final ArrayList<WishTag> arrayList) {
                NotificationsServiceFragment.this.withUiFragment(new UiTask<BaseActivity, NotificationsFragment>() {
                    public void performTask(BaseActivity baseActivity, NotificationsFragment notificationsFragment) {
                        notificationsFragment.handleLoadingCategoriesSuccess(arrayList);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(final String str) {
                if (str == null) {
                    str = NotificationsServiceFragment.this.getString(R.string.notifications_error_message);
                }
                NotificationsServiceFragment.this.withUiFragment(new UiTask<BaseActivity, NotificationsFragment>() {
                    public void performTask(BaseActivity baseActivity, NotificationsFragment notificationsFragment) {
                        baseActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(str));
                        notificationsFragment.handleLoadingErrored(0);
                    }
                }, "FragmentTagMainContent");
            }
        });
    }

    public void loadNotifications(int i, String str, final int i2) {
        this.mGetNotificationsService.requestService(i, str, new GetNotificationsService.SuccessCallback() {
            public void onSuccess(final ArrayList<WishNotification> arrayList, final int i) {
                NotificationsServiceFragment.this.withUiFragment(new UiTask<BaseActivity, NotificationsFragment>() {
                    public void performTask(BaseActivity baseActivity, NotificationsFragment notificationsFragment) {
                        notificationsFragment.handleLoadingNotificationsSuccess(i2, arrayList, i);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(final String str) {
                if (str == null) {
                    str = NotificationsServiceFragment.this.getString(R.string.notifications_error_message);
                }
                NotificationsServiceFragment.this.withUiFragment(new UiTask<BaseActivity, NotificationsFragment>() {
                    public void performTask(BaseActivity baseActivity, NotificationsFragment notificationsFragment) {
                        baseActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(str));
                        notificationsFragment.handleLoadingErrored(i2);
                    }
                }, "FragmentTagMainContent");
            }
        });
    }

    public void handleNotificationClicked(int i, int i2, boolean z) {
        this.mClickNotificationService.requestService(i, i2, z, null, null);
    }

    public boolean isNotificationsLoading() {
        return this.mGetNotificationsService.isPending();
    }

    public void markNotificationViewed(int i, int i2) {
        this.mViewNotificationService.requestService(i, i2, null, null);
        StatusDataCenter.getInstance().decrementUnviewedNotificationCount();
        StatusDataCenter.getInstance().refresh();
    }
}
