package com.etsy.android.a;

import com.etsy.android.BOEApplication;
import com.etsy.android.lib.core.EtsyApplication;
import dagger.internal.c;
import dagger.internal.f;
import javax.a.a;

/* compiled from: AppModule_ProvideEtsyApplicationFactory */
public final class x implements c<EtsyApplication> {
    static final /* synthetic */ boolean a = true;
    private final v b;
    private final a<BOEApplication> c;

    public x(v vVar, a<BOEApplication> aVar) {
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
    public EtsyApplication b() {
        return (EtsyApplication) f.a(this.b.a((BOEApplication) this.c.b()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static c<EtsyApplication> a(v vVar, a<BOEApplication> aVar) {
        return new x(vVar, aVar);
    }
}
