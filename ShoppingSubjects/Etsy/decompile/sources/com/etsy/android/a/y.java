package com.etsy.android.a;

import android.content.Context;
import com.etsy.android.lib.config.a;
import com.etsy.android.lib.config.g;
import dagger.internal.c;
import dagger.internal.f;

/* compiled from: AppModule_ProvideEtsyConfigFactory */
public final class y implements c<a> {
    static final /* synthetic */ boolean a = true;
    private final v b;
    private final javax.a.a<Context> c;
    private final javax.a.a<g> d;

    public y(v vVar, javax.a.a<Context> aVar, javax.a.a<g> aVar2) {
        if (a || vVar != null) {
            this.b = vVar;
            if (a || aVar != null) {
                this.c = aVar;
                if (a || aVar2 != null) {
                    this.d = aVar2;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    /* renamed from: a */
    public a b() {
        return (a) f.a(this.b.a((Context) this.c.b(), (g) this.d.b()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static c<a> a(v vVar, javax.a.a<Context> aVar, javax.a.a<g> aVar2) {
        return new y(vVar, aVar, aVar2);
    }
}
