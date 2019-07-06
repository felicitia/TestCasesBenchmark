package com.google.android.gms.ads.internal;

import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.h;

final class m implements Runnable {
    private final /* synthetic */ AdOverlayInfoParcel a;
    private final /* synthetic */ l b;

    m(l lVar, AdOverlayInfoParcel adOverlayInfoParcel) {
        this.b = lVar;
        this.a = adOverlayInfoParcel;
    }

    public final void run() {
        ao.c();
        h.a(this.b.a.zzvw.zzrt, this.a, true);
    }
}
