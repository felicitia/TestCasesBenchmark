package com.etsy.android.ui.search.v2.a;

import com.etsy.android.ui.search.v2.impressions.f;
import dagger.internal.c;
import javax.a.a;

/* compiled from: SearchImpressionsNetworkModule_ProvidesSearchImpressionsEndpointFactory */
public final class e implements c<f> {
    static final /* synthetic */ boolean a = true;
    private final d b;
    private final a<com.etsy.android.lib.c.e> c;

    public e(d dVar, a<com.etsy.android.lib.c.e> aVar) {
        if (a || dVar != null) {
            this.b = dVar;
            if (a || aVar != null) {
                this.c = aVar;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    /* renamed from: a */
    public f b() {
        return (f) dagger.internal.f.a(this.b.a((com.etsy.android.lib.c.e) this.c.b()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static c<f> a(d dVar, a<com.etsy.android.lib.c.e> aVar) {
        return new e(dVar, aVar);
    }
}
