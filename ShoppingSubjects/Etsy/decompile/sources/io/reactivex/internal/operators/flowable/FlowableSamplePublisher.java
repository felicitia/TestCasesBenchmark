package io.reactivex.internal.operators.flowable;

import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.g;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;
import org.reactivestreams.b;
import org.reactivestreams.c;

public final class FlowableSamplePublisher<T> extends g<T> {
    final b<T> b;
    final b<?> c;
    final boolean d;

    static final class SampleMainEmitLast<T> extends SamplePublisherSubscriber<T> {
        private static final long serialVersionUID = -3029755663834015785L;
        volatile boolean done;
        final AtomicInteger wip = new AtomicInteger();

        SampleMainEmitLast(c<? super T> cVar, b<?> bVar) {
            super(cVar, bVar);
        }

        /* access modifiers changed from: 0000 */
        public void completeMain() {
            this.done = true;
            if (this.wip.getAndIncrement() == 0) {
                emit();
                this.actual.onComplete();
            }
        }

        /* access modifiers changed from: 0000 */
        public void completeOther() {
            this.done = true;
            if (this.wip.getAndIncrement() == 0) {
                emit();
                this.actual.onComplete();
            }
        }

        /* access modifiers changed from: 0000 */
        public void run() {
            if (this.wip.getAndIncrement() == 0) {
                do {
                    boolean z = this.done;
                    emit();
                    if (z) {
                        this.actual.onComplete();
                        return;
                    }
                } while (this.wip.decrementAndGet() != 0);
            }
        }
    }

    static final class SampleMainNoLast<T> extends SamplePublisherSubscriber<T> {
        private static final long serialVersionUID = -3029755663834015785L;

        SampleMainNoLast(c<? super T> cVar, b<?> bVar) {
            super(cVar, bVar);
        }

        /* access modifiers changed from: 0000 */
        public void completeMain() {
            this.actual.onComplete();
        }

        /* access modifiers changed from: 0000 */
        public void completeOther() {
            this.actual.onComplete();
        }

        /* access modifiers changed from: 0000 */
        public void run() {
            emit();
        }
    }

    static abstract class SamplePublisherSubscriber<T> extends AtomicReference<T> implements j<T>, Subscription {
        private static final long serialVersionUID = -3517602651313910099L;
        final c<? super T> actual;
        final AtomicReference<Subscription> other = new AtomicReference<>();
        final AtomicLong requested = new AtomicLong();
        Subscription s;
        final b<?> sampler;

        /* access modifiers changed from: 0000 */
        public abstract void completeMain();

        /* access modifiers changed from: 0000 */
        public abstract void completeOther();

        /* access modifiers changed from: 0000 */
        public abstract void run();

        SamplePublisherSubscriber(c<? super T> cVar, b<?> bVar) {
            this.actual = cVar;
            this.sampler = bVar;
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.s, subscription)) {
                this.s = subscription;
                this.actual.onSubscribe(this);
                if (this.other.get() == null) {
                    this.sampler.subscribe(new a(this));
                    subscription.request(Long.MAX_VALUE);
                }
            }
        }

        public void onNext(T t) {
            lazySet(t);
        }

        public void onError(Throwable th) {
            SubscriptionHelper.cancel(this.other);
            this.actual.onError(th);
        }

        public void onComplete() {
            SubscriptionHelper.cancel(this.other);
            completeMain();
        }

        /* access modifiers changed from: 0000 */
        public boolean setOther(Subscription subscription) {
            return SubscriptionHelper.setOnce(this.other, subscription);
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                io.reactivex.internal.util.b.a(this.requested, j);
            }
        }

        public void cancel() {
            SubscriptionHelper.cancel(this.other);
            this.s.cancel();
        }

        public void error(Throwable th) {
            this.s.cancel();
            this.actual.onError(th);
        }

        public void complete() {
            this.s.cancel();
            completeOther();
        }

        /* access modifiers changed from: 0000 */
        public void emit() {
            Object andSet = getAndSet(null);
            if (andSet == null) {
                return;
            }
            if (this.requested.get() != 0) {
                this.actual.onNext(andSet);
                io.reactivex.internal.util.b.c(this.requested, 1);
                return;
            }
            cancel();
            this.actual.onError(new MissingBackpressureException("Couldn't emit value due to lack of requests!"));
        }
    }

    static final class a<T> implements j<Object> {
        final SamplePublisherSubscriber<T> a;

        a(SamplePublisherSubscriber<T> samplePublisherSubscriber) {
            this.a = samplePublisherSubscriber;
        }

        public void onSubscribe(Subscription subscription) {
            if (this.a.setOther(subscription)) {
                subscription.request(Long.MAX_VALUE);
            }
        }

        public void onNext(Object obj) {
            this.a.run();
        }

        public void onError(Throwable th) {
            this.a.error(th);
        }

        public void onComplete() {
            this.a.complete();
        }
    }

    /* access modifiers changed from: protected */
    public void a(c<? super T> cVar) {
        io.reactivex.subscribers.b bVar = new io.reactivex.subscribers.b(cVar);
        if (this.d) {
            this.b.subscribe(new SampleMainEmitLast(bVar, this.c));
        } else {
            this.b.subscribe(new SampleMainNoLast(bVar, this.c));
        }
    }
}
