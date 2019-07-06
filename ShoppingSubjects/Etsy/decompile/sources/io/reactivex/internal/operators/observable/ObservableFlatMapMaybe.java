package io.reactivex.internal.operators.observable;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.g;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.queue.a;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.m;
import io.reactivex.o;
import io.reactivex.q;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableFlatMapMaybe<T, R> extends a<T, R> {
    final g<? super T, ? extends o<? extends R>> b;
    final boolean c;

    static final class FlatMapMaybeObserver<T, R> extends AtomicInteger implements Observer<T>, Disposable {
        private static final long serialVersionUID = 8600231336733376951L;
        final AtomicInteger active = new AtomicInteger(1);
        final Observer<? super R> actual;
        volatile boolean cancelled;
        Disposable d;
        final boolean delayErrors;
        final AtomicThrowable errors = new AtomicThrowable();
        final g<? super T, ? extends o<? extends R>> mapper;
        final AtomicReference<a<R>> queue = new AtomicReference<>();
        final io.reactivex.disposables.a set = new io.reactivex.disposables.a();

        final class InnerObserver extends AtomicReference<Disposable> implements Disposable, m<R> {
            private static final long serialVersionUID = -502562646270949838L;

            InnerObserver() {
            }

            public void onSubscribe(Disposable disposable) {
                DisposableHelper.setOnce(this, disposable);
            }

            public void onSuccess(R r) {
                FlatMapMaybeObserver.this.innerSuccess(this, r);
            }

            public void onError(Throwable th) {
                FlatMapMaybeObserver.this.innerError(this, th);
            }

            public void onComplete() {
                FlatMapMaybeObserver.this.innerComplete(this);
            }

            public boolean isDisposed() {
                return DisposableHelper.isDisposed((Disposable) get());
            }

            public void dispose() {
                DisposableHelper.dispose(this);
            }
        }

        FlatMapMaybeObserver(Observer<? super R> observer, g<? super T, ? extends o<? extends R>> gVar, boolean z) {
            this.actual = observer;
            this.mapper = gVar;
            this.delayErrors = z;
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.d, disposable)) {
                this.d = disposable;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            try {
                o oVar = (o) io.reactivex.internal.functions.a.a(this.mapper.apply(t), "The mapper returned a null MaybeSource");
                this.active.getAndIncrement();
                InnerObserver innerObserver = new InnerObserver();
                if (!this.cancelled && this.set.a((Disposable) innerObserver)) {
                    oVar.a(innerObserver);
                }
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                this.d.dispose();
                onError(th);
            }
        }

        public void onError(Throwable th) {
            this.active.decrementAndGet();
            if (this.errors.addThrowable(th)) {
                if (!this.delayErrors) {
                    this.set.dispose();
                }
                drain();
                return;
            }
            io.reactivex.d.a.a(th);
        }

        public void onComplete() {
            this.active.decrementAndGet();
            drain();
        }

        public void dispose() {
            this.cancelled = true;
            this.d.dispose();
            this.set.dispose();
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        /* access modifiers changed from: 0000 */
        public void innerSuccess(InnerObserver innerObserver, R r) {
            this.set.c(innerObserver);
            if (get() == 0) {
                boolean z = true;
                if (compareAndSet(0, 1)) {
                    this.actual.onNext(r);
                    if (this.active.decrementAndGet() != 0) {
                        z = false;
                    }
                    a aVar = (a) this.queue.get();
                    if (!z || (aVar != null && !aVar.isEmpty())) {
                        if (decrementAndGet() == 0) {
                            return;
                        }
                        drainLoop();
                    }
                    Throwable terminate = this.errors.terminate();
                    if (terminate != null) {
                        this.actual.onError(terminate);
                    } else {
                        this.actual.onComplete();
                    }
                    return;
                }
            }
            a orCreateQueue = getOrCreateQueue();
            synchronized (orCreateQueue) {
                orCreateQueue.offer(r);
            }
            this.active.decrementAndGet();
            if (getAndIncrement() != 0) {
                return;
            }
            drainLoop();
        }

        /* access modifiers changed from: 0000 */
        public a<R> getOrCreateQueue() {
            a<R> aVar;
            do {
                a<R> aVar2 = (a) this.queue.get();
                if (aVar2 != null) {
                    return aVar2;
                }
                aVar = new a<>(q.d());
            } while (!this.queue.compareAndSet(null, aVar));
            return aVar;
        }

        /* access modifiers changed from: 0000 */
        public void innerError(InnerObserver innerObserver, Throwable th) {
            this.set.c(innerObserver);
            if (this.errors.addThrowable(th)) {
                if (!this.delayErrors) {
                    this.d.dispose();
                    this.set.dispose();
                }
                this.active.decrementAndGet();
                drain();
                return;
            }
            io.reactivex.d.a.a(th);
        }

        /* access modifiers changed from: 0000 */
        public void innerComplete(InnerObserver innerObserver) {
            this.set.c(innerObserver);
            if (get() == 0) {
                boolean z = true;
                if (compareAndSet(0, 1)) {
                    if (this.active.decrementAndGet() != 0) {
                        z = false;
                    }
                    a aVar = (a) this.queue.get();
                    if (z && (aVar == null || aVar.isEmpty())) {
                        Throwable terminate = this.errors.terminate();
                        if (terminate != null) {
                            this.actual.onError(terminate);
                        } else {
                            this.actual.onComplete();
                        }
                        return;
                    } else if (decrementAndGet() != 0) {
                        drainLoop();
                    } else {
                        return;
                    }
                }
            }
            this.active.decrementAndGet();
            drain();
        }

        /* access modifiers changed from: 0000 */
        public void drain() {
            if (getAndIncrement() == 0) {
                drainLoop();
            }
        }

        /* access modifiers changed from: 0000 */
        public void clear() {
            a aVar = (a) this.queue.get();
            if (aVar != null) {
                aVar.clear();
            }
        }

        /* access modifiers changed from: 0000 */
        public void drainLoop() {
            Observer<? super R> observer = this.actual;
            AtomicInteger atomicInteger = this.active;
            AtomicReference<a<R>> atomicReference = this.queue;
            int i = 1;
            while (!this.cancelled) {
                if (this.delayErrors || ((Throwable) this.errors.get()) == null) {
                    boolean z = false;
                    boolean z2 = atomicInteger.get() == 0;
                    a aVar = (a) atomicReference.get();
                    Object poll = aVar != null ? aVar.poll() : null;
                    if (poll == null) {
                        z = true;
                    }
                    if (z2 && z) {
                        Throwable terminate = this.errors.terminate();
                        if (terminate != null) {
                            observer.onError(terminate);
                        } else {
                            observer.onComplete();
                        }
                        return;
                    } else if (z) {
                        i = addAndGet(-i);
                        if (i == 0) {
                            return;
                        }
                    } else {
                        observer.onNext(poll);
                    }
                } else {
                    Throwable terminate2 = this.errors.terminate();
                    clear();
                    observer.onError(terminate2);
                    return;
                }
            }
            clear();
        }
    }

    /* access modifiers changed from: protected */
    public void a(Observer<? super R> observer) {
        this.a.subscribe(new FlatMapMaybeObserver(observer, this.b, this.c));
    }
}
