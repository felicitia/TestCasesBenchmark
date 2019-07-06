package com.google.android.gms.internal.measurement;

import android.os.RemoteException;

final class ds implements Runnable {
    private final /* synthetic */ zzeb a;
    private final /* synthetic */ dq b;

    ds(dq dqVar, zzeb zzeb) {
        this.b = dqVar;
        this.a = zzeb;
    }

    public final void run() {
        zzfa d = this.b.b;
        if (d == null) {
            this.b.r().h_().a("Failed to reset data on the service; null service");
            return;
        }
        try {
            d.zzd(this.a);
        } catch (RemoteException e) {
            this.b.r().h_().a("Failed to reset data on the service", e);
        }
        this.b.J();
    }
}
