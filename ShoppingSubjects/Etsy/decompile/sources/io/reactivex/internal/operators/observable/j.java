package io.reactivex.internal.operators.observable;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.k;
import io.reactivex.m;
import io.reactivex.t;

/* compiled from: ObservableSingleMaybe */
public final class j<T> extends k<T> {
    final t<T> a;

    /* compiled from: ObservableSingleMaybe */
    static final class a<T> implements Observer<T>, Disposable {
        final m<? super T> a;
        Disposable b;
        T c;
        boolean d;

        a(m<? super T> mVar) {
            this.a = mVar;
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.b, disposable)) {
                this.b = disposable;
                this.a.onSubscribe(this);
            }
        }

        public void dispose() {
            this.b.dispose();
        }

        public boolean isDisposed() {
            return this.b.isDisposed();
        }

        public void onNext(T t) {
            if (!this.d) {
                if (this.c != null) {
                    this.d = true;
                    this.b.dispose();
                    this.a.onError(new IllegalArgumentException("Sequence contains more than one element!"));
                    return;
                }
                this.c = t;
            }
        }

        public void onError(Throwable th) {
            if (this.d) {
                io.reactivex.d.a.a(th);
                return;
            }
            this.d = true;
            this.a.onError(th);
        }

        public void onComplete() {
            if (!this.d) {
                this.d = true;
                T t = this.c;
                this.c = null;
                if (t == null) {
                    this.a.onComplete();
                } else {
                    this.a.onSuccess(t);
                }
            }
        }
    }

    public j(t<T> tVar) {
        this.a = tVar;
    }

    public void b(m<? super T> mVar) {
        this.a.subscribe(new a(mVar));
    }
}
