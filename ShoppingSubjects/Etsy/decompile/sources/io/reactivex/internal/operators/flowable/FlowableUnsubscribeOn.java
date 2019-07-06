package io.reactivex.internal.operators.flowable;

import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import io.reactivex.u;
import java.util.concurrent.atomic.AtomicBoolean;
import org.reactivestreams.Subscription;
import org.reactivestreams.c;

public final class FlowableUnsubscribeOn<T> extends a<T, T> {
    final u c;

    static final class UnsubscribeSubscriber<T> extends AtomicBoolean implements j<T>, Subscription {
        private static final long serialVersionUID = 1015244841293359600L;
        final c<? super T> actual;
        Subscription s;
        final u scheduler;

        final class a implements Runnable {
            a() {
            }

            public void run() {
                UnsubscribeSubscriber.this.s.cancel();
            }
        }

        UnsubscribeSubscriber(c<? super T> cVar, u uVar) {
            this.actual = cVar;
            this.scheduler = uVar;
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.s, subscription)) {
                this.s = subscription;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            if (!get()) {
                this.actual.onNext(t);
            }
        }

        public void onError(Throwable th) {
            if (get()) {
                io.reactivex.d.a.a(th);
            } else {
                this.actual.onError(th);
            }
        }

        public void onComplete() {
            if (!get()) {
                this.actual.onComplete();
            }
        }

        public void request(long j) {
            this.s.request(j);
        }

        public void cancel() {
            if (compareAndSet(false, true)) {
                this.scheduler.a((Runnable) new a());
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a(c<? super T> cVar) {
        this.b.a((j<? super T>) new UnsubscribeSubscriber<Object>(cVar, this.c));
    }
}
