package com.threatmetrix.TrustDefender;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import com.threatmetrix.TrustDefender.Profile.Handle;
import com.threatmetrix.TrustDefender.Profile.Result;
import com.threatmetrix.TrustDefender.StrongAuth.StrongAuthCallback;
import com.threatmetrix.TrustDefender.StrongAuth.StrongAuthConfiguration;
import com.threatmetrix.TrustDefender.internal.D2;
import com.threatmetrix.TrustDefender.internal.D2.E;
import com.threatmetrix.TrustDefender.internal.HF;
import com.threatmetrix.TrustDefender.internal.HZ;
import com.threatmetrix.TrustDefender.internal.NH;
import com.threatmetrix.TrustDefender.internal.NK;
import com.threatmetrix.TrustDefender.internal.P.O;
import com.threatmetrix.TrustDefender.internal.PH;
import com.threatmetrix.TrustDefender.internal.TL;
import com.threatmetrix.TrustDefender.internal.TN;
import com.threatmetrix.TrustDefender.internal.U;
import com.threatmetrix.TrustDefender.internal.X7;
import com.threatmetrix.TrustDefender.internal.XU;
import com.threatmetrix.TrustDefender.internal.XU.R;
import com.threatmetrix.TrustDefender.internal.XZ;
import com.threatmetrix.TrustDefender.internal.YB;
import com.threatmetrix.TrustDefender.internal.ZL;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class TrustDefender extends XU {

    /* renamed from: class reason: not valid java name */
    private static final boolean f14class;

    /* renamed from: const reason: not valid java name */
    private static final boolean f15const = (D2.m38do(E.OKHTTP3) != null);

    /* renamed from: if reason: not valid java name */
    public static final String f16if = TL.m331if(TrustDefender.class);

    /* renamed from: throw reason: not valid java name */
    private static volatile TrustDefender f17throw;

    /* renamed from: catch reason: not valid java name */
    private int f18catch = 30000;

    /* renamed from: do reason: not valid java name */
    public Timer f19do;

    /* renamed from: double reason: not valid java name */
    private volatile boolean f20double = false;

    /* renamed from: final reason: not valid java name */
    private boolean f21final = false;

    /* renamed from: float reason: not valid java name */
    private int f22float = 0;

    /* renamed from: int reason: not valid java name */
    public volatile boolean f23int = true;

    /* renamed from: new reason: not valid java name */
    public int f24new;

    /* renamed from: super reason: not valid java name */
    private volatile int f25super = 10000;

    /* renamed from: while reason: not valid java name */
    private volatile int f26while = 0;

    /* JADX WARNING: Can't wrap try/catch for region: R(4:70|71|(1:73)|74) */
    /* JADX WARNING: Code restructure failed: missing block: B:71:?, code lost:
        r4 = com.threatmetrix.TrustDefender.THMStatusCode.THM_StrongAuth_Unsupported;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0131, code lost:
        if (r3 != null) goto L_0x0133;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0133, code lost:
        com.threatmetrix.TrustDefender.internal.YB.f703int.execute(new com.threatmetrix.TrustDefender.TrustDefender.AnonymousClass5(r1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x013d, code lost:
        return;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:70:0x012f */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x018a  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0150  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0169  */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x017b  */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:79:0x0145=Splitter:B:79:0x0145, B:93:0x0177=Splitter:B:93:0x0177, B:86:0x015e=Splitter:B:86:0x015e} */
    /* renamed from: do reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ void m3do(com.threatmetrix.TrustDefender.TrustDefender r17, java.lang.String r18, java.lang.String r19, java.lang.String r20, java.lang.String r21, com.threatmetrix.TrustDefender.internal.X7.E r22, boolean r23, com.threatmetrix.TrustDefender.StrongAuth.StrongAuthCallback r24, com.threatmetrix.TrustDefender.EndNotifier r25, boolean r26) {
        /*
            r1 = r17
            r2 = r18
            r3 = r25
            com.threatmetrix.TrustDefender.internal.VZ r6 = r1.f643else     // Catch:{ O -> 0x0174, InterruptedException -> 0x015b, RuntimeException -> 0x0142, all -> 0x013e }
            boolean r6 = r6.m357if()     // Catch:{ O -> 0x0174, InterruptedException -> 0x015b, RuntimeException -> 0x0142, all -> 0x013e }
            if (r6 != 0) goto L_0x0024
            java.lang.String r4 = f16if     // Catch:{ O -> 0x0174, InterruptedException -> 0x015b, RuntimeException -> 0x0142, all -> 0x013e }
            java.lang.String r6 = "Not inited"
            com.threatmetrix.TrustDefender.internal.TL.m338new(r4, r6)     // Catch:{ O -> 0x0174, InterruptedException -> 0x015b, RuntimeException -> 0x0142, all -> 0x013e }
            com.threatmetrix.TrustDefender.THMStatusCode r4 = com.threatmetrix.TrustDefender.THMStatusCode.THM_Internal_Error     // Catch:{ O -> 0x0174, InterruptedException -> 0x015b, RuntimeException -> 0x0142, all -> 0x013e }
            if (r3 == 0) goto L_0x0023
            java.util.concurrent.Executor r5 = com.threatmetrix.TrustDefender.internal.YB.f703int
            com.threatmetrix.TrustDefender.TrustDefender$5 r6 = new com.threatmetrix.TrustDefender.TrustDefender$5
            r6.<init>(r3, r2, r4)
            r5.execute(r6)
        L_0x0023:
            return
        L_0x0024:
            com.threatmetrix.TrustDefender.internal.VZ r6 = r1.f643else     // Catch:{ O -> 0x0174, InterruptedException -> 0x015b, RuntimeException -> 0x0142, all -> 0x013e }
            int r7 = r1.f637byte     // Catch:{ O -> 0x0174, InterruptedException -> 0x015b, RuntimeException -> 0x0142, all -> 0x013e }
            boolean r6 = r6.m360int(r7)     // Catch:{ O -> 0x0174, InterruptedException -> 0x015b, RuntimeException -> 0x0142, all -> 0x013e }
            if (r6 != 0) goto L_0x003d
            com.threatmetrix.TrustDefender.THMStatusCode r4 = com.threatmetrix.TrustDefender.THMStatusCode.THM_Internal_Error     // Catch:{ O -> 0x0174, InterruptedException -> 0x015b, RuntimeException -> 0x0142, all -> 0x013e }
            if (r3 == 0) goto L_0x003c
            java.util.concurrent.Executor r5 = com.threatmetrix.TrustDefender.internal.YB.f703int
            com.threatmetrix.TrustDefender.TrustDefender$5 r6 = new com.threatmetrix.TrustDefender.TrustDefender$5
            r6.<init>(r3, r2, r4)
            r5.execute(r6)
        L_0x003c:
            return
        L_0x003d:
            com.threatmetrix.TrustDefender.internal.P$O r7 = r1.f640char     // Catch:{ O -> 0x0174, InterruptedException -> 0x015b, RuntimeException -> 0x0142, all -> 0x013e }
            if (r7 != 0) goto L_0x0050
            com.threatmetrix.TrustDefender.THMStatusCode r4 = com.threatmetrix.TrustDefender.THMStatusCode.THM_Internal_Error     // Catch:{ O -> 0x0174, InterruptedException -> 0x015b, RuntimeException -> 0x0142, all -> 0x013e }
            if (r3 == 0) goto L_0x004f
            java.util.concurrent.Executor r5 = com.threatmetrix.TrustDefender.internal.YB.f703int
            com.threatmetrix.TrustDefender.TrustDefender$5 r6 = new com.threatmetrix.TrustDefender.TrustDefender$5
            r6.<init>(r3, r2, r4)
            r5.execute(r6)
        L_0x004f:
            return
        L_0x0050:
            long r14 = java.lang.System.currentTimeMillis()     // Catch:{ O -> 0x0174, InterruptedException -> 0x015b, RuntimeException -> 0x0142, all -> 0x013e }
            com.threatmetrix.TrustDefender.internal.YB r6 = new com.threatmetrix.TrustDefender.internal.YB     // Catch:{ O -> 0x0174, InterruptedException -> 0x015b, RuntimeException -> 0x0142, all -> 0x013e }
            r6.<init>()     // Catch:{ O -> 0x0174, InterruptedException -> 0x015b, RuntimeException -> 0x0142, all -> 0x013e }
            com.threatmetrix.TrustDefender.internal.XU$W r13 = r1.m422new(r2, r6)     // Catch:{ O -> 0x0174, InterruptedException -> 0x015b, RuntimeException -> 0x0142, all -> 0x013e }
            if (r19 != 0) goto L_0x0063
            java.lang.String r8 = ""
            r9 = r8
            goto L_0x0065
        L_0x0063:
            r9 = r19
        L_0x0065:
            com.threatmetrix.TrustDefender.internal.CG r8 = r13.f678for     // Catch:{ IllegalArgumentException -> 0x012f }
            java.lang.String r8 = r8.f73else     // Catch:{ IllegalArgumentException -> 0x012f }
            byte[] r12 = com.threatmetrix.TrustDefender.internal.NK.m213for(r8)     // Catch:{ IllegalArgumentException -> 0x012f }
            if (r20 == 0) goto L_0x0090
            if (r21 != 0) goto L_0x0072
            goto L_0x0090
        L_0x0072:
            if (r26 == 0) goto L_0x0082
            r8 = r22
            r10 = r20
            r11 = r21
            r5 = r13
            r13 = r24
            com.threatmetrix.TrustDefender.internal.X7$L r7 = com.threatmetrix.TrustDefender.internal.X7.m383int(r7, r8, r9, r10, r11, r12, r13)     // Catch:{ O -> 0x0174, InterruptedException -> 0x015b, RuntimeException -> 0x0142, all -> 0x013e }
            goto L_0x0097
        L_0x0082:
            r5 = r13
            r8 = r22
            r10 = r20
            r11 = r21
            r13 = r24
            com.threatmetrix.TrustDefender.internal.X7$L r7 = com.threatmetrix.TrustDefender.internal.X7.m378for(r7, r8, r9, r10, r11, r12, r13)     // Catch:{ O -> 0x0174, InterruptedException -> 0x015b, RuntimeException -> 0x0142, all -> 0x013e }
            goto L_0x0097
        L_0x0090:
            r5 = r13
            com.threatmetrix.TrustDefender.internal.X7$W r7 = com.threatmetrix.TrustDefender.internal.X7.W.MISSING_PARAMETER     // Catch:{ O -> 0x0174, InterruptedException -> 0x015b, RuntimeException -> 0x0142, all -> 0x013e }
            com.threatmetrix.TrustDefender.internal.X7$L r7 = com.threatmetrix.TrustDefender.internal.X7.m384int(r7)     // Catch:{ O -> 0x0174, InterruptedException -> 0x015b, RuntimeException -> 0x0142, all -> 0x013e }
        L_0x0097:
            com.threatmetrix.TrustDefender.internal.X r8 = new com.threatmetrix.TrustDefender.internal.X     // Catch:{ O -> 0x0174, InterruptedException -> 0x015b, RuntimeException -> 0x0142, all -> 0x013e }
            r8.<init>()     // Catch:{ O -> 0x0174, InterruptedException -> 0x015b, RuntimeException -> 0x0142, all -> 0x013e }
            if (r23 == 0) goto L_0x00be
            com.threatmetrix.TrustDefender.internal.X7$W r9 = r7.f617new     // Catch:{ O -> 0x0174, InterruptedException -> 0x015b, RuntimeException -> 0x0142, all -> 0x013e }
            com.threatmetrix.TrustDefender.THMStatusCode r9 = r9.f632long     // Catch:{ O -> 0x0174, InterruptedException -> 0x015b, RuntimeException -> 0x0142, all -> 0x013e }
            com.threatmetrix.TrustDefender.THMStatusCode r10 = com.threatmetrix.TrustDefender.THMStatusCode.THM_OK     // Catch:{ O -> 0x0174, InterruptedException -> 0x015b, RuntimeException -> 0x0142, all -> 0x013e }
            if (r9 == r10) goto L_0x00c7
            java.lang.String r4 = f16if     // Catch:{ O -> 0x0174, InterruptedException -> 0x015b, RuntimeException -> 0x0142, all -> 0x013e }
            java.lang.String r5 = "Strong Auth failed"
            com.threatmetrix.TrustDefender.internal.TL.m338new(r4, r5)     // Catch:{ O -> 0x0174, InterruptedException -> 0x015b, RuntimeException -> 0x0142, all -> 0x013e }
            com.threatmetrix.TrustDefender.internal.X7$W r4 = r7.f617new     // Catch:{ O -> 0x0174, InterruptedException -> 0x015b, RuntimeException -> 0x0142, all -> 0x013e }
            com.threatmetrix.TrustDefender.THMStatusCode r4 = r4.f632long     // Catch:{ O -> 0x0174, InterruptedException -> 0x015b, RuntimeException -> 0x0142, all -> 0x013e }
            if (r3 == 0) goto L_0x00bd
            java.util.concurrent.Executor r5 = com.threatmetrix.TrustDefender.internal.YB.f703int
            com.threatmetrix.TrustDefender.TrustDefender$5 r6 = new com.threatmetrix.TrustDefender.TrustDefender$5
            r6.<init>(r3, r2, r4)
            r5.execute(r6)
        L_0x00bd:
            return
        L_0x00be:
            java.lang.String r9 = "sa_st"
            com.threatmetrix.TrustDefender.internal.X7$W r10 = r7.f617new     // Catch:{ O -> 0x0174, InterruptedException -> 0x015b, RuntimeException -> 0x0142, all -> 0x013e }
            java.lang.String r10 = r10.f633this     // Catch:{ O -> 0x0174, InterruptedException -> 0x015b, RuntimeException -> 0x0142, all -> 0x013e }
            r8.put(r9, r10)     // Catch:{ O -> 0x0174, InterruptedException -> 0x015b, RuntimeException -> 0x0142, all -> 0x013e }
        L_0x00c7:
            java.lang.String r9 = r7.f616int     // Catch:{ O -> 0x0174, InterruptedException -> 0x015b, RuntimeException -> 0x0142, all -> 0x013e }
            if (r9 == 0) goto L_0x00fe
            if (r26 == 0) goto L_0x00d0
            java.lang.String r4 = "sa_pk"
            goto L_0x00d2
        L_0x00d0:
            java.lang.String r4 = "sa_sig"
        L_0x00d2:
            java.lang.String r9 = r7.f616int     // Catch:{ O -> 0x0174, InterruptedException -> 0x015b, RuntimeException -> 0x0142, all -> 0x013e }
            java.lang.String r10 = r7.f616int     // Catch:{ O -> 0x0174, InterruptedException -> 0x015b, RuntimeException -> 0x0142, all -> 0x013e }
            int r10 = r10.length()     // Catch:{ O -> 0x0174, InterruptedException -> 0x015b, RuntimeException -> 0x0142, all -> 0x013e }
            int r10 = r10 + 1
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch:{ O -> 0x0174, InterruptedException -> 0x015b, RuntimeException -> 0x0142, all -> 0x013e }
            java.util.HashMap<java.lang.String, java.lang.Integer> r11 = r8.f606do     // Catch:{ O -> 0x0174, InterruptedException -> 0x015b, RuntimeException -> 0x0142, all -> 0x013e }
            r11.put(r4, r10)     // Catch:{ O -> 0x0174, InterruptedException -> 0x015b, RuntimeException -> 0x0142, all -> 0x013e }
            r10 = 0
            r8.m374for(r4, r9, r10)     // Catch:{ O -> 0x0174, InterruptedException -> 0x015b, RuntimeException -> 0x0142, all -> 0x013e }
            java.lang.String r4 = f16if     // Catch:{ O -> 0x0174, InterruptedException -> 0x015b, RuntimeException -> 0x0142, all -> 0x013e }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ O -> 0x0174, InterruptedException -> 0x015b, RuntimeException -> 0x0142, all -> 0x013e }
            java.lang.String r10 = "Generated registration credential is:"
            r9.<init>(r10)     // Catch:{ O -> 0x0174, InterruptedException -> 0x015b, RuntimeException -> 0x0142, all -> 0x013e }
            java.lang.String r10 = r7.f616int     // Catch:{ O -> 0x0174, InterruptedException -> 0x015b, RuntimeException -> 0x0142, all -> 0x013e }
            r9.append(r10)     // Catch:{ O -> 0x0174, InterruptedException -> 0x015b, RuntimeException -> 0x0142, all -> 0x013e }
            java.lang.String r9 = r9.toString()     // Catch:{ O -> 0x0174, InterruptedException -> 0x015b, RuntimeException -> 0x0142, all -> 0x013e }
            com.threatmetrix.TrustDefender.internal.TL.m338new(r4, r9)     // Catch:{ O -> 0x0174, InterruptedException -> 0x015b, RuntimeException -> 0x0142, all -> 0x013e }
        L_0x00fe:
            com.threatmetrix.TrustDefender.internal.X r4 = r5.m433int(r1, r14, r8)     // Catch:{ O -> 0x0174, InterruptedException -> 0x015b, RuntimeException -> 0x0142, all -> 0x013e }
            com.threatmetrix.TrustDefender.THMStatusCode r5 = r1.m420int(r5, r4, r6)     // Catch:{ O -> 0x0174, InterruptedException -> 0x015b, RuntimeException -> 0x0142, all -> 0x013e }
            r6.m449for()     // Catch:{ O -> 0x012c, InterruptedException -> 0x0129, RuntimeException -> 0x0126 }
            com.threatmetrix.TrustDefender.THMStatusCode r4 = com.threatmetrix.TrustDefender.THMStatusCode.THM_OK     // Catch:{ O -> 0x012c, InterruptedException -> 0x0129, RuntimeException -> 0x0126 }
            if (r5 != r4) goto L_0x0112
            com.threatmetrix.TrustDefender.internal.X7$W r4 = r7.f617new     // Catch:{ O -> 0x012c, InterruptedException -> 0x0129, RuntimeException -> 0x0126 }
            com.threatmetrix.TrustDefender.THMStatusCode r4 = r4.f632long     // Catch:{ O -> 0x012c, InterruptedException -> 0x0129, RuntimeException -> 0x0126 }
            r5 = r4
        L_0x0112:
            java.lang.String r4 = f16if     // Catch:{ O -> 0x012c, InterruptedException -> 0x0129, RuntimeException -> 0x0126 }
            java.lang.String r6 = "Register request complete"
            com.threatmetrix.TrustDefender.internal.TL.m338new(r4, r6)     // Catch:{ O -> 0x012c, InterruptedException -> 0x0129, RuntimeException -> 0x0126 }
            if (r3 == 0) goto L_0x0125
            java.util.concurrent.Executor r4 = com.threatmetrix.TrustDefender.internal.YB.f703int
            com.threatmetrix.TrustDefender.TrustDefender$5 r6 = new com.threatmetrix.TrustDefender.TrustDefender$5
            r6.<init>(r3, r2, r5)
            r4.execute(r6)
        L_0x0125:
            return
        L_0x0126:
            r0 = move-exception
            r4 = r0
            goto L_0x0145
        L_0x0129:
            r0 = move-exception
            r4 = r0
            goto L_0x015e
        L_0x012c:
            r0 = move-exception
            r4 = r0
            goto L_0x0177
        L_0x012f:
            com.threatmetrix.TrustDefender.THMStatusCode r4 = com.threatmetrix.TrustDefender.THMStatusCode.THM_StrongAuth_Unsupported     // Catch:{ O -> 0x0174, InterruptedException -> 0x015b, RuntimeException -> 0x0142, all -> 0x013e }
            if (r3 == 0) goto L_0x013d
            java.util.concurrent.Executor r5 = com.threatmetrix.TrustDefender.internal.YB.f703int
            com.threatmetrix.TrustDefender.TrustDefender$5 r6 = new com.threatmetrix.TrustDefender.TrustDefender$5
            r6.<init>(r3, r2, r4)
            r5.execute(r6)
        L_0x013d:
            return
        L_0x013e:
            r0 = move-exception
            r4 = r0
            r5 = 0
            goto L_0x0188
        L_0x0142:
            r0 = move-exception
            r4 = r0
            r5 = 0
        L_0x0145:
            java.lang.String r6 = f16if     // Catch:{ all -> 0x0186 }
            java.lang.String r7 = "Register request failed"
            com.threatmetrix.TrustDefender.internal.TL.m328for(r6, r7, r4)     // Catch:{ all -> 0x0186 }
            com.threatmetrix.TrustDefender.THMStatusCode r4 = com.threatmetrix.TrustDefender.THMStatusCode.THM_StrongAuth_Failed     // Catch:{ all -> 0x0186 }
            if (r3 == 0) goto L_0x015a
            java.util.concurrent.Executor r5 = com.threatmetrix.TrustDefender.internal.YB.f703int
            com.threatmetrix.TrustDefender.TrustDefender$5 r6 = new com.threatmetrix.TrustDefender.TrustDefender$5
            r6.<init>(r3, r2, r4)
            r5.execute(r6)
        L_0x015a:
            return
        L_0x015b:
            r0 = move-exception
            r4 = r0
            r5 = 0
        L_0x015e:
            java.lang.String r6 = f16if     // Catch:{ all -> 0x0186 }
            java.lang.String r7 = "Register request interrupted"
            com.threatmetrix.TrustDefender.internal.TL.m328for(r6, r7, r4)     // Catch:{ all -> 0x0186 }
            com.threatmetrix.TrustDefender.THMStatusCode r4 = com.threatmetrix.TrustDefender.THMStatusCode.THM_StrongAuth_Cancelled     // Catch:{ all -> 0x0186 }
            if (r3 == 0) goto L_0x0173
            java.util.concurrent.Executor r5 = com.threatmetrix.TrustDefender.internal.YB.f703int
            com.threatmetrix.TrustDefender.TrustDefender$5 r6 = new com.threatmetrix.TrustDefender.TrustDefender$5
            r6.<init>(r3, r2, r4)
            r5.execute(r6)
        L_0x0173:
            return
        L_0x0174:
            r0 = move-exception
            r4 = r0
            r5 = 0
        L_0x0177:
            com.threatmetrix.TrustDefender.THMStatusCode r4 = r4.f670for     // Catch:{ all -> 0x0186 }
            if (r3 == 0) goto L_0x0185
            java.util.concurrent.Executor r5 = com.threatmetrix.TrustDefender.internal.YB.f703int
            com.threatmetrix.TrustDefender.TrustDefender$5 r6 = new com.threatmetrix.TrustDefender.TrustDefender$5
            r6.<init>(r3, r2, r4)
            r5.execute(r6)
        L_0x0185:
            return
        L_0x0186:
            r0 = move-exception
            r4 = r0
        L_0x0188:
            if (r3 == 0) goto L_0x0194
            java.util.concurrent.Executor r6 = com.threatmetrix.TrustDefender.internal.YB.f703int
            com.threatmetrix.TrustDefender.TrustDefender$5 r7 = new com.threatmetrix.TrustDefender.TrustDefender$5
            r7.<init>(r3, r2, r5)
            r6.execute(r7)
        L_0x0194:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.TrustDefender.m3do(com.threatmetrix.TrustDefender.TrustDefender, java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.threatmetrix.TrustDefender.internal.X7$E, boolean, com.threatmetrix.TrustDefender.StrongAuth$StrongAuthCallback, com.threatmetrix.TrustDefender.EndNotifier, boolean):void");
    }

    public /* bridge */ /* synthetic */ boolean doPackageScan() {
        return super.doPackageScan();
    }

    public /* bridge */ /* synthetic */ boolean doPackageScan(int i, TimeUnit timeUnit) {
        return super.doPackageScan(i, timeUnit);
    }

    public /* bridge */ /* synthetic */ Handle doProfileRequest(EndNotifier endNotifier) {
        return super.doProfileRequest(endNotifier);
    }

    public /* bridge */ /* synthetic */ Handle doProfileRequest(ProfilingOptions profilingOptions, EndNotifier endNotifier) {
        return super.doProfileRequest(profilingOptions, endNotifier);
    }

    public /* bridge */ /* synthetic */ Handle doProfileRequest(String str, EndNotifier endNotifier) {
        return super.doProfileRequest(str, endNotifier);
    }

    public /* bridge */ /* synthetic */ void init(Config config) throws IllegalArgumentException, IllegalStateException {
        super.init(config);
    }

    public /* bridge */ /* synthetic */ void pauseLocationServices(boolean z) {
        super.pauseLocationServices(z);
    }

    static {
        boolean z = false;
        if (D2.m38do(E.OKIO) != null) {
            z = true;
        }
        f14class = z;
    }

    public void init(Context context, String str) {
        init(new Config().setContext(context).setOrgId(str));
    }

    private TrustDefender() {
        super(new ZL(), new XZ(), new U());
    }

    /* renamed from: int reason: not valid java name */
    private static synchronized TrustDefender m4int() {
        synchronized (TrustDefender.class) {
            TrustDefender trustDefender = f17throw;
            if (trustDefender != null) {
                return trustDefender;
            }
            TrustDefender trustDefender2 = new TrustDefender();
            f17throw = trustDefender2;
            return trustDefender2;
        }
    }

    public static TrustDefender getInstance() {
        TrustDefender trustDefender = f17throw;
        if (trustDefender != null) {
            return trustDefender;
        }
        return m4int();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: if reason: not valid java name */
    public final void m9if(Config config) {
        if ((!f15const || !f14class) && config.f9try) {
            TL.m332if(f16if, "OkHttp library not available, please include the library. For information about how to include the library see http://square.github.io/okhttp/");
            throw new IllegalStateException("Failed to init: Third party (OkHttp 3+) library is not found");
        }
        this.f26while = (int) XU.m405for((long) config.m_packageScanLimit, 0, "package scan limit");
        this.f25super = (int) XU.m405for((long) config.m_packageScanTimeLimit, 0, "package scan timeout");
        this.f18catch = (int) XU.m405for((long) config.m_initPackageScanTimeLimit, 0, "init package scan timeout");
        this.f655void = config.m_initPackageScanInterruptible;
        this.f22float = (int) XU.m405for((long) config.m_initPackageScanLimit, 0, "init package scan limit");
        this.f20double = config.m_disableProfilePackageScan;
        this.f21final = config.m_disableInitPackageScan;
        this.f23int = true;
        this.f24new = (int) XU.m405for((long) config.m_screenOffTimeout, (long) Config.f6int, "screenOffTimeout");
        if (this.f19do != null) {
            this.f19do.cancel();
        }
        XU.m405for(config.m_lowPowerUpdateTime, 0, "LowPowerUpdateTime");
        XU.m405for(config.m_highPowerUpdateTime, 0, "HighPowerUpdateTime");
        XU.m405for((long) config.m_accuracy, 2, "location accuracy");
    }

    /* access modifiers changed from: protected */
    /* renamed from: do reason: not valid java name */
    public final boolean m7do(int i, boolean z, R r) {
        final int i2;
        final boolean z2;
        final long j;
        if (r == R.init) {
            if (this.f21final) {
                return false;
            }
            i = this.f18catch;
        } else if (r == R.doProfileRequest) {
            HZ.m78new(this.f640char.f487for);
            if (this.f20double) {
                return false;
            }
            i = this.f25super;
        }
        final int i3 = i;
        String str = f16if;
        StringBuilder sb = new StringBuilder("doPackageScan(");
        sb.append(r);
        sb.append("): marking scan as started");
        TL.m338new(str, sb.toString());
        if ((!z || this.f643else.m359int()) && (z || this.f643else.m357if())) {
            if (r == R.doProfileRequest || r == R.init) {
                j = this.f653this.get() & 16384;
                z2 = true;
                i2 = r == R.init ? this.f22float : this.f26while;
            } else {
                j = this.f653this.get();
                z2 = false;
                i2 = 0;
            }
            if ((j & 28672) != 0) {
                if (!this.f643else.m353do(z2)) {
                    TL.m325do(f16if, "Scan already in progress or cancel requested, aborting");
                    return false;
                }
                final R r2 = r;
                AnonymousClass1 r22 = new Runnable() {
                    public final void run() {
                        String str;
                        StringBuilder sb;
                        int i = 0;
                        try {
                            if ((j & 12288) != 0) {
                                i = 2;
                            }
                            if (!((j & 16384) == 0 && (j & 8192) == 0)) {
                                i |= 1;
                            }
                            if ((j & 524288) != 0) {
                                i |= 32;
                            }
                            if (!z2) {
                                i |= 16;
                            }
                            PH.m275do().m297int(TrustDefender.this.f640char, i, i2, i3);
                            str = TrustDefender.f16if;
                            sb = new StringBuilder("doPackageScan(");
                        } catch (InterruptedException unused) {
                            str = TrustDefender.f16if;
                            sb = new StringBuilder("doPackageScan(");
                        } catch (Throwable th) {
                            String str2 = TrustDefender.f16if;
                            StringBuilder sb2 = new StringBuilder("doPackageScan(");
                            sb2.append(r2);
                            sb2.append("): complete");
                            TL.m338new(str2, sb2.toString());
                            TrustDefender.this.f643else.m363void();
                            throw th;
                        }
                        sb.append(r2);
                        sb.append("): complete");
                        TL.m338new(str, sb.toString());
                        TrustDefender.this.f643else.m363void();
                    }
                };
                new Thread(r22).start();
            }
            return true;
        }
        String str2 = f16if;
        StringBuilder sb2 = new StringBuilder("doPackageScan(");
        sb2.append(r);
        sb2.append("): aborted! not inited");
        TL.m332if(str2, sb2.toString());
        return false;
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x002e  */
    /* JADX WARNING: Removed duplicated region for block: B:14:? A[RETURN, SYNTHETIC] */
    /* renamed from: int reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.threatmetrix.TrustDefender.internal.K m11int(com.threatmetrix.TrustDefender.internal.P.O r7) {
        /*
            r6 = this;
            com.threatmetrix.TrustDefender.internal.DI.m49if()
            java.util.concurrent.atomic.AtomicLong r0 = r6.f653this
            long r0 = r0.get()
            r2 = 38
            long r4 = r0 & r2
            r0 = 0
            int r2 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r2 == 0) goto L_0x0015
            r0 = 1
            goto L_0x0016
        L_0x0015:
            r0 = 0
        L_0x0016:
            r1 = 0
            boolean r2 = com.threatmetrix.TrustDefender.internal.N.Q.m167for()
            if (r2 == 0) goto L_0x002b
            if (r0 == 0) goto L_0x002b
            com.threatmetrix.TrustDefender.internal.R r0 = new com.threatmetrix.TrustDefender.internal.R     // Catch:{ InterruptedException -> 0x002b }
            java.util.concurrent.atomic.AtomicLong r2 = r6.f653this     // Catch:{ InterruptedException -> 0x002b }
            long r2 = r2.get()     // Catch:{ InterruptedException -> 0x002b }
            r0.<init>(r7, r2)     // Catch:{ InterruptedException -> 0x002b }
            goto L_0x002c
        L_0x002b:
            r0 = r1
        L_0x002c:
            if (r0 != 0) goto L_0x0039
            com.threatmetrix.TrustDefender.internal.K r0 = new com.threatmetrix.TrustDefender.internal.K
            r0.<init>()
            java.lang.String r7 = com.threatmetrix.TrustDefender.internal.WY.m370for(r7)
            r0.f260if = r7
        L_0x0039:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.TrustDefender.m11int(com.threatmetrix.TrustDefender.internal.P$O):com.threatmetrix.TrustDefender.internal.K");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0021, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0022, code lost:
        com.threatmetrix.TrustDefender.internal.TL.m338new(f16if, "Failed to build OkHttp3 client even without TLS factory");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
        com.threatmetrix.TrustDefender.internal.TL.m332if(f16if, "Failed to build OkHttp3 client, most probably because of TLS factory");
        r0.m317new(r2.f637byte, false);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0014 */
    /* renamed from: do reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.threatmetrix.TrustDefender.internal.HF m1do(java.util.List<java.lang.String> r3) {
        /*
            r2 = this;
            boolean r0 = f15const
            if (r0 == 0) goto L_0x0029
            boolean r0 = f14class
            if (r0 == 0) goto L_0x0029
            com.threatmetrix.TrustDefender.internal.RI r0 = new com.threatmetrix.TrustDefender.internal.RI
            r0.<init>(r3)
            int r3 = r2.f637byte     // Catch:{ IllegalStateException -> 0x0014 }
            r1 = 1
            r0.m317new(r3, r1)     // Catch:{ IllegalStateException -> 0x0014 }
            return r0
        L_0x0014:
            java.lang.String r3 = f16if     // Catch:{ RuntimeException -> 0x0022 }
            java.lang.String r1 = "Failed to build OkHttp3 client, most probably because of TLS factory"
            com.threatmetrix.TrustDefender.internal.TL.m332if(r3, r1)     // Catch:{ RuntimeException -> 0x0022 }
            int r3 = r2.f637byte     // Catch:{ RuntimeException -> 0x0022 }
            r1 = 0
            r0.m317new(r3, r1)     // Catch:{ RuntimeException -> 0x0022 }
            return r0
        L_0x0022:
            java.lang.String r3 = f16if
            java.lang.String r0 = "Failed to build OkHttp3 client even without TLS factory"
            com.threatmetrix.TrustDefender.internal.TL.m338new(r3, r0)
        L_0x0029:
            r3 = 0
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.TrustDefender.m1do(java.util.List):com.threatmetrix.TrustDefender.internal.HF");
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: if reason: not valid java name */
    public final void m10if(O o) {
        if (this.f24new > 0) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            o.f487for.registerReceiver(new NH(this), intentFilter);
        }
    }

    /* renamed from: new reason: not valid java name */
    private String m5new(String str, String str2, String str3, String str4, String str5, StrongAuthCallback strongAuthCallback, EndNotifier endNotifier, boolean z) {
        final String str6 = str;
        final EndNotifier endNotifier2 = endNotifier;
        String str7 = NK.m205char(str6);
        if (!str7.equals(str6)) {
            TL.m329for(f16if, "StrongAuth Failure: Session id {} is not valid, can't proceed.", str6);
            final THMStatusCode tHMStatusCode = THMStatusCode.THM_StrongAuth_Failed;
            if (endNotifier2 != null) {
                YB.f703int.execute(new Runnable() {
                    public final void run() {
                        r15.complete(new Result(r4, r13));
                    }
                });
            }
            return str6;
        }
        final X7.E e = X7.E.m393int(str5);
        final String str8 = str7;
        final String str9 = str2;
        final String str10 = str3;
        final String str11 = str4;
        final StrongAuthCallback strongAuthCallback2 = strongAuthCallback;
        final boolean z2 = z;
        AnonymousClass4 r0 = new Runnable() {

            /* renamed from: try reason: not valid java name */
            final /* synthetic */ boolean f43try = false;

            public final void run() {
                TrustDefender.m3do(TrustDefender.this, str8, str9, str10, str11, e, this.f43try, strongAuthCallback2, endNotifier2, z2);
            }
        };
        new Thread(r0).start();
        return str7;
    }

    public String processStrongAuthMessage(StrongAuthConfiguration strongAuthConfiguration, StrongAuthCallback strongAuthCallback, final EndNotifier endNotifier) {
        boolean z;
        final String str = strongAuthConfiguration.auth_session;
        String str2 = strongAuthConfiguration.auth_action;
        String str3 = strongAuthConfiguration.auth_method;
        String str4 = strongAuthConfiguration.auth_title;
        String str5 = strongAuthConfiguration.auth_prompt;
        String str6 = strongAuthConfiguration.auth_context;
        if (NK.m215if(str2) || NK.m215if(str3) || NK.m215if(str6) || NK.m215if(str5) || NK.m215if(str)) {
            final THMStatusCode tHMStatusCode = THMStatusCode.THM_StrongAuth_Failed;
            if (endNotifier != null) {
                YB.f703int.execute(new Runnable() {
                    public final void run() {
                        r15.complete(new Result(r4, r13));
                    }
                });
            }
            return str;
        }
        if ("register".equals(str2)) {
            z = true;
        } else if ("stepup".equals(str2)) {
            z = false;
        } else {
            TL.m338new(f16if, "auth_action is invalid, can't proceed");
            final THMStatusCode tHMStatusCode2 = THMStatusCode.THM_StrongAuth_Failed;
            if (endNotifier != null) {
                YB.f703int.execute(new Runnable(null) {
                    public final void run() {
                        r15.complete(new Result(r4, r13));
                    }
                });
            }
            return str;
        }
        return m5new(str, str4, str5, str6, str3, strongAuthCallback, endNotifier, z);
    }

    public String processStrongAuthMessage(Object obj, StrongAuthCallback strongAuthCallback, final EndNotifier endNotifier) {
        boolean z;
        if (obj instanceof Bundle) {
            Bundle bundle = (Bundle) obj;
            StrongAuthConfiguration strongAuthConfiguration = new StrongAuthConfiguration();
            strongAuthConfiguration.setAuthMethod(bundle.getString(StrongAuth.AUTH_METHOD));
            strongAuthConfiguration.setAuthAction(bundle.getString(StrongAuth.AUTH_ACTION));
            strongAuthConfiguration.setAuthContext(bundle.getString(StrongAuth.AUTH_CONTEXT));
            strongAuthConfiguration.setAuthSession(bundle.getString(StrongAuth.AUTH_SESSION));
            strongAuthConfiguration.setAuthPrompt(bundle.getString(StrongAuth.AUTH_PROMPT));
            strongAuthConfiguration.setAuthTitle(bundle.getString(StrongAuth.AUTH_TITLE));
            return processStrongAuthMessage(strongAuthConfiguration, strongAuthCallback, endNotifier);
        }
        Method method = D2.m44for(obj.getClass(), "getData", new Class[0]);
        if (method == null) {
            TL.m338new(f16if, "Message parameter does not have \"getData\" method");
            final THMStatusCode tHMStatusCode = THMStatusCode.THM_StrongAuth_Failed;
            if (endNotifier != null) {
                YB.f703int.execute(new Runnable(null) {
                    public final void run() {
                        endNotifier.complete(new Result(r4, r13));
                    }
                });
            }
            return null;
        }
        Object obj2 = D2.m39do(obj, method, new Object[0]);
        if (!(obj2 instanceof Map)) {
            TL.m338new(f16if, "\"getData\" returns wrong type");
            final THMStatusCode tHMStatusCode2 = THMStatusCode.THM_StrongAuth_Failed;
            if (endNotifier != null) {
                YB.f703int.execute(new Runnable(null) {
                    public final void run() {
                        endNotifier.complete(new Result(r4, r13));
                    }
                });
            }
            return null;
        }
        Map map = (Map) obj2;
        final String str = (String) map.get(StrongAuth.AUTH_SESSION);
        String str2 = (String) map.get(StrongAuth.AUTH_ACTION);
        String str3 = (String) map.get(StrongAuth.AUTH_CONTEXT);
        String str4 = (String) map.get(StrongAuth.AUTH_METHOD);
        String str5 = (String) map.get(StrongAuth.AUTH_PROMPT);
        String str6 = (String) map.get(StrongAuth.AUTH_TITLE);
        if (NK.m215if(str2) || NK.m215if(str4) || NK.m215if(str3) || NK.m215if(str5) || NK.m215if(str)) {
            final THMStatusCode tHMStatusCode3 = THMStatusCode.THM_StrongAuth_Failed;
            if (endNotifier != null) {
                YB.f703int.execute(new Runnable() {
                    public final void run() {
                        endNotifier.complete(new Result(str, tHMStatusCode3));
                    }
                });
            }
            return str;
        }
        if ("register".equals(str2)) {
            z = true;
        } else if ("stepup".equals(str2)) {
            z = false;
        } else {
            TL.m338new(f16if, "auth_action is missing, can't proceed");
            final THMStatusCode tHMStatusCode4 = THMStatusCode.THM_StrongAuth_Failed;
            if (endNotifier != null) {
                YB.f703int.execute(new Runnable() {
                    public final void run() {
                        endNotifier.complete(new Result(str, tHMStatusCode3));
                    }
                });
            }
            return str;
        }
        return m5new(str, str6, str5, str3, str4, strongAuthCallback, endNotifier, z);
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: for reason: not valid java name */
    public final HF m8for(Config config) {
        if (config.f9try) {
            return m1do(config.m_certificateHashes);
        }
        TN tn = new TN(config.m_certificateHashes);
        tn.m68new(this.f637byte, true);
        return tn;
    }
}
