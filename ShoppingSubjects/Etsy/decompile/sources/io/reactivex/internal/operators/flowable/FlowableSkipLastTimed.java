package io.reactivex.internal.operators.flowable;

import io.reactivex.g;
import io.reactivex.internal.queue.a;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.b;
import io.reactivex.j;
import io.reactivex.u;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.reactivestreams.Subscription;
import org.reactivestreams.c;

public final class FlowableSkipLastTimed<T> extends a<T, T> {
    final long c;
    final TimeUnit d;
    final u e;
    final int f;
    final boolean g;

    static final class SkipLastTimedSubscriber<T> extends AtomicInteger implements j<T>, Subscription {
        private static final long serialVersionUID = -5677354903406201275L;
        final c<? super T> actual;
        volatile boolean cancelled;
        final boolean delayError;
        volatile boolean done;
        Throwable error;
        final a<Object> queue;
        final AtomicLong requested = new AtomicLong();
        Subscription s;
        final u scheduler;
        final long time;
        final TimeUnit unit;

        SkipLastTimedSubscriber(c<? super T> cVar, long j, TimeUnit timeUnit, u uVar, int i, boolean z) {
            this.actual = cVar;
            this.time = j;
            this.unit = timeUnit;
            this.scheduler = uVar;
            this.queue = new a<>(i);
            this.delayError = z;
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.s, subscription)) {
                this.s = subscription;
                this.actual.onSubscribe(this);
                subscription.request(Long.MAX_VALUE);
            }
        }

        public void onNext(T t) {
            this.queue.a(Long.valueOf(this.scheduler.a(this.unit)), t);
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
                    this.queue.clear();
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void drain() {
            if (getAndIncrement() == 0) {
                c<? super T> cVar = this.actual;
                a<Object> aVar = this.queue;
                boolean z = this.delayError;
                TimeUnit timeUnit = this.unit;
                u uVar = this.scheduler;
                long j = this.time;
                int i = 1;
                do {
                    long j2 = this.requested.get();
                    long j3 = 0;
                    while (j3 != j2) {
                        boolean z2 = this.done;
                        Long l = (Long) aVar.a();
                        boolean z3 = l == null;
                        long a = uVar.a(timeUnit);
                        if (!z3 && l.longValue() > a - j) {
                            z3 = true;
                        }
                        if (!checkTerminated(z2, z3, cVar, z)) {
                            if (z3) {
                                break;
                            }
                            aVar.poll();
                            cVar.onNext(aVar.poll());
                            j3++;
                        } else {
                            return;
                        }
                    }
                    if (j3 != 0) {
                        b.c(this.requested, j3);
                    }
                    i = addAndGet(-i);
                } while (i != 0);
            }
        }

        /* access modifiers changed from: 0000 */
        public boolean checkTerminated(boolean z, boolean z2, c<? super T> cVar, boolean z3) {
            if (this.cancelled) {
                this.queue.clear();
                return true;
            }
            if (z) {
                if (!z3) {
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
    }

    /* access modifiers changed from: protected */
    public void a(c<? super T> cVar) {
        g gVar = this.b;
        SkipLastTimedSubscriber skipLastTimedSubscriber = new SkipLastTimedSubscriber(cVar, this.c, this.d, this.e, this.f, this.g);
        gVar.a((j<? super T>) skipLastTimedSubscriber);
    }
}
