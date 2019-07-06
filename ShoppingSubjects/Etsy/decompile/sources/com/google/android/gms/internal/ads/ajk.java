package com.google.android.gms.internal.ads;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.google.android.gms.ads.search.b;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Set;

@bu
public final class ajk {
    private final Date a;
    private final String b;
    private final int c;
    private final Set<String> d;
    private final Location e;
    private final boolean f;
    private final Bundle g;
    private final Map<Class<? extends Object>, Object> h;
    private final String i;
    private final String j;
    private final b k;
    private final int l;
    private final Set<String> m;
    private final Bundle n;
    private final Set<String> o;
    private final boolean p;

    public ajk(ajl ajl) {
        this(ajl, null);
    }

    public ajk(ajl ajl, b bVar) {
        this.a = ajl.g;
        this.b = ajl.h;
        this.c = ajl.i;
        this.d = Collections.unmodifiableSet(ajl.a);
        this.e = ajl.j;
        this.f = ajl.k;
        this.g = ajl.b;
        this.h = Collections.unmodifiableMap(ajl.c);
        this.i = ajl.l;
        this.j = ajl.m;
        this.k = bVar;
        this.l = ajl.n;
        this.m = Collections.unmodifiableSet(ajl.d);
        this.n = ajl.e;
        this.o = Collections.unmodifiableSet(ajl.f);
        this.p = ajl.o;
    }

    public final Bundle a(Class<? extends com.google.android.gms.ads.mediation.b> cls) {
        return this.g.getBundle(cls.getName());
    }

    public final Date a() {
        return this.a;
    }

    public final boolean a(Context context) {
        Set<String> set = this.m;
        ajh.a();
        return set.contains(jp.a(context));
    }

    public final String b() {
        return this.b;
    }

    public final int c() {
        return this.c;
    }

    public final Set<String> d() {
        return this.d;
    }

    public final Location e() {
        return this.e;
    }

    public final boolean f() {
        return this.f;
    }

    public final String g() {
        return this.i;
    }

    public final String h() {
        return this.j;
    }

    public final b i() {
        return this.k;
    }

    public final Map<Class<? extends Object>, Object> j() {
        return this.h;
    }

    public final Bundle k() {
        return this.g;
    }

    public final int l() {
        return this.l;
    }

    public final Bundle m() {
        return this.n;
    }

    public final Set<String> n() {
        return this.o;
    }

    public final boolean o() {
        return this.p;
    }
}
