package io.reactivex.internal.operators.single;

import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.a;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.g;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.v;
import io.reactivex.x;
import io.reactivex.z;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicReference;

public final class SingleUsing<T, U> extends v<T> {
    final Callable<U> a;
    final g<? super U, ? extends z<? extends T>> b;
    final Consumer<? super U> c;
    final boolean d;

    static final class UsingSingleObserver<T, U> extends AtomicReference<Object> implements Disposable, x<T> {
        private static final long serialVersionUID = -5331524057054083935L;
        final x<? super T> actual;
        Disposable d;
        final Consumer<? super U> disposer;
        final boolean eager;

        UsingSingleObserver(x<? super T> xVar, U u, boolean z, Consumer<? super U> consumer) {
            super(u);
            this.actual = xVar;
            this.eager = z;
            this.disposer = consumer;
        }

        public void dispose() {
            this.d.dispose();
            this.d = DisposableHelper.DISPOSED;
            disposeAfter();
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
                disposeAfter();
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
                disposeAfter();
            }
        }

        /* access modifiers changed from: 0000 */
        public void disposeAfter() {
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
    }

    /* access modifiers changed from: protected */
    public void b(x<? super T> xVar) {
        try {
            Object call = this.a.call();
            try {
                ((z) io.reactivex.internal.functions.a.a(this.b.apply(call), "The singleFunction returned a null SingleSource")).a(new UsingSingleObserver(xVar, call, this.d, this.c));
                return;
            } catch (Throwable th) {
                a.b(th);
                io.reactivex.d.a.a(th);
            }
            EmptyDisposable.error(th, xVar);
            if (!this.d) {
                this.c.accept(call);
            }
        } catch (Throwable th2) {
            a.b(th2);
            EmptyDisposable.error(th2, xVar);
        }
    }
}
