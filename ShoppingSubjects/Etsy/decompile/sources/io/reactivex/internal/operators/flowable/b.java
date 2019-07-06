package io.reactivex.internal.operators.flowable;

import io.reactivex.functions.d;
import io.reactivex.functions.g;
import io.reactivex.j;
import org.reactivestreams.c;

/* compiled from: FlowableDistinctUntilChanged */
public final class b<T, K> extends a<T, T> {
    final g<? super T, K> c;
    final d<? super K, ? super K> d;

    /* compiled from: FlowableDistinctUntilChanged */
    static final class a<T, K> extends io.reactivex.internal.subscribers.a<T, T> {
        final g<? super T, K> a;
        final d<? super K, ? super K> b;
        K c;
        boolean d;

        a(io.reactivex.internal.a.a<? super T> aVar, g<? super T, K> gVar, d<? super K, ? super K> dVar) {
            super(aVar);
            this.a = gVar;
            this.b = dVar;
        }

        public void onNext(T t) {
            if (!tryOnNext(t)) {
                this.f.request(1);
            }
        }

        public boolean tryOnNext(T t) {
            if (this.h) {
                return false;
            }
            if (this.i != 0) {
                return this.e.tryOnNext(t);
            }
            try {
                K apply = this.a.apply(t);
                if (this.d) {
                    boolean a2 = this.b.a(this.c, apply);
                    this.c = apply;
                    if (a2) {
                        return false;
                    }
                } else {
                    this.d = true;
                    this.c = apply;
                }
                this.e.onNext(t);
                return true;
            } catch (Throwable th) {
                a(th);
                return true;
            }
        }

        public int requestFusion(int i) {
            return a(i);
        }

        public T poll() throws Exception {
            while (true) {
                T poll = this.g.poll();
                if (poll == null) {
                    return null;
                }
                K apply = this.a.apply(poll);
                if (!this.d) {
                    this.d = true;
                    this.c = apply;
                    return poll;
                } else if (!this.b.a(this.c, apply)) {
                    this.c = apply;
                    return poll;
                } else {
                    this.c = apply;
                    if (this.i != 1) {
                        this.f.request(1);
                    }
                }
            }
        }
    }

    /* renamed from: io.reactivex.internal.operators.flowable.b$b reason: collision with other inner class name */
    /* compiled from: FlowableDistinctUntilChanged */
    static final class C0186b<T, K> extends io.reactivex.internal.subscribers.b<T, T> implements io.reactivex.internal.a.a<T> {
        final g<? super T, K> a;
        final d<? super K, ? super K> b;
        K c;
        boolean d;

        C0186b(c<? super T> cVar, g<? super T, K> gVar, d<? super K, ? super K> dVar) {
            super(cVar);
            this.a = gVar;
            this.b = dVar;
        }

        public void onNext(T t) {
            if (!tryOnNext(t)) {
                this.f.request(1);
            }
        }

        public boolean tryOnNext(T t) {
            if (this.h) {
                return false;
            }
            if (this.i != 0) {
                this.e.onNext(t);
                return true;
            }
            try {
                K apply = this.a.apply(t);
                if (this.d) {
                    boolean a2 = this.b.a(this.c, apply);
                    this.c = apply;
                    if (a2) {
                        return false;
                    }
                } else {
                    this.d = true;
                    this.c = apply;
                }
                this.e.onNext(t);
                return true;
            } catch (Throwable th) {
                a(th);
                return true;
            }
        }

        public int requestFusion(int i) {
            return a(i);
        }

        public T poll() throws Exception {
            while (true) {
                T poll = this.g.poll();
                if (poll == null) {
                    return null;
                }
                K apply = this.a.apply(poll);
                if (!this.d) {
                    this.d = true;
                    this.c = apply;
                    return poll;
                } else if (!this.b.a(this.c, apply)) {
                    this.c = apply;
                    return poll;
                } else {
                    this.c = apply;
                    if (this.i != 1) {
                        this.f.request(1);
                    }
                }
            }
        }
    }

    public b(io.reactivex.g<T> gVar, g<? super T, K> gVar2, d<? super K, ? super K> dVar) {
        super(gVar);
        this.c = gVar2;
        this.d = dVar;
    }

    /* access modifiers changed from: protected */
    public void a(c<? super T> cVar) {
        if (cVar instanceof io.reactivex.internal.a.a) {
            this.b.a((j<? super T>) new a<Object>((io.reactivex.internal.a.a) cVar, this.c, this.d));
            return;
        }
        this.b.a((j<? super T>) new C0186b<Object>(cVar, this.c, this.d));
    }
}
