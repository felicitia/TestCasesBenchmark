package io.reactivex.internal.operators.observable;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.a;
import io.reactivex.internal.a.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.observers.BasicIntQueueDisposable;

public final class ObservableDoFinally<T> extends a<T, T> {
    final a b;

    static final class DoFinallyObserver<T> extends BasicIntQueueDisposable<T> implements Observer<T> {
        private static final long serialVersionUID = 4109457741734051389L;
        final Observer<? super T> actual;
        Disposable d;
        final a onFinally;
        b<T> qd;
        boolean syncFused;

        DoFinallyObserver(Observer<? super T> observer, a aVar) {
            this.actual = observer;
            this.onFinally = aVar;
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.d, disposable)) {
                this.d = disposable;
                if (disposable instanceof b) {
                    this.qd = (b) disposable;
                }
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            this.actual.onNext(t);
        }

        public void onError(Throwable th) {
            this.actual.onError(th);
            runFinally();
        }

        public void onComplete() {
            this.actual.onComplete();
            runFinally();
        }

        public void dispose() {
            this.d.dispose();
            runFinally();
        }

        public boolean isDisposed() {
            return this.d.isDisposed();
        }

        public int requestFusion(int i) {
            b<T> bVar = this.qd;
            if (bVar == null || (i & 4) != 0) {
                return 0;
            }
            int requestFusion = bVar.requestFusion(i);
            if (requestFusion != 0) {
                boolean z = true;
                if (requestFusion != 1) {
                    z = false;
                }
                this.syncFused = z;
            }
            return requestFusion;
        }

        public void clear() {
            this.qd.clear();
        }

        public boolean isEmpty() {
            return this.qd.isEmpty();
        }

        public T poll() throws Exception {
            T poll = this.qd.poll();
            if (poll == null && this.syncFused) {
                runFinally();
            }
            return poll;
        }

        /* access modifiers changed from: 0000 */
        public void runFinally() {
            if (compareAndSet(0, 1)) {
                try {
                    this.onFinally.a();
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    io.reactivex.d.a.a(th);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a(Observer<? super T> observer) {
        this.a.subscribe(new DoFinallyObserver(observer, this.b));
    }
}
