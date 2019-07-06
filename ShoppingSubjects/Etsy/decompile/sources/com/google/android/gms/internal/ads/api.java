package com.google.android.gms.internal.ads;

final /* synthetic */ class api implements aov {
    private final apg a;
    private final apx b;
    private final aou c;

    api(apg apg, apx apx, aou aou) {
        this.a = apg;
        this.b = apx;
        this.c = aou;
    }

    public final void a() {
        hd.a.postDelayed(new apj(this.a, this.b, this.c), (long) apr.b);
    }
}
