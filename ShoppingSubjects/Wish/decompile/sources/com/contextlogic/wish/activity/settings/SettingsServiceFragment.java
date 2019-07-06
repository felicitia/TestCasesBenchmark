package com.contextlogic.wish.activity.settings;

import android.os.Bundle;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.api.service.compound.AuthenticationService.LogoutCallback;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.dialog.BaseDialogFragment.BaseDialogCallback;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice.BackgroundType;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice.ChoiceType;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment.MultiButtonDialogFragmentBuilder;
import java.util.ArrayList;

public class SettingsServiceFragment extends ServiceFragment<SettingsActivity> {
    /* access modifiers changed from: private */
    public static int CHOICE_ID_LOGOUT = 1;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public void handleLogout() {
        withActivity(new ActivityTask<SettingsActivity>() {
            public void performTask(SettingsActivity settingsActivity) {
                ArrayList arrayList = new ArrayList();
                MultiButtonDialogChoice multiButtonDialogChoice = new MultiButtonDialogChoice(SettingsServiceFragment.CHOICE_ID_LOGOUT, settingsActivity.getString(R.string.logout), R.color.white, R.drawable.main_button_selector, BackgroundType.DRAWABLE, ChoiceType.DEFAULT);
                arrayList.add(multiButtonDialogChoice);
                settingsActivity.startDialog(new MultiButtonDialogFragmentBuilder().setTitle(settingsActivity.getString(R.string.do_you_want_to_log_out)).setButtons(arrayList).build(), new BaseDialogCallback() {
                    public void onCancel(BaseDialogFragment baseDialogFragment) {
                    }

                    public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                        if (i == SettingsServiceFragment.CHOICE_ID_LOGOUT) {
                            SettingsServiceFragment.this.handleLogout(false);
                        }
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public void handleLogout(final boolean z) {
        withActivity(new ActivityTask<SettingsActivity>() {
            public void performTask(SettingsActivity settingsActivity) {
                settingsActivity.showLoadingDialog();
                SettingsServiceFragment.this.getAuthenticationService().logout(z, false, new LogoutCallback() {
                    public void onSuccess() {
                        SettingsServiceFragment.this.withActivity(new ActivityTask<SettingsActivity>() {
                            public void performTask(SettingsActivity settingsActivity) {
                                settingsActivity.hideLoadingDialog();
                                settingsActivity.closeForLogout();
                            }
                        });
                    }

                    public void onFailure(final String str) {
                        SettingsServiceFragment.this.withActivity(new ActivityTask<SettingsActivity>() {
                            public void performTask(SettingsActivity settingsActivity) {
                                settingsActivity.hideLoadingDialog();
                                settingsActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(str));
                            }
                        });
                    }
                });
            }
        });
    }
}
