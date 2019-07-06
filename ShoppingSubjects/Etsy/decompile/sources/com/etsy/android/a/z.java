package com.etsy.android.a;

import dagger.internal.c;
import dagger.internal.f;
import javax.a.a;

/* compiled from: AppModule_ProvideEtsyConfigMapFactory */
public final class z implements c<com.etsy.android.lib.config.c> {
    static final /* synthetic */ boolean a = true;
    private final v b;
    private final a<com.etsy.android.lib.config.a> c;

    public z(v vVar, a<com.etsy.android.lib.config.a> aVar) {
        if (a || vVar != null) {
            this.b = vVar;
            if (a || aVar != null) {
                this.c = aVar;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    /* renamed from: a */
    public com.etsy.android.lib.config.c b() {
        return (com.etsy.android.lib.config.c) f.a(this.b.a((com.etsy.android.lib.config.a) this.c.b()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static c<com.etsy.android.lib.config.c> a(v vVar, a<com.etsy.android.lib.config.a> aVar) {
        return new z(vVar, aVar);
    }
}
