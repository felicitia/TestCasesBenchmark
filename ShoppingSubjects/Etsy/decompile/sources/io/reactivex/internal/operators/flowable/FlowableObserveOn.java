package io.reactivex.internal.operators.flowable;

import io.reactivex.d.a;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.a.d;
import io.reactivex.internal.a.g;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.BasicIntQueueSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.b;
import io.reactivex.j;
import io.reactivex.u;
import io.reactivex.u.c;
import java.util.concurrent.atomic.AtomicLong;
import org.reactivestreams.Subscription;

public final class FlowableObserveOn<T> extends a<T, T> {
    final u c;
    final boolean d;
    final int e;

    static abstract class BaseObserveOnSubscriber<T> extends BasicIntQueueSubscription<T> implements j<T>, Runnable {
        private static final long serialVersionUID = -8241002408341274697L;
        volatile boolean cancelled;
        final boolean delayError;
        volatile boolean done;
        Throwable error;
        final int limit;
        boolean outputFused;
        final int prefetch;
        long produced;
        g<T> queue;
        final AtomicLong requested = new AtomicLong();
        Subscription s;
        int sourceMode;
        final c worker;

        /* access modifiers changed from: 0000 */
        public abstract void runAsync();

        /* access modifiers changed from: 0000 */
        public abstract void runBackfused();

        /* access modifiers changed from: 0000 */
        public abstract void runSync();

        BaseObserveOnSubscriber(c cVar, boolean z, int i) {
            this.worker = cVar;
            this.delayError = z;
            this.prefetch = i;
            this.limit = i - (i >> 2);
        }

        public final void onNext(T t) {
            if (!this.done) {
                if (this.sourceMode == 2) {
                    trySchedule();
                    return;
                }
                if (!this.queue.offer(t)) {
                    this.s.cancel();
                    this.error = new MissingBackpressureException("Queue is full?!");
                    this.done = true;
                }
                trySchedule();
            }
        }

        public final void onError(Throwable th) {
            if (this.done) {
                a.a(th);
                return;
            }
            this.error = th;
            this.done = true;
            trySchedule();
        }

        public final void onComplete() {
            if (!this.done) {
                this.done = true;
                trySchedule();
            }
        }

        public final void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                b.a(this.requested, j);
                trySchedule();
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
        public final void trySchedule() {
            if (getAndIncrement() == 0) {
                this.worker.a((Runnable) this);
            }
        }

        public final void run() {
            if (this.outputFused) {
                runBackfused();
            } else if (this.sourceMode == 1) {
                runSync();
            } else {
                runAsync();
            }
        }

        /* access modifiers changed from: 0000 */
        public final boolean checkTerminated(boolean z, boolean z2, org.reactivestreams.c<?> cVar) {
            if (this.cancelled) {
                clear();
                return true;
            }
            if (z) {
                if (!this.delayError) {
                    Throwable th = this.error;
                    if (th != null) {
                        clear();
                        cVar.onError(th);
                        this.worker.dispose();
                        return true;
                    } else if (z2) {
                        cVar.onComplete();
                        this.worker.dispose();
                        return true;
                    }
                } else if (z2) {
                    Throwable th2 = this.error;
                    if (th2 != null) {
                        cVar.onError(th2);
                    } else {
                        cVar.onComplete();
                    }
                    this.worker.dispose();
                    return true;
                }
            }
            return false;
        }

        public final int requestFusion(int i) {
            if ((i & 2) == 0) {
                return 0;
            }
            this.outputFused = true;
            return 2;
        }

        public final void clear() {
            this.queue.clear();
        }

        public final boolean isEmpty() {
            return this.queue.isEmpty();
        }
    }

    static final class ObserveOnConditionalSubscriber<T> extends BaseObserveOnSubscriber<T> {
        private static final long serialVersionUID = 644624475404284533L;
        final io.reactivex.internal.a.a<? super T> actual;
        long consumed;

        ObserveOnConditionalSubscriber(io.reactivex.internal.a.a<? super T> aVar, c cVar, boolean z, int i) {
            super(cVar, z, i);
            this.actual = aVar;
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.s, subscription)) {
                this.s = subscription;
                if (subscription instanceof d) {
                    d dVar = (d) subscription;
                    int requestFusion = dVar.requestFusion(7);
                    if (requestFusion == 1) {
                        this.sourceMode = 1;
                        this.queue = dVar;
                        this.done = true;
                        this.actual.onSubscribe(this);
                        return;
                    } else if (requestFusion == 2) {
                        this.sourceMode = 2;
                        this.queue = dVar;
                        this.actual.onSubscribe(this);
                        subscription.request((long) this.prefetch);
                        return;
                    }
                }
                this.queue = new SpscArrayQueue(this.prefetch);
                this.actual.onSubscribe(this);
                subscription.request((long) this.prefetch);
            }
        }

        /* access modifiers changed from: 0000 */
        public void runSync() {
            io.reactivex.internal.a.a<? super T> aVar = this.actual;
            g gVar = this.queue;
            long j = this.produced;
            int i = 1;
            while (true) {
                long j2 = this.requested.get();
                while (j != j2) {
                    try {
                        Object poll = gVar.poll();
                        if (!this.cancelled) {
                            if (poll == null) {
                                aVar.onComplete();
                                this.worker.dispose();
                                return;
                            } else if (aVar.tryOnNext(poll)) {
                                j++;
                            }
                        } else {
                            return;
                        }
                    } catch (Throwable th) {
                        io.reactivex.exceptions.a.b(th);
                        this.s.cancel();
                        aVar.onError(th);
                        this.worker.dispose();
                        return;
                    }
                }
                if (!this.cancelled) {
                    if (gVar.isEmpty()) {
                        aVar.onComplete();
                        this.worker.dispose();
                        return;
                    }
                    int i2 = get();
                    if (i == i2) {
                        this.produced = j;
                        i = addAndGet(-i);
                        if (i == 0) {
                            return;
                        }
                    } else {
                        i = i2;
                    }
                } else {
                    return;
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void runAsync() {
            io.reactivex.internal.a.a<? super T> aVar = this.actual;
            g gVar = this.queue;
            long j = this.produced;
            long j2 = this.consumed;
            int i = 1;
            while (true) {
                long j3 = this.requested.get();
                while (j != j3) {
                    boolean z = this.done;
                    try {
                        Object poll = gVar.poll();
                        boolean z2 = poll == null;
                        if (!checkTerminated(z, z2, aVar)) {
                            if (z2) {
                                break;
                            }
                            if (aVar.tryOnNext(poll)) {
                                j++;
                            }
                            long j4 = j2 + 1;
                            if (j4 == ((long) this.limit)) {
                                this.s.request(j4);
                                j2 = 0;
                            } else {
                                j2 = j4;
                            }
                        } else {
                            return;
                        }
                    } catch (Throwable th) {
                        io.reactivex.exceptions.a.b(th);
                        this.s.cancel();
                        gVar.clear();
                        aVar.onError(th);
                        this.worker.dispose();
                        return;
                    }
                }
                if (j != j3 || !checkTerminated(this.done, gVar.isEmpty(), aVar)) {
                    int i2 = get();
                    if (i == i2) {
                        this.produced = j;
                        this.consumed = j2;
                        i = addAndGet(-i);
                        if (i == 0) {
                            return;
                        }
                    } else {
                        i = i2;
                    }
                } else {
                    return;
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void runBackfused() {
            int i = 1;
            while (!this.cancelled) {
                boolean z = this.done;
                this.actual.onNext(null);
                if (z) {
                    Throwable th = this.error;
                    if (th != null) {
                        this.actual.onError(th);
                    } else {
                        this.actual.onComplete();
                    }
                    this.worker.dispose();
                    return;
                }
                i = addAndGet(-i);
                if (i == 0) {
                    return;
                }
            }
        }

        public T poll() throws Exception {
            T poll = this.queue.poll();
            if (!(poll == null || this.sourceMode == 1)) {
                long j = this.consumed + 1;
                if (j == ((long) this.limit)) {
                    this.consumed = 0;
                    this.s.request(j);
                } else {
                    this.consumed = j;
                }
            }
            return poll;
        }
    }

    static final class ObserveOnSubscriber<T> extends BaseObserveOnSubscriber<T> implements j<T> {
        private static final long serialVersionUID = -4547113800637756442L;
        final org.reactivestreams.c<? super T> actual;

        ObserveOnSubscriber(org.reactivestreams.c<? super T> cVar, c cVar2, boolean z, int i) {
            super(cVar2, z, i);
            this.actual = cVar;
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.s, subscription)) {
                this.s = subscription;
                if (subscription instanceof d) {
                    d dVar = (d) subscription;
                    int requestFusion = dVar.requestFusion(7);
                    if (requestFusion == 1) {
                        this.sourceMode = 1;
                        this.queue = dVar;
                        this.done = true;
                        this.actual.onSubscribe(this);
                        return;
                    } else if (requestFusion == 2) {
                        this.sourceMode = 2;
                        this.queue = dVar;
                        this.actual.onSubscribe(this);
                        subscription.request((long) this.prefetch);
                        return;
                    }
                }
                this.queue = new SpscArrayQueue(this.prefetch);
                this.actual.onSubscribe(this);
                subscription.request((long) this.prefetch);
            }
        }

        /* access modifiers changed from: 0000 */
        public void runSync() {
            org.reactivestreams.c<? super T> cVar = this.actual;
            g gVar = this.queue;
            long j = this.produced;
            int i = 1;
            while (true) {
                long j2 = this.requested.get();
                while (j != j2) {
                    try {
                        Object poll = gVar.poll();
                        if (!this.cancelled) {
                            if (poll == null) {
                                cVar.onComplete();
                                this.worker.dispose();
                                return;
                            }
                            cVar.onNext(poll);
                            j++;
                        } else {
                            return;
                        }
                    } catch (Throwable th) {
                        io.reactivex.exceptions.a.b(th);
                        this.s.cancel();
                        cVar.onError(th);
                        this.worker.dispose();
                        return;
                    }
                }
                if (!this.cancelled) {
                    if (gVar.isEmpty()) {
                        cVar.onComplete();
                        this.worker.dispose();
                        return;
                    }
                    int i2 = get();
                    if (i == i2) {
                        this.produced = j;
                        i = addAndGet(-i);
                        if (i == 0) {
                            return;
                        }
                    } else {
                        i = i2;
                    }
                } else {
                    return;
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void runAsync() {
            org.reactivestreams.c<? super T> cVar = this.actual;
            g gVar = this.queue;
            long j = this.produced;
            int i = 1;
            while (true) {
                long j2 = this.requested.get();
                while (j != j2) {
                    boolean z = this.done;
                    try {
                        Object poll = gVar.poll();
                        boolean z2 = poll == null;
                        if (!checkTerminated(z, z2, cVar)) {
                            if (z2) {
                                break;
                            }
                            cVar.onNext(poll);
                            long j3 = j + 1;
                            if (j3 == ((long) this.limit)) {
                                if (j2 != Long.MAX_VALUE) {
                                    j2 = this.requested.addAndGet(-j3);
                                }
                                this.s.request(j3);
                                j = 0;
                            } else {
                                j = j3;
                            }
                        } else {
                            return;
                        }
                    } catch (Throwable th) {
                        io.reactivex.exceptions.a.b(th);
                        this.s.cancel();
                        gVar.clear();
                        cVar.onError(th);
                        this.worker.dispose();
                        return;
                    }
                }
                if (j != j2 || !checkTerminated(this.done, gVar.isEmpty(), cVar)) {
                    int i2 = get();
                    if (i == i2) {
                        this.produced = j;
                        i = addAndGet(-i);
                        if (i == 0) {
                            return;
                        }
                    } else {
                        i = i2;
                    }
                } else {
                    return;
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void runBackfused() {
            int i = 1;
            while (!this.cancelled) {
                boolean z = this.done;
                this.actual.onNext(null);
                if (z) {
                    Throwable th = this.error;
                    if (th != null) {
                        this.actual.onError(th);
                    } else {
                        this.actual.onComplete();
                    }
                    this.worker.dispose();
                    return;
                }
                i = addAndGet(-i);
                if (i == 0) {
                    return;
                }
            }
        }

        public T poll() throws Exception {
            T poll = this.queue.poll();
            if (!(poll == null || this.sourceMode == 1)) {
                long j = this.produced + 1;
                if (j == ((long) this.limit)) {
                    this.produced = 0;
                    this.s.request(j);
                } else {
                    this.produced = j;
                }
            }
            return poll;
        }
    }

    public FlowableObserveOn(io.reactivex.g<T> gVar, u uVar, boolean z, int i) {
        super(gVar);
        this.c = uVar;
        this.d = z;
        this.e = i;
    }

    public void a(org.reactivestreams.c<? super T> cVar) {
        c a = this.c.a();
        if (cVar instanceof io.reactivex.internal.a.a) {
            this.b.a((j<? super T>) new ObserveOnConditionalSubscriber<Object>((io.reactivex.internal.a.a) cVar, a, this.d, this.e));
        } else {
            this.b.a((j<? super T>) new ObserveOnSubscriber<Object>(cVar, a, this.d, this.e));
        }
    }
}
