package io.reactivex.internal.operators.flowable;

import io.reactivex.d.a;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.g;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.b;
import io.reactivex.j;
import io.reactivex.u;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import org.reactivestreams.Subscription;
import org.reactivestreams.c;

public final class FlowableThrottleFirstTimed<T> extends a<T, T> {
    final long c;
    final TimeUnit d;
    final u e;

    static final class DebounceTimedSubscriber<T> extends AtomicLong implements j<T>, Runnable, Subscription {
        private static final long serialVersionUID = -9102637559663639004L;
        final c<? super T> actual;
        boolean done;
        volatile boolean gate;
        Subscription s;
        final long timeout;
        final SequentialDisposable timer = new SequentialDisposable();
        final TimeUnit unit;
        final u.c worker;

        DebounceTimedSubscriber(c<? super T> cVar, long j, TimeUnit timeUnit, u.c cVar2) {
            this.actual = cVar;
            this.timeout = j;
            this.unit = timeUnit;
            this.worker = cVar2;
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.s, subscription)) {
                this.s = subscription;
                this.actual.onSubscribe(this);
                subscription.request(Long.MAX_VALUE);
            }
        }

        public void onNext(T t) {
            if (!this.done && !this.gate) {
                this.gate = true;
                if (get() != 0) {
                    this.actual.onNext(t);
                    b.c(this, 1);
                    Disposable disposable = (Disposable) this.timer.get();
                    if (disposable != null) {
                        disposable.dispose();
                    }
                    this.timer.replace(this.worker.a(this, this.timeout, this.unit));
                } else {
                    this.done = true;
                    cancel();
                    this.actual.onError(new MissingBackpressureException("Could not deliver value due to lack of requests"));
                }
            }
        }

        public void run() {
            this.gate = false;
        }

        public void onError(Throwable th) {
            if (this.done) {
                a.a(th);
                return;
            }
            this.done = true;
            this.actual.onError(th);
            this.worker.dispose();
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.actual.onComplete();
                this.worker.dispose();
            }
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                b.a((AtomicLong) this, j);
            }
        }

        public void cancel() {
            this.s.cancel();
            this.worker.dispose();
        }
    }

    /* access modifiers changed from: protected */
    public void a(c<? super T> cVar) {
        g gVar = this.b;
        DebounceTimedSubscriber debounceTimedSubscriber = new DebounceTimedSubscriber(new io.reactivex.subscribers.b(cVar), this.c, this.d, this.e.a());
        gVar.a((j<? super T>) debounceTimedSubscriber);
    }
}
