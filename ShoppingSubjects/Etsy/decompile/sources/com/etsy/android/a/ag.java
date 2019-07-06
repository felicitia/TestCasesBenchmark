package com.etsy.android.a;

import dagger.internal.c;
import dagger.internal.f;

/* compiled from: AppModule_ProvideTimeFactory */
public final class ag implements c<com.etsy.android.lib.util.ag> {
    static final /* synthetic */ boolean a = true;
    private final v b;

    public ag(v vVar) {
        if (a || vVar != null) {
            this.b = vVar;
            return;
        }
        throw new AssertionError();
    }

    /* renamed from: a */
    public com.etsy.android.lib.util.ag b() {
        return (com.etsy.android.lib.util.ag) f.a(this.b.b(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static c<com.etsy.android.lib.util.ag> a(v vVar) {
        return new ag(vVar);
    }
}
