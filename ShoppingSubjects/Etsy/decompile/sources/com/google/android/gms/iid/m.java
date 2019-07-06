package com.google.android.gms.iid;

import android.content.Intent;

final class m implements Runnable {
    private final /* synthetic */ Intent a;
    private final /* synthetic */ Intent b;
    private final /* synthetic */ zze c;

    m(zze zze, Intent intent, Intent intent2) {
        this.c = zze;
        this.a = intent;
        this.b = intent2;
    }

    public final void run() {
        this.c.handleIntent(this.a);
        this.c.zzf(this.b);
    }
}
