package io.reactivex.internal.operators.completable;

import io.reactivex.a;
import io.reactivex.c;
import io.reactivex.disposables.Disposable;
import io.reactivex.e;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public final class CompletableMergeArray extends a {
    final e[] a;

    static final class InnerCompletableObserver extends AtomicInteger implements c {
        private static final long serialVersionUID = -8360547806504310570L;
        final c actual;
        final AtomicBoolean once;
        final io.reactivex.disposables.a set;

        InnerCompletableObserver(c cVar, AtomicBoolean atomicBoolean, io.reactivex.disposables.a aVar, int i) {
            this.actual = cVar;
            this.once = atomicBoolean;
            this.set = aVar;
            lazySet(i);
        }

        public void onSubscribe(Disposable disposable) {
            this.set.a(disposable);
        }

        public void onError(Throwable th) {
            this.set.dispose();
            if (this.once.compareAndSet(false, true)) {
                this.actual.onError(th);
            } else {
                io.reactivex.d.a.a(th);
            }
        }

        public void onComplete() {
            if (decrementAndGet() == 0 && this.once.compareAndSet(false, true)) {
                this.actual.onComplete();
            }
        }
    }

    public void b(c cVar) {
        io.reactivex.disposables.a aVar = new io.reactivex.disposables.a();
        int i = 0;
        InnerCompletableObserver innerCompletableObserver = new InnerCompletableObserver(cVar, new AtomicBoolean(), aVar, this.a.length + 1);
        cVar.onSubscribe(aVar);
        e[] eVarArr = this.a;
        int length = eVarArr.length;
        while (i < length) {
            e eVar = eVarArr[i];
            if (!aVar.isDisposed()) {
                if (eVar == null) {
                    aVar.dispose();
                    innerCompletableObserver.onError(new NullPointerException("A completable source is null"));
                    return;
                }
                eVar.a(innerCompletableObserver);
                i++;
            } else {
                return;
            }
        }
        innerCompletableObserver.onComplete();
    }
}
