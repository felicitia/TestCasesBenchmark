package io.reactivex.internal.operators.flowable;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.g;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.queue.a;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.b;
import io.reactivex.j;
import io.reactivex.m;
import io.reactivex.o;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;
import org.reactivestreams.c;

public final class FlowableFlatMapMaybe<T, R> extends a<T, R> {
    final g<? super T, ? extends o<? extends R>> c;
    final boolean d;
    final int e;

    static final class FlatMapMaybeSubscriber<T, R> extends AtomicInteger implements j<T>, Subscription {
        private static final long serialVersionUID = 8600231336733376951L;
        final AtomicInteger active = new AtomicInteger(1);
        final c<? super R> actual;
        volatile boolean cancelled;
        final boolean delayErrors;
        final AtomicThrowable errors = new AtomicThrowable();
        final g<? super T, ? extends o<? extends R>> mapper;
        final int maxConcurrency;
        final AtomicReference<a<R>> queue = new AtomicReference<>();
        final AtomicLong requested = new AtomicLong();
        Subscription s;
        final io.reactivex.disposables.a set = new io.reactivex.disposables.a();

        final class InnerObserver extends AtomicReference<Disposable> implements Disposable, m<R> {
            private static final long serialVersionUID = -502562646270949838L;

            InnerObserver() {
            }

            public void onSubscribe(Disposable disposable) {
                DisposableHelper.setOnce(this, disposable);
            }

            public void onSuccess(R r) {
                FlatMapMaybeSubscriber.this.innerSuccess(this, r);
            }

            public void onError(Throwable th) {
                FlatMapMaybeSubscriber.this.innerError(this, th);
            }

            public void onComplete() {
                FlatMapMaybeSubscriber.this.innerComplete(this);
            }

            public boolean isDisposed() {
                return DisposableHelper.isDisposed((Disposable) get());
            }

            public void dispose() {
                DisposableHelper.dispose(this);
            }
        }

        FlatMapMaybeSubscriber(c<? super R> cVar, g<? super T, ? extends o<? extends R>> gVar, boolean z, int i) {
            this.actual = cVar;
            this.mapper = gVar;
            this.delayErrors = z;
            this.maxConcurrency = i;
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.s, subscription)) {
                this.s = subscription;
                this.actual.onSubscribe(this);
                if (this.maxConcurrency == Integer.MAX_VALUE) {
                    subscription.request(Long.MAX_VALUE);
                } else {
                    subscription.request((long) this.maxConcurrency);
                }
            }
        }

        public void onNext(T t) {
            try {
                o oVar = (o) io.reactivex.internal.functions.a.a(this.mapper.apply(t), "The mapper returned a null MaybeSource");
                this.active.getAndIncrement();
                InnerObserver innerObserver = new InnerObserver();
                if (!this.cancelled && this.set.a((Disposable) innerObserver)) {
                    oVar.a(innerObserver);
                }
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                this.s.cancel();
                onError(th);
            }
        }

        public void onError(Throwable th) {
            this.active.decrementAndGet();
            if (this.errors.addThrowable(th)) {
                if (!this.delayErrors) {
                    this.set.dispose();
                }
                drain();
                return;
            }
            io.reactivex.d.a.a(th);
        }

        public void onComplete() {
            this.active.decrementAndGet();
            drain();
        }

        public void cancel() {
            this.cancelled = true;
            this.s.cancel();
            this.set.dispose();
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                b.a(this.requested, j);
                drain();
            }
        }

        /* access modifiers changed from: 0000 */
        public void innerSuccess(InnerObserver innerObserver, R r) {
            this.set.c(innerObserver);
            if (get() == 0) {
                boolean z = true;
                if (compareAndSet(0, 1)) {
                    if (this.active.decrementAndGet() != 0) {
                        z = false;
                    }
                    if (this.requested.get() != 0) {
                        this.actual.onNext(r);
                        a aVar = (a) this.queue.get();
                        if (!z || (aVar != null && !aVar.isEmpty())) {
                            b.c(this.requested, 1);
                            if (this.maxConcurrency != Integer.MAX_VALUE) {
                                this.s.request(1);
                            }
                        } else {
                            Throwable terminate = this.errors.terminate();
                            if (terminate != null) {
                                this.actual.onError(terminate);
                            } else {
                                this.actual.onComplete();
                            }
                            return;
                        }
                    } else {
                        a orCreateQueue = getOrCreateQueue();
                        synchronized (orCreateQueue) {
                            orCreateQueue.offer(r);
                        }
                    }
                    if (decrementAndGet() == 0) {
                        return;
                    }
                    drainLoop();
                }
            }
            a orCreateQueue2 = getOrCreateQueue();
            synchronized (orCreateQueue2) {
                orCreateQueue2.offer(r);
            }
            this.active.decrementAndGet();
            if (getAndIncrement() != 0) {
                return;
            }
            drainLoop();
        }

        /* access modifiers changed from: 0000 */
        public a<R> getOrCreateQueue() {
            a<R> aVar;
            do {
                a<R> aVar2 = (a) this.queue.get();
                if (aVar2 != null) {
                    return aVar2;
                }
                aVar = new a<>(io.reactivex.g.a());
            } while (!this.queue.compareAndSet(null, aVar));
            return aVar;
        }

        /* access modifiers changed from: 0000 */
        public void innerError(InnerObserver innerObserver, Throwable th) {
            this.set.c(innerObserver);
            if (this.errors.addThrowable(th)) {
                if (!this.delayErrors) {
                    this.s.cancel();
                    this.set.dispose();
                } else if (this.maxConcurrency != Integer.MAX_VALUE) {
                    this.s.request(1);
                }
                this.active.decrementAndGet();
                drain();
                return;
            }
            io.reactivex.d.a.a(th);
        }

        /* access modifiers changed from: 0000 */
        public void innerComplete(InnerObserver innerObserver) {
            this.set.c(innerObserver);
            if (get() == 0) {
                boolean z = true;
                if (compareAndSet(0, 1)) {
                    if (this.active.decrementAndGet() != 0) {
                        z = false;
                    }
                    a aVar = (a) this.queue.get();
                    if (!z || (aVar != null && !aVar.isEmpty())) {
                        if (this.maxConcurrency != Integer.MAX_VALUE) {
                            this.s.request(1);
                        }
                        if (decrementAndGet() != 0) {
                            drainLoop();
                        }
                        return;
                    }
                    Throwable terminate = this.errors.terminate();
                    if (terminate != null) {
                        this.actual.onError(terminate);
                    } else {
                        this.actual.onComplete();
                    }
                    return;
                }
            }
            this.active.decrementAndGet();
            if (this.maxConcurrency != Integer.MAX_VALUE) {
                this.s.request(1);
            }
            drain();
        }

        /* access modifiers changed from: 0000 */
        public void drain() {
            if (getAndIncrement() == 0) {
                drainLoop();
            }
        }

        /* access modifiers changed from: 0000 */
        public void clear() {
            a aVar = (a) this.queue.get();
            if (aVar != null) {
                aVar.clear();
            }
        }

        /* access modifiers changed from: 0000 */
        public void drainLoop() {
            boolean z;
            c<? super R> cVar = this.actual;
            AtomicInteger atomicInteger = this.active;
            AtomicReference<a<R>> atomicReference = this.queue;
            int i = 1;
            do {
                long j = this.requested.get();
                long j2 = 0;
                while (true) {
                    z = false;
                    if (j2 == j) {
                        break;
                    } else if (this.cancelled) {
                        clear();
                        return;
                    } else if (this.delayErrors || ((Throwable) this.errors.get()) == null) {
                        boolean z2 = atomicInteger.get() == 0;
                        a aVar = (a) atomicReference.get();
                        Object poll = aVar != null ? aVar.poll() : null;
                        boolean z3 = poll == null;
                        if (z2 && z3) {
                            Throwable terminate = this.errors.terminate();
                            if (terminate != null) {
                                cVar.onError(terminate);
                            } else {
                                cVar.onComplete();
                            }
                            return;
                        } else if (z3) {
                            break;
                        } else {
                            cVar.onNext(poll);
                            j2++;
                        }
                    } else {
                        Throwable terminate2 = this.errors.terminate();
                        clear();
                        cVar.onError(terminate2);
                        return;
                    }
                }
                if (j2 == j) {
                    if (this.cancelled) {
                        clear();
                        return;
                    } else if (this.delayErrors || ((Throwable) this.errors.get()) == null) {
                        boolean z4 = atomicInteger.get() == 0;
                        a aVar2 = (a) atomicReference.get();
                        if (aVar2 == null || aVar2.isEmpty()) {
                            z = true;
                        }
                        if (z4 && z) {
                            Throwable terminate3 = this.errors.terminate();
                            if (terminate3 != null) {
                                cVar.onError(terminate3);
                            } else {
                                cVar.onComplete();
                            }
                            return;
                        }
                    } else {
                        Throwable terminate4 = this.errors.terminate();
                        clear();
                        cVar.onError(terminate4);
                        return;
                    }
                }
                if (j2 != 0) {
                    b.c(this.requested, j2);
                    if (this.maxConcurrency != Integer.MAX_VALUE) {
                        this.s.request(j2);
                    }
                }
                i = addAndGet(-i);
            } while (i != 0);
        }
    }

    /* access modifiers changed from: protected */
    public void a(c<? super R> cVar) {
        this.b.a((j<? super T>) new FlatMapMaybeSubscriber<Object>(cVar, this.c, this.d, this.e));
    }
}
