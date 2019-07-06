package com.etsy.android.ui.convos.convolistredesign;

import dagger.internal.c;
import dagger.internal.f;
import javax.a.a;

/* compiled from: ConvosListFragmentModule_ProvidesConvosListViewFactory */
public final class k implements c<o> {
    static final /* synthetic */ boolean a = true;
    private final a<ConvosListFragment> b;

    public k(a<ConvosListFragment> aVar) {
        if (a || aVar != null) {
            this.b = aVar;
            return;
        }
        throw new AssertionError();
    }

    /* renamed from: a */
    public o b() {
        return (o) f.a(g.a((ConvosListFragment) this.b.b()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static c<o> a(a<ConvosListFragment> aVar) {
        return new k(aVar);
    }
}
