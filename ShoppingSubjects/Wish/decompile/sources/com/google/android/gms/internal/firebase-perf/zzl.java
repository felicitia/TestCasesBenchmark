package com.google.android.gms.internal.firebase-perf;

final class zzl implements Runnable {
    private final /* synthetic */ zzh zzbm;
    private final /* synthetic */ boolean zzbq;

    zzl(zzh zzh, boolean z) {
        this.zzbm = zzh;
        this.zzbq = z;
    }

    public final void run() {
        this.zzbm.zzc(this.zzbq);
    }
}
