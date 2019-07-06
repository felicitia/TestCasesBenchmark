package io.reactivex.internal.operators.flowable;

import io.reactivex.exceptions.a;
import io.reactivex.functions.Consumer;
import io.reactivex.g;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.b;
import io.reactivex.j;
import java.util.concurrent.atomic.AtomicLong;
import org.reactivestreams.Subscription;
import org.reactivestreams.c;

public final class FlowableOnBackpressureDrop<T> extends a<T, T> implements Consumer<T> {
    final Consumer<? super T> c = this;

    static final class BackpressureDropSubscriber<T> extends AtomicLong implements j<T>, Subscription {
        private static final long serialVersionUID = -6246093802440953054L;
        final c<? super T> actual;
        boolean done;
        final Consumer<? super T> onDrop;
        Subscription s;

        BackpressureDropSubscriber(c<? super T> cVar, Consumer<? super T> consumer) {
            this.actual = cVar;
            this.onDrop = consumer;
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.s, subscription)) {
                this.s = subscription;
                this.actual.onSubscribe(this);
                subscription.request(Long.MAX_VALUE);
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                if (get() != 0) {
                    this.actual.onNext(t);
                    b.c(this, 1);
                } else {
                    try {
                        this.onDrop.accept(t);
                    } catch (Throwable th) {
                        a.b(th);
                        cancel();
                        onError(th);
                    }
                }
            }
        }

        public void onError(Throwable th) {
            if (this.done) {
                io.reactivex.d.a.a(th);
                return;
            }
            this.done = true;
            this.actual.onError(th);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.actual.onComplete();
            }
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                b.a((AtomicLong) this, j);
            }
        }

        public void cancel() {
            this.s.cancel();
        }
    }

    public void accept(T t) {
    }

    public FlowableOnBackpressureDrop(g<T> gVar) {
        super(gVar);
    }

    /* access modifiers changed from: protected */
    public void a(c<? super T> cVar) {
        this.b.a((j<? super T>) new BackpressureDropSubscriber<Object>(cVar, this.c));
    }
}
