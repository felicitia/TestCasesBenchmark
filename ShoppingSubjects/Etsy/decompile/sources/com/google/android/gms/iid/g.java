package com.google.android.gms.iid;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

final class g extends Handler {
    private final /* synthetic */ f a;

    g(f fVar, Looper looper) {
        this.a = fVar;
        super(looper);
    }

    public final void handleMessage(Message message) {
        this.a.a(message);
    }
}
