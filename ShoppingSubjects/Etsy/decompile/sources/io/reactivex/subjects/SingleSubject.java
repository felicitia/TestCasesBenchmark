package io.reactivex.subjects;

import io.reactivex.d.a;
import io.reactivex.disposables.Disposable;
import io.reactivex.v;
import io.reactivex.x;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public final class SingleSubject<T> extends v<T> implements x<T> {
    static final SingleDisposable[] b = new SingleDisposable[0];
    static final SingleDisposable[] c = new SingleDisposable[0];
    final AtomicReference<SingleDisposable<T>[]> a = new AtomicReference<>(b);
    final AtomicBoolean d = new AtomicBoolean();
    T e;
    Throwable f;

    static final class SingleDisposable<T> extends AtomicReference<SingleSubject<T>> implements Disposable {
        private static final long serialVersionUID = -7650903191002190468L;
        final x<? super T> actual;

        SingleDisposable(x<? super T> xVar, SingleSubject<T> singleSubject) {
            this.actual = xVar;
            lazySet(singleSubject);
        }

        public void dispose() {
            SingleSubject singleSubject = (SingleSubject) getAndSet(null);
            if (singleSubject != null) {
                singleSubject.b(this);
            }
        }

        public boolean isDisposed() {
            return get() == null;
        }
    }

    SingleSubject() {
    }

    public void onSubscribe(Disposable disposable) {
        if (this.a.get() == c) {
            disposable.dispose();
        }
    }

    public void onSuccess(T t) {
        if (t == null) {
            onError(new NullPointerException("Null values are not allowed in 2.x"));
            return;
        }
        if (this.d.compareAndSet(false, true)) {
            this.e = t;
            for (SingleDisposable singleDisposable : (SingleDisposable[]) this.a.getAndSet(c)) {
                singleDisposable.actual.onSuccess(t);
            }
        }
    }

    public void onError(Throwable th) {
        if (th == null) {
            th = new NullPointerException("Null errors are not allowed in 2.x");
        }
        if (this.d.compareAndSet(false, true)) {
            this.f = th;
            for (SingleDisposable singleDisposable : (SingleDisposable[]) this.a.getAndSet(c)) {
                singleDisposable.actual.onError(th);
            }
            return;
        }
        a.a(th);
    }

    /* access modifiers changed from: protected */
    public void b(x<? super T> xVar) {
        SingleDisposable singleDisposable = new SingleDisposable(xVar, this);
        xVar.onSubscribe(singleDisposable);
        if (!a(singleDisposable)) {
            Throwable th = this.f;
            if (th != null) {
                xVar.onError(th);
            } else {
                xVar.onSuccess(this.e);
            }
        } else if (singleDisposable.isDisposed()) {
            b(singleDisposable);
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean a(SingleDisposable<T> singleDisposable) {
        SingleDisposable[] singleDisposableArr;
        SingleDisposable[] singleDisposableArr2;
        do {
            singleDisposableArr = (SingleDisposable[]) this.a.get();
            if (singleDisposableArr == c) {
                return false;
            }
            int length = singleDisposableArr.length;
            singleDisposableArr2 = new SingleDisposable[(length + 1)];
            System.arraycopy(singleDisposableArr, 0, singleDisposableArr2, 0, length);
            singleDisposableArr2[length] = singleDisposable;
        } while (!this.a.compareAndSet(singleDisposableArr, singleDisposableArr2));
        return true;
    }

    /* access modifiers changed from: 0000 */
    public void b(SingleDisposable<T> singleDisposable) {
        SingleDisposable<T>[] singleDisposableArr;
        SingleDisposable[] singleDisposableArr2;
        do {
            singleDisposableArr = (SingleDisposable[]) this.a.get();
            int length = singleDisposableArr.length;
            if (length != 0) {
                int i = -1;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    } else if (singleDisposableArr[i2] == singleDisposable) {
                        i = i2;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (i >= 0) {
                    if (length == 1) {
                        singleDisposableArr2 = b;
                    } else {
                        SingleDisposable[] singleDisposableArr3 = new SingleDisposable[(length - 1)];
                        System.arraycopy(singleDisposableArr, 0, singleDisposableArr3, 0, i);
                        System.arraycopy(singleDisposableArr, i + 1, singleDisposableArr3, i, (length - i) - 1);
                        singleDisposableArr2 = singleDisposableArr3;
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        } while (!this.a.compareAndSet(singleDisposableArr, singleDisposableArr2));
    }
}
