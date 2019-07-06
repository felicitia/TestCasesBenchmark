package io.reactivex.internal.operators.observable;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.g;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.a;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.e;
import io.reactivex.q;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.c;
import io.reactivex.t;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableRetryWhen<T> extends a<T, T> {
    final g<? super q<Throwable>, ? extends t<?>> b;

    static final class RepeatWhenObserver<T> extends AtomicInteger implements Observer<T>, Disposable {
        private static final long serialVersionUID = 802743776666017014L;
        volatile boolean active;
        final Observer<? super T> actual;
        final AtomicReference<Disposable> d = new AtomicReference<>();
        final AtomicThrowable error = new AtomicThrowable();
        final InnerRepeatObserver inner = new InnerRepeatObserver<>();
        final c<Throwable> signaller;
        final t<T> source;
        final AtomicInteger wip = new AtomicInteger();

        final class InnerRepeatObserver extends AtomicReference<Disposable> implements Observer<Object> {
            private static final long serialVersionUID = 3254781284376480842L;

            InnerRepeatObserver() {
            }

            public void onSubscribe(Disposable disposable) {
                DisposableHelper.setOnce(this, disposable);
            }

            public void onNext(Object obj) {
                RepeatWhenObserver.this.innerNext();
            }

            public void onError(Throwable th) {
                RepeatWhenObserver.this.innerError(th);
            }

            public void onComplete() {
                RepeatWhenObserver.this.innerComplete();
            }
        }

        RepeatWhenObserver(Observer<? super T> observer, c<Throwable> cVar, t<T> tVar) {
            this.actual = observer;
            this.signaller = cVar;
            this.source = tVar;
        }

        public void onSubscribe(Disposable disposable) {
            DisposableHelper.replace(this.d, disposable);
        }

        public void onNext(T t) {
            e.a(this.actual, t, (AtomicInteger) this, this.error);
        }

        public void onError(Throwable th) {
            this.active = false;
            this.signaller.onNext(th);
        }

        public void onComplete() {
            DisposableHelper.dispose(this.inner);
            e.a(this.actual, (AtomicInteger) this, this.error);
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((Disposable) this.d.get());
        }

        public void dispose() {
            DisposableHelper.dispose(this.d);
            DisposableHelper.dispose(this.inner);
        }

        /* access modifiers changed from: 0000 */
        public void innerNext() {
            subscribeNext();
        }

        /* access modifiers changed from: 0000 */
        public void innerError(Throwable th) {
            DisposableHelper.dispose(this.d);
            e.a(this.actual, th, (AtomicInteger) this, this.error);
        }

        /* access modifiers changed from: 0000 */
        public void innerComplete() {
            DisposableHelper.dispose(this.d);
            e.a(this.actual, (AtomicInteger) this, this.error);
        }

        /* access modifiers changed from: 0000 */
        public void subscribeNext() {
            if (this.wip.getAndIncrement() == 0) {
                while (!isDisposed()) {
                    if (!this.active) {
                        this.active = true;
                        this.source.subscribe(this);
                    }
                    if (this.wip.decrementAndGet() == 0) {
                    }
                }
            }
        }
    }

    public ObservableRetryWhen(t<T> tVar, g<? super q<Throwable>, ? extends t<?>> gVar) {
        super(tVar);
        this.b = gVar;
    }

    /* access modifiers changed from: protected */
    public void a(Observer<? super T> observer) {
        c c = PublishSubject.a().c();
        try {
            t tVar = (t) a.a(this.b.apply(c), "The handler returned a null ObservableSource");
            RepeatWhenObserver repeatWhenObserver = new RepeatWhenObserver(observer, c, this.a);
            observer.onSubscribe(repeatWhenObserver);
            tVar.subscribe(repeatWhenObserver.inner);
            repeatWhenObserver.subscribeNext();
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            EmptyDisposable.error(th, observer);
        }
    }
}
