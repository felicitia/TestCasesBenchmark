package io.reactivex.internal.subscribers;

import io.reactivex.internal.a.d;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import org.reactivestreams.Subscription;

/* compiled from: BasicFuseableConditionalSubscriber */
public abstract class a<T, R> implements io.reactivex.internal.a.a<T>, d<R> {
    protected final io.reactivex.internal.a.a<? super R> e;
    protected Subscription f;
    protected d<T> g;
    protected boolean h;
    protected int i;

    /* access modifiers changed from: protected */
    public boolean a() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void b() {
    }

    public a(io.reactivex.internal.a.a<? super R> aVar) {
        this.e = aVar;
    }

    public final void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.validate(this.f, subscription)) {
            this.f = subscription;
            if (subscription instanceof d) {
                this.g = (d) subscription;
            }
            if (a()) {
                this.e.onSubscribe(this);
                b();
            }
        }
    }

    public void onError(Throwable th) {
        if (this.h) {
            io.reactivex.d.a.a(th);
            return;
        }
        this.h = true;
        this.e.onError(th);
    }

    /* access modifiers changed from: protected */
    public final void a(Throwable th) {
        io.reactivex.exceptions.a.b(th);
        this.f.cancel();
        onError(th);
    }

    public void onComplete() {
        if (!this.h) {
            this.h = true;
            this.e.onComplete();
        }
    }

    /* access modifiers changed from: protected */
    public final int a(int i2) {
        d<T> dVar = this.g;
        if (dVar == null || (i2 & 4) != 0) {
            return 0;
        }
        int requestFusion = dVar.requestFusion(i2);
        if (requestFusion != 0) {
            this.i = requestFusion;
        }
        return requestFusion;
    }

    public void request(long j) {
        this.f.request(j);
    }

    public void cancel() {
        this.f.cancel();
    }

    public boolean isEmpty() {
        return this.g.isEmpty();
    }

    public void clear() {
        this.g.clear();
    }

    public final boolean offer(R r) {
        throw new UnsupportedOperationException("Should not be called!");
    }
}
