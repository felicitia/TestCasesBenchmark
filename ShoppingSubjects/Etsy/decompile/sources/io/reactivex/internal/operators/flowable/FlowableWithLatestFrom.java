package io.reactivex.internal.operators.flowable;

import io.reactivex.functions.c;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;
import org.reactivestreams.b;

public final class FlowableWithLatestFrom<T, U, R> extends a<T, R> {
    final c<? super T, ? super U, ? extends R> c;
    final b<? extends U> d;

    static final class WithLatestFromSubscriber<T, U, R> extends AtomicReference<U> implements io.reactivex.internal.a.a<T>, Subscription {
        private static final long serialVersionUID = -312246233408980075L;
        final org.reactivestreams.c<? super R> actual;
        final c<? super T, ? super U, ? extends R> combiner;
        final AtomicReference<Subscription> other = new AtomicReference<>();
        final AtomicLong requested = new AtomicLong();
        final AtomicReference<Subscription> s = new AtomicReference<>();

        WithLatestFromSubscriber(org.reactivestreams.c<? super R> cVar, c<? super T, ? super U, ? extends R> cVar2) {
            this.actual = cVar;
            this.combiner = cVar2;
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
            Object obj = get();
            if (obj == null) {
                return false;
            }
            try {
                this.actual.onNext(io.reactivex.internal.functions.a.a(this.combiner.apply(t, obj), "The combiner returned a null value"));
                return true;
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                cancel();
                this.actual.onError(th);
                return false;
            }
        }

        public void onError(Throwable th) {
            SubscriptionHelper.cancel(this.other);
            this.actual.onError(th);
        }

        public void onComplete() {
            SubscriptionHelper.cancel(this.other);
            this.actual.onComplete();
        }

        public void request(long j) {
            SubscriptionHelper.deferredRequest(this.s, this.requested, j);
        }

        public void cancel() {
            SubscriptionHelper.cancel(this.s);
            SubscriptionHelper.cancel(this.other);
        }

        public boolean setOther(Subscription subscription) {
            return SubscriptionHelper.setOnce(this.other, subscription);
        }

        public void otherError(Throwable th) {
            SubscriptionHelper.cancel(this.s);
            this.actual.onError(th);
        }
    }

    final class a implements j<U> {
        private final WithLatestFromSubscriber<T, U, R> b;

        public void onComplete() {
        }

        a(WithLatestFromSubscriber<T, U, R> withLatestFromSubscriber) {
            this.b = withLatestFromSubscriber;
        }

        public void onSubscribe(Subscription subscription) {
            if (this.b.setOther(subscription)) {
                subscription.request(Long.MAX_VALUE);
            }
        }

        public void onNext(U u) {
            this.b.lazySet(u);
        }

        public void onError(Throwable th) {
            this.b.otherError(th);
        }
    }

    /* access modifiers changed from: protected */
    public void a(org.reactivestreams.c<? super R> cVar) {
        io.reactivex.subscribers.b bVar = new io.reactivex.subscribers.b(cVar);
        WithLatestFromSubscriber withLatestFromSubscriber = new WithLatestFromSubscriber(bVar, this.c);
        bVar.onSubscribe(withLatestFromSubscriber);
        this.d.subscribe(new a(withLatestFromSubscriber));
        this.b.a((j<? super T>) withLatestFromSubscriber);
    }
}
