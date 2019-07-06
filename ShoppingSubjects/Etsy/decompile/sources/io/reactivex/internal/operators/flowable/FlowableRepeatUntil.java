package io.reactivex.internal.operators.flowable;

import io.reactivex.exceptions.a;
import io.reactivex.functions.e;
import io.reactivex.internal.subscriptions.SubscriptionArbiter;
import io.reactivex.j;
import java.util.concurrent.atomic.AtomicInteger;
import org.reactivestreams.Subscription;
import org.reactivestreams.b;
import org.reactivestreams.c;

public final class FlowableRepeatUntil<T> extends a<T, T> {
    final e c;

    static final class RepeatSubscriber<T> extends AtomicInteger implements j<T> {
        private static final long serialVersionUID = -7098360935104053232L;
        final c<? super T> actual;
        final SubscriptionArbiter sa;
        final b<? extends T> source;
        final e stop;

        RepeatSubscriber(c<? super T> cVar, e eVar, SubscriptionArbiter subscriptionArbiter, b<? extends T> bVar) {
            this.actual = cVar;
            this.sa = subscriptionArbiter;
            this.source = bVar;
            this.stop = eVar;
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
            try {
                if (this.stop.getAsBoolean()) {
                    this.actual.onComplete();
                } else {
                    subscribeNext();
                }
            } catch (Throwable th) {
                a.b(th);
                this.actual.onError(th);
            }
        }

        /* access modifiers changed from: 0000 */
        public void subscribeNext() {
            if (getAndIncrement() == 0) {
                int i = 1;
                do {
                    this.source.subscribe(this);
                    i = addAndGet(-i);
                } while (i != 0);
            }
        }
    }

    public void a(c<? super T> cVar) {
        SubscriptionArbiter subscriptionArbiter = new SubscriptionArbiter();
        cVar.onSubscribe(subscriptionArbiter);
        new RepeatSubscriber(cVar, this.c, subscriptionArbiter, this.b).subscribeNext();
    }
}
