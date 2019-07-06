package io.reactivex.internal.operators.observable;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.q;
import io.reactivex.u;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableTimer extends q<Long> {
    final u a;
    final long b;
    final TimeUnit c;

    static final class TimerObserver extends AtomicReference<Disposable> implements Disposable, Runnable {
        private static final long serialVersionUID = -2809475196591179431L;
        final Observer<? super Long> actual;

        TimerObserver(Observer<? super Long> observer) {
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
                lazySet(EmptyDisposable.INSTANCE);
                this.actual.onComplete();
            }
        }

        public void setResource(Disposable disposable) {
            DisposableHelper.trySet(this, disposable);
        }
    }

    public ObservableTimer(long j, TimeUnit timeUnit, u uVar) {
        this.b = j;
        this.c = timeUnit;
        this.a = uVar;
    }

    public void a(Observer<? super Long> observer) {
        TimerObserver timerObserver = new TimerObserver(observer);
        observer.onSubscribe(timerObserver);
        timerObserver.setResource(this.a.a(timerObserver, this.b, this.c));
    }
}
