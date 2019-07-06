package com.google.android.gms.common.api;

import com.google.android.gms.common.api.Result;

public abstract class PendingResult<R extends Result> {

    public interface StatusListener {
        void onComplete(Status status);
    }

    public void addStatusListener(StatusListener statusListener) {
        throw new UnsupportedOperationException();
    }

    public abstract void cancel();

    public abstract boolean isCanceled();

    public abstract void setResultCallback(ResultCallback<? super R> resultCallback);

    public Integer zzo() {
        throw new UnsupportedOperationException();
    }
}
