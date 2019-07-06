package io.reactivex.internal.operators.observable;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.q;
import io.reactivex.t;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableAmb<T> extends q<T> {
    final t<? extends T>[] a;
    final Iterable<? extends t<? extends T>> b;

    static final class AmbInnerObserver<T> extends AtomicReference<Disposable> implements Observer<T> {
        private static final long serialVersionUID = -1185974347409665484L;
        final Observer<? super T> actual;
        final int index;
        final a<T> parent;
        boolean won;

        AmbInnerObserver(a<T> aVar, int i, Observer<? super T> observer) {
            this.parent = aVar;
            this.index = i;
            this.actual = observer;
        }

        public void onSubscribe(Disposable disposable) {
            DisposableHelper.setOnce(this, disposable);
        }

        public void onNext(T t) {
            if (this.won) {
                this.actual.onNext(t);
            } else if (this.parent.a(this.index)) {
                this.won = true;
                this.actual.onNext(t);
            } else {
                ((Disposable) get()).dispose();
            }
        }

        public void onError(Throwable th) {
            if (this.won) {
                this.actual.onError(th);
            } else if (this.parent.a(this.index)) {
                this.won = true;
                this.actual.onError(th);
            } else {
                io.reactivex.d.a.a(th);
            }
        }

        public void onComplete() {
            if (this.won) {
                this.actual.onComplete();
            } else if (this.parent.a(this.index)) {
                this.won = true;
                this.actual.onComplete();
            }
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }
    }

    static final class a<T> implements Disposable {
        final Observer<? super T> a;
        final AmbInnerObserver<T>[] b;
        final AtomicInteger c = new AtomicInteger();

        a(Observer<? super T> observer, int i) {
            this.a = observer;
            this.b = new AmbInnerObserver[i];
        }

        public void a(t<? extends T>[] tVarArr) {
            AmbInnerObserver<T>[] ambInnerObserverArr = this.b;
            int length = ambInnerObserverArr.length;
            int i = 0;
            while (i < length) {
                int i2 = i + 1;
                ambInnerObserverArr[i] = new AmbInnerObserver<>(this, i2, this.a);
                i = i2;
            }
            this.c.lazySet(0);
            this.a.onSubscribe(this);
            for (int i3 = 0; i3 < length && this.c.get() == 0; i3++) {
                tVarArr[i3].subscribe(ambInnerObserverArr[i3]);
            }
        }

        public boolean a(int i) {
            int i2 = this.c.get();
            boolean z = true;
            int i3 = 0;
            if (i2 != 0) {
                if (i2 != i) {
                    z = false;
                }
                return z;
            } else if (!this.c.compareAndSet(0, i)) {
                return false;
            } else {
                AmbInnerObserver<T>[] ambInnerObserverArr = this.b;
                int length = ambInnerObserverArr.length;
                while (i3 < length) {
                    int i4 = i3 + 1;
                    if (i4 != i) {
                        ambInnerObserverArr[i3].dispose();
                    }
                    i3 = i4;
                }
                return true;
            }
        }

        public void dispose() {
            if (this.c.get() != -1) {
                this.c.lazySet(-1);
                for (AmbInnerObserver<T> dispose : this.b) {
                    dispose.dispose();
                }
            }
        }

        public boolean isDisposed() {
            return this.c.get() == -1;
        }
    }

    public void a(Observer<? super T> observer) {
        int i;
        t<? extends T>[] tVarArr = this.a;
        if (tVarArr == null) {
            tVarArr = new q[8];
            try {
                i = 0;
                for (t<? extends T> tVar : this.b) {
                    if (tVar == null) {
                        EmptyDisposable.error((Throwable) new NullPointerException("One of the sources is null"), observer);
                        return;
                    }
                    if (i == tVarArr.length) {
                        t<? extends T>[] tVarArr2 = new t[((i >> 2) + i)];
                        System.arraycopy(tVarArr, 0, tVarArr2, 0, i);
                        tVarArr = tVarArr2;
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
            EmptyDisposable.complete(observer);
        } else if (i == 1) {
            tVarArr[0].subscribe(observer);
        } else {
            new a(observer, i).a(tVarArr);
        }
    }
}
