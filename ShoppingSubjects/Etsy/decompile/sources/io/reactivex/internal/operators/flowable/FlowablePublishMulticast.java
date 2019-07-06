package io.reactivex.internal.operators.flowable;

import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.functions.g;
import io.reactivex.internal.a.d;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.i;
import io.reactivex.j;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;
import org.reactivestreams.c;

public final class FlowablePublishMulticast<T, R> extends a<T, R> {
    final g<? super io.reactivex.g<T>, ? extends org.reactivestreams.b<? extends R>> c;
    final int d;
    final boolean e;

    static final class MulticastSubscription<T> extends AtomicLong implements Subscription {
        private static final long serialVersionUID = 8664815189257569791L;
        final c<? super T> actual;
        final a<T> parent;

        MulticastSubscription(c<? super T> cVar, a<T> aVar) {
            this.actual = cVar;
            this.parent = aVar;
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                io.reactivex.internal.util.b.b((AtomicLong) this, j);
                this.parent.f();
            }
        }

        public void cancel() {
            if (getAndSet(Long.MIN_VALUE) != Long.MIN_VALUE) {
                this.parent.b(this);
                this.parent.f();
            }
        }

        public boolean isCancelled() {
            return get() == Long.MIN_VALUE;
        }
    }

    static final class a<T> extends io.reactivex.g<T> implements Disposable, j<T> {
        static final MulticastSubscription[] b = new MulticastSubscription[0];
        static final MulticastSubscription[] c = new MulticastSubscription[0];
        final AtomicInteger d = new AtomicInteger();
        final AtomicReference<MulticastSubscription<T>[]> e = new AtomicReference<>(b);
        final int f;
        final int g;
        final boolean h;
        final AtomicReference<Subscription> i = new AtomicReference<>();
        volatile io.reactivex.internal.a.g<T> j;
        int k;
        volatile boolean l;
        Throwable m;
        int n;

        a(int i2, boolean z) {
            this.f = i2;
            this.g = i2 - (i2 >> 2);
            this.h = z;
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.setOnce(this.i, subscription)) {
                if (subscription instanceof d) {
                    d dVar = (d) subscription;
                    int requestFusion = dVar.requestFusion(3);
                    if (requestFusion == 1) {
                        this.k = requestFusion;
                        this.j = dVar;
                        this.l = true;
                        f();
                        return;
                    } else if (requestFusion == 2) {
                        this.k = requestFusion;
                        this.j = dVar;
                        i.a(subscription, this.f);
                        return;
                    }
                }
                this.j = i.a(this.f);
                i.a(subscription, this.f);
            }
        }

        public void dispose() {
            SubscriptionHelper.cancel(this.i);
            if (this.d.getAndIncrement() == 0) {
                io.reactivex.internal.a.g<T> gVar = this.j;
                if (gVar != null) {
                    gVar.clear();
                }
            }
        }

        public boolean isDisposed() {
            return SubscriptionHelper.isCancelled((Subscription) this.i.get());
        }

        public void onNext(T t) {
            if (!this.l) {
                if (this.k != 0 || this.j.offer(t)) {
                    f();
                    return;
                }
                ((Subscription) this.i.get()).cancel();
                onError(new MissingBackpressureException());
            }
        }

        public void onError(Throwable th) {
            if (this.l) {
                io.reactivex.d.a.a(th);
                return;
            }
            this.m = th;
            this.l = true;
            f();
        }

        public void onComplete() {
            if (!this.l) {
                this.l = true;
                f();
            }
        }

        /* access modifiers changed from: 0000 */
        public boolean a(MulticastSubscription<T> multicastSubscription) {
            MulticastSubscription[] multicastSubscriptionArr;
            MulticastSubscription[] multicastSubscriptionArr2;
            do {
                multicastSubscriptionArr = (MulticastSubscription[]) this.e.get();
                if (multicastSubscriptionArr == c) {
                    return false;
                }
                int length = multicastSubscriptionArr.length;
                multicastSubscriptionArr2 = new MulticastSubscription[(length + 1)];
                System.arraycopy(multicastSubscriptionArr, 0, multicastSubscriptionArr2, 0, length);
                multicastSubscriptionArr2[length] = multicastSubscription;
            } while (!this.e.compareAndSet(multicastSubscriptionArr, multicastSubscriptionArr2));
            return true;
        }

        /* access modifiers changed from: 0000 */
        public void b(MulticastSubscription<T> multicastSubscription) {
            MulticastSubscription<T>[] multicastSubscriptionArr;
            MulticastSubscription[] multicastSubscriptionArr2;
            do {
                multicastSubscriptionArr = (MulticastSubscription[]) this.e.get();
                if (multicastSubscriptionArr != c && multicastSubscriptionArr != b) {
                    int length = multicastSubscriptionArr.length;
                    int i2 = -1;
                    int i3 = 0;
                    while (true) {
                        if (i3 >= length) {
                            break;
                        } else if (multicastSubscriptionArr[i3] == multicastSubscription) {
                            i2 = i3;
                            break;
                        } else {
                            i3++;
                        }
                    }
                    if (i2 >= 0) {
                        if (length == 1) {
                            multicastSubscriptionArr2 = b;
                        } else {
                            MulticastSubscription[] multicastSubscriptionArr3 = new MulticastSubscription[(length - 1)];
                            System.arraycopy(multicastSubscriptionArr, 0, multicastSubscriptionArr3, 0, i2);
                            System.arraycopy(multicastSubscriptionArr, i2 + 1, multicastSubscriptionArr3, i2, (length - i2) - 1);
                            multicastSubscriptionArr2 = multicastSubscriptionArr3;
                        }
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            } while (!this.e.compareAndSet(multicastSubscriptionArr, multicastSubscriptionArr2));
        }

        /* access modifiers changed from: protected */
        public void a(c<? super T> cVar) {
            MulticastSubscription multicastSubscription = new MulticastSubscription(cVar, this);
            cVar.onSubscribe(multicastSubscription);
            if (!a(multicastSubscription)) {
                Throwable th = this.m;
                if (th != null) {
                    cVar.onError(th);
                } else {
                    cVar.onComplete();
                }
            } else if (multicastSubscription.isCancelled()) {
                b(multicastSubscription);
            } else {
                f();
            }
        }

        /* access modifiers changed from: 0000 */
        public void f() {
            if (this.d.getAndIncrement() == 0) {
                io.reactivex.internal.a.g<T> gVar = this.j;
                int i2 = this.n;
                int i3 = this.g;
                boolean z = true;
                boolean z2 = this.k != 1;
                int i4 = i2;
                int i5 = 1;
                while (true) {
                    MulticastSubscription[] multicastSubscriptionArr = (MulticastSubscription[]) this.e.get();
                    int length = multicastSubscriptionArr.length;
                    if (!(gVar == null || length == 0)) {
                        long j2 = Long.MAX_VALUE;
                        for (MulticastSubscription multicastSubscription : multicastSubscriptionArr) {
                            long j3 = multicastSubscription.get();
                            if (j3 != Long.MIN_VALUE && j2 > j3) {
                                j2 = j3;
                            }
                        }
                        long j4 = 0;
                        while (j4 != j2) {
                            if (isDisposed()) {
                                gVar.clear();
                                return;
                            }
                            boolean z3 = this.l;
                            if (z3 && !this.h) {
                                Throwable th = this.m;
                                if (th != null) {
                                    a(th);
                                    return;
                                }
                            }
                            try {
                                Object poll = gVar.poll();
                                boolean z4 = poll == null ? z : false;
                                if (z3 && z4) {
                                    Throwable th2 = this.m;
                                    if (th2 != null) {
                                        a(th2);
                                    } else {
                                        g();
                                    }
                                    return;
                                } else if (z4) {
                                    break;
                                } else {
                                    int length2 = multicastSubscriptionArr.length;
                                    int i6 = 0;
                                    while (i6 < length2) {
                                        int i7 = length2;
                                        MulticastSubscription multicastSubscription2 = multicastSubscriptionArr[i6];
                                        if (multicastSubscription2.get() != Long.MIN_VALUE) {
                                            multicastSubscription2.actual.onNext(poll);
                                        }
                                        i6++;
                                        length2 = i7;
                                    }
                                    long j5 = j4 + 1;
                                    if (z2) {
                                        int i8 = i4 + 1;
                                        if (i8 == i3) {
                                            ((Subscription) this.i.get()).request((long) i3);
                                            i4 = 0;
                                        } else {
                                            i4 = i8;
                                        }
                                    }
                                    j4 = j5;
                                    z = true;
                                }
                            } catch (Throwable th3) {
                                io.reactivex.exceptions.a.b(th3);
                                SubscriptionHelper.cancel(this.i);
                                a(th3);
                                return;
                            }
                        }
                        if (j4 == j2) {
                            if (isDisposed()) {
                                gVar.clear();
                                return;
                            }
                            boolean z5 = this.l;
                            if (z5 && !this.h) {
                                Throwable th4 = this.m;
                                if (th4 != null) {
                                    a(th4);
                                    return;
                                }
                            }
                            if (z5 && gVar.isEmpty()) {
                                Throwable th5 = this.m;
                                if (th5 != null) {
                                    a(th5);
                                } else {
                                    g();
                                }
                                return;
                            }
                        }
                        for (MulticastSubscription c2 : multicastSubscriptionArr) {
                            io.reactivex.internal.util.b.c(c2, j4);
                        }
                    }
                    this.n = i4;
                    i5 = this.d.addAndGet(-i5);
                    if (i5 != 0) {
                        if (gVar == null) {
                            gVar = this.j;
                        }
                        z = true;
                    } else {
                        return;
                    }
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void a(Throwable th) {
            MulticastSubscription[] multicastSubscriptionArr;
            for (MulticastSubscription multicastSubscription : (MulticastSubscription[]) this.e.getAndSet(c)) {
                if (multicastSubscription.get() != Long.MIN_VALUE) {
                    multicastSubscription.actual.onError(th);
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void g() {
            MulticastSubscription[] multicastSubscriptionArr;
            for (MulticastSubscription multicastSubscription : (MulticastSubscription[]) this.e.getAndSet(c)) {
                if (multicastSubscription.get() != Long.MIN_VALUE) {
                    multicastSubscription.actual.onComplete();
                }
            }
        }
    }

    static final class b<R> implements j<R>, Subscription {
        final c<? super R> a;
        final a<?> b;
        Subscription c;

        b(c<? super R> cVar, a<?> aVar) {
            this.a = cVar;
            this.b = aVar;
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.c, subscription)) {
                this.c = subscription;
                this.a.onSubscribe(this);
            }
        }

        public void onNext(R r) {
            this.a.onNext(r);
        }

        public void onError(Throwable th) {
            this.a.onError(th);
            this.b.dispose();
        }

        public void onComplete() {
            this.a.onComplete();
            this.b.dispose();
        }

        public void request(long j) {
            this.c.request(j);
        }

        public void cancel() {
            this.c.cancel();
            this.b.dispose();
        }
    }

    /* access modifiers changed from: protected */
    public void a(c<? super R> cVar) {
        a aVar = new a(this.d, this.e);
        try {
            ((org.reactivestreams.b) io.reactivex.internal.functions.a.a(this.c.apply(aVar), "selector returned a null Publisher")).subscribe(new b(cVar, aVar));
            this.b.a((j<? super T>) aVar);
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            EmptySubscription.error(th, cVar);
        }
    }
}
