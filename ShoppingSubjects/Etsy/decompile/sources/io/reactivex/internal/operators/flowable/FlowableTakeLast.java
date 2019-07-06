package io.reactivex.internal.operators.flowable;

import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.b;
import io.reactivex.j;
import java.util.ArrayDeque;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.reactivestreams.Subscription;
import org.reactivestreams.c;

public final class FlowableTakeLast<T> extends a<T, T> {
    final int c;

    static final class TakeLastSubscriber<T> extends ArrayDeque<T> implements j<T>, Subscription {
        private static final long serialVersionUID = 7240042530241604978L;
        final c<? super T> actual;
        volatile boolean cancelled;
        final int count;
        volatile boolean done;
        final AtomicLong requested = new AtomicLong();
        Subscription s;
        final AtomicInteger wip = new AtomicInteger();

        TakeLastSubscriber(c<? super T> cVar, int i) {
            this.actual = cVar;
            this.count = i;
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.s, subscription)) {
                this.s = subscription;
                this.actual.onSubscribe(this);
                subscription.request(Long.MAX_VALUE);
            }
        }

        public void onNext(T t) {
            if (this.count == size()) {
                poll();
            }
            offer(t);
        }

        public void onError(Throwable th) {
            this.actual.onError(th);
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
            this.cancelled = true;
            this.s.cancel();
        }

        /* access modifiers changed from: 0000 */
        public void drain() {
            if (this.wip.getAndIncrement() == 0) {
                c<? super T> cVar = this.actual;
                long j = this.requested.get();
                while (!this.cancelled) {
                    if (this.done) {
                        long j2 = 0;
                        while (j2 != j) {
                            if (!this.cancelled) {
                                Object poll = poll();
                                if (poll == null) {
                                    cVar.onComplete();
                                    return;
                                } else {
                                    cVar.onNext(poll);
                                    j2++;
                                }
                            } else {
                                return;
                            }
                        }
                        if (!(j2 == 0 || j == Long.MAX_VALUE)) {
                            j = this.requested.addAndGet(-j2);
                        }
                    }
                    if (this.wip.decrementAndGet() == 0) {
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a(c<? super T> cVar) {
        this.b.a((j<? super T>) new TakeLastSubscriber<Object>(cVar, this.c));
    }
}
