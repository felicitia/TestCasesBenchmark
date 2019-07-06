package io.reactivex.internal.operators.single;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.a;
import io.reactivex.functions.g;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.observers.BasicIntQueueDisposable;
import io.reactivex.q;
import io.reactivex.x;
import io.reactivex.z;
import java.util.Iterator;

public final class SingleFlatMapIterableObservable<T, R> extends q<R> {
    final z<T> a;
    final g<? super T, ? extends Iterable<? extends R>> b;

    static final class FlatMapIterableObserver<T, R> extends BasicIntQueueDisposable<R> implements x<T> {
        private static final long serialVersionUID = -8938804753851907758L;
        final Observer<? super R> actual;
        volatile boolean cancelled;
        Disposable d;
        volatile Iterator<? extends R> it;
        final g<? super T, ? extends Iterable<? extends R>> mapper;
        boolean outputFused;

        FlatMapIterableObserver(Observer<? super R> observer, g<? super T, ? extends Iterable<? extends R>> gVar) {
            this.actual = observer;
            this.mapper = gVar;
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.d, disposable)) {
                this.d = disposable;
                this.actual.onSubscribe(this);
            }
        }

        public void onSuccess(T t) {
            Observer<? super R> observer = this.actual;
            try {
                Iterator<? extends R> it2 = ((Iterable) this.mapper.apply(t)).iterator();
                if (!it2.hasNext()) {
                    observer.onComplete();
                } else if (this.outputFused) {
                    this.it = it2;
                    observer.onNext(null);
                    observer.onComplete();
                } else {
                    while (!this.cancelled) {
                        try {
                            observer.onNext(it2.next());
                            if (!this.cancelled) {
                                try {
                                    if (!it2.hasNext()) {
                                        observer.onComplete();
                                        return;
                                    }
                                } catch (Throwable th) {
                                    a.b(th);
                                    observer.onError(th);
                                    return;
                                }
                            } else {
                                return;
                            }
                        } catch (Throwable th2) {
                            a.b(th2);
                            observer.onError(th2);
                            return;
                        }
                    }
                }
            } catch (Throwable th3) {
                a.b(th3);
                this.actual.onError(th3);
            }
        }

        public void onError(Throwable th) {
            this.d = DisposableHelper.DISPOSED;
            this.actual.onError(th);
        }

        public void dispose() {
            this.cancelled = true;
            this.d.dispose();
            this.d = DisposableHelper.DISPOSED;
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        public int requestFusion(int i) {
            if ((i & 2) == 0) {
                return 0;
            }
            this.outputFused = true;
            return 2;
        }

        public void clear() {
            this.it = null;
        }

        public boolean isEmpty() {
            return this.it == null;
        }

        public R poll() throws Exception {
            Iterator<? extends R> it2 = this.it;
            if (it2 == null) {
                return null;
            }
            R a = io.reactivex.internal.functions.a.a(it2.next(), "The iterator returned a null value");
            if (!it2.hasNext()) {
                this.it = null;
            }
            return a;
        }
    }

    /* access modifiers changed from: protected */
    public void a(Observer<? super R> observer) {
        this.a.a(new FlatMapIterableObserver(observer, this.b));
    }
}
