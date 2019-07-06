package io.reactivex.internal.operators.observable;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.c;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.q;
import java.util.Iterator;

/* compiled from: ObservableZipIterable */
public final class n<T, U, V> extends q<V> {
    final q<? extends T> a;
    final Iterable<U> b;
    final c<? super T, ? super U, ? extends V> c;

    /* compiled from: ObservableZipIterable */
    static final class a<T, U, V> implements Observer<T>, Disposable {
        final Observer<? super V> a;
        final Iterator<U> b;
        final c<? super T, ? super U, ? extends V> c;
        Disposable d;
        boolean e;

        a(Observer<? super V> observer, Iterator<U> it, c<? super T, ? super U, ? extends V> cVar) {
            this.a = observer;
            this.b = it;
            this.c = cVar;
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.d, disposable)) {
                this.d = disposable;
                this.a.onSubscribe(this);
            }
        }

        public void dispose() {
            this.d.dispose();
        }

        public boolean isDisposed() {
            return this.d.isDisposed();
        }

        public void onNext(T t) {
            if (!this.e) {
                try {
                    try {
                        this.a.onNext(io.reactivex.internal.functions.a.a(this.c.apply(t, io.reactivex.internal.functions.a.a(this.b.next(), "The iterator returned a null value")), "The zipper function returned a null value"));
                        try {
                            if (!this.b.hasNext()) {
                                this.e = true;
                                this.d.dispose();
                                this.a.onComplete();
                            }
                        } catch (Throwable th) {
                            io.reactivex.exceptions.a.b(th);
                            a(th);
                        }
                    } catch (Throwable th2) {
                        io.reactivex.exceptions.a.b(th2);
                        a(th2);
                    }
                } catch (Throwable th3) {
                    io.reactivex.exceptions.a.b(th3);
                    a(th3);
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void a(Throwable th) {
            this.e = true;
            this.d.dispose();
            this.a.onError(th);
        }

        public void onError(Throwable th) {
            if (this.e) {
                io.reactivex.d.a.a(th);
                return;
            }
            this.e = true;
            this.a.onError(th);
        }

        public void onComplete() {
            if (!this.e) {
                this.e = true;
                this.a.onComplete();
            }
        }
    }

    public n(q<? extends T> qVar, Iterable<U> iterable, c<? super T, ? super U, ? extends V> cVar) {
        this.a = qVar;
        this.b = iterable;
        this.c = cVar;
    }

    public void a(Observer<? super V> observer) {
        try {
            Iterator it = (Iterator) io.reactivex.internal.functions.a.a(this.b.iterator(), "The iterator returned by other is null");
            try {
                if (!it.hasNext()) {
                    EmptyDisposable.complete(observer);
                } else {
                    this.a.subscribe(new a(observer, it, this.c));
                }
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                EmptyDisposable.error(th, observer);
            }
        } catch (Throwable th2) {
            io.reactivex.exceptions.a.b(th2);
            EmptyDisposable.error(th2, observer);
        }
    }
}
