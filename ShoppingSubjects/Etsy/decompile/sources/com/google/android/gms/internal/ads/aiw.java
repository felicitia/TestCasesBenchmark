package com.google.android.gms.internal.ads;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@bu
public final class aiw {
    private long a;
    private Bundle b;
    private int c;
    private List<String> d;
    private boolean e;
    private int f;
    private boolean g;
    private String h;
    private zzmq i;
    private Location j;
    private String k;
    private Bundle l;
    private Bundle m;
    private List<String> n;
    private String o;
    private String p;
    private boolean q;

    public aiw() {
        this.a = -1;
        this.b = new Bundle();
        this.c = -1;
        this.d = new ArrayList();
        this.e = false;
        this.f = -1;
        this.g = false;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = new Bundle();
        this.m = new Bundle();
        this.n = new ArrayList();
        this.o = null;
        this.p = null;
        this.q = false;
    }

    public aiw(zzjj zzjj) {
        this.a = zzjj.zzapw;
        this.b = zzjj.extras;
        this.c = zzjj.zzapx;
        this.d = zzjj.zzapy;
        this.e = zzjj.zzapz;
        this.f = zzjj.zzaqa;
        this.g = zzjj.zzaqb;
        this.h = zzjj.zzaqc;
        this.i = zzjj.zzaqd;
        this.j = zzjj.zzaqe;
        this.k = zzjj.zzaqf;
        this.l = zzjj.zzaqg;
        this.m = zzjj.zzaqh;
        this.n = zzjj.zzaqi;
        this.o = zzjj.zzaqj;
        this.p = zzjj.zzaqk;
    }

    public final aiw a(@Nullable Location location) {
        this.j = null;
        return this;
    }

    public final zzjj a() {
        long j2 = this.a;
        Bundle bundle = this.b;
        int i2 = this.c;
        List<String> list = this.d;
        boolean z = this.e;
        int i3 = this.f;
        boolean z2 = this.g;
        String str = this.h;
        zzmq zzmq = this.i;
        Location location = this.j;
        String str2 = this.k;
        Bundle bundle2 = this.l;
        Bundle bundle3 = this.m;
        Bundle bundle4 = bundle3;
        Bundle bundle5 = bundle4;
        zzjj zzjj = new zzjj(7, j2, bundle, i2, list, z, i3, z2, str, zzmq, location, str2, bundle2, bundle5, this.n, this.o, this.p, false);
        return zzjj;
    }
}
