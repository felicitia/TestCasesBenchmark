package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Binder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.ao;

@bu
public final class agy {
    private final Runnable a = new agz(this);
    /* access modifiers changed from: private */
    public final Object b = new Object();
    /* access modifiers changed from: private */
    @Nullable
    public ahd c;
    @Nullable
    private Context d;
    /* access modifiers changed from: private */
    @Nullable
    public zzho e;

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void b() {
        /*
            r6 = this;
            java.lang.Object r0 = r6.b
            monitor-enter(r0)
            android.content.Context r1 = r6.d     // Catch:{ all -> 0x0030 }
            if (r1 == 0) goto L_0x002e
            com.google.android.gms.internal.ads.ahd r1 = r6.c     // Catch:{ all -> 0x0030 }
            if (r1 == 0) goto L_0x000c
            goto L_0x002e
        L_0x000c:
            com.google.android.gms.internal.ads.ahb r1 = new com.google.android.gms.internal.ads.ahb     // Catch:{ all -> 0x0030 }
            r1.<init>(r6)     // Catch:{ all -> 0x0030 }
            com.google.android.gms.internal.ads.ahc r2 = new com.google.android.gms.internal.ads.ahc     // Catch:{ all -> 0x0030 }
            r2.<init>(r6)     // Catch:{ all -> 0x0030 }
            com.google.android.gms.internal.ads.ahd r3 = new com.google.android.gms.internal.ads.ahd     // Catch:{ all -> 0x0030 }
            android.content.Context r4 = r6.d     // Catch:{ all -> 0x0030 }
            com.google.android.gms.internal.ads.jb r5 = com.google.android.gms.ads.internal.ao.t()     // Catch:{ all -> 0x0030 }
            android.os.Looper r5 = r5.a()     // Catch:{ all -> 0x0030 }
            r3.<init>(r4, r5, r1, r2)     // Catch:{ all -> 0x0030 }
            r6.c = r3     // Catch:{ all -> 0x0030 }
            com.google.android.gms.internal.ads.ahd r1 = r6.c     // Catch:{ all -> 0x0030 }
            r1.checkAvailabilityAndConnect()     // Catch:{ all -> 0x0030 }
            monitor-exit(r0)     // Catch:{ all -> 0x0030 }
            return
        L_0x002e:
            monitor-exit(r0)     // Catch:{ all -> 0x0030 }
            return
        L_0x0030:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0030 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.agy.b():void");
    }

    /* access modifiers changed from: private */
    public final void c() {
        synchronized (this.b) {
            if (this.c != null) {
                if (this.c.isConnected() || this.c.isConnecting()) {
                    this.c.disconnect();
                }
                this.c = null;
                this.e = null;
                Binder.flushPendingCommands();
            }
        }
    }

    public final zzhi a(zzhl zzhl) {
        synchronized (this.b) {
            if (this.e == null) {
                zzhi zzhi = new zzhi();
                return zzhi;
            }
            try {
                zzhi zza = this.e.zza(zzhl);
                return zza;
            } catch (RemoteException e2) {
                gv.b("Unable to call into cache service.", e2);
                return new zzhi();
            }
        }
    }

    public final void a() {
        if (((Boolean) ajh.f().a(akl.cF)).booleanValue()) {
            synchronized (this.b) {
                b();
                ao.e();
                hd.a.removeCallbacks(this.a);
                ao.e();
                hd.a.postDelayed(this.a, ((Long) ajh.f().a(akl.cG)).longValue());
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0047, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(android.content.Context r3) {
        /*
            r2 = this;
            if (r3 != 0) goto L_0x0003
            return
        L_0x0003:
            java.lang.Object r0 = r2.b
            monitor-enter(r0)
            android.content.Context r1 = r2.d     // Catch:{ all -> 0x0048 }
            if (r1 == 0) goto L_0x000c
            monitor-exit(r0)     // Catch:{ all -> 0x0048 }
            return
        L_0x000c:
            android.content.Context r3 = r3.getApplicationContext()     // Catch:{ all -> 0x0048 }
            r2.d = r3     // Catch:{ all -> 0x0048 }
            com.google.android.gms.internal.ads.akb<java.lang.Boolean> r3 = com.google.android.gms.internal.ads.akl.cE     // Catch:{ all -> 0x0048 }
            com.google.android.gms.internal.ads.akj r1 = com.google.android.gms.internal.ads.ajh.f()     // Catch:{ all -> 0x0048 }
            java.lang.Object r3 = r1.a(r3)     // Catch:{ all -> 0x0048 }
            java.lang.Boolean r3 = (java.lang.Boolean) r3     // Catch:{ all -> 0x0048 }
            boolean r3 = r3.booleanValue()     // Catch:{ all -> 0x0048 }
            if (r3 == 0) goto L_0x0028
            r2.b()     // Catch:{ all -> 0x0048 }
            goto L_0x0046
        L_0x0028:
            com.google.android.gms.internal.ads.akb<java.lang.Boolean> r3 = com.google.android.gms.internal.ads.akl.cD     // Catch:{ all -> 0x0048 }
            com.google.android.gms.internal.ads.akj r1 = com.google.android.gms.internal.ads.ajh.f()     // Catch:{ all -> 0x0048 }
            java.lang.Object r3 = r1.a(r3)     // Catch:{ all -> 0x0048 }
            java.lang.Boolean r3 = (java.lang.Boolean) r3     // Catch:{ all -> 0x0048 }
            boolean r3 = r3.booleanValue()     // Catch:{ all -> 0x0048 }
            if (r3 == 0) goto L_0x0046
            com.google.android.gms.internal.ads.aha r3 = new com.google.android.gms.internal.ads.aha     // Catch:{ all -> 0x0048 }
            r3.<init>(r2)     // Catch:{ all -> 0x0048 }
            com.google.android.gms.internal.ads.aga r1 = com.google.android.gms.ads.internal.ao.h()     // Catch:{ all -> 0x0048 }
            r1.a(r3)     // Catch:{ all -> 0x0048 }
        L_0x0046:
            monitor-exit(r0)     // Catch:{ all -> 0x0048 }
            return
        L_0x0048:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0048 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.agy.a(android.content.Context):void");
    }
}
