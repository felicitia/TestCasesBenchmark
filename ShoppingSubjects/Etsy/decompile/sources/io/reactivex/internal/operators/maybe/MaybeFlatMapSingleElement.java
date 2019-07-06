package io.reactivex.internal.operators.maybe;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.g;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.k;
import io.reactivex.m;
import io.reactivex.o;
import io.reactivex.x;
import io.reactivex.z;
import java.util.concurrent.atomic.AtomicReference;

public final class MaybeFlatMapSingleElement<T, R> extends k<R> {
    final o<T> a;
    final g<? super T, ? extends z<? extends R>> b;

    static final class FlatMapMaybeObserver<T, R> extends AtomicReference<Disposable> implements Disposable, m<T> {
        private static final long serialVersionUID = 4827726964688405508L;
        final m<? super R> actual;
        final g<? super T, ? extends z<? extends R>> mapper;

        FlatMapMaybeObserver(m<? super R> mVar, g<? super T, ? extends z<? extends R>> gVar) {
            this.actual = mVar;
            this.mapper = gVar;
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((Disposable) get());
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.setOnce(this, disposable)) {
                this.actual.onSubscribe(this);
            }
        }

        public void onSuccess(T t) {
            try {
                ((z) io.reactivex.internal.functions.a.a(this.mapper.apply(t), "The mapper returned a null SingleSource")).a(new a(this, this.actual));
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                onError(th);
            }
        }

        public void onError(Throwable th) {
            this.actual.onError(th);
        }

        public void onComplete() {
            this.actual.onComplete();
        }
    }

    static final class a<R> implements x<R> {
        final AtomicReference<Disposable> a;
        final m<? super R> b;

        a(AtomicReference<Disposable> atomicReference, m<? super R> mVar) {
            this.a = atomicReference;
            this.b = mVar;
        }

        public void onSubscribe(Disposable disposable) {
            DisposableHelper.replace(this.a, disposable);
        }

        public void onSuccess(R r) {
            this.b.onSuccess(r);
        }

        public void onError(Throwable th) {
            this.b.onError(th);
        }
    }

    /* access modifiers changed from: protected */
    public void b(m<? super R> mVar) {
        this.a.a(new FlatMapMaybeObserver(mVar, this.b));
    }
}
