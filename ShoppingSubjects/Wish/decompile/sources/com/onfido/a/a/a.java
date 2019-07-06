package com.onfido.a.a;

public final class a<T> implements com.onfido.b.a.a<T> {
    static final /* synthetic */ boolean a = true;
    private static final Object b = new Object();
    private volatile com.onfido.b.a.a<T> c;
    private volatile Object d = b;

    private a(com.onfido.b.a.a<T> aVar) {
        if (a || aVar != null) {
            this.c = aVar;
            return;
        }
        throw new AssertionError();
    }

    public static <T> com.onfido.b.a.a<T> a(com.onfido.b.a.a<T> aVar) {
        d.a(aVar);
        return aVar instanceof a ? aVar : new a(aVar);
    }

    public T get() {
        T t;
        T t2 = this.d;
        if (t2 != b) {
            return t2;
        }
        synchronized (this) {
            t = this.d;
            if (t == b) {
                t = this.c.get();
                T t3 = this.d;
                if (t3 == b || t3 == t) {
                    this.d = t;
                    this.c = null;
                } else {
                    String valueOf = String.valueOf("Scoped provider was invoked recursively returning different results: ");
                    String valueOf2 = String.valueOf(t3);
                    String valueOf3 = String.valueOf(t);
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 3 + String.valueOf(valueOf2).length() + String.valueOf(valueOf3).length());
                    sb.append(valueOf);
                    sb.append(valueOf2);
                    sb.append(" & ");
                    sb.append(valueOf3);
                    throw new IllegalStateException(sb.toString());
                }
            }
        }
        return t;
    }
}
