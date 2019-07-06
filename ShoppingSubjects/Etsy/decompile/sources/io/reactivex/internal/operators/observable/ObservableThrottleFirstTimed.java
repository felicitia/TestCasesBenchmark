package io.reactivex.internal.operators.observable;

import io.reactivex.Observer;
import io.reactivex.d.a;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.observers.b;
import io.reactivex.t;
import io.reactivex.u;
import io.reactivex.u.c;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableThrottleFirstTimed<T> extends a<T, T> {
    final long b;
    final TimeUnit c;
    final u d;

    static final class DebounceTimedObserver<T> extends AtomicReference<Disposable> implements Observer<T>, Disposable, Runnable {
        private static final long serialVersionUID = 786994795061867455L;
        final Observer<? super T> actual;
        boolean done;
        volatile boolean gate;
        Disposable s;
        final long timeout;
        final TimeUnit unit;
        final c worker;

        DebounceTimedObserver(Observer<? super T> observer, long j, TimeUnit timeUnit, c cVar) {
            this.actual = observer;
            this.timeout = j;
            this.unit = timeUnit;
            this.worker = cVar;
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.s, disposable)) {
                this.s = disposable;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            if (!this.gate && !this.done) {
                this.gate = true;
                this.actual.onNext(t);
                Disposable disposable = (Disposable) get();
                if (disposable != null) {
                    disposable.dispose();
                }
                DisposableHelper.replace(this, this.worker.a(this, this.timeout, this.unit));
            }
        }

        public void run() {
            this.gate = false;
        }

        public void onError(Throwable th) {
            if (this.done) {
                a.a(th);
                return;
            }
            this.done = true;
            this.actual.onError(th);
            this.worker.dispose();
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.actual.onComplete();
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

    public void a(Observer<? super T> observer) {
        t tVar = this.a;
        DebounceTimedObserver debounceTimedObserver = new DebounceTimedObserver(new b(observer), this.b, this.c, this.d.a());
        tVar.subscribe(debounceTimedObserver);
    }
}
