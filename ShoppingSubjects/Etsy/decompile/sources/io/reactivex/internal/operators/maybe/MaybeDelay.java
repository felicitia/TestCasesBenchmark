package io.reactivex.internal.operators.maybe;

import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.m;
import io.reactivex.o;
import io.reactivex.u;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public final class MaybeDelay<T> extends a<T, T> {
    final long b;
    final TimeUnit c;
    final u d;

    static final class DelayMaybeObserver<T> extends AtomicReference<Disposable> implements Disposable, m<T>, Runnable {
        private static final long serialVersionUID = 5566860102500855068L;
        final m<? super T> actual;
        final long delay;
        Throwable error;
        final u scheduler;
        final TimeUnit unit;
        T value;

        DelayMaybeObserver(m<? super T> mVar, long j, TimeUnit timeUnit, u uVar) {
            this.actual = mVar;
            this.delay = j;
            this.unit = timeUnit;
            this.scheduler = uVar;
        }

        public void run() {
            Throwable th = this.error;
            if (th != null) {
                this.actual.onError(th);
                return;
            }
            T t = this.value;
            if (t != null) {
                this.actual.onSuccess(t);
            } else {
                this.actual.onComplete();
            }
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
            schedule();
        }

        public void onError(Throwable th) {
            this.error = th;
            schedule();
        }

        public void onComplete() {
            schedule();
        }

        /* access modifiers changed from: 0000 */
        public void schedule() {
            DisposableHelper.replace(this, this.scheduler.a(this, this.delay, this.unit));
        }
    }

    /* access modifiers changed from: protected */
    public void b(m<? super T> mVar) {
        o oVar = this.a;
        DelayMaybeObserver delayMaybeObserver = new DelayMaybeObserver(mVar, this.b, this.c, this.d);
        oVar.a(delayMaybeObserver);
    }
}
