package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.h;

final class asc implements Runnable {
    private final /* synthetic */ AdOverlayInfoParcel a;
    private final /* synthetic */ zzzv b;

    asc(zzzv zzzv, AdOverlayInfoParcel adOverlayInfoParcel) {
        this.b = zzzv;
        this.a = adOverlayInfoParcel;
    }

    public final void run() {
        ao.c();
        h.a(this.b.a, this.a, true);
    }
}
