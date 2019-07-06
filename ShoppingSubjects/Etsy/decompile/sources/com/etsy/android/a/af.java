package com.etsy.android.a;

import android.content.Context;
import com.etsy.android.lib.core.v;
import com.etsy.android.util.f;
import dagger.internal.c;
import javax.a.a;

/* compiled from: AppModule_ProvideSessionFactory */
public final class af implements c<v> {
    static final /* synthetic */ boolean a = true;
    private final a<Context> b;
    private final a<f> c;
    private final a<com.etsy.android.lib.h.a> d;

    public af(a<Context> aVar, a<f> aVar2, a<com.etsy.android.lib.h.a> aVar3) {
        if (a || aVar != null) {
            this.b = aVar;
            if (a || aVar2 != null) {
                this.c = aVar2;
                if (a || aVar3 != null) {
                    this.d = aVar3;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    /* renamed from: a */
    public v b() {
        return (v) dagger.internal.f.a(v.a((Context) this.b.b(), (f) this.c.b(), (com.etsy.android.lib.h.a) this.d.b()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static c<v> a(a<Context> aVar, a<f> aVar2, a<com.etsy.android.lib.h.a> aVar3) {
        return new af(aVar, aVar2, aVar3);
    }
}
