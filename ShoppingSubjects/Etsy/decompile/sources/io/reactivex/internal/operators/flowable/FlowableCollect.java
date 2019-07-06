package io.reactivex.internal.operators.flowable;

import io.reactivex.exceptions.a;
import io.reactivex.functions.b;
import io.reactivex.internal.subscriptions.DeferredScalarSubscription;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import java.util.concurrent.Callable;
import org.reactivestreams.Subscription;
import org.reactivestreams.c;

public final class FlowableCollect<T, U> extends a<T, U> {
    final Callable<? extends U> c;
    final b<? super U, ? super T> d;

    static final class CollectSubscriber<T, U> extends DeferredScalarSubscription<U> implements j<T> {
        private static final long serialVersionUID = -3589550218733891694L;
        final b<? super U, ? super T> collector;
        boolean done;
        Subscription s;
        final U u;

        CollectSubscriber(c<? super U> cVar, U u2, b<? super U, ? super T> bVar) {
            super(cVar);
            this.collector = bVar;
            this.u = u2;
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.s, subscription)) {
                this.s = subscription;
                this.actual.onSubscribe(this);
                subscription.request(Long.MAX_VALUE);
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                try {
                    this.collector.a(this.u, t);
                } catch (Throwable th) {
                    a.b(th);
                    this.s.cancel();
                    onError(th);
                }
            }
        }

        public void onError(Throwable th) {
            if (this.done) {
                io.reactivex.d.a.a(th);
                return;
            }
            this.done = true;
            this.actual.onError(th);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                complete(this.u);
            }
        }

        public void cancel() {
            super.cancel();
            this.s.cancel();
        }
    }

    /* access modifiers changed from: protected */
    public void a(c<? super U> cVar) {
        try {
            this.b.a((j<? super T>) new CollectSubscriber<Object>(cVar, io.reactivex.internal.functions.a.a(this.c.call(), "The initial value supplied is null"), this.d));
        } catch (Throwable th) {
            EmptySubscription.error(th, cVar);
        }
    }
}
