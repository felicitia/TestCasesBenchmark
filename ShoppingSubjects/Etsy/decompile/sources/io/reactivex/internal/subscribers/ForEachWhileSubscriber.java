package io.reactivex.internal.subscribers;

import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.a;
import io.reactivex.functions.i;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;

public final class ForEachWhileSubscriber<T> extends AtomicReference<Subscription> implements Disposable, j<T> {
    private static final long serialVersionUID = -4403180040475402120L;
    boolean done;
    final a onComplete;
    final Consumer<? super Throwable> onError;
    final i<? super T> onNext;

    public ForEachWhileSubscriber(i<? super T> iVar, Consumer<? super Throwable> consumer, a aVar) {
        this.onNext = iVar;
        this.onError = consumer;
        this.onComplete = aVar;
    }

    public void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.setOnce(this, subscription)) {
            subscription.request(Long.MAX_VALUE);
        }
    }

    public void onNext(T t) {
        if (!this.done) {
            try {
                if (!this.onNext.test(t)) {
                    dispose();
                    onComplete();
                }
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                dispose();
                onError(th);
            }
        }
    }

    public void onError(Throwable th) {
        if (this.done) {
            io.reactivex.d.a.a(th);
            return;
        }
        this.done = true;
        try {
            this.onError.accept(th);
        } catch (Throwable th2) {
            io.reactivex.exceptions.a.b(th2);
            io.reactivex.d.a.a((Throwable) new CompositeException(th, th2));
        }
    }

    public void onComplete() {
        if (!this.done) {
            this.done = true;
            try {
                this.onComplete.a();
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                io.reactivex.d.a.a(th);
            }
        }
    }

    public void dispose() {
        SubscriptionHelper.cancel(this);
    }

    public boolean isDisposed() {
        return SubscriptionHelper.isCancelled((Subscription) get());
    }
}
