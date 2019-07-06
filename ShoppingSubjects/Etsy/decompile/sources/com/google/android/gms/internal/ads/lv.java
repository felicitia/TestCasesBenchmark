package com.google.android.gms.internal.ads;

final class lv implements Runnable {
    private final /* synthetic */ zzaov a;

    lv(zzaov zzaov) {
        this.a = zzaov;
    }

    public final void run() {
        if (this.a.zzcxd != null) {
            this.a.zzcxd.zzsu();
        }
    }
}
