package io.reactivex.rxkotlin;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.g;
import io.reactivex.q;
import io.reactivex.v;
import kotlin.h;
import kotlin.jvm.a.a;
import kotlin.jvm.a.b;
import kotlin.jvm.internal.p;

/* compiled from: subscribers.kt */
public final class c {
    private static final b<Object, h> a = SubscribersKt$onNextStub$1.INSTANCE;
    private static final b<Throwable, h> b = SubscribersKt$onErrorStub$1.INSTANCE;
    private static final a<h> c = SubscribersKt$onCompleteStub$1.INSTANCE;

    /* JADX WARNING: Incorrect type for immutable var: ssa=kotlin.jvm.a.a, code=kotlin.jvm.a.a<kotlin.h>, for r2v0, types: [kotlin.jvm.a.a] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=kotlin.jvm.a.b, code=kotlin.jvm.a.b<java.lang.Object, kotlin.h>, for r3v0, types: [kotlin.jvm.a.b] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=kotlin.jvm.a.b, code=kotlin.jvm.a.b<java.lang.Throwable, kotlin.h>, for r1v0, types: [kotlin.jvm.a.b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* bridge */ /* synthetic */ io.reactivex.disposables.Disposable a(io.reactivex.q r0, kotlin.jvm.a.b<java.lang.Throwable, kotlin.h> r1, kotlin.jvm.a.a<kotlin.h> r2, kotlin.jvm.a.b<java.lang.Object, kotlin.h> r3, int r4, java.lang.Object r5) {
        /*
            r5 = r4 & 1
            if (r5 == 0) goto L_0x0006
            kotlin.jvm.a.b<java.lang.Throwable, kotlin.h> r1 = b
        L_0x0006:
            r5 = r4 & 2
            if (r5 == 0) goto L_0x000c
            kotlin.jvm.a.a<kotlin.h> r2 = c
        L_0x000c:
            r4 = r4 & 4
            if (r4 == 0) goto L_0x0012
            kotlin.jvm.a.b<java.lang.Object, kotlin.h> r3 = a
        L_0x0012:
            io.reactivex.disposables.Disposable r0 = a(r0, r1, r2, r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.rxkotlin.c.a(io.reactivex.q, kotlin.jvm.a.b, kotlin.jvm.a.a, kotlin.jvm.a.b, int, java.lang.Object):io.reactivex.disposables.Disposable");
    }

    public static final <T> Disposable a(q<T> qVar, b<? super Throwable, h> bVar, a<h> aVar, b<? super T, h> bVar2) {
        p.b(qVar, "$receiver");
        p.b(bVar, "onError");
        p.b(aVar, "onComplete");
        p.b(bVar2, "onNext");
        Disposable a2 = qVar.a((Consumer<? super T>) new e<Object>(bVar2), (Consumer<? super Throwable>) new e<Object>(bVar), (io.reactivex.functions.a) new d(aVar));
        p.a((Object) a2, "subscribe(onNext, onError, onComplete)");
        return a2;
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=kotlin.jvm.a.a, code=kotlin.jvm.a.a<kotlin.h>, for r2v0, types: [kotlin.jvm.a.a] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=kotlin.jvm.a.b, code=kotlin.jvm.a.b<java.lang.Object, kotlin.h>, for r3v0, types: [kotlin.jvm.a.b] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=kotlin.jvm.a.b, code=kotlin.jvm.a.b<java.lang.Throwable, kotlin.h>, for r1v0, types: [kotlin.jvm.a.b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* bridge */ /* synthetic */ io.reactivex.disposables.Disposable a(io.reactivex.g r0, kotlin.jvm.a.b<java.lang.Throwable, kotlin.h> r1, kotlin.jvm.a.a<kotlin.h> r2, kotlin.jvm.a.b<java.lang.Object, kotlin.h> r3, int r4, java.lang.Object r5) {
        /*
            r5 = r4 & 1
            if (r5 == 0) goto L_0x0006
            kotlin.jvm.a.b<java.lang.Throwable, kotlin.h> r1 = b
        L_0x0006:
            r5 = r4 & 2
            if (r5 == 0) goto L_0x000c
            kotlin.jvm.a.a<kotlin.h> r2 = c
        L_0x000c:
            r4 = r4 & 4
            if (r4 == 0) goto L_0x0012
            kotlin.jvm.a.b<java.lang.Object, kotlin.h> r3 = a
        L_0x0012:
            io.reactivex.disposables.Disposable r0 = a(r0, r1, r2, r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.rxkotlin.c.a(io.reactivex.g, kotlin.jvm.a.b, kotlin.jvm.a.a, kotlin.jvm.a.b, int, java.lang.Object):io.reactivex.disposables.Disposable");
    }

    public static final <T> Disposable a(g<T> gVar, b<? super Throwable, h> bVar, a<h> aVar, b<? super T, h> bVar2) {
        p.b(gVar, "$receiver");
        p.b(bVar, "onError");
        p.b(aVar, "onComplete");
        p.b(bVar2, "onNext");
        Disposable a2 = gVar.a((Consumer<? super T>) new e<Object>(bVar2), (Consumer<? super Throwable>) new e<Object>(bVar), (io.reactivex.functions.a) new d(aVar));
        p.a((Object) a2, "subscribe(onNext, onError, onComplete)");
        return a2;
    }

    public static final <T> Disposable a(v<T> vVar, b<? super Throwable, h> bVar, b<? super T, h> bVar2) {
        p.b(vVar, "$receiver");
        p.b(bVar, "onError");
        p.b(bVar2, "onSuccess");
        Disposable a2 = vVar.a((Consumer<? super T>) new e<Object>(bVar2), (Consumer<? super Throwable>) new e<Object>(bVar));
        p.a((Object) a2, "subscribe(onSuccess, onError)");
        return a2;
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=kotlin.jvm.a.a, code=kotlin.jvm.a.a<kotlin.h>, for r2v0, types: [kotlin.jvm.a.a] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=kotlin.jvm.a.b, code=kotlin.jvm.a.b<java.lang.Throwable, kotlin.h>, for r1v0, types: [kotlin.jvm.a.b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* bridge */ /* synthetic */ io.reactivex.disposables.Disposable a(io.reactivex.a r0, kotlin.jvm.a.b<java.lang.Throwable, kotlin.h> r1, kotlin.jvm.a.a<kotlin.h> r2, int r3, java.lang.Object r4) {
        /*
            r4 = r3 & 1
            if (r4 == 0) goto L_0x0006
            kotlin.jvm.a.b<java.lang.Throwable, kotlin.h> r1 = b
        L_0x0006:
            r3 = r3 & 2
            if (r3 == 0) goto L_0x000c
            kotlin.jvm.a.a<kotlin.h> r2 = c
        L_0x000c:
            io.reactivex.disposables.Disposable r0 = a(r0, r1, r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.rxkotlin.c.a(io.reactivex.a, kotlin.jvm.a.b, kotlin.jvm.a.a, int, java.lang.Object):io.reactivex.disposables.Disposable");
    }

    public static final Disposable a(io.reactivex.a aVar, b<? super Throwable, h> bVar, a<h> aVar2) {
        p.b(aVar, "$receiver");
        p.b(bVar, "onError");
        p.b(aVar2, "onComplete");
        Disposable a2 = aVar.a(new d(aVar2), new e(bVar));
        p.a((Object) a2, "subscribe(onComplete, onError)");
        return a2;
    }
}
