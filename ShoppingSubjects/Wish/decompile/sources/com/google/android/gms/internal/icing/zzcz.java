package com.google.android.gms.internal.icing;

public class zzcz {
    private static final zzbz zzdk = zzbz.zzai();
    private zzbi zzjd;
    private volatile zzdr zzje;
    private volatile zzbi zzjf;

    /* JADX WARNING: Can't wrap try/catch for region: R(4:7|8|9|10) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0012 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.internal.icing.zzdr zzg(com.google.android.gms.internal.icing.zzdr r2) {
        /*
            r1 = this;
            com.google.android.gms.internal.icing.zzdr r0 = r1.zzje
            if (r0 != 0) goto L_0x001c
            monitor-enter(r1)
            com.google.android.gms.internal.icing.zzdr r0 = r1.zzje     // Catch:{ all -> 0x0019 }
            if (r0 == 0) goto L_0x000b
        L_0x0009:
            monitor-exit(r1)     // Catch:{ all -> 0x0019 }
            goto L_0x001c
        L_0x000b:
            r1.zzje = r2     // Catch:{ zzcs -> 0x0012 }
            com.google.android.gms.internal.icing.zzbi r0 = com.google.android.gms.internal.icing.zzbi.zzdq     // Catch:{ zzcs -> 0x0012 }
            r1.zzjf = r0     // Catch:{ zzcs -> 0x0012 }
            goto L_0x0009
        L_0x0012:
            r1.zzje = r2     // Catch:{ all -> 0x0019 }
            com.google.android.gms.internal.icing.zzbi r2 = com.google.android.gms.internal.icing.zzbi.zzdq     // Catch:{ all -> 0x0019 }
            r1.zzjf = r2     // Catch:{ all -> 0x0019 }
            goto L_0x0009
        L_0x0019:
            r2 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0019 }
            throw r2
        L_0x001c:
            com.google.android.gms.internal.icing.zzdr r2 = r1.zzje
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.zzcz.zzg(com.google.android.gms.internal.icing.zzdr):com.google.android.gms.internal.icing.zzdr");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzcz)) {
            return false;
        }
        zzcz zzcz = (zzcz) obj;
        zzdr zzdr = this.zzje;
        zzdr zzdr2 = zzcz.zzje;
        return (zzdr == null && zzdr2 == null) ? zzl().equals(zzcz.zzl()) : (zzdr == null || zzdr2 == null) ? zzdr != null ? zzdr.equals(zzcz.zzg(zzdr.zzba())) : zzg(zzdr2.zzba()).equals(zzdr2) : zzdr.equals(zzdr2);
    }

    public int hashCode() {
        return 1;
    }

    public final int zzan() {
        if (this.zzjf != null) {
            return this.zzjf.size();
        }
        if (this.zzje != null) {
            return this.zzje.zzan();
        }
        return 0;
    }

    public final zzdr zzh(zzdr zzdr) {
        zzdr zzdr2 = this.zzje;
        this.zzjd = null;
        this.zzjf = null;
        this.zzje = zzdr;
        return zzdr2;
    }

    public final zzbi zzl() {
        if (this.zzjf != null) {
            return this.zzjf;
        }
        synchronized (this) {
            if (this.zzjf != null) {
                zzbi zzbi = this.zzjf;
                return zzbi;
            }
            this.zzjf = this.zzje == null ? zzbi.zzdq : this.zzje.zzl();
            zzbi zzbi2 = this.zzjf;
            return zzbi2;
        }
    }
}
