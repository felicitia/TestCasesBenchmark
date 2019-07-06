package io.reactivex.internal.operators.parallel;

import io.reactivex.functions.c;
import io.reactivex.internal.subscribers.DeferredScalarSubscriber;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.parallel.a;
import java.util.concurrent.Callable;
import org.reactivestreams.Subscription;

public final class ParallelReduce<T, R> extends a<R> {
    final a<? extends T> a;
    final Callable<R> b;
    final c<R, ? super T, R> c;

    static final class ParallelReduceSubscriber<T, R> extends DeferredScalarSubscriber<T, R> {
        private static final long serialVersionUID = 8200530050639449080L;
        R accumulator;
        boolean done;
        final c<R, ? super T, R> reducer;

        ParallelReduceSubscriber(org.reactivestreams.c<? super R> cVar, R r, c<R, ? super T, R> cVar2) {
            super(cVar);
            this.accumulator = r;
            this.reducer = cVar2;
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
                    this.accumulator = io.reactivex.internal.functions.a.a(this.reducer.apply(this.accumulator, t), "The reducer returned a null value");
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
            this.accumulator = null;
            this.actual.onError(th);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                R r = this.accumulator;
                this.accumulator = null;
                complete(r);
            }
        }

        public void cancel() {
            super.cancel();
            this.s.cancel();
        }
    }

    public void a(org.reactivestreams.c<? super R>[] cVarArr) {
        if (b(cVarArr)) {
            int length = cVarArr.length;
            org.reactivestreams.c[] cVarArr2 = new org.reactivestreams.c[length];
            int i = 0;
            while (i < length) {
                try {
                    cVarArr2[i] = new ParallelReduceSubscriber(cVarArr[i], io.reactivex.internal.functions.a.a(this.b.call(), "The initialSupplier returned a null value"), this.c);
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
    public void a(org.reactivestreams.c<?>[] cVarArr, Throwable th) {
        for (org.reactivestreams.c<?> error : cVarArr) {
            EmptySubscription.error(th, error);
        }
    }

    public int a() {
        return this.a.a();
    }
}
