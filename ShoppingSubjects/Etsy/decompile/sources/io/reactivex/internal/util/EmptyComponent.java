package io.reactivex.internal.util;

import io.reactivex.Observer;
import io.reactivex.c;
import io.reactivex.d.a;
import io.reactivex.disposables.Disposable;
import io.reactivex.j;
import io.reactivex.m;
import io.reactivex.x;
import org.reactivestreams.Subscription;

public enum EmptyComponent implements Observer<Object>, c, Disposable, j<Object>, m<Object>, x<Object>, Subscription {
    INSTANCE;

    public void cancel() {
    }

    public void dispose() {
    }

    public boolean isDisposed() {
        return true;
    }

    public void onComplete() {
    }

    public void onNext(Object obj) {
    }

    public void onSuccess(Object obj) {
    }

    public void request(long j) {
    }

    public static <T> org.reactivestreams.c<T> asSubscriber() {
        return INSTANCE;
    }

    public static <T> Observer<T> asObserver() {
        return INSTANCE;
    }

    public void onSubscribe(Disposable disposable) {
        disposable.dispose();
    }

    public void onSubscribe(Subscription subscription) {
        subscription.cancel();
    }

    public void onError(Throwable th) {
        a.a(th);
    }
}
