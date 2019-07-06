package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.doubleclick.PublisherAdView;

final class amk implements Runnable {
    private final /* synthetic */ PublisherAdView a;
    private final /* synthetic */ zzks b;
    private final /* synthetic */ zzsb c;

    amk(zzsb zzsb, PublisherAdView publisherAdView, zzks zzks) {
        this.c = zzsb;
        this.a = publisherAdView;
        this.b = zzks;
    }

    public final void run() {
        if (this.a.zza(this.b)) {
            this.c.zzblf.a(this.a);
        } else {
            ka.e("Could not bind.");
        }
    }
}
