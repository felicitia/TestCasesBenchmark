package com.contextlogic.wish.activity.settings.changecurrency;

import android.app.Activity;
import android.os.Bundle;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.BaseFragment.UiTask;
import com.contextlogic.wish.activity.ServiceFragment;
import com.contextlogic.wish.api.service.ApiService.DefaultCodeFailureCallback;
import com.contextlogic.wish.api.service.ApiService.DefaultFailureCallback;
import com.contextlogic.wish.api.service.ApiService.DefaultSuccessCallback;
import com.contextlogic.wish.api.service.standalone.ChangeCurrencyService;
import com.contextlogic.wish.api.service.standalone.GetCurrencySettingsService;
import com.contextlogic.wish.api.service.standalone.GetCurrencySettingsService.SuccessCallback;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.dialog.BaseDialogFragment.BaseDialogCallback;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice.BackgroundType;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogChoice.ChoiceType;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment.MultiButtonDialogFragmentBuilder;
import com.contextlogic.wish.util.KeyboardUtil;
import java.util.ArrayList;

public class ChangeCurrencyServiceFragment extends ServiceFragment<ChangeCurrencyActivity> {
    private ChangeCurrencyService mChangeCurrencyService;
    private GetCurrencySettingsService mGetCurrencySettingsService;

    /* access modifiers changed from: protected */
    public void initializeServices() {
        super.initializeServices();
        this.mGetCurrencySettingsService = new GetCurrencySettingsService();
        this.mChangeCurrencyService = new ChangeCurrencyService();
    }

    /* access modifiers changed from: protected */
    public void cancelAllRequests() {
        super.cancelAllRequests();
        this.mGetCurrencySettingsService.cancelAllRequests();
        this.mChangeCurrencyService.cancelAllRequests();
    }

    public void loadCurrencySettings() {
        withActivity(new ActivityTask<ChangeCurrencyActivity>() {
            public void performTask(ChangeCurrencyActivity changeCurrencyActivity) {
                changeCurrencyActivity.showLoadingDialog();
            }
        });
        this.mGetCurrencySettingsService.requestService(new SuccessCallback() {
            public void onSuccess(final String str, final ArrayList<String> arrayList, final ArrayList<String> arrayList2) {
                ChangeCurrencyServiceFragment.this.withUiFragment(new UiTask<ChangeCurrencyActivity, ChangeCurrencyFragment>() {
                    public void performTask(ChangeCurrencyActivity changeCurrencyActivity, ChangeCurrencyFragment changeCurrencyFragment) {
                        changeCurrencyActivity.hideLoadingDialog();
                        changeCurrencyFragment.handleGetCurrencySettingsSuccess(str, arrayList, arrayList2);
                    }
                });
            }
        }, new DefaultFailureCallback() {
            public void onFailure(String str) {
                ChangeCurrencyServiceFragment.this.withUiFragment(new UiTask<ChangeCurrencyActivity, ChangeCurrencyFragment>() {
                    public void performTask(ChangeCurrencyActivity changeCurrencyActivity, ChangeCurrencyFragment changeCurrencyFragment) {
                        changeCurrencyActivity.hideLoadingDialog();
                        changeCurrencyFragment.handleGetCurrencySettingsFailure();
                        ChangeCurrencyServiceFragment.this.showGetCurrencySettingsFailureDialog(new BaseDialogCallback() {
                            public void onCancel(BaseDialogFragment baseDialogFragment) {
                            }

                            public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                                ((ChangeCurrencyActivity) ChangeCurrencyServiceFragment.this.getBaseActivity()).finishActivity();
                            }
                        });
                    }
                });
            }
        });
    }

    public void showGetCurrencySettingsFailureDialog(final BaseDialogCallback baseDialogCallback) {
        MultiButtonDialogChoice multiButtonDialogChoice = new MultiButtonDialogChoice(3, getString(R.string.ok), R.color.white, R.drawable.main_button_selector, BackgroundType.DRAWABLE, ChoiceType.DEFAULT);
        ArrayList arrayList = new ArrayList();
        arrayList.add(multiButtonDialogChoice);
        final MultiButtonDialogFragment build = new MultiButtonDialogFragmentBuilder().setTitle(getString(R.string.oops)).setSubTitle(getString(R.string.error_load_currency_settings)).hideXButton().setButtons(arrayList).setCancelable(false).build();
        withActivity(new ActivityTask<ChangeCurrencyActivity>() {
            public void performTask(ChangeCurrencyActivity changeCurrencyActivity) {
                changeCurrencyActivity.startDialog(build, baseDialogCallback);
            }
        });
    }

    public void showCurrencyPicker(String str, String str2, ArrayList<String> arrayList, ArrayList<String> arrayList2, BaseDialogCallback baseDialogCallback) {
        final ArrayList<String> arrayList3 = arrayList;
        final ArrayList<String> arrayList4 = arrayList2;
        final String str3 = str2;
        final String str4 = str;
        final BaseDialogCallback baseDialogCallback2 = baseDialogCallback;
        AnonymousClass5 r0 = new ActivityTask<ChangeCurrencyActivity>() {
            public void performTask(ChangeCurrencyActivity changeCurrencyActivity) {
                KeyboardUtil.hideKeyboard((Activity) changeCurrencyActivity);
                final ChangeCurrencyDialogFragment createChangeCurrencyDialog = ChangeCurrencyDialogFragment.createChangeCurrencyDialog(arrayList3, arrayList4, str3 != null ? str3 : str4);
                if (createChangeCurrencyDialog != null) {
                    ChangeCurrencyServiceFragment.this.withServiceFragment(new ServiceTask<BaseActivity, ServiceFragment>() {
                        public void performTask(BaseActivity baseActivity, ServiceFragment serviceFragment) {
                            baseActivity.startDialog(createChangeCurrencyDialog, baseDialogCallback2);
                        }
                    });
                }
            }
        };
        withActivity(r0);
    }

    public void changeCurrency(final String str) {
        withActivity(new ActivityTask<ChangeCurrencyActivity>() {
            public void performTask(ChangeCurrencyActivity changeCurrencyActivity) {
                changeCurrencyActivity.showLoadingDialog();
            }
        });
        this.mChangeCurrencyService.requestService(str, new DefaultSuccessCallback() {
            public void onSuccess() {
                ChangeCurrencyServiceFragment.this.withUiFragment(new UiTask<ChangeCurrencyActivity, ChangeCurrencyFragment>() {
                    public void performTask(ChangeCurrencyActivity changeCurrencyActivity, ChangeCurrencyFragment changeCurrencyFragment) {
                        changeCurrencyActivity.hideLoadingDialog();
                        changeCurrencyFragment.handleChangeCurrencySuccess(str);
                    }
                }, "FragmentTagMainContent");
            }
        }, new DefaultCodeFailureCallback() {
            public void onFailure(final String str, int i) {
                ChangeCurrencyServiceFragment.this.withUiFragment(new UiTask<ChangeCurrencyActivity, ChangeCurrencyFragment>() {
                    public void performTask(ChangeCurrencyActivity changeCurrencyActivity, ChangeCurrencyFragment changeCurrencyFragment) {
                        changeCurrencyActivity.hideLoadingDialog();
                        changeCurrencyFragment.handleChangeCurrencyFailure(str);
                    }
                }, "FragmentTagMainContent");
            }
        });
    }

    public void showSaveChangesDialog(final BaseDialogCallback baseDialogCallback) {
        withActivity(new ActivityTask<ChangeCurrencyActivity>() {
            public void performTask(ChangeCurrencyActivity changeCurrencyActivity) {
                MultiButtonDialogChoice multiButtonDialogChoice = new MultiButtonDialogChoice(1, ChangeCurrencyServiceFragment.this.getString(R.string.save_changes), R.color.white, R.drawable.main_button_selector, BackgroundType.DRAWABLE, ChoiceType.DEFAULT);
                MultiButtonDialogChoice multiButtonDialogChoice2 = new MultiButtonDialogChoice(2, ChangeCurrencyServiceFragment.this.getString(R.string.leave_unchanged), R.color.main_primary, 0, BackgroundType.NONE, ChoiceType.TEXT_ONLY);
                ArrayList arrayList = new ArrayList();
                arrayList.add(multiButtonDialogChoice);
                arrayList.add(multiButtonDialogChoice2);
                changeCurrencyActivity.startDialog(new MultiButtonDialogFragmentBuilder().setTitle(ChangeCurrencyServiceFragment.this.getString(R.string.you_have_unsaved_changes)).setSubTitle(ChangeCurrencyServiceFragment.this.getString(R.string.are_you_sure_you_want_to_leave)).hideXButton().setButtons(arrayList).setCancelable(false).build(), baseDialogCallback);
            }
        });
    }
}
