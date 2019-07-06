package com.etsy.android.b;

import com.etsy.android.lib.c.d;
import dagger.internal.f;
import javax.a.a;

/* compiled from: GiftCardCreateActivityModule_ProvidesGiftCardEndpointFactory */
public final class c implements dagger.internal.c<d> {
    static final /* synthetic */ boolean a = true;
    private final a b;
    private final a<d> c;

    public c(a aVar, a<d> aVar2) {
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
    public d b() {
        return (d) f.a(this.b.a((d) this.c.b()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static dagger.internal.c<d> a(a aVar, a<d> aVar2) {
        return new c(aVar, aVar2);
    }
}
