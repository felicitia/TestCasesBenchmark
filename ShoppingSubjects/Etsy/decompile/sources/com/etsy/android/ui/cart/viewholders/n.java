package com.etsy.android.ui.cart.viewholders;

import com.etsy.android.lib.models.apiv3.cart.CartGroupItem;
import com.jakewharton.rxbinding2.widget.s;
import io.reactivex.functions.Consumer;

final /* synthetic */ class n implements Consumer {
    private final MessageToSellerViewHolder a;
    private final CartGroupItem b;

    n(MessageToSellerViewHolder messageToSellerViewHolder, CartGroupItem cartGroupItem) {
        this.a = messageToSellerViewHolder;
        this.b = cartGroupItem;
    }

    public void accept(Object obj) {
        this.a.lambda$bindCartGroupItem$1$MessageToSellerViewHolder(this.b, (s) obj);
    }
}
