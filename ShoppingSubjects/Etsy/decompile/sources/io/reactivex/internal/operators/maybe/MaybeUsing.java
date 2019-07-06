package io.reactivex.internal.operators.maybe;

import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.a;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.g;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.k;
import io.reactivex.m;
import io.reactivex.o;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicReference;

public final class MaybeUsing<T, D> extends k<T> {
    final Callable<? extends D> a;
    final g<? super D, ? extends o<? extends T>> b;
    final Consumer<? super D> c;
    final boolean d;

    static final class UsingObserver<T, D> extends AtomicReference<Object> implements Disposable, m<T> {
        private static final long serialVersionUID = -674404550052917487L;
        final m<? super T> actual;
        Disposable d;
        final Consumer<? super D> disposer;
        final boolean eager;

        UsingObserver(m<? super T> mVar, D d2, Consumer<? super D> consumer, boolean z) {
            super(d2);
            this.actual = mVar;
            this.disposer = consumer;
            this.eager = z;
        }

        public void dispose() {
            this.d.dispose();
            this.d = DisposableHelper.DISPOSED;
            disposeResourceAfter();
        }

        /* access modifiers changed from: 0000 */
        public void disposeResourceAfter() {
            Object andSet = getAndSet(this);
            if (andSet != this) {
                try {
                    this.disposer.accept(andSet);
                } catch (Throwable th) {
                    a.b(th);
                    io.reactivex.d.a.a(th);
                }
            }
        }

        public boolean isDisposed() {
            return this.d.isDisposed();
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.d, disposable)) {
                this.d = disposable;
                this.actual.onSubscribe(this);
            }
        }

        public void onSuccess(T t) {
            this.d = DisposableHelper.DISPOSED;
            if (this.eager) {
                Object andSet = getAndSet(this);
                if (andSet != this) {
                    try {
                        this.disposer.accept(andSet);
                    } catch (Throwable th) {
                        a.b(th);
                        this.actual.onError(th);
                        return;
                    }
                } else {
                    return;
                }
            }
            this.actual.onSuccess(t);
            if (!this.eager) {
                disposeResourceAfter();
            }
        }

        public void onError(Throwable th) {
            this.d = DisposableHelper.DISPOSED;
            if (this.eager) {
                Object andSet = getAndSet(this);
                if (andSet != this) {
                    try {
                        this.disposer.accept(andSet);
                    } catch (Throwable th2) {
                        a.b(th2);
                        th = new CompositeException(th, th2);
                    }
                } else {
                    return;
                }
            }
            this.actual.onError(th);
            if (!this.eager) {
                disposeResourceAfter();
            }
        }

        public void onComplete() {
            this.d = DisposableHelper.DISPOSED;
            if (this.eager) {
                Object andSet = getAndSet(this);
                if (andSet != this) {
                    try {
                        this.disposer.accept(andSet);
                    } catch (Throwable th) {
                        a.b(th);
                        this.actual.onError(th);
                        return;
                    }
                } else {
                    return;
                }
            }
            this.actual.onComplete();
            if (!this.eager) {
                disposeResourceAfter();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void b(m<? super T> mVar) {
        try {
            Object call = this.a.call();
            try {
                ((o) io.reactivex.internal.functions.a.a(this.b.apply(call), "The sourceSupplier returned a null MaybeSource")).a(new UsingObserver(mVar, call, this.c, this.d));
            } catch (Throwable th) {
                a.b(th);
                io.reactivex.d.a.a(th);
            }
        } catch (Throwable th2) {
            a.b(th2);
            EmptyDisposable.error(th2, mVar);
        }
    }
}
