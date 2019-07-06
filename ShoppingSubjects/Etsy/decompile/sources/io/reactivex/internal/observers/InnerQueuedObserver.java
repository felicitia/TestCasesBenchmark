package io.reactivex.internal.observers;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.a.b;
import io.reactivex.internal.a.g;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.util.i;
import java.util.concurrent.atomic.AtomicReference;

public final class InnerQueuedObserver<T> extends AtomicReference<Disposable> implements Observer<T>, Disposable {
    private static final long serialVersionUID = -5417183359794346637L;
    volatile boolean done;
    int fusionMode;
    final d<T> parent;
    final int prefetch;
    g<T> queue;

    public InnerQueuedObserver(d<T> dVar, int i) {
        this.parent = dVar;
        this.prefetch = i;
    }

    public void onSubscribe(Disposable disposable) {
        if (DisposableHelper.setOnce(this, disposable)) {
            if (disposable instanceof b) {
                b bVar = (b) disposable;
                int requestFusion = bVar.requestFusion(3);
                if (requestFusion == 1) {
                    this.fusionMode = requestFusion;
                    this.queue = bVar;
                    this.done = true;
                    this.parent.innerComplete(this);
                    return;
                } else if (requestFusion == 2) {
                    this.fusionMode = requestFusion;
                    this.queue = bVar;
                    return;
                }
            }
            this.queue = i.a(-this.prefetch);
        }
    }

    public void onNext(T t) {
        if (this.fusionMode == 0) {
            this.parent.innerNext(this, t);
        } else {
            this.parent.drain();
        }
    }

    public void onError(Throwable th) {
        this.parent.innerError(this, th);
    }

    public void onComplete() {
        this.parent.innerComplete(this);
    }

    public void dispose() {
        DisposableHelper.dispose(this);
    }

    public boolean isDisposed() {
        return DisposableHelper.isDisposed((Disposable) get());
    }

    public boolean isDone() {
        return this.done;
    }

    public void setDone() {
        this.done = true;
    }

    public g<T> queue() {
        return this.queue;
    }

    public int fusionMode() {
        return this.fusionMode;
    }
}
