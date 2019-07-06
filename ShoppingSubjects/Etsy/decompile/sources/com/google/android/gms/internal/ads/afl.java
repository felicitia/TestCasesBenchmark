package com.google.android.gms.internal.ads;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

final class afl extends BroadcastReceiver {
    private final /* synthetic */ zzfp a;

    afl(zzfp zzfp) {
        this.a = zzfp;
    }

    public final void onReceive(Context context, Intent intent) {
        this.a.zzm(3);
    }
}
