package com.contextlogic.wish.activity.signup.redesign;

import android.os.Bundle;
import android.text.TextUtils;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.UiTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.datacenter.ProfileDataCenter;
import com.contextlogic.wish.api.model.WishSignupFlowCategory;
import com.contextlogic.wish.api.service.ApiService.DefaultCodeFailureCallback;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.ApiService.DefaultSuccessCallback;
import com.contextlogic.wish.api.service.compound.AuthenticationService.LoginMode;
import com.contextlogic.wish.api.service.standalone.ChangeEmailSocialLoginService;
import com.contextlogic.wish.api.service.standalone.ChangeEmailSocialLoginService.SuccessCallback;
import com.contextlogic.wish.api.service.standalone.GetProfileService;
import com.contextlogic.wish.api.service.standalone.GetSignupFlowCategoriesService;
import com.contextlogic.wish.api.service.standalone.ParseImageChooserIntentService;
import com.contextlogic.wish.api.service.standalone.UpdateProfileService;
import com.contextlogic.wish.api.service.standalone.UploadProfileImageService;
import com.contextlogic.wish.api.service.standalone.UploadSignupFlowInfoService;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import java.util.ArrayList;

public class SignupFlowServiceFragment extends ServiceFragment<SignupFlowActivity> {
    private ChangeEmailSocialLoginService mChangeEmailService;
    private GetProfileService mGetProfileService;
    private GetSignupFlowCategoriesService mGetSingupFlowCategoriesService;
    private ParseImageChooserIntentService mParseImageChooserIntentService;
    private UpdateProfileService mUpdateProfileService;
    private UploadProfileImageService mUploadProfileImageService;
    private UploadSignupFlowInfoService mUploadSignupFlowInfoService;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    /* access modifiers changed from: protected */
    public void initializeServices() {
        super.initializeServices();
        this.mUploadProfileImageService = new UploadProfileImageService();
        this.mParseImageChooserIntentService = new ParseImageChooserIntentService();
        this.mUpdateProfileService = new UpdateProfileService();
        this.mGetProfileService = new GetProfileService();
        this.mGetSingupFlowCategoriesService = new GetSignupFlowCategoriesService();
        this.mUploadSignupFlowInfoService = new UploadSignupFlowInfoService();
        this.mChangeEmailService = new ChangeEmailSocialLoginService();
    }

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
        super.cancelAllRequests();
        this.mUploadProfileImageService.cancelAllRequests();
        this.mParseImageChooserIntentService.cancelAllRequests();
        this.mUpdateProfileService.cancelAllRequests();
        this.mGetProfileService.cancelAllRequests();
        this.mGetSingupFlowCategoriesService.cancelAllRequests();
        this.mUploadSignupFlowInfoService.cancelAllRequests();
        this.mChangeEmailService.cancelAllRequests();
    }

    public void updateEmail(String str) {
        this.mChangeEmailService.requestService(str, new SuccessCallback() {
            public void onSuccess(String str) {
                ProfileDataCenter.getInstance().refresh();
                SignupFlowServiceFragment.this.withUiFragment(new UiTask<BaseActivity, SignupFlowFragment>() {
                    public void performTask(BaseActivity baseActivity, SignupFlowFragment signupFlowFragment) {
                        signupFlowFragment.finishOnboarding();
                    }
                });
            }
        }, new DefaultCodeFailureCallback() {
            public void onFailure(final String str, int i) {
                SignupFlowServiceFragment.this.withUiFragment(new UiTask<BaseActivity, SignupFlowFragment>() {
                    public void performTask(BaseActivity baseActivity, SignupFlowFragment signupFlowFragment) {
                        if (TextUtils.isEmpty(str)) {
                            signupFlowFragment.showInvalidEmail(SignupFlowServiceFragment.this.getString(R.string.error_changing_email));
                        } else {
                            signupFlowFragment.showInvalidEmail(str);
                        }
                    }
                });
            }
        });
    }

    public void updateProfile(String str, String str2, int i, int i2, int i3, String str3, boolean z) {
        this.mUpdateProfileService.requestService(str, str2, i, i2, i3, str3, z, new DefaultSuccessCallback() {
            public void onSuccess() {
                SignupFlowServiceFragment.this.withUiFragment(new UiTask<BaseActivity, SignupFlowFragment>() {
                    public void performTask(BaseActivity baseActivity, SignupFlowFragment signupFlowFragment) {
                        SignupFlowServiceFragment.this.withUiFragment(new UiTask<BaseActivity, SignupFlowFragment>() {
                            public void performTask(BaseActivity baseActivity, SignupFlowFragment signupFlowFragment) {
                                if (((SignupFlowActivity) SignupFlowServiceFragment.this.getBaseActivity()).getLoginMode() == null || ((SignupFlowActivity) SignupFlowServiceFragment.this.getBaseActivity()).getLoginMode() == LoginMode.EMAIL || !ExperimentDataCenter.getInstance().shouldSeeGetEmailOnBoardingScreen()) {
                                    signupFlowFragment.finishOnboarding();
                                } else {
                                    signupFlowFragment.showEmailView();
                                }
                            }
                        });
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(final String str) {
                SignupFlowServiceFragment.this.withActivity(new ActivityTask<SignupFlowActivity>() {
                    public void performTask(SignupFlowActivity signupFlowActivity) {
                        String str;
                        if (str != null) {
                            str = str;
                        } else {
                            str = signupFlowActivity.getString(R.string.error_updating_your_profile);
                        }
                        signupFlowActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(str));
                    }
                });
            }
        });
    }

    public void updateProfile(String str, String str2, String str3, boolean z) {
        updateProfile(str, str2, -1, -1, -1, str3, z);
    }

    public void getCategories(String str) {
        this.mGetSingupFlowCategoriesService.requestService(str, new GetSignupFlowCategoriesService.SuccessCallback() {
            public void onSuccess(final ArrayList<WishSignupFlowCategory> arrayList) {
                SignupFlowServiceFragment.this.withUiFragment(new UiTask<BaseActivity, SignupFlowFragment>() {
                    public void performTask(BaseActivity baseActivity, SignupFlowFragment signupFlowFragment) {
                        signupFlowFragment.handleSignupCategoriesLoadSuccess(arrayList);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultFailureCallback() {
            public void onFailure(final String str) {
                SignupFlowServiceFragment.this.withUiFragment(new UiTask<BaseActivity, SignupFlowFragment>() {
                    public void performTask(BaseActivity baseActivity, SignupFlowFragment signupFlowFragment) {
                        String str;
                        if (str != null) {
                            str = str;
                        } else {
                            str = baseActivity.getString(R.string.error_updating_your_profile);
                        }
                        baseActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(str));
                    }
                }, "FragmentTagMainContent");
            }
        });
    }

    public void addCategories(ArrayList<String> arrayList) {
        this.mUploadSignupFlowInfoService.requestService(arrayList, null, new DefaultSuccessCallback() {
            public void onSuccess() {
                SignupFlowServiceFragment.this.withUiFragment(new UiTask<BaseActivity, SignupFlowFragment>() {
                    public void performTask(BaseActivity baseActivity, SignupFlowFragment signupFlowFragment) {
                        if (((SignupFlowActivity) SignupFlowServiceFragment.this.getBaseActivity()).getLoginMode() == null || ((SignupFlowActivity) SignupFlowServiceFragment.this.getBaseActivity()).getLoginMode() == LoginMode.EMAIL || !ExperimentDataCenter.getInstance().shouldSeeGetEmailOnBoardingScreen()) {
                            signupFlowFragment.finishOnboarding();
                        } else {
                            signupFlowFragment.showEmailView();
                        }
                    }
                });
            }
        }, new DefaultFailureCallback() {
            public void onFailure(final String str) {
                SignupFlowServiceFragment.this.withUiFragment(new UiTask<BaseActivity, SignupFlowFragment>() {
                    public void performTask(BaseActivity baseActivity, SignupFlowFragment signupFlowFragment) {
                        String str;
                        if (str != null) {
                            str = str;
                        } else {
                            str = baseActivity.getString(R.string.error_updating_your_profile);
                        }
                        baseActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(str));
                    }
                }, "FragmentTagMainContent");
            }
        });
    }
}
