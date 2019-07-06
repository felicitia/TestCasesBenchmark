package io.reactivex.internal.operators.observable;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.a.b;
import io.reactivex.internal.a.g;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.observers.BasicIntQueueDisposable;
import io.reactivex.internal.queue.a;
import io.reactivex.internal.schedulers.j;
import io.reactivex.t;
import io.reactivex.u;
import io.reactivex.u.c;

public final class ObservableObserveOn<T> extends a<T, T> {
    final u b;
    final boolean c;
    final int d;

    static final class ObserveOnObserver<T> extends BasicIntQueueDisposable<T> implements Observer<T>, Runnable {
        private static final long serialVersionUID = 6576896619930983584L;
        final Observer<? super T> actual;
        final int bufferSize;
        volatile boolean cancelled;
        final boolean delayError;
        volatile boolean done;
        Throwable error;
        boolean outputFused;
        g<T> queue;
        Disposable s;
        int sourceMode;
        final c worker;

        ObserveOnObserver(Observer<? super T> observer, c cVar, boolean z, int i) {
            this.actual = observer;
            this.worker = cVar;
            this.delayError = z;
            this.bufferSize = i;
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.s, disposable)) {
                this.s = disposable;
                if (disposable instanceof b) {
                    b bVar = (b) disposable;
                    int requestFusion = bVar.requestFusion(7);
                    if (requestFusion == 1) {
                        this.sourceMode = requestFusion;
                        this.queue = bVar;
                        this.done = true;
                        this.actual.onSubscribe(this);
                        schedule();
                        return;
                    } else if (requestFusion == 2) {
                        this.sourceMode = requestFusion;
                        this.queue = bVar;
                        this.actual.onSubscribe(this);
                        return;
                    }
                }
                this.queue = new a(this.bufferSize);
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                if (this.sourceMode != 2) {
                    this.queue.offer(t);
                }
                schedule();
            }
        }

        public void onError(Throwable th) {
            if (this.done) {
                io.reactivex.d.a.a(th);
                return;
            }
            this.error = th;
            this.done = true;
            schedule();
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                schedule();
            }
        }

        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.s.dispose();
                this.worker.dispose();
                if (getAndIncrement() == 0) {
                    this.queue.clear();
                }
            }
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        /* access modifiers changed from: 0000 */
        public void schedule() {
            if (getAndIncrement() == 0) {
                this.worker.a((Runnable) this);
            }
        }

        /* access modifiers changed from: 0000 */
        public void drainNormal() {
            g<T> gVar = this.queue;
            Observer<? super T> observer = this.actual;
            int i = 1;
            while (!checkTerminated(this.done, gVar.isEmpty(), observer)) {
                while (true) {
                    boolean z = this.done;
                    try {
                        Object poll = gVar.poll();
                        boolean z2 = poll == null;
                        if (!checkTerminated(z, z2, observer)) {
                            if (z2) {
                                i = addAndGet(-i);
                                if (i == 0) {
                                    return;
                                }
                            } else {
                                observer.onNext(poll);
                            }
                        } else {
                            return;
                        }
                    } catch (Throwable th) {
                        io.reactivex.exceptions.a.b(th);
                        this.s.dispose();
                        gVar.clear();
                        observer.onError(th);
                        this.worker.dispose();
                        return;
                    }
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void drainFused() {
            int i = 1;
            while (!this.cancelled) {
                boolean z = this.done;
                Throwable th = this.error;
                if (this.delayError || !z || th == null) {
                    this.actual.onNext(null);
                    if (z) {
                        Throwable th2 = this.error;
                        if (th2 != null) {
                            this.actual.onError(th2);
                        } else {
                            this.actual.onComplete();
                        }
                        this.worker.dispose();
                        return;
                    }
                    i = addAndGet(-i);
                    if (i == 0) {
                        return;
                    }
                } else {
                    this.actual.onError(this.error);
                    this.worker.dispose();
                    return;
                }
            }
        }

        public void run() {
            if (this.outputFused) {
                drainFused();
            } else {
                drainNormal();
            }
        }

        /* access modifiers changed from: 0000 */
        public boolean checkTerminated(boolean z, boolean z2, Observer<? super T> observer) {
            if (this.cancelled) {
                this.queue.clear();
                return true;
            }
            if (z) {
                Throwable th = this.error;
                if (this.delayError) {
                    if (z2) {
                        if (th != null) {
                            observer.onError(th);
                        } else {
                            observer.onComplete();
                        }
                        this.worker.dispose();
                        return true;
                    }
                } else if (th != null) {
                    this.queue.clear();
                    observer.onError(th);
                    this.worker.dispose();
                    return true;
                } else if (z2) {
                    observer.onComplete();
                    this.worker.dispose();
                    return true;
                }
            }
            return false;
        }

        public int requestFusion(int i) {
            if ((i & 2) == 0) {
                return 0;
            }
            this.outputFused = true;
            return 2;
        }

        public T poll() throws Exception {
            return this.queue.poll();
        }

        public void clear() {
            this.queue.clear();
        }

        public boolean isEmpty() {
            return this.queue.isEmpty();
        }
    }

    public ObservableObserveOn(t<T> tVar, u uVar, boolean z, int i) {
        super(tVar);
        this.b = uVar;
        this.c = z;
        this.d = i;
    }

    /* access modifiers changed from: protected */
    public void a(Observer<? super T> observer) {
        if (this.b instanceof j) {
            this.a.subscribe(observer);
            return;
        }
        this.a.subscribe(new ObserveOnObserver(observer, this.b.a(), this.c, this.d));
    }
}
