package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.ads.gv;
import com.google.android.gms.internal.ads.zzoo;

final class aa implements Runnable {
    private final /* synthetic */ zzoo a;
    private final /* synthetic */ zzbc b;

    aa(zzbc zzbc, zzoo zzoo) {
        this.b = zzbc;
        this.a = zzoo;
    }

    public final void run() {
        try {
            if (this.b.zzvw.zzade != null) {
                this.b.zzvw.zzade.zza(this.a);
                this.b.zzb(this.a.zzka());
            }
        } catch (RemoteException e) {
            gv.d("#007 Could not call remote method.", e);
        }
    }
}
