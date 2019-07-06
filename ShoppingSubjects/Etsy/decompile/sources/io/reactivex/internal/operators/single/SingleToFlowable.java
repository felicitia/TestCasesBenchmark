package io.reactivex.internal.operators.single;

import io.reactivex.disposables.Disposable;
import io.reactivex.g;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.subscriptions.DeferredScalarSubscription;
import io.reactivex.x;
import io.reactivex.z;
import org.reactivestreams.c;

public final class SingleToFlowable<T> extends g<T> {
    final z<? extends T> b;

    static final class SingleToFlowableObserver<T> extends DeferredScalarSubscription<T> implements x<T> {
        private static final long serialVersionUID = 187782011903685568L;
        Disposable d;

        SingleToFlowableObserver(c<? super T> cVar) {
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

        public void cancel() {
            super.cancel();
            this.d.dispose();
        }
    }

    public SingleToFlowable(z<? extends T> zVar) {
        this.b = zVar;
    }

    public void a(c<? super T> cVar) {
        this.b.a(new SingleToFlowableObserver(cVar));
    }
}
