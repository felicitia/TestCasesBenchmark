package io.reactivex.b;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.g;

/* compiled from: ConnectableFlowable */
public abstract class a<T> extends g<T> {
    public abstract void a(Consumer<? super Disposable> consumer);
}
