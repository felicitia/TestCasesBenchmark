package io.reactivex.internal.operators.observable;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.g;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.e;
import io.reactivex.t;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;

public final class ObservableWithLatestFromMany<T, R> extends a<T, R> {
    final t<?>[] b;
    final Iterable<? extends t<?>> c;
    final g<? super Object[], R> d;

    static final class WithLatestFromObserver<T, R> extends AtomicInteger implements Observer<T>, Disposable {
        private static final long serialVersionUID = 1577321883966341961L;
        final Observer<? super R> actual;
        final g<? super Object[], R> combiner;
        final AtomicReference<Disposable> d;
        volatile boolean done;
        final AtomicThrowable error;
        final WithLatestInnerObserver[] observers;
        final AtomicReferenceArray<Object> values;

        WithLatestFromObserver(Observer<? super R> observer, g<? super Object[], R> gVar, int i) {
            this.actual = observer;
            this.combiner = gVar;
            WithLatestInnerObserver[] withLatestInnerObserverArr = new WithLatestInnerObserver[i];
            for (int i2 = 0; i2 < i; i2++) {
                withLatestInnerObserverArr[i2] = new WithLatestInnerObserver(this, i2);
            }
            this.observers = withLatestInnerObserverArr;
            this.values = new AtomicReferenceArray<>(i);
            this.d = new AtomicReference<>();
            this.error = new AtomicThrowable();
        }

        /* access modifiers changed from: 0000 */
        public void subscribe(t<?>[] tVarArr, int i) {
            WithLatestInnerObserver[] withLatestInnerObserverArr = this.observers;
            AtomicReference<Disposable> atomicReference = this.d;
            for (int i2 = 0; i2 < i && !DisposableHelper.isDisposed((Disposable) atomicReference.get()) && !this.done; i2++) {
                tVarArr[i2].subscribe(withLatestInnerObserverArr[i2]);
            }
        }

        public void onSubscribe(Disposable disposable) {
            DisposableHelper.setOnce(this.d, disposable);
        }

        public void onNext(T t) {
            if (!this.done) {
                AtomicReferenceArray<Object> atomicReferenceArray = this.values;
                int length = atomicReferenceArray.length();
                Object[] objArr = new Object[(length + 1)];
                int i = 0;
                objArr[0] = t;
                while (i < length) {
                    Object obj = atomicReferenceArray.get(i);
                    if (obj != null) {
                        i++;
                        objArr[i] = obj;
                    } else {
                        return;
                    }
                }
                try {
                    e.a(this.actual, io.reactivex.internal.functions.a.a(this.combiner.apply(objArr), "combiner returned a null value"), (AtomicInteger) this, this.error);
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    dispose();
                    onError(th);
                }
            }
        }

        public void onError(Throwable th) {
            if (this.done) {
                io.reactivex.d.a.a(th);
                return;
            }
            this.done = true;
            cancelAllBut(-1);
            e.a(this.actual, th, (AtomicInteger) this, this.error);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                cancelAllBut(-1);
                e.a(this.actual, (AtomicInteger) this, this.error);
            }
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((Disposable) this.d.get());
        }

        public void dispose() {
            DisposableHelper.dispose(this.d);
            for (WithLatestInnerObserver dispose : this.observers) {
                dispose.dispose();
            }
        }

        /* access modifiers changed from: 0000 */
        public void innerNext(int i, Object obj) {
            this.values.set(i, obj);
        }

        /* access modifiers changed from: 0000 */
        public void innerError(int i, Throwable th) {
            this.done = true;
            DisposableHelper.dispose(this.d);
            cancelAllBut(i);
            e.a(this.actual, th, (AtomicInteger) this, this.error);
        }

        /* access modifiers changed from: 0000 */
        public void innerComplete(int i, boolean z) {
            if (!z) {
                this.done = true;
                cancelAllBut(i);
                e.a(this.actual, (AtomicInteger) this, this.error);
            }
        }

        /* access modifiers changed from: 0000 */
        public void cancelAllBut(int i) {
            WithLatestInnerObserver[] withLatestInnerObserverArr = this.observers;
            for (int i2 = 0; i2 < withLatestInnerObserverArr.length; i2++) {
                if (i2 != i) {
                    withLatestInnerObserverArr[i2].dispose();
                }
            }
        }
    }

    static final class WithLatestInnerObserver extends AtomicReference<Disposable> implements Observer<Object> {
        private static final long serialVersionUID = 3256684027868224024L;
        boolean hasValue;
        final int index;
        final WithLatestFromObserver<?, ?> parent;

        WithLatestInnerObserver(WithLatestFromObserver<?, ?> withLatestFromObserver, int i) {
            this.parent = withLatestFromObserver;
            this.index = i;
        }

        public void onSubscribe(Disposable disposable) {
            DisposableHelper.setOnce(this, disposable);
        }

        public void onNext(Object obj) {
            if (!this.hasValue) {
                this.hasValue = true;
            }
            this.parent.innerNext(this.index, obj);
        }

        public void onError(Throwable th) {
            this.parent.innerError(this.index, th);
        }

        public void onComplete() {
            this.parent.innerComplete(this.index, this.hasValue);
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }
    }

    final class a implements g<T, R> {
        a() {
        }

        public R apply(T t) throws Exception {
            return io.reactivex.internal.functions.a.a(ObservableWithLatestFromMany.this.d.apply(new Object[]{t}), "The combiner returned a null value");
        }
    }

    /* access modifiers changed from: protected */
    public void a(Observer<? super R> observer) {
        int i;
        t<?>[] tVarArr = this.b;
        if (tVarArr == null) {
            tVarArr = new t[8];
            try {
                i = 0;
                for (t<?> tVar : this.c) {
                    if (i == tVarArr.length) {
                        tVarArr = (t[]) Arrays.copyOf(tVarArr, (i >> 1) + i);
                    }
                    int i2 = i + 1;
                    tVarArr[i] = tVar;
                    i = i2;
                }
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                EmptyDisposable.error(th, observer);
                return;
            }
        } else {
            i = tVarArr.length;
        }
        if (i == 0) {
            new i(this.a, new a()).a(observer);
            return;
        }
        WithLatestFromObserver withLatestFromObserver = new WithLatestFromObserver(observer, this.d, i);
        observer.onSubscribe(withLatestFromObserver);
        withLatestFromObserver.subscribe(tVarArr, i);
        this.a.subscribe(withLatestFromObserver);
    }
}
