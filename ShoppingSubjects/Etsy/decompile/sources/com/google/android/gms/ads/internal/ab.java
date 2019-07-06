package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.ads.gv;
import com.google.android.gms.internal.ads.zzov;

final class ab implements Runnable {
    private final /* synthetic */ zzov a;
    private final /* synthetic */ zzbc b;

    ab(zzbc zzbc, zzov zzov) {
        this.b = zzbc;
        this.a = zzov;
    }

    public final void run() {
        try {
            if (this.b.zzvw.zzadg != null) {
                this.b.zzvw.zzadg.zza(this.a);
                this.b.zzb(this.a.zzka());
            }
        } catch (RemoteException e) {
            gv.d("#007 Could not call remote method.", e);
        }
    }
}
