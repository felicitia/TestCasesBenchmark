package com.google.android.gms.internal.measurement;

final class cf implements Runnable {
    private final /* synthetic */ zzeb a;
    private final /* synthetic */ zzgp b;

    cf(zzgp zzgp, zzeb zzeb) {
        this.b = zzgp;
        this.a = zzeb;
    }

    public final void run() {
        this.b.zzalo.k();
        this.b.zzalo.b(this.a);
    }
}
