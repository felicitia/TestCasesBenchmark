package com.google.android.gms.internal.measurement;

import android.os.Bundle;

final class zzjk extends zzep {
    private final /* synthetic */ zzjj zzaro;

    zzjk(zzjj zzjj, zzhk zzhk) {
        this.zzaro = zzjj;
        super(zzhk);
    }

    public final void run() {
        zzjj zzjj = this.zzaro;
        zzjj.zzab();
        zzjj.zzgi().zzjc().zzg("Session started, time", Long.valueOf(zzjj.zzbt().elapsedRealtime()));
        zzjj.zzgj().zzamj.set(false);
        zzjj.zzfy().zza("auto", "_s", new Bundle());
        zzjj.zzgj().zzamk.set(zzjj.zzbt().currentTimeMillis());
    }
}
