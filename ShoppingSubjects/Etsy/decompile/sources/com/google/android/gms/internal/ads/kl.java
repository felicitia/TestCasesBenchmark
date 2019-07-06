package com.google.android.gms.internal.ads;

final /* synthetic */ class kl implements Runnable {
    private final le a;
    private final kd b;
    private final kt c;

    kl(le leVar, kd kdVar, kt ktVar) {
        this.a = leVar;
        this.b = kdVar;
        this.c = ktVar;
    }

    public final void run() {
        ki.a(this.a, this.b, this.c);
    }
}
