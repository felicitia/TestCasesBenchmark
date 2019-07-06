package io.reactivex.internal.operators.flowable;

import io.reactivex.d.a;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.g;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.b;
import io.reactivex.j;
import java.util.concurrent.atomic.AtomicLong;
import org.reactivestreams.Subscription;
import org.reactivestreams.c;

public final class FlowableOnBackpressureError<T> extends a<T, T> {

    static final class BackpressureErrorSubscriber<T> extends AtomicLong implements j<T>, Subscription {
        private static final long serialVersionUID = -3176480756392482682L;
        final c<? super T> actual;
        boolean done;
        Subscription s;

        BackpressureErrorSubscriber(c<? super T> cVar) {
            this.actual = cVar;
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
                if (get() != 0) {
                    this.actual.onNext(t);
                    b.c(this, 1);
                } else {
                    onError(new MissingBackpressureException("could not emit value due to lack of requests"));
                }
            }
        }

        public void onError(Throwable th) {
            if (this.done) {
                a.a(th);
                return;
            }
            this.done = true;
            this.actual.onError(th);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.actual.onComplete();
            }
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                b.a((AtomicLong) this, j);
            }
        }

        public void cancel() {
            this.s.cancel();
        }
    }

    public FlowableOnBackpressureError(g<T> gVar) {
        super(gVar);
    }

    /* access modifiers changed from: protected */
    public void a(c<? super T> cVar) {
        this.b.a((j<? super T>) new BackpressureErrorSubscriber<Object>(cVar));
    }
}
