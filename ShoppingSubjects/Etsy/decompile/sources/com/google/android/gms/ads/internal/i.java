package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzjj;

final class i implements Runnable {
    private final /* synthetic */ zzjj a;
    private final /* synthetic */ int b;
    private final /* synthetic */ zzah c;

    i(zzah zzah, zzjj zzjj, int i) {
        this.c = zzah;
        this.a = zzjj;
        this.b = i;
    }

    public final void run() {
        synchronized (this.c.mLock) {
            this.c.zzb(this.a, this.b);
        }
    }
}
