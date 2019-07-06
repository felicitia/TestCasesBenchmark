package io.reactivex.internal.operators.observable;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.a;
import io.reactivex.functions.e;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.t;
import java.util.concurrent.atomic.AtomicInteger;

public final class ObservableRepeatUntil<T> extends a<T, T> {
    final e b;

    static final class RepeatUntilObserver<T> extends AtomicInteger implements Observer<T> {
        private static final long serialVersionUID = -7098360935104053232L;
        final Observer<? super T> actual;
        final SequentialDisposable sd;
        final t<? extends T> source;
        final e stop;

        RepeatUntilObserver(Observer<? super T> observer, e eVar, SequentialDisposable sequentialDisposable, t<? extends T> tVar) {
            this.actual = observer;
            this.sd = sequentialDisposable;
            this.source = tVar;
            this.stop = eVar;
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
            try {
                if (this.stop.getAsBoolean()) {
                    this.actual.onComplete();
                } else {
                    subscribeNext();
                }
            } catch (Throwable th) {
                a.b(th);
                this.actual.onError(th);
            }
        }

        /* access modifiers changed from: 0000 */
        public void subscribeNext() {
            if (getAndIncrement() == 0) {
                int i = 1;
                do {
                    this.source.subscribe(this);
                    i = addAndGet(-i);
                } while (i != 0);
            }
        }
    }

    public void a(Observer<? super T> observer) {
        SequentialDisposable sequentialDisposable = new SequentialDisposable();
        observer.onSubscribe(sequentialDisposable);
        new RepeatUntilObserver(observer, this.b, sequentialDisposable, this.a).subscribeNext();
    }
}
