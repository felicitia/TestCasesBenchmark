package io.reactivex.internal.operators.single;

import io.reactivex.exceptions.a;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.v;
import io.reactivex.x;
import java.util.concurrent.Callable;

/* compiled from: SingleFromCallable */
public final class b<T> extends v<T> {
    final Callable<? extends T> a;

    public b(Callable<? extends T> callable) {
        this.a = callable;
    }

    /* access modifiers changed from: protected */
    public void b(x<? super T> xVar) {
        xVar.onSubscribe(EmptyDisposable.INSTANCE);
        try {
            Object call = this.a.call();
            if (call != null) {
                xVar.onSuccess(call);
            } else {
                xVar.onError(new NullPointerException("The callable returned a null value"));
            }
        } catch (Throwable th) {
            a.b(th);
            xVar.onError(th);
        }
    }
}
