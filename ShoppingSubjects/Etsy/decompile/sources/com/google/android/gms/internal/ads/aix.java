package com.google.android.gms.internal.ads;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.search.b;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

@bu
public final class aix {
    public static final aix a = new aix();

    @VisibleForTesting
    protected aix() {
    }

    public static zzjj a(Context context, ajk ajk) {
        List list;
        Context context2;
        String str;
        ajk ajk2 = ajk;
        Date a2 = ajk.a();
        long time = a2 != null ? a2.getTime() : -1;
        String b = ajk.b();
        int c = ajk.c();
        Set d = ajk.d();
        if (!d.isEmpty()) {
            list = Collections.unmodifiableList(new ArrayList(d));
            context2 = context;
        } else {
            context2 = context;
            list = null;
        }
        boolean a3 = ajk2.a(context2);
        int l = ajk.l();
        Location e = ajk.e();
        Bundle a4 = ajk2.a(AdMobAdapter.class);
        boolean f = ajk.f();
        String g = ajk.g();
        b i = ajk.i();
        zzmq zzmq = i != null ? new zzmq(i) : null;
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            String packageName = applicationContext.getPackageName();
            ajh.a();
            str = jp.a(Thread.currentThread().getStackTrace(), packageName);
        } else {
            str = null;
        }
        zzjj zzjj = new zzjj(7, time, a4, c, list, a3, l, f, g, zzmq, e, b, ajk.k(), ajk.m(), Collections.unmodifiableList(new ArrayList(ajk.n())), ajk.h(), str, ajk.o());
        return zzjj;
    }
}
