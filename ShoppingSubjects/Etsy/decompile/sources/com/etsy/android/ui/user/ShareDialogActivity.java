package com.etsy.android.ui.user;

import android.content.DialogInterface.OnDismissListener;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.ui.nav.e;
import com.etsy.android.uikit.ui.dialog.DialogActivity;

public class ShareDialogActivity extends DialogActivity {
    /* access modifiers changed from: protected */
    public void onShowDialog(OnDismissListener onDismissListener) {
        e.a((FragmentActivity) this).d().a(onDismissListener).a(getIntent().getExtras()).d();
    }
}
