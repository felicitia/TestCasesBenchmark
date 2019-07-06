package com.threatmetrix.TrustDefender.internal;

import com.threatmetrix.TrustDefender.Config;
import com.threatmetrix.TrustDefender.EndNotifier;
import com.threatmetrix.TrustDefender.Profile.Handle;
import com.threatmetrix.TrustDefender.Profile.Result;
import com.threatmetrix.TrustDefender.ProfilingOptions;
import com.threatmetrix.TrustDefender.THMStatusCode;
import java.io.File;
import java.io.FilenameFilter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

public abstract class XU {
    /* access modifiers changed from: private */

    /* renamed from: do reason: not valid java name */
    public static final String f635do = TL.m331if(XU.class);
    public static final String version = "5.2-34";

    /* renamed from: break reason: not valid java name */
    TB f636break = null;

    /* renamed from: byte reason: not valid java name */
    protected volatile int f637byte = 10000;

    /* renamed from: case reason: not valid java name */
    volatile HF f638case = null;

    /* renamed from: catch reason: not valid java name */
    private volatile String f639catch = null;

    /* renamed from: char reason: not valid java name */
    public com.threatmetrix.TrustDefender.internal.P.O f640char = null;

    /* renamed from: class reason: not valid java name */
    private volatile long f641class = 0;

    /* renamed from: const reason: not valid java name */
    private volatile Thread f642const = null;

    /* renamed from: else reason: not valid java name */
    public final VZ f643else = new VZ();
    /* access modifiers changed from: private */

    /* renamed from: final reason: not valid java name */
    public volatile int f644final = 0;
    /* access modifiers changed from: private */

    /* renamed from: float reason: not valid java name */
    public volatile DS f645float = null;

    /* renamed from: for reason: not valid java name */
    K7 f646for = null;

    /* renamed from: goto reason: not valid java name */
    public volatile boolean f647goto = true;
    /* access modifiers changed from: private */

    /* renamed from: if reason: not valid java name */
    public final E f648if;

    /* renamed from: int reason: not valid java name */
    private final L f649int;

    /* renamed from: long reason: not valid java name */
    final L f650long = new L();

    /* renamed from: new reason: not valid java name */
    private final K f651new;
    /* access modifiers changed from: private */

    /* renamed from: super reason: not valid java name */
    public volatile boolean f652super = false;

    /* renamed from: this reason: not valid java name */
    protected final AtomicLong f653this = new AtomicLong(0);

    /* renamed from: try reason: not valid java name */
    YB f654try = new YB();

    /* renamed from: void reason: not valid java name */
    protected volatile boolean f655void = false;

    /* renamed from: while reason: not valid java name */
    private final ReentrantLock f656while = new ReentrantLock();

    public interface E {
        /* renamed from: for reason: not valid java name */
        boolean m423for(com.threatmetrix.TrustDefender.internal.P.O o);

        /* renamed from: if reason: not valid java name */
        String m424if(int i);
    }

    final class I implements Runnable {

        /* renamed from: do reason: not valid java name */
        final EndNotifier f667do;

        /* renamed from: for reason: not valid java name */
        final Result f668for;

        I(Result result, EndNotifier endNotifier) {
            this.f668for = result;
            this.f667do = endNotifier;
        }

        public final void run() {
            if (this.f667do != null) {
                this.f667do.complete(this.f668for);
            }
        }
    }

    public interface K {
        /* renamed from: do reason: not valid java name */
        String m425do();

        /* renamed from: new reason: not valid java name */
        String m426new();

        /* renamed from: new reason: not valid java name */
        void m427new(com.threatmetrix.TrustDefender.internal.P.O o, String str);
    }

    public interface L {
        /* renamed from: do reason: not valid java name */
        void m428do(com.threatmetrix.TrustDefender.internal.K7.O o);

        /* renamed from: if reason: not valid java name */
        com.threatmetrix.TrustDefender.internal.K7.O m429if();

        /* renamed from: int reason: not valid java name */
        void m430int();

        /* renamed from: int reason: not valid java name */
        void m431int(Config config);

        /* renamed from: new reason: not valid java name */
        void m432new();
    }

    public static class O extends Exception {

        /* renamed from: for reason: not valid java name */
        public final THMStatusCode f670for;

        O(THMStatusCode tHMStatusCode) {
            this.f670for = tHMStatusCode;
        }
    }

    public enum R {
        doProfileRequest,
        doPackageScan,
        init
    }

    class T implements Handle {

        /* renamed from: if reason: not valid java name */
        private String f675if;

        T(String str) {
            this.f675if = str;
        }

        public final String getSessionID() {
            return this.f675if;
        }

        public final void cancel() {
            if (XU.this.f643else.m361new()) {
                XU.this.m417if();
            }
        }
    }

    public static class W {

        /* renamed from: do reason: not valid java name */
        final com.threatmetrix.TrustDefender.internal.K7.O f677do;

        /* renamed from: for reason: not valid java name */
        public final CG f678for;

        /* renamed from: if reason: not valid java name */
        final String f679if;

        /* renamed from: int reason: not valid java name */
        final E f680int;

        W(E e, CG cg, String str, com.threatmetrix.TrustDefender.internal.K7.O o) {
            this.f680int = e;
            this.f678for = cg;
            this.f679if = str;
            this.f677do = o;
        }

        /* renamed from: int reason: not valid java name */
        public final X m433int(XU xu, long j, Map<String, String> map) throws InterruptedException {
            return this.f680int.m137do(this.f678for, xu.f636break.f537try, this.f679if, this.f677do, xu.f650long, j, map);
        }
    }

    /* renamed from: do reason: not valid java name */
    public boolean m415do(int i, boolean z, R r) {
        return false;
    }

    /* renamed from: for reason: not valid java name */
    public abstract HF m416for(Config config);

    /* renamed from: if reason: not valid java name */
    public abstract void m418if(Config config) throws IllegalArgumentException, IllegalStateException;

    /* renamed from: if reason: not valid java name */
    public abstract void m419if(com.threatmetrix.TrustDefender.internal.P.O o);

    /* renamed from: int reason: not valid java name */
    public abstract K m421int(com.threatmetrix.TrustDefender.internal.P.O o);

    /* JADX WARNING: Removed duplicated region for block: B:178:0x032b  */
    /* JADX WARNING: Removed duplicated region for block: B:192:0x036d  */
    /* JADX WARNING: Removed duplicated region for block: B:206:0x0399 A[Catch:{ all -> 0x0413 }] */
    /* JADX WARNING: Removed duplicated region for block: B:207:0x03a1 A[Catch:{ all -> 0x0413 }] */
    /* JADX WARNING: Removed duplicated region for block: B:211:0x03b5  */
    /* JADX WARNING: Removed duplicated region for block: B:225:0x03f7  */
    /* JADX WARNING: Removed duplicated region for block: B:236:0x0421  */
    /* JADX WARNING: Removed duplicated region for block: B:249:0x0463  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0149 A[Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0179 A[Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }] */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x017b A[Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0183 A[Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }] */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0185 A[Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }] */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x01c5 A[Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }] */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x01e4 A[Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }] */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x0204  */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:203:0x0391=Splitter:B:203:0x0391, B:172:0x0317=Splitter:B:172:0x0317} */
    /* renamed from: for reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ void m407for(com.threatmetrix.TrustDefender.internal.XU r21, java.lang.String r22, com.threatmetrix.TrustDefender.ProfilingOptions r23, com.threatmetrix.TrustDefender.EndNotifier r24, long r25, com.threatmetrix.TrustDefender.internal.XU.T r27) {
        /*
            r1 = r21
            r2 = r22
            r3 = r23
            r4 = r24
            r5 = r27
            com.threatmetrix.TrustDefender.THMStatusCode r6 = com.threatmetrix.TrustDefender.THMStatusCode.THM_NotYet
            r7 = 2
            r9 = 10
            r11 = 0
            long r12 = java.lang.System.currentTimeMillis()     // Catch:{ InterruptedException -> 0x038e, Exception -> 0x0314, all -> 0x030e }
            int r14 = r1.f637byte     // Catch:{ InterruptedException -> 0x038e, Exception -> 0x0314, all -> 0x030e }
            long r14 = (long) r14     // Catch:{ InterruptedException -> 0x038e, Exception -> 0x0314, all -> 0x030e }
            long r16 = r12 + r14
            java.lang.String r12 = f635do     // Catch:{ InterruptedException -> 0x038e, Exception -> 0x0314, all -> 0x030e }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ InterruptedException -> 0x038e, Exception -> 0x0314, all -> 0x030e }
            java.lang.String r14 = "continuing profile request "
            r13.<init>(r14)     // Catch:{ InterruptedException -> 0x038e, Exception -> 0x0314, all -> 0x030e }
            com.threatmetrix.TrustDefender.internal.VZ r14 = r1.f643else     // Catch:{ InterruptedException -> 0x038e, Exception -> 0x0314, all -> 0x030e }
            boolean r14 = r14.m357if()     // Catch:{ InterruptedException -> 0x038e, Exception -> 0x0314, all -> 0x030e }
            if (r14 == 0) goto L_0x0034
            java.lang.String r14 = "inited already"
            goto L_0x0036
        L_0x002e:
            r0 = move-exception
            goto L_0x0316
        L_0x0031:
            r0 = move-exception
            goto L_0x0390
        L_0x0034:
            java.lang.String r14 = " needs init"
        L_0x0036:
            r13.append(r14)     // Catch:{ InterruptedException -> 0x038e, Exception -> 0x0314, all -> 0x030e }
            java.lang.String r13 = r13.toString()     // Catch:{ InterruptedException -> 0x038e, Exception -> 0x0314, all -> 0x030e }
            com.threatmetrix.TrustDefender.internal.TL.m338new(r12, r13)     // Catch:{ InterruptedException -> 0x038e, Exception -> 0x0314, all -> 0x030e }
            com.threatmetrix.TrustDefender.internal.CC r12 = new com.threatmetrix.TrustDefender.internal.CC     // Catch:{ InterruptedException -> 0x038e, Exception -> 0x0314, all -> 0x030e }
            r12.<init>()     // Catch:{ InterruptedException -> 0x038e, Exception -> 0x0314, all -> 0x030e }
            com.threatmetrix.TrustDefender.internal.VZ r13 = r1.f643else     // Catch:{ InterruptedException -> 0x038e, Exception -> 0x0314, all -> 0x030e }
            boolean r13 = r13.m355for()     // Catch:{ InterruptedException -> 0x038e, Exception -> 0x0314, all -> 0x030e }
            if (r13 != 0) goto L_0x02fe
            java.lang.Thread r13 = java.lang.Thread.currentThread()     // Catch:{ InterruptedException -> 0x038e, Exception -> 0x0314, all -> 0x030e }
            boolean r13 = r13.isInterrupted()     // Catch:{ InterruptedException -> 0x038e, Exception -> 0x0314, all -> 0x030e }
            if (r13 == 0) goto L_0x0059
            goto L_0x02fe
        L_0x0059:
            com.threatmetrix.TrustDefender.internal.VZ r13 = r1.f643else     // Catch:{ InterruptedException -> 0x038e, Exception -> 0x0314, all -> 0x030e }
            boolean r13 = r13.m357if()     // Catch:{ InterruptedException -> 0x038e, Exception -> 0x0314, all -> 0x030e }
            if (r13 != 0) goto L_0x0070
            java.lang.String r3 = f635do     // Catch:{ InterruptedException -> 0x0031, Exception -> 0x002e }
            java.lang.String r12 = "Not inited"
            com.threatmetrix.TrustDefender.internal.TL.m338new(r3, r12)     // Catch:{ InterruptedException -> 0x0031, Exception -> 0x002e }
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException     // Catch:{ InterruptedException -> 0x0031, Exception -> 0x002e }
            java.lang.String r12 = "Not inited"
            r3.<init>(r12)     // Catch:{ InterruptedException -> 0x0031, Exception -> 0x002e }
            throw r3     // Catch:{ InterruptedException -> 0x0031, Exception -> 0x002e }
        L_0x0070:
            com.threatmetrix.TrustDefender.internal.VZ r13 = r1.f643else     // Catch:{ InterruptedException -> 0x038e, Exception -> 0x0314, all -> 0x030e }
            int r14 = r1.f637byte     // Catch:{ InterruptedException -> 0x038e, Exception -> 0x0314, all -> 0x030e }
            boolean r13 = r13.m360int(r14)     // Catch:{ InterruptedException -> 0x038e, Exception -> 0x0314, all -> 0x030e }
            if (r13 != 0) goto L_0x00f9
            com.threatmetrix.TrustDefender.internal.VZ r3 = r1.f643else     // Catch:{ InterruptedException -> 0x0031, Exception -> 0x002e }
            boolean r3 = r3.m355for()     // Catch:{ InterruptedException -> 0x0031, Exception -> 0x002e }
            if (r3 != 0) goto L_0x008c
            java.lang.String r3 = f635do     // Catch:{ InterruptedException -> 0x0031, Exception -> 0x002e }
            java.lang.String r12 = "Timed out waiting for init thread, aborting"
            com.threatmetrix.TrustDefender.internal.TL.m332if(r3, r12)     // Catch:{ InterruptedException -> 0x0031, Exception -> 0x002e }
            com.threatmetrix.TrustDefender.THMStatusCode r3 = com.threatmetrix.TrustDefender.THMStatusCode.THM_Internal_Error     // Catch:{ InterruptedException -> 0x0031, Exception -> 0x002e }
            goto L_0x0094
        L_0x008c:
            java.lang.String r3 = f635do     // Catch:{ InterruptedException -> 0x0031, Exception -> 0x002e }
            java.lang.String r12 = "Thread interrupted, returning"
            com.threatmetrix.TrustDefender.internal.TL.m332if(r3, r12)     // Catch:{ InterruptedException -> 0x0031, Exception -> 0x002e }
            r3 = r6
        L_0x0094:
            com.threatmetrix.TrustDefender.internal.CC.m24if()
            com.threatmetrix.TrustDefender.internal.VZ r6 = r1.f643else
            boolean r6 = r6.m355for()
            if (r6 == 0) goto L_0x00a4
            com.threatmetrix.TrustDefender.THMStatusCode r3 = com.threatmetrix.TrustDefender.THMStatusCode.THM_Interrupted_Error
            java.lang.Thread.interrupted()
        L_0x00a4:
            com.threatmetrix.TrustDefender.Profile$Result r6 = new com.threatmetrix.TrustDefender.Profile$Result
            com.threatmetrix.TrustDefender.Profile r12 = new com.threatmetrix.TrustDefender.Profile
            r12.<init>()
            r6.<init>(r2, r3)
            r1.m410int(r6, r4, r5)
            boolean r4 = com.threatmetrix.TrustDefender.internal.P.m233break()
            if (r4 == 0) goto L_0x00f8
            com.threatmetrix.TrustDefender.internal.P$O r4 = r1.f640char
            boolean r4 = com.threatmetrix.TrustDefender.internal.P.m247else(r4)
            if (r4 == 0) goto L_0x00f8
            com.threatmetrix.TrustDefender.internal.VZ r4 = r1.f643else
            boolean r4 = r4.m361new()
            if (r4 != 0) goto L_0x00f8
            com.threatmetrix.TrustDefender.THMStatusCode r4 = com.threatmetrix.TrustDefender.THMStatusCode.THM_OK
            if (r3 != r4) goto L_0x00f8
            long r3 = java.lang.System.currentTimeMillis()
            long r5 = r1.f641class
        L_0x00d1:
            long r12 = r3 - r5
        L_0x00d3:
            java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.SECONDS
            java.util.concurrent.TimeUnit r4 = java.util.concurrent.TimeUnit.MILLISECONDS
            long r3 = r3.convert(r12, r4)
            int r5 = (r3 > r9 ? 1 : (r3 == r9 ? 0 : -1))
            if (r5 >= 0) goto L_0x00f8
            boolean r3 = r1.m413int(r2, r11)
            if (r3 != 0) goto L_0x00f8
            java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ InterruptedException -> 0x00d3 }
            java.util.concurrent.TimeUnit r4 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ InterruptedException -> 0x00d3 }
            long r3 = r3.convert(r7, r4)     // Catch:{ InterruptedException -> 0x00d3 }
            java.lang.Thread.sleep(r3)     // Catch:{ InterruptedException -> 0x00d3 }
            long r3 = java.lang.System.currentTimeMillis()     // Catch:{ InterruptedException -> 0x00d3 }
            long r5 = r1.f641class     // Catch:{ InterruptedException -> 0x00d3 }
            r12 = 0
            goto L_0x00d1
        L_0x00f8:
            return
        L_0x00f9:
            com.threatmetrix.TrustDefender.internal.XU$R r13 = com.threatmetrix.TrustDefender.internal.XU.R.doProfileRequest     // Catch:{ InterruptedException -> 0x038e, Exception -> 0x0314, all -> 0x030e }
            r14 = 0
            r15 = 1
            boolean r13 = r1.m415do(r14, r15, r13)     // Catch:{ InterruptedException -> 0x038e, Exception -> 0x0314, all -> 0x030e }
            com.threatmetrix.TrustDefender.internal.VZ r7 = r1.f643else     // Catch:{ InterruptedException -> 0x038e, Exception -> 0x0314, all -> 0x030e }
            boolean r7 = r7.m355for()     // Catch:{ InterruptedException -> 0x038e, Exception -> 0x0314, all -> 0x030e }
            if (r7 != 0) goto L_0x02f7
            java.lang.Thread r7 = java.lang.Thread.currentThread()     // Catch:{ InterruptedException -> 0x038e, Exception -> 0x0314, all -> 0x030e }
            boolean r7 = r7.isInterrupted()     // Catch:{ InterruptedException -> 0x038e, Exception -> 0x0314, all -> 0x030e }
            if (r7 == 0) goto L_0x0115
            goto L_0x02f7
        L_0x0115:
            com.threatmetrix.TrustDefender.internal.YB r7 = r1.f654try     // Catch:{ O -> 0x027e, InterruptedException -> 0x0279, Exception -> 0x0274, all -> 0x026f }
            com.threatmetrix.TrustDefender.internal.XU$W r7 = r1.m422new(r2, r7)     // Catch:{ O -> 0x027e, InterruptedException -> 0x0279, Exception -> 0x0274, all -> 0x026f }
            com.threatmetrix.TrustDefender.internal.CG r8 = r7.f678for     // Catch:{ O -> 0x027e, InterruptedException -> 0x0279, Exception -> 0x0274, all -> 0x026f }
            java.lang.String r8 = r8.f78this     // Catch:{ O -> 0x027e, InterruptedException -> 0x0279, Exception -> 0x0274, all -> 0x026f }
            boolean r9 = r1.f655void     // Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }
            if (r9 != 0) goto L_0x012e
            if (r13 == 0) goto L_0x0126
            goto L_0x012e
        L_0x0126:
            com.threatmetrix.TrustDefender.internal.VZ r9 = r1.f643else     // Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }
            r9.m356for(r15, r11)     // Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }
            r20 = r12
            goto L_0x0140
        L_0x012e:
            com.threatmetrix.TrustDefender.internal.VZ r9 = r1.f643else     // Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }
            long r18 = java.lang.System.currentTimeMillis()     // Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }
            r10 = 0
            r20 = r12
            long r11 = r16 - r18
            java.lang.Long r10 = java.lang.Long.valueOf(r11)     // Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }
            r9.m356for(r15, r10)     // Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }
        L_0x0140:
            com.threatmetrix.TrustDefender.internal.X r9 = new com.threatmetrix.TrustDefender.internal.X     // Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }
            r9.<init>()     // Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }
            java.util.List<java.lang.String> r10 = r3.m_customAttributes     // Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }
            if (r10 == 0) goto L_0x0175
            java.util.List<java.lang.String> r3 = r3.m_customAttributes     // Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }
            r10 = 0
        L_0x0150:
            boolean r11 = r3.hasNext()     // Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }
            if (r11 == 0) goto L_0x0175
            java.lang.Object r11 = r3.next()     // Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }
            java.lang.String r13 = "aca"
            r12.<init>(r13)     // Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }
            int r13 = r10 + 1
            r12.append(r10)     // Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }
            java.lang.String r10 = r12.toString()     // Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }
            r9.m374for(r10, r11, r15)     // Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }
            r10 = 5
            if (r13 < r10) goto L_0x0173
            goto L_0x0175
        L_0x0173:
            r10 = r13
            goto L_0x0150
        L_0x0175:
            com.threatmetrix.TrustDefender.internal.XU$K r3 = r1.f651new     // Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }
            if (r3 != 0) goto L_0x017b
            r11 = 0
            goto L_0x0181
        L_0x017b:
            com.threatmetrix.TrustDefender.internal.XU$K r10 = r1.f651new     // Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }
            java.lang.String r11 = r10.m425do()     // Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }
        L_0x0181:
            if (r3 != 0) goto L_0x0185
            r3 = 0
            goto L_0x018b
        L_0x0185:
            com.threatmetrix.TrustDefender.internal.XU$K r3 = r1.f651new     // Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }
            java.lang.String r3 = r3.m426new()     // Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }
        L_0x018b:
            boolean r10 = com.threatmetrix.TrustDefender.internal.NK.m203byte(r11)     // Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }
            if (r10 == 0) goto L_0x01ac
            boolean r10 = com.threatmetrix.TrustDefender.internal.NK.m203byte(r3)     // Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }
            if (r10 == 0) goto L_0x01ac
            java.lang.String r10 = "snet"
            r12 = 5000(0x1388, float:7.006E-42)
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)     // Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }
            java.util.HashMap<java.lang.String, java.lang.Integer> r13 = r9.f606do     // Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }
            r13.put(r10, r12)     // Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }
            r9.m374for(r10, r11, r14)     // Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }
            java.lang.String r10 = "snetn"
            r9.m374for(r10, r3, r14)     // Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }
        L_0x01ac:
            r10 = r25
            com.threatmetrix.TrustDefender.internal.X r3 = r7.m433int(r1, r10, r9)     // Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }
            java.lang.String r9 = "build network threads"
            java.lang.String r10 = "bnt"
            r11 = r20
            r11.m26do(r9, r10)     // Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }
            com.threatmetrix.TrustDefender.internal.YB r9 = r1.f654try     // Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }
            com.threatmetrix.TrustDefender.THMStatusCode r3 = r1.m420int(r7, r3, r9)     // Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }
            com.threatmetrix.TrustDefender.THMStatusCode r7 = com.threatmetrix.TrustDefender.THMStatusCode.THM_OK     // Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }
            if (r3 == r7) goto L_0x01e4
            java.lang.String r3 = f635do     // Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }
            java.lang.String r9 = "Received "
            r7.<init>(r9)     // Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }
            java.lang.String r9 = r6.getDesc()     // Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }
            r7.append(r9)     // Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }
            java.lang.String r9 = " error, profiling will be incomplete"
            r7.append(r9)     // Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }
            java.lang.String r7 = r7.toString()     // Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }
            com.threatmetrix.TrustDefender.internal.TL.m325do(r3, r7)     // Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }
            com.threatmetrix.TrustDefender.THMStatusCode r3 = com.threatmetrix.TrustDefender.THMStatusCode.THM_PartialProfile     // Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }
            goto L_0x01ec
        L_0x01e4:
            long r9 = java.lang.System.currentTimeMillis()     // Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }
            r1.f641class = r9     // Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }
            com.threatmetrix.TrustDefender.THMStatusCode r3 = com.threatmetrix.TrustDefender.THMStatusCode.THM_OK     // Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }
        L_0x01ec:
            r6 = r3
            com.threatmetrix.TrustDefender.internal.YB r3 = r1.f654try     // Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }
            r3.m449for()     // Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }
            java.lang.String r3 = f635do     // Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }
            java.lang.String r7 = "profile request complete"
            com.threatmetrix.TrustDefender.internal.TL.m338new(r3, r7)     // Catch:{ InterruptedException -> 0x026a, Exception -> 0x0265, all -> 0x0262 }
            com.threatmetrix.TrustDefender.internal.CC.m24if()
            com.threatmetrix.TrustDefender.internal.VZ r3 = r1.f643else
            boolean r3 = r3.m355for()
            if (r3 == 0) goto L_0x0209
            com.threatmetrix.TrustDefender.THMStatusCode r6 = com.threatmetrix.TrustDefender.THMStatusCode.THM_Interrupted_Error
            java.lang.Thread.interrupted()
        L_0x0209:
            com.threatmetrix.TrustDefender.Profile$Result r3 = new com.threatmetrix.TrustDefender.Profile$Result
            com.threatmetrix.TrustDefender.Profile r7 = new com.threatmetrix.TrustDefender.Profile
            r7.<init>()
            r3.<init>(r2, r6)
            r1.m410int(r3, r4, r5)
            boolean r3 = com.threatmetrix.TrustDefender.internal.P.m233break()
            if (r3 == 0) goto L_0x0261
            com.threatmetrix.TrustDefender.internal.P$O r3 = r1.f640char
            boolean r3 = com.threatmetrix.TrustDefender.internal.P.m247else(r3)
            if (r3 == 0) goto L_0x0261
            com.threatmetrix.TrustDefender.internal.VZ r3 = r1.f643else
            boolean r3 = r3.m361new()
            if (r3 != 0) goto L_0x0261
            com.threatmetrix.TrustDefender.THMStatusCode r3 = com.threatmetrix.TrustDefender.THMStatusCode.THM_OK
            if (r6 != r3) goto L_0x0261
            long r3 = java.lang.System.currentTimeMillis()
            long r5 = r1.f641class
        L_0x0236:
            long r9 = r3 - r5
        L_0x0238:
            java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.SECONDS
            java.util.concurrent.TimeUnit r4 = java.util.concurrent.TimeUnit.MILLISECONDS
            long r3 = r3.convert(r9, r4)
            r5 = 10
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 >= 0) goto L_0x0261
            boolean r3 = r1.m413int(r2, r8)
            if (r3 != 0) goto L_0x0261
            java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ InterruptedException -> 0x0238 }
            java.util.concurrent.TimeUnit r4 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ InterruptedException -> 0x0238 }
            r5 = 2
            long r3 = r3.convert(r5, r4)     // Catch:{ InterruptedException -> 0x0238 }
            java.lang.Thread.sleep(r3)     // Catch:{ InterruptedException -> 0x0238 }
            long r3 = java.lang.System.currentTimeMillis()     // Catch:{ InterruptedException -> 0x0238 }
            long r5 = r1.f641class     // Catch:{ InterruptedException -> 0x0238 }
            r7 = 0
            goto L_0x0236
        L_0x0261:
            return
        L_0x0262:
            r0 = move-exception
            goto L_0x0311
        L_0x0265:
            r0 = move-exception
            r3 = r0
            r11 = r8
            goto L_0x0317
        L_0x026a:
            r0 = move-exception
            r3 = r0
            r11 = r8
            goto L_0x0391
        L_0x026f:
            r0 = move-exception
            r3 = r0
            r8 = 0
            goto L_0x0416
        L_0x0274:
            r0 = move-exception
            r3 = r0
            r11 = 0
            goto L_0x0317
        L_0x0279:
            r0 = move-exception
            r3 = r0
            r11 = 0
            goto L_0x0391
        L_0x027e:
            r0 = move-exception
            r3 = r0
            com.threatmetrix.TrustDefender.THMStatusCode r3 = r3.f670for     // Catch:{ InterruptedException -> 0x02f4, Exception -> 0x02f1, all -> 0x02ee }
            com.threatmetrix.TrustDefender.internal.CC.m24if()
            com.threatmetrix.TrustDefender.internal.VZ r6 = r1.f643else
            boolean r6 = r6.m355for()
            if (r6 == 0) goto L_0x0292
            com.threatmetrix.TrustDefender.THMStatusCode r3 = com.threatmetrix.TrustDefender.THMStatusCode.THM_Interrupted_Error
            java.lang.Thread.interrupted()
        L_0x0292:
            com.threatmetrix.TrustDefender.Profile$Result r6 = new com.threatmetrix.TrustDefender.Profile$Result
            com.threatmetrix.TrustDefender.Profile r7 = new com.threatmetrix.TrustDefender.Profile
            r7.<init>()
            r6.<init>(r2, r3)
            r1.m410int(r6, r4, r5)
            boolean r4 = com.threatmetrix.TrustDefender.internal.P.m233break()
            if (r4 == 0) goto L_0x02ed
            com.threatmetrix.TrustDefender.internal.P$O r4 = r1.f640char
            boolean r4 = com.threatmetrix.TrustDefender.internal.P.m247else(r4)
            if (r4 == 0) goto L_0x02ed
            com.threatmetrix.TrustDefender.internal.VZ r4 = r1.f643else
            boolean r4 = r4.m361new()
            if (r4 != 0) goto L_0x02ed
            com.threatmetrix.TrustDefender.THMStatusCode r4 = com.threatmetrix.TrustDefender.THMStatusCode.THM_OK
            if (r3 != r4) goto L_0x02ed
            long r3 = java.lang.System.currentTimeMillis()
            long r5 = r1.f641class
            long r7 = r3 - r5
        L_0x02c1:
            java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.SECONDS
            java.util.concurrent.TimeUnit r4 = java.util.concurrent.TimeUnit.MILLISECONDS
            long r3 = r3.convert(r7, r4)
            r5 = 10
            int r9 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r9 >= 0) goto L_0x02ed
            r3 = 0
            boolean r4 = r1.m413int(r2, r3)
            if (r4 != 0) goto L_0x02ed
            java.util.concurrent.TimeUnit r4 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ InterruptedException -> 0x02c1 }
            java.util.concurrent.TimeUnit r5 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ InterruptedException -> 0x02c1 }
            r9 = 2
            long r4 = r4.convert(r9, r5)     // Catch:{ InterruptedException -> 0x02c1 }
            java.lang.Thread.sleep(r4)     // Catch:{ InterruptedException -> 0x02c1 }
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ InterruptedException -> 0x02c1 }
            long r9 = r1.f641class     // Catch:{ InterruptedException -> 0x02c1 }
            r6 = 0
            long r7 = r4 - r9
            goto L_0x02c1
        L_0x02ed:
            return
        L_0x02ee:
            r0 = move-exception
            r3 = 0
            goto L_0x0310
        L_0x02f1:
            r0 = move-exception
            r3 = 0
            goto L_0x0308
        L_0x02f4:
            r0 = move-exception
            r3 = 0
            goto L_0x030b
        L_0x02f7:
            r3 = r11
            java.lang.InterruptedException r7 = new java.lang.InterruptedException     // Catch:{ InterruptedException -> 0x030a, Exception -> 0x0307, all -> 0x0305 }
            r7.<init>()     // Catch:{ InterruptedException -> 0x030a, Exception -> 0x0307, all -> 0x0305 }
            throw r7     // Catch:{ InterruptedException -> 0x030a, Exception -> 0x0307, all -> 0x0305 }
        L_0x02fe:
            r3 = r11
            java.lang.InterruptedException r7 = new java.lang.InterruptedException     // Catch:{ InterruptedException -> 0x030a, Exception -> 0x0307, all -> 0x0305 }
            r7.<init>()     // Catch:{ InterruptedException -> 0x030a, Exception -> 0x0307, all -> 0x0305 }
            throw r7     // Catch:{ InterruptedException -> 0x030a, Exception -> 0x0307, all -> 0x0305 }
        L_0x0305:
            r0 = move-exception
            goto L_0x0310
        L_0x0307:
            r0 = move-exception
        L_0x0308:
            r11 = r3
            goto L_0x0316
        L_0x030a:
            r0 = move-exception
        L_0x030b:
            r11 = r3
            goto L_0x0390
        L_0x030e:
            r0 = move-exception
            r3 = r11
        L_0x0310:
            r8 = r3
        L_0x0311:
            r3 = r0
            goto L_0x0416
        L_0x0314:
            r0 = move-exception
            r3 = r11
        L_0x0316:
            r3 = r0
        L_0x0317:
            com.threatmetrix.TrustDefender.THMStatusCode r7 = com.threatmetrix.TrustDefender.THMStatusCode.THM_Internal_Error     // Catch:{ all -> 0x0413 }
            java.lang.String r6 = f635do     // Catch:{ all -> 0x0389 }
            java.lang.String r8 = "profile request failed"
            com.threatmetrix.TrustDefender.internal.TL.m328for(r6, r8, r3)     // Catch:{ all -> 0x0389 }
            com.threatmetrix.TrustDefender.internal.CC.m24if()
            com.threatmetrix.TrustDefender.internal.VZ r3 = r1.f643else
            boolean r3 = r3.m355for()
            if (r3 == 0) goto L_0x0330
            com.threatmetrix.TrustDefender.THMStatusCode r7 = com.threatmetrix.TrustDefender.THMStatusCode.THM_Interrupted_Error
            java.lang.Thread.interrupted()
        L_0x0330:
            com.threatmetrix.TrustDefender.Profile$Result r3 = new com.threatmetrix.TrustDefender.Profile$Result
            com.threatmetrix.TrustDefender.Profile r6 = new com.threatmetrix.TrustDefender.Profile
            r6.<init>()
            r3.<init>(r2, r7)
            r1.m410int(r3, r4, r5)
            boolean r3 = com.threatmetrix.TrustDefender.internal.P.m233break()
            if (r3 == 0) goto L_0x0388
            com.threatmetrix.TrustDefender.internal.P$O r3 = r1.f640char
            boolean r3 = com.threatmetrix.TrustDefender.internal.P.m247else(r3)
            if (r3 == 0) goto L_0x0388
            com.threatmetrix.TrustDefender.internal.VZ r3 = r1.f643else
            boolean r3 = r3.m361new()
            if (r3 != 0) goto L_0x0388
            com.threatmetrix.TrustDefender.THMStatusCode r3 = com.threatmetrix.TrustDefender.THMStatusCode.THM_OK
            if (r7 != r3) goto L_0x0388
            long r3 = java.lang.System.currentTimeMillis()
            long r5 = r1.f641class
        L_0x035d:
            long r7 = r3 - r5
        L_0x035f:
            java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.SECONDS
            java.util.concurrent.TimeUnit r4 = java.util.concurrent.TimeUnit.MILLISECONDS
            long r3 = r3.convert(r7, r4)
            r5 = 10
            int r9 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r9 >= 0) goto L_0x0388
            boolean r3 = r1.m413int(r2, r11)
            if (r3 != 0) goto L_0x0388
            java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ InterruptedException -> 0x035f }
            java.util.concurrent.TimeUnit r4 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ InterruptedException -> 0x035f }
            r5 = 2
            long r3 = r3.convert(r5, r4)     // Catch:{ InterruptedException -> 0x035f }
            java.lang.Thread.sleep(r3)     // Catch:{ InterruptedException -> 0x035f }
            long r3 = java.lang.System.currentTimeMillis()     // Catch:{ InterruptedException -> 0x035f }
            long r5 = r1.f641class     // Catch:{ InterruptedException -> 0x035f }
            r7 = 0
            goto L_0x035d
        L_0x0388:
            return
        L_0x0389:
            r0 = move-exception
            r3 = r0
            r6 = r7
            goto L_0x0415
        L_0x038e:
            r0 = move-exception
            r3 = r11
        L_0x0390:
            r3 = r0
        L_0x0391:
            com.threatmetrix.TrustDefender.internal.VZ r7 = r1.f643else     // Catch:{ all -> 0x0413 }
            boolean r7 = r7.m355for()     // Catch:{ all -> 0x0413 }
            if (r7 != 0) goto L_0x03a1
            java.lang.String r7 = f635do     // Catch:{ all -> 0x0413 }
            java.lang.String r8 = "profile request interrupted"
            com.threatmetrix.TrustDefender.internal.TL.m328for(r7, r8, r3)     // Catch:{ all -> 0x0413 }
            goto L_0x03a8
        L_0x03a1:
            java.lang.String r3 = f635do     // Catch:{ all -> 0x0413 }
            java.lang.String r7 = "profile request interrupted due to cancel"
            com.threatmetrix.TrustDefender.internal.TL.m338new(r3, r7)     // Catch:{ all -> 0x0413 }
        L_0x03a8:
            com.threatmetrix.TrustDefender.THMStatusCode r3 = com.threatmetrix.TrustDefender.THMStatusCode.THM_Interrupted_Error     // Catch:{ all -> 0x0413 }
            com.threatmetrix.TrustDefender.internal.CC.m24if()
            com.threatmetrix.TrustDefender.internal.VZ r6 = r1.f643else
            boolean r6 = r6.m355for()
            if (r6 == 0) goto L_0x03ba
            com.threatmetrix.TrustDefender.THMStatusCode r3 = com.threatmetrix.TrustDefender.THMStatusCode.THM_Interrupted_Error
            java.lang.Thread.interrupted()
        L_0x03ba:
            com.threatmetrix.TrustDefender.Profile$Result r6 = new com.threatmetrix.TrustDefender.Profile$Result
            com.threatmetrix.TrustDefender.Profile r7 = new com.threatmetrix.TrustDefender.Profile
            r7.<init>()
            r6.<init>(r2, r3)
            r1.m410int(r6, r4, r5)
            boolean r4 = com.threatmetrix.TrustDefender.internal.P.m233break()
            if (r4 == 0) goto L_0x0412
            com.threatmetrix.TrustDefender.internal.P$O r4 = r1.f640char
            boolean r4 = com.threatmetrix.TrustDefender.internal.P.m247else(r4)
            if (r4 == 0) goto L_0x0412
            com.threatmetrix.TrustDefender.internal.VZ r4 = r1.f643else
            boolean r4 = r4.m361new()
            if (r4 != 0) goto L_0x0412
            com.threatmetrix.TrustDefender.THMStatusCode r4 = com.threatmetrix.TrustDefender.THMStatusCode.THM_OK
            if (r3 != r4) goto L_0x0412
            long r3 = java.lang.System.currentTimeMillis()
            long r5 = r1.f641class
        L_0x03e7:
            long r7 = r3 - r5
        L_0x03e9:
            java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.SECONDS
            java.util.concurrent.TimeUnit r4 = java.util.concurrent.TimeUnit.MILLISECONDS
            long r3 = r3.convert(r7, r4)
            r5 = 10
            int r9 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r9 >= 0) goto L_0x0412
            boolean r3 = r1.m413int(r2, r11)
            if (r3 != 0) goto L_0x0412
            java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ InterruptedException -> 0x03e9 }
            java.util.concurrent.TimeUnit r4 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ InterruptedException -> 0x03e9 }
            r5 = 2
            long r3 = r3.convert(r5, r4)     // Catch:{ InterruptedException -> 0x03e9 }
            java.lang.Thread.sleep(r3)     // Catch:{ InterruptedException -> 0x03e9 }
            long r3 = java.lang.System.currentTimeMillis()     // Catch:{ InterruptedException -> 0x03e9 }
            long r5 = r1.f641class     // Catch:{ InterruptedException -> 0x03e9 }
            r7 = 0
            goto L_0x03e7
        L_0x0412:
            return
        L_0x0413:
            r0 = move-exception
            r3 = r0
        L_0x0415:
            r8 = r11
        L_0x0416:
            com.threatmetrix.TrustDefender.internal.CC.m24if()
            com.threatmetrix.TrustDefender.internal.VZ r7 = r1.f643else
            boolean r7 = r7.m355for()
            if (r7 == 0) goto L_0x0426
            com.threatmetrix.TrustDefender.THMStatusCode r6 = com.threatmetrix.TrustDefender.THMStatusCode.THM_Interrupted_Error
            java.lang.Thread.interrupted()
        L_0x0426:
            com.threatmetrix.TrustDefender.Profile$Result r7 = new com.threatmetrix.TrustDefender.Profile$Result
            com.threatmetrix.TrustDefender.Profile r9 = new com.threatmetrix.TrustDefender.Profile
            r9.<init>()
            r7.<init>(r2, r6)
            r1.m410int(r7, r4, r5)
            boolean r4 = com.threatmetrix.TrustDefender.internal.P.m233break()
            if (r4 == 0) goto L_0x0483
            com.threatmetrix.TrustDefender.internal.P$O r4 = r1.f640char
            boolean r4 = com.threatmetrix.TrustDefender.internal.P.m247else(r4)
            if (r4 == 0) goto L_0x0483
            com.threatmetrix.TrustDefender.internal.VZ r4 = r1.f643else
            boolean r4 = r4.m361new()
            if (r4 != 0) goto L_0x0483
            com.threatmetrix.TrustDefender.THMStatusCode r4 = com.threatmetrix.TrustDefender.THMStatusCode.THM_OK
            if (r6 != r4) goto L_0x0483
            long r4 = java.lang.System.currentTimeMillis()
            long r6 = r1.f641class
            long r9 = r4 - r6
        L_0x0455:
            java.util.concurrent.TimeUnit r4 = java.util.concurrent.TimeUnit.SECONDS
            java.util.concurrent.TimeUnit r5 = java.util.concurrent.TimeUnit.MILLISECONDS
            long r4 = r4.convert(r9, r5)
            r6 = 10
            int r11 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r11 >= 0) goto L_0x0483
            boolean r4 = r1.m413int(r2, r8)
            if (r4 != 0) goto L_0x0483
            java.util.concurrent.TimeUnit r4 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ InterruptedException -> 0x0480 }
            java.util.concurrent.TimeUnit r5 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ InterruptedException -> 0x0480 }
            r11 = 2
            long r4 = r4.convert(r11, r5)     // Catch:{ InterruptedException -> 0x0455 }
            java.lang.Thread.sleep(r4)     // Catch:{ InterruptedException -> 0x0455 }
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ InterruptedException -> 0x0455 }
            long r13 = r1.f641class     // Catch:{ InterruptedException -> 0x0455 }
            r9 = 0
            long r9 = r4 - r13
            goto L_0x0455
        L_0x0480:
            r11 = 2
            goto L_0x0455
        L_0x0483:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.internal.XU.m407for(com.threatmetrix.TrustDefender.internal.XU, java.lang.String, com.threatmetrix.TrustDefender.ProfilingOptions, com.threatmetrix.TrustDefender.EndNotifier, long, com.threatmetrix.TrustDefender.internal.XU$T):void");
    }

    protected XU(K k, L l, E e) {
        this.f651new = k;
        this.f649int = l;
        this.f648if = e;
    }

    public Handle doProfileRequest(EndNotifier endNotifier) {
        return doProfileRequest(new ProfilingOptions(), endNotifier);
    }

    public Handle doProfileRequest(String str, EndNotifier endNotifier) {
        return doProfileRequest(new ProfilingOptions().setSessionID(str), endNotifier);
    }

    public Handle doProfileRequest(ProfilingOptions profilingOptions, EndNotifier endNotifier) {
        String str;
        CC.m25new();
        if (NK.m203byte(profilingOptions.m_sessionID)) {
            str = NK.m226try(profilingOptions.m_sessionID);
        } else {
            str = NK.m206do();
        }
        final String str2 = str;
        if (!this.f643else.m357if()) {
            Result result = new Result(str2, THMStatusCode.THM_Internal_Error);
            T t = new T(str2);
            if (endNotifier != null) {
                YB.f703int.execute(new I(result, endNotifier));
            }
            return t;
        } else if (!this.f643else.m362try()) {
            Result result2 = new Result(str2, THMStatusCode.THM_NotYet);
            T t2 = new T(str2);
            if (endNotifier != null) {
                YB.f703int.execute(new I(result2, endNotifier));
            }
            return t2;
        } else {
            boolean z = false;
            if ((this.f653this.get() & 1048576) == 0) {
                com.threatmetrix.TrustDefender.internal.P.O o = this.f640char;
                boolean z2 = o == null || P.m234break(o);
                if (this.f641class != 0) {
                    z2 = z2 || this.f647goto;
                }
                if (!z2) {
                    this.f643else.m351char();
                    Result result3 = new Result(str2, THMStatusCode.THM_Blocked);
                    T t3 = new T(str2);
                    if (endNotifier != null) {
                        YB.f703int.execute(new I(result3, endNotifier));
                    }
                    return t3;
                }
            }
            if (!(this.f641class == 0 || this.f644final == 0 || this.f641class + TimeUnit.MILLISECONDS.convert((long) this.f644final, TimeUnit.MINUTES) <= System.currentTimeMillis())) {
                z = true;
            }
            if (z) {
                this.f643else.m351char();
                Result result4 = new Result(str2, THMStatusCode.THM_In_Quiet_Period);
                T t4 = new T(str2);
                if (endNotifier != null) {
                    YB.f703int.execute(new I(result4, endNotifier));
                }
                return t4;
            } else if (endNotifier == null) {
                this.f643else.m351char();
                new Result(str2, THMStatusCode.THM_EndNotifier_NotFound);
                return new T(str2);
            } else {
                TL.m336if();
                String str3 = f635do;
                StringBuilder sb = new StringBuilder("starting profile request using - 5.2-34 options ");
                sb.append(this.f653this.get());
                sb.append(" timeout ");
                sb.append(this.f637byte);
                sb.append("ms fp ");
                sb.append(this.f636break != null ? this.f636break.f535case : "");
                sb.append(" java.vm.version ");
                sb.append(System.getProperty("java.vm.version"));
                TL.m338new(str3, sb.toString());
                T t5 = new T(str2);
                this.f654try.m450int();
                L l = this.f649int;
                if (l != null) {
                    l.m428do(profilingOptions.m_location);
                }
                this.f650long.f322new++;
                final long j = S.m172if();
                final ProfilingOptions profilingOptions2 = profilingOptions;
                final EndNotifier endNotifier2 = endNotifier;
                final T t6 = t5;
                AnonymousClass1 r1 = new Runnable() {
                    public final void run() {
                        XU.m407for(XU.this, str2, profilingOptions2, endNotifier2, j, t6);
                        XU.this.f650long.f321int = S.m172if() - j;
                    }
                };
                Thread thread = new Thread(r1);
                this.f642const = thread;
                thread.start();
                return t5;
            }
        }
    }

    public boolean doPackageScan() {
        return m415do(0, true, R.doPackageScan);
    }

    public boolean doPackageScan(int i, TimeUnit timeUnit) {
        int millis = (int) timeUnit.toMillis((long) i);
        if (millis == 0 && i != 0) {
            millis = 1;
        }
        return m415do(millis, true, R.doPackageScan);
    }

    public void pauseLocationServices(boolean z) {
        L l = this.f649int;
        if (l != null) {
            if (z) {
                l.m432new();
            } else {
                l.m430int();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: if reason: not valid java name */
    public final synchronized void m417if() {
        TL.m338new(f635do, "cancel()");
        if (!this.f643else.m350case()) {
            TL.m325do(f635do, "Cancel already happened");
            return;
        }
        boolean z = this.f643else.m354else();
        if (z) {
            TL.m338new(f635do, "Cancelling package scan");
        }
        boolean z2 = this.f643else.m361new();
        if (z || z2) {
            TL.m338new(f635do, "cancelling any outstanding JNI calls");
            PH.m275do().m296int();
        }
        if (z2) {
            TL.m338new(f635do, "cancelling active profiling request");
            this.f654try.m450int();
            Thread thread = this.f642const;
            if (thread != null) {
                String str = f635do;
                StringBuilder sb = new StringBuilder("sending interrupt to profile thread TID: ");
                sb.append(thread.getId());
                TL.m338new(str, sb.toString());
                thread.interrupt();
            }
            this.f654try = new YB();
            if (thread != null && thread.isAlive()) {
                TL.m338new(f635do, "waiting for profile thread to complete");
                YB.m445if(thread, this.f637byte);
            }
        }
        if (z) {
            TL.m338new(f635do, "Waiting for package scan");
            this.f643else.m356for(false, null);
        }
        if (z || z2) {
            TL.m338new(f635do, "Waiting for any outstanding JNI calls");
            PH.m275do().m301new();
        }
        this.f643else.m349byte();
        TL.m338new(f635do, "Cancelled");
    }

    /* access modifiers changed from: protected */
    /* renamed from: int reason: not valid java name */
    public final THMStatusCode m420int(W w, X x, YB yb) throws InterruptedException {
        X x2 = x;
        F f = new F(this.f638case, L.f171int, this.f636break.m324for(w.f678for.f78this, "clear.png"), x2, TB.m321for(this.f646for), this.f643else);
        yb.m448for(f);
        return yb.m447do(true, this.f643else, this.f637byte);
    }

    /* renamed from: int reason: not valid java name */
    private boolean m413int(String str, String str2) {
        String str3 = this.f646for.f283void.f284boolean;
        if (str3 == null || !str3.equalsIgnoreCase(str)) {
            return true;
        }
        String str4 = P.m253goto();
        if (str4 == null) {
            return false;
        }
        X x = new X();
        x.m374for("sa_pt", str4, false);
        String str5 = x.m375if();
        x.clear();
        x.m374for("org_id", this.f636break.f537try, false);
        x.m374for("session_id", str, false);
        x.m374for("ja", NK.m212for(str5, str), false);
        YB yb = new YB();
        F f = new F(this.f638case, L.f171int, this.f636break.m324for(str2, "clear2.png"), x, TB.f531do, null);
        yb.m448for(f);
        this.f646for.f283void.f284boolean = null;
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0231  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x0299  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x02f9  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x030b  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x0327  */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x0329  */
    /* renamed from: new reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.threatmetrix.TrustDefender.internal.XU.W m422new(java.lang.String r22, com.threatmetrix.TrustDefender.internal.YB r23) throws java.lang.InterruptedException, com.threatmetrix.TrustDefender.internal.XU.O {
        /*
            r21 = this;
            r1 = r21
            r8 = r22
            r9 = r23
            com.threatmetrix.TrustDefender.internal.CC r2 = new com.threatmetrix.TrustDefender.internal.CC
            r2.<init>()
            com.threatmetrix.TrustDefender.internal.TB r3 = r1.f636break
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "https://"
            r4.<init>(r5)
            java.lang.String r3 = r3.f535case
            r4.append(r3)
            java.lang.String r3 = "/fp/mobile/conf"
            r4.append(r3)
            java.lang.String r12 = r4.toString()
            com.threatmetrix.TrustDefender.internal.TB r3 = r1.f636break
            com.threatmetrix.TrustDefender.internal.X r13 = new com.threatmetrix.TrustDefender.internal.X
            r13.<init>()
            java.lang.String r4 = "org_id"
            java.lang.String r5 = r3.f537try
            r6 = 0
            r13.m374for(r4, r5, r6)
            java.lang.String r4 = "session_id"
            r13.m374for(r4, r8, r6)
            java.lang.String r4 = "os"
            java.lang.String r5 = com.threatmetrix.TrustDefender.internal.P.m235byte()
            r13.m374for(r4, r5, r6)
            java.lang.String r4 = "osVersion"
            java.lang.String r5 = com.threatmetrix.TrustDefender.internal.N.I.C0012I.f389int
            r13.m374for(r4, r5, r6)
            java.lang.String r4 = r3.f536int
            if (r4 == 0) goto L_0x0059
            java.lang.String r4 = r3.f536int
            boolean r4 = r4.isEmpty()
            if (r4 != 0) goto L_0x0059
            java.lang.String r4 = "api_key"
            java.lang.String r3 = r3.f536int
            r13.m374for(r4, r3, r6)
        L_0x0059:
            java.lang.String r3 = "sdk_version"
            java.lang.String r4 = com.threatmetrix.TrustDefender.internal.K7.f270new
            r13.m374for(r3, r4, r6)
            com.threatmetrix.TrustDefender.internal.C r3 = new com.threatmetrix.TrustDefender.internal.C
            com.threatmetrix.TrustDefender.internal.HF r11 = r1.f638case
            com.threatmetrix.TrustDefender.internal.K7 r4 = r1.f646for
            java.util.Map r14 = com.threatmetrix.TrustDefender.internal.TB.m323int(r4)
            com.threatmetrix.TrustDefender.internal.VZ r15 = r1.f643else
            r10 = r3
            r10.<init>(r11, r12, r13, r14, r15)
            com.threatmetrix.TrustDefender.internal.A3 r4 = r9.m448for(r3)
            if (r4 == 0) goto L_0x0077
            goto L_0x0078
        L_0x0077:
            r3 = 0
        L_0x0078:
            java.lang.String r4 = "get config"
            java.lang.String r5 = "gc"
            r2.m26do(r4, r5)
            if (r3 != 0) goto L_0x0090
            java.lang.String r2 = f635do
            java.lang.String r3 = "Failed to connect to server, aborting"
            com.threatmetrix.TrustDefender.internal.TL.m332if(r2, r3)
            com.threatmetrix.TrustDefender.internal.XU$O r2 = new com.threatmetrix.TrustDefender.internal.XU$O
            com.threatmetrix.TrustDefender.THMStatusCode r3 = com.threatmetrix.TrustDefender.THMStatusCode.THM_ConfigurationError
            r2.<init>(r3)
            throw r2
        L_0x0090:
            com.threatmetrix.TrustDefender.internal.VZ r4 = r1.f643else
            boolean r4 = r4.m355for()
            if (r4 != 0) goto L_0x033f
            java.lang.Thread r4 = java.lang.Thread.currentThread()
            boolean r4 = r4.isInterrupted()
            if (r4 == 0) goto L_0x00a4
            goto L_0x033f
        L_0x00a4:
            java.util.concurrent.locks.ReentrantLock r4 = r1.f656while     // Catch:{ all -> 0x0337 }
            boolean r4 = r4.tryLock()     // Catch:{ all -> 0x0337 }
            r11 = 0
            if (r4 == 0) goto L_0x0107
            com.threatmetrix.TrustDefender.internal.XU$E r4 = r1.f648if     // Catch:{ all -> 0x0337 }
            if (r4 == 0) goto L_0x00d0
            boolean r5 = r1.f652super     // Catch:{ all -> 0x0337 }
            if (r5 == 0) goto L_0x00d0
            java.util.concurrent.atomic.AtomicLong r5 = r1.f653this     // Catch:{ all -> 0x0337 }
            long r13 = r5.get()     // Catch:{ all -> 0x0337 }
            r15 = 131072(0x20000, double:6.47582E-319)
            long r17 = r13 & r15
            int r5 = (r17 > r11 ? 1 : (r17 == r11 ? 0 : -1))
            if (r5 == 0) goto L_0x00d0
            int r5 = r1.f637byte     // Catch:{ all -> 0x0337 }
            int r5 = r5 / 10
            java.lang.String r4 = r4.m424if(r5)     // Catch:{ all -> 0x0337 }
            r16 = r4
            goto L_0x00d2
        L_0x00d0:
            r16 = 0
        L_0x00d2:
            com.threatmetrix.TrustDefender.internal.VZ r4 = r1.f643else     // Catch:{ all -> 0x0337 }
            boolean r4 = r4.m355for()     // Catch:{ all -> 0x0337 }
            if (r4 != 0) goto L_0x0101
            java.lang.Thread r4 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x0337 }
            boolean r4 = r4.isInterrupted()     // Catch:{ all -> 0x0337 }
            if (r4 == 0) goto L_0x00e5
            goto L_0x0101
        L_0x00e5:
            com.threatmetrix.TrustDefender.internal.K7 r4 = r1.f646for     // Catch:{ all -> 0x0337 }
            com.threatmetrix.TrustDefender.internal.VZ r5 = r1.f643else     // Catch:{ all -> 0x0337 }
            java.lang.String r7 = r1.f639catch     // Catch:{ all -> 0x0337 }
            com.threatmetrix.TrustDefender.internal.K7$E r15 = new com.threatmetrix.TrustDefender.internal.K7$E     // Catch:{ all -> 0x0337 }
            com.threatmetrix.TrustDefender.internal.K7$E r14 = r4.f283void     // Catch:{ all -> 0x0337 }
            r13 = r15
            r17 = r14
            r14 = r4
            r10 = r15
            r15 = r17
            r17 = r5
            r18 = r7
            r13.<init>(r14, r15, r16, r17, r18)     // Catch:{ all -> 0x0337 }
            r4.f283void = r10     // Catch:{ all -> 0x0337 }
            r15 = r10
            goto L_0x0110
        L_0x0101:
            java.lang.InterruptedException r2 = new java.lang.InterruptedException     // Catch:{ all -> 0x0337 }
            r2.<init>()     // Catch:{ all -> 0x0337 }
            throw r2     // Catch:{ all -> 0x0337 }
        L_0x0107:
            java.util.concurrent.locks.ReentrantLock r4 = r1.f656while     // Catch:{ all -> 0x0337 }
            r4.lock()     // Catch:{ all -> 0x0337 }
            com.threatmetrix.TrustDefender.internal.K7 r4 = r1.f646for     // Catch:{ all -> 0x0337 }
            com.threatmetrix.TrustDefender.internal.K7$E r15 = r4.f283void     // Catch:{ all -> 0x0337 }
        L_0x0110:
            java.util.concurrent.locks.ReentrantLock r4 = r1.f656while
            r4.unlock()
            com.threatmetrix.TrustDefender.internal.VZ r4 = r1.f643else
            boolean r4 = r4.m355for()
            if (r4 != 0) goto L_0x0331
            java.lang.Thread r4 = java.lang.Thread.currentThread()
            boolean r4 = r4.isInterrupted()
            if (r4 == 0) goto L_0x0129
            goto L_0x0331
        L_0x0129:
            com.threatmetrix.TrustDefender.internal.VZ r4 = r1.f643else
            int r5 = r1.f637byte
            com.threatmetrix.TrustDefender.THMStatusCode r4 = r9.m447do(r6, r4, r5)
            java.lang.String r5 = "wait for config network request"
            java.lang.String r7 = "wfcnr"
            r2.m26do(r5, r7)
            r23.m449for()
            com.threatmetrix.TrustDefender.THMStatusCode r2 = com.threatmetrix.TrustDefender.THMStatusCode.THM_OK
            if (r4 == r2) goto L_0x015c
            java.lang.String r2 = f635do
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r5 = "Failed to retrieve config, aborting: "
            r3.<init>(r5)
            java.lang.String r5 = r4.toString()
            r3.append(r5)
            java.lang.String r3 = r3.toString()
            com.threatmetrix.TrustDefender.internal.TL.m332if(r2, r3)
            com.threatmetrix.TrustDefender.internal.XU$O r2 = new com.threatmetrix.TrustDefender.internal.XU$O
            r2.<init>(r4)
            throw r2
        L_0x015c:
            com.threatmetrix.TrustDefender.internal.VZ r2 = r1.f643else
            boolean r2 = r2.m355for()
            if (r2 == 0) goto L_0x016a
            java.lang.InterruptedException r2 = new java.lang.InterruptedException
            r2.<init>()
            throw r2
        L_0x016a:
            com.threatmetrix.TrustDefender.internal.CG r10 = r3.f61if
            if (r10 != 0) goto L_0x017d
            java.lang.String r2 = f635do
            java.lang.String r3 = "Failed to get config, bailing out"
            com.threatmetrix.TrustDefender.internal.TL.m332if(r2, r3)
            com.threatmetrix.TrustDefender.internal.XU$O r2 = new com.threatmetrix.TrustDefender.internal.XU$O
            com.threatmetrix.TrustDefender.THMStatusCode r3 = com.threatmetrix.TrustDefender.THMStatusCode.THM_ConfigurationError
            r2.<init>(r3)
            throw r2
        L_0x017d:
            com.threatmetrix.TrustDefender.internal.XU$K r2 = r1.f651new
            java.util.concurrent.atomic.AtomicLong r3 = r1.f653this
            long r3 = r3.get()
            r13 = 16777216(0x1000000, double:8.289046E-317)
            long r16 = r3 & r13
            int r3 = (r16 > r11 ? 1 : (r16 == r11 ? 0 : -1))
            if (r3 == 0) goto L_0x0197
            if (r2 == 0) goto L_0x0197
            com.threatmetrix.TrustDefender.internal.P$O r3 = r1.f640char
            java.lang.String r4 = r10.f69byte
            r2.m427new(r3, r4)
        L_0x0197:
            com.threatmetrix.TrustDefender.internal.DS r2 = r1.f645float
            com.threatmetrix.TrustDefender.internal.P$O r3 = r1.f640char
            java.lang.String r4 = r1.f639catch
            if (r2 == 0) goto L_0x01c9
            long r13 = r10.f74for
            long r5 = r10.f76int
            java.lang.String r7 = "5.2-34"
            int r11 = r10.f79try
            long r8 = r2.f145new
            int r12 = (r13 > r8 ? 1 : (r13 == r8 ? 0 : -1))
            if (r12 != 0) goto L_0x01c2
            long r8 = r2.f144if
            int r12 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
            if (r12 != 0) goto L_0x01c2
            java.lang.String r5 = r2.f142do
            boolean r5 = r7.equals(r5)
            if (r5 == 0) goto L_0x01c2
            int r5 = r2.f143for
            if (r11 == r5) goto L_0x01c0
            goto L_0x01c2
        L_0x01c0:
            r5 = 0
            goto L_0x01c3
        L_0x01c2:
            r5 = 1
        L_0x01c3:
            if (r5 == 0) goto L_0x01c6
            goto L_0x01c9
        L_0x01c6:
            r19 = 0
            goto L_0x022f
        L_0x01c9:
            com.threatmetrix.TrustDefender.internal.CC r5 = new com.threatmetrix.TrustDefender.internal.CC
            r5.<init>()
            if (r2 != 0) goto L_0x01d7
            com.threatmetrix.TrustDefender.internal.DS r2 = new com.threatmetrix.TrustDefender.internal.DS
            r2.<init>()
            r1.f645float = r2
        L_0x01d7:
            long r6 = r10.f74for
            r2.f145new = r6
            long r6 = r10.f76int
            r2.f144if = r6
            java.lang.String r6 = "5.2-34"
            r2.f142do = r6
            int r6 = r10.f79try
            r2.f143for = r6
            com.threatmetrix.TrustDefender.internal.PH r6 = com.threatmetrix.TrustDefender.internal.PH.m275do()
            java.lang.String r7 = "enableOptions"
            long r8 = r10.f74for
            java.lang.String r8 = java.lang.String.valueOf(r8)
            r6.m278do(r7, r8)
            com.threatmetrix.TrustDefender.internal.PH r6 = com.threatmetrix.TrustDefender.internal.PH.m275do()
            java.lang.String r7 = "disableOptions"
            long r8 = r10.f76int
            java.lang.String r8 = java.lang.String.valueOf(r8)
            r6.m278do(r7, r8)
            com.threatmetrix.TrustDefender.internal.PH r6 = com.threatmetrix.TrustDefender.internal.PH.m275do()
            java.lang.String r7 = "sdkVersion"
            java.lang.String r8 = "5.2-34"
            r6.m278do(r7, r8)
            com.threatmetrix.TrustDefender.internal.PH r6 = com.threatmetrix.TrustDefender.internal.PH.m275do()
            java.lang.String r7 = "quietPeriod"
            int r8 = r10.f79try
            java.lang.String r8 = java.lang.String.valueOf(r8)
            r6.m278do(r7, r8)
            if (r3 == 0) goto L_0x0226
            if (r4 == 0) goto L_0x0226
            com.threatmetrix.TrustDefender.internal.OE.m232int(r3, r4, r2)
        L_0x0226:
            java.lang.String r2 = "Processed stored options"
            java.lang.String r3 = "pso"
            r5.m26do(r2, r3)
            r19 = 1
        L_0x022f:
            if (r19 == 0) goto L_0x024f
            long r2 = r10.f76int
            r4 = 535822334(0x1feffffe, double:2.647314075E-315)
            long r6 = r2 & r4
            long r2 = r10.f74for
            long r8 = r2 & r4
            java.util.concurrent.atomic.AtomicLong r2 = r1.f653this
            java.util.concurrent.atomic.AtomicLong r3 = r1.f653this
            long r3 = r3.get()
            r11 = -1
            long r13 = r6 ^ r11
            long r5 = r3 & r13
            long r3 = r5 | r8
            r2.set(r3)
        L_0x024f:
            int r2 = r10.f79try
            r1.f644final = r2
            java.util.concurrent.atomic.AtomicLong r2 = r1.f653this
            long r2 = r2.get()
            r4 = 65536(0x10000, double:3.2379E-319)
            long r6 = r2 & r4
            r2 = 0
            int r4 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r4 == 0) goto L_0x0287
            java.lang.String r2 = r10.f70case
            boolean r2 = com.threatmetrix.TrustDefender.internal.NK.m203byte(r2)
            if (r2 == 0) goto L_0x0287
            java.lang.String r6 = r10.f70case
            java.util.Map<java.lang.String, java.lang.String> r8 = com.threatmetrix.TrustDefender.internal.TB.f531do
            com.threatmetrix.TrustDefender.internal.F r2 = new com.threatmetrix.TrustDefender.internal.F
            com.threatmetrix.TrustDefender.internal.HF r4 = r1.f638case
            int r5 = com.threatmetrix.TrustDefender.internal.F.L.f172new
            com.threatmetrix.TrustDefender.internal.X r7 = new com.threatmetrix.TrustDefender.internal.X
            r7.<init>()
            com.threatmetrix.TrustDefender.internal.VZ r9 = r1.f643else
            r3 = r2
            r3.<init>(r4, r5, r6, r7, r8, r9)
            r8 = r23
            r8.m448for(r2)
            goto L_0x0289
        L_0x0287:
            r8 = r23
        L_0x0289:
            java.util.concurrent.atomic.AtomicLong r2 = r1.f653this
            long r2 = r2.get()
            r4 = 1024(0x400, double:5.06E-321)
            long r6 = r2 & r4
            r2 = 0
            int r4 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r4 == 0) goto L_0x02f9
            com.threatmetrix.TrustDefender.internal.CC r2 = new com.threatmetrix.TrustDefender.internal.CC
            r2.<init>()
            com.threatmetrix.TrustDefender.internal.TB r3 = r1.f636break
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r3 = r3.f537try
            r4.append(r3)
            java.lang.String r3 = "-"
            r4.append(r3)
            r9 = r22
            java.lang.String r3 = com.threatmetrix.TrustDefender.internal.NK.m204case(r22)
            r4.append(r3)
            java.lang.String r3 = "-mob"
            r4.append(r3)
            java.lang.String r3 = com.threatmetrix.TrustDefender.internal.TB.f534new
            java.lang.String r5 = "Launching DNS profiling request"
            com.threatmetrix.TrustDefender.internal.TL.m338new(r3, r5)
            java.lang.String r3 = ".d"
            java.lang.String r5 = com.threatmetrix.TrustDefender.internal.TB.f532for
            if (r5 == 0) goto L_0x02dd
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r5 = com.threatmetrix.TrustDefender.internal.TB.f532for
            r3.append(r5)
            java.lang.String r5 = ".q"
            r3.append(r5)
            java.lang.String r3 = r3.toString()
        L_0x02dd:
            r4.append(r3)
            java.lang.String r3 = ".aa.online-metrix.net"
            r4.append(r3)
            java.lang.String r3 = r4.toString()
            com.threatmetrix.TrustDefender.internal.S r4 = new com.threatmetrix.TrustDefender.internal.S
            r4.<init>(r3)
            r8.m448for(r4)
            java.lang.String r3 = "Started DNS request"
            java.lang.String r4 = "sdr"
            r2.m26do(r3, r4)
            goto L_0x02fb
        L_0x02f9:
            r9 = r22
        L_0x02fb:
            java.util.concurrent.atomic.AtomicLong r2 = r1.f653this
            long r2 = r2.get()
            r4 = 64
            long r6 = r2 & r4
            r2 = 0
            int r4 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r4 == 0) goto L_0x0321
            com.threatmetrix.TrustDefender.internal.ZR r11 = new com.threatmetrix.TrustDefender.internal.ZR
            com.threatmetrix.TrustDefender.internal.TB r2 = r1.f636break
            java.lang.String r3 = r2.f535case
            com.threatmetrix.TrustDefender.internal.TB r2 = r1.f636break
            java.lang.String r4 = r2.f537try
            java.lang.String r6 = r10.f75if
            int r7 = r1.f637byte
            r2 = r11
            r5 = r9
            r2.<init>(r3, r4, r5, r6, r7)
            r8.m448for(r11)
        L_0x0321:
            com.threatmetrix.TrustDefender.internal.XU$L r2 = r1.f649int
            com.threatmetrix.TrustDefender.internal.XU$W r3 = new com.threatmetrix.TrustDefender.internal.XU$W
            if (r2 != 0) goto L_0x0329
            r2 = 0
            goto L_0x032d
        L_0x0329:
            com.threatmetrix.TrustDefender.internal.K7$O r2 = r2.m429if()
        L_0x032d:
            r3.<init>(r15, r10, r9, r2)
            return r3
        L_0x0331:
            java.lang.InterruptedException r2 = new java.lang.InterruptedException
            r2.<init>()
            throw r2
        L_0x0337:
            r0 = move-exception
            r2 = r0
            java.util.concurrent.locks.ReentrantLock r3 = r1.f656while
            r3.unlock()
            throw r2
        L_0x033f:
            java.lang.InterruptedException r2 = new java.lang.InterruptedException
            r2.<init>()
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.internal.XU.m422new(java.lang.String, com.threatmetrix.TrustDefender.internal.YB):com.threatmetrix.TrustDefender.internal.XU$W");
    }

    /* access modifiers changed from: private */
    /* renamed from: do reason: not valid java name */
    public DS m403do() {
        DS ds;
        com.threatmetrix.TrustDefender.internal.P.O o;
        String str;
        if (PH.m275do().f494char) {
            ds = new DS();
            try {
                String str2 = PH.m275do().m292if("enableOptions");
                if (str2 != null) {
                    ds.f145new = Long.parseLong(str2);
                }
                String str3 = PH.m275do().m292if("disableOptions");
                if (str3 != null) {
                    ds.f144if = Long.parseLong(str3);
                }
                String str4 = PH.m275do().m292if("quietPeriod");
                if (str4 != null) {
                    ds.f143for = Integer.parseInt(str4);
                }
                String str5 = PH.m275do().m292if("sdkVersion");
                if (str5 != null) {
                    ds.f142do = str5;
                    o = this.f640char;
                    str = this.f639catch;
                    if (ds == null || !(ds.f142do.equals("5.2-34") || o == null || str == null)) {
                        OE.m231int(o, str);
                    }
                    if (ds != null || ds.f142do.equals("5.2-34")) {
                        return ds;
                    }
                    return null;
                }
            } catch (NumberFormatException e) {
                TL.m328for(f635do, "Options/ quietPeriod are not a number", (Throwable) e);
            }
        }
        ds = null;
        o = this.f640char;
        str = this.f639catch;
        OE.m231int(o, str);
        if (ds != null) {
        }
        return ds;
    }

    /* renamed from: int reason: not valid java name */
    private Handle m410int(Result result, EndNotifier endNotifier, Handle handle) {
        this.f643else.m351char();
        if (endNotifier != null) {
            YB.f703int.execute(new I(result, endNotifier));
        }
        return handle;
    }

    /* renamed from: for reason: not valid java name */
    protected static long m405for(long j, long j2, String str) {
        if (j >= 0 && (j2 == 0 || j <= j2)) {
            return j;
        }
        TL.m334if(f635do, "Invalid value for {}, {}", str, String.valueOf(j));
        StringBuilder sb = new StringBuilder("Invalid value for {");
        sb.append(str);
        sb.append("}");
        throw new IllegalArgumentException(sb.toString());
    }

    public void init(final Config config) throws IllegalArgumentException, IllegalStateException {
        final com.threatmetrix.TrustDefender.internal.P.O o;
        if (config.m_context == null) {
            o = null;
        } else {
            o = new com.threatmetrix.TrustDefender.internal.P.O(config.m_context.getApplicationContext());
        }
        if (o == null) {
            throw new IllegalArgumentException("Failed to init: Invalid Context");
        }
        TL.m335if(0 == (config.m0if() & 268435456));
        if (!this.f643else.m352do()) {
            TL.m338new(f635do, "Already init'd");
            return;
        }
        try {
            m418if(config);
            if (!TB.m322for(config.m_orgId)) {
                this.f643else.m358int(false);
                throw new IllegalArgumentException("Failed to init: Invalid format for org id");
            } else if (TB.m320do(config.m_fp_server)) {
                this.f643else.m358int(false);
                throw new IllegalArgumentException("Failed to init: Invalid format for fingerprint server");
            } else {
                this.f636break = new TB(config.m_fp_server, config.m_orgId, config.m_apiKey);
                List<String> list = config.m_certificateHashes;
                if (list != null) {
                    int i = 0;
                    for (int i2 = 0; i2 < list.size(); i2++) {
                        String str = (String) list.get(i2);
                        if (i2 == 0 && str != null) {
                            i = str.length();
                        }
                        if (str == null || !NK.m221long(str) || i != str.length()) {
                            TL.m334if(f635do, "Invalid certificate fingerprint {} only SHA1/SHA256 are accepted", str);
                            this.f643else.m358int(false);
                            throw new IllegalArgumentException("Failed to init: Invalid certificate hash.");
                        }
                    }
                }
                this.f638case = m416for(config);
                if (this.f638case == null) {
                    this.f643else.m358int(false);
                    TL.m332if(f635do, "Failed to instantiate Okhttp client, is the right version linked? Please consider either using another version or disabling Okhttpby adding .setDisableOKHTTP(true) to Config object.");
                    throw new IllegalStateException("Failed to init: failed to instantiate OkHttp");
                }
                TL.m338new(f635do, "Starting init()");
                this.f647goto = true;
                final L l = this.f649int;
                if (l != null) {
                    l.m432new();
                }
                this.f640char = o;
                this.f637byte = (int) m405for((long) config.m_timeout, 2147483647L, "timeout");
                String packageName = o.f487for.getPackageName();
                StringBuilder sb = new StringBuilder();
                sb.append(packageName);
                sb.append("TDM");
                sb.append(config.m_orgId);
                this.f639catch = sb.toString();
                this.f653this.set(config.m0if());
                new Thread(new Runnable() {
                    public final void run() {
                        com.threatmetrix.TrustDefender.internal.P.O o;
                        CC cc = new CC();
                        TL.m338new(XU.f635do, "Doing slow init stuff");
                        if (l != null) {
                            l.m431int(config);
                        }
                        PH ph = PH.m275do();
                        com.threatmetrix.TrustDefender.internal.P.O o2 = o;
                        boolean z = TL.m336if();
                        boolean z2 = TL.m330for();
                        String absolutePath = o2.f487for.getFilesDir().getAbsolutePath();
                        L l = new L(o2);
                        String str = l.f411int != null ? l.f411int.nativeLibraryDir : "";
                        if (!ph.m293if(absolutePath, z, z2)) {
                            String[] list = new File(str).list(new FilenameFilter() {
                                public final boolean accept(File file, String str) {
                                    return str.contains(KF.jniFilename);
                                }
                            });
                            if (!(list == null || list.length == 0)) {
                                ph.f495do = true;
                            }
                        }
                        String str2 = XU.f635do;
                        StringBuilder sb = new StringBuilder("Native libs: ");
                        sb.append(PH.m275do().f494char ? "available" : "unavailable");
                        TL.m325do(str2, sb.toString());
                        DS ds = XU.this.m403do();
                        if (ds != null) {
                            String str3 = XU.f635do;
                            StringBuilder sb2 = new StringBuilder("applying saved options (");
                            sb2.append(ds.f145new);
                            sb2.append(" / ");
                            sb2.append(ds.f144if);
                            sb2.append(") to ");
                            sb2.append(XU.this.f653this.get());
                            TL.m338new(str3, sb2.toString());
                            XU.this.f653this.set((XU.this.f653this.get() & (ds.f144if ^ -1)) | ds.f145new);
                            XU.f635do;
                            XU.this.f645float = ds;
                            XU.this.f644final = ds.f143for;
                        }
                        if ((XU.this.f653this.get() & 1048576) == 0) {
                            XU.this.m419if(o);
                        }
                        E e = XU.this.f648if;
                        if (!((XU.this.f653this.get() & 131072) == 0 || e == null)) {
                            XU.this.f652super = e.m423for(o);
                        }
                        K k = XU.this.m421int(o);
                        Config config = config;
                        if (config.m_context == null) {
                            o = null;
                        } else {
                            o = new com.threatmetrix.TrustDefender.internal.P.O(config.m_context.getApplicationContext());
                        }
                        K7 k7 = new K7(o, "", config.m0if(), XU.this.f653this, config.m_registerForPush, k);
                        XU.this.f646for = k7;
                        cc.m26do("getUserAgent", "gua");
                        TL.m338new(XU.f635do, "Creating HTTP Client");
                        XU.this.f638case.f175byte = k7.f276else;
                        cc.m26do("create HttpClient", "ch");
                        TL.m338new(XU.f635do, "HTTP Client created and user agent set");
                        NK.m208do((String) null);
                        XU.this.m415do(0, false, R.init);
                        XU.this.f643else.m358int(true);
                        TL.m338new(XU.f635do, "init completed successfully");
                    }
                }).start();
            }
        } catch (RuntimeException e) {
            if ((e instanceof IllegalStateException) || (e instanceof IllegalArgumentException)) {
                this.f643else.m358int(false);
            }
            throw e;
        }
    }
}
