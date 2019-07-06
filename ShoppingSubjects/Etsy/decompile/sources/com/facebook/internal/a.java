package com.facebook.internal;

import android.content.Intent;
import java.util.UUID;

/* compiled from: AppCall */
public class a {
    private static a a;
    private UUID b;
    private Intent c;
    private int d;

    public static a a() {
        return a;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0021, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized com.facebook.internal.a a(java.util.UUID r4, int r5) {
        /*
            java.lang.Class<com.facebook.internal.a> r0 = com.facebook.internal.a.class
            monitor-enter(r0)
            com.facebook.internal.a r1 = a()     // Catch:{ all -> 0x0022 }
            r2 = 0
            if (r1 == 0) goto L_0x0020
            java.util.UUID r3 = r1.c()     // Catch:{ all -> 0x0022 }
            boolean r4 = r3.equals(r4)     // Catch:{ all -> 0x0022 }
            if (r4 == 0) goto L_0x0020
            int r4 = r1.d()     // Catch:{ all -> 0x0022 }
            if (r4 == r5) goto L_0x001b
            goto L_0x0020
        L_0x001b:
            a(r2)     // Catch:{ all -> 0x0022 }
            monitor-exit(r0)
            return r1
        L_0x0020:
            monitor-exit(r0)
            return r2
        L_0x0022:
            r4 = move-exception
            monitor-exit(r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.a.a(java.util.UUID, int):com.facebook.internal.a");
    }

    private static synchronized boolean a(a aVar) {
        boolean z;
        synchronized (a.class) {
            a a2 = a();
            a = aVar;
            z = a2 != null;
        }
        return z;
    }

    public a(int i) {
        this(i, UUID.randomUUID());
    }

    public a(int i, UUID uuid) {
        this.b = uuid;
        this.d = i;
    }

    public Intent b() {
        return this.c;
    }

    public UUID c() {
        return this.b;
    }

    public int d() {
        return this.d;
    }

    public void a(Intent intent) {
        this.c = intent;
    }

    public boolean e() {
        return a(this);
    }
}
