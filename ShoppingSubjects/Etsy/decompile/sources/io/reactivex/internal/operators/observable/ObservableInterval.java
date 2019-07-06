package io.reactivex.internal.operators.observable;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.schedulers.j;
import io.reactivex.q;
import io.reactivex.u;
import io.reactivex.u.c;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableInterval extends q<Long> {
    final u a;
    final long b;
    final long c;
    final TimeUnit d;

    static final class IntervalObserver extends AtomicReference<Disposable> implements Disposable, Runnable {
        private static final long serialVersionUID = 346773832286157679L;
        final Observer<? super Long> actual;
        long count;

        IntervalObserver(Observer<? super Long> observer) {
            this.actual = observer;
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }

        public boolean isDisposed() {
            return get() == DisposableHelper.DISPOSED;
        }

        public void run() {
            if (get() != DisposableHelper.DISPOSED) {
                Observer<? super Long> observer = this.actual;
                long j = this.count;
                this.count = j + 1;
                observer.onNext(Long.valueOf(j));
            }
        }

        public void setResource(Disposable disposable) {
            DisposableHelper.setOnce(this, disposable);
        }
    }

    public ObservableInterval(long j, long j2, TimeUnit timeUnit, u uVar) {
        this.b = j;
        this.c = j2;
        this.d = timeUnit;
        this.a = uVar;
    }

    public void a(Observer<? super Long> observer) {
        IntervalObserver intervalObserver = new IntervalObserver(observer);
        observer.onSubscribe(intervalObserver);
        u uVar = this.a;
        if (uVar instanceof j) {
            c a2 = uVar.a();
            intervalObserver.setResource(a2);
            a2.a(intervalObserver, this.b, this.c, this.d);
            return;
        }
        intervalObserver.setResource(uVar.a(intervalObserver, this.b, this.c, this.d));
    }
}
