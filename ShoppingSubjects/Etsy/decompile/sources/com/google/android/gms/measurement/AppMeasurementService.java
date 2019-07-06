package com.google.android.gms.measurement;

import android.app.Service;
import android.app.job.JobParameters;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.MainThread;
import com.google.android.gms.internal.measurement.ej;
import com.google.android.gms.internal.measurement.en;

public final class AppMeasurementService extends Service implements en {
    private ej<AppMeasurementService> zzadd;

    private final ej<AppMeasurementService> zzfq() {
        if (this.zzadd == null) {
            this.zzadd = new ej<>(this);
        }
        return this.zzadd;
    }

    public final boolean callServiceStopSelfResult(int i) {
        return stopSelfResult(i);
    }

    @MainThread
    public final IBinder onBind(Intent intent) {
        return zzfq().a(intent);
    }

    @MainThread
    public final void onCreate() {
        super.onCreate();
        zzfq().a();
    }

    @MainThread
    public final void onDestroy() {
        zzfq().b();
        super.onDestroy();
    }

    @MainThread
    public final void onRebind(Intent intent) {
        zzfq().c(intent);
    }

    @MainThread
    public final int onStartCommand(Intent intent, int i, int i2) {
        return zzfq().a(intent, i, i2);
    }

    @MainThread
    public final boolean onUnbind(Intent intent) {
        return zzfq().b(intent);
    }

    public final void zza(JobParameters jobParameters, boolean z) {
        throw new UnsupportedOperationException();
    }

    public final void zzb(Intent intent) {
        AppMeasurementReceiver.completeWakefulIntent(intent);
    }
}
