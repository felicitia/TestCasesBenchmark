package io.reactivex.internal.operators.flowable;

import org.reactivestreams.Publisher;

public final class FlowableOnBackpressureLatest<T> extends AbstractFlowableWithUpstream<T, T> {
    public FlowableOnBackpressureLatest(Publisher<T> publisher) {
        super(publisher);
    }
}
