package io.reactivex.internal.operators.flowable;

import io.reactivex.functions.c;
import io.reactivex.internal.a.f;
import io.reactivex.internal.functions.a;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.b;
import io.reactivex.j;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.reactivestreams.Subscription;

public final class FlowableScanSeed<T, R> extends a<T, R> {
    final c<R, ? super T, R> c;
    final Callable<R> d;

    static final class ScanSeedSubscriber<T, R> extends AtomicInteger implements j<T>, Subscription {
        private static final long serialVersionUID = -1776795561228106469L;
        final c<R, ? super T, R> accumulator;
        final org.reactivestreams.c<? super R> actual;
        volatile boolean cancelled;
        int consumed;
        volatile boolean done;
        Throwable error;
        final int limit;
        final int prefetch;
        final f<R> queue;
        final AtomicLong requested = new AtomicLong();
        Subscription s;
        R value;

        ScanSeedSubscriber(org.reactivestreams.c<? super R> cVar, c<R, ? super T, R> cVar2, R r, int i) {
            this.actual = cVar;
            this.accumulator = cVar2;
            this.value = r;
            this.prefetch = i;
            this.limit = i - (i >> 2);
            this.queue = new SpscArrayQueue(i);
            this.queue.offer(r);
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.s, subscription)) {
                this.s = subscription;
                this.actual.onSubscribe(this);
                subscription.request((long) (this.prefetch - 1));
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                try {
                    R a = a.a(this.accumulator.apply(this.value, t), "The accumulator returned a null value");
                    this.value = a;
                    this.queue.offer(a);
                    drain();
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    this.s.cancel();
                    onError(th);
                }
            }
        }

        public void onError(Throwable th) {
            if (this.done) {
                io.reactivex.d.a.a(th);
                return;
            }
            this.error = th;
            this.done = true;
            drain();
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                drain();
            }
        }

        public void cancel() {
            this.cancelled = true;
            this.s.cancel();
            if (getAndIncrement() == 0) {
                this.queue.clear();
            }
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                b.a(this.requested, j);
                drain();
            }
        }

        /* access modifiers changed from: 0000 */
        public void drain() {
            if (getAndIncrement() == 0) {
                org.reactivestreams.c<? super R> cVar = this.actual;
                f<R> fVar = this.queue;
                int i = this.limit;
                int i2 = this.consumed;
                int i3 = 1;
                do {
                    long j = this.requested.get();
                    long j2 = 0;
                    while (j2 != j) {
                        if (this.cancelled) {
                            fVar.clear();
                            return;
                        }
                        boolean z = this.done;
                        if (z) {
                            Throwable th = this.error;
                            if (th != null) {
                                fVar.clear();
                                cVar.onError(th);
                                return;
                            }
                        }
                        Object poll = fVar.poll();
                        boolean z2 = poll == null;
                        if (z && z2) {
                            cVar.onComplete();
                            return;
                        } else if (z2) {
                            break;
                        } else {
                            cVar.onNext(poll);
                            long j3 = j2 + 1;
                            i2++;
                            if (i2 == i) {
                                this.s.request((long) i);
                                i2 = 0;
                            }
                            j2 = j3;
                        }
                    }
                    if (j2 == j && this.done) {
                        Throwable th2 = this.error;
                        if (th2 != null) {
                            fVar.clear();
                            cVar.onError(th2);
                            return;
                        } else if (fVar.isEmpty()) {
                            cVar.onComplete();
                            return;
                        }
                    }
                    if (j2 != 0) {
                        b.c(this.requested, j2);
                    }
                    this.consumed = i2;
                    i3 = addAndGet(-i3);
                } while (i3 != 0);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a(org.reactivestreams.c<? super R> cVar) {
        try {
            this.b.a((j<? super T>) new ScanSeedSubscriber<Object>(cVar, this.c, a.a(this.d.call(), "The seed supplied is null"), a()));
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            EmptySubscription.error(th, cVar);
        }
    }
}
