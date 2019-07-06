package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResult.StatusListener;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;

public final class OptionalPendingResultImpl<R extends Result> extends OptionalPendingResult<R> {
    private final BasePendingResult<R> zzlo;

    public OptionalPendingResultImpl(PendingResult<R> pendingResult) {
        this.zzlo = (BasePendingResult) pendingResult;
    }

    public final void addStatusListener(StatusListener statusListener) {
        this.zzlo.addStatusListener(statusListener);
    }

    public final void cancel() {
        this.zzlo.cancel();
    }

    public final boolean isCanceled() {
        return this.zzlo.isCanceled();
    }

    public final void setResultCallback(ResultCallback<? super R> resultCallback) {
        this.zzlo.setResultCallback(resultCallback);
    }

    public final Integer zzo() {
        return this.zzlo.zzo();
    }
}
