package com.google.android.gms.iid;

import android.os.Handler.Callback;
import android.os.Message;

final /* synthetic */ class w implements Callback {
    private final v a;

    w(v vVar) {
        this.a = vVar;
    }

    public final boolean handleMessage(Message message) {
        return this.a.a(message);
    }
}
