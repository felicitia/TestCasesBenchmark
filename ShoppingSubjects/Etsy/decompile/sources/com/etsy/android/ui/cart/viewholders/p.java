package com.etsy.android.ui.cart.viewholders;

import android.widget.CompoundButton;
import com.etsy.android.lib.models.apiv3.cart.CartGroupItem;
import kotlin.jvm.a.b;

final /* synthetic */ class p implements b {
    private final PaymentOptionsViewHolder a;
    private final CartGroupItem b;

    p(PaymentOptionsViewHolder paymentOptionsViewHolder, CartGroupItem cartGroupItem) {
        this.a = paymentOptionsViewHolder;
        this.b = cartGroupItem;
    }

    public Object invoke(Object obj) {
        return this.a.lambda$bindCartGroupItem$0$PaymentOptionsViewHolder(this.b, (CompoundButton) obj);
    }
}
