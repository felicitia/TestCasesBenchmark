package com.bumptech.glide.load.engine;

import android.os.Looper;
import com.bumptech.glide.load.b;

/* compiled from: EngineResource */
class g<Z> implements i<Z> {
    private final i<Z> a;
    private final boolean b;
    private a c;
    private b d;
    private int e;
    private boolean f;

    /* compiled from: EngineResource */
    interface a {
        void b(b bVar, g<?> gVar);
    }

    g(i<Z> iVar, boolean z) {
        if (iVar == null) {
            throw new NullPointerException("Wrapped resource must not be null");
        }
        this.a = iVar;
        this.b = z;
    }

    /* access modifiers changed from: 0000 */
    public void a(b bVar, a aVar) {
        this.d = bVar;
        this.c = aVar;
    }

    /* access modifiers changed from: 0000 */
    public boolean a() {
        return this.b;
    }

    public Z b() {
        return this.a.b();
    }

    public int c() {
        return this.a.c();
    }

    public void d() {
        if (this.e > 0) {
            throw new IllegalStateException("Cannot recycle a resource while it is still acquired");
        } else if (this.f) {
            throw new IllegalStateException("Cannot recycle a resource that has already been recycled");
        } else {
            this.f = true;
            this.a.d();
        }
    }

    /* access modifiers changed from: 0000 */
    public void e() {
        if (this.f) {
            throw new IllegalStateException("Cannot acquire a recycled resource");
        } else if (!Looper.getMainLooper().equals(Looper.myLooper())) {
            throw new IllegalThreadStateException("Must call acquire on the main thread");
        } else {
            this.e++;
        }
    }

    /* access modifiers changed from: 0000 */
    public void f() {
        if (this.e <= 0) {
            throw new IllegalStateException("Cannot release a recycled or not yet acquired resource");
        } else if (!Looper.getMainLooper().equals(Looper.myLooper())) {
            throw new IllegalThreadStateException("Must call release on the main thread");
        } else {
            int i = this.e - 1;
            this.e = i;
            if (i == 0) {
                this.c.b(this.d, this);
            }
        }
    }
}
