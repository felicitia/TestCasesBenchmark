package com.google.android.gms.internal.ads;

public class xs {
    private static final ww a = ww.a();
    private zzbah b;
    private volatile yk c;
    private volatile zzbah d;

    /* JADX WARNING: Can't wrap try/catch for region: R(4:7|8|9|10) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0012 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.internal.ads.yk b(com.google.android.gms.internal.ads.yk r2) {
        /*
            r1 = this;
            com.google.android.gms.internal.ads.yk r0 = r1.c
            if (r0 != 0) goto L_0x001c
            monitor-enter(r1)
            com.google.android.gms.internal.ads.yk r0 = r1.c     // Catch:{ all -> 0x0019 }
            if (r0 == 0) goto L_0x000b
        L_0x0009:
            monitor-exit(r1)     // Catch:{ all -> 0x0019 }
            goto L_0x001c
        L_0x000b:
            r1.c = r2     // Catch:{ zzbbu -> 0x0012 }
            com.google.android.gms.internal.ads.zzbah r0 = com.google.android.gms.internal.ads.zzbah.zzdpq     // Catch:{ zzbbu -> 0x0012 }
            r1.d = r0     // Catch:{ zzbbu -> 0x0012 }
            goto L_0x0009
        L_0x0012:
            r1.c = r2     // Catch:{ all -> 0x0019 }
            com.google.android.gms.internal.ads.zzbah r2 = com.google.android.gms.internal.ads.zzbah.zzdpq     // Catch:{ all -> 0x0019 }
            r1.d = r2     // Catch:{ all -> 0x0019 }
            goto L_0x0009
        L_0x0019:
            r2 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0019 }
            throw r2
        L_0x001c:
            com.google.android.gms.internal.ads.yk r2 = r1.c
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.xs.b(com.google.android.gms.internal.ads.yk):com.google.android.gms.internal.ads.yk");
    }

    public final yk a(yk ykVar) {
        yk ykVar2 = this.c;
        this.b = null;
        this.d = null;
        this.c = ykVar;
        return ykVar2;
    }

    public final int b() {
        if (this.d != null) {
            return this.d.size();
        }
        if (this.c != null) {
            return this.c.l();
        }
        return 0;
    }

    public final zzbah c() {
        if (this.d != null) {
            return this.d;
        }
        synchronized (this) {
            if (this.d != null) {
                zzbah zzbah = this.d;
                return zzbah;
            }
            this.d = this.c == null ? zzbah.zzdpq : this.c.h();
            zzbah zzbah2 = this.d;
            return zzbah2;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof xs)) {
            return false;
        }
        xs xsVar = (xs) obj;
        yk ykVar = this.c;
        yk ykVar2 = xsVar.c;
        return (ykVar == null && ykVar2 == null) ? c().equals(xsVar.c()) : (ykVar == null || ykVar2 == null) ? ykVar != null ? ykVar.equals(xsVar.b(ykVar.p())) : b(ykVar2.p()).equals(ykVar2) : ykVar.equals(ykVar2);
    }

    public int hashCode() {
        return 1;
    }
}
