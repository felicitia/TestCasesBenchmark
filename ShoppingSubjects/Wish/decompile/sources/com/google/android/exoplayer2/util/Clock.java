package com.google.android.exoplayer2.util;

import android.os.Handler.Callback;
import android.os.Looper;

public interface Clock {
    public static final Clock DEFAULT = new SystemClock();

    HandlerWrapper createHandler(Looper looper, Callback callback);

    long elapsedRealtime();

    long uptimeMillis();
}
