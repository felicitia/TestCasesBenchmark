package com.google.android.gms.internal.ads;

final class alh implements Runnable {
    private final /* synthetic */ zzov a;

    alh(zzov zzov) {
        this.a = zzov;
    }

    public final void run() {
        if (this.a.zzbij != null) {
            this.a.zzbij.i();
            this.a.zzbij.h();
            this.a.zzbij.k();
        }
        this.a.zzbij = null;
    }
}
