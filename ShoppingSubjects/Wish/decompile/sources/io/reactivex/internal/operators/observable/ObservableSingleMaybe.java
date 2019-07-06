package io.reactivex.internal.operators.observable;

import io.reactivex.Maybe;
import io.reactivex.ObservableSource;

public final class ObservableSingleMaybe<T> extends Maybe<T> {
    final ObservableSource<T> source;

    public ObservableSingleMaybe(ObservableSource<T> observableSource) {
        this.source = observableSource;
    }
}
