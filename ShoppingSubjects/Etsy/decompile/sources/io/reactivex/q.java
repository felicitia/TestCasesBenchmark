package io.reactivex;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.i;
import io.reactivex.internal.functions.Functions;
import io.reactivex.internal.functions.a;
import io.reactivex.internal.observers.LambdaObserver;
import io.reactivex.internal.operators.flowable.FlowableOnBackpressureError;
import io.reactivex.internal.operators.observable.ObservableCreate;
import io.reactivex.internal.operators.observable.ObservableDebounceTimed;
import io.reactivex.internal.operators.observable.ObservableFlatMap;
import io.reactivex.internal.operators.observable.ObservableInterval;
import io.reactivex.internal.operators.observable.ObservableObserveOn;
import io.reactivex.internal.operators.observable.ObservableRetryPredicate;
import io.reactivex.internal.operators.observable.ObservableRetryWhen;
import io.reactivex.internal.operators.observable.ObservableScalarXMap;
import io.reactivex.internal.operators.observable.ObservableSubscribeOn;
import io.reactivex.internal.operators.observable.ObservableTimer;
import io.reactivex.internal.operators.observable.b;
import io.reactivex.internal.operators.observable.c;
import io.reactivex.internal.operators.observable.d;
import io.reactivex.internal.operators.observable.e;
import io.reactivex.internal.operators.observable.f;
import io.reactivex.internal.operators.observable.g;
import io.reactivex.internal.operators.observable.h;
import io.reactivex.internal.operators.observable.j;
import io.reactivex.internal.operators.observable.k;
import io.reactivex.internal.operators.observable.l;
import io.reactivex.internal.operators.observable.m;
import io.reactivex.internal.operators.observable.n;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/* compiled from: Observable */
public abstract class q<T> implements t<T> {
    /* access modifiers changed from: protected */
    public abstract void a(Observer<? super T> observer);

    public static int d() {
        return g.a();
    }

    public static <T> q<T> a(s<T> sVar) {
        a.a(sVar, "source is null");
        return io.reactivex.d.a.a((q<T>) new ObservableCreate<T>(sVar));
    }

    public static <T> q<T> e() {
        return io.reactivex.d.a.a(d.a);
    }

    public static <T> q<T> a(Callable<? extends Throwable> callable) {
        a.a(callable, "errorSupplier is null");
        return io.reactivex.d.a.a((q<T>) new e<T>(callable));
    }

    public static <T> q<T> a(Throwable th) {
        a.a(th, "e is null");
        return a(Functions.a(th));
    }

    public static <T> q<T> b(Callable<? extends T> callable) {
        a.a(callable, "supplier is null");
        return io.reactivex.d.a.a((q<T>) new g<T>(callable));
    }

    public static q<Long> a(long j, long j2, TimeUnit timeUnit) {
        return a(j, j2, timeUnit, io.reactivex.e.a.a());
    }

    public static q<Long> a(long j, long j2, TimeUnit timeUnit, u uVar) {
        a.a(timeUnit, "unit is null");
        a.a(uVar, "scheduler is null");
        ObservableInterval observableInterval = new ObservableInterval(Math.max(0, j), Math.max(0, j2), timeUnit, uVar);
        return io.reactivex.d.a.a((q<T>) observableInterval);
    }

    public static q<Long> a(long j, TimeUnit timeUnit) {
        return a(j, timeUnit, io.reactivex.e.a.a());
    }

    public static q<Long> a(long j, TimeUnit timeUnit, u uVar) {
        a.a(timeUnit, "unit is null");
        a.a(uVar, "scheduler is null");
        return io.reactivex.d.a.a((q<T>) new ObservableTimer<T>(Math.max(j, 0), timeUnit, uVar));
    }

    public final q<T> b(long j, TimeUnit timeUnit) {
        return b(j, timeUnit, io.reactivex.e.a.a());
    }

    public final q<T> b(long j, TimeUnit timeUnit, u uVar) {
        a.a(timeUnit, "unit is null");
        a.a(uVar, "scheduler is null");
        ObservableDebounceTimed observableDebounceTimed = new ObservableDebounceTimed(this, j, timeUnit, uVar);
        return io.reactivex.d.a.a((q<T>) observableDebounceTimed);
    }

    public final q<T> a(io.reactivex.functions.a aVar) {
        return a(Functions.b(), Functions.b(), aVar, Functions.c);
    }

    private q<T> a(Consumer<? super T> consumer, Consumer<? super Throwable> consumer2, io.reactivex.functions.a aVar, io.reactivex.functions.a aVar2) {
        a.a(consumer, "onNext is null");
        a.a(consumer2, "onError is null");
        a.a(aVar, "onComplete is null");
        a.a(aVar2, "onAfterTerminate is null");
        b bVar = new b(this, consumer, consumer2, aVar, aVar2);
        return io.reactivex.d.a.a((q<T>) bVar);
    }

    public final v<T> a(long j) {
        if (j >= 0) {
            return io.reactivex.d.a.a((v<T>) new c<T>(this, j, null));
        }
        StringBuilder sb = new StringBuilder();
        sb.append("index >= 0 required but it was ");
        sb.append(j);
        throw new IndexOutOfBoundsException(sb.toString());
    }

    public final q<T> a(i<? super T> iVar) {
        a.a(iVar, "predicate is null");
        return io.reactivex.d.a.a((q<T>) new f<T>(this, iVar));
    }

    public final v<T> f() {
        return a(0);
    }

    public final <R> q<R> a(io.reactivex.functions.g<? super T, ? extends t<? extends R>> gVar) {
        return a(gVar, false);
    }

    public final <R> q<R> a(io.reactivex.functions.g<? super T, ? extends t<? extends R>> gVar, boolean z) {
        return a(gVar, z, Integer.MAX_VALUE);
    }

    public final <R> q<R> a(io.reactivex.functions.g<? super T, ? extends t<? extends R>> gVar, boolean z, int i) {
        return a(gVar, z, i, d());
    }

    public final <R> q<R> a(io.reactivex.functions.g<? super T, ? extends t<? extends R>> gVar, boolean z, int i, int i2) {
        a.a(gVar, "mapper is null");
        a.a(i, "maxConcurrency");
        a.a(i2, "bufferSize");
        if (this instanceof io.reactivex.internal.a.e) {
            Object call = ((io.reactivex.internal.a.e) this).call();
            if (call == null) {
                return e();
            }
            return ObservableScalarXMap.a(call, gVar);
        }
        ObservableFlatMap observableFlatMap = new ObservableFlatMap(this, gVar, z, i, i2);
        return io.reactivex.d.a.a((q<T>) observableFlatMap);
    }

    public final a g() {
        return io.reactivex.d.a.a((a) new h(this));
    }

    public final <R> q<R> b(io.reactivex.functions.g<? super T, ? extends R> gVar) {
        a.a(gVar, "mapper is null");
        return io.reactivex.d.a.a((q<T>) new io.reactivex.internal.operators.observable.i<T>(this, gVar));
    }

    public final q<T> a(u uVar) {
        return a(uVar, false, d());
    }

    public final q<T> a(u uVar, boolean z, int i) {
        a.a(uVar, "scheduler is null");
        a.a(i, "bufferSize");
        return io.reactivex.d.a.a((q<T>) new ObservableObserveOn<T>(this, uVar, z, i));
    }

    public final q<T> h() {
        return a(Long.MAX_VALUE, Functions.c());
    }

    public final q<T> a(long j, i<? super Throwable> iVar) {
        if (j < 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("times >= 0 required but it was ");
            sb.append(j);
            throw new IllegalArgumentException(sb.toString());
        }
        a.a(iVar, "predicate is null");
        return io.reactivex.d.a.a((q<T>) new ObservableRetryPredicate<T>(this, j, iVar));
    }

    public final q<T> c(io.reactivex.functions.g<? super q<Throwable>, ? extends t<?>> gVar) {
        a.a(gVar, "handler is null");
        return io.reactivex.d.a.a((q<T>) new ObservableRetryWhen<T>(this, gVar));
    }

    public final k<T> i() {
        return io.reactivex.d.a.a((k<T>) new j<T>(this));
    }

    public final v<T> j() {
        return io.reactivex.d.a.a((v<T>) new k<T>(this, null));
    }

    public final q<T> b(long j) {
        if (j <= 0) {
            return io.reactivex.d.a.a(this);
        }
        return io.reactivex.d.a.a((q<T>) new l<T>(this, j));
    }

    public final Disposable k() {
        return a(Functions.b(), Functions.f, Functions.c, Functions.b());
    }

    public final Disposable a(Consumer<? super T> consumer) {
        return a(consumer, Functions.f, Functions.c, Functions.b());
    }

    public final Disposable a(Consumer<? super T> consumer, Consumer<? super Throwable> consumer2) {
        return a(consumer, consumer2, Functions.c, Functions.b());
    }

    public final Disposable a(Consumer<? super T> consumer, Consumer<? super Throwable> consumer2, io.reactivex.functions.a aVar) {
        return a(consumer, consumer2, aVar, Functions.b());
    }

    public final Disposable a(Consumer<? super T> consumer, Consumer<? super Throwable> consumer2, io.reactivex.functions.a aVar, Consumer<? super Disposable> consumer3) {
        a.a(consumer, "onNext is null");
        a.a(consumer2, "onError is null");
        a.a(aVar, "onComplete is null");
        a.a(consumer3, "onSubscribe is null");
        LambdaObserver lambdaObserver = new LambdaObserver(consumer, consumer2, aVar, consumer3);
        subscribe(lambdaObserver);
        return lambdaObserver;
    }

    public final void subscribe(Observer<? super T> observer) {
        a.a(observer, "observer is null");
        try {
            Observer a = io.reactivex.d.a.a(this, observer);
            a.a(a, "Plugin returned null Observer");
            a(a);
        } catch (NullPointerException e) {
            throw e;
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            io.reactivex.d.a.a(th);
            NullPointerException nullPointerException = new NullPointerException("Actually not, but can't throw other exceptions due to RS");
            nullPointerException.initCause(th);
            throw nullPointerException;
        }
    }

    public final q<T> b(u uVar) {
        a.a(uVar, "scheduler is null");
        return io.reactivex.d.a.a((q<T>) new ObservableSubscribeOn<T>(this, uVar));
    }

    public final q<T> b(i<? super T> iVar) {
        a.a(iVar, "predicate is null");
        return io.reactivex.d.a.a((q<T>) new m<T>(this, iVar));
    }

    public final g<T> a(BackpressureStrategy backpressureStrategy) {
        io.reactivex.internal.operators.flowable.c cVar = new io.reactivex.internal.operators.flowable.c(this);
        switch (backpressureStrategy) {
            case DROP:
                return cVar.d();
            case LATEST:
                return cVar.e();
            case MISSING:
                return cVar;
            case ERROR:
                return io.reactivex.d.a.a((g<T>) new FlowableOnBackpressureError<T>(cVar));
            default:
                return cVar.c();
        }
    }

    public final <U, R> q<R> a(Iterable<U> iterable, io.reactivex.functions.c<? super T, ? super U, ? extends R> cVar) {
        a.a(iterable, "other is null");
        a.a(cVar, "zipper is null");
        return io.reactivex.d.a.a((q<T>) new n<T>(this, iterable, cVar));
    }
}
