package com.google.android.gms.internal.ads;

final class ale implements Runnable {
    private final /* synthetic */ zzoq a;

    ale(zzoq zzoq) {
        this.a = zzoq;
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
