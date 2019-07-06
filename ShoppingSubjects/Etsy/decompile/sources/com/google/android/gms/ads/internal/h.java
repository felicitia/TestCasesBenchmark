package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzjj;

final class h implements Runnable {
    private final /* synthetic */ zzjj a;
    private final /* synthetic */ zzah b;

    h(zzah zzah, zzjj zzjj) {
        this.b = zzah;
        this.a = zzjj;
    }

    public final void run() {
        synchronized (this.b.mLock) {
            if (this.b.zzde()) {
                this.b.zze(this.a);
            } else {
                this.b.zzb(this.a, 1);
            }
        }
    }
}
