package com.onfido.android.sdk.capture.utils;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import kotlin.jvm.internal.Intrinsics;

public final class ReactiveExtensionsKt {
    public static final <T> Observable<T> subscribeAndObserve(Observable<T> observable, Scheduler scheduler, Scheduler scheduler2) {
        Intrinsics.checkParameterIsNotNull(observable, "$receiver");
        Intrinsics.checkParameterIsNotNull(scheduler, "subscriber");
        Intrinsics.checkParameterIsNotNull(scheduler2, "observer");
        Observable<T> observeOn = observable.subscribeOn(scheduler).observeOn(scheduler2);
        Intrinsics.checkExpressionValueIsNotNull(observeOn, "subscribeOn(subscriber)\n…     .observeOn(observer)");
        return observeOn;
    }

    public static /* synthetic */ Observable subscribeAndObserve$default(Observable observable, Scheduler scheduler, Scheduler scheduler2, int i, Object obj) {
        if ((i & 1) != 0) {
            scheduler = Schedulers.io();
            Intrinsics.checkExpressionValueIsNotNull(scheduler, "Schedulers.io()");
        }
        if ((i & 2) != 0) {
            scheduler2 = AndroidSchedulers.mainThread();
            Intrinsics.checkExpressionValueIsNotNull(scheduler2, "AndroidSchedulers.mainThread()");
        }
        return subscribeAndObserve(observable, scheduler, scheduler2);
    }
}
