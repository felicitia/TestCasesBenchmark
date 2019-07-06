package io.reactivex.internal.operators.flowable;

import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.functions.g;
import io.reactivex.internal.a.d;
import io.reactivex.internal.a.f;
import io.reactivex.internal.functions.a;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.j;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;
import org.reactivestreams.b;
import org.reactivestreams.c;

public final class FlowableFlatMap<T, U> extends a<T, U> {
    final g<? super T, ? extends b<? extends U>> c;
    final boolean d;
    final int e;
    final int f;

    static final class InnerSubscriber<T, U> extends AtomicReference<Subscription> implements Disposable, j<U> {
        private static final long serialVersionUID = -4606175640614850599L;
        final int bufferSize;
        volatile boolean done;
        int fusionMode;
        final long id;
        final int limit = (this.bufferSize >> 2);
        final MergeSubscriber<T, U> parent;
        long produced;
        volatile io.reactivex.internal.a.g<U> queue;

        InnerSubscriber(MergeSubscriber<T, U> mergeSubscriber, long j) {
            this.id = j;
            this.parent = mergeSubscriber;
            this.bufferSize = mergeSubscriber.bufferSize;
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.setOnce(this, subscription)) {
                if (subscription instanceof d) {
                    d dVar = (d) subscription;
                    int requestFusion = dVar.requestFusion(7);
                    if (requestFusion == 1) {
                        this.fusionMode = requestFusion;
                        this.queue = dVar;
                        this.done = true;
                        this.parent.drain();
                        return;
                    } else if (requestFusion == 2) {
                        this.fusionMode = requestFusion;
                        this.queue = dVar;
                    }
                }
                subscription.request((long) this.bufferSize);
            }
        }

        public void onNext(U u) {
            if (this.fusionMode != 2) {
                this.parent.tryEmit(u, this);
            } else {
                this.parent.drain();
            }
        }

        public void onError(Throwable th) {
            lazySet(SubscriptionHelper.CANCELLED);
            this.parent.innerError(this, th);
        }

        public void onComplete() {
            this.done = true;
            this.parent.drain();
        }

        /* access modifiers changed from: 0000 */
        public void requestMore(long j) {
            if (this.fusionMode != 1) {
                long j2 = this.produced + j;
                if (j2 >= ((long) this.limit)) {
                    this.produced = 0;
                    ((Subscription) get()).request(j2);
                    return;
                }
                this.produced = j2;
            }
        }

        public void dispose() {
            SubscriptionHelper.cancel(this);
        }

        public boolean isDisposed() {
            return get() == SubscriptionHelper.CANCELLED;
        }
    }

    static final class MergeSubscriber<T, U> extends AtomicInteger implements j<T>, Subscription {
        static final InnerSubscriber<?, ?>[] CANCELLED = new InnerSubscriber[0];
        static final InnerSubscriber<?, ?>[] EMPTY = new InnerSubscriber[0];
        private static final long serialVersionUID = -2117620485640801370L;
        final c<? super U> actual;
        final int bufferSize;
        volatile boolean cancelled;
        final boolean delayErrors;
        volatile boolean done;
        final AtomicThrowable errs = new AtomicThrowable();
        long lastId;
        int lastIndex;
        final g<? super T, ? extends b<? extends U>> mapper;
        final int maxConcurrency;
        volatile f<U> queue;
        final AtomicLong requested = new AtomicLong();
        Subscription s;
        int scalarEmitted;
        final int scalarLimit;
        final AtomicReference<InnerSubscriber<?, ?>[]> subscribers = new AtomicReference<>();
        long uniqueId;

        MergeSubscriber(c<? super U> cVar, g<? super T, ? extends b<? extends U>> gVar, boolean z, int i, int i2) {
            this.actual = cVar;
            this.mapper = gVar;
            this.delayErrors = z;
            this.maxConcurrency = i;
            this.bufferSize = i2;
            this.scalarLimit = Math.max(1, i >> 1);
            this.subscribers.lazySet(EMPTY);
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.s, subscription)) {
                this.s = subscription;
                this.actual.onSubscribe(this);
                if (this.cancelled) {
                    return;
                }
                if (this.maxConcurrency == Integer.MAX_VALUE) {
                    subscription.request(Long.MAX_VALUE);
                } else {
                    subscription.request((long) this.maxConcurrency);
                }
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                try {
                    b bVar = (b) a.a(this.mapper.apply(t), "The mapper returned a null Publisher");
                    if (bVar instanceof Callable) {
                        try {
                            Object call = ((Callable) bVar).call();
                            if (call != null) {
                                tryEmitScalar(call);
                            } else if (this.maxConcurrency != Integer.MAX_VALUE && !this.cancelled) {
                                int i = this.scalarEmitted + 1;
                                this.scalarEmitted = i;
                                if (i == this.scalarLimit) {
                                    this.scalarEmitted = 0;
                                    this.s.request((long) this.scalarLimit);
                                }
                            }
                        } catch (Throwable th) {
                            io.reactivex.exceptions.a.b(th);
                            this.errs.addThrowable(th);
                            drain();
                        }
                    } else {
                        long j = this.uniqueId;
                        this.uniqueId = j + 1;
                        InnerSubscriber innerSubscriber = new InnerSubscriber(this, j);
                        if (addInner(innerSubscriber)) {
                            bVar.subscribe(innerSubscriber);
                        }
                    }
                } catch (Throwable th2) {
                    io.reactivex.exceptions.a.b(th2);
                    this.s.cancel();
                    onError(th2);
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public boolean addInner(InnerSubscriber<T, U> innerSubscriber) {
            InnerSubscriber<?, ?>[] innerSubscriberArr;
            InnerSubscriber[] innerSubscriberArr2;
            do {
                innerSubscriberArr = (InnerSubscriber[]) this.subscribers.get();
                if (innerSubscriberArr == CANCELLED) {
                    innerSubscriber.dispose();
                    return false;
                }
                int length = innerSubscriberArr.length;
                innerSubscriberArr2 = new InnerSubscriber[(length + 1)];
                System.arraycopy(innerSubscriberArr, 0, innerSubscriberArr2, 0, length);
                innerSubscriberArr2[length] = innerSubscriber;
            } while (!this.subscribers.compareAndSet(innerSubscriberArr, innerSubscriberArr2));
            return true;
        }

        /* access modifiers changed from: 0000 */
        public void removeInner(InnerSubscriber<T, U> innerSubscriber) {
            InnerSubscriber<?, ?>[] innerSubscriberArr;
            Object obj;
            do {
                innerSubscriberArr = (InnerSubscriber[]) this.subscribers.get();
                if (innerSubscriberArr != CANCELLED && innerSubscriberArr != EMPTY) {
                    int length = innerSubscriberArr.length;
                    int i = -1;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length) {
                            break;
                        } else if (innerSubscriberArr[i2] == innerSubscriber) {
                            i = i2;
                            break;
                        } else {
                            i2++;
                        }
                    }
                    if (i >= 0) {
                        if (length == 1) {
                            obj = EMPTY;
                        } else {
                            InnerSubscriber[] innerSubscriberArr2 = new InnerSubscriber[(length - 1)];
                            System.arraycopy(innerSubscriberArr, 0, innerSubscriberArr2, 0, i);
                            System.arraycopy(innerSubscriberArr, i + 1, innerSubscriberArr2, i, (length - i) - 1);
                            obj = innerSubscriberArr2;
                        }
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            } while (!this.subscribers.compareAndSet(innerSubscriberArr, obj));
        }

        /* access modifiers changed from: 0000 */
        public io.reactivex.internal.a.g<U> getMainQueue() {
            f<U> fVar = this.queue;
            if (fVar == null) {
                if (this.maxConcurrency == Integer.MAX_VALUE) {
                    fVar = new io.reactivex.internal.queue.a<>(this.bufferSize);
                } else {
                    fVar = new SpscArrayQueue<>(this.maxConcurrency);
                }
                this.queue = fVar;
            }
            return fVar;
        }

        /* access modifiers changed from: 0000 */
        public void tryEmitScalar(U u) {
            if (get() == 0 && compareAndSet(0, 1)) {
                long j = this.requested.get();
                io.reactivex.internal.a.g gVar = this.queue;
                if (j == 0 || (gVar != null && !gVar.isEmpty())) {
                    if (gVar == null) {
                        gVar = getMainQueue();
                    }
                    if (!gVar.offer(u)) {
                        onError(new IllegalStateException("Scalar queue full?!"));
                        return;
                    }
                } else {
                    this.actual.onNext(u);
                    if (j != Long.MAX_VALUE) {
                        this.requested.decrementAndGet();
                    }
                    if (this.maxConcurrency != Integer.MAX_VALUE && !this.cancelled) {
                        int i = this.scalarEmitted + 1;
                        this.scalarEmitted = i;
                        if (i == this.scalarLimit) {
                            this.scalarEmitted = 0;
                            this.s.request((long) this.scalarLimit);
                        }
                    }
                }
                if (decrementAndGet() == 0) {
                    return;
                }
            } else if (!getMainQueue().offer(u)) {
                onError(new IllegalStateException("Scalar queue full?!"));
                return;
            } else if (getAndIncrement() != 0) {
                return;
            }
            drainLoop();
        }

        /* access modifiers changed from: 0000 */
        public io.reactivex.internal.a.g<U> getInnerQueue(InnerSubscriber<T, U> innerSubscriber) {
            io.reactivex.internal.a.g<U> gVar = innerSubscriber.queue;
            if (gVar != null) {
                return gVar;
            }
            SpscArrayQueue spscArrayQueue = new SpscArrayQueue(this.bufferSize);
            innerSubscriber.queue = spscArrayQueue;
            return spscArrayQueue;
        }

        /* access modifiers changed from: 0000 */
        public void tryEmit(U u, InnerSubscriber<T, U> innerSubscriber) {
            if (get() != 0 || !compareAndSet(0, 1)) {
                io.reactivex.internal.a.g gVar = innerSubscriber.queue;
                if (gVar == null) {
                    gVar = new SpscArrayQueue(this.bufferSize);
                    innerSubscriber.queue = gVar;
                }
                if (!gVar.offer(u)) {
                    onError(new MissingBackpressureException("Inner queue full?!"));
                    return;
                } else if (getAndIncrement() != 0) {
                    return;
                }
            } else {
                long j = this.requested.get();
                io.reactivex.internal.a.g<U> gVar2 = innerSubscriber.queue;
                if (j == 0 || (gVar2 != null && !gVar2.isEmpty())) {
                    if (gVar2 == null) {
                        gVar2 = getInnerQueue(innerSubscriber);
                    }
                    if (!gVar2.offer(u)) {
                        onError(new MissingBackpressureException("Inner queue full?!"));
                        return;
                    }
                } else {
                    this.actual.onNext(u);
                    if (j != Long.MAX_VALUE) {
                        this.requested.decrementAndGet();
                    }
                    innerSubscriber.requestMore(1);
                }
                if (decrementAndGet() == 0) {
                    return;
                }
            }
            drainLoop();
        }

        public void onError(Throwable th) {
            if (this.done) {
                io.reactivex.d.a.a(th);
                return;
            }
            if (this.errs.addThrowable(th)) {
                this.done = true;
                drain();
            } else {
                io.reactivex.d.a.a(th);
            }
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                drain();
            }
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                io.reactivex.internal.util.b.a(this.requested, j);
                drain();
            }
        }

        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.s.cancel();
                disposeAll();
                if (getAndIncrement() == 0) {
                    f<U> fVar = this.queue;
                    if (fVar != null) {
                        fVar.clear();
                    }
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void drain() {
            if (getAndIncrement() == 0) {
                drainLoop();
            }
        }

        /* access modifiers changed from: 0000 */
        public void drainLoop() {
            long j;
            c<? super U> cVar;
            int i;
            boolean z;
            long j2;
            int i2;
            int i3;
            int i4;
            long j3;
            c<? super U> cVar2 = this.actual;
            int i5 = 1;
            while (!checkTerminate()) {
                f<U> fVar = this.queue;
                long j4 = this.requested.get();
                boolean z2 = j4 == Long.MAX_VALUE;
                if (fVar != null) {
                    j = 0;
                    while (true) {
                        long j5 = 0;
                        Object obj = null;
                        while (true) {
                            if (j4 == 0) {
                                break;
                            }
                            Object poll = fVar.poll();
                            if (!checkTerminate()) {
                                if (poll == null) {
                                    obj = poll;
                                    break;
                                }
                                cVar2.onNext(poll);
                                j4--;
                                j5++;
                                j++;
                                obj = poll;
                            } else {
                                return;
                            }
                        }
                        if (j5 != 0) {
                            if (z2) {
                                j4 = Long.MAX_VALUE;
                            } else {
                                j4 = this.requested.addAndGet(-j5);
                            }
                        }
                        if (j4 == 0 || obj == null) {
                            break;
                        }
                    }
                } else {
                    j = 0;
                }
                boolean z3 = this.done;
                f<U> fVar2 = this.queue;
                InnerSubscriber[] innerSubscriberArr = (InnerSubscriber[]) this.subscribers.get();
                int length = innerSubscriberArr.length;
                if (!z3 || ((fVar2 != null && !fVar2.isEmpty()) || length != 0)) {
                    if (length != 0) {
                        i = i5;
                        long j6 = this.lastId;
                        int i6 = this.lastIndex;
                        if (length <= i6 || innerSubscriberArr[i6].id != j6) {
                            if (length <= i6) {
                                i6 = 0;
                            }
                            int i7 = i6;
                            for (int i8 = 0; i8 < length && innerSubscriberArr[i7].id != j6; i8++) {
                                i7++;
                                if (i7 == length) {
                                    i7 = 0;
                                }
                            }
                            this.lastIndex = i7;
                            this.lastId = innerSubscriberArr[i7].id;
                            i6 = i7;
                        }
                        int i9 = 0;
                        z = false;
                        while (true) {
                            if (i9 >= length) {
                                cVar = cVar2;
                                break;
                            } else if (!checkTerminate()) {
                                InnerSubscriber innerSubscriber = innerSubscriberArr[i6];
                                Object obj2 = null;
                                while (!checkTerminate()) {
                                    io.reactivex.internal.a.g<U> gVar = innerSubscriber.queue;
                                    if (gVar == null) {
                                        cVar = cVar2;
                                        i2 = i9;
                                        i3 = length;
                                    } else {
                                        i3 = length;
                                        Object obj3 = obj2;
                                        long j7 = 0;
                                        while (j4 != 0) {
                                            try {
                                                obj3 = gVar.poll();
                                                if (obj3 == null) {
                                                    break;
                                                }
                                                cVar2.onNext(obj3);
                                                if (!checkTerminate()) {
                                                    j7++;
                                                    j4--;
                                                } else {
                                                    return;
                                                }
                                            } catch (Throwable th) {
                                                io.reactivex.exceptions.a.b(th);
                                                innerSubscriber.dispose();
                                                this.errs.addThrowable(th);
                                                if (!checkTerminate()) {
                                                    removeInner(innerSubscriber);
                                                    cVar = cVar2;
                                                    i2 = i9 + 1;
                                                    i4 = i3;
                                                    z = true;
                                                } else {
                                                    return;
                                                }
                                            }
                                        }
                                        if (j7 != 0) {
                                            if (!z2) {
                                                cVar = cVar2;
                                                i2 = i9;
                                                j3 = this.requested.addAndGet(-j7);
                                            } else {
                                                cVar = cVar2;
                                                i2 = i9;
                                                j3 = Long.MAX_VALUE;
                                            }
                                            innerSubscriber.requestMore(j7);
                                            j4 = j3;
                                        } else {
                                            cVar = cVar2;
                                            i2 = i9;
                                        }
                                        if (!(j4 == 0 || obj3 == null)) {
                                            obj2 = obj3;
                                            length = i3;
                                            cVar2 = cVar;
                                            i9 = i2;
                                        }
                                    }
                                    boolean z4 = innerSubscriber.done;
                                    io.reactivex.internal.a.g<U> gVar2 = innerSubscriber.queue;
                                    if (z4 && (gVar2 == null || gVar2.isEmpty())) {
                                        removeInner(innerSubscriber);
                                        if (!checkTerminate()) {
                                            j++;
                                            z = true;
                                        } else {
                                            return;
                                        }
                                    }
                                    if (j4 == 0) {
                                        break;
                                    }
                                    int i10 = i6 + 1;
                                    i4 = i3;
                                    i6 = i10 == i4 ? 0 : i10;
                                    length = i4;
                                    i9 = i2 + 1;
                                    cVar2 = cVar;
                                }
                                return;
                            } else {
                                return;
                            }
                        }
                        this.lastIndex = i6;
                        this.lastId = innerSubscriberArr[i6].id;
                        j2 = j;
                    } else {
                        cVar = cVar2;
                        i = i5;
                        j2 = j;
                        z = false;
                    }
                    if (j2 != 0 && !this.cancelled) {
                        this.s.request(j2);
                    }
                    if (z) {
                        i5 = i;
                    } else {
                        i5 = addAndGet(-i);
                        if (i5 == 0) {
                            return;
                        }
                    }
                    cVar2 = cVar;
                } else {
                    Throwable terminate = this.errs.terminate();
                    if (terminate != ExceptionHelper.a) {
                        if (terminate == null) {
                            cVar2.onComplete();
                        } else {
                            cVar2.onError(terminate);
                        }
                    }
                    return;
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public boolean checkTerminate() {
            if (this.cancelled) {
                clearScalarQueue();
                return true;
            } else if (this.delayErrors || this.errs.get() == null) {
                return false;
            } else {
                clearScalarQueue();
                Throwable terminate = this.errs.terminate();
                if (terminate != ExceptionHelper.a) {
                    this.actual.onError(terminate);
                }
                return true;
            }
        }

        /* access modifiers changed from: 0000 */
        public void clearScalarQueue() {
            f<U> fVar = this.queue;
            if (fVar != null) {
                fVar.clear();
            }
        }

        /* access modifiers changed from: 0000 */
        public void disposeAll() {
            if (((InnerSubscriber[]) this.subscribers.get()) != CANCELLED) {
                InnerSubscriber<?, ?>[] innerSubscriberArr = (InnerSubscriber[]) this.subscribers.getAndSet(CANCELLED);
                if (innerSubscriberArr != CANCELLED) {
                    for (InnerSubscriber<?, ?> dispose : innerSubscriberArr) {
                        dispose.dispose();
                    }
                    Throwable terminate = this.errs.terminate();
                    if (terminate != null && terminate != ExceptionHelper.a) {
                        io.reactivex.d.a.a(terminate);
                    }
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void innerError(InnerSubscriber<T, U> innerSubscriber, Throwable th) {
            if (this.errs.addThrowable(th)) {
                innerSubscriber.done = true;
                if (!this.delayErrors) {
                    this.s.cancel();
                    for (InnerSubscriber dispose : (InnerSubscriber[]) this.subscribers.getAndSet(CANCELLED)) {
                        dispose.dispose();
                    }
                }
                drain();
                return;
            }
            io.reactivex.d.a.a(th);
        }
    }

    /* access modifiers changed from: protected */
    public void a(c<? super U> cVar) {
        if (!e.a(this.b, cVar, this.c)) {
            this.b.a(a(cVar, this.c, this.d, this.e, this.f));
        }
    }

    public static <T, U> j<T> a(c<? super U> cVar, g<? super T, ? extends b<? extends U>> gVar, boolean z, int i, int i2) {
        MergeSubscriber mergeSubscriber = new MergeSubscriber(cVar, gVar, z, i, i2);
        return mergeSubscriber;
    }
}
