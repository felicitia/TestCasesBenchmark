package io.reactivex.internal.operators.observable;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.t;
import io.reactivex.v;
import io.reactivex.x;
import java.util.NoSuchElementException;

/* compiled from: ObservableElementAtSingle */
public final class c<T> extends v<T> {
    final t<T> a;
    final long b;
    final T c;

    /* compiled from: ObservableElementAtSingle */
    static final class a<T> implements Observer<T>, Disposable {
        final x<? super T> a;
        final long b;
        final T c;
        Disposable d;
        long e;
        boolean f;

        a(x<? super T> xVar, long j, T t) {
            this.a = xVar;
            this.b = j;
            this.c = t;
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.d, disposable)) {
                this.d = disposable;
                this.a.onSubscribe(this);
            }
        }

        public void dispose() {
            this.d.dispose();
        }

        public boolean isDisposed() {
            return this.d.isDisposed();
        }

        public void onNext(T t) {
            if (!this.f) {
                long j = this.e;
                if (j == this.b) {
                    this.f = true;
                    this.d.dispose();
                    this.a.onSuccess(t);
                    return;
                }
                this.e = j + 1;
            }
        }

        public void onError(Throwable th) {
            if (this.f) {
                io.reactivex.d.a.a(th);
                return;
            }
            this.f = true;
            this.a.onError(th);
        }

        public void onComplete() {
            if (!this.f) {
                this.f = true;
                T t = this.c;
                if (t != null) {
                    this.a.onSuccess(t);
                } else {
                    this.a.onError(new NoSuchElementException());
                }
            }
        }
    }

    public c(t<T> tVar, long j, T t) {
        this.a = tVar;
        this.b = j;
        this.c = t;
    }

    public void b(x<? super T> xVar) {
        this.a.subscribe(new a(xVar, this.b, this.c));
    }
}
