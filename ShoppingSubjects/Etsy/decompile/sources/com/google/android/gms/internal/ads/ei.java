package com.google.android.gms.internal.ads;

final class ei implements Runnable {
    private final /* synthetic */ gb a;
    private final /* synthetic */ zzagr b;

    ei(zzagr zzagr, gb gbVar) {
        this.b = zzagr;
        this.a = gbVar;
    }

    public final void run() {
        zzagr zzagr = this.b;
        ga gaVar = new ga(this.a, null, null, null, null, null, null, null);
        zzagr.zzb(gaVar);
    }
}
