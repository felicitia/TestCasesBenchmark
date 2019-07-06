package io.reactivex.internal.operators.flowable;

import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.a;
import io.reactivex.e;
import io.reactivex.functions.g;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.subscriptions.BasicIntQueueSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.j;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;
import org.reactivestreams.c;

public final class FlowableFlatMapCompletable<T> extends a<T, T> {
    final g<? super T, ? extends e> c;
    final int d;
    final boolean e;

    static final class FlatMapCompletableMainSubscriber<T> extends BasicIntQueueSubscription<T> implements j<T> {
        private static final long serialVersionUID = 8443155186132538303L;
        final c<? super T> actual;
        volatile boolean cancelled;
        final boolean delayErrors;
        final AtomicThrowable errors = new AtomicThrowable();
        final g<? super T, ? extends e> mapper;
        final int maxConcurrency;
        Subscription s;
        final a set = new a();

        final class InnerConsumer extends AtomicReference<Disposable> implements io.reactivex.c, Disposable {
            private static final long serialVersionUID = 8606673141535671828L;

            InnerConsumer() {
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

        public void clear() {
        }

        public boolean isEmpty() {
            return true;
        }

        public T poll() throws Exception {
            return null;
        }

        public void request(long j) {
        }

        public int requestFusion(int i) {
            return i & 2;
        }

        FlatMapCompletableMainSubscriber(c<? super T> cVar, g<? super T, ? extends e> gVar, boolean z, int i) {
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
                InnerConsumer innerConsumer = new InnerConsumer();
                if (!this.cancelled && this.set.a((Disposable) innerConsumer)) {
                    eVar.a(innerConsumer);
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
                cancel();
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

        public void cancel() {
            this.cancelled = true;
            this.s.cancel();
            this.set.dispose();
        }

        /* access modifiers changed from: 0000 */
        public void innerComplete(InnerConsumer innerConsumer) {
            this.set.c(innerConsumer);
            onComplete();
        }

        /* access modifiers changed from: 0000 */
        public void innerError(InnerConsumer innerConsumer, Throwable th) {
            this.set.c(innerConsumer);
            onError(th);
        }
    }

    /* access modifiers changed from: protected */
    public void a(c<? super T> cVar) {
        this.b.a((j<? super T>) new FlatMapCompletableMainSubscriber<Object>(cVar, this.c, this.e, this.d));
    }
}
