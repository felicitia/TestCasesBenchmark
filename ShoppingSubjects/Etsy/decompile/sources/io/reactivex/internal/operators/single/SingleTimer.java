package io.reactivex.internal.operators.single;

import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.u;
import io.reactivex.v;
import io.reactivex.x;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public final class SingleTimer extends v<Long> {
    final long a;
    final TimeUnit b;
    final u c;

    static final class TimerDisposable extends AtomicReference<Disposable> implements Disposable, Runnable {
        private static final long serialVersionUID = 8465401857522493082L;
        final x<? super Long> actual;

        TimerDisposable(x<? super Long> xVar) {
            this.actual = xVar;
        }

        public void run() {
            this.actual.onSuccess(Long.valueOf(0));
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

    public SingleTimer(long j, TimeUnit timeUnit, u uVar) {
        this.a = j;
        this.b = timeUnit;
        this.c = uVar;
    }

    /* access modifiers changed from: protected */
    public void b(x<? super Long> xVar) {
        TimerDisposable timerDisposable = new TimerDisposable(xVar);
        xVar.onSubscribe(timerDisposable);
        timerDisposable.setFuture(this.c.a(timerDisposable, this.a, this.b));
    }
}
