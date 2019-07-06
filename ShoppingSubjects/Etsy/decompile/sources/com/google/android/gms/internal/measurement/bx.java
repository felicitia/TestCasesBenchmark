package com.google.android.gms.internal.measurement;

final class bx implements Runnable {
    private final /* synthetic */ zzef a;
    private final /* synthetic */ zzeb b;
    private final /* synthetic */ zzgp c;

    bx(zzgp zzgp, zzef zzef, zzeb zzeb) {
        this.c = zzgp;
        this.a = zzef;
        this.b = zzeb;
    }

    public final void run() {
        this.c.zzalo.k();
        this.c.zzalo.b(this.a, this.b);
    }
}
