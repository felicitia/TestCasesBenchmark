package io.reactivex.internal.operators.single;

import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.v;
import io.reactivex.x;
import java.util.concurrent.Callable;

/* compiled from: SingleError */
public final class a<T> extends v<T> {
    final Callable<? extends Throwable> a;

    public a(Callable<? extends Throwable> callable) {
        this.a = callable;
    }

    /* access modifiers changed from: protected */
    public void b(x<? super T> xVar) {
        try {
            th = (Throwable) io.reactivex.internal.functions.a.a(this.a.call(), "Callable returned null throwable. Null values are generally not allowed in 2.x operators and sources.");
        } catch (Throwable th) {
            th = th;
            io.reactivex.exceptions.a.b(th);
        }
        EmptyDisposable.error(th, xVar);
    }
}
