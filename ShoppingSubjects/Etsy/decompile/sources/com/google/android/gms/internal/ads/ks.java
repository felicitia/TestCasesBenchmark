package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

@bu
final class ks<T> implements kt<T> {
    private final T a;
    private final kv b = new kv();

    ks(T t) {
        this.a = t;
        this.b.a();
    }

    public final void a(Runnable runnable, Executor executor) {
        this.b.a(runnable, executor);
    }

    public final boolean cancel(boolean z) {
        return false;
    }

    public final T get() {
        return this.a;
    }

    public final T get(long j, TimeUnit timeUnit) {
        return this.a;
    }

    public final boolean isCancelled() {
        return false;
    }

    public final boolean isDone() {
        return true;
    }
}
