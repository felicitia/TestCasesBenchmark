package io.reactivex.internal.operators.single;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.q;
import io.reactivex.x;
import io.reactivex.z;

/* compiled from: SingleToObservable */
public final class g<T> extends q<T> {
    final z<? extends T> a;

    /* compiled from: SingleToObservable */
    static final class a<T> implements Disposable, x<T> {
        final Observer<? super T> a;
        Disposable b;

        a(Observer<? super T> observer) {
            this.a = observer;
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.b, disposable)) {
                this.b = disposable;
                this.a.onSubscribe(this);
            }
        }

        public void onSuccess(T t) {
            this.a.onNext(t);
            this.a.onComplete();
        }

        public void onError(Throwable th) {
            this.a.onError(th);
        }

        public void dispose() {
            this.b.dispose();
        }

        public boolean isDisposed() {
            return this.b.isDisposed();
        }
    }

    public g(z<? extends T> zVar) {
        this.a = zVar;
    }

    public void a(Observer<? super T> observer) {
        this.a.a(new a(observer));
    }
}
