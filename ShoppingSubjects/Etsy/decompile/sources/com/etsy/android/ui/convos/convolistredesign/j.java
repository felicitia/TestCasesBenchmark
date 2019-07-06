package com.etsy.android.ui.convos.convolistredesign;

import com.etsy.android.lib.logger.l;
import com.etsy.android.ui.convos.convoredesign.t;
import dagger.internal.c;
import dagger.internal.f;
import javax.a.a;

/* compiled from: ConvosListFragmentModule_ProvidesConvosListPresenterFactory */
public final class j implements c<m> {
    static final /* synthetic */ boolean a = true;
    private final a<b> b;
    private final a<o> c;
    private final a<com.etsy.android.lib.f.a> d;
    private final a<t> e;
    private final a<l> f;

    public j(a<b> aVar, a<o> aVar2, a<com.etsy.android.lib.f.a> aVar3, a<t> aVar4, a<l> aVar5) {
        if (a || aVar != null) {
            this.b = aVar;
            if (a || aVar2 != null) {
                this.c = aVar2;
                if (a || aVar3 != null) {
                    this.d = aVar3;
                    if (a || aVar4 != null) {
                        this.e = aVar4;
                        if (a || aVar5 != null) {
                            this.f = aVar5;
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
        throw new AssertionError();
    }

    /* renamed from: a */
    public m b() {
        return (m) f.a(g.a((b) this.b.b(), (o) this.c.b(), (com.etsy.android.lib.f.a) this.d.b(), (t) this.e.b(), (l) this.f.b()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static c<m> a(a<b> aVar, a<o> aVar2, a<com.etsy.android.lib.f.a> aVar3, a<t> aVar4, a<l> aVar5) {
        j jVar = new j(aVar, aVar2, aVar3, aVar4, aVar5);
        return jVar;
    }
}
