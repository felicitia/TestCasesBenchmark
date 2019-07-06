package okhttp3.internal.http2;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.EOFException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import okio.e;
import okio.s;
import okio.t;
import okio.u;

/* compiled from: Http2Stream */
public final class g {
    static final /* synthetic */ boolean i = true;
    long a = 0;
    long b;
    final int c;
    final e d;
    final a e;
    final c f = new c();
    final c g = new c();
    ErrorCode h = null;
    private final List<a> j;
    private List<a> k;
    private boolean l;
    private final b m;

    /* compiled from: Http2Stream */
    final class a implements s {
        static final /* synthetic */ boolean c = true;
        boolean a;
        boolean b;
        private final okio.c e = new okio.c();

        static {
            Class<g> cls = g.class;
        }

        a() {
        }

        public void a_(okio.c cVar, long j) throws IOException {
            if (c || !Thread.holdsLock(g.this)) {
                this.e.a_(cVar, j);
                while (this.e.b() >= PlaybackStateCompat.ACTION_PREPARE) {
                    a(false);
                }
                return;
            }
            throw new AssertionError();
        }

        /* JADX INFO: finally extract failed */
        private void a(boolean z) throws IOException {
            long min;
            synchronized (g.this) {
                g.this.g.c();
                while (g.this.b <= 0 && !this.b && !this.a && g.this.h == null) {
                    try {
                        g.this.l();
                    } catch (Throwable th) {
                        g.this.g.b();
                        throw th;
                    }
                }
                g.this.g.b();
                g.this.k();
                min = Math.min(g.this.b, this.e.b());
                g.this.b -= min;
            }
            g.this.g.c();
            try {
                g.this.d.a(g.this.c, z && min == this.e.b(), this.e, min);
            } finally {
                g.this.g.b();
            }
        }

        public void flush() throws IOException {
            if (c || !Thread.holdsLock(g.this)) {
                synchronized (g.this) {
                    g.this.k();
                }
                while (this.e.b() > 0) {
                    a(false);
                    g.this.d.b();
                }
                return;
            }
            throw new AssertionError();
        }

        public u a() {
            return g.this.g;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:15:0x0023, code lost:
            if (r8.d.e.b != false) goto L_0x004e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x002f, code lost:
            if (r8.e.b() <= 0) goto L_0x003f;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0039, code lost:
            if (r8.e.b() <= 0) goto L_0x004e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x003b, code lost:
            a(true);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x003f, code lost:
            r8.d.d.a(r8.d.c, true, (okio.c) null, 0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x004e, code lost:
            r2 = r8.d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x0050, code lost:
            monitor-enter(r2);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
            r8.a = true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x0053, code lost:
            monitor-exit(r2);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x0054, code lost:
            r8.d.d.b();
            r8.d.j();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x0060, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void close() throws java.io.IOException {
            /*
                r8 = this;
                boolean r0 = c
                if (r0 != 0) goto L_0x0012
                okhttp3.internal.http2.g r0 = okhttp3.internal.http2.g.this
                boolean r0 = java.lang.Thread.holdsLock(r0)
                if (r0 == 0) goto L_0x0012
                java.lang.AssertionError r0 = new java.lang.AssertionError
                r0.<init>()
                throw r0
            L_0x0012:
                okhttp3.internal.http2.g r0 = okhttp3.internal.http2.g.this
                monitor-enter(r0)
                boolean r1 = r8.a     // Catch:{ all -> 0x0064 }
                if (r1 == 0) goto L_0x001b
                monitor-exit(r0)     // Catch:{ all -> 0x0064 }
                return
            L_0x001b:
                monitor-exit(r0)     // Catch:{ all -> 0x0064 }
                okhttp3.internal.http2.g r0 = okhttp3.internal.http2.g.this
                okhttp3.internal.http2.g$a r0 = r0.e
                boolean r0 = r0.b
                r1 = 1
                if (r0 != 0) goto L_0x004e
                okio.c r0 = r8.e
                long r2 = r0.b()
                r4 = 0
                int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
                if (r0 <= 0) goto L_0x003f
            L_0x0031:
                okio.c r0 = r8.e
                long r2 = r0.b()
                int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
                if (r0 <= 0) goto L_0x004e
                r8.a(r1)
                goto L_0x0031
            L_0x003f:
                okhttp3.internal.http2.g r0 = okhttp3.internal.http2.g.this
                okhttp3.internal.http2.e r2 = r0.d
                okhttp3.internal.http2.g r0 = okhttp3.internal.http2.g.this
                int r3 = r0.c
                r4 = 1
                r5 = 0
                r6 = 0
                r2.a(r3, r4, r5, r6)
            L_0x004e:
                okhttp3.internal.http2.g r2 = okhttp3.internal.http2.g.this
                monitor-enter(r2)
                r8.a = r1     // Catch:{ all -> 0x0061 }
                monitor-exit(r2)     // Catch:{ all -> 0x0061 }
                okhttp3.internal.http2.g r0 = okhttp3.internal.http2.g.this
                okhttp3.internal.http2.e r0 = r0.d
                r0.b()
                okhttp3.internal.http2.g r0 = okhttp3.internal.http2.g.this
                r0.j()
                return
            L_0x0061:
                r0 = move-exception
                monitor-exit(r2)     // Catch:{ all -> 0x0061 }
                throw r0
            L_0x0064:
                r1 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x0064 }
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.g.a.close():void");
        }
    }

    /* compiled from: Http2Stream */
    private final class b implements t {
        static final /* synthetic */ boolean c = true;
        boolean a;
        boolean b;
        private final okio.c e = new okio.c();
        private final okio.c f = new okio.c();
        private final long g;

        static {
            Class<g> cls = g.class;
        }

        b(long j) {
            this.g = j;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:16:0x0075, code lost:
            r10 = r7.d.d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0079, code lost:
            monitor-enter(r10);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
            r7.d.d.i += r8;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x0099, code lost:
            if (r7.d.d.i < ((long) (r7.d.d.k.d() / 2))) goto L_0x00af;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x009b, code lost:
            r7.d.d.a(0, r7.d.d.i);
            r7.d.d.i = 0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x00af, code lost:
            monitor-exit(r10);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x00b0, code lost:
            return r8;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public long a(okio.c r8, long r9) throws java.io.IOException {
            /*
                r7 = this;
                r0 = 0
                int r2 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
                if (r2 >= 0) goto L_0x001d
                java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r1 = "byteCount < 0: "
                r0.append(r1)
                r0.append(r9)
                java.lang.String r9 = r0.toString()
                r8.<init>(r9)
                throw r8
            L_0x001d:
                okhttp3.internal.http2.g r2 = okhttp3.internal.http2.g.this
                monitor-enter(r2)
                r7.b()     // Catch:{ all -> 0x00b4 }
                r7.c()     // Catch:{ all -> 0x00b4 }
                okio.c r3 = r7.f     // Catch:{ all -> 0x00b4 }
                long r3 = r3.b()     // Catch:{ all -> 0x00b4 }
                int r5 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
                if (r5 != 0) goto L_0x0034
                r8 = -1
                monitor-exit(r2)     // Catch:{ all -> 0x00b4 }
                return r8
            L_0x0034:
                okio.c r3 = r7.f     // Catch:{ all -> 0x00b4 }
                okio.c r4 = r7.f     // Catch:{ all -> 0x00b4 }
                long r4 = r4.b()     // Catch:{ all -> 0x00b4 }
                long r9 = java.lang.Math.min(r9, r4)     // Catch:{ all -> 0x00b4 }
                long r8 = r3.a(r8, r9)     // Catch:{ all -> 0x00b4 }
                okhttp3.internal.http2.g r10 = okhttp3.internal.http2.g.this     // Catch:{ all -> 0x00b4 }
                long r3 = r10.a     // Catch:{ all -> 0x00b4 }
                long r5 = r3 + r8
                r10.a = r5     // Catch:{ all -> 0x00b4 }
                okhttp3.internal.http2.g r10 = okhttp3.internal.http2.g.this     // Catch:{ all -> 0x00b4 }
                long r3 = r10.a     // Catch:{ all -> 0x00b4 }
                okhttp3.internal.http2.g r10 = okhttp3.internal.http2.g.this     // Catch:{ all -> 0x00b4 }
                okhttp3.internal.http2.e r10 = r10.d     // Catch:{ all -> 0x00b4 }
                okhttp3.internal.http2.k r10 = r10.k     // Catch:{ all -> 0x00b4 }
                int r10 = r10.d()     // Catch:{ all -> 0x00b4 }
                int r10 = r10 / 2
                long r5 = (long) r10     // Catch:{ all -> 0x00b4 }
                int r10 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                if (r10 < 0) goto L_0x0074
                okhttp3.internal.http2.g r10 = okhttp3.internal.http2.g.this     // Catch:{ all -> 0x00b4 }
                okhttp3.internal.http2.e r10 = r10.d     // Catch:{ all -> 0x00b4 }
                okhttp3.internal.http2.g r3 = okhttp3.internal.http2.g.this     // Catch:{ all -> 0x00b4 }
                int r3 = r3.c     // Catch:{ all -> 0x00b4 }
                okhttp3.internal.http2.g r4 = okhttp3.internal.http2.g.this     // Catch:{ all -> 0x00b4 }
                long r4 = r4.a     // Catch:{ all -> 0x00b4 }
                r10.a(r3, r4)     // Catch:{ all -> 0x00b4 }
                okhttp3.internal.http2.g r10 = okhttp3.internal.http2.g.this     // Catch:{ all -> 0x00b4 }
                r10.a = r0     // Catch:{ all -> 0x00b4 }
            L_0x0074:
                monitor-exit(r2)     // Catch:{ all -> 0x00b4 }
                okhttp3.internal.http2.g r10 = okhttp3.internal.http2.g.this
                okhttp3.internal.http2.e r10 = r10.d
                monitor-enter(r10)
                okhttp3.internal.http2.g r2 = okhttp3.internal.http2.g.this     // Catch:{ all -> 0x00b1 }
                okhttp3.internal.http2.e r2 = r2.d     // Catch:{ all -> 0x00b1 }
                long r3 = r2.i     // Catch:{ all -> 0x00b1 }
                long r5 = r3 + r8
                r2.i = r5     // Catch:{ all -> 0x00b1 }
                okhttp3.internal.http2.g r2 = okhttp3.internal.http2.g.this     // Catch:{ all -> 0x00b1 }
                okhttp3.internal.http2.e r2 = r2.d     // Catch:{ all -> 0x00b1 }
                long r2 = r2.i     // Catch:{ all -> 0x00b1 }
                okhttp3.internal.http2.g r4 = okhttp3.internal.http2.g.this     // Catch:{ all -> 0x00b1 }
                okhttp3.internal.http2.e r4 = r4.d     // Catch:{ all -> 0x00b1 }
                okhttp3.internal.http2.k r4 = r4.k     // Catch:{ all -> 0x00b1 }
                int r4 = r4.d()     // Catch:{ all -> 0x00b1 }
                int r4 = r4 / 2
                long r4 = (long) r4     // Catch:{ all -> 0x00b1 }
                int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
                if (r6 < 0) goto L_0x00af
                okhttp3.internal.http2.g r2 = okhttp3.internal.http2.g.this     // Catch:{ all -> 0x00b1 }
                okhttp3.internal.http2.e r2 = r2.d     // Catch:{ all -> 0x00b1 }
                r3 = 0
                okhttp3.internal.http2.g r4 = okhttp3.internal.http2.g.this     // Catch:{ all -> 0x00b1 }
                okhttp3.internal.http2.e r4 = r4.d     // Catch:{ all -> 0x00b1 }
                long r4 = r4.i     // Catch:{ all -> 0x00b1 }
                r2.a(r3, r4)     // Catch:{ all -> 0x00b1 }
                okhttp3.internal.http2.g r2 = okhttp3.internal.http2.g.this     // Catch:{ all -> 0x00b1 }
                okhttp3.internal.http2.e r2 = r2.d     // Catch:{ all -> 0x00b1 }
                r2.i = r0     // Catch:{ all -> 0x00b1 }
            L_0x00af:
                monitor-exit(r10)     // Catch:{ all -> 0x00b1 }
                return r8
            L_0x00b1:
                r8 = move-exception
                monitor-exit(r10)     // Catch:{ all -> 0x00b1 }
                throw r8
            L_0x00b4:
                r8 = move-exception
                monitor-exit(r2)     // Catch:{ all -> 0x00b4 }
                throw r8
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.g.b.a(okio.c, long):long");
        }

        private void b() throws IOException {
            g.this.f.c();
            while (this.f.b() == 0 && !this.b && !this.a && g.this.h == null) {
                try {
                    g.this.l();
                } finally {
                    g.this.f.b();
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void a(e eVar, long j) throws IOException {
            boolean z;
            boolean z2;
            boolean z3;
            if (c || !Thread.holdsLock(g.this)) {
                while (j > 0) {
                    synchronized (g.this) {
                        z = this.b;
                        z2 = false;
                        z3 = j + this.f.b() > this.g;
                    }
                    if (z3) {
                        eVar.i(j);
                        g.this.b(ErrorCode.FLOW_CONTROL_ERROR);
                        return;
                    } else if (z) {
                        eVar.i(j);
                        return;
                    } else {
                        long a2 = eVar.a(this.e, j);
                        if (a2 == -1) {
                            throw new EOFException();
                        }
                        long j2 = j - a2;
                        synchronized (g.this) {
                            if (this.f.b() == 0) {
                                z2 = true;
                            }
                            this.f.a((t) this.e);
                            if (z2) {
                                g.this.notifyAll();
                            }
                        }
                        j = j2;
                    }
                }
                return;
            }
            throw new AssertionError();
        }

        public u a() {
            return g.this.f;
        }

        public void close() throws IOException {
            synchronized (g.this) {
                this.a = true;
                this.f.t();
                g.this.notifyAll();
            }
            g.this.j();
        }

        private void c() throws IOException {
            if (this.a) {
                throw new IOException("stream closed");
            } else if (g.this.h != null) {
                throw new StreamResetException(g.this.h);
            }
        }
    }

    /* compiled from: Http2Stream */
    class c extends okio.a {
        c() {
        }

        /* access modifiers changed from: protected */
        public void a() {
            g.this.b(ErrorCode.CANCEL);
        }

        /* access modifiers changed from: protected */
        public IOException a(IOException iOException) {
            SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
            if (iOException != null) {
                socketTimeoutException.initCause(iOException);
            }
            return socketTimeoutException;
        }

        public void b() throws IOException {
            if (i_()) {
                throw a(null);
            }
        }
    }

    g(int i2, e eVar, boolean z, boolean z2, List<a> list) {
        if (eVar == null) {
            throw new NullPointerException("connection == null");
        } else if (list == null) {
            throw new NullPointerException("requestHeaders == null");
        } else {
            this.c = i2;
            this.d = eVar;
            this.b = (long) eVar.l.d();
            this.m = new b((long) eVar.k.d());
            this.e = new a();
            this.m.b = z2;
            this.e.b = z;
            this.j = list;
        }
    }

    public int a() {
        return this.c;
    }

    public synchronized boolean b() {
        if (this.h != null) {
            return false;
        }
        if ((this.m.b || this.m.a) && ((this.e.b || this.e.a) && this.l)) {
            return false;
        }
        return true;
    }

    public boolean c() {
        if (this.d.a == ((this.c & 1) == 1)) {
            return true;
        }
        return false;
    }

    /* JADX INFO: finally extract failed */
    public synchronized List<a> d() throws IOException {
        List<a> list;
        if (!c()) {
            throw new IllegalStateException("servers cannot read response headers");
        }
        this.f.c();
        while (this.k == null && this.h == null) {
            try {
                l();
            } catch (Throwable th) {
                this.f.b();
                throw th;
            }
        }
        this.f.b();
        list = this.k;
        if (list != null) {
            this.k = null;
        } else {
            throw new StreamResetException(this.h);
        }
        return list;
    }

    public u e() {
        return this.f;
    }

    public u f() {
        return this.g;
    }

    public t g() {
        return this.m;
    }

    public s h() {
        synchronized (this) {
            if (!this.l && !c()) {
                throw new IllegalStateException("reply before requesting the sink");
            }
        }
        return this.e;
    }

    public void a(ErrorCode errorCode) throws IOException {
        if (d(errorCode)) {
            this.d.b(this.c, errorCode);
        }
    }

    public void b(ErrorCode errorCode) {
        if (d(errorCode)) {
            this.d.a(this.c, errorCode);
        }
    }

    private boolean d(ErrorCode errorCode) {
        if (i || !Thread.holdsLock(this)) {
            synchronized (this) {
                if (this.h != null) {
                    return false;
                }
                if (this.m.b && this.e.b) {
                    return false;
                }
                this.h = errorCode;
                notifyAll();
                this.d.b(this.c);
                return true;
            }
        }
        throw new AssertionError();
    }

    /* access modifiers changed from: 0000 */
    public void a(List<a> list) {
        boolean z;
        if (i || !Thread.holdsLock(this)) {
            synchronized (this) {
                z = true;
                this.l = true;
                if (this.k == null) {
                    this.k = list;
                    z = b();
                    notifyAll();
                } else {
                    ArrayList arrayList = new ArrayList();
                    arrayList.addAll(this.k);
                    arrayList.add(null);
                    arrayList.addAll(list);
                    this.k = arrayList;
                }
            }
            if (!z) {
                this.d.b(this.c);
                return;
            }
            return;
        }
        throw new AssertionError();
    }

    /* access modifiers changed from: 0000 */
    public void a(e eVar, int i2) throws IOException {
        if (i || !Thread.holdsLock(this)) {
            this.m.a(eVar, (long) i2);
            return;
        }
        throw new AssertionError();
    }

    /* access modifiers changed from: 0000 */
    public void i() {
        boolean b2;
        if (i || !Thread.holdsLock(this)) {
            synchronized (this) {
                this.m.b = true;
                b2 = b();
                notifyAll();
            }
            if (!b2) {
                this.d.b(this.c);
                return;
            }
            return;
        }
        throw new AssertionError();
    }

    /* access modifiers changed from: 0000 */
    public synchronized void c(ErrorCode errorCode) {
        if (this.h == null) {
            this.h = errorCode;
            notifyAll();
        }
    }

    /* access modifiers changed from: 0000 */
    public void j() throws IOException {
        boolean z;
        boolean b2;
        if (i || !Thread.holdsLock(this)) {
            synchronized (this) {
                z = !this.m.b && this.m.a && (this.e.b || this.e.a);
                b2 = b();
            }
            if (z) {
                a(ErrorCode.CANCEL);
            } else if (!b2) {
                this.d.b(this.c);
            }
        } else {
            throw new AssertionError();
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(long j2) {
        this.b += j2;
        if (j2 > 0) {
            notifyAll();
        }
    }

    /* access modifiers changed from: 0000 */
    public void k() throws IOException {
        if (this.e.a) {
            throw new IOException("stream closed");
        } else if (this.e.b) {
            throw new IOException("stream finished");
        } else if (this.h != null) {
            throw new StreamResetException(this.h);
        }
    }

    /* access modifiers changed from: 0000 */
    public void l() throws InterruptedIOException {
        try {
            wait();
        } catch (InterruptedException unused) {
            throw new InterruptedIOException();
        }
    }
}
