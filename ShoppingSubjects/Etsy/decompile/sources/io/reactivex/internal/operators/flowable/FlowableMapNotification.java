package io.reactivex.internal.operators.flowable;

import io.reactivex.exceptions.CompositeException;
import io.reactivex.functions.g;
import io.reactivex.internal.functions.a;
import io.reactivex.internal.subscribers.SinglePostCompleteSubscriber;
import io.reactivex.j;
import java.util.concurrent.Callable;
import org.reactivestreams.c;

public final class FlowableMapNotification<T, R> extends a<T, R> {
    final g<? super T, ? extends R> c;
    final g<? super Throwable, ? extends R> d;
    final Callable<? extends R> e;

    static final class MapNotificationSubscriber<T, R> extends SinglePostCompleteSubscriber<T, R> {
        private static final long serialVersionUID = 2757120512858778108L;
        final Callable<? extends R> onCompleteSupplier;
        final g<? super Throwable, ? extends R> onErrorMapper;
        final g<? super T, ? extends R> onNextMapper;

        MapNotificationSubscriber(c<? super R> cVar, g<? super T, ? extends R> gVar, g<? super Throwable, ? extends R> gVar2, Callable<? extends R> callable) {
            super(cVar);
            this.onNextMapper = gVar;
            this.onErrorMapper = gVar2;
            this.onCompleteSupplier = callable;
        }

        public void onNext(T t) {
            try {
                Object a = a.a(this.onNextMapper.apply(t), "The onNext publisher returned is null");
                this.produced++;
                this.actual.onNext(a);
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                this.actual.onError(th);
            }
        }

        public void onError(Throwable th) {
            try {
                complete(a.a(this.onErrorMapper.apply(th), "The onError publisher returned is null"));
            } catch (Throwable th2) {
                io.reactivex.exceptions.a.b(th2);
                this.actual.onError(new CompositeException(th, th2));
            }
        }

        public void onComplete() {
            try {
                complete(a.a(this.onCompleteSupplier.call(), "The onComplete publisher returned is null"));
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                this.actual.onError(th);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a(c<? super R> cVar) {
        this.b.a((j<? super T>) new MapNotificationSubscriber<Object>(cVar, this.c, this.d, this.e));
    }
}
