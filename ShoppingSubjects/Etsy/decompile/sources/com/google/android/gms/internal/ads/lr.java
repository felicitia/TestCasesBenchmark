package com.google.android.gms.internal.ads;

final class lr implements Runnable {
    private final /* synthetic */ zzaov a;

    lr(zzaov zzaov) {
        this.a = zzaov;
    }

    public final void run() {
        if (this.a.zzcxd != null) {
            this.a.zzcxd.zzsv();
        }
    }
}
