package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.xh.e;

final class yz implements yi {
    private final yk a;
    private final String b;
    private final za c;

    yz(yk ykVar, String str, Object[] objArr) {
        this.a = ykVar;
        this.b = str;
        this.c = new za(ykVar.getClass(), str, objArr);
    }

    public final int a() {
        return (this.c.d & 1) == 1 ? e.h : e.i;
    }

    public final boolean b() {
        return (this.c.d & 2) == 2;
    }

    public final yk c() {
        return this.a;
    }

    /* access modifiers changed from: 0000 */
    public final za d() {
        return this.c;
    }

    public final int e() {
        return this.c.h;
    }

    public final int f() {
        return this.c.i;
    }

    public final int g() {
        return this.c.e;
    }

    public final int h() {
        return this.c.j;
    }

    public final int i() {
        return this.c.m;
    }

    /* access modifiers changed from: 0000 */
    public final int[] j() {
        return this.c.n;
    }

    public final int k() {
        return this.c.l;
    }

    public final int l() {
        return this.c.k;
    }
}
