package io.reactivex.internal.operators.maybe;

import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.a;
import io.reactivex.functions.d;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.m;
import io.reactivex.o;
import io.reactivex.v;
import io.reactivex.x;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class MaybeEqualSingle<T> extends v<Boolean> {
    final o<? extends T> a;
    final o<? extends T> b;
    final d<? super T, ? super T> c;

    static final class EqualCoordinator<T> extends AtomicInteger implements Disposable {
        final x<? super Boolean> actual;
        final d<? super T, ? super T> isEqual;
        final EqualObserver<T> observer1 = new EqualObserver<>(this);
        final EqualObserver<T> observer2 = new EqualObserver<>(this);

        EqualCoordinator(x<? super Boolean> xVar, d<? super T, ? super T> dVar) {
            super(2);
            this.actual = xVar;
            this.isEqual = dVar;
        }

        /* access modifiers changed from: 0000 */
        public void subscribe(o<? extends T> oVar, o<? extends T> oVar2) {
            oVar.a(this.observer1);
            oVar2.a(this.observer2);
        }

        public void dispose() {
            this.observer1.dispose();
            this.observer2.dispose();
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((Disposable) this.observer1.get());
        }

        /* access modifiers changed from: 0000 */
        public void done() {
            if (decrementAndGet() == 0) {
                Object obj = this.observer1.value;
                Object obj2 = this.observer2.value;
                if (obj == null || obj2 == null) {
                    this.actual.onSuccess(Boolean.valueOf(obj == null && obj2 == null));
                } else {
                    try {
                        this.actual.onSuccess(Boolean.valueOf(this.isEqual.a(obj, obj2)));
                    } catch (Throwable th) {
                        a.b(th);
                        this.actual.onError(th);
                    }
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public void error(EqualObserver<T> equalObserver, Throwable th) {
            if (getAndSet(0) > 0) {
                if (equalObserver == this.observer1) {
                    this.observer2.dispose();
                } else {
                    this.observer1.dispose();
                }
                this.actual.onError(th);
                return;
            }
            io.reactivex.d.a.a(th);
        }
    }

    static final class EqualObserver<T> extends AtomicReference<Disposable> implements m<T> {
        private static final long serialVersionUID = -3031974433025990931L;
        final EqualCoordinator<T> parent;
        Object value;

        EqualObserver(EqualCoordinator<T> equalCoordinator) {
            this.parent = equalCoordinator;
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }

        public void onSubscribe(Disposable disposable) {
            DisposableHelper.setOnce(this, disposable);
        }

        public void onSuccess(T t) {
            this.value = t;
            this.parent.done();
        }

        public void onError(Throwable th) {
            this.parent.error(this, th);
        }

        public void onComplete() {
            this.parent.done();
        }
    }

    /* access modifiers changed from: protected */
    public void b(x<? super Boolean> xVar) {
        EqualCoordinator equalCoordinator = new EqualCoordinator(xVar, this.c);
        xVar.onSubscribe(equalCoordinator);
        equalCoordinator.subscribe(this.a, this.b);
    }
}
