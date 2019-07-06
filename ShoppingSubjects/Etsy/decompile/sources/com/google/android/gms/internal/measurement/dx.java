package com.google.android.gms.internal.measurement;

import android.os.RemoteException;

final class dx implements Runnable {
    private final /* synthetic */ zzeb a;
    private final /* synthetic */ dq b;

    dx(dq dqVar, zzeb zzeb) {
        this.b = dqVar;
        this.a = zzeb;
    }

    public final void run() {
        zzfa d = this.b.b;
        if (d == null) {
            this.b.r().h_().a("Failed to send measurementEnabled to service");
            return;
        }
        try {
            d.zzb(this.a);
            this.b.J();
        } catch (RemoteException e) {
            this.b.r().h_().a("Failed to send measurementEnabled to the service", e);
        }
    }
}
