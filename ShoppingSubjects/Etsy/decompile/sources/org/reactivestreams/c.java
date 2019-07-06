package org.reactivestreams;

/* compiled from: Subscriber */
public interface c<T> {
    void onComplete();

    void onError(Throwable th);

    void onNext(T t);

    void onSubscribe(Subscription subscription);
}
