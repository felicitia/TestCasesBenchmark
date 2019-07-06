package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.ga;
import com.google.android.gms.internal.ads.gb;

final class av implements Runnable {
    private final /* synthetic */ gb a;
    private final /* synthetic */ zzi b;

    av(zzi zzi, gb gbVar) {
        this.b = zzi;
        this.a = gbVar;
    }

    public final void run() {
        zzi zzi = this.b;
        ga gaVar = new ga(this.a, null, null, null, null, null, null, null);
        zzi.zzb(gaVar);
    }
}
