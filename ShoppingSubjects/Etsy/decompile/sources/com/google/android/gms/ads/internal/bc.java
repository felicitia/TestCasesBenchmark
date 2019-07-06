package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.ads.gv;
import com.google.android.gms.internal.ads.zzov;

final class bc implements Runnable {
    private final /* synthetic */ zzov a;
    private final /* synthetic */ zzq b;

    bc(zzq zzq, zzov zzov) {
        this.b = zzq;
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
