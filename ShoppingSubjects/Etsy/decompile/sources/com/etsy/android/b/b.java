package com.etsy.android.b;

import com.etsy.android.ui.giftcards.GiftCardCreateActivity;
import com.etsy.android.uikit.nav.TrackingBaseActivity;
import dagger.internal.c;
import dagger.internal.f;
import javax.a.a;

/* compiled from: GiftCardCreateActivityModule_ProvidesGiftCardCreateActivityFactory */
public final class b implements c<TrackingBaseActivity> {
    static final /* synthetic */ boolean a = true;
    private final a b;
    private final a<GiftCardCreateActivity> c;

    public b(a aVar, a<GiftCardCreateActivity> aVar2) {
        if (a || aVar != null) {
            this.b = aVar;
            if (a || aVar2 != null) {
                this.c = aVar2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    /* renamed from: a */
    public TrackingBaseActivity b() {
        return (TrackingBaseActivity) f.a(this.b.a((GiftCardCreateActivity) this.c.b()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static c<TrackingBaseActivity> a(a aVar, a<GiftCardCreateActivity> aVar2) {
        return new b(aVar, aVar2);
    }
}
