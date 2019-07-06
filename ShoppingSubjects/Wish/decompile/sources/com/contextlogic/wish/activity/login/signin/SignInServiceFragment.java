package com.contextlogic.wish.activity.login.signin;

import android.os.Bundle;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.UiTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.api.service.standalone.GetSignInInfoService;
import com.google.android.gms.auth.api.credentials.Credential;

public class SignInServiceFragment extends ServiceFragment<SignInActivity> {
    private GetSignInInfoService mGetSignInfoService;
    /* access modifiers changed from: private */
    public Runnable mRunnable;
    private boolean mSmartLockAttempted;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mGetSignInfoService = new GetSignInInfoService();
    }

    /* access modifiers changed from: protected */
    public void initializeServices() {
        super.initializeServices();
    }

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
        super.cancelAllRequests();
        this.mGetSignInfoService.cancelAllRequests();
    }

    public void attemptSmartLockLogin(BaseActivity baseActivity) {
        this.mRunnable = new Runnable() {
            public void run() {
                SignInServiceFragment.this.getHandler().removeCallbacks(SignInServiceFragment.this.mRunnable);
                SignInServiceFragment.this.withActivity(new ActivityTask<SignInActivity>() {
                    public void performTask(SignInActivity signInActivity) {
                        signInActivity.hideLoadingDialog();
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
        withUiFragment(new UiTask<BaseActivity, SignInFragment>() {
            public void performTask(BaseActivity baseActivity, SignInFragment signInFragment) {
                signInFragment.showHints();
            }
        });
    }

    public void setSmartLockAttempted(boolean z) {
        this.mSmartLockAttempted = z;
    }

    public boolean smartLockAttempted() {
        return this.mSmartLockAttempted;
    }
}
