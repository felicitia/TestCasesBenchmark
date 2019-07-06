package io.reactivex.internal.operators.parallel;

import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.g;
import io.reactivex.internal.a.f;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.b;
import io.reactivex.j;
import io.reactivex.parallel.a;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;
import org.reactivestreams.c;

public final class ParallelJoin<T> extends g<T> {
    final a<? extends T> b;
    final int c;
    final boolean d;

    static final class JoinInnerSubscriber<T> extends AtomicReference<Subscription> implements j<T> {
        private static final long serialVersionUID = 8410034718427740355L;
        final int limit;
        final JoinSubscriptionBase<T> parent;
        final int prefetch;
        long produced;
        volatile f<T> queue;

        JoinInnerSubscriber(JoinSubscriptionBase<T> joinSubscriptionBase, int i) {
            this.parent = joinSubscriptionBase;
            this.prefetch = i;
            this.limit = i - (i >> 2);
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.setOnce(this, subscription)) {
                subscription.request((long) this.prefetch);
            }
        }

        public void onNext(T t) {
            this.parent.onNext(this, t);
        }

        public void onError(Throwable th) {
            this.parent.onError(th);
        }

        public void onComplete() {
            this.parent.onComplete();
        }

        public void requestOne() {
            long j = this.produced + 1;
            if (j == ((long) this.limit)) {
                this.produced = 0;
                ((Subscription) get()).request(j);
                return;
            }
            this.produced = j;
        }

        public void request(long j) {
            long j2 = this.produced + j;
            if (j2 >= ((long) this.limit)) {
                this.produced = 0;
                ((Subscription) get()).request(j2);
                return;
            }
            this.produced = j2;
        }

        public boolean cancel() {
            return SubscriptionHelper.cancel(this);
        }

        /* access modifiers changed from: 0000 */
        public f<T> getQueue() {
            f<T> fVar = this.queue;
            if (fVar != null) {
                return fVar;
            }
            SpscArrayQueue spscArrayQueue = new SpscArrayQueue(this.prefetch);
            this.queue = spscArrayQueue;
            return spscArrayQueue;
        }
    }

    static final class JoinSubscription<T> extends JoinSubscriptionBase<T> {
        private static final long serialVersionUID = 6312374661811000451L;

        JoinSubscription(c<? super T> cVar, int i, int i2) {
            super(cVar, i, i2);
        }

        public void onNext(JoinInnerSubscriber<T> joinInnerSubscriber, T t) {
            if (get() == 0 && compareAndSet(0, 1)) {
                if (this.requested.get() != 0) {
                    this.actual.onNext(t);
                    if (this.requested.get() != Long.MAX_VALUE) {
                        this.requested.decrementAndGet();
                    }
                    joinInnerSubscriber.request(1);
                } else if (!joinInnerSubscriber.getQueue().offer(t)) {
                    cancelAll();
                    MissingBackpressureException missingBackpressureException = new MissingBackpressureException("Queue full?!");
                    if (this.errors.compareAndSet(null, missingBackpressureException)) {
                        this.actual.onError(missingBackpressureException);
                    } else {
                        io.reactivex.d.a.a((Throwable) missingBackpressureException);
                    }
                    return;
                }
                if (decrementAndGet() == 0) {
                    return;
                }
            } else if (!joinInnerSubscriber.getQueue().offer(t)) {
                cancelAll();
                onError(new MissingBackpressureException("Queue full?!"));
                return;
            } else if (getAndIncrement() != 0) {
                return;
            }
            drainLoop();
        }

        public void onError(Throwable th) {
            if (this.errors.compareAndSet(null, th)) {
                cancelAll();
                drain();
            } else if (th != this.errors.get()) {
                io.reactivex.d.a.a(th);
            }
        }

        public void onComplete() {
            this.done.decrementAndGet();
            drain();
        }

        /* access modifiers changed from: 0000 */
        public void drain() {
            if (getAndIncrement() == 0) {
                drainLoop();
            }
        }

        /* access modifiers changed from: 0000 */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x0062, code lost:
            if (r12 == false) goto L_0x006a;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:0x0064, code lost:
            if (r11 == false) goto L_0x006a;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x0066, code lost:
            r4.onComplete();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:0x0069, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:0x006a, code lost:
            if (r11 == false) goto L_0x006d;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void drainLoop() {
            /*
                r21 = this;
                r0 = r21
                io.reactivex.internal.operators.parallel.ParallelJoin$JoinInnerSubscriber[] r1 = r0.subscribers
                int r3 = r1.length
                org.reactivestreams.c r4 = r0.actual
                r5 = 1
            L_0x0008:
                java.util.concurrent.atomic.AtomicLong r6 = r0.requested
                long r6 = r6.get()
                r8 = 0
                r10 = r8
            L_0x0011:
                int r12 = (r10 > r6 ? 1 : (r10 == r6 ? 0 : -1))
                if (r12 == 0) goto L_0x006f
                boolean r12 = r0.cancelled
                if (r12 == 0) goto L_0x001d
                r21.cleanup()
                return
            L_0x001d:
                io.reactivex.internal.util.AtomicThrowable r12 = r0.errors
                java.lang.Object r12 = r12.get()
                java.lang.Throwable r12 = (java.lang.Throwable) r12
                if (r12 == 0) goto L_0x002e
                r21.cleanup()
                r4.onError(r12)
                return
            L_0x002e:
                java.util.concurrent.atomic.AtomicInteger r12 = r0.done
                int r12 = r12.get()
                if (r12 != 0) goto L_0x0038
                r12 = 1
                goto L_0x0039
            L_0x0038:
                r12 = 0
            L_0x0039:
                r14 = r10
                r10 = 0
                r11 = 1
            L_0x003c:
                int r2 = r1.length
                if (r10 >= r2) goto L_0x0062
                r2 = r1[r10]
                io.reactivex.internal.a.f<T> r13 = r2.queue
                if (r13 == 0) goto L_0x005f
                java.lang.Object r13 = r13.poll()
                if (r13 == 0) goto L_0x005f
                r4.onNext(r13)
                r2.requestOne()
                r17 = 1
                long r19 = r14 + r17
                int r2 = (r19 > r6 ? 1 : (r19 == r6 ? 0 : -1))
                if (r2 != 0) goto L_0x005c
                r14 = r19
                goto L_0x0070
            L_0x005c:
                r14 = r19
                r11 = 0
            L_0x005f:
                int r10 = r10 + 1
                goto L_0x003c
            L_0x0062:
                if (r12 == 0) goto L_0x006a
                if (r11 == 0) goto L_0x006a
                r4.onComplete()
                return
            L_0x006a:
                if (r11 == 0) goto L_0x006d
                goto L_0x0070
            L_0x006d:
                r10 = r14
                goto L_0x0011
            L_0x006f:
                r14 = r10
            L_0x0070:
                int r2 = (r14 > r6 ? 1 : (r14 == r6 ? 0 : -1))
                if (r2 != 0) goto L_0x00b7
                boolean r2 = r0.cancelled
                if (r2 == 0) goto L_0x007c
                r21.cleanup()
                return
            L_0x007c:
                io.reactivex.internal.util.AtomicThrowable r2 = r0.errors
                java.lang.Object r2 = r2.get()
                java.lang.Throwable r2 = (java.lang.Throwable) r2
                if (r2 == 0) goto L_0x008d
                r21.cleanup()
                r4.onError(r2)
                return
            L_0x008d:
                java.util.concurrent.atomic.AtomicInteger r2 = r0.done
                int r2 = r2.get()
                if (r2 != 0) goto L_0x0097
                r2 = 1
                goto L_0x0098
            L_0x0097:
                r2 = 0
            L_0x0098:
                r10 = 0
            L_0x0099:
                if (r10 >= r3) goto L_0x00ad
                r11 = r1[r10]
                io.reactivex.internal.a.f<T> r11 = r11.queue
                if (r11 == 0) goto L_0x00aa
                boolean r11 = r11.isEmpty()
                if (r11 != 0) goto L_0x00aa
                r16 = 0
                goto L_0x00af
            L_0x00aa:
                int r10 = r10 + 1
                goto L_0x0099
            L_0x00ad:
                r16 = 1
            L_0x00af:
                if (r2 == 0) goto L_0x00b7
                if (r16 == 0) goto L_0x00b7
                r4.onComplete()
                return
            L_0x00b7:
                int r2 = (r14 > r8 ? 1 : (r14 == r8 ? 0 : -1))
                if (r2 == 0) goto L_0x00ca
                r8 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                int r2 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
                if (r2 == 0) goto L_0x00ca
                java.util.concurrent.atomic.AtomicLong r2 = r0.requested
                long r6 = -r14
                r2.addAndGet(r6)
            L_0x00ca:
                int r2 = r21.get()
                if (r2 != r5) goto L_0x00d8
                int r2 = -r5
                int r2 = r0.addAndGet(r2)
                if (r2 != 0) goto L_0x00d8
                return
            L_0x00d8:
                r5 = r2
                goto L_0x0008
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.parallel.ParallelJoin.JoinSubscription.drainLoop():void");
        }
    }

    static abstract class JoinSubscriptionBase<T> extends AtomicInteger implements Subscription {
        private static final long serialVersionUID = 3100232009247827843L;
        final c<? super T> actual;
        volatile boolean cancelled;
        final AtomicInteger done = new AtomicInteger();
        final AtomicThrowable errors = new AtomicThrowable();
        final AtomicLong requested = new AtomicLong();
        final JoinInnerSubscriber<T>[] subscribers;

        /* access modifiers changed from: 0000 */
        public abstract void drain();

        /* access modifiers changed from: 0000 */
        public abstract void onComplete();

        /* access modifiers changed from: 0000 */
        public abstract void onError(Throwable th);

        /* access modifiers changed from: 0000 */
        public abstract void onNext(JoinInnerSubscriber<T> joinInnerSubscriber, T t);

        JoinSubscriptionBase(c<? super T> cVar, int i, int i2) {
            this.actual = cVar;
            JoinInnerSubscriber<T>[] joinInnerSubscriberArr = new JoinInnerSubscriber[i];
            for (int i3 = 0; i3 < i; i3++) {
                joinInnerSubscriberArr[i3] = new JoinInnerSubscriber<>(this, i2);
            }
            this.subscribers = joinInnerSubscriberArr;
            this.done.lazySet(i);
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                b.a(this.requested, j);
                drain();
            }
        }

        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                cancelAll();
                if (getAndIncrement() == 0) {
                    cleanup();
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void cancelAll() {
            for (JoinInnerSubscriber<T> cancel : this.subscribers) {
                cancel.cancel();
            }
        }

        /* access modifiers changed from: 0000 */
        public void cleanup() {
            for (JoinInnerSubscriber<T> joinInnerSubscriber : this.subscribers) {
                joinInnerSubscriber.queue = null;
            }
        }
    }

    static final class JoinSubscriptionDelayError<T> extends JoinSubscriptionBase<T> {
        private static final long serialVersionUID = -5737965195918321883L;

        JoinSubscriptionDelayError(c<? super T> cVar, int i, int i2) {
            super(cVar, i, i2);
        }

        /* access modifiers changed from: 0000 */
        public void onNext(JoinInnerSubscriber<T> joinInnerSubscriber, T t) {
            if (get() != 0 || !compareAndSet(0, 1)) {
                if (!joinInnerSubscriber.getQueue().offer(t) && joinInnerSubscriber.cancel()) {
                    this.errors.addThrowable(new MissingBackpressureException("Queue full?!"));
                    this.done.decrementAndGet();
                }
                if (getAndIncrement() != 0) {
                    return;
                }
            } else {
                if (this.requested.get() != 0) {
                    this.actual.onNext(t);
                    if (this.requested.get() != Long.MAX_VALUE) {
                        this.requested.decrementAndGet();
                    }
                    joinInnerSubscriber.request(1);
                } else if (!joinInnerSubscriber.getQueue().offer(t)) {
                    joinInnerSubscriber.cancel();
                    this.errors.addThrowable(new MissingBackpressureException("Queue full?!"));
                    this.done.decrementAndGet();
                    drainLoop();
                    return;
                }
                if (decrementAndGet() == 0) {
                    return;
                }
            }
            drainLoop();
        }

        /* access modifiers changed from: 0000 */
        public void onError(Throwable th) {
            this.errors.addThrowable(th);
            this.done.decrementAndGet();
            drain();
        }

        /* access modifiers changed from: 0000 */
        public void onComplete() {
            this.done.decrementAndGet();
            drain();
        }

        /* access modifiers changed from: 0000 */
        public void drain() {
            if (getAndIncrement() == 0) {
                drainLoop();
            }
        }

        /* access modifiers changed from: 0000 */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x0050, code lost:
            if (r12 == false) goto L_0x006c;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x0052, code lost:
            if (r11 == false) goto L_0x006c;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x005c, code lost:
            if (((java.lang.Throwable) r0.errors.get()) == null) goto L_0x0068;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x005e, code lost:
            r4.onError(r0.errors.terminate());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x0068, code lost:
            r4.onComplete();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:0x006b, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x006c, code lost:
            if (r11 == false) goto L_0x006f;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void drainLoop() {
            /*
                r21 = this;
                r0 = r21
                io.reactivex.internal.operators.parallel.ParallelJoin$JoinInnerSubscriber[] r1 = r0.subscribers
                int r3 = r1.length
                org.reactivestreams.c r4 = r0.actual
                r5 = 1
            L_0x0008:
                java.util.concurrent.atomic.AtomicLong r6 = r0.requested
                long r6 = r6.get()
                r8 = 0
                r10 = r8
            L_0x0011:
                int r12 = (r10 > r6 ? 1 : (r10 == r6 ? 0 : -1))
                if (r12 == 0) goto L_0x0071
                boolean r12 = r0.cancelled
                if (r12 == 0) goto L_0x001d
                r21.cleanup()
                return
            L_0x001d:
                java.util.concurrent.atomic.AtomicInteger r12 = r0.done
                int r12 = r12.get()
                if (r12 != 0) goto L_0x0027
                r12 = 1
                goto L_0x0028
            L_0x0027:
                r12 = 0
            L_0x0028:
                r14 = r10
                r10 = 0
                r11 = 1
            L_0x002b:
                if (r10 >= r3) goto L_0x0050
                r2 = r1[r10]
                io.reactivex.internal.a.f<T> r13 = r2.queue
                if (r13 == 0) goto L_0x004d
                java.lang.Object r13 = r13.poll()
                if (r13 == 0) goto L_0x004d
                r4.onNext(r13)
                r2.requestOne()
                r17 = 1
                long r19 = r14 + r17
                int r2 = (r19 > r6 ? 1 : (r19 == r6 ? 0 : -1))
                if (r2 != 0) goto L_0x004a
                r14 = r19
                goto L_0x0072
            L_0x004a:
                r14 = r19
                r11 = 0
            L_0x004d:
                int r10 = r10 + 1
                goto L_0x002b
            L_0x0050:
                if (r12 == 0) goto L_0x006c
                if (r11 == 0) goto L_0x006c
                io.reactivex.internal.util.AtomicThrowable r1 = r0.errors
                java.lang.Object r1 = r1.get()
                java.lang.Throwable r1 = (java.lang.Throwable) r1
                if (r1 == 0) goto L_0x0068
                io.reactivex.internal.util.AtomicThrowable r1 = r0.errors
                java.lang.Throwable r1 = r1.terminate()
                r4.onError(r1)
                goto L_0x006b
            L_0x0068:
                r4.onComplete()
            L_0x006b:
                return
            L_0x006c:
                if (r11 == 0) goto L_0x006f
                goto L_0x0072
            L_0x006f:
                r10 = r14
                goto L_0x0011
            L_0x0071:
                r14 = r10
            L_0x0072:
                int r2 = (r14 > r6 ? 1 : (r14 == r6 ? 0 : -1))
                if (r2 != 0) goto L_0x00bc
                boolean r2 = r0.cancelled
                if (r2 == 0) goto L_0x007e
                r21.cleanup()
                return
            L_0x007e:
                java.util.concurrent.atomic.AtomicInteger r2 = r0.done
                int r2 = r2.get()
                if (r2 != 0) goto L_0x0088
                r2 = 1
                goto L_0x0089
            L_0x0088:
                r2 = 0
            L_0x0089:
                r10 = 0
            L_0x008a:
                if (r10 >= r3) goto L_0x009e
                r11 = r1[r10]
                io.reactivex.internal.a.f<T> r11 = r11.queue
                if (r11 == 0) goto L_0x009b
                boolean r11 = r11.isEmpty()
                if (r11 != 0) goto L_0x009b
                r16 = 0
                goto L_0x00a0
            L_0x009b:
                int r10 = r10 + 1
                goto L_0x008a
            L_0x009e:
                r16 = 1
            L_0x00a0:
                if (r2 == 0) goto L_0x00bc
                if (r16 == 0) goto L_0x00bc
                io.reactivex.internal.util.AtomicThrowable r1 = r0.errors
                java.lang.Object r1 = r1.get()
                java.lang.Throwable r1 = (java.lang.Throwable) r1
                if (r1 == 0) goto L_0x00b8
                io.reactivex.internal.util.AtomicThrowable r1 = r0.errors
                java.lang.Throwable r1 = r1.terminate()
                r4.onError(r1)
                goto L_0x00bb
            L_0x00b8:
                r4.onComplete()
            L_0x00bb:
                return
            L_0x00bc:
                int r2 = (r14 > r8 ? 1 : (r14 == r8 ? 0 : -1))
                if (r2 == 0) goto L_0x00cf
                r8 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                int r2 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
                if (r2 == 0) goto L_0x00cf
                java.util.concurrent.atomic.AtomicLong r2 = r0.requested
                long r6 = -r14
                r2.addAndGet(r6)
            L_0x00cf:
                int r2 = r21.get()
                if (r2 != r5) goto L_0x00dd
                int r2 = -r5
                int r2 = r0.addAndGet(r2)
                if (r2 != 0) goto L_0x00dd
                return
            L_0x00dd:
                r5 = r2
                goto L_0x0008
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.parallel.ParallelJoin.JoinSubscriptionDelayError.drainLoop():void");
        }
    }

    /* access modifiers changed from: protected */
    public void a(c<? super T> cVar) {
        JoinSubscriptionBase joinSubscriptionBase;
        if (this.d) {
            joinSubscriptionBase = new JoinSubscriptionDelayError(cVar, this.b.a(), this.c);
        } else {
            joinSubscriptionBase = new JoinSubscription(cVar, this.b.a(), this.c);
        }
        cVar.onSubscribe(joinSubscriptionBase);
        this.b.a(joinSubscriptionBase.subscribers);
    }
}
