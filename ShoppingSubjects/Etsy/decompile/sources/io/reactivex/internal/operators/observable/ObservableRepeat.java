package io.reactivex.internal.operators.observable;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.t;
import java.util.concurrent.atomic.AtomicInteger;

public final class ObservableRepeat<T> extends a<T, T> {
    final long b;

    static final class RepeatObserver<T> extends AtomicInteger implements Observer<T> {
        private static final long serialVersionUID = -7098360935104053232L;
        final Observer<? super T> actual;
        long remaining;
        final SequentialDisposable sd;
        final t<? extends T> source;

        RepeatObserver(Observer<? super T> observer, long j, SequentialDisposable sequentialDisposable, t<? extends T> tVar) {
            this.actual = observer;
            this.sd = sequentialDisposable;
            this.source = tVar;
            this.remaining = j;
        }

        public void onSubscribe(Disposable disposable) {
            this.sd.replace(disposable);
        }

        public void onNext(T t) {
            this.actual.onNext(t);
        }

        public void onError(Throwable th) {
            this.actual.onError(th);
        }

        public void onComplete() {
            long j = this.remaining;
            if (j != Long.MAX_VALUE) {
                this.remaining = j - 1;
            }
            if (j != 0) {
                subscribeNext();
            } else {
                this.actual.onComplete();
            }
        }

        /* access modifiers changed from: 0000 */
        public void subscribeNext() {
            if (getAndIncrement() == 0) {
                int i = 1;
                while (!this.sd.isDisposed()) {
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
        long j = Long.MAX_VALUE;
        if (this.b != Long.MAX_VALUE) {
            j = this.b - 1;
        }
        RepeatObserver repeatObserver = new RepeatObserver(observer, j, sequentialDisposable, this.a);
        repeatObserver.subscribeNext();
    }
}
