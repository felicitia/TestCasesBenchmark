package com.google.android.gms.internal.ads;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

final class hi extends BroadcastReceiver {
    private final /* synthetic */ hd a;

    private hi(hd hdVar) {
        this.a = hdVar;
    }

    /* synthetic */ hi(hd hdVar, he heVar) {
        this(hdVar);
    }

    public final void onReceive(Context context, Intent intent) {
        if ("android.intent.action.USER_PRESENT".equals(intent.getAction())) {
            this.a.c = true;
            return;
        }
        if ("android.intent.action.SCREEN_OFF".equals(intent.getAction())) {
            this.a.c = false;
        }
    }
}
