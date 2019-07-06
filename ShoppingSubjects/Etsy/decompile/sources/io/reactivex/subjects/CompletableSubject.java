package io.reactivex.subjects;

import io.reactivex.a;
import io.reactivex.c;
import io.reactivex.disposables.Disposable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public final class CompletableSubject extends a implements c {
    static final CompletableDisposable[] b = new CompletableDisposable[0];
    static final CompletableDisposable[] c = new CompletableDisposable[0];
    final AtomicReference<CompletableDisposable[]> a = new AtomicReference<>(b);
    final AtomicBoolean d = new AtomicBoolean();
    Throwable e;

    static final class CompletableDisposable extends AtomicReference<CompletableSubject> implements Disposable {
        private static final long serialVersionUID = -7650903191002190468L;
        final c actual;

        CompletableDisposable(c cVar, CompletableSubject completableSubject) {
            this.actual = cVar;
            lazySet(completableSubject);
        }

        public void dispose() {
            CompletableSubject completableSubject = (CompletableSubject) getAndSet(null);
            if (completableSubject != null) {
                completableSubject.b(this);
            }
        }

        public boolean isDisposed() {
            return get() == null;
        }
    }

    CompletableSubject() {
    }

    public void onSubscribe(Disposable disposable) {
        if (this.a.get() == c) {
            disposable.dispose();
        }
    }

    public void onError(Throwable th) {
        if (th == null) {
            th = new NullPointerException("Null errors are not allowed in 2.x");
        }
        if (this.d.compareAndSet(false, true)) {
            this.e = th;
            for (CompletableDisposable completableDisposable : (CompletableDisposable[]) this.a.getAndSet(c)) {
                completableDisposable.actual.onError(th);
            }
            return;
        }
        io.reactivex.d.a.a(th);
    }

    public void onComplete() {
        if (this.d.compareAndSet(false, true)) {
            for (CompletableDisposable completableDisposable : (CompletableDisposable[]) this.a.getAndSet(c)) {
                completableDisposable.actual.onComplete();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void b(c cVar) {
        CompletableDisposable completableDisposable = new CompletableDisposable(cVar, this);
        cVar.onSubscribe(completableDisposable);
        if (!a(completableDisposable)) {
            Throwable th = this.e;
            if (th != null) {
                cVar.onError(th);
            } else {
                cVar.onComplete();
            }
        } else if (completableDisposable.isDisposed()) {
            b(completableDisposable);
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean a(CompletableDisposable completableDisposable) {
        CompletableDisposable[] completableDisposableArr;
        CompletableDisposable[] completableDisposableArr2;
        do {
            completableDisposableArr = (CompletableDisposable[]) this.a.get();
            if (completableDisposableArr == c) {
                return false;
            }
            int length = completableDisposableArr.length;
            completableDisposableArr2 = new CompletableDisposable[(length + 1)];
            System.arraycopy(completableDisposableArr, 0, completableDisposableArr2, 0, length);
            completableDisposableArr2[length] = completableDisposable;
        } while (!this.a.compareAndSet(completableDisposableArr, completableDisposableArr2));
        return true;
    }

    /* access modifiers changed from: 0000 */
    public void b(CompletableDisposable completableDisposable) {
        CompletableDisposable[] completableDisposableArr;
        CompletableDisposable[] completableDisposableArr2;
        do {
            completableDisposableArr = (CompletableDisposable[]) this.a.get();
            int length = completableDisposableArr.length;
            if (length != 0) {
                int i = -1;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    } else if (completableDisposableArr[i2] == completableDisposable) {
                        i = i2;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (i >= 0) {
                    if (length == 1) {
                        completableDisposableArr2 = b;
                    } else {
                        CompletableDisposable[] completableDisposableArr3 = new CompletableDisposable[(length - 1)];
                        System.arraycopy(completableDisposableArr, 0, completableDisposableArr3, 0, i);
                        System.arraycopy(completableDisposableArr, i + 1, completableDisposableArr3, i, (length - i) - 1);
                        completableDisposableArr2 = completableDisposableArr3;
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        } while (!this.a.compareAndSet(completableDisposableArr, completableDisposableArr2));
    }
}
