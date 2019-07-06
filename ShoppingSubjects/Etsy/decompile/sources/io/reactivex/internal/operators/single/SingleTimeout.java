package io.reactivex.internal.operators.single;

import io.reactivex.d.a;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.u;
import io.reactivex.v;
import io.reactivex.x;
import io.reactivex.z;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;

public final class SingleTimeout<T> extends v<T> {
    final z<T> a;
    final long b;
    final TimeUnit c;
    final u d;
    final z<? extends T> e;

    static final class TimeoutMainObserver<T> extends AtomicReference<Disposable> implements Disposable, x<T>, Runnable {
        private static final long serialVersionUID = 37497744973048446L;
        final x<? super T> actual;
        final TimeoutFallbackObserver<T> fallback;
        z<? extends T> other;
        final AtomicReference<Disposable> task = new AtomicReference<>();

        static final class TimeoutFallbackObserver<T> extends AtomicReference<Disposable> implements x<T> {
            private static final long serialVersionUID = 2071387740092105509L;
            final x<? super T> actual;

            TimeoutFallbackObserver(x<? super T> xVar) {
                this.actual = xVar;
            }

            public void onSubscribe(Disposable disposable) {
                DisposableHelper.setOnce(this, disposable);
            }

            public void onSuccess(T t) {
                this.actual.onSuccess(t);
            }

            public void onError(Throwable th) {
                this.actual.onError(th);
            }
        }

        TimeoutMainObserver(x<? super T> xVar, z<? extends T> zVar) {
            this.actual = xVar;
            this.other = zVar;
            if (zVar != null) {
                this.fallback = new TimeoutFallbackObserver<>(xVar);
            } else {
                this.fallback = null;
            }
        }

        public void run() {
            Disposable disposable = (Disposable) get();
            if (disposable != DisposableHelper.DISPOSED && compareAndSet(disposable, DisposableHelper.DISPOSED)) {
                if (disposable != null) {
                    disposable.dispose();
                }
                z<? extends T> zVar = this.other;
                if (zVar == null) {
                    this.actual.onError(new TimeoutException());
                    return;
                }
                this.other = null;
                zVar.a(this.fallback);
            }
        }

        public void onSubscribe(Disposable disposable) {
            DisposableHelper.setOnce(this, disposable);
        }

        public void onSuccess(T t) {
            Disposable disposable = (Disposable) get();
            if (disposable != DisposableHelper.DISPOSED && compareAndSet(disposable, DisposableHelper.DISPOSED)) {
                DisposableHelper.dispose(this.task);
                this.actual.onSuccess(t);
            }
        }

        public void onError(Throwable th) {
            Disposable disposable = (Disposable) get();
            if (disposable == DisposableHelper.DISPOSED || !compareAndSet(disposable, DisposableHelper.DISPOSED)) {
                a.a(th);
                return;
            }
            DisposableHelper.dispose(this.task);
            this.actual.onError(th);
        }

        public void dispose() {
            DisposableHelper.dispose(this);
            DisposableHelper.dispose(this.task);
            if (this.fallback != null) {
                DisposableHelper.dispose(this.fallback);
            }
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((Disposable) get());
        }
    }

    /* access modifiers changed from: protected */
    public void b(x<? super T> xVar) {
        TimeoutMainObserver timeoutMainObserver = new TimeoutMainObserver(xVar, this.e);
        xVar.onSubscribe(timeoutMainObserver);
        DisposableHelper.replace(timeoutMainObserver.task, this.d.a(timeoutMainObserver, this.b, this.c));
        this.a.a(timeoutMainObserver);
    }
}
