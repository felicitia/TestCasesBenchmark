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

public final class FlowableTakeLastTimed<T> extends a<T, T> {
    final long c;
    final long d;
    final TimeUnit e;
    final u f;
    final int g;
    final boolean h;

    static final class TakeLastTimedSubscriber<T> extends AtomicInteger implements j<T>, Subscription {
        private static final long serialVersionUID = -5677354903406201275L;
        final c<? super T> actual;
        volatile boolean cancelled;
        final long count;
        final boolean delayError;
        volatile boolean done;
        Throwable error;
        final a<Object> queue;
        final AtomicLong requested = new AtomicLong();
        Subscription s;
        final u scheduler;
        final long time;
        final TimeUnit unit;

        TakeLastTimedSubscriber(c<? super T> cVar, long j, long j2, TimeUnit timeUnit, u uVar, int i, boolean z) {
            this.actual = cVar;
            this.count = j;
            this.time = j2;
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
            a<Object> aVar = this.queue;
            long a = this.scheduler.a(this.unit);
            aVar.a(Long.valueOf(a), t);
            trim(a, aVar);
        }

        public void onError(Throwable th) {
            if (this.delayError) {
                trim(this.scheduler.a(this.unit), this.queue);
            }
            this.error = th;
            this.done = true;
            drain();
        }

        public void onComplete() {
            trim(this.scheduler.a(this.unit), this.queue);
            this.done = true;
            drain();
        }

        /* access modifiers changed from: 0000 */
        public void trim(long j, a<Object> aVar) {
            long j2 = this.time;
            long j3 = this.count;
            boolean z = j3 == Long.MAX_VALUE;
            while (!aVar.isEmpty()) {
                if (((Long) aVar.a()).longValue() < j - j2 || (!z && ((long) (aVar.b() >> 1)) > j3)) {
                    aVar.poll();
                    aVar.poll();
                } else {
                    return;
                }
            }
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
                int i = 1;
                do {
                    if (this.done) {
                        if (!checkTerminated(aVar.isEmpty(), cVar, z)) {
                            long j = this.requested.get();
                            long j2 = 0;
                            while (true) {
                                if (!checkTerminated(aVar.a() == null, cVar, z)) {
                                    if (j != j2) {
                                        aVar.poll();
                                        cVar.onNext(aVar.poll());
                                        j2++;
                                    } else if (j2 != 0) {
                                        b.c(this.requested, j2);
                                    }
                                } else {
                                    return;
                                }
                            }
                        } else {
                            return;
                        }
                    }
                    i = addAndGet(-i);
                } while (i != 0);
            }
        }

        /* access modifiers changed from: 0000 */
        public boolean checkTerminated(boolean z, c<? super T> cVar, boolean z2) {
            if (this.cancelled) {
                this.queue.clear();
                return true;
            }
            if (!z2) {
                Throwable th = this.error;
                if (th != null) {
                    this.queue.clear();
                    cVar.onError(th);
                    return true;
                } else if (z) {
                    cVar.onComplete();
                    return true;
                }
            } else if (z) {
                Throwable th2 = this.error;
                if (th2 != null) {
                    cVar.onError(th2);
                } else {
                    cVar.onComplete();
                }
                return true;
            }
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public void a(c<? super T> cVar) {
        g gVar = this.b;
        TakeLastTimedSubscriber takeLastTimedSubscriber = new TakeLastTimedSubscriber(cVar, this.c, this.d, this.e, this.f, this.g, this.h);
        gVar.a((j<? super T>) takeLastTimedSubscriber);
    }
}
