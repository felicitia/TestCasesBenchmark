package com.google.android.gms.internal.ads;

import android.content.Context;

@bu
public abstract class v extends gq {
    protected final z a;
    protected final Context b;
    protected final Object c = new Object();
    protected final Object d = new Object();
    protected final gb e;
    protected zzaej f;

    protected v(Context context, gb gbVar, z zVar) {
        super(true);
        this.b = context;
        this.e = gbVar;
        this.f = gbVar.b;
        this.a = zVar;
    }

    /* access modifiers changed from: protected */
    public abstract ga a(int i);

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0033 A[Catch:{ zzabk -> 0x0014 }] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x003b A[Catch:{ zzabk -> 0x0014 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.c
            monitor-enter(r0)
            java.lang.String r1 = "AdRendererBackgroundTask started."
            com.google.android.gms.internal.ads.gv.b(r1)     // Catch:{ all -> 0x0060 }
            com.google.android.gms.internal.ads.gb r1 = r5.e     // Catch:{ all -> 0x0060 }
            int r1 = r1.e     // Catch:{ all -> 0x0060 }
            long r2 = android.os.SystemClock.elapsedRealtime()     // Catch:{ zzabk -> 0x0014 }
            r5.a(r2)     // Catch:{ zzabk -> 0x0014 }
            goto L_0x0050
        L_0x0014:
            r1 = move-exception
            int r2 = r1.getErrorCode()     // Catch:{ all -> 0x0060 }
            r3 = 3
            if (r2 == r3) goto L_0x0028
            r3 = -1
            if (r2 != r3) goto L_0x0020
            goto L_0x0028
        L_0x0020:
            java.lang.String r1 = r1.getMessage()     // Catch:{ all -> 0x0060 }
            com.google.android.gms.internal.ads.gv.e(r1)     // Catch:{ all -> 0x0060 }
            goto L_0x002f
        L_0x0028:
            java.lang.String r1 = r1.getMessage()     // Catch:{ all -> 0x0060 }
            com.google.android.gms.internal.ads.gv.d(r1)     // Catch:{ all -> 0x0060 }
        L_0x002f:
            com.google.android.gms.internal.ads.zzaej r1 = r5.f     // Catch:{ all -> 0x0060 }
            if (r1 != 0) goto L_0x003b
            com.google.android.gms.internal.ads.zzaej r1 = new com.google.android.gms.internal.ads.zzaej     // Catch:{ all -> 0x0060 }
            r1.<init>(r2)     // Catch:{ all -> 0x0060 }
        L_0x0038:
            r5.f = r1     // Catch:{ all -> 0x0060 }
            goto L_0x0045
        L_0x003b:
            com.google.android.gms.internal.ads.zzaej r1 = new com.google.android.gms.internal.ads.zzaej     // Catch:{ all -> 0x0060 }
            com.google.android.gms.internal.ads.zzaej r3 = r5.f     // Catch:{ all -> 0x0060 }
            long r3 = r3.zzbsu     // Catch:{ all -> 0x0060 }
            r1.<init>(r2, r3)     // Catch:{ all -> 0x0060 }
            goto L_0x0038
        L_0x0045:
            android.os.Handler r1 = com.google.android.gms.internal.ads.hd.a     // Catch:{ all -> 0x0060 }
            com.google.android.gms.internal.ads.w r3 = new com.google.android.gms.internal.ads.w     // Catch:{ all -> 0x0060 }
            r3.<init>(r5)     // Catch:{ all -> 0x0060 }
            r1.post(r3)     // Catch:{ all -> 0x0060 }
            r1 = r2
        L_0x0050:
            com.google.android.gms.internal.ads.ga r1 = r5.a(r1)     // Catch:{ all -> 0x0060 }
            android.os.Handler r2 = com.google.android.gms.internal.ads.hd.a     // Catch:{ all -> 0x0060 }
            com.google.android.gms.internal.ads.x r3 = new com.google.android.gms.internal.ads.x     // Catch:{ all -> 0x0060 }
            r3.<init>(r5, r1)     // Catch:{ all -> 0x0060 }
            r2.post(r3)     // Catch:{ all -> 0x0060 }
            monitor-exit(r0)     // Catch:{ all -> 0x0060 }
            return
        L_0x0060:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0060 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.v.a():void");
    }

    /* access modifiers changed from: protected */
    public abstract void a(long j) throws zzabk;

    public void c_() {
    }
}
