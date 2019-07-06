package io.reactivex.internal.operators.completable;

import io.reactivex.a;
import io.reactivex.c;
import io.reactivex.disposables.Disposable;
import io.reactivex.e;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public final class CompletableCache extends a implements c {
    static final InnerCompletableCache[] a = new InnerCompletableCache[0];
    static final InnerCompletableCache[] b = new InnerCompletableCache[0];
    final e c;
    final AtomicReference<InnerCompletableCache[]> d;
    final AtomicBoolean e;
    Throwable f;

    final class InnerCompletableCache extends AtomicBoolean implements Disposable {
        private static final long serialVersionUID = 8943152917179642732L;
        final c actual;

        InnerCompletableCache(c cVar) {
            this.actual = cVar;
        }

        public boolean isDisposed() {
            return get();
        }

        public void dispose() {
            if (compareAndSet(false, true)) {
                CompletableCache.this.b(this);
            }
        }
    }

    public void onSubscribe(Disposable disposable) {
    }

    /* access modifiers changed from: protected */
    public void b(c cVar) {
        InnerCompletableCache innerCompletableCache = new InnerCompletableCache(cVar);
        cVar.onSubscribe(innerCompletableCache);
        if (a(innerCompletableCache)) {
            if (innerCompletableCache.isDisposed()) {
                b(innerCompletableCache);
            }
            if (this.e.compareAndSet(false, true)) {
                this.c.a(this);
                return;
            }
            return;
        }
        Throwable th = this.f;
        if (th != null) {
            cVar.onError(th);
        } else {
            cVar.onComplete();
        }
    }

    public void onError(Throwable th) {
        InnerCompletableCache[] innerCompletableCacheArr;
        this.f = th;
        for (InnerCompletableCache innerCompletableCache : (InnerCompletableCache[]) this.d.getAndSet(b)) {
            if (!innerCompletableCache.get()) {
                innerCompletableCache.actual.onError(th);
            }
        }
    }

    public void onComplete() {
        InnerCompletableCache[] innerCompletableCacheArr;
        for (InnerCompletableCache innerCompletableCache : (InnerCompletableCache[]) this.d.getAndSet(b)) {
            if (!innerCompletableCache.get()) {
                innerCompletableCache.actual.onComplete();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean a(InnerCompletableCache innerCompletableCache) {
        InnerCompletableCache[] innerCompletableCacheArr;
        InnerCompletableCache[] innerCompletableCacheArr2;
        do {
            innerCompletableCacheArr = (InnerCompletableCache[]) this.d.get();
            if (innerCompletableCacheArr == b) {
                return false;
            }
            int length = innerCompletableCacheArr.length;
            innerCompletableCacheArr2 = new InnerCompletableCache[(length + 1)];
            System.arraycopy(innerCompletableCacheArr, 0, innerCompletableCacheArr2, 0, length);
            innerCompletableCacheArr2[length] = innerCompletableCache;
        } while (!this.d.compareAndSet(innerCompletableCacheArr, innerCompletableCacheArr2));
        return true;
    }

    /* access modifiers changed from: 0000 */
    public void b(InnerCompletableCache innerCompletableCache) {
        InnerCompletableCache[] innerCompletableCacheArr;
        InnerCompletableCache[] innerCompletableCacheArr2;
        do {
            innerCompletableCacheArr = (InnerCompletableCache[]) this.d.get();
            int length = innerCompletableCacheArr.length;
            if (length != 0) {
                int i = -1;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    } else if (innerCompletableCacheArr[i2] == innerCompletableCache) {
                        i = i2;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (i >= 0) {
                    if (length == 1) {
                        innerCompletableCacheArr2 = a;
                    } else {
                        InnerCompletableCache[] innerCompletableCacheArr3 = new InnerCompletableCache[(length - 1)];
                        System.arraycopy(innerCompletableCacheArr, 0, innerCompletableCacheArr3, 0, i);
                        System.arraycopy(innerCompletableCacheArr, i + 1, innerCompletableCacheArr3, i, (length - i) - 1);
                        innerCompletableCacheArr2 = innerCompletableCacheArr3;
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        } while (!this.d.compareAndSet(innerCompletableCacheArr, innerCompletableCacheArr2));
    }
}
