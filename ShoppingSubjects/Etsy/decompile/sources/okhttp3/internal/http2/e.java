package okhttp3.internal.http2;

import android.support.v4.internal.view.SupportMenu;
import java.io.Closeable;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okio.ByteString;

/* compiled from: Http2Connection */
public final class e implements Closeable {
    static final /* synthetic */ boolean r = true;
    /* access modifiers changed from: private */
    public static final ExecutorService s;
    final boolean a;
    final b b;
    final Map<Integer, g> c = new LinkedHashMap();
    final String d;
    int e;
    int f;
    boolean g;
    final j h;
    long i = 0;
    long j;
    k k = new k();
    final k l = new k();
    boolean m = false;
    final Socket n;
    final h o;
    final d p;
    final Set<Integer> q = new LinkedHashSet();
    /* access modifiers changed from: private */
    public final ScheduledExecutorService t;
    private final ExecutorService u;
    /* access modifiers changed from: private */
    public boolean v;

    /* compiled from: Http2Connection */
    public static class a {
        Socket a;
        String b;
        okio.e c;
        okio.d d;
        b e = b.f;
        j f = j.a;
        boolean g;
        int h;

        public a(boolean z) {
            this.g = z;
        }

        public a a(Socket socket, String str, okio.e eVar, okio.d dVar) {
            this.a = socket;
            this.b = str;
            this.c = eVar;
            this.d = dVar;
            return this;
        }

        public a a(b bVar) {
            this.e = bVar;
            return this;
        }

        public a a(int i) {
            this.h = i;
            return this;
        }

        public e a() {
            return new e(this);
        }
    }

    /* compiled from: Http2Connection */
    public static abstract class b {
        public static final b f = new b() {
            public void a(g gVar) throws IOException {
                gVar.a(ErrorCode.REFUSED_STREAM);
            }
        };

        public void a(e eVar) {
        }

        public abstract void a(g gVar) throws IOException;
    }

    /* compiled from: Http2Connection */
    final class c extends okhttp3.internal.b {
        final boolean a;
        final int c;
        final int d;

        c(boolean z, int i, int i2) {
            super("OkHttp %s ping %08x%08x", e.this.d, Integer.valueOf(i), Integer.valueOf(i2));
            this.a = z;
            this.c = i;
            this.d = i2;
        }

        public void c() {
            e.this.a(this.a, this.c, this.d);
        }
    }

    /* compiled from: Http2Connection */
    class d extends okhttp3.internal.b implements b {
        final f a;

        public void a() {
        }

        public void a(int i, int i2, int i3, boolean z) {
        }

        d(f fVar) {
            super("OkHttp %s", e.this.d);
            this.a = fVar;
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Can't wrap try/catch for region: R(5:12|11|14|15|(7:16|17|18|19|20|21|23)) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x001c, code lost:
            r2 = th;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x001e */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void c() {
            /*
                r5 = this;
                okhttp3.internal.http2.ErrorCode r0 = okhttp3.internal.http2.ErrorCode.INTERNAL_ERROR
                okhttp3.internal.http2.ErrorCode r1 = okhttp3.internal.http2.ErrorCode.INTERNAL_ERROR
                okhttp3.internal.http2.f r2 = r5.a     // Catch:{ IOException -> 0x001e }
                r2.a(r5)     // Catch:{ IOException -> 0x001e }
            L_0x0009:
                okhttp3.internal.http2.f r2 = r5.a     // Catch:{ IOException -> 0x001e }
                r3 = 0
                boolean r2 = r2.a(r3, r5)     // Catch:{ IOException -> 0x001e }
                if (r2 == 0) goto L_0x0013
                goto L_0x0009
            L_0x0013:
                okhttp3.internal.http2.ErrorCode r2 = okhttp3.internal.http2.ErrorCode.NO_ERROR     // Catch:{ IOException -> 0x001e }
                okhttp3.internal.http2.ErrorCode r0 = okhttp3.internal.http2.ErrorCode.CANCEL     // Catch:{ IOException -> 0x001a }
                okhttp3.internal.http2.e r1 = okhttp3.internal.http2.e.this     // Catch:{ IOException -> 0x0027 }
                goto L_0x0024
            L_0x001a:
                r0 = r2
                goto L_0x001e
            L_0x001c:
                r2 = move-exception
                goto L_0x0031
            L_0x001e:
                okhttp3.internal.http2.ErrorCode r2 = okhttp3.internal.http2.ErrorCode.PROTOCOL_ERROR     // Catch:{ all -> 0x001c }
                okhttp3.internal.http2.ErrorCode r0 = okhttp3.internal.http2.ErrorCode.PROTOCOL_ERROR     // Catch:{ all -> 0x002d }
                okhttp3.internal.http2.e r1 = okhttp3.internal.http2.e.this     // Catch:{ IOException -> 0x0027 }
            L_0x0024:
                r1.a(r2, r0)     // Catch:{ IOException -> 0x0027 }
            L_0x0027:
                okhttp3.internal.http2.f r0 = r5.a
                okhttp3.internal.c.a(r0)
                return
            L_0x002d:
                r0 = move-exception
                r4 = r2
                r2 = r0
                r0 = r4
            L_0x0031:
                okhttp3.internal.http2.e r3 = okhttp3.internal.http2.e.this     // Catch:{ IOException -> 0x0036 }
                r3.a(r0, r1)     // Catch:{ IOException -> 0x0036 }
            L_0x0036:
                okhttp3.internal.http2.f r0 = r5.a
                okhttp3.internal.c.a(r0)
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.e.d.c():void");
        }

        public void a(boolean z, int i, okio.e eVar, int i2) throws IOException {
            if (e.this.c(i)) {
                e.this.a(i, eVar, i2, z);
                return;
            }
            g a2 = e.this.a(i);
            if (a2 == null) {
                e.this.a(i, ErrorCode.PROTOCOL_ERROR);
                eVar.i((long) i2);
                return;
            }
            a2.a(eVar, i2);
            if (z) {
                a2.i();
            }
        }

        /* JADX INFO: used method not loaded: okhttp3.internal.http2.g.a(java.util.List):null, types can be incorrect */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x0071, code lost:
            r0.a((java.util.List) r13);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x0074, code lost:
            if (r10 == false) goto L_0x0079;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x0076, code lost:
            r0.i();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x0079, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a(boolean r10, int r11, int r12, java.util.List<okhttp3.internal.http2.a> r13) {
            /*
                r9 = this;
                okhttp3.internal.http2.e r12 = okhttp3.internal.http2.e.this
                boolean r12 = r12.c(r11)
                if (r12 == 0) goto L_0x000e
                okhttp3.internal.http2.e r12 = okhttp3.internal.http2.e.this
                r12.a(r11, r13, r10)
                return
            L_0x000e:
                okhttp3.internal.http2.e r12 = okhttp3.internal.http2.e.this
                monitor-enter(r12)
                okhttp3.internal.http2.e r0 = okhttp3.internal.http2.e.this     // Catch:{ all -> 0x007a }
                okhttp3.internal.http2.g r0 = r0.a(r11)     // Catch:{ all -> 0x007a }
                if (r0 != 0) goto L_0x0070
                okhttp3.internal.http2.e r0 = okhttp3.internal.http2.e.this     // Catch:{ all -> 0x007a }
                boolean r0 = r0.g     // Catch:{ all -> 0x007a }
                if (r0 == 0) goto L_0x0021
                monitor-exit(r12)     // Catch:{ all -> 0x007a }
                return
            L_0x0021:
                okhttp3.internal.http2.e r0 = okhttp3.internal.http2.e.this     // Catch:{ all -> 0x007a }
                int r0 = r0.e     // Catch:{ all -> 0x007a }
                if (r11 > r0) goto L_0x0029
                monitor-exit(r12)     // Catch:{ all -> 0x007a }
                return
            L_0x0029:
                int r0 = r11 % 2
                okhttp3.internal.http2.e r1 = okhttp3.internal.http2.e.this     // Catch:{ all -> 0x007a }
                int r1 = r1.f     // Catch:{ all -> 0x007a }
                r2 = 2
                int r1 = r1 % r2
                if (r0 != r1) goto L_0x0035
                monitor-exit(r12)     // Catch:{ all -> 0x007a }
                return
            L_0x0035:
                okhttp3.internal.http2.g r0 = new okhttp3.internal.http2.g     // Catch:{ all -> 0x007a }
                okhttp3.internal.http2.e r5 = okhttp3.internal.http2.e.this     // Catch:{ all -> 0x007a }
                r6 = 0
                r3 = r0
                r4 = r11
                r7 = r10
                r8 = r13
                r3.<init>(r4, r5, r6, r7, r8)     // Catch:{ all -> 0x007a }
                okhttp3.internal.http2.e r10 = okhttp3.internal.http2.e.this     // Catch:{ all -> 0x007a }
                r10.e = r11     // Catch:{ all -> 0x007a }
                okhttp3.internal.http2.e r10 = okhttp3.internal.http2.e.this     // Catch:{ all -> 0x007a }
                java.util.Map<java.lang.Integer, okhttp3.internal.http2.g> r10 = r10.c     // Catch:{ all -> 0x007a }
                java.lang.Integer r13 = java.lang.Integer.valueOf(r11)     // Catch:{ all -> 0x007a }
                r10.put(r13, r0)     // Catch:{ all -> 0x007a }
                java.util.concurrent.ExecutorService r10 = okhttp3.internal.http2.e.s     // Catch:{ all -> 0x007a }
                okhttp3.internal.http2.e$d$1 r13 = new okhttp3.internal.http2.e$d$1     // Catch:{ all -> 0x007a }
                java.lang.String r1 = "OkHttp %s stream %d"
                java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x007a }
                r3 = 0
                okhttp3.internal.http2.e r4 = okhttp3.internal.http2.e.this     // Catch:{ all -> 0x007a }
                java.lang.String r4 = r4.d     // Catch:{ all -> 0x007a }
                r2[r3] = r4     // Catch:{ all -> 0x007a }
                r3 = 1
                java.lang.Integer r11 = java.lang.Integer.valueOf(r11)     // Catch:{ all -> 0x007a }
                r2[r3] = r11     // Catch:{ all -> 0x007a }
                r13.<init>(r1, r2, r0)     // Catch:{ all -> 0x007a }
                r10.execute(r13)     // Catch:{ all -> 0x007a }
                monitor-exit(r12)     // Catch:{ all -> 0x007a }
                return
            L_0x0070:
                monitor-exit(r12)     // Catch:{ all -> 0x007a }
                r0.a(r13)
                if (r10 == 0) goto L_0x0079
                r0.i()
            L_0x0079:
                return
            L_0x007a:
                r10 = move-exception
                monitor-exit(r12)     // Catch:{ all -> 0x007a }
                throw r10
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.e.d.a(boolean, int, int, java.util.List):void");
        }

        public void a(int i, ErrorCode errorCode) {
            if (e.this.c(i)) {
                e.this.c(i, errorCode);
                return;
            }
            g b = e.this.b(i);
            if (b != null) {
                b.c(errorCode);
            }
        }

        public void a(boolean z, k kVar) {
            g[] gVarArr;
            long j;
            int i;
            synchronized (e.this) {
                int d = e.this.l.d();
                if (z) {
                    e.this.l.a();
                }
                e.this.l.a(kVar);
                a(kVar);
                int d2 = e.this.l.d();
                gVarArr = null;
                if (d2 == -1 || d2 == d) {
                    j = 0;
                } else {
                    j = (long) (d2 - d);
                    if (!e.this.m) {
                        e.this.a(j);
                        e.this.m = true;
                    }
                    if (!e.this.c.isEmpty()) {
                        gVarArr = (g[]) e.this.c.values().toArray(new g[e.this.c.size()]);
                    }
                }
                e.s.execute(new okhttp3.internal.b("OkHttp %s settings", e.this.d) {
                    public void c() {
                        e.this.b.a(e.this);
                    }
                });
            }
            if (gVarArr != null && j != 0) {
                for (g gVar : gVarArr) {
                    synchronized (gVar) {
                        gVar.a(j);
                    }
                }
            }
        }

        private void a(final k kVar) {
            try {
                e.this.t.execute(new okhttp3.internal.b("OkHttp %s ACK Settings", new Object[]{e.this.d}) {
                    public void c() {
                        try {
                            e.this.o.a(kVar);
                        } catch (IOException unused) {
                            e.this.f();
                        }
                    }
                });
            } catch (RejectedExecutionException unused) {
            }
        }

        public void a(boolean z, int i, int i2) {
            if (z) {
                synchronized (e.this) {
                    e.this.v = false;
                    e.this.notifyAll();
                }
                return;
            }
            try {
                e.this.t.execute(new c(true, i, i2));
            } catch (RejectedExecutionException unused) {
            }
        }

        public void a(int i, ErrorCode errorCode, ByteString byteString) {
            g[] gVarArr;
            byteString.size();
            synchronized (e.this) {
                gVarArr = (g[]) e.this.c.values().toArray(new g[e.this.c.size()]);
                e.this.g = true;
            }
            for (g gVar : gVarArr) {
                if (gVar.a() > i && gVar.c()) {
                    gVar.c(ErrorCode.REFUSED_STREAM);
                    e.this.b(gVar.a());
                }
            }
        }

        public void a(int i, long j) {
            if (i == 0) {
                synchronized (e.this) {
                    e.this.j += j;
                    e.this.notifyAll();
                }
                return;
            }
            g a2 = e.this.a(i);
            if (a2 != null) {
                synchronized (a2) {
                    a2.a(j);
                }
            }
        }

        public void a(int i, int i2, List<a> list) {
            e.this.a(i2, list);
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean c(int i2) {
        return i2 != 0 && (i2 & 1) == 0;
    }

    static {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), okhttp3.internal.c.a("OkHttp Http2Connection", true));
        s = threadPoolExecutor;
    }

    e(a aVar) {
        a aVar2 = aVar;
        this.h = aVar2.f;
        this.a = aVar2.g;
        this.b = aVar2.e;
        this.f = aVar2.g ? 1 : 2;
        if (aVar2.g) {
            this.f += 2;
        }
        if (aVar2.g) {
            this.k.a(7, 16777216);
        }
        this.d = aVar2.b;
        this.t = new ScheduledThreadPoolExecutor(1, okhttp3.internal.c.a(okhttp3.internal.c.a("OkHttp %s Writer", this.d), false));
        if (aVar2.h != 0) {
            this.t.scheduleAtFixedRate(new c(false, 0, 0), (long) aVar2.h, (long) aVar2.h, TimeUnit.MILLISECONDS);
        }
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), okhttp3.internal.c.a(okhttp3.internal.c.a("OkHttp %s Push Observer", this.d), true));
        this.u = threadPoolExecutor;
        this.l.a(7, SupportMenu.USER_MASK);
        this.l.a(5, 16384);
        this.j = (long) this.l.d();
        this.n = aVar2.a;
        this.o = new h(aVar2.d, this.a);
        this.p = new d(new f(aVar2.c, this.a));
    }

    /* access modifiers changed from: 0000 */
    public synchronized g a(int i2) {
        return (g) this.c.get(Integer.valueOf(i2));
    }

    /* access modifiers changed from: 0000 */
    public synchronized g b(int i2) {
        g gVar;
        gVar = (g) this.c.remove(Integer.valueOf(i2));
        notifyAll();
        return gVar;
    }

    public synchronized int a() {
        return this.l.c(Integer.MAX_VALUE);
    }

    public g a(List<a> list, boolean z) throws IOException {
        return b(0, list, z);
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0049  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private okhttp3.internal.http2.g b(int r11, java.util.List<okhttp3.internal.http2.a> r12, boolean r13) throws java.io.IOException {
        /*
            r10 = this;
            r6 = r13 ^ 1
            r4 = 0
            okhttp3.internal.http2.h r7 = r10.o
            monitor-enter(r7)
            monitor-enter(r10)     // Catch:{ all -> 0x0078 }
            int r0 = r10.f     // Catch:{ all -> 0x0075 }
            r1 = 1073741823(0x3fffffff, float:1.9999999)
            if (r0 <= r1) goto L_0x0013
            okhttp3.internal.http2.ErrorCode r0 = okhttp3.internal.http2.ErrorCode.REFUSED_STREAM     // Catch:{ all -> 0x0075 }
            r10.a(r0)     // Catch:{ all -> 0x0075 }
        L_0x0013:
            boolean r0 = r10.g     // Catch:{ all -> 0x0075 }
            if (r0 == 0) goto L_0x001d
            okhttp3.internal.http2.ConnectionShutdownException r11 = new okhttp3.internal.http2.ConnectionShutdownException     // Catch:{ all -> 0x0075 }
            r11.<init>()     // Catch:{ all -> 0x0075 }
            throw r11     // Catch:{ all -> 0x0075 }
        L_0x001d:
            int r8 = r10.f     // Catch:{ all -> 0x0075 }
            int r0 = r10.f     // Catch:{ all -> 0x0075 }
            int r0 = r0 + 2
            r10.f = r0     // Catch:{ all -> 0x0075 }
            okhttp3.internal.http2.g r9 = new okhttp3.internal.http2.g     // Catch:{ all -> 0x0075 }
            r0 = r9
            r1 = r8
            r2 = r10
            r3 = r6
            r5 = r12
            r0.<init>(r1, r2, r3, r4, r5)     // Catch:{ all -> 0x0075 }
            if (r13 == 0) goto L_0x0042
            long r0 = r10.j     // Catch:{ all -> 0x0075 }
            r2 = 0
            int r13 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r13 == 0) goto L_0x0042
            long r0 = r9.b     // Catch:{ all -> 0x0075 }
            int r13 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r13 != 0) goto L_0x0040
            goto L_0x0042
        L_0x0040:
            r13 = 0
            goto L_0x0043
        L_0x0042:
            r13 = 1
        L_0x0043:
            boolean r0 = r9.b()     // Catch:{ all -> 0x0075 }
            if (r0 == 0) goto L_0x0052
            java.util.Map<java.lang.Integer, okhttp3.internal.http2.g> r0 = r10.c     // Catch:{ all -> 0x0075 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x0075 }
            r0.put(r1, r9)     // Catch:{ all -> 0x0075 }
        L_0x0052:
            monitor-exit(r10)     // Catch:{ all -> 0x0075 }
            if (r11 != 0) goto L_0x005b
            okhttp3.internal.http2.h r0 = r10.o     // Catch:{ all -> 0x0078 }
            r0.a(r6, r8, r11, r12)     // Catch:{ all -> 0x0078 }
            goto L_0x006c
        L_0x005b:
            boolean r0 = r10.a     // Catch:{ all -> 0x0078 }
            if (r0 == 0) goto L_0x0067
            java.lang.IllegalArgumentException r11 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x0078 }
            java.lang.String r12 = "client streams shouldn't have associated stream IDs"
            r11.<init>(r12)     // Catch:{ all -> 0x0078 }
            throw r11     // Catch:{ all -> 0x0078 }
        L_0x0067:
            okhttp3.internal.http2.h r0 = r10.o     // Catch:{ all -> 0x0078 }
            r0.a(r11, r8, r12)     // Catch:{ all -> 0x0078 }
        L_0x006c:
            monitor-exit(r7)     // Catch:{ all -> 0x0078 }
            if (r13 == 0) goto L_0x0074
            okhttp3.internal.http2.h r11 = r10.o
            r11.b()
        L_0x0074:
            return r9
        L_0x0075:
            r11 = move-exception
            monitor-exit(r10)     // Catch:{ all -> 0x0075 }
            throw r11     // Catch:{ all -> 0x0078 }
        L_0x0078:
            r11 = move-exception
            monitor-exit(r7)     // Catch:{ all -> 0x0078 }
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.e.b(int, java.util.List, boolean):okhttp3.internal.http2.g");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:26|27|28) */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r2 = java.lang.Math.min((int) java.lang.Math.min(r14, r10.j), r10.o.c());
        r6 = (long) r2;
        r10.j -= r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0062, code lost:
        throw new java.io.InterruptedIOException();
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:26:0x005d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(int r11, boolean r12, okio.c r13, long r14) throws java.io.IOException {
        /*
            r10 = this;
            r0 = 0
            int r2 = (r14 > r0 ? 1 : (r14 == r0 ? 0 : -1))
            r3 = 0
            if (r2 != 0) goto L_0x000d
            okhttp3.internal.http2.h r14 = r10.o
            r14.a(r12, r11, r13, r3)
            return
        L_0x000d:
            int r2 = (r14 > r0 ? 1 : (r14 == r0 ? 0 : -1))
            if (r2 <= 0) goto L_0x0065
            monitor-enter(r10)
        L_0x0012:
            long r4 = r10.j     // Catch:{ InterruptedException -> 0x005d }
            int r2 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r2 > 0) goto L_0x0030
            java.util.Map<java.lang.Integer, okhttp3.internal.http2.g> r2 = r10.c     // Catch:{ InterruptedException -> 0x005d }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r11)     // Catch:{ InterruptedException -> 0x005d }
            boolean r2 = r2.containsKey(r4)     // Catch:{ InterruptedException -> 0x005d }
            if (r2 != 0) goto L_0x002c
            java.io.IOException r11 = new java.io.IOException     // Catch:{ InterruptedException -> 0x005d }
            java.lang.String r12 = "stream closed"
            r11.<init>(r12)     // Catch:{ InterruptedException -> 0x005d }
            throw r11     // Catch:{ InterruptedException -> 0x005d }
        L_0x002c:
            r10.wait()     // Catch:{ InterruptedException -> 0x005d }
            goto L_0x0012
        L_0x0030:
            long r4 = r10.j     // Catch:{ all -> 0x005b }
            long r4 = java.lang.Math.min(r14, r4)     // Catch:{ all -> 0x005b }
            int r2 = (int) r4     // Catch:{ all -> 0x005b }
            okhttp3.internal.http2.h r4 = r10.o     // Catch:{ all -> 0x005b }
            int r4 = r4.c()     // Catch:{ all -> 0x005b }
            int r2 = java.lang.Math.min(r2, r4)     // Catch:{ all -> 0x005b }
            long r4 = r10.j     // Catch:{ all -> 0x005b }
            long r6 = (long) r2     // Catch:{ all -> 0x005b }
            long r8 = r4 - r6
            r10.j = r8     // Catch:{ all -> 0x005b }
            monitor-exit(r10)     // Catch:{ all -> 0x005b }
            long r4 = r14 - r6
            okhttp3.internal.http2.h r14 = r10.o
            if (r12 == 0) goto L_0x0055
            int r15 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r15 != 0) goto L_0x0055
            r15 = 1
            goto L_0x0056
        L_0x0055:
            r15 = r3
        L_0x0056:
            r14.a(r15, r11, r13, r2)
            r14 = r4
            goto L_0x000d
        L_0x005b:
            r11 = move-exception
            goto L_0x0063
        L_0x005d:
            java.io.InterruptedIOException r11 = new java.io.InterruptedIOException     // Catch:{ all -> 0x005b }
            r11.<init>()     // Catch:{ all -> 0x005b }
            throw r11     // Catch:{ all -> 0x005b }
        L_0x0063:
            monitor-exit(r10)     // Catch:{ all -> 0x005b }
            throw r11
        L_0x0065:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.e.a(int, boolean, okio.c, long):void");
    }

    /* access modifiers changed from: 0000 */
    public void a(long j2) {
        this.j += j2;
        if (j2 > 0) {
            notifyAll();
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(int i2, ErrorCode errorCode) {
        try {
            ScheduledExecutorService scheduledExecutorService = this.t;
            final int i3 = i2;
            final ErrorCode errorCode2 = errorCode;
            AnonymousClass1 r1 = new okhttp3.internal.b("OkHttp %s stream %d", new Object[]{this.d, Integer.valueOf(i2)}) {
                public void c() {
                    try {
                        e.this.b(i3, errorCode2);
                    } catch (IOException unused) {
                        e.this.f();
                    }
                }
            };
            scheduledExecutorService.execute(r1);
        } catch (RejectedExecutionException unused) {
        }
    }

    /* access modifiers changed from: 0000 */
    public void b(int i2, ErrorCode errorCode) throws IOException {
        this.o.a(i2, errorCode);
    }

    /* access modifiers changed from: 0000 */
    public void a(int i2, long j2) {
        try {
            ScheduledExecutorService scheduledExecutorService = this.t;
            final int i3 = i2;
            final long j3 = j2;
            AnonymousClass2 r1 = new okhttp3.internal.b("OkHttp Window Update %s stream %d", new Object[]{this.d, Integer.valueOf(i2)}) {
                public void c() {
                    try {
                        e.this.o.a(i3, j3);
                    } catch (IOException unused) {
                        e.this.f();
                    }
                }
            };
            scheduledExecutorService.execute(r1);
        } catch (RejectedExecutionException unused) {
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(boolean z, int i2, int i3) {
        boolean z2;
        if (!z) {
            synchronized (this) {
                z2 = this.v;
                this.v = true;
            }
            if (z2) {
                f();
                return;
            }
        }
        try {
            this.o.a(z, i2, i3);
        } catch (IOException unused) {
            f();
        }
    }

    public void b() throws IOException {
        this.o.b();
    }

    public void a(ErrorCode errorCode) throws IOException {
        synchronized (this.o) {
            synchronized (this) {
                if (!this.g) {
                    this.g = true;
                    int i2 = this.e;
                    this.o.a(i2, errorCode, okhttp3.internal.c.a);
                }
            }
        }
    }

    public void close() throws IOException {
        a(ErrorCode.NO_ERROR, ErrorCode.CANCEL);
    }

    /* access modifiers changed from: 0000 */
    public void a(ErrorCode errorCode, ErrorCode errorCode2) throws IOException {
        if (r || !Thread.holdsLock(this)) {
            g[] gVarArr = null;
            try {
                a(errorCode);
                e = null;
            } catch (IOException e2) {
                e = e2;
            }
            synchronized (this) {
                if (!this.c.isEmpty()) {
                    gVarArr = (g[]) this.c.values().toArray(new g[this.c.size()]);
                    this.c.clear();
                }
            }
            if (gVarArr != null) {
                for (g a2 : gVarArr) {
                    try {
                        a2.a(errorCode2);
                    } catch (IOException e3) {
                        if (e != null) {
                            e = e3;
                        }
                    }
                }
            }
            try {
                this.o.close();
            } catch (IOException e4) {
                if (e == null) {
                    e = e4;
                }
            }
            try {
                this.n.close();
            } catch (IOException e5) {
                e = e5;
            }
            this.t.shutdown();
            this.u.shutdown();
            if (e != null) {
                throw e;
            }
            return;
        }
        throw new AssertionError();
    }

    /* access modifiers changed from: private */
    public void f() {
        try {
            a(ErrorCode.PROTOCOL_ERROR, ErrorCode.PROTOCOL_ERROR);
        } catch (IOException unused) {
        }
    }

    public void c() throws IOException {
        a(true);
    }

    /* access modifiers changed from: 0000 */
    public void a(boolean z) throws IOException {
        if (z) {
            this.o.a();
            this.o.b(this.k);
            int d2 = this.k.d();
            if (d2 != 65535) {
                this.o.a(0, (long) (d2 - SupportMenu.USER_MASK));
            }
        }
        new Thread(this.p).start();
    }

    public synchronized boolean d() {
        return this.g;
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r0 = r8.u;
        r2 = r8;
        r5 = r9;
        r6 = r10;
        r1 = new okhttp3.internal.http2.e.AnonymousClass3(r2, "OkHttp %s Push Request[%s]", new java.lang.Object[]{r8.d, java.lang.Integer.valueOf(r9)});
        r0.execute(r1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(int r9, java.util.List<okhttp3.internal.http2.a> r10) {
        /*
            r8 = this;
            monitor-enter(r8)
            java.util.Set<java.lang.Integer> r0 = r8.q     // Catch:{ all -> 0x003e }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x003e }
            boolean r0 = r0.contains(r1)     // Catch:{ all -> 0x003e }
            if (r0 == 0) goto L_0x0014
            okhttp3.internal.http2.ErrorCode r10 = okhttp3.internal.http2.ErrorCode.PROTOCOL_ERROR     // Catch:{ all -> 0x003e }
            r8.a(r9, r10)     // Catch:{ all -> 0x003e }
            monitor-exit(r8)     // Catch:{ all -> 0x003e }
            return
        L_0x0014:
            java.util.Set<java.lang.Integer> r0 = r8.q     // Catch:{ all -> 0x003e }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x003e }
            r0.add(r1)     // Catch:{ all -> 0x003e }
            monitor-exit(r8)     // Catch:{ all -> 0x003e }
            java.util.concurrent.ExecutorService r0 = r8.u     // Catch:{ RejectedExecutionException -> 0x003d }
            okhttp3.internal.http2.e$3 r7 = new okhttp3.internal.http2.e$3     // Catch:{ RejectedExecutionException -> 0x003d }
            java.lang.String r3 = "OkHttp %s Push Request[%s]"
            r1 = 2
            java.lang.Object[] r4 = new java.lang.Object[r1]     // Catch:{ RejectedExecutionException -> 0x003d }
            r1 = 0
            java.lang.String r2 = r8.d     // Catch:{ RejectedExecutionException -> 0x003d }
            r4[r1] = r2     // Catch:{ RejectedExecutionException -> 0x003d }
            r1 = 1
            java.lang.Integer r2 = java.lang.Integer.valueOf(r9)     // Catch:{ RejectedExecutionException -> 0x003d }
            r4[r1] = r2     // Catch:{ RejectedExecutionException -> 0x003d }
            r1 = r7
            r2 = r8
            r5 = r9
            r6 = r10
            r1.<init>(r3, r4, r5, r6)     // Catch:{ RejectedExecutionException -> 0x003d }
            r0.execute(r7)     // Catch:{ RejectedExecutionException -> 0x003d }
        L_0x003d:
            return
        L_0x003e:
            r9 = move-exception
            monitor-exit(r8)     // Catch:{ all -> 0x003e }
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.e.a(int, java.util.List):void");
    }

    /* access modifiers changed from: 0000 */
    public void a(int i2, List<a> list, boolean z) {
        try {
            ExecutorService executorService = this.u;
            final int i3 = i2;
            final List<a> list2 = list;
            final boolean z2 = z;
            AnonymousClass4 r1 = new okhttp3.internal.b("OkHttp %s Push Headers[%s]", new Object[]{this.d, Integer.valueOf(i2)}) {
                public void c() {
                    boolean a2 = e.this.h.a(i3, list2, z2);
                    if (a2) {
                        try {
                            e.this.o.a(i3, ErrorCode.CANCEL);
                        } catch (IOException unused) {
                            return;
                        }
                    }
                    if (a2 || z2) {
                        synchronized (e.this) {
                            e.this.q.remove(Integer.valueOf(i3));
                        }
                    }
                }
            };
            executorService.execute(r1);
        } catch (RejectedExecutionException unused) {
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(int i2, okio.e eVar, int i3, boolean z) throws IOException {
        final okio.c cVar = new okio.c();
        long j2 = (long) i3;
        eVar.a(j2);
        eVar.a(cVar, j2);
        if (cVar.b() != j2) {
            StringBuilder sb = new StringBuilder();
            sb.append(cVar.b());
            sb.append(" != ");
            sb.append(i3);
            throw new IOException(sb.toString());
        }
        ExecutorService executorService = this.u;
        final int i4 = i2;
        final int i5 = i3;
        final boolean z2 = z;
        AnonymousClass5 r0 = new okhttp3.internal.b("OkHttp %s Push Data[%s]", new Object[]{this.d, Integer.valueOf(i2)}) {
            public void c() {
                try {
                    boolean a2 = e.this.h.a(i4, cVar, i5, z2);
                    if (a2) {
                        e.this.o.a(i4, ErrorCode.CANCEL);
                    }
                    if (a2 || z2) {
                        synchronized (e.this) {
                            e.this.q.remove(Integer.valueOf(i4));
                        }
                    }
                } catch (IOException unused) {
                }
            }
        };
        executorService.execute(r0);
    }

    /* access modifiers changed from: 0000 */
    public void c(int i2, ErrorCode errorCode) {
        ExecutorService executorService = this.u;
        final int i3 = i2;
        final ErrorCode errorCode2 = errorCode;
        AnonymousClass6 r1 = new okhttp3.internal.b("OkHttp %s Push Reset[%s]", new Object[]{this.d, Integer.valueOf(i2)}) {
            public void c() {
                e.this.h.a(i3, errorCode2);
                synchronized (e.this) {
                    e.this.q.remove(Integer.valueOf(i3));
                }
            }
        };
        executorService.execute(r1);
    }
}
