package io.reactivex.internal.operators.flowable;

import io.reactivex.internal.subscriptions.DeferredScalarSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import org.reactivestreams.Subscription;
import org.reactivestreams.c;

public final class FlowableCount<T> extends a<T, Long> {

    static final class CountSubscriber extends DeferredScalarSubscription<Long> implements j<Object> {
        private static final long serialVersionUID = 4973004223787171406L;
        long count;
        Subscription s;

        CountSubscriber(c<? super Long> cVar) {
            super(cVar);
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.s, subscription)) {
                this.s = subscription;
                this.actual.onSubscribe(this);
                subscription.request(Long.MAX_VALUE);
            }
        }

        public void onNext(Object obj) {
            this.count++;
        }

        public void onError(Throwable th) {
            this.actual.onError(th);
        }

        public void onComplete() {
            complete(Long.valueOf(this.count));
        }

        public void cancel() {
            super.cancel();
            this.s.cancel();
        }
    }

    /* access modifiers changed from: protected */
    public void a(c<? super Long> cVar) {
        this.b.a((j<? super T>) new CountSubscriber<Object>(cVar));
    }
}
