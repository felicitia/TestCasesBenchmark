package io.reactivex.internal.operators.observable;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import java.util.ArrayDeque;

public final class ObservableTakeLast<T> extends a<T, T> {
    final int b;

    static final class TakeLastObserver<T> extends ArrayDeque<T> implements Observer<T>, Disposable {
        private static final long serialVersionUID = 7240042530241604978L;
        final Observer<? super T> actual;
        volatile boolean cancelled;
        final int count;
        Disposable s;

        TakeLastObserver(Observer<? super T> observer, int i) {
            this.actual = observer;
            this.count = i;
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.s, disposable)) {
                this.s = disposable;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            if (this.count == size()) {
                poll();
            }
            offer(t);
        }

        public void onError(Throwable th) {
            this.actual.onError(th);
        }

        public void onComplete() {
            Observer<? super T> observer = this.actual;
            while (!this.cancelled) {
                Object poll = poll();
                if (poll == null) {
                    if (!this.cancelled) {
                        observer.onComplete();
                    }
                    return;
                }
                observer.onNext(poll);
            }
        }

        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.s.dispose();
            }
        }

        public boolean isDisposed() {
            return this.cancelled;
        }
    }

    public void a(Observer<? super T> observer) {
        this.a.subscribe(new TakeLastObserver(observer, this.b));
    }
}
