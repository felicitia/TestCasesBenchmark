package com.etsy.android.ui.convos.convoredesign;

import com.etsy.android.lib.c.b;
import dagger.internal.c;
import dagger.internal.f;
import javax.a.a;

/* compiled from: ConvoThreadFragmentModule_ProvidesCartEndpointFactory */
public final class y implements c<a> {
    static final /* synthetic */ boolean a = true;
    private final a<b> b;

    public y(a<b> aVar) {
        if (a || aVar != null) {
            this.b = aVar;
            return;
        }
        throw new AssertionError();
    }

    /* renamed from: a */
    public a b() {
        return (a) f.a(x.a((b) this.b.b()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static c<a> a(a<b> aVar) {
        return new y(aVar);
    }
}
