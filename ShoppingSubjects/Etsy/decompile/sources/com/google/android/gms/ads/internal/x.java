package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.ga;
import com.google.android.gms.internal.ads.gb;

final class x implements Runnable {
    private final /* synthetic */ gb a;
    private final /* synthetic */ zzbc b;

    x(zzbc zzbc, gb gbVar) {
        this.b = zzbc;
        this.a = gbVar;
    }

    public final void run() {
        zzbc zzbc = this.b;
        ga gaVar = new ga(this.a, null, null, null, null, null, null, null);
        zzbc.zzb(gaVar);
    }
}
