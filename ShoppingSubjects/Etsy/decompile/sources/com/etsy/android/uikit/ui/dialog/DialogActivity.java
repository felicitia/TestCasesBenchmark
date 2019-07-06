package com.etsy.android.uikit.ui.dialog;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import com.etsy.android.lib.logger.f;
import com.etsy.android.uikit.ui.core.TransparentActivity;

public abstract class DialogActivity extends TransparentActivity {
    private static final String TAG = f.a(DialogActivity.class);

    /* access modifiers changed from: protected */
    public abstract void onShowDialog(OnDismissListener onDismissListener);

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        onShowDialog(new OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
                DialogActivity.this.goBackDelayed();
            }
        });
    }
}
