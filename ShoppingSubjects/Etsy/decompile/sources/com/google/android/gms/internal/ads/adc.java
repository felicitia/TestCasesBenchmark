package com.google.android.gms.internal.ads;

final class adc implements Runnable {
    private final /* synthetic */ int a;
    private final /* synthetic */ boolean b;
    private final /* synthetic */ acy c;

    adc(acy acy, int i, boolean z) {
        this.c = acy;
        this.a = i;
        this.b = z;
    }

    public final void run() {
        vy b2 = this.c.b(this.a, this.b);
        this.c.k = b2;
        if (acy.b(this.a, b2)) {
            this.c.a(this.a + 1, this.b);
        }
    }
}
