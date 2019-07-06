package com.contextlogic.wish.activity.settings.changephonenumber;

import android.os.Bundle;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.UiTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.standalone.GetPhoneResetKeyService;
import com.contextlogic.wish.api.service.standalone.GetPhoneResetKeyService.SuccessCallback;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.dialog.BaseDialogFragment.BaseDialogCallback;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice.BackgroundType;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice.ChoiceType;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment.MultiButtonDialogFragmentBuilder;
import java.util.ArrayList;

public class ChangePhoneNumberServiceFragment extends ServiceFragment<ChangePhoneNumberActivity> {
    private GetPhoneResetKeyService mGetPhoneResetKeyService;

    /* access modifiers changed from: protected */
    public void initializeServices() {
        super.initializeServices();
        this.mGetPhoneResetKeyService = new GetPhoneResetKeyService();
    }

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
        super.cancelAllRequests();
        this.mGetPhoneResetKeyService.cancelAllRequests();
    }

    public void getPhoneResetKey() {
        this.mGetPhoneResetKeyService.requestService(new SuccessCallback() {
            public void onSuccess(final String str, final String str2) {
                ChangePhoneNumberServiceFragment.this.withUiFragment(new UiTask<ChangePhoneNumberActivity, ChangePhoneNumberFragment>() {
                    public void performTask(ChangePhoneNumberActivity changePhoneNumberActivity, ChangePhoneNumberFragment changePhoneNumberFragment) {
                        changePhoneNumberFragment.handleGetPhoneResetKeySuccess(str, str2);
                    }
                });
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                ChangePhoneNumberServiceFragment.this.withUiFragment(new UiTask<ChangePhoneNumberActivity, ChangePhoneNumberFragment>() {
                    public void performTask(ChangePhoneNumberActivity changePhoneNumberActivity, ChangePhoneNumberFragment changePhoneNumberFragment) {
                        changePhoneNumberFragment.handleGetPhoneResetKeyFailure();
                        ChangePhoneNumberServiceFragment.this.showGetPhoneResetKeyFailureDialog(new BaseDialogCallback() {
                            public void onCancel(BaseDialogFragment baseDialogFragment) {
                            }

                            public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                            }
                        });
                    }
                });
            }
        });
    }

    public void showGetPhoneResetKeyFailureDialog(final BaseDialogCallback baseDialogCallback) {
        MultiButtonDialogChoice multiButtonDialogChoice = new MultiButtonDialogChoice(3, getString(R.string.ok), R.color.white, R.drawable.main_button_selector, BackgroundType.DRAWABLE, ChoiceType.DEFAULT);
        ArrayList arrayList = new ArrayList();
        arrayList.add(multiButtonDialogChoice);
        final MultiButtonDialogFragment build = new MultiButtonDialogFragmentBuilder().setTitle(getString(R.string.error_title_get_phone_reset_key)).setSubTitle(getString(R.string.error_message_get_phone_reset_key)).hideXButton().setButtons(arrayList).setCancelable(false).build();
        withActivity(new ActivityTask<ChangePhoneNumberActivity>() {
            public void performTask(ChangePhoneNumberActivity changePhoneNumberActivity) {
                changePhoneNumberActivity.startDialog(build, baseDialogCallback);
            }
        });
    }
}
