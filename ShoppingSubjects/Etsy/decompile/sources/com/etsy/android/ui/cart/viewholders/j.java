package com.etsy.android.ui.cart.viewholders;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import com.etsy.android.lib.models.apiv3.cart.CartGroupItem;

final /* synthetic */ class j implements OnCheckedChangeListener {
    private final GiftOptionsViewHolder a;
    private final CartGroupItem b;

    j(GiftOptionsViewHolder giftOptionsViewHolder, CartGroupItem cartGroupItem) {
        this.a = giftOptionsViewHolder;
        this.b = cartGroupItem;
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        this.a.lambda$bindCartGroupItem$5$GiftOptionsViewHolder(this.b, compoundButton, z);
    }
}
