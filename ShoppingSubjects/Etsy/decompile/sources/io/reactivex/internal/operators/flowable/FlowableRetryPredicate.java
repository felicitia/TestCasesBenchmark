package io.reactivex.internal.operators.flowable;

import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.a;
import io.reactivex.functions.i;
import io.reactivex.internal.subscriptions.SubscriptionArbiter;
import io.reactivex.j;
import java.util.concurrent.atomic.AtomicInteger;
import org.reactivestreams.Subscription;
import org.reactivestreams.b;
import org.reactivestreams.c;

public final class FlowableRetryPredicate<T> extends a<T, T> {
    final i<? super Throwable> c;
    final long d;

    static final class RepeatSubscriber<T> extends AtomicInteger implements j<T> {
        private static final long serialVersionUID = -7098360935104053232L;
        final c<? super T> actual;
        final i<? super Throwable> predicate;
        long remaining;
        final SubscriptionArbiter sa;
        final b<? extends T> source;

        RepeatSubscriber(c<? super T> cVar, long j, i<? super Throwable> iVar, SubscriptionArbiter subscriptionArbiter, b<? extends T> bVar) {
            this.actual = cVar;
            this.sa = subscriptionArbiter;
            this.source = bVar;
            this.predicate = iVar;
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
            long j = this.remaining;
            if (j != Long.MAX_VALUE) {
                this.remaining = j - 1;
            }
            if (j == 0) {
                this.actual.onError(th);
            } else {
                try {
                    if (!this.predicate.test(th)) {
                        this.actual.onError(th);
                        return;
                    }
                    subscribeNext();
                } catch (Throwable th2) {
                    a.b(th2);
                    this.actual.onError(new CompositeException(th, th2));
                }
            }
        }

        public void onComplete() {
            this.actual.onComplete();
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
        RepeatSubscriber repeatSubscriber = new RepeatSubscriber(cVar, this.d, this.c, subscriptionArbiter, this.b);
        repeatSubscriber.subscribeNext();
    }
}
