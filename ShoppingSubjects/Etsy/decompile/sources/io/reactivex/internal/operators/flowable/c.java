package io.reactivex.internal.operators.flowable;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.g;
import io.reactivex.q;
import org.reactivestreams.Subscription;

/* compiled from: FlowableFromObservable */
public final class c<T> extends g<T> {
    private final q<T> b;

    /* compiled from: FlowableFromObservable */
    static class a<T> implements Observer<T>, Subscription {
        private final org.reactivestreams.c<? super T> a;
        private Disposable b;

        public void request(long j) {
        }

        a(org.reactivestreams.c<? super T> cVar) {
            this.a = cVar;
        }

        public void onComplete() {
            this.a.onComplete();
        }

        public void onError(Throwable th) {
            this.a.onError(th);
        }

        public void onNext(T t) {
            this.a.onNext(t);
        }

        public void onSubscribe(Disposable disposable) {
            this.b = disposable;
            this.a.onSubscribe(this);
        }

        public void cancel() {
            this.b.dispose();
        }
    }

    public c(q<T> qVar) {
        this.b = qVar;
    }

    /* access modifiers changed from: protected */
    public void a(org.reactivestreams.c<? super T> cVar) {
        this.b.subscribe(new a(cVar));
    }
}
