package io.reactivex.internal.operators.single;

import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.functions.g;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.a;
import io.reactivex.internal.observers.e;
import io.reactivex.v;
import io.reactivex.x;
import io.reactivex.z;
import java.util.concurrent.atomic.AtomicReference;

public final class SingleResumeNext<T> extends v<T> {
    final z<? extends T> a;
    final g<? super Throwable, ? extends z<? extends T>> b;

    static final class ResumeMainSingleObserver<T> extends AtomicReference<Disposable> implements Disposable, x<T> {
        private static final long serialVersionUID = -5314538511045349925L;
        final x<? super T> actual;
        final g<? super Throwable, ? extends z<? extends T>> nextFunction;

        ResumeMainSingleObserver(x<? super T> xVar, g<? super Throwable, ? extends z<? extends T>> gVar) {
            this.actual = xVar;
            this.nextFunction = gVar;
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.setOnce(this, disposable)) {
                this.actual.onSubscribe(this);
            }
        }

        public void onSuccess(T t) {
            this.actual.onSuccess(t);
        }

        public void onError(Throwable th) {
            try {
                ((z) a.a(this.nextFunction.apply(th), "The nextFunction returned a null SingleSource.")).a(new e(this, this.actual));
            } catch (Throwable th2) {
                io.reactivex.exceptions.a.b(th2);
                this.actual.onError(new CompositeException(th, th2));
            }
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((Disposable) get());
        }
    }

    public SingleResumeNext(z<? extends T> zVar, g<? super Throwable, ? extends z<? extends T>> gVar) {
        this.a = zVar;
        this.b = gVar;
    }

    /* access modifiers changed from: protected */
    public void b(x<? super T> xVar) {
        this.a.a(new ResumeMainSingleObserver(xVar, this.b));
    }
}
