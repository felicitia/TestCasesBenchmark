package io.reactivex.internal.operators.completable;

import io.reactivex.a;
import io.reactivex.c;
import io.reactivex.disposables.Disposable;
import io.reactivex.e;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.a.d;
import io.reactivex.internal.a.g;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;
import org.reactivestreams.b;

public final class CompletableConcat extends a {
    final b<? extends e> a;
    final int b;

    static final class CompletableConcatSubscriber extends AtomicInteger implements Disposable, j<e> {
        private static final long serialVersionUID = 9032184911934499404L;
        volatile boolean active;
        final c actual;
        int consumed;
        volatile boolean done;
        final ConcatInnerObserver inner = new ConcatInnerObserver(this);
        final int limit;
        final AtomicBoolean once = new AtomicBoolean();
        final int prefetch;
        g<e> queue;
        Subscription s;
        int sourceFused;

        static final class ConcatInnerObserver extends AtomicReference<Disposable> implements c {
            private static final long serialVersionUID = -5454794857847146511L;
            final CompletableConcatSubscriber parent;

            ConcatInnerObserver(CompletableConcatSubscriber completableConcatSubscriber) {
                this.parent = completableConcatSubscriber;
            }

            public void onSubscribe(Disposable disposable) {
                DisposableHelper.replace(this, disposable);
            }

            public void onError(Throwable th) {
                this.parent.innerError(th);
            }

            public void onComplete() {
                this.parent.innerComplete();
            }
        }

        CompletableConcatSubscriber(c cVar, int i) {
            this.actual = cVar;
            this.prefetch = i;
            this.limit = i - (i >> 2);
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.s, subscription)) {
                this.s = subscription;
                long j = this.prefetch == Integer.MAX_VALUE ? Long.MAX_VALUE : (long) this.prefetch;
                if (subscription instanceof d) {
                    d dVar = (d) subscription;
                    int requestFusion = dVar.requestFusion(3);
                    if (requestFusion == 1) {
                        this.sourceFused = requestFusion;
                        this.queue = dVar;
                        this.done = true;
                        this.actual.onSubscribe(this);
                        drain();
                        return;
                    } else if (requestFusion == 2) {
                        this.sourceFused = requestFusion;
                        this.queue = dVar;
                        this.actual.onSubscribe(this);
                        subscription.request(j);
                        return;
                    }
                }
                if (this.prefetch == Integer.MAX_VALUE) {
                    this.queue = new io.reactivex.internal.queue.a(io.reactivex.g.a());
                } else {
                    this.queue = new SpscArrayQueue(this.prefetch);
                }
                this.actual.onSubscribe(this);
                subscription.request(j);
            }
        }

        public void onNext(e eVar) {
            if (this.sourceFused != 0 || this.queue.offer(eVar)) {
                drain();
            } else {
                onError(new MissingBackpressureException());
            }
        }

        public void onError(Throwable th) {
            if (this.once.compareAndSet(false, true)) {
                DisposableHelper.dispose(this.inner);
                this.actual.onError(th);
                return;
            }
            io.reactivex.d.a.a(th);
        }

        public void onComplete() {
            this.done = true;
            drain();
        }

        public void dispose() {
            this.s.cancel();
            DisposableHelper.dispose(this.inner);
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((Disposable) this.inner.get());
        }

        /* access modifiers changed from: 0000 */
        public void drain() {
            if (getAndIncrement() == 0) {
                while (!isDisposed()) {
                    if (!this.active) {
                        boolean z = this.done;
                        try {
                            e eVar = (e) this.queue.poll();
                            boolean z2 = eVar == null;
                            if (z && z2) {
                                if (this.once.compareAndSet(false, true)) {
                                    this.actual.onComplete();
                                }
                                return;
                            } else if (!z2) {
                                this.active = true;
                                eVar.a(this.inner);
                                request();
                            }
                        } catch (Throwable th) {
                            io.reactivex.exceptions.a.b(th);
                            innerError(th);
                            return;
                        }
                    }
                    if (decrementAndGet() == 0) {
                        return;
                    }
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void request() {
            if (this.sourceFused != 1) {
                int i = this.consumed + 1;
                if (i == this.limit) {
                    this.consumed = 0;
                    this.s.request((long) i);
                    return;
                }
                this.consumed = i;
            }
        }

        /* access modifiers changed from: 0000 */
        public void innerError(Throwable th) {
            if (this.once.compareAndSet(false, true)) {
                this.s.cancel();
                this.actual.onError(th);
                return;
            }
            io.reactivex.d.a.a(th);
        }

        /* access modifiers changed from: 0000 */
        public void innerComplete() {
            this.active = false;
            drain();
        }
    }

    public void b(c cVar) {
        this.a.subscribe(new CompletableConcatSubscriber(cVar, this.b));
    }
}
