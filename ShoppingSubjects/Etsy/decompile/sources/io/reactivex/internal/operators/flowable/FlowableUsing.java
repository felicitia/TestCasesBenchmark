package io.reactivex.internal.operators.flowable;

import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.a;
import io.reactivex.functions.Consumer;
import io.reactivex.g;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import org.reactivestreams.Subscription;
import org.reactivestreams.b;
import org.reactivestreams.c;

public final class FlowableUsing<T, D> extends g<T> {
    final Callable<? extends D> b;
    final io.reactivex.functions.g<? super D, ? extends b<? extends T>> c;
    final Consumer<? super D> d;
    final boolean e;

    static final class UsingSubscriber<T, D> extends AtomicBoolean implements j<T>, Subscription {
        private static final long serialVersionUID = 5904473792286235046L;
        final c<? super T> actual;
        final Consumer<? super D> disposer;
        final boolean eager;
        final D resource;
        Subscription s;

        UsingSubscriber(c<? super T> cVar, D d, Consumer<? super D> consumer, boolean z) {
            this.actual = cVar;
            this.resource = d;
            this.disposer = consumer;
            this.eager = z;
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.s, subscription)) {
                this.s = subscription;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            this.actual.onNext(t);
        }

        public void onError(Throwable th) {
            if (this.eager) {
                Throwable th2 = null;
                if (compareAndSet(false, true)) {
                    try {
                        this.disposer.accept(this.resource);
                    } catch (Throwable th3) {
                        th2 = th3;
                        a.b(th2);
                    }
                }
                this.s.cancel();
                if (th2 != null) {
                    this.actual.onError(new CompositeException(th, th2));
                    return;
                }
                this.actual.onError(th);
                return;
            }
            this.actual.onError(th);
            this.s.cancel();
            disposeAfter();
        }

        public void onComplete() {
            if (this.eager) {
                if (compareAndSet(false, true)) {
                    try {
                        this.disposer.accept(this.resource);
                    } catch (Throwable th) {
                        a.b(th);
                        this.actual.onError(th);
                        return;
                    }
                }
                this.s.cancel();
                this.actual.onComplete();
            } else {
                this.actual.onComplete();
                this.s.cancel();
                disposeAfter();
            }
        }

        public void request(long j) {
            this.s.request(j);
        }

        public void cancel() {
            disposeAfter();
            this.s.cancel();
        }

        /* access modifiers changed from: 0000 */
        public void disposeAfter() {
            if (compareAndSet(false, true)) {
                try {
                    this.disposer.accept(this.resource);
                } catch (Throwable th) {
                    a.b(th);
                    io.reactivex.d.a.a(th);
                }
            }
        }
    }

    public void a(c<? super T> cVar) {
        try {
            Object call = this.b.call();
            try {
                ((b) io.reactivex.internal.functions.a.a(this.c.apply(call), "The sourceSupplier returned a null Publisher")).subscribe(new UsingSubscriber(cVar, call, this.d, this.e));
            } catch (Throwable th) {
                a.b(th);
                EmptySubscription.error(new CompositeException(th, th), cVar);
            }
        } catch (Throwable th2) {
            a.b(th2);
            EmptySubscription.error(th2, cVar);
        }
    }
}
