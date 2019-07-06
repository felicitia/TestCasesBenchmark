package com.google.android.gms.internal.measurement;

final class ca implements Runnable {
    private final /* synthetic */ zzef a;
    private final /* synthetic */ zzgp b;

    ca(zzgp zzgp, zzef zzef) {
        this.b = zzgp;
        this.a = zzef;
    }

    public final void run() {
        this.b.zzalo.k();
        this.b.zzalo.a(this.a);
    }
}
