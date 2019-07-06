package com.google.android.gms.internal.ads;

final class apq implements lh {
    private final /* synthetic */ apx a;
    private final /* synthetic */ apg b;

    apq(apg apg, apx apx) {
        this.b = apg;
        this.a = apx;
    }

    public final void a() {
        synchronized (this.b.a) {
            this.b.h = 1;
            gv.a("Failed loading new engine. Marking new engine destroyable.");
            this.a.e();
        }
    }
}
