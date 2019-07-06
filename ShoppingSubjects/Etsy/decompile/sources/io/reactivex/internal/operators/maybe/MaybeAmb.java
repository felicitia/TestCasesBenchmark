package io.reactivex.internal.operators.maybe;

import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.a;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.k;
import io.reactivex.m;
import io.reactivex.o;
import java.util.concurrent.atomic.AtomicBoolean;

public final class MaybeAmb<T> extends k<T> {
    private final o<? extends T>[] a;
    private final Iterable<? extends o<? extends T>> b;

    static final class AmbMaybeObserver<T> extends AtomicBoolean implements Disposable, m<T> {
        private static final long serialVersionUID = -7044685185359438206L;
        final m<? super T> actual;
        final a set = new a();

        AmbMaybeObserver(m<? super T> mVar) {
            this.actual = mVar;
        }

        public void dispose() {
            if (compareAndSet(false, true)) {
                this.set.dispose();
            }
        }

        public boolean isDisposed() {
            return get();
        }

        public void onSubscribe(Disposable disposable) {
            this.set.a(disposable);
        }

        public void onSuccess(T t) {
            if (compareAndSet(false, true)) {
                this.set.dispose();
                this.actual.onSuccess(t);
            }
        }

        public void onError(Throwable th) {
            if (compareAndSet(false, true)) {
                this.set.dispose();
                this.actual.onError(th);
                return;
            }
            io.reactivex.d.a.a(th);
        }

        public void onComplete() {
            if (compareAndSet(false, true)) {
                this.set.dispose();
                this.actual.onComplete();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void b(m<? super T> mVar) {
        int i;
        o<? extends T>[] oVarArr = this.a;
        int i2 = 0;
        if (oVarArr == null) {
            oVarArr = new o[8];
            try {
                i = 0;
                for (o<? extends T> oVar : this.b) {
                    if (oVar == null) {
                        EmptyDisposable.error((Throwable) new NullPointerException("One of the sources is null"), mVar);
                        return;
                    }
                    if (i == oVarArr.length) {
                        o<? extends T>[] oVarArr2 = new o[((i >> 2) + i)];
                        System.arraycopy(oVarArr, 0, oVarArr2, 0, i);
                        oVarArr = oVarArr2;
                    }
                    int i3 = i + 1;
                    oVarArr[i] = oVar;
                    i = i3;
                }
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                EmptyDisposable.error(th, mVar);
                return;
            }
        } else {
            i = oVarArr.length;
        }
        AmbMaybeObserver ambMaybeObserver = new AmbMaybeObserver(mVar);
        mVar.onSubscribe(ambMaybeObserver);
        while (i2 < i) {
            o<? extends T> oVar2 = oVarArr[i2];
            if (!ambMaybeObserver.isDisposed()) {
                if (oVar2 == null) {
                    ambMaybeObserver.onError(new NullPointerException("One of the MaybeSources is null"));
                    return;
                } else {
                    oVar2.a(ambMaybeObserver);
                    i2++;
                }
            } else {
                return;
            }
        }
        if (i == 0) {
            mVar.onComplete();
        }
    }
}
