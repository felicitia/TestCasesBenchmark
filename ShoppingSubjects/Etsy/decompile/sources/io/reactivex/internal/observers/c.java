package io.reactivex.internal.observers;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.f;

/* compiled from: FullArbiterObserver */
public final class c<T> implements Observer<T> {
    final f<T> a;
    Disposable b;

    public c(f<T> fVar) {
        this.a = fVar;
    }

    public void onSubscribe(Disposable disposable) {
        if (DisposableHelper.validate(this.b, disposable)) {
            this.b = disposable;
            this.a.a(disposable);
        }
    }

    public void onNext(T t) {
        this.a.a(t, this.b);
    }

    public void onError(Throwable th) {
        this.a.a(th, this.b);
    }

    public void onComplete() {
        this.a.b(this.b);
    }
}
