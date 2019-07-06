package com.etsy.android.a;

import android.content.Context;
import com.etsy.android.lib.logger.legacy.b;
import dagger.internal.c;
import dagger.internal.f;
import javax.a.a;

/* compiled from: AppModule_ProvideLegacyEtsyLoggerFactory */
public final class ab implements c<b> {
    static final /* synthetic */ boolean a = true;
    private final a<Context> b;

    public ab(a<Context> aVar) {
        if (a || aVar != null) {
            this.b = aVar;
            return;
        }
        throw new AssertionError();
    }

    /* renamed from: a */
    public b b() {
        return (b) f.a(v.b((Context) this.b.b()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static c<b> a(a<Context> aVar) {
        return new ab(aVar);
    }
}
