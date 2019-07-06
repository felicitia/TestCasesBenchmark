package com.etsy.android.ui.convos.convolistredesign;

import com.etsy.android.lib.c.e;
import com.etsy.android.lib.util.sharedprefs.b;
import com.etsy.android.ui.convos.convoredesign.f;
import dagger.internal.c;
import javax.a.a;

/* compiled from: ConvosListFragmentModule_ProvideConversationListRepositoryFactory */
public final class h implements c<b> {
    static final /* synthetic */ boolean a = true;
    private final a<f> b;
    private final a<e> c;
    private final a<b> d;

    public h(a<f> aVar, a<e> aVar2, a<b> aVar3) {
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
    public b b() {
        return (b) dagger.internal.f.a(g.a((f) this.b.b(), (e) this.c.b(), (b) this.d.b()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static c<b> a(a<f> aVar, a<e> aVar2, a<b> aVar3) {
        return new h(aVar, aVar2, aVar3);
    }
}
