package io.reactivex.internal.operators.flowable;

import io.reactivex.functions.g;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.e;
import io.reactivex.j;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;
import org.reactivestreams.Subscription;
import org.reactivestreams.b;
import org.reactivestreams.c;

public final class FlowableWithLatestFromMany<T, R> extends a<T, R> {
    final b<?>[] c;
    final Iterable<? extends b<?>> d;
    final g<? super Object[], R> e;

    static final class WithLatestFromSubscriber<T, R> extends AtomicInteger implements io.reactivex.internal.a.a<T>, Subscription {
        private static final long serialVersionUID = 1577321883966341961L;
        final c<? super R> actual;
        final g<? super Object[], R> combiner;
        volatile boolean done;
        final AtomicThrowable error;
        final AtomicLong requested;
        final AtomicReference<Subscription> s;
        final WithLatestInnerSubscriber[] subscribers;
        final AtomicReferenceArray<Object> values;

        WithLatestFromSubscriber(c<? super R> cVar, g<? super Object[], R> gVar, int i) {
            this.actual = cVar;
            this.combiner = gVar;
            WithLatestInnerSubscriber[] withLatestInnerSubscriberArr = new WithLatestInnerSubscriber[i];
            for (int i2 = 0; i2 < i; i2++) {
                withLatestInnerSubscriberArr[i2] = new WithLatestInnerSubscriber(this, i2);
            }
            this.subscribers = withLatestInnerSubscriberArr;
            this.values = new AtomicReferenceArray<>(i);
            this.s = new AtomicReference<>();
            this.requested = new AtomicLong();
            this.error = new AtomicThrowable();
        }

        /* access modifiers changed from: 0000 */
        public void subscribe(b<?>[] bVarArr, int i) {
            WithLatestInnerSubscriber[] withLatestInnerSubscriberArr = this.subscribers;
            AtomicReference<Subscription> atomicReference = this.s;
            for (int i2 = 0; i2 < i && !SubscriptionHelper.isCancelled((Subscription) atomicReference.get()); i2++) {
                bVarArr[i2].subscribe(withLatestInnerSubscriberArr[i2]);
            }
        }

        public void onSubscribe(Subscription subscription) {
            SubscriptionHelper.deferredSetOnce(this.s, this.requested, subscription);
        }

        public void onNext(T t) {
            if (!tryOnNext(t) && !this.done) {
                ((Subscription) this.s.get()).request(1);
            }
        }

        public boolean tryOnNext(T t) {
            if (this.done) {
                return false;
            }
            AtomicReferenceArray<Object> atomicReferenceArray = this.values;
            int length = atomicReferenceArray.length();
            Object[] objArr = new Object[(length + 1)];
            objArr[0] = t;
            int i = 0;
            while (i < length) {
                Object obj = atomicReferenceArray.get(i);
                if (obj == null) {
                    return false;
                }
                i++;
                objArr[i] = obj;
            }
            try {
                e.a(this.actual, io.reactivex.internal.functions.a.a(this.combiner.apply(objArr), "The combiner returned a null value"), (AtomicInteger) this, this.error);
                return true;
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                cancel();
                onError(th);
                return false;
            }
        }

        public void onError(Throwable th) {
            if (this.done) {
                io.reactivex.d.a.a(th);
                return;
            }
            this.done = true;
            cancelAllBut(-1);
            e.a(this.actual, th, (AtomicInteger) this, this.error);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                cancelAllBut(-1);
                e.a(this.actual, (AtomicInteger) this, this.error);
            }
        }

        public void request(long j) {
            SubscriptionHelper.deferredRequest(this.s, this.requested, j);
        }

        public void cancel() {
            SubscriptionHelper.cancel(this.s);
            for (WithLatestInnerSubscriber dispose : this.subscribers) {
                dispose.dispose();
            }
        }

        /* access modifiers changed from: 0000 */
        public void innerNext(int i, Object obj) {
            this.values.set(i, obj);
        }

        /* access modifiers changed from: 0000 */
        public void innerError(int i, Throwable th) {
            this.done = true;
            SubscriptionHelper.cancel(this.s);
            cancelAllBut(i);
            e.a(this.actual, th, (AtomicInteger) this, this.error);
        }

        /* access modifiers changed from: 0000 */
        public void innerComplete(int i, boolean z) {
            if (!z) {
                this.done = true;
                SubscriptionHelper.cancel(this.s);
                cancelAllBut(i);
                e.a(this.actual, (AtomicInteger) this, this.error);
            }
        }

        /* access modifiers changed from: 0000 */
        public void cancelAllBut(int i) {
            WithLatestInnerSubscriber[] withLatestInnerSubscriberArr = this.subscribers;
            for (int i2 = 0; i2 < withLatestInnerSubscriberArr.length; i2++) {
                if (i2 != i) {
                    withLatestInnerSubscriberArr[i2].dispose();
                }
            }
        }
    }

    static final class WithLatestInnerSubscriber extends AtomicReference<Subscription> implements j<Object> {
        private static final long serialVersionUID = 3256684027868224024L;
        boolean hasValue;
        final int index;
        final WithLatestFromSubscriber<?, ?> parent;

        WithLatestInnerSubscriber(WithLatestFromSubscriber<?, ?> withLatestFromSubscriber, int i) {
            this.parent = withLatestFromSubscriber;
            this.index = i;
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.setOnce(this, subscription)) {
                subscription.request(Long.MAX_VALUE);
            }
        }

        public void onNext(Object obj) {
            if (!this.hasValue) {
                this.hasValue = true;
            }
            this.parent.innerNext(this.index, obj);
        }

        public void onError(Throwable th) {
            this.parent.innerError(this.index, th);
        }

        public void onComplete() {
            this.parent.innerComplete(this.index, this.hasValue);
        }

        /* access modifiers changed from: 0000 */
        public void dispose() {
            SubscriptionHelper.cancel(this);
        }
    }

    final class a implements g<T, R> {
        a() {
        }

        public R apply(T t) throws Exception {
            return io.reactivex.internal.functions.a.a(FlowableWithLatestFromMany.this.e.apply(new Object[]{t}), "The combiner returned a null value");
        }
    }

    /* access modifiers changed from: protected */
    public void a(c<? super R> cVar) {
        int i;
        b<?>[] bVarArr = this.c;
        if (bVarArr == null) {
            bVarArr = new b[8];
            try {
                i = 0;
                for (b<?> bVar : this.d) {
                    if (i == bVarArr.length) {
                        bVarArr = (b[]) Arrays.copyOf(bVarArr, (i >> 1) + i);
                    }
                    int i2 = i + 1;
                    bVarArr[i] = bVar;
                    i = i2;
                }
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                EmptySubscription.error(th, cVar);
                return;
            }
        } else {
            i = bVarArr.length;
        }
        if (i == 0) {
            new d(this.b, new a()).a(cVar);
            return;
        }
        WithLatestFromSubscriber withLatestFromSubscriber = new WithLatestFromSubscriber(cVar, this.e, i);
        cVar.onSubscribe(withLatestFromSubscriber);
        withLatestFromSubscriber.subscribe(bVarArr, i);
        this.b.a((j<? super T>) withLatestFromSubscriber);
    }
}
