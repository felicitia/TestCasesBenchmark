package io.reactivex.internal.operators.flowable;

import io.reactivex.d.a;
import io.reactivex.g;
import io.reactivex.internal.a.d;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.j;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;
import org.reactivestreams.b;
import org.reactivestreams.c;

public final class FlowableZip<T, R> extends g<R> {
    final b<? extends T>[] b;
    final Iterable<? extends b<? extends T>> c;
    final io.reactivex.functions.g<? super Object[], ? extends R> d;
    final int e;
    final boolean f;

    static final class ZipCoordinator<T, R> extends AtomicInteger implements Subscription {
        private static final long serialVersionUID = -2434867452883857743L;
        final c<? super R> actual;
        volatile boolean cancelled;
        final Object[] current;
        final boolean delayErrors;
        final AtomicThrowable errors;
        final AtomicLong requested;
        final ZipSubscriber<T, R>[] subscribers;
        final io.reactivex.functions.g<? super Object[], ? extends R> zipper;

        ZipCoordinator(c<? super R> cVar, io.reactivex.functions.g<? super Object[], ? extends R> gVar, int i, int i2, boolean z) {
            this.actual = cVar;
            this.zipper = gVar;
            this.delayErrors = z;
            ZipSubscriber<T, R>[] zipSubscriberArr = new ZipSubscriber[i];
            for (int i3 = 0; i3 < i; i3++) {
                zipSubscriberArr[i3] = new ZipSubscriber<>(this, i2);
            }
            this.current = new Object[i];
            this.subscribers = zipSubscriberArr;
            this.requested = new AtomicLong();
            this.errors = new AtomicThrowable();
        }

        /* access modifiers changed from: 0000 */
        public void subscribe(b<? extends T>[] bVarArr, int i) {
            ZipSubscriber<T, R>[] zipSubscriberArr = this.subscribers;
            for (int i2 = 0; i2 < i && !this.cancelled && (this.delayErrors || this.errors.get() == null); i2++) {
                bVarArr[i2].subscribe(zipSubscriberArr[i2]);
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
                cancelAll();
            }
        }

        /* access modifiers changed from: 0000 */
        public void error(ZipSubscriber<T, R> zipSubscriber, Throwable th) {
            if (this.errors.addThrowable(th)) {
                zipSubscriber.done = true;
                drain();
                return;
            }
            a.a(th);
        }

        /* access modifiers changed from: 0000 */
        public void cancelAll() {
            for (ZipSubscriber<T, R> cancel : this.subscribers) {
                cancel.cancel();
            }
        }

        /* access modifiers changed from: 0000 */
        public void drain() {
            boolean z;
            if (getAndIncrement() == 0) {
                c<? super R> cVar = this.actual;
                ZipSubscriber<T, R>[] zipSubscriberArr = this.subscribers;
                int length = zipSubscriberArr.length;
                Object[] objArr = this.current;
                int i = 1;
                do {
                    long j = this.requested.get();
                    long j2 = 0;
                    while (j != j2) {
                        if (!this.cancelled) {
                            if (this.delayErrors || this.errors.get() == null) {
                                boolean z2 = false;
                                for (int i2 = 0; i2 < length; i2++) {
                                    ZipSubscriber<T, R> zipSubscriber = zipSubscriberArr[i2];
                                    if (objArr[i2] == null) {
                                        try {
                                            boolean z3 = zipSubscriber.done;
                                            io.reactivex.internal.a.g<T> gVar = zipSubscriber.queue;
                                            Object poll = gVar != null ? gVar.poll() : null;
                                            boolean z4 = poll == null;
                                            if (!z3 || !z4) {
                                                if (!z4) {
                                                    objArr[i2] = poll;
                                                    z = z2;
                                                } else {
                                                    z = true;
                                                }
                                                z2 = z;
                                            } else {
                                                cancelAll();
                                                if (((Throwable) this.errors.get()) != null) {
                                                    cVar.onError(this.errors.terminate());
                                                } else {
                                                    cVar.onComplete();
                                                }
                                                return;
                                            }
                                        } catch (Throwable th) {
                                            Throwable th2 = th;
                                            io.reactivex.exceptions.a.b(th2);
                                            this.errors.addThrowable(th2);
                                            if (!this.delayErrors) {
                                                cancelAll();
                                                cVar.onError(this.errors.terminate());
                                                return;
                                            }
                                            z2 = true;
                                        }
                                    }
                                }
                                if (z2) {
                                    break;
                                }
                                try {
                                    cVar.onNext(io.reactivex.internal.functions.a.a(this.zipper.apply(objArr.clone()), "The zipper returned a null value"));
                                    long j3 = j2 + 1;
                                    Arrays.fill(objArr, null);
                                    j2 = j3;
                                } catch (Throwable th3) {
                                    Throwable th4 = th3;
                                    io.reactivex.exceptions.a.b(th4);
                                    cancelAll();
                                    this.errors.addThrowable(th4);
                                    cVar.onError(this.errors.terminate());
                                    return;
                                }
                            } else {
                                cancelAll();
                                cVar.onError(this.errors.terminate());
                                return;
                            }
                        } else {
                            return;
                        }
                    }
                    if (j == j2) {
                        if (!this.cancelled) {
                            if (this.delayErrors || this.errors.get() == null) {
                                for (int i3 = 0; i3 < length; i3++) {
                                    ZipSubscriber<T, R> zipSubscriber2 = zipSubscriberArr[i3];
                                    if (objArr[i3] == null) {
                                        try {
                                            boolean z5 = zipSubscriber2.done;
                                            io.reactivex.internal.a.g<T> gVar2 = zipSubscriber2.queue;
                                            Object poll2 = gVar2 != null ? gVar2.poll() : null;
                                            boolean z6 = poll2 == null;
                                            if (z5 && z6) {
                                                cancelAll();
                                                if (((Throwable) this.errors.get()) != null) {
                                                    cVar.onError(this.errors.terminate());
                                                } else {
                                                    cVar.onComplete();
                                                }
                                                return;
                                            } else if (!z6) {
                                                objArr[i3] = poll2;
                                            }
                                        } catch (Throwable th5) {
                                            Throwable th6 = th5;
                                            io.reactivex.exceptions.a.b(th6);
                                            this.errors.addThrowable(th6);
                                            if (!this.delayErrors) {
                                                cancelAll();
                                                cVar.onError(this.errors.terminate());
                                                return;
                                            }
                                        }
                                    }
                                }
                            } else {
                                cancelAll();
                                cVar.onError(this.errors.terminate());
                                return;
                            }
                        } else {
                            return;
                        }
                    }
                    if (j2 != 0) {
                        for (ZipSubscriber<T, R> request : zipSubscriberArr) {
                            request.request(j2);
                        }
                        if (j != Long.MAX_VALUE) {
                            this.requested.addAndGet(-j2);
                        }
                    }
                    i = addAndGet(-i);
                } while (i != 0);
            }
        }
    }

    static final class ZipSubscriber<T, R> extends AtomicReference<Subscription> implements j<T>, Subscription {
        private static final long serialVersionUID = -4627193790118206028L;
        volatile boolean done;
        final int limit;
        final ZipCoordinator<T, R> parent;
        final int prefetch;
        long produced;
        io.reactivex.internal.a.g<T> queue;
        int sourceMode;

        ZipSubscriber(ZipCoordinator<T, R> zipCoordinator, int i) {
            this.parent = zipCoordinator;
            this.prefetch = i;
            this.limit = i - (i >> 2);
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.setOnce(this, subscription)) {
                if (subscription instanceof d) {
                    d dVar = (d) subscription;
                    int requestFusion = dVar.requestFusion(7);
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
            if (this.sourceMode != 2) {
                this.queue.offer(t);
            }
            this.parent.drain();
        }

        public void onError(Throwable th) {
            this.parent.error(this, th);
        }

        public void onComplete() {
            this.done = true;
            this.parent.drain();
        }

        public void cancel() {
            SubscriptionHelper.cancel(this);
        }

        public void request(long j) {
            if (this.sourceMode != 1) {
                long j2 = this.produced + j;
                if (j2 >= ((long) this.limit)) {
                    this.produced = 0;
                    ((Subscription) get()).request(j2);
                    return;
                }
                this.produced = j2;
            }
        }
    }

    public void a(c<? super R> cVar) {
        b<? extends T>[] bVarArr;
        int i;
        b<? extends T>[] bVarArr2 = this.b;
        if (bVarArr2 == null) {
            b<? extends T>[] bVarArr3 = new b[8];
            b<? extends T>[] bVarArr4 = bVarArr3;
            i = 0;
            for (b<? extends T> bVar : this.c) {
                if (i == bVarArr4.length) {
                    b<? extends T>[] bVarArr5 = new b[((i >> 2) + i)];
                    System.arraycopy(bVarArr4, 0, bVarArr5, 0, i);
                    bVarArr4 = bVarArr5;
                }
                int i2 = i + 1;
                bVarArr4[i] = bVar;
                i = i2;
            }
            bVarArr = bVarArr4;
        } else {
            bVarArr = bVarArr2;
            i = bVarArr2.length;
        }
        if (i == 0) {
            EmptySubscription.complete(cVar);
            return;
        }
        ZipCoordinator zipCoordinator = new ZipCoordinator(cVar, this.d, i, this.e, this.f);
        cVar.onSubscribe(zipCoordinator);
        zipCoordinator.subscribe(bVarArr, i);
    }
}
