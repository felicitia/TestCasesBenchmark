package io.reactivex.internal.operators.observable;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.f;
import io.reactivex.observers.b;
import io.reactivex.t;
import io.reactivex.u;
import io.reactivex.u.c;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableTimeoutTimed<T> extends a<T, T> {
    static final Disposable f = new a();
    final long b;
    final TimeUnit c;
    final u d;
    final t<? extends T> e;

    static final class TimeoutTimedObserver<T> extends AtomicReference<Disposable> implements Observer<T>, Disposable {
        private static final long serialVersionUID = -8387234228317808253L;
        final Observer<? super T> actual;
        volatile boolean done;
        volatile long index;
        Disposable s;
        final long timeout;
        final TimeUnit unit;
        final c worker;

        final class a implements Runnable {
            private final long b;

            a(long j) {
                this.b = j;
            }

            public void run() {
                if (this.b == TimeoutTimedObserver.this.index) {
                    TimeoutTimedObserver.this.done = true;
                    TimeoutTimedObserver.this.s.dispose();
                    DisposableHelper.dispose(TimeoutTimedObserver.this);
                    TimeoutTimedObserver.this.actual.onError(new TimeoutException());
                    TimeoutTimedObserver.this.worker.dispose();
                }
            }
        }

        TimeoutTimedObserver(Observer<? super T> observer, long j, TimeUnit timeUnit, c cVar) {
            this.actual = observer;
            this.timeout = j;
            this.unit = timeUnit;
            this.worker = cVar;
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.s, disposable)) {
                this.s = disposable;
                this.actual.onSubscribe(this);
                scheduleTimeout(0);
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                long j = this.index + 1;
                this.index = j;
                this.actual.onNext(t);
                scheduleTimeout(j);
            }
        }

        /* access modifiers changed from: 0000 */
        public void scheduleTimeout(long j) {
            Disposable disposable = (Disposable) get();
            if (disposable != null) {
                disposable.dispose();
            }
            if (compareAndSet(disposable, ObservableTimeoutTimed.f)) {
                DisposableHelper.replace(this, this.worker.a(new a(j), this.timeout, this.unit));
            }
        }

        public void onError(Throwable th) {
            if (this.done) {
                io.reactivex.d.a.a(th);
                return;
            }
            this.done = true;
            this.actual.onError(th);
            dispose();
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.actual.onComplete();
                dispose();
            }
        }

        public void dispose() {
            this.s.dispose();
            this.worker.dispose();
        }

        public boolean isDisposed() {
            return this.worker.isDisposed();
        }
    }

    static final class TimeoutTimedOtherObserver<T> extends AtomicReference<Disposable> implements Observer<T>, Disposable {
        private static final long serialVersionUID = -4619702551964128179L;
        final Observer<? super T> actual;
        final f<T> arbiter;
        volatile boolean done;
        volatile long index;
        final t<? extends T> other;
        Disposable s;
        final long timeout;
        final TimeUnit unit;
        final c worker;

        final class a implements Runnable {
            private final long b;

            a(long j) {
                this.b = j;
            }

            public void run() {
                if (this.b == TimeoutTimedOtherObserver.this.index) {
                    TimeoutTimedOtherObserver.this.done = true;
                    TimeoutTimedOtherObserver.this.s.dispose();
                    DisposableHelper.dispose(TimeoutTimedOtherObserver.this);
                    TimeoutTimedOtherObserver.this.subscribeNext();
                    TimeoutTimedOtherObserver.this.worker.dispose();
                }
            }
        }

        TimeoutTimedOtherObserver(Observer<? super T> observer, long j, TimeUnit timeUnit, c cVar, t<? extends T> tVar) {
            this.actual = observer;
            this.timeout = j;
            this.unit = timeUnit;
            this.worker = cVar;
            this.other = tVar;
            this.arbiter = new f<>(observer, this, 8);
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.s, disposable)) {
                this.s = disposable;
                if (this.arbiter.a(disposable)) {
                    this.actual.onSubscribe(this.arbiter);
                    scheduleTimeout(0);
                }
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                long j = this.index + 1;
                this.index = j;
                if (this.arbiter.a(t, this.s)) {
                    scheduleTimeout(j);
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void scheduleTimeout(long j) {
            Disposable disposable = (Disposable) get();
            if (disposable != null) {
                disposable.dispose();
            }
            if (compareAndSet(disposable, ObservableTimeoutTimed.f)) {
                DisposableHelper.replace(this, this.worker.a(new a(j), this.timeout, this.unit));
            }
        }

        /* access modifiers changed from: 0000 */
        public void subscribeNext() {
            this.other.subscribe(new io.reactivex.internal.observers.c(this.arbiter));
        }

        public void onError(Throwable th) {
            if (this.done) {
                io.reactivex.d.a.a(th);
                return;
            }
            this.done = true;
            this.arbiter.a(th, this.s);
            this.worker.dispose();
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.arbiter.b(this.s);
                this.worker.dispose();
            }
        }

        public void dispose() {
            this.s.dispose();
            this.worker.dispose();
        }

        public boolean isDisposed() {
            return this.worker.isDisposed();
        }
    }

    static final class a implements Disposable {
        public void dispose() {
        }

        public boolean isDisposed() {
            return true;
        }

        a() {
        }
    }

    public void a(Observer<? super T> observer) {
        if (this.e == null) {
            t tVar = this.a;
            TimeoutTimedObserver timeoutTimedObserver = new TimeoutTimedObserver(new b(observer), this.b, this.c, this.d.a());
            tVar.subscribe(timeoutTimedObserver);
            return;
        }
        t tVar2 = this.a;
        TimeoutTimedOtherObserver timeoutTimedOtherObserver = new TimeoutTimedOtherObserver(observer, this.b, this.c, this.d.a(), this.e);
        tVar2.subscribe(timeoutTimedOtherObserver);
    }
}
