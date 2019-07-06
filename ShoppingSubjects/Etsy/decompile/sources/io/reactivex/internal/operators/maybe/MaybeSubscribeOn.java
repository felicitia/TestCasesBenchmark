package io.reactivex.internal.operators.maybe;

import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.m;
import io.reactivex.o;
import io.reactivex.u;
import java.util.concurrent.atomic.AtomicReference;

public final class MaybeSubscribeOn<T> extends a<T, T> {
    final u b;

    static final class SubscribeOnMaybeObserver<T> extends AtomicReference<Disposable> implements Disposable, m<T> {
        private static final long serialVersionUID = 8571289934935992137L;
        final m<? super T> actual;
        final SequentialDisposable task = new SequentialDisposable();

        SubscribeOnMaybeObserver(m<? super T> mVar) {
            this.actual = mVar;
        }

        public void dispose() {
            DisposableHelper.dispose(this);
            this.task.dispose();
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((Disposable) get());
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

        public void onComplete() {
            this.actual.onComplete();
        }
    }

    static final class a<T> implements Runnable {
        final m<? super T> a;
        final o<T> b;

        a(m<? super T> mVar, o<T> oVar) {
            this.a = mVar;
            this.b = oVar;
        }

        public void run() {
            this.b.a(this.a);
        }
    }

    /* access modifiers changed from: protected */
    public void b(m<? super T> mVar) {
        SubscribeOnMaybeObserver subscribeOnMaybeObserver = new SubscribeOnMaybeObserver(mVar);
        mVar.onSubscribe(subscribeOnMaybeObserver);
        subscribeOnMaybeObserver.task.replace(this.b.a((Runnable) new a(subscribeOnMaybeObserver, this.a)));
    }
}
