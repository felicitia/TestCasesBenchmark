package io.reactivex.internal.operators.maybe;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.a;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.m;
import java.util.concurrent.atomic.AtomicInteger;

public final class MaybeDoFinally<T> extends a<T, T> {
    final a b;

    static final class DoFinallyObserver<T> extends AtomicInteger implements Disposable, m<T> {
        private static final long serialVersionUID = 4109457741734051389L;
        final m<? super T> actual;
        Disposable d;
        final a onFinally;

        DoFinallyObserver(m<? super T> mVar, a aVar) {
            this.actual = mVar;
            this.onFinally = aVar;
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.d, disposable)) {
                this.d = disposable;
                this.actual.onSubscribe(this);
            }
        }

        public void onSuccess(T t) {
            this.actual.onSuccess(t);
            runFinally();
        }

        public void onError(Throwable th) {
            this.actual.onError(th);
            runFinally();
        }

        public void onComplete() {
            this.actual.onComplete();
            runFinally();
        }

        public void dispose() {
            this.d.dispose();
            runFinally();
        }

        public boolean isDisposed() {
            return this.d.isDisposed();
        }

        /* access modifiers changed from: 0000 */
        public void runFinally() {
            if (compareAndSet(0, 1)) {
                try {
                    this.onFinally.a();
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    io.reactivex.d.a.a(th);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void b(m<? super T> mVar) {
        this.a.a(new DoFinallyObserver(mVar, this.b));
    }
}
