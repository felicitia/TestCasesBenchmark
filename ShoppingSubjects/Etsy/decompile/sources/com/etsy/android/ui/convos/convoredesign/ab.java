package com.etsy.android.ui.convos.convoredesign;

import dagger.internal.c;
import dagger.internal.f;
import javax.a.a;

/* compiled from: ConvoThreadFragmentModule_ProvidesConvoViewFactory */
public final class ab implements c<ai> {
    static final /* synthetic */ boolean a = true;
    private final a<ConvoThreadFragment2> b;

    public ab(a<ConvoThreadFragment2> aVar) {
        if (a || aVar != null) {
            this.b = aVar;
            return;
        }
        throw new AssertionError();
    }

    /* renamed from: a */
    public ai b() {
        return (ai) f.a(x.b((ConvoThreadFragment2) this.b.b()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static c<ai> a(a<ConvoThreadFragment2> aVar) {
        return new ab(aVar);
    }
}
