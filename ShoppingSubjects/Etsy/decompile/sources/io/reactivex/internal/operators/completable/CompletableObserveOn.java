package io.reactivex.internal.operators.completable;

import io.reactivex.a;
import io.reactivex.c;
import io.reactivex.disposables.Disposable;
import io.reactivex.e;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.u;
import java.util.concurrent.atomic.AtomicReference;

public final class CompletableObserveOn extends a {
    final e a;
    final u b;

    static final class ObserveOnCompletableObserver extends AtomicReference<Disposable> implements c, Disposable, Runnable {
        private static final long serialVersionUID = 8571289934935992137L;
        final c actual;
        Throwable error;
        final u scheduler;

        ObserveOnCompletableObserver(c cVar, u uVar) {
            this.actual = cVar;
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
            this.actual.onComplete();
        }
    }

    public CompletableObserveOn(e eVar, u uVar) {
        this.a = eVar;
        this.b = uVar;
    }

    /* access modifiers changed from: protected */
    public void b(c cVar) {
        this.a.a(new ObserveOnCompletableObserver(cVar, this.b));
    }
}
