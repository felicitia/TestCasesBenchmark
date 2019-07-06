package io.reactivex.internal.operators.observable;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.g;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.f;
import io.reactivex.internal.observers.c;
import io.reactivex.t;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableTimeout<T, U, V> extends a<T, T> {
    final t<U> b;
    final g<? super T, ? extends t<V>> c;
    final t<? extends T> d;

    static final class TimeoutObserver<T, U, V> extends AtomicReference<Disposable> implements Observer<T>, Disposable, a {
        private static final long serialVersionUID = 2672739326310051084L;
        final Observer<? super T> actual;
        final t<U> firstTimeoutIndicator;
        volatile long index;
        final g<? super T, ? extends t<V>> itemTimeoutIndicator;
        Disposable s;

        TimeoutObserver(Observer<? super T> observer, t<U> tVar, g<? super T, ? extends t<V>> gVar) {
            this.actual = observer;
            this.firstTimeoutIndicator = tVar;
            this.itemTimeoutIndicator = gVar;
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.s, disposable)) {
                this.s = disposable;
                Observer<? super T> observer = this.actual;
                t<U> tVar = this.firstTimeoutIndicator;
                if (tVar != null) {
                    b bVar = new b(this, 0);
                    if (compareAndSet(null, bVar)) {
                        observer.onSubscribe(this);
                        tVar.subscribe(bVar);
                        return;
                    }
                    return;
                }
                observer.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            long j = this.index + 1;
            this.index = j;
            this.actual.onNext(t);
            Disposable disposable = (Disposable) get();
            if (disposable != null) {
                disposable.dispose();
            }
            try {
                t tVar = (t) io.reactivex.internal.functions.a.a(this.itemTimeoutIndicator.apply(t), "The ObservableSource returned is null");
                b bVar = new b(this, j);
                if (compareAndSet(disposable, bVar)) {
                    tVar.subscribe(bVar);
                }
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                dispose();
                this.actual.onError(th);
            }
        }

        public void onError(Throwable th) {
            DisposableHelper.dispose(this);
            this.actual.onError(th);
        }

        public void onComplete() {
            DisposableHelper.dispose(this);
            this.actual.onComplete();
        }

        public void dispose() {
            if (DisposableHelper.dispose(this)) {
                this.s.dispose();
            }
        }

        public boolean isDisposed() {
            return this.s.isDisposed();
        }

        public void timeout(long j) {
            if (j == this.index) {
                dispose();
                this.actual.onError(new TimeoutException());
            }
        }

        public void innerError(Throwable th) {
            this.s.dispose();
            this.actual.onError(th);
        }
    }

    static final class TimeoutOtherObserver<T, U, V> extends AtomicReference<Disposable> implements Observer<T>, Disposable, a {
        private static final long serialVersionUID = -1957813281749686898L;
        final Observer<? super T> actual;
        final f<T> arbiter;
        boolean done;
        final t<U> firstTimeoutIndicator;
        volatile long index;
        final g<? super T, ? extends t<V>> itemTimeoutIndicator;
        final t<? extends T> other;
        Disposable s;

        TimeoutOtherObserver(Observer<? super T> observer, t<U> tVar, g<? super T, ? extends t<V>> gVar, t<? extends T> tVar2) {
            this.actual = observer;
            this.firstTimeoutIndicator = tVar;
            this.itemTimeoutIndicator = gVar;
            this.other = tVar2;
            this.arbiter = new f<>(observer, this, 8);
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.s, disposable)) {
                this.s = disposable;
                this.arbiter.a(disposable);
                Observer<? super T> observer = this.actual;
                t<U> tVar = this.firstTimeoutIndicator;
                if (tVar != null) {
                    b bVar = new b(this, 0);
                    if (compareAndSet(null, bVar)) {
                        observer.onSubscribe(this.arbiter);
                        tVar.subscribe(bVar);
                        return;
                    }
                    return;
                }
                observer.onSubscribe(this.arbiter);
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                long j = this.index + 1;
                this.index = j;
                if (this.arbiter.a(t, this.s)) {
                    Disposable disposable = (Disposable) get();
                    if (disposable != null) {
                        disposable.dispose();
                    }
                    try {
                        t tVar = (t) io.reactivex.internal.functions.a.a(this.itemTimeoutIndicator.apply(t), "The ObservableSource returned is null");
                        b bVar = new b(this, j);
                        if (compareAndSet(disposable, bVar)) {
                            tVar.subscribe(bVar);
                        }
                    } catch (Throwable th) {
                        io.reactivex.exceptions.a.b(th);
                        this.actual.onError(th);
                    }
                }
            }
        }

        public void onError(Throwable th) {
            if (this.done) {
                io.reactivex.d.a.a(th);
                return;
            }
            this.done = true;
            dispose();
            this.arbiter.a(th, this.s);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                dispose();
                this.arbiter.b(this.s);
            }
        }

        public void dispose() {
            if (DisposableHelper.dispose(this)) {
                this.s.dispose();
            }
        }

        public boolean isDisposed() {
            return this.s.isDisposed();
        }

        public void timeout(long j) {
            if (j == this.index) {
                dispose();
                this.other.subscribe(new c(this.arbiter));
            }
        }

        public void innerError(Throwable th) {
            this.s.dispose();
            this.actual.onError(th);
        }
    }

    interface a {
        void innerError(Throwable th);

        void timeout(long j);
    }

    static final class b<T, U, V> extends io.reactivex.observers.a<Object> {
        final a a;
        final long b;
        boolean c;

        b(a aVar, long j) {
            this.a = aVar;
            this.b = j;
        }

        public void onNext(Object obj) {
            if (!this.c) {
                this.c = true;
                dispose();
                this.a.timeout(this.b);
            }
        }

        public void onError(Throwable th) {
            if (this.c) {
                io.reactivex.d.a.a(th);
                return;
            }
            this.c = true;
            this.a.innerError(th);
        }

        public void onComplete() {
            if (!this.c) {
                this.c = true;
                this.a.timeout(this.b);
            }
        }
    }

    public void a(Observer<? super T> observer) {
        if (this.d == null) {
            this.a.subscribe(new TimeoutObserver(new io.reactivex.observers.b(observer), this.b, this.c));
        } else {
            this.a.subscribe(new TimeoutOtherObserver(observer, this.b, this.c, this.d));
        }
    }
}
