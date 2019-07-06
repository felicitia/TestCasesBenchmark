package io.reactivex.internal.operators.observable;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.observers.b;
import io.reactivex.t;
import io.reactivex.u;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableSampleTimed<T> extends a<T, T> {
    final long b;
    final TimeUnit c;
    final u d;
    final boolean e;

    static final class SampleTimedEmitLast<T> extends SampleTimedObserver<T> {
        private static final long serialVersionUID = -7139995637533111443L;
        final AtomicInteger wip = new AtomicInteger(1);

        SampleTimedEmitLast(Observer<? super T> observer, long j, TimeUnit timeUnit, u uVar) {
            super(observer, j, timeUnit, uVar);
        }

        /* access modifiers changed from: 0000 */
        public void complete() {
            emit();
            if (this.wip.decrementAndGet() == 0) {
                this.actual.onComplete();
            }
        }

        public void run() {
            if (this.wip.incrementAndGet() == 2) {
                emit();
                if (this.wip.decrementAndGet() == 0) {
                    this.actual.onComplete();
                }
            }
        }
    }

    static final class SampleTimedNoLast<T> extends SampleTimedObserver<T> {
        private static final long serialVersionUID = -7139995637533111443L;

        SampleTimedNoLast(Observer<? super T> observer, long j, TimeUnit timeUnit, u uVar) {
            super(observer, j, timeUnit, uVar);
        }

        /* access modifiers changed from: 0000 */
        public void complete() {
            this.actual.onComplete();
        }

        public void run() {
            emit();
        }
    }

    static abstract class SampleTimedObserver<T> extends AtomicReference<T> implements Observer<T>, Disposable, Runnable {
        private static final long serialVersionUID = -3517602651313910099L;
        final Observer<? super T> actual;
        final long period;
        Disposable s;
        final u scheduler;
        final AtomicReference<Disposable> timer = new AtomicReference<>();
        final TimeUnit unit;

        /* access modifiers changed from: 0000 */
        public abstract void complete();

        SampleTimedObserver(Observer<? super T> observer, long j, TimeUnit timeUnit, u uVar) {
            this.actual = observer;
            this.period = j;
            this.unit = timeUnit;
            this.scheduler = uVar;
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.s, disposable)) {
                this.s = disposable;
                this.actual.onSubscribe(this);
                DisposableHelper.replace(this.timer, this.scheduler.a(this, this.period, this.period, this.unit));
            }
        }

        public void onNext(T t) {
            lazySet(t);
        }

        public void onError(Throwable th) {
            cancelTimer();
            this.actual.onError(th);
        }

        public void onComplete() {
            cancelTimer();
            complete();
        }

        /* access modifiers changed from: 0000 */
        public void cancelTimer() {
            DisposableHelper.dispose(this.timer);
        }

        public void dispose() {
            cancelTimer();
            this.s.dispose();
        }

        public boolean isDisposed() {
            return this.s.isDisposed();
        }

        /* access modifiers changed from: 0000 */
        public void emit() {
            Object andSet = getAndSet(null);
            if (andSet != null) {
                this.actual.onNext(andSet);
            }
        }
    }

    public void a(Observer<? super T> observer) {
        b bVar = new b(observer);
        if (this.e) {
            t tVar = this.a;
            SampleTimedEmitLast sampleTimedEmitLast = new SampleTimedEmitLast(bVar, this.b, this.c, this.d);
            tVar.subscribe(sampleTimedEmitLast);
            return;
        }
        t tVar2 = this.a;
        SampleTimedNoLast sampleTimedNoLast = new SampleTimedNoLast(bVar, this.b, this.c, this.d);
        tVar2.subscribe(sampleTimedNoLast);
    }
}
