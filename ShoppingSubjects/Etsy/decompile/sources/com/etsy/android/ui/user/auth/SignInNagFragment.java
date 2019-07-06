package com.etsy.android.ui.user.auth;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.Button;
import android.widget.TextView;
import com.etsy.android.R;
import com.etsy.android.lib.auth.ExternalAccountException;
import com.etsy.android.lib.auth.SignInXAuthException;
import com.etsy.android.lib.auth.f;
import com.etsy.android.lib.auth.f.a.C0058a;
import com.etsy.android.lib.auth.f.a.d;
import com.etsy.android.lib.auth.h;
import com.etsy.android.lib.core.b.a;
import com.etsy.android.lib.messaging.EtsyAction;
import com.etsy.android.lib.util.ExternalAccountUtil;
import com.etsy.android.lib.util.ExternalAccountUtil.AccountType;
import com.etsy.android.ui.EtsyFragment;
import com.etsy.android.ui.dialog.EtsyDialogFragment;
import com.etsy.android.uikit.util.TrackingOnClickListener;
import com.google.android.gms.common.GooglePlayServicesUtil;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class SignInNagFragment extends EtsyFragment implements a {
    /* access modifiers changed from: private */
    public SignInActivity mActivity;
    private OnClickListener mClickListener;
    /* access modifiers changed from: private */
    public TextView mError;
    /* access modifiers changed from: private */
    public View mLoginFormView;
    h mLoginRequester;
    private EtsyDialogFragment mParentDialog;
    /* access modifiers changed from: private */
    public View mProgressView;
    private boolean mShowGoogleSignIn;
    a mSignInHandler;
    private TextView mTxtDesc;
    private Disposable userRepository;

    @NonNull
    public String getTrackingName() {
        return "login_nag";
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mClickListener = createClickListener();
        this.mShowGoogleSignIn = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity()) == 0;
    }

    public void onStart() {
        super.onStart();
        this.userRepository = this.mLoginRequester.a().b(io.reactivex.e.a.b()).a(io.reactivex.a.b.a.a()).a((Consumer<? super T>) new w<Object>(this));
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void lambda$onStart$0$SignInNagFragment(f.a aVar) throws Exception {
        if (aVar instanceof d) {
            showProgress(true);
        } else if (aVar instanceof C0058a) {
            showError(((C0058a) aVar).a());
        } else {
            showProgress(false);
            this.mSignInHandler.a(aVar, this.mLoginRequester);
        }
    }

    public void onStop() {
        super.onStop();
        this.userRepository.dispose();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_signin_nag, viewGroup, false);
        this.mError = (TextView) inflate.findViewById(R.id.txt_error);
        Button button = (Button) inflate.findViewById(R.id.button_google_signin);
        if (this.mShowGoogleSignIn) {
            button.setOnClickListener(new TrackingOnClickListener() {
                public void onViewClick(View view) {
                    ExternalAccountUtil.c("login_nag_google_button_tapped");
                    SignInNagFragment.this.signIn(AccountType.GOOGLE);
                }
            });
        } else {
            button.setVisibility(8);
        }
        ((Button) inflate.findViewById(R.id.button_facebook_signin)).setOnClickListener(new TrackingOnClickListener() {
            public void onViewClick(View view) {
                ExternalAccountUtil.c("login_nag_facebook_button_tapped");
                SignInNagFragment.this.signIn(AccountType.FACEBOOK);
            }
        });
        this.mTxtDesc = (TextView) inflate.findViewById(R.id.text_sign_in_dialog_desc);
        inflate.findViewById(R.id.btn_register_dialog).setOnClickListener(this.mClickListener);
        inflate.findViewById(R.id.btn_sign_in_dialog).setOnClickListener(this.mClickListener);
        this.mLoginFormView = inflate.findViewById(R.id.login_form);
        this.mProgressView = inflate.findViewById(R.id.login_progress);
        return inflate;
    }

    /* access modifiers changed from: private */
    public void signIn(AccountType accountType) {
        showProgress(true);
        this.mLoginRequester.a(accountType);
    }

    public void onResume() {
        super.onResume();
        this.mParentDialog.setWindowAnimation(R.anim.dialog_enter_default);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mActivity = (SignInActivity) getActivity();
        this.mParentDialog = (EtsyDialogFragment) getParentFragment();
        this.mParentDialog.setWindowAnimation(R.style.DialogAnimBottom);
        this.mParentDialog.enableTouchInterceptPadding(getResources().getDimensionPixelSize(R.dimen.signin_dialog_padding));
        this.mParentDialog.hideHeader();
        String stringExtra = this.mActivity.getIntent().getStringExtra(EtsyAction.ACTION_TYPE_NAME);
        switch (EtsyAction.fromName(stringExtra)) {
            case FOLLOW:
                this.mTxtDesc.setText(getString(R.string.sign_in_dialog_follow_desc_text));
                break;
            case PURCHASE:
                this.mTxtDesc.setText(getString(R.string.sign_in_dialog_purchase_desc_text));
                break;
            case FAVORITE:
                this.mTxtDesc.setText(getString(R.string.sign_in_dialog_favorite_desc_text));
                break;
            case CONTACT_USER:
                this.mTxtDesc.setText(getString(R.string.sign_in_dialog_contact_desc_text));
                break;
            case MANAGE_ITEM_COLLECTIONS:
                this.mTxtDesc.setText(getString(R.string.sign_in_dialog_manage_collections_desc_text));
                break;
            case VIEW_ORDER:
                this.mTxtDesc.setText(getString(R.string.sign_in_dialog_view_order_desc_text));
                break;
            case VIEW_PURCHASES:
                this.mTxtDesc.setText(getString(R.string.sign_in_dialog_view_purchases_desc_text));
                break;
            case VIEW_CONVO:
                this.mTxtDesc.setText(getString(R.string.sign_in_dialog_view_convos_desc_text));
                break;
            case SUBSCRIBE_VACATION_NOTIFICATION:
                this.mTxtDesc.setText(getString(R.string.sign_in_dialog_subscribe_vacation_notification_text));
                break;
            default:
                this.mTxtDesc.setText(getString(R.string.sign_in_dialog_default_text));
                break;
        }
        ExternalAccountUtil.c("login_nag_displayed");
        StringBuilder sb = new StringBuilder();
        sb.append("login_nag_displayed_action.");
        sb.append(stringExtra);
        ExternalAccountUtil.c(sb.toString());
    }

    private OnClickListener createClickListener() {
        return new TrackingOnClickListener() {
            public void onViewClick(View view) {
                SignInNagFragment.this.mError.setVisibility(8);
                if (view.getId() == R.id.btn_sign_in_dialog) {
                    SignInNagFragment.this.mActivity.showSignIn();
                } else if (view.getId() == R.id.btn_register_dialog) {
                    SignInNagFragment.this.mActivity.showRegister();
                }
            }
        };
    }

    public void showProgress(final boolean z) {
        int i = 8;
        if (z) {
            this.mError.setVisibility(8);
        }
        int integer = getResources().getInteger(17694720);
        this.mLoginFormView.setVisibility(z ? 4 : 0);
        long j = (long) integer;
        float f = 1.0f;
        this.mLoginFormView.animate().setDuration(j).alpha(z ? 0.0f : 1.0f).setListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                if (SignInNagFragment.this.mLoginFormView != null) {
                    SignInNagFragment.this.mLoginFormView.setVisibility(z ? 4 : 0);
                }
            }
        });
        View view = this.mProgressView;
        if (z) {
            i = 0;
        }
        view.setVisibility(i);
        ViewPropertyAnimator duration = this.mProgressView.animate().setDuration(j);
        if (!z) {
            f = 0.0f;
        }
        duration.alpha(f).setListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                if (SignInNagFragment.this.mProgressView != null) {
                    SignInNagFragment.this.mProgressView.setVisibility(z ? 0 : 8);
                }
            }
        });
    }

    public void showError(Throwable th) {
        showProgress(false);
        this.mError.setVisibility(0);
        if (th instanceof ExternalAccountException) {
            this.mError.setText(getString(R.string.external_error_connecting, ExternalAccountUtil.a(((ExternalAccountException) th).getAccountType())));
        } else if (th instanceof SignInXAuthException) {
            this.mError.setText(th.getMessage());
        } else {
            this.mError.setText(R.string.error_external_account_api);
            com.etsy.android.lib.logger.f.b(th);
        }
    }
}
