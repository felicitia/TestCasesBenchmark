package io.reactivex.internal.operators.flowable;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;
import org.reactivestreams.Subscription;
import org.reactivestreams.c;

public final class FlowableRefCount<T> extends a<T, T> {
    final io.reactivex.b.a<T> c;
    volatile io.reactivex.disposables.a d;
    final AtomicInteger e;
    final ReentrantLock f;

    final class ConnectionSubscriber extends AtomicReference<Subscription> implements j<T>, Subscription {
        private static final long serialVersionUID = 152064694420235350L;
        final io.reactivex.disposables.a currentBase;
        final AtomicLong requested = new AtomicLong();
        final Disposable resource;
        final c<? super T> subscriber;

        ConnectionSubscriber(c<? super T> cVar, io.reactivex.disposables.a aVar, Disposable disposable) {
            this.subscriber = cVar;
            this.currentBase = aVar;
            this.resource = disposable;
        }

        public void onSubscribe(Subscription subscription) {
            SubscriptionHelper.deferredSetOnce(this, this.requested, subscription);
        }

        public void onError(Throwable th) {
            cleanup();
            this.subscriber.onError(th);
        }

        public void onNext(T t) {
            this.subscriber.onNext(t);
        }

        public void onComplete() {
            cleanup();
            this.subscriber.onComplete();
        }

        public void request(long j) {
            SubscriptionHelper.deferredRequest(this, this.requested, j);
        }

        public void cancel() {
            SubscriptionHelper.cancel(this);
            this.resource.dispose();
        }

        /* access modifiers changed from: 0000 */
        public void cleanup() {
            FlowableRefCount.this.f.lock();
            try {
                if (FlowableRefCount.this.d == this.currentBase) {
                    if (FlowableRefCount.this.c instanceof Disposable) {
                        ((Disposable) FlowableRefCount.this.c).dispose();
                    }
                    FlowableRefCount.this.d.dispose();
                    FlowableRefCount.this.d = new io.reactivex.disposables.a();
                    FlowableRefCount.this.e.set(0);
                }
            } finally {
                FlowableRefCount.this.f.unlock();
            }
        }
    }

    final class a implements Consumer<Disposable> {
        private final c<? super T> b;
        private final AtomicBoolean c;

        a(c<? super T> cVar, AtomicBoolean atomicBoolean) {
            this.b = cVar;
            this.c = atomicBoolean;
        }

        /* renamed from: a */
        public void accept(Disposable disposable) {
            try {
                FlowableRefCount.this.d.a(disposable);
                FlowableRefCount.this.a(this.b, FlowableRefCount.this.d);
            } finally {
                FlowableRefCount.this.f.unlock();
                this.c.set(false);
            }
        }
    }

    final class b implements Runnable {
        private final io.reactivex.disposables.a b;

        b(io.reactivex.disposables.a aVar) {
            this.b = aVar;
        }

        public void run() {
            FlowableRefCount.this.f.lock();
            try {
                if (FlowableRefCount.this.d == this.b && FlowableRefCount.this.e.decrementAndGet() == 0) {
                    if (FlowableRefCount.this.c instanceof Disposable) {
                        ((Disposable) FlowableRefCount.this.c).dispose();
                    }
                    FlowableRefCount.this.d.dispose();
                    FlowableRefCount.this.d = new io.reactivex.disposables.a();
                }
            } finally {
                FlowableRefCount.this.f.unlock();
            }
        }
    }

    public void a(c<? super T> cVar) {
        this.f.lock();
        if (this.e.incrementAndGet() == 1) {
            AtomicBoolean atomicBoolean = new AtomicBoolean(true);
            try {
                this.c.a(a(cVar, atomicBoolean));
            } finally {
                if (atomicBoolean.get()) {
                    this.f.unlock();
                }
            }
        } else {
            try {
                a(cVar, this.d);
            } finally {
                this.f.unlock();
            }
        }
    }

    private Consumer<Disposable> a(c<? super T> cVar, AtomicBoolean atomicBoolean) {
        return new a(cVar, atomicBoolean);
    }

    /* access modifiers changed from: 0000 */
    public void a(c<? super T> cVar, io.reactivex.disposables.a aVar) {
        ConnectionSubscriber connectionSubscriber = new ConnectionSubscriber(cVar, aVar, a(aVar));
        cVar.onSubscribe(connectionSubscriber);
        this.c.a((j<? super T>) connectionSubscriber);
    }

    private Disposable a(io.reactivex.disposables.a aVar) {
        return io.reactivex.disposables.b.a(new b(aVar));
    }
}
