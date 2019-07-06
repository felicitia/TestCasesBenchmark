package io.reactivex.internal.operators.maybe;

import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.functions.g;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.m;
import io.reactivex.o;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicReference;

public final class MaybeFlatMapNotification<T, R> extends a<T, R> {
    final g<? super T, ? extends o<? extends R>> b;
    final g<? super Throwable, ? extends o<? extends R>> c;
    final Callable<? extends o<? extends R>> d;

    static final class FlatMapMaybeObserver<T, R> extends AtomicReference<Disposable> implements Disposable, m<T> {
        private static final long serialVersionUID = 4375739915521278546L;
        final m<? super R> actual;
        Disposable d;
        final Callable<? extends o<? extends R>> onCompleteSupplier;
        final g<? super Throwable, ? extends o<? extends R>> onErrorMapper;
        final g<? super T, ? extends o<? extends R>> onSuccessMapper;

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

        FlatMapMaybeObserver(m<? super R> mVar, g<? super T, ? extends o<? extends R>> gVar, g<? super Throwable, ? extends o<? extends R>> gVar2, Callable<? extends o<? extends R>> callable) {
            this.actual = mVar;
            this.onSuccessMapper = gVar;
            this.onErrorMapper = gVar2;
            this.onCompleteSupplier = callable;
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
                ((o) io.reactivex.internal.functions.a.a(this.onSuccessMapper.apply(t), "The onSuccessMapper returned a null MaybeSource")).a(new a());
            } catch (Exception e) {
                io.reactivex.exceptions.a.b(e);
                this.actual.onError(e);
            }
        }

        public void onError(Throwable th) {
            try {
                ((o) io.reactivex.internal.functions.a.a(this.onErrorMapper.apply(th), "The onErrorMapper returned a null MaybeSource")).a(new a());
            } catch (Exception e) {
                io.reactivex.exceptions.a.b(e);
                this.actual.onError(new CompositeException(th, e));
            }
        }

        public void onComplete() {
            try {
                ((o) io.reactivex.internal.functions.a.a(this.onCompleteSupplier.call(), "The onCompleteSupplier returned a null MaybeSource")).a(new a());
            } catch (Exception e) {
                io.reactivex.exceptions.a.b(e);
                this.actual.onError(e);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void b(m<? super R> mVar) {
        this.a.a(new FlatMapMaybeObserver(mVar, this.b, this.c, this.d));
    }
}
