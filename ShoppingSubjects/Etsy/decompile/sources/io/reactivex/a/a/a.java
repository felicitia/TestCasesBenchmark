package io.reactivex.a.a;

import io.reactivex.functions.g;
import io.reactivex.u;
import java.util.concurrent.Callable;

/* compiled from: RxAndroidPlugins */
public final class a {
    private static volatile g<Callable<u>, u> a;
    private static volatile g<u, u> b;

    public static u a(Callable<u> callable) {
        if (callable == null) {
            throw new NullPointerException("scheduler == null");
        }
        g<Callable<u>, u> gVar = a;
        if (gVar == null) {
            return b(callable);
        }
        return a(gVar, callable);
    }

    public static u a(u uVar) {
        if (uVar == null) {
            throw new NullPointerException("scheduler == null");
        }
        g<u, u> gVar = b;
        if (gVar == null) {
            return uVar;
        }
        return (u) a(gVar, (T) uVar);
    }

    static u b(Callable<u> callable) {
        try {
            u uVar = (u) callable.call();
            if (uVar != null) {
                return uVar;
            }
            throw new NullPointerException("Scheduler Callable returned null");
        } catch (Throwable th) {
            throw io.reactivex.exceptions.a.a(th);
        }
    }

    static u a(g<Callable<u>, u> gVar, Callable<u> callable) {
        u uVar = (u) a(gVar, (T) callable);
        if (uVar != null) {
            return uVar;
        }
        throw new NullPointerException("Scheduler Callable returned null");
    }

    static <T, R> R a(g<T, R> gVar, T t) {
        try {
            return gVar.apply(t);
        } catch (Throwable th) {
            throw io.reactivex.exceptions.a.a(th);
        }
    }
}
