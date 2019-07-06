package io.reactivex.internal.operators.completable;

import io.reactivex.a;
import io.reactivex.c;
import io.reactivex.disposables.Disposable;
import io.reactivex.e;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.g;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicReference;

public final class CompletableUsing<R> extends a {
    final Callable<R> a;
    final g<? super R, ? extends e> b;
    final Consumer<? super R> c;
    final boolean d;

    static final class UsingObserver<R> extends AtomicReference<Object> implements c, Disposable {
        private static final long serialVersionUID = -674404550052917487L;
        final c actual;
        Disposable d;
        final Consumer<? super R> disposer;
        final boolean eager;

        UsingObserver(c cVar, R r, Consumer<? super R> consumer, boolean z) {
            super(r);
            this.actual = cVar;
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
                    io.reactivex.exceptions.a.b(th);
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

        public void onError(Throwable th) {
            this.d = DisposableHelper.DISPOSED;
            if (this.eager) {
                Object andSet = getAndSet(this);
                if (andSet != this) {
                    try {
                        this.disposer.accept(andSet);
                    } catch (Throwable th2) {
                        io.reactivex.exceptions.a.b(th2);
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
                        io.reactivex.exceptions.a.b(th);
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
    public void b(c cVar) {
        try {
            Object call = this.a.call();
            try {
                ((e) io.reactivex.internal.functions.a.a(this.b.apply(call), "The completableFunction returned a null CompletableSource")).a(new UsingObserver(cVar, call, this.c, this.d));
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                io.reactivex.d.a.a(th);
            }
        } catch (Throwable th2) {
            io.reactivex.exceptions.a.b(th2);
            EmptyDisposable.error(th2, cVar);
        }
    }
}
