package com.paypal.android.sdk.onetouch.core.metadata;

import android.os.Handler;
import android.os.Message;
import java.lang.ref.WeakReference;

final class k extends Handler {
    private final WeakReference<h> a;

    public k(h hVar) {
        this.a = new WeakReference<>(hVar);
    }

    public final void handleMessage(Message message) {
        h hVar = (h) this.a.get();
        if (hVar != null) {
            hVar.a(message);
        }
    }
}
