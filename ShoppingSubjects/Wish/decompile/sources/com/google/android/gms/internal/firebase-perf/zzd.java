package com.google.android.gms.internal.firebase-perf;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

final class zzd extends BroadcastReceiver {
    private final /* synthetic */ zzc zzai;

    zzd(zzc zzc) {
        this.zzai = zzc;
    }

    public final void onReceive(Context context, Intent intent) {
        if (this.zzai.hasStarted() && !this.zzai.isStopped()) {
            this.zzai.zzag.add(zzw.zzae().zzaf());
        }
    }
}
