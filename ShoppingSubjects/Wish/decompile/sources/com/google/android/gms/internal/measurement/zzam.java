package com.google.android.gms.internal.measurement;

final class zzam implements Runnable {
    private final /* synthetic */ zzai zzvg;
    private final /* synthetic */ zzch zzvk;

    zzam(zzai zzai, zzch zzch) {
        this.zzvg = zzai;
        this.zzvk = zzch;
    }

    public final void run() {
        this.zzvg.zzve.zza(this.zzvk);
    }
}
