package com.google.android.gms.internal.measurement;

final class ez implements Runnable {
    private final /* synthetic */ fd a;
    private final /* synthetic */ ey b;

    ez(ey eyVar, fd fdVar) {
        this.b = eyVar;
        this.a = fdVar;
    }

    public final void run() {
        this.b.a(this.a);
        this.b.a();
    }
}
