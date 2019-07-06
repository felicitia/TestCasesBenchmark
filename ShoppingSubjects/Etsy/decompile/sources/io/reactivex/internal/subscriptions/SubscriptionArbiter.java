package io.reactivex.internal.subscriptions;

import io.reactivex.internal.functions.a;
import io.reactivex.internal.util.b;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;

public class SubscriptionArbiter extends AtomicInteger implements Subscription {
    private static final long serialVersionUID = -2189523197179400958L;
    Subscription actual;
    volatile boolean cancelled;
    final AtomicLong missedProduced = new AtomicLong();
    final AtomicLong missedRequested = new AtomicLong();
    final AtomicReference<Subscription> missedSubscription = new AtomicReference<>();
    long requested;
    protected boolean unbounded;

    public final void setSubscription(Subscription subscription) {
        if (this.cancelled) {
            subscription.cancel();
            return;
        }
        a.a(subscription, "s is null");
        if (get() != 0 || !compareAndSet(0, 1)) {
            Subscription subscription2 = (Subscription) this.missedSubscription.getAndSet(subscription);
            if (subscription2 != null) {
                subscription2.cancel();
            }
            drain();
            return;
        }
        Subscription subscription3 = this.actual;
        if (subscription3 != null) {
            subscription3.cancel();
        }
        this.actual = subscription;
        long j = this.requested;
        if (decrementAndGet() != 0) {
            drainLoop();
        }
        if (j != 0) {
            subscription.request(j);
        }
    }

    public final void request(long j) {
        if (SubscriptionHelper.validate(j) && !this.unbounded) {
            if (get() != 0 || !compareAndSet(0, 1)) {
                b.a(this.missedRequested, j);
                drain();
                return;
            }
            long j2 = this.requested;
            if (j2 != Long.MAX_VALUE) {
                long a = b.a(j2, j);
                this.requested = a;
                if (a == Long.MAX_VALUE) {
                    this.unbounded = true;
                }
            }
            Subscription subscription = this.actual;
            if (decrementAndGet() != 0) {
                drainLoop();
            }
            if (subscription != null) {
                subscription.request(j);
            }
        }
    }

    public final void produced(long j) {
        if (!this.unbounded) {
            if (get() != 0 || !compareAndSet(0, 1)) {
                b.a(this.missedProduced, j);
                drain();
                return;
            }
            long j2 = this.requested;
            if (j2 != Long.MAX_VALUE) {
                long j3 = j2 - j;
                long j4 = 0;
                if (j3 < 0) {
                    SubscriptionHelper.reportMoreProduced(j3);
                } else {
                    j4 = j3;
                }
                this.requested = j4;
            }
            if (decrementAndGet() != 0) {
                drainLoop();
            }
        }
    }

    public void cancel() {
        if (!this.cancelled) {
            this.cancelled = true;
            drain();
        }
    }

    /* access modifiers changed from: 0000 */
    public final void drain() {
        if (getAndIncrement() == 0) {
            drainLoop();
        }
    }

    /* access modifiers changed from: 0000 */
    public final void drainLoop() {
        int i;
        Subscription subscription;
        int i2;
        Subscription subscription2 = null;
        int i3 = 1;
        long j = 0;
        do {
            Subscription subscription3 = (Subscription) this.missedSubscription.get();
            if (subscription3 != null) {
                subscription3 = (Subscription) this.missedSubscription.getAndSet(null);
            }
            long j2 = this.missedRequested.get();
            if (j2 != 0) {
                j2 = this.missedRequested.getAndSet(0);
            }
            long j3 = this.missedProduced.get();
            if (j3 != 0) {
                j3 = this.missedProduced.getAndSet(0);
            }
            Subscription subscription4 = this.actual;
            if (this.cancelled) {
                if (subscription4 != null) {
                    subscription4.cancel();
                    this.actual = null;
                }
                if (subscription3 != null) {
                    subscription3.cancel();
                }
                i2 = i3;
                subscription = subscription2;
            } else {
                long j4 = this.requested;
                if (j4 != Long.MAX_VALUE) {
                    j4 = b.a(j4, j2);
                    if (j4 != Long.MAX_VALUE) {
                        i2 = i3;
                        subscription = subscription2;
                        long j5 = j4 - j3;
                        if (j5 < 0) {
                            SubscriptionHelper.reportMoreProduced(j5);
                            j5 = 0;
                        }
                        j4 = j5;
                    } else {
                        i2 = i3;
                        subscription = subscription2;
                    }
                    this.requested = j4;
                } else {
                    i2 = i3;
                    subscription = subscription2;
                }
                if (subscription3 != null) {
                    if (subscription4 != null) {
                        subscription4.cancel();
                    }
                    this.actual = subscription3;
                    if (j4 != 0) {
                        j = b.a(j, j4);
                        subscription2 = subscription3;
                    }
                } else if (!(subscription4 == null || j2 == 0)) {
                    j = b.a(j, j2);
                    subscription2 = subscription4;
                }
                i = i2;
                i3 = addAndGet(-i);
            }
            i = i2;
            subscription2 = subscription;
            i3 = addAndGet(-i);
        } while (i3 != 0);
        if (j != 0) {
            subscription2.request(j);
        }
    }

    public final boolean isUnbounded() {
        return this.unbounded;
    }

    public final boolean isCancelled() {
        return this.cancelled;
    }
}
