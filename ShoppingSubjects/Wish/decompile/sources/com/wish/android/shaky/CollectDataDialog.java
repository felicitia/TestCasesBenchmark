package com.wish.android.shaky;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.os.Bundle;

public class CollectDataDialog extends DialogFragment {
    public Dialog onCreateDialog(Bundle bundle) {
        super.onCreate(bundle);
        AnonymousClass1 r3 = new ProgressDialog(getActivity(), R.style.AppCompatAlertDialog) {
            public void onBackPressed() {
            }
        };
        r3.setTitle(R.string.shaky_collecting_feedback);
        r3.setCancelable(false);
        r3.setCanceledOnTouchOutside(false);
        r3.setProgressStyle(1);
        r3.setIndeterminate(true);
        r3.setProgressPercentFormat(null);
        r3.setProgressNumberFormat(null);
        return r3;
    }
}
