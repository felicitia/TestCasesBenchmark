package io.reactivex.internal.operators.flowable;

import io.reactivex.g;
import io.reactivex.internal.queue.a;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.b;
import io.reactivex.j;
import io.reactivex.processors.UnicastProcessor;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.reactivestreams.Subscription;
import org.reactivestreams.c;

public final class FlowableWindow<T> extends a<T, g<T>> {
    final long c;
    final long d;
    final int e;

    static final class WindowExactSubscriber<T> extends AtomicInteger implements j<T>, Runnable, Subscription {
        private static final long serialVersionUID = -2365647875069161133L;
        final c<? super g<T>> actual;
        final int bufferSize;
        long index;
        final AtomicBoolean once = new AtomicBoolean();
        Subscription s;
        final long size;
        UnicastProcessor<T> window;

        WindowExactSubscriber(c<? super g<T>> cVar, long j, int i) {
            super(1);
            this.actual = cVar;
            this.size = j;
            this.bufferSize = i;
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.s, subscription)) {
                this.s = subscription;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            long j = this.index;
            UnicastProcessor<T> unicastProcessor = this.window;
            if (j == 0) {
                getAndIncrement();
                unicastProcessor = UnicastProcessor.a(this.bufferSize, this);
                this.window = unicastProcessor;
                this.actual.onNext(unicastProcessor);
            }
            long j2 = j + 1;
            unicastProcessor.onNext(t);
            if (j2 == this.size) {
                this.index = 0;
                this.window = null;
                unicastProcessor.onComplete();
                return;
            }
            this.index = j2;
        }

        public void onError(Throwable th) {
            UnicastProcessor<T> unicastProcessor = this.window;
            if (unicastProcessor != null) {
                this.window = null;
                unicastProcessor.onError(th);
            }
            this.actual.onError(th);
        }

        public void onComplete() {
            UnicastProcessor<T> unicastProcessor = this.window;
            if (unicastProcessor != null) {
                this.window = null;
                unicastProcessor.onComplete();
            }
            this.actual.onComplete();
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                this.s.request(b.b(this.size, j));
            }
        }

        public void cancel() {
            if (this.once.compareAndSet(false, true)) {
                run();
            }
        }

        public void run() {
            if (decrementAndGet() == 0) {
                this.s.cancel();
            }
        }
    }

    static final class WindowOverlapSubscriber<T> extends AtomicInteger implements j<T>, Runnable, Subscription {
        private static final long serialVersionUID = 2428527070996323976L;
        final c<? super g<T>> actual;
        final int bufferSize;
        volatile boolean cancelled;
        volatile boolean done;
        Throwable error;
        final AtomicBoolean firstRequest = new AtomicBoolean();
        long index;
        final AtomicBoolean once = new AtomicBoolean();
        long produced;
        final a<UnicastProcessor<T>> queue;
        final AtomicLong requested = new AtomicLong();
        Subscription s;
        final long size;
        final long skip;
        final ArrayDeque<UnicastProcessor<T>> windows = new ArrayDeque<>();
        final AtomicInteger wip = new AtomicInteger();

        WindowOverlapSubscriber(c<? super g<T>> cVar, long j, long j2, int i) {
            super(1);
            this.actual = cVar;
            this.size = j;
            this.skip = j2;
            this.queue = new a<>(i);
            this.bufferSize = i;
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.s, subscription)) {
                this.s = subscription;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                long j = this.index;
                if (j == 0 && !this.cancelled) {
                    getAndIncrement();
                    UnicastProcessor a = UnicastProcessor.a(this.bufferSize, this);
                    this.windows.offer(a);
                    this.queue.offer(a);
                    drain();
                }
                long j2 = j + 1;
                Iterator it = this.windows.iterator();
                while (it.hasNext()) {
                    ((org.reactivestreams.a) it.next()).onNext(t);
                }
                long j3 = this.produced + 1;
                if (j3 == this.size) {
                    this.produced = j3 - this.skip;
                    org.reactivestreams.a aVar = (org.reactivestreams.a) this.windows.poll();
                    if (aVar != null) {
                        aVar.onComplete();
                    }
                } else {
                    this.produced = j3;
                }
                if (j2 == this.skip) {
                    this.index = 0;
                } else {
                    this.index = j2;
                }
            }
        }

        public void onError(Throwable th) {
            if (this.done) {
                io.reactivex.d.a.a(th);
                return;
            }
            Iterator it = this.windows.iterator();
            while (it.hasNext()) {
                ((org.reactivestreams.a) it.next()).onError(th);
            }
            this.windows.clear();
            this.error = th;
            this.done = true;
            drain();
        }

        public void onComplete() {
            if (!this.done) {
                Iterator it = this.windows.iterator();
                while (it.hasNext()) {
                    ((org.reactivestreams.a) it.next()).onComplete();
                }
                this.windows.clear();
                this.done = true;
                drain();
            }
        }

        /* access modifiers changed from: 0000 */
        public void drain() {
            if (this.wip.getAndIncrement() == 0) {
                c<? super g<T>> cVar = this.actual;
                a<UnicastProcessor<T>> aVar = this.queue;
                int i = 1;
                do {
                    long j = this.requested.get();
                    long j2 = 0;
                    while (j2 != j) {
                        boolean z = this.done;
                        UnicastProcessor unicastProcessor = (UnicastProcessor) aVar.poll();
                        boolean z2 = unicastProcessor == null;
                        if (!checkTerminated(z, z2, cVar, aVar)) {
                            if (z2) {
                                break;
                            }
                            cVar.onNext(unicastProcessor);
                            j2++;
                        } else {
                            return;
                        }
                    }
                    if (j2 != j || !checkTerminated(this.done, aVar.isEmpty(), cVar, aVar)) {
                        if (!(j2 == 0 || j == Long.MAX_VALUE)) {
                            this.requested.addAndGet(-j2);
                        }
                        i = this.wip.addAndGet(-i);
                    } else {
                        return;
                    }
                } while (i != 0);
            }
        }

        /* access modifiers changed from: 0000 */
        public boolean checkTerminated(boolean z, boolean z2, c<?> cVar, a<?> aVar) {
            if (this.cancelled) {
                aVar.clear();
                return true;
            }
            if (z) {
                Throwable th = this.error;
                if (th != null) {
                    aVar.clear();
                    cVar.onError(th);
                    return true;
                } else if (z2) {
                    cVar.onComplete();
                    return true;
                }
            }
            return false;
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                b.a(this.requested, j);
                if (this.firstRequest.get() || !this.firstRequest.compareAndSet(false, true)) {
                    this.s.request(b.b(this.skip, j));
                } else {
                    this.s.request(b.a(this.size, b.b(this.skip, j - 1)));
                }
                drain();
            }
        }

        public void cancel() {
            this.cancelled = true;
            if (this.once.compareAndSet(false, true)) {
                run();
            }
        }

        public void run() {
            if (decrementAndGet() == 0) {
                this.s.cancel();
            }
        }
    }

    static final class WindowSkipSubscriber<T> extends AtomicInteger implements j<T>, Runnable, Subscription {
        private static final long serialVersionUID = -8792836352386833856L;
        final c<? super g<T>> actual;
        final int bufferSize;
        final AtomicBoolean firstRequest = new AtomicBoolean();
        long index;
        final AtomicBoolean once = new AtomicBoolean();
        Subscription s;
        final long size;
        final long skip;
        UnicastProcessor<T> window;

        WindowSkipSubscriber(c<? super g<T>> cVar, long j, long j2, int i) {
            super(1);
            this.actual = cVar;
            this.size = j;
            this.skip = j2;
            this.bufferSize = i;
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.s, subscription)) {
                this.s = subscription;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            long j = this.index;
            UnicastProcessor<T> unicastProcessor = this.window;
            if (j == 0) {
                getAndIncrement();
                unicastProcessor = UnicastProcessor.a(this.bufferSize, this);
                this.window = unicastProcessor;
                this.actual.onNext(unicastProcessor);
            }
            long j2 = j + 1;
            if (unicastProcessor != null) {
                unicastProcessor.onNext(t);
            }
            if (j2 == this.size) {
                this.window = null;
                unicastProcessor.onComplete();
            }
            if (j2 == this.skip) {
                this.index = 0;
            } else {
                this.index = j2;
            }
        }

        public void onError(Throwable th) {
            UnicastProcessor<T> unicastProcessor = this.window;
            if (unicastProcessor != null) {
                this.window = null;
                unicastProcessor.onError(th);
            }
            this.actual.onError(th);
        }

        public void onComplete() {
            UnicastProcessor<T> unicastProcessor = this.window;
            if (unicastProcessor != null) {
                this.window = null;
                unicastProcessor.onComplete();
            }
            this.actual.onComplete();
        }

        public void request(long j) {
            if (!SubscriptionHelper.validate(j)) {
                return;
            }
            if (this.firstRequest.get() || !this.firstRequest.compareAndSet(false, true)) {
                this.s.request(b.b(this.skip, j));
                return;
            }
            this.s.request(b.a(b.b(this.size, j), b.b(this.skip - this.size, j - 1)));
        }

        public void cancel() {
            if (this.once.compareAndSet(false, true)) {
                run();
            }
        }

        public void run() {
            if (decrementAndGet() == 0) {
                this.s.cancel();
            }
        }
    }

    public void a(c<? super g<T>> cVar) {
        if (this.d == this.c) {
            this.b.a((j<? super T>) new WindowExactSubscriber<Object>(cVar, this.c, this.e));
        } else if (this.d > this.c) {
            g gVar = this.b;
            WindowSkipSubscriber windowSkipSubscriber = new WindowSkipSubscriber(cVar, this.c, this.d, this.e);
            gVar.a((j<? super T>) windowSkipSubscriber);
        } else {
            g gVar2 = this.b;
            WindowOverlapSubscriber windowOverlapSubscriber = new WindowOverlapSubscriber(cVar, this.c, this.d, this.e);
            gVar2.a((j<? super T>) windowOverlapSubscriber);
        }
    }
}
