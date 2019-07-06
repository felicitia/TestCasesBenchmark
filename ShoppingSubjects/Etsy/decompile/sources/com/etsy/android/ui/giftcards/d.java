package com.etsy.android.ui.giftcards;

import com.etsy.android.lib.models.GiftCardAmounts;
import io.reactivex.functions.Consumer;

final /* synthetic */ class d implements Consumer {
    private final GiftCardCreateActivity a;

    d(GiftCardCreateActivity giftCardCreateActivity) {
        this.a = giftCardCreateActivity;
    }

    public void accept(Object obj) {
        this.a.lambda$requestGiftCardAmounts$3$GiftCardCreateActivity((GiftCardAmounts) obj);
    }
}
