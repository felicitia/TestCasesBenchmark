package io.reactivex.internal.operators.observable;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.u;
import java.util.concurrent.atomic.AtomicBoolean;

public final class ObservableUnsubscribeOn<T> extends a<T, T> {
    final u b;

    static final class UnsubscribeObserver<T> extends AtomicBoolean implements Observer<T>, Disposable {
        private static final long serialVersionUID = 1015244841293359600L;
        final Observer<? super T> actual;
        Disposable s;
        final u scheduler;

        final class a implements Runnable {
            a() {
            }

            public void run() {
                UnsubscribeObserver.this.s.dispose();
            }
        }

        UnsubscribeObserver(Observer<? super T> observer, u uVar) {
            this.actual = observer;
            this.scheduler = uVar;
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.s, disposable)) {
                this.s = disposable;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            if (!get()) {
                this.actual.onNext(t);
            }
        }

        public void onError(Throwable th) {
            if (get()) {
                io.reactivex.d.a.a(th);
            } else {
                this.actual.onError(th);
            }
        }

        public void onComplete() {
            if (!get()) {
                this.actual.onComplete();
            }
        }

        public void dispose() {
            if (compareAndSet(false, true)) {
                this.scheduler.a((Runnable) new a());
            }
        }

        public boolean isDisposed() {
            return get();
        }
    }

    public void a(Observer<? super T> observer) {
        this.a.subscribe(new UnsubscribeObserver(observer, this.b));
    }
}
