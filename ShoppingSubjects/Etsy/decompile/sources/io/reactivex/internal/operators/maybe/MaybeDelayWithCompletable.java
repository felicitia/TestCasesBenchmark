package io.reactivex.internal.operators.maybe;

import io.reactivex.c;
import io.reactivex.disposables.Disposable;
import io.reactivex.e;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.k;
import io.reactivex.m;
import io.reactivex.o;
import java.util.concurrent.atomic.AtomicReference;

public final class MaybeDelayWithCompletable<T> extends k<T> {
    final o<T> a;
    final e b;

    static final class OtherObserver<T> extends AtomicReference<Disposable> implements c, Disposable {
        private static final long serialVersionUID = 703409937383992161L;
        final m<? super T> actual;
        final o<T> source;

        OtherObserver(m<? super T> mVar, o<T> oVar) {
            this.actual = mVar;
            this.source = oVar;
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.setOnce(this, disposable)) {
                this.actual.onSubscribe(this);
            }
        }

        public void onError(Throwable th) {
            this.actual.onError(th);
        }

        public void onComplete() {
            this.source.a(new a(this, this.actual));
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((Disposable) get());
        }
    }

    static final class a<T> implements m<T> {
        final AtomicReference<Disposable> a;
        final m<? super T> b;

        a(AtomicReference<Disposable> atomicReference, m<? super T> mVar) {
            this.a = atomicReference;
            this.b = mVar;
        }

        public void onSubscribe(Disposable disposable) {
            DisposableHelper.replace(this.a, disposable);
        }

        public void onSuccess(T t) {
            this.b.onSuccess(t);
        }

        public void onError(Throwable th) {
            this.b.onError(th);
        }

        public void onComplete() {
            this.b.onComplete();
        }
    }

    /* access modifiers changed from: protected */
    public void b(m<? super T> mVar) {
        this.b.a(new OtherObserver(mVar, this.a));
    }
}
