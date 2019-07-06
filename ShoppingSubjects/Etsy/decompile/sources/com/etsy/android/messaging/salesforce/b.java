package com.etsy.android.messaging.salesforce;

import android.content.Context;
import dagger.internal.c;
import javax.a.a;

/* compiled from: PushNotificationPopulator_Factory */
public final class b implements c<a> {
    static final /* synthetic */ boolean a = true;
    private final a<Context> b;
    private final a<com.etsy.android.lib.util.b.a> c;
    private final a<com.etsy.android.lib.util.sharedprefs.b> d;

    public b(a<Context> aVar, a<com.etsy.android.lib.util.b.a> aVar2, a<com.etsy.android.lib.util.sharedprefs.b> aVar3) {
        if (a || aVar != null) {
            this.b = aVar;
            if (a || aVar2 != null) {
                this.c = aVar2;
                if (a || aVar3 != null) {
                    this.d = aVar3;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    /* renamed from: a */
    public a b() {
        return new a((Context) this.b.b(), (com.etsy.android.lib.util.b.a) this.c.b(), (com.etsy.android.lib.util.sharedprefs.b) this.d.b());
    }

    public static c<a> a(a<Context> aVar, a<com.etsy.android.lib.util.b.a> aVar2, a<com.etsy.android.lib.util.sharedprefs.b> aVar3) {
        return new b(aVar, aVar2, aVar3);
    }
}
