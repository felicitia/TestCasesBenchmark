package io.reactivex.internal.operators.flowable;

import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import java.util.concurrent.atomic.AtomicBoolean;
import org.reactivestreams.Subscription;
import org.reactivestreams.c;

public final class FlowableTake<T> extends a<T, T> {
    final long c;

    static final class TakeSubscriber<T> extends AtomicBoolean implements j<T>, Subscription {
        private static final long serialVersionUID = -5636543848937116287L;
        final c<? super T> actual;
        boolean done;
        final long limit;
        long remaining;
        Subscription subscription;

        TakeSubscriber(c<? super T> cVar, long j) {
            this.actual = cVar;
            this.limit = j;
            this.remaining = j;
        }

        public void onSubscribe(Subscription subscription2) {
            if (SubscriptionHelper.validate(this.subscription, subscription2)) {
                this.subscription = subscription2;
                if (this.limit == 0) {
                    subscription2.cancel();
                    this.done = true;
                    EmptySubscription.complete(this.actual);
                    return;
                }
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                long j = this.remaining;
                this.remaining = j - 1;
                if (j > 0) {
                    boolean z = this.remaining == 0;
                    this.actual.onNext(t);
                    if (z) {
                        this.subscription.cancel();
                        onComplete();
                    }
                }
            }
        }

        public void onError(Throwable th) {
            if (!this.done) {
                this.done = true;
                this.subscription.cancel();
                this.actual.onError(th);
            }
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.actual.onComplete();
            }
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                if (get() || !compareAndSet(false, true) || j < this.limit) {
                    this.subscription.request(j);
                } else {
                    this.subscription.request(Long.MAX_VALUE);
                }
            }
        }

        public void cancel() {
            this.subscription.cancel();
        }
    }

    /* access modifiers changed from: protected */
    public void a(c<? super T> cVar) {
        this.b.a((j<? super T>) new TakeSubscriber<Object>(cVar, this.c));
    }
}
