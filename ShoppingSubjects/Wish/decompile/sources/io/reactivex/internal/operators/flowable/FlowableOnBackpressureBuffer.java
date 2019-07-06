package io.reactivex.internal.operators.flowable;

import io.reactivex.functions.Action;
import org.reactivestreams.Publisher;

public final class FlowableOnBackpressureBuffer<T> extends AbstractFlowableWithUpstream<T, T> {
    final int bufferSize;
    final boolean delayError;
    final Action onOverflow;
    final boolean unbounded;

    public FlowableOnBackpressureBuffer(Publisher<T> publisher, int i, boolean z, boolean z2, Action action) {
        super(publisher);
        this.bufferSize = i;
        this.unbounded = z;
        this.delayError = z2;
        this.onOverflow = action;
    }
}
