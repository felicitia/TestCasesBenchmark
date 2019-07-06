package com.google.android.gms.internal.ads;

import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RunnableFuture;

@bu
public abstract class kc extends AbstractExecutorService implements ky {
    /* renamed from: a */
    public final kt<?> submit(Runnable runnable) throws RejectedExecutionException {
        return (kt) super.submit(runnable);
    }

    /* renamed from: a */
    public final <T> kt<T> submit(Callable<T> callable) throws RejectedExecutionException {
        return (kt) super.submit(callable);
    }

    /* access modifiers changed from: protected */
    public final <T> RunnableFuture<T> newTaskFor(Runnable runnable, T t) {
        return new kx(runnable, t);
    }

    /* access modifiers changed from: protected */
    public final <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        return new kx(callable);
    }

    public /* synthetic */ Future submit(Runnable runnable, Object obj) {
        return (kt) super.submit(runnable, obj);
    }
}
