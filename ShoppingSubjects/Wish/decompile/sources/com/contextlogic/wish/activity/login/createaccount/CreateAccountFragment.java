package com.contextlogic.wish.activity.login.createaccount;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.SpannedString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseActivity.ActivityResultCallback;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.login.LoginFormEditText;
import com.contextlogic.wish.activity.login.SignInSignUpSocialButtonsLayout;
import com.contextlogic.wish.activity.login.header.LoginHeaderView;
import com.contextlogic.wish.activity.login.signin.LoginFormDropdownEditText;
import com.contextlogic.wish.activity.login.signin.SavedUserRow;
import com.contextlogic.wish.activity.login.signin.SignInActivity;
import com.contextlogic.wish.activity.termspolicy.TermsPolicyTextView;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.AuthenticationDataCenter;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.LoggedOutCountdownCoupon;
import com.contextlogic.wish.api.model.WishSignupVideoPopupSpec;
import com.contextlogic.wish.api.service.compound.AuthenticationService;
import com.contextlogic.wish.api.service.compound.AuthenticationService.LoginMode;
import com.contextlogic.wish.api.service.standalone.LoginService.LoginContext;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.dialog.bottomsheet.SignUpSocialButtonsBottomSheet;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice.BackgroundType;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice.ChoiceType;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment.MultiButtonDialogFragmentBuilder;
import com.contextlogic.wish.social.SmartLockManager;
import com.contextlogic.wish.social.SmartLockManager.Callback;
import com.contextlogic.wish.social.SmartLockManager.ResolutionActivityTask;
import com.contextlogic.wish.ui.image.AutoReleasableImageView;
import com.contextlogic.wish.ui.text.ThemedTextView;
import com.contextlogic.wish.ui.timer.TimerTextView;
import com.contextlogic.wish.ui.timer.TimerTextView.TimerWatcherAdapter;
import com.contextlogic.wish.util.IntentUtil;
import com.contextlogic.wish.util.KeyboardUtil;
import com.contextlogic.wish.util.LocaleUtil;
import com.contextlogic.wish.util.PreferenceUtil;
import com.contextlogic.wish.util.ViewUtil;
import com.contextlogic.wish.video.VideoPopupDialogFragment;
import com.google.android.gms.auth.api.credentials.Credential;
import java.util.ArrayList;
import java.util.Date;

public class CreateAccountFragment extends UiFragment<CreateAccountActivity> {
    /* access modifiers changed from: private */
    public static String PASSWORD_HIDE = "hide";
    /* access modifiers changed from: private */
    public static String PASSWORD_SHOW = "show";
    private SavedUserRow mAnotherUser;
    protected AutoReleasableImageView mBlueBackground;
    private View mCreateAccountContainer;
    protected ArrayList<String> mDeviceEmails;
    protected LoginFormDropdownEditText mEmailText;
    protected LoginFormEditText mFirstNameText;
    private ThemedTextView mFreeGiftBottom;
    private TimerTextView mFreeGiftCountdown;
    private ThemedTextView mFreeGiftText;
    /* access modifiers changed from: private */
    public LinearLayout mFreeGiftTimer;
    private ThemedTextView mFreeGiftTop;
    /* access modifiers changed from: private */
    public LoginHeaderView mHeader;
    protected boolean mHintsRetrieved;
    protected LoginFormEditText mLastNameText;
    protected FrameLayout mLoadingContainer;
    protected boolean mLoadingSpinnerHidden;
    protected LinearLayout mLogoContainer;
    private View mMoreOptionsBottomFill;
    private TextView mMoreOptionsText;
    private View mMoreOptionsTopFill;
    protected LoginFormEditText mPasswordText;
    /* access modifiers changed from: private */
    public LoginHeaderView mReloginHeader;
    protected Runnable mRunnable;
    private SavedUserRow mSavedUser;
    private FrameLayout mSavedUserContainer;
    private boolean mShowRelogin;
    private View mSignInButton;
    private View mSignUpQuicklyLayout;
    private SignInSignUpSocialButtonsLayout mSocialButtonsLayout;
    private TermsPolicyTextView mTermsPolicy;
    private TermsPolicyTextView mTermsPolicyFullVer;
    /* access modifiers changed from: private */
    public TextView mTogglePasswordButton;
    /* access modifiers changed from: private */
    public boolean mUserChanged;
    private boolean mUserDeleted;
    private String mUserName;
    protected LinearLayout mWhiteCover;

    /* access modifiers changed from: protected */
    public int getLayoutResourceId() {
        return R.layout.create_account_fragment_old;
    }

    /* access modifiers changed from: protected */
    public void initialize() {
        WishAnalyticsLogger.trackFirstLoginSessionEvent(WishAnalyticsEvent.IMPRESSION_FIRST_CREATE_ACCOUNT);
        this.mSavedUserContainer = (FrameLayout) findViewById(R.id.login_fragment_re_login_container);
        this.mSavedUser = (SavedUserRow) findViewById(R.id.login_fragment_saved_user);
        this.mAnotherUser = (SavedUserRow) findViewById(R.id.login_fragment_login_as_other_user);
        this.mCreateAccountContainer = findViewById(R.id.login_fragment_content_container);
        this.mFirstNameText = (LoginFormEditText) findViewById(R.id.create_account_fragment_first_name_text);
        this.mLastNameText = (LoginFormEditText) findViewById(R.id.create_account_fragment_last_name_text);
        this.mSignUpQuicklyLayout = findViewById(R.id.create_account_fragment_sign_up_quickly_layout);
        this.mSocialButtonsLayout = (SignInSignUpSocialButtonsLayout) findViewById(R.id.create_account_fragment_social_buttons_layout);
        this.mMoreOptionsText = (TextView) findViewById(R.id.create_account_fragment_more_options);
        this.mMoreOptionsTopFill = findViewById(R.id.more_options_top_space_fill);
        this.mMoreOptionsBottomFill = findViewById(R.id.more_options_bottom_space_fill);
        this.mFreeGiftTop = (ThemedTextView) findViewById(R.id.create_account_Fragment_freegift_text_top);
        this.mFreeGiftBottom = (ThemedTextView) findViewById(R.id.create_account_Fragment_freegift_text_bottom);
        this.mFreeGiftTimer = (LinearLayout) findViewById(R.id.create_account_fragment_freegift_timer);
        this.mFreeGiftCountdown = (TimerTextView) findViewById(R.id.create_account_fragment_freegift_timer_countdown);
        this.mFreeGiftText = (ThemedTextView) findViewById(R.id.create_account_fragment_freegift_timer_text);
        ThemedTextView themedTextView = (ThemedTextView) findViewById(R.id.create_account_fragment_signup);
        if (ExperimentDataCenter.getInstance().shouldSeeTopFreeGiftText()) {
            this.mFreeGiftTop.setVisibility(0);
            this.mFreeGiftTop.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(getContext(), R.drawable.giftbox_large), null, null, null);
            this.mFreeGiftTop.setCompoundDrawablePadding(getResources().getDimensionPixelSize(R.dimen.four_padding));
            LayoutParams layoutParams = new LayoutParams(-2, -2);
            layoutParams.setMargins(WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.sixteen_padding), WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.fourty_padding), WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.sixteen_padding), 0);
            layoutParams.addRule(3, R.id.login_fragment_header_view);
            layoutParams.addRule(14);
            themedTextView.setLayoutParams(layoutParams);
        }
        if (ExperimentDataCenter.getInstance().shouldShowSignupFreeGiftHeader() || ExperimentDataCenter.getInstance().shouldSeeBottomFreeGiftText()) {
            if (ExperimentDataCenter.getInstance().shouldShowSignupFreeGiftHeaderTimer()) {
                LoggedOutCountdownCoupon signupTimedGift = ((CreateAccountActivity) getBaseActivity()).getSignupTimedGift();
                if (!(signupTimedGift == null || signupTimedGift.getExpiry() == null || !signupTimedGift.getExpiry().after(new Date()))) {
                    this.mFreeGiftTimer.setVisibility(0);
                    this.mFreeGiftText.setText(getString(R.string.get_a_free_gift_and_discount, Integer.valueOf(signupTimedGift.getDiscountPercent())));
                    this.mFreeGiftCountdown.safeSetLetterSpacing(0.25f);
                    this.mFreeGiftCountdown.setup(signupTimedGift.getExpiry(), new TimerWatcherAdapter() {
                        public void onCountdownComplete() {
                            CreateAccountFragment.this.mFreeGiftTimer.setVisibility(8);
                        }
                    });
                }
            } else {
                this.mFreeGiftBottom.setVisibility(0);
                this.mFreeGiftBottom.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(getContext(), R.drawable.giftbox_small), null, null, null);
                this.mFreeGiftBottom.setCompoundDrawablePadding(getResources().getDimensionPixelSize(R.dimen.four_padding));
            }
        }
        this.mTermsPolicy = (TermsPolicyTextView) findViewById(R.id.login_fragment_terms_policy);
        this.mTermsPolicyFullVer = (TermsPolicyTextView) findViewById(R.id.login_fragment_terms_policy_full_ver);
        if (ExperimentDataCenter.getInstance().shouldShowTermsOfUseAccountCreation()) {
            this.mTermsPolicyFullVer.setVisibility(0);
            this.mTermsPolicy.setVisibility(8);
        }
        if (LocaleUtil.isJapanese()) {
            ViewGroup viewGroup = (ViewGroup) this.mFirstNameText.getParent();
            viewGroup.removeView(this.mFirstNameText);
            viewGroup.addView(this.mFirstNameText, viewGroup.indexOfChild(this.mLastNameText) + 1);
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.mFirstNameText.getLayoutParams();
            LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) this.mLastNameText.getLayoutParams();
            int i = layoutParams2.rightMargin;
            layoutParams2.rightMargin = 0;
            layoutParams3.rightMargin = i;
            int i2 = layoutParams2.leftMargin;
            layoutParams2.leftMargin = 0;
            layoutParams3.leftMargin = i2;
            this.mFirstNameText.setLayoutParams(layoutParams2);
            this.mLastNameText.setLayoutParams(layoutParams3);
            int nextFocusDownId = this.mFirstNameText.getNextFocusDownId();
            this.mFirstNameText.setNextFocusDownId(this.mLastNameText.getNextFocusDownId());
            this.mLastNameText.setNextFocusDownId(nextFocusDownId);
        }
        this.mDeviceEmails = AuthenticationService.getDeviceEmails();
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), 17367050, this.mDeviceEmails);
        this.mEmailText = (LoginFormDropdownEditText) findViewById(R.id.create_account_fragment_email_text);
        this.mEmailText.setCompoundDrawablePadding(WishApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.twelve_padding));
        this.mEmailText.setClearButton(WishApplication.getInstance().getResources().getDrawable(R.drawable.clear_textbox_12));
        this.mEmailText.setAdapter(arrayAdapter);
        this.mHeader = (LoginHeaderView) findViewById(R.id.login_fragment_header_view);
        this.mReloginHeader = (LoginHeaderView) findViewById(R.id.login_fragment_relogin_header_view);
        this.mPasswordText = (LoginFormEditText) findViewById(R.id.create_account_fragment_password_text);
        this.mPasswordText.setOnEditorActionListener(new OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 6) {
                    return false;
                }
                CreateAccountFragment.this.createAccount();
                return true;
            }
        });
        this.mTogglePasswordButton = (TextView) findViewById(R.id.create_account_fragment_password_toggle_button);
        this.mTogglePasswordButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (((String) view.getTag()).equals(CreateAccountFragment.PASSWORD_SHOW)) {
                    CreateAccountFragment.this.mTogglePasswordButton.setTag(CreateAccountFragment.PASSWORD_HIDE);
                    CreateAccountFragment.this.mTogglePasswordButton.setText(R.string.show);
                    CreateAccountFragment.this.mPasswordText.setInputType(129);
                } else {
                    CreateAccountFragment.this.mTogglePasswordButton.setTag(CreateAccountFragment.PASSWORD_SHOW);
                    CreateAccountFragment.this.mTogglePasswordButton.setText(R.string.hide);
                    CreateAccountFragment.this.mPasswordText.setInputType(144);
                }
                CreateAccountFragment.this.mPasswordText.setSelection(CreateAccountFragment.this.mPasswordText.length());
            }
        });
        this.mPasswordText.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    CreateAccountFragment.this.mTogglePasswordButton.setVisibility(0);
                } else {
                    CreateAccountFragment.this.mTogglePasswordButton.setVisibility(8);
                }
            }
        });
        this.mSignInButton = findViewById(R.id.create_account_fragment_sign_in_button);
        addHaveAnAccountSignInOnClickListener(this.mSignInButton);
        this.mLogoContainer = (LinearLayout) findViewById(R.id.login_fragment_logo_container);
        this.mWhiteCover = (LinearLayout) findViewById(R.id.login_fragment_white_cover);
        this.mBlueBackground = (AutoReleasableImageView) findViewById(R.id.login_fragment_primary_background);
        this.mLoadingContainer = (FrameLayout) findViewById(R.id.login_fragment_loading_container);
        AnonymousClass5 r1 = new OnClickListener() {
            public void onClick(View view) {
                if (view.getId() == R.id.login_fragment_login_as_other_user) {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_FAST_SIGN_IN_NEW_USER);
                } else {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_SIGNUP_WITH_EMAIL_NEW_SIGNUP_PAGE);
                }
                CreateAccountFragment.this.withActivity(new ActivityTask<CreateAccountActivity>() {
                    public void performTask(CreateAccountActivity createAccountActivity) {
                        int addResultCodeCallback = createAccountActivity.addResultCodeCallback(new ActivityResultCallback() {
                            public void onActivityResult(BaseActivity baseActivity, int i, int i2, Intent intent) {
                                if (i2 == 1) {
                                    CreateAccountFragment.this.mUserChanged = true;
                                    CreateAccountFragment.this.refreshUi();
                                }
                            }
                        });
                        CreateAccountActivity.setIsFirstTime(false);
                        Intent intent = new Intent();
                        intent.setClass(createAccountActivity, SignInActivity.class);
                        intent.putExtra("ProductGridProducts", createAccountActivity.getSigninAdapterImages());
                        IntentUtil.putParcelableExtra(intent, "ExtraPreLoginIntent", createAccountActivity.getPreLoginIntent());
                        createAccountActivity.startActivityForResult(intent, addResultCodeCallback);
                    }
                });
            }
        };
        final AnonymousClass6 r2 = new OnClickListener() {
            public void onClick(View view) {
                CreateAccountFragment.this.withServiceFragment(new ServiceTask<BaseActivity, ServiceFragment>() {
                    public void performTask(BaseActivity baseActivity, ServiceFragment serviceFragment) {
                        KeyboardUtil.hideKeyboard((Activity) baseActivity);
                        WishAnalyticsLogger.trackFirstLoginSessionEvent(WishAnalyticsEvent.CLICK_FIRST_GOOGLE_SIGN_UP);
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_GOOGLE_SIGN_UP);
                        serviceFragment.login(LoginMode.GOOGLE);
                    }
                });
            }
        };
        final AnonymousClass7 r5 = new OnClickListener() {
            public void onClick(View view) {
                CreateAccountFragment.this.withServiceFragment(new ServiceTask<BaseActivity, ServiceFragment>() {
                    public void performTask(BaseActivity baseActivity, ServiceFragment serviceFragment) {
                        KeyboardUtil.hideKeyboard((Activity) baseActivity);
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_SPLASH_FACEBOOK_LOGIN);
                        WishAnalyticsLogger.trackFirstLoginSessionEvent(WishAnalyticsEvent.CLICK_FIRST_FACEBOOK_SIGN_UP);
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_FB_SIGN_UP);
                        serviceFragment.login(LoginMode.FACEBOOK);
                    }
                });
            }
        };
        ((TextView) findViewById(R.id.create_account_fragment_create_account_button)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                CreateAccountFragment.this.createAccount();
            }
        });
        this.mSocialButtonsLayout.setFacebookClickListener(r5);
        this.mSocialButtonsLayout.setGoogleClickListener(r2);
        this.mMoreOptionsText.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_SIGNUP_MORE_OPTIONS);
                SignUpSocialButtonsBottomSheet.create(CreateAccountFragment.this.getBaseActivity()).setFacebookClickListener(r5).setGoogleClickListener(r2).show();
            }
        });
        if (ExperimentDataCenter.getInstance().shouldSeeNewSignUpScreen()) {
            ((LinearLayout) findViewById(R.id.create_account_fragment_bottom_content_container)).setGravity(80);
            setDisappearingHints();
            themedTextView.setTypeface(themedTextView.getTypeface(), 1);
            this.mSignInButton.setVisibility(8);
            ((TextView) findViewById(R.id.create_account_fragment_redesign_divider_text)).setText(R.string.or);
            TextView textView = (TextView) findViewById(R.id.create_account_fragment_sign_in_textview);
            String string = getString(R.string.have_an_account);
            SpannableString spannableString = new SpannableString(getString(R.string.sign_in));
            spannableString.setSpan(new ClickableSpan() {
                public void onClick(View view) {
                    CreateAccountFragment.this.withActivity(new ActivityTask<CreateAccountActivity>() {
                        public void performTask(CreateAccountActivity createAccountActivity) {
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_ALREADY_HAVE_ACCOUNT_SIGN_IN);
                            Intent intent = new Intent();
                            intent.putExtra("ProductGridProducts", createAccountActivity.getSigninAdapterImages());
                            intent.setClass(createAccountActivity, SignInActivity.class);
                            createAccountActivity.startActivity(intent);
                        }
                    });
                }

                public void updateDrawState(TextPaint textPaint) {
                    super.updateDrawState(textPaint);
                    textPaint.setUnderlineText(false);
                }
            }, 0, spannableString.length(), 33);
            textView.setLinkTextColor(WishApplication.getInstance().getResources().getColor(R.color.main_primary));
            textView.setMovementMethod(LinkMovementMethod.getInstance());
            textView.setText((SpannedString) TextUtils.concat(new CharSequence[]{string, " ", spannableString}));
            textView.setVisibility(0);
            if (ExperimentDataCenter.getInstance().shouldSeeVerticalSocialButtons()) {
                this.mSocialButtonsLayout.setOrientation(1);
            } else {
                this.mSocialButtonsLayout.setOrientation(0);
            }
        }
        initializeValues();
        setupViews(r1);
    }

    private void setDisappearingHints() {
        this.mFirstNameText.setHintTextColor(getResources().getColorStateList(R.color.edit_text_hint_dissappear_on_focus));
        this.mLastNameText.setHintTextColor(getResources().getColorStateList(R.color.edit_text_hint_dissappear_on_focus));
        this.mEmailText.setHintTextColor(getResources().getColorStateList(R.color.edit_text_hint_dissappear_on_focus));
        this.mPasswordText.setHintTextColor(getResources().getColorStateList(R.color.edit_text_hint_dissappear_on_focus));
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0014  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setupViews(android.view.View.OnClickListener r6) {
        /*
            r5 = this;
            java.lang.String r0 = "LoggedInUserImage"
            org.json.JSONObject r0 = com.contextlogic.wish.util.PreferenceUtil.getInsecureJSONObject(r0)
            r1 = 0
            if (r0 == 0) goto L_0x000f
            com.contextlogic.wish.api.model.WishImage r2 = new com.contextlogic.wish.api.model.WishImage     // Catch:{ Throwable -> 0x000f }
            r2.<init>(r0)     // Catch:{ Throwable -> 0x000f }
            goto L_0x0010
        L_0x000f:
            r2 = r1
        L_0x0010:
            boolean r0 = r5.mShowRelogin
            if (r0 == 0) goto L_0x015a
            com.contextlogic.wish.analytics.WishAnalyticsLogger$WishAnalyticsEvent r0 = com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent.IMPRESSION_MOBILE_RELOGIN
            com.contextlogic.wish.analytics.WishAnalyticsLogger.trackEvent(r0)
            android.widget.FrameLayout r0 = r5.mSavedUserContainer
            r3 = 0
            r0.setVisibility(r3)
            com.contextlogic.wish.activity.login.signin.SavedUserRow r0 = r5.mSavedUser
            java.lang.String r4 = r5.mUserName
            r0.setup(r2, r4, r3)
            com.contextlogic.wish.activity.login.signin.SavedUserRow r0 = r5.mAnotherUser
            com.contextlogic.wish.application.WishApplication r2 = com.contextlogic.wish.application.WishApplication.getInstance()
            r4 = 2131755750(0x7f1002e6, float:1.9142388E38)
            java.lang.String r2 = r2.getString(r4)
            r4 = 1
            r0.setup(r1, r2, r4)
            com.contextlogic.wish.activity.login.signin.SavedUserRow r0 = r5.mSavedUser
            com.contextlogic.wish.activity.login.createaccount.CreateAccountFragment$11 r1 = new com.contextlogic.wish.activity.login.createaccount.CreateAccountFragment$11
            r1.<init>()
            r0.setOnClickListener(r1)
            com.contextlogic.wish.activity.login.signin.SavedUserRow r0 = r5.mAnotherUser
            r0.setOnClickListener(r6)
            boolean r6 = com.contextlogic.wish.util.TabletUtil.isTablet()
            r0 = 2131101068(0x7f06058c, float:1.7814535E38)
            if (r6 == 0) goto L_0x00db
            r6 = 2131297560(0x7f090518, float:1.8213068E38)
            android.view.View r6 = r5.findViewById(r6)
            android.view.ViewGroup$LayoutParams r6 = r6.getLayoutParams()
            android.content.res.Resources r1 = r5.getResources()
            android.content.res.Configuration r1 = r1.getConfiguration()
            int r1 = r1.screenHeightDp
            int r1 = r1 / 3
            r6.height = r1
            r6 = 2131297559(0x7f090517, float:1.8213066E38)
            android.view.View r6 = r5.findViewById(r6)
            android.view.ViewGroup$LayoutParams r6 = r6.getLayoutParams()
            boolean r1 = r6 instanceof android.view.ViewGroup.MarginLayoutParams
            if (r1 == 0) goto L_0x0097
            android.view.ViewGroup$MarginLayoutParams r6 = (android.view.ViewGroup.MarginLayoutParams) r6
            android.content.res.Resources r1 = r5.getResources()
            int r1 = r1.getDimensionPixelOffset(r0)
            r6.leftMargin = r1
            android.content.res.Resources r1 = r5.getResources()
            int r1 = r1.getDimensionPixelOffset(r0)
            r6.topMargin = r1
            android.content.res.Resources r1 = r5.getResources()
            int r1 = r1.getDimensionPixelOffset(r0)
            r6.rightMargin = r1
        L_0x0097:
            com.contextlogic.wish.api.datacenter.ExperimentDataCenter r6 = com.contextlogic.wish.api.datacenter.ExperimentDataCenter.getInstance()
            boolean r6 = r6.shouldSeeNewSignUpScreen()
            if (r6 == 0) goto L_0x00ef
            android.widget.LinearLayout$LayoutParams r6 = new android.widget.LinearLayout$LayoutParams
            r1 = -1
            android.content.res.Resources r2 = r5.getResources()
            r3 = 2131100911(0x7f0604ef, float:1.7814217E38)
            int r2 = r2.getDimensionPixelSize(r3)
            r6.<init>(r1, r2)
            android.content.res.Resources r1 = r5.getResources()
            r2 = 2131101091(0x7f0605a3, float:1.7814582E38)
            int r1 = r1.getDimensionPixelSize(r2)
            r6.leftMargin = r1
            r6.rightMargin = r1
            android.content.res.Resources r1 = r5.getResources()
            r2 = 2131100151(0x7f0601f7, float:1.7812675E38)
            int r1 = r1.getDimensionPixelSize(r2)
            r6.bottomMargin = r1
            r6.topMargin = r1
            com.contextlogic.wish.activity.login.signin.SavedUserRow r1 = r5.mAnotherUser
            r1.setLayoutParams(r6)
            com.contextlogic.wish.activity.login.signin.SavedUserRow r1 = r5.mSavedUser
            r1.setLayoutParams(r6)
            goto L_0x00ef
        L_0x00db:
            android.widget.FrameLayout r6 = r5.mSavedUserContainer
            r1 = 8
            r6.setVisibility(r1)
            android.view.View r6 = r5.mCreateAccountContainer
            r6.setVisibility(r3)
            r5.refreshSocialButtons()
            com.contextlogic.wish.analytics.WishAnalyticsLogger$WishAnalyticsEvent r6 = com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent.IMPRESSION_MOBILE_EMAIL_SIGN_UP
            com.contextlogic.wish.analytics.WishAnalyticsLogger.trackEvent(r6)
        L_0x00ef:
            boolean r6 = com.contextlogic.wish.util.TabletUtil.isTablet()
            if (r6 == 0) goto L_0x015a
            r6 = 2131296892(0x7f09027c, float:1.8211714E38)
            android.view.View r6 = r5.findViewById(r6)
            android.view.ViewGroup$LayoutParams r6 = r6.getLayoutParams()
            boolean r1 = r6 instanceof android.view.ViewGroup.MarginLayoutParams
            if (r1 == 0) goto L_0x0124
            android.view.ViewGroup$MarginLayoutParams r6 = (android.view.ViewGroup.MarginLayoutParams) r6
            android.content.res.Resources r1 = r5.getResources()
            int r1 = r1.getDimensionPixelOffset(r0)
            r6.leftMargin = r1
            android.content.res.Resources r1 = r5.getResources()
            int r1 = r1.getDimensionPixelOffset(r0)
            r6.topMargin = r1
            android.content.res.Resources r1 = r5.getResources()
            int r1 = r1.getDimensionPixelOffset(r0)
            r6.rightMargin = r1
        L_0x0124:
            com.contextlogic.wish.activity.login.header.LoginHeaderView r6 = r5.mHeader
            android.view.ViewGroup$LayoutParams r6 = r6.getLayoutParams()
            android.content.res.Resources r1 = r5.getResources()
            android.content.res.Configuration r1 = r1.getConfiguration()
            int r1 = r1.screenHeightDp
            int r1 = r1 / 3
            r6.height = r1
            r6 = 2131297546(0x7f09050a, float:1.821304E38)
            android.view.View r6 = r5.findViewById(r6)
            android.content.res.Resources r1 = r5.getResources()
            int r1 = r1.getDimensionPixelOffset(r0)
            int r2 = r6.getPaddingTop()
            android.content.res.Resources r3 = r5.getResources()
            int r0 = r3.getDimensionPixelOffset(r0)
            int r3 = r6.getPaddingBottom()
            r6.setPadding(r1, r2, r0, r3)
        L_0x015a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.activity.login.createaccount.CreateAccountFragment.setupViews(android.view.View$OnClickListener):void");
    }

    private void refreshSocialButtons() {
        if (ExperimentDataCenter.getInstance().shouldHideSocialSignupButtons()) {
            this.mSignUpQuicklyLayout.setVisibility(8);
            this.mSocialButtonsLayout.setVisibility(8);
            if (ExperimentDataCenter.getInstance().showMoreOptionsNearTop()) {
                this.mMoreOptionsText.setVisibility(0);
                this.mMoreOptionsTopFill.setVisibility(8);
                this.mMoreOptionsBottomFill.setVisibility(0);
            } else if (ExperimentDataCenter.getInstance().showMoreOptionsNearBottom()) {
                this.mMoreOptionsText.setVisibility(0);
                this.mMoreOptionsTopFill.setVisibility(0);
                this.mMoreOptionsBottomFill.setVisibility(8);
            } else {
                this.mMoreOptionsText.setVisibility(8);
            }
        } else {
            this.mSignUpQuicklyLayout.setVisibility(0);
            this.mSocialButtonsLayout.setVisibility(0);
            this.mMoreOptionsText.setVisibility(8);
        }
    }

    /* access modifiers changed from: protected */
    public boolean validateFields() {
        ArrayList arrayList = new ArrayList();
        if (ViewUtil.extractEditTextValue(this.mFirstNameText) == null) {
            this.mFirstNameText.setError();
            arrayList.add(this.mFirstNameText);
        }
        if (ViewUtil.extractEditTextValue(this.mLastNameText) == null) {
            this.mLastNameText.setError();
            arrayList.add(this.mLastNameText);
        }
        if (ViewUtil.extractEditTextValue(this.mEmailText) == null) {
            this.mEmailText.setError();
            arrayList.add(this.mEmailText);
        }
        if (ViewUtil.extractEditTextValue(this.mPasswordText) == null) {
            this.mPasswordText.setError();
            arrayList.add(this.mPasswordText);
        }
        if (arrayList.size() <= 0) {
            return true;
        }
        withActivity(new ActivityTask<CreateAccountActivity>() {
            public void performTask(CreateAccountActivity createAccountActivity) {
                MultiButtonDialogChoice multiButtonDialogChoice = new MultiButtonDialogChoice(0, createAccountActivity.getString(R.string.got_it), R.color.white, R.drawable.main_button_selector, BackgroundType.DRAWABLE, ChoiceType.DEFAULT);
                ArrayList arrayList = new ArrayList();
                arrayList.add(multiButtonDialogChoice);
                createAccountActivity.startDialog(new MultiButtonDialogFragmentBuilder().setTitle(createAccountActivity.getString(R.string.something_went_wrong)).setSubTitle(createAccountActivity.getString(R.string.fill_in_all_fields)).setButtons(arrayList).hideXButton().build());
            }
        });
        ((EditText) arrayList.get(0)).requestFocus();
        return false;
    }

    /* access modifiers changed from: protected */
    public void initializeValues() {
        this.mUserName = PreferenceUtil.getString("LoggedInUserName");
        boolean z = false;
        this.mUserDeleted = PreferenceUtil.getBoolean("LoggedInUserDeleted", false);
        if (getSavedInstanceState() != null) {
            this.mUserChanged = getSavedInstanceState().getBoolean("SavedStateUserChanged", false);
        }
        if (this.mUserName != null && !this.mUserDeleted && !this.mUserChanged) {
            z = true;
        }
        this.mShowRelogin = z;
    }

    /* access modifiers changed from: protected */
    public void showHints() {
        if (!this.mShowRelogin) {
            withActivity(new ActivityTask<CreateAccountActivity>() {
                public void performTask(CreateAccountActivity createAccountActivity) {
                    CreateAccountFragment.this.mRunnable = new Runnable() {
                        public void run() {
                            CreateAccountFragment.this.getHandler().removeCallbacks(CreateAccountFragment.this.mRunnable);
                            CreateAccountFragment.this.withActivity(new ActivityTask<CreateAccountActivity>() {
                                public void performTask(CreateAccountActivity createAccountActivity) {
                                    CreateAccountFragment.this.mLoadingSpinnerHidden = true;
                                    createAccountActivity.hideLoadingDialog();
                                }
                            });
                        }
                    };
                    CreateAccountFragment.this.getHandler().postDelayed(CreateAccountFragment.this.mRunnable, 3000);
                    createAccountActivity.showLoadingDialog();
                    SmartLockManager.getInstance().retrieveSignInHints(new Callback() {
                        public void onSuccess(final Credential credential) {
                            if (!CreateAccountFragment.this.mLoadingSpinnerHidden || CreateAccountFragment.this.mHintsRetrieved) {
                                CreateAccountFragment.this.withServiceFragment(new ServiceTask<BaseActivity, ServiceFragment>() {
                                    public void performTask(BaseActivity baseActivity, ServiceFragment serviceFragment) {
                                        baseActivity.hideLoadingDialog();
                                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_SMART_LOCK_CREATE_ACCT_HINT_DIALOG_SUCCESS);
                                        if (credential != null) {
                                            if (credential.getName() != null) {
                                                String[] split = credential.getName().split(" ");
                                                if (split.length > 1) {
                                                    CreateAccountFragment.this.mFirstNameText.setText(split[0]);
                                                    CreateAccountFragment.this.mLastNameText.setText(split[1]);
                                                }
                                            }
                                            CreateAccountFragment.this.mEmailText.setText(credential.getId());
                                        }
                                    }
                                });
                            }
                        }

                        public void onFailure() {
                            if (!CreateAccountFragment.this.mLoadingSpinnerHidden) {
                                CreateAccountFragment.this.withActivity(new ActivityTask<CreateAccountActivity>() {
                                    public void performTask(CreateAccountActivity createAccountActivity) {
                                        createAccountActivity.hideLoadingDialog();
                                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_SMART_LOCK_CREATE_ACCT_HINT_DIALOG_CANCEL);
                                        if (CreateAccountFragment.this.mDeviceEmails.size() > 0) {
                                            CreateAccountFragment.this.mEmailText.setText((CharSequence) CreateAccountFragment.this.mDeviceEmails.get(0));
                                        }
                                    }
                                });
                            }
                        }

                        public void withActivityForResolution(final ResolutionActivityTask resolutionActivityTask) {
                            CreateAccountFragment.this.withActivity(new ActivityTask<CreateAccountActivity>() {
                                public void performTask(CreateAccountActivity createAccountActivity) {
                                    if (!CreateAccountFragment.this.mLoadingSpinnerHidden) {
                                        CreateAccountFragment.this.mHintsRetrieved = true;
                                        createAccountActivity.hideLoadingDialog();
                                        resolutionActivityTask.performTask(createAccountActivity);
                                    }
                                }
                            });
                        }
                    });
                }
            });
        }
    }

    public void handleSaveInstanceState(Bundle bundle) {
        bundle.putBoolean("SavedStateUserChanged", this.mUserChanged);
    }

    public void restoreImages() {
        if (this.mSavedUser != null) {
            this.mSavedUser.restoreImages();
        }
        if (this.mAnotherUser != null) {
            this.mAnotherUser.restoreImages();
        }
        if (this.mHeader != null) {
            this.mHeader.getProductGrid().restoreImages();
        }
        if (this.mReloginHeader != null) {
            this.mReloginHeader.getProductGrid().restoreImages();
        }
    }

    public void releaseImages() {
        if (this.mSavedUser != null) {
            this.mSavedUser.releaseImages();
        }
        if (this.mAnotherUser != null) {
            this.mAnotherUser.releaseImages();
        }
        if (this.mHeader != null) {
            this.mHeader.getProductGrid().releaseImages();
        }
        if (this.mReloginHeader != null) {
            this.mReloginHeader.getProductGrid().releaseImages();
        }
    }

    /* access modifiers changed from: private */
    public void refreshUi() {
        this.mShowRelogin = this.mUserName != null && !this.mUserDeleted && !this.mUserChanged;
        if (this.mShowRelogin) {
            this.mCreateAccountContainer.setVisibility(8);
            this.mSavedUserContainer.setVisibility(0);
            withActivity(new ActivityTask<CreateAccountActivity>() {
                public void performTask(CreateAccountActivity createAccountActivity) {
                    CreateAccountFragment.this.mReloginHeader.setUpSignInAdapterImages(createAccountActivity.getSigninAdapterImages());
                }
            });
            return;
        }
        withActivity(new ActivityTask<CreateAccountActivity>() {
            public void performTask(CreateAccountActivity createAccountActivity) {
                CreateAccountFragment.this.mHeader.setUpSignInAdapterImages(createAccountActivity.getSigninAdapterImages());
            }
        });
        this.mCreateAccountContainer.setVisibility(0);
        this.mSavedUserContainer.setVisibility(8);
        refreshSocialButtons();
    }

    public void handleResume() {
        super.handleResume();
        refreshUi();
        withServiceFragment(new ServiceTask<BaseActivity, CreateAccountServiceFragment>() {
            public void performTask(BaseActivity baseActivity, CreateAccountServiceFragment createAccountServiceFragment) {
                if (!createAccountServiceFragment.smartLockAttempted()) {
                    createAccountServiceFragment.setSmartLockAttempted(true);
                    if (!AuthenticationDataCenter.getInstance().isLoggedIn() && !PreferenceUtil.getBoolean("DisableSmartLock")) {
                        baseActivity.showLoadingDialog();
                        createAccountServiceFragment.attemptSmartLockLogin(baseActivity);
                    } else if (!AuthenticationDataCenter.getInstance().isLoggedIn() && PreferenceUtil.getBoolean("DisableSmartLock")) {
                        CreateAccountFragment.this.showHints();
                    }
                }
            }
        });
    }

    public boolean showSignupPopupVideo() {
        if (!this.mShowRelogin && !PreferenceUtil.getBoolean("SignupVideoSeen", false)) {
            WishSignupVideoPopupSpec signupPopupVideo = ((CreateAccountActivity) getBaseActivity()).getSignupPopupVideo();
            if (signupPopupVideo != null) {
                VideoPopupDialogFragment createDialog = VideoPopupDialogFragment.createDialog(signupPopupVideo);
                if (createDialog != null) {
                    PreferenceUtil.setBoolean("SignupVideoSeen", true);
                    ((CreateAccountActivity) getBaseActivity()).startDialog(createDialog);
                    return true;
                }
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void createAccount() {
        KeyboardUtil.hideKeyboard((Fragment) this);
        WishAnalyticsLogger.trackFirstLoginSessionEvent(WishAnalyticsEvent.CLICK_FIRST_EMAIL_SIGN_UP);
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_SIGN_UP);
        if (validateFields()) {
            withServiceFragment(new ServiceTask<BaseActivity, ServiceFragment>() {
                public void performTask(BaseActivity baseActivity, ServiceFragment serviceFragment) {
                    LoginContext loginContext = new LoginContext();
                    loginContext.firstName = ViewUtil.extractEditTextValue(CreateAccountFragment.this.mFirstNameText);
                    loginContext.lastName = ViewUtil.extractEditTextValue(CreateAccountFragment.this.mLastNameText);
                    loginContext.email = ViewUtil.extractEditTextValue(CreateAccountFragment.this.mEmailText);
                    loginContext.password = ViewUtil.extractEditTextValue(CreateAccountFragment.this.mPasswordText);
                    loginContext.createNewUser = true;
                    serviceFragment.login(LoginMode.EMAIL, loginContext);
                }
            });
        }
    }

    private void addHaveAnAccountSignInOnClickListener(View view) {
        view.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                CreateAccountFragment.this.withActivity(new ActivityTask<CreateAccountActivity>() {
                    public void performTask(CreateAccountActivity createAccountActivity) {
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_ALREADY_HAVE_ACCOUNT_SIGN_IN);
                        Intent intent = new Intent();
                        intent.putExtra("ProductGridProducts", createAccountActivity.getSigninAdapterImages());
                        intent.setClass(createAccountActivity, SignInActivity.class);
                        IntentUtil.putParcelableExtra(intent, "ExtraPreLoginIntent", createAccountActivity.getPreLoginIntent());
                        createAccountActivity.startActivity(intent);
                    }
                });
            }
        });
    }
}
