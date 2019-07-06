package com.threatmetrix.TrustDefender.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class YB {

    /* renamed from: do reason: not valid java name */
    private static final boolean f701do;
    /* access modifiers changed from: private */

    /* renamed from: for reason: not valid java name */
    public static final String f702for = TL.m331if(YB.class);

    /* renamed from: int reason: not valid java name */
    public static final Executor f703int = Executors.newFixedThreadPool(6);

    /* renamed from: byte reason: not valid java name */
    private final Lock f704byte = this.f706new.writeLock();

    /* renamed from: if reason: not valid java name */
    private final ArrayList<A3> f705if = new ArrayList<>();

    /* renamed from: new reason: not valid java name */
    private final ReadWriteLock f706new = new ReentrantReadWriteLock();

    /* renamed from: try reason: not valid java name */
    private final Lock f707try = this.f706new.readLock();

    final class I implements Runnable {

        /* renamed from: do reason: not valid java name */
        final Thread f708do;

        I(Thread thread) {
            this.f708do = thread;
        }

        public final void run() {
            String str = YB.f702for;
            StringBuilder sb = new StringBuilder("sending interrupt to TID: ");
            sb.append(this.f708do.getId());
            TL.m338new(str, sb.toString());
            this.f708do.interrupt();
        }
    }

    static {
        String property = System.getProperty("java.vm.version");
        boolean z = property != null && property.equals("2.0.0");
        f701do = z;
        if (z) {
            TL.m338new(f702for, "Broken join() detected, activating fallback routine");
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        m446new();
        r0 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        m446new();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
        m446new();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00ce, code lost:
        r0 = r3;
     */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00dd A[Catch:{ all -> 0x0118 }] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0115  */
    /* JADX WARNING: Removed duplicated region for block: B:73:? A[RETURN, SYNTHETIC] */
    /* renamed from: do reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.threatmetrix.TrustDefender.THMStatusCode m447do(boolean r9, com.threatmetrix.TrustDefender.internal.VZ r10, int r11) throws java.lang.InterruptedException {
        /*
            r8 = this;
            com.threatmetrix.TrustDefender.THMStatusCode r0 = com.threatmetrix.TrustDefender.THMStatusCode.THM_NotYet
            com.threatmetrix.TrustDefender.internal.CC r1 = new com.threatmetrix.TrustDefender.internal.CC
            r1.<init>()
            java.util.concurrent.locks.Lock r2 = r8.f707try     // Catch:{ all -> 0x0118 }
            r2.lockInterruptibly()     // Catch:{ all -> 0x0118 }
            java.util.ArrayList<com.threatmetrix.TrustDefender.internal.A3> r2 = r8.f705if     // Catch:{ all -> 0x0118 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x0118 }
        L_0x0012:
            boolean r3 = r2.hasNext()     // Catch:{ all -> 0x0118 }
            if (r3 == 0) goto L_0x0105
            java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x0118 }
            com.threatmetrix.TrustDefender.internal.A3 r3 = (com.threatmetrix.TrustDefender.internal.A3) r3     // Catch:{ all -> 0x0118 }
            boolean r4 = r10.m355for()     // Catch:{ all -> 0x0118 }
            if (r4 != 0) goto L_0x0103
            java.lang.Thread r4 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x0118 }
            boolean r4 = r4.isInterrupted()     // Catch:{ all -> 0x0118 }
            if (r4 == 0) goto L_0x0030
            goto L_0x0103
        L_0x0030:
            long r4 = (long) r11
            r3.join(r4)     // Catch:{ InterruptedException -> 0x00d6 }
            java.lang.Thread$State r4 = r3.getState()     // Catch:{ InterruptedException -> 0x00d6 }
            java.lang.Thread$State r5 = java.lang.Thread.State.TERMINATED     // Catch:{ InterruptedException -> 0x00d6 }
            if (r4 == r5) goto L_0x005d
            java.lang.String r4 = f702for     // Catch:{ InterruptedException -> 0x00d6 }
            java.lang.String r5 = "Connection hasn't completed before the timeout expired, aborting"
            com.threatmetrix.TrustDefender.internal.TL.m332if(r4, r5)     // Catch:{ InterruptedException -> 0x00d6 }
            com.threatmetrix.TrustDefender.THMStatusCode r4 = com.threatmetrix.TrustDefender.THMStatusCode.THM_NetworkTimeout_Error     // Catch:{ InterruptedException -> 0x00d6 }
            if (r9 != 0) goto L_0x0051
            r8.m446new()     // Catch:{ InterruptedException -> 0x004d }
            r0 = r4
            goto L_0x0105
        L_0x004d:
            r9 = move-exception
            r0 = r4
            goto L_0x00d7
        L_0x0051:
            java.util.concurrent.Executor r0 = f703int     // Catch:{ InterruptedException -> 0x004d }
            com.threatmetrix.TrustDefender.internal.YB$I r5 = new com.threatmetrix.TrustDefender.internal.YB$I     // Catch:{ InterruptedException -> 0x004d }
            r5.<init>(r3)     // Catch:{ InterruptedException -> 0x004d }
            r0.execute(r5)     // Catch:{ InterruptedException -> 0x004d }
            r0 = r4
            goto L_0x0012
        L_0x005d:
            java.lang.Runnable r4 = r3.f48for     // Catch:{ InterruptedException -> 0x00d6 }
            boolean r4 = r4 instanceof com.threatmetrix.TrustDefender.internal.F     // Catch:{ InterruptedException -> 0x00d6 }
            r5 = 0
            if (r4 == 0) goto L_0x0069
            java.lang.Runnable r4 = r3.f48for     // Catch:{ InterruptedException -> 0x00d6 }
            com.threatmetrix.TrustDefender.internal.F r4 = (com.threatmetrix.TrustDefender.internal.F) r4     // Catch:{ InterruptedException -> 0x00d6 }
            goto L_0x006a
        L_0x0069:
            r4 = r5
        L_0x006a:
            if (r4 == 0) goto L_0x0012
            java.lang.Runnable r6 = r3.f48for     // Catch:{ InterruptedException -> 0x00d6 }
            boolean r6 = r6 instanceof com.threatmetrix.TrustDefender.internal.F     // Catch:{ InterruptedException -> 0x00d6 }
            if (r6 == 0) goto L_0x0077
            java.lang.Runnable r3 = r3.f48for     // Catch:{ InterruptedException -> 0x00d6 }
            r5 = r3
            com.threatmetrix.TrustDefender.internal.F r5 = (com.threatmetrix.TrustDefender.internal.F) r5     // Catch:{ InterruptedException -> 0x00d6 }
        L_0x0077:
            com.threatmetrix.TrustDefender.THMStatusCode r3 = r5.m63do()     // Catch:{ InterruptedException -> 0x00d6 }
            com.threatmetrix.TrustDefender.THMStatusCode r5 = com.threatmetrix.TrustDefender.THMStatusCode.THM_OK     // Catch:{ InterruptedException -> 0x00d6 }
            if (r3 != r5) goto L_0x00aa
            com.threatmetrix.TrustDefender.internal.JR r5 = r4.f167new     // Catch:{ InterruptedException -> 0x00d6 }
            int r5 = r5.m114case()     // Catch:{ InterruptedException -> 0x00d6 }
            r6 = 200(0xc8, float:2.8E-43)
            if (r5 == r6) goto L_0x00aa
            java.lang.String r3 = f702for     // Catch:{ InterruptedException -> 0x00d6 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ InterruptedException -> 0x00d6 }
            java.lang.String r6 = "Connection returned http status code:"
            r5.<init>(r6)     // Catch:{ InterruptedException -> 0x00d6 }
            com.threatmetrix.TrustDefender.internal.JR r4 = r4.f167new     // Catch:{ InterruptedException -> 0x00d6 }
            int r4 = r4.m114case()     // Catch:{ InterruptedException -> 0x00d6 }
            r5.append(r4)     // Catch:{ InterruptedException -> 0x00d6 }
            java.lang.String r4 = r5.toString()     // Catch:{ InterruptedException -> 0x00d6 }
            com.threatmetrix.TrustDefender.internal.TL.m338new(r3, r4)     // Catch:{ InterruptedException -> 0x00d6 }
            com.threatmetrix.TrustDefender.THMStatusCode r3 = com.threatmetrix.TrustDefender.THMStatusCode.THM_Connection_Error     // Catch:{ InterruptedException -> 0x00d6 }
            if (r9 != 0) goto L_0x00d3
            r8.m446new()     // Catch:{ InterruptedException -> 0x00d0 }
            goto L_0x00ce
        L_0x00aa:
            com.threatmetrix.TrustDefender.THMStatusCode r5 = com.threatmetrix.TrustDefender.THMStatusCode.THM_OK     // Catch:{ InterruptedException -> 0x00d6 }
            if (r3 == r5) goto L_0x0012
            java.lang.String r5 = f702for     // Catch:{ InterruptedException -> 0x00d6 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ InterruptedException -> 0x00d6 }
            java.lang.String r7 = "Connection returned status :"
            r6.<init>(r7)     // Catch:{ InterruptedException -> 0x00d6 }
            com.threatmetrix.TrustDefender.THMStatusCode r4 = r4.m63do()     // Catch:{ InterruptedException -> 0x00d6 }
            java.lang.String r4 = r4.getDesc()     // Catch:{ InterruptedException -> 0x00d6 }
            r6.append(r4)     // Catch:{ InterruptedException -> 0x00d6 }
            java.lang.String r4 = r6.toString()     // Catch:{ InterruptedException -> 0x00d6 }
            com.threatmetrix.TrustDefender.internal.TL.m338new(r5, r4)     // Catch:{ InterruptedException -> 0x00d6 }
            if (r9 != 0) goto L_0x00d3
            r8.m446new()     // Catch:{ InterruptedException -> 0x00d0 }
        L_0x00ce:
            r0 = r3
            goto L_0x0105
        L_0x00d0:
            r9 = move-exception
            r0 = r3
            goto L_0x00d7
        L_0x00d3:
            r0 = r3
            goto L_0x0012
        L_0x00d6:
            r9 = move-exception
        L_0x00d7:
            boolean r10 = r10.m355for()     // Catch:{ all -> 0x0118 }
            if (r10 != 0) goto L_0x0105
            com.threatmetrix.TrustDefender.THMStatusCode r10 = com.threatmetrix.TrustDefender.THMStatusCode.THM_NotYet     // Catch:{ all -> 0x0118 }
            if (r0 != r10) goto L_0x00e4
            com.threatmetrix.TrustDefender.THMStatusCode r10 = com.threatmetrix.TrustDefender.THMStatusCode.THM_Connection_Error     // Catch:{ all -> 0x0118 }
            r0 = r10
        L_0x00e4:
            r8.m446new()     // Catch:{ all -> 0x0118 }
            java.lang.Thread r10 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x0118 }
            java.lang.String r11 = f702for     // Catch:{ all -> 0x0118 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0118 }
            java.lang.String r3 = "thread join: this thread = TID "
            r2.<init>(r3)     // Catch:{ all -> 0x0118 }
            long r3 = r10.getId()     // Catch:{ all -> 0x0118 }
            r2.append(r3)     // Catch:{ all -> 0x0118 }
            java.lang.String r10 = r2.toString()     // Catch:{ all -> 0x0118 }
            com.threatmetrix.TrustDefender.internal.TL.m328for(r11, r10, r9)     // Catch:{ all -> 0x0118 }
            goto L_0x0105
        L_0x0103:
            com.threatmetrix.TrustDefender.THMStatusCode r0 = com.threatmetrix.TrustDefender.THMStatusCode.THM_Interrupted_Error     // Catch:{ all -> 0x0118 }
        L_0x0105:
            java.lang.String r9 = "wait for network threads"
            java.lang.String r10 = "wfnt"
            r1.m26do(r9, r10)
            java.util.concurrent.locks.Lock r9 = r8.f707try
            r9.unlock()
            com.threatmetrix.TrustDefender.THMStatusCode r9 = com.threatmetrix.TrustDefender.THMStatusCode.THM_NotYet
            if (r0 != r9) goto L_0x0117
            com.threatmetrix.TrustDefender.THMStatusCode r0 = com.threatmetrix.TrustDefender.THMStatusCode.THM_OK
        L_0x0117:
            return r0
        L_0x0118:
            r9 = move-exception
            java.lang.String r10 = "wait for network threads"
            java.lang.String r11 = "wfnt"
            r1.m26do(r10, r11)
            java.util.concurrent.locks.Lock r10 = r8.f707try
            r10.unlock()
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.internal.YB.m447do(boolean, com.threatmetrix.TrustDefender.internal.VZ, int):com.threatmetrix.TrustDefender.THMStatusCode");
    }

    /* renamed from: new reason: not valid java name */
    private void m446new() {
        Iterator it = this.f705if.iterator();
        while (it.hasNext()) {
            f703int.execute(new I((A3) it.next()));
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: int reason: not valid java name */
    public final void m450int() {
        try {
            this.f707try.lock();
            Iterator it = this.f705if.iterator();
            while (it.hasNext()) {
                it.next();
                m446new();
            }
        } finally {
            this.f707try.unlock();
        }
    }

    /* renamed from: for reason: not valid java name */
    public final void m449for() {
        try {
            this.f704byte.lock();
            this.f705if.clear();
        } finally {
            this.f704byte.unlock();
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: for reason: not valid java name */
    public final A3 m448for(Runnable runnable) {
        try {
            A3 a3 = new A3(runnable);
            if (runnable instanceof F) {
                F f = (F) runnable;
                String str = f702for;
                StringBuilder sb = new StringBuilder("Adding thread ID: ");
                sb.append(a3.getId());
                sb.append(" for: ");
                sb.append(f.f163do);
                TL.m338new(str, sb.toString());
                this.f704byte.lock();
                this.f705if.add(a3);
                this.f704byte.unlock();
            }
            a3.start();
            return a3;
        } catch (RuntimeException unused) {
            return null;
        } catch (Throwable th) {
            this.f704byte.unlock();
            throw th;
        }
    }

    /* renamed from: if reason: not valid java name */
    static boolean m445if(Thread thread, int i) {
        int i2;
        String str = f702for;
        StringBuilder sb = new StringBuilder("waiting for thread to complete - ");
        sb.append(thread.getId());
        TL.m338new(str, sb.toString());
        if (f701do) {
            i2 = i / 100;
            if (i2 < 100) {
                i2 = 100;
            }
        } else {
            i2 = i;
        }
        int i3 = 0;
        boolean z = false;
        do {
            try {
                thread.join((long) i2);
            } catch (InterruptedException unused) {
                z = true;
            }
            i3 += i2;
            if (!thread.isAlive() || i3 >= i) {
            }
        } while (!z);
        if (!thread.isAlive()) {
            return true;
        }
        if (!z) {
            Exception exc = new Exception();
            exc.setStackTrace(thread.getStackTrace());
            String str2 = f702for;
            StringBuilder sb2 = new StringBuilder("join() timeout expired, but thread is still alive (!). Stack trace of TID ");
            sb2.append(thread.getId());
            TL.m333if(str2, sb2.toString(), (Throwable) exc);
        }
        return false;
    }
}
