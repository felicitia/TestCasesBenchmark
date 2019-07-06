package io.reactivex.internal.operators.observable;

import io.reactivex.Observer;
import io.reactivex.functions.g;
import io.reactivex.internal.a.b;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.q;
import io.reactivex.t;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

public final class ObservableScalarXMap {

    public static final class ScalarDisposable<T> extends AtomicInteger implements b<T>, Runnable {
        static final int FUSED = 1;
        static final int ON_COMPLETE = 3;
        static final int ON_NEXT = 2;
        static final int START = 0;
        private static final long serialVersionUID = 3880992722410194083L;
        final Observer<? super T> observer;
        final T value;

        public ScalarDisposable(Observer<? super T> observer2, T t) {
            this.observer = observer2;
            this.value = t;
        }

        public boolean offer(T t) {
            throw new UnsupportedOperationException("Should not be called!");
        }

        public boolean offer(T t, T t2) {
            throw new UnsupportedOperationException("Should not be called!");
        }

        public T poll() throws Exception {
            if (get() != 1) {
                return null;
            }
            lazySet(3);
            return this.value;
        }

        public boolean isEmpty() {
            return get() != 1;
        }

        public void clear() {
            lazySet(3);
        }

        public void dispose() {
            set(3);
        }

        public boolean isDisposed() {
            return get() == 3;
        }

        public int requestFusion(int i) {
            if ((i & 1) == 0) {
                return 0;
            }
            lazySet(1);
            return 1;
        }

        public void run() {
            if (get() == 0 && compareAndSet(0, 2)) {
                this.observer.onNext(this.value);
                if (get() == 2) {
                    lazySet(3);
                    this.observer.onComplete();
                }
            }
        }
    }

    static final class a<T, R> extends q<R> {
        final T a;
        final g<? super T, ? extends t<? extends R>> b;

        a(T t, g<? super T, ? extends t<? extends R>> gVar) {
            this.a = t;
            this.b = gVar;
        }

        public void a(Observer<? super R> observer) {
            try {
                t tVar = (t) io.reactivex.internal.functions.a.a(this.b.apply(this.a), "The mapper returned a null ObservableSource");
                if (tVar instanceof Callable) {
                    try {
                        Object call = ((Callable) tVar).call();
                        if (call == null) {
                            EmptyDisposable.complete(observer);
                            return;
                        }
                        ScalarDisposable scalarDisposable = new ScalarDisposable(observer, call);
                        observer.onSubscribe(scalarDisposable);
                        scalarDisposable.run();
                    } catch (Throwable th) {
                        io.reactivex.exceptions.a.b(th);
                        EmptyDisposable.error(th, observer);
                    }
                } else {
                    tVar.subscribe(observer);
                }
            } catch (Throwable th2) {
                EmptyDisposable.error(th2, observer);
            }
        }
    }

    public static <T, R> boolean a(t<T> tVar, Observer<? super R> observer, g<? super T, ? extends t<? extends R>> gVar) {
        if (!(tVar instanceof Callable)) {
            return false;
        }
        try {
            Object call = ((Callable) tVar).call();
            if (call == null) {
                EmptyDisposable.complete(observer);
                return true;
            }
            try {
                t tVar2 = (t) io.reactivex.internal.functions.a.a(gVar.apply(call), "The mapper returned a null ObservableSource");
                if (tVar2 instanceof Callable) {
                    try {
                        Object call2 = ((Callable) tVar2).call();
                        if (call2 == null) {
                            EmptyDisposable.complete(observer);
                            return true;
                        }
                        ScalarDisposable scalarDisposable = new ScalarDisposable(observer, call2);
                        observer.onSubscribe(scalarDisposable);
                        scalarDisposable.run();
                    } catch (Throwable th) {
                        io.reactivex.exceptions.a.b(th);
                        EmptyDisposable.error(th, observer);
                        return true;
                    }
                } else {
                    tVar2.subscribe(observer);
                }
                return true;
            } catch (Throwable th2) {
                io.reactivex.exceptions.a.b(th2);
                EmptyDisposable.error(th2, observer);
                return true;
            }
        } catch (Throwable th3) {
            io.reactivex.exceptions.a.b(th3);
            EmptyDisposable.error(th3, observer);
            return true;
        }
    }

    public static <T, U> q<U> a(T t, g<? super T, ? extends t<? extends U>> gVar) {
        return io.reactivex.d.a.a((q<T>) new a<T>(t, gVar));
    }
}
