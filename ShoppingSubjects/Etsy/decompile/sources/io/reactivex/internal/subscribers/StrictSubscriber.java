package io.reactivex.internal.subscribers;

import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.e;
import io.reactivex.j;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;
import org.reactivestreams.c;

public class StrictSubscriber<T> extends AtomicInteger implements j<T>, Subscription {
    private static final long serialVersionUID = -4945028590049415624L;
    final c<? super T> actual;
    volatile boolean done;
    final AtomicThrowable error = new AtomicThrowable();
    final AtomicBoolean once = new AtomicBoolean();
    final AtomicLong requested = new AtomicLong();
    final AtomicReference<Subscription> s = new AtomicReference<>();

    public StrictSubscriber(c<? super T> cVar) {
        this.actual = cVar;
    }

    public void request(long j) {
        if (j <= 0) {
            cancel();
            StringBuilder sb = new StringBuilder();
            sb.append("§3.9 violated: positive request amount required but it was ");
            sb.append(j);
            onError(new IllegalArgumentException(sb.toString()));
            return;
        }
        SubscriptionHelper.deferredRequest(this.s, this.requested, j);
    }

    public void cancel() {
        if (!this.done) {
            SubscriptionHelper.cancel(this.s);
        }
    }

    public void onSubscribe(Subscription subscription) {
        if (this.once.compareAndSet(false, true)) {
            this.actual.onSubscribe(this);
            SubscriptionHelper.deferredSetOnce(this.s, this.requested, subscription);
            return;
        }
        subscription.cancel();
        cancel();
        onError(new IllegalStateException("§2.12 violated: onSubscribe must be called at most once"));
    }

    public void onNext(T t) {
        e.a(this.actual, t, (AtomicInteger) this, this.error);
    }

    public void onError(Throwable th) {
        this.done = true;
        e.a(this.actual, th, (AtomicInteger) this, this.error);
    }

    public void onComplete() {
        this.done = true;
        e.a(this.actual, (AtomicInteger) this, this.error);
    }
}
