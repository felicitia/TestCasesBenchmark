package io.reactivex.internal.operators.flowable;

import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.functions.c;
import io.reactivex.functions.g;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.j;
import io.reactivex.processors.UnicastProcessor;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;
import org.reactivestreams.b;

public final class FlowableGroupJoin<TLeft, TRight, TLeftEnd, TRightEnd, R> extends a<TLeft, R> {
    final b<? extends TRight> c;
    final g<? super TLeft, ? extends b<TLeftEnd>> d;
    final g<? super TRight, ? extends b<TRightEnd>> e;
    final c<? super TLeft, ? super io.reactivex.g<TRight>, ? extends R> f;

    static final class GroupJoinSubscription<TLeft, TRight, TLeftEnd, TRightEnd, R> extends AtomicInteger implements a, Subscription {
        static final Integer LEFT_CLOSE = Integer.valueOf(3);
        static final Integer LEFT_VALUE = Integer.valueOf(1);
        static final Integer RIGHT_CLOSE = Integer.valueOf(4);
        static final Integer RIGHT_VALUE = Integer.valueOf(2);
        private static final long serialVersionUID = -6071216598687999801L;
        final AtomicInteger active;
        final org.reactivestreams.c<? super R> actual;
        volatile boolean cancelled;
        final io.reactivex.disposables.a disposables = new io.reactivex.disposables.a();
        final AtomicReference<Throwable> error = new AtomicReference<>();
        final g<? super TLeft, ? extends b<TLeftEnd>> leftEnd;
        int leftIndex;
        final Map<Integer, UnicastProcessor<TRight>> lefts = new LinkedHashMap();
        final io.reactivex.internal.queue.a<Object> queue = new io.reactivex.internal.queue.a<>(io.reactivex.g.a());
        final AtomicLong requested = new AtomicLong();
        final c<? super TLeft, ? super io.reactivex.g<TRight>, ? extends R> resultSelector;
        final g<? super TRight, ? extends b<TRightEnd>> rightEnd;
        int rightIndex;
        final Map<Integer, TRight> rights = new LinkedHashMap();

        GroupJoinSubscription(org.reactivestreams.c<? super R> cVar, g<? super TLeft, ? extends b<TLeftEnd>> gVar, g<? super TRight, ? extends b<TRightEnd>> gVar2, c<? super TLeft, ? super io.reactivex.g<TRight>, ? extends R> cVar2) {
            this.actual = cVar;
            this.leftEnd = gVar;
            this.rightEnd = gVar2;
            this.resultSelector = cVar2;
            this.active = new AtomicInteger(2);
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                io.reactivex.internal.util.b.a(this.requested, j);
            }
        }

        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                cancelAll();
                if (getAndIncrement() == 0) {
                    this.queue.clear();
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void cancelAll() {
            this.disposables.dispose();
        }

        /* access modifiers changed from: 0000 */
        public void errorAll(org.reactivestreams.c<?> cVar) {
            Throwable a = ExceptionHelper.a(this.error);
            for (UnicastProcessor onError : this.lefts.values()) {
                onError.onError(a);
            }
            this.lefts.clear();
            this.rights.clear();
            cVar.onError(a);
        }

        /* access modifiers changed from: 0000 */
        public void fail(Throwable th, org.reactivestreams.c<?> cVar, io.reactivex.internal.a.g<?> gVar) {
            io.reactivex.exceptions.a.b(th);
            ExceptionHelper.a(this.error, th);
            gVar.clear();
            cancelAll();
            errorAll(cVar);
        }

        /* access modifiers changed from: 0000 */
        public void drain() {
            if (getAndIncrement() == 0) {
                io.reactivex.internal.queue.a<Object> aVar = this.queue;
                org.reactivestreams.c<? super R> cVar = this.actual;
                int i = 1;
                while (!this.cancelled) {
                    if (((Throwable) this.error.get()) != null) {
                        aVar.clear();
                        cancelAll();
                        errorAll(cVar);
                        return;
                    }
                    boolean z = this.active.get() == 0;
                    Integer num = (Integer) aVar.poll();
                    boolean z2 = num == null;
                    if (z && z2) {
                        for (UnicastProcessor onComplete : this.lefts.values()) {
                            onComplete.onComplete();
                        }
                        this.lefts.clear();
                        this.rights.clear();
                        this.disposables.dispose();
                        cVar.onComplete();
                        return;
                    } else if (z2) {
                        i = addAndGet(-i);
                        if (i == 0) {
                            return;
                        }
                    } else {
                        Object poll = aVar.poll();
                        if (num == LEFT_VALUE) {
                            UnicastProcessor f = UnicastProcessor.f();
                            int i2 = this.leftIndex;
                            this.leftIndex = i2 + 1;
                            this.lefts.put(Integer.valueOf(i2), f);
                            try {
                                b bVar = (b) io.reactivex.internal.functions.a.a(this.leftEnd.apply(poll), "The leftEnd returned a null Publisher");
                                LeftRightEndSubscriber leftRightEndSubscriber = new LeftRightEndSubscriber(this, true, i2);
                                this.disposables.a((Disposable) leftRightEndSubscriber);
                                bVar.subscribe(leftRightEndSubscriber);
                                if (((Throwable) this.error.get()) != null) {
                                    aVar.clear();
                                    cancelAll();
                                    errorAll(cVar);
                                    return;
                                }
                                try {
                                    Object a = io.reactivex.internal.functions.a.a(this.resultSelector.apply(poll, f), "The resultSelector returned a null value");
                                    if (this.requested.get() != 0) {
                                        cVar.onNext(a);
                                        io.reactivex.internal.util.b.c(this.requested, 1);
                                        for (Object onNext : this.rights.values()) {
                                            f.onNext(onNext);
                                        }
                                    } else {
                                        fail(new MissingBackpressureException("Could not emit value due to lack of requests"), cVar, aVar);
                                        return;
                                    }
                                } catch (Throwable th) {
                                    fail(th, cVar, aVar);
                                    return;
                                }
                            } catch (Throwable th2) {
                                fail(th2, cVar, aVar);
                                return;
                            }
                        } else if (num == RIGHT_VALUE) {
                            int i3 = this.rightIndex;
                            this.rightIndex = i3 + 1;
                            this.rights.put(Integer.valueOf(i3), poll);
                            try {
                                b bVar2 = (b) io.reactivex.internal.functions.a.a(this.rightEnd.apply(poll), "The rightEnd returned a null Publisher");
                                LeftRightEndSubscriber leftRightEndSubscriber2 = new LeftRightEndSubscriber(this, false, i3);
                                this.disposables.a((Disposable) leftRightEndSubscriber2);
                                bVar2.subscribe(leftRightEndSubscriber2);
                                if (((Throwable) this.error.get()) != null) {
                                    aVar.clear();
                                    cancelAll();
                                    errorAll(cVar);
                                    return;
                                }
                                for (UnicastProcessor onNext2 : this.lefts.values()) {
                                    onNext2.onNext(poll);
                                }
                            } catch (Throwable th3) {
                                fail(th3, cVar, aVar);
                                return;
                            }
                        } else if (num == LEFT_CLOSE) {
                            LeftRightEndSubscriber leftRightEndSubscriber3 = (LeftRightEndSubscriber) poll;
                            UnicastProcessor unicastProcessor = (UnicastProcessor) this.lefts.remove(Integer.valueOf(leftRightEndSubscriber3.index));
                            this.disposables.b(leftRightEndSubscriber3);
                            if (unicastProcessor != null) {
                                unicastProcessor.onComplete();
                            }
                        } else if (num == RIGHT_CLOSE) {
                            LeftRightEndSubscriber leftRightEndSubscriber4 = (LeftRightEndSubscriber) poll;
                            this.rights.remove(Integer.valueOf(leftRightEndSubscriber4.index));
                            this.disposables.b(leftRightEndSubscriber4);
                        }
                    }
                }
                aVar.clear();
            }
        }

        public void innerError(Throwable th) {
            if (ExceptionHelper.a(this.error, th)) {
                this.active.decrementAndGet();
                drain();
                return;
            }
            io.reactivex.d.a.a(th);
        }

        public void innerComplete(LeftRightSubscriber leftRightSubscriber) {
            this.disposables.c(leftRightSubscriber);
            this.active.decrementAndGet();
            drain();
        }

        public void innerValue(boolean z, Object obj) {
            synchronized (this) {
                this.queue.a(z ? LEFT_VALUE : RIGHT_VALUE, obj);
            }
            drain();
        }

        public void innerClose(boolean z, LeftRightEndSubscriber leftRightEndSubscriber) {
            synchronized (this) {
                this.queue.a(z ? LEFT_CLOSE : RIGHT_CLOSE, leftRightEndSubscriber);
            }
            drain();
        }

        public void innerCloseError(Throwable th) {
            if (ExceptionHelper.a(this.error, th)) {
                drain();
            } else {
                io.reactivex.d.a.a(th);
            }
        }
    }

    static final class LeftRightEndSubscriber extends AtomicReference<Subscription> implements Disposable, j<Object> {
        private static final long serialVersionUID = 1883890389173668373L;
        final int index;
        final boolean isLeft;
        final a parent;

        LeftRightEndSubscriber(a aVar, boolean z, int i) {
            this.parent = aVar;
            this.isLeft = z;
            this.index = i;
        }

        public void dispose() {
            SubscriptionHelper.cancel(this);
        }

        public boolean isDisposed() {
            return SubscriptionHelper.isCancelled((Subscription) get());
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.setOnce(this, subscription)) {
                subscription.request(Long.MAX_VALUE);
            }
        }

        public void onNext(Object obj) {
            if (SubscriptionHelper.cancel(this)) {
                this.parent.innerClose(this.isLeft, this);
            }
        }

        public void onError(Throwable th) {
            this.parent.innerCloseError(th);
        }

        public void onComplete() {
            this.parent.innerClose(this.isLeft, this);
        }
    }

    static final class LeftRightSubscriber extends AtomicReference<Subscription> implements Disposable, j<Object> {
        private static final long serialVersionUID = 1883890389173668373L;
        final boolean isLeft;
        final a parent;

        LeftRightSubscriber(a aVar, boolean z) {
            this.parent = aVar;
            this.isLeft = z;
        }

        public void dispose() {
            SubscriptionHelper.cancel(this);
        }

        public boolean isDisposed() {
            return SubscriptionHelper.isCancelled((Subscription) get());
        }

        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.setOnce(this, subscription)) {
                subscription.request(Long.MAX_VALUE);
            }
        }

        public void onNext(Object obj) {
            this.parent.innerValue(this.isLeft, obj);
        }

        public void onError(Throwable th) {
            this.parent.innerError(th);
        }

        public void onComplete() {
            this.parent.innerComplete(this);
        }
    }

    interface a {
        void innerClose(boolean z, LeftRightEndSubscriber leftRightEndSubscriber);

        void innerCloseError(Throwable th);

        void innerComplete(LeftRightSubscriber leftRightSubscriber);

        void innerError(Throwable th);

        void innerValue(boolean z, Object obj);
    }

    /* access modifiers changed from: protected */
    public void a(org.reactivestreams.c<? super R> cVar) {
        GroupJoinSubscription groupJoinSubscription = new GroupJoinSubscription(cVar, this.d, this.e, this.f);
        cVar.onSubscribe(groupJoinSubscription);
        LeftRightSubscriber leftRightSubscriber = new LeftRightSubscriber(groupJoinSubscription, true);
        groupJoinSubscription.disposables.a((Disposable) leftRightSubscriber);
        LeftRightSubscriber leftRightSubscriber2 = new LeftRightSubscriber(groupJoinSubscription, false);
        groupJoinSubscription.disposables.a((Disposable) leftRightSubscriber2);
        this.b.a((j<? super T>) leftRightSubscriber);
        this.c.subscribe(leftRightSubscriber2);
    }
}
