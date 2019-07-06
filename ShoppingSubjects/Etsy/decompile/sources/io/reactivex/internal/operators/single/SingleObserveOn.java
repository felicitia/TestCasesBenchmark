package io.reactivex.internal.operators.single;

import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.u;
import io.reactivex.v;
import io.reactivex.x;
import io.reactivex.z;
import java.util.concurrent.atomic.AtomicReference;

public final class SingleObserveOn<T> extends v<T> {
    final z<T> a;
    final u b;

    static final class ObserveOnSingleObserver<T> extends AtomicReference<Disposable> implements Disposable, x<T>, Runnable {
        private static final long serialVersionUID = 3528003840217436037L;
        final x<? super T> actual;
        Throwable error;
        final u scheduler;
        T value;

        ObserveOnSingleObserver(x<? super T> xVar, u uVar) {
            this.actual = xVar;
            this.scheduler = uVar;
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

        public void run() {
            Throwable th = this.error;
            if (th != null) {
                this.actual.onError(th);
            } else {
                this.actual.onSuccess(this.value);
            }
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((Disposable) get());
        }
    }

    public SingleObserveOn(z<T> zVar, u uVar) {
        this.a = zVar;
        this.b = uVar;
    }

    /* access modifiers changed from: protected */
    public void b(x<? super T> xVar) {
        this.a.a(new ObserveOnSingleObserver(xVar, this.b));
    }
}
