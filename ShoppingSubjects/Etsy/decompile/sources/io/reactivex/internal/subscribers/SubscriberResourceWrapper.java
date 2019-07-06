package io.reactivex.internal.subscribers;

import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;
import org.reactivestreams.c;

public final class SubscriberResourceWrapper<T> extends AtomicReference<Disposable> implements Disposable, j<T>, Subscription {
    private static final long serialVersionUID = -8612022020200669122L;
    final c<? super T> actual;
    final AtomicReference<Subscription> subscription = new AtomicReference<>();

    public SubscriberResourceWrapper(c<? super T> cVar) {
        this.actual = cVar;
    }

    public void onSubscribe(Subscription subscription2) {
        if (SubscriptionHelper.setOnce(this.subscription, subscription2)) {
            this.actual.onSubscribe(this);
        }
    }

    public void onNext(T t) {
        this.actual.onNext(t);
    }

    public void onError(Throwable th) {
        DisposableHelper.dispose(this);
        this.actual.onError(th);
    }

    public void onComplete() {
        DisposableHelper.dispose(this);
        this.actual.onComplete();
    }

    public void request(long j) {
        if (SubscriptionHelper.validate(j)) {
            ((Subscription) this.subscription.get()).request(j);
        }
    }

    public void dispose() {
        SubscriptionHelper.cancel(this.subscription);
        DisposableHelper.dispose(this);
    }

    public boolean isDisposed() {
        return this.subscription.get() == SubscriptionHelper.CANCELLED;
    }

    public void cancel() {
        dispose();
    }

    public void setResource(Disposable disposable) {
        DisposableHelper.set(this, disposable);
    }
}
