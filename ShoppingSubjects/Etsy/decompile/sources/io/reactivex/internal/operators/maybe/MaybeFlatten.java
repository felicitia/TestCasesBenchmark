package io.reactivex.internal.operators.maybe;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.g;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.m;
import io.reactivex.o;
import java.util.concurrent.atomic.AtomicReference;

public final class MaybeFlatten<T, R> extends a<T, R> {
    final g<? super T, ? extends o<? extends R>> b;

    static final class FlatMapMaybeObserver<T, R> extends AtomicReference<Disposable> implements Disposable, m<T> {
        private static final long serialVersionUID = 4375739915521278546L;
        final m<? super R> actual;
        Disposable d;
        final g<? super T, ? extends o<? extends R>> mapper;

        final class a implements m<R> {
            a() {
            }

            public void onSubscribe(Disposable disposable) {
                DisposableHelper.setOnce(FlatMapMaybeObserver.this, disposable);
            }

            public void onSuccess(R r) {
                FlatMapMaybeObserver.this.actual.onSuccess(r);
            }

            public void onError(Throwable th) {
                FlatMapMaybeObserver.this.actual.onError(th);
            }

            public void onComplete() {
                FlatMapMaybeObserver.this.actual.onComplete();
            }
        }

        FlatMapMaybeObserver(m<? super R> mVar, g<? super T, ? extends o<? extends R>> gVar) {
            this.actual = mVar;
            this.mapper = gVar;
        }

        public void dispose() {
            DisposableHelper.dispose(this);
            this.d.dispose();
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((Disposable) get());
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.d, disposable)) {
                this.d = disposable;
                this.actual.onSubscribe(this);
            }
        }

        public void onSuccess(T t) {
            try {
                o oVar = (o) io.reactivex.internal.functions.a.a(this.mapper.apply(t), "The mapper returned a null MaybeSource");
                if (!isDisposed()) {
                    oVar.a(new a());
                }
            } catch (Exception e) {
                io.reactivex.exceptions.a.b(e);
                this.actual.onError(e);
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
    public void b(m<? super R> mVar) {
        this.a.a(new FlatMapMaybeObserver(mVar, this.b));
    }
}
