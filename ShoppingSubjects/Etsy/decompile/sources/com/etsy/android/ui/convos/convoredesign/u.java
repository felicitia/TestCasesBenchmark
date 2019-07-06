package com.etsy.android.ui.convos.convoredesign;

import com.etsy.android.messaging.h;
import dagger.internal.c;
import javax.a.a;

/* compiled from: ConvoNotificationRepo_Factory */
public final class u implements c<t> {
    static final /* synthetic */ boolean a = true;
    private final a<h> b;

    public u(a<h> aVar) {
        if (a || aVar != null) {
            this.b = aVar;
            return;
        }
        throw new AssertionError();
    }

    /* renamed from: a */
    public t b() {
        return new t((h) this.b.b());
    }

    public static c<t> a(a<h> aVar) {
        return new u(aVar);
    }
}
