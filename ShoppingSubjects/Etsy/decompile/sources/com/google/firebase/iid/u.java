package com.google.firebase.iid;

import android.content.Intent;

final class u implements Runnable {
    private final /* synthetic */ Intent a;
    private final /* synthetic */ Intent b;
    private final /* synthetic */ zzb c;

    u(zzb zzb, Intent intent, Intent intent2) {
        this.c = zzb;
        this.a = intent;
        this.b = intent2;
    }

    public final void run() {
        this.c.zzd(this.a);
        this.c.zza(this.b);
    }
}
