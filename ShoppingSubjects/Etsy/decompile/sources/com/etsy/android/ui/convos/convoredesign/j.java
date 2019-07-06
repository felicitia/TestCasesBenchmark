package com.etsy.android.ui.convos.convoredesign;

import dagger.internal.c;
import dagger.internal.f;
import javax.a.a;

/* compiled from: ConvoDbModule_ProvideConvoDaoFactory */
public final class j implements c<f> {
    static final /* synthetic */ boolean a = true;
    private final a<ConvoDatabase> b;

    public j(a<ConvoDatabase> aVar) {
        if (a || aVar != null) {
            this.b = aVar;
            return;
        }
        throw new AssertionError();
    }

    /* renamed from: a */
    public f b() {
        return (f) f.a(i.a((ConvoDatabase) this.b.b()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static c<f> a(a<ConvoDatabase> aVar) {
        return new j(aVar);
    }
}
