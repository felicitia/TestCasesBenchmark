package io.reactivex.internal.operators.maybe;

import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.k;
import io.reactivex.m;
import io.reactivex.u;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public final class MaybeTimer extends k<Long> {
    final long a;
    final TimeUnit b;
    final u c;

    static final class TimerDisposable extends AtomicReference<Disposable> implements Disposable, Runnable {
        private static final long serialVersionUID = 2875964065294031672L;
        final m<? super Long> actual;

        TimerDisposable(m<? super Long> mVar) {
            this.actual = mVar;
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

    /* access modifiers changed from: protected */
    public void b(m<? super Long> mVar) {
        TimerDisposable timerDisposable = new TimerDisposable(mVar);
        mVar.onSubscribe(timerDisposable);
        timerDisposable.setFuture(this.c.a(timerDisposable, this.a, this.b));
    }
}
