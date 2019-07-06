package io.reactivex.internal.operators.observable;

import io.reactivex.Observer;
import io.reactivex.internal.a.e;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.q;

/* compiled from: ObservableEmpty */
public final class d extends q<Object> implements e<Object> {
    public static final q<Object> a = new d();

    public Object call() {
        return null;
    }

    private d() {
    }

    /* access modifiers changed from: protected */
    public void a(Observer<? super Object> observer) {
        EmptyDisposable.complete(observer);
    }
}
