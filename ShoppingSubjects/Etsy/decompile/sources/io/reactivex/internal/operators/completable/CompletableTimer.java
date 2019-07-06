package io.reactivex.internal.operators.completable;

import io.reactivex.a;
import io.reactivex.c;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.u;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public final class CompletableTimer extends a {
    final long a;
    final TimeUnit b;
    final u c;

    static final class TimerDisposable extends AtomicReference<Disposable> implements Disposable, Runnable {
        private static final long serialVersionUID = 3167244060586201109L;
        final c actual;

        TimerDisposable(c cVar) {
            this.actual = cVar;
        }

        public void run() {
            this.actual.onComplete();
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((Disposable) get());
        }

        /* access modifiers changed from: 0000 */
        public void setFuture(Disposable disposable) {
            DisposableHelper.replace(this, disposable);
        }
    }

    /* access modifiers changed from: protected */
    public void b(c cVar) {
        TimerDisposable timerDisposable = new TimerDisposable(cVar);
        cVar.onSubscribe(timerDisposable);
        timerDisposable.setFuture(this.c.a(timerDisposable, this.a, this.b));
    }
}
