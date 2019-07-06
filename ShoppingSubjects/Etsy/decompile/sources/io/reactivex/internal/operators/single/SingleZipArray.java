package io.reactivex.internal.operators.single;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.g;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.v;
import io.reactivex.x;
import io.reactivex.z;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class SingleZipArray<T, R> extends v<R> {
    final z<? extends T>[] a;
    final g<? super Object[], ? extends R> b;

    static final class ZipCoordinator<T, R> extends AtomicInteger implements Disposable {
        private static final long serialVersionUID = -5556924161382950569L;
        final x<? super R> actual;
        final ZipSingleObserver<T>[] observers;
        final Object[] values;
        final g<? super Object[], ? extends R> zipper;

        ZipCoordinator(x<? super R> xVar, int i, g<? super Object[], ? extends R> gVar) {
            super(i);
            this.actual = xVar;
            this.zipper = gVar;
            ZipSingleObserver<T>[] zipSingleObserverArr = new ZipSingleObserver[i];
            for (int i2 = 0; i2 < i; i2++) {
                zipSingleObserverArr[i2] = new ZipSingleObserver<>(this, i2);
            }
            this.observers = zipSingleObserverArr;
            this.values = new Object[i];
        }

        public boolean isDisposed() {
            return get() <= 0;
        }

        public void dispose() {
            if (getAndSet(0) > 0) {
                for (ZipSingleObserver<T> dispose : this.observers) {
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
            ZipSingleObserver<T>[] zipSingleObserverArr = this.observers;
            int length = zipSingleObserverArr.length;
            for (int i2 = 0; i2 < i; i2++) {
                zipSingleObserverArr[i2].dispose();
            }
            while (true) {
                i++;
                if (i < length) {
                    zipSingleObserverArr[i].dispose();
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
    }

    static final class ZipSingleObserver<T> extends AtomicReference<Disposable> implements x<T> {
        private static final long serialVersionUID = 3323743579927613702L;
        final int index;
        final ZipCoordinator<T, ?> parent;

        ZipSingleObserver(ZipCoordinator<T, ?> zipCoordinator, int i) {
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
    }

    final class a implements g<T, R> {
        a() {
        }

        public R apply(T t) throws Exception {
            return io.reactivex.internal.functions.a.a(SingleZipArray.this.b.apply(new Object[]{t}), "The zipper returned a null value");
        }
    }

    /* access modifiers changed from: protected */
    public void b(x<? super R> xVar) {
        z<? extends T>[] zVarArr = this.a;
        int i = 0;
        int length = zVarArr.length;
        if (length == 1) {
            zVarArr[0].a(new a(xVar, new a()));
            return;
        }
        ZipCoordinator zipCoordinator = new ZipCoordinator(xVar, length, this.b);
        xVar.onSubscribe(zipCoordinator);
        while (i < length && !zipCoordinator.isDisposed()) {
            z<? extends T> zVar = zVarArr[i];
            if (zVar == null) {
                zipCoordinator.innerError(new NullPointerException("One of the sources is null"), i);
                return;
            } else {
                zVar.a(zipCoordinator.observers[i]);
                i++;
            }
        }
    }
}
