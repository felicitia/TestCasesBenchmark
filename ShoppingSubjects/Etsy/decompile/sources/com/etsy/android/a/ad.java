package com.etsy.android.a;

import android.content.Context;
import com.etsy.android.lib.util.NetworkUtils;
import dagger.internal.c;
import dagger.internal.f;
import javax.a.a;

/* compiled from: AppModule_ProvideNetworkUtilsFactory */
public final class ad implements c<NetworkUtils> {
    static final /* synthetic */ boolean a = true;
    private final v b;
    private final a<Context> c;

    public ad(v vVar, a<Context> aVar) {
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
    public NetworkUtils b() {
        return (NetworkUtils) f.a(this.b.c((Context) this.c.b()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static c<NetworkUtils> a(v vVar, a<Context> aVar) {
        return new ad(vVar, aVar);
    }
}
