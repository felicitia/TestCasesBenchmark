package com.google.android.gms.internal.measurement;

import android.os.RemoteException;

final class dv implements Runnable {
    private final /* synthetic */ dm a;
    private final /* synthetic */ dq b;

    dv(dq dqVar, dm dmVar) {
        this.b = dqVar;
        this.a = dmVar;
    }

    public final void run() {
        long j;
        String str;
        String str2;
        String packageName;
        zzfa d = this.b.b;
        if (d == null) {
            this.b.r().h_().a("Failed to send current screen to service");
            return;
        }
        try {
            if (this.a == null) {
                j = 0;
                str = null;
                str2 = null;
                packageName = this.b.n().getPackageName();
            } else {
                j = this.a.c;
                str = this.a.a;
                str2 = this.a.b;
                packageName = this.b.n().getPackageName();
            }
            d.zza(j, str, str2, packageName);
            this.b.J();
        } catch (RemoteException e) {
            this.b.r().h_().a("Failed to send current screen to the service", e);
        }
    }
}
