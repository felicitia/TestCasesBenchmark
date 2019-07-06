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

public final class ObservableIntervalRange extends q<Long> {
    final u a;
    final long b;
    final long c;
    final long d;
    final long e;
    final TimeUnit f;

    static final class IntervalRangeObserver extends AtomicReference<Disposable> implements Disposable, Runnable {
        private static final long serialVersionUID = 1891866368734007884L;
        final Observer<? super Long> actual;
        long count;
        final long end;

        IntervalRangeObserver(Observer<? super Long> observer, long j, long j2) {
            this.actual = observer;
            this.count = j;
            this.end = j2;
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }

        public boolean isDisposed() {
            return get() == DisposableHelper.DISPOSED;
        }

        public void run() {
            if (!isDisposed()) {
                long j = this.count;
                this.actual.onNext(Long.valueOf(j));
                if (j == this.end) {
                    DisposableHelper.dispose(this);
                    this.actual.onComplete();
                    return;
                }
                this.count = j + 1;
            }
        }

        public void setResource(Disposable disposable) {
            DisposableHelper.setOnce(this, disposable);
        }
    }

    public void a(Observer<? super Long> observer) {
        IntervalRangeObserver intervalRangeObserver = new IntervalRangeObserver(observer, this.b, this.c);
        observer.onSubscribe(intervalRangeObserver);
        u uVar = this.a;
        if (uVar instanceof j) {
            c a2 = uVar.a();
            intervalRangeObserver.setResource(a2);
            a2.a(intervalRangeObserver, this.d, this.e, this.f);
            return;
        }
        intervalRangeObserver.setResource(uVar.a(intervalRangeObserver, this.d, this.e, this.f));
    }
}
