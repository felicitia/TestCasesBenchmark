package io.reactivex.internal.operators.observable;

import io.reactivex.Observer;
import io.reactivex.d.a;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.f;
import io.reactivex.internal.disposables.CancellableDisposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.q;
import io.reactivex.r;
import io.reactivex.s;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableCreate<T> extends q<T> {
    final s<T> a;

    static final class CreateEmitter<T> extends AtomicReference<Disposable> implements Disposable, r<T> {
        private static final long serialVersionUID = -3434801548987643227L;
        final Observer<? super T> observer;

        CreateEmitter(Observer<? super T> observer2) {
            this.observer = observer2;
        }

        public void onNext(T t) {
            if (t == null) {
                onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
                return;
            }
            if (!isDisposed()) {
                this.observer.onNext(t);
            }
        }

        public void onError(Throwable th) {
            if (!tryOnError(th)) {
                a.a(th);
            }
        }

        /* JADX INFO: finally extract failed */
        public boolean tryOnError(Throwable th) {
            if (th == null) {
                th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
            }
            if (isDisposed()) {
                return false;
            }
            try {
                this.observer.onError(th);
                dispose();
                return true;
            } catch (Throwable th2) {
                dispose();
                throw th2;
            }
        }

        public void onComplete() {
            if (!isDisposed()) {
                try {
                    this.observer.onComplete();
                } finally {
                    dispose();
                }
            }
        }

        public void setDisposable(Disposable disposable) {
            DisposableHelper.set(this, disposable);
        }

        public void setCancellable(f fVar) {
            setDisposable(new CancellableDisposable(fVar));
        }

        public r<T> serialize() {
            return new SerializedEmitter(this);
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((Disposable) get());
        }
    }

    static final class SerializedEmitter<T> extends AtomicInteger implements r<T> {
        private static final long serialVersionUID = 4883307006032401862L;
        volatile boolean done;
        final r<T> emitter;
        final AtomicThrowable error = new AtomicThrowable();
        final io.reactivex.internal.queue.a<T> queue = new io.reactivex.internal.queue.a<>(16);

        public r<T> serialize() {
            return this;
        }

        SerializedEmitter(r<T> rVar) {
            this.emitter = rVar;
        }

        public void onNext(T t) {
            if (!this.emitter.isDisposed() && !this.done) {
                if (t == null) {
                    onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
                    return;
                }
                if (get() != 0 || !compareAndSet(0, 1)) {
                    io.reactivex.internal.queue.a<T> aVar = this.queue;
                    synchronized (aVar) {
                        aVar.offer(t);
                    }
                    if (getAndIncrement() != 0) {
                        return;
                    }
                } else {
                    this.emitter.onNext(t);
                    if (decrementAndGet() == 0) {
                        return;
                    }
                }
                drainLoop();
            }
        }

        public void onError(Throwable th) {
            if (!tryOnError(th)) {
                a.a(th);
            }
        }

        public boolean tryOnError(Throwable th) {
            if (this.emitter.isDisposed() || this.done) {
                return false;
            }
            if (th == null) {
                th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
            }
            if (!this.error.addThrowable(th)) {
                return false;
            }
            this.done = true;
            drain();
            return true;
        }

        public void onComplete() {
            if (!this.emitter.isDisposed() && !this.done) {
                this.done = true;
                drain();
            }
        }

        /* access modifiers changed from: 0000 */
        public void drain() {
            if (getAndIncrement() == 0) {
                drainLoop();
            }
        }

        /* access modifiers changed from: 0000 */
        public void drainLoop() {
            r<T> rVar = this.emitter;
            io.reactivex.internal.queue.a<T> aVar = this.queue;
            AtomicThrowable atomicThrowable = this.error;
            int i = 1;
            while (!rVar.isDisposed()) {
                if (atomicThrowable.get() != null) {
                    aVar.clear();
                    rVar.onError(atomicThrowable.terminate());
                    return;
                }
                boolean z = this.done;
                Object poll = aVar.poll();
                boolean z2 = poll == null;
                if (z && z2) {
                    rVar.onComplete();
                    return;
                } else if (z2) {
                    i = addAndGet(-i);
                    if (i == 0) {
                        return;
                    }
                } else {
                    rVar.onNext(poll);
                }
            }
            aVar.clear();
        }

        public void setDisposable(Disposable disposable) {
            this.emitter.setDisposable(disposable);
        }

        public void setCancellable(f fVar) {
            this.emitter.setCancellable(fVar);
        }

        public boolean isDisposed() {
            return this.emitter.isDisposed();
        }
    }

    public ObservableCreate(s<T> sVar) {
        this.a = sVar;
    }

    /* access modifiers changed from: protected */
    public void a(Observer<? super T> observer) {
        CreateEmitter createEmitter = new CreateEmitter(observer);
        observer.onSubscribe(createEmitter);
        try {
            this.a.a(createEmitter);
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            createEmitter.onError(th);
        }
    }
}
