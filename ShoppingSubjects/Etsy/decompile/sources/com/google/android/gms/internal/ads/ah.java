package com.google.android.gms.internal.ads;

final class ah implements Runnable {
    private final /* synthetic */ ga a;
    private final /* synthetic */ ag b;

    ah(ag agVar, ga gaVar) {
        this.b = agVar;
        this.a = gaVar;
    }

    public final void run() {
        this.b.a.zzb(this.a);
    }
}
