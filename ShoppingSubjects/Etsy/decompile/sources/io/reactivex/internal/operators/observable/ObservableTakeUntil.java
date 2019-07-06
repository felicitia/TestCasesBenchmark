package io.reactivex.internal.operators.observable;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.ArrayCompositeDisposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.observers.b;
import io.reactivex.t;
import java.util.concurrent.atomic.AtomicBoolean;

public final class ObservableTakeUntil<T, U> extends a<T, T> {
    final t<? extends U> b;

    static final class TakeUntilObserver<T> extends AtomicBoolean implements Observer<T> {
        private static final long serialVersionUID = 3451719290311127173L;
        final Observer<? super T> actual;
        final ArrayCompositeDisposable frc;
        Disposable s;

        TakeUntilObserver(Observer<? super T> observer, ArrayCompositeDisposable arrayCompositeDisposable) {
            this.actual = observer;
            this.frc = arrayCompositeDisposable;
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.s, disposable)) {
                this.s = disposable;
                this.frc.setResource(0, disposable);
            }
        }

        public void onNext(T t) {
            this.actual.onNext(t);
        }

        public void onError(Throwable th) {
            this.frc.dispose();
            this.actual.onError(th);
        }

        public void onComplete() {
            this.frc.dispose();
            this.actual.onComplete();
        }
    }

    final class a implements Observer<U> {
        private final ArrayCompositeDisposable b;
        private final b<T> c;

        a(ArrayCompositeDisposable arrayCompositeDisposable, b<T> bVar) {
            this.b = arrayCompositeDisposable;
            this.c = bVar;
        }

        public void onSubscribe(Disposable disposable) {
            this.b.setResource(1, disposable);
        }

        public void onNext(U u) {
            this.b.dispose();
            this.c.onComplete();
        }

        public void onError(Throwable th) {
            this.b.dispose();
            this.c.onError(th);
        }

        public void onComplete() {
            this.b.dispose();
            this.c.onComplete();
        }
    }

    public void a(Observer<? super T> observer) {
        b bVar = new b(observer);
        ArrayCompositeDisposable arrayCompositeDisposable = new ArrayCompositeDisposable(2);
        TakeUntilObserver takeUntilObserver = new TakeUntilObserver(bVar, arrayCompositeDisposable);
        observer.onSubscribe(arrayCompositeDisposable);
        this.b.subscribe(new a(arrayCompositeDisposable, bVar));
        this.a.subscribe(takeUntilObserver);
    }
}
