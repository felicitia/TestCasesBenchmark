package io.reactivex.internal.operators.flowable;

import io.reactivex.exceptions.CompositeException;
import io.reactivex.functions.g;
import io.reactivex.internal.functions.a;
import io.reactivex.internal.subscribers.SinglePostCompleteSubscriber;
import io.reactivex.j;
import org.reactivestreams.c;

public final class FlowableOnErrorReturn<T> extends a<T, T> {
    final g<? super Throwable, ? extends T> c;

    static final class OnErrorReturnSubscriber<T> extends SinglePostCompleteSubscriber<T, T> {
        private static final long serialVersionUID = -3740826063558713822L;
        final g<? super Throwable, ? extends T> valueSupplier;

        OnErrorReturnSubscriber(c<? super T> cVar, g<? super Throwable, ? extends T> gVar) {
            super(cVar);
            this.valueSupplier = gVar;
        }

        public void onNext(T t) {
            this.produced++;
            this.actual.onNext(t);
        }

        public void onError(Throwable th) {
            try {
                complete(a.a(this.valueSupplier.apply(th), "The valueSupplier returned a null value"));
            } catch (Throwable th2) {
                io.reactivex.exceptions.a.b(th2);
                this.actual.onError(new CompositeException(th, th2));
            }
        }

        public void onComplete() {
            this.actual.onComplete();
        }
    }

    /* access modifiers changed from: protected */
    public void a(c<? super T> cVar) {
        this.b.a((j<? super T>) new OnErrorReturnSubscriber<Object>(cVar, this.c));
    }
}
