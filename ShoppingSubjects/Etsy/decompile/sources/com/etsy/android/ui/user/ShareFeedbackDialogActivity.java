package com.etsy.android.ui.user;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.ui.nav.e;
import com.etsy.android.uikit.ui.core.TransparentActivity;

public class ShareFeedbackDialogActivity extends TransparentActivity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            e.a((FragmentActivity) this).f().a(getIntent().getExtras()).g();
        }
    }
}
