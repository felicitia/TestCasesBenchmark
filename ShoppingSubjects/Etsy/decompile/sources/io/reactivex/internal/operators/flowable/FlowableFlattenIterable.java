package io.reactivex.internal.operators.flowable;

import io.reactivex.d.a;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.functions.g;
import io.reactivex.internal.a.d;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.BasicIntQueueSubscription;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.internal.util.b;
import io.reactivex.j;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;
import org.reactivestreams.c;

public final class FlowableFlattenIterable<T, R> extends a<T, R> {
    final g<? super T, ? extends Iterable<? extends R>> c;
    final int d;

    static final class FlattenIterableSubscriber<T, R> extends BasicIntQueueSubscription<R> implements j<T> {
        private static final long serialVersionUID = -3096000382929934955L;
        final c<? super R> actual;
        volatile boolean cancelled;
        int consumed;
        Iterator<? extends R> current;
        volatile boolean done;
        final AtomicReference<Throwable> error = new AtomicReference<>();
        int fusionMode;
        final int limit;
        final g<? super T, ? extends Iterable<? extends R>> mapper;
        final int prefetch;
        io.reactivex.internal.a.g<T> queue;
        final AtomicLong requested = new AtomicLong();
        Subscription s;

        FlattenIterableSubscriber(c<? super R> cVar, g<? super T, ? extends Iterable<? extends R>> gVar, int i) {
            this.actual = cVar;
            this.mapper = gVar;
            this.prefetch = i;
            this.limit = i - (i >> 2);
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.s, subscription)) {
                this.s = subscription;
                if (subscription instanceof d) {
                    d dVar = (d) subscription;
                    int requestFusion = dVar.requestFusion(3);
                    if (requestFusion == 1) {
                        this.fusionMode = requestFusion;
                        this.queue = dVar;
                        this.done = true;
                        this.actual.onSubscribe(this);
                        return;
                    } else if (requestFusion == 2) {
                        this.fusionMode = requestFusion;
                        this.queue = dVar;
                        this.actual.onSubscribe(this);
                        subscription.request((long) this.prefetch);
                        return;
                    }
                }
                this.queue = new SpscArrayQueue(this.prefetch);
                this.actual.onSubscribe(this);
                subscription.request((long) this.prefetch);
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                if (this.fusionMode != 0 || this.queue.offer(t)) {
                    drain();
                } else {
                    onError(new MissingBackpressureException("Queue is full?!"));
                }
            }
        }

        public void onError(Throwable th) {
            if (this.done || !ExceptionHelper.a(this.error, th)) {
                a.a(th);
                return;
            }
            this.done = true;
            drain();
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                drain();
            }
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
                this.s.cancel();
                if (getAndIncrement() == 0) {
                    this.queue.clear();
                }
            }
        }

        /* access modifiers changed from: 0000 */
        /* JADX WARNING: Code restructure failed: missing block: B:69:0x0126, code lost:
            if (r7 == null) goto L_0x0131;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void drain() {
            /*
                r19 = this;
                r1 = r19
                int r2 = r19.getAndIncrement()
                if (r2 == 0) goto L_0x0009
                return
            L_0x0009:
                org.reactivestreams.c<? super R> r2 = r1.actual
                io.reactivex.internal.a.g<T> r3 = r1.queue
                int r4 = r1.fusionMode
                r5 = 0
                r6 = 1
                if (r4 == r6) goto L_0x0015
                r4 = r6
                goto L_0x0016
            L_0x0015:
                r4 = r5
            L_0x0016:
                java.util.Iterator<? extends R> r7 = r1.current
                r8 = 0
                r9 = r6
            L_0x001a:
                if (r7 != 0) goto L_0x0080
                boolean r10 = r1.done
                java.lang.Object r11 = r3.poll()     // Catch:{ Throwable -> 0x0063 }
                if (r11 != 0) goto L_0x0026
                r12 = r6
                goto L_0x0027
            L_0x0026:
                r12 = r5
            L_0x0027:
                boolean r10 = r1.checkTerminated(r10, r12, r2, r3)
                if (r10 == 0) goto L_0x002e
                return
            L_0x002e:
                if (r11 == 0) goto L_0x0080
                io.reactivex.functions.g<? super T, ? extends java.lang.Iterable<? extends R>> r7 = r1.mapper     // Catch:{ Throwable -> 0x004a }
                java.lang.Object r7 = r7.apply(r11)     // Catch:{ Throwable -> 0x004a }
                java.lang.Iterable r7 = (java.lang.Iterable) r7     // Catch:{ Throwable -> 0x004a }
                java.util.Iterator r7 = r7.iterator()     // Catch:{ Throwable -> 0x004a }
                boolean r10 = r7.hasNext()     // Catch:{ Throwable -> 0x004a }
                if (r10 != 0) goto L_0x0047
                r1.consumedOne(r4)
                r7 = r8
                goto L_0x001a
            L_0x0047:
                r1.current = r7
                goto L_0x0080
            L_0x004a:
                r0 = move-exception
                r3 = r0
                io.reactivex.exceptions.a.b(r3)
                org.reactivestreams.Subscription r4 = r1.s
                r4.cancel()
                java.util.concurrent.atomic.AtomicReference<java.lang.Throwable> r4 = r1.error
                io.reactivex.internal.util.ExceptionHelper.a(r4, r3)
                java.util.concurrent.atomic.AtomicReference<java.lang.Throwable> r3 = r1.error
                java.lang.Throwable r3 = io.reactivex.internal.util.ExceptionHelper.a(r3)
                r2.onError(r3)
                return
            L_0x0063:
                r0 = move-exception
                io.reactivex.exceptions.a.b(r0)
                org.reactivestreams.Subscription r4 = r1.s
                r4.cancel()
                java.util.concurrent.atomic.AtomicReference<java.lang.Throwable> r4 = r1.error
                io.reactivex.internal.util.ExceptionHelper.a(r4, r0)
                java.util.concurrent.atomic.AtomicReference<java.lang.Throwable> r4 = r1.error
                java.lang.Throwable r4 = io.reactivex.internal.util.ExceptionHelper.a(r4)
                r1.current = r8
                r3.clear()
                r2.onError(r4)
                return
            L_0x0080:
                if (r7 == 0) goto L_0x0129
                java.util.concurrent.atomic.AtomicLong r10 = r1.requested
                long r10 = r10.get()
                r14 = 0
            L_0x008a:
                int r16 = (r14 > r10 ? 1 : (r14 == r10 ? 0 : -1))
                if (r16 == 0) goto L_0x00f9
                boolean r6 = r1.done
                boolean r6 = r1.checkTerminated(r6, r5, r2, r3)
                if (r6 == 0) goto L_0x0097
                return
            L_0x0097:
                java.lang.Object r6 = r7.next()     // Catch:{ Throwable -> 0x00de }
                java.lang.String r12 = "The iterator returned a null value"
                java.lang.Object r6 = io.reactivex.internal.functions.a.a(r6, r12)     // Catch:{ Throwable -> 0x00de }
                r2.onNext(r6)
                boolean r6 = r1.done
                boolean r6 = r1.checkTerminated(r6, r5, r2, r3)
                if (r6 == 0) goto L_0x00ad
                return
            L_0x00ad:
                r12 = 1
                long r17 = r14 + r12
                boolean r6 = r7.hasNext()     // Catch:{ Throwable -> 0x00c4 }
                if (r6 != 0) goto L_0x00c0
                r1.consumedOne(r4)
                r1.current = r8
                r7 = r8
                r14 = r17
                goto L_0x00f9
            L_0x00c0:
                r14 = r17
                r6 = 1
                goto L_0x008a
            L_0x00c4:
                r0 = move-exception
                io.reactivex.exceptions.a.b(r0)
                r1.current = r8
                org.reactivestreams.Subscription r3 = r1.s
                r3.cancel()
                java.util.concurrent.atomic.AtomicReference<java.lang.Throwable> r3 = r1.error
                io.reactivex.internal.util.ExceptionHelper.a(r3, r0)
                java.util.concurrent.atomic.AtomicReference<java.lang.Throwable> r3 = r1.error
                java.lang.Throwable r3 = io.reactivex.internal.util.ExceptionHelper.a(r3)
                r2.onError(r3)
                return
            L_0x00de:
                r0 = move-exception
                r3 = r0
                io.reactivex.exceptions.a.b(r3)
                r1.current = r8
                org.reactivestreams.Subscription r4 = r1.s
                r4.cancel()
                java.util.concurrent.atomic.AtomicReference<java.lang.Throwable> r4 = r1.error
                io.reactivex.internal.util.ExceptionHelper.a(r4, r3)
                java.util.concurrent.atomic.AtomicReference<java.lang.Throwable> r3 = r1.error
                java.lang.Throwable r3 = io.reactivex.internal.util.ExceptionHelper.a(r3)
                r2.onError(r3)
                return
            L_0x00f9:
                int r6 = (r14 > r10 ? 1 : (r14 == r10 ? 0 : -1))
                if (r6 != 0) goto L_0x0111
                boolean r6 = r1.done
                boolean r12 = r3.isEmpty()
                if (r12 == 0) goto L_0x0109
                if (r7 != 0) goto L_0x0109
                r12 = 1
                goto L_0x010a
            L_0x0109:
                r12 = r5
            L_0x010a:
                boolean r6 = r1.checkTerminated(r6, r12, r2, r3)
                if (r6 == 0) goto L_0x0111
                return
            L_0x0111:
                r12 = 0
                int r6 = (r14 > r12 ? 1 : (r14 == r12 ? 0 : -1))
                if (r6 == 0) goto L_0x0126
                r12 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                int r6 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
                if (r6 == 0) goto L_0x0126
                java.util.concurrent.atomic.AtomicLong r6 = r1.requested
                long r10 = -r14
                r6.addAndGet(r10)
            L_0x0126:
                if (r7 != 0) goto L_0x0129
                goto L_0x0131
            L_0x0129:
                int r6 = -r9
                int r9 = r1.addAndGet(r6)
                if (r9 != 0) goto L_0x0131
                return
            L_0x0131:
                r6 = 1
                goto L_0x001a
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.flowable.FlowableFlattenIterable.FlattenIterableSubscriber.drain():void");
        }

        /* access modifiers changed from: 0000 */
        public void consumedOne(boolean z) {
            if (z) {
                int i = this.consumed + 1;
                if (i == this.limit) {
                    this.consumed = 0;
                    this.s.request((long) i);
                    return;
                }
                this.consumed = i;
            }
        }

        /* access modifiers changed from: 0000 */
        public boolean checkTerminated(boolean z, boolean z2, c<?> cVar, io.reactivex.internal.a.g<?> gVar) {
            if (this.cancelled) {
                this.current = null;
                gVar.clear();
                return true;
            }
            if (z) {
                if (((Throwable) this.error.get()) != null) {
                    Throwable a = ExceptionHelper.a(this.error);
                    this.current = null;
                    gVar.clear();
                    cVar.onError(a);
                    return true;
                } else if (z2) {
                    cVar.onComplete();
                    return true;
                }
            }
            return false;
        }

        public void clear() {
            this.current = null;
            this.queue.clear();
        }

        public boolean isEmpty() {
            Iterator<? extends R> it = this.current;
            if (it == null) {
                return this.queue.isEmpty();
            }
            return !it.hasNext();
        }

        public R poll() throws Exception {
            Iterator<? extends R> it = this.current;
            while (true) {
                if (it != null) {
                    break;
                }
                Object poll = this.queue.poll();
                if (poll != null) {
                    it = ((Iterable) this.mapper.apply(poll)).iterator();
                    if (it.hasNext()) {
                        this.current = it;
                        break;
                    }
                    it = null;
                } else {
                    return null;
                }
            }
            R a = io.reactivex.internal.functions.a.a(it.next(), "The iterator returned a null value");
            if (!it.hasNext()) {
                this.current = null;
            }
            return a;
        }

        public int requestFusion(int i) {
            return ((i & 1) == 0 || this.fusionMode != 1) ? 0 : 1;
        }
    }

    public void a(c<? super R> cVar) {
        if (this.b instanceof Callable) {
            try {
                Object call = ((Callable) this.b).call();
                if (call == null) {
                    EmptySubscription.complete(cVar);
                    return;
                }
                try {
                    FlowableFromIterable.a(cVar, ((Iterable) this.c.apply(call)).iterator());
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    EmptySubscription.error(th, cVar);
                }
            } catch (Throwable th2) {
                io.reactivex.exceptions.a.b(th2);
                EmptySubscription.error(th2, cVar);
            }
        } else {
            this.b.a((j<? super T>) new FlattenIterableSubscriber<Object>(cVar, this.c, this.d));
        }
    }
}
