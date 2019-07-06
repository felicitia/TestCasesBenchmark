package io.reactivex;

import io.reactivex.disposables.Disposable;

/* compiled from: CompletableObserver */
public interface c {
    void onComplete();

    void onError(Throwable th);

    void onSubscribe(Disposable disposable);
}
