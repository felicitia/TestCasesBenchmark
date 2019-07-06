package io.reactivex.internal.operators.flowable;

import io.reactivex.functions.c;
import io.reactivex.internal.functions.a;
import io.reactivex.internal.subscriptions.DeferredScalarSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import org.reactivestreams.Subscription;

public final class FlowableReduce<T> extends a<T, T> {
    final c<T, T, T> c;

    static final class ReduceSubscriber<T> extends DeferredScalarSubscription<T> implements j<T> {
        private static final long serialVersionUID = -4663883003264602070L;
        final c<T, T, T> reducer;
        Subscription s;

        ReduceSubscriber(org.reactivestreams.c<? super T> cVar, c<T, T, T> cVar2) {
            super(cVar);
            this.reducer = cVar2;
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.s, subscription)) {
                this.s = subscription;
                this.actual.onSubscribe(this);
                subscription.request(Long.MAX_VALUE);
            }
        }

        public void onNext(T t) {
            if (this.s != SubscriptionHelper.CANCELLED) {
                Object obj = this.value;
                if (obj == null) {
                    this.value = t;
                } else {
                    try {
                        this.value = a.a(this.reducer.apply(obj, t), "The reducer returned a null value");
                    } catch (Throwable th) {
                        io.reactivex.exceptions.a.b(th);
                        this.s.cancel();
                        onError(th);
                    }
                }
            }
        }

        public void onError(Throwable th) {
            if (this.s == SubscriptionHelper.CANCELLED) {
                io.reactivex.d.a.a(th);
                return;
            }
            this.s = SubscriptionHelper.CANCELLED;
            this.actual.onError(th);
        }

        public void onComplete() {
            if (this.s != SubscriptionHelper.CANCELLED) {
                this.s = SubscriptionHelper.CANCELLED;
                Object obj = this.value;
                if (obj != null) {
                    complete(obj);
                } else {
                    this.actual.onComplete();
                }
            }
        }

        public void cancel() {
            super.cancel();
            this.s.cancel();
            this.s = SubscriptionHelper.CANCELLED;
        }
    }

    /* access modifiers changed from: protected */
    public void a(org.reactivestreams.c<? super T> cVar) {
        this.b.a((j<? super T>) new ReduceSubscriber<Object>(cVar, this.c));
    }
}
