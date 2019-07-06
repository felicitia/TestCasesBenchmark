package com.contextlogic.wish.activity.login.forgotpassword;

import android.os.Bundle;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.standalone.ResetPasswordService;
import com.contextlogic.wish.api.service.standalone.ResetPasswordService.SuccessCallback;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.dialog.BaseDialogFragment.BaseDialogCallback;
import com.contextlogic.wish.dialog.bottomsheet.SuccessBottomSheetDialog;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice.BackgroundType;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice.ChoiceType;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment.MultiButtonDialogFragmentBuilder;
import java.util.ArrayList;

public class ForgotPasswordServiceFragment extends ServiceFragment<ForgotPasswordActivity> {
    private ResetPasswordService mResetPasswordService;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    /* access modifiers changed from: protected */
    public void initializeServices() {
        super.initializeServices();
        this.mResetPasswordService = new ResetPasswordService();
    }

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
        super.cancelAllRequests();
        this.mResetPasswordService.cancelAllRequests();
    }

    public void resetPassword(final String str) {
        withActivity(new ActivityTask<ForgotPasswordActivity>() {
            public void performTask(ForgotPasswordActivity forgotPasswordActivity) {
                forgotPasswordActivity.showLoadingDialog();
            }
        });
        this.mResetPasswordService.requestService(str, new SuccessCallback() {
            public void onSuccess(final String str) {
                ForgotPasswordServiceFragment.this.withActivity(new ActivityTask<ForgotPasswordActivity>() {
                    public void performTask(ForgotPasswordActivity forgotPasswordActivity) {
                        BaseDialogFragment baseDialogFragment;
                        forgotPasswordActivity.hideLoadingDialog();
                        if (ExperimentDataCenter.getInstance().shouldSeeNewSignUpScreen()) {
                            SuccessBottomSheetDialog.create(forgotPasswordActivity).setTitle(ForgotPasswordServiceFragment.this.getResources().getString(R.string.password_rest_link_sent)).setMessage(String.format(ForgotPasswordServiceFragment.this.getResources().getString(R.string.password_reset_link_sent_to_email), new Object[]{str})).autoDismiss().show();
                            return;
                        }
                        if (ExperimentDataCenter.getInstance().showNewSignupFlow()) {
                            baseDialogFragment = ForgotPasswordServiceFragment.this.createDialog(forgotPasswordActivity.getString(R.string.email_is_on_its_way), str);
                        } else {
                            baseDialogFragment = MultiButtonDialogFragment.createMultiButtonOkDialog(forgotPasswordActivity.getString(R.string.password_reset), str);
                        }
                        forgotPasswordActivity.startDialog(baseDialogFragment, new BaseDialogCallback() {
                            public void onCancel(BaseDialogFragment baseDialogFragment) {
                                ForgotPasswordServiceFragment.this.withActivity(new ActivityTask<ForgotPasswordActivity>() {
                                    public void performTask(ForgotPasswordActivity forgotPasswordActivity) {
                                        forgotPasswordActivity.finishActivity();
                                    }
                                });
                            }

                            public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                                ForgotPasswordServiceFragment.this.withActivity(new ActivityTask<ForgotPasswordActivity>() {
                                    public void performTask(ForgotPasswordActivity forgotPasswordActivity) {
                                        forgotPasswordActivity.finishActivity();
                                    }
                                });
                            }
                        });
                    }
                });
            }
        }, new DefaultFailureCallback() {
            public void onFailure(final String str) {
                ForgotPasswordServiceFragment.this.withActivity(new ActivityTask<ForgotPasswordActivity>() {
                    public void performTask(ForgotPasswordActivity forgotPasswordActivity) {
                        String str;
                        if (str != null) {
                            str = str;
                        } else {
                            str = forgotPasswordActivity.getString(R.string.error_resetting_password);
                        }
                        forgotPasswordActivity.hideLoadingDialog();
                        if (ExperimentDataCenter.getInstance().showNewSignupFlow()) {
                            forgotPasswordActivity.startDialog(ForgotPasswordServiceFragment.this.createDialog(forgotPasswordActivity.getString(R.string.something_went_wrong), str));
                        } else {
                            forgotPasswordActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(str));
                        }
                    }
                });
            }
        });
    }

    public BaseDialogFragment createDialog(final String str, final String str2) {
        final BaseDialogFragment[] baseDialogFragmentArr = new BaseDialogFragment[1];
        withActivity(new ActivityTask<ForgotPasswordActivity>() {
            public void performTask(ForgotPasswordActivity forgotPasswordActivity) {
                MultiButtonDialogChoice multiButtonDialogChoice = new MultiButtonDialogChoice(0, forgotPasswordActivity.getString(R.string.got_it), R.color.white, R.drawable.main_button_selector, BackgroundType.DRAWABLE, ChoiceType.DEFAULT);
                ArrayList arrayList = new ArrayList();
                arrayList.add(multiButtonDialogChoice);
                baseDialogFragmentArr[0] = new MultiButtonDialogFragmentBuilder().setTitle(str).setSubTitle(str2).setButtons(arrayList).hideXButton().build();
            }
        });
        return baseDialogFragmentArr[0];
    }
}
