package io.reactivex.internal.operators.flowable;

import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.g;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.b;
import io.reactivex.j;
import io.reactivex.u;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;
import org.reactivestreams.c;

public final class FlowableSampleTimed<T> extends a<T, T> {
    final long c;
    final TimeUnit d;
    final u e;
    final boolean f;

    static final class SampleTimedEmitLast<T> extends SampleTimedSubscriber<T> {
        private static final long serialVersionUID = -7139995637533111443L;
        final AtomicInteger wip = new AtomicInteger(1);

        SampleTimedEmitLast(c<? super T> cVar, long j, TimeUnit timeUnit, u uVar) {
            super(cVar, j, timeUnit, uVar);
        }

        /* access modifiers changed from: 0000 */
        public void complete() {
            emit();
            if (this.wip.decrementAndGet() == 0) {
                this.actual.onComplete();
            }
        }

        public void run() {
            if (this.wip.incrementAndGet() == 2) {
                emit();
                if (this.wip.decrementAndGet() == 0) {
                    this.actual.onComplete();
                }
            }
        }
    }

    static final class SampleTimedNoLast<T> extends SampleTimedSubscriber<T> {
        private static final long serialVersionUID = -7139995637533111443L;

        SampleTimedNoLast(c<? super T> cVar, long j, TimeUnit timeUnit, u uVar) {
            super(cVar, j, timeUnit, uVar);
        }

        /* access modifiers changed from: 0000 */
        public void complete() {
            this.actual.onComplete();
        }

        public void run() {
            emit();
        }
    }

    static abstract class SampleTimedSubscriber<T> extends AtomicReference<T> implements j<T>, Runnable, Subscription {
        private static final long serialVersionUID = -3517602651313910099L;
        final c<? super T> actual;
        final long period;
        final AtomicLong requested = new AtomicLong();
        Subscription s;
        final u scheduler;
        final SequentialDisposable timer = new SequentialDisposable();
        final TimeUnit unit;

        /* access modifiers changed from: 0000 */
        public abstract void complete();

        SampleTimedSubscriber(c<? super T> cVar, long j, TimeUnit timeUnit, u uVar) {
            this.actual = cVar;
            this.period = j;
            this.unit = timeUnit;
            this.scheduler = uVar;
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.s, subscription)) {
                this.s = subscription;
                this.actual.onSubscribe(this);
                this.timer.replace(this.scheduler.a(this, this.period, this.period, this.unit));
                subscription.request(Long.MAX_VALUE);
            }
        }

        public void onNext(T t) {
            lazySet(t);
        }

        public void onError(Throwable th) {
            cancelTimer();
            this.actual.onError(th);
        }

        public void onComplete() {
            cancelTimer();
            complete();
        }

        /* access modifiers changed from: 0000 */
        public void cancelTimer() {
            DisposableHelper.dispose(this.timer);
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                b.a(this.requested, j);
            }
        }

        public void cancel() {
            cancelTimer();
            this.s.cancel();
        }

        /* access modifiers changed from: 0000 */
        public void emit() {
            Object andSet = getAndSet(null);
            if (andSet == null) {
                return;
            }
            if (this.requested.get() != 0) {
                this.actual.onNext(andSet);
                b.c(this.requested, 1);
                return;
            }
            cancel();
            this.actual.onError(new MissingBackpressureException("Couldn't emit value due to lack of requests!"));
        }
    }

    /* access modifiers changed from: protected */
    public void a(c<? super T> cVar) {
        io.reactivex.subscribers.b bVar = new io.reactivex.subscribers.b(cVar);
        if (this.f) {
            g gVar = this.b;
            SampleTimedEmitLast sampleTimedEmitLast = new SampleTimedEmitLast(bVar, this.c, this.d, this.e);
            gVar.a((j<? super T>) sampleTimedEmitLast);
            return;
        }
        g gVar2 = this.b;
        SampleTimedNoLast sampleTimedNoLast = new SampleTimedNoLast(bVar, this.c, this.d, this.e);
        gVar2.a((j<? super T>) sampleTimedNoLast);
    }
}
