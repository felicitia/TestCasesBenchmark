package com.contextlogic.wish.activity.signup;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.UiTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.activity.signup.SignupCategory.SignupCategoryActivity;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.datacenter.ProfileDataCenter;
import com.contextlogic.wish.api.service.ApiService.DefaultCodeFailureCallback;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.ApiService.DefaultSuccessCallback;
import com.contextlogic.wish.api.service.compound.AuthenticationService.LoginMode;
import com.contextlogic.wish.api.service.standalone.ChangeEmailSocialLoginService;
import com.contextlogic.wish.api.service.standalone.ChangeEmailSocialLoginService.SuccessCallback;
import com.contextlogic.wish.api.service.standalone.GetUserStatusService;
import com.contextlogic.wish.api.service.standalone.LoginService.SignupFlowContext.SignupFlowType;
import com.contextlogic.wish.api.service.standalone.UpdateProfileService;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.dialog.BaseDialogFragment.BaseDialogCallback;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import com.contextlogic.wish.util.IntentUtil;

public class SignupProfileUpdateServiceFragment extends ServiceFragment<SignupProfileUpdateActivity> {
    private ChangeEmailSocialLoginService mChangeEmailService;
    /* access modifiers changed from: private */
    public GetUserStatusService mGetUserStatusService;
    private UpdateProfileService mUpdateProfileService;

    /* renamed from: com.contextlogic.wish.activity.signup.SignupProfileUpdateServiceFragment$8 reason: invalid class name */
    static /* synthetic */ class AnonymousClass8 {
        static final /* synthetic */ int[] $SwitchMap$com$contextlogic$wish$api$service$standalone$LoginService$SignupFlowContext$SignupFlowType = new int[SignupFlowType.values().length];

        static {
            try {
                $SwitchMap$com$contextlogic$wish$api$service$standalone$LoginService$SignupFlowContext$SignupFlowType[SignupFlowType.Categories.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    /* access modifiers changed from: protected */
    public void initializeServices() {
        super.initializeServices();
        this.mUpdateProfileService = new UpdateProfileService();
        this.mGetUserStatusService = new GetUserStatusService();
        this.mChangeEmailService = new ChangeEmailSocialLoginService();
    }

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
        super.cancelAllRequests();
        this.mUpdateProfileService.cancelAllRequests();
        this.mGetUserStatusService.cancelAllRequests();
        this.mChangeEmailService.cancelAllRequests();
    }

    public void updateProfile(int i, int i2, int i3, String str) {
        this.mUpdateProfileService.requestService(null, null, i, i2, i3, str, false, new DefaultSuccessCallback() {
            public void onSuccess() {
                ProfileDataCenter.getInstance().refresh();
                SignupProfileUpdateServiceFragment.this.mGetUserStatusService.requestService(new DefaultSuccessCallback() {
                    public void onSuccess() {
                        SignupProfileUpdateServiceFragment.this.withUiFragment(new UiTask<BaseActivity, SignupProfileUpdateFragment>() {
                            public void performTask(BaseActivity baseActivity, SignupProfileUpdateFragment signupProfileUpdateFragment) {
                                SignupProfileUpdateServiceFragment.this.showSuccessDialog();
                            }
                        }, "FragmentTagMainContent");
                    }
                }, new DefaultFailureCallback() {
                    public void onFailure(String str) {
                        SignupProfileUpdateServiceFragment.this.withUiFragment(new UiTask<BaseActivity, SignupProfileUpdateFragment>() {
                            public void performTask(BaseActivity baseActivity, SignupProfileUpdateFragment signupProfileUpdateFragment) {
                                signupProfileUpdateFragment.profileUpdateSuccess();
                            }
                        }, "FragmentTagMainContent");
                    }
                });
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                if (str == null) {
                    str = WishApplication.getInstance().getString(R.string.error_updating_your_profile);
                }
                SignupProfileUpdateServiceFragment.this.showErrorDialog(str);
            }
        });
    }

    public void showSuccessDialog() {
        withActivity(new ActivityTask<SignupProfileUpdateActivity>() {
            public void performTask(SignupProfileUpdateActivity signupProfileUpdateActivity) {
                Intent intent = new Intent();
                if (signupProfileUpdateActivity.getSignupFlowContext() == null || signupProfileUpdateActivity.getSignupFlowContext().signupFlowMode != SignupFlowType.FreeGifts) {
                    if (signupProfileUpdateActivity.getSignupFlowContext() == null || signupProfileUpdateActivity.getSignupFlowContext().signupFlowMode != SignupFlowType.Categories) {
                        intent.putExtra("ArgShowSuccess", true);
                        IntentUtil.putParcelableExtra(intent, "ExtraLoginMode", signupProfileUpdateActivity.getLoginMode());
                        intent.setClass(signupProfileUpdateActivity, SignupCategoryActivity.class);
                        signupProfileUpdateActivity.startActivity(intent, true);
                        return;
                    }
                    intent.putExtra("ArgShowSuccess", true);
                    IntentUtil.putParcelableExtra(intent, "ExtraLoginMode", signupProfileUpdateActivity.getLoginMode());
                    intent.setClass(signupProfileUpdateActivity, SignupCategoryActivity.class);
                    signupProfileUpdateActivity.startActivity(intent, true);
                } else if (!ExperimentDataCenter.getInstance().shouldSeeGetEmailOnBoardingScreen() || signupProfileUpdateActivity.getLoginMode() == null || signupProfileUpdateActivity.getLoginMode() == LoginMode.EMAIL) {
                    signupProfileUpdateActivity.startHomeActivity();
                } else {
                    SignupProfileUpdateServiceFragment.this.withUiFragment(new UiTask<BaseActivity, SignupProfileUpdateFragment>() {
                        public void performTask(BaseActivity baseActivity, SignupProfileUpdateFragment signupProfileUpdateFragment) {
                            signupProfileUpdateFragment.showGetEmailView();
                        }
                    });
                }
            }
        });
    }

    public void showErrorDialog(final String str) {
        withActivity(new ActivityTask<SignupProfileUpdateActivity>() {
            public void performTask(SignupProfileUpdateActivity signupProfileUpdateActivity) {
                signupProfileUpdateActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(str), new BaseDialogCallback() {
                    public void onCancel(BaseDialogFragment baseDialogFragment) {
                    }

                    public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                    }
                });
            }
        });
    }

    public void updateEmail(String str) {
        this.mChangeEmailService.requestService(str, new SuccessCallback() {
            public void onSuccess(String str) {
                ProfileDataCenter.getInstance().refresh();
                SignupProfileUpdateServiceFragment.this.withUiFragment(new UiTask<BaseActivity, SignupProfileUpdateFragment>() {
                    public void performTask(BaseActivity baseActivity, SignupProfileUpdateFragment signupProfileUpdateFragment) {
                        baseActivity.startHomeActivity();
                    }
                });
            }
        }, new DefaultCodeFailureCallback() {
            public void onFailure(final String str, int i) {
                SignupProfileUpdateServiceFragment.this.withUiFragment(new UiTask<BaseActivity, SignupProfileUpdateFragment>() {
                    public void performTask(BaseActivity baseActivity, SignupProfileUpdateFragment signupProfileUpdateFragment) {
                        if (TextUtils.isEmpty(str)) {
                            signupProfileUpdateFragment.showInvalidEmail(SignupProfileUpdateServiceFragment.this.getString(R.string.error_changing_email));
                        } else {
                            signupProfileUpdateFragment.showInvalidEmail(str);
                        }
                    }
                });
            }
        });
    }

    public void handleSkip() {
        WishAnalyticsLogger.trackFirstLoginSessionEvent(WishAnalyticsEvent.CLICK_FIRST_SKIP_PERSONALIZE);
        withActivity(new ActivityTask<SignupProfileUpdateActivity>() {
            public void performTask(final SignupProfileUpdateActivity signupProfileUpdateActivity) {
                signupProfileUpdateActivity.startDialog(MultiButtonDialogFragment.createMultiButtonYesNoDialog(SignupProfileUpdateServiceFragment.this.getString(R.string.are_you_sure), SignupProfileUpdateServiceFragment.this.getString(R.string.are_you_sure_no_personalize)), new BaseDialogCallback() {
                    public void onCancel(BaseDialogFragment baseDialogFragment) {
                        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_SIGNUP_PROFILE_UPDATE_SKIP_STAY);
                    }

                    public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                        if (i == 1) {
                            WishAnalyticsLogger.trackFirstLoginSessionEvent(WishAnalyticsEvent.CLICK_FIRST_SKIP_PERSONALIZE_CONFIRMED);
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_SIGNUP_PROFILE_UPDATE_SKIP_YES);
                            if (AnonymousClass8.$SwitchMap$com$contextlogic$wish$api$service$standalone$LoginService$SignupFlowContext$SignupFlowType[signupProfileUpdateActivity.getSignupFlowContext().signupFlowMode.ordinal()] == 1) {
                                Intent intent = new Intent();
                                intent.setClass(signupProfileUpdateActivity, SignupCategoryActivity.class);
                                signupProfileUpdateActivity.startActivity(intent, true);
                            } else if (((SignupProfileUpdateActivity) SignupProfileUpdateServiceFragment.this.getBaseActivity()).getLoginMode() == null || ((SignupProfileUpdateActivity) SignupProfileUpdateServiceFragment.this.getBaseActivity()).getLoginMode() == LoginMode.EMAIL || !ExperimentDataCenter.getInstance().shouldSeeGetEmailOnBoardingScreen()) {
                                signupProfileUpdateActivity.startHomeActivity();
                            } else {
                                SignupProfileUpdateServiceFragment.this.withUiFragment(new UiTask<BaseActivity, SignupProfileUpdateFragment>() {
                                    public void performTask(BaseActivity baseActivity, SignupProfileUpdateFragment signupProfileUpdateFragment) {
                                        signupProfileUpdateFragment.showGetEmailView();
                                    }
                                });
                            }
                        }
                    }
                });
            }
        });
    }
}
