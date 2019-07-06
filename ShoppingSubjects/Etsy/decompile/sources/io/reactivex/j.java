package io.reactivex;

import org.reactivestreams.Subscription;
import org.reactivestreams.c;

/* compiled from: FlowableSubscriber */
public interface j<T> extends c<T> {
    void onSubscribe(Subscription subscription);
}
