package io.reactivex.internal.observers;

import io.reactivex.c;
import io.reactivex.d.a;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.OnErrorNotImplementedException;
import io.reactivex.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicReference;

public final class EmptyCompletableObserver extends AtomicReference<Disposable> implements c, Disposable {
    private static final long serialVersionUID = -7545121636549663526L;

    public boolean hasCustomOnError() {
        return false;
    }

    public void dispose() {
        DisposableHelper.dispose(this);
    }

    public boolean isDisposed() {
        return get() == DisposableHelper.DISPOSED;
    }

    public void onComplete() {
        lazySet(DisposableHelper.DISPOSED);
    }

    public void onError(Throwable th) {
        lazySet(DisposableHelper.DISPOSED);
        a.a((Throwable) new OnErrorNotImplementedException(th));
    }

    public void onSubscribe(Disposable disposable) {
        DisposableHelper.setOnce(this, disposable);
    }
}
