package io.reactivex.internal.operators.flowable;

import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.internal.subscriptions.SubscriptionArbiter;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import io.reactivex.u;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;

public final class FlowableTimeoutTimed<T> extends a<T, T> {
    final long c;
    final TimeUnit d;
    final u e;
    final org.reactivestreams.b<? extends T> f;

    static final class TimeoutFallbackSubscriber<T> extends SubscriptionArbiter implements b, j<T> {
        private static final long serialVersionUID = 3764492702657003550L;
        final org.reactivestreams.c<? super T> actual;
        long consumed;
        org.reactivestreams.b<? extends T> fallback;
        final AtomicLong index = new AtomicLong();
        final SequentialDisposable task = new SequentialDisposable();
        final long timeout;
        final TimeUnit unit;
        final AtomicReference<Subscription> upstream = new AtomicReference<>();
        final io.reactivex.u.c worker;

        TimeoutFallbackSubscriber(org.reactivestreams.c<? super T> cVar, long j, TimeUnit timeUnit, io.reactivex.u.c cVar2, org.reactivestreams.b<? extends T> bVar) {
            this.actual = cVar;
            this.timeout = j;
            this.unit = timeUnit;
            this.worker = cVar2;
            this.fallback = bVar;
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.setOnce(this.upstream, subscription)) {
                setSubscription(subscription);
            }
        }

        public void onNext(T t) {
            long j = this.index.get();
            if (j != Long.MAX_VALUE) {
                long j2 = j + 1;
                if (this.index.compareAndSet(j, j2)) {
                    ((Disposable) this.task.get()).dispose();
                    this.consumed++;
                    this.actual.onNext(t);
                    startTimeout(j2);
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void startTimeout(long j) {
            this.task.replace(this.worker.a(new c(j, this), this.timeout, this.unit));
        }

        public void onError(Throwable th) {
            if (this.index.getAndSet(Long.MAX_VALUE) != Long.MAX_VALUE) {
                this.task.dispose();
                this.actual.onError(th);
                this.worker.dispose();
                return;
            }
            io.reactivex.d.a.a(th);
        }

        public void onComplete() {
            if (this.index.getAndSet(Long.MAX_VALUE) != Long.MAX_VALUE) {
                this.task.dispose();
                this.actual.onComplete();
                this.worker.dispose();
            }
        }

        public void onTimeout(long j) {
            if (this.index.compareAndSet(j, Long.MAX_VALUE)) {
                SubscriptionHelper.cancel(this.upstream);
                long j2 = this.consumed;
                if (j2 != 0) {
                    produced(j2);
                }
                org.reactivestreams.b<? extends T> bVar = this.fallback;
                this.fallback = null;
                bVar.subscribe(new a(this.actual, this));
                this.worker.dispose();
            }
        }

        public void cancel() {
            super.cancel();
            this.worker.dispose();
        }
    }

    static final class TimeoutSubscriber<T> extends AtomicLong implements b, j<T>, Subscription {
        private static final long serialVersionUID = 3764492702657003550L;
        final org.reactivestreams.c<? super T> actual;
        final AtomicLong requested = new AtomicLong();
        final SequentialDisposable task = new SequentialDisposable();
        final long timeout;
        final TimeUnit unit;
        final AtomicReference<Subscription> upstream = new AtomicReference<>();
        final io.reactivex.u.c worker;

        TimeoutSubscriber(org.reactivestreams.c<? super T> cVar, long j, TimeUnit timeUnit, io.reactivex.u.c cVar2) {
            this.actual = cVar;
            this.timeout = j;
            this.unit = timeUnit;
            this.worker = cVar2;
        }

        public void onSubscribe(Subscription subscription) {
            SubscriptionHelper.deferredSetOnce(this.upstream, this.requested, subscription);
        }

        public void onNext(T t) {
            long j = get();
            if (j != Long.MAX_VALUE) {
                long j2 = j + 1;
                if (compareAndSet(j, j2)) {
                    ((Disposable) this.task.get()).dispose();
                    this.actual.onNext(t);
                    startTimeout(j2);
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void startTimeout(long j) {
            this.task.replace(this.worker.a(new c(j, this), this.timeout, this.unit));
        }

        public void onError(Throwable th) {
            if (getAndSet(Long.MAX_VALUE) != Long.MAX_VALUE) {
                this.task.dispose();
                this.actual.onError(th);
                this.worker.dispose();
                return;
            }
            io.reactivex.d.a.a(th);
        }

        public void onComplete() {
            if (getAndSet(Long.MAX_VALUE) != Long.MAX_VALUE) {
                this.task.dispose();
                this.actual.onComplete();
                this.worker.dispose();
            }
        }

        public void onTimeout(long j) {
            if (compareAndSet(j, Long.MAX_VALUE)) {
                SubscriptionHelper.cancel(this.upstream);
                this.actual.onError(new TimeoutException());
                this.worker.dispose();
            }
        }

        public void request(long j) {
            SubscriptionHelper.deferredRequest(this.upstream, this.requested, j);
        }

        public void cancel() {
            SubscriptionHelper.cancel(this.upstream);
            this.worker.dispose();
        }
    }

    static final class a<T> implements j<T> {
        final org.reactivestreams.c<? super T> a;
        final SubscriptionArbiter b;

        a(org.reactivestreams.c<? super T> cVar, SubscriptionArbiter subscriptionArbiter) {
            this.a = cVar;
            this.b = subscriptionArbiter;
        }

        public void onSubscribe(Subscription subscription) {
            this.b.setSubscription(subscription);
        }

        public void onNext(T t) {
            this.a.onNext(t);
        }

        public void onError(Throwable th) {
            this.a.onError(th);
        }

        public void onComplete() {
            this.a.onComplete();
        }
    }

    interface b {
        void onTimeout(long j);
    }

    static final class c implements Runnable {
        final b a;
        final long b;

        c(long j, b bVar) {
            this.b = j;
            this.a = bVar;
        }

        public void run() {
            this.a.onTimeout(this.b);
        }
    }

    /* access modifiers changed from: protected */
    public void a(org.reactivestreams.c<? super T> cVar) {
        if (this.f == null) {
            TimeoutSubscriber timeoutSubscriber = new TimeoutSubscriber(cVar, this.c, this.d, this.e.a());
            cVar.onSubscribe(timeoutSubscriber);
            timeoutSubscriber.startTimeout(0);
            this.b.a((j<? super T>) timeoutSubscriber);
            return;
        }
        TimeoutFallbackSubscriber timeoutFallbackSubscriber = new TimeoutFallbackSubscriber(cVar, this.c, this.d, this.e.a(), this.f);
        cVar.onSubscribe(timeoutFallbackSubscriber);
        timeoutFallbackSubscriber.startTimeout(0);
        this.b.a((j<? super T>) timeoutFallbackSubscriber);
    }
}
