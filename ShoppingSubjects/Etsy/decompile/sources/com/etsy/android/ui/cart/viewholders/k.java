package com.etsy.android.ui.cart.viewholders;

import android.view.View;
import android.view.View.OnClickListener;
import com.etsy.android.lib.models.apiv3.cart.GiftOptions;

final /* synthetic */ class k implements OnClickListener {
    private final GiftOptionsViewHolder a;
    private final GiftOptions b;

    k(GiftOptionsViewHolder giftOptionsViewHolder, GiftOptions giftOptions) {
        this.a = giftOptionsViewHolder;
        this.b = giftOptions;
    }

    public void onClick(View view) {
        this.a.lambda$bindCartGroupItem$6$GiftOptionsViewHolder(this.b, view);
    }
}
