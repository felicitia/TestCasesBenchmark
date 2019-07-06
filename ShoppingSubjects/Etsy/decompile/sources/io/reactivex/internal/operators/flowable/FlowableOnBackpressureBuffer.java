package io.reactivex.internal.operators.flowable;

import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.functions.a;
import io.reactivex.g;
import io.reactivex.internal.a.f;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.BasicIntQueueSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.b;
import io.reactivex.j;
import java.util.concurrent.atomic.AtomicLong;
import org.reactivestreams.Subscription;
import org.reactivestreams.c;

public final class FlowableOnBackpressureBuffer<T> extends a<T, T> {
    final int c;
    final boolean d;
    final boolean e;
    final a f;

    static final class BackpressureBufferSubscriber<T> extends BasicIntQueueSubscription<T> implements j<T> {
        private static final long serialVersionUID = -2514538129242366402L;
        final c<? super T> actual;
        volatile boolean cancelled;
        final boolean delayError;
        volatile boolean done;
        Throwable error;
        final a onOverflow;
        boolean outputFused;
        final f<T> queue;
        final AtomicLong requested = new AtomicLong();
        Subscription s;

        BackpressureBufferSubscriber(c<? super T> cVar, int i, boolean z, boolean z2, a aVar) {
            f<T> fVar;
            this.actual = cVar;
            this.onOverflow = aVar;
            this.delayError = z2;
            if (z) {
                fVar = new io.reactivex.internal.queue.a<>(i);
            } else {
                fVar = new SpscArrayQueue<>(i);
            }
            this.queue = fVar;
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.s, subscription)) {
                this.s = subscription;
                this.actual.onSubscribe(this);
                subscription.request(Long.MAX_VALUE);
            }
        }

        public void onNext(T t) {
            if (!this.queue.offer(t)) {
                this.s.cancel();
                MissingBackpressureException missingBackpressureException = new MissingBackpressureException("Buffer is full");
                try {
                    this.onOverflow.a();
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    missingBackpressureException.initCause(th);
                }
                onError(missingBackpressureException);
                return;
            }
            if (this.outputFused) {
                this.actual.onNext(null);
            } else {
                drain();
            }
        }

        public void onError(Throwable th) {
            this.error = th;
            this.done = true;
            if (this.outputFused) {
                this.actual.onError(th);
            } else {
                drain();
            }
        }

        public void onComplete() {
            this.done = true;
            if (this.outputFused) {
                this.actual.onComplete();
            } else {
                drain();
            }
        }

        public void request(long j) {
            if (!this.outputFused && SubscriptionHelper.validate(j)) {
                b.a(this.requested, j);
                drain();
            }
        }

        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.s.cancel();
                if (getAndIncrement() == 0) {
                    this.queue.clear();
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void drain() {
            if (getAndIncrement() == 0) {
                f<T> fVar = this.queue;
                c<? super T> cVar = this.actual;
                int i = 1;
                while (!checkTerminated(this.done, fVar.isEmpty(), cVar)) {
                    long j = this.requested.get();
                    long j2 = 0;
                    while (j2 != j) {
                        boolean z = this.done;
                        Object poll = fVar.poll();
                        boolean z2 = poll == null;
                        if (!checkTerminated(z, z2, cVar)) {
                            if (z2) {
                                break;
                            }
                            cVar.onNext(poll);
                            j2++;
                        } else {
                            return;
                        }
                    }
                    if (j2 != j || !checkTerminated(this.done, fVar.isEmpty(), cVar)) {
                        if (!(j2 == 0 || j == Long.MAX_VALUE)) {
                            this.requested.addAndGet(-j2);
                        }
                        i = addAndGet(-i);
                        if (i == 0) {
                        }
                    } else {
                        return;
                    }
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public boolean checkTerminated(boolean z, boolean z2, c<? super T> cVar) {
            if (this.cancelled) {
                this.queue.clear();
                return true;
            }
            if (z) {
                if (!this.delayError) {
                    Throwable th = this.error;
                    if (th != null) {
                        this.queue.clear();
                        cVar.onError(th);
                        return true;
                    } else if (z2) {
                        cVar.onComplete();
                        return true;
                    }
                } else if (z2) {
                    Throwable th2 = this.error;
                    if (th2 != null) {
                        cVar.onError(th2);
                    } else {
                        cVar.onComplete();
                    }
                    return true;
                }
            }
            return false;
        }

        public int requestFusion(int i) {
            if ((i & 2) == 0) {
                return 0;
            }
            this.outputFused = true;
            return 2;
        }

        public T poll() throws Exception {
            return this.queue.poll();
        }

        public void clear() {
            this.queue.clear();
        }

        public boolean isEmpty() {
            return this.queue.isEmpty();
        }
    }

    public FlowableOnBackpressureBuffer(g<T> gVar, int i, boolean z, boolean z2, a aVar) {
        super(gVar);
        this.c = i;
        this.d = z;
        this.e = z2;
        this.f = aVar;
    }

    /* access modifiers changed from: protected */
    public void a(c<? super T> cVar) {
        g gVar = this.b;
        BackpressureBufferSubscriber backpressureBufferSubscriber = new BackpressureBufferSubscriber(cVar, this.c, this.d, this.e, this.f);
        gVar.a((j<? super T>) backpressureBufferSubscriber);
    }
}
