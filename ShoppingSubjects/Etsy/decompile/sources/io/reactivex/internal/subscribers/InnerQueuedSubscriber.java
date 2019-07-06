package io.reactivex.internal.subscribers;

import io.reactivex.internal.a.d;
import io.reactivex.internal.a.g;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.i;
import io.reactivex.j;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;

public final class InnerQueuedSubscriber<T> extends AtomicReference<Subscription> implements j<T>, Subscription {
    private static final long serialVersionUID = 22876611072430776L;
    volatile boolean done;
    int fusionMode;
    final int limit;
    final c<T> parent;
    final int prefetch;
    long produced;
    volatile g<T> queue;

    public InnerQueuedSubscriber(c<T> cVar, int i) {
        this.parent = cVar;
        this.prefetch = i;
        this.limit = i - (i >> 2);
    }

    public void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.setOnce(this, subscription)) {
            if (subscription instanceof d) {
                d dVar = (d) subscription;
                int requestFusion = dVar.requestFusion(3);
                if (requestFusion == 1) {
                    this.fusionMode = requestFusion;
                    this.queue = dVar;
                    this.done = true;
                    this.parent.innerComplete(this);
                    return;
                } else if (requestFusion == 2) {
                    this.fusionMode = requestFusion;
                    this.queue = dVar;
                    i.a(subscription, this.prefetch);
                    return;
                }
            }
            this.queue = i.a(this.prefetch);
            i.a(subscription, this.prefetch);
        }
    }

    public void onNext(T t) {
        if (this.fusionMode == 0) {
            this.parent.innerNext(this, t);
        } else {
            this.parent.drain();
        }
    }

    public void onError(Throwable th) {
        this.parent.innerError(this, th);
    }

    public void onComplete() {
        this.parent.innerComplete(this);
    }

    public void request(long j) {
        if (this.fusionMode != 1) {
            long j2 = this.produced + j;
            if (j2 >= ((long) this.limit)) {
                this.produced = 0;
                ((Subscription) get()).request(j2);
                return;
            }
            this.produced = j2;
        }
    }

    public void requestOne() {
        if (this.fusionMode != 1) {
            long j = this.produced + 1;
            if (j == ((long) this.limit)) {
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

    public boolean isDone() {
        return this.done;
    }

    public void setDone() {
        this.done = true;
    }

    public g<T> queue() {
        return this.queue;
    }
}
