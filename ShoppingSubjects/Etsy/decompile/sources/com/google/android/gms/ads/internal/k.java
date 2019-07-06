package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.et;
import com.google.android.gms.internal.ads.zzaig;

final class k implements et {
    private final /* synthetic */ zzal a;

    k(zzal zzal) {
        this.a = zzal;
    }

    public final void onRewardedVideoAdClosed() {
        this.a.zzcb();
    }

    public final void onRewardedVideoAdLeftApplication() {
        this.a.zzbo();
    }

    public final void onRewardedVideoAdOpened() {
        this.a.zzcc();
    }

    public final void onRewardedVideoCompleted() {
        this.a.zzdl();
    }

    public final void onRewardedVideoStarted() {
        this.a.zzdk();
    }

    public final void zzc(zzaig zzaig) {
        this.a.zzb(zzaig);
    }

    public final void zzdm() {
        this.a.onAdClicked();
    }
}
