package com.google.android.gms.internal.measurement;

final class ck implements Runnable {
    private final /* synthetic */ zzka a;
    private final /* synthetic */ zzeb b;
    private final /* synthetic */ zzgp c;

    ck(zzgp zzgp, zzka zzka, zzeb zzeb) {
        this.c = zzgp;
        this.a = zzka;
        this.b = zzeb;
    }

    public final void run() {
        this.c.zzalo.k();
        this.c.zzalo.a(this.a, this.b);
    }
}
