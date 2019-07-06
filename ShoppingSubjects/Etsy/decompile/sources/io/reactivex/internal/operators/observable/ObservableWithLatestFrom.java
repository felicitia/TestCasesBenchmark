package io.reactivex.internal.operators.observable;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.c;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.observers.b;
import io.reactivex.t;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableWithLatestFrom<T, U, R> extends a<T, R> {
    final c<? super T, ? super U, ? extends R> b;
    final t<? extends U> c;

    static final class WithLatestFromObserver<T, U, R> extends AtomicReference<U> implements Observer<T>, Disposable {
        private static final long serialVersionUID = -312246233408980075L;
        final Observer<? super R> actual;
        final c<? super T, ? super U, ? extends R> combiner;
        final AtomicReference<Disposable> other = new AtomicReference<>();
        final AtomicReference<Disposable> s = new AtomicReference<>();

        WithLatestFromObserver(Observer<? super R> observer, c<? super T, ? super U, ? extends R> cVar) {
            this.actual = observer;
            this.combiner = cVar;
        }

        public void onSubscribe(Disposable disposable) {
            DisposableHelper.setOnce(this.s, disposable);
        }

        public void onNext(T t) {
            Object obj = get();
            if (obj != null) {
                try {
                    this.actual.onNext(io.reactivex.internal.functions.a.a(this.combiner.apply(t, obj), "The combiner returned a null value"));
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    dispose();
                    this.actual.onError(th);
                }
            }
        }

        public void onError(Throwable th) {
            DisposableHelper.dispose(this.other);
            this.actual.onError(th);
        }

        public void onComplete() {
            DisposableHelper.dispose(this.other);
            this.actual.onComplete();
        }

        public void dispose() {
            DisposableHelper.dispose(this.s);
            DisposableHelper.dispose(this.other);
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((Disposable) this.s.get());
        }

        public boolean setOther(Disposable disposable) {
            return DisposableHelper.setOnce(this.other, disposable);
        }

        public void otherError(Throwable th) {
            DisposableHelper.dispose(this.s);
            this.actual.onError(th);
        }
    }

    final class a implements Observer<U> {
        private final WithLatestFromObserver<T, U, R> b;

        public void onComplete() {
        }

        a(WithLatestFromObserver<T, U, R> withLatestFromObserver) {
            this.b = withLatestFromObserver;
        }

        public void onSubscribe(Disposable disposable) {
            this.b.setOther(disposable);
        }

        public void onNext(U u) {
            this.b.lazySet(u);
        }

        public void onError(Throwable th) {
            this.b.otherError(th);
        }
    }

    public void a(Observer<? super R> observer) {
        b bVar = new b(observer);
        WithLatestFromObserver withLatestFromObserver = new WithLatestFromObserver(bVar, this.b);
        bVar.onSubscribe(withLatestFromObserver);
        this.c.subscribe(new a(withLatestFromObserver));
        this.a.subscribe(withLatestFromObserver);
    }
}
