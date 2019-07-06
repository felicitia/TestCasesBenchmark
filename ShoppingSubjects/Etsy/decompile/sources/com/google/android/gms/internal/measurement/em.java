package com.google.android.gms.internal.measurement;

final class em implements Runnable {
    private final /* synthetic */ ey a;
    private final /* synthetic */ Runnable b;

    em(ej ejVar, ey eyVar, Runnable runnable) {
        this.a = eyVar;
        this.b = runnable;
    }

    public final void run() {
        this.a.k();
        this.a.a(this.b);
        this.a.j();
    }
}
