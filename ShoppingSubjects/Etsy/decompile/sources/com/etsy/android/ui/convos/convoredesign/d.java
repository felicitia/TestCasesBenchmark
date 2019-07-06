package com.etsy.android.ui.convos.convoredesign;

import dagger.internal.c;
import javax.a.a;

/* compiled from: ConversationRepository_Factory */
public final class d implements c<c> {
    static final /* synthetic */ boolean a = true;
    private final a<b> b;
    private final a<a> c;
    private final a<f> d;

    public d(a<b> aVar, a<a> aVar2, a<f> aVar3) {
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
    public c b() {
        return new c((b) this.b.b(), (a) this.c.b(), (f) this.d.b());
    }

    public static c<c> a(a<b> aVar, a<a> aVar2, a<f> aVar3) {
        return new d(aVar, aVar2, aVar3);
    }
}
