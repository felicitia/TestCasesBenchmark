package com.facebook.internal;

import com.facebook.f;
import java.util.concurrent.Executor;

/* compiled from: WorkQueue */
public class ab {
    static final /* synthetic */ boolean a = true;
    /* access modifiers changed from: private */
    public final Object b;
    /* access modifiers changed from: private */
    public b c;
    private final int d;
    private final Executor e;
    private b f;
    private int g;

    /* compiled from: WorkQueue */
    public interface a {
        boolean a();

        void b();
    }

    /* compiled from: WorkQueue */
    private class b implements a {
        static final /* synthetic */ boolean a = true;
        private final Runnable c;
        private b d;
        private b e;
        private boolean f;

        static {
            Class<ab> cls = ab.class;
        }

        b(Runnable runnable) {
            this.c = runnable;
        }

        public boolean a() {
            synchronized (ab.this.b) {
                if (c()) {
                    return false;
                }
                ab.this.c = a(ab.this.c);
                return true;
            }
        }

        public void b() {
            synchronized (ab.this.b) {
                if (!c()) {
                    ab.this.c = a(ab.this.c);
                    ab.this.c = a(ab.this.c, true);
                }
            }
        }

        public boolean c() {
            return this.f;
        }

        /* access modifiers changed from: 0000 */
        public Runnable d() {
            return this.c;
        }

        /* access modifiers changed from: 0000 */
        public void a(boolean z) {
            this.f = z;
        }

        /* access modifiers changed from: 0000 */
        public b a(b bVar, boolean z) {
            if (!a && this.d != null) {
                throw new AssertionError();
            } else if (a || this.e == null) {
                if (bVar == null) {
                    this.e = this;
                    this.d = this;
                    bVar = this;
                } else {
                    this.d = bVar;
                    this.e = bVar.e;
                    b bVar2 = this.d;
                    this.e.d = this;
                    bVar2.e = this;
                }
                return z ? this : bVar;
            } else {
                throw new AssertionError();
            }
        }

        /* access modifiers changed from: 0000 */
        public b a(b bVar) {
            if (!a && this.d == null) {
                throw new AssertionError();
            } else if (a || this.e != null) {
                if (bVar == this) {
                    if (this.d == this) {
                        bVar = null;
                    } else {
                        bVar = this.d;
                    }
                }
                this.d.e = this.e;
                this.e.d = this.d;
                this.e = null;
                this.d = null;
                return bVar;
            } else {
                throw new AssertionError();
            }
        }
    }

    public ab() {
        this(8);
    }

    public ab(int i) {
        this(i, f.d());
    }

    public ab(int i, Executor executor) {
        this.b = new Object();
        this.f = null;
        this.g = 0;
        this.d = i;
        this.e = executor;
    }

    public a a(Runnable runnable) {
        return a(runnable, true);
    }

    public a a(Runnable runnable, boolean z) {
        b bVar = new b(runnable);
        synchronized (this.b) {
            this.c = bVar.a(this.c, z);
        }
        a();
        return bVar;
    }

    private void a() {
        a((b) null);
    }

    /* access modifiers changed from: private */
    public void a(b bVar) {
        b bVar2;
        synchronized (this.b) {
            if (bVar != null) {
                try {
                    this.f = bVar.a(this.f);
                    this.g--;
                } catch (Throwable th) {
                    while (true) {
                        throw th;
                    }
                }
            }
            if (this.g < this.d) {
                bVar2 = this.c;
                if (bVar2 != null) {
                    this.c = bVar2.a(this.c);
                    this.f = bVar2.a(this.f, false);
                    this.g++;
                    bVar2.a(true);
                }
            } else {
                bVar2 = null;
            }
        }
        if (bVar2 != null) {
            b(bVar2);
        }
    }

    private void b(final b bVar) {
        this.e.execute(new Runnable() {
            public void run() {
                try {
                    bVar.d().run();
                } finally {
                    ab.this.a(bVar);
                }
            }
        });
    }
}
