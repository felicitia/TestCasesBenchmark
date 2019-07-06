package com.google.android.gms.internal.measurement;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public final class bq extends cp {
    /* access modifiers changed from: private */
    public static final AtomicLong k = new AtomicLong(Long.MIN_VALUE);
    private ExecutorService a;
    /* access modifiers changed from: private */
    public bt b;
    /* access modifiers changed from: private */
    public bt c;
    private final PriorityBlockingQueue<bs<?>> d = new PriorityBlockingQueue<>();
    private final BlockingQueue<bs<?>> e = new LinkedBlockingQueue();
    private final UncaughtExceptionHandler f = new br(this, "Thread death: Uncaught exception on worker thread");
    private final UncaughtExceptionHandler g = new br(this, "Thread death: Uncaught exception on network thread");
    /* access modifiers changed from: private */
    public final Object h = new Object();
    /* access modifiers changed from: private */
    public final Semaphore i = new Semaphore(2);
    /* access modifiers changed from: private */
    public volatile boolean j;

    bq(bu buVar) {
        super(buVar);
    }

    private final void a(bs<?> bsVar) {
        synchronized (this.h) {
            this.d.add(bsVar);
            if (this.b == null) {
                this.b = new bt(this, "Measurement Worker", this.d);
                this.b.setUncaughtExceptionHandler(this.f);
                this.b.start();
            } else {
                this.b.a();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Can't wrap try/catch for region: R(6:16|17|(1:19)(1:20)|21|22|23) */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0014, code lost:
        r2 = r().i();
        r3 = "Timed out waiting for ";
        r4 = java.lang.String.valueOf(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0026, code lost:
        if (r4.length() == 0) goto L_0x002d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0028, code lost:
        r3 = r3.concat(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002d, code lost:
        r3 = new java.lang.String(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0033, code lost:
        r2.a(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0036, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r2 = r().i();
        r3 = "Interrupted waiting for ";
        r4 = java.lang.String.valueOf(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0049, code lost:
        if (r4.length() != 0) goto L_0x004b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004b, code lost:
        r3 = r3.concat(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0050, code lost:
        r3 = new java.lang.String(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0056, code lost:
        r2.a(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x005b, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000e, code lost:
        r1 = r1.get();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0012, code lost:
        if (r1 != null) goto L_0x0036;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x0037 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <T> T a(java.util.concurrent.atomic.AtomicReference<T> r1, long r2, java.lang.String r4, java.lang.Runnable r5) {
        /*
            r0 = this;
            monitor-enter(r1)
            com.google.android.gms.internal.measurement.bq r2 = r0.q()     // Catch:{ all -> 0x005c }
            r2.a(r5)     // Catch:{ all -> 0x005c }
            r2 = 15000(0x3a98, double:7.411E-320)
            r1.wait(r2)     // Catch:{ InterruptedException -> 0x0037 }
            monitor-exit(r1)     // Catch:{ all -> 0x005c }
            java.lang.Object r1 = r1.get()
            if (r1 != 0) goto L_0x0036
            com.google.android.gms.internal.measurement.aq r2 = r0.r()
            com.google.android.gms.internal.measurement.as r2 = r2.i()
            java.lang.String r3 = "Timed out waiting for "
            java.lang.String r4 = java.lang.String.valueOf(r4)
            int r5 = r4.length()
            if (r5 == 0) goto L_0x002d
            java.lang.String r3 = r3.concat(r4)
            goto L_0x0033
        L_0x002d:
            java.lang.String r4 = new java.lang.String
            r4.<init>(r3)
            r3 = r4
        L_0x0033:
            r2.a(r3)
        L_0x0036:
            return r1
        L_0x0037:
            com.google.android.gms.internal.measurement.aq r2 = r0.r()     // Catch:{ all -> 0x005c }
            com.google.android.gms.internal.measurement.as r2 = r2.i()     // Catch:{ all -> 0x005c }
            java.lang.String r3 = "Interrupted waiting for "
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x005c }
            int r5 = r4.length()     // Catch:{ all -> 0x005c }
            if (r5 == 0) goto L_0x0050
            java.lang.String r3 = r3.concat(r4)     // Catch:{ all -> 0x005c }
            goto L_0x0056
        L_0x0050:
            java.lang.String r4 = new java.lang.String     // Catch:{ all -> 0x005c }
            r4.<init>(r3)     // Catch:{ all -> 0x005c }
            r3 = r4
        L_0x0056:
            r2.a(r3)     // Catch:{ all -> 0x005c }
            r2 = 0
            monitor-exit(r1)     // Catch:{ all -> 0x005c }
            return r2
        L_0x005c:
            r2 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x005c }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.bq.a(java.util.concurrent.atomic.AtomicReference, long, java.lang.String, java.lang.Runnable):java.lang.Object");
    }

    public final <V> Future<V> a(Callable<V> callable) throws IllegalStateException {
        z();
        Preconditions.checkNotNull(callable);
        bs bsVar = new bs(this, callable, false, "Task exception on worker thread");
        if (Thread.currentThread() == this.b) {
            if (!this.d.isEmpty()) {
                r().i().a("Callable skipped the worker queue.");
            }
            bsVar.run();
            return bsVar;
        }
        a(bsVar);
        return bsVar;
    }

    public final /* bridge */ /* synthetic */ void a() {
        super.a();
    }

    public final void a(Runnable runnable) throws IllegalStateException {
        z();
        Preconditions.checkNotNull(runnable);
        a(new bs<>(this, runnable, false, "Task exception on worker thread"));
    }

    public final <V> Future<V> b(Callable<V> callable) throws IllegalStateException {
        z();
        Preconditions.checkNotNull(callable);
        bs bsVar = new bs(this, callable, true, "Task exception on worker thread");
        if (Thread.currentThread() == this.b) {
            bsVar.run();
            return bsVar;
        }
        a(bsVar);
        return bsVar;
    }

    public final /* bridge */ /* synthetic */ void b() {
        super.b();
    }

    public final void b(Runnable runnable) throws IllegalStateException {
        z();
        Preconditions.checkNotNull(runnable);
        bs bsVar = new bs(this, runnable, false, "Task exception on network thread");
        synchronized (this.h) {
            this.e.add(bsVar);
            if (this.c == null) {
                this.c = new bt(this, "Measurement Network", this.e);
                this.c.setUncaughtExceptionHandler(this.g);
                this.c.start();
            } else {
                this.c.a();
            }
        }
    }

    public final void c() {
        if (Thread.currentThread() != this.c) {
            throw new IllegalStateException("Call expected from network thread");
        }
    }

    public final void d() {
        if (Thread.currentThread() != this.b) {
            throw new IllegalStateException("Call expected from worker thread");
        }
    }

    /* access modifiers changed from: protected */
    public final boolean e() {
        return false;
    }

    public final boolean g() {
        return Thread.currentThread() == this.b;
    }

    /* access modifiers changed from: 0000 */
    public final ExecutorService h() {
        ExecutorService executorService;
        synchronized (this.h) {
            if (this.a == null) {
                ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, 30, TimeUnit.SECONDS, new ArrayBlockingQueue(100));
                this.a = threadPoolExecutor;
            }
            executorService = this.a;
        }
        return executorService;
    }

    public final /* bridge */ /* synthetic */ ag l() {
        return super.l();
    }

    public final /* bridge */ /* synthetic */ Clock m() {
        return super.m();
    }

    public final /* bridge */ /* synthetic */ Context n() {
        return super.n();
    }

    public final /* bridge */ /* synthetic */ ao o() {
        return super.o();
    }

    public final /* bridge */ /* synthetic */ fg p() {
        return super.p();
    }

    public final /* bridge */ /* synthetic */ bq q() {
        return super.q();
    }

    public final /* bridge */ /* synthetic */ aq r() {
        return super.r();
    }

    public final /* bridge */ /* synthetic */ bb s() {
        return super.s();
    }

    public final /* bridge */ /* synthetic */ w t() {
        return super.t();
    }

    public final /* bridge */ /* synthetic */ v u() {
        return super.u();
    }
}
