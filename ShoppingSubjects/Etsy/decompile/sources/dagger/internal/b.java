package dagger.internal;

import javax.a.a;

/* compiled from: DoubleCheck */
public final class b<T> implements a<T> {
    static final /* synthetic */ boolean a = true;
    private static final Object b = new Object();
    private volatile a<T> c;
    private volatile Object d = b;

    private b(a<T> aVar) {
        if (a || aVar != null) {
            this.c = aVar;
            return;
        }
        throw new AssertionError();
    }

    public T b() {
        T t = this.d;
        if (t == b) {
            synchronized (this) {
                t = this.d;
                if (t == b) {
                    t = this.c.b();
                    T t2 = this.d;
                    if (t2 == b || t2 == t) {
                        this.d = t;
                        this.c = null;
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Scoped provider was invoked recursively returning different results: ");
                        sb.append(t2);
                        sb.append(" & ");
                        sb.append(t);
                        sb.append(". This is likely due to a circular dependency.");
                        throw new IllegalStateException(sb.toString());
                    }
                }
            }
        }
        return t;
    }

    public static <T> a<T> a(a<T> aVar) {
        f.a(aVar);
        if (aVar instanceof b) {
            return aVar;
        }
        return new b(aVar);
    }
}
