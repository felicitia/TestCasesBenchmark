package io.reactivex.internal.operators.flowable;

import io.reactivex.functions.g;
import io.reactivex.j;
import org.reactivestreams.c;

/* compiled from: FlowableMap */
public final class d<T, U> extends a<T, U> {
    final g<? super T, ? extends U> c;

    /* compiled from: FlowableMap */
    static final class a<T, U> extends io.reactivex.internal.subscribers.a<T, U> {
        final g<? super T, ? extends U> a;

        a(io.reactivex.internal.a.a<? super U> aVar, g<? super T, ? extends U> gVar) {
            super(aVar);
            this.a = gVar;
        }

        public void onNext(T t) {
            if (!this.h) {
                if (this.i != 0) {
                    this.e.onNext(null);
                    return;
                }
                try {
                    this.e.onNext(io.reactivex.internal.functions.a.a(this.a.apply(t), "The mapper function returned a null value."));
                } catch (Throwable th) {
                    a(th);
                }
            }
        }

        public boolean tryOnNext(T t) {
            if (this.h) {
                return false;
            }
            try {
                return this.e.tryOnNext(io.reactivex.internal.functions.a.a(this.a.apply(t), "The mapper function returned a null value."));
            } catch (Throwable th) {
                a(th);
                return true;
            }
        }

        public int requestFusion(int i) {
            return a(i);
        }

        public U poll() throws Exception {
            Object poll = this.g.poll();
            if (poll != null) {
                return io.reactivex.internal.functions.a.a(this.a.apply(poll), "The mapper function returned a null value.");
            }
            return null;
        }
    }

    /* compiled from: FlowableMap */
    static final class b<T, U> extends io.reactivex.internal.subscribers.b<T, U> {
        final g<? super T, ? extends U> a;

        b(c<? super U> cVar, g<? super T, ? extends U> gVar) {
            super(cVar);
            this.a = gVar;
        }

        public void onNext(T t) {
            if (!this.h) {
                if (this.i != 0) {
                    this.e.onNext(null);
                    return;
                }
                try {
                    this.e.onNext(io.reactivex.internal.functions.a.a(this.a.apply(t), "The mapper function returned a null value."));
                } catch (Throwable th) {
                    a(th);
                }
            }
        }

        public int requestFusion(int i) {
            return a(i);
        }

        public U poll() throws Exception {
            Object poll = this.g.poll();
            if (poll != null) {
                return io.reactivex.internal.functions.a.a(this.a.apply(poll), "The mapper function returned a null value.");
            }
            return null;
        }
    }

    public d(io.reactivex.g<T> gVar, g<? super T, ? extends U> gVar2) {
        super(gVar);
        this.c = gVar2;
    }

    /* access modifiers changed from: protected */
    public void a(c<? super U> cVar) {
        if (cVar instanceof io.reactivex.internal.a.a) {
            this.b.a((j<? super T>) new a<Object>((io.reactivex.internal.a.a) cVar, this.c));
        } else {
            this.b.a((j<? super T>) new b<Object>(cVar, this.c));
        }
    }
}
