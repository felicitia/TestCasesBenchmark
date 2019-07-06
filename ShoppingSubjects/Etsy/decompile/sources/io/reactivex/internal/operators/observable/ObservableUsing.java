package io.reactivex.internal.operators.observable;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.a;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.g;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.q;
import io.reactivex.t;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;

public final class ObservableUsing<T, D> extends q<T> {
    final Callable<? extends D> a;
    final g<? super D, ? extends t<? extends T>> b;
    final Consumer<? super D> c;
    final boolean d;

    static final class UsingObserver<T, D> extends AtomicBoolean implements Observer<T>, Disposable {
        private static final long serialVersionUID = 5904473792286235046L;
        final Observer<? super T> actual;
        final Consumer<? super D> disposer;
        final boolean eager;
        final D resource;
        Disposable s;

        UsingObserver(Observer<? super T> observer, D d, Consumer<? super D> consumer, boolean z) {
            this.actual = observer;
            this.resource = d;
            this.disposer = consumer;
            this.eager = z;
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.s, disposable)) {
                this.s = disposable;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            this.actual.onNext(t);
        }

        public void onError(Throwable th) {
            if (this.eager) {
                if (compareAndSet(false, true)) {
                    try {
                        this.disposer.accept(this.resource);
                    } catch (Throwable th2) {
                        a.b(th2);
                        th = new CompositeException(th, th2);
                    }
                }
                this.s.dispose();
                this.actual.onError(th);
                return;
            }
            this.actual.onError(th);
            this.s.dispose();
            disposeAfter();
        }

        public void onComplete() {
            if (this.eager) {
                if (compareAndSet(false, true)) {
                    try {
                        this.disposer.accept(this.resource);
                    } catch (Throwable th) {
                        a.b(th);
                        this.actual.onError(th);
                        return;
                    }
                }
                this.s.dispose();
                this.actual.onComplete();
            } else {
                this.actual.onComplete();
                this.s.dispose();
                disposeAfter();
            }
        }

        public void dispose() {
            disposeAfter();
            this.s.dispose();
        }

        public boolean isDisposed() {
            return get();
        }

        /* access modifiers changed from: 0000 */
        public void disposeAfter() {
            if (compareAndSet(false, true)) {
                try {
                    this.disposer.accept(this.resource);
                } catch (Throwable th) {
                    a.b(th);
                    io.reactivex.d.a.a(th);
                }
            }
        }
    }

    public void a(Observer<? super T> observer) {
        try {
            Object call = this.a.call();
            try {
                ((t) io.reactivex.internal.functions.a.a(this.b.apply(call), "The sourceSupplier returned a null ObservableSource")).subscribe(new UsingObserver(observer, call, this.c, this.d));
            } catch (Throwable th) {
                a.b(th);
                EmptyDisposable.error((Throwable) new CompositeException(th, th), observer);
            }
        } catch (Throwable th2) {
            a.b(th2);
            EmptyDisposable.error(th2, observer);
        }
    }
}
