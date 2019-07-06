package com.google.android.gms.internal.measurement;

import android.os.RemoteException;

final class du implements Runnable {
    private final /* synthetic */ zzeb a;
    private final /* synthetic */ dq b;

    du(dq dqVar, zzeb zzeb) {
        this.b = dqVar;
        this.a = zzeb;
    }

    public final void run() {
        zzfa d = this.b.b;
        if (d == null) {
            this.b.r().h_().a("Discarding data. Failed to send app launch");
            return;
        }
        try {
            d.zza(this.a);
            this.b.a(d, null, this.a);
            this.b.J();
        } catch (RemoteException e) {
            this.b.r().h_().a("Failed to send app launch to the service", e);
        }
    }
}
