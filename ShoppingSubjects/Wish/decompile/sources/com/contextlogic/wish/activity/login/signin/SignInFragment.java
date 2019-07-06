package com.contextlogic.wish.activity.login.signin;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.login.SignInSignUpSocialButtonsLayout;
import com.contextlogic.wish.activity.login.createaccount.CreateAccountActivity;
import com.contextlogic.wish.activity.login.forgotpassword.ForgotPasswordActivity;
import com.contextlogic.wish.activity.login.header.LoginHeaderView;
import com.contextlogic.wish.activity.termspolicy.TermsPolicyTextView;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.AuthenticationDataCenter;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.service.compound.AuthenticationService;
import com.contextlogic.wish.api.service.compound.AuthenticationService.LoginMode;
import com.contextlogic.wish.api.service.standalone.LoginService.LoginContext;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import com.contextlogic.wish.social.SmartLockManager;
import com.contextlogic.wish.social.SmartLockManager.Callback;
import com.contextlogic.wish.social.SmartLockManager.ResolutionActivityTask;
import com.contextlogic.wish.util.IntentUtil;
import com.contextlogic.wish.util.KeyboardUtil;
import com.contextlogic.wish.util.PreferenceUtil;
import com.contextlogic.wish.util.TabletUtil;
import com.contextlogic.wish.util.ViewUtil;
import com.google.android.gms.auth.api.credentials.Credential;
import java.util.ArrayList;
import java.util.Iterator;

public class SignInFragment extends UiFragment<SignInActivity> {
    private View mCreateAccountButton;
    /* access modifiers changed from: private */
    public LoginFormDropdownEditText mEmailText;
    /* access modifiers changed from: private */
    public LoginHeaderView mHeader;
    /* access modifiers changed from: private */
    public boolean mHintsRetrieved;
    /* access modifiers changed from: private */
    public boolean mLoadingSpinnerHidden;
    /* access modifiers changed from: private */
    public LoginFormDropdownEditText mPasswordText;
    /* access modifiers changed from: private */
    public Runnable mRunnable;
    private TermsPolicyTextView mTermsPolicy;
    private TermsPolicyTextView mTermsPolicyFullVer;

    /* access modifiers changed from: protected */
    public int getLayoutResourceId() {
        return R.layout.sign_in_fragment_old;
    }

    public void restoreImages() {
        if (this.mHeader != null) {
            this.mHeader.getProductGrid().restoreImages();
        }
    }

    public void releaseImages() {
        if (this.mHeader != null) {
            this.mHeader.getProductGrid().releaseImages();
        }
    }

    /* access modifiers changed from: protected */
    public void initialize() {
        ((TextView) findViewById(R.id.sign_in_fragment_sign_in_button)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SignInFragment.this.signIn();
            }
        });
        ((TextView) findViewById(R.id.sign_in_fragment_forgot_password_button)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SignInFragment.this.withActivity(new ActivityTask<SignInActivity>() {
                    public void performTask(SignInActivity signInActivity) {
                        Intent intent = new Intent();
                        intent.setClass(signInActivity, ForgotPasswordActivity.class);
                        signInActivity.startActivity(intent);
                    }
                });
            }
        });
        SignInSignUpSocialButtonsLayout signInSignUpSocialButtonsLayout = (SignInSignUpSocialButtonsLayout) findViewById(R.id.sign_in_fragment_social_buttons_layout);
        signInSignUpSocialButtonsLayout.setFacebookClickListener(new OnClickListener() {
            public void onClick(View view) {
                SignInFragment.this.withServiceFragment(new ServiceTask<BaseActivity, ServiceFragment>() {
                    public void performTask(BaseActivity baseActivity, ServiceFragment serviceFragment) {
                        KeyboardUtil.hideKeyboard((Activity) baseActivity);
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_FB_SIGN_IN);
                        serviceFragment.login(LoginMode.FACEBOOK);
                    }
                });
            }
        });
        signInSignUpSocialButtonsLayout.setGoogleClickListener(new OnClickListener() {
            public void onClick(View view) {
                SignInFragment.this.withServiceFragment(new ServiceTask<BaseActivity, ServiceFragment>() {
                    public void performTask(BaseActivity baseActivity, ServiceFragment serviceFragment) {
                        KeyboardUtil.hideKeyboard((Activity) baseActivity);
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_GOOGLE_SIGN_IN);
                        serviceFragment.login(LoginMode.GOOGLE);
                    }
                });
            }
        });
        if (((SignInActivity) getBaseActivity()).getOnlyEmail()) {
            signInSignUpSocialButtonsLayout.setVisibility(8);
        }
        this.mCreateAccountButton = findViewById(R.id.sign_in_fragment_create_account_button);
        this.mCreateAccountButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SignInFragment.this.withActivity(new ActivityTask<SignInActivity>() {
                    public void performTask(SignInActivity signInActivity) {
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_NEW_CREATE_AN_ACCOUNT);
                        Intent intent = new Intent();
                        intent.setClass(signInActivity, CreateAccountActivity.class);
                        IntentUtil.putParcelableExtra(intent, "ExtraPreLoginIntent", signInActivity.getPreLoginIntent());
                        signInActivity.startActivity(intent);
                    }
                });
            }
        });
        this.mTermsPolicy = (TermsPolicyTextView) findViewById(R.id.sign_in_fragment_terms_policy);
        this.mTermsPolicyFullVer = (TermsPolicyTextView) findViewById(R.id.sign_in_fragment_terms_policy_full_ver);
        if (ExperimentDataCenter.getInstance().shouldShowTermsOfUseAccountCreation()) {
            this.mTermsPolicyFullVer.setVisibility(0);
            this.mTermsPolicy.setVisibility(8);
        }
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_SIGN_IN_PAGE);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), 17367050, AuthenticationService.getDeviceEmails());
        this.mEmailText = (LoginFormDropdownEditText) findViewById(R.id.sign_in_fragment_email_text);
        this.mPasswordText = (LoginFormDropdownEditText) findViewById(R.id.sign_in_fragment_password_text);
        this.mEmailText.setCompoundDrawablePadding(WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.twelve_padding));
        this.mEmailText.setClearButton(WishApplication.getInstance().getResources().getDrawable(R.drawable.clear_textbox_12));
        this.mEmailText.setAdapter(arrayAdapter);
        this.mHeader = (LoginHeaderView) findViewById(R.id.login_fragment_header_view);
        withActivity(new ActivityTask<SignInActivity>() {
            public void performTask(SignInActivity signInActivity) {
                String string = PreferenceUtil.getString("user_login_email");
                String prefilledEmailAddress = signInActivity.getPrefilledEmailAddress();
                if (prefilledEmailAddress != null) {
                    SignInFragment.this.mEmailText.setText(prefilledEmailAddress);
                } else if (string != null) {
                    SignInFragment.this.mEmailText.setText(string);
                }
                if (signInActivity.showFreeGiftErrorMessage()) {
                    SignInFragment.this.findViewById(R.id.sign_free_gift_warning).setVisibility(0);
                }
            }
        });
        this.mPasswordText.setCompoundDrawablePadding(WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.twelve_padding));
        this.mPasswordText.setClearButton(WishApplication.getInstance().getResources().getDrawable(R.drawable.clear_textbox_12));
        this.mPasswordText.setOnEditorActionListener(new OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 6) {
                    return false;
                }
                SignInFragment.this.signIn();
                return true;
            }
        });
        this.mCreateAccountButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SignInFragment.this.withActivity(new ActivityTask<SignInActivity>() {
                    public void performTask(SignInActivity signInActivity) {
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_NEW_CREATE_AN_ACCOUNT);
                        if (signInActivity.isTaskRoot()) {
                            Intent intent = new Intent();
                            intent.putExtra("ExtraShowRedesignSignup", false);
                            intent.setClass(signInActivity, CreateAccountActivity.class);
                            IntentUtil.putParcelableExtra(intent, "ExtraPreLoginIntent", signInActivity.getPreLoginIntent());
                            signInActivity.startActivity(intent);
                            return;
                        }
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_MOBILE_EMAIL_SIGN_UP);
                        signInActivity.setResult(1, new Intent());
                        signInActivity.finish();
                    }
                });
            }
        });
        withActivity(new ActivityTask<SignInActivity>() {
            public void performTask(SignInActivity signInActivity) {
                SignInFragment.this.mHeader.setUpSignInAdapterImages(signInActivity.getSigninAdapterImages());
            }
        });
        if (TabletUtil.isTablet()) {
            this.mHeader.getLayoutParams().height = getResources().getConfiguration().screenHeightDp / 3;
            LayoutParams layoutParams = findViewById(R.id.sign_in_fragment_body_container).getLayoutParams();
            if (layoutParams instanceof MarginLayoutParams) {
                MarginLayoutParams marginLayoutParams = (MarginLayoutParams) layoutParams;
                marginLayoutParams.leftMargin = getResources().getDimensionPixelOffset(R.dimen.thirty_two_padding);
                marginLayoutParams.rightMargin = getResources().getDimensionPixelOffset(R.dimen.thirty_two_padding);
            }
        }
        if (ExperimentDataCenter.getInstance().shouldSeeNewSignUpScreen()) {
            ((LinearLayout) findViewById(R.id.sign_in_fragment_bottom_content_container)).setGravity(80);
            this.mCreateAccountButton.setVisibility(8);
            setDissappearingHints();
            if (getBaseActivity() != null && !((SignInActivity) getBaseActivity()).getOnlyEmail()) {
                if (ExperimentDataCenter.getInstance().shouldSeeVerticalSocialButtons()) {
                    signInSignUpSocialButtonsLayout.setOrientation(1);
                } else {
                    signInSignUpSocialButtonsLayout.setOrientation(0);
                }
            }
            View findViewById = findViewById(R.id.sign_in_fragment_separator);
            if (ExperimentDataCenter.getInstance().shouldHideSocialSignupButtons() || ((SignInActivity) getBaseActivity()).getOnlyEmail()) {
                findViewById.setVisibility(8);
            }
            TextView textView = (TextView) findViewById(R.id.sign_in_fragment_sign_in_text);
            textView.setTypeface(textView.getTypeface(), 1);
            TextView textView2 = (TextView) findViewById(R.id.sign_in_fragment_create_account_text_view);
            StringBuilder sb = new StringBuilder();
            sb.append(getString(R.string.new_to_wish));
            sb.append(" ");
            String sb2 = sb.toString();
            SpannableString spannableString = new SpannableString(getString(R.string.create_an_account));
            spannableString.setSpan(new ClickableSpan() {
                public void onClick(View view) {
                    SignInFragment.this.withActivity(new ActivityTask<SignInActivity>() {
                        public void performTask(SignInActivity signInActivity) {
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_NEW_CREATE_AN_ACCOUNT);
                            if (signInActivity.isTaskRoot()) {
                                Intent intent = new Intent();
                                intent.putExtra("ExtraShowRedesignSignup", false);
                                intent.setClass(signInActivity, CreateAccountActivity.class);
                                signInActivity.startActivity(intent);
                                return;
                            }
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_MOBILE_EMAIL_SIGN_UP);
                            signInActivity.setResult(1, new Intent());
                            signInActivity.finish();
                        }
                    });
                }

                public void updateDrawState(TextPaint textPaint) {
                    super.updateDrawState(textPaint);
                    textPaint.setUnderlineText(false);
                }
            }, 0, spannableString.length(), 33);
            textView2.setLinkTextColor(WishApplication.getInstance().getResources().getColor(R.color.main_primary));
            textView2.setMovementMethod(LinkMovementMethod.getInstance());
            textView2.setText(TextUtils.concat(new CharSequence[]{sb2, spannableString}));
            textView2.setVisibility(0);
        }
    }

    private void setDissappearingHints() {
        this.mEmailText.setHintTextColor(getResources().getColorStateList(R.color.edit_text_hint_dissappear_on_focus));
        this.mPasswordText.setHintTextColor(getResources().getColorStateList(R.color.edit_text_hint_dissappear_on_focus));
    }

    /* access modifiers changed from: protected */
    public boolean validateFields() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.mEmailText);
        arrayList.add(this.mPasswordText);
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            LoginFormDropdownEditText loginFormDropdownEditText = (LoginFormDropdownEditText) it.next();
            if (ViewUtil.extractEditTextValue(loginFormDropdownEditText) == null) {
                loginFormDropdownEditText.setError();
                arrayList2.add(loginFormDropdownEditText);
            }
        }
        if (arrayList2.size() <= 0) {
            return true;
        }
        withActivity(new ActivityTask<SignInActivity>() {
            public void performTask(SignInActivity signInActivity) {
                signInActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(signInActivity.getString(R.string.fill_in_all_fields)));
            }
        });
        ((EditText) arrayList2.get(0)).requestFocus();
        return false;
    }

    public void handleResume() {
        super.handleResume();
        withServiceFragment(new ServiceTask<BaseActivity, SignInServiceFragment>() {
            public void performTask(BaseActivity baseActivity, SignInServiceFragment signInServiceFragment) {
                if (!signInServiceFragment.smartLockAttempted()) {
                    signInServiceFragment.setSmartLockAttempted(true);
                    if (!AuthenticationDataCenter.getInstance().isLoggedIn() && !PreferenceUtil.getBoolean("DisableSmartLock")) {
                        baseActivity.showLoadingDialog();
                        signInServiceFragment.attemptSmartLockLogin(baseActivity);
                    } else if (!AuthenticationDataCenter.getInstance().isLoggedIn() && PreferenceUtil.getBoolean("DisableSmartLock") && TextUtils.isEmpty(SignInFragment.this.mEmailText.getText())) {
                        SignInFragment.this.showHints();
                    }
                }
            }
        });
    }

    public void showHints() {
        withActivity(new ActivityTask<SignInActivity>() {
            public void performTask(SignInActivity signInActivity) {
                SignInFragment.this.mRunnable = new Runnable() {
                    public void run() {
                        SignInFragment.this.getHandler().removeCallbacks(SignInFragment.this.mRunnable);
                        SignInFragment.this.withActivity(new ActivityTask<SignInActivity>() {
                            public void performTask(SignInActivity signInActivity) {
                                SignInFragment.this.mLoadingSpinnerHidden = true;
                                signInActivity.hideLoadingDialog();
                            }
                        });
                    }
                };
                SignInFragment.this.getHandler().postDelayed(SignInFragment.this.mRunnable, 3000);
                signInActivity.showLoadingDialog();
                SmartLockManager.getInstance().retrieveSignInHints(new Callback() {
                    public void onSuccess(Credential credential) {
                        if (!SignInFragment.this.mLoadingSpinnerHidden || SignInFragment.this.mHintsRetrieved) {
                            SignInFragment.this.withActivity(new ActivityTask<SignInActivity>() {
                                public void performTask(SignInActivity signInActivity) {
                                    signInActivity.hideLoadingDialog();
                                }
                            });
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_SMART_LOCK_SIGN_IN_HINT_DIALOG_SUCCESS);
                            if (credential != null) {
                                SignInFragment.this.mEmailText.setText(credential.getId());
                                if (credential.getPassword() != null) {
                                    SignInFragment.this.mPasswordText.setText(credential.getPassword());
                                }
                            }
                        }
                    }

                    public void onFailure() {
                        if (!SignInFragment.this.mLoadingSpinnerHidden) {
                            SignInFragment.this.withActivity(new ActivityTask<SignInActivity>() {
                                public void performTask(SignInActivity signInActivity) {
                                    signInActivity.hideLoadingDialog();
                                }
                            });
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_SMART_LOCK_SIGN_IN_HINT_DIALOG_CANCEL);
                            String string = PreferenceUtil.getString("user_login_email");
                            if (string != null) {
                                SignInFragment.this.mEmailText.setText(string);
                            }
                        }
                    }

                    public void withActivityForResolution(final ResolutionActivityTask resolutionActivityTask) {
                        SignInFragment.this.withActivity(new ActivityTask<SignInActivity>() {
                            public void performTask(SignInActivity signInActivity) {
                                SignInFragment.this.mHintsRetrieved = true;
                                resolutionActivityTask.performTask(signInActivity);
                            }
                        });
                    }
                });
            }
        });
    }

    /* access modifiers changed from: protected */
    public void signIn() {
        KeyboardUtil.hideKeyboard((Fragment) this);
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_SIGN_IN);
        if (validateFields()) {
            withServiceFragment(new ServiceTask<BaseActivity, ServiceFragment>() {
                public void performTask(BaseActivity baseActivity, ServiceFragment serviceFragment) {
                    LoginContext loginContext = new LoginContext();
                    loginContext.email = ViewUtil.extractEditTextValue(SignInFragment.this.mEmailText);
                    loginContext.password = ViewUtil.extractEditTextValue(SignInFragment.this.mPasswordText);
                    serviceFragment.login(LoginMode.EMAIL, loginContext);
                }
            });
        }
    }

    public boolean onBackPressed() {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_BACK_PRESS_ON_SIGN_IN_PAGE);
        return super.onBackPressed();
    }
}
