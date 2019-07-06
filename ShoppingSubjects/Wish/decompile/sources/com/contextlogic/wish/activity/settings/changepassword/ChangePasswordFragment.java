package com.contextlogic.wish.activity.settings.changepassword;

import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.settings.SettingsFormFragment;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.dialog.bottomsheet.SuccessBottomSheetDialog;
import com.contextlogic.wish.ui.view.FormTextInputLayout;
import com.contextlogic.wish.ui.view.FormTextInputLayout.OnFieldChangedListener;
import com.contextlogic.wish.ui.view.FormTextInputLayout.OnVerifyFieldListener;
import com.contextlogic.wish.util.ArrayUtil;
import com.contextlogic.wish.util.KeyboardUtil;
import com.contextlogic.wish.util.ViewUtil;
import java.util.Arrays;

public class ChangePasswordFragment extends SettingsFormFragment<ChangePasswordActivity> {
    private boolean[] mAreFieldsChanged = new boolean[Field.values().length];
    private boolean[] mAreFieldsErrored = new boolean[Field.values().length];
    /* access modifiers changed from: private */
    public FormTextInputLayout mNewPasswordFti;
    /* access modifiers changed from: private */
    public FormTextInputLayout mOldPasswordFti;

    private enum Field {
        OLD_PASSWORD,
        NEW_PASSWORD
    }

    /* access modifiers changed from: protected */
    public int getContentLayoutResourceId() {
        return R.layout.change_password_redesign_fragment;
    }

    public void releaseImages() {
    }

    public void restoreImages() {
    }

    /* access modifiers changed from: protected */
    public void initializeContent(View view) {
        this.mOldPasswordFti = (FormTextInputLayout) view.findViewById(R.id.change_password_redesign_old_password_fti_layout);
        this.mNewPasswordFti = (FormTextInputLayout) view.findViewById(R.id.change_password_redesign_new_password_fti_layout);
        initializeListeners();
        getLoadingPageView().markLoadingComplete();
    }

    private void initializeListeners() {
        this.mOldPasswordFti.setOnFieldChangedListener(getOnFieldChangedListener(Field.OLD_PASSWORD));
        this.mNewPasswordFti.setOnFieldChangedListener(getOnFieldChangedListener(Field.NEW_PASSWORD));
        this.mOldPasswordFti.setOnVerifyFormListener(getPasswordVerifier(Field.OLD_PASSWORD));
        this.mNewPasswordFti.setOnVerifyFormListener(getPasswordVerifier(Field.NEW_PASSWORD));
        if (ExperimentDataCenter.getInstance().shouldShowBottomNavigation()) {
            AnonymousClass1 r0 = new OnFocusChangeListener() {
                public void onFocusChange(View view, final boolean z) {
                    ChangePasswordFragment.this.withActivity(new ActivityTask<ChangePasswordActivity>() {
                        public void performTask(ChangePasswordActivity changePasswordActivity) {
                            changePasswordActivity.setBottomNavigationVisible(!z);
                        }
                    });
                }
            };
            this.mOldPasswordFti.setOnFocusChangedListener(r0);
            this.mNewPasswordFti.setOnFocusChangedListener(r0);
        }
    }

    private OnFieldChangedListener getOnFieldChangedListener(final Field field) {
        return new OnFieldChangedListener() {
            public void onFieldChanged(String str) {
                ChangePasswordFragment.this.updateFieldChanged(field, !TextUtils.isEmpty(str));
            }
        };
    }

    private OnVerifyFieldListener getPasswordVerifier(final Field field) {
        return new OnVerifyFieldListener() {
            public String getErrorMessage(String str) {
                boolean isEmpty = TextUtils.isEmpty(str);
                ChangePasswordFragment.this.updateFieldErrored(field, isEmpty);
                if (isEmpty) {
                    return ChangePasswordFragment.this.getString(R.string.password_field_is_empty);
                }
                return null;
            }
        };
    }

    private void updateButtonState() {
        setButtonState(ArrayUtil.all(this.mAreFieldsChanged) && !ArrayUtil.any(this.mAreFieldsErrored));
    }

    public void handleUpdateSuccess() {
        if (getView() != null) {
            getView().requestFocus();
        }
        if (this.mAreFieldsChanged != null) {
            Arrays.fill(this.mAreFieldsChanged, false);
            updateButtonState();
        }
        if (this.mOldPasswordFti != null) {
            this.mOldPasswordFti.clear();
        }
        if (this.mNewPasswordFti != null) {
            this.mNewPasswordFti.clear();
        }
        withActivity(new ActivityTask<ChangePasswordActivity>() {
            public void performTask(ChangePasswordActivity changePasswordActivity) {
                SuccessBottomSheetDialog.create(changePasswordActivity).setTitle(ChangePasswordFragment.this.getString(R.string.your_password_is_updated)).setMessage(ChangePasswordFragment.this.getString(R.string.please_use_your_new_password)).autoDismiss().show();
            }
        });
    }

    public void handleOldPasswordError(String str) {
        if (this.mOldPasswordFti != null) {
            this.mOldPasswordFti.setErrored(str);
            updateFieldErrored(Field.OLD_PASSWORD, true);
        }
    }

    public void handleNewPasswordError(String str) {
        if (this.mNewPasswordFti != null) {
            this.mNewPasswordFti.setErrored(str);
            updateFieldErrored(Field.NEW_PASSWORD, true);
        }
    }

    /* access modifiers changed from: private */
    public void updateFieldErrored(Field field, boolean z) {
        if (this.mAreFieldsErrored != null && field != null && this.mAreFieldsErrored[field.ordinal()] != z) {
            this.mAreFieldsErrored[field.ordinal()] = z;
            updateButtonState();
        }
    }

    /* access modifiers changed from: private */
    public void updateFieldChanged(Field field, boolean z) {
        if (this.mAreFieldsChanged != null && field != null && this.mAreFieldsChanged[field.ordinal()] != z) {
            this.mAreFieldsChanged[field.ordinal()] = z;
            updateButtonState();
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveButtonClicked() {
        KeyboardUtil.hideKeyboard((Fragment) this);
        if (getView() != null) {
            getView().requestFocus();
        }
        withServiceFragment(new ServiceTask<BaseActivity, ChangePasswordServiceFragment>() {
            public void performTask(BaseActivity baseActivity, ChangePasswordServiceFragment changePasswordServiceFragment) {
                changePasswordServiceFragment.changePassword(ViewUtil.extractEditTextValue(ChangePasswordFragment.this.mOldPasswordFti.getEditText()), ViewUtil.extractEditTextValue(ChangePasswordFragment.this.mNewPasswordFti.getEditText()));
            }
        });
    }
}
