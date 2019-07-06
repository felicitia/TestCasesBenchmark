package com.google.firebase.perf.metrics;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.internal.firebase-perf.zzw;

final class zzc extends BroadcastReceiver {
    private final /* synthetic */ Trace zzdy;

    zzc(Trace trace) {
        this.zzdy = trace;
    }

    public final void onReceive(Context context, Intent intent) {
        if (this.zzdy.hasStarted() && !this.zzdy.isStopped()) {
            this.zzdy.zzag.add(zzw.zzae().zzaf());
        }
    }
}
