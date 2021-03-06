package io.reactivex.internal.operators.completable;

import io.reactivex.a;
import io.reactivex.c;
import io.reactivex.disposables.Disposable;
import io.reactivex.e;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.disposables.SequentialDisposable;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

public final class CompletableConcatIterable extends a {
    final Iterable<? extends e> a;

    static final class ConcatInnerObserver extends AtomicInteger implements c {
        private static final long serialVersionUID = -7965400327305809232L;
        final c actual;
        final SequentialDisposable sd = new SequentialDisposable();
        final Iterator<? extends e> sources;

        ConcatInnerObserver(c cVar, Iterator<? extends e> it) {
            this.actual = cVar;
            this.sources = it;
        }

        public void onSubscribe(Disposable disposable) {
            this.sd.update(disposable);
        }

        public void onError(Throwable th) {
            this.actual.onError(th);
        }

        public void onComplete() {
            next();
        }

        /* access modifiers changed from: 0000 */
        public void next() {
            if (!this.sd.isDisposed() && getAndIncrement() == 0) {
                Iterator<? extends e> it = this.sources;
                while (!this.sd.isDisposed()) {
                    try {
                        if (!it.hasNext()) {
                            this.actual.onComplete();
                            return;
                        }
                        try {
                            ((e) io.reactivex.internal.functions.a.a(it.next(), "The CompletableSource returned is null")).a(this);
                            if (decrementAndGet() == 0) {
                                return;
                            }
                        } catch (Throwable th) {
                            io.reactivex.exceptions.a.b(th);
                            this.actual.onError(th);
                            return;
                        }
                    } catch (Throwable th2) {
                        io.reactivex.exceptions.a.b(th2);
                        this.actual.onError(th2);
                        return;
                    }
                }
            }
        }
    }

    public void b(c cVar) {
        try {
            ConcatInnerObserver concatInnerObserver = new ConcatInnerObserver(cVar, (Iterator) io.reactivex.internal.functions.a.a(this.a.iterator(), "The iterator returned is null"));
            cVar.onSubscribe(concatInnerObserver.sd);
            concatInnerObserver.next();
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            EmptyDisposable.error(th, cVar);
        }
    }
}
