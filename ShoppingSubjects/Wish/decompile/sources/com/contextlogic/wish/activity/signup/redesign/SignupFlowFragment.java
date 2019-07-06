package com.contextlogic.wish.activity.signup.redesign;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.FrameLayout;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.activity.browse.BrowseActivity;
import com.contextlogic.wish.activity.signup.SignupFreeGift.SignupFreeGiftActivity;
import com.contextlogic.wish.activity.signup.redesign.BirthdayPicker.BirthdayPickerView;
import com.contextlogic.wish.activity.signup.redesign.SelectCategory.SelectCategoryView;
import com.contextlogic.wish.activity.signup.redesign.SelectGender.SelectGenderView;
import com.contextlogic.wish.activity.signup.redesign.SignupFlowFooterView.FooterManager;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.model.WishSignupFlowCategory;
import com.contextlogic.wish.api.service.standalone.LoginService.SignupFlowContext.SignupFlowType;
import com.contextlogic.wish.util.IntentUtil;
import java.util.ArrayList;

public class SignupFlowFragment extends UiFragment<SignupFlowActivity> {
    public static int PAGER_COUNT = 6;
    public static String SAVED_STATE_INDEX = "SavedStateIndex";
    public static String SAVED_STATE_PAGER_PREFIX = "SavedStatePager_";
    /* access modifiers changed from: private */
    public SignupGetEmailView mGetEmailView;
    /* access modifiers changed from: private */
    public FrameLayout mLayout;
    /* access modifiers changed from: private */
    public SignupFlowBaseView mOnboardingView;

    /* renamed from: com.contextlogic.wish.activity.signup.redesign.SignupFlowFragment$9 reason: invalid class name */
    static /* synthetic */ class AnonymousClass9 {
        static final /* synthetic */ int[] $SwitchMap$com$contextlogic$wish$api$service$standalone$LoginService$SignupFlowContext$SignupFlowType = new int[SignupFlowType.values().length];

        static {
            try {
                $SwitchMap$com$contextlogic$wish$api$service$standalone$LoginService$SignupFlowContext$SignupFlowType[SignupFlowType.FreeGifts.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public enum SignupPage {
        UploadProfilePhoto,
        SelectGender,
        RankFilter,
        SelectCategory,
        BirthdayPicker,
        FinishSignup,
        Unknown;

        public static SignupPage fromInt(int i) {
            switch (i) {
                case 0:
                    return UploadProfilePhoto;
                case 1:
                    return SelectGender;
                case 2:
                    return RankFilter;
                case 3:
                    return SelectCategory;
                case 4:
                    return BirthdayPicker;
                case 5:
                    return FinishSignup;
                default:
                    return Unknown;
            }
        }
    }

    public int getLayoutResourceId() {
        return R.layout.redesign_signup_flow_fragment;
    }

    public boolean onBackPressed() {
        return true;
    }

    public void initialize() {
        this.mLayout = (FrameLayout) findViewById(R.id.redesign_signup_flow_fragment_container);
        withActivity(new ActivityTask<SignupFlowActivity>() {
            public void performTask(SignupFlowActivity signupFlowActivity) {
                if (ExperimentDataCenter.getInstance().shouldSeeOnboardingGender()) {
                    SignupFlowFragment.this.mOnboardingView = new SelectGenderView(signupFlowActivity, SignupFlowFragment.this);
                } else if (ExperimentDataCenter.getInstance().shouldSeeOnboardingInterests()) {
                    SignupFlowFragment.this.mOnboardingView = new SelectCategoryView(signupFlowActivity, SignupFlowFragment.this);
                } else if (ExperimentDataCenter.getInstance().shouldSeeOnboardingBirthday()) {
                    SignupFlowFragment.this.mOnboardingView = new BirthdayPickerView(signupFlowActivity, SignupFlowFragment.this);
                }
                if (SignupFlowFragment.this.mOnboardingView == null) {
                    if (ExperimentDataCenter.getInstance().shouldSeeGetEmailOnBoardingScreen()) {
                        SignupFlowFragment.this.mGetEmailView = new SignupGetEmailView(signupFlowActivity);
                        SignupFlowFragment.this.mGetEmailView.setOnFinishClicked(new FooterManager() {
                            public void handleFooterButtonClick() {
                                SignupFlowFragment.this.withServiceFragment(new ServiceTask<BaseActivity, SignupFlowServiceFragment>() {
                                    public void performTask(BaseActivity baseActivity, SignupFlowServiceFragment signupFlowServiceFragment) {
                                        String enteredEmail = SignupFlowFragment.this.mGetEmailView.getEnteredEmail();
                                        if (!TextUtils.isEmpty(enteredEmail)) {
                                            SignupFlowFragment.this.updateEmail(enteredEmail);
                                            return;
                                        }
                                        SignupFlowFragment.this.showInvalidEmail(SignupFlowFragment.this.getString(R.string.please_enter_a_valid_email_address));
                                    }
                                });
                            }
                        });
                        SignupFlowFragment.this.mLayout.addView(SignupFlowFragment.this.mGetEmailView);
                        signupFlowActivity.getSupportActionBar().hide();
                        signupFlowActivity.hideLoadingDialog();
                    } else {
                        signupFlowActivity.finish();
                    }
                    return;
                }
                SignupFlowFragment.this.mLayout.addView(SignupFlowFragment.this.mOnboardingView);
                signupFlowActivity.getSupportActionBar().hide();
                signupFlowActivity.hideLoadingDialog();
            }
        });
    }

    public Bundle getSavedInstanceState(int i) {
        if (getSavedInstanceState() != null) {
            return getSavedInstanceState().getBundle(getPagerKey(i));
        }
        return null;
    }

    public void handleSaveInstanceState(Bundle bundle) {
        if (this.mOnboardingView != null) {
            this.mOnboardingView.handleSaveInstanceState(bundle);
        }
    }

    public String getPagerKey(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(SAVED_STATE_PAGER_PREFIX);
        sb.append(SignupPage.fromInt(i).name());
        return sb.toString();
    }

    public void restoreImages() {
        if (this.mOnboardingView != null) {
            this.mOnboardingView.restoreImages();
        }
    }

    public void releaseImages() {
        if (this.mOnboardingView != null) {
            this.mOnboardingView.releaseImages();
        }
    }

    /* access modifiers changed from: protected */
    public void handleResume() {
        ((SignupFlowActivity) getBaseActivity()).getActionBarManager().getActionBarDrawerToggle().setHomeAsUpIndicator((int) R.drawable.blue_back_button_24);
        ((SignupFlowActivity) getBaseActivity()).getActionBarManager().setBadgeVisible(false);
    }

    public void handleSignupCategoriesLoadSuccess(ArrayList<WishSignupFlowCategory> arrayList) {
        if (this.mOnboardingView != null && (this.mOnboardingView instanceof SelectCategoryView)) {
            ((SelectCategoryView) this.mOnboardingView).handleCategoriesLoadSuccess(arrayList);
        }
    }

    public void uploadSelectedSigupFlowCategories(final ArrayList<String> arrayList) {
        withServiceFragment(new ServiceTask<BaseActivity, SignupFlowServiceFragment>() {
            public void performTask(BaseActivity baseActivity, SignupFlowServiceFragment signupFlowServiceFragment) {
                signupFlowServiceFragment.addCategories(arrayList);
            }
        });
    }

    public void uploadProfileGender(final String str) {
        if (this.mOnboardingView != null && (this.mOnboardingView instanceof SelectGenderView) && !str.equals("neutral")) {
            withServiceFragment(new ServiceTask<BaseActivity, SignupFlowServiceFragment>() {
                public void performTask(BaseActivity baseActivity, SignupFlowServiceFragment signupFlowServiceFragment) {
                    signupFlowServiceFragment.updateProfile(null, null, str, false);
                }
            });
        }
    }

    public void uploadProfileBirthday(final int i, final int i2, final int i3) {
        withServiceFragment(new ServiceTask<BaseActivity, SignupFlowServiceFragment>() {
            public void performTask(BaseActivity baseActivity, SignupFlowServiceFragment signupFlowServiceFragment) {
                signupFlowServiceFragment.updateProfile(null, null, i, i2, i3, null, false);
            }
        });
    }

    public void showEmailView() {
        this.mLayout.removeView(this.mOnboardingView);
        this.mGetEmailView = new SignupGetEmailView(getBaseActivity());
        this.mGetEmailView.setOnFinishClicked(new FooterManager() {
            public void handleFooterButtonClick() {
                SignupFlowFragment.this.withServiceFragment(new ServiceTask<BaseActivity, SignupFlowServiceFragment>() {
                    public void performTask(BaseActivity baseActivity, SignupFlowServiceFragment signupFlowServiceFragment) {
                        String enteredEmail = SignupFlowFragment.this.mGetEmailView.getEnteredEmail();
                        if (!TextUtils.isEmpty(enteredEmail)) {
                            SignupFlowFragment.this.updateEmail(enteredEmail);
                            return;
                        }
                        SignupFlowFragment.this.showInvalidEmail(SignupFlowFragment.this.getString(R.string.please_enter_a_valid_email_address));
                    }
                });
            }
        });
        this.mLayout.addView(this.mGetEmailView);
    }

    public void showInvalidEmail(String str) {
        if (this.mGetEmailView != null) {
            this.mGetEmailView.showInvalidEmail(str);
        }
    }

    public void updateEmail(final String str) {
        withServiceFragment(new ServiceTask<BaseActivity, SignupFlowServiceFragment>() {
            public void performTask(BaseActivity baseActivity, SignupFlowServiceFragment signupFlowServiceFragment) {
                signupFlowServiceFragment.updateEmail(str);
            }
        });
    }

    public void finishOnboarding() {
        if (ExperimentDataCenter.getInstance().shouldSeeOnboardingGender()) {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_REDESIGN_SIGNUP_GENDER_NEXT);
        } else if (ExperimentDataCenter.getInstance().shouldSeeOnboardingInterests()) {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_REDESIGN_SIGNUP_INTERESTS_NEXT);
        } else if (ExperimentDataCenter.getInstance().shouldSeeOnboardingBirthday()) {
            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_MOBILE_REDESIGN_SIGNUP_BIRTHDAY_NEXT);
        }
        withActivity(new ActivityTask<SignupFlowActivity>() {
            public void performTask(SignupFlowActivity signupFlowActivity) {
                if (AnonymousClass9.$SwitchMap$com$contextlogic$wish$api$service$standalone$LoginService$SignupFlowContext$SignupFlowType[signupFlowActivity.getSignupFlowContext().signupFlowMode.ordinal()] != 1) {
                    signupFlowActivity.startHomeActivity();
                    return;
                }
                Intent intent = new Intent();
                IntentUtil.putParcelableExtra(intent, "ArgSignupFlowContext", signupFlowActivity.getSignupFlowContext());
                intent.setClass(signupFlowActivity, SignupFreeGiftActivity.class);
                Intent intent2 = new Intent();
                intent2.setClass(signupFlowActivity, BrowseActivity.class);
                intent2.putExtra("ExtraPlaceholderMode", true);
                IntentUtil.putParcelableExtra(intent2, "ExtraFollowUpIntent", intent);
                signupFlowActivity.startActivity(intent2, true);
            }
        });
    }
}
