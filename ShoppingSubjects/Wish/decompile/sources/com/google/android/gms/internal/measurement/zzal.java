package com.google.android.gms.internal.measurement;

final class zzal implements Runnable {
    private final /* synthetic */ zzai zzvg;
    private final /* synthetic */ String zzvi;
    private final /* synthetic */ Runnable zzvj;

    zzal(zzai zzai, String str, Runnable runnable) {
        this.zzvg = zzai;
        this.zzvi = str;
        this.zzvj = runnable;
    }

    public final void run() {
        this.zzvg.zzve.zzy(this.zzvi);
        if (this.zzvj != null) {
            this.zzvj.run();
        }
    }
}
