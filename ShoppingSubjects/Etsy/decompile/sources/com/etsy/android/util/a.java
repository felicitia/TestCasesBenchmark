package com.etsy.android.util;

import com.etsy.android.lib.logger.l;
import dagger.internal.c;

/* compiled from: AppLifecycleObserver_Factory */
public final class a implements c<AppLifecycleObserver> {
    static final /* synthetic */ boolean a = true;
    private final javax.a.a<l> b;

    public a(javax.a.a<l> aVar) {
        if (a || aVar != null) {
            this.b = aVar;
            return;
        }
        throw new AssertionError();
    }

    /* renamed from: a */
    public AppLifecycleObserver b() {
        return new AppLifecycleObserver((l) this.b.b());
    }

    public static c<AppLifecycleObserver> a(javax.a.a<l> aVar) {
        return new a(aVar);
    }
}
