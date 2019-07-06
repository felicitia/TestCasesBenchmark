package com.etsy.android.ui.cart.viewholders;

import com.etsy.android.lib.models.apiv3.cart.CartGroupItem;
import com.jakewharton.rxbinding2.widget.s;
import io.reactivex.functions.Consumer;

final /* synthetic */ class h implements Consumer {
    private final GiftOptionsViewHolder a;
    private final CartGroupItem b;

    h(GiftOptionsViewHolder giftOptionsViewHolder, CartGroupItem cartGroupItem) {
        this.a = giftOptionsViewHolder;
        this.b = cartGroupItem;
    }

    public void accept(Object obj) {
        this.a.lambda$bindCartGroupItem$4$GiftOptionsViewHolder(this.b, (s) obj);
    }
}
