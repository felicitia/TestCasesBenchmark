package io.reactivex.internal.operators.flowable;

import io.reactivex.functions.g;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.processors.UnicastProcessor;
import io.reactivex.processors.a;
import org.reactivestreams.Subscription;
import org.reactivestreams.b;
import org.reactivestreams.c;

public final class FlowableRetryWhen<T> extends a<T, T> {
    final g<? super io.reactivex.g<Throwable>, ? extends b<?>> c;

    static final class RetryWhenSubscriber<T> extends WhenSourceSubscriber<T, Throwable> {
        private static final long serialVersionUID = -2680129890138081029L;

        RetryWhenSubscriber(c<? super T> cVar, a<Throwable> aVar, Subscription subscription) {
            super(cVar, aVar, subscription);
        }

        public void onError(Throwable th) {
            again(th);
        }

        public void onComplete() {
            this.receiver.cancel();
            this.actual.onComplete();
        }
    }

    public void a(c<? super T> cVar) {
        io.reactivex.subscribers.b bVar = new io.reactivex.subscribers.b(cVar);
        a g = UnicastProcessor.a(8).g();
        try {
            b bVar2 = (b) io.reactivex.internal.functions.a.a(this.c.apply(g), "handler returned a null Publisher");
            WhenReceiver whenReceiver = new WhenReceiver(this.b);
            RetryWhenSubscriber retryWhenSubscriber = new RetryWhenSubscriber(bVar, g, whenReceiver);
            whenReceiver.subscriber = retryWhenSubscriber;
            cVar.onSubscribe(retryWhenSubscriber);
            bVar2.subscribe(whenReceiver);
            whenReceiver.onNext(Integer.valueOf(0));
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            EmptySubscription.error(th, cVar);
        }
    }
}
