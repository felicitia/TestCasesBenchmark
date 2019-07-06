package com.google.android.gms.ads.internal;

final /* synthetic */ class v implements Runnable {
    private final zzay a;
    private final Runnable b;

    v(zzay zzay, Runnable runnable) {
        this.a = zzay;
        this.b = runnable;
    }

    public final void run() {
        this.a.zza(this.b);
    }
}
