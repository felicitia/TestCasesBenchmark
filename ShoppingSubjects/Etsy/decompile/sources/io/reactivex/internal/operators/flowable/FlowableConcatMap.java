package io.reactivex.internal.operators.flowable;

import io.reactivex.functions.g;
import io.reactivex.internal.a.d;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionArbiter;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.ErrorMode;
import io.reactivex.j;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import org.reactivestreams.Subscription;
import org.reactivestreams.c;

public final class FlowableConcatMap<T, R> extends a<T, R> {
    final g<? super T, ? extends org.reactivestreams.b<? extends R>> c;
    final int d;
    final ErrorMode e;

    static abstract class BaseConcatMapSubscriber<T, R> extends AtomicInteger implements a<R>, j<T>, Subscription {
        private static final long serialVersionUID = -3511336836796789179L;
        volatile boolean active;
        volatile boolean cancelled;
        int consumed;
        volatile boolean done;
        final AtomicThrowable errors = new AtomicThrowable();
        final ConcatMapInner<R> inner = new ConcatMapInner<>(this);
        final int limit;
        final g<? super T, ? extends org.reactivestreams.b<? extends R>> mapper;
        final int prefetch;
        io.reactivex.internal.a.g<T> queue;
        Subscription s;
        int sourceMode;

        /* access modifiers changed from: 0000 */
        public abstract void drain();

        /* access modifiers changed from: 0000 */
        public abstract void subscribeActual();

        BaseConcatMapSubscriber(g<? super T, ? extends org.reactivestreams.b<? extends R>> gVar, int i) {
            this.mapper = gVar;
            this.prefetch = i;
            this.limit = i - (i >> 2);
        }

        public final void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.s, subscription)) {
                this.s = subscription;
                if (subscription instanceof d) {
                    d dVar = (d) subscription;
                    int requestFusion = dVar.requestFusion(3);
                    if (requestFusion == 1) {
                        this.sourceMode = requestFusion;
                        this.queue = dVar;
                        this.done = true;
                        subscribeActual();
                        drain();
                        return;
                    } else if (requestFusion == 2) {
                        this.sourceMode = requestFusion;
                        this.queue = dVar;
                        subscribeActual();
                        subscription.request((long) this.prefetch);
                        return;
                    }
                }
                this.queue = new SpscArrayQueue(this.prefetch);
                subscribeActual();
                subscription.request((long) this.prefetch);
            }
        }

        public final void onNext(T t) {
            if (this.sourceMode == 2 || this.queue.offer(t)) {
                drain();
                return;
            }
            this.s.cancel();
            onError(new IllegalStateException("Queue full?!"));
        }

        public final void onComplete() {
            this.done = true;
            drain();
        }

        public final void innerComplete() {
            this.active = false;
            drain();
        }
    }

    static final class ConcatMapDelayed<T, R> extends BaseConcatMapSubscriber<T, R> {
        private static final long serialVersionUID = -2945777694260521066L;
        final c<? super R> actual;
        final boolean veryEnd;

        ConcatMapDelayed(c<? super R> cVar, g<? super T, ? extends org.reactivestreams.b<? extends R>> gVar, int i, boolean z) {
            super(gVar, i);
            this.actual = cVar;
            this.veryEnd = z;
        }

        /* access modifiers changed from: 0000 */
        public void subscribeActual() {
            this.actual.onSubscribe(this);
        }

        public void onError(Throwable th) {
            if (this.errors.addThrowable(th)) {
                this.done = true;
                drain();
                return;
            }
            io.reactivex.d.a.a(th);
        }

        public void innerNext(R r) {
            this.actual.onNext(r);
        }

        public void innerError(Throwable th) {
            if (this.errors.addThrowable(th)) {
                if (!this.veryEnd) {
                    this.s.cancel();
                    this.done = true;
                }
                this.active = false;
                drain();
                return;
            }
            io.reactivex.d.a.a(th);
        }

        public void request(long j) {
            this.inner.request(j);
        }

        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.inner.cancel();
                this.s.cancel();
            }
        }

        /* access modifiers changed from: 0000 */
        public void drain() {
            if (getAndIncrement() == 0) {
                while (!this.cancelled) {
                    if (!this.active) {
                        boolean z = this.done;
                        if (!z || this.veryEnd || ((Throwable) this.errors.get()) == null) {
                            try {
                                Object poll = this.queue.poll();
                                boolean z2 = poll == null;
                                if (z && z2) {
                                    Throwable terminate = this.errors.terminate();
                                    if (terminate != null) {
                                        this.actual.onError(terminate);
                                    } else {
                                        this.actual.onComplete();
                                    }
                                    return;
                                } else if (!z2) {
                                    try {
                                        org.reactivestreams.b bVar = (org.reactivestreams.b) io.reactivex.internal.functions.a.a(this.mapper.apply(poll), "The mapper returned a null Publisher");
                                        if (this.sourceMode != 1) {
                                            int i = this.consumed + 1;
                                            if (i == this.limit) {
                                                this.consumed = 0;
                                                this.s.request((long) i);
                                            } else {
                                                this.consumed = i;
                                            }
                                        }
                                        if (bVar instanceof Callable) {
                                            try {
                                                Object call = ((Callable) bVar).call();
                                                if (call == null) {
                                                    continue;
                                                } else if (this.inner.isUnbounded()) {
                                                    this.actual.onNext(call);
                                                } else {
                                                    this.active = true;
                                                    this.inner.setSubscription(new b(call, this.inner));
                                                }
                                            } catch (Throwable th) {
                                                io.reactivex.exceptions.a.b(th);
                                                this.s.cancel();
                                                this.errors.addThrowable(th);
                                                this.actual.onError(this.errors.terminate());
                                                return;
                                            }
                                        } else {
                                            this.active = true;
                                            bVar.subscribe(this.inner);
                                        }
                                    } catch (Throwable th2) {
                                        io.reactivex.exceptions.a.b(th2);
                                        this.s.cancel();
                                        this.errors.addThrowable(th2);
                                        this.actual.onError(this.errors.terminate());
                                        return;
                                    }
                                }
                            } catch (Throwable th3) {
                                io.reactivex.exceptions.a.b(th3);
                                this.s.cancel();
                                this.errors.addThrowable(th3);
                                this.actual.onError(this.errors.terminate());
                                return;
                            }
                        } else {
                            this.actual.onError(this.errors.terminate());
                            return;
                        }
                    }
                    if (decrementAndGet() == 0) {
                    }
                }
            }
        }
    }

    static final class ConcatMapImmediate<T, R> extends BaseConcatMapSubscriber<T, R> {
        private static final long serialVersionUID = 7898995095634264146L;
        final c<? super R> actual;
        final AtomicInteger wip = new AtomicInteger();

        ConcatMapImmediate(c<? super R> cVar, g<? super T, ? extends org.reactivestreams.b<? extends R>> gVar, int i) {
            super(gVar, i);
            this.actual = cVar;
        }

        /* access modifiers changed from: 0000 */
        public void subscribeActual() {
            this.actual.onSubscribe(this);
        }

        public void onError(Throwable th) {
            if (this.errors.addThrowable(th)) {
                this.inner.cancel();
                if (getAndIncrement() == 0) {
                    this.actual.onError(this.errors.terminate());
                    return;
                }
                return;
            }
            io.reactivex.d.a.a(th);
        }

        public void innerNext(R r) {
            if (get() == 0 && compareAndSet(0, 1)) {
                this.actual.onNext(r);
                if (!compareAndSet(1, 0)) {
                    this.actual.onError(this.errors.terminate());
                }
            }
        }

        public void innerError(Throwable th) {
            if (this.errors.addThrowable(th)) {
                this.s.cancel();
                if (getAndIncrement() == 0) {
                    this.actual.onError(this.errors.terminate());
                    return;
                }
                return;
            }
            io.reactivex.d.a.a(th);
        }

        public void request(long j) {
            this.inner.request(j);
        }

        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.inner.cancel();
                this.s.cancel();
            }
        }

        /* access modifiers changed from: 0000 */
        public void drain() {
            if (this.wip.getAndIncrement() == 0) {
                while (!this.cancelled) {
                    if (!this.active) {
                        boolean z = this.done;
                        try {
                            Object poll = this.queue.poll();
                            boolean z2 = poll == null;
                            if (z && z2) {
                                this.actual.onComplete();
                                return;
                            } else if (!z2) {
                                try {
                                    org.reactivestreams.b bVar = (org.reactivestreams.b) io.reactivex.internal.functions.a.a(this.mapper.apply(poll), "The mapper returned a null Publisher");
                                    if (this.sourceMode != 1) {
                                        int i = this.consumed + 1;
                                        if (i == this.limit) {
                                            this.consumed = 0;
                                            this.s.request((long) i);
                                        } else {
                                            this.consumed = i;
                                        }
                                    }
                                    if (bVar instanceof Callable) {
                                        try {
                                            Object call = ((Callable) bVar).call();
                                            if (call == null) {
                                                continue;
                                            } else if (!this.inner.isUnbounded()) {
                                                this.active = true;
                                                this.inner.setSubscription(new b(call, this.inner));
                                            } else if (get() == 0 && compareAndSet(0, 1)) {
                                                this.actual.onNext(call);
                                                if (!compareAndSet(1, 0)) {
                                                    this.actual.onError(this.errors.terminate());
                                                    return;
                                                }
                                            }
                                        } catch (Throwable th) {
                                            io.reactivex.exceptions.a.b(th);
                                            this.s.cancel();
                                            this.errors.addThrowable(th);
                                            this.actual.onError(this.errors.terminate());
                                            return;
                                        }
                                    } else {
                                        this.active = true;
                                        bVar.subscribe(this.inner);
                                    }
                                } catch (Throwable th2) {
                                    io.reactivex.exceptions.a.b(th2);
                                    this.s.cancel();
                                    this.errors.addThrowable(th2);
                                    this.actual.onError(this.errors.terminate());
                                    return;
                                }
                            }
                        } catch (Throwable th3) {
                            io.reactivex.exceptions.a.b(th3);
                            this.s.cancel();
                            this.errors.addThrowable(th3);
                            this.actual.onError(this.errors.terminate());
                            return;
                        }
                    }
                    if (this.wip.decrementAndGet() == 0) {
                    }
                }
            }
        }
    }

    static final class ConcatMapInner<R> extends SubscriptionArbiter implements j<R> {
        private static final long serialVersionUID = 897683679971470653L;
        final a<R> parent;
        long produced;

        ConcatMapInner(a<R> aVar) {
            this.parent = aVar;
        }

        public void onSubscribe(Subscription subscription) {
            setSubscription(subscription);
        }

        public void onNext(R r) {
            this.produced++;
            this.parent.innerNext(r);
        }

        public void onError(Throwable th) {
            long j = this.produced;
            if (j != 0) {
                this.produced = 0;
                produced(j);
            }
            this.parent.innerError(th);
        }

        public void onComplete() {
            long j = this.produced;
            if (j != 0) {
                this.produced = 0;
                produced(j);
            }
            this.parent.innerComplete();
        }
    }

    interface a<T> {
        void innerComplete();

        void innerError(Throwable th);

        void innerNext(T t);
    }

    static final class b<T> implements Subscription {
        final c<? super T> a;
        final T b;
        boolean c;

        public void cancel() {
        }

        b(T t, c<? super T> cVar) {
            this.b = t;
            this.a = cVar;
        }

        public void request(long j) {
            if (j > 0 && !this.c) {
                this.c = true;
                c<? super T> cVar = this.a;
                cVar.onNext(this.b);
                cVar.onComplete();
            }
        }
    }

    public static <T, R> c<T> a(c<? super R> cVar, g<? super T, ? extends org.reactivestreams.b<? extends R>> gVar, int i, ErrorMode errorMode) {
        switch (errorMode) {
            case BOUNDARY:
                return new ConcatMapDelayed(cVar, gVar, i, false);
            case END:
                return new ConcatMapDelayed(cVar, gVar, i, true);
            default:
                return new ConcatMapImmediate(cVar, gVar, i);
        }
    }

    /* access modifiers changed from: protected */
    public void a(c<? super R> cVar) {
        if (!e.a(this.b, cVar, this.c)) {
            this.b.subscribe(a(cVar, this.c, this.d, this.e));
        }
    }
}
