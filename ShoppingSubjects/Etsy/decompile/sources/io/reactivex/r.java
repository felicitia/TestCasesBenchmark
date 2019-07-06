package io.reactivex;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.f;

/* compiled from: ObservableEmitter */
public interface r<T> extends f<T> {
    boolean isDisposed();

    void setCancellable(f fVar);

    void setDisposable(Disposable disposable);
}
