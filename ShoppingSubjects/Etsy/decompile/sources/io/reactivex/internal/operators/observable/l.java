package io.reactivex.internal.operators.observable;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.t;

/* compiled from: ObservableSkip */
public final class l<T> extends a<T, T> {
    final long b;

    /* compiled from: ObservableSkip */
    static final class a<T> implements Observer<T>, Disposable {
        final Observer<? super T> a;
        long b;
        Disposable c;

        a(Observer<? super T> observer, long j) {
            this.a = observer;
            this.b = j;
        }

        public void onSubscribe(Disposable disposable) {
            this.c = disposable;
            this.a.onSubscribe(this);
        }

        public void onNext(T t) {
            if (this.b != 0) {
                this.b--;
            } else {
                this.a.onNext(t);
            }
        }

        public void onError(Throwable th) {
            this.a.onError(th);
        }

        public void onComplete() {
            this.a.onComplete();
        }

        public void dispose() {
            this.c.dispose();
        }

        public boolean isDisposed() {
            return this.c.isDisposed();
        }
    }

    public l(t<T> tVar, long j) {
        super(tVar);
        this.b = j;
    }

    public void a(Observer<? super T> observer) {
        this.a.subscribe(new a(observer, this.b));
    }
}
