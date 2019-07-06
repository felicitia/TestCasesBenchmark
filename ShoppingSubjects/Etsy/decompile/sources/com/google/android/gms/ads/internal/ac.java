package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.ads.gv;
import com.google.android.gms.internal.ads.zzoq;

final class ac implements Runnable {
    private final /* synthetic */ zzoq a;
    private final /* synthetic */ zzbc b;

    ac(zzbc zzbc, zzoq zzoq) {
        this.b = zzbc;
        this.a = zzoq;
    }

    public final void run() {
        try {
            if (this.b.zzvw.zzadf != null) {
                this.b.zzvw.zzadf.zza(this.a);
                this.b.zzb(this.a.zzka());
            }
        } catch (RemoteException e) {
            gv.d("#007 Could not call remote method.", e);
        }
    }
}
