package io.reactivex.rxkotlin;

import kotlin.Triple;
import kotlin.jvm.a.q;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.s;
import kotlin.reflect.d;

/* compiled from: flowable.kt */
final class FlowableKt$combineLatest$3 extends FunctionReference implements q<T, R, U, Triple<? extends T, ? extends R, ? extends U>> {
    public static final FlowableKt$combineLatest$3 INSTANCE = new FlowableKt$combineLatest$3();

    FlowableKt$combineLatest$3() {
        super(3);
    }

    public final String getName() {
        return "<init>";
    }

    public final d getOwner() {
        return s.a(Triple.class);
    }

    public final String getSignature() {
        return "<init>(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V";
    }

    public final Triple<T, R, U> invoke(T t, R r, U u) {
        p.b(t, "p1");
        p.b(r, "p2");
        p.b(u, "p3");
        return new Triple<>(t, r, u);
    }
}
