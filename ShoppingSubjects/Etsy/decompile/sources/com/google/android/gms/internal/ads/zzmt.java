package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.i.a;

public final class zzmt extends zzls {
    private final a zzuy;

    public zzmt(a aVar) {
        this.zzuy = aVar;
    }

    public final void onVideoEnd() {
        this.zzuy.d();
    }

    public final void onVideoMute(boolean z) {
        this.zzuy.a(z);
    }

    public final void onVideoPause() {
        this.zzuy.c();
    }

    public final void onVideoPlay() {
        this.zzuy.b();
    }

    public final void onVideoStart() {
        this.zzuy.a();
    }
}
