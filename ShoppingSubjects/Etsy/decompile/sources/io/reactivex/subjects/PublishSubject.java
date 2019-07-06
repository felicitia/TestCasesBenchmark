package io.reactivex.subjects;

import io.reactivex.Observer;
import io.reactivex.d.a;
import io.reactivex.disposables.Disposable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public final class PublishSubject<T> extends c<T> {
    static final PublishDisposable[] a = new PublishDisposable[0];
    static final PublishDisposable[] b = new PublishDisposable[0];
    final AtomicReference<PublishDisposable<T>[]> c = new AtomicReference<>(b);
    Throwable d;

    static final class PublishDisposable<T> extends AtomicBoolean implements Disposable {
        private static final long serialVersionUID = 3562861878281475070L;
        final Observer<? super T> actual;
        final PublishSubject<T> parent;

        PublishDisposable(Observer<? super T> observer, PublishSubject<T> publishSubject) {
            this.actual = observer;
            this.parent = publishSubject;
        }

        public void onNext(T t) {
            if (!get()) {
                this.actual.onNext(t);
            }
        }

        public void onError(Throwable th) {
            if (get()) {
                a.a(th);
            } else {
                this.actual.onError(th);
            }
        }

        public void onComplete() {
            if (!get()) {
                this.actual.onComplete();
            }
        }

        public void dispose() {
            if (compareAndSet(false, true)) {
                this.parent.b(this);
            }
        }

        public boolean isDisposed() {
            return get();
        }
    }

    public static <T> PublishSubject<T> a() {
        return new PublishSubject<>();
    }

    PublishSubject() {
    }

    public void a(Observer<? super T> observer) {
        PublishDisposable publishDisposable = new PublishDisposable(observer, this);
        observer.onSubscribe(publishDisposable);
        if (!a(publishDisposable)) {
            Throwable th = this.d;
            if (th != null) {
                observer.onError(th);
            } else {
                observer.onComplete();
            }
        } else if (publishDisposable.isDisposed()) {
            b(publishDisposable);
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean a(PublishDisposable<T> publishDisposable) {
        PublishDisposable[] publishDisposableArr;
        PublishDisposable[] publishDisposableArr2;
        do {
            publishDisposableArr = (PublishDisposable[]) this.c.get();
            if (publishDisposableArr == a) {
                return false;
            }
            int length = publishDisposableArr.length;
            publishDisposableArr2 = new PublishDisposable[(length + 1)];
            System.arraycopy(publishDisposableArr, 0, publishDisposableArr2, 0, length);
            publishDisposableArr2[length] = publishDisposable;
        } while (!this.c.compareAndSet(publishDisposableArr, publishDisposableArr2));
        return true;
    }

    /* access modifiers changed from: 0000 */
    public void b(PublishDisposable<T> publishDisposable) {
        PublishDisposable<T>[] publishDisposableArr;
        PublishDisposable[] publishDisposableArr2;
        do {
            publishDisposableArr = (PublishDisposable[]) this.c.get();
            if (publishDisposableArr != a && publishDisposableArr != b) {
                int length = publishDisposableArr.length;
                int i = -1;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    } else if (publishDisposableArr[i2] == publishDisposable) {
                        i = i2;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (i >= 0) {
                    if (length == 1) {
                        publishDisposableArr2 = b;
                    } else {
                        PublishDisposable[] publishDisposableArr3 = new PublishDisposable[(length - 1)];
                        System.arraycopy(publishDisposableArr, 0, publishDisposableArr3, 0, i);
                        System.arraycopy(publishDisposableArr, i + 1, publishDisposableArr3, i, (length - i) - 1);
                        publishDisposableArr2 = publishDisposableArr3;
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        } while (!this.c.compareAndSet(publishDisposableArr, publishDisposableArr2));
    }

    public void onSubscribe(Disposable disposable) {
        if (this.c.get() == a) {
            disposable.dispose();
        }
    }

    public void onNext(T t) {
        if (this.c.get() != a) {
            if (t == null) {
                onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
                return;
            }
            for (PublishDisposable onNext : (PublishDisposable[]) this.c.get()) {
                onNext.onNext(t);
            }
        }
    }

    public void onError(Throwable th) {
        if (this.c.get() == a) {
            a.a(th);
            return;
        }
        if (th == null) {
            th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        }
        this.d = th;
        for (PublishDisposable onError : (PublishDisposable[]) this.c.getAndSet(a)) {
            onError.onError(th);
        }
    }

    public void onComplete() {
        if (this.c.get() != a) {
            for (PublishDisposable onComplete : (PublishDisposable[]) this.c.getAndSet(a)) {
                onComplete.onComplete();
            }
        }
    }

    public boolean b() {
        return ((PublishDisposable[]) this.c.get()).length != 0;
    }
}
