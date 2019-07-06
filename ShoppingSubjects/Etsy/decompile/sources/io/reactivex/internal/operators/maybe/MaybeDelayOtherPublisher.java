package io.reactivex.internal.operators.maybe;

import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import io.reactivex.m;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;
import org.reactivestreams.b;

public final class MaybeDelayOtherPublisher<T, U> extends a<T, T> {
    final b<U> b;

    static final class OtherSubscriber<T> extends AtomicReference<Subscription> implements j<Object> {
        private static final long serialVersionUID = -1215060610805418006L;
        final m<? super T> actual;
        Throwable error;
        T value;

        OtherSubscriber(m<? super T> mVar) {
            this.actual = mVar;
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.setOnce(this, subscription)) {
                subscription.request(Long.MAX_VALUE);
            }
        }

        public void onNext(Object obj) {
            Subscription subscription = (Subscription) get();
            if (subscription != SubscriptionHelper.CANCELLED) {
                lazySet(SubscriptionHelper.CANCELLED);
                subscription.cancel();
                onComplete();
            }
        }

        public void onError(Throwable th) {
            Throwable th2 = this.error;
            if (th2 == null) {
                this.actual.onError(th);
                return;
            }
            this.actual.onError(new CompositeException(th2, th));
        }

        public void onComplete() {
            Throwable th = this.error;
            if (th != null) {
                this.actual.onError(th);
                return;
            }
            T t = this.value;
            if (t != null) {
                this.actual.onSuccess(t);
            } else {
                this.actual.onComplete();
            }
        }
    }

    static final class a<T, U> implements Disposable, m<T> {
        final OtherSubscriber<T> a;
        final b<U> b;
        Disposable c;

        a(m<? super T> mVar, b<U> bVar) {
            this.a = new OtherSubscriber<>(mVar);
            this.b = bVar;
        }

        public void dispose() {
            this.c.dispose();
            this.c = DisposableHelper.DISPOSED;
            SubscriptionHelper.cancel(this.a);
        }

        public boolean isDisposed() {
            return SubscriptionHelper.isCancelled((Subscription) this.a.get());
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.c, disposable)) {
                this.c = disposable;
                this.a.actual.onSubscribe(this);
            }
        }

        public void onSuccess(T t) {
            this.c = DisposableHelper.DISPOSED;
            this.a.value = t;
            a();
        }

        public void onError(Throwable th) {
            this.c = DisposableHelper.DISPOSED;
            this.a.error = th;
            a();
        }

        public void onComplete() {
            this.c = DisposableHelper.DISPOSED;
            a();
        }

        /* access modifiers changed from: 0000 */
        public void a() {
            this.b.subscribe(this.a);
        }
    }

    /* access modifiers changed from: protected */
    public void b(m<? super T> mVar) {
        this.a.a(new a(mVar, this.b));
    }
}
