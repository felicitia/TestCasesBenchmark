package io.reactivex.internal.operators.flowable;

import io.reactivex.d.a;
import io.reactivex.internal.subscribers.SinglePostCompleteSubscriber;
import io.reactivex.j;
import io.reactivex.p;
import org.reactivestreams.c;

public final class FlowableMaterialize<T> extends a<T, p<T>> {

    static final class MaterializeSubscriber<T> extends SinglePostCompleteSubscriber<T, p<T>> {
        private static final long serialVersionUID = -3740826063558713822L;

        MaterializeSubscriber(c<? super p<T>> cVar) {
            super(cVar);
        }

        public void onNext(T t) {
            this.produced++;
            this.actual.onNext(p.a(t));
        }

        public void onError(Throwable th) {
            complete(p.a(th));
        }

        public void onComplete() {
            complete(p.c());
        }

        /* access modifiers changed from: protected */
        public void onDrop(p<T> pVar) {
            if (pVar.a()) {
                a.a(pVar.b());
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a(c<? super p<T>> cVar) {
        this.b.a((j<? super T>) new MaterializeSubscriber<Object>(cVar));
    }
}
