package io.reactivex.internal.operators.maybe;

import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.m;
import io.reactivex.o;
import io.reactivex.v;
import io.reactivex.x;
import io.reactivex.z;
import java.util.concurrent.atomic.AtomicReference;

public final class MaybeSwitchIfEmptySingle<T> extends v<T> {
    final o<T> a;
    final z<? extends T> b;

    static final class SwitchIfEmptyMaybeObserver<T> extends AtomicReference<Disposable> implements Disposable, m<T> {
        private static final long serialVersionUID = 4603919676453758899L;
        final x<? super T> actual;
        final z<? extends T> other;

        static final class a<T> implements x<T> {
            final x<? super T> a;
            final AtomicReference<Disposable> b;

            a(x<? super T> xVar, AtomicReference<Disposable> atomicReference) {
                this.a = xVar;
                this.b = atomicReference;
            }

            public void onSubscribe(Disposable disposable) {
                DisposableHelper.setOnce(this.b, disposable);
            }

            public void onSuccess(T t) {
                this.a.onSuccess(t);
            }

            public void onError(Throwable th) {
                this.a.onError(th);
            }
        }

        SwitchIfEmptyMaybeObserver(x<? super T> xVar, z<? extends T> zVar) {
            this.actual = xVar;
            this.other = zVar;
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((Disposable) get());
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
            this.actual.onError(th);
        }

        public void onComplete() {
            Disposable disposable = (Disposable) get();
            if (disposable != DisposableHelper.DISPOSED && compareAndSet(disposable, null)) {
                this.other.a(new a(this.actual, this));
            }
        }
    }

    /* access modifiers changed from: protected */
    public void b(x<? super T> xVar) {
        this.a.a(new SwitchIfEmptyMaybeObserver(xVar, this.b));
    }
}
