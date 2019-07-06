package com.etsy.android.a;

import androidx.work.j;
import dagger.internal.c;
import dagger.internal.f;

/* compiled from: AppModule_ProvideWorkManagerFactory */
public final class ah implements c<j> {
    static final /* synthetic */ boolean a = true;
    private final v b;

    public ah(v vVar) {
        if (a || vVar != null) {
            this.b = vVar;
            return;
        }
        throw new AssertionError();
    }

    /* renamed from: a */
    public j b() {
        return (j) f.a(this.b.c(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static c<j> a(v vVar) {
        return new ah(vVar);
    }
}
