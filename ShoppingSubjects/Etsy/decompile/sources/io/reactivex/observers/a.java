package io.reactivex.observers;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.util.d;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: DisposableObserver */
public abstract class a<T> implements Observer<T>, Disposable {
    final AtomicReference<Disposable> d = new AtomicReference<>();

    /* access modifiers changed from: protected */
    public void a() {
    }

    public final void onSubscribe(Disposable disposable) {
        if (d.a(this.d, disposable, getClass())) {
            a();
        }
    }

    public final boolean isDisposed() {
        return this.d.get() == DisposableHelper.DISPOSED;
    }

    public final void dispose() {
        DisposableHelper.dispose(this.d);
    }
}
