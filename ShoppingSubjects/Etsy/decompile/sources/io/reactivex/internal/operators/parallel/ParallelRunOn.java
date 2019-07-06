package io.reactivex.internal.operators.parallel;

import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.b;
import io.reactivex.j;
import io.reactivex.parallel.a;
import io.reactivex.u;
import io.reactivex.u.c;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.reactivestreams.Subscription;

public final class ParallelRunOn<T> extends a<T> {
    final a<? extends T> a;
    final u b;
    final int c;

    static abstract class BaseRunOnSubscriber<T> extends AtomicInteger implements j<T>, Runnable, Subscription {
        private static final long serialVersionUID = 9222303586456402150L;
        volatile boolean cancelled;
        int consumed;
        volatile boolean done;
        Throwable error;
        final int limit;
        final int prefetch;
        final SpscArrayQueue<T> queue;
        final AtomicLong requested = new AtomicLong();
        Subscription s;
        final c worker;

        BaseRunOnSubscriber(int i, SpscArrayQueue<T> spscArrayQueue, c cVar) {
            this.prefetch = i;
            this.queue = spscArrayQueue;
            this.limit = i - (i >> 2);
            this.worker = cVar;
        }

        public final void onNext(T t) {
            if (!this.done) {
                if (!this.queue.offer(t)) {
                    this.s.cancel();
                    onError(new MissingBackpressureException("Queue is full?!"));
                    return;
                }
                schedule();
            }
        }

        public final void onError(Throwable th) {
            if (this.done) {
                io.reactivex.d.a.a(th);
                return;
            }
            this.error = th;
            this.done = true;
            schedule();
        }

        public final void onComplete() {
            if (!this.done) {
                this.done = true;
                schedule();
            }
        }

        public final void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                b.a(this.requested, j);
                schedule();
            }
        }

        public final void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.s.cancel();
                this.worker.dispose();
                if (getAndIncrement() == 0) {
                    this.queue.clear();
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public final void schedule() {
            if (getAndIncrement() == 0) {
                this.worker.a((Runnable) this);
            }
        }
    }

    static final class RunOnConditionalSubscriber<T> extends BaseRunOnSubscriber<T> {
        private static final long serialVersionUID = 1075119423897941642L;
        final io.reactivex.internal.a.a<? super T> actual;

        RunOnConditionalSubscriber(io.reactivex.internal.a.a<? super T> aVar, int i, SpscArrayQueue<T> spscArrayQueue, c cVar) {
            super(i, spscArrayQueue, cVar);
            this.actual = aVar;
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.s, subscription)) {
                this.s = subscription;
                this.actual.onSubscribe(this);
                subscription.request((long) this.prefetch);
            }
        }

        public void run() {
            int i;
            int i2 = this.consumed;
            SpscArrayQueue spscArrayQueue = this.queue;
            io.reactivex.internal.a.a<? super T> aVar = this.actual;
            int i3 = this.limit;
            int i4 = 1;
            while (true) {
                long j = this.requested.get();
                long j2 = 0;
                while (j2 != j) {
                    if (this.cancelled) {
                        spscArrayQueue.clear();
                        return;
                    }
                    boolean z = this.done;
                    if (z) {
                        Throwable th = this.error;
                        if (th != null) {
                            spscArrayQueue.clear();
                            aVar.onError(th);
                            this.worker.dispose();
                            return;
                        }
                    }
                    Object poll = spscArrayQueue.poll();
                    boolean z2 = poll == null;
                    if (z && z2) {
                        aVar.onComplete();
                        this.worker.dispose();
                        return;
                    } else if (z2) {
                        break;
                    } else {
                        if (aVar.tryOnNext(poll)) {
                            j2++;
                        }
                        i2++;
                        if (i2 == i3) {
                            i = i4;
                            this.s.request((long) i2);
                            i2 = 0;
                        } else {
                            i = i4;
                        }
                        i4 = i;
                    }
                }
                int i5 = i4;
                if (j2 == j) {
                    if (this.cancelled) {
                        spscArrayQueue.clear();
                        return;
                    } else if (this.done) {
                        Throwable th2 = this.error;
                        if (th2 != null) {
                            spscArrayQueue.clear();
                            aVar.onError(th2);
                            this.worker.dispose();
                            return;
                        } else if (spscArrayQueue.isEmpty()) {
                            aVar.onComplete();
                            this.worker.dispose();
                            return;
                        }
                    }
                }
                if (!(j2 == 0 || j == Long.MAX_VALUE)) {
                    this.requested.addAndGet(-j2);
                }
                int i6 = get();
                int i7 = i5;
                if (i6 == i7) {
                    this.consumed = i2;
                    i6 = addAndGet(-i7);
                    if (i6 == 0) {
                        return;
                    }
                }
                i4 = i6;
            }
        }
    }

    static final class RunOnSubscriber<T> extends BaseRunOnSubscriber<T> {
        private static final long serialVersionUID = 1075119423897941642L;
        final org.reactivestreams.c<? super T> actual;

        RunOnSubscriber(org.reactivestreams.c<? super T> cVar, int i, SpscArrayQueue<T> spscArrayQueue, c cVar2) {
            super(i, spscArrayQueue, cVar2);
            this.actual = cVar;
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.s, subscription)) {
                this.s = subscription;
                this.actual.onSubscribe(this);
                subscription.request((long) this.prefetch);
            }
        }

        public void run() {
            int i = this.consumed;
            SpscArrayQueue spscArrayQueue = this.queue;
            org.reactivestreams.c<? super T> cVar = this.actual;
            int i2 = this.limit;
            int i3 = 1;
            while (true) {
                long j = this.requested.get();
                long j2 = 0;
                while (j2 != j) {
                    if (this.cancelled) {
                        spscArrayQueue.clear();
                        return;
                    }
                    boolean z = this.done;
                    if (z) {
                        Throwable th = this.error;
                        if (th != null) {
                            spscArrayQueue.clear();
                            cVar.onError(th);
                            this.worker.dispose();
                            return;
                        }
                    }
                    Object poll = spscArrayQueue.poll();
                    boolean z2 = poll == null;
                    if (z && z2) {
                        cVar.onComplete();
                        this.worker.dispose();
                        return;
                    } else if (z2) {
                        break;
                    } else {
                        cVar.onNext(poll);
                        long j3 = j2 + 1;
                        i++;
                        if (i == i2) {
                            this.s.request((long) i);
                            i = 0;
                        }
                        j2 = j3;
                    }
                }
                if (j2 == j) {
                    if (this.cancelled) {
                        spscArrayQueue.clear();
                        return;
                    } else if (this.done) {
                        Throwable th2 = this.error;
                        if (th2 != null) {
                            spscArrayQueue.clear();
                            cVar.onError(th2);
                            this.worker.dispose();
                            return;
                        } else if (spscArrayQueue.isEmpty()) {
                            cVar.onComplete();
                            this.worker.dispose();
                            return;
                        }
                    }
                }
                if (!(j2 == 0 || j == Long.MAX_VALUE)) {
                    this.requested.addAndGet(-j2);
                }
                int i4 = get();
                if (i4 == i3) {
                    this.consumed = i;
                    i3 = addAndGet(-i3);
                    if (i3 == 0) {
                        return;
                    }
                } else {
                    i3 = i4;
                }
            }
        }
    }

    public void a(org.reactivestreams.c<? super T>[] cVarArr) {
        if (b(cVarArr)) {
            int length = cVarArr.length;
            org.reactivestreams.c[] cVarArr2 = new org.reactivestreams.c[length];
            int i = this.c;
            for (int i2 = 0; i2 < length; i2++) {
                io.reactivex.internal.a.a aVar = cVarArr[i2];
                c a2 = this.b.a();
                SpscArrayQueue spscArrayQueue = new SpscArrayQueue(i);
                if (aVar instanceof io.reactivex.internal.a.a) {
                    cVarArr2[i2] = new RunOnConditionalSubscriber(aVar, i, spscArrayQueue, a2);
                } else {
                    cVarArr2[i2] = new RunOnSubscriber(aVar, i, spscArrayQueue, a2);
                }
            }
            this.a.a(cVarArr2);
        }
    }

    public int a() {
        return this.a.a();
    }
}
