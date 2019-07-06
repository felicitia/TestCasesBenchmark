package io.reactivex.internal.operators.parallel;

import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.a.d;
import io.reactivex.internal.a.g;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import io.reactivex.parallel.a;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLongArray;
import org.reactivestreams.Subscription;
import org.reactivestreams.b;
import org.reactivestreams.c;

public final class ParallelFromPublisher<T> extends a<T> {
    final b<? extends T> a;
    final int b;
    final int c;

    static final class ParallelDispatcher<T> extends AtomicInteger implements j<T> {
        private static final long serialVersionUID = -4470634016609963609L;
        volatile boolean cancelled;
        volatile boolean done;
        final long[] emissions;
        Throwable error;
        int index;
        final int limit;
        final int prefetch;
        int produced;
        g<T> queue;
        final AtomicLongArray requests;
        Subscription s;
        int sourceMode;
        final AtomicInteger subscriberCount = new AtomicInteger();
        final c<? super T>[] subscribers;

        final class a implements Subscription {
            final int a;
            final int b;

            a(int i, int i2) {
                this.a = i;
                this.b = i2;
            }

            public void request(long j) {
                long j2;
                if (SubscriptionHelper.validate(j)) {
                    AtomicLongArray atomicLongArray = ParallelDispatcher.this.requests;
                    do {
                        j2 = atomicLongArray.get(this.a);
                        if (j2 != Long.MAX_VALUE) {
                        } else {
                            return;
                        }
                    } while (!atomicLongArray.compareAndSet(this.a, j2, io.reactivex.internal.util.b.a(j2, j)));
                    if (ParallelDispatcher.this.subscriberCount.get() == this.b) {
                        ParallelDispatcher.this.drain();
                    }
                }
            }

            public void cancel() {
                if (ParallelDispatcher.this.requests.compareAndSet(this.a + this.b, 0, 1)) {
                    ParallelDispatcher.this.cancel(this.b + this.b);
                }
            }
        }

        ParallelDispatcher(c<? super T>[] cVarArr, int i) {
            this.subscribers = cVarArr;
            this.prefetch = i;
            this.limit = i - (i >> 2);
            int length = cVarArr.length;
            int i2 = length + length;
            this.requests = new AtomicLongArray(i2 + 1);
            this.requests.lazySet(i2, (long) length);
            this.emissions = new long[length];
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.s, subscription)) {
                this.s = subscription;
                if (subscription instanceof d) {
                    d dVar = (d) subscription;
                    int requestFusion = dVar.requestFusion(7);
                    if (requestFusion == 1) {
                        this.sourceMode = requestFusion;
                        this.queue = dVar;
                        this.done = true;
                        setupSubscribers();
                        drain();
                        return;
                    } else if (requestFusion == 2) {
                        this.sourceMode = requestFusion;
                        this.queue = dVar;
                        setupSubscribers();
                        subscription.request((long) this.prefetch);
                        return;
                    }
                }
                this.queue = new SpscArrayQueue(this.prefetch);
                setupSubscribers();
                subscription.request((long) this.prefetch);
            }
        }

        /* access modifiers changed from: 0000 */
        public void setupSubscribers() {
            c<? super T>[] cVarArr = this.subscribers;
            int i = 0;
            int length = cVarArr.length;
            while (i < length && !this.cancelled) {
                int i2 = i + 1;
                this.subscriberCount.lazySet(i2);
                cVarArr[i].onSubscribe(new a(i, length));
                i = i2;
            }
        }

        public void onNext(T t) {
            if (this.sourceMode != 0 || this.queue.offer(t)) {
                drain();
                return;
            }
            this.s.cancel();
            onError(new MissingBackpressureException("Queue is full?"));
        }

        public void onError(Throwable th) {
            this.error = th;
            this.done = true;
            drain();
        }

        public void onComplete() {
            this.done = true;
            drain();
        }

        /* access modifiers changed from: 0000 */
        public void cancel(int i) {
            if (this.requests.decrementAndGet(i) == 0) {
                this.cancelled = true;
                this.s.cancel();
                if (getAndIncrement() == 0) {
                    this.queue.clear();
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void drainAsync() {
            g<T> gVar = this.queue;
            c<? super T>[] cVarArr = this.subscribers;
            AtomicLongArray atomicLongArray = this.requests;
            long[] jArr = this.emissions;
            int length = jArr.length;
            int i = this.index;
            int i2 = this.produced;
            int i3 = 1;
            while (true) {
                int i4 = 0;
                int i5 = i2;
                int i6 = 0;
                while (!this.cancelled) {
                    boolean z = this.done;
                    if (z) {
                        Throwable th = this.error;
                        if (th != null) {
                            gVar.clear();
                            int length2 = cVarArr.length;
                            while (i4 < length2) {
                                cVarArr[i4].onError(th);
                                i4++;
                            }
                            return;
                        }
                    }
                    boolean isEmpty = gVar.isEmpty();
                    if (!z || !isEmpty) {
                        if (!isEmpty) {
                            long j = atomicLongArray.get(i);
                            long j2 = jArr[i];
                            if (j == j2 || atomicLongArray.get(length + i) != 0) {
                                i6++;
                            } else {
                                try {
                                    Object poll = gVar.poll();
                                    if (poll != null) {
                                        cVarArr[i].onNext(poll);
                                        jArr[i] = j2 + 1;
                                        int i7 = i5 + 1;
                                        if (i7 == this.limit) {
                                            this.s.request((long) i7);
                                            i7 = 0;
                                        }
                                        i5 = i7;
                                        i6 = 0;
                                    }
                                } catch (Throwable th2) {
                                    io.reactivex.exceptions.a.b(th2);
                                    this.s.cancel();
                                    int length3 = cVarArr.length;
                                    while (i4 < length3) {
                                        cVarArr[i4].onError(th2);
                                        i4++;
                                    }
                                    return;
                                }
                            }
                            i++;
                            if (i == length) {
                                i = 0;
                                continue;
                            }
                            if (i6 == length) {
                            }
                        }
                        i2 = i5;
                        int i8 = get();
                        if (i8 == i3) {
                            this.index = i;
                            this.produced = i2;
                            i3 = addAndGet(-i3);
                            if (i3 == 0) {
                                return;
                            }
                        } else {
                            i3 = i8;
                        }
                    } else {
                        int length4 = cVarArr.length;
                        while (i4 < length4) {
                            cVarArr[i4].onComplete();
                            i4++;
                        }
                        return;
                    }
                }
                gVar.clear();
                return;
            }
        }

        /* access modifiers changed from: 0000 */
        public void drainSync() {
            g<T> gVar = this.queue;
            c<? super T>[] cVarArr = this.subscribers;
            AtomicLongArray atomicLongArray = this.requests;
            long[] jArr = this.emissions;
            int length = jArr.length;
            int i = this.index;
            int i2 = 1;
            while (true) {
                int i3 = 0;
                int i4 = 0;
                while (!this.cancelled) {
                    if (gVar.isEmpty()) {
                        int length2 = cVarArr.length;
                        while (i3 < length2) {
                            cVarArr[i3].onComplete();
                            i3++;
                        }
                        return;
                    }
                    long j = atomicLongArray.get(i);
                    long j2 = jArr[i];
                    if (j == j2 || atomicLongArray.get(length + i) != 0) {
                        i4++;
                    } else {
                        try {
                            Object poll = gVar.poll();
                            if (poll == null) {
                                int length3 = cVarArr.length;
                                while (i3 < length3) {
                                    cVarArr[i3].onComplete();
                                    i3++;
                                }
                                return;
                            }
                            cVarArr[i].onNext(poll);
                            jArr[i] = j2 + 1;
                            i4 = 0;
                        } catch (Throwable th) {
                            io.reactivex.exceptions.a.b(th);
                            this.s.cancel();
                            int length4 = cVarArr.length;
                            while (i3 < length4) {
                                cVarArr[i3].onError(th);
                                i3++;
                            }
                            return;
                        }
                    }
                    i++;
                    if (i == length) {
                        i = 0;
                        continue;
                    }
                    if (i4 == length) {
                        int i5 = get();
                        if (i5 == i2) {
                            this.index = i;
                            i2 = addAndGet(-i2);
                            if (i2 == 0) {
                                return;
                            }
                        } else {
                            i2 = i5;
                        }
                    }
                }
                gVar.clear();
                return;
            }
        }

        /* access modifiers changed from: 0000 */
        public void drain() {
            if (getAndIncrement() == 0) {
                if (this.sourceMode == 1) {
                    drainSync();
                } else {
                    drainAsync();
                }
            }
        }
    }

    public int a() {
        return this.b;
    }

    public void a(c<? super T>[] cVarArr) {
        if (b(cVarArr)) {
            this.a.subscribe(new ParallelDispatcher(cVarArr, this.c));
        }
    }
}
