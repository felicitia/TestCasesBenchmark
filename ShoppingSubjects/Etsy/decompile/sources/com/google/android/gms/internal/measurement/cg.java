package com.google.android.gms.internal.measurement;

final class cg implements Runnable {
    private final /* synthetic */ zzex a;
    private final /* synthetic */ zzeb b;
    private final /* synthetic */ zzgp c;

    cg(zzgp zzgp, zzex zzex, zzeb zzeb) {
        this.c = zzgp;
        this.a = zzex;
        this.b = zzeb;
    }

    public final void run() {
        this.c.zzalo.k();
        this.c.zzalo.a(this.a, this.b);
    }
}
