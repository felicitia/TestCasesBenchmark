package io.reactivex.internal.observers;

import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.a;
import io.reactivex.functions.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.x;
import java.util.concurrent.atomic.AtomicReference;

public final class BiConsumerSingleObserver<T> extends AtomicReference<Disposable> implements Disposable, x<T> {
    private static final long serialVersionUID = 4943102778943297569L;
    final b<? super T, ? super Throwable> onCallback;

    public BiConsumerSingleObserver(b<? super T, ? super Throwable> bVar) {
        this.onCallback = bVar;
    }

    public void onError(Throwable th) {
        try {
            lazySet(DisposableHelper.DISPOSED);
            this.onCallback.a(null, th);
        } catch (Throwable th2) {
            a.b(th2);
            io.reactivex.d.a.a((Throwable) new CompositeException(th, th2));
        }
    }

    public void onSubscribe(Disposable disposable) {
        DisposableHelper.setOnce(this, disposable);
    }

    public void onSuccess(T t) {
        try {
            lazySet(DisposableHelper.DISPOSED);
            this.onCallback.a(t, null);
        } catch (Throwable th) {
            a.b(th);
            io.reactivex.d.a.a(th);
        }
    }

    public void dispose() {
        DisposableHelper.dispose(this);
    }

    public boolean isDisposed() {
        return get() == DisposableHelper.DISPOSED;
    }
}
