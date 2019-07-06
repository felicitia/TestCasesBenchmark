package io.reactivex.internal.operators.flowable;

import io.reactivex.disposables.Disposable;
import io.reactivex.g;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.j;
import io.reactivex.u;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;
import org.reactivestreams.c;

public final class FlowableReplay<T> extends io.reactivex.b.a<T> implements Disposable {
    static final Callable f = new a();
    final g<T> b;
    final AtomicReference<ReplaySubscriber<T>> c;
    final Callable<? extends b<T>> d;
    final org.reactivestreams.b<T> e;

    static class BoundedReplayBuffer<T> extends AtomicReference<Node> implements b<T> {
        private static final long serialVersionUID = 2346567790059478686L;
        long index;
        int size;
        Node tail;

        /* access modifiers changed from: 0000 */
        public Object enterTransform(Object obj) {
            return obj;
        }

        /* access modifiers changed from: 0000 */
        public Object leaveTransform(Object obj) {
            return obj;
        }

        /* access modifiers changed from: 0000 */
        public void truncate() {
        }

        /* access modifiers changed from: 0000 */
        public void truncateFinal() {
        }

        BoundedReplayBuffer() {
            Node node = new Node(null, 0);
            this.tail = node;
            set(node);
        }

        /* access modifiers changed from: 0000 */
        public final void addLast(Node node) {
            this.tail.set(node);
            this.tail = node;
            this.size++;
        }

        /* access modifiers changed from: 0000 */
        public final void removeFirst() {
            Node node = (Node) ((Node) get()).get();
            if (node == null) {
                throw new IllegalStateException("Empty list!");
            }
            this.size--;
            setFirst(node);
        }

        /* access modifiers changed from: 0000 */
        public final void removeSome(int i) {
            Node node = (Node) get();
            while (i > 0) {
                node = (Node) node.get();
                i--;
                this.size--;
            }
            setFirst(node);
        }

        /* access modifiers changed from: 0000 */
        public final void setFirst(Node node) {
            set(node);
        }

        public final void next(T t) {
            Object enterTransform = enterTransform(NotificationLite.next(t));
            long j = this.index + 1;
            this.index = j;
            addLast(new Node(enterTransform, j));
            truncate();
        }

        public final void error(Throwable th) {
            Object enterTransform = enterTransform(NotificationLite.error(th));
            long j = this.index + 1;
            this.index = j;
            addLast(new Node(enterTransform, j));
            truncateFinal();
        }

        public final void complete() {
            Object enterTransform = enterTransform(NotificationLite.complete());
            long j = this.index + 1;
            this.index = j;
            addLast(new Node(enterTransform, j));
            truncateFinal();
        }

        /* JADX INFO: finally extract failed */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0013, code lost:
            if (r19.isDisposed() == false) goto L_0x0016;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0015, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0016, code lost:
            r4 = r19.get();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0022, code lost:
            if (r4 != Long.MAX_VALUE) goto L_0x0026;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x0024, code lost:
            r2 = true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x0026, code lost:
            r2 = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0027, code lost:
            r7 = (io.reactivex.internal.operators.flowable.FlowableReplay.Node) r19.index();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x002f, code lost:
            if (r7 != null) goto L_0x003e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0031, code lost:
            r7 = getHead();
            r1.index = r7;
            io.reactivex.internal.util.b.a(r1.totalRequested, r7.index);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x003e, code lost:
            r10 = 0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x0041, code lost:
            if (r4 == 0) goto L_0x008d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x0043, code lost:
            r12 = (io.reactivex.internal.operators.flowable.FlowableReplay.Node) r7.get();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x0049, code lost:
            if (r12 == null) goto L_0x008d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x004b, code lost:
            r7 = leaveTransform(r12.value);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x005a, code lost:
            if (io.reactivex.internal.util.NotificationLite.accept(r7, r1.child) == false) goto L_0x005f;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:0x005c, code lost:
            r1.index = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x005e, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:0x005f, code lost:
            r16 = r10 + 1;
            r10 = r4 - 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:0x0069, code lost:
            if (r19.isDisposed() == false) goto L_0x006c;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:33:0x006b, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:0x006c, code lost:
            r4 = r10;
            r7 = r12;
            r10 = r16;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:35:0x0071, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:36:0x0072, code lost:
            r2 = r0;
            io.reactivex.exceptions.a.b(r2);
            r1.index = null;
            r19.dispose();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:40:0x0087, code lost:
            r1.child.onError(r2);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:41:0x008c, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:42:0x008d, code lost:
            r13 = r18;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:43:0x0091, code lost:
            if (r10 == 0) goto L_0x009a;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:44:0x0093, code lost:
            r1.index = r7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:45:0x0095, code lost:
            if (r2 != false) goto L_0x009a;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:46:0x0097, code lost:
            r1.produced(r10);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:47:0x009a, code lost:
            monitor-enter(r19);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:50:0x009d, code lost:
            if (r1.missed != false) goto L_0x00a3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:51:0x009f, code lost:
            r1.emitting = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:52:0x00a1, code lost:
            monitor-exit(r19);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:53:0x00a2, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:54:0x00a3, code lost:
            r1.missed = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:55:0x00a5, code lost:
            monitor-exit(r19);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:57:0x00a8, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:60:0x00ab, code lost:
            throw r0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:67:0x00b2, code lost:
            r0 = th;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void replay(io.reactivex.internal.operators.flowable.FlowableReplay.InnerSubscription<T> r19) {
            /*
                r18 = this;
                r1 = r19
                monitor-enter(r19)
                boolean r2 = r1.emitting     // Catch:{ all -> 0x00ac }
                r3 = 1
                if (r2 == 0) goto L_0x000c
                r1.missed = r3     // Catch:{ all -> 0x00ac }
                monitor-exit(r19)     // Catch:{ all -> 0x00ac }
                return
            L_0x000c:
                r1.emitting = r3     // Catch:{ all -> 0x00ac }
                monitor-exit(r19)     // Catch:{ all -> 0x00ac }
            L_0x000f:
                boolean r2 = r19.isDisposed()
                if (r2 == 0) goto L_0x0016
                return
            L_0x0016:
                long r4 = r19.get()
                r6 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
                r6 = 0
                if (r2 != 0) goto L_0x0026
                r2 = r3
                goto L_0x0027
            L_0x0026:
                r2 = r6
            L_0x0027:
                java.lang.Object r7 = r19.index()
                io.reactivex.internal.operators.flowable.FlowableReplay$Node r7 = (io.reactivex.internal.operators.flowable.FlowableReplay.Node) r7
                r8 = 0
                if (r7 != 0) goto L_0x003e
                io.reactivex.internal.operators.flowable.FlowableReplay$Node r7 = r18.getHead()
                r1.index = r7
                java.util.concurrent.atomic.AtomicLong r10 = r1.totalRequested
                long r11 = r7.index
                io.reactivex.internal.util.b.a(r10, r11)
            L_0x003e:
                r10 = r8
            L_0x003f:
                int r12 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
                if (r12 == 0) goto L_0x008d
                java.lang.Object r12 = r7.get()
                io.reactivex.internal.operators.flowable.FlowableReplay$Node r12 = (io.reactivex.internal.operators.flowable.FlowableReplay.Node) r12
                if (r12 == 0) goto L_0x008d
                java.lang.Object r7 = r12.value
                r13 = r18
                java.lang.Object r7 = r13.leaveTransform(r7)
                r14 = 0
                org.reactivestreams.c<? super T> r15 = r1.child     // Catch:{ Throwable -> 0x0071 }
                boolean r15 = io.reactivex.internal.util.NotificationLite.accept(r7, r15)     // Catch:{ Throwable -> 0x0071 }
                if (r15 == 0) goto L_0x005f
                r1.index = r14     // Catch:{ Throwable -> 0x0071 }
                return
            L_0x005f:
                r14 = 1
                long r16 = r10 + r14
                long r10 = r4 - r14
                boolean r4 = r19.isDisposed()
                if (r4 == 0) goto L_0x006c
                return
            L_0x006c:
                r4 = r10
                r7 = r12
                r10 = r16
                goto L_0x003f
            L_0x0071:
                r0 = move-exception
                r2 = r0
                io.reactivex.exceptions.a.b(r2)
                r1.index = r14
                r19.dispose()
                boolean r3 = io.reactivex.internal.util.NotificationLite.isError(r7)
                if (r3 != 0) goto L_0x008c
                boolean r3 = io.reactivex.internal.util.NotificationLite.isComplete(r7)
                if (r3 != 0) goto L_0x008c
                org.reactivestreams.c<? super T> r1 = r1.child
                r1.onError(r2)
            L_0x008c:
                return
            L_0x008d:
                r13 = r18
                int r4 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1))
                if (r4 == 0) goto L_0x009a
                r1.index = r7
                if (r2 != 0) goto L_0x009a
                r1.produced(r10)
            L_0x009a:
                monitor-enter(r19)
                boolean r2 = r1.missed     // Catch:{ all -> 0x00a8 }
                if (r2 != 0) goto L_0x00a3
                r1.emitting = r6     // Catch:{ all -> 0x00a8 }
                monitor-exit(r19)     // Catch:{ all -> 0x00a8 }
                return
            L_0x00a3:
                r1.missed = r6     // Catch:{ all -> 0x00a8 }
                monitor-exit(r19)     // Catch:{ all -> 0x00a8 }
                goto L_0x000f
            L_0x00a8:
                r0 = move-exception
                r2 = r0
                monitor-exit(r19)     // Catch:{ all -> 0x00a8 }
                throw r2
            L_0x00ac:
                r0 = move-exception
                r13 = r18
            L_0x00af:
                r2 = r0
                monitor-exit(r19)     // Catch:{ all -> 0x00b2 }
                throw r2
            L_0x00b2:
                r0 = move-exception
                goto L_0x00af
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.flowable.FlowableReplay.BoundedReplayBuffer.replay(io.reactivex.internal.operators.flowable.FlowableReplay$InnerSubscription):void");
        }

        /* access modifiers changed from: 0000 */
        public final void collect(Collection<? super T> collection) {
            Node head = getHead();
            while (true) {
                head = (Node) head.get();
                if (head != null) {
                    Object leaveTransform = leaveTransform(head.value);
                    if (!NotificationLite.isComplete(leaveTransform) && !NotificationLite.isError(leaveTransform)) {
                        collection.add(NotificationLite.getValue(leaveTransform));
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public boolean hasError() {
            return this.tail.value != null && NotificationLite.isError(leaveTransform(this.tail.value));
        }

        /* access modifiers changed from: 0000 */
        public boolean hasCompleted() {
            return this.tail.value != null && NotificationLite.isComplete(leaveTransform(this.tail.value));
        }

        /* access modifiers changed from: 0000 */
        public Node getHead() {
            return (Node) get();
        }
    }

    static final class InnerSubscription<T> extends AtomicLong implements Disposable, Subscription {
        static final long CANCELLED = Long.MIN_VALUE;
        private static final long serialVersionUID = -4453897557930727610L;
        final c<? super T> child;
        boolean emitting;
        Object index;
        boolean missed;
        final ReplaySubscriber<T> parent;
        final AtomicLong totalRequested = new AtomicLong();

        InnerSubscription(ReplaySubscriber<T> replaySubscriber, c<? super T> cVar) {
            this.parent = replaySubscriber;
            this.child = cVar;
        }

        public void request(long j) {
            long j2;
            if (SubscriptionHelper.validate(j)) {
                do {
                    j2 = get();
                    if (j2 != CANCELLED) {
                        if (j2 >= 0 && j == 0) {
                            return;
                        }
                    } else {
                        return;
                    }
                } while (!compareAndSet(j2, io.reactivex.internal.util.b.a(j2, j)));
                io.reactivex.internal.util.b.a(this.totalRequested, j);
                this.parent.manageRequests();
                this.parent.buffer.replay(this);
            }
        }

        public long produced(long j) {
            return io.reactivex.internal.util.b.d(this, j);
        }

        public boolean isDisposed() {
            return get() == CANCELLED;
        }

        public void cancel() {
            dispose();
        }

        public void dispose() {
            if (getAndSet(CANCELLED) != CANCELLED) {
                this.parent.remove(this);
                this.parent.manageRequests();
            }
        }

        /* access modifiers changed from: 0000 */
        public <U> U index() {
            return this.index;
        }
    }

    static final class Node extends AtomicReference<Node> {
        private static final long serialVersionUID = 245354315435971818L;
        final long index;
        final Object value;

        Node(Object obj, long j) {
            this.value = obj;
            this.index = j;
        }
    }

    static final class ReplaySubscriber<T> extends AtomicReference<Subscription> implements Disposable, j<T> {
        static final InnerSubscription[] EMPTY = new InnerSubscription[0];
        static final InnerSubscription[] TERMINATED = new InnerSubscription[0];
        private static final long serialVersionUID = 7224554242710036740L;
        final b<T> buffer;
        boolean done;
        final AtomicInteger management = new AtomicInteger();
        long maxChildRequested;
        long maxUpstreamRequested;
        final AtomicBoolean shouldConnect = new AtomicBoolean();
        final AtomicReference<InnerSubscription<T>[]> subscribers = new AtomicReference<>(EMPTY);

        ReplaySubscriber(b<T> bVar) {
            this.buffer = bVar;
        }

        public boolean isDisposed() {
            return this.subscribers.get() == TERMINATED;
        }

        public void dispose() {
            this.subscribers.set(TERMINATED);
            SubscriptionHelper.cancel(this);
        }

        /* access modifiers changed from: 0000 */
        public boolean add(InnerSubscription<T> innerSubscription) {
            InnerSubscription[] innerSubscriptionArr;
            InnerSubscription[] innerSubscriptionArr2;
            if (innerSubscription == null) {
                throw new NullPointerException();
            }
            do {
                innerSubscriptionArr = (InnerSubscription[]) this.subscribers.get();
                if (innerSubscriptionArr == TERMINATED) {
                    return false;
                }
                int length = innerSubscriptionArr.length;
                innerSubscriptionArr2 = new InnerSubscription[(length + 1)];
                System.arraycopy(innerSubscriptionArr, 0, innerSubscriptionArr2, 0, length);
                innerSubscriptionArr2[length] = innerSubscription;
            } while (!this.subscribers.compareAndSet(innerSubscriptionArr, innerSubscriptionArr2));
            return true;
        }

        /* access modifiers changed from: 0000 */
        public void remove(InnerSubscription<T> innerSubscription) {
            InnerSubscription[] innerSubscriptionArr;
            InnerSubscription[] innerSubscriptionArr2;
            do {
                innerSubscriptionArr = (InnerSubscription[]) this.subscribers.get();
                int length = innerSubscriptionArr.length;
                if (length != 0) {
                    int i = -1;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length) {
                            break;
                        } else if (innerSubscriptionArr[i2].equals(innerSubscription)) {
                            i = i2;
                            break;
                        } else {
                            i2++;
                        }
                    }
                    if (i >= 0) {
                        if (length == 1) {
                            innerSubscriptionArr2 = EMPTY;
                        } else {
                            InnerSubscription[] innerSubscriptionArr3 = new InnerSubscription[(length - 1)];
                            System.arraycopy(innerSubscriptionArr, 0, innerSubscriptionArr3, 0, i);
                            System.arraycopy(innerSubscriptionArr, i + 1, innerSubscriptionArr3, i, (length - i) - 1);
                            innerSubscriptionArr2 = innerSubscriptionArr3;
                        }
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            } while (!this.subscribers.compareAndSet(innerSubscriptionArr, innerSubscriptionArr2));
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.setOnce(this, subscription)) {
                manageRequests();
                for (InnerSubscription replay : (InnerSubscription[]) this.subscribers.get()) {
                    this.buffer.replay(replay);
                }
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                this.buffer.next(t);
                for (InnerSubscription replay : (InnerSubscription[]) this.subscribers.get()) {
                    this.buffer.replay(replay);
                }
            }
        }

        public void onError(Throwable th) {
            if (!this.done) {
                this.done = true;
                this.buffer.error(th);
                for (InnerSubscription replay : (InnerSubscription[]) this.subscribers.getAndSet(TERMINATED)) {
                    this.buffer.replay(replay);
                }
                return;
            }
            io.reactivex.d.a.a(th);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.buffer.complete();
                for (InnerSubscription replay : (InnerSubscription[]) this.subscribers.getAndSet(TERMINATED)) {
                    this.buffer.replay(replay);
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void manageRequests() {
            if (this.management.getAndIncrement() == 0) {
                int i = 1;
                while (!isDisposed()) {
                    InnerSubscription[] innerSubscriptionArr = (InnerSubscription[]) this.subscribers.get();
                    long j = this.maxChildRequested;
                    long j2 = j;
                    for (InnerSubscription innerSubscription : innerSubscriptionArr) {
                        j2 = Math.max(j2, innerSubscription.totalRequested.get());
                    }
                    long j3 = this.maxUpstreamRequested;
                    Subscription subscription = (Subscription) get();
                    long j4 = j2 - j;
                    if (j4 != 0) {
                        this.maxChildRequested = j2;
                        if (subscription == null) {
                            long j5 = j3 + j4;
                            if (j5 < 0) {
                                j5 = Long.MAX_VALUE;
                            }
                            this.maxUpstreamRequested = j5;
                        } else if (j3 != 0) {
                            this.maxUpstreamRequested = 0;
                            subscription.request(j3 + j4);
                        } else {
                            subscription.request(j4);
                        }
                    } else if (!(j3 == 0 || subscription == null)) {
                        this.maxUpstreamRequested = 0;
                        subscription.request(j3);
                    }
                    i = this.management.addAndGet(-i);
                    if (i == 0) {
                        return;
                    }
                }
            }
        }
    }

    static final class SizeAndTimeBoundReplayBuffer<T> extends BoundedReplayBuffer<T> {
        private static final long serialVersionUID = 3457957419649567404L;
        final int limit;
        final long maxAge;
        final u scheduler;
        final TimeUnit unit;

        SizeAndTimeBoundReplayBuffer(int i, long j, TimeUnit timeUnit, u uVar) {
            this.scheduler = uVar;
            this.limit = i;
            this.maxAge = j;
            this.unit = timeUnit;
        }

        /* access modifiers changed from: 0000 */
        public Object enterTransform(Object obj) {
            return new io.reactivex.e.b(obj, this.scheduler.a(this.unit), this.unit);
        }

        /* access modifiers changed from: 0000 */
        public Object leaveTransform(Object obj) {
            return ((io.reactivex.e.b) obj).a();
        }

        /* access modifiers changed from: 0000 */
        public void truncate() {
            Node node;
            long a = this.scheduler.a(this.unit) - this.maxAge;
            Node node2 = (Node) get();
            Node node3 = (Node) node2.get();
            int i = 0;
            while (true) {
                Node node4 = node3;
                node = node2;
                node2 = node4;
                if (node2 != null) {
                    if (this.size <= this.limit) {
                        if (((io.reactivex.e.b) node2.value).b() > a) {
                            break;
                        }
                        i++;
                        this.size--;
                        node3 = (Node) node2.get();
                    } else {
                        i++;
                        this.size--;
                        node3 = (Node) node2.get();
                    }
                } else {
                    break;
                }
            }
            if (i != 0) {
                setFirst(node);
            }
        }

        /* access modifiers changed from: 0000 */
        /* JADX WARNING: Removed duplicated region for block: B:14:? A[RETURN, SYNTHETIC] */
        /* JADX WARNING: Removed duplicated region for block: B:9:0x003f  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void truncateFinal() {
            /*
                r10 = this;
                io.reactivex.u r0 = r10.scheduler
                java.util.concurrent.TimeUnit r1 = r10.unit
                long r0 = r0.a(r1)
                long r2 = r10.maxAge
                long r4 = r0 - r2
                java.lang.Object r0 = r10.get()
                io.reactivex.internal.operators.flowable.FlowableReplay$Node r0 = (io.reactivex.internal.operators.flowable.FlowableReplay.Node) r0
                java.lang.Object r1 = r0.get()
                io.reactivex.internal.operators.flowable.FlowableReplay$Node r1 = (io.reactivex.internal.operators.flowable.FlowableReplay.Node) r1
                r2 = 0
            L_0x0019:
                r9 = r1
                r1 = r0
                r0 = r9
                if (r0 == 0) goto L_0x003d
                int r3 = r10.size
                r6 = 1
                if (r3 <= r6) goto L_0x003d
                java.lang.Object r3 = r0.value
                io.reactivex.e.b r3 = (io.reactivex.e.b) r3
                long r7 = r3.b()
                int r3 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
                if (r3 > 0) goto L_0x003d
                int r2 = r2 + 1
                int r1 = r10.size
                int r1 = r1 - r6
                r10.size = r1
                java.lang.Object r1 = r0.get()
                io.reactivex.internal.operators.flowable.FlowableReplay$Node r1 = (io.reactivex.internal.operators.flowable.FlowableReplay.Node) r1
                goto L_0x0019
            L_0x003d:
                if (r2 == 0) goto L_0x0042
                r10.setFirst(r1)
            L_0x0042:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.flowable.FlowableReplay.SizeAndTimeBoundReplayBuffer.truncateFinal():void");
        }

        /* access modifiers changed from: 0000 */
        public Node getHead() {
            Node node;
            long a = this.scheduler.a(this.unit) - this.maxAge;
            Node node2 = (Node) get();
            Object obj = node2.get();
            while (true) {
                Node node3 = (Node) obj;
                node = node2;
                node2 = node3;
                if (node2 != null) {
                    io.reactivex.e.b bVar = (io.reactivex.e.b) node2.value;
                    if (NotificationLite.isComplete(bVar.a()) || NotificationLite.isError(bVar.a()) || bVar.b() > a) {
                        break;
                    }
                    obj = node2.get();
                } else {
                    break;
                }
            }
            return node;
        }
    }

    static final class SizeBoundReplayBuffer<T> extends BoundedReplayBuffer<T> {
        private static final long serialVersionUID = -5898283885385201806L;
        final int limit;

        SizeBoundReplayBuffer(int i) {
            this.limit = i;
        }

        /* access modifiers changed from: 0000 */
        public void truncate() {
            if (this.size > this.limit) {
                removeFirst();
            }
        }
    }

    static final class UnboundedReplayBuffer<T> extends ArrayList<Object> implements b<T> {
        private static final long serialVersionUID = 7063189396499112664L;
        volatile int size;

        UnboundedReplayBuffer(int i) {
            super(i);
        }

        public void next(T t) {
            add(NotificationLite.next(t));
            this.size++;
        }

        public void error(Throwable th) {
            add(NotificationLite.error(th));
            this.size++;
        }

        public void complete() {
            add(NotificationLite.complete());
            this.size++;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x0011, code lost:
            r3 = r2.child;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0017, code lost:
            if (r20.isDisposed() == false) goto L_0x001a;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0019, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x001a, code lost:
            r4 = r1.size;
            r5 = (java.lang.Integer) r20.index();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x0023, code lost:
            if (r5 == null) goto L_0x002a;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x0025, code lost:
            r5 = r5.intValue();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x002a, code lost:
            r5 = 0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x002b, code lost:
            r7 = r20.get();
            r9 = 0;
            r11 = r7;
            r13 = r9;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x0035, code lost:
            if (r11 == r9) goto L_0x006e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0037, code lost:
            if (r5 >= r4) goto L_0x006e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x0039, code lost:
            r15 = get(r5);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x0041, code lost:
            if (io.reactivex.internal.util.NotificationLite.accept(r15, r3) == false) goto L_0x0044;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x0043, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x0048, code lost:
            if (r20.isDisposed() == false) goto L_0x004b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:0x004a, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x004b, code lost:
            r5 = r5 + 1;
            r13 = r13 + 1;
            r11 = r11 - 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:0x0057, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:0x0058, code lost:
            io.reactivex.exceptions.a.b(r0);
            r20.dispose();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:36:0x006a, code lost:
            r3.onError(r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:37:0x006d, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:39:0x0070, code lost:
            if (r13 == r9) goto L_0x0084;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:40:0x0072, code lost:
            r2.index = java.lang.Integer.valueOf(r5);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:41:0x007f, code lost:
            if (r7 == Long.MAX_VALUE) goto L_0x0084;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:42:0x0081, code lost:
            r2.produced(r13);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:43:0x0084, code lost:
            monitor-enter(r20);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:46:0x0087, code lost:
            if (r2.missed != false) goto L_0x008d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:47:0x0089, code lost:
            r2.emitting = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:48:0x008b, code lost:
            monitor-exit(r20);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:49:0x008c, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:50:0x008d, code lost:
            r2.missed = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:51:0x008f, code lost:
            monitor-exit(r20);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:53:0x0091, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:56:0x0094, code lost:
            throw r0;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void replay(io.reactivex.internal.operators.flowable.FlowableReplay.InnerSubscription<T> r20) {
            /*
                r19 = this;
                r1 = r19
                r2 = r20
                monitor-enter(r20)
                boolean r3 = r2.emitting     // Catch:{ all -> 0x0095 }
                r4 = 1
                if (r3 == 0) goto L_0x000e
                r2.missed = r4     // Catch:{ all -> 0x0095 }
                monitor-exit(r20)     // Catch:{ all -> 0x0095 }
                return
            L_0x000e:
                r2.emitting = r4     // Catch:{ all -> 0x0095 }
                monitor-exit(r20)     // Catch:{ all -> 0x0095 }
                org.reactivestreams.c<? super T> r3 = r2.child
            L_0x0013:
                boolean r4 = r20.isDisposed()
                if (r4 == 0) goto L_0x001a
                return
            L_0x001a:
                int r4 = r1.size
                java.lang.Object r5 = r20.index()
                java.lang.Integer r5 = (java.lang.Integer) r5
                r6 = 0
                if (r5 == 0) goto L_0x002a
                int r5 = r5.intValue()
                goto L_0x002b
            L_0x002a:
                r5 = r6
            L_0x002b:
                long r7 = r20.get()
                r9 = 0
                r11 = r7
                r13 = r9
            L_0x0033:
                int r15 = (r11 > r9 ? 1 : (r11 == r9 ? 0 : -1))
                if (r15 == 0) goto L_0x006e
                if (r5 >= r4) goto L_0x006e
                java.lang.Object r15 = r1.get(r5)
                boolean r16 = io.reactivex.internal.util.NotificationLite.accept(r15, r3)     // Catch:{ Throwable -> 0x0057 }
                if (r16 == 0) goto L_0x0044
                return
            L_0x0044:
                boolean r15 = r20.isDisposed()
                if (r15 == 0) goto L_0x004b
                return
            L_0x004b:
                int r5 = r5 + 1
                r15 = 1
                long r17 = r11 - r15
                long r11 = r13 + r15
                r13 = r11
                r11 = r17
                goto L_0x0033
            L_0x0057:
                r0 = move-exception
                io.reactivex.exceptions.a.b(r0)
                r20.dispose()
                boolean r2 = io.reactivex.internal.util.NotificationLite.isError(r15)
                if (r2 != 0) goto L_0x006d
                boolean r2 = io.reactivex.internal.util.NotificationLite.isComplete(r15)
                if (r2 != 0) goto L_0x006d
                r3.onError(r0)
            L_0x006d:
                return
            L_0x006e:
                int r4 = (r13 > r9 ? 1 : (r13 == r9 ? 0 : -1))
                if (r4 == 0) goto L_0x0084
                java.lang.Integer r4 = java.lang.Integer.valueOf(r5)
                r2.index = r4
                r4 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                int r9 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
                if (r9 == 0) goto L_0x0084
                r2.produced(r13)
            L_0x0084:
                monitor-enter(r20)
                boolean r4 = r2.missed     // Catch:{ all -> 0x0091 }
                if (r4 != 0) goto L_0x008d
                r2.emitting = r6     // Catch:{ all -> 0x0091 }
                monitor-exit(r20)     // Catch:{ all -> 0x0091 }
                return
            L_0x008d:
                r2.missed = r6     // Catch:{ all -> 0x0091 }
                monitor-exit(r20)     // Catch:{ all -> 0x0091 }
                goto L_0x0013
            L_0x0091:
                r0 = move-exception
                r3 = r0
                monitor-exit(r20)     // Catch:{ all -> 0x0091 }
                throw r3
            L_0x0095:
                r0 = move-exception
                r3 = r0
                monitor-exit(r20)     // Catch:{ all -> 0x0095 }
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.flowable.FlowableReplay.UnboundedReplayBuffer.replay(io.reactivex.internal.operators.flowable.FlowableReplay$InnerSubscription):void");
        }
    }

    static final class a implements Callable<Object> {
        a() {
        }

        public Object call() {
            return new UnboundedReplayBuffer(16);
        }
    }

    interface b<T> {
        void complete();

        void error(Throwable th);

        void next(T t);

        void replay(InnerSubscription<T> innerSubscription);
    }

    /* access modifiers changed from: protected */
    public void a(c<? super T> cVar) {
        this.e.subscribe(cVar);
    }

    public void dispose() {
        this.c.lazySet(null);
    }

    public boolean isDisposed() {
        Disposable disposable = (Disposable) this.c.get();
        return disposable == null || disposable.isDisposed();
    }

    /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(io.reactivex.functions.Consumer<? super io.reactivex.disposables.Disposable> r5) {
        /*
            r4 = this;
        L_0x0000:
            java.util.concurrent.atomic.AtomicReference<io.reactivex.internal.operators.flowable.FlowableReplay$ReplaySubscriber<T>> r0 = r4.c
            java.lang.Object r0 = r0.get()
            io.reactivex.internal.operators.flowable.FlowableReplay$ReplaySubscriber r0 = (io.reactivex.internal.operators.flowable.FlowableReplay.ReplaySubscriber) r0
            if (r0 == 0) goto L_0x0010
            boolean r1 = r0.isDisposed()
            if (r1 == 0) goto L_0x0027
        L_0x0010:
            java.util.concurrent.Callable<? extends io.reactivex.internal.operators.flowable.FlowableReplay$b<T>> r1 = r4.d     // Catch:{ Throwable -> 0x0057 }
            java.lang.Object r1 = r1.call()     // Catch:{ Throwable -> 0x0057 }
            io.reactivex.internal.operators.flowable.FlowableReplay$b r1 = (io.reactivex.internal.operators.flowable.FlowableReplay.b) r1     // Catch:{ Throwable -> 0x0057 }
            io.reactivex.internal.operators.flowable.FlowableReplay$ReplaySubscriber r2 = new io.reactivex.internal.operators.flowable.FlowableReplay$ReplaySubscriber
            r2.<init>(r1)
            java.util.concurrent.atomic.AtomicReference<io.reactivex.internal.operators.flowable.FlowableReplay$ReplaySubscriber<T>> r1 = r4.c
            boolean r0 = r1.compareAndSet(r0, r2)
            if (r0 != 0) goto L_0x0026
            goto L_0x0000
        L_0x0026:
            r0 = r2
        L_0x0027:
            java.util.concurrent.atomic.AtomicBoolean r1 = r0.shouldConnect
            boolean r1 = r1.get()
            r2 = 1
            r3 = 0
            if (r1 != 0) goto L_0x003b
            java.util.concurrent.atomic.AtomicBoolean r1 = r0.shouldConnect
            boolean r1 = r1.compareAndSet(r3, r2)
            if (r1 == 0) goto L_0x003b
            r1 = r2
            goto L_0x003c
        L_0x003b:
            r1 = r3
        L_0x003c:
            r5.accept(r0)     // Catch:{ Throwable -> 0x0047 }
            if (r1 == 0) goto L_0x0046
            io.reactivex.g<T> r5 = r4.b
            r5.a(r0)
        L_0x0046:
            return
        L_0x0047:
            r5 = move-exception
            if (r1 == 0) goto L_0x004f
            java.util.concurrent.atomic.AtomicBoolean r0 = r0.shouldConnect
            r0.compareAndSet(r2, r3)
        L_0x004f:
            io.reactivex.exceptions.a.b(r5)
            java.lang.RuntimeException r5 = io.reactivex.internal.util.ExceptionHelper.a(r5)
            throw r5
        L_0x0057:
            r5 = move-exception
            io.reactivex.exceptions.a.b(r5)
            java.lang.RuntimeException r5 = io.reactivex.internal.util.ExceptionHelper.a(r5)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.flowable.FlowableReplay.a(io.reactivex.functions.Consumer):void");
    }
}
