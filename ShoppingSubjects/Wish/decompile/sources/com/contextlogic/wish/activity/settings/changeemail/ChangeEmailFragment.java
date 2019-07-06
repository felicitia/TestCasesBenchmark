package com.contextlogic.wish.activity.settings.changeemail;

import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.settings.SettingsFormFragment;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.datacenter.ProfileDataCenter;
import com.contextlogic.wish.ui.view.FormTextInputLayout;
import com.contextlogic.wish.ui.view.FormTextInputLayout.OnFieldChangedListener;
import com.contextlogic.wish.ui.view.FormTextInputLayout.OnVerifyFieldListener;
import com.contextlogic.wish.util.ArrayUtil;
import com.contextlogic.wish.util.KeyboardUtil;
import com.contextlogic.wish.util.ViewUtil;
import java.util.Arrays;

public class ChangeEmailFragment extends SettingsFormFragment<ChangeEmailActivity> {
    private boolean[] mAreFieldsChanged = new boolean[Field.values().length];
    private FormTextInputLayout mConfirmNewEmailFti;
    private String mCurrentEmail;
    private View mCurrentEmailLayout;
    private TextView mCurrentEmailTextView;
    private FormTextInputLayout mNewEmailFti;

    private enum Field {
        NEW_EMAIL,
        CONFIRM_NEW_EMAIL
    }

    /* access modifiers changed from: protected */
    public int getContentLayoutResourceId() {
        return R.layout.change_email_fragment;
    }

    public void releaseImages() {
    }

    public void restoreImages() {
    }

    /* access modifiers changed from: protected */
    public void initializeContent(View view) {
        this.mCurrentEmailLayout = view.findViewById(R.id.change_email_current_email_layout);
        this.mCurrentEmailTextView = (TextView) view.findViewById(R.id.change_email_current_email);
        this.mNewEmailFti = (FormTextInputLayout) view.findViewById(R.id.change_email_new_email_fti_layout);
        this.mConfirmNewEmailFti = (FormTextInputLayout) view.findViewById(R.id.change_email_confirm_new_email_fti_layout);
        initializeCurrentEmail(ProfileDataCenter.getInstance().getEmail());
        initializeListeners();
        getLoadingPageView().markLoadingComplete();
    }

    private void initializeCurrentEmail(String str) {
        this.mCurrentEmail = str;
        if (this.mCurrentEmail != null && this.mCurrentEmailTextView != null && this.mCurrentEmailLayout != null) {
            this.mCurrentEmailTextView.setText(this.mCurrentEmail);
            this.mCurrentEmailLayout.setVisibility(0);
        } else if (this.mCurrentEmailLayout != null) {
            this.mCurrentEmailLayout.setVisibility(8);
        }
    }

    private void initializeListeners() {
        this.mNewEmailFti.setOnVerifyFormListener(getEmptyVerifier(Field.NEW_EMAIL));
        this.mConfirmNewEmailFti.setOnVerifyFormListener(getEmptyVerifier(Field.CONFIRM_NEW_EMAIL));
        this.mNewEmailFti.setOnFieldChangedListener(getOnFieldChangedListener(Field.NEW_EMAIL));
        this.mConfirmNewEmailFti.setOnFieldChangedListener(getOnFieldChangedListener(Field.CONFIRM_NEW_EMAIL));
        if (ExperimentDataCenter.getInstance().shouldShowBottomNavigation()) {
            AnonymousClass1 r0 = new OnFocusChangeListener() {
                public void onFocusChange(View view, final boolean z) {
                    ChangeEmailFragment.this.withActivity(new ActivityTask<ChangeEmailActivity>() {
                        public void performTask(ChangeEmailActivity changeEmailActivity) {
                            changeEmailActivity.setBottomNavigationVisible(!z);
                        }
                    });
                }
            };
            this.mNewEmailFti.setOnFocusChangedListener(r0);
            this.mConfirmNewEmailFti.setOnFocusChangedListener(r0);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0044  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean verify() {
        /*
            r7 = this;
            com.contextlogic.wish.ui.view.FormTextInputLayout r0 = r7.mNewEmailFti
            r1 = 0
            if (r0 == 0) goto L_0x005e
            com.contextlogic.wish.ui.view.FormTextInputLayout r0 = r7.mConfirmNewEmailFti
            if (r0 != 0) goto L_0x000a
            goto L_0x005e
        L_0x000a:
            com.contextlogic.wish.ui.view.FormTextInputLayout r0 = r7.mNewEmailFti
            com.contextlogic.wish.ui.text.ThemedEditText r0 = r0.getEditText()
            java.lang.String r0 = com.contextlogic.wish.util.ViewUtil.extractEditTextValue(r0)
            com.contextlogic.wish.ui.view.FormTextInputLayout r2 = r7.mConfirmNewEmailFti
            com.contextlogic.wish.ui.text.ThemedEditText r2 = r2.getEditText()
            java.lang.String r2 = com.contextlogic.wish.util.ViewUtil.extractEditTextValue(r2)
            r3 = 1
            r4 = 2131756215(0x7f1004b7, float:1.9143331E38)
            r5 = 0
            if (r0 != 0) goto L_0x002b
            java.lang.String r3 = r7.getString(r4)
        L_0x0029:
            r6 = 0
            goto L_0x003d
        L_0x002b:
            java.lang.String r6 = r7.mCurrentEmail
            boolean r6 = r0.equals(r6)
            if (r6 == 0) goto L_0x003b
            r3 = 2131756434(0x7f100592, float:1.9143775E38)
            java.lang.String r3 = r7.getString(r3)
            goto L_0x0029
        L_0x003b:
            r3 = r5
            r6 = 1
        L_0x003d:
            if (r2 != 0) goto L_0x0044
            java.lang.String r5 = r7.getString(r4)
            goto L_0x0053
        L_0x0044:
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L_0x0052
            r0 = 2131755478(0x7f1001d6, float:1.9141836E38)
            java.lang.String r5 = r7.getString(r0)
            goto L_0x0053
        L_0x0052:
            r1 = r6
        L_0x0053:
            com.contextlogic.wish.activity.settings.changeemail.ChangeEmailFragment$Field r0 = com.contextlogic.wish.activity.settings.changeemail.ChangeEmailFragment.Field.NEW_EMAIL
            r7.setFieldErrored(r0, r3)
            com.contextlogic.wish.activity.settings.changeemail.ChangeEmailFragment$Field r0 = com.contextlogic.wish.activity.settings.changeemail.ChangeEmailFragment.Field.CONFIRM_NEW_EMAIL
            r7.setFieldErrored(r0, r5)
            return r1
        L_0x005e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.contextlogic.wish.activity.settings.changeemail.ChangeEmailFragment.verify():boolean");
    }

    private void setFieldErrored(Field field, String str) {
        switch (field) {
            case NEW_EMAIL:
                this.mNewEmailFti.setErrored(str);
                return;
            case CONFIRM_NEW_EMAIL:
                this.mConfirmNewEmailFti.setErrored(str);
                return;
            default:
                return;
        }
    }

    private OnFieldChangedListener getOnFieldChangedListener(final Field field) {
        return new OnFieldChangedListener() {
            public void onFieldChanged(String str) {
                ChangeEmailFragment.this.updateFieldChanged(field, !TextUtils.isEmpty(str));
            }
        };
    }

    private OnVerifyFieldListener getEmptyVerifier(Field field) {
        return new OnVerifyFieldListener() {
            public String getErrorMessage(String str) {
                if (TextUtils.isEmpty(str)) {
                    return ChangeEmailFragment.this.getString(R.string.required_field);
                }
                return null;
            }
        };
    }

    /* access modifiers changed from: private */
    public void updateFieldChanged(Field field, boolean z) {
        if (this.mAreFieldsChanged != null && field != null && this.mAreFieldsChanged[field.ordinal()] != z) {
            this.mAreFieldsChanged[field.ordinal()] = z;
            updateButtonState();
        }
    }

    private void updateButtonState() {
        setButtonState(ArrayUtil.all(this.mAreFieldsChanged));
    }

    /* access modifiers changed from: protected */
    public void onSaveButtonClicked() {
        if (this.mNewEmailFti != null) {
            final String extractEditTextValue = ViewUtil.extractEditTextValue(this.mNewEmailFti.getEditText());
            if (extractEditTextValue != null && verify()) {
                KeyboardUtil.hideKeyboard((Fragment) this);
                withServiceFragment(new ServiceTask<BaseActivity, ChangeEmailServiceFragment>() {
                    public void performTask(BaseActivity baseActivity, ChangeEmailServiceFragment changeEmailServiceFragment) {
                        changeEmailServiceFragment.changeEmail(extractEditTextValue);
                    }
                });
            }
        }
    }

    public void handleChangeEmailSuccess(String str) {
        if (getView() != null) {
            getView().requestFocus();
        }
        if (this.mAreFieldsChanged != null) {
            Arrays.fill(this.mAreFieldsChanged, false);
            updateButtonState();
        }
        if (this.mNewEmailFti != null) {
            this.mNewEmailFti.clear();
        }
        if (this.mConfirmNewEmailFti != null) {
            this.mConfirmNewEmailFti.clear();
        }
        showSuccessDialog(str);
    }

    public void handleChangeEmailFailure(String str) {
        if (this.mNewEmailFti != null) {
            this.mNewEmailFti.setErrored(str);
            updateButtonState();
        }
    }

    private void showSuccessDialog(final String str) {
        withActivity(new ActivityTask<ChangeEmailActivity>() {
            public void performTask(ChangeEmailActivity changeEmailActivity) {
                changeEmailActivity.startDialog(ChangeEmailSuccessDialogFragment.createDialog(str));
            }
        });
    }
}
