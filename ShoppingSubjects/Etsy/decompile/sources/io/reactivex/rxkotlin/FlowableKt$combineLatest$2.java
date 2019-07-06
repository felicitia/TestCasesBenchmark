package io.reactivex.rxkotlin;

import kotlin.Pair;
import kotlin.jvm.a.m;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.s;
import kotlin.reflect.d;

/* compiled from: flowable.kt */
final class FlowableKt$combineLatest$2 extends FunctionReference implements m<T, R, Pair<? extends T, ? extends R>> {
    public static final FlowableKt$combineLatest$2 INSTANCE = new FlowableKt$combineLatest$2();

    FlowableKt$combineLatest$2() {
        super(2);
    }

    public final String getName() {
        return "<init>";
    }

    public final d getOwner() {
        return s.a(Pair.class);
    }

    public final String getSignature() {
        return "<init>(Ljava/lang/Object;Ljava/lang/Object;)V";
    }

    public final Pair<T, R> invoke(T t, R r) {
        p.b(t, "p1");
        p.b(r, "p2");
        return new Pair<>(t, r);
    }
}
