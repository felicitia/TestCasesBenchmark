package com.google.android.gms.internal.ads;

final /* synthetic */ class lq implements Runnable {
    private final zzaov a;
    private final int b;

    lq(zzaov zzaov, int i) {
        this.a = zzaov;
        this.b = i;
    }

    public final void run() {
        this.a.zzah(this.b);
    }
}
