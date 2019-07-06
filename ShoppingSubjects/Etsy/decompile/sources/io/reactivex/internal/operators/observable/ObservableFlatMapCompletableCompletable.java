package io.reactivex.internal.operators.observable;

import io.reactivex.Observer;
import io.reactivex.a;
import io.reactivex.c;
import io.reactivex.disposables.Disposable;
import io.reactivex.e;
import io.reactivex.functions.g;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.t;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableFlatMapCompletableCompletable<T> extends a {
    final t<T> a;
    final g<? super T, ? extends e> b;
    final boolean c;

    static final class FlatMapCompletableMainObserver<T> extends AtomicInteger implements Observer<T>, Disposable {
        private static final long serialVersionUID = 8443155186132538303L;
        final c actual;
        Disposable d;
        final boolean delayErrors;
        volatile boolean disposed;
        final AtomicThrowable errors = new AtomicThrowable();
        final g<? super T, ? extends e> mapper;
        final io.reactivex.disposables.a set = new io.reactivex.disposables.a();

        final class InnerObserver extends AtomicReference<Disposable> implements c, Disposable {
            private static final long serialVersionUID = 8606673141535671828L;

            InnerObserver() {
            }

            public void onSubscribe(Disposable disposable) {
                DisposableHelper.setOnce(this, disposable);
            }

            public void onComplete() {
                FlatMapCompletableMainObserver.this.innerComplete(this);
            }

            public void onError(Throwable th) {
                FlatMapCompletableMainObserver.this.innerError(this, th);
            }

            public void dispose() {
                DisposableHelper.dispose(this);
            }

            public boolean isDisposed() {
                return DisposableHelper.isDisposed((Disposable) get());
            }
        }

        FlatMapCompletableMainObserver(c cVar, g<? super T, ? extends e> gVar, boolean z) {
            this.actual = cVar;
            this.mapper = gVar;
            this.delayErrors = z;
            lazySet(1);
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.d, disposable)) {
                this.d = disposable;
                this.actual.onSubscribe(this);
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
                this.d.dispose();
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
            }
        }

        public void dispose() {
            this.disposed = true;
            this.d.dispose();
            this.set.dispose();
        }

        public boolean isDisposed() {
            return this.d.isDisposed();
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
        this.a.subscribe(new FlatMapCompletableMainObserver(cVar, this.b, this.c));
    }
}
