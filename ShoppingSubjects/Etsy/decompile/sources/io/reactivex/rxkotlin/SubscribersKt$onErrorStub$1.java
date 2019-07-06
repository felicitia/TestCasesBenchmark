package io.reactivex.rxkotlin;

import io.reactivex.d.a;
import io.reactivex.exceptions.OnErrorNotImplementedException;
import kotlin.h;
import kotlin.jvm.a.b;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.p;

/* compiled from: subscribers.kt */
final class SubscribersKt$onErrorStub$1 extends Lambda implements b<Throwable, h> {
    public static final SubscribersKt$onErrorStub$1 INSTANCE = new SubscribersKt$onErrorStub$1();

    SubscribersKt$onErrorStub$1() {
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return h.a;
    }

    public final void invoke(Throwable th) {
        p.b(th, "it");
        a.a((Throwable) new OnErrorNotImplementedException(th));
    }
}
