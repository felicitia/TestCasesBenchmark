package com.etsy.android.deeplinking;

import android.content.Context;
import dagger.internal.c;
import javax.a.a;

/* compiled from: Button_Factory */
public final class b implements c<a> {
    static final /* synthetic */ boolean a = true;
    private final a<Context> b;

    public b(a<Context> aVar) {
        if (a || aVar != null) {
            this.b = aVar;
            return;
        }
        throw new AssertionError();
    }

    /* renamed from: a */
    public a b() {
        return new a((Context) this.b.b());
    }

    public static c<a> a(a<Context> aVar) {
        return new b(aVar);
    }
}
