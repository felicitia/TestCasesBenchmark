package io.reactivex.internal.operators.single;

import io.reactivex.disposables.b;
import io.reactivex.v;
import io.reactivex.x;

/* compiled from: SingleJust */
public final class d<T> extends v<T> {
    final T a;

    public d(T t) {
        this.a = t;
    }

    /* access modifiers changed from: protected */
    public void b(x<? super T> xVar) {
        xVar.onSubscribe(b.a());
        xVar.onSuccess(this.a);
    }
}
