package io.reactivex.e;

import io.reactivex.internal.schedulers.i;
import io.reactivex.internal.schedulers.j;
import io.reactivex.u;
import java.util.concurrent.Callable;

/* compiled from: Schedulers */
public final class a {
    static final u a = io.reactivex.d.a.d(new h());
    static final u b = io.reactivex.d.a.a((Callable<u>) new b<u>());
    static final u c = io.reactivex.d.a.b((Callable<u>) new c<u>());
    static final u d = j.c();
    static final u e = io.reactivex.d.a.c((Callable<u>) new f<u>());

    /* renamed from: io.reactivex.e.a$a reason: collision with other inner class name */
    /* compiled from: Schedulers */
    static final class C0183a {
        static final u a = new io.reactivex.internal.schedulers.a();
    }

    /* compiled from: Schedulers */
    static final class b implements Callable<u> {
        b() {
        }

        /* renamed from: a */
        public u call() throws Exception {
            return C0183a.a;
        }
    }

    /* compiled from: Schedulers */
    static final class c implements Callable<u> {
        c() {
        }

        /* renamed from: a */
        public u call() throws Exception {
            return d.a;
        }
    }

    /* compiled from: Schedulers */
    static final class d {
        static final u a = new io.reactivex.internal.schedulers.d();
    }

    /* compiled from: Schedulers */
    static final class e {
        static final u a = new io.reactivex.internal.schedulers.e();
    }

    /* compiled from: Schedulers */
    static final class f implements Callable<u> {
        f() {
        }

        /* renamed from: a */
        public u call() throws Exception {
            return e.a;
        }
    }

    /* compiled from: Schedulers */
    static final class g {
        static final u a = new i();
    }

    /* compiled from: Schedulers */
    static final class h implements Callable<u> {
        h() {
        }

        /* renamed from: a */
        public u call() throws Exception {
            return g.a;
        }
    }

    public static u a() {
        return io.reactivex.d.a.a(b);
    }

    public static u b() {
        return io.reactivex.d.a.b(c);
    }

    public static u c() {
        return io.reactivex.d.a.c(a);
    }
}
