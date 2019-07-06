package io.reactivex.processors;

import io.reactivex.d.a;
import io.reactivex.internal.subscriptions.DeferredScalarSubscription;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;
import org.reactivestreams.c;

public final class AsyncProcessor<T> extends a<T> {
    static final AsyncSubscription[] b = new AsyncSubscription[0];
    static final AsyncSubscription[] c = new AsyncSubscription[0];
    final AtomicReference<AsyncSubscription<T>[]> d;
    Throwable e;
    T f;

    static final class AsyncSubscription<T> extends DeferredScalarSubscription<T> {
        private static final long serialVersionUID = 5629876084736248016L;
        final AsyncProcessor<T> parent;

        AsyncSubscription(c<? super T> cVar, AsyncProcessor<T> asyncProcessor) {
            super(cVar);
            this.parent = asyncProcessor;
        }

        public void cancel() {
            if (super.tryCancel()) {
                this.parent.b(this);
            }
        }

        /* access modifiers changed from: 0000 */
        public void onComplete() {
            if (!isCancelled()) {
                this.actual.onComplete();
            }
        }

        /* access modifiers changed from: 0000 */
        public void onError(Throwable th) {
            if (isCancelled()) {
                a.a(th);
            } else {
                this.actual.onError(th);
            }
        }
    }

    public void onSubscribe(Subscription subscription) {
        if (this.d.get() == c) {
            subscription.cancel();
        } else {
            subscription.request(Long.MAX_VALUE);
        }
    }

    public void onNext(T t) {
        if (this.d.get() != c) {
            if (t == null) {
                f();
            } else {
                this.f = t;
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void f() {
        this.f = null;
        NullPointerException nullPointerException = new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
        this.e = nullPointerException;
        for (AsyncSubscription onError : (AsyncSubscription[]) this.d.getAndSet(c)) {
            onError.onError(nullPointerException);
        }
    }

    public void onError(Throwable th) {
        if (th == null) {
            th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        }
        if (this.d.get() == c) {
            a.a(th);
            return;
        }
        this.f = null;
        this.e = th;
        for (AsyncSubscription onError : (AsyncSubscription[]) this.d.getAndSet(c)) {
            onError.onError(th);
        }
    }

    public void onComplete() {
        if (this.d.get() != c) {
            T t = this.f;
            AsyncSubscription[] asyncSubscriptionArr = (AsyncSubscription[]) this.d.getAndSet(c);
            int i = 0;
            if (t == null) {
                int length = asyncSubscriptionArr.length;
                while (i < length) {
                    asyncSubscriptionArr[i].onComplete();
                    i++;
                }
            } else {
                int length2 = asyncSubscriptionArr.length;
                while (i < length2) {
                    asyncSubscriptionArr[i].complete(t);
                    i++;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a(c<? super T> cVar) {
        AsyncSubscription asyncSubscription = new AsyncSubscription(cVar, this);
        cVar.onSubscribe(asyncSubscription);
        if (!a(asyncSubscription)) {
            Throwable th = this.e;
            if (th != null) {
                cVar.onError(th);
                return;
            }
            T t = this.f;
            if (t != null) {
                asyncSubscription.complete(t);
            } else {
                asyncSubscription.onComplete();
            }
        } else if (asyncSubscription.isCancelled()) {
            b(asyncSubscription);
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean a(AsyncSubscription<T> asyncSubscription) {
        AsyncSubscription[] asyncSubscriptionArr;
        AsyncSubscription[] asyncSubscriptionArr2;
        do {
            asyncSubscriptionArr = (AsyncSubscription[]) this.d.get();
            if (asyncSubscriptionArr == c) {
                return false;
            }
            int length = asyncSubscriptionArr.length;
            asyncSubscriptionArr2 = new AsyncSubscription[(length + 1)];
            System.arraycopy(asyncSubscriptionArr, 0, asyncSubscriptionArr2, 0, length);
            asyncSubscriptionArr2[length] = asyncSubscription;
        } while (!this.d.compareAndSet(asyncSubscriptionArr, asyncSubscriptionArr2));
        return true;
    }

    /* access modifiers changed from: 0000 */
    public void b(AsyncSubscription<T> asyncSubscription) {
        AsyncSubscription<T>[] asyncSubscriptionArr;
        AsyncSubscription[] asyncSubscriptionArr2;
        do {
            asyncSubscriptionArr = (AsyncSubscription[]) this.d.get();
            int length = asyncSubscriptionArr.length;
            if (length != 0) {
                int i = -1;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    } else if (asyncSubscriptionArr[i2] == asyncSubscription) {
                        i = i2;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (i >= 0) {
                    if (length == 1) {
                        asyncSubscriptionArr2 = b;
                    } else {
                        AsyncSubscription[] asyncSubscriptionArr3 = new AsyncSubscription[(length - 1)];
                        System.arraycopy(asyncSubscriptionArr, 0, asyncSubscriptionArr3, 0, i);
                        System.arraycopy(asyncSubscriptionArr, i + 1, asyncSubscriptionArr3, i, (length - i) - 1);
                        asyncSubscriptionArr2 = asyncSubscriptionArr3;
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        } while (!this.d.compareAndSet(asyncSubscriptionArr, asyncSubscriptionArr2));
    }
}
