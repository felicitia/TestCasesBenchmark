package com.contextlogic.wish.activity.settings.changecurrency;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.settings.SettingsFormFragment;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.application.WishApplication;
import com.contextlogic.wish.dialog.BaseDialogFragment;
import com.contextlogic.wish.dialog.BaseDialogFragment.BaseDialogCallback;
import com.contextlogic.wish.dialog.bottomsheet.SuccessBottomSheetDialog;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import com.contextlogic.wish.ui.view.FormTextInputLayout;
import com.contextlogic.wish.util.StringUtil;
import java.util.ArrayList;

public class ChangeCurrencyFragment extends SettingsFormFragment<ChangeCurrencyActivity> {
    /* access modifiers changed from: private */
    public String mNewCurrency;
    /* access modifiers changed from: private */
    public String mNewCurrencySymbol;
    /* access modifiers changed from: private */
    public String mOldCurrency;
    /* access modifiers changed from: private */
    public String mOldCurrencySymbol;
    /* access modifiers changed from: private */
    public ArrayList<String> mOptions;
    /* access modifiers changed from: private */
    public FormTextInputLayout mSelectedCurrencyFti;
    /* access modifiers changed from: private */
    public ArrayList<String> mSymbols;

    /* access modifiers changed from: protected */
    public int getContentLayoutResourceId() {
        return R.layout.change_currency_fragment;
    }

    public void releaseImages() {
    }

    public void restoreImages() {
    }

    /* access modifiers changed from: protected */
    public void initializeContent(View view) {
        this.mSelectedCurrencyFti = (FormTextInputLayout) view.findViewById(R.id.change_currency_selected_currency_fti_layout);
        this.mSelectedCurrencyFti.setVisibility(0);
        this.mSelectedCurrencyFti.setTextCentered();
        this.mButton.setText(R.string.change_currency);
        loadCurrencySettings();
    }

    public boolean hasItems() {
        return (this.mOldCurrency == null || this.mOldCurrencySymbol == null || this.mOptions == null || this.mSymbols == null) ? false : true;
    }

    private void loadCurrencySettings() {
        getLoadingPageView().prepareForReload();
        withServiceFragment(new ServiceTask<BaseActivity, ChangeCurrencyServiceFragment>() {
            public void performTask(BaseActivity baseActivity, ChangeCurrencyServiceFragment changeCurrencyServiceFragment) {
                changeCurrencyServiceFragment.loadCurrencySettings();
            }
        });
    }

    public void handleGetCurrencySettingsSuccess(String str, ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        String str2;
        String str3;
        this.mOptions = arrayList;
        this.mSymbols = arrayList2;
        this.mOldCurrency = str;
        int indexOf = this.mOptions.indexOf(this.mOldCurrency);
        if (indexOf == -1) {
            str2 = null;
        } else {
            str2 = (String) this.mSymbols.get(indexOf);
        }
        this.mOldCurrencySymbol = str2;
        if (this.mOldCurrencySymbol == null) {
            str3 = this.mOldCurrency;
        } else {
            str3 = WishApplication.getInstance().getString(R.string.change_currency_selection_dialog_item_text, new Object[]{this.mOldCurrency, this.mOldCurrencySymbol});
        }
        this.mSelectedCurrencyFti.setText(str3);
        this.mSelectedCurrencyFti.setEnabled(true);
        this.mSelectedCurrencyFti.setDropdownClickListener(new OnClickListener() {
            public void onClick(View view) {
                ChangeCurrencyFragment.this.showCurrencyPicker();
            }
        });
        getLoadingPageView().markLoadingComplete();
        updateButtonState();
    }

    public void handleGetCurrencySettingsFailure() {
        getLoadingPageView().markLoadingErrored();
        this.mSelectedCurrencyFti.setEnabled(false);
    }

    /* access modifiers changed from: 0000 */
    public void showCurrencyPicker() {
        withServiceFragment(new ServiceTask<BaseActivity, ChangeCurrencyServiceFragment>() {
            public void performTask(BaseActivity baseActivity, ChangeCurrencyServiceFragment changeCurrencyServiceFragment) {
                changeCurrencyServiceFragment.showCurrencyPicker(ChangeCurrencyFragment.this.mOldCurrency, ChangeCurrencyFragment.this.mNewCurrency, ChangeCurrencyFragment.this.mOptions, ChangeCurrencyFragment.this.mSymbols, new BaseDialogCallback() {
                    public void onCancel(BaseDialogFragment baseDialogFragment) {
                    }

                    public void onSelection(BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                        ChangeCurrencyFragment.this.mNewCurrency = bundle.getString("ResultNewCurrency");
                        ChangeCurrencyFragment.this.mNewCurrencySymbol = bundle.getString("ResultNewCurrencySymbol");
                        ChangeCurrencyFragment.this.mSelectedCurrencyFti.setText(ChangeCurrencyFragment.this.getString(R.string.change_currency_selection_dialog_item_text, ChangeCurrencyFragment.this.mNewCurrency, ChangeCurrencyFragment.this.mNewCurrencySymbol));
                        ChangeCurrencyFragment.this.updateButtonState();
                    }
                });
            }
        });
    }

    /* access modifiers changed from: protected */
    public void updateButtonState() {
        setButtonState(this.mNewCurrency != null && !this.mNewCurrency.equals(this.mOldCurrency));
    }

    /* access modifiers changed from: protected */
    public void onSaveButtonClicked() {
        withServiceFragment(new ServiceTask<BaseActivity, ChangeCurrencyServiceFragment>() {
            public void performTask(BaseActivity baseActivity, ChangeCurrencyServiceFragment changeCurrencyServiceFragment) {
                if (ChangeCurrencyFragment.this.mNewCurrency != null) {
                    changeCurrencyServiceFragment.changeCurrency(ChangeCurrencyFragment.this.mNewCurrency);
                }
            }
        });
    }

    public void handleChangeCurrencySuccess(String str) {
        if (getView() != null) {
            getView().requestFocus();
        }
        this.mOldCurrency = str;
        this.mOldCurrencySymbol = (String) this.mSymbols.get(this.mOptions.indexOf(this.mOldCurrency));
        this.mNewCurrency = null;
        this.mNewCurrencySymbol = null;
        if (this.mSelectedCurrencyFti != null) {
            this.mSelectedCurrencyFti.setErrored(null);
        }
        updateButtonState();
        withActivity(new ActivityTask<ChangeCurrencyActivity>() {
            public void performTask(ChangeCurrencyActivity changeCurrencyActivity) {
                SuccessBottomSheetDialog create = SuccessBottomSheetDialog.create(changeCurrencyActivity);
                create.setTitle(ChangeCurrencyFragment.this.getString(R.string.change_currency_success_dialog_title, ChangeCurrencyFragment.this.mOldCurrency, ChangeCurrencyFragment.this.mOldCurrencySymbol));
                ChangeCurrencyFragment changeCurrencyFragment = ChangeCurrencyFragment.this;
                WishApplication.getInstance();
                create.setMessage(changeCurrencyFragment.getString(R.string.change_currency_success_dialog_message, StringUtil.capitalize(WishApplication.getAppType())));
                create.autoDismiss();
                create.setOnDismissListener(new OnDismissListener() {
                    public void onDismiss(DialogInterface dialogInterface) {
                        ChangeCurrencyFragment.this.withActivity(new ActivityTask<ChangeCurrencyActivity>() {
                            public void performTask(ChangeCurrencyActivity changeCurrencyActivity) {
                                changeCurrencyActivity.finishActivity();
                            }
                        });
                    }
                });
                create.show();
                Intent intent = new Intent();
                intent.putExtra("ExtraRequiresReload", true);
                changeCurrencyActivity.setResult(-1, intent);
            }
        });
    }

    public void handleChangeCurrencyFailure(final String str) {
        if (str == null) {
            str = getString(R.string.error_change_currency);
        }
        if (this.mSelectedCurrencyFti != null) {
            this.mSelectedCurrencyFti.setErrored(str);
        }
        updateButtonState();
        withActivity(new ActivityTask<ChangeCurrencyActivity>() {
            public void performTask(ChangeCurrencyActivity changeCurrencyActivity) {
                changeCurrencyActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(str));
            }
        });
    }

    public boolean onBackPressed() {
        if (this.mNewCurrency == null || this.mNewCurrency.equals(this.mOldCurrency)) {
            return false;
        }
        WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.IMPRESSION_CHANGE_CURRENCY_SAVED_CHANGES_CONFIRMATION);
        withServiceFragment(new ServiceTask<BaseActivity, ChangeCurrencyServiceFragment>() {
            public void performTask(BaseActivity baseActivity, ChangeCurrencyServiceFragment changeCurrencyServiceFragment) {
                changeCurrencyServiceFragment.showSaveChangesDialog(new BaseDialogCallback() {
                    public void onCancel(BaseDialogFragment baseDialogFragment) {
                    }

                    public void onSelection(final BaseDialogFragment baseDialogFragment, int i, Bundle bundle) {
                        if (i == 2) {
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_CHANGE_CURRENCY_POPUP_LEAVE_UNCHANGED);
                            ChangeCurrencyFragment.this.withActivity(new ActivityTask<ChangeCurrencyActivity>() {
                                public void performTask(ChangeCurrencyActivity changeCurrencyActivity) {
                                    changeCurrencyActivity.finishActivity();
                                }
                            });
                        } else if (i == 1) {
                            WishAnalyticsLogger.trackEvent(WishAnalyticsEvent.CLICK_CHANGE_CURRENCY_POPUP_SAVE_CHANGES);
                            ChangeCurrencyFragment.this.withActivity(new ActivityTask<ChangeCurrencyActivity>() {
                                public void performTask(ChangeCurrencyActivity changeCurrencyActivity) {
                                    baseDialogFragment.cancel();
                                    ChangeCurrencyFragment.this.onSaveButtonClicked();
                                }
                            });
                        }
                    }
                });
            }
        });
        return true;
    }
}
