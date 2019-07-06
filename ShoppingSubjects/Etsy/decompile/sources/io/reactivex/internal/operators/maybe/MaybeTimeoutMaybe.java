package io.reactivex.internal.operators.maybe;

import io.reactivex.d.a;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.m;
import io.reactivex.o;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;

public final class MaybeTimeoutMaybe<T, U> extends a<T, T> {
    final o<U> b;
    final o<? extends T> c;

    static final class TimeoutFallbackMaybeObserver<T> extends AtomicReference<Disposable> implements m<T> {
        private static final long serialVersionUID = 8663801314800248617L;
        final m<? super T> actual;

        TimeoutFallbackMaybeObserver(m<? super T> mVar) {
            this.actual = mVar;
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

        public void onComplete() {
            this.actual.onComplete();
        }
    }

    static final class TimeoutMainMaybeObserver<T, U> extends AtomicReference<Disposable> implements Disposable, m<T> {
        private static final long serialVersionUID = -5955289211445418871L;
        final m<? super T> actual;
        final o<? extends T> fallback;
        final TimeoutOtherMaybeObserver<T, U> other = new TimeoutOtherMaybeObserver<>(this);
        final TimeoutFallbackMaybeObserver<T> otherObserver;

        TimeoutMainMaybeObserver(m<? super T> mVar, o<? extends T> oVar) {
            this.actual = mVar;
            this.fallback = oVar;
            this.otherObserver = oVar != null ? new TimeoutFallbackMaybeObserver<>(mVar) : null;
        }

        public void dispose() {
            DisposableHelper.dispose(this);
            DisposableHelper.dispose(this.other);
            TimeoutFallbackMaybeObserver<T> timeoutFallbackMaybeObserver = this.otherObserver;
            if (timeoutFallbackMaybeObserver != null) {
                DisposableHelper.dispose(timeoutFallbackMaybeObserver);
            }
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((Disposable) get());
        }

        public void onSubscribe(Disposable disposable) {
            DisposableHelper.setOnce(this, disposable);
        }

        public void onSuccess(T t) {
            DisposableHelper.dispose(this.other);
            if (getAndSet(DisposableHelper.DISPOSED) != DisposableHelper.DISPOSED) {
                this.actual.onSuccess(t);
            }
        }

        public void onError(Throwable th) {
            DisposableHelper.dispose(this.other);
            if (getAndSet(DisposableHelper.DISPOSED) != DisposableHelper.DISPOSED) {
                this.actual.onError(th);
            } else {
                a.a(th);
            }
        }

        public void onComplete() {
            DisposableHelper.dispose(this.other);
            if (getAndSet(DisposableHelper.DISPOSED) != DisposableHelper.DISPOSED) {
                this.actual.onComplete();
            }
        }

        public void otherError(Throwable th) {
            if (DisposableHelper.dispose(this)) {
                this.actual.onError(th);
            } else {
                a.a(th);
            }
        }

        public void otherComplete() {
            if (!DisposableHelper.dispose(this)) {
                return;
            }
            if (this.fallback == null) {
                this.actual.onError(new TimeoutException());
            } else {
                this.fallback.a(this.otherObserver);
            }
        }
    }

    static final class TimeoutOtherMaybeObserver<T, U> extends AtomicReference<Disposable> implements m<Object> {
        private static final long serialVersionUID = 8663801314800248617L;
        final TimeoutMainMaybeObserver<T, U> parent;

        TimeoutOtherMaybeObserver(TimeoutMainMaybeObserver<T, U> timeoutMainMaybeObserver) {
            this.parent = timeoutMainMaybeObserver;
        }

        public void onSubscribe(Disposable disposable) {
            DisposableHelper.setOnce(this, disposable);
        }

        public void onSuccess(Object obj) {
            this.parent.otherComplete();
        }

        public void onError(Throwable th) {
            this.parent.otherError(th);
        }

        public void onComplete() {
            this.parent.otherComplete();
        }
    }

    /* access modifiers changed from: protected */
    public void b(m<? super T> mVar) {
        TimeoutMainMaybeObserver timeoutMainMaybeObserver = new TimeoutMainMaybeObserver(mVar, this.c);
        mVar.onSubscribe(timeoutMainMaybeObserver);
        this.b.a(timeoutMainMaybeObserver.other);
        this.a.a(timeoutMainMaybeObserver);
    }
}
