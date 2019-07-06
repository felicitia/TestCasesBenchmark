package io.reactivex.processors;

import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.b;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;
import org.reactivestreams.c;

public final class ReplayProcessor<T> extends a<T> {
    static final ReplaySubscription[] e = new ReplaySubscription[0];
    static final ReplaySubscription[] f = new ReplaySubscription[0];
    private static final Object[] g = new Object[0];
    final a<T> b;
    boolean c;
    final AtomicReference<ReplaySubscription<T>[]> d;

    static final class Node<T> extends AtomicReference<Node<T>> {
        private static final long serialVersionUID = 6404226426336033100L;
        final T value;

        Node(T t) {
            this.value = t;
        }
    }

    static final class ReplaySubscription<T> extends AtomicInteger implements Subscription {
        private static final long serialVersionUID = 466549804534799122L;
        final c<? super T> actual;
        volatile boolean cancelled;
        long emitted;
        Object index;
        final AtomicLong requested = new AtomicLong();
        final ReplayProcessor<T> state;

        ReplaySubscription(c<? super T> cVar, ReplayProcessor<T> replayProcessor) {
            this.actual = cVar;
            this.state = replayProcessor;
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                b.a(this.requested, j);
                this.state.b.a(this);
            }
        }

        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.state.b(this);
            }
        }
    }

    static final class TimedNode<T> extends AtomicReference<TimedNode<T>> {
        private static final long serialVersionUID = 6404226426336033100L;
        final long time;
        final T value;

        TimedNode(T t, long j) {
            this.value = t;
            this.time = j;
        }
    }

    interface a<T> {
        void a();

        void a(ReplaySubscription<T> replaySubscription);

        void a(T t);

        void a(Throwable th);
    }

    /* access modifiers changed from: protected */
    public void a(c<? super T> cVar) {
        ReplaySubscription replaySubscription = new ReplaySubscription(cVar, this);
        cVar.onSubscribe(replaySubscription);
        if (!a(replaySubscription) || !replaySubscription.cancelled) {
            this.b.a(replaySubscription);
        } else {
            b(replaySubscription);
        }
    }

    public void onSubscribe(Subscription subscription) {
        if (this.c) {
            subscription.cancel();
        } else {
            subscription.request(Long.MAX_VALUE);
        }
    }

    public void onNext(T t) {
        if (t == null) {
            onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
        } else if (!this.c) {
            a<T> aVar = this.b;
            aVar.a(t);
            for (ReplaySubscription a2 : (ReplaySubscription[]) this.d.get()) {
                aVar.a(a2);
            }
        }
    }

    public void onError(Throwable th) {
        if (th == null) {
            th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        }
        if (this.c) {
            io.reactivex.d.a.a(th);
            return;
        }
        this.c = true;
        a<T> aVar = this.b;
        aVar.a(th);
        for (ReplaySubscription a2 : (ReplaySubscription[]) this.d.getAndSet(f)) {
            aVar.a(a2);
        }
    }

    public void onComplete() {
        if (!this.c) {
            this.c = true;
            a<T> aVar = this.b;
            aVar.a();
            for (ReplaySubscription a2 : (ReplaySubscription[]) this.d.getAndSet(f)) {
                aVar.a(a2);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean a(ReplaySubscription<T> replaySubscription) {
        ReplaySubscription[] replaySubscriptionArr;
        ReplaySubscription[] replaySubscriptionArr2;
        do {
            replaySubscriptionArr = (ReplaySubscription[]) this.d.get();
            if (replaySubscriptionArr == f) {
                return false;
            }
            int length = replaySubscriptionArr.length;
            replaySubscriptionArr2 = new ReplaySubscription[(length + 1)];
            System.arraycopy(replaySubscriptionArr, 0, replaySubscriptionArr2, 0, length);
            replaySubscriptionArr2[length] = replaySubscription;
        } while (!this.d.compareAndSet(replaySubscriptionArr, replaySubscriptionArr2));
        return true;
    }

    /* access modifiers changed from: 0000 */
    public void b(ReplaySubscription<T> replaySubscription) {
        ReplaySubscription<T>[] replaySubscriptionArr;
        ReplaySubscription[] replaySubscriptionArr2;
        do {
            replaySubscriptionArr = (ReplaySubscription[]) this.d.get();
            if (replaySubscriptionArr != f && replaySubscriptionArr != e) {
                int length = replaySubscriptionArr.length;
                int i = -1;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    } else if (replaySubscriptionArr[i2] == replaySubscription) {
                        i = i2;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (i >= 0) {
                    if (length == 1) {
                        replaySubscriptionArr2 = e;
                    } else {
                        ReplaySubscription[] replaySubscriptionArr3 = new ReplaySubscription[(length - 1)];
                        System.arraycopy(replaySubscriptionArr, 0, replaySubscriptionArr3, 0, i);
                        System.arraycopy(replaySubscriptionArr, i + 1, replaySubscriptionArr3, i, (length - i) - 1);
                        replaySubscriptionArr2 = replaySubscriptionArr3;
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        } while (!this.d.compareAndSet(replaySubscriptionArr, replaySubscriptionArr2));
    }
}
