package io.reactivex.internal.operators.flowable;

import io.reactivex.b.a;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.g;
import io.reactivex.internal.a.d;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.j;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;
import org.reactivestreams.b;
import org.reactivestreams.c;

public final class FlowablePublish<T> extends a<T> {
    final g<T> b;
    final AtomicReference<PublishSubscriber<T>> c;
    final int d;
    final b<T> e;

    static final class InnerSubscriber<T> extends AtomicLong implements Subscription {
        private static final long serialVersionUID = -4453897557930727610L;
        final c<? super T> child;
        volatile PublishSubscriber<T> parent;

        InnerSubscriber(c<? super T> cVar) {
            this.child = cVar;
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                io.reactivex.internal.util.b.b((AtomicLong) this, j);
                PublishSubscriber<T> publishSubscriber = this.parent;
                if (publishSubscriber != null) {
                    publishSubscriber.dispatch();
                }
            }
        }

        public long produced(long j) {
            return io.reactivex.internal.util.b.d(this, j);
        }

        public void cancel() {
            if (get() != Long.MIN_VALUE && getAndSet(Long.MIN_VALUE) != Long.MIN_VALUE) {
                PublishSubscriber<T> publishSubscriber = this.parent;
                if (publishSubscriber != null) {
                    publishSubscriber.remove(this);
                    publishSubscriber.dispatch();
                }
            }
        }
    }

    static final class PublishSubscriber<T> extends AtomicInteger implements Disposable, j<T> {
        static final InnerSubscriber[] EMPTY = new InnerSubscriber[0];
        static final InnerSubscriber[] TERMINATED = new InnerSubscriber[0];
        private static final long serialVersionUID = -202316842419149694L;
        final int bufferSize;
        final AtomicReference<PublishSubscriber<T>> current;
        volatile io.reactivex.internal.a.g<T> queue;
        final AtomicReference<Subscription> s = new AtomicReference<>();
        final AtomicBoolean shouldConnect;
        int sourceMode;
        final AtomicReference<InnerSubscriber[]> subscribers = new AtomicReference<>(EMPTY);
        volatile Object terminalEvent;

        PublishSubscriber(AtomicReference<PublishSubscriber<T>> atomicReference, int i) {
            this.current = atomicReference;
            this.shouldConnect = new AtomicBoolean();
            this.bufferSize = i;
        }

        public void dispose() {
            if (this.subscribers.get() != TERMINATED && ((InnerSubscriber[]) this.subscribers.getAndSet(TERMINATED)) != TERMINATED) {
                this.current.compareAndSet(this, null);
                SubscriptionHelper.cancel(this.s);
            }
        }

        public boolean isDisposed() {
            return this.subscribers.get() == TERMINATED;
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.setOnce(this.s, subscription)) {
                if (subscription instanceof d) {
                    d dVar = (d) subscription;
                    int requestFusion = dVar.requestFusion(3);
                    if (requestFusion == 1) {
                        this.sourceMode = requestFusion;
                        this.queue = dVar;
                        this.terminalEvent = NotificationLite.complete();
                        dispatch();
                        return;
                    } else if (requestFusion == 2) {
                        this.sourceMode = requestFusion;
                        this.queue = dVar;
                        subscription.request((long) this.bufferSize);
                        return;
                    }
                }
                this.queue = new SpscArrayQueue(this.bufferSize);
                subscription.request((long) this.bufferSize);
            }
        }

        public void onNext(T t) {
            if (this.sourceMode != 0 || this.queue.offer(t)) {
                dispatch();
            } else {
                onError(new MissingBackpressureException("Prefetch queue is full?!"));
            }
        }

        public void onError(Throwable th) {
            if (this.terminalEvent == null) {
                this.terminalEvent = NotificationLite.error(th);
                dispatch();
                return;
            }
            io.reactivex.d.a.a(th);
        }

        public void onComplete() {
            if (this.terminalEvent == null) {
                this.terminalEvent = NotificationLite.complete();
                dispatch();
            }
        }

        /* access modifiers changed from: 0000 */
        public boolean add(InnerSubscriber<T> innerSubscriber) {
            InnerSubscriber[] innerSubscriberArr;
            InnerSubscriber[] innerSubscriberArr2;
            do {
                innerSubscriberArr = (InnerSubscriber[]) this.subscribers.get();
                if (innerSubscriberArr == TERMINATED) {
                    return false;
                }
                int length = innerSubscriberArr.length;
                innerSubscriberArr2 = new InnerSubscriber[(length + 1)];
                System.arraycopy(innerSubscriberArr, 0, innerSubscriberArr2, 0, length);
                innerSubscriberArr2[length] = innerSubscriber;
            } while (!this.subscribers.compareAndSet(innerSubscriberArr, innerSubscriberArr2));
            return true;
        }

        /* access modifiers changed from: 0000 */
        public void remove(InnerSubscriber<T> innerSubscriber) {
            InnerSubscriber[] innerSubscriberArr;
            InnerSubscriber[] innerSubscriberArr2;
            do {
                innerSubscriberArr = (InnerSubscriber[]) this.subscribers.get();
                int length = innerSubscriberArr.length;
                if (length == 0) {
                    break;
                }
                int i = -1;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    } else if (innerSubscriberArr[i2].equals(innerSubscriber)) {
                        i = i2;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (i >= 0) {
                    if (length == 1) {
                        innerSubscriberArr2 = EMPTY;
                    } else {
                        InnerSubscriber[] innerSubscriberArr3 = new InnerSubscriber[(length - 1)];
                        System.arraycopy(innerSubscriberArr, 0, innerSubscriberArr3, 0, i);
                        System.arraycopy(innerSubscriberArr, i + 1, innerSubscriberArr3, i, (length - i) - 1);
                        innerSubscriberArr2 = innerSubscriberArr3;
                    }
                } else {
                    return;
                }
            } while (!this.subscribers.compareAndSet(innerSubscriberArr, innerSubscriberArr2));
        }

        /* access modifiers changed from: 0000 */
        public boolean checkTerminated(Object obj, boolean z) {
            int i = 0;
            if (obj != null) {
                if (!NotificationLite.isComplete(obj)) {
                    Throwable error = NotificationLite.getError(obj);
                    this.current.compareAndSet(this, null);
                    InnerSubscriber[] innerSubscriberArr = (InnerSubscriber[]) this.subscribers.getAndSet(TERMINATED);
                    if (innerSubscriberArr.length != 0) {
                        int length = innerSubscriberArr.length;
                        while (i < length) {
                            innerSubscriberArr[i].child.onError(error);
                            i++;
                        }
                    } else {
                        io.reactivex.d.a.a(error);
                    }
                    return true;
                } else if (z) {
                    this.current.compareAndSet(this, null);
                    InnerSubscriber[] innerSubscriberArr2 = (InnerSubscriber[]) this.subscribers.getAndSet(TERMINATED);
                    int length2 = innerSubscriberArr2.length;
                    while (i < length2) {
                        innerSubscriberArr2[i].child.onComplete();
                        i++;
                    }
                    return true;
                }
            }
            return false;
        }

        /* access modifiers changed from: 0000 */
        /* JADX WARNING: Code restructure failed: missing block: B:71:0x0110, code lost:
            if (r6 == false) goto L_0x0112;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void dispatch() {
            /*
                r21 = this;
                r1 = r21
                int r2 = r21.getAndIncrement()
                if (r2 == 0) goto L_0x0009
                return
            L_0x0009:
                r3 = 1
            L_0x000a:
                java.lang.Object r4 = r1.terminalEvent
                io.reactivex.internal.a.g<T> r5 = r1.queue
                if (r5 == 0) goto L_0x0019
                boolean r7 = r5.isEmpty()
                if (r7 == 0) goto L_0x0017
                goto L_0x0019
            L_0x0017:
                r7 = 0
                goto L_0x001a
            L_0x0019:
                r7 = 1
            L_0x001a:
                boolean r4 = r1.checkTerminated(r4, r7)
                if (r4 == 0) goto L_0x0021
                return
            L_0x0021:
                if (r7 != 0) goto L_0x0116
                java.util.concurrent.atomic.AtomicReference<io.reactivex.internal.operators.flowable.FlowablePublish$InnerSubscriber[]> r4 = r1.subscribers
                java.lang.Object r4 = r4.get()
                io.reactivex.internal.operators.flowable.FlowablePublish$InnerSubscriber[] r4 = (io.reactivex.internal.operators.flowable.FlowablePublish.InnerSubscriber[]) r4
                int r8 = r4.length
                r9 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                int r11 = r4.length
                r12 = r9
                r9 = 0
                r10 = 0
            L_0x0035:
                r14 = 0
                if (r9 >= r11) goto L_0x0058
                r6 = r4[r9]
                r16 = r3
                long r2 = r6.get()
                int r6 = (r2 > r14 ? 1 : (r2 == r14 ? 0 : -1))
                if (r6 < 0) goto L_0x004b
                long r2 = java.lang.Math.min(r12, r2)
                r12 = r2
                goto L_0x0053
            L_0x004b:
                r14 = -9223372036854775808
                int r6 = (r2 > r14 ? 1 : (r2 == r14 ? 0 : -1))
                if (r6 != 0) goto L_0x0053
                int r10 = r10 + 1
            L_0x0053:
                int r9 = r9 + 1
                r3 = r16
                goto L_0x0035
            L_0x0058:
                r16 = r3
                r2 = 1
                if (r8 != r10) goto L_0x009d
                java.lang.Object r4 = r1.terminalEvent
                java.lang.Object r5 = r5.poll()     // Catch:{ Throwable -> 0x0065 }
                goto L_0x007b
            L_0x0065:
                r0 = move-exception
                io.reactivex.exceptions.a.b(r0)
                java.util.concurrent.atomic.AtomicReference<org.reactivestreams.Subscription> r4 = r1.s
                java.lang.Object r4 = r4.get()
                org.reactivestreams.Subscription r4 = (org.reactivestreams.Subscription) r4
                r4.cancel()
                java.lang.Object r4 = io.reactivex.internal.util.NotificationLite.error(r0)
                r1.terminalEvent = r4
                r5 = 0
            L_0x007b:
                if (r5 != 0) goto L_0x007f
                r5 = 1
                goto L_0x0080
            L_0x007f:
                r5 = 0
            L_0x0080:
                boolean r4 = r1.checkTerminated(r4, r5)
                if (r4 == 0) goto L_0x0087
                return
            L_0x0087:
                int r4 = r1.sourceMode
                r5 = 1
                if (r4 == r5) goto L_0x009a
                java.util.concurrent.atomic.AtomicReference<org.reactivestreams.Subscription> r4 = r1.s
                java.lang.Object r4 = r4.get()
                org.reactivestreams.Subscription r4 = (org.reactivestreams.Subscription) r4
                r4.request(r2)
                r3 = 1
                goto L_0x0112
            L_0x009a:
                r3 = r5
                goto L_0x0112
            L_0x009d:
                r8 = r7
                r7 = 0
            L_0x009f:
                long r9 = (long) r7
                int r11 = (r9 > r12 ? 1 : (r9 == r12 ? 0 : -1))
                if (r11 >= 0) goto L_0x00f5
                java.lang.Object r8 = r1.terminalEvent
                java.lang.Object r11 = r5.poll()     // Catch:{ Throwable -> 0x00ab }
                goto L_0x00c1
            L_0x00ab:
                r0 = move-exception
                io.reactivex.exceptions.a.b(r0)
                java.util.concurrent.atomic.AtomicReference<org.reactivestreams.Subscription> r8 = r1.s
                java.lang.Object r8 = r8.get()
                org.reactivestreams.Subscription r8 = (org.reactivestreams.Subscription) r8
                r8.cancel()
                java.lang.Object r8 = io.reactivex.internal.util.NotificationLite.error(r0)
                r1.terminalEvent = r8
                r11 = 0
            L_0x00c1:
                if (r11 != 0) goto L_0x00c5
                r6 = 1
                goto L_0x00c6
            L_0x00c5:
                r6 = 0
            L_0x00c6:
                boolean r8 = r1.checkTerminated(r8, r6)
                if (r8 == 0) goto L_0x00cd
                return
            L_0x00cd:
                if (r6 == 0) goto L_0x00d0
                goto L_0x00f6
            L_0x00d0:
                java.lang.Object r8 = io.reactivex.internal.util.NotificationLite.getValue(r11)
                int r9 = r4.length
                r10 = 0
            L_0x00d6:
                if (r10 >= r9) goto L_0x00ef
                r11 = r4[r10]
                long r17 = r11.get()
                int r19 = (r17 > r14 ? 1 : (r17 == r14 ? 0 : -1))
                if (r19 <= 0) goto L_0x00ea
                org.reactivestreams.c<? super T> r14 = r11.child
                r14.onNext(r8)
                r11.produced(r2)
            L_0x00ea:
                int r10 = r10 + 1
                r14 = 0
                goto L_0x00d6
            L_0x00ef:
                int r7 = r7 + 1
                r8 = r6
                r14 = 0
                goto L_0x009f
            L_0x00f5:
                r6 = r8
            L_0x00f6:
                if (r7 <= 0) goto L_0x0109
                int r2 = r1.sourceMode
                r3 = 1
                if (r2 == r3) goto L_0x010a
                java.util.concurrent.atomic.AtomicReference<org.reactivestreams.Subscription> r2 = r1.s
                java.lang.Object r2 = r2.get()
                org.reactivestreams.Subscription r2 = (org.reactivestreams.Subscription) r2
                r2.request(r9)
                goto L_0x010a
            L_0x0109:
                r3 = 1
            L_0x010a:
                r4 = 0
                int r2 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1))
                if (r2 == 0) goto L_0x0119
                if (r6 != 0) goto L_0x0119
            L_0x0112:
                r3 = r16
                goto L_0x000a
            L_0x0116:
                r16 = r3
                r3 = 1
            L_0x0119:
                r2 = r16
                int r2 = -r2
                int r2 = r1.addAndGet(r2)
                if (r2 != 0) goto L_0x0123
                return
            L_0x0123:
                r3 = r2
                goto L_0x000a
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.flowable.FlowablePublish.PublishSubscriber.dispatch():void");
        }
    }

    /* access modifiers changed from: protected */
    public void a(c<? super T> cVar) {
        this.e.subscribe(cVar);
    }

    /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(io.reactivex.functions.Consumer<? super io.reactivex.disposables.Disposable> r5) {
        /*
            r4 = this;
        L_0x0000:
            java.util.concurrent.atomic.AtomicReference<io.reactivex.internal.operators.flowable.FlowablePublish$PublishSubscriber<T>> r0 = r4.c
            java.lang.Object r0 = r0.get()
            io.reactivex.internal.operators.flowable.FlowablePublish$PublishSubscriber r0 = (io.reactivex.internal.operators.flowable.FlowablePublish.PublishSubscriber) r0
            if (r0 == 0) goto L_0x0010
            boolean r1 = r0.isDisposed()
            if (r1 == 0) goto L_0x0023
        L_0x0010:
            io.reactivex.internal.operators.flowable.FlowablePublish$PublishSubscriber r1 = new io.reactivex.internal.operators.flowable.FlowablePublish$PublishSubscriber
            java.util.concurrent.atomic.AtomicReference<io.reactivex.internal.operators.flowable.FlowablePublish$PublishSubscriber<T>> r2 = r4.c
            int r3 = r4.d
            r1.<init>(r2, r3)
            java.util.concurrent.atomic.AtomicReference<io.reactivex.internal.operators.flowable.FlowablePublish$PublishSubscriber<T>> r2 = r4.c
            boolean r0 = r2.compareAndSet(r0, r1)
            if (r0 != 0) goto L_0x0022
            goto L_0x0000
        L_0x0022:
            r0 = r1
        L_0x0023:
            java.util.concurrent.atomic.AtomicBoolean r1 = r0.shouldConnect
            boolean r1 = r1.get()
            r2 = 1
            r3 = 0
            if (r1 != 0) goto L_0x0036
            java.util.concurrent.atomic.AtomicBoolean r1 = r0.shouldConnect
            boolean r1 = r1.compareAndSet(r3, r2)
            if (r1 == 0) goto L_0x0036
            goto L_0x0037
        L_0x0036:
            r2 = r3
        L_0x0037:
            r5.accept(r0)     // Catch:{ Throwable -> 0x0042 }
            if (r2 == 0) goto L_0x0041
            io.reactivex.g<T> r5 = r4.b
            r5.a(r0)
        L_0x0041:
            return
        L_0x0042:
            r5 = move-exception
            io.reactivex.exceptions.a.b(r5)
            java.lang.RuntimeException r5 = io.reactivex.internal.util.ExceptionHelper.a(r5)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.flowable.FlowablePublish.a(io.reactivex.functions.Consumer):void");
    }
}
