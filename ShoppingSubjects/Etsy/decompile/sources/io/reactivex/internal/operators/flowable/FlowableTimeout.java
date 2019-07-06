package io.reactivex.internal.operators.flowable;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.g;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.internal.subscriptions.SubscriptionArbiter;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;
import org.reactivestreams.b;
import org.reactivestreams.c;

public final class FlowableTimeout<T, U, V> extends a<T, T> {
    final b<U> c;
    final g<? super T, ? extends b<V>> d;
    final b<? extends T> e;

    static final class TimeoutConsumer extends AtomicReference<Subscription> implements Disposable, j<Object> {
        private static final long serialVersionUID = 8708641127342403073L;
        final long idx;
        final a parent;

        TimeoutConsumer(long j, a aVar) {
            this.idx = j;
            this.parent = aVar;
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.setOnce(this, subscription)) {
                subscription.request(Long.MAX_VALUE);
            }
        }

        public void onNext(Object obj) {
            Subscription subscription = (Subscription) get();
            if (subscription != SubscriptionHelper.CANCELLED) {
                subscription.cancel();
                lazySet(SubscriptionHelper.CANCELLED);
                this.parent.onTimeout(this.idx);
            }
        }

        public void onError(Throwable th) {
            if (get() != SubscriptionHelper.CANCELLED) {
                lazySet(SubscriptionHelper.CANCELLED);
                this.parent.onTimeoutError(this.idx, th);
                return;
            }
            io.reactivex.d.a.a(th);
        }

        public void onComplete() {
            if (get() != SubscriptionHelper.CANCELLED) {
                lazySet(SubscriptionHelper.CANCELLED);
                this.parent.onTimeout(this.idx);
            }
        }

        public void dispose() {
            SubscriptionHelper.cancel(this);
        }

        public boolean isDisposed() {
            return SubscriptionHelper.isCancelled((Subscription) get());
        }
    }

    static final class TimeoutFallbackSubscriber<T> extends SubscriptionArbiter implements a, j<T> {
        private static final long serialVersionUID = 3764492702657003550L;
        final c<? super T> actual;
        long consumed;
        b<? extends T> fallback;
        final AtomicLong index;
        final g<? super T, ? extends b<?>> itemTimeoutIndicator;
        final SequentialDisposable task = new SequentialDisposable();
        final AtomicReference<Subscription> upstream = new AtomicReference<>();

        TimeoutFallbackSubscriber(c<? super T> cVar, g<? super T, ? extends b<?>> gVar, b<? extends T> bVar) {
            this.actual = cVar;
            this.itemTimeoutIndicator = gVar;
            this.fallback = bVar;
            this.index = new AtomicLong();
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
                    Disposable disposable = (Disposable) this.task.get();
                    if (disposable != null) {
                        disposable.dispose();
                    }
                    this.consumed++;
                    this.actual.onNext(t);
                    try {
                        b bVar = (b) io.reactivex.internal.functions.a.a(this.itemTimeoutIndicator.apply(t), "The itemTimeoutIndicator returned a null Publisher.");
                        TimeoutConsumer timeoutConsumer = new TimeoutConsumer(j2, this);
                        if (this.task.replace(timeoutConsumer)) {
                            bVar.subscribe(timeoutConsumer);
                        }
                    } catch (Throwable th) {
                        io.reactivex.exceptions.a.b(th);
                        ((Subscription) this.upstream.get()).cancel();
                        this.index.getAndSet(Long.MAX_VALUE);
                        this.actual.onError(th);
                    }
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void startFirstTimeout(b<?> bVar) {
            if (bVar != null) {
                TimeoutConsumer timeoutConsumer = new TimeoutConsumer(0, this);
                if (this.task.replace(timeoutConsumer)) {
                    bVar.subscribe(timeoutConsumer);
                }
            }
        }

        public void onError(Throwable th) {
            if (this.index.getAndSet(Long.MAX_VALUE) != Long.MAX_VALUE) {
                this.task.dispose();
                this.actual.onError(th);
                this.task.dispose();
                return;
            }
            io.reactivex.d.a.a(th);
        }

        public void onComplete() {
            if (this.index.getAndSet(Long.MAX_VALUE) != Long.MAX_VALUE) {
                this.task.dispose();
                this.actual.onComplete();
                this.task.dispose();
            }
        }

        public void onTimeout(long j) {
            if (this.index.compareAndSet(j, Long.MAX_VALUE)) {
                SubscriptionHelper.cancel(this.upstream);
                b<? extends T> bVar = this.fallback;
                this.fallback = null;
                long j2 = this.consumed;
                if (j2 != 0) {
                    produced(j2);
                }
                bVar.subscribe(new a(this.actual, this));
            }
        }

        public void onTimeoutError(long j, Throwable th) {
            if (this.index.compareAndSet(j, Long.MAX_VALUE)) {
                SubscriptionHelper.cancel(this.upstream);
                this.actual.onError(th);
                return;
            }
            io.reactivex.d.a.a(th);
        }

        public void cancel() {
            super.cancel();
            this.task.dispose();
        }
    }

    static final class TimeoutSubscriber<T> extends AtomicLong implements a, j<T>, Subscription {
        private static final long serialVersionUID = 3764492702657003550L;
        final c<? super T> actual;
        final g<? super T, ? extends b<?>> itemTimeoutIndicator;
        final AtomicLong requested = new AtomicLong();
        final SequentialDisposable task = new SequentialDisposable();
        final AtomicReference<Subscription> upstream = new AtomicReference<>();

        TimeoutSubscriber(c<? super T> cVar, g<? super T, ? extends b<?>> gVar) {
            this.actual = cVar;
            this.itemTimeoutIndicator = gVar;
        }

        public void onSubscribe(Subscription subscription) {
            SubscriptionHelper.deferredSetOnce(this.upstream, this.requested, subscription);
        }

        public void onNext(T t) {
            long j = get();
            if (j != Long.MAX_VALUE) {
                long j2 = j + 1;
                if (compareAndSet(j, j2)) {
                    Disposable disposable = (Disposable) this.task.get();
                    if (disposable != null) {
                        disposable.dispose();
                    }
                    this.actual.onNext(t);
                    try {
                        b bVar = (b) io.reactivex.internal.functions.a.a(this.itemTimeoutIndicator.apply(t), "The itemTimeoutIndicator returned a null Publisher.");
                        TimeoutConsumer timeoutConsumer = new TimeoutConsumer(j2, this);
                        if (this.task.replace(timeoutConsumer)) {
                            bVar.subscribe(timeoutConsumer);
                        }
                    } catch (Throwable th) {
                        io.reactivex.exceptions.a.b(th);
                        ((Subscription) this.upstream.get()).cancel();
                        getAndSet(Long.MAX_VALUE);
                        this.actual.onError(th);
                    }
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void startFirstTimeout(b<?> bVar) {
            if (bVar != null) {
                TimeoutConsumer timeoutConsumer = new TimeoutConsumer(0, this);
                if (this.task.replace(timeoutConsumer)) {
                    bVar.subscribe(timeoutConsumer);
                }
            }
        }

        public void onError(Throwable th) {
            if (getAndSet(Long.MAX_VALUE) != Long.MAX_VALUE) {
                this.task.dispose();
                this.actual.onError(th);
                return;
            }
            io.reactivex.d.a.a(th);
        }

        public void onComplete() {
            if (getAndSet(Long.MAX_VALUE) != Long.MAX_VALUE) {
                this.task.dispose();
                this.actual.onComplete();
            }
        }

        public void onTimeout(long j) {
            if (compareAndSet(j, Long.MAX_VALUE)) {
                SubscriptionHelper.cancel(this.upstream);
                this.actual.onError(new TimeoutException());
            }
        }

        public void onTimeoutError(long j, Throwable th) {
            if (compareAndSet(j, Long.MAX_VALUE)) {
                SubscriptionHelper.cancel(this.upstream);
                this.actual.onError(th);
                return;
            }
            io.reactivex.d.a.a(th);
        }

        public void request(long j) {
            SubscriptionHelper.deferredRequest(this.upstream, this.requested, j);
        }

        public void cancel() {
            SubscriptionHelper.cancel(this.upstream);
            this.task.dispose();
        }
    }

    interface a extends b {
        void onTimeoutError(long j, Throwable th);
    }

    /* access modifiers changed from: protected */
    public void a(c<? super T> cVar) {
        if (this.e == null) {
            TimeoutSubscriber timeoutSubscriber = new TimeoutSubscriber(cVar, this.d);
            cVar.onSubscribe(timeoutSubscriber);
            timeoutSubscriber.startFirstTimeout(this.c);
            this.b.a((j<? super T>) timeoutSubscriber);
            return;
        }
        TimeoutFallbackSubscriber timeoutFallbackSubscriber = new TimeoutFallbackSubscriber(cVar, this.d, this.e);
        cVar.onSubscribe(timeoutFallbackSubscriber);
        timeoutFallbackSubscriber.startFirstTimeout(this.c);
        this.b.a((j<? super T>) timeoutFallbackSubscriber);
    }
}
