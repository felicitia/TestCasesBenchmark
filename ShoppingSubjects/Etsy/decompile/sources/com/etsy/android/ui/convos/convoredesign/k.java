package com.etsy.android.ui.convos.convoredesign;

import android.content.Context;
import dagger.internal.c;
import dagger.internal.f;
import javax.a.a;

/* compiled from: ConvoDbModule_ProvideConvoDatabaseFactory */
public final class k implements c<ConvoDatabase> {
    static final /* synthetic */ boolean a = true;
    private final a<Context> b;

    public k(a<Context> aVar) {
        if (a || aVar != null) {
            this.b = aVar;
            return;
        }
        throw new AssertionError();
    }

    /* renamed from: a */
    public ConvoDatabase b() {
        return (ConvoDatabase) f.a(i.a((Context) this.b.b()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static c<ConvoDatabase> a(a<Context> aVar) {
        return new k(aVar);
    }
}
