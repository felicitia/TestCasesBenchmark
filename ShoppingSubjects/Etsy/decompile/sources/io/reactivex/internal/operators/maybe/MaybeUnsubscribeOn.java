package io.reactivex.internal.operators.maybe;

import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.m;
import io.reactivex.u;
import java.util.concurrent.atomic.AtomicReference;

public final class MaybeUnsubscribeOn<T> extends a<T, T> {
    final u b;

    static final class UnsubscribeOnMaybeObserver<T> extends AtomicReference<Disposable> implements Disposable, m<T>, Runnable {
        private static final long serialVersionUID = 3256698449646456986L;
        final m<? super T> actual;
        Disposable ds;
        final u scheduler;

        UnsubscribeOnMaybeObserver(m<? super T> mVar, u uVar) {
            this.actual = mVar;
            this.scheduler = uVar;
        }

        public void dispose() {
            Disposable disposable = (Disposable) getAndSet(DisposableHelper.DISPOSED);
            if (disposable != DisposableHelper.DISPOSED) {
                this.ds = disposable;
                this.scheduler.a((Runnable) this);
            }
        }

        public void run() {
            this.ds.dispose();
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
            this.actual.onComplete();
        }
    }

    /* access modifiers changed from: protected */
    public void b(m<? super T> mVar) {
        this.a.a(new UnsubscribeOnMaybeObserver(mVar, this.b));
    }
}
