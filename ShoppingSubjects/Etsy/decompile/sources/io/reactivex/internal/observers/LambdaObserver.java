package io.reactivex.internal.observers;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.a;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.Functions;
import java.util.concurrent.atomic.AtomicReference;

public final class LambdaObserver<T> extends AtomicReference<Disposable> implements Observer<T>, Disposable {
    private static final long serialVersionUID = -7251123623727029452L;
    final a onComplete;
    final Consumer<? super Throwable> onError;
    final Consumer<? super T> onNext;
    final Consumer<? super Disposable> onSubscribe;

    public LambdaObserver(Consumer<? super T> consumer, Consumer<? super Throwable> consumer2, a aVar, Consumer<? super Disposable> consumer3) {
        this.onNext = consumer;
        this.onError = consumer2;
        this.onComplete = aVar;
        this.onSubscribe = consumer3;
    }

    public void onSubscribe(Disposable disposable) {
        if (DisposableHelper.setOnce(this, disposable)) {
            try {
                this.onSubscribe.accept(this);
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                disposable.dispose();
                onError(th);
            }
        }
    }

    public void onNext(T t) {
        if (!isDisposed()) {
            try {
                this.onNext.accept(t);
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                ((Disposable) get()).dispose();
                onError(th);
            }
        }
    }

    public void onError(Throwable th) {
        if (!isDisposed()) {
            lazySet(DisposableHelper.DISPOSED);
            try {
                this.onError.accept(th);
            } catch (Throwable th2) {
                io.reactivex.exceptions.a.b(th2);
                io.reactivex.d.a.a((Throwable) new CompositeException(th, th2));
            }
        }
    }

    public void onComplete() {
        if (!isDisposed()) {
            lazySet(DisposableHelper.DISPOSED);
            try {
                this.onComplete.a();
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                io.reactivex.d.a.a(th);
            }
        }
    }

    public void dispose() {
        DisposableHelper.dispose(this);
    }

    public boolean isDisposed() {
        return get() == DisposableHelper.DISPOSED;
    }

    public boolean hasCustomOnError() {
        return this.onError != Functions.f;
    }
}
