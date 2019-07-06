package com.google.android.gms.internal.firebase-perf;

final class zzk implements Runnable {
    private final /* synthetic */ zzh zzbm;
    private final /* synthetic */ int zzbo;
    private final /* synthetic */ zzap zzbp;

    zzk(zzh zzh, zzap zzap, int i) {
        this.zzbm = zzh;
        this.zzbp = zzap;
        this.zzbo = i;
    }

    public final void run() {
        this.zzbm.zzb(this.zzbp, this.zzbo);
    }
}
