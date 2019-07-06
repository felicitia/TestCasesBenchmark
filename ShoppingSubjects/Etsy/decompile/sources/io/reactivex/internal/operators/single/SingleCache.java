package io.reactivex.internal.operators.single;

import io.reactivex.disposables.Disposable;
import io.reactivex.v;
import io.reactivex.x;
import io.reactivex.z;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class SingleCache<T> extends v<T> implements x<T> {
    static final CacheDisposable[] a = new CacheDisposable[0];
    static final CacheDisposable[] b = new CacheDisposable[0];
    final z<? extends T> c;
    final AtomicInteger d;
    final AtomicReference<CacheDisposable<T>[]> e;
    T f;
    Throwable g;

    static final class CacheDisposable<T> extends AtomicBoolean implements Disposable {
        private static final long serialVersionUID = 7514387411091976596L;
        final x<? super T> actual;
        final SingleCache<T> parent;

        CacheDisposable(x<? super T> xVar, SingleCache<T> singleCache) {
            this.actual = xVar;
            this.parent = singleCache;
        }

        public boolean isDisposed() {
            return get();
        }

        public void dispose() {
            if (compareAndSet(false, true)) {
                this.parent.b(this);
            }
        }
    }

    public void onSubscribe(Disposable disposable) {
    }

    /* access modifiers changed from: protected */
    public void b(x<? super T> xVar) {
        CacheDisposable cacheDisposable = new CacheDisposable(xVar, this);
        xVar.onSubscribe(cacheDisposable);
        if (a(cacheDisposable)) {
            if (cacheDisposable.isDisposed()) {
                b(cacheDisposable);
            }
            if (this.d.getAndIncrement() == 0) {
                this.c.a(this);
            }
            return;
        }
        Throwable th = this.g;
        if (th != null) {
            xVar.onError(th);
        } else {
            xVar.onSuccess(this.f);
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean a(CacheDisposable<T> cacheDisposable) {
        CacheDisposable[] cacheDisposableArr;
        CacheDisposable[] cacheDisposableArr2;
        do {
            cacheDisposableArr = (CacheDisposable[]) this.e.get();
            if (cacheDisposableArr == b) {
                return false;
            }
            int length = cacheDisposableArr.length;
            cacheDisposableArr2 = new CacheDisposable[(length + 1)];
            System.arraycopy(cacheDisposableArr, 0, cacheDisposableArr2, 0, length);
            cacheDisposableArr2[length] = cacheDisposable;
        } while (!this.e.compareAndSet(cacheDisposableArr, cacheDisposableArr2));
        return true;
    }

    /* access modifiers changed from: 0000 */
    public void b(CacheDisposable<T> cacheDisposable) {
        CacheDisposable<T>[] cacheDisposableArr;
        CacheDisposable[] cacheDisposableArr2;
        do {
            cacheDisposableArr = (CacheDisposable[]) this.e.get();
            int length = cacheDisposableArr.length;
            if (length != 0) {
                int i = -1;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    } else if (cacheDisposableArr[i2] == cacheDisposable) {
                        i = i2;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (i >= 0) {
                    if (length == 1) {
                        cacheDisposableArr2 = a;
                    } else {
                        CacheDisposable[] cacheDisposableArr3 = new CacheDisposable[(length - 1)];
                        System.arraycopy(cacheDisposableArr, 0, cacheDisposableArr3, 0, i);
                        System.arraycopy(cacheDisposableArr, i + 1, cacheDisposableArr3, i, (length - i) - 1);
                        cacheDisposableArr2 = cacheDisposableArr3;
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        } while (!this.e.compareAndSet(cacheDisposableArr, cacheDisposableArr2));
    }

    public void onSuccess(T t) {
        CacheDisposable[] cacheDisposableArr;
        this.f = t;
        for (CacheDisposable cacheDisposable : (CacheDisposable[]) this.e.getAndSet(b)) {
            if (!cacheDisposable.isDisposed()) {
                cacheDisposable.actual.onSuccess(t);
            }
        }
    }

    public void onError(Throwable th) {
        CacheDisposable[] cacheDisposableArr;
        this.g = th;
        for (CacheDisposable cacheDisposable : (CacheDisposable[]) this.e.getAndSet(b)) {
            if (!cacheDisposable.isDisposed()) {
                cacheDisposable.actual.onError(th);
            }
        }
    }
}
