package retrofit2;

import okhttp3.aa;
import okhttp3.ab;
import okhttp3.s;

/* compiled from: Response */
public final class l<T> {
    private final aa a;
    private final T b;
    private final ab c;

    public static <T> l<T> a(T t, aa aaVar) {
        o.a(aaVar, "rawResponse == null");
        if (aaVar.c()) {
            return new l<>(aaVar, t, null);
        }
        throw new IllegalArgumentException("rawResponse must be successful response");
    }

    public static <T> l<T> a(ab abVar, aa aaVar) {
        o.a(abVar, "body == null");
        o.a(aaVar, "rawResponse == null");
        if (!aaVar.c()) {
            return new l<>(aaVar, null, abVar);
        }
        throw new IllegalArgumentException("rawResponse should not be successful response");
    }

    private l(aa aaVar, T t, ab abVar) {
        this.a = aaVar;
        this.b = t;
        this.c = abVar;
    }

    public int a() {
        return this.a.b();
    }

    public String b() {
        return this.a.d();
    }

    public s c() {
        return this.a.f();
    }

    public boolean d() {
        return this.a.c();
    }

    public T e() {
        return this.b;
    }

    public ab f() {
        return this.c;
    }

    public String toString() {
        return this.a.toString();
    }
}
