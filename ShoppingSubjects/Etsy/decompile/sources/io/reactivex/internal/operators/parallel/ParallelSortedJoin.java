package io.reactivex.internal.operators.parallel;

import io.reactivex.g;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.b;
import io.reactivex.j;
import io.reactivex.parallel.a;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;
import org.reactivestreams.c;

public final class ParallelSortedJoin<T> extends g<T> {
    final a<List<T>> b;
    final Comparator<? super T> c;

    static final class SortedJoinInnerSubscriber<T> extends AtomicReference<Subscription> implements j<List<T>> {
        private static final long serialVersionUID = 6751017204873808094L;
        final int index;
        final SortedJoinSubscription<T> parent;

        public void onComplete() {
        }

        SortedJoinInnerSubscriber(SortedJoinSubscription<T> sortedJoinSubscription, int i) {
            this.parent = sortedJoinSubscription;
            this.index = i;
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.setOnce(this, subscription)) {
                subscription.request(Long.MAX_VALUE);
            }
        }

        public void onNext(List<T> list) {
            this.parent.innerNext(list, this.index);
        }

        public void onError(Throwable th) {
            this.parent.innerError(th);
        }

        /* access modifiers changed from: 0000 */
        public void cancel() {
            SubscriptionHelper.cancel(this);
        }
    }

    static final class SortedJoinSubscription<T> extends AtomicInteger implements Subscription {
        private static final long serialVersionUID = 3481980673745556697L;
        final c<? super T> actual;
        volatile boolean cancelled;
        final Comparator<? super T> comparator;
        final AtomicReference<Throwable> error = new AtomicReference<>();
        final int[] indexes;
        final List<T>[] lists;
        final AtomicInteger remaining = new AtomicInteger();
        final AtomicLong requested = new AtomicLong();
        final SortedJoinInnerSubscriber<T>[] subscribers;

        SortedJoinSubscription(c<? super T> cVar, int i, Comparator<? super T> comparator2) {
            this.actual = cVar;
            this.comparator = comparator2;
            SortedJoinInnerSubscriber<T>[] sortedJoinInnerSubscriberArr = new SortedJoinInnerSubscriber[i];
            for (int i2 = 0; i2 < i; i2++) {
                sortedJoinInnerSubscriberArr[i2] = new SortedJoinInnerSubscriber<>(this, i2);
            }
            this.subscribers = sortedJoinInnerSubscriberArr;
            this.lists = new List[i];
            this.indexes = new int[i];
            this.remaining.lazySet(i);
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                b.a(this.requested, j);
                if (this.remaining.get() == 0) {
                    drain();
                }
            }
        }

        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                cancelAll();
                if (getAndIncrement() == 0) {
                    Arrays.fill(this.lists, null);
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void cancelAll() {
            for (SortedJoinInnerSubscriber<T> cancel : this.subscribers) {
                cancel.cancel();
            }
        }

        /* access modifiers changed from: 0000 */
        public void innerNext(List<T> list, int i) {
            this.lists[i] = list;
            if (this.remaining.decrementAndGet() == 0) {
                drain();
            }
        }

        /* access modifiers changed from: 0000 */
        public void innerError(Throwable th) {
            if (this.error.compareAndSet(null, th)) {
                drain();
            } else if (th != this.error.get()) {
                io.reactivex.d.a.a(th);
            }
        }

        /* access modifiers changed from: 0000 */
        public void drain() {
            boolean z;
            Object obj;
            if (getAndIncrement() == 0) {
                c<? super T> cVar = this.actual;
                List<T>[] listArr = this.lists;
                int[] iArr = this.indexes;
                int length = iArr.length;
                int i = 1;
                while (true) {
                    long j = this.requested.get();
                    long j2 = 0;
                    while (j2 != j) {
                        if (this.cancelled) {
                            Arrays.fill(listArr, null);
                            return;
                        }
                        Throwable th = (Throwable) this.error.get();
                        if (th != null) {
                            cancelAll();
                            Arrays.fill(listArr, null);
                            cVar.onError(th);
                            return;
                        }
                        int i2 = -1;
                        Object obj2 = null;
                        int i3 = 0;
                        while (i3 < length) {
                            List<T> list = listArr[i3];
                            int i4 = iArr[i3];
                            int i5 = i;
                            if (list.size() != i4) {
                                if (obj2 == null) {
                                    obj = list.get(i4);
                                } else {
                                    obj = list.get(i4);
                                    try {
                                        if (!(this.comparator.compare(obj2, obj) > 0)) {
                                        }
                                    } catch (Throwable th2) {
                                        Throwable th3 = th2;
                                        io.reactivex.exceptions.a.b(th3);
                                        cancelAll();
                                        Arrays.fill(listArr, null);
                                        if (!this.error.compareAndSet(null, th3)) {
                                            io.reactivex.d.a.a(th3);
                                        }
                                        cVar.onError((Throwable) this.error.get());
                                        return;
                                    }
                                }
                                obj2 = obj;
                                i2 = i3;
                            }
                            i3++;
                            i = i5;
                        }
                        int i6 = i;
                        if (obj2 == null) {
                            Arrays.fill(listArr, null);
                            cVar.onComplete();
                            return;
                        }
                        cVar.onNext(obj2);
                        iArr[i2] = iArr[i2] + 1;
                        j2++;
                        i = i6;
                    }
                    int i7 = i;
                    if (j2 == j) {
                        if (this.cancelled) {
                            Arrays.fill(listArr, null);
                            return;
                        }
                        Throwable th4 = (Throwable) this.error.get();
                        if (th4 != null) {
                            cancelAll();
                            Arrays.fill(listArr, null);
                            cVar.onError(th4);
                            return;
                        }
                        int i8 = 0;
                        while (true) {
                            if (i8 >= length) {
                                z = true;
                                break;
                            } else if (iArr[i8] != listArr[i8].size()) {
                                z = false;
                                break;
                            } else {
                                i8++;
                            }
                        }
                        if (z) {
                            Arrays.fill(listArr, null);
                            cVar.onComplete();
                            return;
                        }
                    }
                    if (!(j2 == 0 || j == Long.MAX_VALUE)) {
                        this.requested.addAndGet(-j2);
                    }
                    int i9 = get();
                    int i10 = i7;
                    if (i9 == i10) {
                        i9 = addAndGet(-i10);
                        if (i9 == 0) {
                            return;
                        }
                    }
                    i = i9;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a(c<? super T> cVar) {
        SortedJoinSubscription sortedJoinSubscription = new SortedJoinSubscription(cVar, this.b.a(), this.c);
        cVar.onSubscribe(sortedJoinSubscription);
        this.b.a(sortedJoinSubscription.subscribers);
    }
}
