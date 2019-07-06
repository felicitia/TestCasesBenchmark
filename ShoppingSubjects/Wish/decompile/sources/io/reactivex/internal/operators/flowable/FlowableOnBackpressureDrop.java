package io.reactivex.internal.operators.flowable;

import io.reactivex.functions.Consumer;
import org.reactivestreams.Publisher;

public final class FlowableOnBackpressureDrop<T> extends AbstractFlowableWithUpstream<T, T> implements Consumer<T> {
    final Consumer<? super T> onDrop = this;

    public void accept(T t) {
    }

    public FlowableOnBackpressureDrop(Publisher<T> publisher) {
        super(publisher);
    }
}
