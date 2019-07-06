package com.contextlogic.wish.activity.settings.changeemail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.contextlogic.wish.R;
import com.contextlogic.wish.activity.BaseActivity;
import com.contextlogic.wish.dialog.BaseDialogFragment;

public class ChangeEmailSuccessDialogFragment<A extends BaseActivity> extends BaseDialogFragment<A> {
    public static ChangeEmailSuccessDialogFragment createDialog(String str) {
        ChangeEmailSuccessDialogFragment changeEmailSuccessDialogFragment = new ChangeEmailSuccessDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("ArgNewEmail", str);
        changeEmailSuccessDialogFragment.setArguments(bundle);
        return changeEmailSuccessDialogFragment;
    }

    public View getContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.change_email_success_dialog_fragment, viewGroup, false);
        TextView textView = (TextView) inflate.findViewById(R.id.change_email_success_dialog_email);
        View findViewById = inflate.findViewById(R.id.change_email_success_dialog_button);
        textView.setText(getNewEmail());
        findViewById.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ChangeEmailSuccessDialogFragment.this.cancel();
            }
        });
        return inflate;
    }

    private String getNewEmail() {
        if (getArguments() != null) {
            return getArguments().getString("ArgNewEmail");
        }
        return null;
    }
}
