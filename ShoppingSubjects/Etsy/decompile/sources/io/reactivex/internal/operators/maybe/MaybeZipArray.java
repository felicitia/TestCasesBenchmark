package io.reactivex.internal.operators.maybe;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.g;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.k;
import io.reactivex.m;
import io.reactivex.o;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class MaybeZipArray<T, R> extends k<R> {
    final o<? extends T>[] a;
    final g<? super Object[], ? extends R> b;

    static final class ZipCoordinator<T, R> extends AtomicInteger implements Disposable {
        private static final long serialVersionUID = -5556924161382950569L;
        final m<? super R> actual;
        final ZipMaybeObserver<T>[] observers;
        final Object[] values;
        final g<? super Object[], ? extends R> zipper;

        ZipCoordinator(m<? super R> mVar, int i, g<? super Object[], ? extends R> gVar) {
            super(i);
            this.actual = mVar;
            this.zipper = gVar;
            ZipMaybeObserver<T>[] zipMaybeObserverArr = new ZipMaybeObserver[i];
            for (int i2 = 0; i2 < i; i2++) {
                zipMaybeObserverArr[i2] = new ZipMaybeObserver<>(this, i2);
            }
            this.observers = zipMaybeObserverArr;
            this.values = new Object[i];
        }

        public boolean isDisposed() {
            return get() <= 0;
        }

        public void dispose() {
            if (getAndSet(0) > 0) {
                for (ZipMaybeObserver<T> dispose : this.observers) {
                    dispose.dispose();
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void innerSuccess(T t, int i) {
            this.values[i] = t;
            if (decrementAndGet() == 0) {
                try {
                    this.actual.onSuccess(io.reactivex.internal.functions.a.a(this.zipper.apply(this.values), "The zipper returned a null value"));
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    this.actual.onError(th);
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void disposeExcept(int i) {
            ZipMaybeObserver<T>[] zipMaybeObserverArr = this.observers;
            int length = zipMaybeObserverArr.length;
            for (int i2 = 0; i2 < i; i2++) {
                zipMaybeObserverArr[i2].dispose();
            }
            while (true) {
                i++;
                if (i < length) {
                    zipMaybeObserverArr[i].dispose();
                } else {
                    return;
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void innerError(Throwable th, int i) {
            if (getAndSet(0) > 0) {
                disposeExcept(i);
                this.actual.onError(th);
                return;
            }
            io.reactivex.d.a.a(th);
        }

        /* access modifiers changed from: 0000 */
        public void innerComplete(int i) {
            if (getAndSet(0) > 0) {
                disposeExcept(i);
                this.actual.onComplete();
            }
        }
    }

    static final class ZipMaybeObserver<T> extends AtomicReference<Disposable> implements m<T> {
        private static final long serialVersionUID = 3323743579927613702L;
        final int index;
        final ZipCoordinator<T, ?> parent;

        ZipMaybeObserver(ZipCoordinator<T, ?> zipCoordinator, int i) {
            this.parent = zipCoordinator;
            this.index = i;
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }

        public void onSubscribe(Disposable disposable) {
            DisposableHelper.setOnce(this, disposable);
        }

        public void onSuccess(T t) {
            this.parent.innerSuccess(t, this.index);
        }

        public void onError(Throwable th) {
            this.parent.innerError(th, this.index);
        }

        public void onComplete() {
            this.parent.innerComplete(this.index);
        }
    }

    final class a implements g<T, R> {
        a() {
        }

        public R apply(T t) throws Exception {
            return io.reactivex.internal.functions.a.a(MaybeZipArray.this.b.apply(new Object[]{t}), "The zipper returned a null value");
        }
    }

    /* access modifiers changed from: protected */
    public void b(m<? super R> mVar) {
        o<? extends T>[] oVarArr = this.a;
        int i = 0;
        int length = oVarArr.length;
        if (length == 1) {
            oVarArr[0].a(new a(mVar, new a()));
            return;
        }
        ZipCoordinator zipCoordinator = new ZipCoordinator(mVar, length, this.b);
        mVar.onSubscribe(zipCoordinator);
        while (i < length && !zipCoordinator.isDisposed()) {
            o<? extends T> oVar = oVarArr[i];
            if (oVar == null) {
                zipCoordinator.innerError(new NullPointerException("One of the sources is null"), i);
                return;
            } else {
                oVar.a(zipCoordinator.observers[i]);
                i++;
            }
        }
    }
}
