package io.reactivex.internal.operators.flowable;

import io.reactivex.g;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;
import org.reactivestreams.b;
import org.reactivestreams.c;

public final class FlowableAmb<T> extends g<T> {
    final b<? extends T>[] b;
    final Iterable<? extends b<? extends T>> c;

    static final class AmbInnerSubscriber<T> extends AtomicReference<Subscription> implements j<T>, Subscription {
        private static final long serialVersionUID = -1185974347409665484L;
        final c<? super T> actual;
        final int index;
        final AtomicLong missedRequested = new AtomicLong();
        final a<T> parent;
        boolean won;

        AmbInnerSubscriber(a<T> aVar, int i, c<? super T> cVar) {
            this.parent = aVar;
            this.index = i;
            this.actual = cVar;
        }

        public void onSubscribe(Subscription subscription) {
            SubscriptionHelper.deferredSetOnce(this, this.missedRequested, subscription);
        }

        public void request(long j) {
            SubscriptionHelper.deferredRequest(this, this.missedRequested, j);
        }

        public void onNext(T t) {
            if (this.won) {
                this.actual.onNext(t);
            } else if (this.parent.a(this.index)) {
                this.won = true;
                this.actual.onNext(t);
            } else {
                ((Subscription) get()).cancel();
            }
        }

        public void onError(Throwable th) {
            if (this.won) {
                this.actual.onError(th);
            } else if (this.parent.a(this.index)) {
                this.won = true;
                this.actual.onError(th);
            } else {
                ((Subscription) get()).cancel();
                io.reactivex.d.a.a(th);
            }
        }

        public void onComplete() {
            if (this.won) {
                this.actual.onComplete();
            } else if (this.parent.a(this.index)) {
                this.won = true;
                this.actual.onComplete();
            } else {
                ((Subscription) get()).cancel();
            }
        }

        public void cancel() {
            SubscriptionHelper.cancel(this);
        }
    }

    static final class a<T> implements Subscription {
        final c<? super T> a;
        final AmbInnerSubscriber<T>[] b;
        final AtomicInteger c = new AtomicInteger();

        a(c<? super T> cVar, int i) {
            this.a = cVar;
            this.b = new AmbInnerSubscriber[i];
        }

        public void a(b<? extends T>[] bVarArr) {
            AmbInnerSubscriber<T>[] ambInnerSubscriberArr = this.b;
            int length = ambInnerSubscriberArr.length;
            int i = 0;
            while (i < length) {
                int i2 = i + 1;
                ambInnerSubscriberArr[i] = new AmbInnerSubscriber<>(this, i2, this.a);
                i = i2;
            }
            this.c.lazySet(0);
            this.a.onSubscribe(this);
            for (int i3 = 0; i3 < length && this.c.get() == 0; i3++) {
                bVarArr[i3].subscribe(ambInnerSubscriberArr[i3]);
            }
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                int i = this.c.get();
                if (i > 0) {
                    this.b[i - 1].request(j);
                } else if (i == 0) {
                    for (AmbInnerSubscriber<T> request : this.b) {
                        request.request(j);
                    }
                }
            }
        }

        public boolean a(int i) {
            int i2 = 0;
            if (this.c.get() != 0 || !this.c.compareAndSet(0, i)) {
                return false;
            }
            AmbInnerSubscriber<T>[] ambInnerSubscriberArr = this.b;
            int length = ambInnerSubscriberArr.length;
            while (i2 < length) {
                int i3 = i2 + 1;
                if (i3 != i) {
                    ambInnerSubscriberArr[i2].cancel();
                }
                i2 = i3;
            }
            return true;
        }

        public void cancel() {
            if (this.c.get() != -1) {
                this.c.lazySet(-1);
                for (AmbInnerSubscriber<T> cancel : this.b) {
                    cancel.cancel();
                }
            }
        }
    }

    public void a(c<? super T> cVar) {
        int i;
        b<? extends T>[] bVarArr = this.b;
        if (bVarArr == null) {
            bVarArr = new b[8];
            try {
                i = 0;
                for (b<? extends T> bVar : this.c) {
                    if (bVar == null) {
                        EmptySubscription.error(new NullPointerException("One of the sources is null"), cVar);
                        return;
                    }
                    if (i == bVarArr.length) {
                        b<? extends T>[] bVarArr2 = new b[((i >> 2) + i)];
                        System.arraycopy(bVarArr, 0, bVarArr2, 0, i);
                        bVarArr = bVarArr2;
                    }
                    int i2 = i + 1;
                    bVarArr[i] = bVar;
                    i = i2;
                }
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                EmptySubscription.error(th, cVar);
                return;
            }
        } else {
            i = bVarArr.length;
        }
        if (i == 0) {
            EmptySubscription.complete(cVar);
        } else if (i == 1) {
            bVarArr[0].subscribe(cVar);
        } else {
            new a(cVar, i).a(bVarArr);
        }
    }
}
