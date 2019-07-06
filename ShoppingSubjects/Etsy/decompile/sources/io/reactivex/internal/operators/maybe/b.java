package io.reactivex.internal.operators.maybe;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.g;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.m;

/* compiled from: MaybeMap */
public final class b<T, R> extends a<T, R> {
    final g<? super T, ? extends R> b;

    /* compiled from: MaybeMap */
    static final class a<T, R> implements Disposable, m<T> {
        final m<? super R> a;
        final g<? super T, ? extends R> b;
        Disposable c;

        a(m<? super R> mVar, g<? super T, ? extends R> gVar) {
            this.a = mVar;
            this.b = gVar;
        }

        public void dispose() {
            Disposable disposable = this.c;
            this.c = DisposableHelper.DISPOSED;
            disposable.dispose();
        }

        public boolean isDisposed() {
            return this.c.isDisposed();
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.c, disposable)) {
                this.c = disposable;
                this.a.onSubscribe(this);
            }
        }

        public void onSuccess(T t) {
            try {
                this.a.onSuccess(io.reactivex.internal.functions.a.a(this.b.apply(t), "The mapper returned a null item"));
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                this.a.onError(th);
            }
        }

        public void onError(Throwable th) {
            this.a.onError(th);
        }

        public void onComplete() {
            this.a.onComplete();
        }
    }

    /* access modifiers changed from: protected */
    public void b(m<? super R> mVar) {
        this.a.a(new a(mVar, this.b));
    }
}
