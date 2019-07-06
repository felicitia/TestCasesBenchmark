package io.reactivex.internal.operators.completable;

import io.reactivex.a;
import io.reactivex.b;
import io.reactivex.c;
import io.reactivex.d;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.f;
import io.reactivex.internal.disposables.CancellableDisposable;
import io.reactivex.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicReference;

public final class CompletableCreate extends a {
    final d a;

    static final class Emitter extends AtomicReference<Disposable> implements b, Disposable {
        private static final long serialVersionUID = -2467358622224974244L;
        final c actual;

        Emitter(c cVar) {
            this.actual = cVar;
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

        public void onError(Throwable th) {
            if (!tryOnError(th)) {
                io.reactivex.d.a.a(th);
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

    /* access modifiers changed from: protected */
    public void b(c cVar) {
        Emitter emitter = new Emitter(cVar);
        cVar.onSubscribe(emitter);
        try {
            this.a.a(emitter);
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            emitter.onError(th);
        }
    }
}
