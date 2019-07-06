package io.reactivex.internal.operators.observable;

import io.reactivex.Observer;
import io.reactivex.a;
import io.reactivex.c;
import io.reactivex.disposables.Disposable;
import io.reactivex.e;
import io.reactivex.functions.g;
import io.reactivex.internal.a.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.t;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableConcatMapCompletable<T> extends a {
    final t<T> a;
    final g<? super T, ? extends e> b;
    final int c;

    static final class SourceObserver<T> extends AtomicInteger implements Observer<T>, Disposable {
        private static final long serialVersionUID = 6893587405571511048L;
        volatile boolean active;
        final c actual;
        final int bufferSize;
        volatile boolean disposed;
        volatile boolean done;
        final InnerObserver inner;
        final g<? super T, ? extends e> mapper;
        io.reactivex.internal.a.g<T> queue;
        Disposable s;
        int sourceMode;

        static final class InnerObserver extends AtomicReference<Disposable> implements c {
            private static final long serialVersionUID = -5987419458390772447L;
            final c actual;
            final SourceObserver<?> parent;

            InnerObserver(c cVar, SourceObserver<?> sourceObserver) {
                this.actual = cVar;
                this.parent = sourceObserver;
            }

            public void onSubscribe(Disposable disposable) {
                DisposableHelper.set(this, disposable);
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

        SourceObserver(c cVar, g<? super T, ? extends e> gVar, int i) {
            this.actual = cVar;
            this.mapper = gVar;
            this.bufferSize = i;
            this.inner = new InnerObserver(cVar, this);
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.s, disposable)) {
                this.s = disposable;
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
            if (!this.done) {
                if (this.sourceMode == 0) {
                    this.queue.offer(t);
                }
                drain();
            }
        }

        public void onError(Throwable th) {
            if (this.done) {
                io.reactivex.d.a.a(th);
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
                                    e eVar = (e) io.reactivex.internal.functions.a.a(this.mapper.apply(poll), "The mapper returned a null CompletableSource");
                                    this.active = true;
                                    eVar.a(this.inner);
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

    public void b(c cVar) {
        this.a.subscribe(new SourceObserver(cVar, this.b, this.c));
    }
}
