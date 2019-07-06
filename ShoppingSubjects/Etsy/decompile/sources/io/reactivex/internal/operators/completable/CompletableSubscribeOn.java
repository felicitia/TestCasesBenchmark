package io.reactivex.internal.operators.completable;

import io.reactivex.a;
import io.reactivex.c;
import io.reactivex.disposables.Disposable;
import io.reactivex.e;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.u;
import java.util.concurrent.atomic.AtomicReference;

public final class CompletableSubscribeOn extends a {
    final e a;
    final u b;

    static final class SubscribeOnObserver extends AtomicReference<Disposable> implements c, Disposable, Runnable {
        private static final long serialVersionUID = 7000911171163930287L;
        final c actual;
        final e source;
        final SequentialDisposable task = new SequentialDisposable();

        SubscribeOnObserver(c cVar, e eVar) {
            this.actual = cVar;
            this.source = eVar;
        }

        public void run() {
            this.source.a(this);
        }

        public void onSubscribe(Disposable disposable) {
            DisposableHelper.setOnce(this, disposable);
        }

        public void onError(Throwable th) {
            this.actual.onError(th);
        }

        public void onComplete() {
            this.actual.onComplete();
        }

        public void dispose() {
            DisposableHelper.dispose(this);
            this.task.dispose();
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((Disposable) get());
        }
    }

    public CompletableSubscribeOn(e eVar, u uVar) {
        this.a = eVar;
        this.b = uVar;
    }

    /* access modifiers changed from: protected */
    public void b(c cVar) {
        SubscribeOnObserver subscribeOnObserver = new SubscribeOnObserver(cVar, this.a);
        cVar.onSubscribe(subscribeOnObserver);
        subscribeOnObserver.task.replace(this.b.a((Runnable) subscribeOnObserver));
    }
}
