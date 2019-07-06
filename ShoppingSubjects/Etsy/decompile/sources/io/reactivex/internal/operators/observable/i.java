package io.reactivex.internal.operators.observable;

import io.reactivex.Observer;
import io.reactivex.functions.g;
import io.reactivex.t;

/* compiled from: ObservableMap */
public final class i<T, U> extends a<T, U> {
    final g<? super T, ? extends U> b;

    /* compiled from: ObservableMap */
    static final class a<T, U> extends io.reactivex.internal.observers.a<T, U> {
        final g<? super T, ? extends U> f;

        a(Observer<? super U> observer, g<? super T, ? extends U> gVar) {
            super(observer);
            this.f = gVar;
        }

        public void onNext(T t) {
            if (!this.d) {
                if (this.e != 0) {
                    this.a.onNext(null);
                    return;
                }
                try {
                    this.a.onNext(io.reactivex.internal.functions.a.a(this.f.apply(t), "The mapper function returned a null value."));
                } catch (Throwable th) {
                    a(th);
                }
            }
        }

        public int requestFusion(int i) {
            return a(i);
        }

        public U poll() throws Exception {
            Object poll = this.c.poll();
            if (poll != null) {
                return io.reactivex.internal.functions.a.a(this.f.apply(poll), "The mapper function returned a null value.");
            }
            return null;
        }
    }

    public i(t<T> tVar, g<? super T, ? extends U> gVar) {
        super(tVar);
        this.b = gVar;
    }

    public void a(Observer<? super U> observer) {
        this.a.subscribe(new a(observer, this.b));
    }
}
