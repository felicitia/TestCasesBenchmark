package io.reactivex.internal.operators.flowable;

import io.reactivex.g;
import io.reactivex.internal.subscriptions.BasicIntQueueSubscription;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.j;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;
import org.reactivestreams.b;
import org.reactivestreams.c;

public final class FlowableCombineLatest<T, R> extends g<R> {
    final b<? extends T>[] b;
    final Iterable<? extends b<? extends T>> c;
    final io.reactivex.functions.g<? super Object[], ? extends R> d;
    final int e;
    final boolean f;

    static final class CombineLatestCoordinator<T, R> extends BasicIntQueueSubscription<R> {
        private static final long serialVersionUID = -5082275438355852221L;
        final c<? super R> actual;
        volatile boolean cancelled;
        final io.reactivex.functions.g<? super Object[], ? extends R> combiner;
        int completedSources;
        final boolean delayErrors;
        volatile boolean done;
        final AtomicReference<Throwable> error;
        final Object[] latest;
        int nonEmptySources;
        boolean outputFused;
        final io.reactivex.internal.queue.a<Object> queue;
        final AtomicLong requested;
        final CombineLatestInnerSubscriber<T>[] subscribers;

        CombineLatestCoordinator(c<? super R> cVar, io.reactivex.functions.g<? super Object[], ? extends R> gVar, int i, int i2, boolean z) {
            this.actual = cVar;
            this.combiner = gVar;
            CombineLatestInnerSubscriber<T>[] combineLatestInnerSubscriberArr = new CombineLatestInnerSubscriber[i];
            for (int i3 = 0; i3 < i; i3++) {
                combineLatestInnerSubscriberArr[i3] = new CombineLatestInnerSubscriber<>(this, i3, i2);
            }
            this.subscribers = combineLatestInnerSubscriberArr;
            this.latest = new Object[i];
            this.queue = new io.reactivex.internal.queue.a<>(i2);
            this.requested = new AtomicLong();
            this.error = new AtomicReference<>();
            this.delayErrors = z;
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                io.reactivex.internal.util.b.a(this.requested, j);
                drain();
            }
        }

        public void cancel() {
            this.cancelled = true;
            cancelAll();
        }

        /* access modifiers changed from: 0000 */
        public void subscribe(b<? extends T>[] bVarArr, int i) {
            CombineLatestInnerSubscriber<T>[] combineLatestInnerSubscriberArr = this.subscribers;
            for (int i2 = 0; i2 < i && !this.done && !this.cancelled; i2++) {
                bVarArr[i2].subscribe(combineLatestInnerSubscriberArr[i2]);
            }
        }

        /* access modifiers changed from: 0000 */
        public void innerValue(int i, T t) {
            boolean z;
            synchronized (this) {
                Object[] objArr = this.latest;
                int i2 = this.nonEmptySources;
                if (objArr[i] == null) {
                    i2++;
                    this.nonEmptySources = i2;
                }
                objArr[i] = t;
                if (objArr.length == i2) {
                    this.queue.a(this.subscribers[i], objArr.clone());
                    z = false;
                } else {
                    z = true;
                }
            }
            if (z) {
                this.subscribers[i].requestOne();
            } else {
                drain();
            }
        }

        /* access modifiers changed from: 0000 */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0018, code lost:
            drain();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x001b, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void innerComplete(int r3) {
            /*
                r2 = this;
                monitor-enter(r2)
                java.lang.Object[] r0 = r2.latest     // Catch:{ all -> 0x001c }
                r3 = r0[r3]     // Catch:{ all -> 0x001c }
                r1 = 1
                if (r3 == 0) goto L_0x0015
                int r3 = r2.completedSources     // Catch:{ all -> 0x001c }
                int r3 = r3 + r1
                int r0 = r0.length     // Catch:{ all -> 0x001c }
                if (r3 != r0) goto L_0x0011
                r2.done = r1     // Catch:{ all -> 0x001c }
                goto L_0x0017
            L_0x0011:
                r2.completedSources = r3     // Catch:{ all -> 0x001c }
                monitor-exit(r2)     // Catch:{ all -> 0x001c }
                return
            L_0x0015:
                r2.done = r1     // Catch:{ all -> 0x001c }
            L_0x0017:
                monitor-exit(r2)     // Catch:{ all -> 0x001c }
                r2.drain()
                return
            L_0x001c:
                r3 = move-exception
                monitor-exit(r2)     // Catch:{ all -> 0x001c }
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.flowable.FlowableCombineLatest.CombineLatestCoordinator.innerComplete(int):void");
        }

        /* access modifiers changed from: 0000 */
        public void innerError(int i, Throwable th) {
            if (!ExceptionHelper.a(this.error, th)) {
                io.reactivex.d.a.a(th);
            } else if (!this.delayErrors) {
                cancelAll();
                this.done = true;
                drain();
            } else {
                innerComplete(i);
            }
        }

        /* access modifiers changed from: 0000 */
        public void drainOutput() {
            c<? super R> cVar = this.actual;
            io.reactivex.internal.queue.a<Object> aVar = this.queue;
            int i = 1;
            while (!this.cancelled) {
                Throwable th = (Throwable) this.error.get();
                if (th != null) {
                    aVar.clear();
                    cVar.onError(th);
                    return;
                }
                boolean z = this.done;
                boolean isEmpty = aVar.isEmpty();
                if (!isEmpty) {
                    cVar.onNext(null);
                }
                if (!z || !isEmpty) {
                    i = addAndGet(-i);
                    if (i == 0) {
                        return;
                    }
                } else {
                    cVar.onComplete();
                    return;
                }
            }
            aVar.clear();
        }

        /* access modifiers changed from: 0000 */
        public void drainAsync() {
            c<? super R> cVar = this.actual;
            io.reactivex.internal.queue.a<Object> aVar = this.queue;
            int i = 1;
            do {
                long j = this.requested.get();
                long j2 = 0;
                while (j2 != j) {
                    boolean z = this.done;
                    Object poll = aVar.poll();
                    boolean z2 = poll == null;
                    if (!checkTerminated(z, z2, cVar, aVar)) {
                        if (z2) {
                            break;
                        }
                        try {
                            cVar.onNext(io.reactivex.internal.functions.a.a(this.combiner.apply((Object[]) aVar.poll()), "The combiner returned a null value"));
                            ((CombineLatestInnerSubscriber) poll).requestOne();
                            j2++;
                        } catch (Throwable th) {
                            io.reactivex.exceptions.a.b(th);
                            cancelAll();
                            ExceptionHelper.a(this.error, th);
                            cVar.onError(ExceptionHelper.a(this.error));
                            return;
                        }
                    } else {
                        return;
                    }
                }
                if (j2 != j || !checkTerminated(this.done, aVar.isEmpty(), cVar, aVar)) {
                    if (!(j2 == 0 || j == Long.MAX_VALUE)) {
                        this.requested.addAndGet(-j2);
                    }
                    i = addAndGet(-i);
                } else {
                    return;
                }
            } while (i != 0);
        }

        /* access modifiers changed from: 0000 */
        public void drain() {
            if (getAndIncrement() == 0) {
                if (this.outputFused) {
                    drainOutput();
                } else {
                    drainAsync();
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public boolean checkTerminated(boolean z, boolean z2, c<?> cVar, io.reactivex.internal.queue.a<?> aVar) {
            if (this.cancelled) {
                cancelAll();
                aVar.clear();
                return true;
            }
            if (z) {
                if (!this.delayErrors) {
                    Throwable a = ExceptionHelper.a(this.error);
                    if (a != null && a != ExceptionHelper.a) {
                        cancelAll();
                        aVar.clear();
                        cVar.onError(a);
                        return true;
                    } else if (z2) {
                        cancelAll();
                        cVar.onComplete();
                        return true;
                    }
                } else if (z2) {
                    cancelAll();
                    Throwable a2 = ExceptionHelper.a(this.error);
                    if (a2 == null || a2 == ExceptionHelper.a) {
                        cVar.onComplete();
                    } else {
                        cVar.onError(a2);
                    }
                    return true;
                }
            }
            return false;
        }

        /* access modifiers changed from: 0000 */
        public void cancelAll() {
            for (CombineLatestInnerSubscriber<T> cancel : this.subscribers) {
                cancel.cancel();
            }
        }

        public int requestFusion(int i) {
            boolean z = false;
            if ((i & 4) != 0) {
                return 0;
            }
            int i2 = i & 2;
            if (i2 != 0) {
                z = true;
            }
            this.outputFused = z;
            return i2;
        }

        public R poll() throws Exception {
            Object poll = this.queue.poll();
            if (poll == null) {
                return null;
            }
            R a = io.reactivex.internal.functions.a.a(this.combiner.apply((Object[]) this.queue.poll()), "The combiner returned a null value");
            ((CombineLatestInnerSubscriber) poll).requestOne();
            return a;
        }

        public void clear() {
            this.queue.clear();
        }

        public boolean isEmpty() {
            return this.queue.isEmpty();
        }
    }

    static final class CombineLatestInnerSubscriber<T> extends AtomicReference<Subscription> implements j<T> {
        private static final long serialVersionUID = -8730235182291002949L;
        final int index;
        final int limit;
        final CombineLatestCoordinator<T, ?> parent;
        final int prefetch;
        int produced;

        CombineLatestInnerSubscriber(CombineLatestCoordinator<T, ?> combineLatestCoordinator, int i, int i2) {
            this.parent = combineLatestCoordinator;
            this.index = i;
            this.prefetch = i2;
            this.limit = i2 - (i2 >> 2);
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.setOnce(this, subscription)) {
                subscription.request((long) this.prefetch);
            }
        }

        public void onNext(T t) {
            this.parent.innerValue(this.index, t);
        }

        public void onError(Throwable th) {
            this.parent.innerError(this.index, th);
        }

        public void onComplete() {
            this.parent.innerComplete(this.index);
        }

        public void cancel() {
            SubscriptionHelper.cancel(this);
        }

        public void requestOne() {
            int i = this.produced + 1;
            if (i == this.limit) {
                this.produced = 0;
                ((Subscription) get()).request((long) i);
                return;
            }
            this.produced = i;
        }
    }

    final class a implements io.reactivex.functions.g<T, R> {
        a() {
        }

        public R apply(T t) throws Exception {
            return FlowableCombineLatest.this.d.apply(new Object[]{t});
        }
    }

    public void a(c<? super R> cVar) {
        int i;
        b<? extends T>[] bVarArr = this.b;
        if (bVarArr == null) {
            bVarArr = new b[8];
            try {
                Iterator it = (Iterator) io.reactivex.internal.functions.a.a(this.c.iterator(), "The iterator returned is null");
                i = 0;
                while (it.hasNext()) {
                    try {
                        try {
                            b<? extends T> bVar = (b) io.reactivex.internal.functions.a.a(it.next(), "The publisher returned by the iterator is null");
                            if (i == bVarArr.length) {
                                b<? extends T>[] bVarArr2 = new b[((i >> 2) + i)];
                                System.arraycopy(bVarArr, 0, bVarArr2, 0, i);
                                bVarArr = bVarArr2;
                            }
                            int i2 = i + 1;
                            bVarArr[i] = bVar;
                            i = i2;
                        } catch (Throwable th) {
                            io.reactivex.exceptions.a.b(th);
                            EmptySubscription.error(th, cVar);
                            return;
                        }
                    } catch (Throwable th2) {
                        io.reactivex.exceptions.a.b(th2);
                        EmptySubscription.error(th2, cVar);
                        return;
                    }
                }
            } catch (Throwable th3) {
                io.reactivex.exceptions.a.b(th3);
                EmptySubscription.error(th3, cVar);
                return;
            }
        } else {
            i = bVarArr.length;
        }
        if (i == 0) {
            EmptySubscription.complete(cVar);
        } else if (i == 1) {
            bVarArr[0].subscribe(new b(cVar, new a()));
        } else {
            CombineLatestCoordinator combineLatestCoordinator = new CombineLatestCoordinator(cVar, this.d, i, this.e, this.f);
            cVar.onSubscribe(combineLatestCoordinator);
            combineLatestCoordinator.subscribe(bVarArr, i);
        }
    }
}
