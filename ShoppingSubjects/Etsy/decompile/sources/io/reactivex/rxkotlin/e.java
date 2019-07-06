package io.reactivex.rxkotlin;

import io.reactivex.functions.Consumer;
import kotlin.jvm.a.b;
import kotlin.jvm.internal.p;

/* compiled from: subscribers.kt */
final class e implements Consumer {
    private final /* synthetic */ b a;

    e(b bVar) {
        this.a = bVar;
    }

    public final /* synthetic */ void accept(T t) {
        p.a(this.a.invoke(t), "invoke(...)");
    }
}
