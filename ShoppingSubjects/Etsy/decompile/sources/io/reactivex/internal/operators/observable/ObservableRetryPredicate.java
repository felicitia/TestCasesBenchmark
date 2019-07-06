package io.reactivex.internal.operators.observable;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.a;
import io.reactivex.functions.i;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.q;
import io.reactivex.t;
import java.util.concurrent.atomic.AtomicInteger;

public final class ObservableRetryPredicate<T> extends a<T, T> {
    final i<? super Throwable> b;
    final long c;

    static final class RepeatObserver<T> extends AtomicInteger implements Observer<T> {
        private static final long serialVersionUID = -7098360935104053232L;
        final Observer<? super T> actual;
        final i<? super Throwable> predicate;
        long remaining;
        final SequentialDisposable sa;
        final t<? extends T> source;

        RepeatObserver(Observer<? super T> observer, long j, i<? super Throwable> iVar, SequentialDisposable sequentialDisposable, t<? extends T> tVar) {
            this.actual = observer;
            this.sa = sequentialDisposable;
            this.source = tVar;
            this.predicate = iVar;
            this.remaining = j;
        }

        public void onSubscribe(Disposable disposable) {
            this.sa.update(disposable);
        }

        public void onNext(T t) {
            this.actual.onNext(t);
        }

        public void onError(Throwable th) {
            long j = this.remaining;
            if (j != Long.MAX_VALUE) {
                this.remaining = j - 1;
            }
            if (j == 0) {
                this.actual.onError(th);
            } else {
                try {
                    if (!this.predicate.test(th)) {
                        this.actual.onError(th);
                        return;
                    }
                    subscribeNext();
                } catch (Throwable th2) {
                    a.b(th2);
                    this.actual.onError(new CompositeException(th, th2));
                }
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

    public ObservableRetryPredicate(q<T> qVar, long j, i<? super Throwable> iVar) {
        super(qVar);
        this.b = iVar;
        this.c = j;
    }

    public void a(Observer<? super T> observer) {
        SequentialDisposable sequentialDisposable = new SequentialDisposable();
        observer.onSubscribe(sequentialDisposable);
        RepeatObserver repeatObserver = new RepeatObserver(observer, this.c, this.b, sequentialDisposable, this.a);
        repeatObserver.subscribeNext();
    }
}
