package io.reactivex.internal.operators.maybe;

import io.reactivex.disposables.Disposable;
import io.reactivex.g;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.subscriptions.DeferredScalarSubscription;
import io.reactivex.m;
import io.reactivex.o;
import org.reactivestreams.c;

public final class MaybeToFlowable<T> extends g<T> {
    final o<T> b;

    static final class MaybeToFlowableSubscriber<T> extends DeferredScalarSubscription<T> implements m<T> {
        private static final long serialVersionUID = 7603343402964826922L;
        Disposable d;

        MaybeToFlowableSubscriber(c<? super T> cVar) {
            super(cVar);
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
            this.actual.onError(th);
        }

        public void onComplete() {
            this.actual.onComplete();
        }

        public void cancel() {
            super.cancel();
            this.d.dispose();
        }
    }

    public MaybeToFlowable(o<T> oVar) {
        this.b = oVar;
    }

    /* access modifiers changed from: protected */
    public void a(c<? super T> cVar) {
        this.b.a(new MaybeToFlowableSubscriber(cVar));
    }
}
