package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

final class yr<T> implements zd<T> {
    private final yk a;
    private final zv<?, ?> b;
    private final boolean c;
    private final wy<?> d;

    private yr(zv<?, ?> zvVar, wy<?> wyVar, yk ykVar) {
        this.b = zvVar;
        this.c = wyVar.a(ykVar);
        this.d = wyVar;
        this.a = ykVar;
    }

    static <T> yr<T> a(zv<?, ?> zvVar, wy<?> wyVar, yk ykVar) {
        return new yr<>(zvVar, wyVar, ykVar);
    }

    public final int a(T t) {
        int hashCode = this.b.b(t).hashCode();
        return this.c ? (hashCode * 53) + this.d.a((Object) t).hashCode() : hashCode;
    }

    public final T a() {
        return this.a.o().d();
    }

    public final void a(T t, aai aai) throws IOException {
        int a2;
        Object value;
        Iterator e = this.d.a((Object) t).e();
        while (e.hasNext()) {
            Entry entry = (Entry) e.next();
            xd xdVar = (xd) entry.getKey();
            if (xdVar.c() != zzbex.MESSAGE || xdVar.d() || xdVar.e()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            }
            if (entry instanceof xp) {
                a2 = xdVar.a();
                value = ((xp) entry).a().c();
            } else {
                a2 = xdVar.a();
                value = entry.getValue();
            }
            aai.a(a2, value);
        }
        zv<?, ?> zvVar = this.b;
        zvVar.b(zvVar.b(t), aai);
    }

    public final void a(T t, zc zcVar, ww wwVar) throws IOException {
        boolean z;
        zv<?, ?> zvVar = this.b;
        wy<?> wyVar = this.d;
        Object c2 = zvVar.c(t);
        xb b2 = wyVar.b(t);
        do {
            try {
                if (zcVar.a() == Integer.MAX_VALUE) {
                    zvVar.b((Object) t, c2);
                    return;
                }
                int b3 = zcVar.b();
                if (b3 == 11) {
                    Object obj = null;
                    int i = 0;
                    zzbah zzbah = null;
                    while (zcVar.a() != Integer.MAX_VALUE) {
                        int b4 = zcVar.b();
                        if (b4 == 16) {
                            i = zcVar.o();
                            obj = wyVar.a(wwVar, this.a, i);
                        } else if (b4 == 26) {
                            if (obj != null) {
                                wyVar.a(zcVar, obj, wwVar, b2);
                            } else {
                                zzbah = zcVar.n();
                            }
                        } else if (!zcVar.c()) {
                            break;
                        }
                    }
                    if (zcVar.b() != 12) {
                        throw zzbbu.zzadp();
                    } else if (zzbah != null) {
                        if (obj != null) {
                            wyVar.a(zzbah, obj, wwVar, b2);
                        } else {
                            zvVar.a(c2, i, zzbah);
                        }
                    }
                } else if ((b3 & 7) == 2) {
                    Object a2 = wyVar.a(wwVar, this.a, b3 >>> 3);
                    if (a2 != null) {
                        wyVar.a(zcVar, a2, wwVar, b2);
                    } else {
                        z = zvVar.a(c2, zcVar);
                        continue;
                    }
                } else {
                    z = zcVar.c();
                    continue;
                }
                z = true;
                continue;
            } finally {
                zvVar.b((Object) t, c2);
            }
        } while (z);
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(T r7, byte[] r8, int r9, int r10, com.google.android.gms.internal.ads.wd r11) throws java.io.IOException {
        /*
            r6 = this;
            com.google.android.gms.internal.ads.xh r7 = (com.google.android.gms.internal.ads.xh) r7
            com.google.android.gms.internal.ads.zw r0 = r7.zzdtt
            com.google.android.gms.internal.ads.zw r1 = com.google.android.gms.internal.ads.zw.a()
            if (r0 != r1) goto L_0x0010
            com.google.android.gms.internal.ads.zw r0 = com.google.android.gms.internal.ads.zw.b()
            r7.zzdtt = r0
        L_0x0010:
            r7 = r0
        L_0x0011:
            if (r9 >= r10) goto L_0x0069
            int r2 = com.google.android.gms.internal.ads.wc.a(r8, r9, r11)
            int r0 = r11.a
            r9 = 11
            r1 = 2
            if (r0 == r9) goto L_0x0030
            r9 = r0 & 7
            if (r9 != r1) goto L_0x002b
            r1 = r8
            r3 = r10
            r4 = r7
            r5 = r11
            int r9 = com.google.android.gms.internal.ads.wc.a(r0, r1, r2, r3, r4, r5)
            goto L_0x0011
        L_0x002b:
            int r9 = com.google.android.gms.internal.ads.wc.a(r0, r8, r2, r10, r11)
            goto L_0x0011
        L_0x0030:
            r9 = 0
            r0 = 0
        L_0x0032:
            if (r2 >= r10) goto L_0x005f
            int r2 = com.google.android.gms.internal.ads.wc.a(r8, r2, r11)
            int r3 = r11.a
            int r4 = r3 >>> 3
            r5 = r3 & 7
            switch(r4) {
                case 2: goto L_0x004d;
                case 3: goto L_0x0042;
                default: goto L_0x0041;
            }
        L_0x0041:
            goto L_0x0056
        L_0x0042:
            if (r5 != r1) goto L_0x0056
            int r2 = com.google.android.gms.internal.ads.wc.e(r8, r2, r11)
            java.lang.Object r0 = r11.c
            com.google.android.gms.internal.ads.zzbah r0 = (com.google.android.gms.internal.ads.zzbah) r0
            goto L_0x0032
        L_0x004d:
            if (r5 != 0) goto L_0x0056
            int r2 = com.google.android.gms.internal.ads.wc.a(r8, r2, r11)
            int r9 = r11.a
            goto L_0x0032
        L_0x0056:
            r4 = 12
            if (r3 == r4) goto L_0x005f
            int r2 = com.google.android.gms.internal.ads.wc.a(r3, r8, r2, r10, r11)
            goto L_0x0032
        L_0x005f:
            if (r0 == 0) goto L_0x0067
            int r9 = r9 << 3
            r9 = r9 | r1
            r7.a(r9, r0)
        L_0x0067:
            r9 = r2
            goto L_0x0011
        L_0x0069:
            if (r9 == r10) goto L_0x0070
            com.google.android.gms.internal.ads.zzbbu r7 = com.google.android.gms.internal.ads.zzbbu.zzadr()
            throw r7
        L_0x0070:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.yr.a(java.lang.Object, byte[], int, int, com.google.android.gms.internal.ads.wd):void");
    }

    public final boolean a(T t, T t2) {
        if (!this.b.b(t).equals(this.b.b(t2))) {
            return false;
        }
        if (this.c) {
            return this.d.a((Object) t).equals(this.d.a((Object) t2));
        }
        return true;
    }

    public final int b(T t) {
        zv<?, ?> zvVar = this.b;
        int e = 0 + zvVar.e(zvVar.b(t));
        return this.c ? e + this.d.a((Object) t).i() : e;
    }

    public final void b(T t, T t2) {
        zf.a(this.b, t, t2);
        if (this.c) {
            zf.a(this.d, t, t2);
        }
    }

    public final void c(T t) {
        this.b.d(t);
        this.d.c(t);
    }

    public final boolean d(T t) {
        return this.d.a((Object) t).g();
    }
}
