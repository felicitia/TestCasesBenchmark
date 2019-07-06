package com.etsy.android.ui.convos.convoredesign;

import com.etsy.android.lib.c.e;
import dagger.internal.c;
import dagger.internal.f;
import javax.a.a;

/* compiled from: ConvoThreadFragmentModule_ProvidesConversationEndpointFactory */
public final class z implements c<b> {
    static final /* synthetic */ boolean a = true;
    private final a<e> b;

    public z(a<e> aVar) {
        if (a || aVar != null) {
            this.b = aVar;
            return;
        }
        throw new AssertionError();
    }

    /* renamed from: a */
    public b b() {
        return (b) f.a(x.a((e) this.b.b()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static c<b> a(a<e> aVar) {
        return new z(aVar);
    }
}
