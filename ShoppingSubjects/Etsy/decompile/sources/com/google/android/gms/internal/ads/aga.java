package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.common.util.PlatformVersion;

@bu
public final class aga {
    private final Object a = new Object();
    private agb b = null;
    private boolean c = false;

    @Nullable
    public final Activity a() {
        synchronized (this.a) {
            if (!PlatformVersion.isAtLeastIceCreamSandwich()) {
                return null;
            }
            if (this.b == null) {
                return null;
            }
            Activity a2 = this.b.a();
            return a2;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x004f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(android.content.Context r5) {
        /*
            r4 = this;
            java.lang.Object r0 = r4.a
            monitor-enter(r0)
            boolean r1 = r4.c     // Catch:{ all -> 0x0050 }
            if (r1 != 0) goto L_0x004e
            boolean r1 = com.google.android.gms.common.util.PlatformVersion.isAtLeastIceCreamSandwich()     // Catch:{ all -> 0x0050 }
            if (r1 != 0) goto L_0x000f
            monitor-exit(r0)     // Catch:{ all -> 0x0050 }
            return
        L_0x000f:
            com.google.android.gms.internal.ads.akb<java.lang.Boolean> r1 = com.google.android.gms.internal.ads.akl.aG     // Catch:{ all -> 0x0050 }
            com.google.android.gms.internal.ads.akj r2 = com.google.android.gms.internal.ads.ajh.f()     // Catch:{ all -> 0x0050 }
            java.lang.Object r1 = r2.a(r1)     // Catch:{ all -> 0x0050 }
            java.lang.Boolean r1 = (java.lang.Boolean) r1     // Catch:{ all -> 0x0050 }
            boolean r1 = r1.booleanValue()     // Catch:{ all -> 0x0050 }
            if (r1 != 0) goto L_0x0023
            monitor-exit(r0)     // Catch:{ all -> 0x0050 }
            return
        L_0x0023:
            r1 = 0
            android.content.Context r2 = r5.getApplicationContext()     // Catch:{ all -> 0x0050 }
            if (r2 != 0) goto L_0x002b
            r2 = r5
        L_0x002b:
            boolean r3 = r2 instanceof android.app.Application     // Catch:{ all -> 0x0050 }
            if (r3 == 0) goto L_0x0032
            r1 = r2
            android.app.Application r1 = (android.app.Application) r1     // Catch:{ all -> 0x0050 }
        L_0x0032:
            if (r1 != 0) goto L_0x003b
            java.lang.String r5 = "Can not cast Context to Application"
            com.google.android.gms.internal.ads.gv.e(r5)     // Catch:{ all -> 0x0050 }
            monitor-exit(r0)     // Catch:{ all -> 0x0050 }
            return
        L_0x003b:
            com.google.android.gms.internal.ads.agb r2 = r4.b     // Catch:{ all -> 0x0050 }
            if (r2 != 0) goto L_0x0046
            com.google.android.gms.internal.ads.agb r2 = new com.google.android.gms.internal.ads.agb     // Catch:{ all -> 0x0050 }
            r2.<init>()     // Catch:{ all -> 0x0050 }
            r4.b = r2     // Catch:{ all -> 0x0050 }
        L_0x0046:
            com.google.android.gms.internal.ads.agb r2 = r4.b     // Catch:{ all -> 0x0050 }
            r2.a(r1, r5)     // Catch:{ all -> 0x0050 }
            r5 = 1
            r4.c = r5     // Catch:{ all -> 0x0050 }
        L_0x004e:
            monitor-exit(r0)     // Catch:{ all -> 0x0050 }
            return
        L_0x0050:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0050 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.aga.a(android.content.Context):void");
    }

    public final void a(agd agd) {
        synchronized (this.a) {
            if (PlatformVersion.isAtLeastIceCreamSandwich()) {
                if (((Boolean) ajh.f().a(akl.aG)).booleanValue()) {
                    if (this.b == null) {
                        this.b = new agb();
                    }
                    this.b.a(agd);
                }
            }
        }
    }

    @Nullable
    public final Context b() {
        synchronized (this.a) {
            if (!PlatformVersion.isAtLeastIceCreamSandwich()) {
                return null;
            }
            if (this.b == null) {
                return null;
            }
            Context b2 = this.b.b();
            return b2;
        }
    }
}
