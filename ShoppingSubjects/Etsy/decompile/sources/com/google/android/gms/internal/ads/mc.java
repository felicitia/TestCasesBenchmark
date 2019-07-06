package com.google.android.gms.internal.ads;

final /* synthetic */ class mc implements Runnable {
    private final zzapg a;

    private mc(zzapg zzapg) {
        this.a = zzapg;
    }

    static Runnable a(zzapg zzapg) {
        return new mc(zzapg);
    }

    public final void run() {
        this.a.stop();
    }
}
