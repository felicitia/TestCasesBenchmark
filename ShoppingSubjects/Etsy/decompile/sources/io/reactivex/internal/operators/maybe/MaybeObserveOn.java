package io.reactivex.internal.operators.maybe;

import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.m;
import io.reactivex.u;
import java.util.concurrent.atomic.AtomicReference;

public final class MaybeObserveOn<T> extends a<T, T> {
    final u b;

    static final class ObserveOnMaybeObserver<T> extends AtomicReference<Disposable> implements Disposable, m<T>, Runnable {
        private static final long serialVersionUID = 8571289934935992137L;
        final m<? super T> actual;
        Throwable error;
        final u scheduler;
        T value;

        ObserveOnMaybeObserver(m<? super T> mVar, u uVar) {
            this.actual = mVar;
            this.scheduler = uVar;
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
            this.value = t;
            DisposableHelper.replace(this, this.scheduler.a((Runnable) this));
        }

        public void onError(Throwable th) {
            this.error = th;
            DisposableHelper.replace(this, this.scheduler.a((Runnable) this));
        }

        public void onComplete() {
            DisposableHelper.replace(this, this.scheduler.a((Runnable) this));
        }

        public void run() {
            Throwable th = this.error;
            if (th != null) {
                this.error = null;
                this.actual.onError(th);
                return;
            }
            T t = this.value;
            if (t != null) {
                this.value = null;
                this.actual.onSuccess(t);
                return;
            }
            this.actual.onComplete();
        }
    }

    /* access modifiers changed from: protected */
    public void b(m<? super T> mVar) {
        this.a.a(new ObserveOnMaybeObserver(mVar, this.b));
    }
}
