package io.reactivex.internal.operators.flowable;

import io.reactivex.internal.a.a;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.e;
import io.reactivex.j;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;
import org.reactivestreams.b;
import org.reactivestreams.c;

public final class FlowableSkipUntil<T, U> extends a<T, T> {
    final b<U> c;

    static final class SkipUntilMainSubscriber<T> extends AtomicInteger implements a<T>, Subscription {
        private static final long serialVersionUID = -6270983465606289181L;
        final c<? super T> actual;
        final AtomicThrowable error = new AtomicThrowable();
        volatile boolean gate;
        final OtherSubscriber other = new OtherSubscriber<>();
        final AtomicLong requested = new AtomicLong();
        final AtomicReference<Subscription> s = new AtomicReference<>();

        final class OtherSubscriber extends AtomicReference<Subscription> implements j<Object> {
            private static final long serialVersionUID = -5592042965931999169L;

            OtherSubscriber() {
            }

            public void onSubscribe(Subscription subscription) {
                if (SubscriptionHelper.setOnce(this, subscription)) {
                    subscription.request(Long.MAX_VALUE);
                }
            }

            public void onNext(Object obj) {
                SkipUntilMainSubscriber.this.gate = true;
                ((Subscription) get()).cancel();
            }

            public void onError(Throwable th) {
                SubscriptionHelper.cancel(SkipUntilMainSubscriber.this.s);
                e.a(SkipUntilMainSubscriber.this.actual, th, (AtomicInteger) SkipUntilMainSubscriber.this, SkipUntilMainSubscriber.this.error);
            }

            public void onComplete() {
                SkipUntilMainSubscriber.this.gate = true;
            }
        }

        SkipUntilMainSubscriber(c<? super T> cVar) {
            this.actual = cVar;
        }

        public void onSubscribe(Subscription subscription) {
            SubscriptionHelper.deferredSetOnce(this.s, this.requested, subscription);
        }

        public void onNext(T t) {
            if (!tryOnNext(t)) {
                ((Subscription) this.s.get()).request(1);
            }
        }

        public boolean tryOnNext(T t) {
            if (!this.gate) {
                return false;
            }
            e.a(this.actual, t, (AtomicInteger) this, this.error);
            return true;
        }

        public void onError(Throwable th) {
            SubscriptionHelper.cancel(this.other);
            e.a(this.actual, th, (AtomicInteger) this, this.error);
        }

        public void onComplete() {
            SubscriptionHelper.cancel(this.other);
            e.a(this.actual, (AtomicInteger) this, this.error);
        }

        public void request(long j) {
            SubscriptionHelper.deferredRequest(this.s, this.requested, j);
        }

        public void cancel() {
            SubscriptionHelper.cancel(this.s);
            SubscriptionHelper.cancel(this.other);
        }
    }

    /* access modifiers changed from: protected */
    public void a(c<? super T> cVar) {
        SkipUntilMainSubscriber skipUntilMainSubscriber = new SkipUntilMainSubscriber(cVar);
        cVar.onSubscribe(skipUntilMainSubscriber);
        this.c.subscribe(skipUntilMainSubscriber.other);
        this.b.a((j<? super T>) skipUntilMainSubscriber);
    }
}
