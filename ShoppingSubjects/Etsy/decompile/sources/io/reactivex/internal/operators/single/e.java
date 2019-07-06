package io.reactivex.internal.operators.single;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.g;
import io.reactivex.v;
import io.reactivex.x;
import io.reactivex.z;

/* compiled from: SingleMap */
public final class e<T, R> extends v<R> {
    final z<? extends T> a;
    final g<? super T, ? extends R> b;

    /* compiled from: SingleMap */
    static final class a<T, R> implements x<T> {
        final x<? super R> a;
        final g<? super T, ? extends R> b;

        a(x<? super R> xVar, g<? super T, ? extends R> gVar) {
            this.a = xVar;
            this.b = gVar;
        }

        public void onSubscribe(Disposable disposable) {
            this.a.onSubscribe(disposable);
        }

        public void onSuccess(T t) {
            try {
                this.a.onSuccess(io.reactivex.internal.functions.a.a(this.b.apply(t), "The mapper function returned a null value."));
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                onError(th);
            }
        }

        public void onError(Throwable th) {
            this.a.onError(th);
        }
    }

    public e(z<? extends T> zVar, g<? super T, ? extends R> gVar) {
        this.a = zVar;
        this.b = gVar;
    }

    /* access modifiers changed from: protected */
    public void b(x<? super R> xVar) {
        this.a.a(new a(xVar, this.b));
    }
}
