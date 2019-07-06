package com.etsy.android.ui.cart.viewholders;

import android.support.v4.app.FragmentActivity;
import com.etsy.android.lib.models.apiv3.cart.AndroidPayData;

final /* synthetic */ class a implements Runnable {
    private final CheckoutSectionAndroidPayViewHolder a;
    private final FragmentActivity b;
    private final AndroidPayData c;

    a(CheckoutSectionAndroidPayViewHolder checkoutSectionAndroidPayViewHolder, FragmentActivity fragmentActivity, AndroidPayData androidPayData) {
        this.a = checkoutSectionAndroidPayViewHolder;
        this.b = fragmentActivity;
        this.c = androidPayData;
    }

    public void run() {
        this.a.lambda$bindCartGroupItem$0$CheckoutSectionAndroidPayViewHolder(this.b, this.c);
    }
}
