package io.reactivex.internal.operators.observable;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.a;
import io.reactivex.functions.d;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.t;
import java.util.concurrent.atomic.AtomicInteger;

public final class ObservableRetryBiPredicate<T> extends a<T, T> {
    final d<? super Integer, ? super Throwable> b;

    static final class RetryBiObserver<T> extends AtomicInteger implements Observer<T> {
        private static final long serialVersionUID = -7098360935104053232L;
        final Observer<? super T> actual;
        final d<? super Integer, ? super Throwable> predicate;
        int retries;
        final SequentialDisposable sa;
        final t<? extends T> source;

        RetryBiObserver(Observer<? super T> observer, d<? super Integer, ? super Throwable> dVar, SequentialDisposable sequentialDisposable, t<? extends T> tVar) {
            this.actual = observer;
            this.sa = sequentialDisposable;
            this.source = tVar;
            this.predicate = dVar;
        }

        public void onSubscribe(Disposable disposable) {
            this.sa.update(disposable);
        }

        public void onNext(T t) {
            this.actual.onNext(t);
        }

        public void onError(Throwable th) {
            try {
                d<? super Integer, ? super Throwable> dVar = this.predicate;
                int i = this.retries + 1;
                this.retries = i;
                if (!dVar.a(Integer.valueOf(i), th)) {
                    this.actual.onError(th);
                } else {
                    subscribeNext();
                }
            } catch (Throwable th2) {
                a.b(th2);
                this.actual.onError(new CompositeException(th, th2));
            }
        }

        public void onComplete() {
            this.actual.onComplete();
        }

        /* access modifiers changed from: 0000 */
        public void subscribeNext() {
            if (getAndIncrement() == 0) {
                int i = 1;
                while (!this.sa.isDisposed()) {
                    this.source.subscribe(this);
                    i = addAndGet(-i);
                    if (i == 0) {
                    }
                }
            }
        }
    }

    public void a(Observer<? super T> observer) {
        SequentialDisposable sequentialDisposable = new SequentialDisposable();
        observer.onSubscribe(sequentialDisposable);
        new RetryBiObserver(observer, this.b, sequentialDisposable, this.a).subscribeNext();
    }
}
