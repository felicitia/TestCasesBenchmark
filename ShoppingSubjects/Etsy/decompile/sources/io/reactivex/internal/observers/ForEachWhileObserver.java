package io.reactivex.internal.observers;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.a;
import io.reactivex.functions.i;
import io.reactivex.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicReference;

public final class ForEachWhileObserver<T> extends AtomicReference<Disposable> implements Observer<T>, Disposable {
    private static final long serialVersionUID = -4403180040475402120L;
    boolean done;
    final a onComplete;
    final Consumer<? super Throwable> onError;
    final i<? super T> onNext;

    public ForEachWhileObserver(i<? super T> iVar, Consumer<? super Throwable> consumer, a aVar) {
        this.onNext = iVar;
        this.onError = consumer;
        this.onComplete = aVar;
    }

    public void onSubscribe(Disposable disposable) {
        DisposableHelper.setOnce(this, disposable);
    }

    public void onNext(T t) {
        if (!this.done) {
            try {
                if (!this.onNext.test(t)) {
                    dispose();
                    onComplete();
                }
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                dispose();
                onError(th);
            }
        }
    }

    public void onError(Throwable th) {
        if (this.done) {
            io.reactivex.d.a.a(th);
            return;
        }
        this.done = true;
        try {
            this.onError.accept(th);
        } catch (Throwable th2) {
            io.reactivex.exceptions.a.b(th2);
            io.reactivex.d.a.a((Throwable) new CompositeException(th, th2));
        }
    }

    public void onComplete() {
        if (!this.done) {
            this.done = true;
            try {
                this.onComplete.a();
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                io.reactivex.d.a.a(th);
            }
        }
    }

    public void dispose() {
        DisposableHelper.dispose(this);
    }

    public boolean isDisposed() {
        return DisposableHelper.isDisposed((Disposable) get());
    }
}
