package io.reactivex.processors;

import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.internal.util.a;
import io.reactivex.internal.util.a.C0189a;
import io.reactivex.internal.util.b;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import org.reactivestreams.Subscription;
import org.reactivestreams.c;

public final class BehaviorProcessor<T> extends a<T> {
    static final Object[] c = new Object[0];
    static final BehaviorSubscription[] d = new BehaviorSubscription[0];
    static final BehaviorSubscription[] e = new BehaviorSubscription[0];
    final AtomicReference<BehaviorSubscription<T>[]> b;
    final Lock f;
    final Lock g;
    final AtomicReference<Object> h;
    final AtomicReference<Throwable> i;
    long j;

    static final class BehaviorSubscription<T> extends AtomicLong implements C0189a<Object>, Subscription {
        private static final long serialVersionUID = 3293175281126227086L;
        final c<? super T> actual;
        volatile boolean cancelled;
        boolean emitting;
        boolean fastPath;
        long index;
        boolean next;
        a<Object> queue;
        final BehaviorProcessor<T> state;

        BehaviorSubscription(c<? super T> cVar, BehaviorProcessor<T> behaviorProcessor) {
            this.actual = cVar;
            this.state = behaviorProcessor;
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                b.a((AtomicLong) this, j);
            }
        }

        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.state.b(this);
            }
        }

        /* access modifiers changed from: 0000 */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0031, code lost:
            if (r0 == null) goto L_0x003d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0037, code lost:
            if (test(r0) == false) goto L_0x003a;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x0039, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x003a, code lost:
            emitLoop();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x003d, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void emitFirst() {
            /*
                r4 = this;
                boolean r0 = r4.cancelled
                if (r0 == 0) goto L_0x0005
                return
            L_0x0005:
                monitor-enter(r4)
                boolean r0 = r4.cancelled     // Catch:{ all -> 0x003e }
                if (r0 == 0) goto L_0x000c
                monitor-exit(r4)     // Catch:{ all -> 0x003e }
                return
            L_0x000c:
                boolean r0 = r4.next     // Catch:{ all -> 0x003e }
                if (r0 == 0) goto L_0x0012
                monitor-exit(r4)     // Catch:{ all -> 0x003e }
                return
            L_0x0012:
                io.reactivex.processors.BehaviorProcessor<T> r0 = r4.state     // Catch:{ all -> 0x003e }
                java.util.concurrent.locks.Lock r1 = r0.f     // Catch:{ all -> 0x003e }
                r1.lock()     // Catch:{ all -> 0x003e }
                long r2 = r0.j     // Catch:{ all -> 0x003e }
                r4.index = r2     // Catch:{ all -> 0x003e }
                java.util.concurrent.atomic.AtomicReference<java.lang.Object> r0 = r0.h     // Catch:{ all -> 0x003e }
                java.lang.Object r0 = r0.get()     // Catch:{ all -> 0x003e }
                r1.unlock()     // Catch:{ all -> 0x003e }
                r1 = 1
                if (r0 == 0) goto L_0x002b
                r2 = r1
                goto L_0x002c
            L_0x002b:
                r2 = 0
            L_0x002c:
                r4.emitting = r2     // Catch:{ all -> 0x003e }
                r4.next = r1     // Catch:{ all -> 0x003e }
                monitor-exit(r4)     // Catch:{ all -> 0x003e }
                if (r0 == 0) goto L_0x003d
                boolean r0 = r4.test(r0)
                if (r0 == 0) goto L_0x003a
                return
            L_0x003a:
                r4.emitLoop()
            L_0x003d:
                return
            L_0x003e:
                r0 = move-exception
                monitor-exit(r4)     // Catch:{ all -> 0x003e }
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.processors.BehaviorProcessor.BehaviorSubscription.emitFirst():void");
        }

        /* access modifiers changed from: 0000 */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x0031, code lost:
            r3.fastPath = true;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void emitNext(java.lang.Object r4, long r5) {
            /*
                r3 = this;
                boolean r0 = r3.cancelled
                if (r0 == 0) goto L_0x0005
                return
            L_0x0005:
                boolean r0 = r3.fastPath
                if (r0 != 0) goto L_0x0037
                monitor-enter(r3)
                boolean r0 = r3.cancelled     // Catch:{ all -> 0x0034 }
                if (r0 == 0) goto L_0x0010
                monitor-exit(r3)     // Catch:{ all -> 0x0034 }
                return
            L_0x0010:
                long r0 = r3.index     // Catch:{ all -> 0x0034 }
                int r2 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
                if (r2 != 0) goto L_0x0018
                monitor-exit(r3)     // Catch:{ all -> 0x0034 }
                return
            L_0x0018:
                boolean r5 = r3.emitting     // Catch:{ all -> 0x0034 }
                if (r5 == 0) goto L_0x002d
                io.reactivex.internal.util.a<java.lang.Object> r5 = r3.queue     // Catch:{ all -> 0x0034 }
                if (r5 != 0) goto L_0x0028
                io.reactivex.internal.util.a r5 = new io.reactivex.internal.util.a     // Catch:{ all -> 0x0034 }
                r6 = 4
                r5.<init>(r6)     // Catch:{ all -> 0x0034 }
                r3.queue = r5     // Catch:{ all -> 0x0034 }
            L_0x0028:
                r5.a(r4)     // Catch:{ all -> 0x0034 }
                monitor-exit(r3)     // Catch:{ all -> 0x0034 }
                return
            L_0x002d:
                r5 = 1
                r3.next = r5     // Catch:{ all -> 0x0034 }
                monitor-exit(r3)     // Catch:{ all -> 0x0034 }
                r3.fastPath = r5
                goto L_0x0037
            L_0x0034:
                r4 = move-exception
                monitor-exit(r3)     // Catch:{ all -> 0x0034 }
                throw r4
            L_0x0037:
                r3.test(r4)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.processors.BehaviorProcessor.BehaviorSubscription.emitNext(java.lang.Object, long):void");
        }

        public boolean test(Object obj) {
            if (this.cancelled) {
                return true;
            }
            if (NotificationLite.isComplete(obj)) {
                this.actual.onComplete();
                return true;
            } else if (NotificationLite.isError(obj)) {
                this.actual.onError(NotificationLite.getError(obj));
                return true;
            } else {
                long j = get();
                if (j != 0) {
                    this.actual.onNext(NotificationLite.getValue(obj));
                    if (j != Long.MAX_VALUE) {
                        decrementAndGet();
                    }
                    return false;
                }
                cancel();
                this.actual.onError(new MissingBackpressureException("Could not deliver value due to lack of requests"));
                return true;
            }
        }

        /* access modifiers changed from: 0000 */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0013, code lost:
            r0.a((io.reactivex.internal.util.a.C0189a<? super T>) r2);
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void emitLoop() {
            /*
                r2 = this;
            L_0x0000:
                boolean r0 = r2.cancelled
                if (r0 == 0) goto L_0x0005
                return
            L_0x0005:
                monitor-enter(r2)
                io.reactivex.internal.util.a<java.lang.Object> r0 = r2.queue     // Catch:{ all -> 0x0017 }
                if (r0 != 0) goto L_0x000f
                r0 = 0
                r2.emitting = r0     // Catch:{ all -> 0x0017 }
                monitor-exit(r2)     // Catch:{ all -> 0x0017 }
                return
            L_0x000f:
                r1 = 0
                r2.queue = r1     // Catch:{ all -> 0x0017 }
                monitor-exit(r2)     // Catch:{ all -> 0x0017 }
                r0.a(r2)
                goto L_0x0000
            L_0x0017:
                r0 = move-exception
                monitor-exit(r2)     // Catch:{ all -> 0x0017 }
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.processors.BehaviorProcessor.BehaviorSubscription.emitLoop():void");
        }

        public boolean isFull() {
            return get() == 0;
        }
    }

    /* access modifiers changed from: protected */
    public void a(c<? super T> cVar) {
        BehaviorSubscription behaviorSubscription = new BehaviorSubscription(cVar, this);
        cVar.onSubscribe(behaviorSubscription);
        if (!a(behaviorSubscription)) {
            Throwable th = (Throwable) this.i.get();
            if (th == ExceptionHelper.a) {
                cVar.onComplete();
            } else {
                cVar.onError(th);
            }
        } else if (behaviorSubscription.cancelled) {
            b(behaviorSubscription);
        } else {
            behaviorSubscription.emitFirst();
        }
    }

    public void onSubscribe(Subscription subscription) {
        if (this.i.get() != null) {
            subscription.cancel();
        } else {
            subscription.request(Long.MAX_VALUE);
        }
    }

    public void onNext(T t) {
        if (t == null) {
            onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
        } else if (this.i.get() == null) {
            Object next = NotificationLite.next(t);
            b(next);
            for (BehaviorSubscription emitNext : (BehaviorSubscription[]) this.b.get()) {
                emitNext.emitNext(next, this.j);
            }
        }
    }

    public void onError(Throwable th) {
        if (th == null) {
            th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        }
        if (!this.i.compareAndSet(null, th)) {
            io.reactivex.d.a.a(th);
            return;
        }
        Object error = NotificationLite.error(th);
        for (BehaviorSubscription emitNext : a(error)) {
            emitNext.emitNext(error, this.j);
        }
    }

    public void onComplete() {
        if (this.i.compareAndSet(null, ExceptionHelper.a)) {
            Object complete = NotificationLite.complete();
            for (BehaviorSubscription emitNext : a(complete)) {
                emitNext.emitNext(complete, this.j);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean a(BehaviorSubscription<T> behaviorSubscription) {
        BehaviorSubscription[] behaviorSubscriptionArr;
        BehaviorSubscription[] behaviorSubscriptionArr2;
        do {
            behaviorSubscriptionArr = (BehaviorSubscription[]) this.b.get();
            if (behaviorSubscriptionArr == e) {
                return false;
            }
            int length = behaviorSubscriptionArr.length;
            behaviorSubscriptionArr2 = new BehaviorSubscription[(length + 1)];
            System.arraycopy(behaviorSubscriptionArr, 0, behaviorSubscriptionArr2, 0, length);
            behaviorSubscriptionArr2[length] = behaviorSubscription;
        } while (!this.b.compareAndSet(behaviorSubscriptionArr, behaviorSubscriptionArr2));
        return true;
    }

    /* access modifiers changed from: 0000 */
    public void b(BehaviorSubscription<T> behaviorSubscription) {
        BehaviorSubscription<T>[] behaviorSubscriptionArr;
        BehaviorSubscription[] behaviorSubscriptionArr2;
        do {
            behaviorSubscriptionArr = (BehaviorSubscription[]) this.b.get();
            if (behaviorSubscriptionArr != e && behaviorSubscriptionArr != d) {
                int length = behaviorSubscriptionArr.length;
                int i2 = -1;
                int i3 = 0;
                while (true) {
                    if (i3 >= length) {
                        break;
                    } else if (behaviorSubscriptionArr[i3] == behaviorSubscription) {
                        i2 = i3;
                        break;
                    } else {
                        i3++;
                    }
                }
                if (i2 >= 0) {
                    if (length == 1) {
                        behaviorSubscriptionArr2 = d;
                    } else {
                        BehaviorSubscription[] behaviorSubscriptionArr3 = new BehaviorSubscription[(length - 1)];
                        System.arraycopy(behaviorSubscriptionArr, 0, behaviorSubscriptionArr3, 0, i2);
                        System.arraycopy(behaviorSubscriptionArr, i2 + 1, behaviorSubscriptionArr3, i2, (length - i2) - 1);
                        behaviorSubscriptionArr2 = behaviorSubscriptionArr3;
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        } while (!this.b.compareAndSet(behaviorSubscriptionArr, behaviorSubscriptionArr2));
    }

    /* access modifiers changed from: 0000 */
    public BehaviorSubscription<T>[] a(Object obj) {
        BehaviorSubscription<T>[] behaviorSubscriptionArr = (BehaviorSubscription[]) this.b.get();
        if (behaviorSubscriptionArr != e) {
            behaviorSubscriptionArr = (BehaviorSubscription[]) this.b.getAndSet(e);
            if (behaviorSubscriptionArr != e) {
                b(obj);
            }
        }
        return behaviorSubscriptionArr;
    }

    /* access modifiers changed from: 0000 */
    public void b(Object obj) {
        Lock lock = this.g;
        lock.lock();
        this.j++;
        this.h.lazySet(obj);
        lock.unlock();
    }
}
