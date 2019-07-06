package io.reactivex.internal.operators.observable;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.t;

/* compiled from: ObservableDoOnEach */
public final class b<T> extends a<T, T> {
    final Consumer<? super T> b;
    final Consumer<? super Throwable> c;
    final io.reactivex.functions.a d;
    final io.reactivex.functions.a e;

    /* compiled from: ObservableDoOnEach */
    static final class a<T> implements Observer<T>, Disposable {
        final Observer<? super T> a;
        final Consumer<? super T> b;
        final Consumer<? super Throwable> c;
        final io.reactivex.functions.a d;
        final io.reactivex.functions.a e;
        Disposable f;
        boolean g;

        a(Observer<? super T> observer, Consumer<? super T> consumer, Consumer<? super Throwable> consumer2, io.reactivex.functions.a aVar, io.reactivex.functions.a aVar2) {
            this.a = observer;
            this.b = consumer;
            this.c = consumer2;
            this.d = aVar;
            this.e = aVar2;
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.f, disposable)) {
                this.f = disposable;
                this.a.onSubscribe(this);
            }
        }

        public void dispose() {
            this.f.dispose();
        }

        public boolean isDisposed() {
            return this.f.isDisposed();
        }

        public void onNext(T t) {
            if (!this.g) {
                try {
                    this.b.accept(t);
                    this.a.onNext(t);
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    this.f.dispose();
                    onError(th);
                }
            }
        }

        public void onError(Throwable th) {
            if (this.g) {
                io.reactivex.d.a.a(th);
                return;
            }
            this.g = true;
            try {
                this.c.accept(th);
            } catch (Throwable th2) {
                io.reactivex.exceptions.a.b(th2);
                th = new CompositeException(th, th2);
            }
            this.a.onError(th);
            try {
                this.e.a();
            } catch (Throwable th3) {
                io.reactivex.exceptions.a.b(th3);
                io.reactivex.d.a.a(th3);
            }
        }

        public void onComplete() {
            if (!this.g) {
                try {
                    this.d.a();
                    this.g = true;
                    this.a.onComplete();
                    try {
                        this.e.a();
                    } catch (Throwable th) {
                        io.reactivex.exceptions.a.b(th);
                        io.reactivex.d.a.a(th);
                    }
                } catch (Throwable th2) {
                    io.reactivex.exceptions.a.b(th2);
                    onError(th2);
                }
            }
        }
    }

    public b(t<T> tVar, Consumer<? super T> consumer, Consumer<? super Throwable> consumer2, io.reactivex.functions.a aVar, io.reactivex.functions.a aVar2) {
        super(tVar);
        this.b = consumer;
        this.c = consumer2;
        this.d = aVar;
        this.e = aVar2;
    }

    public void a(Observer<? super T> observer) {
        t tVar = this.a;
        a aVar = new a(observer, this.b, this.c, this.d, this.e);
        tVar.subscribe(aVar);
    }
}
