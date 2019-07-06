package io.reactivex.internal.operators.flowable;

import io.reactivex.g;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import io.reactivex.u;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;
import org.reactivestreams.b;
import org.reactivestreams.c;

public final class FlowableSubscribeOn<T> extends a<T, T> {
    final u c;
    final boolean d;

    static final class SubscribeOnSubscriber<T> extends AtomicReference<Thread> implements j<T>, Runnable, Subscription {
        private static final long serialVersionUID = 8094547886072529208L;
        final c<? super T> actual;
        final boolean nonScheduledRequests;
        final AtomicLong requested = new AtomicLong();
        final AtomicReference<Subscription> s = new AtomicReference<>();
        b<T> source;
        final u.c worker;

        static final class a implements Runnable {
            private final Subscription a;
            private final long b;

            a(Subscription subscription, long j) {
                this.a = subscription;
                this.b = j;
            }

            public void run() {
                this.a.request(this.b);
            }
        }

        SubscribeOnSubscriber(c<? super T> cVar, u.c cVar2, b<T> bVar, boolean z) {
            this.actual = cVar;
            this.worker = cVar2;
            this.source = bVar;
            this.nonScheduledRequests = !z;
        }

        public void run() {
            lazySet(Thread.currentThread());
            b<T> bVar = this.source;
            this.source = null;
            bVar.subscribe(this);
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.setOnce(this.s, subscription)) {
                long andSet = this.requested.getAndSet(0);
                if (andSet != 0) {
                    requestUpstream(andSet, subscription);
                }
            }
        }

        public void onNext(T t) {
            this.actual.onNext(t);
        }

        public void onError(Throwable th) {
            this.actual.onError(th);
            this.worker.dispose();
        }

        public void onComplete() {
            this.actual.onComplete();
            this.worker.dispose();
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                Subscription subscription = (Subscription) this.s.get();
                if (subscription != null) {
                    requestUpstream(j, subscription);
                    return;
                }
                io.reactivex.internal.util.b.a(this.requested, j);
                Subscription subscription2 = (Subscription) this.s.get();
                if (subscription2 != null) {
                    long andSet = this.requested.getAndSet(0);
                    if (andSet != 0) {
                        requestUpstream(andSet, subscription2);
                    }
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void requestUpstream(long j, Subscription subscription) {
            if (this.nonScheduledRequests || Thread.currentThread() == get()) {
                subscription.request(j);
            } else {
                this.worker.a((Runnable) new a(subscription, j));
            }
        }

        public void cancel() {
            SubscriptionHelper.cancel(this.s);
            this.worker.dispose();
        }
    }

    public FlowableSubscribeOn(g<T> gVar, u uVar, boolean z) {
        super(gVar);
        this.c = uVar;
        this.d = z;
    }

    public void a(c<? super T> cVar) {
        u.c a = this.c.a();
        SubscribeOnSubscriber subscribeOnSubscriber = new SubscribeOnSubscriber(cVar, a, this.b, this.d);
        cVar.onSubscribe(subscribeOnSubscriber);
        a.a((Runnable) subscribeOnSubscriber);
    }
}
