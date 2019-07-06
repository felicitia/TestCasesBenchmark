package com.google.android.gms.internal.measurement;

final class ch implements Runnable {
    private final /* synthetic */ zzex a;
    private final /* synthetic */ String b;
    private final /* synthetic */ zzgp c;

    ch(zzgp zzgp, zzex zzex, String str) {
        this.c = zzgp;
        this.a = zzex;
        this.b = str;
    }

    public final void run() {
        this.c.zzalo.k();
        this.c.zzalo.a(this.a, this.b);
    }
}
