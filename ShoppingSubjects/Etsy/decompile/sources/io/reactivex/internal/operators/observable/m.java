package io.reactivex.internal.operators.observable;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.i;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.t;

/* compiled from: ObservableTakeWhile */
public final class m<T> extends a<T, T> {
    final i<? super T> b;

    /* compiled from: ObservableTakeWhile */
    static final class a<T> implements Observer<T>, Disposable {
        final Observer<? super T> a;
        final i<? super T> b;
        Disposable c;
        boolean d;

        a(Observer<? super T> observer, i<? super T> iVar) {
            this.a = observer;
            this.b = iVar;
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
            if (!this.d) {
                try {
                    if (!this.b.test(t)) {
                        this.d = true;
                        this.c.dispose();
                        this.a.onComplete();
                        return;
                    }
                    this.a.onNext(t);
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    this.c.dispose();
                    onError(th);
                }
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
                this.a.onComplete();
            }
        }
    }

    public m(t<T> tVar, i<? super T> iVar) {
        super(tVar);
        this.b = iVar;
    }

    public void a(Observer<? super T> observer) {
        this.a.subscribe(new a(observer, this.b));
    }
}
