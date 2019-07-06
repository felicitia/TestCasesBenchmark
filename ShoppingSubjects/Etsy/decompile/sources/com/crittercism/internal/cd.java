package com.crittercism.internal;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;
import com.crittercism.internal.ap.c;
import com.crittercism.internal.ap.d;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public final class cd implements com.crittercism.internal.ay.a {
    final ScheduledExecutorService a;
    final ca b;
    ay c;
    public a d;
    volatile boolean e = false;
    public volatile ScheduledFuture f;
    public volatile Future g;
    public volatile Future h;
    public boolean i = true;
    public ConnectivityManager j;
    public Object k;
    private as l;
    private final ExecutorService m;
    private ce n;
    private long o;
    private boolean p = false;
    private boolean q = true;
    private long r = 0;
    private ap s;
    private d t;
    private String u;

    public interface a {
        void a(cb cbVar);
    }

    class b implements c {
        private com.crittercism.internal.ap.a b;
        private d c;

        public b(com.crittercism.internal.ap.a aVar, d dVar) {
            this.b = aVar;
            this.c = dVar;
        }

        public final void a(ap apVar, String str) {
            if (this.b.a().equals(str)) {
                cd.this.b(((Boolean) apVar.a(this.b)).booleanValue());
                return;
            }
            if (this.c.a().equals(str)) {
                cd.this.a(((Long) apVar.a(this.c)).longValue(), TimeUnit.MILLISECONDS);
            }
        }
    }

    public cd(as asVar, ScheduledExecutorService scheduledExecutorService, ExecutorService executorService, ca caVar, ay ayVar, ce ceVar, String str, ap apVar, com.crittercism.internal.ap.a aVar, d dVar, d dVar2) {
        this.l = asVar;
        this.a = scheduledExecutorService;
        this.m = executorService;
        this.b = caVar;
        this.c = ayVar;
        this.n = ceVar;
        this.c.a((com.crittercism.internal.ay.a) this);
        this.a.execute(new Runnable() {
            public final void run() {
                if (cd.this.c.c()) {
                    cd.this.e = true;
                }
            }
        });
        this.s = apVar;
        this.t = dVar2;
        this.r = ((Long) apVar.a(dVar2)).longValue();
        this.o = ((Long) apVar.a(dVar)).longValue();
        this.p = ((Boolean) apVar.a(aVar)).booleanValue();
        this.u = str;
        apVar.as.add(new b(aVar, dVar));
    }

    public final void a() {
        this.e = true;
        b();
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Can't wrap try/catch for region: R(6:22|23|(2:26|24)|34|27|28) */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        r0 = r0.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0055, code lost:
        if (r0.hasNext() != false) goto L_0x0057;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0057, code lost:
        r5.c.a(((com.crittercism.internal.bi) r0.next()).f());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0068, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x006a, code lost:
        return;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:22:0x004d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void c() {
        /*
            r5 = this;
            monitor-enter(r5)
            boolean r0 = r5.q     // Catch:{ all -> 0x006b }
            if (r0 != 0) goto L_0x0069
            boolean r0 = r5.d()     // Catch:{ all -> 0x006b }
            if (r0 != 0) goto L_0x000c
            goto L_0x0069
        L_0x000c:
            long r0 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x006b }
            com.crittercism.internal.ap r2 = r5.s     // Catch:{ all -> 0x006b }
            com.crittercism.internal.ap$d r3 = r5.t     // Catch:{ all -> 0x006b }
            java.lang.Long r4 = java.lang.Long.valueOf(r0)     // Catch:{ all -> 0x006b }
            r2.a(r3, r4)     // Catch:{ all -> 0x006b }
            r5.r = r0     // Catch:{ all -> 0x006b }
            com.crittercism.internal.ay r0 = r5.c     // Catch:{ all -> 0x006b }
            java.util.List r0 = r0.d()     // Catch:{ all -> 0x006b }
            boolean r1 = r5.e     // Catch:{ all -> 0x006b }
            r2 = 0
            r5.e = r2     // Catch:{ all -> 0x006b }
            int r2 = r0.size()     // Catch:{ all -> 0x006b }
            if (r2 != 0) goto L_0x0030
            monitor-exit(r5)
            return
        L_0x0030:
            com.crittercism.internal.ce r2 = r5.n     // Catch:{ IOException -> 0x004d }
            com.crittercism.internal.as r3 = r5.l     // Catch:{ IOException -> 0x004d }
            com.crittercism.internal.bz r2 = r2.a(r3, r0)     // Catch:{ IOException -> 0x004d }
            if (r2 != 0) goto L_0x003e
            r5.e = r1     // Catch:{ all -> 0x006b }
            monitor-exit(r5)
            return
        L_0x003e:
            java.util.concurrent.ExecutorService r1 = r5.m     // Catch:{ all -> 0x006b }
            com.crittercism.internal.cd$3 r3 = new com.crittercism.internal.cd$3     // Catch:{ all -> 0x006b }
            r3.<init>(r2, r0)     // Catch:{ all -> 0x006b }
            java.util.concurrent.Future r0 = r1.submit(r3)     // Catch:{ all -> 0x006b }
            r5.g = r0     // Catch:{ all -> 0x006b }
            monitor-exit(r5)
            return
        L_0x004d:
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x006b }
        L_0x0051:
            boolean r1 = r0.hasNext()     // Catch:{ all -> 0x006b }
            if (r1 == 0) goto L_0x0067
            java.lang.Object r1 = r0.next()     // Catch:{ all -> 0x006b }
            com.crittercism.internal.bi r1 = (com.crittercism.internal.bi) r1     // Catch:{ all -> 0x006b }
            com.crittercism.internal.ay r2 = r5.c     // Catch:{ all -> 0x006b }
            java.lang.String r1 = r1.f()     // Catch:{ all -> 0x006b }
            r2.a(r1)     // Catch:{ all -> 0x006b }
            goto L_0x0051
        L_0x0067:
            monitor-exit(r5)
            return
        L_0x0069:
            monitor-exit(r5)
            return
        L_0x006b:
            r0 = move-exception
            monitor-exit(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.crittercism.internal.cd.c():void");
    }

    private boolean d() {
        if (this.j == null) {
            return true;
        }
        NetworkInfo activeNetworkInfo = this.j.getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return false;
        }
        int type = activeNetworkInfo.getType();
        if (!activeNetworkInfo.isConnected() || (!this.i && type != 1)) {
            return false;
        }
        return true;
    }

    public final synchronized void a(long j2, TimeUnit timeUnit) {
        this.o = timeUnit.toMillis(j2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0011, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void a(boolean r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.q     // Catch:{ all -> 0x0012 }
            if (r0 != r2) goto L_0x0007
            monitor-exit(r1)
            return
        L_0x0007:
            r1.q = r2     // Catch:{ all -> 0x0012 }
            boolean r2 = r1.q     // Catch:{ all -> 0x0012 }
            if (r2 != 0) goto L_0x0010
            r1.b()     // Catch:{ all -> 0x0012 }
        L_0x0010:
            monitor-exit(r1)
            return
        L_0x0012:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.crittercism.internal.cd.a(boolean):void");
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0011, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void b(boolean r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.p     // Catch:{ all -> 0x0012 }
            if (r0 != r2) goto L_0x0007
            monitor-exit(r1)
            return
        L_0x0007:
            r1.p = r2     // Catch:{ all -> 0x0012 }
            boolean r2 = r1.p     // Catch:{ all -> 0x0012 }
            if (r2 == 0) goto L_0x0010
            r1.b()     // Catch:{ all -> 0x0012 }
        L_0x0010:
            monitor-exit(r1)
            return
        L_0x0012:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.crittercism.internal.cd.b(boolean):void");
    }

    private synchronized long e() {
        long j2;
        j2 = this.o;
        long currentTimeMillis = System.currentTimeMillis() - this.r;
        if (currentTimeMillis > 0) {
            long j3 = j2 - currentTimeMillis;
            j2 = j3 < 0 ? 0 : j3;
        }
        return j2;
    }

    private static boolean a(@Nullable Future future) {
        return future == null || future.isDone();
    }

    public final String toString() {
        return this.u;
    }

    public final synchronized Future b() {
        boolean z = !a((Future) this.f);
        boolean z2 = !a(this.g);
        if (this.p && !this.q && this.e && d() && !z) {
            if (!z2) {
                try {
                    this.f = this.a.schedule(new Runnable() {
                        public final void run() {
                            cd.this.c();
                            cd.this.f = null;
                        }
                    }, e(), TimeUnit.MILLISECONDS);
                } catch (RejectedExecutionException e2) {
                    cm.a("Unable to schedule sending data", (Throwable) e2);
                }
                return this.f;
            }
        }
        return null;
    }
}
