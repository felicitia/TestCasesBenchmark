package io.reactivex.internal.operators.single;

import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.a;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.v;
import io.reactivex.x;
import io.reactivex.z;
import java.util.concurrent.atomic.AtomicBoolean;

public final class SingleAmb<T> extends v<T> {
    private final z<? extends T>[] a;
    private final Iterable<? extends z<? extends T>> b;

    static final class AmbSingleObserver<T> extends AtomicBoolean implements x<T> {
        private static final long serialVersionUID = -1944085461036028108L;
        final x<? super T> s;
        final a set;

        AmbSingleObserver(x<? super T> xVar, a aVar) {
            this.s = xVar;
            this.set = aVar;
        }

        public void onSubscribe(Disposable disposable) {
            this.set.a(disposable);
        }

        public void onSuccess(T t) {
            if (compareAndSet(false, true)) {
                this.set.dispose();
                this.s.onSuccess(t);
            }
        }

        public void onError(Throwable th) {
            if (compareAndSet(false, true)) {
                this.set.dispose();
                this.s.onError(th);
                return;
            }
            io.reactivex.d.a.a(th);
        }
    }

    /* access modifiers changed from: protected */
    public void b(x<? super T> xVar) {
        int i;
        z<? extends T>[] zVarArr = this.a;
        if (zVarArr == null) {
            zVarArr = new z[8];
            try {
                i = 0;
                for (z<? extends T> zVar : this.b) {
                    if (zVar == null) {
                        EmptyDisposable.error((Throwable) new NullPointerException("One of the sources is null"), xVar);
                        return;
                    }
                    if (i == zVarArr.length) {
                        z<? extends T>[] zVarArr2 = new z[((i >> 2) + i)];
                        System.arraycopy(zVarArr, 0, zVarArr2, 0, i);
                        zVarArr = zVarArr2;
                    }
                    int i2 = i + 1;
                    zVarArr[i] = zVar;
                    i = i2;
                }
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                EmptyDisposable.error(th, xVar);
                return;
            }
        } else {
            i = zVarArr.length;
        }
        a aVar = new a();
        AmbSingleObserver ambSingleObserver = new AmbSingleObserver(xVar, aVar);
        xVar.onSubscribe(aVar);
        int i3 = 0;
        while (i3 < i) {
            z<? extends T> zVar2 = zVarArr[i3];
            if (!ambSingleObserver.get()) {
                if (zVar2 == null) {
                    aVar.dispose();
                    NullPointerException nullPointerException = new NullPointerException("One of the sources is null");
                    if (ambSingleObserver.compareAndSet(false, true)) {
                        xVar.onError(nullPointerException);
                    } else {
                        io.reactivex.d.a.a((Throwable) nullPointerException);
                    }
                    return;
                }
                zVar2.a(ambSingleObserver);
                i3++;
            } else {
                return;
            }
        }
    }
}
