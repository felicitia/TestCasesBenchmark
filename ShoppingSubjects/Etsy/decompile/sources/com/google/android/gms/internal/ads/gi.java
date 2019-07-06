package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.ao;

final class gi {
    private final Object a;
    private volatile int b;
    private volatile long c;

    private gi() {
        this.a = new Object();
        this.b = gj.a;
        this.c = 0;
    }

    /* synthetic */ gi(gh ghVar) {
        this();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void a(int r5, int r6) {
        /*
            r4 = this;
            r4.d()
            com.google.android.gms.common.util.Clock r0 = com.google.android.gms.ads.internal.ao.l()
            long r0 = r0.currentTimeMillis()
            java.lang.Object r2 = r4.a
            monitor-enter(r2)
            int r3 = r4.b     // Catch:{ all -> 0x0020 }
            if (r3 == r5) goto L_0x0014
            monitor-exit(r2)     // Catch:{ all -> 0x0020 }
            return
        L_0x0014:
            r4.b = r6     // Catch:{ all -> 0x0020 }
            int r5 = r4.b     // Catch:{ all -> 0x0020 }
            int r6 = com.google.android.gms.internal.ads.gj.c     // Catch:{ all -> 0x0020 }
            if (r5 != r6) goto L_0x001e
            r4.c = r0     // Catch:{ all -> 0x0020 }
        L_0x001e:
            monitor-exit(r2)     // Catch:{ all -> 0x0020 }
            return
        L_0x0020:
            r5 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0020 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.gi.a(int, int):void");
    }

    private final void d() {
        long currentTimeMillis = ao.l().currentTimeMillis();
        synchronized (this.a) {
            if (this.b == gj.c) {
                if (this.c + ((Long) ajh.f().a(akl.di)).longValue() <= currentTimeMillis) {
                    this.b = gj.a;
                }
            }
        }
    }

    public final void a(boolean z) {
        int i;
        int i2;
        if (z) {
            i = gj.a;
            i2 = gj.b;
        } else {
            i = gj.b;
            i2 = gj.a;
        }
        a(i, i2);
    }

    public final boolean a() {
        d();
        return this.b == gj.b;
    }

    public final boolean b() {
        d();
        return this.b == gj.c;
    }

    public final void c() {
        a(gj.b, gj.c);
    }
}
