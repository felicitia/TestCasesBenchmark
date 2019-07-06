package io.reactivex.internal.operators.flowable;

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

public final class FlowableTakeUntil<T, U> extends a<T, T> {
    final b<? extends U> c;

    static final class TakeUntilMainSubscriber<T> extends AtomicInteger implements j<T>, Subscription {
        private static final long serialVersionUID = -4945480365982832967L;
        final c<? super T> actual;
        final AtomicThrowable error = new AtomicThrowable();
        final OtherSubscriber other = new OtherSubscriber<>();
        final AtomicLong requested = new AtomicLong();
        final AtomicReference<Subscription> s = new AtomicReference<>();

        final class OtherSubscriber extends AtomicReference<Subscription> implements j<Object> {
            private static final long serialVersionUID = -3592821756711087922L;

            OtherSubscriber() {
            }

            public void onSubscribe(Subscription subscription) {
                if (SubscriptionHelper.setOnce(this, subscription)) {
                    subscription.request(Long.MAX_VALUE);
                }
            }

            public void onNext(Object obj) {
                SubscriptionHelper.cancel(this);
                onComplete();
            }

            public void onError(Throwable th) {
                SubscriptionHelper.cancel(TakeUntilMainSubscriber.this.s);
                e.a(TakeUntilMainSubscriber.this.actual, th, (AtomicInteger) TakeUntilMainSubscriber.this, TakeUntilMainSubscriber.this.error);
            }

            public void onComplete() {
                SubscriptionHelper.cancel(TakeUntilMainSubscriber.this.s);
                e.a(TakeUntilMainSubscriber.this.actual, (AtomicInteger) TakeUntilMainSubscriber.this, TakeUntilMainSubscriber.this.error);
            }
        }

        TakeUntilMainSubscriber(c<? super T> cVar) {
            this.actual = cVar;
        }

        public void onSubscribe(Subscription subscription) {
            SubscriptionHelper.deferredSetOnce(this.s, this.requested, subscription);
        }

        public void onNext(T t) {
            e.a(this.actual, t, (AtomicInteger) this, this.error);
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
        TakeUntilMainSubscriber takeUntilMainSubscriber = new TakeUntilMainSubscriber(cVar);
        cVar.onSubscribe(takeUntilMainSubscriber);
        this.c.subscribe(takeUntilMainSubscriber.other);
        this.b.a((j<? super T>) takeUntilMainSubscriber);
    }
}
