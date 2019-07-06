package com.google.firebase.iid;

import android.os.Handler.Callback;
import android.os.Message;

final /* synthetic */ class am implements Callback {
    private final al a;

    am(al alVar) {
        this.a = alVar;
    }

    public final boolean handleMessage(Message message) {
        return this.a.a(message);
    }
}
