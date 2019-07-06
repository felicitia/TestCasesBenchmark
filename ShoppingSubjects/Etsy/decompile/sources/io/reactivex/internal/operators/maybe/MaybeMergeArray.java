package io.reactivex.internal.operators.maybe;

import io.reactivex.disposables.Disposable;
import io.reactivex.g;
import io.reactivex.internal.subscriptions.BasicIntQueueSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.internal.util.b;
import io.reactivex.m;
import io.reactivex.o;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReferenceArray;
import org.reactivestreams.c;

public final class MaybeMergeArray<T> extends g<T> {
    final o<? extends T>[] b;

    static final class ClqSimpleQueue<T> extends ConcurrentLinkedQueue<T> implements a<T> {
        private static final long serialVersionUID = -4025173261791142821L;
        int consumerIndex;
        final AtomicInteger producerIndex = new AtomicInteger();

        ClqSimpleQueue() {
        }

        public boolean offer(T t, T t2) {
            throw new UnsupportedOperationException();
        }

        public boolean offer(T t) {
            this.producerIndex.getAndIncrement();
            return super.offer(t);
        }

        public T poll() {
            T poll = super.poll();
            if (poll != null) {
                this.consumerIndex++;
            }
            return poll;
        }

        public int consumerIndex() {
            return this.consumerIndex;
        }

        public int producerIndex() {
            return this.producerIndex.get();
        }

        public void drop() {
            poll();
        }
    }

    static final class MergeMaybeObserver<T> extends BasicIntQueueSubscription<T> implements m<T> {
        private static final long serialVersionUID = -660395290758764731L;
        final c<? super T> actual;
        volatile boolean cancelled;
        long consumed;
        final AtomicThrowable error = new AtomicThrowable();
        boolean outputFused;
        final a<Object> queue;
        final AtomicLong requested = new AtomicLong();
        final io.reactivex.disposables.a set = new io.reactivex.disposables.a();
        final int sourceCount;

        MergeMaybeObserver(c<? super T> cVar, int i, a<Object> aVar) {
            this.actual = cVar;
            this.sourceCount = i;
            this.queue = aVar;
        }

        public int requestFusion(int i) {
            if ((i & 2) == 0) {
                return 0;
            }
            this.outputFused = true;
            return 2;
        }

        public T poll() throws Exception {
            T poll;
            do {
                poll = this.queue.poll();
            } while (poll == NotificationLite.COMPLETE);
            return poll;
        }

        public boolean isEmpty() {
            return this.queue.isEmpty();
        }

        public void clear() {
            this.queue.clear();
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
                this.set.dispose();
                if (getAndIncrement() == 0) {
                    this.queue.clear();
                }
            }
        }

        public void onSubscribe(Disposable disposable) {
            this.set.a(disposable);
        }

        public void onSuccess(T t) {
            this.queue.offer(t);
            drain();
        }

        public void onError(Throwable th) {
            if (this.error.addThrowable(th)) {
                this.set.dispose();
                this.queue.offer(NotificationLite.COMPLETE);
                drain();
                return;
            }
            io.reactivex.d.a.a(th);
        }

        public void onComplete() {
            this.queue.offer(NotificationLite.COMPLETE);
            drain();
        }

        /* access modifiers changed from: 0000 */
        public boolean isCancelled() {
            return this.cancelled;
        }

        /* access modifiers changed from: 0000 */
        public void drainNormal() {
            c<? super T> cVar = this.actual;
            a<Object> aVar = this.queue;
            long j = this.consumed;
            int i = 1;
            do {
                long j2 = this.requested.get();
                while (j != j2) {
                    if (this.cancelled) {
                        aVar.clear();
                        return;
                    } else if (((Throwable) this.error.get()) != null) {
                        aVar.clear();
                        cVar.onError(this.error.terminate());
                        return;
                    } else if (aVar.consumerIndex() == this.sourceCount) {
                        cVar.onComplete();
                        return;
                    } else {
                        Object poll = aVar.poll();
                        if (poll == null) {
                            break;
                        } else if (poll != NotificationLite.COMPLETE) {
                            cVar.onNext(poll);
                            j++;
                        }
                    }
                }
                if (j == j2) {
                    if (((Throwable) this.error.get()) != null) {
                        aVar.clear();
                        cVar.onError(this.error.terminate());
                        return;
                    }
                    while (aVar.peek() == NotificationLite.COMPLETE) {
                        aVar.drop();
                    }
                    if (aVar.consumerIndex() == this.sourceCount) {
                        cVar.onComplete();
                        return;
                    }
                }
                this.consumed = j;
                i = addAndGet(-i);
            } while (i != 0);
        }

        /* access modifiers changed from: 0000 */
        public void drainFused() {
            c<? super T> cVar = this.actual;
            a<Object> aVar = this.queue;
            int i = 1;
            while (!this.cancelled) {
                Throwable th = (Throwable) this.error.get();
                if (th != null) {
                    aVar.clear();
                    cVar.onError(th);
                    return;
                }
                boolean z = aVar.producerIndex() == this.sourceCount;
                if (!aVar.isEmpty()) {
                    cVar.onNext(null);
                }
                if (z) {
                    cVar.onComplete();
                    return;
                }
                i = addAndGet(-i);
                if (i == 0) {
                    return;
                }
            }
            aVar.clear();
        }

        /* access modifiers changed from: 0000 */
        public void drain() {
            if (getAndIncrement() == 0) {
                if (this.outputFused) {
                    drainFused();
                } else {
                    drainNormal();
                }
            }
        }
    }

    static final class MpscFillOnceSimpleQueue<T> extends AtomicReferenceArray<T> implements a<T> {
        private static final long serialVersionUID = -7969063454040569579L;
        int consumerIndex;
        final AtomicInteger producerIndex = new AtomicInteger();

        MpscFillOnceSimpleQueue(int i) {
            super(i);
        }

        public boolean offer(T t) {
            io.reactivex.internal.functions.a.a(t, "value is null");
            int andIncrement = this.producerIndex.getAndIncrement();
            if (andIncrement >= length()) {
                return false;
            }
            lazySet(andIncrement, t);
            return true;
        }

        public boolean offer(T t, T t2) {
            throw new UnsupportedOperationException();
        }

        public T poll() {
            int i = this.consumerIndex;
            if (i == length()) {
                return null;
            }
            AtomicInteger atomicInteger = this.producerIndex;
            do {
                T t = get(i);
                if (t != null) {
                    this.consumerIndex = i + 1;
                    lazySet(i, null);
                    return t;
                }
            } while (atomicInteger.get() != i);
            return null;
        }

        public T peek() {
            int i = this.consumerIndex;
            if (i == length()) {
                return null;
            }
            return get(i);
        }

        public void drop() {
            int i = this.consumerIndex;
            lazySet(i, null);
            this.consumerIndex = i + 1;
        }

        public boolean isEmpty() {
            return this.consumerIndex == producerIndex();
        }

        public void clear() {
            while (poll() != null) {
                if (isEmpty()) {
                    return;
                }
            }
        }

        public int consumerIndex() {
            return this.consumerIndex;
        }

        public int producerIndex() {
            return this.producerIndex.get();
        }
    }

    interface a<T> extends io.reactivex.internal.a.g<T> {
        int consumerIndex();

        void drop();

        T peek();

        T poll();

        int producerIndex();
    }

    /* access modifiers changed from: protected */
    public void a(c<? super T> cVar) {
        a aVar;
        o<? extends T>[] oVarArr = this.b;
        int length = oVarArr.length;
        if (length <= a()) {
            aVar = new MpscFillOnceSimpleQueue(length);
        } else {
            aVar = new ClqSimpleQueue();
        }
        MergeMaybeObserver mergeMaybeObserver = new MergeMaybeObserver(cVar, length, aVar);
        cVar.onSubscribe(mergeMaybeObserver);
        AtomicThrowable atomicThrowable = mergeMaybeObserver.error;
        int length2 = oVarArr.length;
        int i = 0;
        while (i < length2) {
            o<? extends T> oVar = oVarArr[i];
            if (!mergeMaybeObserver.isCancelled() && atomicThrowable.get() == null) {
                oVar.a(mergeMaybeObserver);
                i++;
            } else {
                return;
            }
        }
    }
}
