package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.kz;

final /* synthetic */ class t implements Runnable {
    private final zzay a;
    private final Runnable b;

    t(zzay zzay, Runnable runnable) {
        this.a = zzay;
        this.b = runnable;
    }

    public final void run() {
        kz.a.execute(new v(this.a, this.b));
    }
}
