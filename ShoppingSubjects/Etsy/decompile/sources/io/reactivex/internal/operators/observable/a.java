package io.reactivex.internal.operators.observable;

import io.reactivex.q;
import io.reactivex.t;

/* compiled from: AbstractObservableWithUpstream */
abstract class a<T, U> extends q<U> {
    protected final t<T> a;

    a(t<T> tVar) {
        this.a = tVar;
    }
}
