package io.reactivex.internal.operators.maybe;

import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import io.reactivex.m;
import io.reactivex.o;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;
import org.reactivestreams.b;

public final class MaybeDelaySubscriptionOtherPublisher<T, U> extends a<T, T> {
    final b<U> b;

    static final class DelayMaybeObserver<T> extends AtomicReference<Disposable> implements m<T> {
        private static final long serialVersionUID = 706635022205076709L;
        final m<? super T> actual;

        DelayMaybeObserver(m<? super T> mVar) {
            this.actual = mVar;
        }

        public void onSubscribe(Disposable disposable) {
            DisposableHelper.setOnce(this, disposable);
        }

        public void onSuccess(T t) {
            this.actual.onSuccess(t);
        }

        public void onError(Throwable th) {
            this.actual.onError(th);
        }

        public void onComplete() {
            this.actual.onComplete();
        }
    }

    static final class a<T> implements Disposable, j<Object> {
        final DelayMaybeObserver<T> a;
        o<T> b;
        Subscription c;

        a(m<? super T> mVar, o<T> oVar) {
            this.a = new DelayMaybeObserver<>(mVar);
            this.b = oVar;
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.c, subscription)) {
                this.c = subscription;
                this.a.actual.onSubscribe(this);
                subscription.request(Long.MAX_VALUE);
            }
        }

        public void onNext(Object obj) {
            if (this.c != SubscriptionHelper.CANCELLED) {
                this.c.cancel();
                this.c = SubscriptionHelper.CANCELLED;
                a();
            }
        }

        public void onError(Throwable th) {
            if (this.c != SubscriptionHelper.CANCELLED) {
                this.c = SubscriptionHelper.CANCELLED;
                this.a.actual.onError(th);
                return;
            }
            io.reactivex.d.a.a(th);
        }

        public void onComplete() {
            if (this.c != SubscriptionHelper.CANCELLED) {
                this.c = SubscriptionHelper.CANCELLED;
                a();
            }
        }

        /* access modifiers changed from: 0000 */
        public void a() {
            o<T> oVar = this.b;
            this.b = null;
            oVar.a(this.a);
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((Disposable) this.a.get());
        }

        public void dispose() {
            this.c.cancel();
            this.c = SubscriptionHelper.CANCELLED;
            DisposableHelper.dispose(this.a);
        }
    }

    /* access modifiers changed from: protected */
    public void b(m<? super T> mVar) {
        this.b.subscribe(new a(mVar, this.a));
    }
}
