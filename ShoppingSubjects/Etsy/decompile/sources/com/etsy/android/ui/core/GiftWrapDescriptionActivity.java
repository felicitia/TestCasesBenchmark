package com.etsy.android.ui.core;

import android.content.DialogInterface.OnDismissListener;
import android.support.v4.app.FragmentActivity;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.ui.nav.e;
import com.etsy.android.uikit.ui.dialog.DialogActivity;

public class GiftWrapDescriptionActivity extends DialogActivity {
    /* access modifiers changed from: protected */
    public void onShowDialog(OnDismissListener onDismissListener) {
        e.a((FragmentActivity) this).d().a(onDismissListener).b(getIntent().getStringExtra(ResponseConstants.SHOP_NAME));
    }
}
