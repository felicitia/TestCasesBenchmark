package io.reactivex;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.functions.Functions;
import io.reactivex.internal.functions.a;
import io.reactivex.internal.operators.flowable.FlowableCreate;
import io.reactivex.internal.operators.flowable.FlowableInternalHelper.RequestMax;
import io.reactivex.internal.operators.flowable.FlowableObserveOn;
import io.reactivex.internal.operators.flowable.FlowableOnBackpressureBuffer;
import io.reactivex.internal.operators.flowable.FlowableOnBackpressureDrop;
import io.reactivex.internal.operators.flowable.FlowableOnBackpressureLatest;
import io.reactivex.internal.operators.flowable.FlowableSubscribeOn;
import io.reactivex.internal.operators.flowable.d;
import io.reactivex.internal.subscribers.LambdaSubscriber;
import io.reactivex.internal.subscribers.StrictSubscriber;
import org.reactivestreams.Subscription;
import org.reactivestreams.b;
import org.reactivestreams.c;

/* compiled from: Flowable */
public abstract class g<T> implements b<T> {
    static final int a = Math.max(1, Integer.getInteger("rx2.buffer-size", 128).intValue());

    /* access modifiers changed from: protected */
    public abstract void a(c<? super T> cVar);

    public static int a() {
        return a;
    }

    public final g<T> b() {
        return a(Functions.a());
    }

    public final <K> g<T> a(io.reactivex.functions.g<? super T, K> gVar) {
        a.a(gVar, "keySelector is null");
        return io.reactivex.d.a.a((g<T>) new io.reactivex.internal.operators.flowable.b<T>(this, gVar, a.a()));
    }

    public final <R> g<R> b(io.reactivex.functions.g<? super T, ? extends R> gVar) {
        a.a(gVar, "mapper is null");
        return io.reactivex.d.a.a((g<T>) new d<T>(this, gVar));
    }

    public final g<T> a(u uVar) {
        return a(uVar, false, a());
    }

    public final g<T> a(u uVar, boolean z, int i) {
        a.a(uVar, "scheduler is null");
        a.a(i, "bufferSize");
        return io.reactivex.d.a.a((g<T>) new FlowableObserveOn<T>(this, uVar, z, i));
    }

    public final g<T> c() {
        return a(a(), false, true);
    }

    public final g<T> a(int i, boolean z, boolean z2) {
        a.a(i, "bufferSize");
        FlowableOnBackpressureBuffer flowableOnBackpressureBuffer = new FlowableOnBackpressureBuffer(this, i, z2, z, Functions.c);
        return io.reactivex.d.a.a((g<T>) flowableOnBackpressureBuffer);
    }

    public final g<T> d() {
        return io.reactivex.d.a.a((g<T>) new FlowableOnBackpressureDrop<T>(this));
    }

    public final g<T> e() {
        return io.reactivex.d.a.a((g<T>) new FlowableOnBackpressureLatest<T>(this));
    }

    public final Disposable a(Consumer<? super T> consumer, Consumer<? super Throwable> consumer2, io.reactivex.functions.a aVar) {
        return a(consumer, consumer2, aVar, RequestMax.INSTANCE);
    }

    public final Disposable a(Consumer<? super T> consumer, Consumer<? super Throwable> consumer2, io.reactivex.functions.a aVar, Consumer<? super Subscription> consumer3) {
        a.a(consumer, "onNext is null");
        a.a(consumer2, "onError is null");
        a.a(aVar, "onComplete is null");
        a.a(consumer3, "onSubscribe is null");
        LambdaSubscriber lambdaSubscriber = new LambdaSubscriber(consumer, consumer2, aVar, consumer3);
        a((j<? super T>) lambdaSubscriber);
        return lambdaSubscriber;
    }

    public final void subscribe(c<? super T> cVar) {
        if (cVar instanceof j) {
            a((j) cVar);
            return;
        }
        a.a(cVar, "s is null");
        a((j<? super T>) new StrictSubscriber<Object>(cVar));
    }

    public final void a(j<? super T> jVar) {
        a.a(jVar, "s is null");
        try {
            c a2 = io.reactivex.d.a.a(this, (c<? super T>) jVar);
            a.a(a2, "Plugin returned null Subscriber");
            a(a2);
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

    public final g<T> b(u uVar) {
        a.a(uVar, "scheduler is null");
        return a(uVar, !(this instanceof FlowableCreate));
    }

    public final g<T> a(u uVar, boolean z) {
        a.a(uVar, "scheduler is null");
        return io.reactivex.d.a.a((g<T>) new FlowableSubscribeOn<T>(this, uVar, z));
    }
}
