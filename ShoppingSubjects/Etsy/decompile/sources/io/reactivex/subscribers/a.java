package io.reactivex.subscribers;

import io.reactivex.disposables.Disposable;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.d;
import io.reactivex.j;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;

/* compiled from: DisposableSubscriber */
public abstract class a<T> implements Disposable, j<T> {
    final AtomicReference<Subscription> f = new AtomicReference<>();

    public final void onSubscribe(Subscription subscription) {
        if (d.a(this.f, subscription, getClass())) {
            b();
        }
    }

    /* access modifiers changed from: protected */
    public void b() {
        ((Subscription) this.f.get()).request(Long.MAX_VALUE);
    }

    /* access modifiers changed from: protected */
    public final void c() {
        dispose();
    }

    public final boolean isDisposed() {
        return this.f.get() == SubscriptionHelper.CANCELLED;
    }

    public final void dispose() {
        SubscriptionHelper.cancel(this.f);
    }
}
