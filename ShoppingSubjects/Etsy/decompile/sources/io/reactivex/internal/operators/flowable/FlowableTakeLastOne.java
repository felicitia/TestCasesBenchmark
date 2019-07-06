package io.reactivex.internal.operators.flowable;

import io.reactivex.internal.subscriptions.DeferredScalarSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import org.reactivestreams.Subscription;
import org.reactivestreams.c;

public final class FlowableTakeLastOne<T> extends a<T, T> {

    static final class TakeLastOneSubscriber<T> extends DeferredScalarSubscription<T> implements j<T> {
        private static final long serialVersionUID = -5467847744262967226L;
        Subscription s;

        TakeLastOneSubscriber(c<? super T> cVar) {
            super(cVar);
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.s, subscription)) {
                this.s = subscription;
                this.actual.onSubscribe(this);
                subscription.request(Long.MAX_VALUE);
            }
        }

        public void onNext(T t) {
            this.value = t;
        }

        public void onError(Throwable th) {
            this.value = null;
            this.actual.onError(th);
        }

        public void onComplete() {
            Object obj = this.value;
            if (obj != null) {
                complete(obj);
            } else {
                this.actual.onComplete();
            }
        }

        public void cancel() {
            super.cancel();
            this.s.cancel();
        }
    }

    /* access modifiers changed from: protected */
    public void a(c<? super T> cVar) {
        this.b.a((j<? super T>) new TakeLastOneSubscriber<Object>(cVar));
    }
}
