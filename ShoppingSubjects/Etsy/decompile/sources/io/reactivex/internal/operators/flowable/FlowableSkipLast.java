package io.reactivex.internal.operators.flowable;

import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import java.util.ArrayDeque;
import org.reactivestreams.Subscription;
import org.reactivestreams.c;

public final class FlowableSkipLast<T> extends a<T, T> {
    final int c;

    static final class SkipLastSubscriber<T> extends ArrayDeque<T> implements j<T>, Subscription {
        private static final long serialVersionUID = -3807491841935125653L;
        final c<? super T> actual;
        Subscription s;
        final int skip;

        SkipLastSubscriber(c<? super T> cVar, int i) {
            super(i);
            this.actual = cVar;
            this.skip = i;
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.s, subscription)) {
                this.s = subscription;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            if (this.skip == size()) {
                this.actual.onNext(poll());
            } else {
                this.s.request(1);
            }
            offer(t);
        }

        public void onError(Throwable th) {
            this.actual.onError(th);
        }

        public void onComplete() {
            this.actual.onComplete();
        }

        public void request(long j) {
            this.s.request(j);
        }

        public void cancel() {
            this.s.cancel();
        }
    }

    /* access modifiers changed from: protected */
    public void a(c<? super T> cVar) {
        this.b.a((j<? super T>) new SkipLastSubscriber<Object>(cVar, this.c));
    }
}
