package io.reactivex;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.g;
import io.reactivex.internal.functions.Functions;
import io.reactivex.internal.functions.a;
import io.reactivex.internal.observers.BiConsumerSingleObserver;
import io.reactivex.internal.observers.ConsumerSingleObserver;
import io.reactivex.internal.operators.single.SingleCreate;
import io.reactivex.internal.operators.single.SingleDoFinally;
import io.reactivex.internal.operators.single.SingleFlatMap;
import io.reactivex.internal.operators.single.SingleObserveOn;
import io.reactivex.internal.operators.single.SingleResumeNext;
import io.reactivex.internal.operators.single.SingleSubscribeOn;
import io.reactivex.internal.operators.single.SingleTimer;
import io.reactivex.internal.operators.single.b;
import io.reactivex.internal.operators.single.c;
import io.reactivex.internal.operators.single.d;
import io.reactivex.internal.operators.single.e;
import io.reactivex.internal.operators.single.f;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/* compiled from: Single */
public abstract class v<T> implements z<T> {
    /* access modifiers changed from: protected */
    public abstract void b(x<? super T> xVar);

    public static <T> v<T> a(y<T> yVar) {
        a.a(yVar, "source is null");
        return io.reactivex.d.a.a((v<T>) new SingleCreate<T>(yVar));
    }

    public static <T> v<T> a(Callable<? extends Throwable> callable) {
        a.a(callable, "errorSupplier is null");
        return io.reactivex.d.a.a((v<T>) new io.reactivex.internal.operators.single.a<T>(callable));
    }

    public static <T> v<T> a(Throwable th) {
        a.a(th, "error is null");
        return a(Functions.a(th));
    }

    public static <T> v<T> b(Callable<? extends T> callable) {
        a.a(callable, "callable is null");
        return io.reactivex.d.a.a((v<T>) new b<T>(callable));
    }

    public static <T> v<T> a(T t) {
        a.a(t, "value is null");
        return io.reactivex.d.a.a((v<T>) new d<T>(t));
    }

    public static v<Long> a(long j, TimeUnit timeUnit) {
        return a(j, timeUnit, io.reactivex.e.a.a());
    }

    public static v<Long> a(long j, TimeUnit timeUnit, u uVar) {
        a.a(timeUnit, "unit is null");
        a.a(uVar, "scheduler is null");
        return io.reactivex.d.a.a((v<T>) new SingleTimer<T>(j, timeUnit, uVar));
    }

    public static <T> v<T> a(z<T> zVar) {
        a.a(zVar, "source is null");
        if (zVar instanceof v) {
            return io.reactivex.d.a.a((v) zVar);
        }
        return io.reactivex.d.a.a((v<T>) new c<T>(zVar));
    }

    public final <R> v<R> a(aa<? super T, ? extends R> aaVar) {
        return a(((aa) a.a(aaVar, "transformer is null")).a(this));
    }

    public final v<T> a(io.reactivex.functions.a aVar) {
        a.a(aVar, "onFinally is null");
        return io.reactivex.d.a.a((v<T>) new SingleDoFinally<T>(this, aVar));
    }

    public final <R> v<R> a(g<? super T, ? extends z<? extends R>> gVar) {
        a.a(gVar, "mapper is null");
        return io.reactivex.d.a.a((v<T>) new SingleFlatMap<T>(this, gVar));
    }

    public final T a() {
        io.reactivex.internal.observers.b bVar = new io.reactivex.internal.observers.b();
        a((x<? super T>) bVar);
        return bVar.b();
    }

    public final <R> v<R> b(g<? super T, ? extends R> gVar) {
        a.a(gVar, "mapper is null");
        return io.reactivex.d.a.a((v<T>) new e<T>(this, gVar));
    }

    public final v<T> a(u uVar) {
        a.a(uVar, "scheduler is null");
        return io.reactivex.d.a.a((v<T>) new SingleObserveOn<T>(this, uVar));
    }

    public final v<T> c(g<Throwable, ? extends T> gVar) {
        a.a(gVar, "resumeFunction is null");
        return io.reactivex.d.a.a((v<T>) new f<T>(this, gVar, null));
    }

    public final v<T> d(g<? super Throwable, ? extends z<? extends T>> gVar) {
        a.a(gVar, "resumeFunctionInCaseOfError is null");
        return io.reactivex.d.a.a((v<T>) new SingleResumeNext<T>(this, gVar));
    }

    public final Disposable a(io.reactivex.functions.b<? super T, ? super Throwable> bVar) {
        a.a(bVar, "onCallback is null");
        BiConsumerSingleObserver biConsumerSingleObserver = new BiConsumerSingleObserver(bVar);
        a((x<? super T>) biConsumerSingleObserver);
        return biConsumerSingleObserver;
    }

    public final Disposable a(Consumer<? super T> consumer) {
        return a(consumer, Functions.f);
    }

    public final Disposable a(Consumer<? super T> consumer, Consumer<? super Throwable> consumer2) {
        a.a(consumer, "onSuccess is null");
        a.a(consumer2, "onError is null");
        ConsumerSingleObserver consumerSingleObserver = new ConsumerSingleObserver(consumer, consumer2);
        a((x<? super T>) consumerSingleObserver);
        return consumerSingleObserver;
    }

    public final void a(x<? super T> xVar) {
        a.a(xVar, "subscriber is null");
        x a = io.reactivex.d.a.a(this, xVar);
        a.a(a, "subscriber returned by the RxJavaPlugins hook is null");
        try {
            b(a);
        } catch (NullPointerException e) {
            throw e;
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            NullPointerException nullPointerException = new NullPointerException("subscribeActual failed");
            nullPointerException.initCause(th);
            throw nullPointerException;
        }
    }

    public final v<T> b(u uVar) {
        a.a(uVar, "scheduler is null");
        return io.reactivex.d.a.a((v<T>) new SingleSubscribeOn<T>(this, uVar));
    }
}
