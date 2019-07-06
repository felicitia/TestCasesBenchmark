package com.google.android.gms.internal.ads;

final class ly implements Runnable {
    private final /* synthetic */ zzaov a;

    ly(zzaov zzaov) {
        this.a = zzaov;
    }

    public final void run() {
        if (this.a.zzcxd != null) {
            this.a.zzcxd.zzsw();
        }
    }
}
