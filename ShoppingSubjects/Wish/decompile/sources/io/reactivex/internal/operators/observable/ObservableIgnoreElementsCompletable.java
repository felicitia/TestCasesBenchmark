package io.reactivex.internal.operators.observable;

import io.reactivex.Completable;
import io.reactivex.ObservableSource;

public final class ObservableIgnoreElementsCompletable<T> extends Completable {
    final ObservableSource<T> source;

    public ObservableIgnoreElementsCompletable(ObservableSource<T> observableSource) {
        this.source = observableSource;
    }
}
