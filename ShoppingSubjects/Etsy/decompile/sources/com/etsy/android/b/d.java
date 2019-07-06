package com.etsy.android.b;

import com.etsy.android.lib.models.GiftCardAmounts;
import com.etsy.android.lib.models.GiftCardDesigns;
import io.reactivex.q;
import retrofit2.b.f;

/* compiled from: GiftCardEndpoint.kt */
public interface d {
    @f(a = "/v2/apps/giftcards/designs")
    q<GiftCardDesigns> a();

    @f(a = "/v2/apps/types/enum/giftcard_amount")
    q<GiftCardAmounts> b();
}
