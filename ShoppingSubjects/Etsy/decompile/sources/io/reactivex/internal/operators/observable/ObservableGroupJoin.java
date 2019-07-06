package io.reactivex.internal.operators.observable;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.c;
import io.reactivex.functions.g;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.q;
import io.reactivex.subjects.UnicastSubject;
import io.reactivex.t;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableGroupJoin<TLeft, TRight, TLeftEnd, TRightEnd, R> extends a<TLeft, R> {
    final t<? extends TRight> b;
    final g<? super TLeft, ? extends t<TLeftEnd>> c;
    final g<? super TRight, ? extends t<TRightEnd>> d;
    final c<? super TLeft, ? super q<TRight>, ? extends R> e;

    static final class GroupJoinDisposable<TLeft, TRight, TLeftEnd, TRightEnd, R> extends AtomicInteger implements Disposable, a {
        static final Integer LEFT_CLOSE = Integer.valueOf(3);
        static final Integer LEFT_VALUE = Integer.valueOf(1);
        static final Integer RIGHT_CLOSE = Integer.valueOf(4);
        static final Integer RIGHT_VALUE = Integer.valueOf(2);
        private static final long serialVersionUID = -6071216598687999801L;
        final AtomicInteger active;
        final Observer<? super R> actual;
        volatile boolean cancelled;
        final io.reactivex.disposables.a disposables = new io.reactivex.disposables.a();
        final AtomicReference<Throwable> error = new AtomicReference<>();
        final g<? super TLeft, ? extends t<TLeftEnd>> leftEnd;
        int leftIndex;
        final Map<Integer, UnicastSubject<TRight>> lefts = new LinkedHashMap();
        final io.reactivex.internal.queue.a<Object> queue = new io.reactivex.internal.queue.a<>(q.d());
        final c<? super TLeft, ? super q<TRight>, ? extends R> resultSelector;
        final g<? super TRight, ? extends t<TRightEnd>> rightEnd;
        int rightIndex;
        final Map<Integer, TRight> rights = new LinkedHashMap();

        GroupJoinDisposable(Observer<? super R> observer, g<? super TLeft, ? extends t<TLeftEnd>> gVar, g<? super TRight, ? extends t<TRightEnd>> gVar2, c<? super TLeft, ? super q<TRight>, ? extends R> cVar) {
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
            for (UnicastSubject onError : this.lefts.values()) {
                onError.onError(a);
            }
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
                        for (UnicastSubject onComplete : this.lefts.values()) {
                            onComplete.onComplete();
                        }
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
                            UnicastSubject a = UnicastSubject.a();
                            int i2 = this.leftIndex;
                            this.leftIndex = i2 + 1;
                            this.lefts.put(Integer.valueOf(i2), a);
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
                                try {
                                    observer.onNext(io.reactivex.internal.functions.a.a(this.resultSelector.apply(poll, a), "The resultSelector returned a null value"));
                                    for (Object onNext : this.rights.values()) {
                                        a.onNext(onNext);
                                    }
                                } catch (Throwable th) {
                                    fail(th, observer, aVar);
                                    return;
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
                                for (UnicastSubject onNext2 : this.lefts.values()) {
                                    onNext2.onNext(poll);
                                }
                            } catch (Throwable th3) {
                                fail(th3, observer, aVar);
                                return;
                            }
                        } else if (num == LEFT_CLOSE) {
                            LeftRightEndObserver leftRightEndObserver3 = (LeftRightEndObserver) poll;
                            UnicastSubject unicastSubject = (UnicastSubject) this.lefts.remove(Integer.valueOf(leftRightEndObserver3.index));
                            this.disposables.b(leftRightEndObserver3);
                            if (unicastSubject != null) {
                                unicastSubject.onComplete();
                            }
                        } else if (num == RIGHT_CLOSE) {
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

    static final class LeftRightEndObserver extends AtomicReference<Disposable> implements Observer<Object>, Disposable {
        private static final long serialVersionUID = 1883890389173668373L;
        final int index;
        final boolean isLeft;
        final a parent;

        LeftRightEndObserver(a aVar, boolean z, int i) {
            this.parent = aVar;
            this.isLeft = z;
            this.index = i;
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((Disposable) get());
        }

        public void onSubscribe(Disposable disposable) {
            DisposableHelper.setOnce(this, disposable);
        }

        public void onNext(Object obj) {
            if (DisposableHelper.dispose(this)) {
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

    static final class LeftRightObserver extends AtomicReference<Disposable> implements Observer<Object>, Disposable {
        private static final long serialVersionUID = 1883890389173668373L;
        final boolean isLeft;
        final a parent;

        LeftRightObserver(a aVar, boolean z) {
            this.parent = aVar;
            this.isLeft = z;
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((Disposable) get());
        }

        public void onSubscribe(Disposable disposable) {
            DisposableHelper.setOnce(this, disposable);
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
        void innerClose(boolean z, LeftRightEndObserver leftRightEndObserver);

        void innerCloseError(Throwable th);

        void innerComplete(LeftRightObserver leftRightObserver);

        void innerError(Throwable th);

        void innerValue(boolean z, Object obj);
    }

    /* access modifiers changed from: protected */
    public void a(Observer<? super R> observer) {
        GroupJoinDisposable groupJoinDisposable = new GroupJoinDisposable(observer, this.c, this.d, this.e);
        observer.onSubscribe(groupJoinDisposable);
        LeftRightObserver leftRightObserver = new LeftRightObserver(groupJoinDisposable, true);
        groupJoinDisposable.disposables.a((Disposable) leftRightObserver);
        LeftRightObserver leftRightObserver2 = new LeftRightObserver(groupJoinDisposable, false);
        groupJoinDisposable.disposables.a((Disposable) leftRightObserver2);
        this.a.subscribe(leftRightObserver);
        this.b.subscribe(leftRightObserver2);
    }
}
