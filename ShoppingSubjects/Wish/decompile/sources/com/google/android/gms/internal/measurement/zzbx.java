package com.google.android.gms.internal.measurement;

import android.os.Looper;

final class zzbx implements Runnable {
    private final /* synthetic */ zzbw zzye;

    zzbx(zzbw zzbw) {
        this.zzye = zzbw;
    }

    public final void run() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            this.zzye.zzvm.zzbw().zza((Runnable) this);
            return;
        }
        boolean zzef = this.zzye.zzef();
        this.zzye.zzyd = 0;
        if (zzef) {
            this.zzye.run();
        }
    }
}
