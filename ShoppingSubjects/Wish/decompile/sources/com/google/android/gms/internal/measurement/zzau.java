package com.google.android.gms.internal.measurement;

import java.lang.Thread.UncaughtExceptionHandler;

final class zzau implements UncaughtExceptionHandler {
    private final /* synthetic */ zzat zzwd;

    zzau(zzat zzat) {
        this.zzwd = zzat;
    }

    public final void uncaughtException(Thread thread, Throwable th) {
        zzcm zzcj = this.zzwd.zzcj();
        if (zzcj != null) {
            zzcj.zze("Job execution failed", th);
        }
    }
}
