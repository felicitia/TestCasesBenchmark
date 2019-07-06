package io.reactivex.internal.operators.completable;

import io.reactivex.a;
import io.reactivex.c;
import io.reactivex.disposables.Disposable;
import io.reactivex.e;
import io.reactivex.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicInteger;

public final class CompletableDoFinally extends a {
    final e a;
    final io.reactivex.functions.a b;

    static final class DoFinallyObserver extends AtomicInteger implements c, Disposable {
        private static final long serialVersionUID = 4109457741734051389L;
        final c actual;
        Disposable d;
        final io.reactivex.functions.a onFinally;

        DoFinallyObserver(c cVar, io.reactivex.functions.a aVar) {
            this.actual = cVar;
            this.onFinally = aVar;
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.d, disposable)) {
                this.d = disposable;
                this.actual.onSubscribe(this);
            }
        }

        public void onError(Throwable th) {
            this.actual.onError(th);
            runFinally();
        }

        public void onComplete() {
            this.actual.onComplete();
            runFinally();
        }

        public void dispose() {
            this.d.dispose();
            runFinally();
        }

        public boolean isDisposed() {
            return this.d.isDisposed();
        }

        /* access modifiers changed from: 0000 */
        public void runFinally() {
            if (compareAndSet(0, 1)) {
                try {
                    this.onFinally.a();
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    io.reactivex.d.a.a(th);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void b(c cVar) {
        this.a.a(new DoFinallyObserver(cVar, this.b));
    }
}
