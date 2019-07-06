package io.reactivex.internal.operators.flowable;

import io.reactivex.a;
import io.reactivex.c;
import io.reactivex.disposables.Disposable;
import io.reactivex.e;
import io.reactivex.g;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.j;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;

public final class FlowableFlatMapCompletableCompletable<T> extends a {
    final g<T> a;
    final io.reactivex.functions.g<? super T, ? extends e> b;
    final int c;
    final boolean d;

    static final class FlatMapCompletableMainSubscriber<T> extends AtomicInteger implements Disposable, j<T> {
        private static final long serialVersionUID = 8443155186132538303L;
        final c actual;
        final boolean delayErrors;
        volatile boolean disposed;
        final AtomicThrowable errors = new AtomicThrowable();
        final io.reactivex.functions.g<? super T, ? extends e> mapper;
        final int maxConcurrency;
        Subscription s;
        final io.reactivex.disposables.a set = new io.reactivex.disposables.a();

        final class InnerObserver extends AtomicReference<Disposable> implements c, Disposable {
            private static final long serialVersionUID = 8606673141535671828L;

            InnerObserver() {
            }

            public void onSubscribe(Disposable disposable) {
                DisposableHelper.setOnce(this, disposable);
            }

            public void onComplete() {
                FlatMapCompletableMainSubscriber.this.innerComplete(this);
            }

            public void onError(Throwable th) {
                FlatMapCompletableMainSubscriber.this.innerError(this, th);
            }

            public void dispose() {
                DisposableHelper.dispose(this);
            }

            public boolean isDisposed() {
                return DisposableHelper.isDisposed((Disposable) get());
            }
        }

        FlatMapCompletableMainSubscriber(c cVar, io.reactivex.functions.g<? super T, ? extends e> gVar, boolean z, int i) {
            this.actual = cVar;
            this.mapper = gVar;
            this.delayErrors = z;
            this.maxConcurrency = i;
            lazySet(1);
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.s, subscription)) {
                this.s = subscription;
                this.actual.onSubscribe(this);
                int i = this.maxConcurrency;
                if (i == Integer.MAX_VALUE) {
                    subscription.request(Long.MAX_VALUE);
                } else {
                    subscription.request((long) i);
                }
            }
        }

        public void onNext(T t) {
            try {
                e eVar = (e) io.reactivex.internal.functions.a.a(this.mapper.apply(t), "The mapper returned a null CompletableSource");
                getAndIncrement();
                InnerObserver innerObserver = new InnerObserver();
                if (!this.disposed && this.set.a((Disposable) innerObserver)) {
                    eVar.a(innerObserver);
                }
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                this.s.cancel();
                onError(th);
            }
        }

        public void onError(Throwable th) {
            if (!this.errors.addThrowable(th)) {
                io.reactivex.d.a.a(th);
            } else if (!this.delayErrors) {
                dispose();
                if (getAndSet(0) > 0) {
                    this.actual.onError(this.errors.terminate());
                }
            } else if (decrementAndGet() == 0) {
                this.actual.onError(this.errors.terminate());
            } else if (this.maxConcurrency != Integer.MAX_VALUE) {
                this.s.request(1);
            }
        }

        public void onComplete() {
            if (decrementAndGet() == 0) {
                Throwable terminate = this.errors.terminate();
                if (terminate != null) {
                    this.actual.onError(terminate);
                } else {
                    this.actual.onComplete();
                }
            } else if (this.maxConcurrency != Integer.MAX_VALUE) {
                this.s.request(1);
            }
        }

        public void dispose() {
            this.disposed = true;
            this.s.cancel();
            this.set.dispose();
        }

        public boolean isDisposed() {
            return this.set.isDisposed();
        }

        /* access modifiers changed from: 0000 */
        public void innerComplete(InnerObserver innerObserver) {
            this.set.c(innerObserver);
            onComplete();
        }

        /* access modifiers changed from: 0000 */
        public void innerError(InnerObserver innerObserver, Throwable th) {
            this.set.c(innerObserver);
            onError(th);
        }
    }

    /* access modifiers changed from: protected */
    public void b(c cVar) {
        this.a.a((j<? super T>) new FlatMapCompletableMainSubscriber<Object>(cVar, this.b, this.d, this.c));
    }
}
