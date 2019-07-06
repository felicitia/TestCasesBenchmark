package io.reactivex.internal.operators.single;

import io.reactivex.d.a;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.f;
import io.reactivex.internal.disposables.CancellableDisposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.v;
import io.reactivex.w;
import io.reactivex.x;
import io.reactivex.y;
import java.util.concurrent.atomic.AtomicReference;

public final class SingleCreate<T> extends v<T> {
    final y<T> a;

    static final class Emitter<T> extends AtomicReference<Disposable> implements Disposable, w<T> {
        private static final long serialVersionUID = -2467358622224974244L;
        final x<? super T> actual;

        Emitter(x<? super T> xVar) {
            this.actual = xVar;
        }

        public void onSuccess(T t) {
            if (get() != DisposableHelper.DISPOSED) {
                Disposable disposable = (Disposable) getAndSet(DisposableHelper.DISPOSED);
                if (disposable != DisposableHelper.DISPOSED) {
                    if (t == null) {
                        try {
                            this.actual.onError(new NullPointerException("onSuccess called with null. Null values are generally not allowed in 2.x operators and sources."));
                        } catch (Throwable th) {
                            if (disposable != null) {
                                disposable.dispose();
                            }
                            throw th;
                        }
                    } else {
                        this.actual.onSuccess(t);
                    }
                    if (disposable != null) {
                        disposable.dispose();
                    }
                }
            }
        }

        public void onError(Throwable th) {
            if (!tryOnError(th)) {
                a.a(th);
            }
        }

        public boolean tryOnError(Throwable th) {
            if (th == null) {
                th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
            }
            if (get() != DisposableHelper.DISPOSED) {
                Disposable disposable = (Disposable) getAndSet(DisposableHelper.DISPOSED);
                if (disposable != DisposableHelper.DISPOSED) {
                    try {
                        this.actual.onError(th);
                        return true;
                    } finally {
                        if (disposable != null) {
                            disposable.dispose();
                        }
                    }
                }
            }
            return false;
        }

        public void setDisposable(Disposable disposable) {
            DisposableHelper.set(this, disposable);
        }

        public void setCancellable(f fVar) {
            setDisposable(new CancellableDisposable(fVar));
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((Disposable) get());
        }
    }

    public SingleCreate(y<T> yVar) {
        this.a = yVar;
    }

    /* access modifiers changed from: protected */
    public void b(x<? super T> xVar) {
        Emitter emitter = new Emitter(xVar);
        xVar.onSubscribe(emitter);
        try {
            this.a.a(emitter);
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            emitter.onError(th);
        }
    }
}
