package io.reactivex.internal.operators.observable;

import io.reactivex.Observer;
import io.reactivex.d.a;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.g;
import io.reactivex.internal.a.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.ErrorMode;
import io.reactivex.t;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableConcatMap<T, U> extends a<T, U> {
    final g<? super T, ? extends t<? extends U>> b;
    final int c;
    final ErrorMode d;

    static final class ConcatMapDelayErrorObserver<T, R> extends AtomicInteger implements Observer<T>, Disposable {
        private static final long serialVersionUID = -6951100001833242599L;
        volatile boolean active;
        final Observer<? super R> actual;
        final int bufferSize;
        volatile boolean cancelled;
        Disposable d;
        volatile boolean done;
        final AtomicThrowable error = new AtomicThrowable();
        final g<? super T, ? extends t<? extends R>> mapper;
        final DelayErrorInnerObserver<R> observer;
        io.reactivex.internal.a.g<T> queue;
        int sourceMode;
        final boolean tillTheEnd;

        static final class DelayErrorInnerObserver<R> extends AtomicReference<Disposable> implements Observer<R> {
            private static final long serialVersionUID = 2620149119579502636L;
            final Observer<? super R> actual;
            final ConcatMapDelayErrorObserver<?, R> parent;

            DelayErrorInnerObserver(Observer<? super R> observer, ConcatMapDelayErrorObserver<?, R> concatMapDelayErrorObserver) {
                this.actual = observer;
                this.parent = concatMapDelayErrorObserver;
            }

            public void onSubscribe(Disposable disposable) {
                DisposableHelper.replace(this, disposable);
            }

            public void onNext(R r) {
                this.actual.onNext(r);
            }

            public void onError(Throwable th) {
                ConcatMapDelayErrorObserver<?, R> concatMapDelayErrorObserver = this.parent;
                if (concatMapDelayErrorObserver.error.addThrowable(th)) {
                    if (!concatMapDelayErrorObserver.tillTheEnd) {
                        concatMapDelayErrorObserver.d.dispose();
                    }
                    concatMapDelayErrorObserver.active = false;
                    concatMapDelayErrorObserver.drain();
                    return;
                }
                a.a(th);
            }

            public void onComplete() {
                ConcatMapDelayErrorObserver<?, R> concatMapDelayErrorObserver = this.parent;
                concatMapDelayErrorObserver.active = false;
                concatMapDelayErrorObserver.drain();
            }

            /* access modifiers changed from: 0000 */
            public void dispose() {
                DisposableHelper.dispose(this);
            }
        }

        ConcatMapDelayErrorObserver(Observer<? super R> observer2, g<? super T, ? extends t<? extends R>> gVar, int i, boolean z) {
            this.actual = observer2;
            this.mapper = gVar;
            this.bufferSize = i;
            this.tillTheEnd = z;
            this.observer = new DelayErrorInnerObserver<>(observer2, this);
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.d, disposable)) {
                this.d = disposable;
                if (disposable instanceof b) {
                    b bVar = (b) disposable;
                    int requestFusion = bVar.requestFusion(3);
                    if (requestFusion == 1) {
                        this.sourceMode = requestFusion;
                        this.queue = bVar;
                        this.done = true;
                        this.actual.onSubscribe(this);
                        drain();
                        return;
                    } else if (requestFusion == 2) {
                        this.sourceMode = requestFusion;
                        this.queue = bVar;
                        this.actual.onSubscribe(this);
                        return;
                    }
                }
                this.queue = new io.reactivex.internal.queue.a(this.bufferSize);
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            if (this.sourceMode == 0) {
                this.queue.offer(t);
            }
            drain();
        }

        public void onError(Throwable th) {
            if (this.error.addThrowable(th)) {
                this.done = true;
                drain();
                return;
            }
            a.a(th);
        }

        public void onComplete() {
            this.done = true;
            drain();
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        public void dispose() {
            this.cancelled = true;
            this.d.dispose();
            this.observer.dispose();
        }

        /* access modifiers changed from: 0000 */
        public void drain() {
            if (getAndIncrement() == 0) {
                Observer<? super R> observer2 = this.actual;
                io.reactivex.internal.a.g<T> gVar = this.queue;
                AtomicThrowable atomicThrowable = this.error;
                while (true) {
                    if (!this.active) {
                        if (this.cancelled) {
                            gVar.clear();
                            return;
                        } else if (this.tillTheEnd || ((Throwable) atomicThrowable.get()) == null) {
                            boolean z = this.done;
                            try {
                                Object poll = gVar.poll();
                                boolean z2 = poll == null;
                                if (z && z2) {
                                    this.cancelled = true;
                                    Throwable terminate = atomicThrowable.terminate();
                                    if (terminate != null) {
                                        observer2.onError(terminate);
                                    } else {
                                        observer2.onComplete();
                                    }
                                    return;
                                } else if (!z2) {
                                    try {
                                        t tVar = (t) io.reactivex.internal.functions.a.a(this.mapper.apply(poll), "The mapper returned a null ObservableSource");
                                        if (tVar instanceof Callable) {
                                            try {
                                                Object call = ((Callable) tVar).call();
                                                if (call != null && !this.cancelled) {
                                                    observer2.onNext(call);
                                                }
                                            } catch (Throwable th) {
                                                io.reactivex.exceptions.a.b(th);
                                                atomicThrowable.addThrowable(th);
                                            }
                                        } else {
                                            this.active = true;
                                            tVar.subscribe(this.observer);
                                        }
                                    } catch (Throwable th2) {
                                        io.reactivex.exceptions.a.b(th2);
                                        this.cancelled = true;
                                        this.d.dispose();
                                        gVar.clear();
                                        atomicThrowable.addThrowable(th2);
                                        observer2.onError(atomicThrowable.terminate());
                                        return;
                                    }
                                }
                            } catch (Throwable th3) {
                                io.reactivex.exceptions.a.b(th3);
                                this.cancelled = true;
                                this.d.dispose();
                                atomicThrowable.addThrowable(th3);
                                observer2.onError(atomicThrowable.terminate());
                                return;
                            }
                        } else {
                            gVar.clear();
                            this.cancelled = true;
                            observer2.onError(atomicThrowable.terminate());
                            return;
                        }
                    }
                    if (decrementAndGet() == 0) {
                        return;
                    }
                }
            }
        }
    }

    static final class SourceObserver<T, U> extends AtomicInteger implements Observer<T>, Disposable {
        private static final long serialVersionUID = 8828587559905699186L;
        volatile boolean active;
        final Observer<? super U> actual;
        final int bufferSize;
        volatile boolean disposed;
        volatile boolean done;
        int fusionMode;
        final InnerObserver<U> inner;
        final g<? super T, ? extends t<? extends U>> mapper;
        io.reactivex.internal.a.g<T> queue;
        Disposable s;

        static final class InnerObserver<U> extends AtomicReference<Disposable> implements Observer<U> {
            private static final long serialVersionUID = -7449079488798789337L;
            final Observer<? super U> actual;
            final SourceObserver<?, ?> parent;

            InnerObserver(Observer<? super U> observer, SourceObserver<?, ?> sourceObserver) {
                this.actual = observer;
                this.parent = sourceObserver;
            }

            public void onSubscribe(Disposable disposable) {
                DisposableHelper.set(this, disposable);
            }

            public void onNext(U u) {
                this.actual.onNext(u);
            }

            public void onError(Throwable th) {
                this.parent.dispose();
                this.actual.onError(th);
            }

            public void onComplete() {
                this.parent.innerComplete();
            }

            /* access modifiers changed from: 0000 */
            public void dispose() {
                DisposableHelper.dispose(this);
            }
        }

        SourceObserver(Observer<? super U> observer, g<? super T, ? extends t<? extends U>> gVar, int i) {
            this.actual = observer;
            this.mapper = gVar;
            this.bufferSize = i;
            this.inner = new InnerObserver<>(observer, this);
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.s, disposable)) {
                this.s = disposable;
                if (disposable instanceof b) {
                    b bVar = (b) disposable;
                    int requestFusion = bVar.requestFusion(3);
                    if (requestFusion == 1) {
                        this.fusionMode = requestFusion;
                        this.queue = bVar;
                        this.done = true;
                        this.actual.onSubscribe(this);
                        drain();
                        return;
                    } else if (requestFusion == 2) {
                        this.fusionMode = requestFusion;
                        this.queue = bVar;
                        this.actual.onSubscribe(this);
                        return;
                    }
                }
                this.queue = new io.reactivex.internal.queue.a(this.bufferSize);
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                if (this.fusionMode == 0) {
                    this.queue.offer(t);
                }
                drain();
            }
        }

        public void onError(Throwable th) {
            if (this.done) {
                a.a(th);
                return;
            }
            this.done = true;
            dispose();
            this.actual.onError(th);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                drain();
            }
        }

        /* access modifiers changed from: 0000 */
        public void innerComplete() {
            this.active = false;
            drain();
        }

        public boolean isDisposed() {
            return this.disposed;
        }

        public void dispose() {
            this.disposed = true;
            this.inner.dispose();
            this.s.dispose();
            if (getAndIncrement() == 0) {
                this.queue.clear();
            }
        }

        /* access modifiers changed from: 0000 */
        public void drain() {
            if (getAndIncrement() == 0) {
                while (!this.disposed) {
                    if (!this.active) {
                        boolean z = this.done;
                        try {
                            Object poll = this.queue.poll();
                            boolean z2 = poll == null;
                            if (z && z2) {
                                this.disposed = true;
                                this.actual.onComplete();
                                return;
                            } else if (!z2) {
                                try {
                                    t tVar = (t) io.reactivex.internal.functions.a.a(this.mapper.apply(poll), "The mapper returned a null ObservableSource");
                                    this.active = true;
                                    tVar.subscribe(this.inner);
                                } catch (Throwable th) {
                                    io.reactivex.exceptions.a.b(th);
                                    dispose();
                                    this.queue.clear();
                                    this.actual.onError(th);
                                    return;
                                }
                            }
                        } catch (Throwable th2) {
                            io.reactivex.exceptions.a.b(th2);
                            dispose();
                            this.queue.clear();
                            this.actual.onError(th2);
                            return;
                        }
                    }
                    if (decrementAndGet() == 0) {
                        return;
                    }
                }
                this.queue.clear();
            }
        }
    }

    public void a(Observer<? super U> observer) {
        if (!ObservableScalarXMap.a(this.a, observer, this.b)) {
            if (this.d == ErrorMode.IMMEDIATE) {
                this.a.subscribe(new SourceObserver(new io.reactivex.observers.b(observer), this.b, this.c));
            } else {
                this.a.subscribe(new ConcatMapDelayErrorObserver(observer, this.b, this.c, this.d == ErrorMode.END));
            }
        }
    }
}
