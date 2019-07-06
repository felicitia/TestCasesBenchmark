package com.google.android.gms.internal.ads;

final class app implements lj<aou> {
    private final /* synthetic */ apx a;
    private final /* synthetic */ apg b;

    app(apg apg, apx apx) {
        this.b = apg;
        this.a = apx;
    }

    public final /* synthetic */ void a(Object obj) {
        synchronized (this.b.a) {
            this.b.h = 0;
            if (!(this.b.g == null || this.a == this.b.g)) {
                gv.a("New JS engine is loaded, marking previous one as destroyable.");
                this.b.g.e();
            }
            this.b.g = this.a;
        }
    }
}
