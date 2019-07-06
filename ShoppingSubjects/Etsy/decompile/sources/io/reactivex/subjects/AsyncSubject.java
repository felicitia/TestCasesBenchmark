package io.reactivex.subjects;

import io.reactivex.Observer;
import io.reactivex.d.a;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.observers.DeferredScalarDisposable;
import java.util.concurrent.atomic.AtomicReference;

public final class AsyncSubject<T> extends c<T> {
    static final AsyncDisposable[] a = new AsyncDisposable[0];
    static final AsyncDisposable[] b = new AsyncDisposable[0];
    final AtomicReference<AsyncDisposable<T>[]> c;
    Throwable d;
    T e;

    static final class AsyncDisposable<T> extends DeferredScalarDisposable<T> {
        private static final long serialVersionUID = 5629876084736248016L;
        final AsyncSubject<T> parent;

        AsyncDisposable(Observer<? super T> observer, AsyncSubject<T> asyncSubject) {
            super(observer);
            this.parent = asyncSubject;
        }

        public void dispose() {
            if (super.tryDispose()) {
                this.parent.b(this);
            }
        }

        /* access modifiers changed from: 0000 */
        public void onComplete() {
            if (!isDisposed()) {
                this.actual.onComplete();
            }
        }

        /* access modifiers changed from: 0000 */
        public void onError(Throwable th) {
            if (isDisposed()) {
                a.a(th);
            } else {
                this.actual.onError(th);
            }
        }
    }

    public void onSubscribe(Disposable disposable) {
        if (this.c.get() == b) {
            disposable.dispose();
        }
    }

    public void onNext(T t) {
        if (this.c.get() != b) {
            if (t == null) {
                a();
            } else {
                this.e = t;
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void a() {
        this.e = null;
        NullPointerException nullPointerException = new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
        this.d = nullPointerException;
        for (AsyncDisposable onError : (AsyncDisposable[]) this.c.getAndSet(b)) {
            onError.onError(nullPointerException);
        }
    }

    public void onError(Throwable th) {
        if (th == null) {
            th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        }
        if (this.c.get() == b) {
            a.a(th);
            return;
        }
        this.e = null;
        this.d = th;
        for (AsyncDisposable onError : (AsyncDisposable[]) this.c.getAndSet(b)) {
            onError.onError(th);
        }
    }

    public void onComplete() {
        if (this.c.get() != b) {
            T t = this.e;
            AsyncDisposable[] asyncDisposableArr = (AsyncDisposable[]) this.c.getAndSet(b);
            int i = 0;
            if (t == null) {
                int length = asyncDisposableArr.length;
                while (i < length) {
                    asyncDisposableArr[i].onComplete();
                    i++;
                }
            } else {
                int length2 = asyncDisposableArr.length;
                while (i < length2) {
                    asyncDisposableArr[i].complete(t);
                    i++;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a(Observer<? super T> observer) {
        AsyncDisposable asyncDisposable = new AsyncDisposable(observer, this);
        observer.onSubscribe(asyncDisposable);
        if (!a(asyncDisposable)) {
            Throwable th = this.d;
            if (th != null) {
                observer.onError(th);
                return;
            }
            T t = this.e;
            if (t != null) {
                asyncDisposable.complete(t);
            } else {
                asyncDisposable.onComplete();
            }
        } else if (asyncDisposable.isDisposed()) {
            b(asyncDisposable);
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean a(AsyncDisposable<T> asyncDisposable) {
        AsyncDisposable[] asyncDisposableArr;
        AsyncDisposable[] asyncDisposableArr2;
        do {
            asyncDisposableArr = (AsyncDisposable[]) this.c.get();
            if (asyncDisposableArr == b) {
                return false;
            }
            int length = asyncDisposableArr.length;
            asyncDisposableArr2 = new AsyncDisposable[(length + 1)];
            System.arraycopy(asyncDisposableArr, 0, asyncDisposableArr2, 0, length);
            asyncDisposableArr2[length] = asyncDisposable;
        } while (!this.c.compareAndSet(asyncDisposableArr, asyncDisposableArr2));
        return true;
    }

    /* access modifiers changed from: 0000 */
    public void b(AsyncDisposable<T> asyncDisposable) {
        AsyncDisposable<T>[] asyncDisposableArr;
        AsyncDisposable[] asyncDisposableArr2;
        do {
            asyncDisposableArr = (AsyncDisposable[]) this.c.get();
            int length = asyncDisposableArr.length;
            if (length != 0) {
                int i = -1;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    } else if (asyncDisposableArr[i2] == asyncDisposable) {
                        i = i2;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (i >= 0) {
                    if (length == 1) {
                        asyncDisposableArr2 = a;
                    } else {
                        AsyncDisposable[] asyncDisposableArr3 = new AsyncDisposable[(length - 1)];
                        System.arraycopy(asyncDisposableArr, 0, asyncDisposableArr3, 0, i);
                        System.arraycopy(asyncDisposableArr, i + 1, asyncDisposableArr3, i, (length - i) - 1);
                        asyncDisposableArr2 = asyncDisposableArr3;
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        } while (!this.c.compareAndSet(asyncDisposableArr, asyncDisposableArr2));
    }
}
