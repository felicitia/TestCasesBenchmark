package io.reactivex.internal.operators.maybe;

import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.a;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.Functions;
import io.reactivex.m;
import java.util.concurrent.atomic.AtomicReference;

public final class MaybeCallbackObserver<T> extends AtomicReference<Disposable> implements Disposable, m<T> {
    private static final long serialVersionUID = -6076952298809384986L;
    final a onComplete;
    final Consumer<? super Throwable> onError;
    final Consumer<? super T> onSuccess;

    public MaybeCallbackObserver(Consumer<? super T> consumer, Consumer<? super Throwable> consumer2, a aVar) {
        this.onSuccess = consumer;
        this.onError = consumer2;
        this.onComplete = aVar;
    }

    public void dispose() {
        DisposableHelper.dispose(this);
    }

    public boolean isDisposed() {
        return DisposableHelper.isDisposed((Disposable) get());
    }

    public void onSubscribe(Disposable disposable) {
        DisposableHelper.setOnce(this, disposable);
    }

    public void onSuccess(T t) {
        lazySet(DisposableHelper.DISPOSED);
        try {
            this.onSuccess.accept(t);
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            io.reactivex.d.a.a(th);
        }
    }

    public void onError(Throwable th) {
        lazySet(DisposableHelper.DISPOSED);
        try {
            this.onError.accept(th);
        } catch (Throwable th2) {
            io.reactivex.exceptions.a.b(th2);
            io.reactivex.d.a.a((Throwable) new CompositeException(th, th2));
        }
    }

    public void onComplete() {
        lazySet(DisposableHelper.DISPOSED);
        try {
            this.onComplete.a();
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            io.reactivex.d.a.a(th);
        }
    }

    public boolean hasCustomOnError() {
        return this.onError != Functions.f;
    }
}
