package com.etsy.android.ui.giftcards;

import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

final /* synthetic */ class a implements OnCheckedChangeListener {
    private final GiftCardCreateActivity a;

    a(GiftCardCreateActivity giftCardCreateActivity) {
        this.a = giftCardCreateActivity;
    }

    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        this.a.lambda$onCreate$0$GiftCardCreateActivity(radioGroup, i);
    }
}
