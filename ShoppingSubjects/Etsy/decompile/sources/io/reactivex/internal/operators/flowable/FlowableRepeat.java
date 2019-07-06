package io.reactivex.internal.operators.flowable;

import io.reactivex.internal.subscriptions.SubscriptionArbiter;
import io.reactivex.j;
import java.util.concurrent.atomic.AtomicInteger;
import org.reactivestreams.Subscription;
import org.reactivestreams.b;
import org.reactivestreams.c;

public final class FlowableRepeat<T> extends a<T, T> {
    final long c;

    static final class RepeatSubscriber<T> extends AtomicInteger implements j<T> {
        private static final long serialVersionUID = -7098360935104053232L;
        final c<? super T> actual;
        long remaining;
        final SubscriptionArbiter sa;
        final b<? extends T> source;

        RepeatSubscriber(c<? super T> cVar, long j, SubscriptionArbiter subscriptionArbiter, b<? extends T> bVar) {
            this.actual = cVar;
            this.sa = subscriptionArbiter;
            this.source = bVar;
            this.remaining = j;
        }

        public void onSubscribe(Subscription subscription) {
            this.sa.setSubscription(subscription);
        }

        public void onNext(T t) {
            this.actual.onNext(t);
            this.sa.produced(1);
        }

        public void onError(Throwable th) {
            this.actual.onError(th);
        }

        public void onComplete() {
            long j = this.remaining;
            if (j != Long.MAX_VALUE) {
                this.remaining = j - 1;
            }
            if (j != 0) {
                subscribeNext();
            } else {
                this.actual.onComplete();
            }
        }

        /* access modifiers changed from: 0000 */
        public void subscribeNext() {
            if (getAndIncrement() == 0) {
                int i = 1;
                while (!this.sa.isCancelled()) {
                    this.source.subscribe(this);
                    i = addAndGet(-i);
                    if (i == 0) {
                    }
                }
            }
        }
    }

    public void a(c<? super T> cVar) {
        SubscriptionArbiter subscriptionArbiter = new SubscriptionArbiter();
        cVar.onSubscribe(subscriptionArbiter);
        long j = Long.MAX_VALUE;
        if (this.c != Long.MAX_VALUE) {
            j = this.c - 1;
        }
        RepeatSubscriber repeatSubscriber = new RepeatSubscriber(cVar, j, subscriptionArbiter, this.b);
        repeatSubscriber.subscribeNext();
    }
}
