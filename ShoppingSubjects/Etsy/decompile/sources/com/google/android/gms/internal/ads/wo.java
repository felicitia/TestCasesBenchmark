package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.List;

final class wo implements zc {
    private final wl a;
    private int b;
    private int c;
    private int d = 0;

    private wo(wl wlVar) {
        this.a = (wl) xj.a(wlVar, "input");
        this.a.c = this;
    }

    public static wo a(wl wlVar) {
        return wlVar.c != null ? wlVar.c : new wo(wlVar);
    }

    private final Object a(zzbes zzbes, Class<?> cls, ww wwVar) throws IOException {
        switch (wp.a[zzbes.ordinal()]) {
            case 1:
                return Boolean.valueOf(k());
            case 2:
                return n();
            case 3:
                return Double.valueOf(d());
            case 4:
                return Integer.valueOf(p());
            case 5:
                return Integer.valueOf(j());
            case 6:
                return Long.valueOf(i());
            case 7:
                return Float.valueOf(e());
            case 8:
                return Integer.valueOf(h());
            case 9:
                return Long.valueOf(g());
            case 10:
                a(2);
                return c(yx.a().a(cls), wwVar);
            case 11:
                return Integer.valueOf(q());
            case 12:
                return Long.valueOf(r());
            case 13:
                return Integer.valueOf(s());
            case 14:
                return Long.valueOf(t());
            case 15:
                return m();
            case 16:
                return Integer.valueOf(o());
            case 17:
                return Long.valueOf(f());
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    private final void a(int i) throws IOException {
        if ((this.b & 7) != i) {
            throw zzbbu.zzadq();
        }
    }

    private final void a(List<String> list, boolean z) throws IOException {
        int a2;
        int a3;
        if ((this.b & 7) != 2) {
            throw zzbbu.zzadq();
        } else if (!(list instanceof xu) || z) {
            do {
                list.add(z ? m() : l());
                if (!this.a.t()) {
                    a2 = this.a.a();
                } else {
                    return;
                }
            } while (a2 == this.b);
            this.d = a2;
        } else {
            xu xuVar = (xu) list;
            do {
                xuVar.a(n());
                if (!this.a.t()) {
                    a3 = this.a.a();
                } else {
                    return;
                }
            } while (a3 == this.b);
            this.d = a3;
        }
    }

    private static void b(int i) throws IOException {
        if ((i & 7) != 0) {
            throw zzbbu.zzadr();
        }
    }

    private final <T> T c(zd<T> zdVar, ww wwVar) throws IOException {
        int m = this.a.m();
        if (this.a.a >= this.a.b) {
            throw new zzbbu("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
        }
        int c2 = this.a.c(m);
        T a2 = zdVar.a();
        this.a.a++;
        zdVar.a(a2, this, wwVar);
        zdVar.c(a2);
        this.a.a(0);
        this.a.a--;
        this.a.d(c2);
        return a2;
    }

    private static void c(int i) throws IOException {
        if ((i & 3) != 0) {
            throw zzbbu.zzadr();
        }
    }

    private final <T> T d(zd<T> zdVar, ww wwVar) throws IOException {
        int i = this.c;
        this.c = ((this.b >>> 3) << 3) | 4;
        try {
            T a2 = zdVar.a();
            zdVar.a(a2, this, wwVar);
            zdVar.c(a2);
            if (this.b == this.c) {
                return a2;
            }
            throw zzbbu.zzadr();
        } finally {
            this.c = i;
        }
    }

    private final void d(int i) throws IOException {
        if (this.a.u() != i) {
            throw zzbbu.zzadl();
        }
    }

    public final int a() throws IOException {
        if (this.d != 0) {
            this.b = this.d;
            this.d = 0;
        } else {
            this.b = this.a.a();
        }
        if (this.b == 0 || this.b == this.c) {
            return Integer.MAX_VALUE;
        }
        return this.b >>> 3;
    }

    public final <T> T a(zd<T> zdVar, ww wwVar) throws IOException {
        a(2);
        return c(zdVar, wwVar);
    }

    public final void a(List<Double> list) throws IOException {
        int a2;
        int a3;
        if (list instanceof ws) {
            ws wsVar = (ws) list;
            switch (this.b & 7) {
                case 1:
                    break;
                case 2:
                    int m = this.a.m();
                    b(m);
                    int u = this.a.u() + m;
                    do {
                        wsVar.a(this.a.b());
                    } while (this.a.u() < u);
                    return;
                default:
                    throw zzbbu.zzadq();
            }
            do {
                wsVar.a(this.a.b());
                if (!this.a.t()) {
                    a3 = this.a.a();
                } else {
                    return;
                }
            } while (a3 == this.b);
            this.d = a3;
            return;
        }
        switch (this.b & 7) {
            case 1:
                break;
            case 2:
                int m2 = this.a.m();
                b(m2);
                int u2 = this.a.u() + m2;
                do {
                    list.add(Double.valueOf(this.a.b()));
                } while (this.a.u() < u2);
                return;
            default:
                throw zzbbu.zzadq();
        }
        do {
            list.add(Double.valueOf(this.a.b()));
            if (!this.a.t()) {
                a2 = this.a.a();
            } else {
                return;
            }
        } while (a2 == this.b);
        this.d = a2;
    }

    public final <T> void a(List<T> list, zd<T> zdVar, ww wwVar) throws IOException {
        if ((this.b & 7) != 2) {
            throw zzbbu.zzadq();
        }
        int i = this.b;
        while (true) {
            list.add(c(zdVar, wwVar));
            if (!this.a.t() && this.d == 0) {
                int a2 = this.a.a();
                if (a2 != i) {
                    this.d = a2;
                    break;
                }
            } else {
                return;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0052, code lost:
        if (c() == false) goto L_0x0054;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x005b, code lost:
        throw new com.google.android.gms.internal.ads.zzbbu("Unable to parse map entry.");
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x004e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <K, V> void a(java.util.Map<K, V> r6, com.google.android.gms.internal.ads.ye<K, V> r7, com.google.android.gms.internal.ads.ww r8) throws java.io.IOException {
        /*
            r5 = this;
            r0 = 2
            r5.a(r0)
            com.google.android.gms.internal.ads.wl r0 = r5.a
            int r0 = r0.m()
            com.google.android.gms.internal.ads.wl r1 = r5.a
            int r0 = r1.c(r0)
            K r1 = r7.b
            V r2 = r7.d
        L_0x0014:
            int r3 = r5.a()     // Catch:{ all -> 0x0065 }
            r4 = 2147483647(0x7fffffff, float:NaN)
            if (r3 == r4) goto L_0x005c
            com.google.android.gms.internal.ads.wl r4 = r5.a     // Catch:{ all -> 0x0065 }
            boolean r4 = r4.t()     // Catch:{ all -> 0x0065 }
            if (r4 != 0) goto L_0x005c
            switch(r3) {
                case 1: goto L_0x003b;
                case 2: goto L_0x002d;
                default: goto L_0x0028;
            }
        L_0x0028:
            boolean r3 = r5.c()     // Catch:{ zzbbv -> 0x004e }
            goto L_0x0044
        L_0x002d:
            com.google.android.gms.internal.ads.zzbes r3 = r7.c     // Catch:{ zzbbv -> 0x004e }
            V r4 = r7.d     // Catch:{ zzbbv -> 0x004e }
            java.lang.Class r4 = r4.getClass()     // Catch:{ zzbbv -> 0x004e }
            java.lang.Object r3 = r5.a(r3, r4, r8)     // Catch:{ zzbbv -> 0x004e }
            r2 = r3
            goto L_0x0014
        L_0x003b:
            com.google.android.gms.internal.ads.zzbes r3 = r7.a     // Catch:{ zzbbv -> 0x004e }
            r4 = 0
            java.lang.Object r3 = r5.a(r3, r4, r4)     // Catch:{ zzbbv -> 0x004e }
            r1 = r3
            goto L_0x0014
        L_0x0044:
            if (r3 != 0) goto L_0x0014
            com.google.android.gms.internal.ads.zzbbu r3 = new com.google.android.gms.internal.ads.zzbbu     // Catch:{ zzbbv -> 0x004e }
            java.lang.String r4 = "Unable to parse map entry."
            r3.<init>(r4)     // Catch:{ zzbbv -> 0x004e }
            throw r3     // Catch:{ zzbbv -> 0x004e }
        L_0x004e:
            boolean r3 = r5.c()     // Catch:{ all -> 0x0065 }
            if (r3 != 0) goto L_0x0014
            com.google.android.gms.internal.ads.zzbbu r6 = new com.google.android.gms.internal.ads.zzbbu     // Catch:{ all -> 0x0065 }
            java.lang.String r7 = "Unable to parse map entry."
            r6.<init>(r7)     // Catch:{ all -> 0x0065 }
            throw r6     // Catch:{ all -> 0x0065 }
        L_0x005c:
            r6.put(r1, r2)     // Catch:{ all -> 0x0065 }
            com.google.android.gms.internal.ads.wl r6 = r5.a
            r6.d(r0)
            return
        L_0x0065:
            r6 = move-exception
            com.google.android.gms.internal.ads.wl r7 = r5.a
            r7.d(r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.wo.a(java.util.Map, com.google.android.gms.internal.ads.ye, com.google.android.gms.internal.ads.ww):void");
    }

    public final int b() {
        return this.b;
    }

    public final <T> T b(zd<T> zdVar, ww wwVar) throws IOException {
        a(3);
        return d(zdVar, wwVar);
    }

    public final void b(List<Float> list) throws IOException {
        int a2;
        int a3;
        if (list instanceof xf) {
            xf xfVar = (xf) list;
            int i = this.b & 7;
            if (i == 2) {
                int m = this.a.m();
                c(m);
                int u = this.a.u() + m;
                do {
                    xfVar.a(this.a.c());
                } while (this.a.u() < u);
            } else if (i != 5) {
                throw zzbbu.zzadq();
            } else {
                do {
                    xfVar.a(this.a.c());
                    if (!this.a.t()) {
                        a3 = this.a.a();
                    } else {
                        return;
                    }
                } while (a3 == this.b);
                this.d = a3;
            }
        } else {
            int i2 = this.b & 7;
            if (i2 == 2) {
                int m2 = this.a.m();
                c(m2);
                int u2 = this.a.u() + m2;
                do {
                    list.add(Float.valueOf(this.a.c()));
                } while (this.a.u() < u2);
            } else if (i2 != 5) {
                throw zzbbu.zzadq();
            } else {
                do {
                    list.add(Float.valueOf(this.a.c()));
                    if (!this.a.t()) {
                        a2 = this.a.a();
                    } else {
                        return;
                    }
                } while (a2 == this.b);
                this.d = a2;
            }
        }
    }

    public final <T> void b(List<T> list, zd<T> zdVar, ww wwVar) throws IOException {
        if ((this.b & 7) != 3) {
            throw zzbbu.zzadq();
        }
        int i = this.b;
        while (true) {
            list.add(d(zdVar, wwVar));
            if (!this.a.t() && this.d == 0) {
                int a2 = this.a.a();
                if (a2 != i) {
                    this.d = a2;
                    break;
                }
            } else {
                return;
            }
        }
    }

    public final void c(List<Long> list) throws IOException {
        int a2;
        int a3;
        if (list instanceof xz) {
            xz xzVar = (xz) list;
            int i = this.b & 7;
            if (i == 0) {
                do {
                    xzVar.a(this.a.d());
                    if (!this.a.t()) {
                        a3 = this.a.a();
                    } else {
                        return;
                    }
                } while (a3 == this.b);
                this.d = a3;
            } else if (i != 2) {
                throw zzbbu.zzadq();
            } else {
                int u = this.a.u() + this.a.m();
                do {
                    xzVar.a(this.a.d());
                } while (this.a.u() < u);
                d(u);
            }
        } else {
            int i2 = this.b & 7;
            if (i2 == 0) {
                do {
                    list.add(Long.valueOf(this.a.d()));
                    if (!this.a.t()) {
                        a2 = this.a.a();
                    } else {
                        return;
                    }
                } while (a2 == this.b);
                this.d = a2;
            } else if (i2 != 2) {
                throw zzbbu.zzadq();
            } else {
                int u2 = this.a.u() + this.a.m();
                do {
                    list.add(Long.valueOf(this.a.d()));
                } while (this.a.u() < u2);
                d(u2);
            }
        }
    }

    public final boolean c() throws IOException {
        if (this.a.t() || this.b == this.c) {
            return false;
        }
        return this.a.b(this.b);
    }

    public final double d() throws IOException {
        a(1);
        return this.a.b();
    }

    public final void d(List<Long> list) throws IOException {
        int a2;
        int a3;
        if (list instanceof xz) {
            xz xzVar = (xz) list;
            int i = this.b & 7;
            if (i == 0) {
                do {
                    xzVar.a(this.a.e());
                    if (!this.a.t()) {
                        a3 = this.a.a();
                    } else {
                        return;
                    }
                } while (a3 == this.b);
                this.d = a3;
            } else if (i != 2) {
                throw zzbbu.zzadq();
            } else {
                int u = this.a.u() + this.a.m();
                do {
                    xzVar.a(this.a.e());
                } while (this.a.u() < u);
                d(u);
            }
        } else {
            int i2 = this.b & 7;
            if (i2 == 0) {
                do {
                    list.add(Long.valueOf(this.a.e()));
                    if (!this.a.t()) {
                        a2 = this.a.a();
                    } else {
                        return;
                    }
                } while (a2 == this.b);
                this.d = a2;
            } else if (i2 != 2) {
                throw zzbbu.zzadq();
            } else {
                int u2 = this.a.u() + this.a.m();
                do {
                    list.add(Long.valueOf(this.a.e()));
                } while (this.a.u() < u2);
                d(u2);
            }
        }
    }

    public final float e() throws IOException {
        a(5);
        return this.a.c();
    }

    public final void e(List<Integer> list) throws IOException {
        int a2;
        int a3;
        if (list instanceof xi) {
            xi xiVar = (xi) list;
            int i = this.b & 7;
            if (i == 0) {
                do {
                    xiVar.c(this.a.f());
                    if (!this.a.t()) {
                        a3 = this.a.a();
                    } else {
                        return;
                    }
                } while (a3 == this.b);
                this.d = a3;
            } else if (i != 2) {
                throw zzbbu.zzadq();
            } else {
                int u = this.a.u() + this.a.m();
                do {
                    xiVar.c(this.a.f());
                } while (this.a.u() < u);
                d(u);
            }
        } else {
            int i2 = this.b & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(this.a.f()));
                    if (!this.a.t()) {
                        a2 = this.a.a();
                    } else {
                        return;
                    }
                } while (a2 == this.b);
                this.d = a2;
            } else if (i2 != 2) {
                throw zzbbu.zzadq();
            } else {
                int u2 = this.a.u() + this.a.m();
                do {
                    list.add(Integer.valueOf(this.a.f()));
                } while (this.a.u() < u2);
                d(u2);
            }
        }
    }

    public final long f() throws IOException {
        a(0);
        return this.a.d();
    }

    public final void f(List<Long> list) throws IOException {
        int a2;
        int a3;
        if (list instanceof xz) {
            xz xzVar = (xz) list;
            switch (this.b & 7) {
                case 1:
                    break;
                case 2:
                    int m = this.a.m();
                    b(m);
                    int u = this.a.u() + m;
                    do {
                        xzVar.a(this.a.g());
                    } while (this.a.u() < u);
                    return;
                default:
                    throw zzbbu.zzadq();
            }
            do {
                xzVar.a(this.a.g());
                if (!this.a.t()) {
                    a3 = this.a.a();
                } else {
                    return;
                }
            } while (a3 == this.b);
            this.d = a3;
            return;
        }
        switch (this.b & 7) {
            case 1:
                break;
            case 2:
                int m2 = this.a.m();
                b(m2);
                int u2 = this.a.u() + m2;
                do {
                    list.add(Long.valueOf(this.a.g()));
                } while (this.a.u() < u2);
                return;
            default:
                throw zzbbu.zzadq();
        }
        do {
            list.add(Long.valueOf(this.a.g()));
            if (!this.a.t()) {
                a2 = this.a.a();
            } else {
                return;
            }
        } while (a2 == this.b);
        this.d = a2;
    }

    public final long g() throws IOException {
        a(0);
        return this.a.e();
    }

    public final void g(List<Integer> list) throws IOException {
        int a2;
        int a3;
        if (list instanceof xi) {
            xi xiVar = (xi) list;
            int i = this.b & 7;
            if (i == 2) {
                int m = this.a.m();
                c(m);
                int u = this.a.u() + m;
                do {
                    xiVar.c(this.a.h());
                } while (this.a.u() < u);
            } else if (i != 5) {
                throw zzbbu.zzadq();
            } else {
                do {
                    xiVar.c(this.a.h());
                    if (!this.a.t()) {
                        a3 = this.a.a();
                    } else {
                        return;
                    }
                } while (a3 == this.b);
                this.d = a3;
            }
        } else {
            int i2 = this.b & 7;
            if (i2 == 2) {
                int m2 = this.a.m();
                c(m2);
                int u2 = this.a.u() + m2;
                do {
                    list.add(Integer.valueOf(this.a.h()));
                } while (this.a.u() < u2);
            } else if (i2 != 5) {
                throw zzbbu.zzadq();
            } else {
                do {
                    list.add(Integer.valueOf(this.a.h()));
                    if (!this.a.t()) {
                        a2 = this.a.a();
                    } else {
                        return;
                    }
                } while (a2 == this.b);
                this.d = a2;
            }
        }
    }

    public final int h() throws IOException {
        a(0);
        return this.a.f();
    }

    public final void h(List<Boolean> list) throws IOException {
        int a2;
        int a3;
        if (list instanceof we) {
            we weVar = (we) list;
            int i = this.b & 7;
            if (i == 0) {
                do {
                    weVar.a(this.a.i());
                    if (!this.a.t()) {
                        a3 = this.a.a();
                    } else {
                        return;
                    }
                } while (a3 == this.b);
                this.d = a3;
            } else if (i != 2) {
                throw zzbbu.zzadq();
            } else {
                int u = this.a.u() + this.a.m();
                do {
                    weVar.a(this.a.i());
                } while (this.a.u() < u);
                d(u);
            }
        } else {
            int i2 = this.b & 7;
            if (i2 == 0) {
                do {
                    list.add(Boolean.valueOf(this.a.i()));
                    if (!this.a.t()) {
                        a2 = this.a.a();
                    } else {
                        return;
                    }
                } while (a2 == this.b);
                this.d = a2;
            } else if (i2 != 2) {
                throw zzbbu.zzadq();
            } else {
                int u2 = this.a.u() + this.a.m();
                do {
                    list.add(Boolean.valueOf(this.a.i()));
                } while (this.a.u() < u2);
                d(u2);
            }
        }
    }

    public final long i() throws IOException {
        a(1);
        return this.a.g();
    }

    public final void i(List<String> list) throws IOException {
        a(list, false);
    }

    public final int j() throws IOException {
        a(5);
        return this.a.h();
    }

    public final void j(List<String> list) throws IOException {
        a(list, true);
    }

    public final void k(List<zzbah> list) throws IOException {
        int a2;
        if ((this.b & 7) != 2) {
            throw zzbbu.zzadq();
        }
        do {
            list.add(n());
            if (!this.a.t()) {
                a2 = this.a.a();
            } else {
                return;
            }
        } while (a2 == this.b);
        this.d = a2;
    }

    public final boolean k() throws IOException {
        a(0);
        return this.a.i();
    }

    public final String l() throws IOException {
        a(2);
        return this.a.j();
    }

    public final void l(List<Integer> list) throws IOException {
        int a2;
        int a3;
        if (list instanceof xi) {
            xi xiVar = (xi) list;
            int i = this.b & 7;
            if (i == 0) {
                do {
                    xiVar.c(this.a.m());
                    if (!this.a.t()) {
                        a3 = this.a.a();
                    } else {
                        return;
                    }
                } while (a3 == this.b);
                this.d = a3;
            } else if (i != 2) {
                throw zzbbu.zzadq();
            } else {
                int u = this.a.u() + this.a.m();
                do {
                    xiVar.c(this.a.m());
                } while (this.a.u() < u);
                d(u);
            }
        } else {
            int i2 = this.b & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(this.a.m()));
                    if (!this.a.t()) {
                        a2 = this.a.a();
                    } else {
                        return;
                    }
                } while (a2 == this.b);
                this.d = a2;
            } else if (i2 != 2) {
                throw zzbbu.zzadq();
            } else {
                int u2 = this.a.u() + this.a.m();
                do {
                    list.add(Integer.valueOf(this.a.m()));
                } while (this.a.u() < u2);
                d(u2);
            }
        }
    }

    public final String m() throws IOException {
        a(2);
        return this.a.k();
    }

    public final void m(List<Integer> list) throws IOException {
        int a2;
        int a3;
        if (list instanceof xi) {
            xi xiVar = (xi) list;
            int i = this.b & 7;
            if (i == 0) {
                do {
                    xiVar.c(this.a.n());
                    if (!this.a.t()) {
                        a3 = this.a.a();
                    } else {
                        return;
                    }
                } while (a3 == this.b);
                this.d = a3;
            } else if (i != 2) {
                throw zzbbu.zzadq();
            } else {
                int u = this.a.u() + this.a.m();
                do {
                    xiVar.c(this.a.n());
                } while (this.a.u() < u);
                d(u);
            }
        } else {
            int i2 = this.b & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(this.a.n()));
                    if (!this.a.t()) {
                        a2 = this.a.a();
                    } else {
                        return;
                    }
                } while (a2 == this.b);
                this.d = a2;
            } else if (i2 != 2) {
                throw zzbbu.zzadq();
            } else {
                int u2 = this.a.u() + this.a.m();
                do {
                    list.add(Integer.valueOf(this.a.n()));
                } while (this.a.u() < u2);
                d(u2);
            }
        }
    }

    public final zzbah n() throws IOException {
        a(2);
        return this.a.l();
    }

    public final void n(List<Integer> list) throws IOException {
        int a2;
        int a3;
        if (list instanceof xi) {
            xi xiVar = (xi) list;
            int i = this.b & 7;
            if (i == 2) {
                int m = this.a.m();
                c(m);
                int u = this.a.u() + m;
                do {
                    xiVar.c(this.a.o());
                } while (this.a.u() < u);
            } else if (i != 5) {
                throw zzbbu.zzadq();
            } else {
                do {
                    xiVar.c(this.a.o());
                    if (!this.a.t()) {
                        a3 = this.a.a();
                    } else {
                        return;
                    }
                } while (a3 == this.b);
                this.d = a3;
            }
        } else {
            int i2 = this.b & 7;
            if (i2 == 2) {
                int m2 = this.a.m();
                c(m2);
                int u2 = this.a.u() + m2;
                do {
                    list.add(Integer.valueOf(this.a.o()));
                } while (this.a.u() < u2);
            } else if (i2 != 5) {
                throw zzbbu.zzadq();
            } else {
                do {
                    list.add(Integer.valueOf(this.a.o()));
                    if (!this.a.t()) {
                        a2 = this.a.a();
                    } else {
                        return;
                    }
                } while (a2 == this.b);
                this.d = a2;
            }
        }
    }

    public final int o() throws IOException {
        a(0);
        return this.a.m();
    }

    public final void o(List<Long> list) throws IOException {
        int a2;
        int a3;
        if (list instanceof xz) {
            xz xzVar = (xz) list;
            switch (this.b & 7) {
                case 1:
                    break;
                case 2:
                    int m = this.a.m();
                    b(m);
                    int u = this.a.u() + m;
                    do {
                        xzVar.a(this.a.p());
                    } while (this.a.u() < u);
                    return;
                default:
                    throw zzbbu.zzadq();
            }
            do {
                xzVar.a(this.a.p());
                if (!this.a.t()) {
                    a3 = this.a.a();
                } else {
                    return;
                }
            } while (a3 == this.b);
            this.d = a3;
            return;
        }
        switch (this.b & 7) {
            case 1:
                break;
            case 2:
                int m2 = this.a.m();
                b(m2);
                int u2 = this.a.u() + m2;
                do {
                    list.add(Long.valueOf(this.a.p()));
                } while (this.a.u() < u2);
                return;
            default:
                throw zzbbu.zzadq();
        }
        do {
            list.add(Long.valueOf(this.a.p()));
            if (!this.a.t()) {
                a2 = this.a.a();
            } else {
                return;
            }
        } while (a2 == this.b);
        this.d = a2;
    }

    public final int p() throws IOException {
        a(0);
        return this.a.n();
    }

    public final void p(List<Integer> list) throws IOException {
        int a2;
        int a3;
        if (list instanceof xi) {
            xi xiVar = (xi) list;
            int i = this.b & 7;
            if (i == 0) {
                do {
                    xiVar.c(this.a.q());
                    if (!this.a.t()) {
                        a3 = this.a.a();
                    } else {
                        return;
                    }
                } while (a3 == this.b);
                this.d = a3;
            } else if (i != 2) {
                throw zzbbu.zzadq();
            } else {
                int u = this.a.u() + this.a.m();
                do {
                    xiVar.c(this.a.q());
                } while (this.a.u() < u);
                d(u);
            }
        } else {
            int i2 = this.b & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(this.a.q()));
                    if (!this.a.t()) {
                        a2 = this.a.a();
                    } else {
                        return;
                    }
                } while (a2 == this.b);
                this.d = a2;
            } else if (i2 != 2) {
                throw zzbbu.zzadq();
            } else {
                int u2 = this.a.u() + this.a.m();
                do {
                    list.add(Integer.valueOf(this.a.q()));
                } while (this.a.u() < u2);
                d(u2);
            }
        }
    }

    public final int q() throws IOException {
        a(5);
        return this.a.o();
    }

    public final void q(List<Long> list) throws IOException {
        int a2;
        int a3;
        if (list instanceof xz) {
            xz xzVar = (xz) list;
            int i = this.b & 7;
            if (i == 0) {
                do {
                    xzVar.a(this.a.r());
                    if (!this.a.t()) {
                        a3 = this.a.a();
                    } else {
                        return;
                    }
                } while (a3 == this.b);
                this.d = a3;
            } else if (i != 2) {
                throw zzbbu.zzadq();
            } else {
                int u = this.a.u() + this.a.m();
                do {
                    xzVar.a(this.a.r());
                } while (this.a.u() < u);
                d(u);
            }
        } else {
            int i2 = this.b & 7;
            if (i2 == 0) {
                do {
                    list.add(Long.valueOf(this.a.r()));
                    if (!this.a.t()) {
                        a2 = this.a.a();
                    } else {
                        return;
                    }
                } while (a2 == this.b);
                this.d = a2;
            } else if (i2 != 2) {
                throw zzbbu.zzadq();
            } else {
                int u2 = this.a.u() + this.a.m();
                do {
                    list.add(Long.valueOf(this.a.r()));
                } while (this.a.u() < u2);
                d(u2);
            }
        }
    }

    public final long r() throws IOException {
        a(1);
        return this.a.p();
    }

    public final int s() throws IOException {
        a(0);
        return this.a.q();
    }

    public final long t() throws IOException {
        a(0);
        return this.a.r();
    }
}
