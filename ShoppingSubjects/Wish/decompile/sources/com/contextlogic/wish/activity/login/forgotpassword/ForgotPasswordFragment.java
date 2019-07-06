package com.contextlogic.wish.activity.login.forgotpassword;

import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.activity.BaseFragment.ActivityTask;
import com.contextlogic.wish.activity.BaseFragment.ServiceTask;
import com.contextlogic.wish.activity.UiFragment;
import com.contextlogic.wish.api.datacenter.ExperimentDataCenter;
import com.contextlogic.wish.api.service.compound.AuthenticationService;
import com.contextlogic.wish.dialog.multibutton.MultiButtonDialogFragment;
import com.contextlogic.wish.ui.text.ThemedDropdownEditText;
import com.contextlogic.wish.util.KeyboardUtil;
import com.contextlogic.wish.util.ViewUtil;
import java.util.ArrayList;
import java.util.Iterator;

public class ForgotPasswordFragment extends UiFragment<ForgotPasswordActivity> {
    /* access modifiers changed from: private */
    public ThemedDropdownEditText mEmailText;

    /* access modifiers changed from: protected */
    public int getLayoutResourceId() {
        return R.layout.forgot_password_fragment;
    }

    public void releaseImages() {
    }

    public void restoreImages() {
    }

    /* access modifiers changed from: protected */
    public void initialize() {
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), 17367050, AuthenticationService.getDeviceEmails());
        this.mEmailText = (ThemedDropdownEditText) findViewById(R.id.forgot_password_fragment_email_text);
        this.mEmailText.setAdapter(arrayAdapter);
        this.mEmailText.setOnEditorActionListener(new OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 6) {
                    return false;
                }
                ForgotPasswordFragment.this.resetPassword();
                return true;
            }
        });
        ((TextView) findViewById(R.id.forgot_password_fragment_reset_password_button)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ForgotPasswordFragment.this.resetPassword();
            }
        });
        if (ExperimentDataCenter.getInstance().shouldSeeNewSignUpScreen()) {
            TextView textView = (TextView) findViewById(R.id.forgot_password_title_text);
            textView.setTypeface(textView.getTypeface(), 1);
            TextView textView2 = (TextView) findViewById(R.id.forgot_password_description_text);
            textView2.setText(R.string.enter_email_password_reset_instruction);
            textView2.setGravity(1);
            textView2.setTextColor(getResources().getColor(R.color.gray1));
            LayoutParams layoutParams = new LayoutParams(-2, -2);
            layoutParams.gravity = 1;
            textView2.setLayoutParams(layoutParams);
            this.mEmailText.setHint(R.string.enter_email_address);
        }
    }

    /* access modifiers changed from: protected */
    public boolean validateFields() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.mEmailText);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            if (ViewUtil.extractEditTextValue((EditText) it.next()) == null) {
                withActivity(new ActivityTask<ForgotPasswordActivity>() {
                    public void performTask(ForgotPasswordActivity forgotPasswordActivity) {
                        forgotPasswordActivity.startDialog(MultiButtonDialogFragment.createMultiButtonErrorDialog(forgotPasswordActivity.getString(R.string.fill_in_all_fields)));
                    }
                });
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void resetPassword() {
        KeyboardUtil.hideKeyboard((Fragment) this);
        if (validateFields()) {
            withServiceFragment(new ServiceTask<BaseActivity, ForgotPasswordServiceFragment>() {
                public void performTask(BaseActivity baseActivity, ForgotPasswordServiceFragment forgotPasswordServiceFragment) {
                    forgotPasswordServiceFragment.resetPassword(ViewUtil.extractEditTextValue(ForgotPasswordFragment.this.mEmailText));
                }
            });
        }
    }
}
