package com.contextlogic.wish.activity.settings.changepassword;

import android.os.Bundle;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.UiTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.api.service.ApiService.DefaultCodeFailureCallback;
import com.contextlogic.wish.api.service.ApiService.DefaultSuccessCallback;
import com.contextlogic.wish.api.service.standalone.ChangePasswordService;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import com.contextlogic.wish.util.PreferenceUtil;

public class ChangePasswordServiceFragment extends ServiceFragment<ChangePasswordActivity> {
    private ChangePasswordService mChangePasswordService;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    /* access modifiers changed from: protected */
    public void initializeServices() {
        super.initializeServices();
        this.mChangePasswordService = new ChangePasswordService();
    }

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
        super.cancelAllRequests();
        this.mChangePasswordService.cancelAllRequests();
    }

    public void changePassword(String str, String str2) {
        withActivity(new ActivityTask<ChangePasswordActivity>() {
            public void performTask(ChangePasswordActivity changePasswordActivity) {
                changePasswordActivity.showLoadingDialog();
            }
        });
        this.mChangePasswordService.requestService(str, str2, getSuccessCallback(str2), getFailureCallback());
    }

    /* access modifiers changed from: protected */
    public DefaultSuccessCallback getSuccessCallback(final String str) {
        return new DefaultSuccessCallback() {
            public void onSuccess() {
                ChangePasswordServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ChangePasswordFragment>() {
                    public void performTask(BaseActivity baseActivity, ChangePasswordFragment changePasswordFragment) {
                        baseActivity.hideLoadingDialog();
                        PreferenceUtil.setString("user_login_password", str);
                        ChangePasswordServiceFragment.this.getAuthenticationService().updatePasswordForSmartLock(str);
                        changePasswordFragment.handleUpdateSuccess();
                    }
                }, "FragmentTagMainContent");
            }
        };
    }

    /* access modifiers changed from: protected */
    public DefaultCodeFailureCallback getFailureCallback() {
        return new DefaultCodeFailureCallback() {
            public void onFailure(final String str, final int i) {
                ChangePasswordServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ChangePasswordFragment>() {
                    public void performTask(BaseActivity baseActivity, ChangePasswordFragment changePasswordFragment) {
                        String str;
                        baseActivity.hideLoadingDialog();
                        int i = i;
                        if (i == 10) {
                            changePasswordFragment.handleOldPasswordError(str);
                        } else if (i != 12) {
                            if (str != null) {
                                str = str;
                            } else {
                                str = baseActivity.getString(R.string.error_changing_password);
                            }
                            baseActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(str));
                        } else {
                            changePasswordFragment.handleNewPasswordError(str);
                        }
                    }
                }, "FragmentTagMainContent");
            }
        };
    }
}
