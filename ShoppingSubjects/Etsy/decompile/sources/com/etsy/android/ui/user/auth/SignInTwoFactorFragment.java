package com.etsy.android.ui.user.auth;

import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.etsy.android.R;
import com.etsy.android.lib.auth.a.C0053a.c;
import com.etsy.android.lib.auth.external.a.C0057a;
import com.etsy.android.lib.auth.external.a.b;
import com.etsy.android.lib.auth.f.a.C0058a;
import com.etsy.android.lib.auth.f.a.d;
import com.etsy.android.lib.auth.h;
import com.etsy.android.lib.core.ab;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.core.z;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.util.ExternalAccountUtil;
import com.etsy.android.lib.util.ExternalAccountUtil.SignInFlow;
import com.etsy.android.lib.util.NetworkUtils;
import com.etsy.android.lib.util.af;
import com.etsy.android.stylekit.e;
import com.etsy.android.ui.dialog.EtsyDialogFragment;
import com.etsy.android.uikit.ui.core.NetworkLoaderFragment;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.util.i;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.util.HashMap;
import org.scribe.exceptions.OAuthConnectionException;

public class SignInTwoFactorFragment extends NetworkLoaderFragment implements com.etsy.android.lib.core.b.a {
    /* access modifiers changed from: private */
    public static final String TAG = f.a(SignInTwoFactorFragment.class);
    private String mAccountId;
    private String mAccountTypeName;
    private SignInActivity mActivity;
    private String mAuthCode;
    private String mAuthToken;
    private OnClickListener mClickListener;
    private boolean mDestroyed = false;
    /* access modifiers changed from: private */
    public i mDialogUtil;
    h mLoginRequester;
    private a mResendCodeTask;
    a mSignInHandler;
    /* access modifiers changed from: private */
    public String mTwoFactorWorkflowKey;
    private TextView mTxtError;
    private EditText mTxtSecurityCode;
    /* access modifiers changed from: private */
    public String mUsername;
    private View mView;
    private Disposable userRepository;

    private class a extends AsyncTask<String, Void, ab> {
        private a() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public ab doInBackground(String... strArr) {
            try {
                return v.a().g().b(SignInTwoFactorFragment.this.mUsername, SignInTwoFactorFragment.this.mTwoFactorWorkflowKey);
            } catch (OAuthConnectionException e) {
                f.e(SignInTwoFactorFragment.TAG, "OAuth Error", e);
                return null;
            }
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
            SignInTwoFactorFragment.this.mDialogUtil.a((int) R.string.sending_security_code);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(ab abVar) {
            SignInTwoFactorFragment.this.mDialogUtil.a();
        }
    }

    @NonNull
    public String getTrackingName() {
        return "login_2_factor";
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mUsername = getArguments().getString(ResponseConstants.USERNAME);
        this.mAccountTypeName = getArguments().getString("account_type_name");
        this.mAccountId = getArguments().getString("account_id");
        this.mAuthToken = getArguments().getString("auth_token");
        this.mAuthCode = getArguments().getString("auth_code");
        this.mTwoFactorWorkflowKey = getArguments().getString("workflow_key");
        this.mClickListener = createClickListener();
    }

    public void onStart() {
        super.onStart();
        this.userRepository = this.mLoginRequester.a().b(io.reactivex.e.a.b()).a(io.reactivex.a.b.a.a()).a((Consumer<? super T>) new y<Object>(this));
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void lambda$onStart$0$SignInTwoFactorFragment(com.etsy.android.lib.auth.f.a aVar) throws Exception {
        if (aVar instanceof d) {
            this.mDialogUtil.a(this.mTxtError);
            this.mDialogUtil.a((int) R.string.signing_in);
        } else if (aVar instanceof C0058a) {
            this.mDialogUtil.a();
            this.mDialogUtil.a(this.mTxtError, (int) R.string.error_two_factor_code);
        } else {
            this.mDialogUtil.a();
            this.mSignInHandler.a(aVar, this.mLoginRequester);
        }
    }

    public void onStop() {
        super.onStop();
        this.userRepository.dispose();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mView = layoutInflater.inflate(R.layout.fragment_signin_two_factor, viewGroup, false);
        return this.mView;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mActivity = (SignInActivity) getActivity();
        this.mDialogUtil = this.mActivity.getDialogHelper();
        EtsyDialogFragment etsyDialogFragment = (EtsyDialogFragment) getParentFragment();
        etsyDialogFragment.setWindowAnimation(R.style.DialogAnimBottom);
        etsyDialogFragment.enableTouchInterceptPadding(getResources().getDimensionPixelSize(R.dimen.signin_dialog_padding));
        this.mTxtError = (TextView) this.mView.findViewById(R.id.txt_error);
        this.mTxtSecurityCode = (EditText) this.mView.findViewById(R.id.edit_security_code);
        this.mTxtSecurityCode.setOnEditorActionListener(new OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 6) {
                    return false;
                }
                SignInTwoFactorFragment.this.handleSignIn();
                return true;
            }
        });
        e.a((TextView) this.mTxtSecurityCode, (int) R.string.sk_typeface_normal);
        this.mView.findViewById(R.id.button_signin).setOnClickListener(this.mClickListener);
        this.mView.findViewById(R.id.button_resend).setOnClickListener(this.mClickListener);
        ((TextView) this.mView.findViewById(R.id.text_sign_in_user)).append(this.mUsername);
        ExternalAccountUtil.c("two_factor_view");
    }

    public void onResume() {
        super.onResume();
        checkForCode();
    }

    public void onDestroy() {
        this.mDestroyed = true;
        super.onDestroy();
    }

    private OnClickListener createClickListener() {
        return new TrackingOnClickListener() {
            public void onViewClick(View view) {
                if (view.getId() == R.id.button_signin) {
                    SignInTwoFactorFragment.this.handleSignIn();
                } else if (view.getId() == R.id.button_resend) {
                    SignInTwoFactorFragment.this.handleResendCode();
                }
            }
        };
    }

    /* access modifiers changed from: private */
    public void handleResendCode() {
        final SignInFlow signInFlow = linkingAccounts() ? SignInFlow.LINK : SignInFlow.REGULAR;
        getAnalyticsContext().a("two_factor_resend", new HashMap<AnalyticsLogAttribute, Object>() {
            {
                put(AnalyticsLogAttribute.FLOW_TYPE, signInFlow.toString());
            }
        });
        resendCode(this.mUsername, this.mTwoFactorWorkflowKey);
    }

    /* access modifiers changed from: private */
    public void handleSignIn() {
        com.etsy.android.lib.auth.external.a aVar;
        String obj = this.mTxtSecurityCode.getText().toString();
        c cVar = new c(this.mUsername, obj, this.mTwoFactorWorkflowKey);
        if (TextUtils.isEmpty(obj)) {
            this.mTxtSecurityCode.setError(getString(R.string.security_code_empty));
        } else if (linkingAccounts()) {
            getAnalyticsContext().a("two_factor_submitted", new HashMap<AnalyticsLogAttribute, Object>() {
                {
                    put(AnalyticsLogAttribute.FLOW_TYPE, SignInFlow.LINK.toString());
                }
            });
            if (af.b(this.mAuthToken)) {
                aVar = new b(this.mAuthToken);
            } else {
                aVar = new C0057a(this.mAuthCode);
            }
            signIn(new com.etsy.android.lib.auth.a.b.d(cVar, this.mAccountTypeName, this.mAccountId, aVar));
        } else {
            getAnalyticsContext().a("two_factor_submitted", new HashMap<AnalyticsLogAttribute, Object>() {
                {
                    put(AnalyticsLogAttribute.FLOW_TYPE, SignInFlow.REGULAR.toString());
                }
            });
            signIn(cVar);
        }
    }

    private boolean linkingAccounts() {
        return (this.mAccountTypeName == null || this.mAccountId == null || (this.mAuthToken == null && this.mAuthCode == null)) ? false : true;
    }

    private void signIn(com.etsy.android.lib.auth.a aVar) {
        if (NetworkUtils.a().b() && aVar != null) {
            this.mLoginRequester.a(aVar);
        }
    }

    private void resendCode(String str, String str2) {
        if (NetworkUtils.a().b()) {
            if (this.mResendCodeTask != null && this.mResendCodeTask.getStatus() == Status.RUNNING) {
                this.mResendCodeTask.cancel(true);
            }
            this.mResendCodeTask = new a();
            z.a(this.mResendCodeTask, str, str2);
        }
    }

    private void checkForCode() {
        if (this.mActivity != null && !this.mDestroyed && this.mTxtSecurityCode != null && this.mActivity.hasSecurityCode()) {
            setSecurityCode(this.mActivity.getSecurityCode());
        }
    }

    private void setSecurityCode(String str) {
        if (!TextUtils.isEmpty(str)) {
            getAnalyticsContext().a("populate_security_code", null);
            this.mTxtSecurityCode.setText(str);
        }
    }

    public boolean handleBackPressed() {
        final SignInFlow signInFlow = linkingAccounts() ? SignInFlow.LINK : SignInFlow.REGULAR;
        getAnalyticsContext().a("two_factor_aborted", new HashMap<AnalyticsLogAttribute, Object>() {
            {
                put(AnalyticsLogAttribute.FLOW_TYPE, signInFlow.toString());
            }
        });
        return super.handleBackPressed();
    }
}
