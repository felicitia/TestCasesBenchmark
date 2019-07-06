package com.contextlogic.wish.activity.settings.changeemail;

import android.os.Bundle;
import android.text.TextUtils;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.UiTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.api.service.ApiService.DefaultCodeFailureCallback;
import com.contextlogic.wish.api.service.standalone.ChangeEmailService;
import com.contextlogic.wish.api.service.standalone.ChangeEmailService.SuccessCallback;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;

public class ChangeEmailServiceFragment extends ServiceFragment<ChangeEmailActivity> {
    private ChangeEmailService mChangeEmailService;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    /* access modifiers changed from: protected */
    public void initializeServices() {
        super.initializeServices();
        this.mChangeEmailService = new ChangeEmailService();
    }

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
        super.cancelAllRequests();
        this.mChangeEmailService.cancelAllRequests();
    }

    public void changeEmail(String str) {
        withActivity(new ActivityTask<ChangeEmailActivity>() {
            public void performTask(ChangeEmailActivity changeEmailActivity) {
                changeEmailActivity.showLoadingDialog();
            }
        });
        this.mChangeEmailService.requestService(str, getSuccessCallback(), getFailureCallback());
    }

    /* access modifiers changed from: protected */
    public SuccessCallback getSuccessCallback() {
        return new SuccessCallback() {
            public void onSuccess(final String str) {
                ChangeEmailServiceFragment.this.withUiFragment(new UiTask<ChangeEmailActivity, ChangeEmailFragment>() {
                    public void performTask(ChangeEmailActivity changeEmailActivity, ChangeEmailFragment changeEmailFragment) {
                        changeEmailActivity.hideLoadingDialog();
                        changeEmailFragment.handleChangeEmailSuccess(str);
                    }
                });
            }
        };
    }

    /* access modifiers changed from: protected */
    public DefaultCodeFailureCallback getFailureCallback() {
        return new DefaultCodeFailureCallback() {
            public void onFailure(final String str, int i) {
                ChangeEmailServiceFragment.this.withUiFragment(new UiTask<BaseActivity, ChangeEmailFragment>() {
                    public void performTask(BaseActivity baseActivity, ChangeEmailFragment changeEmailFragment) {
                        baseActivity.hideLoadingDialog();
                        if (!TextUtils.isEmpty(str)) {
                            changeEmailFragment.handleChangeEmailFailure(str);
                        } else {
                            baseActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(ChangeEmailServiceFragment.this.getString(R.string.error_changing_email)));
                        }
                    }
                });
            }
        };
    }
}
