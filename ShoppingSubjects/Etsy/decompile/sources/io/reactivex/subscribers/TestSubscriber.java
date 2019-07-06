package io.reactivex.subscribers;

import io.reactivex.disposables.Disposable;
import io.reactivex.internal.a.d;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import io.reactivex.observers.BaseTestConsumer;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;
import org.reactivestreams.c;

public class TestSubscriber<T> extends BaseTestConsumer<T, TestSubscriber<T>> implements Disposable, j<T>, Subscription {
    private final c<? super T> i;
    private volatile boolean j;
    private final AtomicReference<Subscription> k;
    private final AtomicLong l;
    private d<T> m;

    enum EmptySubscriber implements j<Object> {
        INSTANCE;

        public void onComplete() {
        }

        public void onError(Throwable th) {
        }

        public void onNext(Object obj) {
        }

        public void onSubscribe(Subscription subscription) {
        }
    }

    /* access modifiers changed from: protected */
    public void a() {
    }

    public void onSubscribe(Subscription subscription) {
        this.e = Thread.currentThread();
        if (subscription == null) {
            this.c.add(new NullPointerException("onSubscribe received a null Subscription"));
        } else if (!this.k.compareAndSet(null, subscription)) {
            subscription.cancel();
            if (this.k.get() != SubscriptionHelper.CANCELLED) {
                List list = this.c;
                StringBuilder sb = new StringBuilder();
                sb.append("onSubscribe received multiple subscriptions: ");
                sb.append(subscription);
                list.add(new IllegalStateException(sb.toString()));
            }
        } else {
            if (this.g != 0 && (subscription instanceof d)) {
                this.m = (d) subscription;
                int requestFusion = this.m.requestFusion(this.g);
                this.h = requestFusion;
                if (requestFusion == 1) {
                    this.f = true;
                    this.e = Thread.currentThread();
                    while (true) {
                        try {
                            Object poll = this.m.poll();
                            if (poll == null) {
                                break;
                            }
                            this.b.add(poll);
                        } catch (Throwable th) {
                            this.c.add(th);
                        }
                    }
                    this.d++;
                    return;
                }
            }
            this.i.onSubscribe(subscription);
            long andSet = this.l.getAndSet(0);
            if (andSet != 0) {
                subscription.request(andSet);
            }
            a();
        }
    }

    public void onNext(T t) {
        if (!this.f) {
            this.f = true;
            if (this.k.get() == null) {
                this.c.add(new IllegalStateException("onSubscribe not called in proper order"));
            }
        }
        this.e = Thread.currentThread();
        if (this.h == 2) {
            while (true) {
                try {
                    Object poll = this.m.poll();
                    if (poll == null) {
                        break;
                    }
                    this.b.add(poll);
                } catch (Throwable th) {
                    this.c.add(th);
                    this.m.cancel();
                }
            }
            return;
        }
        this.b.add(t);
        if (t == null) {
            this.c.add(new NullPointerException("onNext received a null value"));
        }
        this.i.onNext(t);
    }

    public void onError(Throwable th) {
        if (!this.f) {
            this.f = true;
            if (this.k.get() == null) {
                this.c.add(new NullPointerException("onSubscribe not called in proper order"));
            }
        }
        try {
            this.e = Thread.currentThread();
            this.c.add(th);
            if (th == null) {
                this.c.add(new IllegalStateException("onError received a null Throwable"));
            }
            this.i.onError(th);
        } finally {
            this.a.countDown();
        }
    }

    public void onComplete() {
        if (!this.f) {
            this.f = true;
            if (this.k.get() == null) {
                this.c.add(new IllegalStateException("onSubscribe not called in proper order"));
            }
        }
        try {
            this.e = Thread.currentThread();
            this.d++;
            this.i.onComplete();
        } finally {
            this.a.countDown();
        }
    }

    public final void request(long j2) {
        SubscriptionHelper.deferredRequest(this.k, this.l, j2);
    }

    public final void cancel() {
        if (!this.j) {
            this.j = true;
            SubscriptionHelper.cancel(this.k);
        }
    }

    public final void dispose() {
        cancel();
    }

    public final boolean isDisposed() {
        return this.j;
    }
}
