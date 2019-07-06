package io.reactivex.internal.operators.maybe;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.c;
import io.reactivex.functions.g;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.a;
import io.reactivex.m;
import io.reactivex.o;
import java.util.concurrent.atomic.AtomicReference;

public final class MaybeFlatMapBiSelector<T, U, R> extends a<T, R> {
    final g<? super T, ? extends o<? extends U>> b;
    final c<? super T, ? super U, ? extends R> c;

    static final class FlatMapBiMainObserver<T, U, R> implements Disposable, m<T> {
        final g<? super T, ? extends o<? extends U>> a;
        final InnerObserver<T, U, R> b;

        static final class InnerObserver<T, U, R> extends AtomicReference<Disposable> implements m<U> {
            private static final long serialVersionUID = -2897979525538174559L;
            final m<? super R> actual;
            final c<? super T, ? super U, ? extends R> resultSelector;
            T value;

            InnerObserver(m<? super R> mVar, c<? super T, ? super U, ? extends R> cVar) {
                this.actual = mVar;
                this.resultSelector = cVar;
            }

            public void onSubscribe(Disposable disposable) {
                DisposableHelper.setOnce(this, disposable);
            }

            public void onSuccess(U u) {
                T t = this.value;
                this.value = null;
                try {
                    this.actual.onSuccess(a.a(this.resultSelector.apply(t, u), "The resultSelector returned a null value"));
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    this.actual.onError(th);
                }
            }

            public void onError(Throwable th) {
                this.actual.onError(th);
            }

            public void onComplete() {
                this.actual.onComplete();
            }
        }

        FlatMapBiMainObserver(m<? super R> mVar, g<? super T, ? extends o<? extends U>> gVar, c<? super T, ? super U, ? extends R> cVar) {
            this.b = new InnerObserver<>(mVar, cVar);
            this.a = gVar;
        }

        public void dispose() {
            DisposableHelper.dispose(this.b);
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((Disposable) this.b.get());
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.setOnce(this.b, disposable)) {
                this.b.actual.onSubscribe(this);
            }
        }

        public void onSuccess(T t) {
            try {
                o oVar = (o) a.a(this.a.apply(t), "The mapper returned a null MaybeSource");
                if (DisposableHelper.replace(this.b, null)) {
                    this.b.value = t;
                    oVar.a(this.b);
                }
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                this.b.actual.onError(th);
            }
        }

        public void onError(Throwable th) {
            this.b.actual.onError(th);
        }

        public void onComplete() {
            this.b.actual.onComplete();
        }
    }

    /* access modifiers changed from: protected */
    public void b(m<? super R> mVar) {
        this.a.a(new FlatMapBiMainObserver(mVar, this.b, this.c));
    }
}
