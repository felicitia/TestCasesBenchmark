package io.reactivex.internal.operators.observable;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableTimer extends Observable<Long> {
    final long delay;
    final Scheduler scheduler;
    final TimeUnit unit;

    static final class IntervalOnceObserver extends AtomicReference<Disposable> implements Disposable, Runnable {
        final Observer<? super Long> actual;

        IntervalOnceObserver(Observer<? super Long> observer) {
            this.actual = observer;
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }

        public boolean isDisposed() {
            return get() == DisposableHelper.DISPOSED;
        }

        public void run() {
            if (!isDisposed()) {
                this.actual.onNext(Long.valueOf(0));
                this.actual.onComplete();
                lazySet(EmptyDisposable.INSTANCE);
            }
        }

        public void setResource(Disposable disposable) {
            DisposableHelper.setOnce(this, disposable);
        }
    }

    public ObservableTimer(long j, TimeUnit timeUnit, Scheduler scheduler2) {
        this.delay = j;
        this.unit = timeUnit;
        this.scheduler = scheduler2;
    }

    public void subscribeActual(Observer<? super Long> observer) {
        IntervalOnceObserver intervalOnceObserver = new IntervalOnceObserver(observer);
        observer.onSubscribe(intervalOnceObserver);
        intervalOnceObserver.setResource(this.scheduler.scheduleDirect(intervalOnceObserver, this.delay, this.unit));
    }
}
