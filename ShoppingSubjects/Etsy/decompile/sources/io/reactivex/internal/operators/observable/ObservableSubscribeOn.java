package io.reactivex.internal.operators.observable;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.t;
import io.reactivex.u;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableSubscribeOn<T> extends a<T, T> {
    final u b;

    static final class SubscribeOnObserver<T> extends AtomicReference<Disposable> implements Observer<T>, Disposable {
        private static final long serialVersionUID = 8094547886072529208L;
        final Observer<? super T> actual;
        final AtomicReference<Disposable> s = new AtomicReference<>();

        SubscribeOnObserver(Observer<? super T> observer) {
            this.actual = observer;
        }

        public void onSubscribe(Disposable disposable) {
            DisposableHelper.setOnce(this.s, disposable);
        }

        public void onNext(T t) {
            this.actual.onNext(t);
        }

        public void onError(Throwable th) {
            this.actual.onError(th);
        }

        public void onComplete() {
            this.actual.onComplete();
        }

        public void dispose() {
            DisposableHelper.dispose(this.s);
            DisposableHelper.dispose(this);
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((Disposable) get());
        }

        /* access modifiers changed from: 0000 */
        public void setDisposable(Disposable disposable) {
            DisposableHelper.setOnce(this, disposable);
        }
    }

    final class a implements Runnable {
        private final SubscribeOnObserver<T> b;

        a(SubscribeOnObserver<T> subscribeOnObserver) {
            this.b = subscribeOnObserver;
        }

        public void run() {
            ObservableSubscribeOn.this.a.subscribe(this.b);
        }
    }

    public ObservableSubscribeOn(t<T> tVar, u uVar) {
        super(tVar);
        this.b = uVar;
    }

    public void a(Observer<? super T> observer) {
        SubscribeOnObserver subscribeOnObserver = new SubscribeOnObserver(observer);
        observer.onSubscribe(subscribeOnObserver);
        subscribeOnObserver.setDisposable(this.b.a((Runnable) new a(subscribeOnObserver)));
    }
}
