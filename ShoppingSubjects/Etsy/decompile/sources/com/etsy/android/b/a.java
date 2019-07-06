package com.etsy.android.b;

import com.etsy.android.lib.c.d;
import com.etsy.android.ui.giftcards.GiftCardCreateActivity;
import com.etsy.android.uikit.nav.TrackingBaseActivity;
import kotlin.jvm.internal.p;

/* compiled from: GiftCardCreateActivityModule.kt */
public final class a {
    public final TrackingBaseActivity a(GiftCardCreateActivity giftCardCreateActivity) {
        p.b(giftCardCreateActivity, "activity");
        return giftCardCreateActivity;
    }

    public final d a(d dVar) {
        p.b(dVar, "retrofit");
        Object a = dVar.a().a(d.class);
        p.a(a, "retrofit.v2retrofit.crea…CardEndpoint::class.java)");
        return (d) a;
    }
}
