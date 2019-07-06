package com.etsy.android.uikit.ui.dialog;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class RetainedNestingDialogFragment extends DialogFragment {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
    }
}
