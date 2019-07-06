package io.reactivex.internal.operators.flowable;

import io.reactivex.g;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.b;
import io.reactivex.j;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;
import org.reactivestreams.c;

public final class FlowableOnBackpressureLatest<T> extends a<T, T> {

    static final class BackpressureLatestSubscriber<T> extends AtomicInteger implements j<T>, Subscription {
        private static final long serialVersionUID = 163080509307634843L;
        final c<? super T> actual;
        volatile boolean cancelled;
        final AtomicReference<T> current = new AtomicReference<>();
        volatile boolean done;
        Throwable error;
        final AtomicLong requested = new AtomicLong();
        Subscription s;

        BackpressureLatestSubscriber(c<? super T> cVar) {
            this.actual = cVar;
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.s, subscription)) {
                this.s = subscription;
                this.actual.onSubscribe(this);
                subscription.request(Long.MAX_VALUE);
            }
        }

        public void onNext(T t) {
            this.current.lazySet(t);
            drain();
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

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                b.a(this.requested, j);
                drain();
            }
        }

        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.s.cancel();
                if (getAndIncrement() == 0) {
                    this.current.lazySet(null);
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void drain() {
            boolean z;
            if (getAndIncrement() == 0) {
                c<? super T> cVar = this.actual;
                AtomicLong atomicLong = this.requested;
                AtomicReference<T> atomicReference = this.current;
                int i = 1;
                do {
                    long j = 0;
                    while (true) {
                        z = false;
                        if (j == atomicLong.get()) {
                            break;
                        }
                        boolean z2 = this.done;
                        Object andSet = atomicReference.getAndSet(null);
                        boolean z3 = andSet == null;
                        if (!checkTerminated(z2, z3, cVar, atomicReference)) {
                            if (z3) {
                                break;
                            }
                            cVar.onNext(andSet);
                            j++;
                        } else {
                            return;
                        }
                    }
                    if (j == atomicLong.get()) {
                        boolean z4 = this.done;
                        if (atomicReference.get() == null) {
                            z = true;
                        }
                        if (checkTerminated(z4, z, cVar, atomicReference)) {
                            return;
                        }
                    }
                    if (j != 0) {
                        b.c(atomicLong, j);
                    }
                    i = addAndGet(-i);
                } while (i != 0);
            }
        }

        /* access modifiers changed from: 0000 */
        public boolean checkTerminated(boolean z, boolean z2, c<?> cVar, AtomicReference<T> atomicReference) {
            if (this.cancelled) {
                atomicReference.lazySet(null);
                return true;
            }
            if (z) {
                Throwable th = this.error;
                if (th != null) {
                    atomicReference.lazySet(null);
                    cVar.onError(th);
                    return true;
                } else if (z2) {
                    cVar.onComplete();
                    return true;
                }
            }
            return false;
        }
    }

    public FlowableOnBackpressureLatest(g<T> gVar) {
        super(gVar);
    }

    /* access modifiers changed from: protected */
    public void a(c<? super T> cVar) {
        this.b.a((j<? super T>) new BackpressureLatestSubscriber<Object>(cVar));
    }
}
