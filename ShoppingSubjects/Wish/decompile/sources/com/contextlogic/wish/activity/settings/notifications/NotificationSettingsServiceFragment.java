package com.contextlogic.wish.activity.settings.notifications;

import android.os.Bundle;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.UiTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.api.model.WishNotificationPreference;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.standalone.ChangeSmsPreferenceService;
import com.contextlogic.wish.api.service.standalone.GetNotificationPreferencesService;
import com.contextlogic.wish.api.service.standalone.GetNotificationPreferencesService.SuccessCallback;
import com.contextlogic.wish.api.service.standalone.GetSmsPreferenceService;
import com.contextlogic.wish.api.service.standalone.SaveNotificationPreferencesService;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import java.util.ArrayList;

public class NotificationSettingsServiceFragment extends ServiceFragment<NotificationSettingsActivity> {
    private ChangeSmsPreferenceService mChangeSmsPreferenceService;
    private GetNotificationPreferencesService mGetNotificationPreferencesService;
    private GetSmsPreferenceService mGetSmsPreferenceService;
    private SaveNotificationPreferencesService mSaveNotificationPreferencesService;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    /* access modifiers changed from: protected */
    public void initializeServices() {
        super.initializeServices();
        this.mGetNotificationPreferencesService = new GetNotificationPreferencesService();
        this.mSaveNotificationPreferencesService = new SaveNotificationPreferencesService();
        this.mGetSmsPreferenceService = new GetSmsPreferenceService();
        this.mChangeSmsPreferenceService = new ChangeSmsPreferenceService();
    }

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
        super.cancelAllRequests();
        this.mGetNotificationPreferencesService.cancelAllRequests();
        this.mSaveNotificationPreferencesService.cancelAllRequests();
        this.mGetSmsPreferenceService.cancelAllRequests();
        this.mChangeSmsPreferenceService.cancelAllRequests();
    }

    /* access modifiers changed from: 0000 */
    public void loadNotificationPreferences() {
        this.mGetNotificationPreferencesService.requestService(new SuccessCallback() {
            public void onSuccess(final ArrayList<WishNotificationPreference> arrayList) {
                NotificationSettingsServiceFragment.this.withUiFragment(new UiTask<BaseActivity, NotificationSettingsFragment>() {
                    public void performTask(BaseActivity baseActivity, NotificationSettingsFragment notificationSettingsFragment) {
                        notificationSettingsFragment.handlePushNotiPrefLoadingSuccess(arrayList);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(final String str) {
                NotificationSettingsServiceFragment.this.withUiFragment(new UiTask<BaseActivity, NotificationSettingsFragment>() {
                    public void performTask(BaseActivity baseActivity, NotificationSettingsFragment notificationSettingsFragment) {
                        baseActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(str));
                        notificationSettingsFragment.handleLoadingErrored();
                    }
                }, "FragmentTagMainContent");
            }
        });
    }

    /* access modifiers changed from: 0000 */
    public void saveNotificationPreference(WishNotificationPreference wishNotificationPreference) {
        this.mSaveNotificationPreferencesService.requestService(wishNotificationPreference, null, null);
    }

    /* access modifiers changed from: 0000 */
    public void loadSmsPreference() {
        this.mGetSmsPreferenceService.requestService(new GetSmsPreferenceService.SuccessCallback() {
            public void onSuccess(final boolean z) {
                NotificationSettingsServiceFragment.this.withUiFragment(new UiTask<BaseActivity, NotificationSettingsFragment>() {
                    public void performTask(BaseActivity baseActivity, NotificationSettingsFragment notificationSettingsFragment) {
                        notificationSettingsFragment.handleSmsNotiPrefLoadingSuccess(z);
                    }
                });
            }
        }, new DefaultFailureCallback() {
            public void onFailure(final String str) {
                NotificationSettingsServiceFragment.this.withUiFragment(new UiTask<BaseActivity, NotificationSettingsFragment>() {
                    public void performTask(BaseActivity baseActivity, NotificationSettingsFragment notificationSettingsFragment) {
                        baseActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(str));
                        notificationSettingsFragment.handleLoadingErrored();
                    }
                });
            }
        });
    }

    public void changeSmsPreference(boolean z) {
        this.mChangeSmsPreferenceService.requestService(z, null, null);
    }
}
