package io.reactivex.internal.operators.flowable;

import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.g;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.u;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;
import org.reactivestreams.c;

public final class FlowableTimer extends g<Long> {
    final u b;
    final long c;
    final TimeUnit d;

    static final class TimerSubscriber extends AtomicReference<Disposable> implements Runnable, Subscription {
        private static final long serialVersionUID = -2809475196591179431L;
        final c<? super Long> actual;
        volatile boolean requested;

        TimerSubscriber(c<? super Long> cVar) {
            this.actual = cVar;
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                this.requested = true;
            }
        }

        public void cancel() {
            DisposableHelper.dispose(this);
        }

        public void run() {
            if (get() == DisposableHelper.DISPOSED) {
                return;
            }
            if (this.requested) {
                this.actual.onNext(Long.valueOf(0));
                lazySet(EmptyDisposable.INSTANCE);
                this.actual.onComplete();
                return;
            }
            lazySet(EmptyDisposable.INSTANCE);
            this.actual.onError(new MissingBackpressureException("Can't deliver value due to lack of requests"));
        }

        public void setResource(Disposable disposable) {
            DisposableHelper.trySet(this, disposable);
        }
    }

    public void a(c<? super Long> cVar) {
        TimerSubscriber timerSubscriber = new TimerSubscriber(cVar);
        cVar.onSubscribe(timerSubscriber);
        timerSubscriber.setResource(this.b.a(timerSubscriber, this.c, this.d));
    }
}
