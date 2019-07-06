package io.reactivex.internal.disposables;

import io.reactivex.Observer;
import io.reactivex.c;
import io.reactivex.internal.a.b;
import io.reactivex.m;
import io.reactivex.x;

public enum EmptyDisposable implements b<Object> {
    INSTANCE,
    NEVER;

    public void clear() {
    }

    public void dispose() {
    }

    public boolean isEmpty() {
        return true;
    }

    public Object poll() throws Exception {
        return null;
    }

    public int requestFusion(int i) {
        return i & 2;
    }

    public boolean isDisposed() {
        return this == INSTANCE;
    }

    public static void complete(Observer<?> observer) {
        observer.onSubscribe(INSTANCE);
        observer.onComplete();
    }

    public static void complete(m<?> mVar) {
        mVar.onSubscribe(INSTANCE);
        mVar.onComplete();
    }

    public static void error(Throwable th, Observer<?> observer) {
        observer.onSubscribe(INSTANCE);
        observer.onError(th);
    }

    public static void complete(c cVar) {
        cVar.onSubscribe(INSTANCE);
        cVar.onComplete();
    }

    public static void error(Throwable th, c cVar) {
        cVar.onSubscribe(INSTANCE);
        cVar.onError(th);
    }

    public static void error(Throwable th, x<?> xVar) {
        xVar.onSubscribe(INSTANCE);
        xVar.onError(th);
    }

    public static void error(Throwable th, m<?> mVar) {
        mVar.onSubscribe(INSTANCE);
        mVar.onError(th);
    }

    public boolean offer(Object obj) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    public boolean offer(Object obj, Object obj2) {
        throw new UnsupportedOperationException("Should not be called!");
    }
}
