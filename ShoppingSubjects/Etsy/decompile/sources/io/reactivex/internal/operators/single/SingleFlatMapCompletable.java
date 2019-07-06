package io.reactivex.internal.operators.single;

import io.reactivex.a;
import io.reactivex.c;
import io.reactivex.disposables.Disposable;
import io.reactivex.e;
import io.reactivex.functions.g;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.x;
import io.reactivex.z;
import java.util.concurrent.atomic.AtomicReference;

public final class SingleFlatMapCompletable<T> extends a {
    final z<T> a;
    final g<? super T, ? extends e> b;

    static final class FlatMapCompletableObserver<T> extends AtomicReference<Disposable> implements c, Disposable, x<T> {
        private static final long serialVersionUID = -2177128922851101253L;
        final c actual;
        final g<? super T, ? extends e> mapper;

        FlatMapCompletableObserver(c cVar, g<? super T, ? extends e> gVar) {
            this.actual = cVar;
            this.mapper = gVar;
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((Disposable) get());
        }

        public void onSubscribe(Disposable disposable) {
            DisposableHelper.replace(this, disposable);
        }

        public void onSuccess(T t) {
            try {
                e eVar = (e) io.reactivex.internal.functions.a.a(this.mapper.apply(t), "The mapper returned a null CompletableSource");
                if (!isDisposed()) {
                    eVar.a(this);
                }
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                onError(th);
            }
        }

        public void onError(Throwable th) {
            this.actual.onError(th);
        }

        public void onComplete() {
            this.actual.onComplete();
        }
    }

    /* access modifiers changed from: protected */
    public void b(c cVar) {
        FlatMapCompletableObserver flatMapCompletableObserver = new FlatMapCompletableObserver(cVar, this.b);
        cVar.onSubscribe(flatMapCompletableObserver);
        this.a.a(flatMapCompletableObserver);
    }
}
