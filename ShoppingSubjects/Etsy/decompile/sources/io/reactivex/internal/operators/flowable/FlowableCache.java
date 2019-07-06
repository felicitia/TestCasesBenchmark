package io.reactivex.internal.operators.flowable;

import io.reactivex.g;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.internal.util.b;
import io.reactivex.internal.util.f;
import io.reactivex.j;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;
import org.reactivestreams.c;

public final class FlowableCache<T> extends a<T, T> {
    final a<T> c;
    final AtomicBoolean d;

    static final class ReplaySubscription<T> extends AtomicInteger implements Subscription {
        private static final long serialVersionUID = -2557562030197141021L;
        final c<? super T> child;
        Object[] currentBuffer;
        int currentIndexInBuffer;
        int index;
        final AtomicLong requested = new AtomicLong();
        final a<T> state;

        ReplaySubscription(c<? super T> cVar, a<T> aVar) {
            this.child = cVar;
            this.state = aVar;
        }

        public void request(long j) {
            long j2;
            if (SubscriptionHelper.validate(j)) {
                do {
                    j2 = this.requested.get();
                    if (j2 != -1) {
                    } else {
                        return;
                    }
                } while (!this.requested.compareAndSet(j2, b.a(j2, j)));
                replay();
            }
        }

        public void cancel() {
            if (this.requested.getAndSet(-1) != -1) {
                this.state.b(this);
            }
        }

        public void replay() {
            if (getAndIncrement() == 0) {
                c<? super T> cVar = this.child;
                AtomicLong atomicLong = this.requested;
                int i = 1;
                int i2 = 1;
                while (true) {
                    long j = atomicLong.get();
                    if (j >= 0) {
                        int c = this.state.c();
                        if (c != 0) {
                            Object[] objArr = this.currentBuffer;
                            if (objArr == null) {
                                objArr = this.state.b();
                                this.currentBuffer = objArr;
                            }
                            int length = objArr.length - i;
                            int i3 = this.index;
                            int i4 = this.currentIndexInBuffer;
                            int i5 = 0;
                            while (i3 < c && j > 0) {
                                if (atomicLong.get() != -1) {
                                    if (i4 == length) {
                                        objArr = (Object[]) objArr[length];
                                        i4 = 0;
                                    }
                                    if (!NotificationLite.accept(objArr[i4], cVar)) {
                                        i4++;
                                        i3++;
                                        i5++;
                                        j--;
                                    } else {
                                        return;
                                    }
                                } else {
                                    return;
                                }
                            }
                            if (atomicLong.get() != -1) {
                                if (j == 0) {
                                    Object obj = objArr[i4];
                                    if (NotificationLite.isComplete(obj)) {
                                        cVar.onComplete();
                                        return;
                                    } else if (NotificationLite.isError(obj)) {
                                        cVar.onError(NotificationLite.getError(obj));
                                        return;
                                    }
                                }
                                if (i5 != 0) {
                                    b.d(atomicLong, (long) i5);
                                }
                                this.index = i3;
                                this.currentIndexInBuffer = i4;
                                this.currentBuffer = objArr;
                            } else {
                                return;
                            }
                        }
                        i2 = addAndGet(-i2);
                        if (i2 != 0) {
                            i = 1;
                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
                }
            }
        }
    }

    static final class a<T> extends f implements j<T> {
        static final ReplaySubscription[] d = new ReplaySubscription[0];
        static final ReplaySubscription[] e = new ReplaySubscription[0];
        final g<T> a;
        final AtomicReference<Subscription> b;
        final AtomicReference<ReplaySubscription<T>[]> c;
        volatile boolean f;
        boolean g;

        public void a(ReplaySubscription<T> replaySubscription) {
            ReplaySubscription[] replaySubscriptionArr;
            ReplaySubscription[] replaySubscriptionArr2;
            do {
                replaySubscriptionArr = (ReplaySubscription[]) this.c.get();
                if (replaySubscriptionArr != e) {
                    int length = replaySubscriptionArr.length;
                    replaySubscriptionArr2 = new ReplaySubscription[(length + 1)];
                    System.arraycopy(replaySubscriptionArr, 0, replaySubscriptionArr2, 0, length);
                    replaySubscriptionArr2[length] = replaySubscription;
                } else {
                    return;
                }
            } while (!this.c.compareAndSet(replaySubscriptionArr, replaySubscriptionArr2));
        }

        public void b(ReplaySubscription<T> replaySubscription) {
            ReplaySubscription[] replaySubscriptionArr;
            ReplaySubscription[] replaySubscriptionArr2;
            do {
                replaySubscriptionArr = (ReplaySubscription[]) this.c.get();
                int length = replaySubscriptionArr.length;
                if (length != 0) {
                    int i = -1;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length) {
                            break;
                        } else if (replaySubscriptionArr[i2].equals(replaySubscription)) {
                            i = i2;
                            break;
                        } else {
                            i2++;
                        }
                    }
                    if (i >= 0) {
                        if (length == 1) {
                            replaySubscriptionArr2 = d;
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
            } while (!this.c.compareAndSet(replaySubscriptionArr, replaySubscriptionArr2));
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.setOnce(this.b, subscription)) {
                subscription.request(Long.MAX_VALUE);
            }
        }

        public void a() {
            this.a.a((j<? super T>) this);
            this.f = true;
        }

        public void onNext(T t) {
            if (!this.g) {
                a(NotificationLite.next(t));
                for (ReplaySubscription replay : (ReplaySubscription[]) this.c.get()) {
                    replay.replay();
                }
            }
        }

        public void onError(Throwable th) {
            if (!this.g) {
                this.g = true;
                a(NotificationLite.error(th));
                SubscriptionHelper.cancel(this.b);
                for (ReplaySubscription replay : (ReplaySubscription[]) this.c.getAndSet(e)) {
                    replay.replay();
                }
                return;
            }
            io.reactivex.d.a.a(th);
        }

        public void onComplete() {
            if (!this.g) {
                this.g = true;
                a(NotificationLite.complete());
                SubscriptionHelper.cancel(this.b);
                for (ReplaySubscription replay : (ReplaySubscription[]) this.c.getAndSet(e)) {
                    replay.replay();
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a(c<? super T> cVar) {
        ReplaySubscription replaySubscription = new ReplaySubscription(cVar, this.c);
        this.c.a(replaySubscription);
        cVar.onSubscribe(replaySubscription);
        if (!this.d.get() && this.d.compareAndSet(false, true)) {
            this.c.a();
        }
    }
}
