package com.google.android.gms.internal.firebase-perf;

public class zzda {
    private static final zzbz zzhi = zzbz.zzda();
    private zzbd zzns;
    private volatile zzdt zznt;
    private volatile zzbd zznu;

    public int hashCode() {
        return 1;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzda)) {
            return false;
        }
        zzda zzda = (zzda) obj;
        zzdt zzdt = this.zznt;
        zzdt zzdt2 = zzda.zznt;
        if (zzdt == null && zzdt2 == null) {
            return zzbe().equals(zzda.zzbe());
        }
        if (zzdt != null && zzdt2 != null) {
            return zzdt.equals(zzdt2);
        }
        if (zzdt != null) {
            return zzdt.equals(zzda.zzh(zzdt.zzds()));
        }
        return zzh(zzdt2.zzds()).equals(zzdt2);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:7|8|9|10|11|12) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0012 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.internal.firebase-perf.zzdt zzh(com.google.android.gms.internal.firebase-perf.zzdt r2) {
        /*
            r1 = this;
            com.google.android.gms.internal.firebase-perf.zzdt r0 = r1.zznt
            if (r0 != 0) goto L_0x001d
            monitor-enter(r1)
            com.google.android.gms.internal.firebase-perf.zzdt r0 = r1.zznt     // Catch:{ all -> 0x001a }
            if (r0 == 0) goto L_0x000b
            monitor-exit(r1)     // Catch:{ all -> 0x001a }
            goto L_0x001d
        L_0x000b:
            r1.zznt = r2     // Catch:{ zzct -> 0x0012 }
            com.google.android.gms.internal.firebase-perf.zzbd r0 = com.google.android.gms.internal.firebase-perf.zzbd.zzho     // Catch:{ zzct -> 0x0012 }
            r1.zznu = r0     // Catch:{ zzct -> 0x0012 }
            goto L_0x0018
        L_0x0012:
            r1.zznt = r2     // Catch:{ all -> 0x001a }
            com.google.android.gms.internal.firebase-perf.zzbd r2 = com.google.android.gms.internal.firebase-perf.zzbd.zzho     // Catch:{ all -> 0x001a }
            r1.zznu = r2     // Catch:{ all -> 0x001a }
        L_0x0018:
            monitor-exit(r1)     // Catch:{ all -> 0x001a }
            goto L_0x001d
        L_0x001a:
            r2 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x001a }
            throw r2
        L_0x001d:
            com.google.android.gms.internal.firebase-perf.zzdt r2 = r1.zznt
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase-perf.zzda.zzh(com.google.android.gms.internal.firebase-perf.zzdt):com.google.android.gms.internal.firebase-perf.zzdt");
    }

    public final zzdt zzi(zzdt zzdt) {
        zzdt zzdt2 = this.zznt;
        this.zzns = null;
        this.zznu = null;
        this.zznt = zzdt;
        return zzdt2;
    }

    public final int zzdg() {
        if (this.zznu != null) {
            return this.zznu.size();
        }
        if (this.zznt != null) {
            return this.zznt.zzdg();
        }
        return 0;
    }

    public final zzbd zzbe() {
        if (this.zznu != null) {
            return this.zznu;
        }
        synchronized (this) {
            if (this.zznu != null) {
                zzbd zzbd = this.zznu;
                return zzbd;
            }
            if (this.zznt == null) {
                this.zznu = zzbd.zzho;
            } else {
                this.zznu = this.zznt.zzbe();
            }
            zzbd zzbd2 = this.zznu;
            return zzbd2;
        }
    }
}
