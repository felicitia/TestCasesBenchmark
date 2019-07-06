package io.reactivex.internal.operators.observable;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.g;
import io.reactivex.internal.a.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.observers.InnerQueuedObserver;
import io.reactivex.internal.observers.d;
import io.reactivex.internal.queue.a;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.ErrorMode;
import io.reactivex.t;
import java.util.ArrayDeque;
import java.util.concurrent.atomic.AtomicInteger;

public final class ObservableConcatMapEager<T, R> extends a<T, R> {
    final g<? super T, ? extends t<? extends R>> b;
    final ErrorMode c;
    final int d;
    final int e;

    static final class ConcatMapEagerMainObserver<T, R> extends AtomicInteger implements Observer<T>, Disposable, d<R> {
        private static final long serialVersionUID = 8080567949447303262L;
        int activeCount;
        final Observer<? super R> actual;
        volatile boolean cancelled;
        InnerQueuedObserver<R> current;
        Disposable d;
        volatile boolean done;
        final AtomicThrowable error = new AtomicThrowable();
        final ErrorMode errorMode;
        final g<? super T, ? extends t<? extends R>> mapper;
        final int maxConcurrency;
        final ArrayDeque<InnerQueuedObserver<R>> observers = new ArrayDeque<>();
        final int prefetch;
        io.reactivex.internal.a.g<T> queue;
        int sourceMode;

        ConcatMapEagerMainObserver(Observer<? super R> observer, g<? super T, ? extends t<? extends R>> gVar, int i, int i2, ErrorMode errorMode2) {
            this.actual = observer;
            this.mapper = gVar;
            this.maxConcurrency = i;
            this.prefetch = i2;
            this.errorMode = errorMode2;
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
                this.queue = new a(this.prefetch);
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
            io.reactivex.d.a.a(th);
        }

        public void onComplete() {
            this.done = true;
            drain();
        }

        public void dispose() {
            this.cancelled = true;
            if (getAndIncrement() == 0) {
                this.queue.clear();
                disposeAll();
            }
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        /* access modifiers changed from: 0000 */
        public void disposeAll() {
            InnerQueuedObserver<R> innerQueuedObserver = this.current;
            if (innerQueuedObserver != null) {
                innerQueuedObserver.dispose();
            }
            while (true) {
                InnerQueuedObserver innerQueuedObserver2 = (InnerQueuedObserver) this.observers.poll();
                if (innerQueuedObserver2 != null) {
                    innerQueuedObserver2.dispose();
                } else {
                    return;
                }
            }
        }

        public void innerNext(InnerQueuedObserver<R> innerQueuedObserver, R r) {
            innerQueuedObserver.queue().offer(r);
            drain();
        }

        public void innerError(InnerQueuedObserver<R> innerQueuedObserver, Throwable th) {
            if (this.error.addThrowable(th)) {
                if (this.errorMode == ErrorMode.IMMEDIATE) {
                    this.d.dispose();
                }
                innerQueuedObserver.setDone();
                drain();
                return;
            }
            io.reactivex.d.a.a(th);
        }

        public void innerComplete(InnerQueuedObserver<R> innerQueuedObserver) {
            innerQueuedObserver.setDone();
            drain();
        }

        public void drain() {
            if (getAndIncrement() == 0) {
                io.reactivex.internal.a.g<T> gVar = this.queue;
                ArrayDeque<InnerQueuedObserver<R>> arrayDeque = this.observers;
                Observer<? super R> observer = this.actual;
                ErrorMode errorMode2 = this.errorMode;
                int i = 1;
                while (true) {
                    int i2 = this.activeCount;
                    while (true) {
                        if (i2 == this.maxConcurrency) {
                            break;
                        } else if (this.cancelled) {
                            gVar.clear();
                            disposeAll();
                            return;
                        } else if (errorMode2 != ErrorMode.IMMEDIATE || ((Throwable) this.error.get()) == null) {
                            try {
                                Object poll = gVar.poll();
                                if (poll == null) {
                                    break;
                                }
                                t tVar = (t) io.reactivex.internal.functions.a.a(this.mapper.apply(poll), "The mapper returned a null ObservableSource");
                                InnerQueuedObserver innerQueuedObserver = new InnerQueuedObserver(this, this.prefetch);
                                arrayDeque.offer(innerQueuedObserver);
                                tVar.subscribe(innerQueuedObserver);
                                i2++;
                            } catch (Throwable th) {
                                io.reactivex.exceptions.a.b(th);
                                this.d.dispose();
                                gVar.clear();
                                disposeAll();
                                this.error.addThrowable(th);
                                observer.onError(this.error.terminate());
                                return;
                            }
                        } else {
                            gVar.clear();
                            disposeAll();
                            observer.onError(this.error.terminate());
                            return;
                        }
                    }
                    this.activeCount = i2;
                    if (this.cancelled) {
                        gVar.clear();
                        disposeAll();
                        return;
                    } else if (errorMode2 != ErrorMode.IMMEDIATE || ((Throwable) this.error.get()) == null) {
                        InnerQueuedObserver<R> innerQueuedObserver2 = this.current;
                        if (innerQueuedObserver2 == null) {
                            if (errorMode2 != ErrorMode.BOUNDARY || ((Throwable) this.error.get()) == null) {
                                boolean z = this.done;
                                InnerQueuedObserver<R> innerQueuedObserver3 = (InnerQueuedObserver) arrayDeque.poll();
                                boolean z2 = innerQueuedObserver3 == null;
                                if (!z || !z2) {
                                    if (!z2) {
                                        this.current = innerQueuedObserver3;
                                    }
                                    innerQueuedObserver2 = innerQueuedObserver3;
                                } else {
                                    if (((Throwable) this.error.get()) != null) {
                                        gVar.clear();
                                        disposeAll();
                                        observer.onError(this.error.terminate());
                                    } else {
                                        observer.onComplete();
                                    }
                                    return;
                                }
                            } else {
                                gVar.clear();
                                disposeAll();
                                observer.onError(this.error.terminate());
                                return;
                            }
                        }
                        if (innerQueuedObserver2 != null) {
                            io.reactivex.internal.a.g queue2 = innerQueuedObserver2.queue();
                            while (!this.cancelled) {
                                boolean isDone = innerQueuedObserver2.isDone();
                                if (errorMode2 != ErrorMode.IMMEDIATE || ((Throwable) this.error.get()) == null) {
                                    try {
                                        Object poll2 = queue2.poll();
                                        boolean z3 = poll2 == null;
                                        if (isDone && z3) {
                                            this.current = null;
                                            this.activeCount--;
                                        } else if (!z3) {
                                            observer.onNext(poll2);
                                        }
                                    } catch (Throwable th2) {
                                        io.reactivex.exceptions.a.b(th2);
                                        this.error.addThrowable(th2);
                                        this.current = null;
                                        this.activeCount--;
                                    }
                                } else {
                                    gVar.clear();
                                    disposeAll();
                                    observer.onError(this.error.terminate());
                                    return;
                                }
                            }
                            gVar.clear();
                            disposeAll();
                            return;
                        }
                        i = addAndGet(-i);
                        if (i == 0) {
                            return;
                        }
                    } else {
                        gVar.clear();
                        disposeAll();
                        observer.onError(this.error.terminate());
                        return;
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a(Observer<? super R> observer) {
        t tVar = this.a;
        ConcatMapEagerMainObserver concatMapEagerMainObserver = new ConcatMapEagerMainObserver(observer, this.b, this.d, this.e, this.c);
        tVar.subscribe(concatMapEagerMainObserver);
    }
}
