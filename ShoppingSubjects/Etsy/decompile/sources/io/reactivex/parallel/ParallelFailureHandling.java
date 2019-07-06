package io.reactivex.parallel;

import io.reactivex.functions.c;

public enum ParallelFailureHandling implements c<Long, Throwable, ParallelFailureHandling> {
    STOP,
    ERROR,
    SKIP,
    RETRY;

    public ParallelFailureHandling apply(Long l, Throwable th) {
        return this;
    }
}
