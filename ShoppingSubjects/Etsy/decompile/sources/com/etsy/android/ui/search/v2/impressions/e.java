package com.etsy.android.ui.search.v2.impressions;

import com.etsy.android.lib.logger.l;
import dagger.internal.c;
import javax.a.a;

/* compiled from: SearchImpressionRepository_Factory */
public final class e implements c<d> {
    static final /* synthetic */ boolean a = true;
    private final a<com.etsy.android.lib.config.c> b;
    private final a<l> c;
    private final a<a> d;
    private final a<f> e;

    public e(a<com.etsy.android.lib.config.c> aVar, a<l> aVar2, a<a> aVar3, a<f> aVar4) {
        if (a || aVar != null) {
            this.b = aVar;
            if (a || aVar2 != null) {
                this.c = aVar2;
                if (a || aVar3 != null) {
                    this.d = aVar3;
                    if (a || aVar4 != null) {
                        this.e = aVar4;
                        return;
                    }
                    throw new AssertionError();
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    /* renamed from: a */
    public d b() {
        return new d((com.etsy.android.lib.config.c) this.b.b(), (l) this.c.b(), (a) this.d.b(), (f) this.e.b());
    }

    public static c<d> a(a<com.etsy.android.lib.config.c> aVar, a<l> aVar2, a<a> aVar3, a<f> aVar4) {
        return new e(aVar, aVar2, aVar3, aVar4);
    }
}
