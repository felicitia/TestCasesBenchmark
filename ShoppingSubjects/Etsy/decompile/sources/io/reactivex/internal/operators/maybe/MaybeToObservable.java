package io.reactivex.internal.operators.maybe;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.observers.DeferredScalarDisposable;
import io.reactivex.m;
import io.reactivex.o;
import io.reactivex.q;

public final class MaybeToObservable<T> extends q<T> {
    final o<T> a;

    static final class MaybeToFlowableSubscriber<T> extends DeferredScalarDisposable<T> implements m<T> {
        private static final long serialVersionUID = 7603343402964826922L;
        Disposable d;

        MaybeToFlowableSubscriber(Observer<? super T> observer) {
            super(observer);
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.d, disposable)) {
                this.d = disposable;
                this.actual.onSubscribe(this);
            }
        }

        public void onSuccess(T t) {
            complete(t);
        }

        public void onError(Throwable th) {
            error(th);
        }

        public void onComplete() {
            complete();
        }

        public void dispose() {
            super.dispose();
            this.d.dispose();
        }
    }

    /* access modifiers changed from: protected */
    public void a(Observer<? super T> observer) {
        this.a.a(new MaybeToFlowableSubscriber(observer));
    }
}
