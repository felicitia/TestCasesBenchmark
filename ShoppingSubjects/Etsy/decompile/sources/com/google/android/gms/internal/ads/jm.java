package com.google.android.gms.internal.ads;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

final class jm extends BroadcastReceiver {
    private final /* synthetic */ jl a;

    jm(jl jlVar) {
        this.a = jlVar;
    }

    public final void onReceive(Context context, Intent intent) {
        this.a.a(context, intent);
    }
}
