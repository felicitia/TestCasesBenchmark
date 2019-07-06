package io.reactivex.internal.operators.maybe;

import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.functions.g;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.m;
import io.reactivex.o;
import java.util.concurrent.atomic.AtomicReference;

public final class MaybeOnErrorNext<T> extends a<T, T> {
    final g<? super Throwable, ? extends o<? extends T>> b;
    final boolean c;

    static final class OnErrorNextMaybeObserver<T> extends AtomicReference<Disposable> implements Disposable, m<T> {
        private static final long serialVersionUID = 2026620218879969836L;
        final m<? super T> actual;
        final boolean allowFatal;
        final g<? super Throwable, ? extends o<? extends T>> resumeFunction;

        static final class a<T> implements m<T> {
            final m<? super T> a;
            final AtomicReference<Disposable> b;

            a(m<? super T> mVar, AtomicReference<Disposable> atomicReference) {
                this.a = mVar;
                this.b = atomicReference;
            }

            public void onSubscribe(Disposable disposable) {
                DisposableHelper.setOnce(this.b, disposable);
            }

            public void onSuccess(T t) {
                this.a.onSuccess(t);
            }

            public void onError(Throwable th) {
                this.a.onError(th);
            }

            public void onComplete() {
                this.a.onComplete();
            }
        }

        OnErrorNextMaybeObserver(m<? super T> mVar, g<? super Throwable, ? extends o<? extends T>> gVar, boolean z) {
            this.actual = mVar;
            this.resumeFunction = gVar;
            this.allowFatal = z;
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
            this.actual.onSuccess(t);
        }

        public void onError(Throwable th) {
            if (this.allowFatal || (th instanceof Exception)) {
                try {
                    o oVar = (o) io.reactivex.internal.functions.a.a(this.resumeFunction.apply(th), "The resumeFunction returned a null MaybeSource");
                    DisposableHelper.replace(this, null);
                    oVar.a(new a(this.actual, this));
                } catch (Throwable th2) {
                    io.reactivex.exceptions.a.b(th2);
                    this.actual.onError(new CompositeException(th, th2));
                }
            } else {
                this.actual.onError(th);
            }
        }

        public void onComplete() {
            this.actual.onComplete();
        }
    }

    /* access modifiers changed from: protected */
    public void b(m<? super T> mVar) {
        this.a.a(new OnErrorNextMaybeObserver(mVar, this.b, this.c));
    }
}
