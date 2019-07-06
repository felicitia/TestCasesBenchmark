package com.etsy.android.ui.convos;

import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.lib.convos.k;
import com.etsy.android.lib.core.b.a;
import com.etsy.android.ui.nav.e;
import com.etsy.android.uikit.ui.dialog.DialogActivity;

public class ConvoComposeDialogActivity extends DialogActivity implements k, a {
    private Bundle mArgs;

    /* access modifiers changed from: protected */
    public void onShowDialog(OnDismissListener onDismissListener) {
        if (getIntent() != null) {
            this.mArgs = getIntent().getExtras();
        }
        e.a((FragmentActivity) this).d().a(onDismissListener).a(this.mArgs).c();
    }

    public void onMessageSent() {
        finish();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        Fragment findFragmentByTag = getSupportFragmentManager().findFragmentByTag("convoCompose");
        if (findFragmentByTag != null) {
            findFragmentByTag.onActivityResult(i, i2, intent);
        }
    }
}
