package okhttp3.internal.connection;

import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.net.Socket;
import okhttp3.ac;
import okhttp3.e;
import okhttp3.i;
import okhttp3.internal.b.c;
import okhttp3.internal.http2.ConnectionShutdownException;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.StreamResetException;
import okhttp3.j;
import okhttp3.p;
import okhttp3.w;

/* compiled from: StreamAllocation */
public final class f {
    static final /* synthetic */ boolean d = true;
    public final okhttp3.a a;
    public final e b;
    public final p c;
    private okhttp3.internal.connection.e.a e;
    private ac f;
    private final j g;
    private final Object h;
    private final e i;
    private int j;
    private c k;
    private boolean l;
    private boolean m;
    private boolean n;
    private c o;

    /* compiled from: StreamAllocation */
    public static final class a extends WeakReference<f> {
        public final Object a;

        a(f fVar, Object obj) {
            super(fVar);
            this.a = obj;
        }
    }

    public f(j jVar, okhttp3.a aVar, e eVar, p pVar, Object obj) {
        this.g = jVar;
        this.a = aVar;
        this.b = eVar;
        this.c = pVar;
        this.i = new e(aVar, i(), eVar, pVar);
        this.h = obj;
    }

    public c a(w wVar, okhttp3.t.a aVar, boolean z) {
        try {
            c a2 = a(aVar.c(), aVar.d(), aVar.e(), wVar.d(), wVar.s(), z).a(wVar, aVar, this);
            synchronized (this.g) {
                this.o = a2;
            }
            return a2;
        } catch (IOException e2) {
            throw new RouteException(e2);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0018, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0012, code lost:
        if (r0.a(r9) != false) goto L_0x0018;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private okhttp3.internal.connection.c a(int r4, int r5, int r6, int r7, boolean r8, boolean r9) throws java.io.IOException {
        /*
            r3 = this;
        L_0x0000:
            okhttp3.internal.connection.c r0 = r3.a(r4, r5, r6, r7, r8)
            okhttp3.j r1 = r3.g
            monitor-enter(r1)
            int r2 = r0.b     // Catch:{ all -> 0x0019 }
            if (r2 != 0) goto L_0x000d
            monitor-exit(r1)     // Catch:{ all -> 0x0019 }
            return r0
        L_0x000d:
            monitor-exit(r1)     // Catch:{ all -> 0x0019 }
            boolean r1 = r0.a(r9)
            if (r1 != 0) goto L_0x0018
            r3.e()
            goto L_0x0000
        L_0x0018:
            return r0
        L_0x0019:
            r4 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0019 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.f.a(int, int, int, int, boolean, boolean):okhttp3.internal.connection.c");
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: type inference failed for: r3v6 */
    /* JADX WARNING: type inference failed for: r2v1 */
    /* JADX WARNING: type inference failed for: r3v7 */
    /* JADX WARNING: type inference failed for: r11v1 */
    /* JADX WARNING: type inference failed for: r11v2, types: [int] */
    /* JADX WARNING: type inference failed for: r3v16 */
    /* JADX WARNING: type inference failed for: r2v13 */
    /* JADX WARNING: type inference failed for: r2v17 */
    /* JADX WARNING: type inference failed for: r3v18 */
    /* JADX WARNING: type inference failed for: r3v20 */
    /* JADX WARNING: type inference failed for: r11v5 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 3 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private okhttp3.internal.connection.c a(int r20, int r21, int r22, int r23, boolean r24) throws java.io.IOException {
        /*
            r19 = this;
            r1 = r19
            okhttp3.j r2 = r1.g
            monitor-enter(r2)
            boolean r3 = r1.m     // Catch:{ all -> 0x0140 }
            if (r3 == 0) goto L_0x0011
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0140 }
            java.lang.String r4 = "released"
            r3.<init>(r4)     // Catch:{ all -> 0x0140 }
            throw r3     // Catch:{ all -> 0x0140 }
        L_0x0011:
            okhttp3.internal.b.c r3 = r1.o     // Catch:{ all -> 0x0140 }
            if (r3 == 0) goto L_0x001d
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0140 }
            java.lang.String r4 = "codec != null"
            r3.<init>(r4)     // Catch:{ all -> 0x0140 }
            throw r3     // Catch:{ all -> 0x0140 }
        L_0x001d:
            boolean r3 = r1.n     // Catch:{ all -> 0x0140 }
            if (r3 == 0) goto L_0x0029
            java.io.IOException r3 = new java.io.IOException     // Catch:{ all -> 0x0140 }
            java.lang.String r4 = "Canceled"
            r3.<init>(r4)     // Catch:{ all -> 0x0140 }
            throw r3     // Catch:{ all -> 0x0140 }
        L_0x0029:
            okhttp3.internal.connection.c r3 = r1.k     // Catch:{ all -> 0x0140 }
            java.net.Socket r4 = r19.h()     // Catch:{ all -> 0x0140 }
            okhttp3.internal.connection.c r5 = r1.k     // Catch:{ all -> 0x0140 }
            r6 = 0
            if (r5 == 0) goto L_0x0038
            okhttp3.internal.connection.c r3 = r1.k     // Catch:{ all -> 0x0140 }
            r5 = r6
            goto L_0x003a
        L_0x0038:
            r5 = r3
            r3 = r6
        L_0x003a:
            boolean r7 = r1.l     // Catch:{ all -> 0x0140 }
            if (r7 != 0) goto L_0x003f
            r5 = r6
        L_0x003f:
            r7 = 1
            r8 = 0
            if (r3 != 0) goto L_0x005b
            okhttp3.internal.a r9 = okhttp3.internal.a.a     // Catch:{ all -> 0x0140 }
            okhttp3.j r10 = r1.g     // Catch:{ all -> 0x0140 }
            okhttp3.a r11 = r1.a     // Catch:{ all -> 0x0140 }
            r9.a(r10, r11, r1, r6)     // Catch:{ all -> 0x0140 }
            okhttp3.internal.connection.c r9 = r1.k     // Catch:{ all -> 0x0140 }
            if (r9 == 0) goto L_0x0056
            okhttp3.internal.connection.c r3 = r1.k     // Catch:{ all -> 0x0140 }
            r9 = r3
            r10 = r6
            r3 = r7
            goto L_0x005e
        L_0x0056:
            okhttp3.ac r9 = r1.f     // Catch:{ all -> 0x0140 }
            r10 = r9
            r9 = r3
            goto L_0x005d
        L_0x005b:
            r9 = r3
            r10 = r6
        L_0x005d:
            r3 = r8
        L_0x005e:
            monitor-exit(r2)     // Catch:{ all -> 0x0140 }
            okhttp3.internal.c.a(r4)
            if (r5 == 0) goto L_0x006b
            okhttp3.p r2 = r1.c
            okhttp3.e r4 = r1.b
            r2.b(r4, r5)
        L_0x006b:
            if (r3 == 0) goto L_0x0074
            okhttp3.p r2 = r1.c
            okhttp3.e r4 = r1.b
            r2.a(r4, r9)
        L_0x0074:
            if (r9 == 0) goto L_0x0077
            return r9
        L_0x0077:
            if (r10 != 0) goto L_0x008f
            okhttp3.internal.connection.e$a r2 = r1.e
            if (r2 == 0) goto L_0x0085
            okhttp3.internal.connection.e$a r2 = r1.e
            boolean r2 = r2.a()
            if (r2 != 0) goto L_0x008f
        L_0x0085:
            okhttp3.internal.connection.e r2 = r1.i
            okhttp3.internal.connection.e$a r2 = r2.b()
            r1.e = r2
            r2 = r7
            goto L_0x0090
        L_0x008f:
            r2 = r8
        L_0x0090:
            okhttp3.j r4 = r1.g
            monitor-enter(r4)
            boolean r5 = r1.n     // Catch:{ all -> 0x013c }
            if (r5 == 0) goto L_0x009f
            java.io.IOException r2 = new java.io.IOException     // Catch:{ all -> 0x013c }
            java.lang.String r3 = "Canceled"
            r2.<init>(r3)     // Catch:{ all -> 0x013c }
            throw r2     // Catch:{ all -> 0x013c }
        L_0x009f:
            if (r2 == 0) goto L_0x00ca
            okhttp3.internal.connection.e$a r2 = r1.e     // Catch:{ all -> 0x013c }
            java.util.List r2 = r2.c()     // Catch:{ all -> 0x013c }
            int r5 = r2.size()     // Catch:{ all -> 0x013c }
            r11 = r8
        L_0x00ac:
            if (r11 >= r5) goto L_0x00ca
            java.lang.Object r12 = r2.get(r11)     // Catch:{ all -> 0x013c }
            okhttp3.ac r12 = (okhttp3.ac) r12     // Catch:{ all -> 0x013c }
            okhttp3.internal.a r13 = okhttp3.internal.a.a     // Catch:{ all -> 0x013c }
            okhttp3.j r14 = r1.g     // Catch:{ all -> 0x013c }
            okhttp3.a r15 = r1.a     // Catch:{ all -> 0x013c }
            r13.a(r14, r15, r1, r12)     // Catch:{ all -> 0x013c }
            okhttp3.internal.connection.c r13 = r1.k     // Catch:{ all -> 0x013c }
            if (r13 == 0) goto L_0x00c7
            okhttp3.internal.connection.c r9 = r1.k     // Catch:{ all -> 0x013c }
            r1.f = r12     // Catch:{ all -> 0x013c }
            r3 = r7
            goto L_0x00ca
        L_0x00c7:
            int r11 = r11 + 1
            goto L_0x00ac
        L_0x00ca:
            if (r3 != 0) goto L_0x00e2
            if (r10 != 0) goto L_0x00d4
            okhttp3.internal.connection.e$a r2 = r1.e     // Catch:{ all -> 0x013c }
            okhttp3.ac r10 = r2.b()     // Catch:{ all -> 0x013c }
        L_0x00d4:
            r1.f = r10     // Catch:{ all -> 0x013c }
            r1.j = r8     // Catch:{ all -> 0x013c }
            okhttp3.internal.connection.c r9 = new okhttp3.internal.connection.c     // Catch:{ all -> 0x013c }
            okhttp3.j r2 = r1.g     // Catch:{ all -> 0x013c }
            r9.<init>(r2, r10)     // Catch:{ all -> 0x013c }
            r1.a(r9, r8)     // Catch:{ all -> 0x013c }
        L_0x00e2:
            monitor-exit(r4)     // Catch:{ all -> 0x013c }
            if (r3 == 0) goto L_0x00ed
            okhttp3.p r2 = r1.c
            okhttp3.e r3 = r1.b
            r2.a(r3, r9)
            return r9
        L_0x00ed:
            okhttp3.e r2 = r1.b
            okhttp3.p r3 = r1.c
            r11 = r9
            r12 = r20
            r13 = r21
            r14 = r22
            r15 = r23
            r16 = r24
            r17 = r2
            r18 = r3
            r11.a(r12, r13, r14, r15, r16, r17, r18)
            okhttp3.internal.connection.d r2 = r19.i()
            okhttp3.ac r3 = r9.b()
            r2.b(r3)
            okhttp3.j r2 = r1.g
            monitor-enter(r2)
            r1.l = r7     // Catch:{ all -> 0x0138 }
            okhttp3.internal.a r3 = okhttp3.internal.a.a     // Catch:{ all -> 0x0138 }
            okhttp3.j r4 = r1.g     // Catch:{ all -> 0x0138 }
            r3.b(r4, r9)     // Catch:{ all -> 0x0138 }
            boolean r3 = r9.f()     // Catch:{ all -> 0x0138 }
            if (r3 == 0) goto L_0x012c
            okhttp3.internal.a r3 = okhttp3.internal.a.a     // Catch:{ all -> 0x0138 }
            okhttp3.j r4 = r1.g     // Catch:{ all -> 0x0138 }
            okhttp3.a r5 = r1.a     // Catch:{ all -> 0x0138 }
            java.net.Socket r6 = r3.a(r4, r5, r1)     // Catch:{ all -> 0x0138 }
            okhttp3.internal.connection.c r9 = r1.k     // Catch:{ all -> 0x0138 }
        L_0x012c:
            monitor-exit(r2)     // Catch:{ all -> 0x0138 }
            okhttp3.internal.c.a(r6)
            okhttp3.p r2 = r1.c
            okhttp3.e r3 = r1.b
            r2.a(r3, r9)
            return r9
        L_0x0138:
            r0 = move-exception
            r3 = r0
            monitor-exit(r2)     // Catch:{ all -> 0x0138 }
            throw r3
        L_0x013c:
            r0 = move-exception
            r2 = r0
            monitor-exit(r4)     // Catch:{ all -> 0x013c }
            throw r2
        L_0x0140:
            r0 = move-exception
            r3 = r0
            monitor-exit(r2)     // Catch:{ all -> 0x0140 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.f.a(int, int, int, int, boolean):okhttp3.internal.connection.c");
    }

    private Socket h() {
        if (d || Thread.holdsLock(this.g)) {
            c cVar = this.k;
            if (cVar == null || !cVar.a) {
                return null;
            }
            return a(false, false, true);
        }
        throw new AssertionError();
    }

    public void a(boolean z, c cVar, long j2, IOException iOException) {
        c cVar2;
        Socket a2;
        boolean z2;
        this.c.b(this.b, j2);
        synchronized (this.g) {
            if (cVar != null) {
                if (cVar == this.o) {
                    if (!z) {
                        this.k.b++;
                    }
                    cVar2 = this.k;
                    a2 = a(z, false, true);
                    if (this.k != null) {
                        cVar2 = null;
                    }
                    z2 = this.m;
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append("expected ");
            sb.append(this.o);
            sb.append(" but was ");
            sb.append(cVar);
            throw new IllegalStateException(sb.toString());
        }
        okhttp3.internal.c.a(a2);
        if (cVar2 != null) {
            this.c.b(this.b, (i) cVar2);
        }
        if (iOException != null) {
            this.c.a(this.b, iOException);
        } else if (z2) {
            this.c.g(this.b);
        }
    }

    public c a() {
        c cVar;
        synchronized (this.g) {
            cVar = this.o;
        }
        return cVar;
    }

    private d i() {
        return okhttp3.internal.a.a.a(this.g);
    }

    public ac b() {
        return this.f;
    }

    public synchronized c c() {
        return this.k;
    }

    public void d() {
        c cVar;
        Socket a2;
        synchronized (this.g) {
            cVar = this.k;
            a2 = a(false, true, false);
            if (this.k != null) {
                cVar = null;
            }
        }
        okhttp3.internal.c.a(a2);
        if (cVar != null) {
            this.c.b(this.b, (i) cVar);
        }
    }

    public void e() {
        c cVar;
        Socket a2;
        synchronized (this.g) {
            cVar = this.k;
            a2 = a(true, false, false);
            if (this.k != null) {
                cVar = null;
            }
        }
        okhttp3.internal.c.a(a2);
        if (cVar != null) {
            this.c.b(this.b, (i) cVar);
        }
    }

    private Socket a(boolean z, boolean z2, boolean z3) {
        Socket socket;
        if (d || Thread.holdsLock(this.g)) {
            if (z3) {
                this.o = null;
            }
            if (z2) {
                this.m = true;
            }
            if (this.k != null) {
                if (z) {
                    this.k.a = true;
                }
                if (this.o == null && (this.m || this.k.a)) {
                    b(this.k);
                    if (this.k.d.isEmpty()) {
                        this.k.e = System.nanoTime();
                        if (okhttp3.internal.a.a.a(this.g, this.k)) {
                            socket = this.k.d();
                            this.k = null;
                            return socket;
                        }
                    }
                    socket = null;
                    this.k = null;
                    return socket;
                }
            }
            return null;
        }
        throw new AssertionError();
    }

    public void f() {
        c cVar;
        c cVar2;
        synchronized (this.g) {
            this.n = true;
            cVar = this.o;
            cVar2 = this.k;
        }
        if (cVar != null) {
            cVar.c();
        } else if (cVar2 != null) {
            cVar2.c();
        }
    }

    public void a(IOException iOException) {
        boolean z;
        c cVar;
        Socket a2;
        synchronized (this.g) {
            if (iOException instanceof StreamResetException) {
                StreamResetException streamResetException = (StreamResetException) iOException;
                if (streamResetException.errorCode == ErrorCode.REFUSED_STREAM) {
                    this.j++;
                }
                if (streamResetException.errorCode != ErrorCode.REFUSED_STREAM || this.j > 1) {
                    this.f = null;
                }
                z = false;
                cVar = this.k;
                a2 = a(z, false, true);
                if (this.k != null || !this.l) {
                    cVar = null;
                }
            } else {
                if (this.k != null && (!this.k.f() || (iOException instanceof ConnectionShutdownException))) {
                    if (this.k.b == 0) {
                        if (!(this.f == null || iOException == null)) {
                            this.i.a(this.f, iOException);
                        }
                        this.f = null;
                    }
                }
                z = false;
                cVar = this.k;
                a2 = a(z, false, true);
                cVar = null;
            }
            z = true;
            cVar = this.k;
            a2 = a(z, false, true);
            cVar = null;
        }
        okhttp3.internal.c.a(a2);
        if (cVar != null) {
            this.c.b(this.b, (i) cVar);
        }
    }

    public void a(c cVar, boolean z) {
        if (!d && !Thread.holdsLock(this.g)) {
            throw new AssertionError();
        } else if (this.k != null) {
            throw new IllegalStateException();
        } else {
            this.k = cVar;
            this.l = z;
            cVar.d.add(new a(this, this.h));
        }
    }

    private void b(c cVar) {
        int size = cVar.d.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (((Reference) cVar.d.get(i2)).get() == this) {
                cVar.d.remove(i2);
                return;
            }
        }
        throw new IllegalStateException();
    }

    public Socket a(c cVar) {
        if (!d && !Thread.holdsLock(this.g)) {
            throw new AssertionError();
        } else if (this.o == null && this.k.d.size() == 1) {
            Reference reference = (Reference) this.k.d.get(0);
            Socket a2 = a(true, false, false);
            this.k = cVar;
            cVar.d.add(reference);
            return a2;
        } else {
            throw new IllegalStateException();
        }
    }

    public boolean g() {
        return this.f != null || (this.e != null && this.e.a()) || this.i.a();
    }

    public String toString() {
        c c2 = c();
        return c2 != null ? c2.toString() : this.a.toString();
    }
}
