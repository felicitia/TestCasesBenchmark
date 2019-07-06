package io.reactivex.internal.operators.single;

import io.reactivex.d.a;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import io.reactivex.v;
import io.reactivex.x;
import io.reactivex.z;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;
import org.reactivestreams.b;

public final class SingleTakeUntil<T, U> extends v<T> {
    final z<T> a;
    final b<U> b;

    static final class TakeUntilMainObserver<T> extends AtomicReference<Disposable> implements Disposable, x<T> {
        private static final long serialVersionUID = -622603812305745221L;
        final x<? super T> actual;
        final TakeUntilOtherSubscriber other = new TakeUntilOtherSubscriber(this);

        TakeUntilMainObserver(x<? super T> xVar) {
            this.actual = xVar;
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((Disposable) get());
        }

        public void onSubscribe(Disposable disposable) {
            DisposableHelper.setOnce(this, disposable);
        }

        public void onSuccess(T t) {
            this.other.dispose();
            if (((Disposable) get()) != DisposableHelper.DISPOSED && ((Disposable) getAndSet(DisposableHelper.DISPOSED)) != DisposableHelper.DISPOSED) {
                this.actual.onSuccess(t);
            }
        }

        public void onError(Throwable th) {
            this.other.dispose();
            if (((Disposable) get()) == DisposableHelper.DISPOSED || ((Disposable) getAndSet(DisposableHelper.DISPOSED)) == DisposableHelper.DISPOSED) {
                a.a(th);
            } else {
                this.actual.onError(th);
            }
        }

        /* access modifiers changed from: 0000 */
        public void otherError(Throwable th) {
            if (((Disposable) get()) != DisposableHelper.DISPOSED) {
                Disposable disposable = (Disposable) getAndSet(DisposableHelper.DISPOSED);
                if (disposable != DisposableHelper.DISPOSED) {
                    if (disposable != null) {
                        disposable.dispose();
                    }
                    this.actual.onError(th);
                    return;
                }
            }
            a.a(th);
        }
    }

    static final class TakeUntilOtherSubscriber extends AtomicReference<Subscription> implements j<Object> {
        private static final long serialVersionUID = 5170026210238877381L;
        final TakeUntilMainObserver<?> parent;

        TakeUntilOtherSubscriber(TakeUntilMainObserver<?> takeUntilMainObserver) {
            this.parent = takeUntilMainObserver;
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.setOnce(this, subscription)) {
                subscription.request(Long.MAX_VALUE);
            }
        }

        public void onNext(Object obj) {
            if (SubscriptionHelper.cancel(this)) {
                this.parent.otherError(new CancellationException());
            }
        }

        public void onError(Throwable th) {
            this.parent.otherError(th);
        }

        public void onComplete() {
            if (get() != SubscriptionHelper.CANCELLED) {
                lazySet(SubscriptionHelper.CANCELLED);
                this.parent.otherError(new CancellationException());
            }
        }

        public void dispose() {
            SubscriptionHelper.cancel(this);
        }
    }

    /* access modifiers changed from: protected */
    public void b(x<? super T> xVar) {
        TakeUntilMainObserver takeUntilMainObserver = new TakeUntilMainObserver(xVar);
        xVar.onSubscribe(takeUntilMainObserver);
        this.b.subscribe(takeUntilMainObserver.other);
        this.a.a(takeUntilMainObserver);
    }
}
