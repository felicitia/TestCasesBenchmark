package com.wish.android.shaky;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.Toolbar.OnMenuItemClickListener;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import com.threatmetrix.TrustDefender.StrongAuth;

public class FormFragment extends Fragment {
    public static FormFragment newInstance(String str, String str2, Uri uri) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("ScreenshotUri", uri);
        bundle.putString(StrongAuth.AUTH_TITLE, str);
        bundle.putString("hint", str2);
        FormFragment formFragment = new FormFragment();
        formFragment.setArguments(bundle);
        return formFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.shaky_form, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.shaky_toolbar);
        EditText editText = (EditText) view.findViewById(R.id.shaky_form_message);
        ImageView imageView = (ImageView) view.findViewById(R.id.shaky_form_attachment);
        Uri uri = (Uri) getArguments().getParcelable("ScreenshotUri");
        toolbar.setTitle((CharSequence) getArguments().getString(StrongAuth.AUTH_TITLE));
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(createNavigationClickListener());
        toolbar.inflateMenu(R.menu.shaky_feedback_activity_actions);
        toolbar.setOnMenuItemClickListener(createMenuClickListener(editText));
        editText.setHint(getArguments().getString("hint"));
        editText.requestFocus();
        imageView.setImageURI(uri);
        imageView.setOnClickListener(createNavigationClickListener());
    }

    private OnClickListener createNavigationClickListener() {
        return new OnClickListener() {
            public void onClick(View view) {
                LocalBroadcastManager.getInstance(FormFragment.this.getActivity()).sendBroadcast(new Intent("ActionEditImage"));
            }
        };
    }

    private OnMenuItemClickListener createMenuClickListener(final EditText editText) {
        return new OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.action_submit) {
                    String obj = editText.getText().toString();
                    if (FormFragment.this.validate(obj)) {
                        Intent intent = new Intent("ActionSubmitFeedback");
                        intent.putExtra("ExtraUserMessage", obj);
                        LocalBroadcastManager.getInstance(FormFragment.this.getActivity()).sendBroadcast(intent);
                        return true;
                    }
                }
                return false;
            }
        };
    }

    /* access modifiers changed from: private */
    public boolean validate(String str) {
        if (str.trim().length() != 0) {
            return true;
        }
        AlertDialog create = new Builder(getActivity()).create();
        create.setMessage(getString(R.string.shaky_empty_feedback_message));
        create.setButton(-1, getString(R.string.shaky_empty_feedback_confirm), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        create.show();
        return false;
    }
}
