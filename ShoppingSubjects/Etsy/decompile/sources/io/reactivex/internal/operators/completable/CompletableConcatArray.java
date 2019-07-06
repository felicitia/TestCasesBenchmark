package io.reactivex.internal.operators.completable;

import io.reactivex.a;
import io.reactivex.c;
import io.reactivex.disposables.Disposable;
import io.reactivex.e;
import io.reactivex.internal.disposables.SequentialDisposable;
import java.util.concurrent.atomic.AtomicInteger;

public final class CompletableConcatArray extends a {
    final e[] a;

    static final class ConcatInnerObserver extends AtomicInteger implements c {
        private static final long serialVersionUID = -7965400327305809232L;
        final c actual;
        int index;
        final SequentialDisposable sd = new SequentialDisposable();
        final e[] sources;

        ConcatInnerObserver(c cVar, e[] eVarArr) {
            this.actual = cVar;
            this.sources = eVarArr;
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
                e[] eVarArr = this.sources;
                while (!this.sd.isDisposed()) {
                    int i = this.index;
                    this.index = i + 1;
                    if (i == eVarArr.length) {
                        this.actual.onComplete();
                        return;
                    }
                    eVarArr[i].a(this);
                    if (decrementAndGet() == 0) {
                        return;
                    }
                }
            }
        }
    }

    public void b(c cVar) {
        ConcatInnerObserver concatInnerObserver = new ConcatInnerObserver(cVar, this.a);
        cVar.onSubscribe(concatInnerObserver.sd);
        concatInnerObserver.next();
    }
}
