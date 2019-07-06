package com.etsy.android.ui.core;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.ui.nav.e;
import com.etsy.android.uikit.ui.core.BaseDialogFragment;
import com.etsy.android.uikit.ui.core.DialogLauncherActivity;

public class EtsyDialogLauncherActivity extends DialogLauncherActivity {
    public BaseDialogFragment onStartDialogFragment(Bundle bundle) {
        return e.a((FragmentActivity) this).f().i(bundle);
    }
}
