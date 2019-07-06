package io.reactivex.internal.operators.flowable;

import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.functions.g;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;
import org.reactivestreams.b;
import org.reactivestreams.c;

public final class FlowableDebounce<T, U> extends a<T, T> {
    final g<? super T, ? extends b<U>> c;

    static final class DebounceSubscriber<T, U> extends AtomicLong implements j<T>, Subscription {
        private static final long serialVersionUID = 6725975399620862591L;
        final c<? super T> actual;
        final g<? super T, ? extends b<U>> debounceSelector;
        final AtomicReference<Disposable> debouncer = new AtomicReference<>();
        boolean done;
        volatile long index;
        Subscription s;

        static final class a<T, U> extends io.reactivex.subscribers.a<U> {
            final DebounceSubscriber<T, U> a;
            final long b;
            final T c;
            boolean d;
            final AtomicBoolean e = new AtomicBoolean();

            a(DebounceSubscriber<T, U> debounceSubscriber, long j, T t) {
                this.a = debounceSubscriber;
                this.b = j;
                this.c = t;
            }

            public void onNext(U u) {
                if (!this.d) {
                    this.d = true;
                    c();
                    a();
                }
            }

            /* access modifiers changed from: 0000 */
            public void a() {
                if (this.e.compareAndSet(false, true)) {
                    this.a.emit(this.b, this.c);
                }
            }

            public void onError(Throwable th) {
                if (this.d) {
                    io.reactivex.d.a.a(th);
                    return;
                }
                this.d = true;
                this.a.onError(th);
            }

            public void onComplete() {
                if (!this.d) {
                    this.d = true;
                    a();
                }
            }
        }

        DebounceSubscriber(c<? super T> cVar, g<? super T, ? extends b<U>> gVar) {
            this.actual = cVar;
            this.debounceSelector = gVar;
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
                Disposable disposable = (Disposable) this.debouncer.get();
                if (disposable != null) {
                    disposable.dispose();
                }
                try {
                    b bVar = (b) io.reactivex.internal.functions.a.a(this.debounceSelector.apply(t), "The publisher supplied is null");
                    a aVar = new a(this, j, t);
                    if (this.debouncer.compareAndSet(disposable, aVar)) {
                        bVar.subscribe(aVar);
                    }
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    cancel();
                    this.actual.onError(th);
                }
            }
        }

        public void onError(Throwable th) {
            DisposableHelper.dispose(this.debouncer);
            this.actual.onError(th);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                Disposable disposable = (Disposable) this.debouncer.get();
                if (!DisposableHelper.isDisposed(disposable)) {
                    ((a) disposable).a();
                    DisposableHelper.dispose(this.debouncer);
                    this.actual.onComplete();
                }
            }
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                io.reactivex.internal.util.b.a((AtomicLong) this, j);
            }
        }

        public void cancel() {
            this.s.cancel();
            DisposableHelper.dispose(this.debouncer);
        }

        /* access modifiers changed from: 0000 */
        public void emit(long j, T t) {
            if (j != this.index) {
                return;
            }
            if (get() != 0) {
                this.actual.onNext(t);
                io.reactivex.internal.util.b.c(this, 1);
                return;
            }
            cancel();
            this.actual.onError(new MissingBackpressureException("Could not deliver value due to lack of requests"));
        }
    }

    /* access modifiers changed from: protected */
    public void a(c<? super T> cVar) {
        this.b.a((j<? super T>) new DebounceSubscriber<Object>(new io.reactivex.subscribers.b(cVar), this.c));
    }
}
