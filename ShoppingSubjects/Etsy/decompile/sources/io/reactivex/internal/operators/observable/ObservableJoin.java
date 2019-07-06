package io.reactivex.internal.operators.observable;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.a;
import io.reactivex.functions.c;
import io.reactivex.functions.g;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.q;
import io.reactivex.t;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableJoin<TLeft, TRight, TLeftEnd, TRightEnd, R> extends a<TLeft, R> {
    final t<? extends TRight> b;
    final g<? super TLeft, ? extends t<TLeftEnd>> c;
    final g<? super TRight, ? extends t<TRightEnd>> d;
    final c<? super TLeft, ? super TRight, ? extends R> e;

    static final class JoinDisposable<TLeft, TRight, TLeftEnd, TRightEnd, R> extends AtomicInteger implements Disposable, a {
        static final Integer LEFT_CLOSE = Integer.valueOf(3);
        static final Integer LEFT_VALUE = Integer.valueOf(1);
        static final Integer RIGHT_CLOSE = Integer.valueOf(4);
        static final Integer RIGHT_VALUE = Integer.valueOf(2);
        private static final long serialVersionUID = -6071216598687999801L;
        final AtomicInteger active;
        final Observer<? super R> actual;
        volatile boolean cancelled;
        final a disposables = new a();
        final AtomicReference<Throwable> error = new AtomicReference<>();
        final g<? super TLeft, ? extends t<TLeftEnd>> leftEnd;
        int leftIndex;
        final Map<Integer, TLeft> lefts = new LinkedHashMap();
        final io.reactivex.internal.queue.a<Object> queue = new io.reactivex.internal.queue.a<>(q.d());
        final c<? super TLeft, ? super TRight, ? extends R> resultSelector;
        final g<? super TRight, ? extends t<TRightEnd>> rightEnd;
        int rightIndex;
        final Map<Integer, TRight> rights = new LinkedHashMap();

        JoinDisposable(Observer<? super R> observer, g<? super TLeft, ? extends t<TLeftEnd>> gVar, g<? super TRight, ? extends t<TRightEnd>> gVar2, c<? super TLeft, ? super TRight, ? extends R> cVar) {
            this.actual = observer;
            this.leftEnd = gVar;
            this.rightEnd = gVar2;
            this.resultSelector = cVar;
            this.active = new AtomicInteger(2);
        }

        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                cancelAll();
                if (getAndIncrement() == 0) {
                    this.queue.clear();
                }
            }
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        /* access modifiers changed from: 0000 */
        public void cancelAll() {
            this.disposables.dispose();
        }

        /* access modifiers changed from: 0000 */
        public void errorAll(Observer<?> observer) {
            Throwable a = ExceptionHelper.a(this.error);
            this.lefts.clear();
            this.rights.clear();
            observer.onError(a);
        }

        /* access modifiers changed from: 0000 */
        public void fail(Throwable th, Observer<?> observer, io.reactivex.internal.queue.a<?> aVar) {
            io.reactivex.exceptions.a.b(th);
            ExceptionHelper.a(this.error, th);
            aVar.clear();
            cancelAll();
            errorAll(observer);
        }

        /* access modifiers changed from: 0000 */
        public void drain() {
            if (getAndIncrement() == 0) {
                io.reactivex.internal.queue.a<Object> aVar = this.queue;
                Observer<? super R> observer = this.actual;
                int i = 1;
                while (!this.cancelled) {
                    if (((Throwable) this.error.get()) != null) {
                        aVar.clear();
                        cancelAll();
                        errorAll(observer);
                        return;
                    }
                    boolean z = this.active.get() == 0;
                    Integer num = (Integer) aVar.poll();
                    boolean z2 = num == null;
                    if (z && z2) {
                        this.lefts.clear();
                        this.rights.clear();
                        this.disposables.dispose();
                        observer.onComplete();
                        return;
                    } else if (z2) {
                        i = addAndGet(-i);
                        if (i == 0) {
                            return;
                        }
                    } else {
                        Object poll = aVar.poll();
                        if (num == LEFT_VALUE) {
                            int i2 = this.leftIndex;
                            this.leftIndex = i2 + 1;
                            this.lefts.put(Integer.valueOf(i2), poll);
                            try {
                                t tVar = (t) io.reactivex.internal.functions.a.a(this.leftEnd.apply(poll), "The leftEnd returned a null ObservableSource");
                                LeftRightEndObserver leftRightEndObserver = new LeftRightEndObserver(this, true, i2);
                                this.disposables.a((Disposable) leftRightEndObserver);
                                tVar.subscribe(leftRightEndObserver);
                                if (((Throwable) this.error.get()) != null) {
                                    aVar.clear();
                                    cancelAll();
                                    errorAll(observer);
                                    return;
                                }
                                for (Object apply : this.rights.values()) {
                                    try {
                                        observer.onNext(io.reactivex.internal.functions.a.a(this.resultSelector.apply(poll, apply), "The resultSelector returned a null value"));
                                    } catch (Throwable th) {
                                        fail(th, observer, aVar);
                                        return;
                                    }
                                }
                            } catch (Throwable th2) {
                                fail(th2, observer, aVar);
                                return;
                            }
                        } else if (num == RIGHT_VALUE) {
                            int i3 = this.rightIndex;
                            this.rightIndex = i3 + 1;
                            this.rights.put(Integer.valueOf(i3), poll);
                            try {
                                t tVar2 = (t) io.reactivex.internal.functions.a.a(this.rightEnd.apply(poll), "The rightEnd returned a null ObservableSource");
                                LeftRightEndObserver leftRightEndObserver2 = new LeftRightEndObserver(this, false, i3);
                                this.disposables.a((Disposable) leftRightEndObserver2);
                                tVar2.subscribe(leftRightEndObserver2);
                                if (((Throwable) this.error.get()) != null) {
                                    aVar.clear();
                                    cancelAll();
                                    errorAll(observer);
                                    return;
                                }
                                for (Object apply2 : this.lefts.values()) {
                                    try {
                                        observer.onNext(io.reactivex.internal.functions.a.a(this.resultSelector.apply(apply2, poll), "The resultSelector returned a null value"));
                                    } catch (Throwable th3) {
                                        fail(th3, observer, aVar);
                                        return;
                                    }
                                }
                            } catch (Throwable th4) {
                                fail(th4, observer, aVar);
                                return;
                            }
                        } else if (num == LEFT_CLOSE) {
                            LeftRightEndObserver leftRightEndObserver3 = (LeftRightEndObserver) poll;
                            this.lefts.remove(Integer.valueOf(leftRightEndObserver3.index));
                            this.disposables.b(leftRightEndObserver3);
                        } else {
                            LeftRightEndObserver leftRightEndObserver4 = (LeftRightEndObserver) poll;
                            this.rights.remove(Integer.valueOf(leftRightEndObserver4.index));
                            this.disposables.b(leftRightEndObserver4);
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

        public void innerComplete(LeftRightObserver leftRightObserver) {
            this.disposables.c(leftRightObserver);
            this.active.decrementAndGet();
            drain();
        }

        public void innerValue(boolean z, Object obj) {
            synchronized (this) {
                this.queue.a(z ? LEFT_VALUE : RIGHT_VALUE, obj);
            }
            drain();
        }

        public void innerClose(boolean z, LeftRightEndObserver leftRightEndObserver) {
            synchronized (this) {
                this.queue.a(z ? LEFT_CLOSE : RIGHT_CLOSE, leftRightEndObserver);
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

    /* access modifiers changed from: protected */
    public void a(Observer<? super R> observer) {
        JoinDisposable joinDisposable = new JoinDisposable(observer, this.c, this.d, this.e);
        observer.onSubscribe(joinDisposable);
        LeftRightObserver leftRightObserver = new LeftRightObserver(joinDisposable, true);
        joinDisposable.disposables.a((Disposable) leftRightObserver);
        LeftRightObserver leftRightObserver2 = new LeftRightObserver(joinDisposable, false);
        joinDisposable.disposables.a((Disposable) leftRightObserver2);
        this.a.subscribe(leftRightObserver);
        this.b.subscribe(leftRightObserver2);
    }
}
