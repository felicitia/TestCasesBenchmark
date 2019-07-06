package com.etsy.android.util;

import com.etsy.android.lib.push.f;
import dagger.internal.c;
import javax.a.a;

/* compiled from: SessionManager_Factory */
public final class g implements c<f> {
    static final /* synthetic */ boolean a = true;
    private final a<f> b;

    public g(a<f> aVar) {
        if (a || aVar != null) {
            this.b = aVar;
            return;
        }
        throw new AssertionError();
    }

    /* renamed from: a */
    public f b() {
        return new f((f) this.b.b());
    }

    public static c<f> a(a<f> aVar) {
        return new g(aVar);
    }
}
