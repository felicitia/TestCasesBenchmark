package io.reactivex.internal.operators.single;

import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.a;
import io.reactivex.g;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.subscriptions.BasicIntQueueSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.b;
import io.reactivex.x;
import io.reactivex.z;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;
import org.reactivestreams.c;

public final class SingleFlatMapIterableFlowable<T, R> extends g<R> {
    final z<T> b;
    final io.reactivex.functions.g<? super T, ? extends Iterable<? extends R>> c;

    static final class FlatMapIterableObserver<T, R> extends BasicIntQueueSubscription<R> implements x<T> {
        private static final long serialVersionUID = -8938804753851907758L;
        final c<? super R> actual;
        volatile boolean cancelled;
        Disposable d;
        volatile Iterator<? extends R> it;
        final io.reactivex.functions.g<? super T, ? extends Iterable<? extends R>> mapper;
        boolean outputFused;
        final AtomicLong requested = new AtomicLong();

        FlatMapIterableObserver(c<? super R> cVar, io.reactivex.functions.g<? super T, ? extends Iterable<? extends R>> gVar) {
            this.actual = cVar;
            this.mapper = gVar;
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.d, disposable)) {
                this.d = disposable;
                this.actual.onSubscribe(this);
            }
        }

        public void onSuccess(T t) {
            try {
                Iterator<? extends R> it2 = ((Iterable) this.mapper.apply(t)).iterator();
                if (!it2.hasNext()) {
                    this.actual.onComplete();
                    return;
                }
                this.it = it2;
                drain();
            } catch (Throwable th) {
                a.b(th);
                this.actual.onError(th);
            }
        }

        public void onError(Throwable th) {
            this.d = DisposableHelper.DISPOSED;
            this.actual.onError(th);
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                b.a(this.requested, j);
                drain();
            }
        }

        public void cancel() {
            this.cancelled = true;
            this.d.dispose();
            this.d = DisposableHelper.DISPOSED;
        }

        /* access modifiers changed from: 0000 */
        public void drain() {
            if (getAndIncrement() == 0) {
                c<? super R> cVar = this.actual;
                Iterator<? extends R> it2 = this.it;
                if (!this.outputFused || it2 == null) {
                    int i = 1;
                    while (true) {
                        if (it2 != null) {
                            long j = this.requested.get();
                            if (j == Long.MAX_VALUE) {
                                slowPath(cVar, it2);
                                return;
                            }
                            long j2 = 0;
                            while (j2 != j) {
                                if (!this.cancelled) {
                                    try {
                                        cVar.onNext(io.reactivex.internal.functions.a.a(it2.next(), "The iterator returned a null value"));
                                        if (!this.cancelled) {
                                            long j3 = j2 + 1;
                                            try {
                                                if (!it2.hasNext()) {
                                                    cVar.onComplete();
                                                    return;
                                                }
                                                j2 = j3;
                                            } catch (Throwable th) {
                                                a.b(th);
                                                cVar.onError(th);
                                                return;
                                            }
                                        } else {
                                            return;
                                        }
                                    } catch (Throwable th2) {
                                        a.b(th2);
                                        cVar.onError(th2);
                                        return;
                                    }
                                } else {
                                    return;
                                }
                            }
                            if (j2 != 0) {
                                b.c(this.requested, j2);
                            }
                        }
                        i = addAndGet(-i);
                        if (i != 0) {
                            if (it2 == null) {
                                it2 = this.it;
                            }
                        } else {
                            return;
                        }
                    }
                } else {
                    cVar.onNext(null);
                    cVar.onComplete();
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void slowPath(c<? super R> cVar, Iterator<? extends R> it2) {
            while (!this.cancelled) {
                try {
                    cVar.onNext(it2.next());
                    if (!this.cancelled) {
                        try {
                            if (!it2.hasNext()) {
                                cVar.onComplete();
                                return;
                            }
                        } catch (Throwable th) {
                            a.b(th);
                            cVar.onError(th);
                            return;
                        }
                    } else {
                        return;
                    }
                } catch (Throwable th2) {
                    a.b(th2);
                    cVar.onError(th2);
                    return;
                }
            }
        }

        public int requestFusion(int i) {
            if ((i & 2) == 0) {
                return 0;
            }
            this.outputFused = true;
            return 2;
        }

        public void clear() {
            this.it = null;
        }

        public boolean isEmpty() {
            return this.it == null;
        }

        public R poll() throws Exception {
            Iterator<? extends R> it2 = this.it;
            if (it2 == null) {
                return null;
            }
            R a = io.reactivex.internal.functions.a.a(it2.next(), "The iterator returned a null value");
            if (!it2.hasNext()) {
                this.it = null;
            }
            return a;
        }
    }

    /* access modifiers changed from: protected */
    public void a(c<? super R> cVar) {
        this.b.a(new FlatMapIterableObserver(cVar, this.c));
    }
}
