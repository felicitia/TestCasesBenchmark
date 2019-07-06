package io.reactivex.internal.subscribers;

import io.reactivex.internal.subscriptions.DeferredScalarSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import org.reactivestreams.Subscription;
import org.reactivestreams.c;

public abstract class DeferredScalarSubscriber<T, R> extends DeferredScalarSubscription<R> implements j<T> {
    private static final long serialVersionUID = 2984505488220891551L;
    protected boolean hasValue;
    protected Subscription s;

    public DeferredScalarSubscriber(c<? super R> cVar) {
        super(cVar);
    }

    public void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.validate(this.s, subscription)) {
            this.s = subscription;
            this.actual.onSubscribe(this);
            subscription.request(Long.MAX_VALUE);
        }
    }

    public void onError(Throwable th) {
        this.value = null;
        this.actual.onError(th);
    }

    public void onComplete() {
        if (this.hasValue) {
            complete(this.value);
        } else {
            this.actual.onComplete();
        }
    }

    public void cancel() {
        super.cancel();
        this.s.cancel();
    }
}
