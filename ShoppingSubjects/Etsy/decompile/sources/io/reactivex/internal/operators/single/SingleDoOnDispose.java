package io.reactivex.internal.operators.single;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.a;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.v;
import io.reactivex.x;
import io.reactivex.z;
import java.util.concurrent.atomic.AtomicReference;

public final class SingleDoOnDispose<T> extends v<T> {
    final z<T> a;
    final a b;

    static final class DoOnDisposeObserver<T> extends AtomicReference<a> implements Disposable, x<T> {
        private static final long serialVersionUID = -8583764624474935784L;
        final x<? super T> actual;
        Disposable d;

        DoOnDisposeObserver(x<? super T> xVar, a aVar) {
            this.actual = xVar;
            lazySet(aVar);
        }

        public void dispose() {
            a aVar = (a) getAndSet(null);
            if (aVar != null) {
                try {
                    aVar.a();
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    io.reactivex.d.a.a(th);
                }
                this.d.dispose();
            }
        }

        public boolean isDisposed() {
            return this.d.isDisposed();
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.d, disposable)) {
                this.d = disposable;
                this.actual.onSubscribe(this);
            }
        }

        public void onSuccess(T t) {
            this.actual.onSuccess(t);
        }

        public void onError(Throwable th) {
            this.actual.onError(th);
        }
    }

    /* access modifiers changed from: protected */
    public void b(x<? super T> xVar) {
        this.a.a(new DoOnDisposeObserver(xVar, this.b));
    }
}
