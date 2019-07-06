package io.reactivex.disposables;

import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.a;

/* compiled from: Disposables */
public final class b {
    public static Disposable a(Runnable runnable) {
        a.a(runnable, "run is null");
        return new RunnableDisposable(runnable);
    }

    public static Disposable a() {
        return EmptyDisposable.INSTANCE;
    }
}
