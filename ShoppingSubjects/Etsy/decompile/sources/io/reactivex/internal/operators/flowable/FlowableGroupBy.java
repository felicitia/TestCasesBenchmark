package io.reactivex.internal.operators.flowable;

import io.reactivex.b.b;
import io.reactivex.functions.g;
import io.reactivex.internal.subscriptions.BasicIntQueueSubscription;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;
import org.reactivestreams.c;

public final class FlowableGroupBy<T, K, V> extends a<T, b<K, V>> {
    final g<? super T, ? extends K> c;
    final g<? super T, ? extends V> d;
    final int e;
    final boolean f;

    public static final class GroupBySubscriber<T, K, V> extends BasicIntQueueSubscription<b<K, V>> implements j<T> {
        static final Object NULL_KEY = new Object();
        private static final long serialVersionUID = -3688291656102519502L;
        final c<? super b<K, V>> actual;
        final int bufferSize;
        final AtomicBoolean cancelled = new AtomicBoolean();
        final boolean delayError;
        volatile boolean done;
        Throwable error;
        final AtomicInteger groupCount = new AtomicInteger(1);
        final Map<Object, a<K, V>> groups;
        final g<? super T, ? extends K> keySelector;
        boolean outputFused;
        final io.reactivex.internal.queue.a<b<K, V>> queue;
        final AtomicLong requested = new AtomicLong();
        Subscription s;
        final g<? super T, ? extends V> valueSelector;

        public GroupBySubscriber(c<? super b<K, V>> cVar, g<? super T, ? extends K> gVar, g<? super T, ? extends V> gVar2, int i, boolean z) {
            this.actual = cVar;
            this.keySelector = gVar;
            this.valueSelector = gVar2;
            this.bufferSize = i;
            this.delayError = z;
            this.groups = new ConcurrentHashMap();
            this.queue = new io.reactivex.internal.queue.a<>(i);
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.s, subscription)) {
                this.s = subscription;
                this.actual.onSubscribe(this);
                subscription.request((long) this.bufferSize);
            }
        }

        public void onNext(T t) {
            Object obj;
            if (!this.done) {
                io.reactivex.internal.queue.a<b<K, V>> aVar = this.queue;
                try {
                    Object apply = this.keySelector.apply(t);
                    boolean z = false;
                    if (apply != null) {
                        obj = apply;
                    } else {
                        obj = NULL_KEY;
                    }
                    a aVar2 = (a) this.groups.get(obj);
                    if (aVar2 == null) {
                        if (!this.cancelled.get()) {
                            aVar2 = a.a(apply, this.bufferSize, this, this.delayError);
                            this.groups.put(obj, aVar2);
                            this.groupCount.getAndIncrement();
                            z = true;
                        } else {
                            return;
                        }
                    }
                    try {
                        aVar2.a(io.reactivex.internal.functions.a.a(this.valueSelector.apply(t), "The valueSelector returned null"));
                        if (z) {
                            aVar.offer(aVar2);
                            drain();
                        }
                    } catch (Throwable th) {
                        io.reactivex.exceptions.a.b(th);
                        this.s.cancel();
                        onError(th);
                    }
                } catch (Throwable th2) {
                    io.reactivex.exceptions.a.b(th2);
                    this.s.cancel();
                    onError(th2);
                }
            }
        }

        public void onError(Throwable th) {
            if (this.done) {
                io.reactivex.d.a.a(th);
                return;
            }
            for (a a : this.groups.values()) {
                a.a(th);
            }
            this.groups.clear();
            this.error = th;
            this.done = true;
            drain();
        }

        public void onComplete() {
            if (!this.done) {
                for (a f : this.groups.values()) {
                    f.f();
                }
                this.groups.clear();
                this.done = true;
                drain();
            }
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                io.reactivex.internal.util.b.a(this.requested, j);
                drain();
            }
        }

        public void cancel() {
            if (this.cancelled.compareAndSet(false, true) && this.groupCount.decrementAndGet() == 0) {
                this.s.cancel();
            }
        }

        public void cancel(K k) {
            if (k == null) {
                k = NULL_KEY;
            }
            this.groups.remove(k);
            if (this.groupCount.decrementAndGet() == 0) {
                this.s.cancel();
                if (getAndIncrement() == 0) {
                    this.queue.clear();
                }
            }
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

        /* access modifiers changed from: 0000 */
        public void drainFused() {
            io.reactivex.internal.queue.a<b<K, V>> aVar = this.queue;
            c<? super b<K, V>> cVar = this.actual;
            int i = 1;
            while (!this.cancelled.get()) {
                boolean z = this.done;
                if (z && !this.delayError) {
                    Throwable th = this.error;
                    if (th != null) {
                        aVar.clear();
                        cVar.onError(th);
                        return;
                    }
                }
                cVar.onNext(null);
                if (z) {
                    Throwable th2 = this.error;
                    if (th2 != null) {
                        cVar.onError(th2);
                    } else {
                        cVar.onComplete();
                    }
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
        public void drainNormal() {
            io.reactivex.internal.queue.a<b<K, V>> aVar = this.queue;
            c<? super b<K, V>> cVar = this.actual;
            int i = 1;
            do {
                long j = this.requested.get();
                long j2 = 0;
                while (j2 != j) {
                    boolean z = this.done;
                    b bVar = (b) aVar.poll();
                    boolean z2 = bVar == null;
                    if (!checkTerminated(z, z2, cVar, aVar)) {
                        if (z2) {
                            break;
                        }
                        cVar.onNext(bVar);
                        j2++;
                    } else {
                        return;
                    }
                }
                if (j2 != j || !checkTerminated(this.done, aVar.isEmpty(), cVar, aVar)) {
                    if (j2 != 0) {
                        if (j != Long.MAX_VALUE) {
                            this.requested.addAndGet(-j2);
                        }
                        this.s.request(j2);
                    }
                    i = addAndGet(-i);
                } else {
                    return;
                }
            } while (i != 0);
        }

        /* access modifiers changed from: 0000 */
        public boolean checkTerminated(boolean z, boolean z2, c<?> cVar, io.reactivex.internal.queue.a<?> aVar) {
            if (this.cancelled.get()) {
                aVar.clear();
                return true;
            }
            if (this.delayError) {
                if (z && z2) {
                    Throwable th = this.error;
                    if (th != null) {
                        cVar.onError(th);
                    } else {
                        cVar.onComplete();
                    }
                    return true;
                }
            } else if (z) {
                Throwable th2 = this.error;
                if (th2 != null) {
                    aVar.clear();
                    cVar.onError(th2);
                    return true;
                } else if (z2) {
                    cVar.onComplete();
                    return true;
                }
            }
            return false;
        }

        public int requestFusion(int i) {
            if ((i & 2) == 0) {
                return 0;
            }
            this.outputFused = true;
            return 2;
        }

        public b<K, V> poll() {
            return (b) this.queue.poll();
        }

        public void clear() {
            this.queue.clear();
        }

        public boolean isEmpty() {
            return this.queue.isEmpty();
        }
    }

    static final class State<T, K> extends BasicIntQueueSubscription<T> implements org.reactivestreams.b<T> {
        private static final long serialVersionUID = -3852313036005250360L;
        final AtomicReference<c<? super T>> actual = new AtomicReference<>();
        final AtomicBoolean cancelled = new AtomicBoolean();
        final boolean delayError;
        volatile boolean done;
        Throwable error;
        final K key;
        final AtomicBoolean once = new AtomicBoolean();
        boolean outputFused;
        final GroupBySubscriber<?, K, T> parent;
        int produced;
        final io.reactivex.internal.queue.a<T> queue;
        final AtomicLong requested = new AtomicLong();

        State(int i, GroupBySubscriber<?, K, T> groupBySubscriber, K k, boolean z) {
            this.queue = new io.reactivex.internal.queue.a<>(i);
            this.parent = groupBySubscriber;
            this.key = k;
            this.delayError = z;
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                io.reactivex.internal.util.b.a(this.requested, j);
                drain();
            }
        }

        public void cancel() {
            if (this.cancelled.compareAndSet(false, true)) {
                this.parent.cancel(this.key);
            }
        }

        public void subscribe(c<? super T> cVar) {
            if (this.once.compareAndSet(false, true)) {
                cVar.onSubscribe(this);
                this.actual.lazySet(cVar);
                drain();
                return;
            }
            EmptySubscription.error(new IllegalStateException("Only one Subscriber allowed!"), cVar);
        }

        public void onNext(T t) {
            this.queue.offer(t);
            drain();
        }

        public void onError(Throwable th) {
            this.error = th;
            this.done = true;
            drain();
        }

        public void onComplete() {
            this.done = true;
            drain();
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

        /* access modifiers changed from: 0000 */
        public void drainFused() {
            io.reactivex.internal.queue.a<T> aVar = this.queue;
            c cVar = (c) this.actual.get();
            int i = 1;
            while (true) {
                if (cVar != null) {
                    if (this.cancelled.get()) {
                        aVar.clear();
                        return;
                    }
                    boolean z = this.done;
                    if (z && !this.delayError) {
                        Throwable th = this.error;
                        if (th != null) {
                            aVar.clear();
                            cVar.onError(th);
                            return;
                        }
                    }
                    cVar.onNext(null);
                    if (z) {
                        Throwable th2 = this.error;
                        if (th2 != null) {
                            cVar.onError(th2);
                        } else {
                            cVar.onComplete();
                        }
                        return;
                    }
                }
                i = addAndGet(-i);
                if (i != 0) {
                    if (cVar == null) {
                        cVar = (c) this.actual.get();
                    }
                } else {
                    return;
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void drainNormal() {
            io.reactivex.internal.queue.a<T> aVar = this.queue;
            boolean z = this.delayError;
            c cVar = (c) this.actual.get();
            int i = 1;
            while (true) {
                if (cVar != null) {
                    long j = this.requested.get();
                    long j2 = 0;
                    while (j2 != j) {
                        boolean z2 = this.done;
                        Object poll = aVar.poll();
                        boolean z3 = poll == null;
                        if (!checkTerminated(z2, z3, cVar, z)) {
                            if (z3) {
                                break;
                            }
                            cVar.onNext(poll);
                            j2++;
                        } else {
                            return;
                        }
                    }
                    if (j2 == j && checkTerminated(this.done, aVar.isEmpty(), cVar, z)) {
                        return;
                    }
                    if (j2 != 0) {
                        if (j != Long.MAX_VALUE) {
                            this.requested.addAndGet(-j2);
                        }
                        this.parent.s.request(j2);
                    }
                }
                i = addAndGet(-i);
                if (i != 0) {
                    if (cVar == null) {
                        cVar = (c) this.actual.get();
                    }
                } else {
                    return;
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public boolean checkTerminated(boolean z, boolean z2, c<? super T> cVar, boolean z3) {
            if (this.cancelled.get()) {
                this.queue.clear();
                return true;
            }
            if (z) {
                if (!z3) {
                    Throwable th = this.error;
                    if (th != null) {
                        this.queue.clear();
                        cVar.onError(th);
                        return true;
                    } else if (z2) {
                        cVar.onComplete();
                        return true;
                    }
                } else if (z2) {
                    Throwable th2 = this.error;
                    if (th2 != null) {
                        cVar.onError(th2);
                    } else {
                        cVar.onComplete();
                    }
                    return true;
                }
            }
            return false;
        }

        public int requestFusion(int i) {
            if ((i & 2) == 0) {
                return 0;
            }
            this.outputFused = true;
            return 2;
        }

        public T poll() {
            T poll = this.queue.poll();
            if (poll != null) {
                this.produced++;
                return poll;
            }
            int i = this.produced;
            if (i != 0) {
                this.produced = 0;
                this.parent.s.request((long) i);
            }
            return null;
        }

        public boolean isEmpty() {
            return this.queue.isEmpty();
        }

        public void clear() {
            this.queue.clear();
        }
    }

    static final class a<K, T> extends b<K, T> {
        final State<T, K> c;

        public static <T, K> a<K, T> a(K k, int i, GroupBySubscriber<?, K, T> groupBySubscriber, boolean z) {
            return new a<>(k, new State(i, groupBySubscriber, k, z));
        }

        protected a(K k, State<T, K> state) {
            super(k);
            this.c = state;
        }

        /* access modifiers changed from: protected */
        public void a(c<? super T> cVar) {
            this.c.subscribe(cVar);
        }

        public void a(T t) {
            this.c.onNext(t);
        }

        public void a(Throwable th) {
            this.c.onError(th);
        }

        public void f() {
            this.c.onComplete();
        }
    }

    /* access modifiers changed from: protected */
    public void a(c<? super b<K, V>> cVar) {
        io.reactivex.g gVar = this.b;
        GroupBySubscriber groupBySubscriber = new GroupBySubscriber(cVar, this.c, this.d, this.e, this.f);
        gVar.a((j<? super T>) groupBySubscriber);
    }
}
