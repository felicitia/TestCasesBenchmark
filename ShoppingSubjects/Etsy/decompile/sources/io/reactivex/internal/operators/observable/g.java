package io.reactivex.internal.operators.observable;

import io.reactivex.Observer;
import io.reactivex.internal.functions.a;
import io.reactivex.internal.observers.DeferredScalarDisposable;
import io.reactivex.q;
import java.util.concurrent.Callable;

/* compiled from: ObservableFromCallable */
public final class g<T> extends q<T> implements Callable<T> {
    final Callable<? extends T> a;

    public g(Callable<? extends T> callable) {
        this.a = callable;
    }

    public void a(Observer<? super T> observer) {
        DeferredScalarDisposable deferredScalarDisposable = new DeferredScalarDisposable(observer);
        observer.onSubscribe(deferredScalarDisposable);
        if (!deferredScalarDisposable.isDisposed()) {
            try {
                deferredScalarDisposable.complete(a.a(this.a.call(), "Callable returned null"));
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                if (!deferredScalarDisposable.isDisposed()) {
                    observer.onError(th);
                } else {
                    io.reactivex.d.a.a(th);
                }
            }
        }
    }

    public T call() throws Exception {
        return a.a(this.a.call(), "The callable returned a null value");
    }
}
