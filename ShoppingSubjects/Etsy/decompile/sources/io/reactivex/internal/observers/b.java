package io.reactivex.internal.observers;

import io.reactivex.c;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.m;
import io.reactivex.x;
import java.util.concurrent.CountDownLatch;

/* compiled from: BlockingMultiObserver */
public final class b<T> extends CountDownLatch implements c, m<T>, x<T> {
    T a;
    Throwable b;
    Disposable c;
    volatile boolean d;

    public b() {
        super(1);
    }

    /* access modifiers changed from: 0000 */
    public void a() {
        this.d = true;
        Disposable disposable = this.c;
        if (disposable != null) {
            disposable.dispose();
        }
    }

    public void onSubscribe(Disposable disposable) {
        this.c = disposable;
        if (this.d) {
            disposable.dispose();
        }
    }

    public void onSuccess(T t) {
        this.a = t;
        countDown();
    }

    public void onError(Throwable th) {
        this.b = th;
        countDown();
    }

    public void onComplete() {
        countDown();
    }

    public T b() {
        if (getCount() != 0) {
            try {
                io.reactivex.internal.util.c.a();
                await();
            } catch (InterruptedException e) {
                a();
                throw ExceptionHelper.a((Throwable) e);
            }
        }
        Throwable th = this.b;
        if (th == null) {
            return this.a;
        }
        throw ExceptionHelper.a(th);
    }
}
