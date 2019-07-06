package com.google.android.gms.internal.measurement;

final class dh implements Runnable {
    private final /* synthetic */ boolean a;
    private final /* synthetic */ cs b;

    dh(cs csVar, boolean z) {
        this.b = csVar;
        this.a = z;
    }

    public final void run() {
        this.b.c(this.a);
    }
}
