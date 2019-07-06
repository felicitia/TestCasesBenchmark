package io.reactivex.internal.operators.single;

import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.observers.e;
import io.reactivex.v;
import io.reactivex.x;
import io.reactivex.z;
import java.util.concurrent.atomic.AtomicReference;

public final class SingleDelayWithSingle<T, U> extends v<T> {
    final z<T> a;
    final z<U> b;

    static final class OtherObserver<T, U> extends AtomicReference<Disposable> implements Disposable, x<U> {
        private static final long serialVersionUID = -8565274649390031272L;
        final x<? super T> actual;
        final z<T> source;

        OtherObserver(x<? super T> xVar, z<T> zVar) {
            this.actual = xVar;
            this.source = zVar;
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.set(this, disposable)) {
                this.actual.onSubscribe(this);
            }
        }

        public void onSuccess(U u) {
            this.source.a(new e(this, this.actual));
        }

        public void onError(Throwable th) {
            this.actual.onError(th);
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((Disposable) get());
        }
    }

    /* access modifiers changed from: protected */
    public void b(x<? super T> xVar) {
        this.b.a(new OtherObserver(xVar, this.a));
    }
}
