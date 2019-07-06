package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.a;

@bu
public final class zzjf extends zzki {
    private final a zzapu;

    public zzjf(a aVar) {
        this.zzapu = aVar;
    }

    public final a getAdListener() {
        return this.zzapu;
    }

    public final void onAdClicked() {
        this.zzapu.onAdClicked();
    }

    public final void onAdClosed() {
        this.zzapu.c();
    }

    public final void onAdFailedToLoad(int i) {
        this.zzapu.a(i);
    }

    public final void onAdImpression() {
        this.zzapu.e();
    }

    public final void onAdLeftApplication() {
        this.zzapu.d();
    }

    public final void onAdLoaded() {
        this.zzapu.a();
    }

    public final void onAdOpened() {
        this.zzapu.b();
    }
}
