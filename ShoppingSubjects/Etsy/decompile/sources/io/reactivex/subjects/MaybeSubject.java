package io.reactivex.subjects;

import io.reactivex.d.a;
import io.reactivex.disposables.Disposable;
import io.reactivex.k;
import io.reactivex.m;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public final class MaybeSubject<T> extends k<T> implements m<T> {
    static final MaybeDisposable[] b = new MaybeDisposable[0];
    static final MaybeDisposable[] c = new MaybeDisposable[0];
    final AtomicReference<MaybeDisposable<T>[]> a = new AtomicReference<>(b);
    final AtomicBoolean d = new AtomicBoolean();
    T e;
    Throwable f;

    static final class MaybeDisposable<T> extends AtomicReference<MaybeSubject<T>> implements Disposable {
        private static final long serialVersionUID = -7650903191002190468L;
        final m<? super T> actual;

        MaybeDisposable(m<? super T> mVar, MaybeSubject<T> maybeSubject) {
            this.actual = mVar;
            lazySet(maybeSubject);
        }

        public void dispose() {
            MaybeSubject maybeSubject = (MaybeSubject) getAndSet(null);
            if (maybeSubject != null) {
                maybeSubject.b(this);
            }
        }

        public boolean isDisposed() {
            return get() == null;
        }
    }

    MaybeSubject() {
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
            for (MaybeDisposable maybeDisposable : (MaybeDisposable[]) this.a.getAndSet(c)) {
                maybeDisposable.actual.onSuccess(t);
            }
        }
    }

    public void onError(Throwable th) {
        if (th == null) {
            th = new NullPointerException("Null errors are not allowed in 2.x");
        }
        if (this.d.compareAndSet(false, true)) {
            this.f = th;
            for (MaybeDisposable maybeDisposable : (MaybeDisposable[]) this.a.getAndSet(c)) {
                maybeDisposable.actual.onError(th);
            }
            return;
        }
        a.a(th);
    }

    public void onComplete() {
        if (this.d.compareAndSet(false, true)) {
            for (MaybeDisposable maybeDisposable : (MaybeDisposable[]) this.a.getAndSet(c)) {
                maybeDisposable.actual.onComplete();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void b(m<? super T> mVar) {
        MaybeDisposable maybeDisposable = new MaybeDisposable(mVar, this);
        mVar.onSubscribe(maybeDisposable);
        if (!a(maybeDisposable)) {
            Throwable th = this.f;
            if (th != null) {
                mVar.onError(th);
                return;
            }
            T t = this.e;
            if (t == null) {
                mVar.onComplete();
            } else {
                mVar.onSuccess(t);
            }
        } else if (maybeDisposable.isDisposed()) {
            b(maybeDisposable);
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean a(MaybeDisposable<T> maybeDisposable) {
        MaybeDisposable[] maybeDisposableArr;
        MaybeDisposable[] maybeDisposableArr2;
        do {
            maybeDisposableArr = (MaybeDisposable[]) this.a.get();
            if (maybeDisposableArr == c) {
                return false;
            }
            int length = maybeDisposableArr.length;
            maybeDisposableArr2 = new MaybeDisposable[(length + 1)];
            System.arraycopy(maybeDisposableArr, 0, maybeDisposableArr2, 0, length);
            maybeDisposableArr2[length] = maybeDisposable;
        } while (!this.a.compareAndSet(maybeDisposableArr, maybeDisposableArr2));
        return true;
    }

    /* access modifiers changed from: 0000 */
    public void b(MaybeDisposable<T> maybeDisposable) {
        MaybeDisposable<T>[] maybeDisposableArr;
        MaybeDisposable[] maybeDisposableArr2;
        do {
            maybeDisposableArr = (MaybeDisposable[]) this.a.get();
            int length = maybeDisposableArr.length;
            if (length != 0) {
                int i = -1;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    } else if (maybeDisposableArr[i2] == maybeDisposable) {
                        i = i2;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (i >= 0) {
                    if (length == 1) {
                        maybeDisposableArr2 = b;
                    } else {
                        MaybeDisposable[] maybeDisposableArr3 = new MaybeDisposable[(length - 1)];
                        System.arraycopy(maybeDisposableArr, 0, maybeDisposableArr3, 0, i);
                        System.arraycopy(maybeDisposableArr, i + 1, maybeDisposableArr3, i, (length - i) - 1);
                        maybeDisposableArr2 = maybeDisposableArr3;
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        } while (!this.a.compareAndSet(maybeDisposableArr, maybeDisposableArr2));
    }
}
