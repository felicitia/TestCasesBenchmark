package com.google.android.gms.measurement;

import android.content.BroadcastReceiver.PendingResult;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.MainThread;
import android.support.v4.content.WakefulBroadcastReceiver;
import com.google.android.gms.internal.measurement.bl;
import com.google.android.gms.internal.measurement.bo;

public final class AppMeasurementReceiver extends WakefulBroadcastReceiver implements bo {
    private bl zzadc;

    public final PendingResult doGoAsync() {
        return goAsync();
    }

    @MainThread
    public final void doStartService(Context context, Intent intent) {
        startWakefulService(context, intent);
    }

    @MainThread
    public final void onReceive(Context context, Intent intent) {
        if (this.zzadc == null) {
            this.zzadc = new bl(this);
        }
        this.zzadc.a(context, intent);
    }
}
