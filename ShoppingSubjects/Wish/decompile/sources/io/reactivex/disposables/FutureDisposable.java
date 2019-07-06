package io.reactivex.disposables;

import java.util.concurrent.Future;

final class FutureDisposable extends ReferenceDisposable<Future<?>> {
    private final boolean allowInterrupt;

    FutureDisposable(Future<?> future, boolean z) {
        super(future);
        this.allowInterrupt = z;
    }

    /* access modifiers changed from: protected */
    public void onDisposed(Future<?> future) {
        future.cancel(this.allowInterrupt);
    }
}
