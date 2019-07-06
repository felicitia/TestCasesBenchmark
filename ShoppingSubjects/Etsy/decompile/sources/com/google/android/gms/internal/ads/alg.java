package com.google.android.gms.internal.ads;

final class alg implements Runnable {
    private final /* synthetic */ zzos a;

    alg(zzos zzos) {
        this.a = zzos;
    }

    public final void run() {
        if (this.a.zzbij != null) {
            this.a.zzbij.i();
            this.a.zzbij.h();
        }
        this.a.zzbij = null;
    }
}
