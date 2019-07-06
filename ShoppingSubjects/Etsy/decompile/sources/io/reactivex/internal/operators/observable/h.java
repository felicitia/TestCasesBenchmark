package io.reactivex.internal.operators.observable;

import io.reactivex.Observer;
import io.reactivex.c;
import io.reactivex.disposables.Disposable;
import io.reactivex.t;

/* compiled from: ObservableIgnoreElementsCompletable */
public final class h<T> extends io.reactivex.a {
    final t<T> a;

    /* compiled from: ObservableIgnoreElementsCompletable */
    static final class a<T> implements Observer<T>, Disposable {
        final c a;
        Disposable b;

        public void onNext(T t) {
        }

        a(c cVar) {
            this.a = cVar;
        }

        public void onSubscribe(Disposable disposable) {
            this.b = disposable;
            this.a.onSubscribe(this);
        }

        public void onError(Throwable th) {
            this.a.onError(th);
        }

        public void onComplete() {
            this.a.onComplete();
        }

        public void dispose() {
            this.b.dispose();
        }

        public boolean isDisposed() {
            return this.b.isDisposed();
        }
    }

    public h(t<T> tVar) {
        this.a = tVar;
    }

    public void b(c cVar) {
        this.a.subscribe(new a(cVar));
    }
}
