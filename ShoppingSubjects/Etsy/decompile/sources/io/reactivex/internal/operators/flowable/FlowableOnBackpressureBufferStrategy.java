package io.reactivex.internal.operators.flowable;

import io.reactivex.BackpressureOverflowStrategy;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.functions.a;
import io.reactivex.g;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.b;
import io.reactivex.j;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.reactivestreams.Subscription;
import org.reactivestreams.c;

public final class FlowableOnBackpressureBufferStrategy<T> extends a<T, T> {
    final long c;
    final a d;
    final BackpressureOverflowStrategy e;

    static final class OnBackpressureBufferStrategySubscriber<T> extends AtomicInteger implements j<T>, Subscription {
        private static final long serialVersionUID = 3240706908776709697L;
        final c<? super T> actual;
        final long bufferSize;
        volatile boolean cancelled;
        final Deque<T> deque = new ArrayDeque();
        volatile boolean done;
        Throwable error;
        final a onOverflow;
        final AtomicLong requested = new AtomicLong();
        Subscription s;
        final BackpressureOverflowStrategy strategy;

        OnBackpressureBufferStrategySubscriber(c<? super T> cVar, a aVar, BackpressureOverflowStrategy backpressureOverflowStrategy, long j) {
            this.actual = cVar;
            this.onOverflow = aVar;
            this.strategy = backpressureOverflowStrategy;
            this.bufferSize = j;
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.s, subscription)) {
                this.s = subscription;
                this.actual.onSubscribe(this);
                subscription.request(Long.MAX_VALUE);
            }
        }

        public void onNext(T t) {
            boolean z;
            boolean z2;
            if (!this.done) {
                Deque<T> deque2 = this.deque;
                synchronized (deque2) {
                    z = false;
                    z2 = true;
                    if (((long) deque2.size()) == this.bufferSize) {
                        switch (this.strategy) {
                            case DROP_LATEST:
                                deque2.pollLast();
                                deque2.offer(t);
                                break;
                            case DROP_OLDEST:
                                deque2.poll();
                                deque2.offer(t);
                                break;
                        }
                        z2 = false;
                        z = true;
                    } else {
                        deque2.offer(t);
                        z2 = false;
                    }
                }
                if (z) {
                    if (this.onOverflow != null) {
                        try {
                            this.onOverflow.a();
                        } catch (Throwable th) {
                            io.reactivex.exceptions.a.b(th);
                            this.s.cancel();
                            onError(th);
                        }
                    }
                } else if (z2) {
                    this.s.cancel();
                    onError(new MissingBackpressureException());
                } else {
                    drain();
                }
            }
        }

        public void onError(Throwable th) {
            if (this.done) {
                io.reactivex.d.a.a(th);
                return;
            }
            this.error = th;
            this.done = true;
            drain();
        }

        public void onComplete() {
            this.done = true;
            drain();
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                b.a(this.requested, j);
                drain();
            }
        }

        public void cancel() {
            this.cancelled = true;
            this.s.cancel();
            if (getAndIncrement() == 0) {
                clear(this.deque);
            }
        }

        /* access modifiers changed from: 0000 */
        public void clear(Deque<T> deque2) {
            synchronized (deque2) {
                deque2.clear();
            }
        }

        /* access modifiers changed from: 0000 */
        public void drain() {
            boolean isEmpty;
            Object poll;
            if (getAndIncrement() == 0) {
                Deque<T> deque2 = this.deque;
                c<? super T> cVar = this.actual;
                int i = 1;
                do {
                    long j = this.requested.get();
                    long j2 = 0;
                    while (j2 != j) {
                        if (this.cancelled) {
                            clear(deque2);
                            return;
                        }
                        boolean z = this.done;
                        synchronized (deque2) {
                            poll = deque2.poll();
                        }
                        boolean z2 = poll == null;
                        if (z) {
                            Throwable th = this.error;
                            if (th != null) {
                                clear(deque2);
                                cVar.onError(th);
                                return;
                            } else if (z2) {
                                cVar.onComplete();
                                return;
                            }
                        }
                        if (z2) {
                            break;
                        }
                        cVar.onNext(poll);
                        j2++;
                    }
                    if (j2 == j) {
                        if (this.cancelled) {
                            clear(deque2);
                            return;
                        }
                        boolean z3 = this.done;
                        synchronized (deque2) {
                            isEmpty = deque2.isEmpty();
                        }
                        if (z3) {
                            Throwable th2 = this.error;
                            if (th2 != null) {
                                clear(deque2);
                                cVar.onError(th2);
                                return;
                            } else if (isEmpty) {
                                cVar.onComplete();
                                return;
                            }
                        }
                    }
                    if (j2 != 0) {
                        b.c(this.requested, j2);
                    }
                    i = addAndGet(-i);
                } while (i != 0);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a(c<? super T> cVar) {
        g gVar = this.b;
        OnBackpressureBufferStrategySubscriber onBackpressureBufferStrategySubscriber = new OnBackpressureBufferStrategySubscriber(cVar, this.d, this.e, this.c);
        gVar.a((j<? super T>) onBackpressureBufferStrategySubscriber);
    }
}
