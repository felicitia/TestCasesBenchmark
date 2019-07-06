package com.contextlogic.wish.activity.signup.SignupCategory;

import android.os.Bundle;
import android.text.TextUtils;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.UiTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.api.datacenter.ProfileDataCenter;
import com.contextlogic.wish.api.model.WishSignupCategory;
import com.contextlogic.wish.api.service.ApiService.DefaultCodeFailureCallback;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.ApiService.DefaultSuccessCallback;
import com.contextlogic.wish.api.service.standalone.CategoryUnwishService;
import com.contextlogic.wish.api.service.standalone.CategoryWishService;
import com.contextlogic.wish.api.service.standalone.ChangeEmailSocialLoginService;
import com.contextlogic.wish.api.service.standalone.GetSignupCategoriesService;
import com.contextlogic.wish.api.service.standalone.GetSignupCategoriesService.SuccessCallback;
import java.util.ArrayList;

public class SignupCategoryServiceFragment extends ServiceFragment<SignupCategoryActivity> {
    private CategoryUnwishService mCategoryUnwishService;
    private CategoryWishService mCategoryWishService;
    private ChangeEmailSocialLoginService mChangeEmailService;
    private GetSignupCategoriesService mGetSignupCategoryService;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    /* access modifiers changed from: protected */
    public void initializeServices() {
        super.initializeServices();
        this.mGetSignupCategoryService = new GetSignupCategoriesService();
        this.mCategoryWishService = new CategoryWishService();
        this.mCategoryUnwishService = new CategoryUnwishService();
        this.mChangeEmailService = new ChangeEmailSocialLoginService();
    }

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
        super.cancelAllRequests();
        this.mGetSignupCategoryService.cancelAllRequests();
        this.mCategoryWishService.cancelAllRequests();
        this.mCategoryUnwishService.cancelAllRequests();
        this.mChangeEmailService.cancelAllRequests();
    }

    public void getSignupCategories() {
        this.mGetSignupCategoryService.requestService(new SuccessCallback() {
            public void onSuccess(final ArrayList<WishSignupCategory> arrayList) {
                SignupCategoryServiceFragment.this.getHandler().postDelayed(new Runnable() {
                    public void run() {
                        SignupCategoryServiceFragment.this.withUiFragment(new UiTask<BaseActivity, SignupCategoryFragment>() {
                            public void performTask(BaseActivity baseActivity, SignupCategoryFragment signupCategoryFragment) {
                                signupCategoryFragment.handleLoadingSuccess(arrayList);
                            }
                        }, "FragmentTagMainContent");
                    }
                }, 0);
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                SignupCategoryServiceFragment.this.getHandler().postDelayed(new Runnable() {
                    public void run() {
                        SignupCategoryServiceFragment.this.withUiFragment(new UiTask<BaseActivity, SignupCategoryFragment>() {
                            public void performTask(BaseActivity baseActivity, SignupCategoryFragment signupCategoryFragment) {
                                signupCategoryFragment.handleLoadingFailure();
                            }
                        }, "FragmentTagMainContent");
                    }
                }, 0);
            }
        });
    }

    public void wishForCategory(String str, final int i) {
        if (!this.mCategoryWishService.isPending(str)) {
            this.mCategoryWishService.requestService(str, true, new DefaultSuccessCallback() {
                public void onSuccess() {
                    SignupCategoryServiceFragment.this.wishSuccess(i);
                }
            }, new DefaultFailureCallback() {
                public void onFailure(String str) {
                    SignupCategoryServiceFragment.this.wishOrUnwishFailure();
                }
            });
            withUiFragment(new UiTask<BaseActivity, SignupCategoryFragment>() {
                public void performTask(BaseActivity baseActivity, SignupCategoryFragment signupCategoryFragment) {
                    signupCategoryFragment.refreshWishStates();
                }
            }, "FragmentTagMainContent");
        }
    }

    public void unwishForCategory(String str, final int i) {
        if (!this.mCategoryUnwishService.isPending(str)) {
            this.mCategoryUnwishService.requestService(str, new DefaultSuccessCallback() {
                public void onSuccess() {
                    SignupCategoryServiceFragment.this.unWishSuccess(i);
                }
            }, new DefaultFailureCallback() {
                public void onFailure(String str) {
                    SignupCategoryServiceFragment.this.wishOrUnwishFailure();
                }
            });
            withUiFragment(new UiTask<BaseActivity, SignupCategoryFragment>() {
                public void performTask(BaseActivity baseActivity, SignupCategoryFragment signupCategoryFragment) {
                    signupCategoryFragment.refreshWishStates();
                }
            }, "FragmentTagMainContent");
        }
    }

    public boolean isWishPending(String str) {
        return this.mCategoryWishService.isPending(str) || this.mCategoryUnwishService.isPending(str);
    }

    public int getPendingUnwishRequestCount() {
        return this.mCategoryUnwishService.pendingRequestCount();
    }

    /* access modifiers changed from: private */
    public void wishSuccess(final int i) {
        withUiFragment(new UiTask<BaseActivity, SignupCategoryFragment>() {
            public void performTask(BaseActivity baseActivity, SignupCategoryFragment signupCategoryFragment) {
                signupCategoryFragment.wishSuccess(i);
            }
        }, "FragmentTagMainContent");
    }

    /* access modifiers changed from: private */
    public void unWishSuccess(final int i) {
        withUiFragment(new UiTask<BaseActivity, SignupCategoryFragment>() {
            public void performTask(BaseActivity baseActivity, SignupCategoryFragment signupCategoryFragment) {
                signupCategoryFragment.unwishSuccess(i);
            }
        }, "FragmentTagMainContent");
    }

    public void updateEmail(String str) {
        this.mChangeEmailService.requestService(str, new ChangeEmailSocialLoginService.SuccessCallback() {
            public void onSuccess(String str) {
                ProfileDataCenter.getInstance().refresh();
                SignupCategoryServiceFragment.this.withUiFragment(new UiTask<BaseActivity, SignupCategoryFragment>() {
                    public void performTask(BaseActivity baseActivity, SignupCategoryFragment signupCategoryFragment) {
                        signupCategoryFragment.startBrowseActivity();
                    }
                });
            }
        }, new DefaultCodeFailureCallback() {
            public void onFailure(final String str, int i) {
                SignupCategoryServiceFragment.this.withUiFragment(new UiTask<BaseActivity, SignupCategoryFragment>() {
                    public void performTask(BaseActivity baseActivity, SignupCategoryFragment signupCategoryFragment) {
                        if (TextUtils.isEmpty(str)) {
                            signupCategoryFragment.showInvalidEmail(SignupCategoryServiceFragment.this.getString(R.string.error_changing_email));
                        } else {
                            signupCategoryFragment.showInvalidEmail(str);
                        }
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public void wishOrUnwishFailure() {
        withUiFragment(new UiTask<BaseActivity, SignupCategoryFragment>() {
            public void performTask(BaseActivity baseActivity, SignupCategoryFragment signupCategoryFragment) {
                signupCategoryFragment.wishOrUnwishFailure();
            }
        }, "FragmentTagMainContent");
    }
}
