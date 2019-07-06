package com.google.android.gms.internal.ads;

final class lw implements Runnable {
    private final /* synthetic */ int a;
    private final /* synthetic */ int b;
    private final /* synthetic */ zzaov c;

    lw(zzaov zzaov, int i, int i2) {
        this.c = zzaov;
        this.a = i;
        this.b = i2;
    }

    public final void run() {
        if (this.c.zzcxd != null) {
            this.c.zzcxd.zzf(this.a, this.b);
        }
    }
}
