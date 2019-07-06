package com.etsy.android.ui.user.auth;

import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import com.etsy.android.lib.auth.h;
import com.etsy.android.lib.core.b.a;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.util.ExternalAccountUtil.SignInFlow;
import com.etsy.android.ui.nav.e;
import com.etsy.android.uikit.ui.dialog.DialogActivity;
import com.etsy.android.uikit.util.i;

public class SignInActivity extends DialogActivity implements a, i {
    public static final String EXTRA_REGISTER = "register";
    public static final String EXTRA_SHOW_SOCIAL_BUTTONS = "show_social_buttons";
    public static final String EXTRA_SIGN_IN = "sign_in_extra";
    public static final String HOST_TWO_FACTOR = "etsyapp.io";
    private static final String STATE_PENDING_ACCOUNT_ID = "pending_account_id";
    private static final String STATE_PENDING_ACCOUNT_TYPE_NAME = "pending_account_type_name";
    private static final String STATE_PENDING_AUTH_TOKEN = "pending_auth_token";
    private static final String STATE_PENDING_TWO_FACTOR = "pending_two_factor";
    private static final String STATE_PENDING_USERNAME = "pending_username";
    private static final String STATE_PENDING_WORKFLOW_KEY = "pending_workflow_key";
    private i mDialogHelper;
    private OnDismissListener mDismissListener;
    private boolean mHasSecurityCode;
    private boolean mIsPendingSecurityCode;
    h mLoginRequester;
    private String mPendingAccountId;
    private String mPendingAccountTypeName;
    private String mPendingAuthToken;
    private String mPendingUsername;
    private String mPendingWorkflowKey;
    private String mSecurityCode;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (v.a().e()) {
            finish();
        }
        if (bundle != null) {
            this.mIsPendingSecurityCode = bundle.getBoolean(STATE_PENDING_TWO_FACTOR);
            this.mPendingUsername = bundle.getString(STATE_PENDING_USERNAME);
            this.mPendingAccountTypeName = bundle.getString(STATE_PENDING_ACCOUNT_TYPE_NAME);
            this.mPendingAccountId = bundle.getString(STATE_PENDING_ACCOUNT_ID);
            this.mPendingAuthToken = bundle.getString(STATE_PENDING_AUTH_TOKEN);
            this.mPendingWorkflowKey = bundle.getString(STATE_PENDING_WORKFLOW_KEY);
        }
        this.mDialogHelper = new i(this);
    }

    public void onDestroy() {
        super.onDestroy();
        this.mLoginRequester.b();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        this.mLoginRequester.a(i, i2, intent);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (v.a().e()) {
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onShowDialog(OnDismissListener onDismissListener) {
        this.mDismissListener = onDismissListener;
        Intent intent = getIntent();
        if (intent != null && intent.getBooleanExtra(EXTRA_SIGN_IN, false)) {
            e.a((FragmentActivity) this).d().a(this.mDismissListener).a(SignInFlow.REGULAR, intent.getBooleanExtra("show_social_buttons", false));
        } else if (intent != null && intent.getBooleanExtra(EXTRA_REGISTER, false)) {
            e.a((FragmentActivity) this).d().a(this.mDismissListener).b(SignInFlow.REGULAR, intent.getBooleanExtra("show_social_buttons", false));
        } else if (this.mIsPendingSecurityCode) {
            com.etsy.android.ui.nav.a a = e.a((FragmentActivity) this).d().a(this.mDismissListener);
            if (this.mPendingAccountTypeName == null || this.mPendingAccountId == null || this.mPendingAuthToken == null) {
                a.a(this.mPendingUsername, this.mPendingWorkflowKey, SignInFlow.REGULAR);
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString("account_type_name", this.mPendingAccountTypeName);
            bundle.putString("account_id", this.mPendingAccountId);
            bundle.putString("auth_token", this.mPendingAuthToken);
            a.a(bundle);
            a.a(this.mPendingUsername, this.mPendingWorkflowKey, SignInFlow.LINK);
        } else {
            e.a((FragmentActivity) this).d().a(this.mDismissListener).b();
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.mIsPendingSecurityCode) {
            bundle.putBoolean(STATE_PENDING_TWO_FACTOR, true);
            bundle.putString(STATE_PENDING_USERNAME, this.mPendingUsername);
            bundle.putString(STATE_PENDING_ACCOUNT_TYPE_NAME, this.mPendingAccountTypeName);
            bundle.putString(STATE_PENDING_ACCOUNT_ID, this.mPendingAccountId);
            bundle.putString(STATE_PENDING_AUTH_TOKEN, this.mPendingAuthToken);
            bundle.putString(STATE_PENDING_WORKFLOW_KEY, this.mPendingWorkflowKey);
        }
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Uri data = intent.getData();
        if (!v.a().e() && this.mIsPendingSecurityCode && data != null && data.getHost() != null && data.getHost().equals(HOST_TWO_FACTOR)) {
            this.mSecurityCode = data.getPath().replace("/", "");
            this.mHasSecurityCode = true;
        }
    }

    public void onFetchedUser() {
        setResult(311, getIntent());
        if (getIntent().getBooleanExtra("type", false)) {
            e.a((FragmentActivity) this).a().l();
            finish();
            return;
        }
        e.a((FragmentActivity) this).h();
    }

    public void showLinkAccountSignInScreen(Bundle bundle) {
        dismissRegisterAndSigninFragments();
        e.a((FragmentActivity) this).d().a(buildDismissListener()).a(bundle).a(SignInFlow.LINK);
    }

    public void showLinkRegisterScreen(Bundle bundle) {
        dismissRegisterAndSigninFragments();
        e.a((FragmentActivity) this).d().a(buildDismissListener()).a(bundle).b(SignInFlow.LINK);
    }

    public void showTwoFactor(Bundle bundle, String str, String str2, SignInFlow signInFlow) {
        e.a((FragmentActivity) this).d().a(bundle).a(str, str2, signInFlow);
    }

    public void showSignIn() {
        e.a((FragmentActivity) this).d().a(buildDismissListener()).a(SignInFlow.REGULAR, getIntent().getBooleanExtra("show_social_buttons", false));
    }

    public void showRegister() {
        e.a((FragmentActivity) this).d().a(buildDismissListener()).b(SignInFlow.REGULAR, getIntent().getBooleanExtra("show_social_buttons", false));
    }

    private OnDismissListener buildDismissListener() {
        if (getIntent().getBooleanExtra("show_social_buttons", false)) {
            return this.mDismissListener;
        }
        return null;
    }

    private void dismissRegisterAndSigninFragments() {
        Fragment findFragmentByTag = getSupportFragmentManager().findFragmentByTag(EXTRA_REGISTER);
        Fragment findFragmentByTag2 = getSupportFragmentManager().findFragmentByTag("signIn");
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        if (findFragmentByTag2 != null) {
            beginTransaction.remove(findFragmentByTag2);
        }
        if (findFragmentByTag != null) {
            beginTransaction.remove(findFragmentByTag);
        }
        beginTransaction.commit();
        getSupportFragmentManager().executePendingTransactions();
    }

    public String getSecurityCode() {
        return this.mSecurityCode;
    }

    public boolean hasSecurityCode() {
        return this.mHasSecurityCode;
    }

    public i getDialogHelper() {
        return this.mDialogHelper;
    }
}
