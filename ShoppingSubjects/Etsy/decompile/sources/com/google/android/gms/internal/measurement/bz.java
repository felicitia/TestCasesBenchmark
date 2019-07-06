package com.google.android.gms.internal.measurement;

final class bz implements Runnable {
    private final /* synthetic */ zzef a;
    private final /* synthetic */ zzgp b;

    bz(zzgp zzgp, zzef zzef) {
        this.b = zzgp;
        this.a = zzef;
    }

    public final void run() {
        this.b.zzalo.k();
        this.b.zzalo.b(this.a);
    }
}
