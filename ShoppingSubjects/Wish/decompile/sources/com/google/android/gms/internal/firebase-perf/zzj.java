package com.google.android.gms.internal.firebase-perf;

final class zzj implements Runnable {
    private final /* synthetic */ zzh zzbm;
    private final /* synthetic */ zzat zzbn;
    private final /* synthetic */ int zzbo;

    zzj(zzh zzh, zzat zzat, int i) {
        this.zzbm = zzh;
        this.zzbn = zzat;
        this.zzbo = i;
    }

    public final void run() {
        this.zzbm.zzb(this.zzbn, this.zzbo);
    }
}
