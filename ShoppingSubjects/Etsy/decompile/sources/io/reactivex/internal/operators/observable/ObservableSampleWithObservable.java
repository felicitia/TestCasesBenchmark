package io.reactivex.internal.operators.observable;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.observers.b;
import io.reactivex.t;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableSampleWithObservable<T> extends a<T, T> {
    final t<?> b;
    final boolean c;

    static final class SampleMainEmitLast<T> extends SampleMainObserver<T> {
        private static final long serialVersionUID = -3029755663834015785L;
        volatile boolean done;
        final AtomicInteger wip = new AtomicInteger();

        SampleMainEmitLast(Observer<? super T> observer, t<?> tVar) {
            super(observer, tVar);
        }

        /* access modifiers changed from: 0000 */
        public void completeMain() {
            this.done = true;
            if (this.wip.getAndIncrement() == 0) {
                emit();
                this.actual.onComplete();
            }
        }

        /* access modifiers changed from: 0000 */
        public void completeOther() {
            this.done = true;
            if (this.wip.getAndIncrement() == 0) {
                emit();
                this.actual.onComplete();
            }
        }

        /* access modifiers changed from: 0000 */
        public void run() {
            if (this.wip.getAndIncrement() == 0) {
                do {
                    boolean z = this.done;
                    emit();
                    if (z) {
                        this.actual.onComplete();
                        return;
                    }
                } while (this.wip.decrementAndGet() != 0);
            }
        }
    }

    static final class SampleMainNoLast<T> extends SampleMainObserver<T> {
        private static final long serialVersionUID = -3029755663834015785L;

        SampleMainNoLast(Observer<? super T> observer, t<?> tVar) {
            super(observer, tVar);
        }

        /* access modifiers changed from: 0000 */
        public void completeMain() {
            this.actual.onComplete();
        }

        /* access modifiers changed from: 0000 */
        public void completeOther() {
            this.actual.onComplete();
        }

        /* access modifiers changed from: 0000 */
        public void run() {
            emit();
        }
    }

    static abstract class SampleMainObserver<T> extends AtomicReference<T> implements Observer<T>, Disposable {
        private static final long serialVersionUID = -3517602651313910099L;
        final Observer<? super T> actual;
        final AtomicReference<Disposable> other = new AtomicReference<>();
        Disposable s;
        final t<?> sampler;

        /* access modifiers changed from: 0000 */
        public abstract void completeMain();

        /* access modifiers changed from: 0000 */
        public abstract void completeOther();

        /* access modifiers changed from: 0000 */
        public abstract void run();

        SampleMainObserver(Observer<? super T> observer, t<?> tVar) {
            this.actual = observer;
            this.sampler = tVar;
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.s, disposable)) {
                this.s = disposable;
                this.actual.onSubscribe(this);
                if (this.other.get() == null) {
                    this.sampler.subscribe(new a(this));
                }
            }
        }

        public void onNext(T t) {
            lazySet(t);
        }

        public void onError(Throwable th) {
            DisposableHelper.dispose(this.other);
            this.actual.onError(th);
        }

        public void onComplete() {
            DisposableHelper.dispose(this.other);
            completeMain();
        }

        /* access modifiers changed from: 0000 */
        public boolean setOther(Disposable disposable) {
            return DisposableHelper.setOnce(this.other, disposable);
        }

        public void dispose() {
            DisposableHelper.dispose(this.other);
            this.s.dispose();
        }

        public boolean isDisposed() {
            return this.other.get() == DisposableHelper.DISPOSED;
        }

        public void error(Throwable th) {
            this.s.dispose();
            this.actual.onError(th);
        }

        public void complete() {
            this.s.dispose();
            completeOther();
        }

        /* access modifiers changed from: 0000 */
        public void emit() {
            Object andSet = getAndSet(null);
            if (andSet != null) {
                this.actual.onNext(andSet);
            }
        }
    }

    static final class a<T> implements Observer<Object> {
        final SampleMainObserver<T> a;

        a(SampleMainObserver<T> sampleMainObserver) {
            this.a = sampleMainObserver;
        }

        public void onSubscribe(Disposable disposable) {
            this.a.setOther(disposable);
        }

        public void onNext(Object obj) {
            this.a.run();
        }

        public void onError(Throwable th) {
            this.a.error(th);
        }

        public void onComplete() {
            this.a.complete();
        }
    }

    public void a(Observer<? super T> observer) {
        b bVar = new b(observer);
        if (this.c) {
            this.a.subscribe(new SampleMainEmitLast(bVar, this.b));
        } else {
            this.a.subscribe(new SampleMainNoLast(bVar, this.b));
        }
    }
}
