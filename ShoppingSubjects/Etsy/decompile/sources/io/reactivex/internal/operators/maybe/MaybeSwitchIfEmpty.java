package io.reactivex.internal.operators.maybe;

import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.m;
import io.reactivex.o;
import java.util.concurrent.atomic.AtomicReference;

public final class MaybeSwitchIfEmpty<T> extends a<T, T> {
    final o<? extends T> b;

    static final class SwitchIfEmptyMaybeObserver<T> extends AtomicReference<Disposable> implements Disposable, m<T> {
        private static final long serialVersionUID = -2223459372976438024L;
        final m<? super T> actual;
        final o<? extends T> other;

        static final class a<T> implements m<T> {
            final m<? super T> a;
            final AtomicReference<Disposable> b;

            a(m<? super T> mVar, AtomicReference<Disposable> atomicReference) {
                this.a = mVar;
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

            public void onComplete() {
                this.a.onComplete();
            }
        }

        SwitchIfEmptyMaybeObserver(m<? super T> mVar, o<? extends T> oVar) {
            this.actual = mVar;
            this.other = oVar;
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
    public void b(m<? super T> mVar) {
        this.a.a(new SwitchIfEmptyMaybeObserver(mVar, this.b));
    }
}
