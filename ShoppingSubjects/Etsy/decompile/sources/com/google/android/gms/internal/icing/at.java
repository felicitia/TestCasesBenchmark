package com.google.android.gms.internal.icing;

public class at {
    private static final x a = x.a();
    private zzbi b;
    private volatile bl c;
    private volatile zzbi d;

    /* JADX WARNING: Can't wrap try/catch for region: R(4:7|8|9|10) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0012 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.internal.icing.bl b(com.google.android.gms.internal.icing.bl r2) {
        /*
            r1 = this;
            com.google.android.gms.internal.icing.bl r0 = r1.c
            if (r0 != 0) goto L_0x001c
            monitor-enter(r1)
            com.google.android.gms.internal.icing.bl r0 = r1.c     // Catch:{ all -> 0x0019 }
            if (r0 == 0) goto L_0x000b
        L_0x0009:
            monitor-exit(r1)     // Catch:{ all -> 0x0019 }
            goto L_0x001c
        L_0x000b:
            r1.c = r2     // Catch:{ zzcs -> 0x0012 }
            com.google.android.gms.internal.icing.zzbi r0 = com.google.android.gms.internal.icing.zzbi.zzdq     // Catch:{ zzcs -> 0x0012 }
            r1.d = r0     // Catch:{ zzcs -> 0x0012 }
            goto L_0x0009
        L_0x0012:
            r1.c = r2     // Catch:{ all -> 0x0019 }
            com.google.android.gms.internal.icing.zzbi r2 = com.google.android.gms.internal.icing.zzbi.zzdq     // Catch:{ all -> 0x0019 }
            r1.d = r2     // Catch:{ all -> 0x0019 }
            goto L_0x0009
        L_0x0019:
            r2 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0019 }
            throw r2
        L_0x001c:
            com.google.android.gms.internal.icing.bl r2 = r1.c
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.at.b(com.google.android.gms.internal.icing.bl):com.google.android.gms.internal.icing.bl");
    }

    public final bl a(bl blVar) {
        bl blVar2 = this.c;
        this.b = null;
        this.d = null;
        this.c = blVar;
        return blVar2;
    }

    public final int b() {
        if (this.d != null) {
            return this.d.size();
        }
        if (this.c != null) {
            return this.c.d();
        }
        return 0;
    }

    public final zzbi c() {
        if (this.d != null) {
            return this.d;
        }
        synchronized (this) {
            if (this.d != null) {
                zzbi zzbi = this.d;
                return zzbi;
            }
            this.d = this.c == null ? zzbi.zzdq : this.c.a();
            zzbi zzbi2 = this.d;
            return zzbi2;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof at)) {
            return false;
        }
        at atVar = (at) obj;
        bl blVar = this.c;
        bl blVar2 = atVar.c;
        return (blVar == null && blVar2 == null) ? c().equals(atVar.c()) : (blVar == null || blVar2 == null) ? blVar != null ? blVar.equals(atVar.b(blVar.j())) : b(blVar2.j()).equals(blVar2) : blVar.equals(blVar2);
    }

    public int hashCode() {
        return 1;
    }
}
