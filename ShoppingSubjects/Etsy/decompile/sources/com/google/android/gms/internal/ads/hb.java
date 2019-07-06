package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@bu
public final class hb {
    public static final ky a;
    private static final ky b;

    static {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, Integer.MAX_VALUE, 10, TimeUnit.SECONDS, new SynchronousQueue(), a("Default"));
        a = kz.a(threadPoolExecutor);
        ThreadPoolExecutor threadPoolExecutor2 = new ThreadPoolExecutor(5, 5, 10, TimeUnit.SECONDS, new LinkedBlockingQueue(), a("Loader"));
        threadPoolExecutor2.allowCoreThreadTimeOut(true);
        b = kz.a(threadPoolExecutor2);
    }

    public static kt<?> a(Runnable runnable) {
        return a.a(runnable);
    }

    public static <T> kt<T> a(Callable<T> callable) {
        return a.a(callable);
    }

    private static ThreadFactory a(String str) {
        return new hc(str);
    }

    public static kt<?> b(Runnable runnable) {
        return b.a(runnable);
    }
}
