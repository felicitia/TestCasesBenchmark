package com.contextlogic.wish.activity.settings.accountsettings;

import android.os.Bundle;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.service.compound.AuthenticationService.LogoutCallback;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.dialog.BaseDialogFragment.BaseDialogCallback;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;

public class AccountSettingsServiceFragment extends ServiceFragment<AccountSettingsActivity> {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public void handleDeleteAccount() {
        withActivity(new ActivityTask<AccountSettingsActivity>() {
            public void performTask(AccountSettingsActivity accountSettingsActivity) {
                accountSettingsActivity.startDialog(MultiButtonDialogFragment.createMultiButtonYesNoDialog(accountSettingsActivity.getString(R.string.are_you_sure), accountSettingsActivity.getString(R.string.are_you_sure_deactivate_account)), new BaseDialogCallback() {
                    public void onCancel(BaseDialogFragment baseDialogFragment) {
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_SETTINGS_DELETE_ACCOUNT_CONFIRM_NO);
                    }

                    public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                        if (i == 1) {
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_SETTINGS_DELETE_ACCOUNT_CONFIRM_YES);
                            AccountSettingsServiceFragment.this.handleDeleteAccountDoubleConfirm();
                        }
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public void handleDeleteAccountDoubleConfirm() {
        withActivity(new ActivityTask<AccountSettingsActivity>() {
            public void performTask(AccountSettingsActivity accountSettingsActivity) {
                accountSettingsActivity.startDialog(MultiButtonDialogFragment.createMultiButtonYesNoDialog(accountSettingsActivity.getString(R.string.are_you_really_sure), accountSettingsActivity.getString(R.string.are_you_sure_delete_account_confirm)), new BaseDialogCallback() {
                    public void onCancel(BaseDialogFragment baseDialogFragment) {
                    }

                    public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                        if (i == 1) {
                            AccountSettingsServiceFragment.this.handleLogout(true);
                        }
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public void handleLogout(final boolean z) {
        withActivity(new ActivityTask<AccountSettingsActivity>() {
            public void performTask(AccountSettingsActivity accountSettingsActivity) {
                accountSettingsActivity.showLoadingDialog();
                AccountSettingsServiceFragment.this.getAuthenticationService().logout(z, false, new LogoutCallback() {
                    public void onSuccess() {
                        AccountSettingsServiceFragment.this.withActivity(new ActivityTask<AccountSettingsActivity>() {
                            public void performTask(AccountSettingsActivity accountSettingsActivity) {
                                accountSettingsActivity.hideLoadingDialog();
                                accountSettingsActivity.closeForLogout();
                            }
                        });
                    }

                    public void onFailure(final String str) {
                        AccountSettingsServiceFragment.this.withActivity(new ActivityTask<AccountSettingsActivity>() {
                            public void performTask(AccountSettingsActivity accountSettingsActivity) {
                                accountSettingsActivity.hideLoadingDialog();
                                accountSettingsActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(str));
                            }
                        });
                    }
                });
            }
        });
    }
}
