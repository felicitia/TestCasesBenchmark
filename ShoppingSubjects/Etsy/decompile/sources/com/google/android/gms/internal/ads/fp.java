package com.google.android.gms.internal.ads;

import android.content.Context;

@bu
public final class fp implements afn {
    private final Context a;
    private final Object b;
    private String c;
    private boolean d;

    public fp(Context context, String str) {
        if (context.getApplicationContext() != null) {
            context = context.getApplicationContext();
        }
        this.a = context;
        this.c = str;
        this.d = false;
        this.b = new Object();
    }

    public final void a(String str) {
        this.c = str;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003e, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(boolean r4) {
        /*
            r3 = this;
            com.google.android.gms.internal.ads.fq r0 = com.google.android.gms.ads.internal.ao.B()
            android.content.Context r1 = r3.a
            boolean r0 = r0.a(r1)
            if (r0 != 0) goto L_0x000d
            return
        L_0x000d:
            java.lang.Object r0 = r3.b
            monitor-enter(r0)
            boolean r1 = r3.d     // Catch:{ all -> 0x003f }
            if (r1 != r4) goto L_0x0016
            monitor-exit(r0)     // Catch:{ all -> 0x003f }
            return
        L_0x0016:
            r3.d = r4     // Catch:{ all -> 0x003f }
            java.lang.String r4 = r3.c     // Catch:{ all -> 0x003f }
            boolean r4 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x003f }
            if (r4 == 0) goto L_0x0022
            monitor-exit(r0)     // Catch:{ all -> 0x003f }
            return
        L_0x0022:
            boolean r4 = r3.d     // Catch:{ all -> 0x003f }
            if (r4 == 0) goto L_0x0032
            com.google.android.gms.internal.ads.fq r4 = com.google.android.gms.ads.internal.ao.B()     // Catch:{ all -> 0x003f }
            android.content.Context r1 = r3.a     // Catch:{ all -> 0x003f }
            java.lang.String r2 = r3.c     // Catch:{ all -> 0x003f }
            r4.a(r1, r2)     // Catch:{ all -> 0x003f }
            goto L_0x003d
        L_0x0032:
            com.google.android.gms.internal.ads.fq r4 = com.google.android.gms.ads.internal.ao.B()     // Catch:{ all -> 0x003f }
            android.content.Context r1 = r3.a     // Catch:{ all -> 0x003f }
            java.lang.String r2 = r3.c     // Catch:{ all -> 0x003f }
            r4.b(r1, r2)     // Catch:{ all -> 0x003f }
        L_0x003d:
            monitor-exit(r0)     // Catch:{ all -> 0x003f }
            return
        L_0x003f:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x003f }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.fp.a(boolean):void");
    }

    public final void zza(afm afm) {
        a(afm.a);
    }
}
