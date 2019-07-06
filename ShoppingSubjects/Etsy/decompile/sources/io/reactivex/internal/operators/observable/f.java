package io.reactivex.internal.operators.observable;

import io.reactivex.Observer;
import io.reactivex.functions.i;
import io.reactivex.t;

/* compiled from: ObservableFilter */
public final class f<T> extends a<T, T> {
    final i<? super T> b;

    /* compiled from: ObservableFilter */
    static final class a<T> extends io.reactivex.internal.observers.a<T, T> {
        final i<? super T> f;

        a(Observer<? super T> observer, i<? super T> iVar) {
            super(observer);
            this.f = iVar;
        }

        public void onNext(T t) {
            if (this.e == 0) {
                try {
                    if (this.f.test(t)) {
                        this.a.onNext(t);
                    }
                } catch (Throwable th) {
                    a(th);
                }
            } else {
                this.a.onNext(null);
            }
        }

        public int requestFusion(int i) {
            return a(i);
        }

        public T poll() throws Exception {
            T poll;
            do {
                poll = this.c.poll();
                if (poll == null) {
                    break;
                }
            } while (!this.f.test(poll));
            return poll;
        }
    }

    public f(t<T> tVar, i<? super T> iVar) {
        super(tVar);
        this.b = iVar;
    }

    public void a(Observer<? super T> observer) {
        this.a.subscribe(new a(observer, this.b));
    }
}
