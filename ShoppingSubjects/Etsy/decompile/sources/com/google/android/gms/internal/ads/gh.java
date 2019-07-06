package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.ao;

final class gh extends gq {
    private final /* synthetic */ gf a;

    gh(gf gfVar) {
        this.a = gfVar;
    }

    public final void a() {
        akn akn = new akn(this.a.f, this.a.g.zzcw);
        synchronized (this.a.a) {
            try {
                ao.n();
                akq.a(this.a.h, akn);
            } catch (IllegalArgumentException e) {
                gv.c("Cannot config CSI reporter.", e);
            }
        }
    }

    public final void c_() {
    }
}
