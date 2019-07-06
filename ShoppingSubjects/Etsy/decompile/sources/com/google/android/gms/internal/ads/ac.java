package com.google.android.gms.internal.ads;

final class ac implements Runnable {
    private final /* synthetic */ ga a;
    private final /* synthetic */ ab b;

    ac(ab abVar, ga gaVar) {
        this.b = abVar;
        this.a = gaVar;
    }

    public final void run() {
        this.b.a.zzb(this.a);
    }
}
