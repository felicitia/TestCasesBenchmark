package io.reactivex.disposables;

import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.ObjectHelper;
import java.util.concurrent.Future;

public final class Disposables {
    public static Disposable fromRunnable(Runnable runnable) {
        ObjectHelper.requireNonNull(runnable, "run is null");
        return new RunnableDisposable(runnable);
    }

    public static Disposable fromFuture(Future<?> future) {
        ObjectHelper.requireNonNull(future, "future is null");
        return fromFuture(future, true);
    }

    public static Disposable fromFuture(Future<?> future, boolean z) {
        ObjectHelper.requireNonNull(future, "future is null");
        return new FutureDisposable(future, z);
    }

    public static Disposable disposed() {
        return EmptyDisposable.INSTANCE;
    }
}
