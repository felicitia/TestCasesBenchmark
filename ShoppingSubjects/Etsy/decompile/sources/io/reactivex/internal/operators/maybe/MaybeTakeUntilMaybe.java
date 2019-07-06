package io.reactivex.internal.operators.maybe;

import io.reactivex.d.a;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.m;
import io.reactivex.o;
import java.util.concurrent.atomic.AtomicReference;

public final class MaybeTakeUntilMaybe<T, U> extends a<T, T> {
    final o<U> b;

    static final class TakeUntilMainMaybeObserver<T, U> extends AtomicReference<Disposable> implements Disposable, m<T> {
        private static final long serialVersionUID = -2187421758664251153L;
        final m<? super T> actual;
        final TakeUntilOtherMaybeObserver<U> other = new TakeUntilOtherMaybeObserver<>(this);

        static final class TakeUntilOtherMaybeObserver<U> extends AtomicReference<Disposable> implements m<U> {
            private static final long serialVersionUID = -1266041316834525931L;
            final TakeUntilMainMaybeObserver<?, U> parent;

            TakeUntilOtherMaybeObserver(TakeUntilMainMaybeObserver<?, U> takeUntilMainMaybeObserver) {
                this.parent = takeUntilMainMaybeObserver;
            }

            public void onSubscribe(Disposable disposable) {
                DisposableHelper.setOnce(this, disposable);
            }

            public void onSuccess(Object obj) {
                this.parent.otherComplete();
            }

            public void onError(Throwable th) {
                this.parent.otherError(th);
            }

            public void onComplete() {
                this.parent.otherComplete();
            }
        }

        TakeUntilMainMaybeObserver(m<? super T> mVar) {
            this.actual = mVar;
        }

        public void dispose() {
            DisposableHelper.dispose(this);
            DisposableHelper.dispose(this.other);
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((Disposable) get());
        }

        public void onSubscribe(Disposable disposable) {
            DisposableHelper.setOnce(this, disposable);
        }

        public void onSuccess(T t) {
            DisposableHelper.dispose(this.other);
            if (getAndSet(DisposableHelper.DISPOSED) != DisposableHelper.DISPOSED) {
                this.actual.onSuccess(t);
            }
        }

        public void onError(Throwable th) {
            DisposableHelper.dispose(this.other);
            if (getAndSet(DisposableHelper.DISPOSED) != DisposableHelper.DISPOSED) {
                this.actual.onError(th);
            } else {
                a.a(th);
            }
        }

        public void onComplete() {
            DisposableHelper.dispose(this.other);
            if (getAndSet(DisposableHelper.DISPOSED) != DisposableHelper.DISPOSED) {
                this.actual.onComplete();
            }
        }

        /* access modifiers changed from: 0000 */
        public void otherError(Throwable th) {
            if (DisposableHelper.dispose(this)) {
                this.actual.onError(th);
            } else {
                a.a(th);
            }
        }

        /* access modifiers changed from: 0000 */
        public void otherComplete() {
            if (DisposableHelper.dispose(this)) {
                this.actual.onComplete();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void b(m<? super T> mVar) {
        TakeUntilMainMaybeObserver takeUntilMainMaybeObserver = new TakeUntilMainMaybeObserver(mVar);
        mVar.onSubscribe(takeUntilMainMaybeObserver);
        this.b.a(takeUntilMainMaybeObserver.other);
        this.a.a(takeUntilMainMaybeObserver);
    }
}
