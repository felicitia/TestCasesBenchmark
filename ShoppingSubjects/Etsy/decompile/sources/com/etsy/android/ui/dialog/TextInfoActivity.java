package com.etsy.android.ui.dialog;

import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.ui.nav.e;
import com.etsy.android.uikit.ui.dialog.DialogActivity;

public class TextInfoActivity extends DialogActivity {
    private String mContent;
    private String mTitle;

    public void onCreate(Bundle bundle) {
        Intent intent = getIntent();
        this.mTitle = intent.getStringExtra("title");
        this.mContent = intent.getStringExtra("text");
        super.onCreate(bundle);
    }

    /* access modifiers changed from: protected */
    public void onShowDialog(OnDismissListener onDismissListener) {
        e.a((FragmentActivity) this).d().a(onDismissListener).a(this.mTitle, this.mContent);
    }
}
