package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbc;
import java.util.concurrent.Future;

@bu
public final class ag extends gq {
    /* access modifiers changed from: private */
    public final z a;
    private final zzaej b;
    private final gb c;
    private final ai d;
    private final Object e;
    private Future<ga> f;

    public ag(Context context, zzbc zzbc, gb gbVar, ack ack, z zVar, aky aky) {
        ai aiVar = new ai(context, zzbc, new in(context), ack, gbVar, aky);
        this(gbVar, zVar, aiVar);
    }

    private ag(gb gbVar, z zVar, ai aiVar) {
        this.e = new Object();
        this.c = gbVar;
        this.b = gbVar.b;
        this.a = zVar;
        this.d = aiVar;
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0034  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0037  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a() {
        /*
            r60 = this;
            r1 = r60
            r2 = 0
            r3 = 0
            java.lang.Object r4 = r1.e     // Catch:{ TimeoutException -> 0x0025, InterruptedException | CancellationException | ExecutionException -> 0x0031 }
            monitor-enter(r4)     // Catch:{ TimeoutException -> 0x0025, InterruptedException | CancellationException | ExecutionException -> 0x0031 }
            com.google.android.gms.internal.ads.ai r5 = r1.d     // Catch:{ all -> 0x0021 }
            com.google.android.gms.internal.ads.kt r5 = com.google.android.gms.internal.ads.hb.a(r5)     // Catch:{ all -> 0x0021 }
            r1.f = r5     // Catch:{ all -> 0x0021 }
            monitor-exit(r4)     // Catch:{ all -> 0x0021 }
            java.util.concurrent.Future<com.google.android.gms.internal.ads.ga> r4 = r1.f     // Catch:{ TimeoutException -> 0x0025, InterruptedException | CancellationException | ExecutionException -> 0x0031 }
            r5 = 60000(0xea60, double:2.9644E-319)
            java.util.concurrent.TimeUnit r7 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ TimeoutException -> 0x0025, InterruptedException | CancellationException | ExecutionException -> 0x0031 }
            java.lang.Object r4 = r4.get(r5, r7)     // Catch:{ TimeoutException -> 0x0025, InterruptedException | CancellationException | ExecutionException -> 0x0031 }
            com.google.android.gms.internal.ads.ga r4 = (com.google.android.gms.internal.ads.ga) r4     // Catch:{ TimeoutException -> 0x0025, InterruptedException | CancellationException | ExecutionException -> 0x0031 }
            r2 = -2
            r8 = r2
            r3 = r4
            goto L_0x0032
        L_0x0021:
            r0 = move-exception
            r5 = r0
            monitor-exit(r4)     // Catch:{ all -> 0x0021 }
            throw r5     // Catch:{ TimeoutException -> 0x0025, InterruptedException | CancellationException | ExecutionException -> 0x0031 }
        L_0x0025:
            java.lang.String r2 = "Timed out waiting for native ad."
            com.google.android.gms.internal.ads.gv.e(r2)
            r2 = 2
            java.util.concurrent.Future<com.google.android.gms.internal.ads.ga> r4 = r1.f
            r5 = 1
            r4.cancel(r5)
        L_0x0031:
            r8 = r2
        L_0x0032:
            if (r3 == 0) goto L_0x0037
            r2 = r3
            goto L_0x00ea
        L_0x0037:
            com.google.android.gms.internal.ads.ga r2 = new com.google.android.gms.internal.ads.ga
            com.google.android.gms.internal.ads.gb r3 = r1.c
            com.google.android.gms.internal.ads.zzaef r3 = r3.a
            com.google.android.gms.internal.ads.zzjj r5 = r3.zzccv
            com.google.android.gms.internal.ads.zzaej r3 = r1.b
            int r11 = r3.orientation
            com.google.android.gms.internal.ads.zzaej r3 = r1.b
            long r12 = r3.zzbsu
            com.google.android.gms.internal.ads.gb r3 = r1.c
            com.google.android.gms.internal.ads.zzaef r3 = r3.a
            java.lang.String r14 = r3.zzccy
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r20 = 0
            com.google.android.gms.internal.ads.zzaej r3 = r1.b
            long r3 = r3.zzcer
            com.google.android.gms.internal.ads.gb r15 = r1.c
            com.google.android.gms.internal.ads.zzjn r15 = r15.d
            com.google.android.gms.internal.ads.zzaej r10 = r1.b
            r48 = r11
            long r10 = r10.zzcep
            com.google.android.gms.internal.ads.gb r9 = r1.c
            r49 = r10
            long r10 = r9.f
            com.google.android.gms.internal.ads.zzaej r9 = r1.b
            r51 = r10
            long r10 = r9.zzceu
            com.google.android.gms.internal.ads.zzaej r9 = r1.b
            java.lang.String r9 = r9.zzcev
            com.google.android.gms.internal.ads.gb r7 = r1.c
            org.json.JSONObject r7 = r7.h
            r32 = 0
            r33 = 0
            r34 = 0
            r35 = 0
            com.google.android.gms.internal.ads.gb r6 = r1.c
            com.google.android.gms.internal.ads.zzaej r6 = r6.b
            boolean r6 = r6.zzcfh
            r53 = r3
            com.google.android.gms.internal.ads.gb r3 = r1.c
            com.google.android.gms.internal.ads.zzaej r3 = r3.b
            com.google.android.gms.internal.ads.zzael r3 = r3.zzcfi
            r38 = 0
            r39 = 0
            com.google.android.gms.internal.ads.zzaej r4 = r1.b
            java.lang.String r4 = r4.zzcfl
            r55 = r4
            com.google.android.gms.internal.ads.gb r4 = r1.c
            com.google.android.gms.internal.ads.ahh r4 = r4.i
            r56 = r4
            com.google.android.gms.internal.ads.gb r4 = r1.c
            com.google.android.gms.internal.ads.zzaej r4 = r4.b
            boolean r4 = r4.zzzl
            r43 = 0
            r57 = r4
            com.google.android.gms.internal.ads.gb r4 = r1.c
            com.google.android.gms.internal.ads.zzaej r4 = r4.b
            boolean r4 = r4.zzcfp
            r45 = 0
            r58 = r4
            com.google.android.gms.internal.ads.gb r4 = r1.c
            com.google.android.gms.internal.ads.zzaej r4 = r4.b
            boolean r4 = r4.zzzm
            r59 = r4
            com.google.android.gms.internal.ads.gb r4 = r1.c
            com.google.android.gms.internal.ads.zzaej r4 = r4.b
            java.lang.String r4 = r4.zzcfq
            r47 = r4
            r21 = r53
            r40 = r55
            r41 = r56
            r42 = r57
            r44 = r58
            r46 = r59
            r4 = r2
            r36 = r6
            r6 = 0
            r31 = r7
            r7 = 0
            r30 = r9
            r9 = 0
            r28 = r10
            r24 = r49
            r26 = r51
            r10 = 0
            r11 = r48
            r23 = r15
            r15 = 0
            r37 = r3
            r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12, r14, r15, r16, r17, r18, r19, r20, r21, r23, r24, r26, r28, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47)
        L_0x00ea:
            android.os.Handler r3 = com.google.android.gms.internal.ads.hd.a
            com.google.android.gms.internal.ads.ah r4 = new com.google.android.gms.internal.ads.ah
            r4.<init>(r1, r2)
            r3.post(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.ag.a():void");
    }

    public final void c_() {
        synchronized (this.e) {
            if (this.f != null) {
                this.f.cancel(true);
            }
        }
    }
}
