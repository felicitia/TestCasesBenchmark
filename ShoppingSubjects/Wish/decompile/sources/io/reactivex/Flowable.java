package io.reactivex;

import io.reactivex.internal.functions.Functions;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.operators.flowable.FlowableOnBackpressureBuffer;
import io.reactivex.internal.operators.flowable.FlowableOnBackpressureDrop;
import io.reactivex.internal.operators.flowable.FlowableOnBackpressureLatest;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Publisher;

public abstract class Flowable<T> implements Publisher<T> {
    static final int BUFFER_SIZE = Math.max(16, Integer.getInteger("rx2.buffer-size", 128).intValue());

    public static int bufferSize() {
        return BUFFER_SIZE;
    }

    public final Flowable<T> onBackpressureBuffer() {
        return onBackpressureBuffer(bufferSize(), false, true);
    }

    public final Flowable<T> onBackpressureBuffer(int i, boolean z, boolean z2) {
        ObjectHelper.verifyPositive(i, "bufferSize");
        FlowableOnBackpressureBuffer flowableOnBackpressureBuffer = new FlowableOnBackpressureBuffer(this, i, z2, z, Functions.EMPTY_ACTION);
        return RxJavaPlugins.onAssembly((Flowable<T>) flowableOnBackpressureBuffer);
    }

    public final Flowable<T> onBackpressureDrop() {
        return RxJavaPlugins.onAssembly((Flowable<T>) new FlowableOnBackpressureDrop<T>(this));
    }

    public final Flowable<T> onBackpressureLatest() {
        return RxJavaPlugins.onAssembly((Flowable<T>) new FlowableOnBackpressureLatest<T>(this));
    }
}
