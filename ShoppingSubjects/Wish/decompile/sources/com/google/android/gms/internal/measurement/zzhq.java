package com.google.android.gms.internal.measurement;

import java.util.concurrent.Callable;

final class zzhq implements Callable<String> {
    private final /* synthetic */ zzhm zzaps;

    zzhq(zzhm zzhm) {
        this.zzaps = zzhm;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x006d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object call() throws java.lang.Exception {
        /*
            r10 = this;
            com.google.android.gms.internal.measurement.zzhm r0 = r10.zzaps
            com.google.android.gms.internal.measurement.zzft r0 = r0.zzgj()
            java.lang.String r0 = r0.zzjk()
            if (r0 == 0) goto L_0x000d
            return r0
        L_0x000d:
            com.google.android.gms.internal.measurement.zzhm r0 = r10.zzaps
            com.google.android.gms.internal.measurement.zzhm r0 = r0.zzfy()
            com.google.android.gms.internal.measurement.zzgi r1 = r0.zzgh()
            boolean r1 = r1.zzju()
            r2 = 0
            if (r1 == 0) goto L_0x002c
            com.google.android.gms.internal.measurement.zzfi r0 = r0.zzgi()
            com.google.android.gms.internal.measurement.zzfk r0 = r0.zziv()
            java.lang.String r1 = "Cannot retrieve app instance id from analytics worker thread"
        L_0x0028:
            r0.log(r1)
            goto L_0x0065
        L_0x002c:
            boolean r1 = com.google.android.gms.internal.measurement.zzee.isMainThread()
            if (r1 == 0) goto L_0x003d
            com.google.android.gms.internal.measurement.zzfi r0 = r0.zzgi()
            com.google.android.gms.internal.measurement.zzfk r0 = r0.zziv()
            java.lang.String r1 = "Cannot retrieve app instance id from main thread"
            goto L_0x0028
        L_0x003d:
            com.google.android.gms.common.util.Clock r1 = r0.zzbt()
            long r1 = r1.elapsedRealtime()
            r3 = 120000(0x1d4c0, double:5.9288E-319)
            java.lang.String r5 = r0.zzaj(r3)
            com.google.android.gms.common.util.Clock r6 = r0.zzbt()
            long r6 = r6.elapsedRealtime()
            long r8 = r6 - r1
            if (r5 != 0) goto L_0x0064
            int r1 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r1 >= 0) goto L_0x0064
            long r1 = r3 - r8
            java.lang.String r0 = r0.zzaj(r1)
            r2 = r0
            goto L_0x0065
        L_0x0064:
            r2 = r5
        L_0x0065:
            if (r2 != 0) goto L_0x006d
            java.util.concurrent.TimeoutException r0 = new java.util.concurrent.TimeoutException
            r0.<init>()
            throw r0
        L_0x006d:
            com.google.android.gms.internal.measurement.zzhm r0 = r10.zzaps
            com.google.android.gms.internal.measurement.zzft r0 = r0.zzgj()
            r0.zzbu(r2)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzhq.call():java.lang.Object");
    }
}
