package com.google.android.gms.ads.internal;

import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.internal.ads.bu;
import com.google.android.gms.internal.ads.gq;
import com.google.android.gms.internal.ads.hd;

@bu
final class l extends gq {
    final /* synthetic */ zzal a;
    private final int b;

    public l(zzal zzal, int i) {
        this.a = zzal;
        this.b = i;
    }

    public final void a() {
        zzaq zzaq = new zzaq(this.a.zzvw.zzze, this.a.zzdi(), this.a.zzys, this.a.zzyt, this.a.zzvw.zzze ? this.b : -1, this.a.zzyu, this.a.zzvw.zzacw.L, this.a.zzvw.zzacw.O);
        int requestedOrientation = this.a.zzvw.zzacw.b.getRequestedOrientation();
        if (requestedOrientation == -1) {
            requestedOrientation = this.a.zzvw.zzacw.h;
        }
        AdOverlayInfoParcel adOverlayInfoParcel = new AdOverlayInfoParcel(this.a, this.a, this.a, this.a.zzvw.zzacw.b, requestedOrientation, this.a.zzvw.zzacr, this.a.zzvw.zzacw.A, zzaq);
        hd.a.post(new m(this, adOverlayInfoParcel));
    }

    public final void c_() {
    }
}
