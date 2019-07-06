package io.reactivex.internal.operators.flowable;

import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.functions.d;
import io.reactivex.g;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.DeferredScalarSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.j;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;
import org.reactivestreams.b;
import org.reactivestreams.c;

public final class FlowableSequenceEqual<T> extends g<Boolean> {
    final b<? extends T> b;
    final b<? extends T> c;
    final d<? super T, ? super T> d;
    final int e;

    static final class EqualCoordinator<T> extends DeferredScalarSubscription<Boolean> implements a {
        private static final long serialVersionUID = -6178010334400373240L;
        final d<? super T, ? super T> comparer;
        final AtomicThrowable error;
        final EqualSubscriber<T> first;
        final EqualSubscriber<T> second;
        T v1;
        T v2;
        final AtomicInteger wip = new AtomicInteger();

        EqualCoordinator(c<? super Boolean> cVar, int i, d<? super T, ? super T> dVar) {
            super(cVar);
            this.comparer = dVar;
            this.first = new EqualSubscriber<>(this, i);
            this.second = new EqualSubscriber<>(this, i);
            this.error = new AtomicThrowable();
        }

        /* access modifiers changed from: 0000 */
        public void subscribe(b<? extends T> bVar, b<? extends T> bVar2) {
            bVar.subscribe(this.first);
            bVar2.subscribe(this.second);
        }

        public void cancel() {
            super.cancel();
            this.first.cancel();
            this.second.cancel();
            if (this.wip.getAndIncrement() == 0) {
                this.first.clear();
                this.second.clear();
            }
        }

        /* access modifiers changed from: 0000 */
        public void cancelAndClear() {
            this.first.cancel();
            this.first.clear();
            this.second.cancel();
            this.second.clear();
        }

        public void drain() {
            if (this.wip.getAndIncrement() == 0) {
                int i = 1;
                do {
                    io.reactivex.internal.a.g<T> gVar = this.first.queue;
                    io.reactivex.internal.a.g<T> gVar2 = this.second.queue;
                    if (gVar != null && gVar2 != null) {
                        while (!isCancelled()) {
                            if (((Throwable) this.error.get()) != null) {
                                cancelAndClear();
                                this.actual.onError(this.error.terminate());
                                return;
                            }
                            boolean z = this.first.done;
                            T t = this.v1;
                            if (t == null) {
                                try {
                                    t = gVar.poll();
                                    this.v1 = t;
                                } catch (Throwable th) {
                                    io.reactivex.exceptions.a.b(th);
                                    cancelAndClear();
                                    this.error.addThrowable(th);
                                    this.actual.onError(this.error.terminate());
                                    return;
                                }
                            }
                            boolean z2 = t == null;
                            boolean z3 = this.second.done;
                            T t2 = this.v2;
                            if (t2 == null) {
                                try {
                                    t2 = gVar2.poll();
                                    this.v2 = t2;
                                } catch (Throwable th2) {
                                    io.reactivex.exceptions.a.b(th2);
                                    cancelAndClear();
                                    this.error.addThrowable(th2);
                                    this.actual.onError(this.error.terminate());
                                    return;
                                }
                            }
                            boolean z4 = t2 == null;
                            if (z && z3 && z2 && z4) {
                                complete(Boolean.valueOf(true));
                                return;
                            } else if (z && z3 && z2 != z4) {
                                cancelAndClear();
                                complete(Boolean.valueOf(false));
                                return;
                            } else if (!z2 && !z4) {
                                try {
                                    if (!this.comparer.a(t, t2)) {
                                        cancelAndClear();
                                        complete(Boolean.valueOf(false));
                                        return;
                                    }
                                    this.v1 = null;
                                    this.v2 = null;
                                    this.first.request();
                                    this.second.request();
                                } catch (Throwable th3) {
                                    io.reactivex.exceptions.a.b(th3);
                                    cancelAndClear();
                                    this.error.addThrowable(th3);
                                    this.actual.onError(this.error.terminate());
                                    return;
                                }
                            }
                        }
                        this.first.clear();
                        this.second.clear();
                        return;
                    } else if (isCancelled()) {
                        this.first.clear();
                        this.second.clear();
                        return;
                    } else if (((Throwable) this.error.get()) != null) {
                        cancelAndClear();
                        this.actual.onError(this.error.terminate());
                        return;
                    }
                    i = this.wip.addAndGet(-i);
                } while (i != 0);
            }
        }

        public void innerError(Throwable th) {
            if (this.error.addThrowable(th)) {
                drain();
            } else {
                io.reactivex.d.a.a(th);
            }
        }
    }

    static final class EqualSubscriber<T> extends AtomicReference<Subscription> implements j<T> {
        private static final long serialVersionUID = 4804128302091633067L;
        volatile boolean done;
        final int limit;
        final a parent;
        final int prefetch;
        long produced;
        volatile io.reactivex.internal.a.g<T> queue;
        int sourceMode;

        EqualSubscriber(a aVar, int i) {
            this.parent = aVar;
            this.limit = i - (i >> 2);
            this.prefetch = i;
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.setOnce(this, subscription)) {
                if (subscription instanceof io.reactivex.internal.a.d) {
                    io.reactivex.internal.a.d dVar = (io.reactivex.internal.a.d) subscription;
                    int requestFusion = dVar.requestFusion(3);
                    if (requestFusion == 1) {
                        this.sourceMode = requestFusion;
                        this.queue = dVar;
                        this.done = true;
                        this.parent.drain();
                        return;
                    } else if (requestFusion == 2) {
                        this.sourceMode = requestFusion;
                        this.queue = dVar;
                        subscription.request((long) this.prefetch);
                        return;
                    }
                }
                this.queue = new SpscArrayQueue(this.prefetch);
                subscription.request((long) this.prefetch);
            }
        }

        public void onNext(T t) {
            if (this.sourceMode != 0 || this.queue.offer(t)) {
                this.parent.drain();
            } else {
                onError(new MissingBackpressureException());
            }
        }

        public void onError(Throwable th) {
            this.parent.innerError(th);
        }

        public void onComplete() {
            this.done = true;
            this.parent.drain();
        }

        public void request() {
            if (this.sourceMode != 1) {
                long j = this.produced + 1;
                if (j >= ((long) this.limit)) {
                    this.produced = 0;
                    ((Subscription) get()).request(j);
                    return;
                }
                this.produced = j;
            }
        }

        public void cancel() {
            SubscriptionHelper.cancel(this);
        }

        /* access modifiers changed from: 0000 */
        public void clear() {
            io.reactivex.internal.a.g<T> gVar = this.queue;
            if (gVar != null) {
                gVar.clear();
            }
        }
    }

    interface a {
        void drain();

        void innerError(Throwable th);
    }

    public void a(c<? super Boolean> cVar) {
        EqualCoordinator equalCoordinator = new EqualCoordinator(cVar, this.e, this.d);
        cVar.onSubscribe(equalCoordinator);
        equalCoordinator.subscribe(this.b, this.c);
    }
}
