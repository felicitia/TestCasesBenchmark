package io.reactivex;

import io.reactivex.disposables.Disposable;

/* compiled from: SingleObserver */
public interface x<T> {
    void onError(Throwable th);

    void onSubscribe(Disposable disposable);

    void onSuccess(T t);
}
