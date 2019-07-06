package io.reactivex.internal.operators.observable;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.g;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.q;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.t;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservablePublishSelector<T, R> extends a<T, R> {
    final g<? super q<T>, ? extends t<R>> b;

    static final class TargetObserver<T, R> extends AtomicReference<Disposable> implements Observer<R>, Disposable {
        private static final long serialVersionUID = 854110278590336484L;
        final Observer<? super R> actual;
        Disposable d;

        TargetObserver(Observer<? super R> observer) {
            this.actual = observer;
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.d, disposable)) {
                this.d = disposable;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(R r) {
            this.actual.onNext(r);
        }

        public void onError(Throwable th) {
            DisposableHelper.dispose(this);
            this.actual.onError(th);
        }

        public void onComplete() {
            DisposableHelper.dispose(this);
            this.actual.onComplete();
        }

        public void dispose() {
            this.d.dispose();
            DisposableHelper.dispose(this);
        }

        public boolean isDisposed() {
            return this.d.isDisposed();
        }
    }

    static final class a<T, R> implements Observer<T> {
        final PublishSubject<T> a;
        final AtomicReference<Disposable> b;

        a(PublishSubject<T> publishSubject, AtomicReference<Disposable> atomicReference) {
            this.a = publishSubject;
            this.b = atomicReference;
        }

        public void onSubscribe(Disposable disposable) {
            DisposableHelper.setOnce(this.b, disposable);
        }

        public void onNext(T t) {
            this.a.onNext(t);
        }

        public void onError(Throwable th) {
            this.a.onError(th);
        }

        public void onComplete() {
            this.a.onComplete();
        }
    }

    /* access modifiers changed from: protected */
    public void a(Observer<? super R> observer) {
        PublishSubject a2 = PublishSubject.a();
        try {
            t tVar = (t) io.reactivex.internal.functions.a.a(this.b.apply(a2), "The selector returned a null ObservableSource");
            TargetObserver targetObserver = new TargetObserver(observer);
            tVar.subscribe(targetObserver);
            this.a.subscribe(new a(a2, targetObserver));
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            EmptyDisposable.error(th, observer);
        }
    }
}
