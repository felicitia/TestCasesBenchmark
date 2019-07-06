package io.reactivex.internal.operators.flowable;

import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.a;
import io.reactivex.functions.d;
import io.reactivex.internal.subscriptions.SubscriptionArbiter;
import io.reactivex.j;
import java.util.concurrent.atomic.AtomicInteger;
import org.reactivestreams.Subscription;
import org.reactivestreams.b;
import org.reactivestreams.c;

public final class FlowableRetryBiPredicate<T> extends a<T, T> {
    final d<? super Integer, ? super Throwable> c;

    static final class RetryBiSubscriber<T> extends AtomicInteger implements j<T> {
        private static final long serialVersionUID = -7098360935104053232L;
        final c<? super T> actual;
        final d<? super Integer, ? super Throwable> predicate;
        int retries;
        final SubscriptionArbiter sa;
        final b<? extends T> source;

        RetryBiSubscriber(c<? super T> cVar, d<? super Integer, ? super Throwable> dVar, SubscriptionArbiter subscriptionArbiter, b<? extends T> bVar) {
            this.actual = cVar;
            this.sa = subscriptionArbiter;
            this.source = bVar;
            this.predicate = dVar;
        }

        public void onSubscribe(Subscription subscription) {
            this.sa.setSubscription(subscription);
        }

        public void onNext(T t) {
            this.actual.onNext(t);
            this.sa.produced(1);
        }

        public void onError(Throwable th) {
            try {
                d<? super Integer, ? super Throwable> dVar = this.predicate;
                int i = this.retries + 1;
                this.retries = i;
                if (!dVar.a(Integer.valueOf(i), th)) {
                    this.actual.onError(th);
                } else {
                    subscribeNext();
                }
            } catch (Throwable th2) {
                a.b(th2);
                this.actual.onError(new CompositeException(th, th2));
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
        new RetryBiSubscriber(cVar, this.c, subscriptionArbiter, this.b).subscribeNext();
    }
}
