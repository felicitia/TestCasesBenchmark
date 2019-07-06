package com.etsy.android.a;

import android.content.Context;
import com.etsy.android.BOEApplication;
import dagger.internal.c;
import dagger.internal.f;
import javax.a.a;

/* compiled from: AppModule_ProvideContextFactory */
public final class w implements c<Context> {
    static final /* synthetic */ boolean a = true;
    private final v b;
    private final a<BOEApplication> c;

    public w(v vVar, a<BOEApplication> aVar) {
        if (a || vVar != null) {
            this.b = vVar;
            if (a || aVar != null) {
                this.c = aVar;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    /* renamed from: a */
    public Context b() {
        return (Context) f.a(this.b.b((BOEApplication) this.c.b()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static c<Context> a(v vVar, a<BOEApplication> aVar) {
        return new w(vVar, aVar);
    }
}
