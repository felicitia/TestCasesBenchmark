package com.google.android.gms.internal.ads;

final /* synthetic */ class aph implements Runnable {
    private final apg a;
    private final ack b;
    private final apx c;

    aph(apg apg, ack ack, apx apx) {
        this.a = apg;
        this.b = ack;
        this.c = apx;
    }

    public final void run() {
        this.a.a(this.b, this.c);
    }
}
