package io.reactivex.internal.operators.flowable;

import io.reactivex.functions.a;
import io.reactivex.internal.a.d;
import io.reactivex.internal.subscriptions.BasicIntQueueSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import org.reactivestreams.Subscription;
import org.reactivestreams.c;

public final class FlowableDoFinally<T> extends a<T, T> {
    final a c;

    static final class DoFinallyConditionalSubscriber<T> extends BasicIntQueueSubscription<T> implements io.reactivex.internal.a.a<T> {
        private static final long serialVersionUID = 4109457741734051389L;
        final io.reactivex.internal.a.a<? super T> actual;
        final a onFinally;
        d<T> qs;
        Subscription s;
        boolean syncFused;

        DoFinallyConditionalSubscriber(io.reactivex.internal.a.a<? super T> aVar, a aVar2) {
            this.actual = aVar;
            this.onFinally = aVar2;
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.s, subscription)) {
                this.s = subscription;
                if (subscription instanceof d) {
                    this.qs = (d) subscription;
                }
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            this.actual.onNext(t);
        }

        public boolean tryOnNext(T t) {
            return this.actual.tryOnNext(t);
        }

        public void onError(Throwable th) {
            this.actual.onError(th);
            runFinally();
        }

        public void onComplete() {
            this.actual.onComplete();
            runFinally();
        }

        public void cancel() {
            this.s.cancel();
            runFinally();
        }

        public void request(long j) {
            this.s.request(j);
        }

        public int requestFusion(int i) {
            d<T> dVar = this.qs;
            if (dVar == null || (i & 4) != 0) {
                return 0;
            }
            int requestFusion = dVar.requestFusion(i);
            if (requestFusion != 0) {
                boolean z = true;
                if (requestFusion != 1) {
                    z = false;
                }
                this.syncFused = z;
            }
            return requestFusion;
        }

        public void clear() {
            this.qs.clear();
        }

        public boolean isEmpty() {
            return this.qs.isEmpty();
        }

        public T poll() throws Exception {
            T poll = this.qs.poll();
            if (poll == null && this.syncFused) {
                runFinally();
            }
            return poll;
        }

        /* access modifiers changed from: 0000 */
        public void runFinally() {
            if (compareAndSet(0, 1)) {
                try {
                    this.onFinally.a();
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    io.reactivex.d.a.a(th);
                }
            }
        }
    }

    static final class DoFinallySubscriber<T> extends BasicIntQueueSubscription<T> implements j<T> {
        private static final long serialVersionUID = 4109457741734051389L;
        final c<? super T> actual;
        final a onFinally;
        d<T> qs;
        Subscription s;
        boolean syncFused;

        DoFinallySubscriber(c<? super T> cVar, a aVar) {
            this.actual = cVar;
            this.onFinally = aVar;
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.s, subscription)) {
                this.s = subscription;
                if (subscription instanceof d) {
                    this.qs = (d) subscription;
                }
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            this.actual.onNext(t);
        }

        public void onError(Throwable th) {
            this.actual.onError(th);
            runFinally();
        }

        public void onComplete() {
            this.actual.onComplete();
            runFinally();
        }

        public void cancel() {
            this.s.cancel();
            runFinally();
        }

        public void request(long j) {
            this.s.request(j);
        }

        public int requestFusion(int i) {
            d<T> dVar = this.qs;
            if (dVar == null || (i & 4) != 0) {
                return 0;
            }
            int requestFusion = dVar.requestFusion(i);
            if (requestFusion != 0) {
                boolean z = true;
                if (requestFusion != 1) {
                    z = false;
                }
                this.syncFused = z;
            }
            return requestFusion;
        }

        public void clear() {
            this.qs.clear();
        }

        public boolean isEmpty() {
            return this.qs.isEmpty();
        }

        public T poll() throws Exception {
            T poll = this.qs.poll();
            if (poll == null && this.syncFused) {
                runFinally();
            }
            return poll;
        }

        /* access modifiers changed from: 0000 */
        public void runFinally() {
            if (compareAndSet(0, 1)) {
                try {
                    this.onFinally.a();
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    io.reactivex.d.a.a(th);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a(c<? super T> cVar) {
        if (cVar instanceof io.reactivex.internal.a.a) {
            this.b.a((j<? super T>) new DoFinallyConditionalSubscriber<Object>((io.reactivex.internal.a.a) cVar, this.c));
        } else {
            this.b.a((j<? super T>) new DoFinallySubscriber<Object>(cVar, this.c));
        }
    }
}
