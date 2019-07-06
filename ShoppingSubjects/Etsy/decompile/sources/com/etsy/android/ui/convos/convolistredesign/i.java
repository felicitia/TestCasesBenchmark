package com.etsy.android.ui.convos.convolistredesign;

import com.etsy.android.messaging.h;
import com.etsy.android.ui.convos.convoredesign.t;
import dagger.internal.c;
import dagger.internal.f;
import javax.a.a;

/* compiled from: ConvosListFragmentModule_ProvideConvoNotificationRepoFactory */
public final class i implements c<t> {
    static final /* synthetic */ boolean a = true;
    private final a<h> b;

    public i(a<h> aVar) {
        if (a || aVar != null) {
            this.b = aVar;
            return;
        }
        throw new AssertionError();
    }

    /* renamed from: a */
    public t b() {
        return (t) f.a(g.a((h) this.b.b()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static c<t> a(a<h> aVar) {
        return new i(aVar);
    }
}
