package io.reactivex.rxkotlin;

import io.reactivex.disposables.Disposable;
import kotlin.jvm.internal.p;

/* compiled from: disposable.kt */
public final class a {
    public static final Disposable a(Disposable disposable, io.reactivex.disposables.a aVar) {
        p.b(disposable, "$receiver");
        p.b(aVar, "compositeDisposable");
        aVar.a(disposable);
        return disposable;
    }
}
