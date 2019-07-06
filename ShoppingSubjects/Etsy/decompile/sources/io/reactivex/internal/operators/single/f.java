package io.reactivex.internal.operators.single;

import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.functions.g;
import io.reactivex.v;
import io.reactivex.x;
import io.reactivex.z;

/* compiled from: SingleOnErrorReturn */
public final class f<T> extends v<T> {
    final z<? extends T> a;
    final g<? super Throwable, ? extends T> b;
    final T c;

    /* compiled from: SingleOnErrorReturn */
    final class a implements x<T> {
        private final x<? super T> b;

        a(x<? super T> xVar) {
            this.b = xVar;
        }

        public void onError(Throwable th) {
            T t;
            if (f.this.b != null) {
                try {
                    t = f.this.b.apply(th);
                } catch (Throwable th2) {
                    io.reactivex.exceptions.a.b(th2);
                    this.b.onError(new CompositeException(th, th2));
                    return;
                }
            } else {
                t = f.this.c;
            }
            if (t == null) {
                NullPointerException nullPointerException = new NullPointerException("Value supplied was null");
                nullPointerException.initCause(th);
                this.b.onError(nullPointerException);
                return;
            }
            this.b.onSuccess(t);
        }

        public void onSubscribe(Disposable disposable) {
            this.b.onSubscribe(disposable);
        }

        public void onSuccess(T t) {
            this.b.onSuccess(t);
        }
    }

    public f(z<? extends T> zVar, g<? super Throwable, ? extends T> gVar, T t) {
        this.a = zVar;
        this.b = gVar;
        this.c = t;
    }

    /* access modifiers changed from: protected */
    public void b(x<? super T> xVar) {
        this.a.a(new a(xVar));
    }
}
