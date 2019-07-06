package io.reactivex.internal.operators.flowable;

import io.reactivex.functions.e;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.b;
import io.reactivex.internal.util.i;
import io.reactivex.j;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.reactivestreams.Subscription;
import org.reactivestreams.c;

public final class FlowableBuffer<T, C extends Collection<? super T>> extends a<T, C> {
    final int c;
    final int d;
    final Callable<C> e;

    static final class PublisherBufferOverlappingSubscriber<T, C extends Collection<? super T>> extends AtomicLong implements e, j<T>, Subscription {
        private static final long serialVersionUID = -7370244972039324525L;
        final c<? super C> actual;
        final Callable<C> bufferSupplier;
        final ArrayDeque<C> buffers = new ArrayDeque<>();
        volatile boolean cancelled;
        boolean done;
        int index;
        final AtomicBoolean once = new AtomicBoolean();
        long produced;
        Subscription s;
        final int size;
        final int skip;

        PublisherBufferOverlappingSubscriber(c<? super C> cVar, int i, int i2, Callable<C> callable) {
            this.actual = cVar;
            this.size = i;
            this.skip = i2;
            this.bufferSupplier = callable;
        }

        public boolean getAsBoolean() {
            return this.cancelled;
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                if (!i.a(j, this.actual, this.buffers, this, this)) {
                    if (this.once.get() || !this.once.compareAndSet(false, true)) {
                        this.s.request(b.b((long) this.skip, j));
                    } else {
                        this.s.request(b.a((long) this.size, b.b((long) this.skip, j - 1)));
                    }
                }
            }
        }

        public void cancel() {
            this.cancelled = true;
            this.s.cancel();
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.s, subscription)) {
                this.s = subscription;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                ArrayDeque<C> arrayDeque = this.buffers;
                int i = this.index;
                int i2 = i + 1;
                if (i == 0) {
                    try {
                        arrayDeque.offer((Collection) io.reactivex.internal.functions.a.a(this.bufferSupplier.call(), "The bufferSupplier returned a null buffer"));
                    } catch (Throwable th) {
                        io.reactivex.exceptions.a.b(th);
                        cancel();
                        onError(th);
                        return;
                    }
                }
                Collection collection = (Collection) arrayDeque.peek();
                if (collection != null && collection.size() + 1 == this.size) {
                    arrayDeque.poll();
                    collection.add(t);
                    this.produced++;
                    this.actual.onNext(collection);
                }
                Iterator it = arrayDeque.iterator();
                while (it.hasNext()) {
                    ((Collection) it.next()).add(t);
                }
                if (i2 == this.skip) {
                    i2 = 0;
                }
                this.index = i2;
            }
        }

        public void onError(Throwable th) {
            if (this.done) {
                io.reactivex.d.a.a(th);
                return;
            }
            this.done = true;
            this.buffers.clear();
            this.actual.onError(th);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                long j = this.produced;
                if (j != 0) {
                    b.c(this, j);
                }
                i.a(this.actual, this.buffers, this, this);
            }
        }
    }

    static final class PublisherBufferSkipSubscriber<T, C extends Collection<? super T>> extends AtomicInteger implements j<T>, Subscription {
        private static final long serialVersionUID = -5616169793639412593L;
        final c<? super C> actual;
        C buffer;
        final Callable<C> bufferSupplier;
        boolean done;
        int index;
        Subscription s;
        final int size;
        final int skip;

        PublisherBufferSkipSubscriber(c<? super C> cVar, int i, int i2, Callable<C> callable) {
            this.actual = cVar;
            this.size = i;
            this.skip = i2;
            this.bufferSupplier = callable;
        }

        public void request(long j) {
            if (!SubscriptionHelper.validate(j)) {
                return;
            }
            if (get() != 0 || !compareAndSet(0, 1)) {
                this.s.request(b.b((long) this.skip, j));
                return;
            }
            this.s.request(b.a(b.b(j, (long) this.size), b.b((long) (this.skip - this.size), j - 1)));
        }

        public void cancel() {
            this.s.cancel();
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.s, subscription)) {
                this.s = subscription;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                C c = this.buffer;
                int i = this.index;
                int i2 = i + 1;
                if (i == 0) {
                    try {
                        c = (Collection) io.reactivex.internal.functions.a.a(this.bufferSupplier.call(), "The bufferSupplier returned a null buffer");
                        this.buffer = c;
                    } catch (Throwable th) {
                        io.reactivex.exceptions.a.b(th);
                        cancel();
                        onError(th);
                        return;
                    }
                }
                if (c != null) {
                    c.add(t);
                    if (c.size() == this.size) {
                        this.buffer = null;
                        this.actual.onNext(c);
                    }
                }
                if (i2 == this.skip) {
                    i2 = 0;
                }
                this.index = i2;
            }
        }

        public void onError(Throwable th) {
            if (this.done) {
                io.reactivex.d.a.a(th);
                return;
            }
            this.done = true;
            this.buffer = null;
            this.actual.onError(th);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                C c = this.buffer;
                this.buffer = null;
                if (c != null) {
                    this.actual.onNext(c);
                }
                this.actual.onComplete();
            }
        }
    }

    static final class a<T, C extends Collection<? super T>> implements j<T>, Subscription {
        final c<? super C> a;
        final Callable<C> b;
        final int c;
        C d;
        Subscription e;
        boolean f;
        int g;

        a(c<? super C> cVar, int i, Callable<C> callable) {
            this.a = cVar;
            this.c = i;
            this.b = callable;
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                this.e.request(b.b(j, (long) this.c));
            }
        }

        public void cancel() {
            this.e.cancel();
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.e, subscription)) {
                this.e = subscription;
                this.a.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            if (!this.f) {
                C c2 = this.d;
                if (c2 == null) {
                    try {
                        c2 = (Collection) io.reactivex.internal.functions.a.a(this.b.call(), "The bufferSupplier returned a null buffer");
                        this.d = c2;
                    } catch (Throwable th) {
                        io.reactivex.exceptions.a.b(th);
                        cancel();
                        onError(th);
                        return;
                    }
                }
                c2.add(t);
                int i = this.g + 1;
                if (i == this.c) {
                    this.g = 0;
                    this.d = null;
                    this.a.onNext(c2);
                } else {
                    this.g = i;
                }
            }
        }

        public void onError(Throwable th) {
            if (this.f) {
                io.reactivex.d.a.a(th);
                return;
            }
            this.f = true;
            this.a.onError(th);
        }

        public void onComplete() {
            if (!this.f) {
                this.f = true;
                C c2 = this.d;
                if (c2 != null && !c2.isEmpty()) {
                    this.a.onNext(c2);
                }
                this.a.onComplete();
            }
        }
    }

    public void a(c<? super C> cVar) {
        if (this.c == this.d) {
            this.b.a((j<? super T>) new a<Object>(cVar, this.c, this.e));
        } else if (this.d > this.c) {
            this.b.a((j<? super T>) new PublisherBufferSkipSubscriber<Object>(cVar, this.c, this.d, this.e));
        } else {
            this.b.a((j<? super T>) new PublisherBufferOverlappingSubscriber<Object>(cVar, this.c, this.d, this.e));
        }
    }
}
