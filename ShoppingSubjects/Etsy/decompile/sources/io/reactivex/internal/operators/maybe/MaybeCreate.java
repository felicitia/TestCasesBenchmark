package io.reactivex.internal.operators.maybe;

import io.reactivex.d.a;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.f;
import io.reactivex.internal.disposables.CancellableDisposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.k;
import io.reactivex.l;
import io.reactivex.m;
import io.reactivex.n;
import java.util.concurrent.atomic.AtomicReference;

public final class MaybeCreate<T> extends k<T> {
    final n<T> a;

    static final class Emitter<T> extends AtomicReference<Disposable> implements Disposable, l<T> {
        private static final long serialVersionUID = -2467358622224974244L;
        final m<? super T> actual;

        Emitter(m<? super T> mVar) {
            this.actual = mVar;
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

        public void onComplete() {
            if (get() != DisposableHelper.DISPOSED) {
                Disposable disposable = (Disposable) getAndSet(DisposableHelper.DISPOSED);
                if (disposable != DisposableHelper.DISPOSED) {
                    try {
                        this.actual.onComplete();
                    } finally {
                        if (disposable != null) {
                            disposable.dispose();
                        }
                    }
                }
            }
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

    /* access modifiers changed from: protected */
    public void b(m<? super T> mVar) {
        Emitter emitter = new Emitter(mVar);
        mVar.onSubscribe(emitter);
        try {
            this.a.a(emitter);
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            emitter.onError(th);
        }
    }
}
