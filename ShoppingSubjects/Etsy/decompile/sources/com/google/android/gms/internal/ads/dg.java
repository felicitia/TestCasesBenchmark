package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.internal.ao;

final class dg implements Runnable {
    private final /* synthetic */ zzaef a;
    private final /* synthetic */ zzaeq b;
    private final /* synthetic */ zzafn c;

    dg(zzafn zzafn, zzaef zzaef, zzaeq zzaeq) {
        this.c = zzafn;
        this.a = zzaef;
        this.b = zzaeq;
    }

    public final void run() {
        zzaej zzaej;
        try {
            zzaej = this.c.zzb(this.a);
        } catch (Exception e) {
            ao.i().a((Throwable) e, "AdRequestServiceImpl.loadAdAsync");
            gv.c("Could not fetch ad response due to an Exception.", e);
            zzaej = null;
        }
        if (zzaej == null) {
            zzaej = new zzaej(0);
        }
        try {
            this.b.zza(zzaej);
        } catch (RemoteException e2) {
            gv.c("Fail to forward ad response.", e2);
        }
    }
}
