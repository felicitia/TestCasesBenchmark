package dagger.internal;

import java.lang.ref.WeakReference;
import javax.a.a;

/* compiled from: ReferenceReleasingProvider */
public final class g<T> implements a<T> {
    static final /* synthetic */ boolean a = true;
    private static final Object b = new Object();
    private final a<T> c;
    private volatile Object d;
    private volatile WeakReference<T> e;

    public void a() {
        Object obj = this.d;
        if (obj != null && obj != b) {
            synchronized (this) {
                this.e = new WeakReference<>(obj);
                this.d = null;
            }
        }
    }

    public void c() {
        Object obj = this.d;
        if (this.e != null && obj == null) {
            synchronized (this) {
                Object obj2 = this.d;
                if (this.e != null && obj2 == null) {
                    Object obj3 = this.e.get();
                    if (obj3 != null) {
                        this.d = obj3;
                        this.e = null;
                    }
                }
            }
        }
    }

    public T b() {
        T d2 = d();
        if (d2 == null) {
            synchronized (this) {
                d2 = d();
                if (d2 == null) {
                    d2 = this.c.b();
                    if (d2 == null) {
                        d2 = b;
                    }
                    this.d = d2;
                }
            }
        }
        if (d2 == b) {
            return null;
        }
        return d2;
    }

    private Object d() {
        Object obj = this.d;
        if (obj != null) {
            return obj;
        }
        if (this.e != null) {
            return this.e.get();
        }
        return null;
    }
}
