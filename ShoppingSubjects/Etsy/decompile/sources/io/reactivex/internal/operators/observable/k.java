package io.reactivex.internal.operators.observable;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.t;
import io.reactivex.v;
import io.reactivex.x;
import java.util.NoSuchElementException;

/* compiled from: ObservableSingleSingle */
public final class k<T> extends v<T> {
    final t<? extends T> a;
    final T b;

    /* compiled from: ObservableSingleSingle */
    static final class a<T> implements Observer<T>, Disposable {
        final x<? super T> a;
        final T b;
        Disposable c;
        T d;
        boolean e;

        a(x<? super T> xVar, T t) {
            this.a = xVar;
            this.b = t;
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.c, disposable)) {
                this.c = disposable;
                this.a.onSubscribe(this);
            }
        }

        public void dispose() {
            this.c.dispose();
        }

        public boolean isDisposed() {
            return this.c.isDisposed();
        }

        public void onNext(T t) {
            if (!this.e) {
                if (this.d != null) {
                    this.e = true;
                    this.c.dispose();
                    this.a.onError(new IllegalArgumentException("Sequence contains more than one element!"));
                    return;
                }
                this.d = t;
            }
        }

        public void onError(Throwable th) {
            if (this.e) {
                io.reactivex.d.a.a(th);
                return;
            }
            this.e = true;
            this.a.onError(th);
        }

        public void onComplete() {
            if (!this.e) {
                this.e = true;
                T t = this.d;
                this.d = null;
                if (t == null) {
                    t = this.b;
                }
                if (t != null) {
                    this.a.onSuccess(t);
                } else {
                    this.a.onError(new NoSuchElementException());
                }
            }
        }
    }

    public k(t<? extends T> tVar, T t) {
        this.a = tVar;
        this.b = t;
    }

    public void b(x<? super T> xVar) {
        this.a.subscribe(new a(xVar, this.b));
    }
}
