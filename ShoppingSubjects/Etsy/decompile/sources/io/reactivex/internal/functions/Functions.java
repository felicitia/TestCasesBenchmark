package io.reactivex.internal.functions;

import io.reactivex.exceptions.OnErrorNotImplementedException;
import io.reactivex.functions.Consumer;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import org.reactivestreams.Subscription;

public final class Functions {
    static final io.reactivex.functions.g<Object, Object> a = new g();
    public static final Runnable b = new d();
    public static final io.reactivex.functions.a c = new a();
    static final Consumer<Object> d = new b();
    public static final Consumer<Throwable> e = new e();
    public static final Consumer<Throwable> f = new l();
    public static final io.reactivex.functions.h g = new c();
    static final io.reactivex.functions.i<Object> h = new m();
    static final io.reactivex.functions.i<Object> i = new f();
    static final Callable<Object> j = new k();
    static final Comparator<Object> k = new j();
    public static final Consumer<Subscription> l = new i();

    enum HashSetCallable implements Callable<Set<Object>> {
        INSTANCE;

        public Set<Object> call() throws Exception {
            return new HashSet();
        }
    }

    enum NaturalComparator implements Comparator<Object> {
        INSTANCE;

        public int compare(Object obj, Object obj2) {
            return ((Comparable) obj).compareTo(obj2);
        }
    }

    static final class a implements io.reactivex.functions.a {
        public void a() {
        }

        public String toString() {
            return "EmptyAction";
        }

        a() {
        }
    }

    static final class b implements Consumer<Object> {
        public void accept(Object obj) {
        }

        public String toString() {
            return "EmptyConsumer";
        }

        b() {
        }
    }

    static final class c implements io.reactivex.functions.h {
        c() {
        }
    }

    static final class d implements Runnable {
        public void run() {
        }

        public String toString() {
            return "EmptyRunnable";
        }

        d() {
        }
    }

    static final class e implements Consumer<Throwable> {
        e() {
        }

        /* renamed from: a */
        public void accept(Throwable th) {
            io.reactivex.d.a.a(th);
        }
    }

    static final class f implements io.reactivex.functions.i<Object> {
        public boolean test(Object obj) {
            return false;
        }

        f() {
        }
    }

    static final class g implements io.reactivex.functions.g<Object, Object> {
        public Object apply(Object obj) {
            return obj;
        }

        public String toString() {
            return "IdentityFunction";
        }

        g() {
        }
    }

    static final class h<T, U> implements io.reactivex.functions.g<T, U>, Callable<U> {
        final U a;

        h(U u) {
            this.a = u;
        }

        public U call() throws Exception {
            return this.a;
        }

        public U apply(T t) throws Exception {
            return this.a;
        }
    }

    static final class i implements Consumer<Subscription> {
        i() {
        }

        /* renamed from: a */
        public void accept(Subscription subscription) throws Exception {
            subscription.request(Long.MAX_VALUE);
        }
    }

    static final class j implements Comparator<Object> {
        j() {
        }

        public int compare(Object obj, Object obj2) {
            return ((Comparable) obj).compareTo(obj2);
        }
    }

    static final class k implements Callable<Object> {
        public Object call() {
            return null;
        }

        k() {
        }
    }

    static final class l implements Consumer<Throwable> {
        l() {
        }

        /* renamed from: a */
        public void accept(Throwable th) {
            io.reactivex.d.a.a((Throwable) new OnErrorNotImplementedException(th));
        }
    }

    static final class m implements io.reactivex.functions.i<Object> {
        public boolean test(Object obj) {
            return true;
        }

        m() {
        }
    }

    public static <T> io.reactivex.functions.g<T, T> a() {
        return a;
    }

    public static <T> Consumer<T> b() {
        return d;
    }

    public static <T> io.reactivex.functions.i<T> c() {
        return h;
    }

    public static <T> Callable<T> a(T t) {
        return new h(t);
    }
}
