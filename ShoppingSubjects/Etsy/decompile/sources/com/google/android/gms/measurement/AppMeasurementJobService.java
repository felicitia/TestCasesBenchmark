package com.google.android.gms.measurement;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.support.annotation.MainThread;
import com.google.android.gms.internal.measurement.ej;
import com.google.android.gms.internal.measurement.en;

@TargetApi(24)
public final class AppMeasurementJobService extends JobService implements en {
    private ej<AppMeasurementJobService> zzadd;

    private final ej<AppMeasurementJobService> zzfq() {
        if (this.zzadd == null) {
            this.zzadd = new ej<>(this);
        }
        return this.zzadd;
    }

    public final boolean callServiceStopSelfResult(int i) {
        throw new UnsupportedOperationException();
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

    public final boolean onStartJob(JobParameters jobParameters) {
        return zzfq().a(jobParameters);
    }

    public final boolean onStopJob(JobParameters jobParameters) {
        return false;
    }

    @MainThread
    public final boolean onUnbind(Intent intent) {
        return zzfq().b(intent);
    }

    @TargetApi(24)
    public final void zza(JobParameters jobParameters, boolean z) {
        jobFinished(jobParameters, false);
    }

    public final void zzb(Intent intent) {
    }
}
