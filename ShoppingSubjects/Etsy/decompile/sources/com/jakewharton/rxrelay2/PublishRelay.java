package com.jakewharton.rxrelay2;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public final class PublishRelay<T> extends c<T> {
    private static final PublishDisposable[] a = new PublishDisposable[0];
    private final AtomicReference<PublishDisposable<T>[]> b;

    static final class PublishDisposable<T> extends AtomicBoolean implements Disposable {
        private static final long serialVersionUID = 3562861878281475070L;
        final Observer<? super T> actual;
        final PublishRelay<T> parent;

        PublishDisposable(Observer<? super T> observer, PublishRelay<T> publishRelay) {
            this.actual = observer;
            this.parent = publishRelay;
        }

        /* access modifiers changed from: 0000 */
        public void onNext(T t) {
            if (!get()) {
                this.actual.onNext(t);
            }
        }

        public void dispose() {
            if (compareAndSet(false, true)) {
                this.parent.a(this);
            }
        }

        public boolean isDisposed() {
            return get();
        }
    }

    public void a(Observer<? super T> observer) {
        PublishDisposable publishDisposable = new PublishDisposable(observer, this);
        observer.onSubscribe(publishDisposable);
        b(publishDisposable);
        if (publishDisposable.isDisposed()) {
            a(publishDisposable);
        }
    }

    private void b(PublishDisposable<T> publishDisposable) {
        PublishDisposable[] publishDisposableArr;
        PublishDisposable[] publishDisposableArr2;
        do {
            publishDisposableArr = (PublishDisposable[]) this.b.get();
            int length = publishDisposableArr.length;
            publishDisposableArr2 = new PublishDisposable[(length + 1)];
            System.arraycopy(publishDisposableArr, 0, publishDisposableArr2, 0, length);
            publishDisposableArr2[length] = publishDisposable;
        } while (!this.b.compareAndSet(publishDisposableArr, publishDisposableArr2));
    }

    /* access modifiers changed from: 0000 */
    public void a(PublishDisposable<T> publishDisposable) {
        PublishDisposable<T>[] publishDisposableArr;
        PublishDisposable[] publishDisposableArr2;
        do {
            publishDisposableArr = (PublishDisposable[]) this.b.get();
            if (publishDisposableArr != a) {
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
                        publishDisposableArr2 = a;
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
        } while (!this.b.compareAndSet(publishDisposableArr, publishDisposableArr2));
    }

    public void accept(T t) {
        if (t == null) {
            throw new NullPointerException("value == null");
        }
        for (PublishDisposable onNext : (PublishDisposable[]) this.b.get()) {
            onNext.onNext(t);
        }
    }
}
