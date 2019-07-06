package com.contextlogic.wish.activity.settings.push;

import android.os.Bundle;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.UiTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.api.model.WishPushPreference;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.standalone.GetPushPreferencesService;
import com.contextlogic.wish.api.service.standalone.GetPushPreferencesService.SuccessCallback;
import com.contextlogic.wish.api.service.standalone.SavePushPreferenceService;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import java.util.ArrayList;

public class PushNotificationSettingsServiceFragment extends ServiceFragment<PushNotificationSettingsActivity> {
    private GetPushPreferencesService mGetPushPreferencesService;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    /* access modifiers changed from: protected */
    public void initializeServices() {
        super.initializeServices();
        this.mGetPushPreferencesService = new GetPushPreferencesService();
    }

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
        super.cancelAllRequests();
        this.mGetPushPreferencesService.cancelAllRequests();
    }

    public void loadPushPreferences() {
        this.mGetPushPreferencesService.requestService(new SuccessCallback() {
            public void onSuccess(final ArrayList<WishPushPreference> arrayList) {
                PushNotificationSettingsServiceFragment.this.withUiFragment(new UiTask<BaseActivity, PushNotificationSettingsFragment>() {
                    public void performTask(BaseActivity baseActivity, PushNotificationSettingsFragment pushNotificationSettingsFragment) {
                        pushNotificationSettingsFragment.handleLoadingSuccess(arrayList);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(final String str) {
                PushNotificationSettingsServiceFragment.this.withUiFragment(new UiTask<BaseActivity, PushNotificationSettingsFragment>() {
                    public void performTask(BaseActivity baseActivity, PushNotificationSettingsFragment pushNotificationSettingsFragment) {
                        baseActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(str));
                        pushNotificationSettingsFragment.handleLoadingErrored();
                    }
                }, "FragmentTagMainContent");
            }
        });
    }

    public void savePushPreference(WishPushPreference wishPushPreference) {
        new SavePushPreferenceService().requestService(wishPushPreference, null, null);
    }
}
