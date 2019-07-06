package retrofit2;

import java.io.IOException;
import okhttp3.aa;
import okhttp3.ab;
import okhttp3.e;
import okhttp3.f;
import okhttp3.u;
import okio.c;
import okio.m;
import okio.t;

/* compiled from: OkHttpCall */
final class h<T> implements b<T> {
    private final n<T, ?> a;
    private final Object[] b;
    private volatile boolean c;
    private e d;
    private Throwable e;
    private boolean f;

    /* compiled from: OkHttpCall */
    static final class a extends ab {
        IOException a;
        private final ab b;

        a(ab abVar) {
            this.b = abVar;
        }

        public u a() {
            return this.b.a();
        }

        public long b() {
            return this.b.b();
        }

        public okio.e c() {
            return m.a((t) new okio.h(this.b.c()) {
                public long a(c cVar, long j) throws IOException {
                    try {
                        return super.a(cVar, j);
                    } catch (IOException e) {
                        a.this.a = e;
                        throw e;
                    }
                }
            });
        }

        public void close() {
            this.b.close();
        }

        /* access modifiers changed from: 0000 */
        public void f() throws IOException {
            if (this.a != null) {
                throw this.a;
            }
        }
    }

    /* compiled from: OkHttpCall */
    static final class b extends ab {
        private final u a;
        private final long b;

        b(u uVar, long j) {
            this.a = uVar;
            this.b = j;
        }

        public u a() {
            return this.a;
        }

        public long b() {
            return this.b;
        }

        public okio.e c() {
            throw new IllegalStateException("Cannot read raw response body of a converted body.");
        }
    }

    h(n<T, ?> nVar, Object[] objArr) {
        this.a = nVar;
        this.b = objArr;
    }

    /* renamed from: e */
    public h<T> d() {
        return new h<>(this.a, this.b);
    }

    public void a(final d<T> dVar) {
        e eVar;
        Throwable th;
        o.a(dVar, "callback == null");
        synchronized (this) {
            if (this.f) {
                throw new IllegalStateException("Already executed.");
            }
            this.f = true;
            eVar = this.d;
            th = this.e;
            if (eVar == null && th == null) {
                try {
                    e f2 = f();
                    this.d = f2;
                    eVar = f2;
                } catch (Throwable th2) {
                    th = th2;
                    o.a(th);
                    this.e = th;
                }
            }
        }
        if (th != null) {
            dVar.a((b<T>) this, th);
            return;
        }
        if (this.c) {
            eVar.b();
        }
        eVar.a(new f() {
            public void a(e eVar, aa aaVar) {
                try {
                    try {
                        dVar.a((b<T>) h.this, h.this.a(aaVar));
                    } catch (Throwable th) {
                        com.google.a.a.a.a.a.a.a(th);
                    }
                } catch (Throwable th2) {
                    a(th2);
                }
            }

            public void a(e eVar, IOException iOException) {
                a(iOException);
            }

            private void a(Throwable th) {
                try {
                    dVar.a((b<T>) h.this, th);
                } catch (Throwable th2) {
                    com.google.a.a.a.a.a.a.a(th2);
                }
            }
        });
    }

    public l<T> a() throws IOException {
        e eVar;
        synchronized (this) {
            if (this.f) {
                throw new IllegalStateException("Already executed.");
            }
            this.f = true;
            if (this.e == null) {
                eVar = this.d;
                if (eVar == null) {
                    try {
                        eVar = f();
                        this.d = eVar;
                    } catch (IOException | Error | RuntimeException e2) {
                        o.a(e2);
                        this.e = e2;
                        throw e2;
                    }
                }
            } else if (this.e instanceof IOException) {
                throw ((IOException) this.e);
            } else if (this.e instanceof RuntimeException) {
                throw ((RuntimeException) this.e);
            } else {
                throw ((Error) this.e);
            }
        }
        if (this.c) {
            eVar.b();
        }
        return a(eVar.a());
    }

    private e f() throws IOException {
        e a2 = this.a.a(this.b);
        if (a2 != null) {
            return a2;
        }
        throw new NullPointerException("Call.Factory returned null.");
    }

    /* access modifiers changed from: 0000 */
    public l<T> a(aa aaVar) throws IOException {
        ab g = aaVar.g();
        aa a2 = aaVar.h().a((ab) new b(g.a(), g.b())).a();
        int b2 = a2.b();
        if (b2 < 200 || b2 >= 300) {
            try {
                return l.a(o.a(g), a2);
            } finally {
                g.close();
            }
        } else if (b2 == 204 || b2 == 205) {
            g.close();
            return l.a(null, a2);
        } else {
            a aVar = new a(g);
            try {
                return l.a(this.a.a((ab) aVar), a2);
            } catch (RuntimeException e2) {
                aVar.f();
                throw e2;
            }
        }
    }

    public void b() {
        e eVar;
        this.c = true;
        synchronized (this) {
            eVar = this.d;
        }
        if (eVar != null) {
            eVar.b();
        }
    }

    public boolean c() {
        boolean z = true;
        if (this.c) {
            return true;
        }
        synchronized (this) {
            if (this.d == null || !this.d.c()) {
                z = false;
            }
        }
        return z;
    }
}
