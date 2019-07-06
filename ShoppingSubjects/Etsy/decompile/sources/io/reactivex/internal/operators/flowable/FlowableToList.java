package io.reactivex.internal.operators.flowable;

import io.reactivex.internal.functions.a;
import io.reactivex.internal.subscriptions.DeferredScalarSubscription;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import java.util.Collection;
import java.util.concurrent.Callable;
import org.reactivestreams.Subscription;
import org.reactivestreams.c;

public final class FlowableToList<T, U extends Collection<? super T>> extends a<T, U> {
    final Callable<U> c;

    static final class ToListSubscriber<T, U extends Collection<? super T>> extends DeferredScalarSubscription<U> implements j<T>, Subscription {
        private static final long serialVersionUID = -8134157938864266736L;
        Subscription s;

        ToListSubscriber(c<? super U> cVar, U u) {
            super(cVar);
            this.value = u;
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.s, subscription)) {
                this.s = subscription;
                this.actual.onSubscribe(this);
                subscription.request(Long.MAX_VALUE);
            }
        }

        public void onNext(T t) {
            Collection collection = (Collection) this.value;
            if (collection != null) {
                collection.add(t);
            }
        }

        public void onError(Throwable th) {
            this.value = null;
            this.actual.onError(th);
        }

        public void onComplete() {
            complete(this.value);
        }

        public void cancel() {
            super.cancel();
            this.s.cancel();
        }
    }

    /* access modifiers changed from: protected */
    public void a(c<? super U> cVar) {
        try {
            this.b.a((j<? super T>) new ToListSubscriber<Object>(cVar, (Collection) a.a(this.c.call(), "The collectionSupplier returned a null collection. Null values are generally not allowed in 2.x operators and sources.")));
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            EmptySubscription.error(th, cVar);
        }
    }
}
