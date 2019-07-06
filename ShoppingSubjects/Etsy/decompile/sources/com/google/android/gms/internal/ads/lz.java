package com.google.android.gms.internal.ads;

final class lz implements Runnable {
    private final /* synthetic */ zzaov a;

    lz(zzaov zzaov) {
        this.a = zzaov;
    }

    public final void run() {
        if (this.a.zzcxd != null) {
            this.a.zzcxd.onPaused();
        }
    }
}
