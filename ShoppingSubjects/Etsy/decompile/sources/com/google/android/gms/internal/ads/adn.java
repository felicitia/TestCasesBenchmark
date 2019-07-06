package com.google.android.gms.internal.ads;

import java.util.concurrent.ExecutionException;

public final class adn extends aei {
    private static volatile abk d;
    private static final Object e = new Object();
    private ta f = null;

    public adn(acy acy, String str, String str2, vy vyVar, int i, int i2, ta taVar) {
        super(acy, str, str2, vyVar, i, 27);
        this.f = taVar;
    }

    private final String c() {
        try {
            if (this.a.l() != null) {
                this.a.l().get();
            }
            vy k = this.a.k();
            if (!(k == null || k.n == null)) {
                return k.n;
            }
        } catch (InterruptedException | ExecutionException unused) {
        }
        return null;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x007e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a() throws java.lang.IllegalAccessException, java.lang.reflect.InvocationTargetException {
        /*
            r9 = this;
            com.google.android.gms.internal.ads.abk r0 = d
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x002b
            com.google.android.gms.internal.ads.abk r0 = d
            java.lang.String r0 = r0.a
            boolean r0 = com.google.android.gms.internal.ads.adg.b(r0)
            if (r0 != 0) goto L_0x002b
            com.google.android.gms.internal.ads.abk r0 = d
            java.lang.String r0 = r0.a
            java.lang.String r3 = "E"
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x002b
            com.google.android.gms.internal.ads.abk r0 = d
            java.lang.String r0 = r0.a
            java.lang.String r3 = "0000000000000000000000000000000000000000000000000000000000000000"
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x0029
            goto L_0x002b
        L_0x0029:
            r0 = r2
            goto L_0x002c
        L_0x002b:
            r0 = r1
        L_0x002c:
            if (r0 == 0) goto L_0x00e1
            java.lang.Object r0 = e
            monitor-enter(r0)
            com.google.android.gms.internal.ads.ta r3 = r9.f     // Catch:{ all -> 0x00de }
            r3 = 0
            boolean r4 = com.google.android.gms.internal.ads.adg.b(r3)     // Catch:{ all -> 0x00de }
            r5 = 3
            r6 = 2
            if (r4 != 0) goto L_0x003e
            r4 = 4
            goto L_0x0081
        L_0x003e:
            com.google.android.gms.internal.ads.ta r4 = r9.f     // Catch:{ all -> 0x00de }
            com.google.android.gms.internal.ads.adg.b(r3)     // Catch:{ all -> 0x00de }
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r2)     // Catch:{ all -> 0x00de }
            boolean r4 = r4.booleanValue()     // Catch:{ all -> 0x00de }
            if (r4 == 0) goto L_0x0080
            com.google.android.gms.internal.ads.acy r4 = r9.a     // Catch:{ all -> 0x00de }
            boolean r4 = r4.i()     // Catch:{ all -> 0x00de }
            if (r4 == 0) goto L_0x007b
            com.google.android.gms.internal.ads.akb<java.lang.Boolean> r4 = com.google.android.gms.internal.ads.akl.bO     // Catch:{ all -> 0x00de }
            com.google.android.gms.internal.ads.akj r7 = com.google.android.gms.internal.ads.ajh.f()     // Catch:{ all -> 0x00de }
            java.lang.Object r4 = r7.a(r4)     // Catch:{ all -> 0x00de }
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ all -> 0x00de }
            boolean r4 = r4.booleanValue()     // Catch:{ all -> 0x00de }
            if (r4 == 0) goto L_0x007b
            com.google.android.gms.internal.ads.akb<java.lang.Boolean> r4 = com.google.android.gms.internal.ads.akl.bP     // Catch:{ all -> 0x00de }
            com.google.android.gms.internal.ads.akj r7 = com.google.android.gms.internal.ads.ajh.f()     // Catch:{ all -> 0x00de }
            java.lang.Object r4 = r7.a(r4)     // Catch:{ all -> 0x00de }
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ all -> 0x00de }
            boolean r4 = r4.booleanValue()     // Catch:{ all -> 0x00de }
            if (r4 == 0) goto L_0x007b
            r4 = r1
            goto L_0x007c
        L_0x007b:
            r4 = r2
        L_0x007c:
            if (r4 == 0) goto L_0x0080
            r4 = r5
            goto L_0x0081
        L_0x0080:
            r4 = r6
        L_0x0081:
            java.lang.reflect.Method r7 = r9.c     // Catch:{ all -> 0x00de }
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ all -> 0x00de }
            com.google.android.gms.internal.ads.acy r8 = r9.a     // Catch:{ all -> 0x00de }
            android.content.Context r8 = r8.a()     // Catch:{ all -> 0x00de }
            r5[r2] = r8     // Catch:{ all -> 0x00de }
            if (r4 != r6) goto L_0x0090
            r2 = r1
        L_0x0090:
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)     // Catch:{ all -> 0x00de }
            r5[r1] = r2     // Catch:{ all -> 0x00de }
            com.google.android.gms.internal.ads.akb<java.lang.Boolean> r1 = com.google.android.gms.internal.ads.akl.bI     // Catch:{ all -> 0x00de }
            com.google.android.gms.internal.ads.akj r2 = com.google.android.gms.internal.ads.ajh.f()     // Catch:{ all -> 0x00de }
            java.lang.Object r1 = r2.a(r1)     // Catch:{ all -> 0x00de }
            r5[r6] = r1     // Catch:{ all -> 0x00de }
            java.lang.Object r1 = r7.invoke(r3, r5)     // Catch:{ all -> 0x00de }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ all -> 0x00de }
            com.google.android.gms.internal.ads.abk r2 = new com.google.android.gms.internal.ads.abk     // Catch:{ all -> 0x00de }
            r2.<init>(r1)     // Catch:{ all -> 0x00de }
            d = r2     // Catch:{ all -> 0x00de }
            java.lang.String r1 = r2.a     // Catch:{ all -> 0x00de }
            boolean r1 = com.google.android.gms.internal.ads.adg.b(r1)     // Catch:{ all -> 0x00de }
            if (r1 != 0) goto L_0x00c3
            com.google.android.gms.internal.ads.abk r1 = d     // Catch:{ all -> 0x00de }
            java.lang.String r1 = r1.a     // Catch:{ all -> 0x00de }
            java.lang.String r2 = "E"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x00de }
            if (r1 == 0) goto L_0x00dc
        L_0x00c3:
            switch(r4) {
                case 3: goto L_0x00ce;
                case 4: goto L_0x00c7;
                default: goto L_0x00c6;
            }     // Catch:{ all -> 0x00de }
        L_0x00c6:
            goto L_0x00dc
        L_0x00c7:
            com.google.android.gms.internal.ads.abk r1 = d     // Catch:{ all -> 0x00de }
            java.lang.String r2 = r3.a     // Catch:{ all -> 0x00de }
            r1.a = r2     // Catch:{ all -> 0x00de }
            goto L_0x00dc
        L_0x00ce:
            java.lang.String r1 = r9.c()     // Catch:{ all -> 0x00de }
            boolean r2 = com.google.android.gms.internal.ads.adg.b(r1)     // Catch:{ all -> 0x00de }
            if (r2 != 0) goto L_0x00dc
            com.google.android.gms.internal.ads.abk r2 = d     // Catch:{ all -> 0x00de }
            r2.a = r1     // Catch:{ all -> 0x00de }
        L_0x00dc:
            monitor-exit(r0)     // Catch:{ all -> 0x00de }
            goto L_0x00e1
        L_0x00de:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00de }
            throw r1
        L_0x00e1:
            com.google.android.gms.internal.ads.vy r0 = r9.b
            monitor-enter(r0)
            com.google.android.gms.internal.ads.abk r1 = d     // Catch:{ all -> 0x0116 }
            if (r1 == 0) goto L_0x0114
            com.google.android.gms.internal.ads.vy r1 = r9.b     // Catch:{ all -> 0x0116 }
            com.google.android.gms.internal.ads.abk r2 = d     // Catch:{ all -> 0x0116 }
            java.lang.String r2 = r2.a     // Catch:{ all -> 0x0116 }
            r1.n = r2     // Catch:{ all -> 0x0116 }
            com.google.android.gms.internal.ads.vy r1 = r9.b     // Catch:{ all -> 0x0116 }
            com.google.android.gms.internal.ads.abk r2 = d     // Catch:{ all -> 0x0116 }
            long r2 = r2.b     // Catch:{ all -> 0x0116 }
            java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch:{ all -> 0x0116 }
            r1.t = r2     // Catch:{ all -> 0x0116 }
            com.google.android.gms.internal.ads.vy r1 = r9.b     // Catch:{ all -> 0x0116 }
            com.google.android.gms.internal.ads.abk r2 = d     // Catch:{ all -> 0x0116 }
            java.lang.String r2 = r2.c     // Catch:{ all -> 0x0116 }
            r1.s = r2     // Catch:{ all -> 0x0116 }
            com.google.android.gms.internal.ads.vy r1 = r9.b     // Catch:{ all -> 0x0116 }
            com.google.android.gms.internal.ads.abk r2 = d     // Catch:{ all -> 0x0116 }
            java.lang.String r2 = r2.d     // Catch:{ all -> 0x0116 }
            r1.C = r2     // Catch:{ all -> 0x0116 }
            com.google.android.gms.internal.ads.vy r1 = r9.b     // Catch:{ all -> 0x0116 }
            com.google.android.gms.internal.ads.abk r2 = d     // Catch:{ all -> 0x0116 }
            java.lang.String r2 = r2.e     // Catch:{ all -> 0x0116 }
            r1.D = r2     // Catch:{ all -> 0x0116 }
        L_0x0114:
            monitor-exit(r0)     // Catch:{ all -> 0x0116 }
            return
        L_0x0116:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0116 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.adn.a():void");
    }
}
