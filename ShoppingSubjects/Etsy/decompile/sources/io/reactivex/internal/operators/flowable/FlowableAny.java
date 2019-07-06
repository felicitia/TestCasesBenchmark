package io.reactivex.internal.operators.flowable;

import io.reactivex.exceptions.a;
import io.reactivex.functions.i;
import io.reactivex.internal.subscriptions.DeferredScalarSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import org.reactivestreams.Subscription;
import org.reactivestreams.c;

public final class FlowableAny<T> extends a<T, Boolean> {
    final i<? super T> c;

    static final class AnySubscriber<T> extends DeferredScalarSubscription<Boolean> implements j<T> {
        private static final long serialVersionUID = -2311252482644620661L;
        boolean done;
        final i<? super T> predicate;
        Subscription s;

        AnySubscriber(c<? super Boolean> cVar, i<? super T> iVar) {
            super(cVar);
            this.predicate = iVar;
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
                    if (this.predicate.test(t)) {
                        this.done = true;
                        this.s.cancel();
                        complete(Boolean.valueOf(true));
                    }
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
                complete(Boolean.valueOf(false));
            }
        }

        public void cancel() {
            super.cancel();
            this.s.cancel();
        }
    }

    /* access modifiers changed from: protected */
    public void a(c<? super Boolean> cVar) {
        this.b.a((j<? super T>) new AnySubscriber<Object>(cVar, this.c));
    }
}
