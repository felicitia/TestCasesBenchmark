package com.contextlogic.wish.activity.login.createaccount;

import android.os.Bundle;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.UiTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.google.android.gms.auth.api.credentials.Credential;

public class CreateAccountServiceFragment extends ServiceFragment<CreateAccountActivity> {
    /* access modifiers changed from: private */
    public Runnable mRunnable;
    private boolean mSmartLockAttempted;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public void attemptSmartLockLogin(BaseActivity baseActivity) {
        this.mRunnable = new Runnable() {
            public void run() {
                CreateAccountServiceFragment.this.getHandler().removeCallbacks(CreateAccountServiceFragment.this.mRunnable);
                CreateAccountServiceFragment.this.withActivity(new ActivityTask<CreateAccountActivity>() {
                    public void performTask(CreateAccountActivity createAccountActivity) {
                        createAccountActivity.hideLoadingDialog();
                    }
                });
            }
        };
        getHandler().postDelayed(this.mRunnable, 3000);
        super.attemptSmartLockLogin(baseActivity);
    }

    /* access modifiers changed from: protected */
    public void handleCredentialsRetrieved(Credential credential) {
        getHandler().removeCallbacks(this.mRunnable);
        super.handleCredentialsRetrieved(credential);
    }

    /* access modifiers changed from: protected */
    public void handleCredentialsNotRetrieved() {
        getHandler().removeCallbacks(this.mRunnable);
        super.handleCredentialsNotRetrieved();
        withUiFragment(new UiTask<BaseActivity, CreateAccountFragment>() {
            public void performTask(BaseActivity baseActivity, CreateAccountFragment createAccountFragment) {
                if (!createAccountFragment.showSignupPopupVideo()) {
                    createAccountFragment.showHints();
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
        super.cancelAllRequests();
    }

    public void setSmartLockAttempted(boolean z) {
        this.mSmartLockAttempted = z;
    }

    public boolean smartLockAttempted() {
        return this.mSmartLockAttempted;
    }
}
