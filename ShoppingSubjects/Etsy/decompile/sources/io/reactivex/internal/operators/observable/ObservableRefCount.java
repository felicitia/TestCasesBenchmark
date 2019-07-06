package io.reactivex.internal.operators.observable;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

public final class ObservableRefCount<T> extends a<T, T> {
    final io.reactivex.c.a<? extends T> b;
    volatile io.reactivex.disposables.a c;
    final AtomicInteger d;
    final ReentrantLock e;

    final class ConnectionObserver extends AtomicReference<Disposable> implements Observer<T>, Disposable {
        private static final long serialVersionUID = 3813126992133394324L;
        final io.reactivex.disposables.a currentBase;
        final Disposable resource;
        final Observer<? super T> subscriber;

        ConnectionObserver(Observer<? super T> observer, io.reactivex.disposables.a aVar, Disposable disposable) {
            this.subscriber = observer;
            this.currentBase = aVar;
            this.resource = disposable;
        }

        public void onSubscribe(Disposable disposable) {
            DisposableHelper.setOnce(this, disposable);
        }

        public void onError(Throwable th) {
            cleanup();
            this.subscriber.onError(th);
        }

        public void onNext(T t) {
            this.subscriber.onNext(t);
        }

        public void onComplete() {
            cleanup();
            this.subscriber.onComplete();
        }

        public void dispose() {
            DisposableHelper.dispose(this);
            this.resource.dispose();
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((Disposable) get());
        }

        /* access modifiers changed from: 0000 */
        public void cleanup() {
            ObservableRefCount.this.e.lock();
            try {
                if (ObservableRefCount.this.c == this.currentBase) {
                    if (ObservableRefCount.this.b instanceof Disposable) {
                        ((Disposable) ObservableRefCount.this.b).dispose();
                    }
                    ObservableRefCount.this.c.dispose();
                    ObservableRefCount.this.c = new io.reactivex.disposables.a();
                    ObservableRefCount.this.d.set(0);
                }
            } finally {
                ObservableRefCount.this.e.unlock();
            }
        }
    }

    final class a implements Consumer<Disposable> {
        private final Observer<? super T> b;
        private final AtomicBoolean c;

        a(Observer<? super T> observer, AtomicBoolean atomicBoolean) {
            this.b = observer;
            this.c = atomicBoolean;
        }

        /* renamed from: a */
        public void accept(Disposable disposable) {
            try {
                ObservableRefCount.this.c.a(disposable);
                ObservableRefCount.this.a(this.b, ObservableRefCount.this.c);
            } finally {
                ObservableRefCount.this.e.unlock();
                this.c.set(false);
            }
        }
    }

    final class b implements Runnable {
        private final io.reactivex.disposables.a b;

        b(io.reactivex.disposables.a aVar) {
            this.b = aVar;
        }

        public void run() {
            ObservableRefCount.this.e.lock();
            try {
                if (ObservableRefCount.this.c == this.b && ObservableRefCount.this.d.decrementAndGet() == 0) {
                    if (ObservableRefCount.this.b instanceof Disposable) {
                        ((Disposable) ObservableRefCount.this.b).dispose();
                    }
                    ObservableRefCount.this.c.dispose();
                    ObservableRefCount.this.c = new io.reactivex.disposables.a();
                }
            } finally {
                ObservableRefCount.this.e.unlock();
            }
        }
    }

    public void a(Observer<? super T> observer) {
        this.e.lock();
        if (this.d.incrementAndGet() == 1) {
            AtomicBoolean atomicBoolean = new AtomicBoolean(true);
            try {
                this.b.b(a(observer, atomicBoolean));
            } finally {
                if (atomicBoolean.get()) {
                    this.e.unlock();
                }
            }
        } else {
            try {
                a(observer, this.c);
            } finally {
                this.e.unlock();
            }
        }
    }

    private Consumer<Disposable> a(Observer<? super T> observer, AtomicBoolean atomicBoolean) {
        return new a(observer, atomicBoolean);
    }

    /* access modifiers changed from: 0000 */
    public void a(Observer<? super T> observer, io.reactivex.disposables.a aVar) {
        ConnectionObserver connectionObserver = new ConnectionObserver(observer, aVar, a(aVar));
        observer.onSubscribe(connectionObserver);
        this.b.subscribe(connectionObserver);
    }

    private Disposable a(io.reactivex.disposables.a aVar) {
        return io.reactivex.disposables.b.a(new b(aVar));
    }
}
