package io.reactivex.internal.operators.single;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.a;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.v;
import io.reactivex.x;
import io.reactivex.z;
import java.util.concurrent.atomic.AtomicInteger;

public final class SingleDoFinally<T> extends v<T> {
    final z<T> a;
    final a b;

    static final class DoFinallyObserver<T> extends AtomicInteger implements Disposable, x<T> {
        private static final long serialVersionUID = 4109457741734051389L;
        final x<? super T> actual;
        Disposable d;
        final a onFinally;

        DoFinallyObserver(x<? super T> xVar, a aVar) {
            this.actual = xVar;
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

    public SingleDoFinally(z<T> zVar, a aVar) {
        this.a = zVar;
        this.b = aVar;
    }

    /* access modifiers changed from: protected */
    public void b(x<? super T> xVar) {
        this.a.a(new DoFinallyObserver(xVar, this.b));
    }
}
