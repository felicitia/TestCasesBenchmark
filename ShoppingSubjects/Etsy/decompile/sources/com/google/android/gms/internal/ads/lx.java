package com.google.android.gms.internal.ads;

final class lx implements Runnable {
    private final /* synthetic */ zzaov a;

    lx(zzaov zzaov) {
        this.a = zzaov;
    }

    public final void run() {
        if (this.a.zzcxd != null) {
            this.a.zzcxd.onPaused();
            this.a.zzcxd.zzsy();
        }
    }
}
