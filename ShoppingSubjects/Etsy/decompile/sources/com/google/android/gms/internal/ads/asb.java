package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.overlay.i;

final class asb implements i {
    private final /* synthetic */ zzzv a;

    asb(zzzv zzzv) {
        this.a = zzzv;
    }

    public final void onPause() {
        ka.b("AdMobCustomTabsAdapter overlay is paused.");
    }

    public final void onResume() {
        ka.b("AdMobCustomTabsAdapter overlay is resumed.");
    }

    public final void zzcb() {
        ka.b("AdMobCustomTabsAdapter overlay is closed.");
        this.a.b.c(this.a);
    }

    public final void zzcc() {
        ka.b("Opening AdMobCustomTabsAdapter overlay.");
        this.a.b.b(this.a);
    }
}
