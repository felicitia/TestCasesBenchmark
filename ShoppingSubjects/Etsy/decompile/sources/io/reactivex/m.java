package io.reactivex;

import io.reactivex.disposables.Disposable;

/* compiled from: MaybeObserver */
public interface m<T> {
    void onComplete();

    void onError(Throwable th);

    void onSubscribe(Disposable disposable);

    void onSuccess(T t);
}
