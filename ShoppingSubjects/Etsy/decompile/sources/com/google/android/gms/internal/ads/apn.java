package com.google.android.gms.internal.ads;

final class apn implements Runnable {
    private final /* synthetic */ apx a;
    private final /* synthetic */ aou b;
    private final /* synthetic */ apg c;

    apn(apg apg, apx apx, aou aou) {
        this.c = apg;
        this.a = apx;
        this.b = aou;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0035, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r3 = this;
            com.google.android.gms.internal.ads.apg r0 = r3.c
            java.lang.Object r0 = r0.a
            monitor-enter(r0)
            com.google.android.gms.internal.ads.apx r1 = r3.a     // Catch:{ all -> 0x0036 }
            int r1 = r1.b()     // Catch:{ all -> 0x0036 }
            r2 = -1
            if (r1 == r2) goto L_0x0034
            com.google.android.gms.internal.ads.apx r1 = r3.a     // Catch:{ all -> 0x0036 }
            int r1 = r1.b()     // Catch:{ all -> 0x0036 }
            r2 = 1
            if (r1 != r2) goto L_0x001a
            goto L_0x0034
        L_0x001a:
            com.google.android.gms.internal.ads.apx r1 = r3.a     // Catch:{ all -> 0x0036 }
            r1.a()     // Catch:{ all -> 0x0036 }
            java.util.concurrent.Executor r1 = com.google.android.gms.internal.ads.kz.a     // Catch:{ all -> 0x0036 }
            com.google.android.gms.internal.ads.aou r2 = r3.b     // Catch:{ all -> 0x0036 }
            r2.getClass()     // Catch:{ all -> 0x0036 }
            java.lang.Runnable r2 = com.google.android.gms.internal.ads.apo.a(r2)     // Catch:{ all -> 0x0036 }
            r1.execute(r2)     // Catch:{ all -> 0x0036 }
            java.lang.String r1 = "Could not receive loaded message in a timely manner. Rejecting."
            com.google.android.gms.internal.ads.gv.a(r1)     // Catch:{ all -> 0x0036 }
            monitor-exit(r0)     // Catch:{ all -> 0x0036 }
            return
        L_0x0034:
            monitor-exit(r0)     // Catch:{ all -> 0x0036 }
            return
        L_0x0036:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0036 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.apn.run():void");
    }
}
