package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.ga;

final class ax implements Runnable {
    private final /* synthetic */ aw a;

    ax(aw awVar) {
        this.a = awVar;
    }

    public final void run() {
        zzi zzi = this.a.c;
        ga gaVar = new ga(this.a.a, null, null, null, null, null, null, null);
        zzi.zzb(gaVar);
    }
}
