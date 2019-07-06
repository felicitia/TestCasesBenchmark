package com.salesforce.marketingcloud;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import com.salesforce.marketingcloud.c.f;
import com.salesforce.marketingcloud.d.h;
import com.salesforce.marketingcloud.location.k;
import com.salesforce.marketingcloud.messages.n;
import com.salesforce.marketingcloud.notifications.d;
import com.salesforce.marketingcloud.registration.g;
import java.util.ArrayList;
import java.util.List;

public final class c extends a {
    /* access modifiers changed from: private */
    public static final String c = j.a(c.class);
    private static final Object d = new Object();
    private static final List<C0163c> e = new ArrayList();
    private static Application f = null;
    /* access modifiers changed from: private */
    public static c g = null;
    private static volatile boolean h = false;
    private static volatile boolean i = false;
    private static volatile boolean j = true;
    k a;
    com.salesforce.marketingcloud.b.c b;
    private final b k;
    private d l;
    private List<g> m = new ArrayList();
    private h n;
    private f o;
    private com.salesforce.marketingcloud.messages.a.c p;
    private com.salesforce.marketingcloud.messages.c.c q;
    private g r;
    private d s;
    private com.salesforce.marketingcloud.messages.push.b t;
    private n u;
    private com.salesforce.marketingcloud.analytics.d v;
    private InitializationStatus w;

    public interface a {
        void a(@NonNull InitializationStatus initializationStatus);
    }

    public interface b {
        void a(@NonNull c cVar);
    }

    /* renamed from: com.salesforce.marketingcloud.c$c reason: collision with other inner class name */
    static abstract class C0163c {
        private final Handler a;
        /* access modifiers changed from: private */
        public volatile boolean b;
        private final Runnable c = new Runnable() {
            public void run() {
                synchronized (C0163c.this) {
                    if (!C0163c.this.b) {
                        C0163c.this.a();
                        C0163c.this.b = true;
                    }
                }
            }
        };
        private volatile boolean d;

        C0163c(Looper looper) {
            if (looper == null) {
                looper = Looper.myLooper() != null ? Looper.myLooper() : Looper.getMainLooper();
            }
            this.a = new Handler(looper);
        }

        /* access modifiers changed from: protected */
        public abstract void a();

        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0017, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void b() {
            /*
                r2 = this;
                monitor-enter(r2)
                boolean r0 = r2.b     // Catch:{ all -> 0x0018 }
                if (r0 != 0) goto L_0x0016
                boolean r0 = r2.d     // Catch:{ all -> 0x0018 }
                if (r0 == 0) goto L_0x000a
                goto L_0x0016
            L_0x000a:
                r0 = 1
                r2.d = r0     // Catch:{ all -> 0x0018 }
                android.os.Handler r0 = r2.a     // Catch:{ all -> 0x0018 }
                java.lang.Runnable r1 = r2.c     // Catch:{ all -> 0x0018 }
                r0.post(r1)     // Catch:{ all -> 0x0018 }
                monitor-exit(r2)     // Catch:{ all -> 0x0018 }
                return
            L_0x0016:
                monitor-exit(r2)     // Catch:{ all -> 0x0018 }
                return
            L_0x0018:
                r0 = move-exception
                monitor-exit(r2)     // Catch:{ all -> 0x0018 }
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.c.C0163c.b():void");
        }
    }

    private c(b bVar) {
        this.k = bVar;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x003a, code lost:
        return r2;
     */
    @android.support.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.salesforce.marketingcloud.c a() {
        /*
            boolean r0 = h
            if (r0 != 0) goto L_0x0010
            boolean r0 = i
            if (r0 != 0) goto L_0x0010
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "Call init"
            r0.<init>(r1)
            throw r0
        L_0x0010:
            java.lang.Object r0 = d
            monitor-enter(r0)
            boolean r1 = i     // Catch:{ all -> 0x0046 }
            if (r1 == 0) goto L_0x001b
            com.salesforce.marketingcloud.c r1 = g     // Catch:{ all -> 0x0046 }
            monitor-exit(r0)     // Catch:{ all -> 0x0046 }
            return r1
        L_0x001b:
            r1 = 0
        L_0x001c:
            boolean r2 = i     // Catch:{ all -> 0x003b }
            if (r2 != 0) goto L_0x002e
            boolean r2 = h     // Catch:{ all -> 0x003b }
            if (r2 == 0) goto L_0x002e
            java.lang.Object r2 = d     // Catch:{ InterruptedException -> 0x002c }
            r3 = 0
            r2.wait(r3)     // Catch:{ InterruptedException -> 0x002c }
            goto L_0x001c
        L_0x002c:
            r1 = 1
            goto L_0x001c
        L_0x002e:
            com.salesforce.marketingcloud.c r2 = g     // Catch:{ all -> 0x003b }
            if (r1 == 0) goto L_0x0039
            java.lang.Thread r1 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x0046 }
            r1.interrupt()     // Catch:{ all -> 0x0046 }
        L_0x0039:
            monitor-exit(r0)     // Catch:{ all -> 0x0046 }
            return r2
        L_0x003b:
            r2 = move-exception
            if (r1 == 0) goto L_0x0045
            java.lang.Thread r1 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x0046 }
            r1.interrupt()     // Catch:{ all -> 0x0046 }
        L_0x0045:
            throw r2     // Catch:{ all -> 0x0046 }
        L_0x0046:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0046 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.c.a():com.salesforce.marketingcloud.c");
    }

    public static void a(int i2) {
        j.a(i2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0067, code lost:
        return;
     */
    @android.support.annotation.MainThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(@android.support.annotation.NonNull final android.app.Application r6, @android.support.annotation.NonNull final com.salesforce.marketingcloud.b r7, final com.salesforce.marketingcloud.c.a r8) {
        /*
            java.lang.String r0 = c
            java.lang.String r1 = "~~ MarketingCloudSdk v%s init() ~~"
            r2 = 1
            java.lang.Object[] r3 = new java.lang.Object[r2]
            java.lang.String r4 = com.salesforce.marketingcloud.e.h.a()
            r5 = 0
            r3[r5] = r4
            com.salesforce.marketingcloud.j.a(r0, r1, r3)
            java.lang.String r0 = "Application cannot be null."
            com.salesforce.marketingcloud.e.f.a(r6, r0)
            java.lang.String r0 = "Config cannot be null."
            com.salesforce.marketingcloud.e.f.a(r7, r0)
            java.lang.String r0 = r7.b()
            java.lang.String r1 = r7.c()
            java.lang.String r3 = r7.d()
            com.salesforce.marketingcloud.j.a(r0, r1, r3)
            java.lang.Object r0 = d
            monitor-enter(r0)
            boolean r1 = i     // Catch:{ all -> 0x0088 }
            if (r1 != 0) goto L_0x0035
            boolean r1 = h     // Catch:{ all -> 0x0088 }
            if (r1 == 0) goto L_0x0068
        L_0x0035:
            com.salesforce.marketingcloud.c r1 = g     // Catch:{ all -> 0x0088 }
            if (r1 == 0) goto L_0x0068
            com.salesforce.marketingcloud.c r1 = g     // Catch:{ all -> 0x0088 }
            com.salesforce.marketingcloud.b r1 = r1.k     // Catch:{ all -> 0x0088 }
            boolean r1 = r7.equals(r1)     // Catch:{ all -> 0x0088 }
            if (r1 == 0) goto L_0x0068
            java.lang.String r6 = c     // Catch:{ all -> 0x0088 }
            java.lang.String r7 = "MarketingCloudSdk is already %s"
            java.lang.Object[] r1 = new java.lang.Object[r2]     // Catch:{ all -> 0x0088 }
            boolean r2 = i     // Catch:{ all -> 0x0088 }
            if (r2 == 0) goto L_0x0050
            java.lang.String r2 = "initialized"
            goto L_0x0052
        L_0x0050:
            java.lang.String r2 = "initializing"
        L_0x0052:
            r1[r5] = r2     // Catch:{ all -> 0x0088 }
            com.salesforce.marketingcloud.j.a(r6, r7, r1)     // Catch:{ all -> 0x0088 }
            boolean r6 = c()     // Catch:{ all -> 0x0088 }
            if (r6 == 0) goto L_0x0066
            if (r8 == 0) goto L_0x0066
            com.salesforce.marketingcloud.c r6 = g     // Catch:{ all -> 0x0088 }
            com.salesforce.marketingcloud.InitializationStatus r6 = r6.w     // Catch:{ all -> 0x0088 }
            r8.a(r6)     // Catch:{ all -> 0x0088 }
        L_0x0066:
            monitor-exit(r0)     // Catch:{ all -> 0x0088 }
            return
        L_0x0068:
            java.lang.String r1 = c     // Catch:{ all -> 0x0088 }
            java.lang.String r3 = "Starting initialization"
            java.lang.Object[] r4 = new java.lang.Object[r5]     // Catch:{ all -> 0x0088 }
            com.salesforce.marketingcloud.j.a(r1, r3, r4)     // Catch:{ all -> 0x0088 }
            i = r5     // Catch:{ all -> 0x0088 }
            h = r2     // Catch:{ all -> 0x0088 }
            j = r2     // Catch:{ all -> 0x0088 }
            f = r6     // Catch:{ all -> 0x0088 }
            java.lang.Thread r1 = new java.lang.Thread     // Catch:{ all -> 0x0088 }
            com.salesforce.marketingcloud.c$1 r2 = new com.salesforce.marketingcloud.c$1     // Catch:{ all -> 0x0088 }
            r2.<init>(r6, r7, r8)     // Catch:{ all -> 0x0088 }
            r1.<init>(r2)     // Catch:{ all -> 0x0088 }
            r1.start()     // Catch:{ all -> 0x0088 }
            monitor-exit(r0)     // Catch:{ all -> 0x0088 }
            return
        L_0x0088:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0088 }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.c.a(android.app.Application, com.salesforce.marketingcloud.b, com.salesforce.marketingcloud.c$a):void");
    }

    private void a(InitializationStatus initializationStatus) {
        this.w = initializationStatus;
    }

    public static void a(a aVar) {
        j.a(aVar);
    }

    public static void a(@NonNull b bVar) {
        a(bVar, null);
    }

    public static void a(@NonNull final b bVar, Looper looper) {
        AnonymousClass2 r0 = new C0163c(looper) {
            /* access modifiers changed from: protected */
            public void a() {
                if (bVar != null) {
                    bVar.a(c.g);
                }
            }
        };
        synchronized (e) {
            if (j) {
                e.add(r0);
            } else {
                r0.b();
            }
        }
    }

    private void a(boolean z) {
        for (int size = this.m.size() - 1; size >= 0; size--) {
            try {
                ((g) this.m.get(size)).a(z);
            } catch (Exception e2) {
                j.c(c, e2, "Error encountered tearing down component.", new Object[0]);
            }
        }
        this.m.clear();
        if (this.n != null) {
            try {
                this.n.f();
            } catch (Exception e3) {
                j.c(c, e3, "Error encountered tearing down storage.", new Object[0]);
            }
            this.n = null;
        }
        e.clear();
        i = false;
        j = true;
    }

    private void b(boolean z) {
        a(z);
        h = false;
    }

    public static boolean b() {
        return h;
    }

    /* access modifiers changed from: private */
    public static void c(@NonNull Application application, @NonNull b bVar, a aVar) {
        j.a(c, "executeInit %s", bVar);
        synchronized (d) {
            if (g != null) {
                g.a(bVar.a(g.k));
            }
            g = new c(bVar);
            InitializationStatus j2 = g.j();
            j.b(c, "MarketingCloudSdk init finished with status: %s", j2);
            i = j2.n();
            h = false;
            if (i) {
                g.a(j2);
                g.l.a((a) g);
                synchronized (e) {
                    j = false;
                    j.a(c, "Delivering queued SDK requests to %s listeners", Integer.valueOf(e.size()));
                    if (!e.isEmpty()) {
                        for (C0163c b2 : e) {
                            b2.b();
                        }
                        e.clear();
                    }
                }
            } else {
                g.b(false);
                g = null;
                synchronized (e) {
                    e.clear();
                }
            }
            d.notifyAll();
            if (aVar != null) {
                aVar.a(j2);
            }
        }
    }

    public static boolean c() {
        return i && g != null;
    }

    private InitializationStatus j() {
        com.salesforce.marketingcloud.InitializationStatus.a aVar;
        if (com.salesforce.marketingcloud.e.b.a()) {
            return InitializationStatus.b();
        }
        com.salesforce.marketingcloud.InitializationStatus.a a2 = InitializationStatus.a();
        try {
            String a3 = com.salesforce.marketingcloud.e.c.a(f);
            try {
                this.n = new h(f, new com.salesforce.marketingcloud.e.a(f, this.k.b(), this.k.c(), a3), this.k.b(), this.k.c());
                this.n.a(a2);
            } catch (Throwable th) {
                Throwable th2 = th;
                j.a(c, th2, "Unable to initialize SDK storage.", new Object[0]);
                a2.a(th2);
            }
            if (!a2.g()) {
                g.a(this.k, f, a3);
                return a2.i();
            }
            this.b = new com.salesforce.marketingcloud.b.c(f);
            this.o = new f(f, this.n.e());
            d dVar = new d(a3, this.k, this.n.e(), this.o, this.b);
            this.l = dVar;
            com.salesforce.marketingcloud.a.b bVar = new com.salesforce.marketingcloud.a.b(f, this.n, this.b);
            this.a = k.a((Context) f, this.k);
            com.salesforce.marketingcloud.proximity.g a4 = com.salesforce.marketingcloud.proximity.g.a((Context) f, this.k);
            com.salesforce.marketingcloud.analytics.k kVar = new com.salesforce.marketingcloud.analytics.k(f, this.k, this.n, a3, bVar, this.b, this.o);
            this.v = kVar;
            this.s = d.a(f, this.n, this.k.l(), this.k.m(), this.k.n(), this.k.j(), this.k.k(), this.k.r(), this.k.p(), this.k.o(), this.k.q(), kVar);
            com.salesforce.marketingcloud.messages.c.c cVar = r5;
            com.salesforce.marketingcloud.messages.c.c cVar2 = new com.salesforce.marketingcloud.messages.c.c(this.k, this.n, a3, this.b, bVar, this.o, kVar);
            this.q = cVar;
            Application application = f;
            b bVar2 = this.k;
            h hVar = this.n;
            k kVar2 = this.a;
            com.salesforce.marketingcloud.b.c cVar3 = this.b;
            r5 = r5;
            d dVar2 = this.s;
            com.salesforce.marketingcloud.analytics.k kVar3 = kVar;
            com.salesforce.marketingcloud.InitializationStatus.a aVar2 = a2;
            com.salesforce.marketingcloud.proximity.g gVar = a4;
            com.salesforce.marketingcloud.a.b bVar3 = bVar;
            try {
                n nVar = new n(application, bVar2, hVar, a3, kVar2, a4, cVar3, bVar, this.o, dVar2, kVar3);
                this.u = nVar;
                com.salesforce.marketingcloud.messages.push.b bVar4 = new com.salesforce.marketingcloud.messages.push.b(f, this.n, this.s, bVar3, this.k.d());
                this.t = bVar4;
                g gVar2 = new g(f, this.k, this.n, a3, this.b, bVar3, this.o, this.t, this.u);
                this.r = gVar2;
                this.m.add(this.b);
                this.m.add(com.salesforce.marketingcloud.b.d.a(f));
                this.m.add(this.o);
                this.m.add(this.l);
                this.m.add(bVar3);
                this.m.add(this.a);
                this.m.add(gVar);
                this.m.add(kVar3);
                this.m.add(this.q);
                this.m.add(this.s);
                this.m.add(this.u);
                this.m.add(this.t);
                this.m.add(this.r);
                this.p = new com.salesforce.marketingcloud.messages.a.c(this.q);
                int a5 = this.l.a();
                j.a(c, "Initializing all components with control channel flag [%d]", Integer.valueOf(a5));
                for (g gVar3 : this.m) {
                    j.a(c, "init called for %s", gVar3.b());
                    if (gVar3 instanceof h) {
                        aVar = aVar2;
                        try {
                            ((h) gVar3).a(aVar, a5);
                        } catch (Exception e2) {
                            e = e2;
                            Throwable th3 = e;
                            aVar.a(th3);
                            j.c(c, th3, "Something wrong with internal init", new Object[0]);
                            return aVar.i();
                        }
                    } else {
                        aVar = aVar2;
                        if (gVar3 instanceof i) {
                            ((i) gVar3).a(aVar);
                        }
                    }
                    aVar.a(gVar3);
                    aVar2 = aVar;
                }
                aVar = aVar2;
            } catch (Exception e3) {
                e = e3;
                aVar = aVar2;
                Throwable th32 = e;
                aVar.a(th32);
                j.c(c, th32, "Something wrong with internal init", new Object[0]);
                return aVar.i();
            }
            return aVar.i();
        } catch (Exception e4) {
            e = e4;
            aVar = a2;
            Throwable th322 = e;
            aVar.a(th322);
            j.c(c, th322, "Something wrong with internal init", new Object[0]);
            return aVar.i();
        }
    }

    /* access modifiers changed from: 0000 */
    @RestrictTo({Scope.LIBRARY})
    public void b(int i2) {
        for (int size = this.m.size() - 1; size >= 0; size--) {
            try {
                g gVar = (g) this.m.get(size);
                if (gVar instanceof h) {
                    ((h) gVar).a(i2);
                }
            } catch (Exception e2) {
                j.c(c, e2, "Error encountered during control channel init.", new Object[0]);
            }
        }
    }

    @NonNull
    public b d() {
        return this.k;
    }

    @NonNull
    public com.salesforce.marketingcloud.messages.f e() {
        return this.u;
    }

    @NonNull
    public com.salesforce.marketingcloud.messages.push.a f() {
        return this.t;
    }

    @NonNull
    public com.salesforce.marketingcloud.registration.d g() {
        return this.r;
    }
}
