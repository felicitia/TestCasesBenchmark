package com.google.android.gms.internal.ads;

final /* synthetic */ class apj implements Runnable {
    private final apg a;
    private final apx b;
    private final aou c;

    apj(apg apg, apx apx, aou aou) {
        this.a = apg;
        this.b = apx;
        this.c = aou;
    }

    public final void run() {
        this.a.a(this.b, this.c);
    }
}
