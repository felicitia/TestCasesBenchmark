package io.reactivex.c;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.q;

/* compiled from: ConnectableObservable */
public abstract class a<T> extends q<T> {
    public abstract void b(Consumer<? super Disposable> consumer);
}
