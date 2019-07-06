package com.etsy.android.a;

import android.content.Context;
import com.etsy.android.lib.config.g;
import dagger.internal.c;
import dagger.internal.f;
import javax.a.a;

/* compiled from: AppModule_ProvideInstallInfoFactory */
public final class aa implements c<g> {
    static final /* synthetic */ boolean a = true;
    private final v b;
    private final a<Context> c;

    public aa(v vVar, a<Context> aVar) {
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
    public g b() {
        return (g) f.a(this.b.a((Context) this.c.b()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static c<g> a(v vVar, a<Context> aVar) {
        return new aa(vVar, aVar);
    }
}
