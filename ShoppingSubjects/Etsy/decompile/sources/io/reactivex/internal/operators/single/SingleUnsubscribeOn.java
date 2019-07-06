package io.reactivex.internal.operators.single;

import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.u;
import io.reactivex.v;
import io.reactivex.x;
import io.reactivex.z;
import java.util.concurrent.atomic.AtomicReference;

public final class SingleUnsubscribeOn<T> extends v<T> {
    final z<T> a;
    final u b;

    static final class UnsubscribeOnSingleObserver<T> extends AtomicReference<Disposable> implements Disposable, x<T>, Runnable {
        private static final long serialVersionUID = 3256698449646456986L;
        final x<? super T> actual;
        Disposable ds;
        final u scheduler;

        UnsubscribeOnSingleObserver(x<? super T> xVar, u uVar) {
            this.actual = xVar;
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
    }

    /* access modifiers changed from: protected */
    public void b(x<? super T> xVar) {
        this.a.a(new UnsubscribeOnSingleObserver(xVar, this.b));
    }
}
