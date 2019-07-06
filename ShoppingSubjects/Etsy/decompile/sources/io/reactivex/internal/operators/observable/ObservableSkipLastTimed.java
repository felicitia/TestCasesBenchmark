package io.reactivex.internal.operators.observable;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.queue.a;
import io.reactivex.t;
import io.reactivex.u;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public final class ObservableSkipLastTimed<T> extends a<T, T> {
    final long b;
    final TimeUnit c;
    final u d;
    final int e;
    final boolean f;

    static final class SkipLastTimedObserver<T> extends AtomicInteger implements Observer<T>, Disposable {
        private static final long serialVersionUID = -5677354903406201275L;
        final Observer<? super T> actual;
        volatile boolean cancelled;
        final boolean delayError;
        volatile boolean done;
        Throwable error;
        final a<Object> queue;
        Disposable s;
        final u scheduler;
        final long time;
        final TimeUnit unit;

        SkipLastTimedObserver(Observer<? super T> observer, long j, TimeUnit timeUnit, u uVar, int i, boolean z) {
            this.actual = observer;
            this.time = j;
            this.unit = timeUnit;
            this.scheduler = uVar;
            this.queue = new a<>(i);
            this.delayError = z;
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.s, disposable)) {
                this.s = disposable;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            this.queue.a(Long.valueOf(this.scheduler.a(this.unit)), t);
            drain();
        }

        public void onError(Throwable th) {
            this.error = th;
            this.done = true;
            drain();
        }

        public void onComplete() {
            this.done = true;
            drain();
        }

        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.s.dispose();
                if (getAndIncrement() == 0) {
                    this.queue.clear();
                }
            }
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        /* access modifiers changed from: 0000 */
        public void drain() {
            if (getAndIncrement() == 0) {
                Observer<? super T> observer = this.actual;
                a<Object> aVar = this.queue;
                boolean z = this.delayError;
                TimeUnit timeUnit = this.unit;
                u uVar = this.scheduler;
                long j = this.time;
                int i = 1;
                while (!this.cancelled) {
                    boolean z2 = this.done;
                    Long l = (Long) aVar.a();
                    boolean z3 = l == null;
                    long a = uVar.a(timeUnit);
                    if (!z3 && l.longValue() > a - j) {
                        z3 = true;
                    }
                    if (z2) {
                        if (!z) {
                            Throwable th = this.error;
                            if (th != null) {
                                this.queue.clear();
                                observer.onError(th);
                                return;
                            } else if (z3) {
                                observer.onComplete();
                                return;
                            }
                        } else if (z3) {
                            Throwable th2 = this.error;
                            if (th2 != null) {
                                observer.onError(th2);
                            } else {
                                observer.onComplete();
                            }
                            return;
                        }
                    }
                    if (z3) {
                        i = addAndGet(-i);
                        if (i == 0) {
                            return;
                        }
                    } else {
                        aVar.poll();
                        observer.onNext(aVar.poll());
                    }
                }
                this.queue.clear();
            }
        }
    }

    public void a(Observer<? super T> observer) {
        t tVar = this.a;
        SkipLastTimedObserver skipLastTimedObserver = new SkipLastTimedObserver(observer, this.b, this.c, this.d, this.e, this.f);
        tVar.subscribe(skipLastTimedObserver);
    }
}
