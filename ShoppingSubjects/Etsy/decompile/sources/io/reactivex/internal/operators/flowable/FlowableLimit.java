package io.reactivex.internal.operators.flowable;

import io.reactivex.d.a;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import java.util.concurrent.atomic.AtomicLong;
import org.reactivestreams.Subscription;
import org.reactivestreams.c;

public final class FlowableLimit<T> extends a<T, T> {
    final long c;

    static final class LimitSubscriber<T> extends AtomicLong implements j<T>, Subscription {
        private static final long serialVersionUID = 2288246011222124525L;
        final c<? super T> actual;
        long remaining;
        Subscription upstream;

        LimitSubscriber(c<? super T> cVar, long j) {
            this.actual = cVar;
            this.remaining = j;
            lazySet(j);
        }

        public void onSubscribe(Subscription subscription) {
            if (!SubscriptionHelper.validate(this.upstream, subscription)) {
                return;
            }
            if (this.remaining == 0) {
                subscription.cancel();
                EmptySubscription.complete(this.actual);
                return;
            }
            this.upstream = subscription;
            this.actual.onSubscribe(this);
        }

        public void onNext(T t) {
            long j = this.remaining;
            if (j > 0) {
                long j2 = j - 1;
                this.remaining = j2;
                this.actual.onNext(t);
                if (j2 == 0) {
                    this.upstream.cancel();
                    this.actual.onComplete();
                }
            }
        }

        public void onError(Throwable th) {
            if (this.remaining > 0) {
                this.remaining = 0;
                this.actual.onError(th);
                return;
            }
            a.a(th);
        }

        public void onComplete() {
            if (this.remaining > 0) {
                this.remaining = 0;
                this.actual.onComplete();
            }
        }

        public void request(long j) {
            long j2;
            long j3;
            if (SubscriptionHelper.validate(j)) {
                do {
                    j2 = get();
                    if (j2 != 0) {
                        j3 = j2 <= j ? j2 : j;
                    } else {
                        return;
                    }
                } while (!compareAndSet(j2, j2 - j3));
                this.upstream.request(j3);
            }
        }

        public void cancel() {
            this.upstream.cancel();
        }
    }

    /* access modifiers changed from: protected */
    public void a(c<? super T> cVar) {
        this.b.a((j<? super T>) new LimitSubscriber<Object>(cVar, this.c));
    }
}
