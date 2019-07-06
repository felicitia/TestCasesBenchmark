package io.reactivex.internal.operators.completable;

import io.reactivex.a;
import io.reactivex.c;
import io.reactivex.disposables.Disposable;
import io.reactivex.e;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.j;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;
import org.reactivestreams.b;

public final class CompletableMerge extends a {
    final b<? extends e> a;
    final int b;
    final boolean c;

    static final class CompletableMergeSubscriber extends AtomicInteger implements Disposable, j<e> {
        private static final long serialVersionUID = -2108443387387077490L;
        final c actual;
        final boolean delayErrors;
        final AtomicThrowable error = new AtomicThrowable();
        final int maxConcurrency;
        Subscription s;
        final io.reactivex.disposables.a set = new io.reactivex.disposables.a();

        final class MergeInnerObserver extends AtomicReference<Disposable> implements c, Disposable {
            private static final long serialVersionUID = 251330541679988317L;

            MergeInnerObserver() {
            }

            public void onSubscribe(Disposable disposable) {
                DisposableHelper.setOnce(this, disposable);
            }

            public void onError(Throwable th) {
                CompletableMergeSubscriber.this.innerError(this, th);
            }

            public void onComplete() {
                CompletableMergeSubscriber.this.innerComplete(this);
            }

            public boolean isDisposed() {
                return DisposableHelper.isDisposed((Disposable) get());
            }

            public void dispose() {
                DisposableHelper.dispose(this);
            }
        }

        CompletableMergeSubscriber(c cVar, int i, boolean z) {
            this.actual = cVar;
            this.maxConcurrency = i;
            this.delayErrors = z;
            lazySet(1);
        }

        public void dispose() {
            this.s.cancel();
            this.set.dispose();
        }

        public boolean isDisposed() {
            return this.set.isDisposed();
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.s, subscription)) {
                this.s = subscription;
                this.actual.onSubscribe(this);
                if (this.maxConcurrency == Integer.MAX_VALUE) {
                    subscription.request(Long.MAX_VALUE);
                } else {
                    subscription.request((long) this.maxConcurrency);
                }
            }
        }

        public void onNext(e eVar) {
            getAndIncrement();
            MergeInnerObserver mergeInnerObserver = new MergeInnerObserver();
            this.set.a((Disposable) mergeInnerObserver);
            eVar.a(mergeInnerObserver);
        }

        public void onError(Throwable th) {
            if (!this.delayErrors) {
                this.set.dispose();
                if (!this.error.addThrowable(th)) {
                    io.reactivex.d.a.a(th);
                } else if (getAndSet(0) > 0) {
                    this.actual.onError(this.error.terminate());
                }
            } else if (!this.error.addThrowable(th)) {
                io.reactivex.d.a.a(th);
            } else if (decrementAndGet() == 0) {
                this.actual.onError(this.error.terminate());
            }
        }

        public void onComplete() {
            if (decrementAndGet() != 0) {
                return;
            }
            if (((Throwable) this.error.get()) != null) {
                this.actual.onError(this.error.terminate());
            } else {
                this.actual.onComplete();
            }
        }

        /* access modifiers changed from: 0000 */
        public void innerError(MergeInnerObserver mergeInnerObserver, Throwable th) {
            this.set.c(mergeInnerObserver);
            if (!this.delayErrors) {
                this.s.cancel();
                this.set.dispose();
                if (!this.error.addThrowable(th)) {
                    io.reactivex.d.a.a(th);
                } else if (getAndSet(0) > 0) {
                    this.actual.onError(this.error.terminate());
                }
            } else if (!this.error.addThrowable(th)) {
                io.reactivex.d.a.a(th);
            } else if (decrementAndGet() == 0) {
                this.actual.onError(this.error.terminate());
            } else if (this.maxConcurrency != Integer.MAX_VALUE) {
                this.s.request(1);
            }
        }

        /* access modifiers changed from: 0000 */
        public void innerComplete(MergeInnerObserver mergeInnerObserver) {
            this.set.c(mergeInnerObserver);
            if (decrementAndGet() == 0) {
                Throwable th = (Throwable) this.error.get();
                if (th != null) {
                    this.actual.onError(th);
                } else {
                    this.actual.onComplete();
                }
            } else if (this.maxConcurrency != Integer.MAX_VALUE) {
                this.s.request(1);
            }
        }
    }

    public void b(c cVar) {
        this.a.subscribe(new CompletableMergeSubscriber(cVar, this.b, this.c));
    }
}
