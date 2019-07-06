package com.google.android.gms.internal.ads;

import android.content.Context;
import android.support.annotation.Nullable;
import com.android.volley.toolbox.Volley;
import com.etsy.android.lib.core.http.request.BaseHttpRequest;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.File;
import java.util.Map;
import java.util.concurrent.Executor;

@bu
public final class in {
    private static apa a;
    private static final Object b = new Object();
    @Deprecated
    private static final it<Void> c = new io();

    public in(Context context) {
        if (context.getApplicationContext() != null) {
            context = context.getApplicationContext();
        }
        a(context);
    }

    @VisibleForTesting
    private static apa a(Context context) {
        apa apa;
        apa apa2;
        synchronized (b) {
            if (a == null) {
                akl.a(context);
                if (((Boolean) ajh.f().a(akl.cI)).booleanValue()) {
                    apa2 = ih.a(context);
                } else {
                    apa2 = new apa(new iu(new File(context.getCacheDir(), Volley.DEFAULT_CACHE_DIR)), new fs((ey) new ol()));
                    apa2.a();
                }
                a = apa2;
            }
            apa = a;
        }
        return apa;
    }

    public final kt<String> a(int i, String str, @Nullable Map<String, String> map, @Nullable byte[] bArr) {
        String str2 = str;
        iv ivVar = new iv(null);
        ir irVar = new ir(this, str2, ivVar);
        jt jtVar = new jt(null);
        is isVar = new is(this, i, str2, ivVar, irVar, bArr, map, jtVar);
        if (jt.c()) {
            try {
                jtVar.a(str2, BaseHttpRequest.GET, isVar.b(), isVar.a());
            } catch (zza e) {
                gv.e(e.getMessage());
            }
        }
        a.a(isVar);
        return ivVar;
    }

    @Deprecated
    public final <T> kt<T> a(String str, it<T> itVar) {
        le leVar = new le();
        a.a(new iw(str, leVar));
        return ki.a(ki.a((kt<A>) leVar, (ke<A, B>) new iq<A,B>(this, itVar), (Executor) hb.a), Throwable.class, (kd<? super X, ? extends V>) new ip<Object,Object>(this, itVar), kz.b);
    }

    public final kt<String> a(String str, Map<String, String> map) {
        return a(0, str, map, null);
    }
}
