package io.reactivex.internal.operators.observable;

import io.reactivex.Observer;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.a;
import io.reactivex.q;
import java.util.concurrent.Callable;

/* compiled from: ObservableError */
public final class e<T> extends q<T> {
    final Callable<? extends Throwable> a;

    public e(Callable<? extends Throwable> callable) {
        this.a = callable;
    }

    public void a(Observer<? super T> observer) {
        try {
            th = (Throwable) a.a(this.a.call(), "Callable returned null throwable. Null values are generally not allowed in 2.x operators and sources.");
        } catch (Throwable th) {
            th = th;
            io.reactivex.exceptions.a.b(th);
        }
        EmptyDisposable.error(th, observer);
    }
}
