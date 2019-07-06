package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.internal.functions.ObjectHelper;
import org.reactivestreams.Publisher;

abstract class AbstractFlowableWithUpstream<T, R> extends Flowable<R> {
    protected final Publisher<T> source;

    AbstractFlowableWithUpstream(Publisher<T> publisher) {
        this.source = (Publisher) ObjectHelper.requireNonNull(publisher, "source is null");
    }
}
