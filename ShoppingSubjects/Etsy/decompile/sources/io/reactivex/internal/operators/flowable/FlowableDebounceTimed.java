package io.reactivex.internal.operators.flowable;

import io.reactivex.d.a;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.g;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.b;
import io.reactivex.j;
import io.reactivex.u;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;
import org.reactivestreams.c;

public final class FlowableDebounceTimed<T> extends a<T, T> {
    final long c;
    final TimeUnit d;
    final u e;

    static final class DebounceEmitter<T> extends AtomicReference<Disposable> implements Disposable, Runnable {
        private static final long serialVersionUID = 6812032969491025141L;
        final long idx;
        final AtomicBoolean once = new AtomicBoolean();
        final DebounceTimedSubscriber<T> parent;
        final T value;

        DebounceEmitter(T t, long j, DebounceTimedSubscriber<T> debounceTimedSubscriber) {
            this.value = t;
            this.idx = j;
            this.parent = debounceTimedSubscriber;
        }

        public void run() {
            emit();
        }

        /* access modifiers changed from: 0000 */
        public void emit() {
            if (this.once.compareAndSet(false, true)) {
                this.parent.emit(this.idx, this.value, this);
            }
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }

        public boolean isDisposed() {
            return get() == DisposableHelper.DISPOSED;
        }

        public void setResource(Disposable disposable) {
            DisposableHelper.replace(this, disposable);
        }
    }

    static final class DebounceTimedSubscriber<T> extends AtomicLong implements j<T>, Subscription {
        private static final long serialVersionUID = -9102637559663639004L;
        final c<? super T> actual;
        boolean done;
        volatile long index;
        Subscription s;
        final long timeout;
        final SequentialDisposable timer = new SequentialDisposable();
        final TimeUnit unit;
        final u.c worker;

        DebounceTimedSubscriber(c<? super T> cVar, long j, TimeUnit timeUnit, u.c cVar2) {
            this.actual = cVar;
            this.timeout = j;
            this.unit = timeUnit;
            this.worker = cVar2;
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
                long j = this.index + 1;
                this.index = j;
                Disposable disposable = (Disposable) this.timer.get();
                if (disposable != null) {
                    disposable.dispose();
                }
                DebounceEmitter debounceEmitter = new DebounceEmitter(t, j, this);
                if (this.timer.replace(debounceEmitter)) {
                    debounceEmitter.setResource(this.worker.a(debounceEmitter, this.timeout, this.unit));
                }
            }
        }

        public void onError(Throwable th) {
            if (this.done) {
                a.a(th);
                return;
            }
            this.done = true;
            this.actual.onError(th);
            this.worker.dispose();
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                Disposable disposable = (Disposable) this.timer.get();
                if (!DisposableHelper.isDisposed(disposable)) {
                    DebounceEmitter debounceEmitter = (DebounceEmitter) disposable;
                    if (debounceEmitter != null) {
                        debounceEmitter.emit();
                    }
                    DisposableHelper.dispose(this.timer);
                    this.actual.onComplete();
                    this.worker.dispose();
                }
            }
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                b.a((AtomicLong) this, j);
            }
        }

        public void cancel() {
            this.s.cancel();
            this.worker.dispose();
        }

        /* access modifiers changed from: 0000 */
        public void emit(long j, T t, DebounceEmitter<T> debounceEmitter) {
            if (j != this.index) {
                return;
            }
            if (get() != 0) {
                this.actual.onNext(t);
                b.c(this, 1);
                debounceEmitter.dispose();
                return;
            }
            cancel();
            this.actual.onError(new MissingBackpressureException("Could not deliver value due to lack of requests"));
        }
    }

    /* access modifiers changed from: protected */
    public void a(c<? super T> cVar) {
        g gVar = this.b;
        DebounceTimedSubscriber debounceTimedSubscriber = new DebounceTimedSubscriber(new io.reactivex.subscribers.b(cVar), this.c, this.d, this.e.a());
        gVar.a((j<? super T>) debounceTimedSubscriber);
    }
}
