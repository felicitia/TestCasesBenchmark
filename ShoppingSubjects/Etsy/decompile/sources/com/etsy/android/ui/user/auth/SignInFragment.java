package com.etsy.android.ui.user.auth;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.auth.SignInXAuthException;
import com.etsy.android.lib.auth.a.C0053a.C0054a;
import com.etsy.android.lib.auth.a.b.C0056b;
import com.etsy.android.lib.auth.external.a.C0057a;
import com.etsy.android.lib.auth.external.a.b;
import com.etsy.android.lib.auth.f;
import com.etsy.android.lib.auth.f.a.C0058a;
import com.etsy.android.lib.auth.f.a.d;
import com.etsy.android.lib.auth.h;
import com.etsy.android.lib.core.b.a;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.util.ExternalAccountUtil;
import com.etsy.android.lib.util.ExternalAccountUtil.AccountType;
import com.etsy.android.lib.util.ExternalAccountUtil.SignInFlow;
import com.etsy.android.lib.util.NetworkUtils;
import com.etsy.android.lib.util.af;
import com.etsy.android.lib.util.aj;
import com.etsy.android.lib.util.s;
import com.etsy.android.stylekit.e;
import com.etsy.android.ui.EtsyFragment;
import com.etsy.android.ui.dialog.EtsyDialogFragment;
import com.etsy.android.uikit.ui.dialog.IDialogFragment.WindowMode;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.etsy.android.uikit.util.i;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.util.HashMap;

public class SignInFragment extends EtsyFragment implements OnTouchListener, a {
    public static final String EXTRA_SHOW_SOCIAL_BUTTONS = "show_social_buttons";
    /* access modifiers changed from: private */
    public SignInActivity mActivity;
    private i mDialogUtil;
    private TextView mErrorView;
    h mLoginRequester;
    /* access modifiers changed from: private */
    public EtsyDialogFragment mParentDialog;
    private Button mSignInButton;
    a mSignInHandler;
    private TextView mSwitchToRegister;
    private EditText mTxtPassword;
    private EditText mTxtUsername;
    private Disposable userRepository;

    @NonNull
    public String getTrackingName() {
        return "login_view";
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (!(activity instanceof SignInActivity)) {
            throw new RuntimeException("SignInFragment must be used in SignInActivity");
        }
    }

    public void onStart() {
        super.onStart();
        this.mDialogUtil = ((SignInActivity) getActivity()).getDialogHelper();
        this.userRepository = this.mLoginRequester.a().b(io.reactivex.e.a.b()).a(io.reactivex.a.b.a.a()).a((Consumer<? super T>) new o<Object>(this));
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void lambda$onStart$0$SignInFragment(f.a aVar) throws Exception {
        if (aVar instanceof d) {
            this.mErrorView.setVisibility(8);
            this.mDialogUtil.a((int) R.string.signing_in);
        } else if (aVar instanceof C0058a) {
            this.mDialogUtil.a();
            this.mErrorView.setVisibility(0);
            Throwable a = ((C0058a) aVar).a();
            if (a instanceof SignInXAuthException) {
                this.mErrorView.setText(a.getMessage());
            } else {
                this.mErrorView.setText(R.string.error_sign_in);
            }
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
        View inflate = layoutInflater.inflate(R.layout.fragment_signin, viewGroup, false);
        Bundle arguments = getArguments();
        this.mErrorView = (TextView) inflate.findViewById(R.id.txt_error);
        this.mTxtUsername = (EditText) inflate.findViewById(R.id.edit_username);
        this.mTxtPassword = (EditText) inflate.findViewById(R.id.edit_password);
        TextView textView = (TextView) inflate.findViewById(R.id.txt_title);
        TextView textView2 = (TextView) inflate.findViewById(R.id.txt_subtitle);
        if (arguments.getBoolean("link_external_account")) {
            this.mTxtUsername.setText(arguments.getString("email"));
            textView.setText(R.string.external_signin_header);
            textView2.setText(getString(R.string.external_signin_subheader, getString(ExternalAccountUtil.b(arguments.getString("account_type_name")))));
            textView2.setVisibility(0);
        }
        this.mTxtPassword.setOnTouchListener(this);
        this.mTxtUsername.setOnTouchListener(this);
        this.mSignInButton = (Button) inflate.findViewById(R.id.button_signin);
        this.mSignInButton.setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                SignInFragment.this.signIn();
            }
        });
        initializeSocialSignInButtons(inflate);
        e.a((TextView) this.mTxtPassword, (int) R.string.sk_typeface_normal);
        this.mTxtPassword.setOnEditorActionListener(new p(this));
        this.mTxtUsername.postDelayed(new q(this), 400);
        inflate.findViewById(R.id.forgot_password_link).setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                SignInFragment.this.forgotPassword();
            }
        });
        this.mSwitchToRegister = (TextView) inflate.findViewById(R.id.switch_to_register);
        aj.a(this.mSwitchToRegister, (int) R.string.sign_in_switch_to_register, (int) R.string.sign_in_switch_to_register_highlight, (int) R.color.sk_orange_30);
        return inflate;
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ boolean lambda$onCreateView$1$SignInFragment(TextView textView, int i, KeyEvent keyEvent) {
        if (i != 6) {
            return false;
        }
        this.mSignInButton.performClick();
        return true;
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void lambda$onCreateView$2$SignInFragment() {
        if (TextUtils.isEmpty(this.mTxtUsername.getText())) {
            this.mTxtUsername.requestFocusFromTouch();
        } else {
            this.mTxtPassword.requestFocusFromTouch();
        }
        if (this.mActivity != null) {
            s.d(this.mTxtUsername);
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mActivity = (SignInActivity) getActivity();
        this.mParentDialog = (EtsyDialogFragment) getParentFragment();
        this.mParentDialog.hideHeader();
        this.mParentDialog.setWindowMode(WindowMode.WRAP);
        this.mSwitchToRegister.setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                SignInFragment.this.mParentDialog.dismissAllowingStateLoss(false);
                SignInFragment.this.mActivity.showRegister();
            }
        });
        ExternalAccountUtil.a("login_view", getArguments().getString("account_type_name", null));
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (this.mTxtUsername != null) {
            this.mTxtUsername.requestFocusFromTouch();
        }
        if (this.mActivity != null && !z) {
            s.a((View) this.mTxtUsername);
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        view.requestFocusFromTouch();
        return false;
    }

    public void onPause() {
        this.mTxtPassword.setText("");
        super.onPause();
    }

    private void initializeSocialSignInButtons(View view) {
        int i = 0;
        if (!getArguments().getBoolean("show_social_buttons", false)) {
            i = 8;
        }
        View findViewById = view.findViewById(R.id.signin_button_google);
        View findViewById2 = view.findViewById(R.id.signin_button_facebook);
        findViewById.setVisibility(i);
        findViewById2.setVisibility(i);
        findViewById.setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                SignInFragment.this.mLoginRequester.a(AccountType.GOOGLE);
            }
        });
        findViewById2.setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                SignInFragment.this.mLoginRequester.a(AccountType.FACEBOOK);
            }
        });
    }

    /* access modifiers changed from: private */
    public void signIn() {
        boolean z;
        com.etsy.android.lib.auth.external.a aVar;
        String trim = this.mTxtUsername.getText().toString().trim();
        String obj = this.mTxtPassword.getText().toString();
        if (TextUtils.isEmpty(trim)) {
            this.mTxtUsername.setError(getString(R.string.error_username_empty));
            z = false;
        } else {
            z = true;
        }
        if (TextUtils.isEmpty(obj)) {
            this.mTxtPassword.setError(getString(R.string.error_password_empty));
            z = false;
        }
        if (z && !NetworkUtils.a().b()) {
            this.mErrorView.setVisibility(0);
            this.mErrorView.setText(R.string.network_unavailable);
            z = false;
        }
        if (z) {
            Bundle arguments = getArguments();
            C0054a aVar2 = new C0054a(trim, obj);
            if (arguments.getBoolean("link_external_account")) {
                String string = arguments.getString("account_type_name");
                String string2 = arguments.getString("account_id");
                String string3 = arguments.getString("auth_token");
                String string4 = arguments.getString("auth_code");
                if (af.b(string3)) {
                    aVar = new b(string3);
                } else {
                    aVar = new C0057a(string4);
                }
                com.etsy.android.lib.auth.a bVar = new C0056b(aVar2, string, string2, aVar);
                getAnalyticsContext().a("sign_in_button_tapped", new HashMap<AnalyticsLogAttribute, Object>() {
                    {
                        put(AnalyticsLogAttribute.FLOW_TYPE, SignInFlow.LINK.toString());
                    }
                });
                aVar2 = bVar;
            } else {
                getAnalyticsContext().a("sign_in_button_tapped", new HashMap<AnalyticsLogAttribute, Object>() {
                    {
                        put(AnalyticsLogAttribute.FLOW_TYPE, SignInFlow.REGULAR.toString());
                    }
                });
            }
            this.mLoginRequester.a(aVar2);
        }
    }

    /* access modifiers changed from: private */
    public void forgotPassword() {
        SignInFlow signInFlow;
        String trim = this.mTxtUsername != null ? this.mTxtUsername.getText().toString().trim() : "";
        if (getArguments().getBoolean("link_external_account")) {
            signInFlow = SignInFlow.LINK;
        } else {
            signInFlow = SignInFlow.REGULAR;
        }
        com.etsy.android.ui.nav.e.a(getActivity()).a().a(trim, signInFlow);
    }

    public boolean handleBackPressed() {
        getAnalyticsContext().a("sign_in_aborted", new HashMap<AnalyticsLogAttribute, Object>() {
            {
                if (SignInFragment.this.getArguments().getBoolean("link_external_account")) {
                    put(AnalyticsLogAttribute.FLOW_TYPE, SignInFlow.LINK.toString());
                } else {
                    put(AnalyticsLogAttribute.FLOW_TYPE, SignInFlow.REGULAR.toString());
                }
            }
        });
        return super.handleBackPressed();
    }
}
