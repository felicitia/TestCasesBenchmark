package com.google.android.gms.internal.ads;

final class ald implements Runnable {
    private final /* synthetic */ zzoo a;

    ald(zzoo zzoo) {
        this.a = zzoo;
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
