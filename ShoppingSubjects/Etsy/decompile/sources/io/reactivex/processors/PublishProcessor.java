package io.reactivex.processors;

import io.reactivex.d.a;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.b;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;
import org.reactivestreams.c;

public final class PublishProcessor<T> extends a<T> {
    static final PublishSubscription[] b = new PublishSubscription[0];
    static final PublishSubscription[] c = new PublishSubscription[0];
    final AtomicReference<PublishSubscription<T>[]> d;
    Throwable e;

    static final class PublishSubscription<T> extends AtomicLong implements Subscription {
        private static final long serialVersionUID = 3562861878281475070L;
        final c<? super T> actual;
        final PublishProcessor<T> parent;

        PublishSubscription(c<? super T> cVar, PublishProcessor<T> publishProcessor) {
            this.actual = cVar;
            this.parent = publishProcessor;
        }

        public void onNext(T t) {
            long j = get();
            if (j != Long.MIN_VALUE) {
                if (j != 0) {
                    this.actual.onNext(t);
                    b.d(this, 1);
                } else {
                    cancel();
                    this.actual.onError(new MissingBackpressureException("Could not emit value due to lack of requests"));
                }
            }
        }

        public void onError(Throwable th) {
            if (get() != Long.MIN_VALUE) {
                this.actual.onError(th);
            } else {
                a.a(th);
            }
        }

        public void onComplete() {
            if (get() != Long.MIN_VALUE) {
                this.actual.onComplete();
            }
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                b.b((AtomicLong) this, j);
            }
        }

        public void cancel() {
            if (getAndSet(Long.MIN_VALUE) != Long.MIN_VALUE) {
                this.parent.b(this);
            }
        }

        public boolean isCancelled() {
            return get() == Long.MIN_VALUE;
        }

        /* access modifiers changed from: 0000 */
        public boolean isFull() {
            return get() == 0;
        }
    }

    public void a(c<? super T> cVar) {
        PublishSubscription publishSubscription = new PublishSubscription(cVar, this);
        cVar.onSubscribe(publishSubscription);
        if (!a(publishSubscription)) {
            Throwable th = this.e;
            if (th != null) {
                cVar.onError(th);
            } else {
                cVar.onComplete();
            }
        } else if (publishSubscription.isCancelled()) {
            b(publishSubscription);
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean a(PublishSubscription<T> publishSubscription) {
        PublishSubscription[] publishSubscriptionArr;
        PublishSubscription[] publishSubscriptionArr2;
        do {
            publishSubscriptionArr = (PublishSubscription[]) this.d.get();
            if (publishSubscriptionArr == b) {
                return false;
            }
            int length = publishSubscriptionArr.length;
            publishSubscriptionArr2 = new PublishSubscription[(length + 1)];
            System.arraycopy(publishSubscriptionArr, 0, publishSubscriptionArr2, 0, length);
            publishSubscriptionArr2[length] = publishSubscription;
        } while (!this.d.compareAndSet(publishSubscriptionArr, publishSubscriptionArr2));
        return true;
    }

    /* access modifiers changed from: 0000 */
    public void b(PublishSubscription<T> publishSubscription) {
        PublishSubscription<T>[] publishSubscriptionArr;
        PublishSubscription[] publishSubscriptionArr2;
        do {
            publishSubscriptionArr = (PublishSubscription[]) this.d.get();
            if (publishSubscriptionArr != b && publishSubscriptionArr != c) {
                int length = publishSubscriptionArr.length;
                int i = -1;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    } else if (publishSubscriptionArr[i2] == publishSubscription) {
                        i = i2;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (i >= 0) {
                    if (length == 1) {
                        publishSubscriptionArr2 = c;
                    } else {
                        PublishSubscription[] publishSubscriptionArr3 = new PublishSubscription[(length - 1)];
                        System.arraycopy(publishSubscriptionArr, 0, publishSubscriptionArr3, 0, i);
                        System.arraycopy(publishSubscriptionArr, i + 1, publishSubscriptionArr3, i, (length - i) - 1);
                        publishSubscriptionArr2 = publishSubscriptionArr3;
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        } while (!this.d.compareAndSet(publishSubscriptionArr, publishSubscriptionArr2));
    }

    public void onSubscribe(Subscription subscription) {
        if (this.d.get() == b) {
            subscription.cancel();
        } else {
            subscription.request(Long.MAX_VALUE);
        }
    }

    public void onNext(T t) {
        if (this.d.get() != b) {
            if (t == null) {
                onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
                return;
            }
            for (PublishSubscription onNext : (PublishSubscription[]) this.d.get()) {
                onNext.onNext(t);
            }
        }
    }

    public void onError(Throwable th) {
        if (this.d.get() == b) {
            a.a(th);
            return;
        }
        if (th == null) {
            th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        }
        this.e = th;
        for (PublishSubscription onError : (PublishSubscription[]) this.d.getAndSet(b)) {
            onError.onError(th);
        }
    }

    public void onComplete() {
        if (this.d.get() != b) {
            for (PublishSubscription onComplete : (PublishSubscription[]) this.d.getAndSet(b)) {
                onComplete.onComplete();
            }
        }
    }
}
