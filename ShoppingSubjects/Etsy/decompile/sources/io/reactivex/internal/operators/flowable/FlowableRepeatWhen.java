package io.reactivex.internal.operators.flowable;

import io.reactivex.functions.g;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionArbiter;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import io.reactivex.processors.UnicastProcessor;
import io.reactivex.processors.a;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;
import org.reactivestreams.b;
import org.reactivestreams.c;

public final class FlowableRepeatWhen<T> extends a<T, T> {
    final g<? super io.reactivex.g<Object>, ? extends b<?>> c;

    static final class RepeatWhenSubscriber<T> extends WhenSourceSubscriber<T, Object> {
        private static final long serialVersionUID = -2680129890138081029L;

        RepeatWhenSubscriber(c<? super T> cVar, a<Object> aVar, Subscription subscription) {
            super(cVar, aVar, subscription);
        }

        public void onError(Throwable th) {
            this.receiver.cancel();
            this.actual.onError(th);
        }

        public void onComplete() {
            again(Integer.valueOf(0));
        }
    }

    static final class WhenReceiver<T, U> extends AtomicInteger implements j<Object>, Subscription {
        private static final long serialVersionUID = 2827772011130406689L;
        final AtomicLong requested = new AtomicLong();
        final b<T> source;
        WhenSourceSubscriber<T, U> subscriber;
        final AtomicReference<Subscription> subscription = new AtomicReference<>();

        WhenReceiver(b<T> bVar) {
            this.source = bVar;
        }

        public void onSubscribe(Subscription subscription2) {
            SubscriptionHelper.deferredSetOnce(this.subscription, this.requested, subscription2);
        }

        public void onNext(Object obj) {
            if (getAndIncrement() == 0) {
                while (!SubscriptionHelper.isCancelled((Subscription) this.subscription.get())) {
                    this.source.subscribe(this.subscriber);
                    if (decrementAndGet() == 0) {
                    }
                }
            }
        }

        public void onError(Throwable th) {
            this.subscriber.cancel();
            this.subscriber.actual.onError(th);
        }

        public void onComplete() {
            this.subscriber.cancel();
            this.subscriber.actual.onComplete();
        }

        public void request(long j) {
            SubscriptionHelper.deferredRequest(this.subscription, this.requested, j);
        }

        public void cancel() {
            SubscriptionHelper.cancel(this.subscription);
        }
    }

    static abstract class WhenSourceSubscriber<T, U> extends SubscriptionArbiter implements j<T> {
        private static final long serialVersionUID = -5604623027276966720L;
        protected final c<? super T> actual;
        protected final a<U> processor;
        private long produced;
        protected final Subscription receiver;

        WhenSourceSubscriber(c<? super T> cVar, a<U> aVar, Subscription subscription) {
            this.actual = cVar;
            this.processor = aVar;
            this.receiver = subscription;
        }

        public final void onSubscribe(Subscription subscription) {
            setSubscription(subscription);
        }

        public final void onNext(T t) {
            this.produced++;
            this.actual.onNext(t);
        }

        /* access modifiers changed from: protected */
        public final void again(U u) {
            long j = this.produced;
            if (j != 0) {
                this.produced = 0;
                produced(j);
            }
            this.receiver.request(1);
            this.processor.onNext(u);
        }

        public final void cancel() {
            super.cancel();
            this.receiver.cancel();
        }
    }

    public void a(c<? super T> cVar) {
        io.reactivex.subscribers.b bVar = new io.reactivex.subscribers.b(cVar);
        a g = UnicastProcessor.a(8).g();
        try {
            b bVar2 = (b) io.reactivex.internal.functions.a.a(this.c.apply(g), "handler returned a null Publisher");
            WhenReceiver whenReceiver = new WhenReceiver(this.b);
            RepeatWhenSubscriber repeatWhenSubscriber = new RepeatWhenSubscriber(bVar, g, whenReceiver);
            whenReceiver.subscriber = repeatWhenSubscriber;
            cVar.onSubscribe(repeatWhenSubscriber);
            bVar2.subscribe(whenReceiver);
            whenReceiver.onNext(Integer.valueOf(0));
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            EmptySubscription.error(th, cVar);
        }
    }
}
