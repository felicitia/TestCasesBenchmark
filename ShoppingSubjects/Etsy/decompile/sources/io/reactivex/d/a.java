package io.reactivex.d;

import io.reactivex.Observer;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.exceptions.OnErrorNotImplementedException;
import io.reactivex.exceptions.UndeliverableException;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.c;
import io.reactivex.functions.e;
import io.reactivex.functions.g;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.k;
import io.reactivex.m;
import io.reactivex.q;
import io.reactivex.u;
import io.reactivex.v;
import io.reactivex.x;
import java.util.concurrent.Callable;

/* compiled from: RxJavaPlugins */
public final class a {
    static volatile Consumer<? super Throwable> a;
    static volatile g<? super Runnable, ? extends Runnable> b;
    static volatile g<? super Callable<u>, ? extends u> c;
    static volatile g<? super Callable<u>, ? extends u> d;
    static volatile g<? super Callable<u>, ? extends u> e;
    static volatile g<? super Callable<u>, ? extends u> f;
    static volatile g<? super u, ? extends u> g;
    static volatile g<? super u, ? extends u> h;
    static volatile g<? super u, ? extends u> i;
    static volatile g<? super io.reactivex.g, ? extends io.reactivex.g> j;
    static volatile g<? super q, ? extends q> k;
    static volatile g<? super k, ? extends k> l;
    static volatile g<? super v, ? extends v> m;
    static volatile g<? super io.reactivex.a, ? extends io.reactivex.a> n;
    static volatile c<? super io.reactivex.g, ? super org.reactivestreams.c, ? extends org.reactivestreams.c> o;
    static volatile c<? super k, ? super m, ? extends m> p;
    static volatile c<? super q, ? super Observer, ? extends Observer> q;
    static volatile c<? super v, ? super x, ? extends x> r;
    static volatile c<? super io.reactivex.a, ? super io.reactivex.c, ? extends io.reactivex.c> s;
    static volatile e t;
    static volatile boolean u;

    public static boolean a() {
        return u;
    }

    public static u a(Callable<u> callable) {
        io.reactivex.internal.functions.a.a(callable, "Scheduler Callable can't be null");
        g<? super Callable<u>, ? extends u> gVar = c;
        if (gVar == null) {
            return e(callable);
        }
        return a(gVar, callable);
    }

    public static u b(Callable<u> callable) {
        io.reactivex.internal.functions.a.a(callable, "Scheduler Callable can't be null");
        g<? super Callable<u>, ? extends u> gVar = e;
        if (gVar == null) {
            return e(callable);
        }
        return a(gVar, callable);
    }

    public static u c(Callable<u> callable) {
        io.reactivex.internal.functions.a.a(callable, "Scheduler Callable can't be null");
        g<? super Callable<u>, ? extends u> gVar = f;
        if (gVar == null) {
            return e(callable);
        }
        return a(gVar, callable);
    }

    public static u d(Callable<u> callable) {
        io.reactivex.internal.functions.a.a(callable, "Scheduler Callable can't be null");
        g<? super Callable<u>, ? extends u> gVar = d;
        if (gVar == null) {
            return e(callable);
        }
        return a(gVar, callable);
    }

    public static u a(u uVar) {
        g<? super u, ? extends u> gVar = g;
        if (gVar == null) {
            return uVar;
        }
        return (u) a(gVar, (T) uVar);
    }

    public static void a(Throwable th) {
        Consumer<? super Throwable> consumer = a;
        if (th == null) {
            th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        } else if (!b(th)) {
            th = new UndeliverableException(th);
        }
        if (consumer != null) {
            try {
                consumer.accept(th);
                return;
            } catch (Throwable th2) {
                com.google.a.a.a.a.a.a.a(th2);
                c(th2);
            }
        }
        com.google.a.a.a.a.a.a.a(th);
        c(th);
    }

    static boolean b(Throwable th) {
        if (!(th instanceof OnErrorNotImplementedException) && !(th instanceof MissingBackpressureException) && !(th instanceof IllegalStateException) && !(th instanceof NullPointerException) && !(th instanceof IllegalArgumentException) && !(th instanceof CompositeException)) {
            return false;
        }
        return true;
    }

    static void c(Throwable th) {
        Thread currentThread = Thread.currentThread();
        currentThread.getUncaughtExceptionHandler().uncaughtException(currentThread, th);
    }

    public static u b(u uVar) {
        g<? super u, ? extends u> gVar = i;
        if (gVar == null) {
            return uVar;
        }
        return (u) a(gVar, (T) uVar);
    }

    public static Runnable a(Runnable runnable) {
        g<? super Runnable, ? extends Runnable> gVar = b;
        if (gVar == null) {
            return runnable;
        }
        return (Runnable) a(gVar, (T) runnable);
    }

    public static u c(u uVar) {
        g<? super u, ? extends u> gVar = h;
        if (gVar == null) {
            return uVar;
        }
        return (u) a(gVar, (T) uVar);
    }

    public static <T> org.reactivestreams.c<? super T> a(io.reactivex.g<T> gVar, org.reactivestreams.c<? super T> cVar) {
        c<? super io.reactivex.g, ? super org.reactivestreams.c, ? extends org.reactivestreams.c> cVar2 = o;
        return cVar2 != null ? (org.reactivestreams.c) a(cVar2, gVar, cVar) : cVar;
    }

    public static <T> Observer<? super T> a(q<T> qVar, Observer<? super T> observer) {
        c<? super q, ? super Observer, ? extends Observer> cVar = q;
        return cVar != null ? (Observer) a(cVar, qVar, observer) : observer;
    }

    public static <T> x<? super T> a(v<T> vVar, x<? super T> xVar) {
        c<? super v, ? super x, ? extends x> cVar = r;
        return cVar != null ? (x) a(cVar, vVar, xVar) : xVar;
    }

    public static io.reactivex.c a(io.reactivex.a aVar, io.reactivex.c cVar) {
        c<? super io.reactivex.a, ? super io.reactivex.c, ? extends io.reactivex.c> cVar2 = s;
        return cVar2 != null ? (io.reactivex.c) a(cVar2, aVar, cVar) : cVar;
    }

    public static <T> m<? super T> a(k<T> kVar, m<? super T> mVar) {
        c<? super k, ? super m, ? extends m> cVar = p;
        return cVar != null ? (m) a(cVar, kVar, mVar) : mVar;
    }

    public static <T> k<T> a(k<T> kVar) {
        g<? super k, ? extends k> gVar = l;
        return gVar != null ? (k) a(gVar, (T) kVar) : kVar;
    }

    public static <T> io.reactivex.g<T> a(io.reactivex.g<T> gVar) {
        g<? super io.reactivex.g, ? extends io.reactivex.g> gVar2 = j;
        return gVar2 != null ? (io.reactivex.g) a(gVar2, (T) gVar) : gVar;
    }

    public static <T> q<T> a(q<T> qVar) {
        g<? super q, ? extends q> gVar = k;
        return gVar != null ? (q) a(gVar, (T) qVar) : qVar;
    }

    public static <T> v<T> a(v<T> vVar) {
        g<? super v, ? extends v> gVar = m;
        return gVar != null ? (v) a(gVar, (T) vVar) : vVar;
    }

    public static io.reactivex.a a(io.reactivex.a aVar) {
        g<? super io.reactivex.a, ? extends io.reactivex.a> gVar = n;
        return gVar != null ? (io.reactivex.a) a(gVar, (T) aVar) : aVar;
    }

    public static boolean b() {
        e eVar = t;
        if (eVar == null) {
            return false;
        }
        try {
            return eVar.getAsBoolean();
        } catch (Throwable th) {
            throw ExceptionHelper.a(th);
        }
    }

    static <T, R> R a(g<T, R> gVar, T t2) {
        try {
            return gVar.apply(t2);
        } catch (Throwable th) {
            throw ExceptionHelper.a(th);
        }
    }

    static <T, U, R> R a(c<T, U, R> cVar, T t2, U u2) {
        try {
            return cVar.apply(t2, u2);
        } catch (Throwable th) {
            throw ExceptionHelper.a(th);
        }
    }

    static u e(Callable<u> callable) {
        try {
            return (u) io.reactivex.internal.functions.a.a(callable.call(), "Scheduler Callable result can't be null");
        } catch (Throwable th) {
            throw ExceptionHelper.a(th);
        }
    }

    static u a(g<? super Callable<u>, ? extends u> gVar, Callable<u> callable) {
        return (u) io.reactivex.internal.functions.a.a(a(gVar, (T) callable), "Scheduler Callable result can't be null");
    }
}
