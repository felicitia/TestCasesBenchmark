package io.reactivex.internal.operators.observable;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.queue.a;
import io.reactivex.t;
import io.reactivex.u;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public final class ObservableTakeLastTimed<T> extends a<T, T> {
    final long b;
    final long c;
    final TimeUnit d;
    final u e;
    final int f;
    final boolean g;

    static final class TakeLastTimedObserver<T> extends AtomicBoolean implements Observer<T>, Disposable {
        private static final long serialVersionUID = -5677354903406201275L;
        final Observer<? super T> actual;
        volatile boolean cancelled;
        final long count;
        Disposable d;
        final boolean delayError;
        Throwable error;
        final a<Object> queue;
        final u scheduler;
        final long time;
        final TimeUnit unit;

        TakeLastTimedObserver(Observer<? super T> observer, long j, long j2, TimeUnit timeUnit, u uVar, int i, boolean z) {
            this.actual = observer;
            this.count = j;
            this.time = j2;
            this.unit = timeUnit;
            this.scheduler = uVar;
            this.queue = new a<>(i);
            this.delayError = z;
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.d, disposable)) {
                this.d = disposable;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            a<Object> aVar = this.queue;
            long a = this.scheduler.a(this.unit);
            long j = this.time;
            long j2 = this.count;
            boolean z = j2 == Long.MAX_VALUE;
            aVar.a(Long.valueOf(a), t);
            while (!aVar.isEmpty()) {
                if (((Long) aVar.a()).longValue() <= a - j || (!z && ((long) (aVar.b() >> 1)) > j2)) {
                    aVar.poll();
                    aVar.poll();
                } else {
                    return;
                }
            }
        }

        public void onError(Throwable th) {
            this.error = th;
            drain();
        }

        public void onComplete() {
            drain();
        }

        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.d.dispose();
                if (compareAndSet(false, true)) {
                    this.queue.clear();
                }
            }
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        /* access modifiers changed from: 0000 */
        public void drain() {
            if (compareAndSet(false, true)) {
                Observer<? super T> observer = this.actual;
                a<Object> aVar = this.queue;
                boolean z = this.delayError;
                while (!this.cancelled) {
                    if (!z) {
                        Throwable th = this.error;
                        if (th != null) {
                            aVar.clear();
                            observer.onError(th);
                            return;
                        }
                    }
                    Object poll = aVar.poll();
                    if (poll == null) {
                        Throwable th2 = this.error;
                        if (th2 != null) {
                            observer.onError(th2);
                        } else {
                            observer.onComplete();
                        }
                        return;
                    }
                    Object poll2 = aVar.poll();
                    if (((Long) poll).longValue() >= this.scheduler.a(this.unit) - this.time) {
                        observer.onNext(poll2);
                    }
                }
                aVar.clear();
            }
        }
    }

    public void a(Observer<? super T> observer) {
        t tVar = this.a;
        TakeLastTimedObserver takeLastTimedObserver = new TakeLastTimedObserver(observer, this.b, this.c, this.d, this.e, this.f, this.g);
        tVar.subscribe(takeLastTimedObserver);
    }
}
