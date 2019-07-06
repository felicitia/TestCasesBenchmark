package io.reactivex.internal.operators.flowable;

import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.functions.g;
import io.reactivex.internal.queue.a;
import io.reactivex.internal.subscribers.InnerQueuedSubscriber;
import io.reactivex.internal.subscribers.c;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.ErrorMode;
import io.reactivex.j;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.reactivestreams.Subscription;
import org.reactivestreams.b;

public final class FlowableConcatMapEager<T, R> extends a<T, R> {
    final g<? super T, ? extends b<? extends R>> c;
    final int d;
    final int e;
    final ErrorMode f;

    static final class ConcatMapEagerDelayErrorSubscriber<T, R> extends AtomicInteger implements c<R>, j<T>, Subscription {
        private static final long serialVersionUID = -4255299542215038287L;
        final org.reactivestreams.c<? super R> actual;
        volatile boolean cancelled;
        volatile InnerQueuedSubscriber<R> current;
        volatile boolean done;
        final ErrorMode errorMode;
        final AtomicThrowable errors = new AtomicThrowable();
        final g<? super T, ? extends b<? extends R>> mapper;
        final int maxConcurrency;
        final int prefetch;
        final AtomicLong requested = new AtomicLong();
        Subscription s;
        final a<InnerQueuedSubscriber<R>> subscribers;

        ConcatMapEagerDelayErrorSubscriber(org.reactivestreams.c<? super R> cVar, g<? super T, ? extends b<? extends R>> gVar, int i, int i2, ErrorMode errorMode2) {
            this.actual = cVar;
            this.mapper = gVar;
            this.maxConcurrency = i;
            this.prefetch = i2;
            this.errorMode = errorMode2;
            this.subscribers = new a<>(Math.min(i2, i));
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.s, subscription)) {
                this.s = subscription;
                this.actual.onSubscribe(this);
                subscription.request(this.maxConcurrency == Integer.MAX_VALUE ? Long.MAX_VALUE : (long) this.maxConcurrency);
            }
        }

        public void onNext(T t) {
            try {
                b bVar = (b) io.reactivex.internal.functions.a.a(this.mapper.apply(t), "The mapper returned a null Publisher");
                InnerQueuedSubscriber innerQueuedSubscriber = new InnerQueuedSubscriber(this, this.prefetch);
                if (!this.cancelled) {
                    this.subscribers.offer(innerQueuedSubscriber);
                    if (!this.cancelled) {
                        bVar.subscribe(innerQueuedSubscriber);
                        if (this.cancelled) {
                            innerQueuedSubscriber.cancel();
                            drainAndCancel();
                        }
                    }
                }
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                this.s.cancel();
                onError(th);
            }
        }

        public void onError(Throwable th) {
            if (this.errors.addThrowable(th)) {
                this.done = true;
                drain();
                return;
            }
            io.reactivex.d.a.a(th);
        }

        public void onComplete() {
            this.done = true;
            drain();
        }

        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.s.cancel();
                drainAndCancel();
            }
        }

        /* access modifiers changed from: 0000 */
        public void drainAndCancel() {
            if (getAndIncrement() == 0) {
                do {
                    cancelAll();
                } while (decrementAndGet() != 0);
            }
        }

        /* access modifiers changed from: 0000 */
        public void cancelAll() {
            while (true) {
                InnerQueuedSubscriber innerQueuedSubscriber = (InnerQueuedSubscriber) this.subscribers.poll();
                if (innerQueuedSubscriber != null) {
                    innerQueuedSubscriber.cancel();
                } else {
                    return;
                }
            }
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                io.reactivex.internal.util.b.a(this.requested, j);
                drain();
            }
        }

        public void innerNext(InnerQueuedSubscriber<R> innerQueuedSubscriber, R r) {
            if (innerQueuedSubscriber.queue().offer(r)) {
                drain();
                return;
            }
            innerQueuedSubscriber.cancel();
            innerError(innerQueuedSubscriber, new MissingBackpressureException());
        }

        public void innerError(InnerQueuedSubscriber<R> innerQueuedSubscriber, Throwable th) {
            if (this.errors.addThrowable(th)) {
                innerQueuedSubscriber.setDone();
                if (this.errorMode != ErrorMode.END) {
                    this.s.cancel();
                }
                drain();
                return;
            }
            io.reactivex.d.a.a(th);
        }

        public void innerComplete(InnerQueuedSubscriber<R> innerQueuedSubscriber) {
            innerQueuedSubscriber.setDone();
            drain();
        }

        /* JADX WARNING: Removed duplicated region for block: B:79:0x0133  */
        /* JADX WARNING: Removed duplicated region for block: B:80:0x0137  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void drain() {
            /*
                r20 = this;
                r1 = r20
                int r2 = r20.getAndIncrement()
                if (r2 == 0) goto L_0x0009
                return
            L_0x0009:
                io.reactivex.internal.subscribers.InnerQueuedSubscriber<R> r2 = r1.current
                org.reactivestreams.c<? super R> r3 = r1.actual
                io.reactivex.internal.util.ErrorMode r4 = r1.errorMode
                r6 = 1
            L_0x0010:
                java.util.concurrent.atomic.AtomicLong r7 = r1.requested
                long r7 = r7.get()
                if (r2 != 0) goto L_0x0056
                io.reactivex.internal.util.ErrorMode r2 = io.reactivex.internal.util.ErrorMode.END
                if (r4 == r2) goto L_0x0033
                io.reactivex.internal.util.AtomicThrowable r2 = r1.errors
                java.lang.Object r2 = r2.get()
                java.lang.Throwable r2 = (java.lang.Throwable) r2
                if (r2 == 0) goto L_0x0033
                r20.cancelAll()
                io.reactivex.internal.util.AtomicThrowable r2 = r1.errors
                java.lang.Throwable r2 = r2.terminate()
                r3.onError(r2)
                return
            L_0x0033:
                boolean r2 = r1.done
                io.reactivex.internal.queue.a<io.reactivex.internal.subscribers.InnerQueuedSubscriber<R>> r9 = r1.subscribers
                java.lang.Object r9 = r9.poll()
                io.reactivex.internal.subscribers.InnerQueuedSubscriber r9 = (io.reactivex.internal.subscribers.InnerQueuedSubscriber) r9
                if (r2 == 0) goto L_0x0051
                if (r9 != 0) goto L_0x0051
                io.reactivex.internal.util.AtomicThrowable r2 = r1.errors
                java.lang.Throwable r2 = r2.terminate()
                if (r2 == 0) goto L_0x004d
                r3.onError(r2)
                goto L_0x0050
            L_0x004d:
                r3.onComplete()
            L_0x0050:
                return
            L_0x0051:
                if (r9 == 0) goto L_0x0055
                r1.current = r9
            L_0x0055:
                r2 = r9
            L_0x0056:
                r12 = 0
                if (r2 == 0) goto L_0x0116
                io.reactivex.internal.a.g r13 = r2.queue()
                if (r13 == 0) goto L_0x0116
                r14 = 0
            L_0x0061:
                int r16 = (r14 > r7 ? 1 : (r14 == r7 ? 0 : -1))
                r17 = r6
                r5 = 1
                if (r16 == 0) goto L_0x00cc
                boolean r9 = r1.cancelled
                if (r9 == 0) goto L_0x0071
                r20.cancelAll()
                return
            L_0x0071:
                io.reactivex.internal.util.ErrorMode r9 = io.reactivex.internal.util.ErrorMode.IMMEDIATE
                if (r4 != r9) goto L_0x0091
                io.reactivex.internal.util.AtomicThrowable r9 = r1.errors
                java.lang.Object r9 = r9.get()
                java.lang.Throwable r9 = (java.lang.Throwable) r9
                if (r9 == 0) goto L_0x0091
                r1.current = r12
                r2.cancel()
                r20.cancelAll()
                io.reactivex.internal.util.AtomicThrowable r2 = r1.errors
                java.lang.Throwable r2 = r2.terminate()
                r3.onError(r2)
                return
            L_0x0091:
                boolean r9 = r2.isDone()
                java.lang.Object r10 = r13.poll()     // Catch:{ Throwable -> 0x00bc }
                if (r10 != 0) goto L_0x009d
                r11 = 1
                goto L_0x009e
            L_0x009d:
                r11 = 0
            L_0x009e:
                if (r9 == 0) goto L_0x00ad
                if (r11 == 0) goto L_0x00ad
                r1.current = r12
                org.reactivestreams.Subscription r2 = r1.s
                r2.request(r5)
                r2 = r12
                r18 = 1
                goto L_0x00ce
            L_0x00ad:
                if (r11 == 0) goto L_0x00b0
                goto L_0x00cc
            L_0x00b0:
                r3.onNext(r10)
                long r9 = r14 + r5
                r2.requestOne()
                r14 = r9
                r6 = r17
                goto L_0x0061
            L_0x00bc:
                r0 = move-exception
                io.reactivex.exceptions.a.b(r0)
                r1.current = r12
                r2.cancel()
                r20.cancelAll()
                r3.onError(r0)
                return
            L_0x00cc:
                r18 = 0
            L_0x00ce:
                int r9 = (r14 > r7 ? 1 : (r14 == r7 ? 0 : -1))
                if (r9 != 0) goto L_0x0113
                boolean r9 = r1.cancelled
                if (r9 == 0) goto L_0x00da
                r20.cancelAll()
                return
            L_0x00da:
                io.reactivex.internal.util.ErrorMode r9 = io.reactivex.internal.util.ErrorMode.IMMEDIATE
                if (r4 != r9) goto L_0x00fa
                io.reactivex.internal.util.AtomicThrowable r9 = r1.errors
                java.lang.Object r9 = r9.get()
                java.lang.Throwable r9 = (java.lang.Throwable) r9
                if (r9 == 0) goto L_0x00fa
                r1.current = r12
                r2.cancel()
                r20.cancelAll()
                io.reactivex.internal.util.AtomicThrowable r2 = r1.errors
                java.lang.Throwable r2 = r2.terminate()
                r3.onError(r2)
                return
            L_0x00fa:
                boolean r9 = r2.isDone()
                boolean r10 = r13.isEmpty()
                if (r9 == 0) goto L_0x0113
                if (r10 == 0) goto L_0x0113
                r1.current = r12
                org.reactivestreams.Subscription r2 = r1.s
                r2.request(r5)
                r2 = r12
                r5 = 0
                r18 = 1
                goto L_0x011e
            L_0x0113:
                r5 = 0
                goto L_0x011e
            L_0x0116:
                r17 = r6
                r5 = 0
                r14 = 0
                r18 = 0
            L_0x011e:
                int r9 = (r14 > r5 ? 1 : (r14 == r5 ? 0 : -1))
                if (r9 == 0) goto L_0x0131
                r5 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                int r9 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
                if (r9 == 0) goto L_0x0131
                java.util.concurrent.atomic.AtomicLong r5 = r1.requested
                long r6 = -r14
                r5.addAndGet(r6)
            L_0x0131:
                if (r18 == 0) goto L_0x0137
                r6 = r17
                goto L_0x0010
            L_0x0137:
                r5 = r17
                int r5 = -r5
                int r6 = r1.addAndGet(r5)
                if (r6 != 0) goto L_0x0010
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.flowable.FlowableConcatMapEager.ConcatMapEagerDelayErrorSubscriber.drain():void");
        }
    }

    /* access modifiers changed from: protected */
    public void a(org.reactivestreams.c<? super R> cVar) {
        io.reactivex.g gVar = this.b;
        ConcatMapEagerDelayErrorSubscriber concatMapEagerDelayErrorSubscriber = new ConcatMapEagerDelayErrorSubscriber(cVar, this.c, this.d, this.e, this.f);
        gVar.a((j<? super T>) concatMapEagerDelayErrorSubscriber);
    }
}
