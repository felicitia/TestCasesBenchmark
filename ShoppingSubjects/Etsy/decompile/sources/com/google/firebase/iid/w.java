package com.google.firebase.iid;

import android.content.Intent;
import android.util.Log;

final class w implements Runnable {
    private final /* synthetic */ Intent a;
    private final /* synthetic */ v b;

    w(v vVar, Intent intent) {
        this.b = vVar;
        this.a = intent;
    }

    public final void run() {
        String action = this.a.getAction();
        StringBuilder sb = new StringBuilder(61 + String.valueOf(action).length());
        sb.append("Service took too long to process intent: ");
        sb.append(action);
        sb.append(" App may get closed.");
        Log.w("EnhancedIntentService", sb.toString());
        this.b.a();
    }
}
