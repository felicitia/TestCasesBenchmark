package com.google.android.gms.analytics;

final class zzl implements Runnable {
    private final /* synthetic */ zzg zzsg;
    private final /* synthetic */ zzk zzsh;

    zzl(zzk zzk, zzg zzg) {
        this.zzsh = zzk;
        this.zzsg = zzg;
    }

    public final void run() {
        this.zzsg.zzv().zza(this.zzsg);
        for (zzn zza : this.zzsh.zzsb) {
            zza.zza(this.zzsg);
        }
        zzk.zzb(this.zzsg);
    }
}
