package io.reactivex.internal.operators.single;

import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.u;
import io.reactivex.v;
import io.reactivex.x;
import io.reactivex.z;
import java.util.concurrent.atomic.AtomicReference;

public final class SingleSubscribeOn<T> extends v<T> {
    final z<? extends T> a;
    final u b;

    static final class SubscribeOnObserver<T> extends AtomicReference<Disposable> implements Disposable, x<T>, Runnable {
        private static final long serialVersionUID = 7000911171163930287L;
        final x<? super T> actual;
        final z<? extends T> source;
        final SequentialDisposable task = new SequentialDisposable();

        SubscribeOnObserver(x<? super T> xVar, z<? extends T> zVar) {
            this.actual = xVar;
            this.source = zVar;
        }

        public void onSubscribe(Disposable disposable) {
            DisposableHelper.setOnce(this, disposable);
        }

        public void onSuccess(T t) {
            this.actual.onSuccess(t);
        }

        public void onError(Throwable th) {
            this.actual.onError(th);
        }

        public void dispose() {
            DisposableHelper.dispose(this);
            this.task.dispose();
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((Disposable) get());
        }

        public void run() {
            this.source.a(this);
        }
    }

    public SingleSubscribeOn(z<? extends T> zVar, u uVar) {
        this.a = zVar;
        this.b = uVar;
    }

    /* access modifiers changed from: protected */
    public void b(x<? super T> xVar) {
        SubscribeOnObserver subscribeOnObserver = new SubscribeOnObserver(xVar, this.a);
        xVar.onSubscribe(subscribeOnObserver);
        subscribeOnObserver.task.replace(this.b.a((Runnable) subscribeOnObserver));
    }
}
