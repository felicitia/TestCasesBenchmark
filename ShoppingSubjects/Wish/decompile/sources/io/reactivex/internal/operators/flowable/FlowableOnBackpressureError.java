package io.reactivex.internal.operators.flowable;

import org.reactivestreams.Publisher;

public final class FlowableOnBackpressureError<T> extends AbstractFlowableWithUpstream<T, T> {
    public FlowableOnBackpressureError(Publisher<T> publisher) {
        super(publisher);
    }
}
