package com.contextlogic.wish.activity.signup;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.actionbar.ActionBarItem;
import com.contextlogic.wish.activity.signup.SignupCategory.SignupCategoryActivity;
import com.contextlogic.wish.activity.signup.redesign.SignupFlowFooterView.FooterManager;
import com.contextlogic.wish.activity.signup.redesign.SignupGetEmailView;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.datacenter.ProfileDataCenter;
import com.contextlogic.wish.api.service.compound.AuthenticationService.LoginMode;
import com.contextlogic.wish.api.service.standalone.LoginService.SignupFlowContext.SignupFlowType;
import com.contextlogic.wish.util.KeyboardUtil;
import java.util.Calendar;

public class SignupProfileUpdateFragment extends UiFragment<SignupProfileUpdateActivity> {
    /* access modifiers changed from: private */
    public DatePicker mBirthdayDatePicker;
    /* access modifiers changed from: private */
    public boolean mDateChangedTracked;
    /* access modifiers changed from: private */
    public Spinner mGenderSpinner;
    /* access modifiers changed from: private */
    public SignupGetEmailView mGetEmailView;
    /* access modifiers changed from: private */
    public int mInitialDay;
    /* access modifiers changed from: private */
    public int mInitialMonth;
    /* access modifiers changed from: private */
    public int mInitialYear;

    /* renamed from: com.contextlogic.wish.activity.signup.SignupProfileUpdateFragment$9 reason: invalid class name */
    static /* synthetic */ class AnonymousClass9 {
        static final /* synthetic */ int[] $SwitchMap$com$contextlogic$wish$api$service$standalone$LoginService$SignupFlowContext$SignupFlowType = new int[SignupFlowType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            /*
                com.contextlogic.wish.api.service.standalone.LoginService$SignupFlowContext$SignupFlowType[] r0 = com.contextlogic.wish.api.service.standalone.LoginService.SignupFlowContext.SignupFlowType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$contextlogic$wish$api$service$standalone$LoginService$SignupFlowContext$SignupFlowType = r0
                int[] r0 = $SwitchMap$com$contextlogic$wish$api$service$standalone$LoginService$SignupFlowContext$SignupFlowType     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.contextlogic.wish.api.service.standalone.LoginService$SignupFlowContext$SignupFlowType r1 = com.contextlogic.wish.api.service.standalone.LoginService.SignupFlowContext.SignupFlowType.Categories     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$com$contextlogic$wish$api$service$standalone$LoginService$SignupFlowContext$SignupFlowType     // Catch:{ NoSuchFieldError -> 0x001f }
                com.contextlogic.wish.api.service.standalone.LoginService$SignupFlowContext$SignupFlowType r1 = com.contextlogic.wish.api.service.standalone.LoginService.SignupFlowContext.SignupFlowType.FreeGifts     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = $SwitchMap$com$contextlogic$wish$api$service$standalone$LoginService$SignupFlowContext$SignupFlowType     // Catch:{ NoSuchFieldError -> 0x002a }
                com.contextlogic.wish.api.service.standalone.LoginService$SignupFlowContext$SignupFlowType r1 = com.contextlogic.wish.api.service.standalone.LoginService.SignupFlowContext.SignupFlowType.None     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.activity.signup.SignupProfileUpdateFragment.AnonymousClass9.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public int getLayoutResourceId() {
        return R.layout.signup_profile_update_fragment;
    }

    public void releaseImages() {
    }

    public void restoreImages() {
    }

    /* access modifiers changed from: protected */
    public void initialize() {
        WishAnalyticsLogger.trackFirstLoginSessionEvent(WishAnalyticsEvent.IMPRESSION_FIRST_PERSONALIZED);
        this.mBirthdayDatePicker = (DatePicker) findViewById(R.id.signup_profile_update_fragment_date_picker);
        Calendar instance = Calendar.getInstance();
        instance.set(1, 1900);
        instance.set(2, 1);
        instance.set(5, 1);
        this.mBirthdayDatePicker.setMinDate(instance.getTimeInMillis());
        this.mBirthdayDatePicker.setMaxDate(System.currentTimeMillis());
        this.mInitialMonth = this.mBirthdayDatePicker.getMonth();
        this.mInitialDay = this.mBirthdayDatePicker.getDayOfMonth();
        this.mInitialYear = this.mBirthdayDatePicker.getYear() - 19;
        this.mBirthdayDatePicker.init(this.mInitialYear, this.mInitialMonth, this.mInitialDay, new OnDateChangedListener() {
            public void onDateChanged(DatePicker datePicker, int i, int i2, int i3) {
                if (!SignupProfileUpdateFragment.this.mDateChangedTracked) {
                    WishAnalyticsLogger.trackFirstLoginSessionEvent(WishAnalyticsEvent.CLICK_FIRST_EDIT_BIRTHDAY);
                    SignupProfileUpdateFragment.this.mDateChangedTracked = true;
                }
            }
        });
        this.mGenderSpinner = (Spinner) findViewById(R.id.signup_profile_update_fragment_gender_spinner);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), R.layout.small_spinner_item, new String[]{getActivity().getString(R.string.gender_female), getActivity().getString(R.string.gender_male)});
        arrayAdapter.setDropDownViewResource(17367049);
        this.mGenderSpinner.setAdapter(arrayAdapter);
        String gender = ProfileDataCenter.getInstance().getGender();
        if (gender != null) {
            boolean equals = getString(R.string.default_gender).equals("male");
            char c = 65535;
            int hashCode = gender.hashCode();
            if (hashCode != -1278174388) {
                if (hashCode == 3343885 && gender.equals("male")) {
                    c = 0;
                }
            } else if (gender.equals("female")) {
                c = 1;
            }
            switch (c) {
                case 0:
                    this.mGenderSpinner.setSelection(1);
                    break;
                case 1:
                    this.mGenderSpinner.setSelection(0);
                    break;
                default:
                    this.mGenderSpinner.setSelection(equals ? 1 : 0);
                    break;
            }
        }
        this.mGenderSpinner.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 1) {
                    WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_SIGNUP_PRFFILE_UPDATE_GENDER_SPINNER);
                    WishAnalyticsLogger.trackFirstLoginSessionEvent(WishAnalyticsEvent.CLICK_FIRST_EDIT_GENDER);
                }
                return false;
            }
        });
        ((Button) findViewById(R.id.signup_profile_update_fragment_next_button)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SignupProfileUpdateFragment.this.withActivity(new ActivityTask<SignupProfileUpdateActivity>() {
                    public void performTask(SignupProfileUpdateActivity signupProfileUpdateActivity) {
                        if (signupProfileUpdateActivity.getSignupFlowContext() != null) {
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_SIGNUP_UPDATE_PROFILE_NEXT);
                        } else {
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_EMAIL_UPDATE_PROFILE_NEXT);
                        }
                        SignupProfileUpdateFragment.this.handleDone();
                    }
                });
            }
        });
        withActivity(new ActivityTask<SignupProfileUpdateActivity>() {
            public void performTask(SignupProfileUpdateActivity signupProfileUpdateActivity) {
                signupProfileUpdateActivity.getActionBarManager().clearRightActionBarItems();
                signupProfileUpdateActivity.getActionBarManager().addActionBarItem(new ActionBarItem(SignupProfileUpdateFragment.this.getString(R.string.skip), 2000, (Drawable) null));
            }
        });
    }

    public boolean handleActionBarItemSelected(int i) {
        if (i != 2000) {
            return super.handleActionBarItemSelected(i);
        }
        handleSkip();
        return true;
    }

    public void profileUpdateSuccess() {
        withActivity(new ActivityTask<SignupProfileUpdateActivity>() {
            public void performTask(SignupProfileUpdateActivity signupProfileUpdateActivity) {
                if (AnonymousClass9.$SwitchMap$com$contextlogic$wish$api$service$standalone$LoginService$SignupFlowContext$SignupFlowType[signupProfileUpdateActivity.getSignupFlowContext().signupFlowMode.ordinal()] == 1) {
                    Intent intent = new Intent();
                    intent.setClass(signupProfileUpdateActivity, SignupCategoryActivity.class);
                    signupProfileUpdateActivity.startActivity(intent);
                } else if (((SignupProfileUpdateActivity) SignupProfileUpdateFragment.this.getBaseActivity()).getLoginMode() == null || ((SignupProfileUpdateActivity) SignupProfileUpdateFragment.this.getBaseActivity()).getLoginMode() == LoginMode.EMAIL || !ExperimentDataCenter.getInstance().shouldSeeGetEmailOnBoardingScreen()) {
                    signupProfileUpdateActivity.startHomeActivity();
                } else {
                    SignupProfileUpdateFragment.this.showGetEmailView();
                }
            }
        });
    }

    private void handleSkip() {
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_SIGNUP_PROFILE_UPDATE_SKIP_DIALOG);
        WishAnalyticsLogger.trackFirstLoginSessionEvent(WishAnalyticsEvent.CLICK_FIRST_SKIP_PERSONALIZE);
        withServiceFragment(new ServiceTask<BaseActivity, SignupProfileUpdateServiceFragment>() {
            public void performTask(BaseActivity baseActivity, SignupProfileUpdateServiceFragment signupProfileUpdateServiceFragment) {
                signupProfileUpdateServiceFragment.handleSkip();
            }
        });
    }

    /* access modifiers changed from: private */
    public void handleDone() {
        WishAnalyticsLogger.trackFirstLoginSessionEvent(WishAnalyticsEvent.CLICK_FIRST_PERSONALIZE_DONE);
        withActivity(new ActivityTask<SignupProfileUpdateActivity>() {
            public void performTask(SignupProfileUpdateActivity signupProfileUpdateActivity) {
                KeyboardUtil.hideKeyboard((Fragment) SignupProfileUpdateFragment.this);
                SignupProfileUpdateFragment.this.withServiceFragment(new ServiceTask<BaseActivity, SignupProfileUpdateServiceFragment>() {
                    public void performTask(BaseActivity baseActivity, SignupProfileUpdateServiceFragment signupProfileUpdateServiceFragment) {
                        int month = SignupProfileUpdateFragment.this.mBirthdayDatePicker.getMonth() + 1;
                        int dayOfMonth = SignupProfileUpdateFragment.this.mBirthdayDatePicker.getDayOfMonth();
                        int year = SignupProfileUpdateFragment.this.mBirthdayDatePicker.getYear();
                        String str = SignupProfileUpdateFragment.this.mGenderSpinner.getSelectedItemPosition() == 0 ? "female" : "male";
                        if (!(SignupProfileUpdateFragment.this.mInitialDay == dayOfMonth && SignupProfileUpdateFragment.this.mInitialMonth + 1 == month && SignupProfileUpdateFragment.this.mInitialYear == year)) {
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_SIGNUP_PROFILE_UPDATE_BIRTHDAY_SPINNER);
                        }
                        signupProfileUpdateServiceFragment.updateProfile(dayOfMonth, month, year, str);
                    }
                });
            }
        });
    }

    public void showGetEmailView() {
        if (getBaseActivity() != null) {
            ActionBar supportActionBar = ((SignupProfileUpdateActivity) getBaseActivity()).getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.hide();
            }
            ScrollView scrollView = (ScrollView) findViewById(R.id.signup_update_profile_main_scrollview);
            scrollView.removeView((LinearLayout) findViewById(R.id.signup_update_profile_main_container));
            this.mGetEmailView = new SignupGetEmailView(getBaseActivity());
            this.mGetEmailView.setOnFinishClicked(new FooterManager() {
                public void handleFooterButtonClick() {
                    SignupProfileUpdateFragment.this.withServiceFragment(new ServiceTask<BaseActivity, SignupProfileUpdateServiceFragment>() {
                        public void performTask(BaseActivity baseActivity, SignupProfileUpdateServiceFragment signupProfileUpdateServiceFragment) {
                            String enteredEmail = SignupProfileUpdateFragment.this.mGetEmailView.getEnteredEmail();
                            if (!TextUtils.isEmpty(enteredEmail)) {
                                signupProfileUpdateServiceFragment.updateEmail(enteredEmail);
                                return;
                            }
                            SignupProfileUpdateFragment.this.showInvalidEmail(SignupProfileUpdateFragment.this.getString(R.string.please_enter_a_valid_email_address));
                        }
                    });
                }
            });
            scrollView.addView(this.mGetEmailView);
        }
    }

    public void showInvalidEmail(String str) {
        if (this.mGetEmailView != null) {
            this.mGetEmailView.showInvalidEmail(str);
        }
    }
}
