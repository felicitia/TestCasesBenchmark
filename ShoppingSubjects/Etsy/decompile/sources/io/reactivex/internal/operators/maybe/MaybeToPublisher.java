package io.reactivex.internal.operators.maybe;

import io.reactivex.functions.g;
import io.reactivex.o;
import org.reactivestreams.b;

public enum MaybeToPublisher implements g<o<Object>, b<Object>> {
    INSTANCE;

    public static <T> g<o<T>, b<T>> instance() {
        return INSTANCE;
    }

    public b<Object> apply(o<Object> oVar) throws Exception {
        return new MaybeToFlowable(oVar);
    }
}
