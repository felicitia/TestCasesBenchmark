package com.google.android.gms.internal.measurement;

final class zzao implements Runnable {
    private final /* synthetic */ zzai zzvg;
    private final /* synthetic */ zzca zzvl;

    zzao(zzai zzai, zzca zzca) {
        this.zzvg = zzai;
        this.zzvl = zzca;
    }

    public final void run() {
        this.zzvg.zzve.zzb(this.zzvl);
    }
}
