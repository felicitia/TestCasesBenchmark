package com.etsy.android.ui.giftcards;

import com.etsy.android.lib.models.GiftCardDesigns;
import io.reactivex.functions.Consumer;

final /* synthetic */ class b implements Consumer {
    private final GiftCardCreateActivity a;

    b(GiftCardCreateActivity giftCardCreateActivity) {
        this.a = giftCardCreateActivity;
    }

    public void accept(Object obj) {
        this.a.lambda$requestGiftCardDesigns$1$GiftCardCreateActivity((GiftCardDesigns) obj);
    }
}
