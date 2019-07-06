package io.reactivex.internal.operators.parallel;

import io.reactivex.functions.b;
import io.reactivex.internal.subscribers.DeferredScalarSubscriber;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.parallel.a;
import java.util.concurrent.Callable;
import org.reactivestreams.Subscription;
import org.reactivestreams.c;

public final class ParallelCollect<T, C> extends a<C> {
    final a<? extends T> a;
    final Callable<? extends C> b;
    final b<? super C, ? super T> c;

    static final class ParallelCollectSubscriber<T, C> extends DeferredScalarSubscriber<T, C> {
        private static final long serialVersionUID = -4767392946044436228L;
        C collection;
        final b<? super C, ? super T> collector;
        boolean done;

        ParallelCollectSubscriber(c<? super C> cVar, C c, b<? super C, ? super T> bVar) {
            super(cVar);
            this.collection = c;
            this.collector = bVar;
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
                    this.collector.a(this.collection, t);
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    cancel();
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
            this.collection = null;
            this.actual.onError(th);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                C c = this.collection;
                this.collection = null;
                complete(c);
            }
        }

        public void cancel() {
            super.cancel();
            this.s.cancel();
        }
    }

    public void a(c<? super C>[] cVarArr) {
        if (b(cVarArr)) {
            int length = cVarArr.length;
            c[] cVarArr2 = new c[length];
            int i = 0;
            while (i < length) {
                try {
                    cVarArr2[i] = new ParallelCollectSubscriber(cVarArr[i], io.reactivex.internal.functions.a.a(this.b.call(), "The initialSupplier returned a null value"), this.c);
                    i++;
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    a(cVarArr, th);
                    return;
                }
            }
            this.a.a(cVarArr2);
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(c<?>[] cVarArr, Throwable th) {
        for (c<?> error : cVarArr) {
            EmptySubscription.error(th, error);
        }
    }

    public int a() {
        return this.a.a();
    }
}
