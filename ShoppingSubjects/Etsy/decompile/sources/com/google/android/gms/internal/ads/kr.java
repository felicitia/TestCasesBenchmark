package com.google.android.gms.internal.ads;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

@bu
final class kr<T> implements kt<T> {
    private final Throwable a;
    private final kv b = new kv();

    kr(Throwable th) {
        this.a = th;
        this.b.a();
    }

    public final void a(Runnable runnable, Executor executor) {
        this.b.a(runnable, executor);
    }

    public final boolean cancel(boolean z) {
        return false;
    }

    public final T get() throws ExecutionException {
        throw new ExecutionException(this.a);
    }

    public final T get(long j, TimeUnit timeUnit) throws ExecutionException {
        throw new ExecutionException(this.a);
    }

    public final boolean isCancelled() {
        return false;
    }

    public final boolean isDone() {
        return true;
    }
}
