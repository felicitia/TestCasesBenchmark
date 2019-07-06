package com.etsy.android.ui.convos.convolistredesign;

import com.etsy.android.lib.c.e;
import com.etsy.android.lib.f.a;
import com.etsy.android.lib.logger.l;
import com.etsy.android.lib.util.sharedprefs.b;
import com.etsy.android.messaging.h;
import com.etsy.android.ui.convos.convoredesign.f;
import com.etsy.android.ui.convos.convoredesign.t;
import kotlin.jvm.internal.p;

/* compiled from: ConvosListFragmentModule.kt */
public final class g {
    public static final g a = new g();

    private g() {
    }

    public static final m a(b bVar, o oVar, a aVar, t tVar, l lVar) {
        p.b(bVar, "repository");
        p.b(oVar, "view");
        p.b(aVar, "schedulers");
        p.b(tVar, "convoNotificationRepo");
        p.b(lVar, "logCat");
        m mVar = new m(bVar, oVar, aVar, tVar, lVar);
        return mVar;
    }

    public static final o a(ConvosListFragment convosListFragment) {
        p.b(convosListFragment, "convoListFragment");
        return convosListFragment;
    }

    public static final b a(f fVar, e eVar, b bVar) {
        p.b(fVar, "convoDao");
        p.b(eVar, "moshiRetrofit");
        p.b(bVar, "sharedPreferencesProvider");
        return new b(fVar, eVar, bVar);
    }

    public static final t a(h hVar) {
        p.b(hVar, "notificationRepo");
        return new t(hVar);
    }
}
