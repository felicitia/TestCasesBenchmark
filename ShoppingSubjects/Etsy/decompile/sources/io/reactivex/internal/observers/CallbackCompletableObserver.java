package io.reactivex.internal.observers;

import io.reactivex.c;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.OnErrorNotImplementedException;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.a;
import io.reactivex.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicReference;

public final class CallbackCompletableObserver extends AtomicReference<Disposable> implements c, Disposable, Consumer<Throwable> {
    private static final long serialVersionUID = -4361286194466301354L;
    final a onComplete;
    final Consumer<? super Throwable> onError;

    public CallbackCompletableObserver(a aVar) {
        this.onError = this;
        this.onComplete = aVar;
    }

    public CallbackCompletableObserver(Consumer<? super Throwable> consumer, a aVar) {
        this.onError = consumer;
        this.onComplete = aVar;
    }

    public void accept(Throwable th) {
        io.reactivex.d.a.a((Throwable) new OnErrorNotImplementedException(th));
    }

    public void onComplete() {
        try {
            this.onComplete.a();
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            io.reactivex.d.a.a(th);
        }
        lazySet(DisposableHelper.DISPOSED);
    }

    public void onError(Throwable th) {
        try {
            this.onError.accept(th);
        } catch (Throwable th2) {
            io.reactivex.exceptions.a.b(th2);
            io.reactivex.d.a.a(th2);
        }
        lazySet(DisposableHelper.DISPOSED);
    }

    public void onSubscribe(Disposable disposable) {
        DisposableHelper.setOnce(this, disposable);
    }

    public void dispose() {
        DisposableHelper.dispose(this);
    }

    public boolean isDisposed() {
        return get() == DisposableHelper.DISPOSED;
    }

    public boolean hasCustomOnError() {
        return this.onError != this;
    }
}
