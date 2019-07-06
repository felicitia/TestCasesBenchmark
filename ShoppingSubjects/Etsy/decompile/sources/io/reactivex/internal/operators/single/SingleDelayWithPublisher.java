package io.reactivex.internal.operators.single;

import io.reactivex.d.a;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.observers.e;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import io.reactivex.v;
import io.reactivex.x;
import io.reactivex.z;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;
import org.reactivestreams.b;

public final class SingleDelayWithPublisher<T, U> extends v<T> {
    final z<T> a;
    final b<U> b;

    static final class OtherSubscriber<T, U> extends AtomicReference<Disposable> implements Disposable, j<U> {
        private static final long serialVersionUID = -8565274649390031272L;
        final x<? super T> actual;
        boolean done;
        Subscription s;
        final z<T> source;

        OtherSubscriber(x<? super T> xVar, z<T> zVar) {
            this.actual = xVar;
            this.source = zVar;
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.s, subscription)) {
                this.s = subscription;
                this.actual.onSubscribe(this);
                subscription.request(Long.MAX_VALUE);
            }
        }

        public void onNext(U u) {
            this.s.cancel();
            onComplete();
        }

        public void onError(Throwable th) {
            if (this.done) {
                a.a(th);
                return;
            }
            this.done = true;
            this.actual.onError(th);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.source.a(new e(this, this.actual));
            }
        }

        public void dispose() {
            this.s.cancel();
            DisposableHelper.dispose(this);
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((Disposable) get());
        }
    }

    /* access modifiers changed from: protected */
    public void b(x<? super T> xVar) {
        this.b.subscribe(new OtherSubscriber(xVar, this.a));
    }
}
