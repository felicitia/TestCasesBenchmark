package com.etsy.android.a;

import com.etsy.android.lib.auth.b;
import dagger.internal.c;
import dagger.internal.f;

/* compiled from: AppModule_ProvideLoginRequestRepositoryFactory */
public final class ac implements c<b> {
    static final /* synthetic */ boolean a = true;
    private final v b;

    public ac(v vVar) {
        if (a || vVar != null) {
            this.b = vVar;
            return;
        }
        throw new AssertionError();
    }

    /* renamed from: a */
    public b b() {
        return (b) f.a(this.b.a(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static c<b> a(v vVar) {
        return new ac(vVar);
    }
}
