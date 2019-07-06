package io.reactivex.internal.operators.observable;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;

public final class ObservableBuffer<T, U extends Collection<? super T>> extends a<T, U> {
    final int b;
    final int c;
    final Callable<U> d;

    static final class BufferSkipObserver<T, U extends Collection<? super T>> extends AtomicBoolean implements Observer<T>, Disposable {
        private static final long serialVersionUID = -8223395059921494546L;
        final Observer<? super U> actual;
        final Callable<U> bufferSupplier;
        final ArrayDeque<U> buffers = new ArrayDeque<>();
        final int count;
        long index;
        Disposable s;
        final int skip;

        BufferSkipObserver(Observer<? super U> observer, int i, int i2, Callable<U> callable) {
            this.actual = observer;
            this.count = i;
            this.skip = i2;
            this.bufferSupplier = callable;
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.s, disposable)) {
                this.s = disposable;
                this.actual.onSubscribe(this);
            }
        }

        public void dispose() {
            this.s.dispose();
        }

        public boolean isDisposed() {
            return this.s.isDisposed();
        }

        public void onNext(T t) {
            long j = this.index;
            this.index = j + 1;
            if (j % ((long) this.skip) == 0) {
                try {
                    this.buffers.offer((Collection) io.reactivex.internal.functions.a.a(this.bufferSupplier.call(), "The bufferSupplier returned a null collection. Null values are generally not allowed in 2.x operators and sources."));
                } catch (Throwable th) {
                    this.buffers.clear();
                    this.s.dispose();
                    this.actual.onError(th);
                    return;
                }
            }
            Iterator it = this.buffers.iterator();
            while (it.hasNext()) {
                Collection collection = (Collection) it.next();
                collection.add(t);
                if (this.count <= collection.size()) {
                    it.remove();
                    this.actual.onNext(collection);
                }
            }
        }

        public void onError(Throwable th) {
            this.buffers.clear();
            this.actual.onError(th);
        }

        public void onComplete() {
            while (!this.buffers.isEmpty()) {
                this.actual.onNext(this.buffers.poll());
            }
            this.actual.onComplete();
        }
    }

    static final class a<T, U extends Collection<? super T>> implements Observer<T>, Disposable {
        final Observer<? super U> a;
        final int b;
        final Callable<U> c;
        U d;
        int e;
        Disposable f;

        a(Observer<? super U> observer, int i, Callable<U> callable) {
            this.a = observer;
            this.b = i;
            this.c = callable;
        }

        /* access modifiers changed from: 0000 */
        public boolean a() {
            try {
                this.d = (Collection) io.reactivex.internal.functions.a.a(this.c.call(), "Empty buffer supplied");
                return true;
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                this.d = null;
                if (this.f == null) {
                    EmptyDisposable.error(th, this.a);
                } else {
                    this.f.dispose();
                    this.a.onError(th);
                }
                return false;
            }
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
            U u = this.d;
            if (u != null) {
                u.add(t);
                int i = this.e + 1;
                this.e = i;
                if (i >= this.b) {
                    this.a.onNext(u);
                    this.e = 0;
                    a();
                }
            }
        }

        public void onError(Throwable th) {
            this.d = null;
            this.a.onError(th);
        }

        public void onComplete() {
            U u = this.d;
            this.d = null;
            if (u != null && !u.isEmpty()) {
                this.a.onNext(u);
            }
            this.a.onComplete();
        }
    }

    /* access modifiers changed from: protected */
    public void a(Observer<? super U> observer) {
        if (this.c == this.b) {
            a aVar = new a(observer, this.b, this.d);
            if (aVar.a()) {
                this.a.subscribe(aVar);
                return;
            }
            return;
        }
        this.a.subscribe(new BufferSkipObserver(observer, this.b, this.c, this.d));
    }
}
