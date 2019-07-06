package com.etsy.android.ui.core;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;

final /* synthetic */ class n implements OnDismissListener {
    private final SingleListingCheckoutActivity a;
    private final OnDismissListener b;

    n(SingleListingCheckoutActivity singleListingCheckoutActivity, OnDismissListener onDismissListener) {
        this.a = singleListingCheckoutActivity;
        this.b = onDismissListener;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        this.a.lambda$buildInterceptingDismissListener$0$SingleListingCheckoutActivity(this.b, dialogInterface);
    }
}
