package io.reactivex.internal.operators.flowable;

import io.reactivex.g;

/* compiled from: AbstractFlowableWithUpstream */
abstract class a<T, R> extends g<R> {
    protected final g<T> b;

    a(g<T> gVar) {
        this.b = (g) io.reactivex.internal.functions.a.a(gVar, "source is null");
    }
}
