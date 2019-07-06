package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import com.google.android.gms.ads.reward.a;
import com.google.android.gms.ads.reward.b;

@bu
public final class zzahj extends zzahf {
    @Nullable
    private b zzhc;

    public zzahj(@Nullable b bVar) {
        this.zzhc = bVar;
    }

    @Nullable
    public final b getRewardedVideoAdListener() {
        return this.zzhc;
    }

    public final void onRewardedVideoAdClosed() {
        if (this.zzhc != null) {
            this.zzhc.d();
        }
    }

    public final void onRewardedVideoAdFailedToLoad(int i) {
        if (this.zzhc != null) {
            this.zzhc.a(i);
        }
    }

    public final void onRewardedVideoAdLeftApplication() {
        if (this.zzhc != null) {
            this.zzhc.e();
        }
    }

    public final void onRewardedVideoAdLoaded() {
        if (this.zzhc != null) {
            this.zzhc.a();
        }
    }

    public final void onRewardedVideoAdOpened() {
        if (this.zzhc != null) {
            this.zzhc.b();
        }
    }

    public final void onRewardedVideoCompleted() {
        if (this.zzhc != null) {
            this.zzhc.f();
        }
    }

    public final void onRewardedVideoStarted() {
        if (this.zzhc != null) {
            this.zzhc.c();
        }
    }

    public final void setRewardedVideoAdListener(b bVar) {
        this.zzhc = bVar;
    }

    public final void zza(zzagu zzagu) {
        if (this.zzhc != null) {
            this.zzhc.a((a) new ek(zzagu));
        }
    }
}
