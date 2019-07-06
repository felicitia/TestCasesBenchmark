package io.reactivex.internal.observers;

import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.x;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: ResumeSingleObserver */
public final class e<T> implements x<T> {
    final AtomicReference<Disposable> a;
    final x<? super T> b;

    public e(AtomicReference<Disposable> atomicReference, x<? super T> xVar) {
        this.a = atomicReference;
        this.b = xVar;
    }

    public void onSubscribe(Disposable disposable) {
        DisposableHelper.replace(this.a, disposable);
    }

    public void onSuccess(T t) {
        this.b.onSuccess(t);
    }

    public void onError(Throwable th) {
        this.b.onError(th);
    }
}
