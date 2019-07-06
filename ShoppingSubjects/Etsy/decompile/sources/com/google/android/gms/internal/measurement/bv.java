package com.google.android.gms.internal.measurement;

final class bv implements Runnable {
    private final /* synthetic */ cr a;
    private final /* synthetic */ bu b;

    bv(bu buVar, cr crVar) {
        this.b = buVar;
        this.a = crVar;
    }

    public final void run() {
        this.b.a(this.a);
        this.b.a();
    }
}
