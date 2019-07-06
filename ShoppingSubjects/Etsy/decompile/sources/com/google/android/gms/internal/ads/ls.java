package com.google.android.gms.internal.ads;

final class ls implements Runnable {
    private final /* synthetic */ zzaov a;

    ls(zzaov zzaov) {
        this.a = zzaov;
    }

    public final void run() {
        if (this.a.zzcxd != null) {
            this.a.zzcxd.zzsx();
        }
    }
}
